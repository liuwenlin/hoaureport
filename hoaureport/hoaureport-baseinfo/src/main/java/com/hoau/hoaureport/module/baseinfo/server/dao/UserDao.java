package com.hoau.hoaureport.module.baseinfo.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserOrgResCodeUrisEntity;


/**
 * @author：高佳
 * @create：2015年6月6日 下午3:55:01
 * @description：
 */
@Repository
public interface UserDao {

	/**
	 * 查询用户信息(包含角色组织关系)
	 * @param map
	 * @return
	 * @author 高佳
	 * @date 2015年6月6日
	 * @update 
	 */
	UserEntity queryUserWithRoleIdAndOrgIdByUserName(Map<String, String> map);

	/**
	 * 根据用户部门查询用户权限
	 * @return
	 * @author 高佳
	 * @date 2015年6月9日
	 * @update 
	 */
	List<UserOrgResCodeUrisEntity> queryOrgResCodeUrisByCode(Map<String,Object> map);

	/**
	 * 根据登录名查询用户信息
	 * @param map
	 * @return
	 * @author 高佳
	 * @date 2015年6月10日
	 * @update 
	 */
	UserEntity queryUserByLoginName(Map<String, String> map);

	/**
	 * 根据角色编码查询用户名
	 * @param roleCode
	 * @return
	 * @author 高佳
	 * @date 2015年6月15日
	 * @update 
	 */
	List<String> queryUserNameByRoleCode(Map<String, Object> Map);

	/**
	 * 根据角色编码查询用户组织信息
	 * @param map
	 * @return
	 * @author 高佳
	 * @date 2015年6月15日
	 * @update 
	 */
	List<String> queryUserAndOrgCodesByRoleCodeForCache(Map<String, Object> map);

	/**
	 * 根据条件查询用户信息
	 * @param userEntity
	 * @param rowBounds
	 * @return
	 * @author 高佳
	 * @date 2015年7月23日
	 * @update 
	 */
	List<UserEntity> queryUserByEntity(UserEntity userEntity,
			RowBounds rowBounds);

	/**
	 * 根据条件统计记录条数
	 * @param userEntity
	 * @return
	 * @author 高佳
	 * @date 2015年7月23日
	 * @update 
	 */
	Long countUserByEntity(UserEntity userEntity);

	/**
	 * 新增用户信息
	 * @param userEntity
	 * @author 高佳
	 * @date 2015年7月23日
	 * @update 
	 */
	void addUser(UserEntity userEntity);

	/**
	 * 根据用户名删除用户
	 * @param map
	 * @author 高佳
	 * @date 2015年7月23日
	 * @update 
	 */
	void deleteUserByUserName(Map<String, Object> map);
	
	/**
	 * 根据用户查询记录数
	 * @param map
	 * @author 涂婷婷
	 * @date 2015年9月9日
	 * @update 
	 */
	int queryUserNameByUserName(String name);
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
	 * @return
	 * @author 涂婷婷
	 * @date 2015年9月10日
	 * @update
	 */
	UserEntity queryEmployeeInfoById(String id);

	/**
	 * 查询特许经营用户
	 * @param userEntity
	 * @param rowBounds
	 * @return
	 * @author 高佳
	 * @date 2015年9月25日
	 * @update 
	 */
	List<UserEntity> queryFanchiseUserByEntity(UserEntity userEntity,
			RowBounds rowBounds);

	/**
	 * 统计特许经营用户
	 * @param userEntity
	 * @return
	 * @author 高佳
	 * @date 2015年9月25日
	 * @update 
	 */
	Long countFanchiseUserByEntity(UserEntity userEntity);

}
