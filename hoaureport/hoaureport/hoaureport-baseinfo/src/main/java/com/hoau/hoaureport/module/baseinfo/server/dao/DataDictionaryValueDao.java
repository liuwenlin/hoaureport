package com.hoau.hoaureport.module.baseinfo.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.DataDictionaryEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.DataDictionaryValueEntity;

/**
 * @author：李旭锋
 * @create：2015年6月5日 下午5:26:10
 * @description：
 */
@Repository
public interface DataDictionaryValueDao {
	/**
	 * 
	 * @param dictionaryEntity
	 * @author 李旭锋
	 * @date 2015年6月8日
	 * @update 添加一条
	 */
	public void saveDictionaryValueEntity(DataDictionaryValueEntity dictionaryEntity);

	/**
	 * 
	 * @param dictionaryEntity
	 * @author 李旭锋
	 * @date 2015年6月8日
	 * @update 删除
	 */
	public void deleteDictionaryValueEntity(DataDictionaryValueEntity dictionaryEntity);


	/**
	 * 按条件分页查询
	 * @param dataDictionaryEntity
	 * @param rowBounds
	 * @return
	 * @author 李旭锋
	 * @date 2015年6月9日
	 * @update 
	 */
	public List<DataDictionaryValueEntity> queryDictionaryValueEntityByExample(
			DataDictionaryValueEntity dataDictionaryValueEntity, RowBounds rowBounds);
	
	/**
	 * 按条件不分页查询
	 * @param dataDictionaryValueEntity
	 * @return
	 * @author 李旭锋
	 * @date 2015年8月5日
	 * @update 
	 */
	public List<DataDictionaryValueEntity> queryDictionaryValueEntityByExample(
			DataDictionaryValueEntity dataDictionaryValueEntity);
	/**
	 * 查询符合条件的有多少条数据
	 * @param dataDictionaryEntity
	 * @return
	 * @author 李旭锋
	 * @date 2015年6月9日
	 * @update 
	 */
	public Long queryTotalbyExample(DataDictionaryValueEntity dataDictionaryValueEntity);

	/**
	 * 查询最后更新版本
	 * @return
	 * @author 高佳
	 * @date 2015年6月15日
	 * @update 
	 */
	public Long queryLastVersionNo();

	/**
	 * 缓存数据查询
	 * @return
	 * @author 高佳
	 * @date 2015年6月15日
	 * @update 
	 */
	public List<DataDictionaryEntity> queryDataForCache();
	
	/**
	 * 查询APP最后更新版本
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月31日
	 * @update 
	 */
	public Long queryAppLastVersionNo();
	
	/**
	 * App缓存数据查询
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月31日
	 * @update 
	 */
	public List<DataDictionaryValueEntity> queryAppDataForCache();
	
	/**
	 * 根据词条名称和valueCode查valueName
	 * @param termsCode
	 * @param valueCode
	 * @return
	 * @author 龙海仁
	 * @date 2015年11月10日上午9:17:26
	 * @update 
	 */
	public String queryDataDictionaryValue(@Param("termsCode")String termsCode, @Param("valueCode")String valueCode);
}
