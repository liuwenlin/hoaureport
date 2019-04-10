package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import java.util.List;

import com.hoau.hbdp.framework.entity.BaseEntity;


/**
 * 组织机构查询实体
 * @author 高佳
 * @date 2015年6月30日
 */
public class OrgSearchConditionEntity extends BaseEntity {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 6539382307974729234L;
	
	/**
	 * 查询参数
	 */
	private String queryParam;
	
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 物流代码
	 */
	private String logistCode;
	/**
	 * 省份编码
	 */
	private String provinceCode;
	/**
	 * 省份名称
	 */
	private String provinceName;
	/**
	 * 城市编码
	 */
	private String cityCode;
	/**
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 县编码
	 */
	private String countyCode;
	/**
	 * 区县名称
	 */
	private String countyName;
	/**
	 * 是否有效
	 */
	private String active;
	/**
	 * 是否事业部
	 */
	private String isDivision;

	/**
	 * 是否大区
	 */
	private String isBigRegion;

	/**
	 * 是否路区
	 */
	private String isRoadArea;

	/**
	 * 是否门店
	 */
	private String isSalesDepartment;

	/**
	 * 是否外场
	 */
	private String isTransferCenter;


	/**
	 * 是否车队
	 */
	private String isFleet;
	
	/**
	 * 是否平台
	 */
	private String isPlatform;
	
	
	/**
	 * 组织类型
	 */
	private String type;
	/**
	 * 组织类型集合
	 */
	private List<String> types;
	
	private int limit;
	
	private int start;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogistCode() {
		return logistCode;
	}

	public void setLogistCode(String logistCode) {
		this.logistCode = logistCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getIsDivision() {
		return isDivision;
	}

	public void setIsDivision(String isDivision) {
		this.isDivision = isDivision;
	}

	public String getIsBigRegion() {
		return isBigRegion;
	}

	public void setIsBigRegion(String isBigRegion) {
		this.isBigRegion = isBigRegion;
	}

	

	public String getIsRoadArea() {
		return isRoadArea;
	}

	public void setIsRoadArea(String isRoadArea) {
		this.isRoadArea = isRoadArea;
	}

	public String getIsSalesDepartment() {
		return isSalesDepartment;
	}

	public void setIsSalesDepartment(String isSalesDepartment) {
		this.isSalesDepartment = isSalesDepartment;
	}

	public String getIsTransferCenter() {
		return isTransferCenter;
	}

	public void setIsTransferCenter(String isTransferCenter) {
		this.isTransferCenter = isTransferCenter;
	}

	public String getIsFleet() {
		return isFleet;
	}

	public void setIsFleet(String isFleet) {
		this.isFleet = isFleet;
	}

	public String getIsPlatform() {
		return isPlatform;
	}

	public void setIsPlatform(String isPlatform) {
		this.isPlatform = isPlatform;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public String getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}
	
}