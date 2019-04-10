package com.hoau.hoaureport.module.configreport.shared.domain;

import java.util.Date;

/**
 * 大客户信息
 * ClassName: CustomerInfo 
 * @author 文洁
 * @date 2016年11月7日
 * @version V1.0
 */
public class CustomerInfo {
	//大客户编号
    private String customerNum;
    //大客户名字
    private String bigCustomerName;
    //增加时效(0或1)
    private String addEfficiency;
    //是否有效
    private String active;
    //创建者时间
    private Date createTime;
    //创建者编码
    private String createUserCode;
    //修改时间
    private Date modifyTime;
    //修改者编码
    private String modifyUserCode;

    //下面是setter和getter
    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getBigCustomerName() {
        return bigCustomerName;
    }

    public void setBigCustomerName(String bigCustomerName) {
        this.bigCustomerName = bigCustomerName;
    }

    public String getAddEfficiency() {
        return addEfficiency;
    }

    public void setAddEfficiency(String addEfficiency) {
        this.addEfficiency = addEfficiency;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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