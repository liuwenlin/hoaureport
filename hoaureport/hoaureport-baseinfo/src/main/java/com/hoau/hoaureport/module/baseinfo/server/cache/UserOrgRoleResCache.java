package com.hoau.hoaureport.module.baseinfo.server.cache;

import java.util.List;
import java.util.Set;

import com.hoau.hbdp.framework.cache.DefaultTTLRedisCache;

/**
 * @author：高佳
 * @create：2015年6月9日 下午7:49:34
 * @description：用户部门角色权限缓存
 */
public class UserOrgRoleResCache extends DefaultTTLRedisCache<List<Set<String>>>{
	public static final String USER_ORG_ROLE_RES_CACHE_UUID = "userOrgRoleResource";
	@Override
	public String getUUID() {
		return USER_ORG_ROLE_RES_CACHE_UUID;
	}

}
