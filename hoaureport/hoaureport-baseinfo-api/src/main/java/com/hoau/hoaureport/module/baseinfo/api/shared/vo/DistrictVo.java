package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import java.io.Serializable;
import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.DistrictEntity;


/**
 * @author：高佳
 * @create：2015年6月30日 下午6:46:00
 * @description：行政区域VO
 */
public class DistrictVo implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 省市区
	 */
	private DistrictEntity district;
	/**
	 * 省市区集合
	 */
	private List<DistrictEntity> districtList;
	public DistrictEntity getDistrict() {
		return district;
	}
	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}
	public List<DistrictEntity> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<DistrictEntity> districtList) {
		this.districtList = districtList;
	}
	
}
