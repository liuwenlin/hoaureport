package com.hoau.hoaureport.module.baseinfo.server.cache;

import org.springframework.stereotype.Component;

import com.hoau.hbdp.framework.cache.DefaultTTLRedisCache;

/**
 * @author 张贞献
 * @date 2015-7-24
 * @description：平台缓存
 */
@Component
public class PlatformCache extends DefaultTTLRedisCache<PlatformCacheProvider>{
	public static final String PLATFORM_UUID = "platform";
	@Override
	public String getUUID() {
		return PLATFORM_UUID;
	}
}
