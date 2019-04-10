package com.hoau.hoaureport.module.login.server.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author：高佳
 * @create：2015年11月17日 上午11:31:00
 * @description：灰度发布dao
 */
public interface GatedLaunchDao {

	/**
	 * 根据用户组织查询应用版本
	 * @param userName 用户名
	 * @param orgCode 组织编码
	 * @return
	 * @author 高佳
	 * @date 2015年11月17日
	 * @update 
	 */
	String queryAppVersionByUser(@Param("userName") String userName,@Param("orgCode") String orgCode);

}
