package com.hoau.hoaureport.module.baseinfo.server.cache;

import com.hoau.hbdp.framework.cache.DefaultTTLRedisCache;

/**
 * @author 龙海仁
 * @create：2015年11月10日 上午9:14:45
 * @description：
 */
public class DataDictionaryValueCache extends DefaultTTLRedisCache<String>{
		public final static String UUID = DataDictionaryValueCache.class.getName();
		public String getUUID() {
			return UUID;
		}
}