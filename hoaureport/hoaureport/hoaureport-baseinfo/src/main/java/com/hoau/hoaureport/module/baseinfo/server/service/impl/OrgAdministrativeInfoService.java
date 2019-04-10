package com.hoau.hoaureport.module.baseinfo.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoau.hbdp.framework.cache.CacheManager;
import com.hoau.hbdp.framework.cache.ICache;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IOrgAdministrativeInfoService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgAdministrativeInfoEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.PlatformEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.SalesDepartmentEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.TransferCenterEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.OrgAdministrativeInfoException;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.PlatformException;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.SalesDepartmentException;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.TransferCenterException;
import com.hoau.hoaureport.module.baseinfo.server.cache.OrganizationOrgCodeCache;
import com.hoau.hoaureport.module.baseinfo.server.cache.OrganizationOrgLogistCodeCache;
import com.hoau.hoaureport.module.baseinfo.server.cache.PlatformCache;
import com.hoau.hoaureport.module.baseinfo.server.cache.SalesDepartmentCache;
import com.hoau.hoaureport.module.baseinfo.server.cache.TransferCenterCache;
import com.hoau.hoaureport.module.baseinfo.server.dao.OrgAdministrativeInfoDao;
import com.hoau.hoaureport.module.util.BaseinfoConstants;

/**
 * @author：高佳
 * @create：2015年6月10日 下午2:32:54
 * @description：组织相关service
 */
@Service
public class OrgAdministrativeInfoService implements IOrgAdministrativeInfoService{
	@Autowired
	private OrgAdministrativeInfoDao orgAdministrativeInfoDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public OrgAdministrativeInfoEntity queryOrgAdministrativeInfoByCode(
			String currentDeptCode) {
		if(StringUtil.isBlank(currentDeptCode)){
			throw new OrgAdministrativeInfoException(OrgAdministrativeInfoException.ORGADMINISTRATIVEINFO_CODE_IS_NULL);
		}
		ICache<String, OrgAdministrativeInfoEntity> cache = CacheManager.getInstance().getCache(OrganizationOrgCodeCache.ORG_CODE_CACHE_UUID);
		OrgAdministrativeInfoEntity org = cache.get(currentDeptCode);
		if(org == null){
			throw new OrgAdministrativeInfoException(OrgAdministrativeInfoException.ORGADMINISTRATIVEINFO_NOT_EXIST);
		}
		return org;
	}

	@Override
	public List<OrgAdministrativeInfoEntity> queryOrgAdministrativeInfoBatchByCode(
			String[] orgIds) {
		List<OrgAdministrativeInfoEntity> orgs = new ArrayList<OrgAdministrativeInfoEntity>();
		for (String code : orgIds) {
			orgs.add(this.queryOrgAdministrativeInfoByCode(code));
		}
		return orgs;
	}

	@Override
	public List<OrgAdministrativeInfoEntity> queryOrgAdministrativeInfoByParentCode(
			String parentCode) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parentCode", parentCode);
		map.put("parentActive", BaseinfoConstants.ACTIVE);
		map.put("childActive", BaseinfoConstants.ACTIVE);
		List<OrgAdministrativeInfoEntity> orgs  = orgAdministrativeInfoDao.queryOrgAdministrativeInfoByParentCode(map);
		return orgs;
	}

	@Override
	public List<OrgAdministrativeInfoEntity> queryOrgAdministrativeInfo(
			OrgAdministrativeInfoEntity orgAdministrativeInfoEntity) {
		List<OrgAdministrativeInfoEntity> orgs = orgAdministrativeInfoDao.queryOrgAdministrativeInfo(orgAdministrativeInfoEntity);
		return orgs;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PlatformEntity queryPlatformEntityByCode(String currentDeptCode) {
		if(StringUtil.isBlank(currentDeptCode)){
			throw new PlatformException(PlatformException.PLATFORM_CODE_IS_NULL);
		}
		ICache<String, PlatformEntity> cache = CacheManager.getInstance().getCache(PlatformCache.PLATFORM_UUID);
		PlatformEntity platform = cache.get(currentDeptCode);
		if(platform == null){
			throw new PlatformException(PlatformException.PLATFORM_NOT_EXIST);
		}
		return platform;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SalesDepartmentEntity querySalesDepartmentEntityByCode(
			String currentDeptCode) {
		if(StringUtil.isBlank(currentDeptCode)){
			throw new SalesDepartmentException(SalesDepartmentException.SALESDEPARTMENT_CODE_IS_NULL);
		}
		ICache<String, SalesDepartmentEntity> cache = CacheManager.getInstance().getCache(SalesDepartmentCache.SALESDEPARTMENT_UUID);
		SalesDepartmentEntity salesDepartment = cache.get(currentDeptCode);
		if(salesDepartment == null){
			throw new SalesDepartmentException(SalesDepartmentException.SALESDEPARTMENT_NOT_EXIST);
		}
		return salesDepartment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TransferCenterEntity queryTransferCenterEntityByCode(
			String currentDeptCode) {
		if(StringUtil.isBlank(currentDeptCode)){
			throw new TransferCenterException(TransferCenterException.TRANSFERCENTER_CODE_IS_NULL);
		}
		ICache<String, TransferCenterEntity> cache = CacheManager.getInstance().getCache(TransferCenterCache.TRANSFERCENTER_UUID);
		TransferCenterEntity transferCenter = cache.get(currentDeptCode);
		if(transferCenter == null){
			throw new TransferCenterException(TransferCenterException.TRANSFERCENTER_NOT_EXIST);
		}
		return transferCenter;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrgAdministrativeInfoEntity> queryOrgAdministrativeInfoByLogistCode(
			String logistCode) {
		if(StringUtil.isBlank(logistCode)){
			throw new TransferCenterException(OrgAdministrativeInfoException.ORGADMINISTRATIVEINFO_LOGISTCODE_IS_NULL);
		}
		ICache<String, List<OrgAdministrativeInfoEntity>> cache = CacheManager.getInstance().getCache(OrganizationOrgLogistCodeCache.ORG_LOGISTCODE_CACHE_UUID);
		List<OrgAdministrativeInfoEntity> OrgAdministrativeInfoList = cache.get(logistCode);
		return OrgAdministrativeInfoList;
	}
	
}
