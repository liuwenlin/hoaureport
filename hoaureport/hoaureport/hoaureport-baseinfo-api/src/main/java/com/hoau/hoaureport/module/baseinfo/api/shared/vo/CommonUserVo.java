package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import java.io.Serializable;
import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.CommonUserEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserSearchConditionEntity;


/**
 * @author：李旭锋
 * @create：2015年7月20日 下午6:44:31
 * @description：
 */
public class CommonUserVo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -8913301588204712911L;
	
	/**
	 *向前台传的user集合
	 */
	private List<CommonUserEntity> commonUserEntityList;
	
	/**
	 *用户实体
	 */
	private CommonUserEntity commonUserEntity;
	
	/**
	 *查询条件
	 */
	private UserSearchConditionEntity userSearchConditionEntity;

	public List<CommonUserEntity> getCommonUserEntityList() {
		return commonUserEntityList;
	}

	public void setCommonUserEntityList(List<CommonUserEntity> commonUserEntityList) {
		this.commonUserEntityList = commonUserEntityList;
	}

	public CommonUserEntity getCommonUserEntity() {
		return commonUserEntity;
	}

	public void setCommonUserEntity(CommonUserEntity commonUserEntity) {
		this.commonUserEntity = commonUserEntity;
	}

	public UserSearchConditionEntity getUserSearchConditionEntity() {
		return userSearchConditionEntity;
	}

	public void setUserSearchConditionEntity(
			UserSearchConditionEntity userSearchConditionEntity) {
		this.userSearchConditionEntity = userSearchConditionEntity;
	}
	
	

}
