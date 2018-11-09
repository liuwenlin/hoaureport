package com.hoau.hoaureport.module.baseinfo.server.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hoau.hbdp.framework.cache.provider.ITTLCacheProvider;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.ResourceEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.SSOEntityConvert;
import com.hoau.hoaureport.module.baseinfo.server.dao.ResourceDao;
import com.hoau.sso.module.api.server.service.impl.SSOResourceService;

/**
 * 功能权限缓存数据提供对象
 * @author 高佳
 * @date 2015年6月8日
 */
@Component
public class ResourceMenuCacheProvider implements ITTLCacheProvider<List<ResourceEntity>>{
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private SSOResourceService ssoResourceService;
	@Override
	public List<ResourceEntity> get(String key) {
		/*Map<String,Object> map = new HashMap<String,Object>();
		map.put("parentCode", key);
		map.put("resActive", BaseinfoConstants.ACTIVE);
		map.put("parentActive", BaseinfoConstants.ACTIVE);
		List<ResourceEntity> resMenus = resourceDao.queryDirectChildResourceByCode(map);
		return resMenus;*/
		return SSOEntityConvert.resourceConvert(ssoResourceService.queryResourceByParent(key));
	}
}
