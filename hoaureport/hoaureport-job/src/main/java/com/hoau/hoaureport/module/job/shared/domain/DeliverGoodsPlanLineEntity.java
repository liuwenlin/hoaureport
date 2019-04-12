package com.hoau.hoaureport.module.job.shared.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 送货单车辆实体
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/10 23:38
 */
public class DeliverGoodsPlanLineEntity implements Serializable {
    /**
     * 送货规划线路表id
     */
    private String id;

    /**
     * 车牌号
     */
    private String cph;

    /**
     * 送货单号
     */
    private String deliverGoodsBill;

    /**
     * 送货公司地理编码
     */
    private String storeGeoCode;

    /**
     * 送货单运单
     */
    private List<DeliverGoodsOrders> orderGeoCodeList;

    /**
     * 当前送货单地图返回的规划距离
     */
    private Integer deliverGoodsDistance;

    public DeliverGoodsPlanLineEntity(){}


    public DeliverGoodsPlanLineEntity(String id, String cph, String deliverGoodsBill, String storeGeoCode, List<DeliverGoodsOrders> orderGeoCodeList, Integer deliverGoodsDistance) {
        this.id = id;
        this.cph = cph;
        this.deliverGoodsBill = deliverGoodsBill;
        this.storeGeoCode = storeGeoCode;
        this.orderGeoCodeList = orderGeoCodeList;
        this.deliverGoodsDistance = deliverGoodsDistance;
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

    public String getStoreGeoCode() {
        return storeGeoCode;
    }

    public void setStoreGeoCode(String storeGeoCode) {
        this.storeGeoCode = storeGeoCode;
    }

    public List<DeliverGoodsOrders> getOrderGeoCodeList() {
        return orderGeoCodeList;
    }

    public void setOrderGeoCodeList(List<DeliverGoodsOrders> orderGeoCodeList) {
        this.orderGeoCodeList = orderGeoCodeList;
    }

    public Integer getDeliverGoodsDistance() {
        return deliverGoodsDistance;
    }

    public void setDeliverGoodsDistance(Integer deliverGoodsDistance) {
        this.deliverGoodsDistance = deliverGoodsDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DeliverGoodsPlanLineEntity that = (DeliverGoodsPlanLineEntity) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getCph(), that.getCph())
                .append(getDeliverGoodsBill(), that.getDeliverGoodsBill())
                .append(getStoreGeoCode(), that.getStoreGeoCode())
                .append(getOrderGeoCodeList(), that.getOrderGeoCodeList())
                .append(getDeliverGoodsDistance(), that.getDeliverGoodsDistance())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getCph())
                .append(getDeliverGoodsBill())
                .append(getStoreGeoCode())
                .append(getOrderGeoCodeList())
                .append(getDeliverGoodsDistance())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "DeliverGoodsPlanLineEntity{" +
                "id='" + id + '\'' +
                ", cph='" + cph + '\'' +
                ", deliverGoodsBill='" + deliverGoodsBill + '\'' +
                ", storeGeoCode='" + storeGeoCode + '\'' +
                ", orderGeoCodeList=" + orderGeoCodeList +
                ", deliverGoodsDistance=" + deliverGoodsDistance +
                '}';
    }
}
