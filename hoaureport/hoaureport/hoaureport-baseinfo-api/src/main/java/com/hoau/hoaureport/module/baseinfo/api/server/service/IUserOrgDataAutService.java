package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserOrgDataAutEntity;

/**
 * 
 * @author 涂婷婷
 * @date 2015年7月20日
 * @description：用户部门service接口
 */
public interface IUserOrgDataAutService {
	/**
	 * 查询用户部门数据权限信息
	 * 
	 * @author 涂婷婷
	 * @date 2015年7月20日
	 * @update 查询
	 */
	public List<UserOrgDataAutEntity> queryUserOrgDataAut(
			UserOrgDataAutEntity userOrgDataAutEntity, int limit, int start);

	/**
	 * 查询用户部门数据权限信息记录数
	 * 
	 * @author 涂婷婷
	 * @date 2015年7月20日
	 * @update 查询COUNT
	 */
	public Long queryUserOrgDataAutCount(
			UserOrgDataAutEntity userOrgDataAutEntity);

	/**
	 * 添加用户部门数据权限信息
	 * 
	 * @author 涂婷婷
	 * @date 2015年7月20日
	 * @update 添加
	 */
	public void addUserOrgDataAut(UserOrgDataAutEntity userOrgDataAutEntity);

	/**
	 * 删除用户部门数据权限信息
	 * 
	 * @author 涂婷婷
	 * @date 2015年7月20日
	 * @update 删除
	 */
	public void deleteUserOrgDataAut(
			List<UserOrgDataAutEntity> userOrgDataAutList);

	/**
	 * 查询用户部门记录数
	 * 
	 * @author 涂婷婷
	 * @date 2015年7月21日
	 * @update 查询
	 */
	public List<UserOrgDataAutEntity> queryUserOrgCountByUserName(
			UserOrgDataAutEntity userOrgDataAutEntity);

	/**
	 * 根据用户查询用户部门信息
	 * 
	 * @author 涂婷婷
	 * @date 2015年7月21日
	 * @update 查询
	 */
	public List<UserOrgDataAutEntity> queryUserOrgListByUserName(String userName);
	
	
	/**
	 * 根据用户名删除用户部门信息
	 * @param userName
	 * @author 高佳
	 * @date 2015年9月25日
	 * @update 
	 */
	public void deleteUserOrgDataAutByUserName(String userName);

}
