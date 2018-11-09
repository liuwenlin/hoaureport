package com.hoau.hoaureport.module.configreport.shared.domain;

import java.util.Date;

public class ReportDepartmentManager {
    private String departmentName;

    private String managerName;

    private Date importTime;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Date getImportTime() {
        return importTime;
    }

    public void setImportTime(Date importTime) {
        this.importTime = importTime;
    }
}