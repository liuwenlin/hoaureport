package com.hoau.hoaureport.module.login.server.action;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.exception.security.UserNotLoginException;
import com.hoau.hbdp.framework.server.components.security.SecurityNonCheckRequired;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgAdministrativeInfoEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.common.server.constants.ItfConifgConstant;
import com.hoau.hoaureport.module.frameworkimpl.server.context.HoauReportUserContext;
import com.hoau.hoaureport.module.frameworkimpl.shared.domain.Cookie;
import com.hoau.hoaureport.module.login.server.interceptor.CookieNonCheckRequired;
import com.hoau.hoaureport.module.login.server.service.ILoginService;

/**
 * @author：高佳
 * @create：2015年6月8日 下午3:17:30
 * @description：登录Action
 */
@Controller
@Scope("prototype")
public class LoginAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ILoginService loginService;

	Logger log = Logger.getLogger(this.getClass());
	/**
	 * 当前用户
	 */
	private UserEntity currentUser;

	/**
	 * 当前部门
	 */
	private OrgAdministrativeInfoEntity currentDept;

	/**
	 * 当前用户管理部门编码集合
	 */
	private List<String> currentUserDeptCodes;

	/**
	 * 当前服务器端时间
	 */
	private Date currentServerTime;

	/**
	 * 待切换的当前部门编码
	 */
	private String currenUserDeptCode;

	/**
	 * 查询部门名
	 */
	private String deptName;

	/**
	 * 是否开发环境
	 */
	private boolean dev;

	/**
	 * 加密TOKEN
	 */
	private String requestToken = null;

	private String mac;

	// 当前用户所管理的所有部门
	private List<OrgAdministrativeInfoEntity> userManagerDepts;
	//早报首页uri
	private String homePageUri;
	//早报首页菜单ID
	private String homePageId;

	/**
	 * 跳转登录界面
	 * 
	 * @return
	 * @author 高佳
	 * @date 2015年5月13日
	 * @update
	 */
	@SecurityNonCheckRequired
	@CookieNonCheckRequired
	public String index() {
		try {
			HoauReportUserContext.getCurrentUser();
		} catch(UserNotLoginException e) {
			return "login";
		}			
		return this.returnSuccess();
	}

	@SecurityNonCheckRequired
	@CookieNonCheckRequired
	public String login() throws Exception {
		try {
			loginService.userLogin(currentUser.getUserName(),
					currentUser.getPassword());
			// 这时跳转到main.jsp 根据session生成cookie
			Cookie.saveCookie();
			return this.returnSuccess();
		} catch (BusinessException e) {
			return this.returnError(e);
		}
	}

	/**
	 * 获取当前登录用户信息
	 * 
	 * @return
	 * @author 高佳
	 * @date 2015年6月15日
	 * @update
	 */
	@JSON
	public String currentLoginUserInfo() {
		// 在线信息加入到缓存
		// 获得当前登录用户
		currentUser = HoauReportUserContext.getCurrentUser();
		currentUser.setOrgResCodes(null);
		currentUser.setOrgResUris(null);
		// 获得当前部门
		currentDept = HoauReportUserContext.getCurrentDept();
		// 当前用户管理部门编码集合
		currentUserDeptCodes = HoauReportUserContext.getCurrentUserManagerDeptCodes();
		// 服务器当前时间
		currentServerTime = new Date();
		return returnSuccess();
	}

	/**
	 * 查询当前可切换部门
	 * 
	 * @return
	 * @author 高佳
	 * @date 2015年6月17日
	 * @update
	 */
	@JSON
	public String currentUserChangeDepts() {
		userManagerDepts = loginService.queryCurrentUserChangeDepts(deptName,
				start, limit);
		if (userManagerDepts.size() > 0) {
			totalCount = (long) userManagerDepts.get(0).getVersionNo();
		} else {
			totalCount = 0L;
		}
		return returnSuccess();
	}

	/**
	 * 切换部门
	 * 
	 * @return
	 * @author 高佳
	 * @date 2015年6月17日
	 * @update
	 */
	@JSON
	public String changeCurrentDept() {
		try {
			// 修改部门
			loginService.changeCurrentDept(currenUserDeptCode);
			// 修改了session中存储的当前部门,生成cookie
			Cookie.saveCookie();
			return this.returnSuccess();
		} catch (BusinessException e) {
			return this.returnError(e);
		}
	}

	/**
	 * 通过CAS进入系统主页面
	 * @return
	 * @throws Exception
	 */
	@SecurityNonCheckRequired
	@CookieNonCheckRequired
	public String toReportByCas()throws Exception{
		try {
			currentUser = new UserEntity();
			// 获取用户名
			HttpServletRequest request = ServletActionContext.getRequest();
			AttributePrincipal principal = (AttributePrincipal) request
					.getUserPrincipal();
			System.out.println(principal + "：通过CAS进入了CRM系统！！！");
			String userName = principal.getName();
			currentUser.setUserName(userName);
			loginService.toReportByCas(currentUser);
			
			// 这时跳转到main.jsp 根据session生成cookie
			Cookie.saveCookie();
			return this.returnSuccess();
		} catch (BusinessException e) {
//			e.printStackTrace();
			log.error(e);
			return this.returnError(e);
		}catch (Exception e1){
			log.error(e1);
			return this.returnError(e1.getMessage());
		}
	}
	
	/**
	 * CAS登录验证
	 * 
	 * @return
	 * @author 273503
	 * @date 2016-6-30 下午3:54:05
	 * @update
	 */
	@SecurityNonCheckRequired
	@CookieNonCheckRequired
	public String loginByCas(){
		try {
			currentUser = new UserEntity();
			// 获取用户名
			HttpServletRequest request = ServletActionContext.getRequest();
			AttributePrincipal principal = (AttributePrincipal) request
					.getUserPrincipal();
			System.out.println(principal + "：通过CAS登录了管理中心系统！！！");
			String userName = principal.getName();
			currentUser.setUserName(userName);
			// 初始化用户信息
			loginService.toReportByCas(currentUser);
		} catch (BusinessException e) {
			return returnError(e);
		} catch (Exception e) {
			return returnError("获取CAS中工号异常");
		}
		return returnSuccess();
	}
	
	@SecurityNonCheckRequired
	@CookieNonCheckRequired
	public String main() {
		return returnSuccess();
	}

	/**
	 * 产值早报首页权限判断
	 * @return
	 */
	public String userHomeMapPage(){
		// 获得当前用户
		try {
			
			UserEntity user = (UserEntity) UserContext.getCurrentUser();
			Set<String> uris =user.getOrgResUris();
			if(uris !=null && uris.contains(ItfConifgConstant.MAP_HOMEPAGE_URI)){
				setHomePageUri(ItfConifgConstant.MAP_HOMEPAGE_URI);
				setHomePageId(ItfConifgConstant.MAP_HOMEPAGE_ID);
				return returnSuccess();
			}
		} catch (Exception e) {
			log.error("早报首页判断异常",e);
			return returnError("早报首页判断异常");
		}
		return returnError("无首页权限");
	}
	
	
	
	public UserEntity getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserEntity currentUser) {
		this.currentUser = currentUser;
	}

	public OrgAdministrativeInfoEntity getCurrentDept() {
		return currentDept;
	}

	public void setCurrentDept(OrgAdministrativeInfoEntity currentDept) {
		this.currentDept = currentDept;
	}

	public List<String> getCurrentUserDeptCodes() {
		return currentUserDeptCodes;
	}

	public void setCurrentUserDeptCodes(List<String> currentUserDeptCodes) {
		this.currentUserDeptCodes = currentUserDeptCodes;
	}

	public Date getCurrentServerTime() {
		return currentServerTime;
	}

	public void setCurrentServerTime(Date currentServerTime) {
		this.currentServerTime = currentServerTime;
	}

	public String getCurrenUserDeptCode() {
		return currenUserDeptCode;
	}

	public void setCurrenUserDeptCode(String currenUserDeptCode) {
		this.currenUserDeptCode = currenUserDeptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public boolean isDev() {
		return dev;
	}

	public void setDev(boolean dev) {
		this.dev = dev;
	}

	public String getRequestToken() {
		return requestToken;
	}

	public void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public List<OrgAdministrativeInfoEntity> getUserManagerDepts() {
		return userManagerDepts;
	}

	public void setUserManagerDepts(
			List<OrgAdministrativeInfoEntity> userManagerDepts) {
		this.userManagerDepts = userManagerDepts;
	}

	public String getHomePageUri() {
		return homePageUri;
	}

	public void setHomePageUri(String homePageUri) {
		this.homePageUri = homePageUri;
	}

	public String getHomePageId() {
		return homePageId;
	}

	public void setHomePageId(String homePageId) {
		this.homePageId = homePageId;
	}
	
}
