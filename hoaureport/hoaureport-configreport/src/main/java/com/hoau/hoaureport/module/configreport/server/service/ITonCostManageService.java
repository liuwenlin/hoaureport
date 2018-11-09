package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.TonCostInfo;

/**
 * 吨成本信息管理服务接口
 * ClassName: ITonCostManageService 
 * @author 文洁
 * @date 2016年8月23日
 * @version V1.0
 */
public interface ITonCostManageService {
	/**
	 * 
	 * @Description:查询吨成本信息
	 * @param 吨成本实例
	 * @param 查询开始索引
	 * @param 查询条数
	 * @return List<TonCostInfo> 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	List<TonCostInfo> queryTonCostInfo(TonCostInfo param,int start,int limit);


	/**
	 * 
	 * @Description:查询记录总数
	 * @param 吨成本实例
	 * @return Long 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	Long queryTonCostInfoCount(TonCostInfo param);
	
	/**
	 * 
	 * @Description:增加吨成本
	 * @param 吨成本实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	void addTonCostInfo(TonCostInfo record);
	
	/**
	 * 
	 * @Description:在原纪录上更新吨成本信息
	 * @param 吨成本实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	void updateTonCostInfo(TonCostInfo record);
	
	/**
	 * 
	 * @Description:原纪录作废，更新后的记录作为新数据插入
	 * @param 吨成本实例
	 * @return void 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	void repealAndAddTonCostInfo(TonCostInfo param);
	
	/**
	 * 
	 * @Description:纪录作废
	 * @param 吨成本实例   
	 * @return 记录作废操作者编号 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	String repealTonCostInfo(TonCostInfo param);
	
	/**
	 * 
	 * @Description:判断记录是否已存在
	 * @param 吨成本实例
	 * @return boolean 已存在为true
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	boolean isExist(TonCostInfo param);
	
	/**
	 *  该方法会将错误的信息收集起来，返回给调用者
	 * @Description:处理从Excel里批量导入数据的操作
	 * @param @param path
	 * @param @return
	 * @param @throws Exception   
	 * @return Map<String,Object> Map<String,Object> addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月24日
	 */

	public  Map<String,Object>  bathImplTonCost(String path) throws Exception;
	/**
	 * 
	 * @Description: 添加或者更新吨成本
	 * @param @param info
	 * @param @param countMap
	 * @param @throws Exception   
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月25日
	 */
	public void addOrUpdateTonCost(TonCostInfo info,Map<String,Long> countMap) throws Exception;

}
