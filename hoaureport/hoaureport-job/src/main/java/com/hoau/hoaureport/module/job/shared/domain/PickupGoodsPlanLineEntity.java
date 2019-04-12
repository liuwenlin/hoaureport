package com.hoau.hoaureport.module.job.shared.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 上门提货入库交接单车辆实体
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/11 9:19
 */
public class PickupGoodsPlanLineEntity implements Serializable {
    /**
     * 提货规划线路表id
     */
    private String id;

    /**
     * 车牌号
     */
    private String cph;

    /**
     * 提货单号
     */
    private String pickupGoodsBill;

    /**
     * 提货公司地理编码
     */
    private String storeGeoCode;

    /**
     * 提货单运单
     */
    private List<PickupGoodsOrders> orderGeoCodeList;

    /**
     * 当前提货入库交接单地图返回的规划距离
     */
    private Integer deliverGoodsDistance;

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

    public String getStoreGeoCode() {
        return storeGeoCode;
    }

    public void setStoreGeoCode(String storeGeoCode) {
        this.storeGeoCode = storeGeoCode;
    }

    public List<PickupGoodsOrders> getOrderGeoCodeList() {
        return orderGeoCodeList;
    }

    public void setOrderGeoCodeList(List<PickupGoodsOrders> orderGeoCodeList) {
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

        PickupGoodsPlanLineEntity that = (PickupGoodsPlanLineEntity) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getCph(), that.getCph())
                .append(getPickupGoodsBill(), that.getPickupGoodsBill())
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
                .append(getPickupGoodsBill())
                .append(getStoreGeoCode())
                .append(getOrderGeoCodeList())
                .append(getDeliverGoodsDistance())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "PickupGoodsPlanLineEntity{" +
                "id='" + id + '\'' +
                ", cph='" + cph + '\'' +
                ", deliverGoodsBill='" + pickupGoodsBill + '\'' +
                ", storeGeoCode='" + storeGeoCode + '\'' +
                ", orderGeoCodeList=" + orderGeoCodeList +
                ", deliverGoodsDistance=" + deliverGoodsDistance +
                '}';
    }
}
