package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import com.hoau.hbdp.framework.entity.BaseEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.ResourceEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.TreeNode;

/**
 * 功能树节点的实体对象
 * @author 高佳
 * @date 2015年6月8日
 */
@SuppressWarnings("rawtypes")
public class ResourceTreeNode<T extends BaseEntity> extends TreeNode<T,ResourceTreeNode>{

	//连接
	private String uri;
	
	//是否可展开
	private Boolean expandable;
	
	//是否展开
	private Boolean expend;

	//显示图标
	private String iconCls;
	
	//菜单的CSS
	private String cls;
	
	//菜单的显示顺序
	private String displayOrder;
	
	private ResourceEntity resourceEntity;
	
	
	public ResourceEntity getResourceEntity() {
		return resourceEntity;
	}

	public void setResourceEntity(ResourceEntity resourceEntity) {
		this.resourceEntity = resourceEntity;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	public Boolean getExpandable() {
		return expandable;
	}

	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Boolean getExpend() {
		return expend;
	}

	public void setExpend(Boolean expend) {
		this.expend = expend;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
}
