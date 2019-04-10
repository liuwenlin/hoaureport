package com.hoau.hoaureport.module.baseinfo.server.cache;

import org.springframework.stereotype.Component;

import com.hoau.hbdp.framework.cache.DefaultTTLRedisCache;

/**
 * @author 张贞献
 * @date 2015-7-24
 * @description：场站
 */
@Component
public class TransferCenterCache extends DefaultTTLRedisCache<TransferCenterCacheProvider>{
	public static final String TRANSFERCENTER_UUID = "transferCenter";
	@Override
	public String getUUID() {
		return TRANSFERCENTER_UUID;
	}
	
}
