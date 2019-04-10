package com.hoau.hoaureport.module.common.shared.define;


/**
 * 锁定业务类型
 * @author 高佳
 * @date 2015年6月24日
 */
public enum MutexElementType {
	
	WAYBILL_NO("LockW_", "运单");
	

	private String prefix;

	private String name;

	private MutexElementType(String prefix, String name) {
		this.prefix = prefix;
		this.name = name;
	}

	/**
	 * @return prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
