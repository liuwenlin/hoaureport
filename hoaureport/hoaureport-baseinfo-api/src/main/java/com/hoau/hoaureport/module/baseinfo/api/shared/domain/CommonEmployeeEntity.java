package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 公共组件选择器 员工信息实体
 * @author：李旭锋
 * @create：2015年7月22日 上午9:29:33
 * @description：
 */
public class CommonEmployeeEntity extends BaseEntity{

	/**
	 *
	 */
	private static final long serialVersionUID = 4100157337762971875L;

	//员工工号
	private String employeeCode;
	
	//员工姓名
	private String employeeName;
	
	//性别
	private String gender;
	
	//拼音名
	private String pinyinName;
	
	//拼音简称
	private String pinyinShortName;
	
	//账户
	private String account;
	
	//部门名称
	private String deptName;
	
	//部门编码
	private String deptCode;
	
	//岗位名称
	private String jobName;
	
	//岗位编码
	private String jobCode;
	
	//直接上级
	private String manageCode;

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPinyinName() {
		return pinyinName;
	}

	public void setPinyinName(String pinyinName) {
		this.pinyinName = pinyinName;
	}

	public String getPinyinShortName() {
		return pinyinShortName;
	}

	public void setPinyinShortName(String pinyinShortName) {
		this.pinyinShortName = pinyinShortName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getManageCode() {
		return manageCode;
	}

	public void setManageCode(String manageCode) {
		this.manageCode = manageCode;
	}
	
}
