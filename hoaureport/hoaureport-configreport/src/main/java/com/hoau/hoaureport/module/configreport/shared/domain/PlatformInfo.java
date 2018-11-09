package com.hoau.hoaureport.module.configreport.shared.domain;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * ClassName: PlatformInfo 
 * @author 刘镇松
 * @date 2016年8月17日
 * @version V1.0
 */
public class PlatformInfo {
    private BigDecimal platformId;

    private String platformShortName;

    private String platformName;

    private String isHeadQuartersPlatform;

    private String theFleet;

    private String theRoadArea;

    private String theArea;

    private String theBusinessDepartment;

    private String active;

    private Date effectedTime;

    private Date invalidTime;

    private Date createTime;

    private String createUserCode;

    private Date modifyTime;

    private String modifyUserCode;

    public BigDecimal getPlatformId() {
		return platformId;
	}

	public void setPlatformId(BigDecimal platformId) {
		this.platformId = platformId;
	}

	public String getPlatformShortName() {
        return platformShortName;
    }

    public void setPlatformShortName(String platformShortName) {
        this.platformShortName = platformShortName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getIsHeadQuartersPlatform() {
        return isHeadQuartersPlatform;
    }

    public void setIsHeadQuartersPlatform(String isHeadQuartersPlatform) {
        this.isHeadQuartersPlatform = isHeadQuartersPlatform;
    }

    public String getTheFleet() {
        return theFleet;
    }

    public void setTheFleet(String theFleet) {
        this.theFleet = theFleet;
    }

    public String getTheRoadArea() {
        return theRoadArea;
    }

    public void setTheRoadArea(String theRoadArea) {
        this.theRoadArea = theRoadArea;
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
}