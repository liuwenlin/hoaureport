package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * @author：高佳
 * @create：2015年6月5日 上午11:12:23
 * @description：平台实体
 */
public class PlatformEntity extends BaseEntity {

	
	private static final long serialVersionUID = 1L;

	/**
	 * 部门编码
	 */
	private String code;

	/**
	 * 部门名称
	 */
	private String name;

	/**
	 * 物流代码
	 */
	private String logistCode;

	/**
	 * 驻地营业部所属场站
	 */
	private String transterCenter;

	/**
	 * 可出发
	 */
	private String isLeave;

	/**
	 * 可到达
	 */
	private String isArrive;

	/**
	 * 是否驻地部门
	 */
	private String isStation;

	/**
	 * 版本号
	 */
	private Long versionNo;

	/**
	 * 是否提供定日达
	 */
	private String isSpecifiedTime;

	/**
	 * 是否可发货
	 */
	private String isShipment;

	/**
	 * 是否可送货
	 */
	private String isDelivery;

	/**
	 * 是否可自提
	 */
	private String isPickUp;

	/**
	 * 备注
	 */
	private String notes;

	/**
	 * 是否可用
	 */
	private String active;
	
	/**
	 *上级组织名称
	 */
	private String parentName;

	/**
	 *负责人姓名
	 */

	private String managerName;

	/**
	 *省份
	 */
	private String province;

	/**
	 *省份编码
	 */

	private String provinceCode;

	
	/**
	 *城市
	 */
	private String city;

	/**
	 *城市编码
	 */

	private String cityCode;
	
	/**
	 *区县
	 */
	private String county;

	
	/**
	 *区县编码
	 */
	private String countyCode;

	/**
	 *经度
	 */

	private Double lng;
	
	/**
	 *纬度
	 */
	private Double lat;
	
	/**
	 * 区号
	 */
	private String areaCode;
	
	/**
	 * 详细地址
	 */
	private String addressDetail;
	
	
	
	
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}



	
	

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

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

	public String getTransterCenter() {
		return transterCenter;
	}

	public void setTransterCenter(String transterCenter) {
		this.transterCenter = transterCenter;
	}
	
	public String getIsLeave() {
		return isLeave;
	}

	public void setIsLeave(String isLeave) {
		this.isLeave = isLeave;
	}

	public String getIsArrive() {
		return isArrive;
	}

	public void setIsArrive(String isArrive) {
		this.isArrive = isArrive;
	}

	public String getIsStation() {
		return isStation;
	}

	public void setIsStation(String isStation) {
		this.isStation = isStation;
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	public String getIsSpecifiedTime() {
		return isSpecifiedTime;
	}

	public void setIsSpecifiedTime(String isSpecifiedTime) {
		this.isSpecifiedTime = isSpecifiedTime;
	}

	public String getIsShipment() {
		return isShipment;
	}

	public void setIsShipment(String isShipment) {
		this.isShipment = isShipment;
	}

	public String getIsDelivery() {
		return isDelivery;
	}

	public void setIsDelivery(String isDelivery) {
		this.isDelivery = isDelivery;
	}

	public String getIsPickUp() {
		return isPickUp;
	}

	public void setIsPickUp(String isPickUp) {
		this.isPickUp = isPickUp;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}
