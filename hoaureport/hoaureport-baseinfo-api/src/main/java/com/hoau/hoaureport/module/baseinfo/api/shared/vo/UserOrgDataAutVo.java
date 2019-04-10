package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import java.io.Serializable;
import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserOrgDataAutEntity;

/**
 * @author：涂婷婷
 * @create：2015年7月21日 8
 * @description：用户部门数据权限信息vo
 */
public class UserOrgDataAutVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户部门数据权限信息实体 UserOrgDateAutEntity
	 */
	private UserOrgDataAutEntity userOrgDataAutEntity;

	/**
	 * 用户部门数据权限信息集合对象 List
	 */
	private List<UserOrgDataAutEntity> userOrgDataAutList;

	public List<UserOrgDataAutEntity> getUserOrgDataAutList() {
		return userOrgDataAutList;
	}

	public void setUserOrgDataAutList(
			List<UserOrgDataAutEntity> userOrgDataAutList) {
		this.userOrgDataAutList = userOrgDataAutList;
	}

	public UserOrgDataAutEntity getUserOrgDataAutEntity() {
		return userOrgDataAutEntity;
	}

	public void setUserOrgDataAutEntity(
			UserOrgDataAutEntity userOrgDataAutEntity) {
		this.userOrgDataAutEntity = userOrgDataAutEntity;
	}

}
