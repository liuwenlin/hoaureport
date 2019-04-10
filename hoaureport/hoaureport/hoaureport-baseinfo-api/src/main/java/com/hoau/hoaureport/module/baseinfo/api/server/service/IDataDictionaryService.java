package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.DataDictionaryEntity;

/**
 * @author：李旭锋
 * @create：2015年6月8日 上午9:44:20
 * @description：
 */
public interface IDataDictionaryService {
	/**
	 * 删除一个数据字典
	 * 
	 * @param dictionEntity
	 * @author 李旭锋
	 * @date 2015年6月5日
	 * @update
	 */
	public void deleteDictionary(DataDictionaryEntity dictionEntity);

	/**
	 * query add update delete
	 */

	/**
	 * 添加一条数据字典
	 * 
	 * @param dictionEntity
	 * @author 李旭锋
	 * @date 2015年6月5日
	 * @update
	 */
	public void addDictionary(DataDictionaryEntity dictionEntity);

	/**
	 * 按传入条件查询符合添加的数据字典信息
	 * 
	 * @return
	 * @author 李旭锋
	 * @date 2015年6月5日
	 * @update
	 */
	public List<DataDictionaryEntity> queryDictionaryByExample(
			DataDictionaryEntity dictionaryEntityint, int limit, int start);

	/**
	 * 条件查询总数
	 * 
	 * @param dictionaryEntityint
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月23日
	 * @update
	 */
	public Long queryCountDictionaryByExaple(
			DataDictionaryEntity dictionaryEntityint);

	/**
	 * 查询其具有的子节点信息
	 * 
	 * @param code
	 *            父code值
	 * @return
	 * @author 李旭锋
	 * @date 2015年6月8日
	 * @update
	 */
	public List<DataDictionaryEntity> queryParmasByPrantsCode(String code);

	/**
	 * 查询所有数据字典信息
	 * 
	 * @return
	 * @author 高佳
	 * @date 2015年6月15日
	 * @update
	 */
	public List<DataDictionaryEntity> queryAllDataDictionary();

}
