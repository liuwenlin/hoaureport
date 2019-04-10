package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 用户常用菜单
 * @author 高佳
 * @date 2015年6月8日
 */
public class UserMenuEntity  extends BaseEntity {
	
	/**
	 * 序列ID
	 */
    private static final long serialVersionUID = -3967231350569569593L;

    /**
    *显示顺序
    */	
    private Integer displayOrder;
    
    /**
    *用户
    */	
    private String userCode;

    /**
    *权限
    */	
    private String resourceCode;

    /**
    *是否启用
    */	
    private String active;

	/**
	 * @return displayOrder
	 */
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * @param  displayOrder  
	 */
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	/**
	 * @return userCode
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * @param  userCode  
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * @return resourceCode
	 */
	public String getResourceCode() {
		return resourceCode;
	}

	/**
	 * @param  resourceCode  
	 */
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
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
}