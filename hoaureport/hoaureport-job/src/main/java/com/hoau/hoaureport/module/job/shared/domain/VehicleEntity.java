package com.hoau.hoaureport.module.job.shared.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author linwenlin
 * @date 2018/3/5
 * @time 19:52
 */
public class VehicleEntity implements Serializable{

    // 车辆信息行记录主键
    private String id;

    // 车牌号
    @SerializedName(value = "Vehicle")
    private String vehicle;

    // 车辆ID
    @SerializedName(value = "VehicleID")
    private String vehicleID;

    // 定位时间
    @SerializedName(value="GPSTime")
    private Date gpsTime;

    // 速度
    @SerializedName(value="Speed")
    private double speed;

    // 里程
    @SerializedName(value="Odometer")
    private double odometer;

    // 纬度(WGS-84坐标系)
    @SerializedName(value="Lat")
    private double lat;

    // 经度(WGS-84坐标系)
    @SerializedName(value="Lon")
    private double lon;

    // 车头方向
    @SerializedName(value="Direction")
    private int direction;

    // 状态
    @SerializedName(value="Status")
    private String status;

    // 车辆位置
    @SerializedName(value="PlaceName")
    private String placeName;

    // 省
    @SerializedName(value="Provice")
    private String province;

    // 市
    @SerializedName(value="City")
    private String city;

    // 区
    @SerializedName(value="District")
    private String district;

    // 路名信息
    @SerializedName(value="RoadName")
    private String roadName;

    // 温度1(℃)
    @SerializedName(value="T1")
    private int t1;

    // 温度2(℃)
    @SerializedName(value="T2")
    private int t2;

    // 温度3(℃)
    @SerializedName(value="T3")
    private int t3;

    // 温度4(℃)
    @SerializedName(value="T4")
    private int t4;

    // 根据参数isoffsetlonlat返回的纬度
    @SerializedName(value="Lat02")
    private double lat02;

    // 根据参数isoffsetlonlat返回的经度
    @SerializedName(value="Lon02")
    private double lon02;

    // 地标名称
    @SerializedName(value="AreaName")
    private String areaName;

    // 温度1采集时间
    @SerializedName(value="Time1")
    private String time1;

    // 温度2采集时间
    @SerializedName(value="Time2")
    private String time2;

    // 温度3采集时间
    @SerializedName(value="Time3")
    private String time3;

    // 温度4采集时间
    @SerializedName(value="Time4")
    private String time4;

    @SerializedName(value="HT1")
    private int ht1;

    @SerializedName(value="HT2")
    private int ht2;

    @SerializedName(value="HT3")
    private int ht3;

    @SerializedName(value="HT4")
    private int ht4;

    // 数据记录时间
    private Date record_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public Date getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(Date gpsTime) {
        this.gpsTime = gpsTime;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getOdometer() {
        return odometer;
    }

    public void setOdometer(double odometer) {
        this.odometer = odometer;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public int getT1() {
        return t1;
    }

    public void setT1(int t1) {
        this.t1 = t1;
    }

    public int getT2() {
        return t2;
    }

    public void setT2(int t2) {
        this.t2 = t2;
    }

    public int getT3() {
        return t3;
    }

    public void setT3(int t3) {
        this.t3 = t3;
    }

    public int getT4() {
        return t4;
    }

    public void setT4(int t4) {
        this.t4 = t4;
    }

    public double getLat02() {
        return lat02;
    }

    public void setLat02(double lat02) {
        this.lat02 = lat02;
    }

    public double getLon02() {
        return lon02;
    }

    public void setLon02(double lon02) {
        this.lon02 = lon02;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }

    public String getTime4() {
        return time4;
    }

    public void setTime4(String time4) {
        this.time4 = time4;
    }

    public int getHt1() {
        return ht1;
    }

    public void setHt1(int ht1) {
        this.ht1 = ht1;
    }

    public int getHt2() {
        return ht2;
    }

    public void setHt2(int ht2) {
        this.ht2 = ht2;
    }

    public int getHt3() {
        return ht3;
    }

    public void setHt3(int ht3) {
        this.ht3 = ht3;
    }

    public int getHt4() {
        return ht4;
    }

    public void setHt4(int ht4) {
        this.ht4 = ht4;
    }

    public Date getRecord_date() {
        return record_date;
    }

    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleEntity)) return false;
        VehicleEntity that = (VehicleEntity) o;
        return this.getId().equals(that.id) &&
                this.getVehicle().equals(that.vehicle) &&
                this.getVehicleID().equals(that.vehicleID);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
        result = prime * result + ((vehicleID == null) ? 0 : vehicleID.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "id='" + id + '\'' +
                ", vehicle='" + vehicle + '\'' +
                ", vehicleID='" + vehicleID + '\'' +
                ", gpsTime=" + gpsTime +
                ", speed=" + speed +
                ", odometer=" + odometer +
                ", lat=" + lat +
                ", lon=" + lon +
                ", direction=" + direction +
                ", status='" + status + '\'' +
                ", placeName='" + placeName + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", roadName='" + roadName + '\'' +
                ", t1=" + t1 +
                ", t2=" + t2 +
                ", t3=" + t3 +
                ", t4=" + t4 +
                ", lat02=" + lat02 +
                ", lon02=" + lon02 +
                ", areaName='" + areaName + '\'' +
                ", time1=" + time1 +
                ", time2=" + time2 +
                ", time3=" + time3 +
                ", time4=" + time4 +
                ", ht1=" + ht1 +
                ", ht2=" + ht2 +
                ", ht3=" + ht3 +
                ", ht4=" + ht4 +
                ", record_date=" + record_date +
                '}';
    }
}
