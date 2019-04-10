package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.FleetInfo;
/**
 * 
 * ClassName: FleetManageService 
 * @author 刘镇松
 * @date 2016年8月17日
 * @version V1.0
 */
public interface IFleetManageService {
	/**
	 * 查询场站信息
	 * @return
	 */
	List<FleetInfo> queryFleetInfo(FleetInfo param,int start,int limit);
	/**
	 * 查询总记录数
	 * @param FleetVo
	 * @return
	 */
	Long queryFleetCount(FleetInfo param);
	/**
	 * 
	 * @Description: 添加场站信息
	 * @param @param info   
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	void addFleetInfo(FleetInfo info);
	/**
	 * 
	 * @Description: 更新场站信息
	 * @param @param info   
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	void updateFleetInfo(FleetInfo info);
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
	void repealAndAddInfo(FleetInfo param,String empCode);
}
