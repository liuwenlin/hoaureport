package com.hoau.hoaureport.module.job.shared.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * 上转移串线线路实体
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/10 22:06
 */
public class UpTransferPlanLineEntity implements Serializable {
    /**
     * 上转移规划线路表id
     */
    private String id;

    /**
     * 车牌号
     */
    private String cph;

    /**
     * 上转移线路
     */
    private String line;

    /**
     * 上转移发车公司地理编码
     */
    private String startGeoCode;

    /**
     * 上转移途径公司地理编码
     */
    private String midGeoCode;

    /**
     * 上转移到车公司地理编码
     */
    private String endGeoCode;

    /**
     * 上转移规划行驶距离
     */
    private Integer plannedDistance;

    /**
     * 上转移发车趟数
     */
    private String times;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getStartGeoCode() {
        return startGeoCode;
    }

    public void setStartGeoCode(String startGeoCode) {
        this.startGeoCode = startGeoCode;
    }

    public String getMidGeoCode() {
        return midGeoCode;
    }

    public void setMidGeoCode(String midGeoCode) {
        this.midGeoCode = midGeoCode;
    }

    public String getEndGeoCode() {
        return endGeoCode;
    }

    public void setEndGeoCode(String endGeoCode) {
        this.endGeoCode = endGeoCode;
    }

    public Integer getPlannedDistance() {
        return plannedDistance;
    }

    public void setPlannedDistance(Integer plannedDistance) {
        this.plannedDistance = plannedDistance;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UpTransferPlanLineEntity that = (UpTransferPlanLineEntity) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getCph(), that.getCph())
                .append(getLine(), that.getLine())
                .append(getStartGeoCode(), that.getStartGeoCode())
                .append(getMidGeoCode(), that.getMidGeoCode())
                .append(getEndGeoCode(), that.getEndGeoCode())
                .append(getPlannedDistance(), that.getPlannedDistance())
                .append(getTimes(), that.getTimes())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getCph())
                .append(getLine())
                .append(getStartGeoCode())
                .append(getMidGeoCode())
                .append(getEndGeoCode())
                .append(getPlannedDistance())
                .append(getTimes())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "UpTransferPlanLineEntity{" +
                "id='" + id + '\'' +
                ", cph='" + cph + '\'' +
                ", line='" + line + '\'' +
                ", startGeoCode='" + startGeoCode + '\'' +
                ", midGeoCode='" + midGeoCode + '\'' +
                ", endGeoCode='" + endGeoCode + '\'' +
                ", plannedDistance='" + plannedDistance + '\'' +
                ", times='" + times + '\'' +
                '}';
    }
}
