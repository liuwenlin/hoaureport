package com.hoau.hoaureport.module.baseinfo.server.cache;

import com.hoau.hbdp.framework.cache.DefaultTTLRedisCache;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgAdministrativeInfoEntity;

/**
 * @author：高佳
 * @create：2015年6月12日 上午11:05:15
 * @description：组织编码缓存
 */
public class OrganizationOrgCodeCache extends DefaultTTLRedisCache<OrgAdministrativeInfoEntity>{
	public static final String ORG_CODE_CACHE_UUID = "organizationOrgCode";
	@Override
	public String getUUID() {
		return ORG_CODE_CACHE_UUID;
	}

}
