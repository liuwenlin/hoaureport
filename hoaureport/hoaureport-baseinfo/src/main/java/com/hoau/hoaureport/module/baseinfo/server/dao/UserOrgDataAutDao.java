package com.hoau.hoaureport.module.baseinfo.server.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserOrgDataAutEntity;

/**
 * @author：涂婷婷
 * @create：2015年7月20日
 * @description：
 */
@Repository
public interface UserOrgDataAutDao {

	/**
	 * 查询用户部门数据权限信息
	 * 
	 * @param UserOrgDataAutEntity
	 * @author 涂婷婷
	 * @date 2015年7月20日
	 * @update 查询
	 */
	public List<UserOrgDataAutEntity> queryUserOrgDataAut(
			UserOrgDataAutEntity userOrgDataAutEntity, RowBounds rowBounds);

	/**
	 * 查询用户部门数据权限信息记录数
	 * 
	 * @param UserOrgDataAutEntity
	 * @author 涂婷婷
	 * @date 2015年7月20日
	 * @update 查询COUNT
	 */
	public Long queryUserOrgDataAutCount(
			UserOrgDataAutEntity userOrgDataAutEntity);

	/**
	 * 添加用户部门数据权限信息
	 * 
	 * @param UserOrgDataAutEntity
	 * @author 涂婷婷
	 * @date 2015年7月20日
	 * @update 添加
	 */
	public void addUserOrgDataAut(UserOrgDataAutEntity userOrgDataAutEntity);

	/**
	 * 删除用户部门数据权限信息
	 * 
	 * @param UserOrgDataAutEntity
	 * @author 涂婷婷
	 * @date 2015年7月20日
	 * @update 删除
	 */
	public void deleteUserOrgDataAut(UserOrgDataAutEntity userOrgDataAutEntity);

	/**
	 * 根据用户和部门编码查询用户部门记录数
	 * 
	 * @param UserOrgDataAutEntity
	 * @author 涂婷婷
	 * @date 2015年7月21日
	 * @update 查询
	 */
	public List<UserOrgDataAutEntity> queryUserOrgCountByUserName(
			UserOrgDataAutEntity userOrgDataAutEntity);

	/**
	 * 根据用户查询用户部门信息
	 * 
	 * @param UserOrgDataAutEntity
	 * @author 涂婷婷
	 * @date 2015年7月21日
	 * @update 查询
	 */
	public List<UserOrgDataAutEntity> queryUserOrgListByUserName(
			Map<String, Object> map);

	/**
	 * 根据用户删除用户部门
	 * @param userName 用户名
	 * @param modifyUser 修改用户
	 * @param modifyDate 修改时间
	 * @author 高佳
	 * @date 2015年9月25日
	 * @update 
	 */
	public void deleteUserOrgDataAutByUserName(
			@Param("userName") String userName,@Param("modifyUser") String modifyUser,@Param("modifyDate") Date modifyDate);

}
