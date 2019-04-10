package com.hoau.hoaureport.module.job.server.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.shared.util.JsonUtils;
import com.hoau.hbdp.webservice.components.rest.define.RestErrorCodeConstants;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;
import com.hoau.hoaureport.module.common.server.constants.ItfConifgConstant;
import com.hoau.hoaureport.module.job.server.dao.OrgMapper;
import com.hoau.hoaureport.module.job.server.service.IOrgSyncService;
import com.hoau.hoaureport.module.job.shared.domain.OrgEntity;

/**
 * 同步部门
 * @author 莫涛
 * @date 2016年1月15日下午8:36:46
 */
@SuppressWarnings({"unchecked","deprecation"})
@Service
public class OrgSyncService implements IOrgSyncService {
	private static final Log LOG = LogFactory.getLog(OrgSyncService.class);
	@Autowired
	private OrgMapper orgDao;
	@Autowired
	private RestTemplate restTemplate;

	@Transactional
	@Override
	public void orgSync() {
		long versionNo = orgDao.getLastVersionNo();
		String url;
		String reponseStr;
		if(versionNo == 0){
			url = ItfConifgConstant.MDM_URL_ORG_ALL;
			reponseStr = restTemplate.getForObject(url,String.class);
		}else{
			url = ItfConifgConstant.MDM_URL_ORG_INCREMENT;
			reponseStr = restTemplate.getForObject(url,String.class, versionNo);
		}
		
		ResponseBaseEntity<List<OrgEntity>> result = 
				JsonUtils.toObject(reponseStr, 
								   ResponseBaseEntity.class, 
								   List.class,
								   OrgEntity.class);
		
		if (RestErrorCodeConstants.STATUS_SUCCESS.equals(result.getErrorCode())) {
			List<OrgEntity> orgEntitys = result.getResult();
			if (!CollectionUtils.isEmpty(orgEntitys)) {
				updateOrgs(versionNo, orgEntitys);
			}
			LOG.info("同步部门信息结束");
		} else {
			LOG.error("同步部门信息异常：" + result.getErrorMessage());
			throw new BusinessException("调用接口失败" + result.getErrorMessage());
		}
	}

	private void updateOrgs(long versionNo,
			List<OrgEntity> orgEntitys) {
		for (OrgEntity entity : orgEntitys) {
			//上级部门信息
			entity.setParentCode(entity.getParentDeptCode());
			entity.setParentName(entity.getParentDeptName());
			entity.setNature(entity.getDeptNature());
			
			if(versionNo == 0){//全量 同步 ，不用判断是否存在，直接插入
				
//				entity.setCreateTime(new Date());
//				entity.setCreateUserCode("job");
//				orgDao.addOrg(entity);
				
				if(orgDao.queryOrgByCode(entity.getCode()) == null){
					entity.setCreateTime(new Date());
					entity.setCreateUserCode("job");
					orgDao.addOrg(entity);
				}else{
					entity.setModifyTime(new Date());
					entity.setModifyUserCode("job");
					orgDao.updateOrg(entity);
				}
				
			}else{// 增量  要先判断是否存在，存在更新，不存在插入
				if(orgDao.queryOrgByCode(entity.getCode()) == null){
					entity.setCreateTime(new Date());
					entity.setCreateUserCode("job");
					orgDao.addOrg(entity);
				}else{
					entity.setModifyTime(new Date());
					entity.setModifyUserCode("job");
					orgDao.updateOrg(entity);
				}
			}
		}
	}
}