package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.CommonUserEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserSearchConditionEntity;

/**
 * @author：李旭锋
 * @create：2015年7月20日 下午7:05:43
 * @description：
 */
public interface ICommonUserService {

	/**
	 * 根据条件查询
	 * 
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月20日
	 * @update
	 */
	public List<CommonUserEntity> queryUserByParam(
			UserSearchConditionEntity userSearchConditionEntity);

	/**
	 * 查询总数
	 * 
	 * @param userSearchConditionEntity
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月20日
	 * @update
	 */
	public Long queryCountUserByParam(
			UserSearchConditionEntity userSearchConditionEntity);
}
