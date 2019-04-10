package com.hoau.hoaureport.module.baseinfo.server.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgRoleEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserOrgRoleEntity;


/**
 * @author：高佳
 * @create：2015年7月1日 下午5:46:04
 * @description：
 */
@Repository
public interface UserOrgRoleDao {

	/**
	 * 根据用户名查询组织角色信息
	 * @param map
	 * @return
	 * @author 高佳
	 * @date 2015年7月1日
	 * @update 
	 */
	public List<OrgRoleEntity> queryOrgRolesByUserName(Map<String, Object> map);
	
	/**
	 * 新增用户组织角色信息
	 * @return
	 * @author涂婷婷
	 * @date 2015年7月9日
	 * @update 
	 */
	public void addUserOrgRole(UserOrgRoleEntity userOrgRoleEntity);

	/**
	 * 根据用户组织查询角色编码
	 * @param map
	 * @return
	 * @author 高佳
	 * @date 2015年7月21日
	 * @update 
	 */
	public Set<String> queryRoleCodeByUserOrg(Map<String, Object> map);

	/**
	 * 删除用户所有部门角色信息
	 * @param map
	 * @author 高佳
	 * @date 2015年7月21日
	 * @update 
	 */
	public void deleteUserAllOrgRole(HashMap<String, Object> map);

	/**
	 * 根据用户部门角色删除角色信息
	 * @param map
	 * @author 高佳
	 * @date 2015年7月22日
	 * @update 
	 */
	public void deleteUserOrgRole(HashMap<String, Object> map);

	/**
	 * 删除用户所有角色信息
	 * @param userName
	 * @author 高佳
	 * @param date 
	 * @param version 
	 * @param modifyUser 
	 * @date 2015年12月12日
	 * @update 
	 */
	public void deleteUserAllRole(@Param("userName") String userName,@Param("modifyUser") String modifyUser,@Param("versionNo") long versionNo,@Param("modifyDate") Date modifyDate);
	
}
