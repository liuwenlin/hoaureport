package com.hoau.hoaureport.module.baseinfo.server.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hoau.hbdp.framework.cache.DefaultStrongCache;
import com.hoau.hbdp.framework.cache.provider.IBatchCacheProvider;

/**
 * @author：高佳
 * @create：2015年6月3日 下午2:54:14
 * @description：行政区域缓存
 */
@Component
public class DistrictCache extends DefaultStrongCache<String, DistrictCacheProvider>{
	public static final String DISTRICT_UUID = "district";
	@Override
	public String getUUID() {
		return DISTRICT_UUID;
	}
	
	@Override
	@Autowired
	public void setCacheProvider(
			@Qualifier("districtCacheProvider") IBatchCacheProvider<String, DistrictCacheProvider> cacheProvider) {
		super.setCacheProvider(cacheProvider);
	}
}
