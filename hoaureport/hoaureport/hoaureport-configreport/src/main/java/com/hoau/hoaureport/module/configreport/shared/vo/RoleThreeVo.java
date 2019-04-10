package com.hoau.hoaureport.module.configreport.shared.vo;

public class RoleThreeVo {
	
	private String id;
	
	// 文本描述
	private String text;
	
	// 是否叶子结点
	boolean leaf;
	
	// 复选框
	boolean checked;
	
	// 节点图标
	private String iconCls;

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
