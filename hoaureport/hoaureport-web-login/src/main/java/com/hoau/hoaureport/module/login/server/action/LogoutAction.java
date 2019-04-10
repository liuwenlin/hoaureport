package com.hoau.hoaureport.module.login.server.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hoaureport.module.frameworkimpl.shared.domain.Cookie;
import com.hoau.hoaureport.module.login.server.service.ILoginService;

/**
 * @author：高佳
 * @create：2015年6月8日 下午3:17:30
 * @description：注销Action
 */
@Controller
@Scope("prototype")
public class LogoutAction extends AbstractAction{
	
	@Autowired
	private ILoginService loginService;
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 注销
	 * @return
	 * @author 高佳
	 * @date 2015年6月17日
	 * @update 
	 */
	public String logout(){
		loginService.logout();
		//失效Cookie
		Cookie.invalidateCookie();
		return returnSuccess();
	}
	
}
