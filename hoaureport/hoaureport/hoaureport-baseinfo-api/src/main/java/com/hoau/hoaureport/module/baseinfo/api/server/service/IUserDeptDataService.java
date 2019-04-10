package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.List;

/**
 * @author：高佳
 * @create：2015年6月9日 下午2:40:17
 * @description：
 */
public interface IUserDeptDataService {

	/**
	 * 根据登录名查询用户部门
	 * @param userName
	 * @return
	 * @author 高佳
	 * @date 2015年6月15日
	 * @update 
	 */
	List<String> queryUserDeptDataByCode(String userName);

}
