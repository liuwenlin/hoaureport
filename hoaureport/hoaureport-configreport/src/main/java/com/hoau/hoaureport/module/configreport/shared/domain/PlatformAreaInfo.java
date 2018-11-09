package com.hoau.hoaureport.module.configreport.shared.domain;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * ClassName: PlatformAreaInfo 
 * @author 刘镇松
 * @date 2016年8月18日
 * @version V1.0
 */
public class PlatformAreaInfo {
    private BigDecimal platformAreaId;

    private String areaOperatingUnitShortName;

    private String thePlatFormAreaShortName;

    private String theArea;

    private String theBusinessDepartment;

    private String active;

    private Date effectedTime;

    private Date invalidTime;

    private Date createTime;

    private String createUserCode;

    private Date modifyTime;

    private String modifyUserCode;

    //导入时间
    private Date importTime;

    public BigDecimal getPlatformAreaId() {
		return platformAreaId;
	}

	public void setPlatformAreaId(BigDecimal platformAreaId) {
		this.platformAreaId = platformAreaId;
	}

	public String getAreaOperatingUnitShortName() {
        return areaOperatingUnitShortName;
    }

    public void setAreaOperatingUnitShortName(String areaOperatingUnitShortName) {
        this.areaOperatingUnitShortName = areaOperatingUnitShortName;
    }

    public String getThePlatFormAreaShortName() {
        return thePlatFormAreaShortName;
    }

    public void setThePlatFormAreaShortName(String thePlatFormAreaShortName) {
        this.thePlatFormAreaShortName = thePlatFormAreaShortName;
    }

    public String getTheArea() {
        return theArea;
    }

    public void setTheArea(String theArea) {
        this.theArea = theArea;
    }

    public String getTheBusinessDepartment() {
        return theBusinessDepartment;
    }

    public void setTheBusinessDepartment(String theBusinessDepartment) {
        this.theBusinessDepartment = theBusinessDepartment;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Date getEffectedTime() {
        return effectedTime;
    }

    public void setEffectedTime(Date effectedTime) {
        this.effectedTime = effectedTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUserCode() {
        return modifyUserCode;
    }

    public void setModifyUserCode(String modifyUserCode) {
        this.modifyUserCode = modifyUserCode;
    }

	public Date getImportTime() {
		return importTime;
	}

	public void setImportTime(Date importTime) {
		this.importTime = importTime;
	}
}