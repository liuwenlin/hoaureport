package com.hoau.hoaureport.module.configreport.shared.domain;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 上转移时间节点
 * ClassName: UpTransferTimeNode 
 * @author 刘镇松
 * @date 2016年10月31日
 * @version V1.0
 */
public class UpTransferTimeNode {
	//ID
    private BigDecimal uId;
    //大区
    private String theArea;
    //所属路区
    private String theRoadArea;
    //上转/下转
    private String upOrDownTransfer;
    //是否串线
    private String isLineCrossed;
    //出发部门
    private String departureDepartment;
    //次日（1）or当日（0）
    private String todayOrNextDay;
    //到达部门
    private String arrivalDepartment;
    //发车时间
    private Date dispatchTime;
    //在途时长（分钟）
    private String intransitMinutes;
    //到达时间
    private Date arrivalTime;
    //串线线路
    private String crossedLine;
    //公里数
    private String kilometers;
    //班次
    private String classNum;
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
    public Date getImportTime() {
		return importTime;
	}

	public void setImportTime(Date importTime) {
		this.importTime = importTime;
	}

    public BigDecimal getuId() {
		return uId;
	}

	public void setuId(BigDecimal uId) {
		this.uId = uId;
	}

	public String getTheArea() {
        return theArea;
    }

    public void setTheArea(String theArea) {
        this.theArea = theArea;
    }

    public String getTheRoadArea() {
        return theRoadArea;
    }

    public void setTheRoadArea(String theRoadArea) {
        this.theRoadArea = theRoadArea;
    }

    public String getUpOrDownTransfer() {
        return upOrDownTransfer;
    }

    public void setUpOrDownTransfer(String upOrDownTransfer) {
        this.upOrDownTransfer = upOrDownTransfer;
    }

    public String getIsLineCrossed() {
        return isLineCrossed;
    }

    public void setIsLineCrossed(String isLineCrossed) {
        this.isLineCrossed = isLineCrossed;
    }

    public String getDepartureDepartment() {
        return departureDepartment;
    }

    public void setDepartureDepartment(String departureDepartment) {
        this.departureDepartment = departureDepartment;
    }

    public String getTodayOrNextDay() {
        return todayOrNextDay;
    }

    public void setTodayOrNextDay(String todayOrNextDay) {
        this.todayOrNextDay = todayOrNextDay;
    }

    public String getArrivalDepartment() {
        return arrivalDepartment;
    }

    public void setArrivalDepartment(String arrivalDepartment) {
        this.arrivalDepartment = arrivalDepartment;
    }

    public Date getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Date dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public String getIntransitMinutes() {
        return intransitMinutes;
    }

    public void setIntransitMinutes(String intransitMinutes) {
        this.intransitMinutes = intransitMinutes;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCrossedLine() {
        return crossedLine;
    }

    public void setCrossedLine(String crossedLine) {
        this.crossedLine = crossedLine;
    }

    public String getKilometers() {
        return kilometers;
    }

    public void setKilometers(String kilometers) {
        this.kilometers = kilometers;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
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