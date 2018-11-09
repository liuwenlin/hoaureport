package com.hoau.hoaureport.module.baseinfo.server.cache;

import com.hoau.hbdp.framework.cache.DefaultTTLRedisCache;
import com.hoau.hbdp.framework.entity.IFunction;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.ResourceEntity;

/**
 * @author：高佳
 * @create：2015年6月10日 下午4:44:27
 * @description：资源权限uri缓存
 */
public class ResourceUriCache extends DefaultTTLRedisCache<ResourceEntity>{
	public static final String RESOURCE_URI_CACHE_UUID = IFunction.class.getName();
	@Override
	public String getUUID() {
		return RESOURCE_URI_CACHE_UUID;
	}

}
