package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.LinePlanSuspended;

public class LinePlanSuspendedVo {
	
	private LinePlanSuspended linePlanparam;
	
	private List<LinePlanSuspended> linePlanList;

	private String filePath;//文件路径
	
	private String addSize;//新增条数
	
	private String coverSize;//覆盖条数
	
	private String sumSize;//总条数

	public LinePlanSuspended getLinePlanparam() {
		return linePlanparam;
	}

	public void setLinePlanparam(LinePlanSuspended linePlanparam) {
		this.linePlanparam = linePlanparam;
	}

	public List<LinePlanSuspended> getLinePlanList() {
		return linePlanList;
	}

	public void setLinePlanList(List<LinePlanSuspended> linePlanList) {
		this.linePlanList = linePlanList;
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
