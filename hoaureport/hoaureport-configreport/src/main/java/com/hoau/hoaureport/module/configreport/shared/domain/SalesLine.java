package com.hoau.hoaureport.module.configreport.shared.domain;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 销售线路节点
 * ClassName: SalesLine 
 * @author 刘镇松
 * @date 2016年10月31日
 * @version V1.0
 */
public class SalesLine {
    private BigDecimal slId;

    private String lineNum;

    private String note;

    private String salesLineName;

    private String loadingPort;

    private String loadingPortId;

    private String transitDepot1;

    private String transitDepot2;

    private String termint;

    private String termintId;

    private String rely1Class;

    private String rely2Class;

    private String rely3Class;

    private String line1;

    private String line2;

    private String line3;

    private Date noTransferArrival;

    private Date oneTransferArrival;

    private Date twoTransferArrival;

    private String breakOneDayTimes;

    private String breakTwoDaysTimes;

    private String relyLine1;

    private String relyLine2;

    private String relyLine3;

    private String dispatchDayAndNextDayNum;

    private Date loadingPortFirstLevelDispa;

    private Date transitDepot1DispatchTime;

    private Date transitDepot2DispatchTime;

    private Date transitDepot1ArraivalTime;

    private Date transitDepot2ArraivalTime;

    private Date termitFirstArrivalTime;

    private BigDecimal transitHours1;

    private BigDecimal transitHours2;

    private BigDecimal transitHours3;

    private BigDecimal breakHours1;

    private BigDecimal breakHours2;

    private BigDecimal hoursInTransit;

    private BigDecimal unloadHours;

    private String terminalShortLongLines;

    private String arrival0And1;

    private String openMonth;

    private String lineStatus;

    private String pickupDays;

    private String deliverDays;

    private String monday;

    private String tuesday;

    private String wednesday;

    private String thusday;

    private String friday;

    private String saturday;

    private String sunday;

    private String timelyNote;

    private String openLineYear;

    private String isOpenGolden100Lines;

    private String standardNote;

    private String linePartner;

    private String active;

    private Date effectedTime;

    private Date invalidTime;

    private Date createTime;

    private String createUserCode;

    private Date modifyTime;

    private String modifyUserCode;

    public BigDecimal getSlId() {
		return slId;
	}

	public void setSlId(BigDecimal slId) {
		this.slId = slId;
	}

	public String getLineNum() {
        return lineNum;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSalesLineName() {
        return salesLineName;
    }

    public void setSalesLineName(String salesLineName) {
        this.salesLineName = salesLineName;
    }

    public String getLoadingPort() {
        return loadingPort;
    }

    public void setLoadingPort(String loadingPort) {
        this.loadingPort = loadingPort;
    }

    public String getLoadingPortId() {
        return loadingPortId;
    }

    public void setLoadingPortId(String loadingPortId) {
        this.loadingPortId = loadingPortId;
    }

    public String getTransitDepot1() {
        return transitDepot1;
    }

    public void setTransitDepot1(String transitDepot1) {
        this.transitDepot1 = transitDepot1;
    }

    public String getTransitDepot2() {
        return transitDepot2;
    }

    public void setTransitDepot2(String transitDepot2) {
        this.transitDepot2 = transitDepot2;
    }

    public String getTermint() {
        return termint;
    }

    public void setTermint(String termint) {
        this.termint = termint;
    }

    public String getTermintId() {
        return termintId;
    }

    public void setTermintId(String termintId) {
        this.termintId = termintId;
    }

    public String getRely1Class() {
        return rely1Class;
    }

    public void setRely1Class(String rely1Class) {
        this.rely1Class = rely1Class;
    }

    public String getRely2Class() {
        return rely2Class;
    }

    public void setRely2Class(String rely2Class) {
        this.rely2Class = rely2Class;
    }

    public String getRely3Class() {
        return rely3Class;
    }

    public void setRely3Class(String rely3Class) {
        this.rely3Class = rely3Class;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public Date getNoTransferArrival() {
        return noTransferArrival;
    }

    public void setNoTransferArrival(Date noTransferArrival) {
        this.noTransferArrival = noTransferArrival;
    }

    public Date getOneTransferArrival() {
        return oneTransferArrival;
    }

    public void setOneTransferArrival(Date oneTransferArrival) {
        this.oneTransferArrival = oneTransferArrival;
    }

    public Date getTwoTransferArrival() {
        return twoTransferArrival;
    }

    public void setTwoTransferArrival(Date twoTransferArrival) {
        this.twoTransferArrival = twoTransferArrival;
    }

    public String getBreakOneDayTimes() {
        return breakOneDayTimes;
    }

    public void setBreakOneDayTimes(String breakOneDayTimes) {
        this.breakOneDayTimes = breakOneDayTimes;
    }

    public String getBreakTwoDaysTimes() {
        return breakTwoDaysTimes;
    }

    public void setBreakTwoDaysTimes(String breakTwoDaysTimes) {
        this.breakTwoDaysTimes = breakTwoDaysTimes;
    }

    public String getRelyLine1() {
        return relyLine1;
    }

    public void setRelyLine1(String relyLine1) {
        this.relyLine1 = relyLine1;
    }

    public String getRelyLine2() {
        return relyLine2;
    }

    public void setRelyLine2(String relyLine2) {
        this.relyLine2 = relyLine2;
    }

    public String getRelyLine3() {
        return relyLine3;
    }

    public void setRelyLine3(String relyLine3) {
        this.relyLine3 = relyLine3;
    }

    public String getDispatchDayAndNextDayNum() {
        return dispatchDayAndNextDayNum;
    }

    public void setDispatchDayAndNextDayNum(String dispatchDayAndNextDayNum) {
        this.dispatchDayAndNextDayNum = dispatchDayAndNextDayNum;
    }

    public Date getLoadingPortFirstLevelDispa() {
        return loadingPortFirstLevelDispa;
    }

    public void setLoadingPortFirstLevelDispa(Date loadingPortFirstLevelDispa) {
        this.loadingPortFirstLevelDispa = loadingPortFirstLevelDispa;
    }

    public Date getTransitDepot1DispatchTime() {
        return transitDepot1DispatchTime;
    }

    public void setTransitDepot1DispatchTime(Date transitDepot1DispatchTime) {
        this.transitDepot1DispatchTime = transitDepot1DispatchTime;
    }

    public Date getTransitDepot2DispatchTime() {
        return transitDepot2DispatchTime;
    }

    public void setTransitDepot2DispatchTime(Date transitDepot2DispatchTime) {
        this.transitDepot2DispatchTime = transitDepot2DispatchTime;
    }

    public Date getTransitDepot1ArraivalTime() {
        return transitDepot1ArraivalTime;
    }

    public void setTransitDepot1ArraivalTime(Date transitDepot1ArraivalTime) {
        this.transitDepot1ArraivalTime = transitDepot1ArraivalTime;
    }

    public Date getTransitDepot2ArraivalTime() {
        return transitDepot2ArraivalTime;
    }

    public void setTransitDepot2ArraivalTime(Date transitDepot2ArraivalTime) {
        this.transitDepot2ArraivalTime = transitDepot2ArraivalTime;
    }

    public Date getTermitFirstArrivalTime() {
        return termitFirstArrivalTime;
    }

    public void setTermitFirstArrivalTime(Date termitFirstArrivalTime) {
        this.termitFirstArrivalTime = termitFirstArrivalTime;
    }

    public BigDecimal getTransitHours1() {
		return transitHours1;
	}

	public void setTransitHours1(BigDecimal transitHours1) {
		this.transitHours1 = transitHours1;
	}

	public BigDecimal getTransitHours2() {
		return transitHours2;
	}

	public void setTransitHours2(BigDecimal transitHours2) {
		this.transitHours2 = transitHours2;
	}

	public BigDecimal getTransitHours3() {
		return transitHours3;
	}

	public void setTransitHours3(BigDecimal transitHours3) {
		this.transitHours3 = transitHours3;
	}

	public BigDecimal getBreakHours1() {
        return breakHours1;
    }

    public void setBreakHours1(BigDecimal breakHours1) {
        this.breakHours1 = breakHours1;
    }

    public BigDecimal getBreakHours2() {
        return breakHours2;
    }

    public void setBreakHours2(BigDecimal breakHours2) {
        this.breakHours2 = breakHours2;
    }

    public BigDecimal getHoursInTransit() {
        return hoursInTransit;
    }

    public void setHoursInTransit(BigDecimal hoursInTransit) {
        this.hoursInTransit = hoursInTransit;
    }

    public BigDecimal getUnloadHours() {
        return unloadHours;
    }

    public void setUnloadHours(BigDecimal unloadHours) {
        this.unloadHours = unloadHours;
    }

    public String getTerminalShortLongLines() {
        return terminalShortLongLines;
    }

    public void setTerminalShortLongLines(String terminalShortLongLines) {
        this.terminalShortLongLines = terminalShortLongLines;
    }

    public String getArrival0And1() {
        return arrival0And1;
    }

    public void setArrival0And1(String arrival0And1) {
        this.arrival0And1 = arrival0And1;
    }

    public String getOpenMonth() {
        return openMonth;
    }

    public void setOpenMonth(String openMonth) {
        this.openMonth = openMonth;
    }

    public String getLineStatus() {
        return lineStatus;
    }

    public void setLineStatus(String lineStatus) {
        this.lineStatus = lineStatus;
    }

    public String getPickupDays() {
        return pickupDays;
    }

    public void setPickupDays(String pickupDays) {
        this.pickupDays = pickupDays;
    }

    public String getDeliverDays() {
        return deliverDays;
    }

    public void setDeliverDays(String deliverDays) {
        this.deliverDays = deliverDays;
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

    public String getThusday() {
        return thusday;
    }

    public void setThusday(String thusday) {
        this.thusday = thusday;
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

    public String getTimelyNote() {
        return timelyNote;
    }

    public void setTimelyNote(String timelyNote) {
        this.timelyNote = timelyNote;
    }

    public String getOpenLineYear() {
        return openLineYear;
    }

    public void setOpenLineYear(String openLineYear) {
        this.openLineYear = openLineYear;
    }

    public String getIsOpenGolden100Lines() {
        return isOpenGolden100Lines;
    }

    public void setIsOpenGolden100Lines(String isOpenGolden100Lines) {
        this.isOpenGolden100Lines = isOpenGolden100Lines;
    }

    public String getStandardNote() {
        return standardNote;
    }

    public void setStandardNote(String standardNote) {
        this.standardNote = standardNote;
    }

    public String getLinePartner() {
        return linePartner;
    }

    public void setLinePartner(String linePartner) {
        this.linePartner = linePartner;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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
}