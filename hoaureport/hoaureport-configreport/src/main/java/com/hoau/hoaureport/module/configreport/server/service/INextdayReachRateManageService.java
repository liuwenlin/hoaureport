package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.NextdayReachRate;
/**
 * 
 * ClassName: INextdayReachRateManageService 
 * @author 刘镇松
 * @date 2016年8月25日
 * @version V1.0
 */
public interface INextdayReachRateManageService {
	/**
	 * 
	 * @Description:查询次日送达率信息
	 * @param 次日送达率实例
	 * @param 查询开始索引
	 * @param 查询条数
	 * @return List<NextdayReachRateInfo> 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	List<NextdayReachRate> queryNextdayReachRate(NextdayReachRate param,int start,int limit);


	/**
	 * 
	 * @Description:查询记录总数
	 * @param 次日送达率实例
	 * @return Long 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	Long queryNextdayReachRateCount(NextdayReachRate param);
	
	/**
	 * 
	 * @Description:增加次日送达率
	 * @param 次日送达率实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	void addNextdayReachRate(NextdayReachRate record);
	
	/**
	 * 
	 * @Description:在原纪录上更新次日送达率信息
	 * @param 次日送达率实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	void updateNextdayReachRate(NextdayReachRate record);
	
	/**
	 * 
	 * @Description:原纪录作废，更新后的记录作为新数据插入
	 * @param 次日送达率实例
	 * @return void 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	void repealAndAddNextdayReachRate(NextdayReachRate param);
	
	/**
	 * 
	 * @Description:纪录作废
	 * @param 次日送达率实例   
	 * @return 记录作废操作者编号 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	String repealNextdayReachRate(NextdayReachRate param);
	
	/**
	 * 
	 * @Description:判断记录是否已存在
	 * @param 次日送达率实例
	 * @return boolean 已存在为true
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	boolean isExist(NextdayReachRate param);
	
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

	public  Map<String,Object>  bathImplNextdayReachRate(String path) throws Exception;
	/**
	 * 
	 * @Description: 添加或者更新次日送达率
	 * @param @param info
	 * @param @param countMap
	 * @param @throws Exception   
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月25日
	 */
	public void addOrUpdateNextdayReachRate(NextdayReachRate info,Map<String,Long> countMap) throws Exception;

}
