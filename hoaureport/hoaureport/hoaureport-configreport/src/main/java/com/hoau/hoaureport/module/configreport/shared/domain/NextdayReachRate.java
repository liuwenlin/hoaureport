package com.hoau.hoaureport.module.configreport.shared.domain;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * ClassName: NextdayReachRate 
 * @author 刘镇松
 * @date 2016年8月25日
 * @version V1.0
 */
public class NextdayReachRate {
    private BigDecimal reachRateId;

    private String department;

    private String targetValue;

    private String targetValueMonth;

    private Date importTime;

    private String manager;

    private String active;

    private Date effectedTime;

    private Date invalidTime;

    private Date createTime;

    private String createUserCode;

    private Date modifyTime;

    private String modifyUserCode;
    //上月完成值
    private String lastMonthFinishValue;
    
   
	public String getLastMonthFinishValue() {
		return lastMonthFinishValue;
	}

	public void setLastMonthFinishValue(String lastMonthFinishValue) {
		this.lastMonthFinishValue = lastMonthFinishValue;
	}
	
    public BigDecimal getReachRateId() {
		return reachRateId;
	}

	public void setReachRateId(BigDecimal reachRateId) {
		this.reachRateId = reachRateId;
	}

	public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }

    public String getTargetValueMonth() {
        return targetValueMonth;
    }

    public void setTargetValueMonth(String targetValueMonth) {
        this.targetValueMonth = targetValueMonth;
    }

    public Date getImportTime() {
        return importTime;
    }

    public void setImportTime(Date importTime) {
        this.importTime = importTime;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
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