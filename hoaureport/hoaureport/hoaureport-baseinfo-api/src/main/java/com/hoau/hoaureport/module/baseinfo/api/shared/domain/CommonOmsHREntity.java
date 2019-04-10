package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import java.io.Serializable;
import java.util.Date;

public class CommonOmsHREntity implements Serializable {
    /**
     * ID
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     */
    private String id;

    /**
     * 员工迪辰工号
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     */
    private String empNo;

    /**
     * 员工姓名
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     */
    private String empName;

    /**
     * 职位名称
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     */
    private String jobName;

    /**
     * 员工部门简称
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     */
    private String logistCode;

    /**
     * 电话
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     */
    private String empMobile;

    /**
     * 公司级别
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     */
    private String deptLevel;

    /**
     * 创建人
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     */
    private String createUser;

    /**
     * 创建时间
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     */
    private Date createTime;

    /**
     * 修改人
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     */
    private String modifyUser;

    /**
     * 修改时间
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     */
    private Date modifyTime;

    private static final long serialVersionUID = 1L;

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @return the value of t_bis_oms_hr.ID
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @param id the value for t_bis_oms_hr.ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @return the value of t_bis_oms_hr.emp_no
     */
    public String getEmpNo() {
        return empNo;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @param empNo the value for t_bis_oms_hr.emp_no
     */
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @return the value of t_bis_oms_hr.emp_name
     */
    public String getEmpName() {
        return empName;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @param empName the value for t_bis_oms_hr.emp_name
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @return the value of t_bis_oms_hr.job_name
     */
    public String getJobName() {
        return jobName;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @param jobName the value for t_bis_oms_hr.job_name
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @return the value of t_bis_oms_hr.logist_code
     */
    public String getLogistCode() {
        return logistCode;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @param logistCode the value for t_bis_oms_hr.logist_code
     */
    public void setLogistCode(String logistCode) {
        this.logistCode = logistCode;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @return the value of t_bis_oms_hr.emp_mobile
     */
    public String getEmpMobile() {
        return empMobile;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @param empMobile the value for t_bis_oms_hr.emp_mobile
     */
    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @return the value of t_bis_oms_hr.dept_level
     */
    public String getDeptLevel() {
        return deptLevel;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @param deptLevel the value for t_bis_oms_hr.dept_level
     */
    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @return the value of t_bis_oms_hr.create_user
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @param createUser the value for t_bis_oms_hr.create_user
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @return the value of t_bis_oms_hr.create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @param createTime the value for t_bis_oms_hr.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @return the value of t_bis_oms_hr.modify_user
     */
    public String getModifyUser() {
        return modifyUser;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @param modifyUser the value for t_bis_oms_hr.modify_user
     */
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @return the value of t_bis_oms_hr.modify_time
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     *
     * @author 274010
     * @date 2016-01-23 17:17:51
     * @param modifyTime the value for t_bis_oms_hr.modify_time
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}