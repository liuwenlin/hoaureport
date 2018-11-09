package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgRoleEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;

/**
 * @author：高佳
 * @create：2015年6月9日 下午7:02:47
 * @description：用户Service
 */
public interface IUserService {

	/**
	 * 根据用户名查询用户
	 * @param userName
	 * @return
	 * @author 高佳
	 * @date 2015年6月10日
	 * @update 
	 */
	UserEntity queryUserByLoginName(String userName);

	/**
	 * 根据用户名查询用户
	 * @param id
	 * @return
	 * @author 高佳
	 * @date 2015年6月10日
	 * @update 
	 */
	UserEntity queryUserByUserName(String id);

	/**
	 * 根据角色code查询用户
	 * @param roleCode
	 * @return
	 * @author 高佳
	 * @date 2015年6月13日
	 * @update 
	 */
	List<String> queryUserNameByRoleCode(String roleCode);

	/**
	 * 根据角色code查询用户组织信息
	 * @param roleCode
	 * @return
	 * @author 高佳
	 * @date 2015年6月13日
	 * @update 
	 */
	List<String> queryUserAndOrgCodesByRoleCodeForCache(String roleCode);
	
	/**
	 * 根据用户名查询组织角色信息
	 * @return
	 * @author 高佳
	 * @date 2015年7月1日
	 * @update 
	 */
	List<OrgRoleEntity> queryOrgRolesByUserName(String userName);

	/**
	 * 根据实体条件查询用户信息
	 * @param userEntity
	 * @param limit
	 * @param start
	 * @return
	 * @author 高佳
	 * @date 2015年7月23日
	 * @update 
	 */
	List<UserEntity> queryUserByEntity(UserEntity userEntity, int limit,
			int start);

	/**
	 * 根据条件统计用户信息
	 * @param userEntity
	 * @return
	 * @author 高佳
	 * @date 2015年7月23日
	 * @update 
	 */
	Long countUserByEntity(UserEntity userEntity);
	
	/**
	 * userEntity
	 * 
	 * @param userEntity
	 * @return
	 * @author 涂婷婷
	 * @date 2015年9月14日
	 * @update
	 */
	Long countFanchiseUserByEntity(UserEntity userEntity);

	/**
	 * 新增用户信息
	 * @param userEntity
	 * @author 高佳
	 * @date 2015年7月23日
	 * @update 
	 */
	void addUser(UserEntity userEntity);
	
	/**
	 * 新增特许经营用户信息
	 * @param userEntity
	 * @author 涂婷婷
	 * @date 2015年9月9日
	 * @update 
	 */
	void addEmployee(UserEntity userEntity);
	
	/**
	 * 根据用户名集合批量删除用户
	 * @param userNames
	 * @author 高佳
	 * @date 2015年7月23日
	 * @update 
	 */
	void deleteUserByUserName(List<String> userNames);
	
	/**
	 * 修改特许经营用户
	 * 
	 * @param userEntity
	 * @author 涂婷婷
	 * @date 2015年9月10日
	 * @update
	 */
	void updateEmployeeById(UserEntity userEntity);
	
	/**
	 * 查询特许经营用户根据id
	 * 
	 * @param id
	 * @author 涂婷婷
	 * @date 2015年9月10日
	 * @update
	 */
	UserEntity queryEmployeeInfoById(String id);
	
	/**
	 * 根据实体条件查询特许经营用户信息
	 * 
	 * @param userEntity
	 * @param limit
	 * @param start
	 * @return
	 * @author 涂婷婷
	 * @date 2015年9月14日
	 * @update
	 */
	List<UserEntity> queryFanchiseUserByEntity(UserEntity userEntity, int limit,int start); 


}
