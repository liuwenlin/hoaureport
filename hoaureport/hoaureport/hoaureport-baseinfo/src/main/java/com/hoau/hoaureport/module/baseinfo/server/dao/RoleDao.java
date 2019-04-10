package com.hoau.hoaureport.module.baseinfo.server.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.RoleEntity;


/**
 * @author：涂婷婷
 * @create：2015年6月9日 
 * @description：
 */
@Repository
public interface RoleDao {
	
	/**
	 * 添加角色
	 * @param RoleEntity
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update 添加
	 */
	public void addRole(RoleEntity roleEntity);

	/**
	 * 删除选中角色
	 * @param RoleEntity
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update 删除
	 */
	public void deleteRole(RoleEntity roleEntity);

	/**
	 * 修改角色
	 * @param RoleEntity
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update 修改
	 */
	public void updateRole(RoleEntity roleEntity);
	
	/**
	 * 根据角色编号进行查询
	 * @param RoleEntity
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月8日
	 * @update 根据条件进行查询
	 */
	
	public RoleEntity queryRoleByCode(String code);		
	
	
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
	 * 根据条件进行查询
	 * @param RoleEntity
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月8日
	 * @update 根据条件进行查询
	 */
	public List<RoleEntity> queryRoleEntity(RoleEntity roleEntity);
	
	public Long queryRoleValueByEntityCount(RoleEntity roleEntity);

	/**
	 * 根据code查询角色信息(包含权限信息)
	 * @param map
	 * @return
	 * @author 高佳
	 * @date 2015年6月12日
	 * @update 
	 */
	public RoleEntity queryRoleResourceByCode(Map<String, Object> map);
	
	/**
	 * 根据条件进行查询
	 * @param RoleEntity
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月8日
	 * @update 根据角色编号和名称查询
	 */
	public List<RoleEntity> queryRoleByCodeAndName(RoleEntity roleEntity);
	
	
	/**
	 * 查询所有角色信息
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月30日
	 * @update 
	 */
	public List<RoleEntity> queryAllRoleInfo();
	
	/**
	 * 查询所有特许经营角色信息
	 * @return
	 * @author 涂婷婷
	 * @date 2015年9月14日
	 * @update 
	 */
	public List<RoleEntity> queryFranchiseAllRoleInfo();
	
	/**
	 * 查询角色条数
	 * @author 涂婷婷
	 * @date 2015年6月30日
	 */
	public Long queryRoleCount();
}
