package com.hoau.hoaureport.module.job.shared.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderAddressEnity implements Serializable {
    /**
     * id
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     */
    private String id;

    /**
     * 订单号
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     */
    private String orderno;

    /**
     * 发货市
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     */
    private String shipperCity;

    /**
     * 发货地址
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     */
    private String shipperAddres;

    /**
     * 发货经度
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     */
    private String shipperLng;

    /**
     * 发货维度
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     */
    private String shipperLat;

    /**
     * 取件地址
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     */
    private String pickupAddres;

    /**
     * 取件经度
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     */
    private String pickupLng;

    /**
     * 取件维度
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     */
    private String pickupLat;

    /**
     * 距离
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     */
    private BigDecimal orderDistance;

    /**
     * 解析状态(1:成功，2:失败)
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     */
    private int apistatus;

    /**
     * 创建日期
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     */
    private Date createDate;

    /**
     * 修改日期
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     */
    private Date modifyDate;

    /**
     * 创建人
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     */
    private String createUser;

    /**
     * 修改人
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     */
    private String modifyUser;

    private static final long serialVersionUID = 1L;

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @return the value of REPORT_OMSORDER_ADDRESS.ID
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @param id the value for REPORT_OMSORDER_ADDRESS.ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @return the value of REPORT_OMSORDER_ADDRESS.ORDERNO
     */
    public String getOrderno() {
        return orderno;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @param orderno the value for REPORT_OMSORDER_ADDRESS.ORDERNO
     */
    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @return the value of REPORT_OMSORDER_ADDRESS.SHIPPER_CITY
     */
    public String getShipperCity() {
        return shipperCity;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @param shipperCity the value for REPORT_OMSORDER_ADDRESS.SHIPPER_CITY
     */
    public void setShipperCity(String shipperCity) {
        this.shipperCity = shipperCity;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @return the value of REPORT_OMSORDER_ADDRESS.SHIPPER_ADDRES
     */
    public String getShipperAddres() {
        return shipperAddres;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @param shipperAddres the value for REPORT_OMSORDER_ADDRESS.SHIPPER_ADDRES
     */
    public void setShipperAddres(String shipperAddres) {
        this.shipperAddres = shipperAddres;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @return the value of REPORT_OMSORDER_ADDRESS.SHIPPER_LNG
     */
    public String getShipperLng() {
        return shipperLng;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @param shipperLng the value for REPORT_OMSORDER_ADDRESS.SHIPPER_LNG
     */
    public void setShipperLng(String shipperLng) {
        this.shipperLng = shipperLng;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @return the value of REPORT_OMSORDER_ADDRESS.SHIPPER_LAT
     */
    public String getShipperLat() {
        return shipperLat;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @param shipperLat the value for REPORT_OMSORDER_ADDRESS.SHIPPER_LAT
     */
    public void setShipperLat(String shipperLat) {
        this.shipperLat = shipperLat;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @return the value of REPORT_OMSORDER_ADDRESS.PICKUP_ADDRES
     */
    public String getPickupAddres() {
        return pickupAddres;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @param pickupAddres the value for REPORT_OMSORDER_ADDRESS.PICKUP_ADDRES
     */
    public void setPickupAddres(String pickupAddres) {
        this.pickupAddres = pickupAddres;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @return the value of REPORT_OMSORDER_ADDRESS.PICKUP_LNG
     */
    public String getPickupLng() {
        return pickupLng;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @param pickupLng the value for REPORT_OMSORDER_ADDRESS.PICKUP_LNG
     */
    public void setPickupLng(String pickupLng) {
        this.pickupLng = pickupLng;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @return the value of REPORT_OMSORDER_ADDRESS.PICKUP_LAT
     */
    public String getPickupLat() {
        return pickupLat;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @param pickupLat the value for REPORT_OMSORDER_ADDRESS.PICKUP_LAT
     */
    public void setPickupLat(String pickupLat) {
        this.pickupLat = pickupLat;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @return the value of REPORT_OMSORDER_ADDRESS.ORDER_DISTANCE
     */
    public BigDecimal getOrderDistance() {
        return orderDistance;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @param orderDistance the value for REPORT_OMSORDER_ADDRESS.ORDER_DISTANCE
     */
    public void setOrderDistance(BigDecimal orderDistance) {
        this.orderDistance = orderDistance;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @return the value of REPORT_OMSORDER_ADDRESS.APISTATUS
     */
    public int getApistatus() {
        return apistatus;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @param apistatus the value for REPORT_OMSORDER_ADDRESS.APISTATUS
     */
    public void setApistatus(int apistatus) {
        this.apistatus = apistatus;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @return the value of REPORT_OMSORDER_ADDRESS.CREATE_DATE
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @param createDate the value for REPORT_OMSORDER_ADDRESS.CREATE_DATE
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @return the value of REPORT_OMSORDER_ADDRESS.MODIFY_DATE
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @param modifyDate the value for REPORT_OMSORDER_ADDRESS.MODIFY_DATE
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @return the value of REPORT_OMSORDER_ADDRESS.CREATE_USER
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @param createUser the value for REPORT_OMSORDER_ADDRESS.CREATE_USER
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @return the value of REPORT_OMSORDER_ADDRESS.MODIFY_USER
     */
    public String getModifyUser() {
        return modifyUser;
    }

    /**
     *
     * @author lx
     * @date 2018-03-09 10:57:11
     * @param modifyUser the value for REPORT_OMSORDER_ADDRESS.MODIFY_USER
     */
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }
}