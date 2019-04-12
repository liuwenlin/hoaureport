package com.hoau.hoaureport.module.job.shared.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * 送货单明细表实体
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/11 14:10
 */
public class DeliverGoodsOrders implements Serializable {

    /**
     * 送货单明细表id
     */
    private String id;

    /**
     * 车牌号
     */
    private String cph;

    /**
     * 送货单编号
     */
    private String deliverGoodsBill;

    /**
     * 运单编号
     */
    private String ydbh;

    /**
     * 运单送货地址
     */
    private String address;

    /**
     * 地理编码
     */
    private String geocode;

    /**
     * 运单送货顺序
     */
    private Integer deliverGoodsSequence;

    public DeliverGoodsOrders(){}

    public DeliverGoodsOrders(String id, String cph, String deliverGoodsBill, String ydbh, String address, String geocode, Integer deliverGoodsSequence) {
        this.id = id;
        this.cph = cph;
        this.deliverGoodsBill = deliverGoodsBill;
        this.ydbh = ydbh;
        this.address = address;
        this.geocode = geocode;
        this.deliverGoodsSequence = deliverGoodsSequence;
    }

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

    public String getDeliverGoodsBill() {
        return deliverGoodsBill;
    }

    public void setDeliverGoodsBill(String deliverGoodsBill) {
        this.deliverGoodsBill = deliverGoodsBill;
    }

    public String getYdbh() {
        return ydbh;
    }

    public void setYdbh(String ydbh) {
        this.ydbh = ydbh;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGeocode() {
        return geocode;
    }

    public void setGeocode(String geocode) {
        this.geocode = geocode;
    }

    public Integer getDeliverGoodsSequence() {
        return deliverGoodsSequence;
    }

    public void setDeliverGoodsSequence(Integer deliverGoodsSequence) {
        this.deliverGoodsSequence = deliverGoodsSequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DeliverGoodsOrders that = (DeliverGoodsOrders) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getCph(), that.getCph())
                .append(getDeliverGoodsBill(), that.getDeliverGoodsBill())
                .append(getYdbh(), that.getYdbh())
                .append(getAddress(), that.getAddress())
                .append(getGeocode(), that.getGeocode())
                .append(getDeliverGoodsSequence(), that.getDeliverGoodsSequence())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getCph())
                .append(getDeliverGoodsBill())
                .append(getYdbh())
                .append(getAddress())
                .append(getGeocode())
                .append(getDeliverGoodsSequence())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "DeliverGoodsOrders{" +
                "id='" + id + '\'' +
                ", cph='" + cph + '\'' +
                ", deliverGoodsBill='" + deliverGoodsBill + '\'' +
                ", ydbh='" + ydbh + '\'' +
                ", address='" + address + '\'' +
                ", geocode='" + geocode + '\'' +
                ", deliverGoodsSequence='" + deliverGoodsSequence + '\'' +
                '}';
    }
}
