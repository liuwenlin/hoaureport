package com.hoau.hoaureport.module.job.shared.domain;

import com.hoau.hoaureport.module.job.shared.constant.BillType;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 提送货单编号及车辆实体
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/11 9:19
 */
public class GoodsPlanLineEntity implements Serializable {
    /**
     * 提送货单规划线路明细id
     */
    private String id;

    /**
     * 车牌号
     */
    private String cph;

    /**
     * 提送货单编号
     */
    private String goodsBill;

    /**
     * 提送货单编号类型
     */
    private BillType billType;

    /**
     * 提货公司地理编码
     */
    private String storeGeoCode;

    /**
     * 提货单运单
     */
    private List<GoodsOrdersEntity> orderGeoCodeList;

    /**
     * 当前提送货单地图返回的规划距离
     */
    private Integer goodsDistance;

    public GoodsPlanLineEntity(){}

    public GoodsPlanLineEntity(String id, String cph, String goodsBill, BillType billType, String storeGeoCode, List<GoodsOrdersEntity> orderGeoCodeList, Integer goodsDistance) {
        this.id = id;
        this.cph = cph;
        this.goodsBill = goodsBill;
        this.billType = billType;
        this.storeGeoCode = storeGeoCode;
        this.orderGeoCodeList = orderGeoCodeList;
        this.goodsDistance = goodsDistance;
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

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    public String getStoreGeoCode() {
        return storeGeoCode;
    }

    public void setStoreGeoCode(String storeGeoCode) {
        this.storeGeoCode = storeGeoCode;
    }

    public List<GoodsOrdersEntity> getOrderGeoCodeList() {
        return orderGeoCodeList;
    }

    public void setOrderGeoCodeList(List<GoodsOrdersEntity> orderGeoCodeList) {
        this.orderGeoCodeList = orderGeoCodeList;
    }

    public Integer getGoodsDistance() {
        return goodsDistance;
    }

    public void setGoodsDistance(Integer goodsDistance) {
        this.goodsDistance = goodsDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        GoodsPlanLineEntity that = (GoodsPlanLineEntity) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getCph(), that.getCph())
                .append(getGoodsBill(), that.getGoodsBill())
                .append(getBillType(), that.getBillType())
                .append(getStoreGeoCode(), that.getStoreGeoCode())
                .append(getOrderGeoCodeList(), that.getOrderGeoCodeList())
                .append(getGoodsDistance(), that.getGoodsDistance())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getCph())
                .append(getGoodsBill())
                .append(getBillType())
                .append(getStoreGeoCode())
                .append(getOrderGeoCodeList())
                .append(getGoodsDistance())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "GoodsPlanLineEntity{" +
                "id='" + id + '\'' +
                ", cph='" + cph + '\'' +
                ", goodsBill='" + goodsBill + '\'' +
                ", billType=" + billType +
                ", storeGeoCode='" + storeGeoCode + '\'' +
                ", orderGeoCodeList=" + orderGeoCodeList +
                ", goodsDistance=" + goodsDistance +
                '}';
    }
}
