package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.DeliverIntimeRate;
/**
 * 次日到达率
 * ClassName: IDeliverIntimeRateManageService 
 * @author 刘镇松
 * @date 2016年8月25日
 * @version V1.0
 */
public interface IDeliverIntimeRateManageService {
	/**
	 * 
	 * @Description:查询送货及时率信息
	 * @param 送货及时率实例
	 * @param 查询开始索引
	 * @param 查询条数
	 * @return List<DeliverIntimeRateInfo> 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	List<DeliverIntimeRate> queryDeliverIntimeRate(DeliverIntimeRate param,int start,int limit);


	/**
	 * 
	 * @Description:查询记录总数
	 * @param 送货及时率实例
	 * @return Long 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	Long queryDeliverIntimeRateCount(DeliverIntimeRate param);
	
	/**
	 * 
	 * @Description:增加送货及时率
	 * @param 送货及时率实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	void addDeliverIntimeRate(DeliverIntimeRate record);
	
	/**
	 * 
	 * @Description:在原纪录上更新送货及时率信息
	 * @param 送货及时率实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	void updateDeliverIntimeRate(DeliverIntimeRate record);
	
	/**
	 * 
	 * @Description:原纪录作废，更新后的记录作为新数据插入
	 * @param 送货及时率实例
	 * @return void 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	void repealAndAddDeliverIntimeRate(DeliverIntimeRate param);
	
	/**
	 * 
	 * @Description:纪录作废
	 * @param 送货及时率实例   
	 * @return 记录作废操作者编号 
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	String repealDeliverIntimeRate(DeliverIntimeRate param);
	
	/**
	 * 
	 * @Description:判断记录是否已存在
	 * @param 送货及时率实例
	 * @return boolean 已存在为true
	 * @author 文洁
	 * @date 2016年8月23日
	 */
	boolean isExist(DeliverIntimeRate param);
	
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

	public  Map<String,Object>  bathImplDeliverIntimeRate(String path) throws Exception;
	/**
	 * 
	 * @Description: 添加或者更新送货及时率
	 * @param @param info
	 * @param @param countMap
	 * @param @throws Exception   
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月25日
	 */
	public void addOrUpdateDeliverIntimeRate(DeliverIntimeRate info,Map<String,Long> countMap) throws Exception;

}
