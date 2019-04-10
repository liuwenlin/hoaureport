package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.CommonOmsHREntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OmsHRSearchConditionEntity;

/**
 * 
 * @author 肖明明
 * @date 2016年1月23日下午5:13:46
 */
public interface ICommonOmsHRService {
	/**
	 * 根据人员信息查询（司机或者业务员）
	 * 
	 * @param omsHRSearchConditionEntity
	 * @return
	 * @author 肖明明
	 * @date 2016年1月23日下午5:21:34
	 * @update
	 */

	List<CommonOmsHREntity> queryEmployeeByParam(
			OmsHRSearchConditionEntity omsHRSearchConditionEntity);

	/**
	 * 根据条件人员信息查询（司机或者业务员） 信息总数
	 * 
	 * @param omsHRSearchConditionEntity
	 * @return
	 * @author 肖明明
	 * @date 2016年1月23日下午5:22:39
	 * @update
	 */
	Long queryCountEmployeeByParam(
			OmsHRSearchConditionEntity omsHRSearchConditionEntity);
}
