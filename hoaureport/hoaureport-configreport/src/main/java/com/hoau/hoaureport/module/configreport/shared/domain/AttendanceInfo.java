package com.hoau.hoaureport.module.configreport.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 出勤信息
 * ClassName: AttendanceInfo 
 * @author 文洁
 * @date 2016年9月23日
 * @version V1.0
 */
public class AttendanceInfo {
	//标识
    private BigDecimal attendanceId;
    //姓名
    private String userName;
    //工号
    private String userId;
    //操作公司
    private String companyName;
    //日期
    private Date workDate;
    //工作量
    private BigDecimal dayLoadQuantity;
    //所属年月
    private String currMonth;
    
    //getters和setters
    public BigDecimal getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(BigDecimal attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public BigDecimal getDayLoadQuantity() {
        return dayLoadQuantity;
    }

    public void setDayLoadQuantity(BigDecimal dayLoadQuantity) {
        this.dayLoadQuantity = dayLoadQuantity;
    }

	public String getCurrMonth() {
		return currMonth;
	}

	public void setCurrMonth(String currMonth) {
		this.currMonth = currMonth;
	}
}