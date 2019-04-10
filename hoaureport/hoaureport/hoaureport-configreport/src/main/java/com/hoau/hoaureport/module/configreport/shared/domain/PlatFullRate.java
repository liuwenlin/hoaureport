package com.hoau.hoaureport.module.configreport.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**满载率实体
 * @author by 宋京涛
 * @Description
 * @Date 2018/3/12
 */
public class PlatFullRate extends BaseEntity {

    private static final long serialVersionUID = 1592948112053889199L;

    //部门
    private String department;
    //目标值
    private String targetValue;
    //目标值所属月份
    private String targetValueMonth;
    //导入时间
    private Date importTime;
    //导入人
    private String importUser;
    //上月完成值
    private String lastMonthFinishValue;

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

    public String getImportUser() {
        return importUser;
    }

    public void setImportUser(String importUser) {
        this.importUser = importUser;
    }

    public String getLastMonthFinishValue() {
        return lastMonthFinishValue;
    }

    public void setLastMonthFinishValue(String lastMonthFinishValue) {
        this.lastMonthFinishValue = lastMonthFinishValue;
    }
}
