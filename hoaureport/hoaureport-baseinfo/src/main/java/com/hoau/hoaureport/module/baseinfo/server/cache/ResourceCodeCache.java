package com.hoau.hoaureport.module.baseinfo.server.cache;

import org.springframework.stereotype.Component;

import com.hoau.hbdp.framework.cache.DefaultTTLRedisCache;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.ResourceEntity;

/**
 * @author：高佳
 * @create：2015年6月8日 下午7:14:36
 * @description：权限缓存
 */
@Component
public class ResourceCodeCache extends DefaultTTLRedisCache<ResourceEntity>{
	public static final String RESOURCE_CODE_CACHE_UUID = "resourceCode";
	@Override
	public String getUUID() {
		return RESOURCE_CODE_CACHE_UUID;
	}

}
