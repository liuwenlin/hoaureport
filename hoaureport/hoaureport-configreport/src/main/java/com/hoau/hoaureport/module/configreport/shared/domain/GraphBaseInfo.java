package com.hoau.hoaureport.module.configreport.shared.domain;
/**
 * 视图数据实体
 * @author 273503
 *
 */
public class GraphBaseInfo {
	/**
	 * 事业部
	 */
	private String deptName;
	/**
	 * 完成值
	 */
	private double outputValue;
	/**
	 * 目标值
	 */
	private double target;
	/**
	 * 最小值
	 */
	private long cmin;
	/**
	 * 最大值
	 */
	private long cmax;
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public double getOutputValue() {
		return outputValue;
	}
	public void setOutputValue(double outputValue) {
		this.outputValue = outputValue;
	}
	public double getTarget() {
		return target;
	}
	public void setTarget(double target) {
		this.target = target;
	}
	public long getCmin() {
		return cmin;
	}
	public void setCmin(long cmin) {
		this.cmin = cmin;
	}
	public long getCmax() {
		return cmax;
	}
	public void setCmax(long cmax) {
		this.cmax = cmax;
	}
	
	
	
	
}
