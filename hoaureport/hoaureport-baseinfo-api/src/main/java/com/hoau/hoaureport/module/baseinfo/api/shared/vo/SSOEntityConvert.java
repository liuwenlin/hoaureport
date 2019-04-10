package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import java.util.ArrayList;
import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.EmployeeEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgAdministrativeInfoEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.ResourceEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.RoleEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;

public class SSOEntityConvert {

	/**
	 * 用户信息转换
	 * 
	 * @param user
	 * @return
	 * @author 蒋落琛
	 * @date 2016-4-16上午9:46:23
	 * @update
	 */
	public static UserEntity userConvert(
			com.hoau.sso.module.api.shared.domain.UserEntity userInfo) {
		if (userInfo == null) {
			return null;
		}
		UserEntity user = new UserEntity();
		user.setId(userInfo.getId());
		user.setEmpCode(userInfo.getEmpCode());
		user.setUserName(userInfo.getUserName());
		user.setPassword(userInfo.getPassword());
		user.setTitle(userInfo.getTitle());
		user.setActive(userInfo.getActive());
		user.setVersionNo(userInfo.getVersionNo());
		user.setEmpName(userInfo.getEmpName());
		user.setIsEmp(userInfo.getIsEmp());
		user.setOrgCode(userInfo.getOrgCode());
		user.setOrgName(userInfo.getOrgName());
		user.setOrgids(userInfo.getOrgCodes());
		user.setRoleids(userInfo.getRoleCodes());
		if (userInfo.getEmployee() != null) {
			user.setEmployee(employeeConvert(userInfo.getEmployee()));
		}
		return user;
	}

	/**
	 * 人员信息转换
	 * 
	 * @param employeeInfo
	 * @return
	 * @author 蒋落琛
	 * @date 2016-4-16下午1:31:30
	 * @update
	 */
	public static EmployeeEntity employeeConvert(
			com.hoau.sso.module.api.shared.domain.EmployeeEntity employeeInfo) {
		if (employeeInfo == null) {
			return null;
		}
		EmployeeEntity employee = new EmployeeEntity();
		employee.setId(employeeInfo.getId());
		employee.setEmpName(employeeInfo.getEmpName());
		employee.setPinyin(employeeInfo.getPinyin());
		employee.setPinyinShort(employeeInfo.getPinyinShort());
		employee.setEmpCode(employeeInfo.getEmpCode());
		employee.setGender(employeeInfo.getGender());
		employee.setAccount(employeeInfo.getAccount());
		employee.setPassword(employeeInfo.getPassword());
		employee.setDeptName(employeeInfo.getDeptName());
		employee.setDeptCode(employeeInfo.getDeptCode());
		employee.setJobCode(employeeInfo.getJobCode());
		employee.setJobName(employeeInfo.getJobName());
		employee.setManagerCode(employeeInfo.getManagerCode());
		if (employeeInfo.getDepartment() != null) {
			employee.setDepartment(departmentConvert(employeeInfo
					.getDepartment()));
		}
		employee.setOrgCode(employeeInfo.getOrgCode());
		employee.setStatus(employeeInfo.getStatus());
		employee.setPhone(employeeInfo.getPhone());
		employee.setMobilePhone(employeeInfo.getMobilePhone());
		employee.setEmail(employeeInfo.getEmail());
		employee.setActive(employeeInfo.getActive());
		employee.setVersionNo(employeeInfo.getVersionNo());
		return employee;
	}

	/**
	 * 组织信息转换
	 * 
	 * @param departmentInfo
	 * @return
	 * @author 蒋落琛
	 * @date 2016-4-16下午2:25:50
	 * @update
	 */
	public static OrgAdministrativeInfoEntity departmentConvert(
			com.hoau.sso.module.api.shared.domain.OrgAdministrativeInfoEntity departmentInfo) {
		if (departmentInfo == null) {
			return null;
		}
		OrgAdministrativeInfoEntity department = new OrgAdministrativeInfoEntity();
		department.setId(departmentInfo.getId());
		department.setPinyin(departmentInfo.getPinyin());
		department.setSimplePinyin(departmentInfo.getSimplePinyin());
		department.setCode(departmentInfo.getCode());
		department.setName(departmentInfo.getName());
		department.setParentCode(departmentInfo.getParentCode());
		department.setParentName(departmentInfo.getParentName());
		department.setNature(departmentInfo.getNature());
		department.setLogistCode(departmentInfo.getLogistCode());
		department.setManagerCode(departmentInfo.getManagerCode());
		department.setBeginTime(departmentInfo.getBeginTime());
		department.setEndTime(departmentInfo.getEndTime());
		department.setManagerName(departmentInfo.getManagerName());
		department.setProvinceCode(departmentInfo.getProvinceCode());
		department.setCityCode(departmentInfo.getCityCode());
		department.setCountyCode(departmentInfo.getCountyCode());
		department.setProvinceName(departmentInfo.getProvinceName());
		department.setCityName(departmentInfo.getCityName());
		department.setCountyName(departmentInfo.getCountyName());
		department.setAreaCode(departmentInfo.getAreaCode());
		department.setPhone(departmentInfo.getPhone());
		department.setFax(departmentInfo.getFax());
		// department.set(departmentInfo.getmobile);
		department.setAddressDetail(departmentInfo.getAddressDetail());
		department.setLat(departmentInfo.getLat());
		department.setLng(departmentInfo.getLng());
		department.setActive(departmentInfo.getActive());
		department.setVersionNo(departmentInfo.getVersionNo());
		department.setNotes(departmentInfo.getNotes());
		return department;
	}

	/**
	 * 角色信息转换
	 * 
	 * @param roleInfo
	 * @return
	 * @author 蒋落琛
	 * @date 2016-4-16下午2:26:20
	 * @update
	 */
	public static List<RoleEntity> rolesConvert(
			List<com.hoau.sso.module.api.shared.domain.RoleEntity> rolelist) {
		if (rolelist == null) {
			return null;
		}
		List<RoleEntity> roles = new ArrayList<RoleEntity>();
		for(int i=0; i<rolelist.size(); i++){
			com.hoau.sso.module.api.shared.domain.RoleEntity roleInfo = rolelist.get(i);
			RoleEntity role = new RoleEntity();
			role.setId(roleInfo.getId());
			role.setVersionNo(roleInfo.getVersionNo());
			role.setName(roleInfo.getName());
			role.setCode(roleInfo.getCode());
			role.setNotes(roleInfo.getNotes());
			role.setActive(roleInfo.getActive());
			role.setResIds(roleInfo.getResIds());
			roles.add(role);
		}
		return roles;
	}
	
	/**
	 * 资源信息转换
	 * 
	 * @param resourceInfo
	 * @return
	 * @author 蒋落琛
	 * @date 2016-4-16下午2:26:43
	 * @update
	 */
	public static ResourceEntity resourceConvert(com.hoau.sso.module.api.shared.domain.ResourceEntity resourceInfo) {
		if (resourceInfo == null) {
			return null;
		}
		ResourceEntity resource = new ResourceEntity();
		resource.setCode(resourceInfo.getCode());
		resource.setName(resourceInfo.getName());
		resource.setEntryUri(resourceInfo.getEntryUri());
		resource.setResLevel(resourceInfo.getResLevel());
		if(resourceInfo.getParentRes() != null){
			resource.setParentRes(resourceConvert(resourceInfo.getParentRes()));
		}
		resource.setVersionNo(resourceInfo.getVersionNo());
		resource.setActive(resourceInfo.getActive());
		resource.setDisplayOrder(resourceInfo.getDisplayOrder());
		resource.setChecked(resourceInfo.getChecked());
		resource.setResType(resourceInfo.getResType());
		resource.setLeafFlag(resourceInfo.getLeafFlag());
		resource.setIconCls(resourceInfo.getIconCls());
		resource.setCls(resourceInfo.getCls());
		resource.setNotes(resourceInfo.getNotes());
		/*resource.setBelongSystemType(resourceInfo.getBelongSystemType());
		resource.setResourceTypes(resourceInfo.getResourceTypes());
		resource.setSystemTypes(resourceInfo.getSystemTypes());*/
		return resource;
	}

	/**
	 * 资源信息转换
	 * 
	 * @param resourceInfo
	 * @return
	 * @author 蒋落琛
	 * @date 2016-4-16下午2:26:43
	 * @update
	 */
	public static List<ResourceEntity> resourceConvert(
			List<com.hoau.sso.module.api.shared.domain.ResourceEntity> resourceList) {
		if (resourceList == null || resourceList.size() == 0) {
			return null;
		}
		List<ResourceEntity> rList = new ArrayList<ResourceEntity>();
		for (int i = 0; i < resourceList.size(); i++) {
			com.hoau.sso.module.api.shared.domain.ResourceEntity resourceInfo = resourceList.get(i);
			ResourceEntity resource = resourceConvert(resourceInfo);
			rList.add(resource);
		}
		return rList;
	}
}
