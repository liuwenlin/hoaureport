package com.hoau.hoaureport.module.configreport.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * DD未兑现目标值
 * ClassName: UnfulfilledTarget 
 * @author 文洁
 * @date 2016年12月20日
 * @version V1.0
 */
public class UnfulfilledTarget {
	//DD未兑现目标值ID
    private Long unfulfilledTargetId;
	//部门
    private String department;
	//负责人
    private String manager;
	//目标值（每月更新）
    private BigDecimal targetValue;
	//挑战值（每月更新）
    private BigDecimal challengeValue;
	//历史值（上月完成值）
    private BigDecimal historicalValue;
	//所属月份
    private String targetValueMonth;
	//导入时间
    private Date importTime;
	//是否有效
    private String active;
	//生效时间
    private Date effectedTime;
	//失效时间
    private Date invalidTime;
	//创建者时间
    private Date createTime;
	//创建者编码
    private String createUserCode;
	//修改时间
    private Date modifyTime;
	//修改者编码
    private String modifyUserCode;

    //下面是setters和getters
    public Long getUnfulfilledTargetId() {
        return unfulfilledTargetId;
    }

    public void setUnfulfilledTargetId(Long unfulfilledTargetId) {
        this.unfulfilledTargetId = unfulfilledTargetId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public BigDecimal getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(BigDecimal targetValue) {
        this.targetValue = targetValue;
    }

    public BigDecimal getChallengeValue() {
        return challengeValue;
    }

    public void setChallengeValue(BigDecimal challengeValue) {
        this.challengeValue = challengeValue;
    }

    public BigDecimal getHistoricalValue() {
        return historicalValue;
    }

    public void setHistoricalValue(BigDecimal historicalValue) {
        this.historicalValue = historicalValue;
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