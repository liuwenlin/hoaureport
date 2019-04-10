package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.ResourceEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.RoleResourceEntity;


/**
 * 资源权限vo
 * @author 高佳
 * @date 2015年6月13日
 */
public class ResourceVo implements Serializable {

    private static final long serialVersionUID = 8640505034012743242L;

    /**
     * 角色实体List
     */
    private List<ResourceEntity> resourceEntityList;

    /**
     * 权限详情
     */
    private ResourceEntity resourceEntityDetail;

    /**
     * 角色权限实体List
     */
    private List<RoleResourceEntity> roleResourceEntityList;
    
    /**
     * 上级权限code
     */
    private String parentResCode;

    private String roles;

    private String fullPath;
    
    private Set<String> allFullPath;
    
    private String currRoleCode;
    
    private long resourceNum;
    
    private Boolean isAdd;
    
    
    
    public String getCurrRoleCode() {
        return currRoleCode;
    }

    public long getResourceNum() {
        return resourceNum;
    }

    public void setResourceNum(long resourceNum) {
        this.resourceNum = resourceNum;
    }

    public Boolean getIsAdd() {
        return isAdd;
    }
    
    public void setIsAdd(Boolean isAdd) {
        this.isAdd = isAdd;
    }

    public String getRoles() {
        return roles;
    }


    
    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getFullPath() {
        return fullPath;
    }
    
    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public void setCurrRoleCode(String currRoleCode) {
        this.currRoleCode = currRoleCode;
    }

    public List<ResourceEntity> getResourceEntityList() {
	return resourceEntityList;
    }

    public void setResourceEntityList(List<ResourceEntity> resourceEntityList) {
	this.resourceEntityList = resourceEntityList;
    }

    public ResourceEntity getResourceEntityDetail() {
	return resourceEntityDetail;
    }

    public void setResourceEntityDetail(ResourceEntity resourceEntityDetail) {
	this.resourceEntityDetail = resourceEntityDetail;
    }

    public List<RoleResourceEntity> getRoleResourceEntityList() {
	return roleResourceEntityList;
    }

    public void setRoleResourceEntityList(
	    List<RoleResourceEntity> roleResourceEntityList) {
	this.roleResourceEntityList = roleResourceEntityList;
    }

	public Set<String> getAllFullPath() {
		return allFullPath;
	}

	public void setAllFullPath(Set<String> allFullPath) {
		this.allFullPath = allFullPath;
	}

	public String getParentResCode() {
		return parentResCode;
	}

	public void setParentResCode(String parentResCode) {
		this.parentResCode = parentResCode;
	}
	
	

}
