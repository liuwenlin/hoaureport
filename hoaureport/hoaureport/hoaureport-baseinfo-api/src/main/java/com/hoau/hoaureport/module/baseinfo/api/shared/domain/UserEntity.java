package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.hoau.hbdp.framework.cache.CacheManager;
import com.hoau.hbdp.framework.cache.ICache;
import com.hoau.hbdp.framework.entity.BaseEntity;
import com.hoau.hbdp.framework.entity.IRole;
import com.hoau.hbdp.framework.entity.IUser;
import com.hoau.hoaureport.module.frameworkimpl.server.context.HoauReportUserContext;

/**
 * @author：高佳
 * @create：2015年6月6日 下午3:34:04
 * @description：
 */
public class UserEntity extends BaseEntity implements IUser{

	/**
	 * 序列化ID
	 */
    private static final long serialVersionUID = -3967231350438160812L;
    
    /**
     * 职员编号
     */
    private String empCode;

    /**
     * 用户登录名
     */
    private String userName;

    /**
     * 用户登录密码
     */
    private String password;

    /**
     * 用户所拥有的角色信息ID集合
     */
    private Set<String> roleids;

    /**
     * 用户当前部门对应权限编码
     */
    private Set<String> orgResCodes;
    
    /**
     * 用户当前部门对应权限Uri
     */
    private Set<String> orgResUris;

    /**
     * 用户所拥有的部门信息ID集合
     */
    private Set<String> orgids;

    /**
     * 用户最后登录时间
     */
    private Date lastLogin;

    /**
     * 职位名称
     */
    private String title;

    /**
     * 用户状态(N:未启用 Y：启用)
     */
    private String active;

    /**
     * 用户生效时间
     */
    private Date beginDate;

    /**
     * 用户失效时间
     */
    private Date endDate;

    /**
     * 职员对象
     */
    private EmployeeEntity employee;

    /**
     * 用户的部门角色中的部门列表
     */
    private List<OrgRoleEntity> orgRoleList;

    /**
     * 版本控制号
     */
    private Long versionNo;
    
    /**
     * 用户姓名
     */
    private String empName;
    
    /**
     * 是否公司员工
     */
    private String isEmp;
    /**
     * 所属组织编码(特许经营使用)
     */
    private String orgCode;
    
    /**
     * 所属组织名称(特许经营使用)
     */
    private String orgName;


    @Override
    public Set<String> getRoleids() {
	return roleids;
    }

    @Override
    @Deprecated
    public List<IRole> getRoles() {
	return null;
    }

    public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Override
    public void setRoleids(Set<String> roleids) {
	this.roleids = roleids;
    }

    @Override
    @Deprecated
    public void setRoles(List<IRole> roles) {
    }

    /**
     * @return the empName
     */
    public String getEmpName() {
	return empName;
    }

    /**
     * @param empName
     *            the empName to set
     */
    public void setEmpName(String empName) {
	this.empName = empName;
    }

    
	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	@Override
	public Set<String> queryAccessUris() {
		return this.orgResUris;
	}
	
	@SuppressWarnings("unchecked")
	public void loadAccess() {
		String currentDeptCode = HoauReportUserContext.getCurrentDeptCode();
		String currentUserCode = HoauReportUserContext.getCurrentInfo().getUserName();
		if(this.orgResCodes==null||this.orgResUris==null){
			ICache<String, List<Set<String>>> userOrgRoleResCache = CacheManager.getInstance().getCache("userOrgRoleResource");
			List<Set<String>> userOrgResUriCodes = userOrgRoleResCache.get(currentUserCode+"#"+currentDeptCode);
			this.setOrgResCodes(userOrgResUriCodes.get(0));
			this.setOrgResUris(userOrgResUriCodes.get(1));			
		}
	}
	
	/**
	 * @return empCode
	 */
	public String getEmpCode() {
		return empCode;
	}

	/**
	 * @param  empCode  
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param  userName  
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param  password  
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return orgids
	 */
	public Set<String> getOrgids() {
		return orgids;
	}

	/**
	 * @param  orgids  
	 */
	public void setOrgids(Set<String> orgids) {
		this.orgids = orgids;
	}

	/**
	 * @return lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param  lastLogin  
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param  title  
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * @param  active  
	 */
	public void setActive(String active) {
		this.active = active;
	}

	/**
	 * @return beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param  beginDate  
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param  endDate  
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return employee
	 */
	public EmployeeEntity getEmployee() {
		return employee;
	}

	/**
	 * @param  employee  
	 */
	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	/**
	 * @return orgRoleList
	 */
	public List<OrgRoleEntity> getOrgRoleList() {
		return orgRoleList;
	}

	/**
	 * @param  orgRoleList  
	 */
	public void setOrgRoleList(List<OrgRoleEntity> orgRoleList) {
		this.orgRoleList = orgRoleList;
	}

	public Set<String> getOrgResCodes() {
		return orgResCodes;
	}

	public void setOrgResCodes(Set<String> orgResCodes) {
		this.orgResCodes = orgResCodes;
	}

	public Set<String> getOrgResUris() {
		return orgResUris;
	}

	public void setOrgResUris(Set<String> orgResUris) {
		this.orgResUris = orgResUris;
	}

	public String getIsEmp() {
		return isEmp;
	}

	public void setIsEmp(String isEmp) {
		this.isEmp = isEmp;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
    
    

}
