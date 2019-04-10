package com.hoau.hoaureport.module.configreport.shared.domain;

import java.util.Date;
/**
 * 必走货目标值
 * ClassName: TransGoodsIntimerate 
 * @author 刘镇松
 * @date 2016年11月21日
 * @version V1.0
 */
public class TransGoodsIntimerate {
    private Long tId;

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

    private String lastmonthFinishvalue;

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
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

    public String getLastmonthFinishvalue() {
        return lastmonthFinishvalue;
    }

    public void setLastmonthFinishvalue(String lastmonthFinishvalue) {
        this.lastmonthFinishvalue = lastmonthFinishvalue;
    }
}