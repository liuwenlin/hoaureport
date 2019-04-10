package com.hoau.hoaureport.module.login.server.service;

/**
 * 用于灰度发布
 * @author：高佳
 * @create：2015年11月17日 上午11:25:44
 * @description：灰度发布service
 */
public interface IGatedLaunchService {
	/**
	 * 查询当前用户使用版本
	 * @return
	 * @author 高佳
	 * @param orgCode  组织编码
	 * @param userName 用户名
	 * @date 2015年11月17日
	 * @update 
	 */
	String getCurrentVersion(String userName, String orgCode);
	
	
	/**
	 * 设置cookie 当前版本
	 * @author 高佳
	 * @date 2015年12月23日
	 * @update 
	 */
	void setGatedLaunchVersion();

}
