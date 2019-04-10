package com.hoau.hoaureport.module.configreport.shared.domain;

import java.util.Set;

public class QuerySql extends TimeCondition {

	private static final long serialVersionUID = -7058291696783535856L;

	// private String id;//ID
	private String number;// 序列号
	private String sql;// SQL
	private String tableHead;// 表头
	private String param;// 参数
	private String remark;// 描述
	private String roles;
	private String myColumn; // json格式列名
	private String queryRule; // 取数规则
	
	private int exitType; //导出选择
	private String originalHead;//原值表头
	private String originalSql; //原值sql
	
	private  byte[] sqlBlob;  //SQL 
	private byte[] originalSqlBlob; //原值sql
	

	
	/**
	 * 当前用户部门编码，OMS中可以切换部门
	 */
	private String currDeptCode;
	
	/**
     * 用户所拥有的角色信息ID集合
     */
    private Set<String> roleids;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getTableHead() {
		return tableHead;
	}

	public void setTableHead(String tableHead) {
		this.tableHead = tableHead;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getMyColumn() {
		return myColumn;
	}

	public void setMyColumn(String myColumn) {
		this.myColumn = myColumn;
	}

	public String getCurrDeptCode() {
		return currDeptCode;
	}

	public void setCurrDeptCode(String currDeptCode) {
		this.currDeptCode = currDeptCode;
	}

	public Set<String> getRoleids() {
		return roleids;
	}

	public void setRoleids(Set<String> roleids) {
		this.roleids = roleids;
	}

	public String getQueryRule() {
		return queryRule;
	}

	public void setQueryRule(String queryRule) {
		this.queryRule = queryRule;
	}

	public int getExitType() {
		return exitType;
	}

	public void setExitType(int exitType) {
		this.exitType = exitType;
	}

	public String getOriginalHead() {
		return originalHead;
	}

	public void setOriginalHead(String originalHead) {
		this.originalHead = originalHead;
	}

	public String getOriginalSql() {
		return originalSql;
	}

	public void setOriginalSql(String originalSql) {
		this.originalSql = originalSql;
	}

	public byte[] getSqlBlob() {
		return sqlBlob;
	}

	public void setSqlBlob(byte[] sqlBlob) {
		this.sqlBlob = sqlBlob;
	}

	public byte[] getOriginalSqlBlob() {
		return originalSqlBlob;
	}

	public void setOriginalSqlBlob(byte[] originalSqlBlob) {
		this.originalSqlBlob = originalSqlBlob;
	}
	
}
