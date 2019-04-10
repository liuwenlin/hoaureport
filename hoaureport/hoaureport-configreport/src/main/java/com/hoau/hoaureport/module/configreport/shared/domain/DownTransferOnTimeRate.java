package com.hoau.hoaureport.module.configreport.shared.domain;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 下转准点率目标值
 * ClassName: DownTransferOnTimeRate 
 * @author 文洁
 * @date 2016年11月9日
 * @version V1.0
 */
public class DownTransferOnTimeRate {
	//下转准点率目标值ID
    private BigDecimal downTransferOnTimeRateId;
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
    
   
	public String getLastMonthFinishValue() {
		return lastMonthFinishValue;
	}

	public void setLastMonthFinishValue(String lastMonthFinishValue) {
		this.lastMonthFinishValue = lastMonthFinishValue;
	}
    public BigDecimal getDownTransferOnTimeRateId() {
		return downTransferOnTimeRateId;
	}

	public void setDownTransferOnTimeRateId(BigDecimal downTransferOnTimeRateId) {
		this.downTransferOnTimeRateId = downTransferOnTimeRateId;
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