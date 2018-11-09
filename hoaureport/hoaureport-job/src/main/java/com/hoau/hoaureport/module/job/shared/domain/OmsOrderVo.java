package com.hoau.hoaureport.module.job.shared.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * OMS取件实体VO
 */
public class OmsOrderVo  implements Serializable {


    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 发货城市
     */
    private String shipperCity;
    /**
     * 发货地址
     */
    private String shipperAddress;
    /**
     * 取件地址
     */
    private String pickupAddress;
    /**
     * 业务日期
     */
    private Date bizDay;
    /**
     * 地理位置解析状态
     */
    private int positionStatus;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getShipperCity() {
        return shipperCity;
    }

    public void setShipperCity(String shipperCity) {
        this.shipperCity = shipperCity;
    }

    public String getShipperAddress() {
        return shipperAddress;
    }

    public void setShipperAddress(String shipperAddress) {
        this.shipperAddress = shipperAddress;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public Date getBizDay() {
        return bizDay;
    }

    public void setBizDay(Date bizDay) {
        this.bizDay = bizDay;
    }

    public int getPositionStatus() {
        return positionStatus;
    }

    public void setPositionStatus(int positionStatus) {
        this.positionStatus = positionStatus;
    }
}
