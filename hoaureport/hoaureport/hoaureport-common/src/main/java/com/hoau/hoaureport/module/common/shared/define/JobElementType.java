package com.hoau.hoaureport.module.common.shared.define;


/**
 * job业务类型
 * @author 张贞献
 * @date 2015年7月13日
 */
public enum JobElementType {
	/******************job业务类型枚举***************************/
	/**BSE JOB开始**/
	JOB_BSE_EMPLOYEE_SYNC("JOB_BSE_EMPLOYEE_SYNC", "员工信息同步"),//员工信息同步
	JOB_BSE_DISTRICT_SYNC("JOB_BSE_DISTRICT", "行政区域信息同步"),//行政区域信息同步
	JOB_BSE_DEPT_SYNC("JOB_BSE_DEPT_SYNC", "组织部门信息同步"),//组织部门信息同步
	/**BSE JOB结束**/
	
	/**PKP JOB开始**/
	JOB_PKP_DC_NHDS_SYNC("JOB_PKP_DC_NHDS_SYNC", "偏线回单登记同步"),
	/**QLT JOB结束**/
	
	
	/**QLT JOB开始**/
	JOB_QLT_DC_NHDS_SYNC("JOB_QLT_DC_NHDS_SYNC", "迪辰系统内货短少同步"),
	/**QLT JOB结束**/
	//ToDo 此处只是为了程序报错，
	JOB_MONITOR_GOODSFORECAST("JOB_MONITOR_GOODSFORECAST","货物预测")
	;
	/********************************************************/
	
	public final static String SUCCESS = "Y"; 
	public final static String FAILUTR = "N"; 
	public final static String USER = "CTMS";
	/**
	 * job任务编码（JOB_业务模块_业务功能）
	 */
	private String code;
	/**
	 * job任务名称
	 */
	private String name;

	private JobElementType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
