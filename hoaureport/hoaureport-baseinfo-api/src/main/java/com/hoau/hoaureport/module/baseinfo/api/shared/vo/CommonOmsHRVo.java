package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import java.io.Serializable;
import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.CommonOmsHREntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OmsHRSearchConditionEntity;
/**
 *
 * @author 肖明明
 * @date 2016年1月23日下午5:42:31
 */
public class CommonOmsHRVo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 505722805522570596L;

	private CommonOmsHREntity commonOmsHREntity;
	
	private List<CommonOmsHREntity> commonOmsHRList;
	
	private OmsHRSearchConditionEntity omsHRSearchConditionEntity;

	public CommonOmsHREntity getCommonOmsHREntity() {
		return commonOmsHREntity;
	}

	public void setCommonOmsHREntity(CommonOmsHREntity commonOmsHREntity) {
		this.commonOmsHREntity = commonOmsHREntity;
	}

	public List<CommonOmsHREntity> getCommonOmsHRList() {
		return commonOmsHRList;
	}

	public void setCommonOmsHRList(List<CommonOmsHREntity> commonOmsHRList) {
		this.commonOmsHRList = commonOmsHRList;
	}

	public OmsHRSearchConditionEntity getOmsHRSearchConditionEntity() {
		return omsHRSearchConditionEntity;
	}

	public void setOmsHRSearchConditionEntity(
			OmsHRSearchConditionEntity omsHRSearchConditionEntity) {
		this.omsHRSearchConditionEntity = omsHRSearchConditionEntity;
	}
}
