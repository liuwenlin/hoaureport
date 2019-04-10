package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.CommonOrgEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgSearchConditionEntity;

/**
 * @author：高佳
 * @create：2015年6月30日 下午1:49:37
 * @description：公共选择器组织service
 */
public interface ICommonOrgService {

	/**
	 * 根据条件查询组织信息
	 * @param orgSearchConditionEntity
	 * @return
	 * @author 高佳
	 * @date 2015年6月30日
	 * @update 
	 */
	List<CommonOrgEntity> queryOrgByParam(OrgSearchConditionEntity orgSearchConditionEntity);

	/**
	 * 
	 * @param orgSearchConditionEntity
	 * @return
	 * @author 高佳
	 * @date 2015年7月15日
	 * @update 
	 */
	Long countOrgByParam(OrgSearchConditionEntity orgSearchConditionEntity);
	
}
