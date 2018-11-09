package com.hoau.hoaureport.module.configreport.shared.domain;

import java.util.Date;

public class StationAgingInfo {
    private Long stationAgingId;//ID 

    private String operatingUnitCode;//作业单位代码

    private String shortName;//场站简称

    private String theArea;//所属大区

    private String theBusinessDepartmemt;//所属事业部

    private String active;//是否有效

    private Date effectedTime;//生效时间

    private Date invalidTime;//失效时间

    private Date createTime;//创建时间

    private String createUserCode;//创建人工号

    private Date modifyTime;//修改时间

    private String modifyUserCode;//修改人工号


    public Long getStationAgingId() {
		return stationAgingId;
	}

	public void setStationAgingId(Long stationAgingId) {
		this.stationAgingId = stationAgingId;
	}

	public String getOperatingUnitCode() {
		return operatingUnitCode;
	}

	public void setOperatingUnitCode(String operatingUnitCode) {
		this.operatingUnitCode = operatingUnitCode;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getTheArea() {
		return theArea;
	}

	public void setTheArea(String theArea) {
		this.theArea = theArea;
	}

	public String getTheBusinessDepartmemt() {
		return theBusinessDepartmemt;
	}

	public void setTheBusinessDepartmemt(String theBusinessDepartmemt) {
		this.theBusinessDepartmemt = theBusinessDepartmemt;
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