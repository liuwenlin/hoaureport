package com.hoau.hoaureport.module.configreport.shared.domain;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 车队
 * ClassName: FleetInfo 
 * @author 刘镇松
 * @date 2016年8月17日
 * @version V1.0
 */
public class FleetInfo {

	private BigDecimal fleetId;

    private String fleetShortName;

    private String fleetName;

    private String theArea;

    private String theBusinessDepartment;

    private String active;

    private Date effectedTime;

    private Date invalidTime;

    private Date createTime;

    private String createUserCode;

    private Date modifyTime;

    private String modifyUserCode;


    public BigDecimal getFleetId() {
		return fleetId;
	}

	public void setFleetId(BigDecimal fleetId) {
		this.fleetId = fleetId;
	}

	public String getFleetShortName() {
        return fleetShortName;
    }

    public void setFleetShortName(String fleetShortName) {
        this.fleetShortName = fleetShortName;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
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