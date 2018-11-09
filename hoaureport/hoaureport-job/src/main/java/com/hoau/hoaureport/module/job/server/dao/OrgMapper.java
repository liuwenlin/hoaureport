package com.hoau.hoaureport.module.job.server.dao;

import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.job.shared.domain.OrgEntity;

/**
 *
 * @author 莫涛
 * @date 2016年1月15日下午3:34:30
 */
@Repository
public interface OrgMapper {
	public void addOrg(OrgEntity orgEntity);
	public long getLastVersionNo();
	public void updateOrg(OrgEntity orgEntity);
	public OrgEntity queryOrgByCode(String orgCode);
}