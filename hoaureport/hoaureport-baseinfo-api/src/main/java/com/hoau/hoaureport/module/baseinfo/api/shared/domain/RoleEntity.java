package com.hoau.hoaureport.module.baseinfo.api.shared.domain;


import java.util.List;
import java.util.Set;

import com.hoau.hbdp.framework.entity.BaseEntity;
import com.hoau.hbdp.framework.entity.IRole;

/**
 * 角色entity对象
 * @author 高佳
 * @date 2015年6月6日
 */
public class RoleEntity extends BaseEntity implements IRole {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -3967231350438328890L;
    /**
     * 角色名称
     */
     private String id;
	/**
    * 数据版本号
    */	
    private Long versionNo;

    /**
    * 角色名称
    */
    private String name;
    /**
     * 角色类型
     */
    private String type;

    /**
    * 角色编码
    */
    private String code;

    /**
     * 角色描述
     */
    private String notes;

    /**
     * 是否启用
     */
    private String active;

    /**
     * 功能对象ID
     */
    private Set<String> resIds;
    
    /**
     * 
     */
    private List<RoleResourceEntity> roleResourceEntityList;

	/**
	 * @return resIds
	 */
    @Override
    public Set<String> getFunctionIds() {
	return this.resIds;
    }
    
	/**
	 * @param  value  
	 */
    public void setFunctionIds(Set<String> value) {
    	this.resIds = value;
    }

	/**
	 * @return versionNo
	 */
	public Long getVersionNo() {
		return versionNo;
	}

	/**
	 * @param  versionNo  
	 */
	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param  name  
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param  code  
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param  notes  
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * @param  active  
	 */
	public void setActive(String active) {
		this.active = active;
	}
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return resIds
	 */
	public Set<String> getResIds() {
		return resIds;
	}

	/**
	 * @param  resIds  
	 */
	public void setResIds(Set<String> resIds) {
		this.resIds = resIds;
	}

	/**
	 * @return roleResourceEntityList
	 */
	public List<RoleResourceEntity> getRoleResourceEntityList() {
		return roleResourceEntityList;
	}

	/**
	 * @param  roleResourceEntityList  
	 */
	public void setRoleResourceEntityList(
			List<RoleResourceEntity> roleResourceEntityList) {
		this.roleResourceEntityList = roleResourceEntityList;
	}
}