package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.DownTransferOnTimeRate;
/**
 * 下转准点率
 * ClassName: IDownTransferOnTimeRateManageService 
 * @author 文洁
 * @date 2016年11月9日
 * @version V1.0
 */
public interface IDownTransferOnTimeRateManageService {
	/**
	 * 
	 * @Description:查询发货准点率目标值信息
	 * @param 发货准点率目标值实例
	 * @param 查询开始索引
	 * @param 查询条数
	 * @return List<DownTransferOnTimeRateInfo> 
	 * @author 文洁
	 * @date 2016年11月9日
	 */
	List<DownTransferOnTimeRate> queryDownTransferOnTimeRate(DownTransferOnTimeRate param,int start,int limit);


	/**
	 * 
	 * @Description:查询记录总数
	 * @param 发货准点率目标值实例
	 * @return Long 
	 * @author 文洁
	 * @date 2016年11月9日
	 */
	Long queryDownTransferOnTimeRateCount(DownTransferOnTimeRate param);
	
	/**
	 * 
	 * @Description:增加发货准点率目标值
	 * @param 发货准点率目标值实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年11月9日
	 */
	void addDownTransferOnTimeRate(DownTransferOnTimeRate record);
	
	/**
	 * 
	 * @Description:在原纪录上更新发货准点率目标值信息
	 * @param 发货准点率目标值实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年11月9日
	 */
	void updateDownTransferOnTimeRate(DownTransferOnTimeRate record);
	
	/**
	 * 
	 * @Description:原纪录作废，更新后的记录作为新数据插入
	 * @param 发货准点率目标值实例
	 * @return void 
	 * @author 文洁
	 * @date 2016年11月9日
	 */
	void repealAndAddDownTransferOnTimeRate(DownTransferOnTimeRate param);
	
	/**
	 * 
	 * @Description:纪录作废
	 * @param 发货准点率目标值实例   
	 * @return 记录作废操作者编号 
	 * @author 文洁
	 * @date 2016年11月9日
	 */
	String repealDownTransferOnTimeRate(DownTransferOnTimeRate param);
	
	/**
	 * 
	 * @Description:判断记录是否已存在
	 * @param 发货准点率目标值实例
	 * @return boolean 已存在为true
	 * @author 文洁
	 * @date 2016年11月9日
	 */
	boolean isExist(DownTransferOnTimeRate param);
	
	/**
	 *  该方法会将错误的信息收集起来，返回给调用者
	 * @Description:处理从Excel里批量导入数据的操作
	 * @param @param path
	 * @param @return
	 * @param @throws Exception   
	 * @return Map<String,Object> Map<String,Object> addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
	 * @throws
	 * @author 文洁
	 * @date 2016年11月9日
	 */

	public  Map<String,Object>  bathImplDownTransferOnTimeRate(String path) throws Exception;
	/**
	 * 
	 * @Description: 添加或者更新发货准点率目标值
	 * @param @param info
	 * @param @param countMap
	 * @param @throws Exception   
	 * @return void 
	 * @throws
	 * @author 文洁
	 * @date 2016年11月9日
	 */
	public void addOrUpdateDownTransferOnTimeRate(DownTransferOnTimeRate info,Map<String,Long> countMap) throws Exception;

}
