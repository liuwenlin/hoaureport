package com.hoau.hoaureport.module.configreport.shared.domain;

import java.io.Serializable;
import java.util.Date;

public class MonthSqlEntity implements Serializable {
    /**
     * T_REPORT_MONTHSQL.ID
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     */
    private String id;

    /**
     * T_REPORT_MONTHSQL.SQL_ID
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     */
    private String sqlId;

    /**
     * T_REPORT_MONTHSQL.SQL_NAME
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     */
    private String sqlName;

    /**
     * T_REPORT_MONTHSQL.SQL_SEQ
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     */
    private Short sqlSeq;

    /**
     * T_REPORT_MONTHSQL.ACTIVE
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     */
    private String active;

    /**
     * T_REPORT_MONTHSQL.CREATOR
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     */
    private String creator;

    /**
     * T_REPORT_MONTHSQL.CREATE_DATE
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     */
    private Date createDate;

    /**
     * T_REPORT_MONTHSQL.MODIFIER
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     */
    private String modifier;

    /**
     * T_REPORT_MONTHSQL.MODIFY_DATE
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     */
    private Date modifyDate;

    /**
     * T_REPORT_MONTHSQL.SQL_TYPE
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     */
    private String sqlType;

    /**
     * T_REPORT_MONTHSQL.SQL_BODY
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     */
    private byte[] sqlBody;
    
    private String sqlBodyStr;
    /**
     * 编码格式
     */
    private String charCode;
    
    /**
     * 描述
     */
    private String remark;
    

    private static final long serialVersionUID = 1L;

    
    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @return the value of T_REPORT_MONTHSQL.ID
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @param id the value for T_REPORT_MONTHSQL.ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @return the value of T_REPORT_MONTHSQL.SQL_ID
     */
    public String getSqlId() {
        return sqlId;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @param sqlId the value for T_REPORT_MONTHSQL.SQL_ID
     */
    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @return the value of T_REPORT_MONTHSQL.SQL_NAME
     */
    public String getSqlName() {
        return sqlName;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @param sqlName the value for T_REPORT_MONTHSQL.SQL_NAME
     */
    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @return the value of T_REPORT_MONTHSQL.SQL_SEQ
     */
    public Short getSqlSeq() {
        return sqlSeq;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @param sqlSeq the value for T_REPORT_MONTHSQL.SQL_SEQ
     */
    public void setSqlSeq(Short sqlSeq) {
        this.sqlSeq = sqlSeq;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @return the value of T_REPORT_MONTHSQL.ACTIVE
     */
    public String getActive() {
        return active;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @param active the value for T_REPORT_MONTHSQL.ACTIVE
     */
    public void setActive(String active) {
        this.active = active;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @return the value of T_REPORT_MONTHSQL.CREATOR
     */
    public String getCreator() {
        return creator;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @param creator the value for T_REPORT_MONTHSQL.CREATOR
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @return the value of T_REPORT_MONTHSQL.CREATE_DATE
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @param createDate the value for T_REPORT_MONTHSQL.CREATE_DATE
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @return the value of T_REPORT_MONTHSQL.MODIFIER
     */
    public String getModifier() {
        return modifier;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @param modifier the value for T_REPORT_MONTHSQL.MODIFIER
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @return the value of T_REPORT_MONTHSQL.MODIFY_DATE
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @param modifyDate the value for T_REPORT_MONTHSQL.MODIFY_DATE
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @return the value of T_REPORT_MONTHSQL.SQL_TYPE
     */
    public String getSqlType() {
        return sqlType;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @param sqlType the value for T_REPORT_MONTHSQL.SQL_TYPE
     */
    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @return the value of T_REPORT_MONTHSQL.SQL_BODY
     */
    public byte[] getSqlBody() {
        return sqlBody;
    }

    /**
     *
     * @author 273503
     * @date 2016-07-12 17:19:57
     * @param sqlBody the value for T_REPORT_MONTHSQL.SQL_BODY
     */
    public void setSqlBody(byte[] sqlBody) {
        this.sqlBody = sqlBody;
    }

	public String getSqlBodyStr() {
		return sqlBodyStr;
	}

	public void setSqlBodyStr(String sqlBodyStr) {
		this.sqlBodyStr = sqlBodyStr;
	}

	public String getCharCode() {
		return charCode;
	}

	public void setCharCode(String charCode) {
		this.charCode = charCode;
	}
    
}