package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.FleetAreaInfo;

/**
 * 车队辖区管理服务接口
 * ClassName: IFleetAreaManageService 
 * @author 文洁
 * @date 2016年8月18日
 * @version V1.0
 */
public interface IFleetAreaManageService {
	
	/**
	 * 
	 * @Description:查询车队辖区信息
	 * @param 车队辖区实例
	 * @param 查询开始索引
	 * @param 查询条数
	 * @return List<FleetAreaInfo> 
	 * @author 文洁
	 * @date 2016年8月18日
	 */
	List<FleetAreaInfo> queryFleetAreaInfo(FleetAreaInfo param,int start,int limit);


	/**
	 * 
	 * @Description:查询记录总数
	 * @param 车队辖区实例
	 * @return Long 
	 * @author 文洁
	 * @date 2016年8月18日
	 */
	Long queryFleetAreaCount(FleetAreaInfo param);
	
	/**
	 * 
	 * @Description:增加车队辖区
	 * @param 车队辖区实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年8月18日
	 */
	void addFleetAreaInfo(FleetAreaInfo record);
	
	/**
	 * 
	 * @Description:在原纪录上更新车队辖区信息
	 * @param 车队辖区实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年8月18日
	 */
	void updateFleetAreaInfo(FleetAreaInfo record);
	
	/**
	 * 
	 * @Description:原纪录作废，更新后的记录作为新数据插入
	 * @param 车队辖区实例
	 * @return void 
	 * @author 文洁
	 * @date 2016年8月18日
	 */
	void repealAndAddFleetAreaInfo(FleetAreaInfo param);
	
	/**
	 * 
	 * @Description:纪录作废
	 * @param 车队辖区实例   
	 * @return 记录作废操作者编号 
	 * @author 文洁
	 * @date 2016年8月18日
	 */
	String repealFleetAreaInfo(FleetAreaInfo param);
	
	/**
	 * 
	 * @Description:判断记录是否已存在
	 * @param 车队辖区实例
	 * @return boolean 已存在为true
	 * @author 文洁
	 * @date 2016年8月18日
	 */
	boolean isExist(FleetAreaInfo param);
	
	/**
	 *  该方法会将错误的信息收集起来，返回给调用者
	 * @Description:处理从Excel里批量导入数据的操作
	 * @param  path
	 * @return Map<String,Object> Map<String,Object> addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
	 * @author 文洁
	 * @date 2016年11月02日
	 */
	public  Map<String,Object>  bathImplFleetAreaInfo(String path) throws Exception;
	/**
	 * 
	 * @Description: 添加或者更新车队辖区信息
	 * @param info
	 * @param countMap
	 * @author 文洁
	 * @date 2016年11月02日
	 */
	public void addOrUpdateFleetAreaInfo(FleetAreaInfo info,Map<String,Long> countMap) throws Exception;
	public void repealAllFleetArea();
}
