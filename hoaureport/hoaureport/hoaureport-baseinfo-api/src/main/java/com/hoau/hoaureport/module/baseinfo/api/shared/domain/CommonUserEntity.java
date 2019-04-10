package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * @author：李旭锋
 * @create：2015年7月20日 下午6:51:47
 * @description：
 */
public class CommonUserEntity extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = -8220190017607197655L;
	
	
	
	//用户名
	private String userName;
	
	//员工工号
	private String empCode;
	
	//员工姓名
	private String empName;
	
	//密码
	private String password;
	
	//称谓
	private String title;
	
	//启用时间
	private Date beginTime;
	
	//结束使用时间
	private Date endTime;
	
	//是否公司员工
	private String isEmp;
	
	 // 是否有效
	private String active;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitel(String title) {
		this.title = title;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getIsEmp() {
		return isEmp;
	}

	public void setIsEmp(String isEmp) {
		this.isEmp = isEmp;
	}
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
}
