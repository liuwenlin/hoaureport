package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.FleetInfo;

/**
 * 
 * ClassName: FleetVo 
 * @author 刘镇松
 * @date 2016年8月17日
 * @version V1.0
 */
public class FleetVo {
	/**参数*/
	private FleetInfo fleetInfoParam;
	/**返回的场站集合*/
	private List<FleetInfo> fleetInfoList;
	
	public List<FleetInfo> getFleetInfoList() {
		return fleetInfoList;
	}

	public void setFleetInfoList(List<FleetInfo> fleetInfoList) {
		this.fleetInfoList = fleetInfoList;
	}

	public FleetInfo getFleetInfoParam() {
		return fleetInfoParam;
	}

	public void setFleetInfoParam(FleetInfo fleetInfoParam) {
		this.fleetInfoParam = fleetInfoParam;
	}

}
