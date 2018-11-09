package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import java.io.Serializable;
import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.CommonOrgEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgSearchConditionEntity;


/**
 * 组织前台传递值实体vo
 * @author 高佳
 * @date 2015年6月30日
 */
public class CommonOrgVo implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 2629160509193950089L;

	/**
	 * 传递到前台的组织集合
	 */
	List<CommonOrgEntity> commonOrgEntityList;
	
    
	/**
	 * 组织实体
	 */
	CommonOrgEntity commonOrgEntity;

	/**
	 * 查询条件
	 */
	OrgSearchConditionEntity orgSearchConditionEntity;
	

	public List<CommonOrgEntity> getCommonOrgEntityList() {
		return commonOrgEntityList;
	}

	public void setCommonOrgEntityList(List<CommonOrgEntity> commonOrgEntityList) {
		this.commonOrgEntityList = commonOrgEntityList;
	}

	public CommonOrgEntity getCommonOrgEntity() {
		return commonOrgEntity;
	}

	public void setCommonOrgEntity(CommonOrgEntity commonOrgEntity) {
		this.commonOrgEntity = commonOrgEntity;
	}

	public OrgSearchConditionEntity getOrgSearchConditionEntity() {
		return orgSearchConditionEntity;
	}

	public void setOrgSearchConditionEntity(
			OrgSearchConditionEntity orgSearchConditionEntity) {
		this.orgSearchConditionEntity = orgSearchConditionEntity;
	}

	
	
	
}
