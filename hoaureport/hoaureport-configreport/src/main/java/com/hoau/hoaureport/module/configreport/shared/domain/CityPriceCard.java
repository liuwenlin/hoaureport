package com.hoau.hoaureport.module.configreport.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * DD未兑现-城市价卡
 * ClassName: CityPriceCard 
 * @author 文洁
 * @date 2016年12月14日
 * @version V1.0
 */
public class CityPriceCard {
	//城市价卡ID
    private BigDecimal cityPriceCardId;
    //线路
    private String lineName;
    //起运地
    private String loadingPort;
    //目的地
    private String termini;
    //城市线路
    private String cityLine;
    //发货城市
    private String dispatchCity;
    //发货城市编码
    private String dispatchCityId;
    //到货城市
    private String arrivalCity;
    //到货城市编码
    private String arrivalCityId;
    //发货是否提供定日达
    private String serveLoadIntime;
    //发货是否偏远网点
    private String fromRemoteBranch;
    //到货是否提供定日达
    private String serveReachIntime;
    //到货是否偏远网点
    private String toRemoteBranch;
    //通知承诺时间
    private BigDecimal promiseNoteTime;
    //送货承诺时间
    private BigDecimal promiseDeliverTime;
    //开通月份
    private String openMonth;
    //星期一
    private String monday;
    //星期二
    private String tuesday;
    //星期三
    private String wednesday;
    //星期四
    private String thursday;
    //星期五
    private String friday;
    //星期六
    private String saturday;
    //星期日
    private String sunday;
    //备注
    private String note;
    //是否有效
    private String active;
    //创建者时间
    private Date createTime;
    //创建者编码
    private String createUserCode;
    //修改时间
    private Date modifyTime;
    //修改者编码
    private String modifyUserCode;
    
    //导入时间
    private Date importTime;
    //生效时间
    private Date effectedTime;
	//失效时间
    private Date invalidTime;

    //以下是setters和getters
    public BigDecimal getCityPriceCardId() {
        return cityPriceCardId;
    }

    public void setCityPriceCardId(BigDecimal cityPriceCardId) {
        this.cityPriceCardId = cityPriceCardId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLoadingPort() {
        return loadingPort;
    }

    public void setLoadingPort(String loadingPort) {
        this.loadingPort = loadingPort;
    }

    public String getTermini() {
        return termini;
    }

    public void setTermini(String termini) {
        this.termini = termini;
    }

    public String getCityLine() {
        return cityLine;
    }

    public void setCityLine(String cityLine) {
        this.cityLine = cityLine;
    }

    public String getDispatchCity() {
        return dispatchCity;
    }

    public void setDispatchCity(String dispatchCity) {
        this.dispatchCity = dispatchCity;
    }

    public String getDispatchCityId() {
        return dispatchCityId;
    }

    public void setDispatchCityId(String dispatchCityId) {
        this.dispatchCityId = dispatchCityId;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getArrivalCityId() {
        return arrivalCityId;
    }

    public void setArrivalCityId(String arrivalCityId) {
        this.arrivalCityId = arrivalCityId;
    }

    public String getServeLoadIntime() {
        return serveLoadIntime;
    }

    public void setServeLoadIntime(String serveLoadIntime) {
        this.serveLoadIntime = serveLoadIntime;
    }

    public String getFromRemoteBranch() {
        return fromRemoteBranch;
    }

    public void setFromRemoteBranch(String fromRemoteBranch) {
        this.fromRemoteBranch = fromRemoteBranch;
    }

    public String getServeReachIntime() {
        return serveReachIntime;
    }

    public void setServeReachIntime(String serveReachIntime) {
        this.serveReachIntime = serveReachIntime;
    }

    public String getToRemoteBranch() {
        return toRemoteBranch;
    }

    public void setToRemoteBranch(String toRemoteBranch) {
        this.toRemoteBranch = toRemoteBranch;
    }

    public BigDecimal getPromiseNoteTime() {
        return promiseNoteTime;
    }

    public void setPromiseNoteTime(BigDecimal promiseNoteTime) {
        this.promiseNoteTime = promiseNoteTime;
    }

    public BigDecimal getPromiseDeliverTime() {
        return promiseDeliverTime;
    }

    public void setPromiseDeliverTime(BigDecimal promiseDeliverTime) {
        this.promiseDeliverTime = promiseDeliverTime;
    }

    public String getOpenMonth() {
        return openMonth;
    }

    public void setOpenMonth(String openMonth) {
        this.openMonth = openMonth;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUserCode() {
        return modifyUserCode;
    }

    public void setModifyUserCode(String modifyUserCode) {
        this.modifyUserCode = modifyUserCode;
    }
    

    public Date getImportTime() {
		return importTime;
	}

	public void setImportTime(Date importTime) {
		this.importTime = importTime;
	}

	public Date getEffectedTime() {
		return effectedTime;
	}

	public void setEffectedTime(Date effectedTime) {
		this.effectedTime = effectedTime;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

}