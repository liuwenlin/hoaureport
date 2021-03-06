package com.hoau.hoaureport.module.configreport.shared.vo;
import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.NonCarloadTargetValue;

public class NonCarloadTargetValueVo {
	private NonCarloadTargetValue entity;
	private List<NonCarloadTargetValue> list;
	private String filePath;//文件路径
	private String addSize;//新增条数
	private String coverSize;//覆盖条数
	private String sumSize; //导入总数量
	
	public NonCarloadTargetValue getEntity() {
		return entity;
	}
	public void setEntity(NonCarloadTargetValue entity) {
		this.entity = entity;
	}
	public List<NonCarloadTargetValue> getList() {
		return list;
	}
	public void setList(List<NonCarloadTargetValue> list) {
		this.list = list;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getAddSize() {
		return addSize;
	}
	public void setAddSize(String addSize) {
		this.addSize = addSize;
	}
	public String getCoverSize() {
		return coverSize;
	}
	public void setCoverSize(String coverSize) {
		this.coverSize = coverSize;
	}
	public String getSumSize() {
		return sumSize;
	}
	public void setSumSize(String sumSize) {
		this.sumSize = sumSize;
	}
}
