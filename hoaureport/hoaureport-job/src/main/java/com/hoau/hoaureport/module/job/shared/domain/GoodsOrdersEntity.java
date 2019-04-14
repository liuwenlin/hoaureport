package com.hoau.hoaureport.module.job.shared.domain;

import com.hoau.hoaureport.module.job.shared.constant.BillType;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * 提送货单明细实体
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/11 14:24
 */
public class GoodsOrdersEntity implements Serializable {
    /**
     * 提送货物单明细id
     */
    private String id;

    /**
     * 车牌号
     */
    private String cph;

    /**
     * 提送货物单编号
     */
    private String goodsBill;

    /**
     * 提送货单编号类型
     */
    private String billType;

    /**
     * 运单编号
     */
    private String ydbh;

    /**
     * 城市
     */
    private String city;

    /**
     * 运单地址
     */
    private String address;

    /**
     * 地理编码
     */
    private String geocode;

    /**
     * 运单提货顺序
     */
    private Integer goodsSequence;

    public GoodsOrdersEntity(){}

    public GoodsOrdersEntity(String id, String cph, String goodsBill, String billType, String ydbh, String city, String address, String geocode, Integer goodsSequence) {
        this.id = id;
        this.cph = cph;
        this.goodsBill = goodsBill;
        this.billType = billType;
        this.ydbh = ydbh;
        this.city = city;
        this.address = address;
        this.geocode = geocode;
        this.goodsSequence = goodsSequence;
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

    public String getGoodsBill() {
        return goodsBill;
    }

    public void setGoodsBill(String goodsBill) {
        this.goodsBill = goodsBill;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getYdbh() {
        return ydbh;
    }

    public void setYdbh(String ydbh) {
        this.ydbh = ydbh;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public Integer getGoodsSequence() {
        return goodsSequence;
    }

    public void setGoodsSequence(Integer goodsSequence) {
        this.goodsSequence = goodsSequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        GoodsOrdersEntity that = (GoodsOrdersEntity) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getId(), that.getId())
                .append(getCph(), that.getCph())
                .append(getGoodsBill(), that.getGoodsBill())
                .append(getBillType(), that.getBillType())
                .append(getYdbh(), that.getYdbh())
                .append(getCity(), that.getCity())
                .append(getAddress(), that.getAddress())
                .append(getGeocode(), that.getGeocode())
                .append(getGoodsSequence(), that.getGoodsSequence())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getId())
                .append(getCph())
                .append(getGoodsBill())
                .append(getBillType())
                .append(getYdbh())
                .append(getCity())
                .append(getAddress())
                .append(getGeocode())
                .append(getGoodsSequence())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "GoodsOrdersEntity{" +
                "id='" + id + '\'' +
                ", cph='" + cph + '\'' +
                ", goodsBill='" + goodsBill + '\'' +
                ", billType='" + billType + '\'' +
                ", ydbh='" + ydbh + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", geocode='" + geocode + '\'' +
                ", goodsSequence=" + goodsSequence +
                '}';
    }
}
