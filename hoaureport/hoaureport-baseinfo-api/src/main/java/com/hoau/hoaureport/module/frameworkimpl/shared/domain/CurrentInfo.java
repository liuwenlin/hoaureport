package com.hoau.hoaureport.module.frameworkimpl.shared.domain;

import java.io.Serializable;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgAdministrativeInfoEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.frameworkimpl.server.context.HoauReportUserContext;
import com.hoau.hoaureport.module.util.DataTrans;
/**
 * 系统当前信息
 * @author 高佳
 * @date 2015年6月12日
 */
public class CurrentInfo implements Serializable{
	
	/**
	 * 序列化版本
	 */
	private static final long serialVersionUID = 1L;

	//用户名
	private String userName;
	
	//员工工号
	private String empCode;
	
	//员工姓名
	private String empName;
	
	//当前登录部门编码
	private String currentDeptCode;
	
	//当前登录部门名称
	private String currentDeptName;
	
	//当前登录用户
	private UserEntity user;
	
	//当前部门
	private OrgAdministrativeInfoEntity dept;
	
	public CurrentInfo(UserEntity user, OrgAdministrativeInfoEntity dept) {
		this.user = user;
		this.dept = dept;
		if(user!=null){
			this.userName = user.getUserName();
			this.empCode = user.getEmployee().getEmpCode();
			this.empName = user.getEmployee().getEmpName();			
		}
		if(dept!=null){
			this.currentDeptCode = dept.getCode();
			this.currentDeptName = dept.getName();			
		}
	}
	
	public CurrentInfo(UserEntity user) {
		this.user = user;
		if(user!=null){
			this.userName = user.getUserName();
			if(DataTrans.flagToBool(user.getIsEmp())){
				this.empCode = user.getEmployee().getEmpCode();
				this.empName = user.getEmployee().getEmpName();	
			}else{
				this.empCode = user.getUserName();
				this.empName = user.getEmpName();
			}
					
		}
		this.currentDeptCode = HoauReportUserContext.getCurrentDeptCode();
		this.currentDeptName = HoauReportUserContext.getCurrentDeptName();		
	}

	public String getUserName() {
		return userName;
	}

	public String getEmpCode() {
		return empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public String getCurrentDeptCode() {
		return currentDeptCode;
	}

	public String getCurrentDeptName() {
		return currentDeptName;
	}

	public UserEntity getUser() {
		return user;
	}

	public OrgAdministrativeInfoEntity getDept() {
		if(dept==null){
			return HoauReportUserContext.getCurrentDept();			
		}else{
			return dept;
		}
	}
}
