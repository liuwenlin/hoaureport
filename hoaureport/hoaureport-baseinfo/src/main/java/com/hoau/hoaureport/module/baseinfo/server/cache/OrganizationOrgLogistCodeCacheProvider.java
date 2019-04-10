package com.hoau.hoaureport.module.baseinfo.server.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hoau.hbdp.framework.cache.provider.ITTLCacheProvider;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgAdministrativeInfoEntity;
import com.hoau.hoaureport.module.baseinfo.server.dao.OrgAdministrativeInfoDao;
import com.hoau.hoaureport.module.util.BaseinfoConstants;


/**
 * @author：张贞献
 * @create：2015年8月3日 下午1:00:09
 * @description：物流编码缓存数据提供者
 */
@Component
public class OrganizationOrgLogistCodeCacheProvider implements ITTLCacheProvider<List<OrgAdministrativeInfoEntity>>{
	@Autowired
	private OrgAdministrativeInfoDao orgAdministrativeInfoDao;
	@Override
	public List<OrgAdministrativeInfoEntity> get(String code) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("logistCode", code);
		map.put("active",BaseinfoConstants.ACTIVE);
		map.put("childActive",BaseinfoConstants.ACTIVE);
		List<OrgAdministrativeInfoEntity> list = orgAdministrativeInfoDao.queryOrgAdministrativeInfoBylogistCode(map);
		return list;
	}

}
