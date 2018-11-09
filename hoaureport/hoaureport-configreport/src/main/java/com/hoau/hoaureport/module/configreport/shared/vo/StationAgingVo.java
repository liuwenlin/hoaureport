package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.StationAgingInfo;

/** 
 *@Description:场站（时效）Vo
 *@author :梁令
 *@date 创建时间：2017年1月6日 下午3:25:55 
 */
public class StationAgingVo {
      /** 场站(时效)参数*/
	  private StationAgingInfo stationAgingInfoParam;
	  /** 返回场站(时效)集合*/
	  private List<StationAgingInfo> stationAgingList;
	public StationAgingInfo getStationAgingInfoParam() {
		return stationAgingInfoParam;
	}
	public void setStationAgingInfoParam(StationAgingInfo stationAgingInfoParam) {
		this.stationAgingInfoParam = stationAgingInfoParam;
	}
	public List<StationAgingInfo> getStationAgingList() {
		return stationAgingList;
	}
	public void setStationAgingList(List<StationAgingInfo> stationAgingList) {
		this.stationAgingList = stationAgingList;
	}
}
