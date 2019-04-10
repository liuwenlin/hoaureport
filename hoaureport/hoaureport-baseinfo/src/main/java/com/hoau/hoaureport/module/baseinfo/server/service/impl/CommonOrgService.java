package com.hoau.hoaureport.module.baseinfo.server.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoau.hoaureport.module.baseinfo.api.server.service.ICommonOrgService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.CommonOrgEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgSearchConditionEntity;
import com.hoau.hoaureport.module.baseinfo.server.dao.CommonOrgDao;

/**
 * @author：高佳
 * @create：2015年6月30日 下午2:00:59
 * @description：
 */
@Service
public class CommonOrgService implements ICommonOrgService{
	@Autowired
	private CommonOrgDao commonOrgDao;
	
	@Override
	public List<CommonOrgEntity> queryOrgByParam(
			OrgSearchConditionEntity orgSearchConditionEntity) {
		return commonOrgDao.queryOrgByParam(orgSearchConditionEntity,new RowBounds(orgSearchConditionEntity.getStart(),orgSearchConditionEntity.getLimit()));
	}

	@Override
	public Long countOrgByParam(
			OrgSearchConditionEntity orgSearchConditionEntity) {
		return commonOrgDao.countOrgByParam(orgSearchConditionEntity);
	}

}
