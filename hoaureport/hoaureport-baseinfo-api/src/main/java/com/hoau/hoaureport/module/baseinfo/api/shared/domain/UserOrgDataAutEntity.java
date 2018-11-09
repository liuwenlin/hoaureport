package com.hoau.hoaureport.module.baseinfo.api.shared.domain;


import com.hoau.hbdp.framework.entity.BaseEntity;


/**
 * 用户部门数据权限信息
 * @author 涂婷婷
 * @date 2015年7月20日
 */
public class UserOrgDataAutEntity extends BaseEntity{
	
	/**
	 * 序列化ID
	 */
    private static final long serialVersionUID = -3467231350438160812L;
    
    /**
     * ID
     */
    private String id;
    
    /**
     * 用户编码
     */
    private String userName;
    
    /**
     * 员工名称
     */
    private String empName;
	

	/**
     * 组织编码
     */
    private String orgCode;
    
    /**
     * 组织名称
     */
    private String orgName;
    
    /**
     * 是否包含子部门
     */
    private String includeSubOrg; 

	/**
     * 是否可用
     */
    private String active;

    /**
     *VERSION_NO
     */
    private Long versionNo;
    
	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	   
    public String getIncludeSubOrg() {
		return includeSubOrg;
	}

	public void setIncludeSubOrg(String includeSubOrg) {
		this.includeSubOrg = includeSubOrg;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
    
}