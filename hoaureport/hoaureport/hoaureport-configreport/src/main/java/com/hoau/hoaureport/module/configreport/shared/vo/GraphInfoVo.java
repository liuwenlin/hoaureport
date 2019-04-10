package com.hoau.hoaureport.module.configreport.shared.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 图形化界面数据VO
 * @author 273503
 *
 */
public class GraphInfoVo implements Serializable {
	
	private static final long serialVersionUID = -3252550356406687405L;
	/**
	 * 部门
	 */
	private List<String> depts;
	/**
	 * 完成值
	 */
	private List<Double> outvalues;
	/**
	 * 目标值
	 */
	private List<Double> targets;
	/**
	 * 最大值
	 */
	private Long cmin;
	/**
	 * 最小值
	 */
	private Long cmax;
	
	public List<String> getDepts() {
		return depts;
	}
	public void setDepts(List<String> depts) {
		this.depts = depts;
	}
	public List<Double> getOutvalues() {
		return outvalues;
	}
	public void setOutvalues(List<Double> outvalues) {
		this.outvalues = outvalues;
	}
	public List<Double> getTargets() {
		return targets;
	}
	public void setTargets(List<Double> targets) {
		this.targets = targets;
	}
	public Long getCmin() {
		return cmin;
	}
	public void setCmin(Long cmin) {
		this.cmin = cmin;
	}
	public Long getCmax() {
		return cmax;
	}
	public void setCmax(Long cmax) {
		this.cmax = cmax;
	}
	
	

}
