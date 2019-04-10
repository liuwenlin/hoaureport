package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.UnfulfilledTarget;

/**
 * DD未兑现目标值管理服务接口
 * ClassName: IUnfulfilledTargetManageService 
 * @author 文洁
 * @date 2016年12月20日
 * @version V1.0
 */
public interface IUnfulfilledTargetManageService {
	/**
	 * 
	 * @Description:查询DD未兑现目标值信息
	 * @param DD未兑现目标值实例
	 * @param 查询开始索引
	 * @param 查询条数
	 * @return List<UnfulfilledTarget> 
	 * @author 文洁
	 * @date 2016年12月20日
	 */
	List<UnfulfilledTarget> queryUnfulfilledTarget(UnfulfilledTarget param,int start,int limit);


	/**
	 * 
	 * @Description:查询记录总数
	 * @param DD未兑现目标值实例
	 * @return Long 
	 * @author 文洁
	 * @date 2016年12月20日
	 */
	Long queryUnfulfilledTargetCount(UnfulfilledTarget param);
	
	/**
	 * 
	 * @Description:增加DD未兑现目标值
	 * @param DD未兑现目标值实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年12月20日
	 */
	void addUnfulfilledTarget(UnfulfilledTarget record);
	
	/**
	 * 
	 * @Description:在原纪录上更新DD未兑现目标值信息
	 * @param DD未兑现目标值实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年12月20日
	 */
	void updateUnfulfilledTarget(UnfulfilledTarget record);
	
	/**
	 * 
	 * @Description:原纪录作废，更新后的记录作为新数据插入
	 * @param DD未兑现目标值实例
	 * @return void 
	 * @author 文洁
	 * @date 2016年12月20日
	 */
	void repealAndAddUnfulfilledTarget(UnfulfilledTarget param);
	
	/**
	 * 
	 * @Description:纪录作废
	 * @param DD未兑现目标值实例   
	 * @return 记录作废操作者编号 
	 * @author 文洁
	 * @date 2016年12月20日
	 */
	String repealUnfulfilledTarget(UnfulfilledTarget param);
	
	/**
	 * 
	 * @Description:判断记录是否已存在
	 * @param DD未兑现目标值实例
	 * @return boolean 已存在为true
	 * @author 文洁
	 * @date 2016年12月20日
	 */
	boolean isExist(UnfulfilledTarget param);
	
	/**
	 *  该方法会将错误的信息收集起来，返回给调用者
	 * @Description:处理从Excel里批量导入数据的操作
	 * @param path
	 * @return Map<String,Object> Map<String,Object> addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
	 * @author 文洁
	 * @date 2016年12月20日
	 */
	public  Map<String,Object>  bathImplUnfulfilledTarget(String path) throws Exception;
	
	/**
	 * 
	 * @Description: 添加或者更新DD未兑现目标值
	 * @param info
	 * @param countMap
	 * @return void 
	 * @author 文洁
	 * @date 2016年12月20日
	 */
	public void addOrUpdateUnfulfilledTarget(UnfulfilledTarget info,Map<String,Long> countMap) throws Exception;

}
