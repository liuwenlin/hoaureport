package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.StationTimeInfo;

/** 
 *@Description:场站（时效）Vo
 *@author : 梁令
 *@date 创建时间：2017年1月11日 下午2:15:33 
 */
public class StationTimeVo {
	//返回场站(时效)参数
	private StationTimeInfo stationTimeParam;
	//返回场站(时效)集合
    private List<StationTimeInfo> stationTimeInfoList;
	public StationTimeInfo getStationTimeParam() {
		return stationTimeParam;
	}
	public void setStationTimeParam(StationTimeInfo stationTimeParam) {
		this.stationTimeParam = stationTimeParam;
	}
	public List<StationTimeInfo> getStationTimeInfoList() {
		return stationTimeInfoList;
	}
	public void setStationTimeInfoList(List<StationTimeInfo> stationTimeInfoList) {
		this.stationTimeInfoList = stationTimeInfoList;
	}

}
