package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import java.io.Serializable;
import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.RoleEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.RoleResourceEntity;



/**
 * 角色vo对象
 * @author 涂婷婷
 * @date 2015年6月9日
 */
public class RoleVo implements Serializable{
    private static final long serialVersionUID = -3967231350132228718L;
    
    /**
     *业务角色实体对象（查询条件）
     */
    private RoleEntity roleEntity;
    
    /**
     * 角色集合对象
     */
    private List<RoleEntity> roleEntityList;
    
    
    /**
     * 角色包含的角色权限实体List
     */
    private List<RoleResourceEntity> roleResourceEntityList;

    /**
     * 权限code
     */
    private String resourceCodes;
    /**
     * 删除权限code
     */
    private String deleteResourceCodes;
    
    /**
     * 当前角色
     */
    private  String currRoleCode;
    
	public List<RoleEntity> getRoleEntityList() {
		return roleEntityList;
	}

	public void setRoleEntityList(List<RoleEntity> roleEntityList) {
		this.roleEntityList = roleEntityList;
	}

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}

	public List<RoleResourceEntity> getRoleResourceEntityList() {
		return roleResourceEntityList;
	}

	public void setRoleResourceEntityList(
			List<RoleResourceEntity> roleResourceEntityList) {
		this.roleResourceEntityList = roleResourceEntityList;
	}

	public String getResourceCodes() {
		return resourceCodes;
	}

	public void setResourceCodes(String resourceCodes) {
		this.resourceCodes = resourceCodes;
	}

	public String getDeleteResourceCodes() {
		return deleteResourceCodes;
	}

	public void setDeleteResourceCodes(String deleteResourceCodes) {
		this.deleteResourceCodes = deleteResourceCodes;
	}

	public String getCurrRoleCode() {
		return currRoleCode;
	}

	public void setCurrRoleCode(String currRoleCode) {
		this.currRoleCode = currRoleCode;
	}

 
    
    
    
}
