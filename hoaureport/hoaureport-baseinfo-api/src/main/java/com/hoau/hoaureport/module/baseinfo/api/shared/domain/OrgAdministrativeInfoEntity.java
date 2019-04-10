package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import java.util.Date;
import java.util.List;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 组织信息
 * 
 * @author 高佳
 * @date 2015年6月9日
 */
public class OrgAdministrativeInfoEntity extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 拼音(全拼)
	 */
	private String pinyin;
	/**
	 * 拼音简写（首字母）
	 */
	private String simplePinyin;

	/**
	 * 组织编码
	 */
	private String code;

	/**
	 * 组织名称
	 */
	private String name;
	
	/**
	 * 上级组织
	 */
	private String parentCode;
	
	/**
	 * 上级组织名称
	 */
	private String parentName;
	
	/**
	 * 下属部门
	 */
	private List<OrgAdministrativeInfoEntity> children;
	
	/**
	 * 组织性质
	 */
	private int nature;
	
	/**
	 * 物流代码
	 */
	private String logistCode;
	
	/**
	 * 负责人
	 */
	private String managerCode;
	
	/**
	 * 启用日期
	 */
	private Date beginTime;
	
	/**
	 * 结束日期
	 */
	private Date endTime;
	
	/**
	 * 负责人姓名
	 */
	private String managerName;
	
	/**
	 * 省份编码
	 */
	private String provinceCode;
	
	/**
	 * 城市编码
	 */
	private String cityCode;
	
	/**
	 * 区县编码
	 */
	private String countyCode;
	
	/**
	 * 省份编码
	 */
	private String provinceName;
	
	/**
	 * 城市编码
	 */
	private String cityName;
	
	/**
	 * 区县编码
	 */
	private String countyName;
	
	/**
	 * 区号
	 */
	private String areaCode;
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 传真
	 */
	private String fax;
	
	/**
	 * 详细地址
	 */
	private String addressDetail;
	
	/**
	 * 纬度
	 */
	private Double lat;
	
	/**
	 * 经度
	 */
	private Double lng;
	
	/**
	 * 是否事业部
	 */
	private String isDivision;
	
	/**
	 * 事业部编码
	 */
	private String divisionCode;
	
	/**
	 * 事业部名称
	 */
	private String divisionName;
	
	/**
	 * 是否大区
	 */
	private String isBigRegion;
	
	/**
	 * 大区编码
	 */
	private String bigRegionCode;
	
	/**
	 * 大区名称
	 */
	private String bigRegionName;
	
	/**
	 * 是否路区
	 */
	private String isRoadArea;
	
	/**
	 * 是否场站
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
	 * 是否门店
	 */
	private String isSalesDepartment;
	
	
	/**
	 * 是否可用
	 */
	private String active;
	
	/**
	 * 数据版本号
	 */
	private Long versionNo;
	
	/**
	 * 备注
	 */
	private String notes;

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

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public int getNature() {
		return nature;
	}

	public void setNature(int nature) {
		this.nature = nature;
	}

	public String getLogistCode() {
		return logistCode;
	}

	public void setLogistCode(String logistCode) {
		this.logistCode = logistCode;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getIsDivision() {
		return isDivision;
	}

	public void setIsDivision(String isDivision) {
		this.isDivision = isDivision;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getIsBigRegion() {
		return isBigRegion;
	}

	public void setIsBigRegion(String isBigRegion) {
		this.isBigRegion = isBigRegion;
	}

	public String getBigRegionCode() {
		return bigRegionCode;
	}

	public void setBigRegionCode(String bigRegionCode) {
		this.bigRegionCode = bigRegionCode;
	}

	public String getIsRoadArea() {
		return isRoadArea;
	}

	public void setIsRoadArea(String isRoadArea) {
		this.isRoadArea = isRoadArea;
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

	public String getIsSalesDepartment() {
		return isSalesDepartment;
	}

	public void setIsSalesDepartment(String isSalesDepartment) {
		this.isSalesDepartment = isSalesDepartment;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<OrgAdministrativeInfoEntity> getChildren() {
		return children;
	}

	public void setChildren(List<OrgAdministrativeInfoEntity> children) {
		this.children = children;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getBigRegionName() {
		return bigRegionName;
	}

	public void setBigRegionName(String bigRegionName) {
		this.bigRegionName = bigRegionName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

}