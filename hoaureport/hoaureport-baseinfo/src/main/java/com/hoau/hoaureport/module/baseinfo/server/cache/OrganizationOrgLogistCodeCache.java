package com.hoau.hoaureport.module.baseinfo.server.cache;

import com.hoau.hbdp.framework.cache.DefaultTTLRedisCache;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgAdministrativeInfoEntity;

/**
 * @author：张贞献
 * @create：2015年8月3日 上午1:05:15
 * @description：组织物流编码缓存
 */
public class OrganizationOrgLogistCodeCache extends DefaultTTLRedisCache<OrgAdministrativeInfoEntity>{
	public static final String ORG_LOGISTCODE_CACHE_UUID = "organizationOrgLogistCode";
	@Override
	public String getUUID() {
		return ORG_LOGISTCODE_CACHE_UUID;
	}

}
