package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.NextdayReachRate;

/**
 * 次日送达率VO
 * ClassName: NextdayReachRateVo 
 * @author 文洁
 * @date 2016年8月24日
 * @version V1.0
 */
public class NextdayReachRateVo {
	/**吨成本*/
	private NextdayReachRate nextdayReachRate;
	/**吨成本信息集合*/
	private List<NextdayReachRate> nextdayReachRates;
	private String isConfirm;
	private String order;
	private String selects;
	//文件路径
	private String filePath;
	private String addSize;//新增条数
	private String coverSize;//覆盖条数
	private String sumSize; //导入总数量
	
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
	public String getIsConfirm() {
		return isConfirm;
	}
	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSelects() {
		return selects;
	}
	public void setSelects(String selects) {
		this.selects = selects;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public NextdayReachRate getNextdayReachRate() {
		return nextdayReachRate;
	}
	public void setNextdayReachRate(NextdayReachRate nextdayReachRate) {
		this.nextdayReachRate = nextdayReachRate;
	}
	public List<NextdayReachRate> getNextdayReachRates() {
		return nextdayReachRates;
	}
	public void setNextdayReachRates(List<NextdayReachRate> nextdayReachRates) {
		this.nextdayReachRates = nextdayReachRates;
	}
	
}
