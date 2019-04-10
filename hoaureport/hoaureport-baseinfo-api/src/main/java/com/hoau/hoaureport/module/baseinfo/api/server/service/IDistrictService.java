package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.DistrictEntity;


/**
 * @author：高佳
 * @create：2015年6月3日 下午2:47:30
 * @description：省市区service接口
 */
public interface IDistrictService {
	
	/**
	 * 查询所有省份
	 * @return
	 * @author 高佳
	 * @date 2015年6月3日
	 * @update 
	 */
	List<DistrictEntity> queryAllProvince();
	
	/**
	 * 根据实体查行政区域
	 * @param district
	 * @return
	 * @author 高佳
	 * @date 2015年6月5日
	 * @update 
	 */
	List<DistrictEntity> queryDistrictByEntity(DistrictEntity district);
	
	/**
	 * 根据实体查行政区域
	 * @param district
	 * @param limit 分页参数
	 * @param start 分页参数
	 * @return
	 * @author 高佳
	 * @date 2015年7月1日
	 * @update 
	 */
	List<DistrictEntity> queryDistrictByEntity(DistrictEntity district,int limit,int start);

	/**
	 * 查询所有国家
	 * @param limit
	 * @param start
	 * @return
	 * @author 高佳
	 * @date 2015年7月9日
	 * @update 
	 */
	List<DistrictEntity> queryAllNation(int limit, int start);
}
