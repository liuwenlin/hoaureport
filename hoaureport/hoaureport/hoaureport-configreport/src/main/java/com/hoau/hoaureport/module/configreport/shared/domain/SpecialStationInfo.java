package com.hoau.hoaureport.module.configreport.shared.domain;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 场站（特许）
 * ClassName: SpecialStationInfo 
 * @author 文洁
 * @date 2016年11月17日
 * @version V1.0
 */
public class SpecialStationInfo {
	
    private BigDecimal specialStationsId;
 // 场站简称
    private String stationsShortName;
 // 场站名称
    private String stationsName;
  //所属大区
    private String theArea;
 // 所属事业部
    private String theBusinessDepartment;
 // 是否有效
    private String active;
 // 生效时间
    private Date effectedTime;
 // 失效时间
    private Date invalidTime;
  //创建时间
    private Date createTime;
 // 创建者编码
    private String createUserCode;
 // 修改时间
    private Date modifyTime;
 // 修改者编号
    private String modifyUserCode;

    public BigDecimal getSpecialStationsId() {
		return specialStationsId;
	}

	public void setSpecialStationsId(BigDecimal specialStationsId) {
		this.specialStationsId = specialStationsId;
	}

	public String getStationsShortName() {
        return stationsShortName;
    }

    public void setStationsShortName(String stationsShortName) {
        this.stationsShortName = stationsShortName;
    }

    public String getStationsName() {
        return stationsName;
    }

    public void setStationsName(String stationsName) {
        this.stationsName = stationsName;
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