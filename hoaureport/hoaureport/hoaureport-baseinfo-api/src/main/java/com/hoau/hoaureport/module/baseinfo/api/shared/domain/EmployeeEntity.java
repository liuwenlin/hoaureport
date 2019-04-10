package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;


/**
 * 员工实体
 * @author 高佳
 * @date 2015年6月6日
 */
public class EmployeeEntity extends BaseEntity {

    private static final long serialVersionUID = -3967231350437995984L;

    /**
     * 职员姓名
     */
    private String empName;

    /**
     * 姓名拼音
     */
    private String pinyin;
    
    /**
     * 拼音简称
     */
    private String pinyinShort;

    /**
     * 工号
     */
    private String empCode;

    /**
     * 性别
     */
    private String gender;

    /**
     * 账户
     */
    private String account;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 部门编码
     */
    private String deptCode;
	/**
     * 岗位编码
     */
    private String jobCode;
    
    /**
     * 岗位名称
     */
    private String jobName;
    
    /**
     * 直接上级
     */
    private String managerCode;
    /**
     * 部门
     */
    private OrgAdministrativeInfoEntity department;
    
    /**
    * 组织编码
    */	
    private String orgCode;
    
    /**
     * 状态
     */
    private String status;


    /**
     * 电话
     */
    private String phone;


    /**
     * 手机号码
     */
    private String mobilePhone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 是否启用
     */
    private String active;

    /**
     * 数据版本号
     */
    private Long versionNo;
    public String getOrgCode() {
        return orgCode;
    }

    
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getEmpName() {
	return empName;
    }

    public void setEmpName(String empName) {
	this.empName = empName;
    }

    public String getPinyin() {
	return pinyin;
    }

    public void setPinyin(String pinyin) {
	this.pinyin = pinyin;
    }

    public String getEmpCode() {
	return empCode;
    }

    public void setEmpCode(String empCode) {
	this.empCode = empCode;
    }

    public String getGender() {
	return gender;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

    
    public OrgAdministrativeInfoEntity getDepartment() {
		return department;
	}


	public void setDepartment(OrgAdministrativeInfoEntity department) {
		this.department = department;
	}



    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getMobilePhone() {
	return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
	this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getActive() {
	return active;
    }

    public void setActive(String active) {
	this.active = active;
    }

	public String getPinyinShort() {
		return pinyinShort;
	}


	public void setPinyinShort(String pinyinShort) {
		this.pinyinShort = pinyinShort;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getJobCode() {
		return jobCode;
	}


	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}


	public String getJobName() {
		return jobName;
	}


	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	public String getManagerCode() {
		return managerCode;
	}


	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}


	public Long getVersionNo() {
		return versionNo;
	}


	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}
    public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCode() {
		return deptCode;
	}


	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

}
