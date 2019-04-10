package com.hoau.hoaureport.module.login.server.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hoau.hbdp.framework.cache.provider.ITTLCacheProvider;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.login.server.dao.GatedLaunchDao;

/**
 * @author：高佳
 * @create：2015年12月23日 上午10:45:39
 * @description：
 */
@Component
public class GatedLaunchCacheProvider implements ITTLCacheProvider<String>{
	@Autowired
	private GatedLaunchDao gatedLaunchDao;
	@Override
	public String get(String key) {
		String[] s =key.split("#");
		String version = gatedLaunchDao.queryAppVersionByUser(s[0], s[1]);
		StringUtil.defaultIfBlank(version);
		return version;
	}

}
