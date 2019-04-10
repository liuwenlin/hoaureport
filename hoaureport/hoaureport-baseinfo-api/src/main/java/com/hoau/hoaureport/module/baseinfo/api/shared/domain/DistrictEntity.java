package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * @author：高佳
 * @create：2015年6月3日 下午2:48:14
 * @description：省市区实体
 */
public class DistrictEntity extends BaseEntity{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * 行政区域编码
	 */
	private String districtCode;
	
	/**
	 * 行政区域名称
	 */
	private String districtName;
	
	/**
	 * 行政区域类型
	 * COUNTRY(国家)
	 * PROVINCE(省份)
	 * CITY(城市)
	 * AREA(区县)
	 */
	private String districtType;
	
	/**
	 * 上级行政区域编码
	 */
	private String parentDistrictCode;
	
	
	/**
	 * 拼音
	 */
	private String pinyin;
	
	private String versionNo;
	
	/**
	 * 查询参数(扩展)
	 * 根据名称和拼音查询
	 */
	private String queryParam;


	public String getDistrictCode() {
		return districtCode;
	}


	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}


	public String getDistrictName() {
		return districtName;
	}


	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}


	public String getDistrictType() {
		return districtType;
	}


	public void setDistrictType(String districtType) {
		this.districtType = districtType;
	}


	public String getParentDistrictCode() {
		return parentDistrictCode;
	}


	public void setParentDistrictCode(String parentDistrictCode) {
		this.parentDistrictCode = parentDistrictCode;
	}


	public String getPinyin() {
		return pinyin;
	}


	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}


	public String getVersionNo() {
		return versionNo;
	}


	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}


	public String getQueryParam() {
		return queryParam;
	}


	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}
	
}
