package com.hoau.hoaureport.module.configreport.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 车队辖区信息
 * ClassName: FleetAreaInfo 
 * @author 文洁
 * @date 2016年8月18日
 * @version V1.0
 */
public class FleetAreaInfo {
	//车队辖区ID
    private BigDecimal fleetAreaId;
    //辖区作业单位简称
    private String areaOperationUnitShortname;
    //归属车队简称
    private String theFleetShortName;
    //所属大区
    private String theArea;
    //所属事业部
    private String theBusinessDepartment;
    //是否有效
    private String active;
    //生效时间
    private Date effectedTime;
    //失效时间
    private Date invalidTime;
    //创建时间
    private Date createTime;
    //创建者编码
    private String createUserCode;
    //修改时间
    private Date modifyTime;
    //修改者编码
    private String modifyUserCode;
    //导入时间
    private Date importTime;

    public BigDecimal getFleetAreaId() {
        return fleetAreaId;
    }

    public void setFleetAreaId(BigDecimal fleetAreaId) {
        this.fleetAreaId = fleetAreaId;
    }

    public String getAreaOperationUnitShortname() {
        return areaOperationUnitShortname;
    }

    public void setAreaOperationUnitShortname(String areaOperationUnitShortname) {
        this.areaOperationUnitShortname = areaOperationUnitShortname;
    }

    public String getTheFleetShortName() {
        return theFleetShortName;
    }

    public void setTheFleetShortName(String theFleetShortName) {
        this.theFleetShortName = theFleetShortName;
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