package com.hoau.hoaureport.module.configreport.shared.vo;

import java.io.Serializable;
/**
 * 产值早报vo
 * @author 273503
 *
 */
public class SmsDataVo implements Serializable{
	private static final long serialVersionUID = -4880406701762216651L;
	/**公司名称**/
	private String compName;
	/**当日目标值**/
	private PieDataVo dayTarget;
	/**当日完成值**/
	private PieDataVo dayComplete;
	/**当月目标值**/
	private PieDataVo monthTarget;
	/**当月完成值**/
	private PieDataVo monthComplete;
	/**当月完成比**/
	private PieDataVo monthRatio;
	/**当月预计完成比**/
	private PieDataVo monthPlanRatio;
	/**当月同比**/
	private PieDataVo monthYOY;
	/**当月环比**/
	private PieDataVo monthMOM;
	/**本年目标值**/
	private PieDataVo yearTarget;
	/**本年完成值**/
	private PieDataVo yearComplete;
	/**本年完成比**/
	private PieDataVo yearhRatio;
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public PieDataVo getDayTarget() {
		return dayTarget;
	}
	public void setDayTarget(PieDataVo dayTarget) {
		this.dayTarget = dayTarget;
	}
	public PieDataVo getDayComplete() {
		return dayComplete;
	}
	public void setDayComplete(PieDataVo dayComplete) {
		this.dayComplete = dayComplete;
	}
	public PieDataVo getMonthTarget() {
		return monthTarget;
	}
	public void setMonthTarget(PieDataVo monthTarget) {
		this.monthTarget = monthTarget;
	}
	public PieDataVo getMonthComplete() {
		return monthComplete;
	}
	public void setMonthComplete(PieDataVo monthComplete) {
		this.monthComplete = monthComplete;
	}
	public PieDataVo getMonthRatio() {
		return monthRatio;
	}
	public void setMonthRatio(PieDataVo monthRatio) {
		this.monthRatio = monthRatio;
	}
	public PieDataVo getMonthPlanRatio() {
		return monthPlanRatio;
	}
	public void setMonthPlanRatio(PieDataVo monthPlanRatio) {
		this.monthPlanRatio = monthPlanRatio;
	}
	public PieDataVo getMonthYOY() {
		return monthYOY;
	}
	public void setMonthYOY(PieDataVo monthYOY) {
		this.monthYOY = monthYOY;
	}
	public PieDataVo getMonthMOM() {
		return monthMOM;
	}
	public void setMonthMOM(PieDataVo monthMOM) {
		this.monthMOM = monthMOM;
	}
	public PieDataVo getYearTarget() {
		return yearTarget;
	}
	public void setYearTarget(PieDataVo yearTarget) {
		this.yearTarget = yearTarget;
	}
	public PieDataVo getYearComplete() {
		return yearComplete;
	}
	public void setYearComplete(PieDataVo yearComplete) {
		this.yearComplete = yearComplete;
	}
	public PieDataVo getYearhRatio() {
		return yearhRatio;
	}
	public void setYearhRatio(PieDataVo yearhRatio) {
		this.yearhRatio = yearhRatio;
	}

	
	
	
}
