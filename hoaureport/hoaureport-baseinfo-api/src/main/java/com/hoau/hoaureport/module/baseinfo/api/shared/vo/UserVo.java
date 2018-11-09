package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.RoleEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;


/**
 * @author：高佳
 * @create：2015年7月1日 下午5:01:23
 * @description：用户vo
 */
public class UserVo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 部门编码
	 */
	private String orgCode;
	
	/**
	 * 角色编码集合
	 */
	private Set<String> roleCodes;
	
	
	private UserEntity userEntity;
	
	private List<UserEntity> userEntityList;
	
	private List<RoleEntity> roleEntityList;

	/**
	 * 用户名集合，删除使用
	 */
	private List<String> userNameList;
	
	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public List<UserEntity> getUserEntityList() {
		return userEntityList;
	}

	public void setUserEntityList(List<UserEntity> userEntityList) {
		this.userEntityList = userEntityList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Set<String> getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(Set<String> roleCodes) {
		this.roleCodes = roleCodes;
	}

	public List<RoleEntity> getRoleEntityList() {
		return roleEntityList;
	}

	public void setRoleEntityList(List<RoleEntity> roleEntityList) {
		this.roleEntityList = roleEntityList;
	}

	public List<String> getUserNameList() {
		return userNameList;
	}

	public void setUserNameList(List<String> userNameList) {
		this.userNameList = userNameList;
	}
	
}
