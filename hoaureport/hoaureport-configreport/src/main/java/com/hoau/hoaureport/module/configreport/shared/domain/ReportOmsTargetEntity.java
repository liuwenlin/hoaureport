package com.hoau.hoaureport.module.configreport.shared.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReportOmsTargetEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column REPORT_OMS_TARGET.ID
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    private BigDecimal id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column REPORT_OMS_TARGET.TARGET
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    private BigDecimal target;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column REPORT_OMS_TARGET.LAST_MONTH_FINISH
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    private BigDecimal lastMonthFinish;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column REPORT_OMS_TARGET.BELONG_MONTH
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    private Date belongMonth;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column REPORT_OMS_TARGET.IMPORT_DATE
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    private Date importDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column REPORT_OMS_TARGET.MANAGER
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    private String manager;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column REPORT_OMS_TARGET.CREATE_DATE
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column REPORT_OMS_TARGET.SYB
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    private String syb;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column REPORT_OMS_TARGET.JYDQ
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    private String jydq;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column REPORT_OMS_TARGET.MD
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    private String md;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column REPORT_OMS_TARGET.PX
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    private BigDecimal px;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table REPORT_OMS_TARGET
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column REPORT_OMS_TARGET.ID
     *
     * @return the value of REPORT_OMS_TARGET.ID
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column REPORT_OMS_TARGET.ID
     *
     * @param id the value for REPORT_OMS_TARGET.ID
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column REPORT_OMS_TARGET.TARGET
     *
     * @return the value of REPORT_OMS_TARGET.TARGET
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public BigDecimal getTarget() {
        return target;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column REPORT_OMS_TARGET.TARGET
     *
     * @param target the value for REPORT_OMS_TARGET.TARGET
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public void setTarget(BigDecimal target) {
        this.target = target;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column REPORT_OMS_TARGET.LAST_MONTH_FINISH
     *
     * @return the value of REPORT_OMS_TARGET.LAST_MONTH_FINISH
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public BigDecimal getLastMonthFinish() {
        return lastMonthFinish;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column REPORT_OMS_TARGET.LAST_MONTH_FINISH
     *
     * @param lastMonthFinish the value for REPORT_OMS_TARGET.LAST_MONTH_FINISH
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public void setLastMonthFinish(BigDecimal lastMonthFinish) {
        this.lastMonthFinish = lastMonthFinish;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column REPORT_OMS_TARGET.BELONG_MONTH
     *
     * @return the value of REPORT_OMS_TARGET.BELONG_MONTH
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public Date getBelongMonth() {
        return belongMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column REPORT_OMS_TARGET.BELONG_MONTH
     *
     * @param belongMonth the value for REPORT_OMS_TARGET.BELONG_MONTH
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public void setBelongMonth(Date belongMonth) {
        this.belongMonth = belongMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column REPORT_OMS_TARGET.IMPORT_DATE
     *
     * @return the value of REPORT_OMS_TARGET.IMPORT_DATE
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public Date getImportDate() {
        return importDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column REPORT_OMS_TARGET.IMPORT_DATE
     *
     * @param importDate the value for REPORT_OMS_TARGET.IMPORT_DATE
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column REPORT_OMS_TARGET.MANAGER
     *
     * @return the value of REPORT_OMS_TARGET.MANAGER
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public String getManager() {
        return manager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column REPORT_OMS_TARGET.MANAGER
     *
     * @param manager the value for REPORT_OMS_TARGET.MANAGER
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column REPORT_OMS_TARGET.CREATE_DATE
     *
     * @return the value of REPORT_OMS_TARGET.CREATE_DATE
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column REPORT_OMS_TARGET.CREATE_DATE
     *
     * @param createDate the value for REPORT_OMS_TARGET.CREATE_DATE
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column REPORT_OMS_TARGET.SYB
     *
     * @return the value of REPORT_OMS_TARGET.SYB
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public String getSyb() {
        return syb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column REPORT_OMS_TARGET.SYB
     *
     * @param syb the value for REPORT_OMS_TARGET.SYB
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public void setSyb(String syb) {
        this.syb = syb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column REPORT_OMS_TARGET.JYDQ
     *
     * @return the value of REPORT_OMS_TARGET.JYDQ
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public String getJydq() {
        return jydq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column REPORT_OMS_TARGET.JYDQ
     *
     * @param jydq the value for REPORT_OMS_TARGET.JYDQ
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public void setJydq(String jydq) {
        this.jydq = jydq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column REPORT_OMS_TARGET.MD
     *
     * @return the value of REPORT_OMS_TARGET.MD
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public String getMd() {
        return md;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column REPORT_OMS_TARGET.MD
     *
     * @param md the value for REPORT_OMS_TARGET.MD
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public void setMd(String md) {
        this.md = md;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column REPORT_OMS_TARGET.PX
     *
     * @return the value of REPORT_OMS_TARGET.PX
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public BigDecimal getPx() {
        return px;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column REPORT_OMS_TARGET.PX
     *
     * @param px the value for REPORT_OMS_TARGET.PX
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    public void setPx(BigDecimal px) {
        this.px = px;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table REPORT_OMS_TARGET
     *
     * @mbggenerated Fri Sep 29 15:09:27 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", target=").append(target);
        sb.append(", lastMonthFinish=").append(lastMonthFinish);
        sb.append(", belongMonth=").append(belongMonth);
        sb.append(", importDate=").append(importDate);
        sb.append(", manager=").append(manager);
        sb.append(", createDate=").append(createDate);
        sb.append(", syb=").append(syb);
        sb.append(", jydq=").append(jydq);
        sb.append(", md=").append(md);
        sb.append(", px=").append(px);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}