package com.hoau.hoaureport.module.configreport.shared.domain;
import java.util.List;

import com.hoau.hbdp.framework.entity.BaseEntity;

public class ParamBean extends BaseEntity {

	private static final long serialVersionUID = 3037947331845570918L;

	// 控件ID
	private String id;
	// 控件name
	private String name;
	// 控件value
	private String value;
	// 控件类型 D:时间控件，T:文本控件，M:下拉列表控件 ， C:级联下拉菜单
	private String type;
	// 控件标签
	private String labelName;
	// 下拉列表的值 name,value
	private List<NameValue> map;
	// 下拉值列表查询sql
	private String sql;
	// 级联菜单绑定级联下拉框id 和执行sql
	private String bindIdSql;

	//是否允许为空
	private String allowBlank;

	public ParamBean() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public List<NameValue> getMap() {
		return map;
	}

	public void setMap(List<NameValue> map) {
		this.map = map;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getBindIdSql() {
		return bindIdSql;
	}

	public void setBindIdSql(String bindIdSql) {
		this.bindIdSql = bindIdSql;
	}

	public String getAllowBlank() {
		return allowBlank;
	}

	public void setAllowBlank(String allowBlank) {
		this.allowBlank = allowBlank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
