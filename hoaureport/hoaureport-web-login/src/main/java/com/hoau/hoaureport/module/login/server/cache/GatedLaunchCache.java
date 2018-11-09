package com.hoau.hoaureport.module.login.server.cache;

import com.hoau.hbdp.framework.cache.DefaultTTLRedisCache;

/**
 * @author：高佳
 * @create：2015年12月23日 上午10:43:36
 * @description：
 */
public class GatedLaunchCache extends DefaultTTLRedisCache<String>{
	public static final String UUID = GatedLaunchCache.class.getName();
	@Override
	public String getUUID() {
		return UUID;
	}
	
}
