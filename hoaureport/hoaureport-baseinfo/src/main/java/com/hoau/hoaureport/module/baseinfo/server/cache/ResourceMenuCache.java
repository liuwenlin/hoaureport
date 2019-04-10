package com.hoau.hoaureport.module.baseinfo.server.cache;

import java.util.List;

import com.hoau.hbdp.framework.cache.DefaultTTLRedisCache;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.ResourceEntity;

/**
 * 功能权限缓存
 * @author 高佳
 * @date 2015年6月8日
 */
public class ResourceMenuCache extends DefaultTTLRedisCache<List<ResourceEntity>>{
	public static final String MENU_CACHE_UUID = "menuCache";
	public String getUUID() {
		return MENU_CACHE_UUID;
	}
	
}
