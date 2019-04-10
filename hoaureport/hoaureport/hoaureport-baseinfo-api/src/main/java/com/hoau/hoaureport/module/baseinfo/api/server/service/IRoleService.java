package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.RoleEntity;


/**
 * @author：涂婷婷
 * @create：2015年6月8日 
 * @description：角色service接口
 */
public interface IRoleService {
	/**
	 * 角色删除
	 * @param 
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update 
	 */
	public void deleteRole(RoleEntity roleEntity);
	
	/**
	 * 多条角色删除
	 * @param 
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update 
	 */
	public void deleteRoleByList(List<RoleEntity> roleEntityList);
	
	/**
	 * 角色修改
	 * @param
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update 
	 */
	public void updateRole(RoleEntity roleEntity);
	
	
	/**
	 * 根据角色编号查询角色名称
	 * @param RoleEntity
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月17日
	 * @update 根据条件进行查询
	 */
	public String queryNameByName(String code);
	
	
	/**
	 *  角色添加
	 * @param 
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update 
	 */
	public void addRole(RoleEntity roleEntity);
	
	/**
	 * 根据条件查询角色信息
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update 
	 */
	public List<RoleEntity> queryRole(RoleEntity roleEntity ,int limit,int start);
	
	public Long queryRoleValueByEntityCount(RoleEntity roleEntity);
	
	/**
	 * 根据code查询角色信息
	 * @param code
	 * @return
	 * @author 高佳
	 * @date 2015年6月12日
	 */
	public RoleEntity queryRoleByCode(String code);
	
	/**
	 * 查询所有角色信息
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月30日 
	 */
	public List<RoleEntity> queryAllRoleInfo();
	
	/**
	 * 查询所有特许经营角色信息
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月30日 
	 */
	public List<RoleEntity> queryAllFranchiseRoleInfo();
	
	/**
	 * 查询角色条数
	 * @author 涂婷婷
	 * @date 2015年6月30日
	 */

	public Long queryRoleCount();
}
