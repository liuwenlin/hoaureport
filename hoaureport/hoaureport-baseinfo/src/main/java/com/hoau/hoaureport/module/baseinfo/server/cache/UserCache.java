package com.hoau.hoaureport.module.baseinfo.server.cache;

import com.hoau.hbdp.framework.cache.DefaultTTLRedisCache;
import com.hoau.hbdp.framework.entity.IUser;

/**
 * @author：高佳
 * @create：2015年6月6日 下午3:48:29
 * @description：用户缓存
 */
public class UserCache extends DefaultTTLRedisCache<IUser>{
	public static final String USER_CACHE_UUID = IUser.class.getName();
	@Override
	public String getUUID() {
		return USER_CACHE_UUID;
	}

}
