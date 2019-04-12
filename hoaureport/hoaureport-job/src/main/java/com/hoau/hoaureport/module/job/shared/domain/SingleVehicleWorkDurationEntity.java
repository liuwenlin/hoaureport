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
     * 车牌号
     */
    private String cph;

    /**
     * 上转移里程
     */
    private Integer szylc;

    /**
     * 上转移里程
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
     * 统计日期
     */
    private Date tjrq;

    /**
     * 记录时间
     */
    private Date record_date;

    public SingleVehicleWorkDurationEntity(){}

    public SingleVehicleWorkDurationEntity(String id, String cph, Integer szylc, Integer xzylc, Integer qjghlc, Integer pjghlc, Date tjrq, Date record_date) {
        this.id = id;
        this.cph = cph;
        this.szylc = szylc;
        this.xzylc = xzylc;
        this.qjghlc = qjghlc;
        this.pjghlc = pjghlc;
        this.tjrq = tjrq;
        this.record_date = record_date;
    }

    public String getId() {
        return id;
    }

    public String getCph() {
        return cph;
    }

    public Integer getSzylc() {
        return szylc;
    }

    public Integer getXzylc() {
        return xzylc;
    }

    public Integer getQjghlc() {
        return qjghlc;
    }

    public Integer getPjghlc() {
        return pjghlc;
    }

    public Date getTjrq() {
        return tjrq;
    }

    public Date getRecord_date() {
        return record_date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public void setSzylc(Integer szylc) {
        this.szylc = szylc;
    }

    public void setXzylc(Integer xzylc) {
        this.xzylc = xzylc;
    }

    public void setQjghlc(Integer qjghlc) {
        this.qjghlc = qjghlc;
    }

    public void setPjghlc(Integer pjghlc) {
        this.pjghlc = pjghlc;
    }

    public void setTjrq(Date tjrq) {
        this.tjrq = tjrq;
    }

    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
    }

    @Override
    public String toString() {
        return "SingleVehicleWorkDurationEntity{" +
                "id='" + id + '\'' +
                ", cph='" + cph + '\'' +
                ", szylc=" + szylc +
                ", xzylc=" + xzylc +
                ", qjghlc=" + qjghlc +
                ", pjghlc=" + pjghlc +
                ", tjrq=" + tjrq +
                ", record_date=" + record_date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SingleVehicleWorkDurationEntity that = (SingleVehicleWorkDurationEntity) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getCph(), that.getCph())
                .append(getSzylc(), that.getSzylc())
                .append(getXzylc(), that.getXzylc())
                .append(getQjghlc(), that.getQjghlc())
                .append(getPjghlc(), that.getPjghlc())
                .append(getTjrq(), that.getTjrq())
                .append(getRecord_date(), that.getRecord_date())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getCph())
                .append(getSzylc())
                .append(getXzylc())
                .append(getQjghlc())
                .append(getPjghlc())
                .append(getTjrq())
                .append(getRecord_date())
                .toHashCode();
    }
}
