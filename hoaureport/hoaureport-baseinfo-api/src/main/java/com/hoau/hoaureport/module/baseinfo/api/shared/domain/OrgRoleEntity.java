package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import java.util.List;

import com.hoau.hbdp.framework.entity.BaseEntity;


/**
 * 用户部门角色 中的 部门角色
 * @author 高佳
 * @date 2015年6月6日
 */
public class OrgRoleEntity extends BaseEntity{
	
	/**
	 * 序列化ID
	 */
    private static final long serialVersionUID = -3467231350438160812L;
    
    /**
     * 部门编码
     */
    private String code;
        
    /**
     * 部门名称
     */
    private String isDefaultOrg;
    
    /**
     * 部门是否是默认部门
     */
    private String name;
    
    /**
     * 角色列表信息
     */
    private List<RoleEntity> roleList;
    

   
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIsDefaultOrg() {
		return isDefaultOrg;
	}

	public void setIsDefaultOrg(String isDefaultOrg) {
		this.isDefaultOrg = isDefaultOrg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RoleEntity> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleEntity> roleList) {
		this.roleList = roleList;
	}
    
}