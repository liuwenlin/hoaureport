package com.hoau.hoaureport.module.baseinfo.server.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IUserOrgDataAutService;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IUserOrgRoleService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserOrgDataAutEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.UserOrgDateAutException;
import com.hoau.hoaureport.module.baseinfo.server.dao.UserOrgDataAutDao;
import com.hoau.hoaureport.module.frameworkimpl.server.context.HoauReportUserContext;
import com.hoau.hoaureport.module.util.BaseinfoConstants;
import com.hoau.hoaureport.module.util.UUIDUtil;

/**
 * @author：涂婷婷
 * @create：2015年7月21日 上午8:36:37
 * @description：
 */
@Service
public class UserOrgDataAutService implements IUserOrgDataAutService {
	@Autowired
	private IUserOrgRoleService userOrgRoleService;
	
	@Autowired
	private UserOrgDataAutDao userOrgDataAutDao;

	@Override
	public List<UserOrgDataAutEntity> queryUserOrgDataAut(
			UserOrgDataAutEntity userOrgDataAutEntity, int limit, int start) {
		userOrgDataAutEntity.setActive(BaseinfoConstants.ACTIVE);
		RowBounds rowBounds = new RowBounds(start, limit);
		return userOrgDataAutDao.queryUserOrgDataAut(userOrgDataAutEntity,
				rowBounds);
	}

	@Override
	public Long queryUserOrgDataAutCount(
			UserOrgDataAutEntity userOrgDataAutEntity) {
		userOrgDataAutEntity.setActive(BaseinfoConstants.ACTIVE);
		return userOrgDataAutDao.queryUserOrgDataAutCount(userOrgDataAutEntity);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void addUserOrgDataAut(UserOrgDataAutEntity userOrgDataAutEntity) {
		if (StringUtil.isEmpty(userOrgDataAutEntity.getUserName())) {
			throw new UserOrgDateAutException(
					UserOrgDateAutException.USERNAME_NULL);
		}
		if (StringUtil.isEmpty(userOrgDataAutEntity.getOrgCode())) {
			throw new UserOrgDateAutException(
					UserOrgDateAutException.ORGCODE_NULL);
		}
		if (!CollectionUtils.isEmpty(this
				.queryUserOrgCountByUserName(userOrgDataAutEntity))) {
			throw new UserOrgDateAutException(
					UserOrgDateAutException.USERORG_EXISTL);
			}		
		Date date = new Date();
		userOrgDataAutEntity.setVersionNo(UUIDUtil.getVersion());
		userOrgDataAutEntity.setId(UUIDUtil.getUUID());
		userOrgDataAutEntity.setActive(BaseinfoConstants.ACTIVE);
		userOrgDataAutEntity.setCreateDate(date);
		userOrgDataAutEntity.setCreateUser(HoauReportUserContext.getCurrentUser()
				.getUserName());
		userOrgDataAutEntity.setModifyDate(date);
		userOrgDataAutEntity.setModifyUser(HoauReportUserContext.getCurrentUser()
				.getUserName());
		userOrgDataAutDao.addUserOrgDataAut(userOrgDataAutEntity);

	}

	@Override
	@Transactional
	public void deleteUserOrgDataAut(
			List<UserOrgDataAutEntity> userOrgDataAutList) {
		Date date = new Date();
		UserOrgDataAutEntity queryParam = new UserOrgDataAutEntity();
		for (UserOrgDataAutEntity user : userOrgDataAutList)
		{
			user.setActive(BaseinfoConstants.NO);
			user.setVersionNo(UUIDUtil.getVersion());
			user.setModifyDate(date);
			user.setModifyUser(HoauReportUserContext.getCurrentUser()
					.getUserName());
			queryParam.setId(user.getId());
			List<UserOrgDataAutEntity> list = this.queryUserOrgDataAut(queryParam, 20, 0);
			//删除用户部门数据
			userOrgDataAutDao.deleteUserOrgDataAut(user);
			if(!CollectionUtils.isEmpty(list)){
				//删除用户部门角色数据
				userOrgRoleService.deleteUserAllOrgRole(list.get(0).getUserName(), list.get(0).getOrgCode());
			}
		}
	}

	@Override
	public List<UserOrgDataAutEntity> queryUserOrgCountByUserName(
			UserOrgDataAutEntity userOrgDataAutEntity) {
		userOrgDataAutEntity.setActive(BaseinfoConstants.ACTIVE);
		return userOrgDataAutDao
				.queryUserOrgCountByUserName(userOrgDataAutEntity);
	} 

	@Override
	public List<UserOrgDataAutEntity> queryUserOrgListByUserName(
			String userName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("active", BaseinfoConstants.ACTIVE);
		return userOrgDataAutDao.queryUserOrgListByUserName(map);
	}

	@Override
	public void deleteUserOrgDataAutByUserName(String userName) {
		userOrgDataAutDao.deleteUserOrgDataAutByUserName(userName,HoauReportUserContext.getCurrentUser().getUserName(),new Date());
	}

}
