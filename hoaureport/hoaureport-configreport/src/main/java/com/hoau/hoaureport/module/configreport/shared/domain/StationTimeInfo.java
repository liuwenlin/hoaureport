package com.hoau.hoaureport.module.configreport.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

/** 
 *@Description:场站（时效）实体类
 *@author : 梁令
 *@date 创建时间：2017年1月11日 下午2:15:33 
 */
public class StationTimeInfo {
       //场站(时效)Id
	   private BigDecimal stationTimeId;
	   //场站简称
	   private String shortName;
	   //场站名称
	   private String stationName;
	   //所属大区
	   private String theArea;
	   //所属事业部
	   private String theBusinessDepartment;
	   //是否有效
	   private String active;
	   //生效时间
	   private Date effectedTime;
	  // 失效时间
	   private Date invalidTime;
	   //创建时间
	   private Date createTime;
	  // 创建者编码
	   private String createUserCode;
	  // 修改时间
	   private Date modifyTime;
	  // 修改者编号
	   private String modifyUserCode;
	public BigDecimal getStationTimeId() {
		return stationTimeId;
	}
	public void setStationTimeId(BigDecimal stationTimeId) {
		this.stationTimeId = stationTimeId;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
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
}
