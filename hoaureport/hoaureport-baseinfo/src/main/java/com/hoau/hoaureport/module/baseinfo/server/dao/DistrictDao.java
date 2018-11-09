package com.hoau.hoaureport.module.baseinfo.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.DistrictEntity;


/**
 * @author：高佳
 * @create：2015年6月3日 下午2:56:50
 * @description：
 */
@Repository
public interface DistrictDao {

	/**
	 * 查询最后更新时间
	 * @return
	 * @author 高佳
	 * @date 2015年6月3日
	 * @update 
	 */
	Long getLastUpdateTime();

	/**
	 * 查询热门城市
	 * @return
	 * @author 高佳
	 * @date 2015年6月3日
	 * @update 
	 */
	List<DistrictEntity> queryHotCitys();

	/**
	 * 根据条件查询行政区域
	 * @return
	 * @author 高佳
	 * @date 2015年6月3日
	 * @update 
	 */
	List<DistrictEntity> queryDistrictByEntity(DistrictEntity district);
	
	
	/**
	 * 根据条件查询行政区域
	 * @param district 查询条件
	 * @param rowBounds 分页参数
	 * @return
	 * @author 高佳
	 * @date 2015年7月1日
	 * @update 
	 */
	List<DistrictEntity> queryDistrictByEntity(DistrictEntity district,RowBounds rowBounds);
}
