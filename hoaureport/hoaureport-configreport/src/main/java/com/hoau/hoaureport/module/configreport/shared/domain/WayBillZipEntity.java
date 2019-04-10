package com.hoau.hoaureport.module.configreport.shared.domain;

import java.io.Serializable;
import java.util.Date;

public class WayBillZipEntity implements Serializable {
    /**
     * T_REPORT_WAYBILLZIP.ID
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     */
    private String id;

    /**
     * T_REPORT_WAYBILLZIP.ZIP_NUM
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     */
    private String zipNum;

    /**
     * T_REPORT_WAYBILLZIP.ZIP_PATH
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     */
    private String zipPath;

    /**
     * T_REPORT_WAYBILLZIP.ZIP_NAME
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     */
    private String zipName;

    /**
     * T_REPORT_WAYBILLZIP.ACTIVE
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     */
    private String active;

    /**
     * T_REPORT_WAYBILLZIP.CREATOR
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     */
    private String creator;

    /**
     * T_REPORT_WAYBILLZIP.CREATE_DATE
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     */
    private Date createDate;

    /**
     * T_REPORT_WAYBILLZIP.MODIFIER
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     */
    private String modifier;

    /**
     * T_REPORT_WAYBILLZIP.MODIFY_DATE
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     */
    private Date modifyDate;

    private Date endTime;
    
    private static final long serialVersionUID = 1L;

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @return the value of T_REPORT_WAYBILLZIP.ID
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @param id the value for T_REPORT_WAYBILLZIP.ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @return the value of T_REPORT_WAYBILLZIP.ZIP_NUM
     */
    public String getZipNum() {
        return zipNum;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @param zipNum the value for T_REPORT_WAYBILLZIP.ZIP_NUM
     */
    public void setZipNum(String zipNum) {
        this.zipNum = zipNum;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @return the value of T_REPORT_WAYBILLZIP.ZIP_PATH
     */
    public String getZipPath() {
        return zipPath;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @param zipPath the value for T_REPORT_WAYBILLZIP.ZIP_PATH
     */
    public void setZipPath(String zipPath) {
        this.zipPath = zipPath;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @return the value of T_REPORT_WAYBILLZIP.ZIP_NAME
     */
    public String getZipName() {
        return zipName;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @param zipName the value for T_REPORT_WAYBILLZIP.ZIP_NAME
     */
    public void setZipName(String zipName) {
        this.zipName = zipName;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @return the value of T_REPORT_WAYBILLZIP.ACTIVE
     */
    public String getActive() {
        return active;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @param active the value for T_REPORT_WAYBILLZIP.ACTIVE
     */
    public void setActive(String active) {
        this.active = active;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @return the value of T_REPORT_WAYBILLZIP.CREATOR
     */
    public String getCreator() {
        return creator;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @param creator the value for T_REPORT_WAYBILLZIP.CREATOR
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @return the value of T_REPORT_WAYBILLZIP.CREATE_DATE
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @param createDate the value for T_REPORT_WAYBILLZIP.CREATE_DATE
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @return the value of T_REPORT_WAYBILLZIP.MODIFIER
     */
    public String getModifier() {
        return modifier;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @param modifier the value for T_REPORT_WAYBILLZIP.MODIFIER
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @return the value of T_REPORT_WAYBILLZIP.MODIFY_DATE
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-11 18:49:45
     * @param modifyDate the value for T_REPORT_WAYBILLZIP.MODIFY_DATE
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
    
}