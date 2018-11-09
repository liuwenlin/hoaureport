package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.DataDictionaryValueEntity;

/**
 * @author：李旭锋
 * @create：2015年6月8日 上午9:45:56
 * @description：
 */
public interface IDataDictionaryValueService {


	/**
	 * 添加一条数据字典值
	 * @param dictionEntity
	 * @author 李旭锋
	 * @date 2015年6月5日
	 * @update 
	 */
	public void addDictionaryValue(DataDictionaryValueEntity dictionEntity);


	/**
	 * 返回当前termscode下所有的数据
	 * @param termscode
	 * @return
	 * @author 李旭锋
	 * @date 2015年6月8日
	 * @update 
	 */
	public List<DataDictionaryValueEntity> queryParamByTermsCode(String termscode);

	/**
	 * 
	 * @param dataDictionaryEntity
	 * @param limit
	 * @param start
	 * @return
	 * @author 李旭锋
	 * @date 2015年6月9日
	 * @update 
	 */
	public List<DataDictionaryValueEntity> queryDataDictionaryValueByEntity(
			DataDictionaryValueEntity dataDictionaryValueEntity, int limit, int start);

	/**
	 * 
	 * @param dataDictionaryEntity
	 * @return
	 * @author 李旭锋
	 * @date 2015年6月9日
	 * @update 
	 */
	public Long queryDataDictionaryValueByEntityCount(
			DataDictionaryValueEntity dataDictionaryValueEntity);

	
	/**
	 * 删除多条数据
	 * @param dataDictionaryValuelist
	 * @author 李旭锋
	 * @date 2015年6月10日
	 * @update 
	 */
	public void deleteDataDictionaryValueList(List<DataDictionaryValueEntity> dataDictionaryValuelist);

	/**
	 * 获取数据字典最后更新版本号
	 * @return
	 * @author 高佳
	 * @date 2015年6月15日
	 * @update 
	 */
	public long getLastChangeVersionNo();
	
	/**
	 * 通过词条编码和值编码查询
	 * @return
	 * @author 李旭锋
	 * @date 2015年8月5日
	 * @update 
	 */
	public DataDictionaryValueEntity queryByTermscodeAndValueCode(String termscode,String valuecode);
	
	/**
	 * 通过名称查询
	 * @return
	 * @author 李旭锋
	 * @date 2015年8月5日
	 * @update 
	 */
	public DataDictionaryValueEntity queryByName(String name);
	
	/**
	 * 根据词条和valueCode查valueName
	 * @param termsCode
	 * @param valueCode
	 * @return
	 * @author 龙海仁
	 * @date 2015年11月10日上午9:30:30
	 * @update 
	 */
	public String queryValueNameByTermsCodeAndValueCode(String termsCode, String valueCode);


}
