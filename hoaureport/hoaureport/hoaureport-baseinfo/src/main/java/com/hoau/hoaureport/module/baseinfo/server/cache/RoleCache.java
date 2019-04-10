package com.hoau.hoaureport.module.baseinfo.server.cache;

import com.hoau.hbdp.framework.cache.DefaultTTLRedisCache;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.RoleEntity;

/**
 * @author：高佳
 * @create：2015年6月12日 下午1:51:58
 * @description：角色信息缓存
 */
public class RoleCache extends DefaultTTLRedisCache<RoleEntity>{
	public static final String ROLE_CACHE_UUID = "role";
	@Override
	public String getUUID() {
		return ROLE_CACHE_UUID;
	}

}
