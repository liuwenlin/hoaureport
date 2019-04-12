package com.hoau.hoaureport.module.job.shared.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 上门提货交接单明细表实体
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/11 14:24
 */
public class PickupGoodsOrders {
    /**
     * 提货交接单明细表id
     */
    private String id;

    /**
     * 车牌号
     */
    private String cph;

    /**
     * 提货交接单编号
     */
    private String pickupGoodsBill;

    /**
     * 运单编号
     */
    private String ydbh;

    /**
     * 运单提货地址
     */
    private String address;

    /**
     * 地理编码
     */
    private String geocode;

    /**
     * 运单提货顺序
     */
    private Integer pickupGoodsSequence;

    public PickupGoodsOrders(){}

    public PickupGoodsOrders(String id, String cph, String pickupGoodsBill, String ydbh, String address, String geocode, Integer pickupGoodsSequence) {
        this.id = id;
        this.cph = cph;
        this.pickupGoodsBill = pickupGoodsBill;
        this.ydbh = ydbh;
        this.address = address;
        this.geocode = geocode;
        this.pickupGoodsSequence = pickupGoodsSequence;
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

    public String getPickupGoodsBill() {
        return pickupGoodsBill;
    }

    public void setPickupGoodsBill(String pickupGoodsBill) {
        this.pickupGoodsBill = pickupGoodsBill;
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

    public Integer getpickupGoodsSequence() {
        return pickupGoodsSequence;
    }

    public void setpickupGoodsSequence(Integer pickupGoodsSequence) {
        this.pickupGoodsSequence = pickupGoodsSequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PickupGoodsOrders that = (PickupGoodsOrders) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getCph(), that.getCph())
                .append(getPickupGoodsBill(), that.getPickupGoodsBill())
                .append(getYdbh(), that.getYdbh())
                .append(getAddress(), that.getAddress())
                .append(getGeocode(), that.getGeocode())
                .append(getpickupGoodsSequence(), that.getpickupGoodsSequence())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getCph())
                .append(getPickupGoodsBill())
                .append(getYdbh())
                .append(getAddress())
                .append(getGeocode())
                .append(getpickupGoodsSequence())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "PickupGoodsOrders{" +
                "id='" + id + '\'' +
                ", cph='" + cph + '\'' +
                ", pickupGoodsBill='" + pickupGoodsBill + '\'' +
                ", ydbh='" + ydbh + '\'' +
                ", address='" + address + '\'' +
                ", geocode='" + geocode + '\'' +
                ", pickupGoodsSequence=" + pickupGoodsSequence +
                '}';
    }
}
