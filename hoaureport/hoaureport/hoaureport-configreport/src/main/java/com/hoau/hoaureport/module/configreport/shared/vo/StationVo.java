package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.StationInfo;

/***
 * 
 * ClassName: StationVo 
 * @author 刘镇松
 * @date 2016年8月16日
 * @version V1.0
 */
public class StationVo {
	/**参数*/
	private StationInfo stationInfoParam;
	/**返回的场站集合*/
	private List<StationInfo> stationInfoList;
	
	public List<StationInfo> getStationInfoList() {
		return stationInfoList;
	}

	public void setStationInfoList(List<StationInfo> stationInfoList) {
		this.stationInfoList = stationInfoList;
	}

	public StationInfo getStationInfoParam() {
		return stationInfoParam;
	}

	public void setStationInfoParam(StationInfo stationInfoParam) {
		this.stationInfoParam = stationInfoParam;
	}

}
