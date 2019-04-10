package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;
/**
 *
 * @author 肖明明
 * @date 2016年1月23日下午5:37:51
 */
public class OmsHRSearchConditionEntity extends BaseEntity{
	private static final long serialVersionUID = -1043408640717407847L;
	/**
	 * 查询参数
	 */
	private String queryParam;
	private int start;
	private int limit;
	
	public String getQueryParam() {
		return queryParam;
	}
	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}

}
