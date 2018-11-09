package com.hoau.hoaureport.module.baseinfo.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.DataDictionaryEntity;

/**
 * @author：李旭锋
 * @create：2015年6月5日 下午5:25:50
 * @description：
 */
public interface DataDictionaryDao {
	
	/**
	 * 添加dictionaryentity
	 * @param dictionaryEntity
	 * @author 李旭锋
	 * @date 2015年6月5日
	 * @update 添加
	 */
	public void saveDictionaryEntity(DataDictionaryEntity dictionaryEntity);

	/**
	 * 
	 * @param dictionaryEntity
	 * @author 李旭锋
	 * @date 2015年6月8日
	 * @update 删除
	 */
	public void deleteDictionaryEntity(DataDictionaryEntity dictionaryEntity);


	
	/**
	 * 
	 * @param dictionaryEntity
	 * @return
	 * @author 李旭锋
	 * @date 2015年6月8日
	 * @update 根据条件进行查询
	 */
	public List<DataDictionaryEntity> queryDictionaryEntityByExample(DataDictionaryEntity dictionaryEntity, RowBounds rowBounds);
	

	/**
	 * 查询总数
	 * @return
	 * @author 李旭锋
	 * @date 2015年6月9日
	 * @update 
	 */
	public Long queryCountTotalByExample(DataDictionaryEntity dictionaryEntity);
	
	/**
	 * 
	 * @param code
	 * @return
	 * @author 李旭锋
	 * @date 2015年6月8日
	 * @update 根据code查询子节点
	 */
	public List<DataDictionaryEntity> queryParmasByParentCode(String code);
	
	
}
