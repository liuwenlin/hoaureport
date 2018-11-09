package com.hoau.hoaureport.module.baseinfo.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.hbdp.framework.cache.CacheManager;
import com.hoau.hbdp.framework.cache.ICache;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IEmployeeService;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IUserOrgDataAutService;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IUserOrgRoleService;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IUserService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.EmployeeEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgRoleEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserOrgDataAutEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.UserException;
import com.hoau.hoaureport.module.baseinfo.server.cache.UserCache;
import com.hoau.hoaureport.module.baseinfo.server.dao.UserDao;
import com.hoau.hoaureport.module.baseinfo.server.dao.UserOrgRoleDao;
import com.hoau.hoaureport.module.frameworkimpl.server.context.HoauReportUserContext;
import com.hoau.hoaureport.module.frameworkimpl.server.util.CryptoUtil;
import com.hoau.hoaureport.module.util.BaseinfoConstants;
import com.hoau.hoaureport.module.util.DataTrans;
import com.hoau.hoaureport.module.util.UUIDUtil;

/**
 * @author：高佳
 * @create：2015年6月10日 上午8:21:03
 * @description：用户service
 */
@Service
public class UserService implements IUserService {
	private static final Logger logger = LoggerFactory
			.getLogger(UserService.class);
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserOrgRoleDao userOrgRoleDao;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IUserOrgDataAutService userOrgDataAutService;
	@Autowired
	private IUserOrgRoleService userOrgRoleService;
	/*@Autowired
	private ISerialNumberService serialNumberService;*/

	@Override
	public UserEntity queryUserByLoginName(String userName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		map.put("active", BaseinfoConstants.ACTIVE);
		return userDao.queryUserByLoginName(map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserEntity queryUserByUserName(String userName) {
		UserEntity user = (UserEntity) CacheManager.getInstance()
				.getCache(UserCache.USER_CACHE_UUID).get(userName);
		return user;
	}

	@Override
	public List<String> queryUserNameByRoleCode(String roleCode) {
		if (StringUtil.isEmpty(roleCode)) {
			return new ArrayList<String>();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleCode", roleCode);
		map.put("userActive", BaseinfoConstants.ACTIVE);
		map.put("userRoleOrgActive", BaseinfoConstants.ACTIVE);
		return userDao.queryUserNameByRoleCode(map);
	}

	@Override
	public List<String> queryUserAndOrgCodesByRoleCodeForCache(String roleCode) {
		if (StringUtil.isEmpty(roleCode)) {
			return new ArrayList<String>();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleCode", roleCode);
		map.put("userRoleOrgActive", BaseinfoConstants.ACTIVE);
		return userDao.queryUserAndOrgCodesByRoleCodeForCache(map);
	}

	@Override
	public List<OrgRoleEntity> queryOrgRolesByUserName(String userName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("orgActive", BaseinfoConstants.ACTIVE);
		map.put("roleActive", BaseinfoConstants.ACTIVE);
		map.put("userRoleOrgActive", BaseinfoConstants.ACTIVE);
		return userOrgRoleDao.queryOrgRolesByUserName(map);
	}

	/**
	 * 根据实体条件查询用户信息
	 * 
	 * @param userEntity
	 * @param limit
	 * @param start
	 * @return
	 * @author 高佳
	 * @date 2015年7月23日
	 * @update
	 */
	@Override
	public List<UserEntity> queryUserByEntity(UserEntity userEntity, int limit,
			int start) {
		userEntity.setActive(BaseinfoConstants.ACTIVE);
		RowBounds rowBounds = new RowBounds(start, limit);
		return userDao.queryUserByEntity(userEntity, rowBounds);
	}

	/**
	 * 根据条件统计用户信息
	 * 
	 * @param userEntity
	 * @return
	 * @author 高佳
	 * @date 2015年7月23日
	 * @update
	 */
	@Override
	public Long countUserByEntity(UserEntity userEntity) {
		userEntity.setActive(BaseinfoConstants.ACTIVE);
		return userDao.countUserByEntity(userEntity);
	}

	/**
	 * 根据实体条件查询特许经营用户信息
	 * 
	 * @param userEntity
	 * @param limit
	 * @param start
	 * @return
	 * @author 涂婷婷
	 * @date 2015年9月14日
	 * @update
	 */
	@Override
	public List<UserEntity> queryFanchiseUserByEntity(UserEntity userEntity,
			int limit, int start) {
		userEntity.setActive(BaseinfoConstants.ACTIVE);
		userEntity.setIsEmp(BaseinfoConstants.NO);
		userEntity.setOrgName(HoauReportUserContext.getCurrentDeptCode());
		RowBounds rowBounds = new RowBounds(start, limit);
		return userDao.queryFanchiseUserByEntity(userEntity, rowBounds);
	}

	/**
	 * userEntity
	 * 
	 * @param userEntity
	 * @return
	 * @author 涂婷婷
	 * @date 2015年9月14日
	 * @update
	 */
	@Override
	public Long countFanchiseUserByEntity(UserEntity userEntity) {
		userEntity.setActive(BaseinfoConstants.ACTIVE);
		userEntity.setIsEmp(BaseinfoConstants.NO);
		userEntity.setOrgName(HoauReportUserContext.getCurrentDeptCode());
		return userDao.countFanchiseUserByEntity(userEntity);
	}

	/**
	 * 新增用户信息
	 * 
	 * @param userEntity
	 * @author 高佳
	 * @date 2015年7月23日
	 * @update
	 */
	@Override
	@Transactional
	public void addUser(UserEntity userEntity) {
		// 用户信息不能为空
		if (userEntity == null) {
			throw new UserException(UserException.USER_IS_NULL);
		}
		// 用户名不能为空
		if (StringUtil.isBlank(userEntity.getUserName())) {
			throw new UserException(UserException.USERNAME_NULL);
		}
		// 用户是否存在
		UserEntity entity = queryUserByLoginName(userEntity.getUserName());
		if (entity != null) {
			throw new UserException(UserException.USER_EXIST);
		}
		// 是否本公司用户不能为空
		if (StringUtil.isBlank(userEntity.getIsEmp())) {
			throw new UserException(UserException.IS_EMP_NULL);
		}
		if (DataTrans.flagToBool(userEntity.getIsEmp())) {
			// 是本公司员工
			if (StringUtil.isBlank(userEntity.getEmpCode())) {
				throw new UserException(UserException.EMPCODE_NULL);
			}
			// 查询员工信息
			EmployeeEntity employee = employeeService
					.queryEmployeeByEmpCode(userEntity.getEmpCode());
			if (employee == null) {
				throw new UserException(UserException.EMP_NULL);
			}
			userEntity.setId(UUIDUtil.getUUID());
			userEntity.setEmpName(employee.getEmpName());
			userEntity.setPassword(employee.getPassword());
			userEntity.setActive(BaseinfoConstants.ACTIVE);
			userEntity.setVersionNo(UUIDUtil.getVersion());
			userEntity.setCreateUser(HoauReportUserContext.getCurrentUser()
					.getUserName());
			userEntity.setCreateDate(new Date());
			userEntity.setModifyDate(new Date());
			userEntity.setModifyUser(HoauReportUserContext.getCurrentUser()
					.getUserName());
			// 保存用户信息
			userDao.addUser(userEntity);
			// 保存用户部门信息
			UserOrgDataAutEntity userOrgDataAutEntity = new UserOrgDataAutEntity();
			userOrgDataAutEntity.setOrgCode(employee.getDepartment().getCode());
			userOrgDataAutEntity.setUserName(userEntity.getUserName());
			userOrgDataAutEntity.setIncludeSubOrg(BaseinfoConstants.YES);
			try {
				userOrgDataAutService.addUserOrgDataAut(userOrgDataAutEntity);
			} catch (BusinessException e) {
				logger.error("保存用户部门信息失败:" + e.getErrorCode());
			}
		} else {
			// 非本公司员工
			/**
			 * 非本公司员工用户后续开发
			 */
			throw new UserException("暂不支持非本公司员工");
		}
	}

	/**
	 * 根据用户名集合批量删除用户
	 * 
	 * @param userNames
	 * @author 高佳
	 * @date 2015年7月23日
	 * @update
	 */
	@Override
	@Transactional
	public void deleteUserByUserName(List<String> userNames) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("modifyUser", HoauReportUserContext.getCurrentUser()
				.getUserName());
		map.put("active", BaseinfoConstants.INACTIVE);
		map.put("conditionAction", BaseinfoConstants.ACTIVE);
		for (String userName : userNames) {
			map.put("userName", userName);
			map.put("versionNo", UUIDUtil.getVersion());
			map.put("modifyDate", new Date());
			userDao.deleteUserByUserName(map);
			//删除用户角色信息
			userOrgRoleService.deleteUserAllOrgRole(userName);
			//删除用户部门信息
			userOrgDataAutService.deleteUserOrgDataAutByUserName(userName);
			// 失效缓存中的用户信息
			getUserCache().invalid(userName);
		}
		

	}

	@SuppressWarnings("unchecked")
	private ICache<String, UserEntity> getUserCache() {
		ICache<String, UserEntity> userCache = CacheManager.getInstance()
				.getCache(UserCache.USER_CACHE_UUID);
		return userCache;
	}

	@Override
	@Transactional
	public void addEmployee(UserEntity userEntity) {
		// 用户信息不能为空
		if (userEntity == null) {
			throw new UserException(UserException.USER_IS_NULL);
		}
		/*if (StringUtil.isBlank(userEntity.getUserName())) {
			throw new UserException(UserException.USER_CODE_NULL);
		}*/
		if (StringUtil.isBlank(userEntity.getPassword())) {
			throw new UserException(UserException.PASSWORD_NULL);
		}
		if (queryUserNameByUserName(userEntity.getUserName()) > 0) {
			throw new UserException(UserException.USER_EXIST);
		}
		String pass = CryptoUtil.getInstance().getMD5ofStr(
				userEntity.getPassword());
		userEntity.setId(UUIDUtil.getUUID());
		//userEntity.setUserName(serialNumberService.generateSerialNumber(SerialNumberRuleEnum.USER_NAME, new String[]{}));
		userEntity.setPassword(pass);
		userEntity.setIsEmp(BaseinfoConstants.NO);
		userEntity.setActive(BaseinfoConstants.ACTIVE);
		userEntity.setVersionNo(UUIDUtil.getVersion());
		userEntity.setCreateUser(HoauReportUserContext.getCurrentUser()
				.getUserName());
		userEntity.setCreateDate(new Date());
		userEntity.setModifyDate(new Date());
		userEntity.setModifyUser(HoauReportUserContext.getCurrentUser()
				.getUserName());
		// 保存用户信息
		userDao.addUser(userEntity);
		// 保存用户部门信息
		UserOrgDataAutEntity userOrgDataAutEntity = new UserOrgDataAutEntity();
		userOrgDataAutEntity.setOrgCode(userEntity.getOrgCode());
		userOrgDataAutEntity.setUserName(userEntity.getUserName());
		userOrgDataAutEntity.setIncludeSubOrg(BaseinfoConstants.YES);
		try {
			userOrgDataAutService.addUserOrgDataAut(userOrgDataAutEntity);
		} catch (BusinessException e) {
			logger.error("保存用户部门信息失败:" + e.getErrorCode());
		}
	}

	public int queryUserNameByUserName(String name) {
		return userDao.queryUserNameByUserName(name);
	}

	@Override
	@Transactional
	public void updateEmployeeById(UserEntity userEntity) {

		if (StringUtil.isBlank(userEntity.getUserName())) {
			throw new UserException(UserException.USER_CODE_NULL);
		}
		if (StringUtil.isBlank(userEntity.getEmpName())) {
			throw new UserException(UserException.EMPNAME_NULL);
		}
		Date date = new Date();
		userEntity.setPassword(CryptoUtil.getInstance().getMD5ofStr(
				userEntity.getPassword()));
		userEntity.setModifyDate(date);
		userEntity.setModifyUser(HoauReportUserContext.getCurrentUser()
				.getUserName());
		userEntity.setVersionNo(UUIDUtil.getVersion());
		// 修改用户部门信息
		userDao.updateEmployeeById(userEntity);
	}

	@Override
	public UserEntity queryEmployeeInfoById(String id) {
		return userDao.queryEmployeeInfoById(id);
	}

}
