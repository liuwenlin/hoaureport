package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.CityPriceCard;

/**
 * 城市价卡信息管理服务接口
 * ClassName: ICityPriceCardManageService 
 * @author 文洁
 * @date 2016年12月15日日
 * @version V1.0
 */
public interface ICityPriceCardManageService {
	/**
	 * 
	 * @Description:查询城市价卡信息
	 * @param 城市价卡实例
	 * @param 查询开始索引
	 * @param 查询条数
	 * @return List<CityPriceCard> 
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	List<CityPriceCard> queryCityPriceCard(CityPriceCard param,int start,int limit);


	
	/**
	 * 
	 * @Description:通过明确的条件查询城市价卡信息（不支持模糊查询）
	 * @param 城市价卡实例
	 * @param 查询开始索引
	 * @param 查询条数
	 * @return List<CityPriceCard> 
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	List<CityPriceCard> queryCityPriceCardByExplicitCondition(CityPriceCard param,int start,int limit);
	/**
	 * 
	 * @Description:查询记录总数
	 * @param 城市价卡实例
	 * @return Long 
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	Long queryCityPriceCardCount(CityPriceCard param);
	
	/**
	 * 
	 * @Description:增加城市价卡
	 * @param 城市价卡实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	void addCityPriceCard(CityPriceCard record);
	
	/**
	 * 
	 * @Description:在原纪录上更新城市价卡信息
	 * @param 城市价卡实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	void updateCityPriceCard(CityPriceCard record);
	
	/**
	 * 
	 * @Description:原纪录作废，更新后的记录作为新数据插入
	 * @param 城市价卡实例
	 * @return void 
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	void repealAndAddCityPriceCard(CityPriceCard param);
	
	/**
	 * 
	 * @Description:纪录作废
	 * @param 城市价卡实例   
	 * @return 记录作废操作者编号 
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	String repealCityPriceCard(CityPriceCard param);
	
	/**
	 * 
	 * @Description:判断记录是否已存在
	 * @param 城市价卡实例
	 * @return boolean 已存在为true
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	boolean isExist(CityPriceCard param);
	
	/**
	 *  该方法会将错误的信息收集起来，返回给调用者
	 * @Description:处理从Excel里批量导入数据的操作
	 * @param  path
	 * @return Map<String,Object> Map<String,Object> addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	public  Map<String,Object>  bathImplCityPriceCard(String path) throws Exception;
	/**
	 * 
	 * @Description: 添加或者更新城市价卡所属信息
	 * @param info
	 * @param countMap
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	public void addOrUpdateCityPriceCard(CityPriceCard info,Map<String,Long> countMap) throws Exception;
	public void repealAllCityPriceCard();
}
