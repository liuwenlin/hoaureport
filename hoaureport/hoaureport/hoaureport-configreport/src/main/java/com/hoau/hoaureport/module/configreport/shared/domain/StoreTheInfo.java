package com.hoau.hoaureport.module.configreport.shared.domain;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 门店所属信息
 * ClassName: StoreTheInfo 
 * @author 刘镇松
 * @date 2016年9月12日
 * @version V1.0
 */
public class StoreTheInfo {
	
    private BigDecimal storeId;
    //门店编号
    private String storeCode;
    //门店名称
    private String storeName;
    //所属路区
    private String theRoadArea;
    //所属大区
    private String theArea;
    //所属部门
    private String theBusinessDepartment;
    //是否有效
    private String active;
    //导入时间
    private Date importTime;

    private Date effectedTime;

    private Date invalidTime;

    private Date createTime;

    private String createUserCode;

    private Date modifyTime;

    private String modifyUserCode;

    public BigDecimal getStoreId() {
		return storeId;
	}

	public void setStoreId(BigDecimal storeId) {
		this.storeId = storeId;
	}

	public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getTheRoadArea() {
        return theRoadArea;
    }

    public void setTheRoadArea(String theRoadArea) {
        this.theRoadArea = theRoadArea;
    }

    public String getTheArea() {
        return theArea;
    }

    public void setTheArea(String theArea) {
        this.theArea = theArea;
    }

    public String getTheBusinessDepartment() {
        return theBusinessDepartment;
    }

    public void setTheBusinessDepartment(String theBusinessDepartment) {
        this.theBusinessDepartment = theBusinessDepartment;
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

	public Date getImportTime() {
		return importTime;
	}

	public void setImportTime(Date importTime) {
		this.importTime = importTime;
	}
}