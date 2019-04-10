package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

public class OrgEntity {
    /**
     * ID
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String id;

    /**
     * 组织编码
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String code;

    /**
     * 组织名称
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String name;

    /**
     * 上级组织编码
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String parentCode;

    /**
     * 上级组织名称
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String parentName;

    /**
     * 组织性质
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private BigDecimal nature;

    /**
     * 物流代码
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String logistCode;

    /**
     * 组织负责人
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String managerCode;

    /**
     * 组织负责人姓名
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String managerName;

    /**
     * 省份
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String province;

    /**
     * 省份编码
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String provinceCode;

    /**
     * 城市
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String city;

    /**
     * 城市编码
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String cityCode;

    /**
     * 区县
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String county;

    /**
     * 区县编码
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String countyCode;

    /**
     * 区号
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String areaCode;

    /**
     * 电话
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String phone;

    /**
     * 传真
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String fax;

    /**
     * 经度
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private BigDecimal lng;

    /**
     * 纬度
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private BigDecimal lat;

    /**
     * 是否事业部
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String isDivision;

    /**
     * 事业部编码
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String divisionCode;

    /**
     * 是否大区
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String isBigRegion;

    /**
     * 大区编码
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String bigRegionCode;

    /**
     * 是否场站
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String isTransferCenter;

    /**
     * 是否路区
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String isRoadArea;

    /**
     * 是否车队
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String isFleet;

    /**
     * 是否平台
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String isPlatform;

    /**
     * 是否门店
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String isSalesDepartment;

    /**
     * 是否激活
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String active;

    /**
     * 版本号
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private Long versionNo;

    /**
     * 创建时间
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private Date createTime;

    /**
     * 创建人
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String createUserCode;

    /**
     * 修改时间
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private Date modifyTime;

    /**
     * 修改人
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String modifyUserCode;

    /**
     * 启用日期
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private Date beginTime;

    /**
     * 结束使用日期
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private Date endTime;

    /**
     * 组织名称拼音全拼
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String pinyin;

    /**
     * 组织名称拼音首字母
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String simplePinyin;

    /**
     * 是否大区财务部
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String isBigRegionFinance;

    /**
     * 是否事业部财务部
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String isDivisionFinance;

    /**
     * 是否一级公司财务部
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String isFinance;

    /**
     * 是否特许经营
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     */
    private String isFranchise;

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param id the value for t_bse_org.id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param code the value for t_bse_org.code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param name the value for t_bse_org.name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.parent_code
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param parentCode the value for t_bse_org.parent_code
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.parent_name
     */
    public String getParentName() {
        return parentName;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param parentName the value for t_bse_org.parent_name
     */
    public void setParentName(String parentName) {
        this.parentName = parentName == null ? null : parentName.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.nature
     */
    public BigDecimal getNature() {
        return nature;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param nature the value for t_bse_org.nature
     */
    public void setNature(BigDecimal nature) {
        this.nature = nature;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.logist_code
     */
    public String getLogistCode() {
        return logistCode;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param logistCode the value for t_bse_org.logist_code
     */
    public void setLogistCode(String logistCode) {
        this.logistCode = logistCode == null ? null : logistCode.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.manager_code
     */
    public String getManagerCode() {
        return managerCode;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param managerCode the value for t_bse_org.manager_code
     */
    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode == null ? null : managerCode.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.manager_name
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param managerName the value for t_bse_org.manager_name
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.province
     */
    public String getProvince() {
        return province;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param province the value for t_bse_org.province
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.province_code
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param provinceCode the value for t_bse_org.province_code
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param city the value for t_bse_org.city
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.city_code
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param cityCode the value for t_bse_org.city_code
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.county
     */
    public String getCounty() {
        return county;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param county the value for t_bse_org.county
     */
    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.county_code
     */
    public String getCountyCode() {
        return countyCode;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param countyCode the value for t_bse_org.county_code
     */
    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode == null ? null : countyCode.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.area_code
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param areaCode the value for t_bse_org.area_code
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param phone the value for t_bse_org.phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.fax
     */
    public String getFax() {
        return fax;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param fax the value for t_bse_org.fax
     */
    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.lng
     */
    public BigDecimal getLng() {
        return lng;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param lng the value for t_bse_org.lng
     */
    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.lat
     */
    public BigDecimal getLat() {
        return lat;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param lat the value for t_bse_org.lat
     */
    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.is_division
     */
    public String getIsDivision() {
        return isDivision;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param isDivision the value for t_bse_org.is_division
     */
    public void setIsDivision(String isDivision) {
        this.isDivision = isDivision == null ? null : isDivision.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.division_code
     */
    public String getDivisionCode() {
        return divisionCode;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param divisionCode the value for t_bse_org.division_code
     */
    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode == null ? null : divisionCode.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.is_big_region
     */
    public String getIsBigRegion() {
        return isBigRegion;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param isBigRegion the value for t_bse_org.is_big_region
     */
    public void setIsBigRegion(String isBigRegion) {
        this.isBigRegion = isBigRegion == null ? null : isBigRegion.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.big_region_code
     */
    public String getBigRegionCode() {
        return bigRegionCode;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param bigRegionCode the value for t_bse_org.big_region_code
     */
    public void setBigRegionCode(String bigRegionCode) {
        this.bigRegionCode = bigRegionCode == null ? null : bigRegionCode.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.is_transfer_center
     */
    public String getIsTransferCenter() {
        return isTransferCenter;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param isTransferCenter the value for t_bse_org.is_transfer_center
     */
    public void setIsTransferCenter(String isTransferCenter) {
        this.isTransferCenter = isTransferCenter == null ? null : isTransferCenter.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.is_road_area
     */
    public String getIsRoadArea() {
        return isRoadArea;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param isRoadArea the value for t_bse_org.is_road_area
     */
    public void setIsRoadArea(String isRoadArea) {
        this.isRoadArea = isRoadArea == null ? null : isRoadArea.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.is_fleet
     */
    public String getIsFleet() {
        return isFleet;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param isFleet the value for t_bse_org.is_fleet
     */
    public void setIsFleet(String isFleet) {
        this.isFleet = isFleet == null ? null : isFleet.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.is_platform
     */
    public String getIsPlatform() {
        return isPlatform;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param isPlatform the value for t_bse_org.is_platform
     */
    public void setIsPlatform(String isPlatform) {
        this.isPlatform = isPlatform == null ? null : isPlatform.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.is_sales_department
     */
    public String getIsSalesDepartment() {
        return isSalesDepartment;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param isSalesDepartment the value for t_bse_org.is_sales_department
     */
    public void setIsSalesDepartment(String isSalesDepartment) {
        this.isSalesDepartment = isSalesDepartment == null ? null : isSalesDepartment.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.active
     */
    public String getActive() {
        return active;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param active the value for t_bse_org.active
     */
    public void setActive(String active) {
        this.active = active == null ? null : active.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.version_no
     */
    public Long getVersionNo() {
        return versionNo;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param versionNo the value for t_bse_org.version_no
     */
    public void setVersionNo(Long versionNo) {
        this.versionNo = versionNo;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param createTime the value for t_bse_org.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.create_user_code
     */
    public String getCreateUserCode() {
        return createUserCode;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param createUserCode the value for t_bse_org.create_user_code
     */
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode == null ? null : createUserCode.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.modify_time
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param modifyTime the value for t_bse_org.modify_time
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.modify_user_code
     */
    public String getModifyUserCode() {
        return modifyUserCode;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param modifyUserCode the value for t_bse_org.modify_user_code
     */
    public void setModifyUserCode(String modifyUserCode) {
        this.modifyUserCode = modifyUserCode == null ? null : modifyUserCode.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.begin_time
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param beginTime the value for t_bse_org.begin_time
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param endTime the value for t_bse_org.end_time
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.pinyin
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param pinyin the value for t_bse_org.pinyin
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.simple_pinyin
     */
    public String getSimplePinyin() {
        return simplePinyin;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param simplePinyin the value for t_bse_org.simple_pinyin
     */
    public void setSimplePinyin(String simplePinyin) {
        this.simplePinyin = simplePinyin == null ? null : simplePinyin.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.is_big_region_finance
     */
    public String getIsBigRegionFinance() {
        return isBigRegionFinance;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param isBigRegionFinance the value for t_bse_org.is_big_region_finance
     */
    public void setIsBigRegionFinance(String isBigRegionFinance) {
        this.isBigRegionFinance = isBigRegionFinance == null ? null : isBigRegionFinance.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.is_division_finance
     */
    public String getIsDivisionFinance() {
        return isDivisionFinance;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param isDivisionFinance the value for t_bse_org.is_division_finance
     */
    public void setIsDivisionFinance(String isDivisionFinance) {
        this.isDivisionFinance = isDivisionFinance == null ? null : isDivisionFinance.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.is_finance
     */
    public String getIsFinance() {
        return isFinance;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param isFinance the value for t_bse_org.is_finance
     */
    public void setIsFinance(String isFinance) {
        this.isFinance = isFinance == null ? null : isFinance.trim();
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @return the value of t_bse_org.is_franchise
     */
    public String getIsFranchise() {
        return isFranchise;
    }

    /**
     *
     * @author 275688
     * @date 2016-01-14 16:58:13
     * @param isFranchise the value for t_bse_org.is_franchise
     */
    public void setIsFranchise(String isFranchise) {
        this.isFranchise = isFranchise == null ? null : isFranchise.trim();
    }
}