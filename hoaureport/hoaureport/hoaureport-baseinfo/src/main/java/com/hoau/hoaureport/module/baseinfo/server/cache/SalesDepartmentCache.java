package com.hoau.hoaureport.module.baseinfo.server.cache;

import org.springframework.stereotype.Component;

import com.hoau.hbdp.framework.cache.DefaultTTLRedisCache;

/**
 * @author 张贞献
 * @date 2015-7-24
 * @description：门店信息
 */

@Component
public class SalesDepartmentCache extends DefaultTTLRedisCache<SalesDepartmentCacheProvider>{
	public static final String SALESDEPARTMENT_UUID = "salesDepartment";
	@Override
	public String getUUID() {
		return SALESDEPARTMENT_UUID;
	}
	
}
