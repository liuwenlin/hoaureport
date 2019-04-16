package com.hoau.hoaureport.module.job.shared.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.sql.Date;

/**
 * 单车工作时长表实体
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/10 17:09
 */
public class SingleVehicleWorkDurationEntity implements Serializable {
    /**
     * 单车工作时长表id主键
     */
    private String id;

    /**
     * 事业部
     */
    private String syb;

    /**
     * 大区
     */
    private String dq;

    /**
     * 是否单人单车
     */
    private String sfdrdc;

    /**
     * 车牌号
     */
    private String cph;

    /**
     * 车型编码
     */
    private String cx;

    /**
     * 上转移里程
     */
    private Integer szylc;

    /**
     * 下转移里程
     */
    private Integer xzylc;

    /**
     * 取件规划里程
     */
    private Integer qjghlc;

    /**
     * 派件规划里程
     */
    private Integer pjghlc;

    /**
     * 下转移吨位
     */
    private Integer xzydw;

    /**
     * 上转移吨位
     */
    private Integer szydw;

    /**
     * 取件吨位
     */
    private Integer qjdw;

    /**
     * 派件吨位
     */
    private Integer pjdw;

    /**
     * 取件件数
     */
    private Integer qjjs;

    /**
     * 派件件数
     */
    private Integer pjjs;

    /**
     * 统计日期
     */
    private Date tjrq;

    /**
     * 记录时间
     */
    private Date record_date;

    public SingleVehicleWorkDurationEntity(){}

    public SingleVehicleWorkDurationEntity(String syb, String dq, String sfdrdc, String cph, String cx, Integer xzydw, Integer szydw, Integer qjdw, Integer pjdw, Integer qjjs, Integer pjjs, Date tjrq) {
        this.syb = syb;
        this.dq = dq;
        this.sfdrdc = sfdrdc;
        this.cph = cph;
        this.cx = cx;
        this.xzydw = xzydw;
        this.szydw = szydw;
        this.qjdw = qjdw;
        this.pjdw = pjdw;
        this.qjjs = qjjs;
        this.pjjs = pjjs;
        this.tjrq = tjrq;
    }

    public SingleVehicleWorkDurationEntity(String id, String syb, String dq, String sfdrdc, String cph, String cx, Integer szylc, Integer xzylc, Integer qjghlc, Integer pjghlc, Integer xzydw, Integer szydw, Integer qjdw, Integer pjdw, Integer qjjs, Integer pjjs, Date tjrq, Date record_date) {
        this.id = id;
        this.syb = syb;
        this.dq = dq;
        this.sfdrdc = sfdrdc;
        this.cph = cph;
        this.cx = cx;
        this.szylc = szylc;
        this.xzylc = xzylc;
        this.qjghlc = qjghlc;
        this.pjghlc = pjghlc;
        this.xzydw = xzydw;
        this.szydw = szydw;
        this.qjdw = qjdw;
        this.pjdw = pjdw;
        this.qjjs = qjjs;
        this.pjjs = pjjs;
        this.tjrq = tjrq;
        this.record_date = record_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSyb() {
        return syb;
    }

    public void setSyb(String syb) {
        this.syb = syb;
    }

    public String getDq() {
        return dq;
    }

    public void setDq(String dq) {
        this.dq = dq;
    }

    public String getSfdrdc() {
        return sfdrdc;
    }

    public void setSfdrdc(String sfdrdc) {
        this.sfdrdc = sfdrdc;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public String getCx() {
        return cx;
    }

    public void setCx(String cx) {
        this.cx = cx;
    }

    public Integer getSzylc() {
        return szylc;
    }

    public void setSzylc(Integer szylc) {
        this.szylc = szylc;
    }

    public Integer getXzylc() {
        return xzylc;
    }

    public void setXzylc(Integer xzylc) {
        this.xzylc = xzylc;
    }

    public Integer getQjghlc() {
        return qjghlc;
    }

    public void setQjghlc(Integer qjghlc) {
        this.qjghlc = qjghlc;
    }

    public Integer getPjghlc() {
        return pjghlc;
    }

    public void setPjghlc(Integer pjghlc) {
        this.pjghlc = pjghlc;
    }

    public Integer getXzydw() {
        return xzydw;
    }

    public void setXzydw(Integer xzydw) {
        this.xzydw = xzydw;
    }

    public Integer getSzydw() {
        return szydw;
    }

    public void setSzydw(Integer szydw) {
        this.szydw = szydw;
    }

    public Integer getQjdw() {
        return qjdw;
    }

    public void setQjdw(Integer qjdw) {
        this.qjdw = qjdw;
    }

    public Integer getPjdw() {
        return pjdw;
    }

    public void setPjdw(Integer pjdw) {
        this.pjdw = pjdw;
    }

    public Integer getQjjs() {
        return qjjs;
    }

    public void setQjjs(Integer qjjs) {
        this.qjjs = qjjs;
    }

    public Integer getPjjs() {
        return pjjs;
    }

    public void setPjjs(Integer pjjs) {
        this.pjjs = pjjs;
    }

    public Date getTjrq() {
        return tjrq;
    }

    public void setTjrq(Date tjrq) {
        this.tjrq = tjrq;
    }

    public Date getRecord_date() {
        return record_date;
    }

    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SingleVehicleWorkDurationEntity that = (SingleVehicleWorkDurationEntity) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getSyb(), that.getSyb())
                .append(getDq(), that.getDq())
                .append(getSfdrdc(), that.getSfdrdc())
                .append(getCph(), that.getCph())
                .append(getCx(), that.getCx())
                .append(getSzylc(), that.getSzylc())
                .append(getXzylc(), that.getXzylc())
                .append(getQjghlc(), that.getQjghlc())
                .append(getPjghlc(), that.getPjghlc())
                .append(getXzydw(), that.getXzydw())
                .append(getSzydw(), that.getSzydw())
                .append(getQjdw(), that.getQjdw())
                .append(getPjdw(), that.getPjdw())
                .append(getQjjs(), that.getQjjs())
                .append(getPjjs(), that.getPjjs())
                .append(getTjrq(), that.getTjrq())
                .append(getRecord_date(), that.getRecord_date())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getSyb())
                .append(getDq())
                .append(getSfdrdc())
                .append(getCph())
                .append(getCx())
                .append(getSzylc())
                .append(getXzylc())
                .append(getQjghlc())
                .append(getPjghlc())
                .append(getXzydw())
                .append(getSzydw())
                .append(getQjdw())
                .append(getPjdw())
                .append(getQjjs())
                .append(getPjjs())
                .append(getTjrq())
                .append(getRecord_date())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "SingleVehicleWorkDurationEntity{" +
                "id='" + id + '\'' +
                ", syb='" + syb + '\'' +
                ", dq='" + dq + '\'' +
                ", sfdrdc='" + sfdrdc + '\'' +
                ", cph='" + cph + '\'' +
                ", cx='" + cx + '\'' +
                ", szylc=" + szylc +
                ", xzylc=" + xzylc +
                ", qjghlc=" + qjghlc +
                ", pjghlc=" + pjghlc +
                ", xzydw=" + xzydw +
                ", szydw=" + szydw +
                ", qjdw=" + qjdw +
                ", pjdw=" + pjdw +
                ", qjjs=" + qjjs +
                ", pjjs=" + pjjs +
                ", tjrq=" + tjrq +
                ", record_date=" + record_date +
                '}';
    }
}
