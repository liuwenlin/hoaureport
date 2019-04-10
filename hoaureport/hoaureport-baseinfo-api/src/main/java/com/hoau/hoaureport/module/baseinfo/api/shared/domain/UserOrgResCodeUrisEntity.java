package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * @author：高佳
 * @create：2015年6月10日 下午1:23:48
 * @description：用户组织权限实体
 */
public class UserOrgResCodeUrisEntity extends BaseEntity{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 资源权限编码
	 */
	private String resCode;
	
	/**
	 * 资源权限uri
	 */
	private String resUri;

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResUri() {
		return resUri;
	}

	public void setResUri(String resUri) {
		this.resUri = resUri;
	}
	
	

}
