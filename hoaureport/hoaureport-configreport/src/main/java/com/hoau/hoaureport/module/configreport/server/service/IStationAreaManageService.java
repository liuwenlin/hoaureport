package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.StationAreaInfo;

/**
 * 场站辖区管理服务接口
 * ClassName: IStationAreaManageService 
 * @author 文洁
 * @date 2016年8月17日
 * @version V1.0
 */
public interface IStationAreaManageService {
	
	/**
	 * 
	 * @Description:查询场站辖区信息
	 * @param 场站辖区实例
	 * @param 查询开始索引
	 * @param 查询条数
	 * @return List<StationAreaInfo> 
	 * @author 文洁
	 * @date 2016年8月17日
	 */
	List<StationAreaInfo> queryStationAreaInfo(StationAreaInfo param,int start,int limit);


	/**
	 * 
	 * @Description:查询记录总数
	 * @param 场站辖区实例
	 * @return Long 
	 * @author 文洁
	 * @date 2016年8月17日
	 */
	Long queryStationAreaCount(StationAreaInfo param);
	
	/**
	 * 
	 * @Description:增加场站辖区
	 * @param 场站辖区实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年8月17日
	 */
	void addStationAreaInfo(StationAreaInfo record);
	
	/**
	 * 
	 * @Description:在原纪录上更新场站辖区信息
	 * @param 场站辖区实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年8月17日
	 */
	void updateStationAreaInfo(StationAreaInfo record);
	
	/**
	 * 
	 * @Description:原纪录作废，更新后的记录作为新数据插入
	 * @param 场站辖区实例
	 * @return void 
	 * @author 文洁
	 * @date 2016年8月17日
	 */
	void repealAndAddStationAreaInfo(StationAreaInfo param);
	
	/**
	 * 
	 * @Description:纪录作废
	 * @param 场站辖区实例   
	 * @return 记录作废操作者编号 
	 * @author 文洁
	 * @date 2016年8月17日
	 */
	String repealStationAreaInfo(StationAreaInfo param);
	
	/**
	 * 
	 * @Description:判断记录是否已存在
	 * @param 场站辖区实例
	 * @return boolean 已存在为true
	 * @author 文洁
	 * @date 2016年8月17日
	 */
	boolean isExist(StationAreaInfo param);
	
	/**
	 *  该方法会将错误的信息收集起来，返回给调用者
	 * @Description:处理从Excel里批量导入数据的操作
	 * @param  path
	 * @return Map<String,Object> Map<String,Object> addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
	 * @author 文洁
	 * @date 2016年11月02日
	 */
	public  Map<String,Object>  bathImplStationAreaInfo(String path) throws Exception;
	/**
	 * 
	 * @Description: 添加或者更新场站辖区信息
	 * @param info
	 * @param countMap
	 * @author 文洁
	 * @date 2016年11月02日
	 */
	public void addOrUpdateStationAreaInfo(StationAreaInfo info,Map<String,Long> countMap) throws Exception;
	public void repealAllStationArea();
}
