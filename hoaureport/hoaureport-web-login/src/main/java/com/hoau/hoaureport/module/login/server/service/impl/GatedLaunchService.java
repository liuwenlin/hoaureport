package com.hoau.hoaureport.module.login.server.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.hoau.hbdp.framework.cache.CacheManager;
import com.hoau.hbdp.framework.cache.ICache;
import com.hoau.hbdp.framework.server.context.SessionContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.login.server.cache.GatedLaunchCache;
import com.hoau.hoaureport.module.login.server.service.IGatedLaunchService;
import com.hoau.hoaureport.module.login.shared.define.GatedLaunchConstant;
import com.hoau.hoaureport.module.util.BaseinfoConstants;

/**
 * @author：高佳
 * @create：2015年11月17日 上午11:29:09
 * @description：灰度发布service
 */
@Service
public class GatedLaunchService implements IGatedLaunchService{
	
	@SuppressWarnings("unchecked")
	@Override
	public String getCurrentVersion(String userName,String orgCode) {
		String key = userName+"#"+orgCode;
		ICache<String, String> cache = CacheManager.getInstance().getCache(GatedLaunchCache.UUID);
		return cache.get(key);
	}


	/**
	 * 设置灰度发布版本
	 * 
	 * @author 高佳
	 * @date 2015年11月17日
	 * @update
	 */
	public void setGatedLaunchVersion() {
		String userName = (String) SessionContext.getSession().getObject(
				BaseinfoConstants.FRAMEWORK_KEY_EMPCODE);
		String orgCode = (String) SessionContext.getSession().getObject(
				BaseinfoConstants.KEY_CURRENT_DEPTCODE);
		// 设置当前cookie中的灰度发布版本
		setCookie(GatedLaunchConstant.COOKIE_NAME_VERSION,
				this.getCurrentVersion(userName,orgCode));
	}

	/**
	 * 设置cookie
	 * 
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 * @author 高佳
	 * @date 2015年11月17日
	 * @update
	 */
	private void setCookie(String name, String value) {
		HttpServletResponse response = ServletActionContext.getResponse();
		StringUtil.defaultIfBlank(value);
		Cookie cookie = getCookie(name);
		if(cookie == null){
			cookie = new Cookie(name, value);
		}else{
			cookie.setValue(value);
		}
		cookie.setPath("/");// 同一个域名所有url cookie共享
		response.addCookie(cookie);
	}
	
	/**
	 * 获取cookie
	 * @param name
	 * @return
	 * @author 高佳
	 * @date 2015年11月17日
	 * @update 
	 */
	private Cookie getCookie(String name){
		HttpServletRequest request = ServletActionContext.getRequest();
		Cookie[] cookies = request.getCookies();
		if(!ArrayUtils.isEmpty(cookies)){
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		return null;
	}
}
