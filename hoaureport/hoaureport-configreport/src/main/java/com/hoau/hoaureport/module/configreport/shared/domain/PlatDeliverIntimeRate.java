package com.hoau.hoaureport.module.configreport.shared.domain;

import java.util.Date;

/**
 * 平台-送货及时率目标值
 * ClassName: PlatDeliverIntimeRate 
 * @author 文洁
 * @date 2016年10月17日
 * @version V1.0
 */
public class PlatDeliverIntimeRate {
	//平台-送货及时率ID 
    private Long platIntimerateId;
    //部门
    private String department;
    //目标值
    private String targetValue;
    //目标值所属月份
    private String targetValueMonth;
    //导入时间
    private Date importTime;
    //负责人
    private String manager;
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
    //上月完成值
    private String lastMonthFinishValue;

    //setters 和 getters
    
    public Long getPlatIntimerateId() {
        return platIntimerateId;
    }

    public void setPlatIntimerateId(Long platIntimerateId) {
        this.platIntimerateId = platIntimerateId;
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

	public String getLastMonthFinishValue() {
		return lastMonthFinishValue;
	}

	public void setLastMonthFinishValue(String lastMonthFinishValue) {
		this.lastMonthFinishValue = lastMonthFinishValue;
	}
}