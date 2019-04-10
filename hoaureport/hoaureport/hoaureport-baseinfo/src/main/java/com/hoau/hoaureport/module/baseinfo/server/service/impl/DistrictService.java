package com.hoau.hoaureport.module.baseinfo.server.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoau.hbdp.framework.cache.CacheManager;
import com.hoau.hbdp.framework.cache.ICache;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IDistrictService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.DistrictEntity;
import com.hoau.hoaureport.module.baseinfo.server.cache.DistrictCache;
import com.hoau.hoaureport.module.baseinfo.server.cache.DistrictCacheProvider;
import com.hoau.hoaureport.module.baseinfo.server.dao.DistrictDao;

/**
 * @author：高佳
 * @create：2015年6月3日 下午2:51:00
 * @description：省市区service实现类
 */
@Service
public class DistrictService implements IDistrictService{
	@Autowired
	private DistrictDao districtDao;
	
	/**
	 * 获取缓存实例
	 * @return
	 * @author 高佳
	 * @date 2015年6月3日
	 * @update 
	 */
	@SuppressWarnings("unchecked")
	private ICache<String, List<DistrictEntity>> getCache(){
		return CacheManager.getInstance().getCache(DistrictCache.DISTRICT_UUID);
	}

	@Override
	public List<DistrictEntity> queryAllProvince() {
		//从缓存中获取省份信息
		List<DistrictEntity> provinces = getCache().get(DistrictCacheProvider.KEY_PROVINCES);
		return provinces;
	}
	
	public List<DistrictEntity> queryDistrictByEntity(DistrictEntity district){
		return districtDao.queryDistrictByEntity(district);
	}

	@Override
	public List<DistrictEntity> queryDistrictByEntity(DistrictEntity district,
			int limit, int start) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return districtDao.queryDistrictByEntity(district,rowBounds);
	}

	@Override
	public List<DistrictEntity> queryAllNation(int limit, int start) {
		RowBounds rowBounds = new RowBounds(start,limit);
		DistrictEntity district = new DistrictEntity();
		district.setDistrictType("COUNTRY");
		return districtDao.queryDistrictByEntity(district,rowBounds);
	}
	
}
