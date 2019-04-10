package com.hoau.hoaureport.module.frameworkimpl.server.context;

import java.util.List;

import com.hoau.hbdp.framework.exception.security.UserNotLoginException;
import com.hoau.hbdp.framework.server.context.SessionContext;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.context.WebApplicationContextHolder;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IOrgAdministrativeInfoService;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IUserDeptDataService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgAdministrativeInfoEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.frameworkimpl.shared.domain.CurrentInfo;
import com.hoau.hoaureport.module.util.BaseinfoConstants;

/**
 * 系统当前用户上下文信息类
 * @author 高佳
 * @date 2015年6月9日
 */
public final class HoauReportUserContext {

	private HoauReportUserContext() {
		super();
	}

	/**
	 * 获取当前会话的用户
	 * 
	 * @return UserEntity 当前用户
	 */
	public static UserEntity getCurrentUser() {
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		if (user == null) {
			throw new UserNotLoginException();
		}
		return user;
	}

	/**
	 * 获取当前用户设置的当前部门编码 getCurrentDeptCode
	 * 
	 * @return String 当前部门编码
	 * @since:
	 */
	public static String getCurrentDeptCode() {
		return(String) SessionContext.getSession()
				.getObject(BaseinfoConstants.KEY_CURRENT_DEPTCODE);
	}
	
	/**
	 * 获取当前用户设置的当前部门名称 getCurrentDeptName
	 * 
	 * @return String 当前部门名称
	 * @since:
	 */
	public static String getCurrentDeptName() {
		return(String) SessionContext.getSession()
				.getObject(BaseinfoConstants.KEY_CURRENT_DEPTNAME);
	}

	/**
	 * 获取当前用户设置的当前部门 getCurrentDept
	 * 
	 * @return OrgAdministrativeInfoEntity 当前部门对象
	 * @since:
	 */
	public static OrgAdministrativeInfoEntity getCurrentDept() {
		UserEntity user = getCurrentUser();
		String currentDeptCode = (String) SessionContext.getSession()
				.getObject(BaseinfoConstants.KEY_CURRENT_DEPTCODE);
		//华宇员工
		IOrgAdministrativeInfoService orgAdministrativeInfoService = (IOrgAdministrativeInfoService) WebApplicationContextHolder
				.getWebApplicationContext().getBean(
						"orgAdministrativeInfoService");
		return orgAdministrativeInfoService
				.queryOrgAdministrativeInfoByCode(currentDeptCode);
	}

	/**
	 * 获得当前用户所属的所有部门编码 getCurrentUserManagerDeptCodes
	 * 
	 * @return List<String> 当前用户所属部门的编码集合
	 * @since:
	 */
	public static List<String> getCurrentUserManagerDeptCodes() {
		IUserDeptDataService userDeptDataService = (IUserDeptDataService) WebApplicationContextHolder
				.getWebApplicationContext().getBean("userDeptDataService");
		UserEntity user = HoauReportUserContext.getCurrentUser();
		return userDeptDataService.queryUserDeptDataByCode(user
				.getUserName());
		 
	}

	/**
	 * 获得当前用户所属的所有部门 getCurrentUserManagerDepts
	 * 
	 * @return List<OrgAdministrativeInfoEntity> 当前用户所属所有部门对象集合
	 * @since:
	 */
	/*public static List<OrgAdministrativeInfoEntity> getCurrentUserManagerDepts() {
		IUserDeptDataService userDeptDataService = (IUserDeptDataService) WebApplicationContextHolder
				.getWebApplicationContext().getBean("userDeptDataService");
		IOrgAdministrativeInfoService orgAdministrativeInfoService = (IOrgAdministrativeInfoService) WebApplicationContextHolder
				.getWebApplicationContext().getBean(
						"orgAdministrativeInfoService");
		UserEntity user = BaseinfoConstants.getCurrentUser();
		List<String> userOrgCodes = userDeptDataService
				.queryUserDeptDataByCode(user.getEmpCode());
		String[] orgIds = new String[userOrgCodes.size()];
		orgIds = userOrgCodes.toArray(orgIds);
		return orgAdministrativeInfoService
				.queryOrgAdministrativeInfoBatchByCode(orgIds);
	}*/

	/**
	 * 获得当前系统的当前所有信息 getCurrentInfo
	 * 
	 * @return List<OrgAdministrativeInfoEntity> 当前用户所属所有部门对象集合
	 * @since:
	 */
	public static CurrentInfo getCurrentInfo() {
		UserEntity user = HoauReportUserContext.getCurrentUser();
		return new CurrentInfo(user);
	}
}
