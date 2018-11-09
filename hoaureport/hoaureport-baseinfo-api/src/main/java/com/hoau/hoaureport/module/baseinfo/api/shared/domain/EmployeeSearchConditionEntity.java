package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * @author：李旭锋
 * @create：2015年7月22日 上午9:49:45
 * @description：
 */
public class EmployeeSearchConditionEntity extends BaseEntity{
	
/**
	 *
	 */
	private static final long serialVersionUID = -5018500702879641082L;
	
	//查询参数
	private String queryParam;
	
	//工号
	private String employeeCode;
	
	//姓名
	private String employeeName;
	
	//拼音
	private String pinyinName;
	
	//拼音简拼
	private String pinyinShortName;
	
	//是否可用
	private String active;

	private int limit;
	
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	private int start;

	public String getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}

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
