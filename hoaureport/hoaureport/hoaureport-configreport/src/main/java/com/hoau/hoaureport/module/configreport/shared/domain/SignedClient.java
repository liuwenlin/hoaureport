package com.hoau.hoaureport.module.configreport.shared.domain;

import java.util.Date;

public class SignedClient {
    private String userCode;

    private String contractType;

    private Date modifyTime;

    private String modifyUser;

    public String getUserCode() {
        return userCode;
    }
 
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }
}