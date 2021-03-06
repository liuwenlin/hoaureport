package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.TransGoodsIntimerate;
/**
 * 必走货目标值
 * ClassName: ITransGoodsManageService 
 * @author 刘镇松
 * @date 2016年11月21日
 * @version V1.0
 */
public interface ITransGoodsManageService {
	/**
	 * 
	 * @Description:查询必走货目标值信息
	 * @param 必走货目标值实例
	 * @param 查询开始索引
	 * @param 查询条数
	 * @return List<DeliverIntimeRateInfo> 
	 * @author 刘镇松
	 * @date 2016年8月23日
	 */
	List<TransGoodsIntimerate> queryTransGoodsIntimerate(TransGoodsIntimerate param,int start,int limit);


	/**
	 * 
	 * @Description:查询记录总数
	 * @param 必走货目标值实例
	 * @return Long 
	 * @author 刘镇松
	 * @date 2016年8月23日
	 */
	Long queryTransGoodsIntimerateCount(TransGoodsIntimerate param);
	
	/**
	 * 
	 * @Description:增加必走货目标值
	 * @param 必走货目标值实例   
	 * @return void 
	 * @author 刘镇松
	 * @date 2016年8月23日
	 */
	void addTransGoodsIntimerate(TransGoodsIntimerate record);
	
	/**
	 * 
	 * @Description:在原纪录上更新必走货目标值信息
	 * @param 必走货目标值实例   
	 * @return void 
	 * @author 刘镇松
	 * @date 2016年8月23日
	 */
	void updateTransGoodsIntimerate(TransGoodsIntimerate record);
	
	/**
	 * 
	 * @Description:原纪录作废，更新后的记录作为新数据插入
	 * @param 必走货目标值实例
	 * @return void 
	 * @author 刘镇松
	 * @date 2016年8月23日
	 */
	void repealAndAddTransGoodsIntimerate(TransGoodsIntimerate param);
	
	/**
	 * 
	 * @Description:纪录作废
	 * @param 必走货目标值实例   
	 * @return 记录作废操作者编号 
	 * @author 刘镇松
	 * @date 2016年8月23日
	 */
	String repealTransGoodsIntimerate(TransGoodsIntimerate param);
	
	/**
	 * 
	 * @Description:判断记录是否已存在
	 * @param 必走货目标值实例
	 * @return boolean 已存在为true
	 * @author 刘镇松
	 * @date 2016年8月23日
	 */
	boolean isExist(TransGoodsIntimerate param);
	
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

	public  Map<String,Object>  bathImplTransGoodsIntimerate(String path) throws Exception;
	/**
	 * 
	 * @Description: 添加或者更新必走货目标值
	 * @param @param info
	 * @param @param countMap
	 * @param @throws Exception   
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月25日
	 */
	public void addOrUpdateTransGoodsIntimerate(TransGoodsIntimerate info,Map<String,Long> countMap) throws Exception;

}
