package com.hoau.hoaureport.module.baseinfo.api.shared.domain;


import com.hoau.hbdp.framework.entity.BaseEntity;


/**
 * 用户部门角色
 * @author 涂婷婷
 * @date 2015年6月6日
 */
public class UserOrgRoleEntity extends BaseEntity{
	
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
     * 组织编码
     */
    private String orgCode;
    
    /**
     * 角色编码
     */
    private String roleCode;
    
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


	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
    
}