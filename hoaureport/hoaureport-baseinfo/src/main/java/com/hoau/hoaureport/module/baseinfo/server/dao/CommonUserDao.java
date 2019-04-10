package com.hoau.hoaureport.module.baseinfo.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.CommonUserEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserSearchConditionEntity;

/**
 * @author：李旭锋
 * @create：2015年7月20日 下午7:14:42
 * @description：
 */
@Repository
public interface CommonUserDao {

	/**
	 * 根据条件查询用户信息
	 * @param userSearchConditionEntity
	 * @param rowBounds
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月20日
	 * @update 
	 */
	public List<CommonUserEntity> queryUserByParam(
			UserSearchConditionEntity userSearchConditionEntity,
			RowBounds rowBounds);
	
	/**
	 * 根据条件查询用户信息数量
	 * @param userSearchConditionEntity
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月20日
	 * @update 
	 */
	public Long countUserByParam(UserSearchConditionEntity userSearchConditionEntity);

}
