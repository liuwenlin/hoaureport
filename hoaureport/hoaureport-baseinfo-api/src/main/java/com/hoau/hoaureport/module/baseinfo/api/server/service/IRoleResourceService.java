package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.Set;

/**
 * @author：高佳
 * @create：2015年6月13日 上午9:08:31
 * @description：角色资源权限service
 */
public interface IRoleResourceService {
	/**
	 * 更新角色资源权限信息
	 * @param roleCode 角色code
	 * @param addResourceCodes 新增权限code
	 * @param deleteResourceCodes 删除权限code
	 * @author 高佳
	 * @date 2015年6月13日
	 * @update 
	 */
	void updateRoleResource(String roleCode, Set<String> addResourceCodes,
			Set<String> deleteResourceCodes);
}
