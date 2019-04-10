package com.hoau.hoaureport.module.configreport.shared.domain;

import java.util.Date;

public class UnitpriceMonitorWeekday {
    private String weekdayDate;

    private String weekdayDays;

    private String modifyUser;

    private Date modifyTime;

    public String getWeekdayDate() {
        return weekdayDate;
    }

    public void setWeekdayDate(String weekdayDate) {
        this.weekdayDate = weekdayDate;
    }

    public String getWeekdayDays() {
        return weekdayDays;
    }

    public void setWeekdayDays(String weekdayDays) {
        this.weekdayDays = weekdayDays;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}