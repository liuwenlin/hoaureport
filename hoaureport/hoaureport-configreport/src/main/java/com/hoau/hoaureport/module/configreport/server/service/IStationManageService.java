package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.StationInfo;
/**
 * 
 * @author 刘镇松
 *
 */
public interface IStationManageService {
	/**
	 * 查询场站信息
	 * @return
	 */
	List<StationInfo> queryStationInfo(StationInfo param,int start,int limit);
	/**
	 * 查询总记录数
	 * @param stationVo
	 * @return
	 */
	Long queryStationCount(StationInfo param);
	/**
	 * 
	 * @Description: 添加场站信息
	 * @param @param info   
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	void addStationInfo(StationInfo info);
	/**
	 * 
	 * @Description: 更新场站信息
	 * @param @param info   
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	void updateStationInfo(StationInfo info);
	/**
	 * 
	 * @Description: 作废老数据新增修改新数据
	 * @param @param param
	 * @param @param empCode   
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	void repealAndAddInfo(StationInfo param,String empCode);
}
