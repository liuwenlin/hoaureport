package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * @author：李旭锋
 * @create：2015年7月20日 下午6:54:45
 * @description：
 */
public class UserSearchConditionEntity extends BaseEntity{

	/**
	 *
	 */
	private static final long serialVersionUID = 2356969289396780428L;
	
	//查询参数
	private String queryParam;
	
	//用户名
	private String userName;
	
	//员工工号
	private String empCode;
	
	//员工姓名
	private String empName;
	
	//称谓
	private String title;
	
	//是否可用
	private String active;
	
	//当前是否已启用
	private String isEnable;
	
	//是否是公司员工
	private String isEmp;
	
	private int limit;
	
	private int start;

	public String getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getIsEmp() {
		return isEmp;
	}

	public void setIsEmp(String isEmp) {
		this.isEmp = isEmp;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

}
