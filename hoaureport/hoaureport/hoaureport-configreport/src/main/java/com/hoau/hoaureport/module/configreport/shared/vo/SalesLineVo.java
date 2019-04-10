package com.hoau.hoaureport.module.configreport.shared.vo;
import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.SalesLine;

/**
 * 销售线路
 * ClassName: SalesLineVo 
 * @author 刘镇松
 * @date 2016年10月31日
 * @version V1.0
 */
public class SalesLineVo {
	private SalesLine salesLine;
	private List<SalesLine> salesLineList;
	private String filePath;//文件路径
	private String addSize;//新增条数
	private String coverSize;//覆盖条数
	private String sumSize; //导入总数量
	public SalesLine getSalesLine() {
		return salesLine;
	}
	public void setSalesLine(SalesLine salesLine) {
		this.salesLine = salesLine;
	}
	public List<SalesLine> getSalesLineList() {
		return salesLineList;
	}
	public void setSalesLineList(List<SalesLine> salesLineList) {
		this.salesLineList = salesLineList;
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
