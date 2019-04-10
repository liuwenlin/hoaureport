package com.hoau.hoaureport.module.baseinfo.server.dao;

import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.RoleResourceEntity;


/**
 * @author：高佳
 * @create：2015年6月13日 下午1:36:23
 * @description：
 */
@Repository
public interface RoleResourceDao {

	/**
	 * 删除角色资源信息
	 * @param deleteEntity
	 * @author 高佳
	 * @date 2015年6月13日
	 * @update 
	 */
	void deleteRoleResourceByRoleResource(RoleResourceEntity deleteEntity);

	/**
	 * 新增角色资源权限
	 * @param addEntity
	 * @author 高佳
	 * @date 2015年6月13日
	 * @update 
	 */
	void addRoleResource(RoleResourceEntity addEntity);

}
