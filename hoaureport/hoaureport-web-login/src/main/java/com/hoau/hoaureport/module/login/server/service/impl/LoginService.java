package com.hoau.hoaureport.module.login.server.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoau.hbdp.framework.server.context.SessionContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IOrgAdministrativeInfoService;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IResourceService;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IUserMenuService;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IUserService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgAdministrativeInfoEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.frameworkimpl.server.context.HoauReportUserContext;
import com.hoau.hoaureport.module.frameworkimpl.server.util.CryptoUtil;
import com.hoau.hoaureport.module.login.server.service.IGatedLaunchService;
import com.hoau.hoaureport.module.login.server.service.ILoginService;
import com.hoau.hoaureport.module.login.shared.exception.LoginException;
import com.hoau.hoaureport.module.util.BaseinfoConstants;
import com.hoau.sso.module.api.server.service.impl.SSOUserService;

/**
 * @author：高佳
 * @create：2015年6月9日 下午6:46:59
 * @description：
 */
@Service
public class LoginService implements ILoginService {
	@Autowired
	private IUserService userService;
	@Autowired
	private IOrgAdministrativeInfoService orgAdministrativeInfoService;
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IUserMenuService userMenuService;
	@Autowired
	private SSOUserService ssoUserService;
	/**
	 * 灰度发布service
	 */
	@Autowired
	private IGatedLaunchService gatedLaunchService;

	@Override
	public UserEntity userLogin(String userName, String pwd)
			throws LoginException, UserException {
		// 验证用户
		UserEntity user = validate(userName, pwd);
		// 把登录用户ID、工号与默认部门编码存入session中
		// 存入用户ID
		SessionContext.setCurrentUser(user.getUserName());
		// 存入用户工号
		SessionContext.getSession().setObject(
				BaseinfoConstants.FRAMEWORK_KEY_EMPCODE, user.getUserName());
		// 设置组织信息
		setHoauUserOrgInfo(user.getEmployee().getDepartment().getCode());
		return user;
	}

	/**
	 * 设置华宇用户组织信息
	 * 
	 * @author 高佳
	 * @date 2015年11月17日
	 * @update
	 */
	private void setHoauUserOrgInfo(String orgCode) {
		// 华宇员工
		// 根据编码查询组织
		OrgAdministrativeInfoEntity currentDept = orgAdministrativeInfoService
				.queryOrgAdministrativeInfoByCode(orgCode);
		if (currentDept == null) {
			throw new LoginException(LoginException.CURRENT_DEPT_ISNOT_EXIST);
		}
		// 设置当前部编码到session中
		SessionContext.getSession().setObject(
				BaseinfoConstants.KEY_CURRENT_DEPTCODE, currentDept.getCode());
		// 设置当前部编码到session中
		SessionContext.getSession().setObject(
				BaseinfoConstants.KEY_CURRENT_DEPTNAME, currentDept.getName());
	}


	private UserEntity validate(String userName, String pwd)
			throws LoginException, UserException {
		if (StringUtils.isBlank(userName)) {
			throw new LoginException(LoginException.USER_NAME_NULL);
		}
		if (StringUtils.isBlank(pwd)) {
			throw new LoginException(LoginException.USER_PASSWORD_NULL);
		}
		// 应用OA的加密方式
		pwd = CryptoUtil.getInstance().getMD5ofStr(pwd);
		UserEntity user = userService.queryUserByUserName(userName);
		if (user == null) {
			throw new LoginException(LoginException.USER_NULL);
		}
		// 如果用户已经被禁用，则不能登录
		if (!BaseinfoConstants.ACTIVE.equals(user.getActive())) {
			throw new LoginException(LoginException.USER_DISABLE);
		}
		if (!pwd.equals(user.getPassword())) {
			throw new LoginException(LoginException.USER_PASSWORD_ERROR);
		}
		/*// 通过用户ID得到用户的完整信息
		user = userService.queryUserByUserName(userName);
		// 如果用户为空，则用户没有配置角色
		if (user == null) {
			throw new LoginException(LoginException.CURRENT_USER_NO_ROLE);
		}*/
		return user;
	}

	private UserEntity valiCasDate(String userName, String pwd)
			throws LoginException, UserException {
		if (StringUtils.isBlank(userName)) {
			throw new LoginException(LoginException.USER_NAME_NULL);
		}
//		if (StringUtils.isBlank(pwd)) {
//			throw new LoginException(LoginException.USER_PASSWORD_NULL);
//		}
		// 应用OA的加密方式
//		pwd = CryptoUtil.getInstance().getMD5ofStr(pwd);
		UserEntity user = userService.queryUserByUserName(userName);
		if (user == null) {
			throw new LoginException(LoginException.USER_NULL);
		}
		// 如果用户已经被禁用，则不能登录
		if (!BaseinfoConstants.ACTIVE.equals(user.getActive())) {
			throw new LoginException(LoginException.USER_DISABLE);
		}
//		if (!pwd.equals(user.getPassword())) {
//			throw new LoginException(LoginException.USER_PASSWORD_ERROR);
//		}
		/*// 通过用户ID得到用户的完整信息
		user = userService.queryUserByUserName(userName);
		// 如果用户为空，则用户没有配置角色
		if (user == null) {
			throw new LoginException(LoginException.CURRENT_USER_NO_ROLE);
		}*/
		return user;
	}
	
	
	
	@Override
	public void logout() {
		/**
		 * 失效当前session
		 */
		SessionContext.invalidateSession();
	}

	@Override
	public List<OrgAdministrativeInfoEntity> queryCurrentUserChangeDepts(
			String deptName, int start, int limit) {
		UserEntity user = HoauReportUserContext.getCurrentUser();
		Set<String> userOrgCodes = user.getOrgids();
		String[] orgIds = new String[userOrgCodes.size()];
		orgIds = userOrgCodes.toArray(orgIds);
		int length = start + limit;
		List<OrgAdministrativeInfoEntity> orgs = new ArrayList<OrgAdministrativeInfoEntity>();
		if (StringUtil.isNotBlank(deptName)) {
			orgs = orgAdministrativeInfoService
					.queryOrgAdministrativeInfoBatchByCode(orgIds);
			List<OrgAdministrativeInfoEntity> orgList = new ArrayList<OrgAdministrativeInfoEntity>();
			for (OrgAdministrativeInfoEntity org : orgs) {
				if (org.getName().indexOf(deptName) != -1) {
					orgList.add(org);
				}
			}
			if (length > orgList.size()) {
				length = orgList.size();
			}
			orgs = orgList.subList(start, length);
			length = orgList.size();
		} else {
			if (length > orgIds.length) {
				length = orgIds.length;
			}
			String[] pageOrgIds = Arrays.copyOfRange(orgIds, start, length);
			orgs = orgAdministrativeInfoService
					.queryOrgAdministrativeInfoBatchByCode(pageOrgIds);
			length = orgIds.length;
		}
		if (orgs.size() > 0) {
			orgs.get(0).setVersionNo((long) length);
		}
		return orgs;
	}

	@Override
	public void changeCurrentDept(String currenUserDeptCode) {
		// 存入默认部门编码
		setHoauUserOrgInfo(currenUserDeptCode);
	}

	@Override
	public void toReportByCas(UserEntity currentUser)throws LoginException, UserException {
		// 验证用户
		UserEntity user = valiCasDate(currentUser.getUserName(), null);
		// 把登录用户ID、工号与默认部门编码存入session中
		// 存入用户ID
		SessionContext.setCurrentUser(user.getUserName());
		// 存入用户工号
		SessionContext.getSession().setObject(
				BaseinfoConstants.FRAMEWORK_KEY_EMPCODE, user.getUserName());
		// 设置组织信息
		setHoauUserOrgInfo(user.getEmployee().getDepartment().getCode());
		System.out.println("CAS Login:"+user.getEmployee().getDepartment().getCode());
	}
}
