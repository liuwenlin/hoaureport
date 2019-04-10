package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.ReportDepartmentManager;


public class DepartmentManagerVo {
	
	
	private ReportDepartmentManager rdmVo;
	
	private List<ReportDepartmentManager> lists;
	
	//文件路径
		private String filePath;
		private String addSize;//新增条数
		private String coverSize;//覆盖条数
		private String sumSize; //导入总数量
	

	public ReportDepartmentManager getRdmVo() {
			return rdmVo;
		}

		public void setRdmVo(ReportDepartmentManager rdmVo) {
			this.rdmVo = rdmVo;
		}

	public List<ReportDepartmentManager> getLists() {
		return lists;
	}

	public void setLists(List<ReportDepartmentManager> lists) {
		this.lists = lists;
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