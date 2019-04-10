package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.SpecialStationInfo;

/**
 * 场站特许 VO
 * ClassName: SpecialStationVo 
 * @author 文洁
 * @date 2016年11月17日
 * @version V1.0
 */
public class SpecialStationVo {
	/**参数*/
	private SpecialStationInfo specialStationInfoParam;
	/**返回的场站特许集合*/
	private List<SpecialStationInfo> specialStationInfoList;
	
	public List<SpecialStationInfo> getSpecialStationInfoList() {
		return specialStationInfoList;
	}

	public void setSpecialStationInfoList(List<SpecialStationInfo> specialStationInfoList) {
		this.specialStationInfoList = specialStationInfoList;
	}

	public SpecialStationInfo getSpecialStationInfoParam() {
		return specialStationInfoParam;
	}

	public void setSpecialStationInfoParam(SpecialStationInfo specialStationInfoParam) {
		this.specialStationInfoParam = specialStationInfoParam;
	}

}
