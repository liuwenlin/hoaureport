package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.PlatNextdayReachRate;


/**
 * 平台次日送到率ManageService
 * ClassName: IPlatNextdayReachRateManageService 
 * @author 文洁
 * @date 2016年10月17日
 * @version V1.0
 */
public interface IPlatNextdayReachRateManageService {
	/**
	 * 
	 * @Description:查询平台次日送达率信息
	 * @param 平台次日送达率实例
	 * @param 查询开始索引
	 * @param 查询条数
	 * @return List<PlatNextdayReachRateInfo> 
	 * @author 文洁
	 * @date 2016年10月17日
	 */
	List<PlatNextdayReachRate> queryPlatNextdayReachRate(PlatNextdayReachRate param,int start,int limit);


	/**
	 * 
	 * @Description:查询记录总数
	 * @param 平台次日送达率实例
	 * @return Long 
	 * @author 文洁
	 * @date 2016年10月17日
	 */
	Long queryPlatNextdayReachRateCount(PlatNextdayReachRate param);
	
	/**
	 * 
	 * @Description:增加平台次日送达率
	 * @param 平台次日送达率实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年10月17日
	 */
	void addPlatNextdayReachRate(PlatNextdayReachRate record);
	
	/**
	 * 
	 * @Description:在原纪录上更新平台次日送达率信息
	 * @param 平台次日送达率实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年10月17日
	 */
	void updatePlatNextdayReachRate(PlatNextdayReachRate record);
	
	/**
	 * 
	 * @Description:原纪录作废，更新后的记录作为新数据插入
	 * @param 平台次日送达率实例
	 * @return void 
	 * @author 文洁
	 * @date 2016年10月17日
	 */
	void repealAndAddPlatNextdayReachRate(PlatNextdayReachRate param);
	
	/**
	 * 
	 * @Description:纪录作废
	 * @param 平台次日送达率实例   
	 * @return 记录作废操作者编号 
	 * @author 文洁
	 * @date 2016年10月17日
	 */
	String repealPlatNextdayReachRate(PlatNextdayReachRate param);
	
	/**
	 * 
	 * @Description:判断记录是否已存在
	 * @param 平台次日送达率实例
	 * @return boolean 已存在为true
	 * @author 文洁
	 * @date 2016年10月17日
	 */
	boolean isExist(PlatNextdayReachRate param);
	
	/**
	 *  该方法会将错误的信息收集起来，返回给调用者
	 * @Description:处理从Excel里批量导入数据的操作
	 * @param  path
	 * @return Map<String,Object> Map<String,Object> addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
	 * @author 文洁
	 * @date 2016年10月17日
	 */

	public  Map<String,Object>  bathImplPlatNextdayReachRate(String path) throws Exception;
	/**
	 * 
	 * @Description: 添加或者更新平台次日送达率
	 * @param info
	 * @param countMap
	 * @author 文洁
	 * @date 2016年10月17日
	 */
	public void addOrUpdatePlatNextdayReachRate(PlatNextdayReachRate info,Map<String,Long> countMap) throws Exception;

}
