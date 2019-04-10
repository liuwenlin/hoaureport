package com.hoau.hoaureport.module.configreport.shared.vo;

import java.io.Serializable;

/**
 * 早报饼图数据实体
 * @author 273503
 *
 */
public class PieDataVo  implements Serializable{
	
	
	private static final long serialVersionUID = 5119058284255764183L;
	/**显示值**/
	private double displayVal;
	/**隐藏值**/
	private double hideVal;
	
	public PieDataVo(){
		
	}
	/**
	 * 
	 * @param displayVal 实际显示值
	 * @param hideVal  隐藏值
	 */
	public PieDataVo(double displayVal,double hideVal ){
		this.displayVal =displayVal;
		this.hideVal = hideVal;
	}
	public double getDisplayVal() {
		return displayVal;
	}
	public void setDisplayVal(double displayVal) {
		this.displayVal = displayVal;
	}
	public double getHideVal() {
		return hideVal;
	}
	public void setHideVal(double hideVal) {
		this.hideVal = hideVal;
	}
	
	
	
}
