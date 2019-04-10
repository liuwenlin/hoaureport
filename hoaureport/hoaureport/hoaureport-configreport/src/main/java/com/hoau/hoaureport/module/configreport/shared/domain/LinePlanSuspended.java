package com.hoau.hoaureport.module.configreport.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

public class LinePlanSuspended {
   
	private Long linePlanId;//序号

    private String startWork;//出发运作

    private String arriveWork;//到达运作

    private String startGridLines;//发车线路（原）

    private String gridLines;//发车线路

    private String belongLines;//所属路线 

    private String departureType;//单/对发

    private BigDecimal lineQuantity;//线路货量

    private Integer departureFrequency;//发车频率

    private String monday;//周一

    private String tuesday;//周二

    private String wednesday;//周三

    private String thursday;//周四

    private String friday;//周五

    private String saturday;//周六

    private String sunday;//周日

    private Integer lineKil;//线路公里数

    private String lineType;//线路类型

    private Integer shifts;//班次

    private Integer totalShifts;//总班次

    private Date departureTime;//发车时间

    private BigDecimal travelTimeOne;//在途时间1（h）

    private Date arrivalTimeOne;//到达时间1

    private Date departureTimeTwo;//发车时间2

    private BigDecimal travelTimeTwo;//在途时间2（h）

    private Date arrivalTimeTwo;//到达时间2

    private Date departureTimeThr;//发车时间3

    private BigDecimal travelTimeThr;//在途时间3（h）

    private Date arrivalTime;//到达时间

    private String carType;//车型

    private String trainNumber;//月承诺趟数

    private String actualNumCars;//实际车辆数

    private String demandForCars;//需求车辆数

    private String hangCarsDemand;//甩挂车辆需求

    private Date contractDate;//合同结束日期

    private String stopGoGoods;//停发走货规定

    private Date oppeningTime;//开通时间

    private String remarks;//备注

    private Date effectedTime;//

    private Date invalidTime;

    private String active;//是否有效

    private Date createTime;//创建时间

    private String createUserCode;//创建人工号

    private Date modifyTime;//修改时间

    private String modifyUserCode;//修改人工号

    public Long getLinePlanId() {
        return linePlanId;
    }

    public void setLinePlanId(Long linePlanId) {
        this.linePlanId = linePlanId;
    }
    public String getStartWork() {
        return startWork;
    }

    public void setStartWork(String startWork) {
        this.startWork = startWork;
    }

    public String getArriveWork() {
        return arriveWork;
    }

    public void setArriveWork(String arriveWork) {
        this.arriveWork = arriveWork;
    }

    public String getStartGridLines() {
        return startGridLines;
    }

    public void setStartGridLines(String startGridLines) {
        this.startGridLines = startGridLines;
    }

    public String getGridLines() {
        return gridLines;
    }

    public void setGridLines(String gridLines) {
        this.gridLines = gridLines;
    }

    public String getBelongLines() {
        return belongLines;
    }

    public void setBelongLines(String belongLines) {
        this.belongLines = belongLines;
    }

    public String getDepartureType() {
        return departureType;
    }

    public void setDepartureType(String departureType) {
        this.departureType = departureType;
    }

    public BigDecimal getLineQuantity() {
        return lineQuantity;
    }

    public void setLineQuantity(BigDecimal lineQuantity) {
        this.lineQuantity = lineQuantity;
    }

    public Integer getDepartureFrequency() {
        return departureFrequency;
    }

    public void setDepartureFrequency(Integer departureFrequency) {
        this.departureFrequency = departureFrequency;
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

    public Integer getLineKil() {
        return lineKil;
    }

    public void setLineKil(Integer lineKil) {
        this.lineKil = lineKil;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public Integer getShifts() {
        return shifts;
    }

    public void setShifts(Integer shifts) {
        this.shifts = shifts;
    }

    public Integer getTotalShifts() {
        return totalShifts;
    }

    public void setTotalShifts(Integer totalShifts) {
        this.totalShifts = totalShifts;
    }

    public BigDecimal getTravelTimeOne() {
        return travelTimeOne;
    }

    public void setTravelTimeOne(BigDecimal travelTimeOne) {
        this.travelTimeOne = travelTimeOne;
    }


    public BigDecimal getTravelTimeTwo() {
        return travelTimeTwo;
    }

    public void setTravelTimeTwo(BigDecimal travelTimeTwo) {
        this.travelTimeTwo = travelTimeTwo;
    }


    public BigDecimal getTravelTimeThr() {
        return travelTimeThr;
    }

    public void setTravelTimeThr(BigDecimal travelTimeThr) {
        this.travelTimeThr = travelTimeThr;
    }
    public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalTimeOne() {
		return arrivalTimeOne;
	}

	public void setArrivalTimeOne(Date arrivalTimeOne) {
		this.arrivalTimeOne = arrivalTimeOne;
	}

	public Date getDepartureTimeTwo() {
		return departureTimeTwo;
	}

	public void setDepartureTimeTwo(Date departureTimeTwo) {
		this.departureTimeTwo = departureTimeTwo;
	}

	public Date getArrivalTimeTwo() {
		return arrivalTimeTwo;
	}

	public void setArrivalTimeTwo(Date arrivalTimeTwo) {
		this.arrivalTimeTwo = arrivalTimeTwo;
	}

	public Date getDepartureTimeThr() {
		return departureTimeThr;
	}

	public void setDepartureTimeThr(Date departureTimeThr) {
		this.departureTimeThr = departureTimeThr;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getActualNumCars() {
        return actualNumCars;
    }

    public void setActualNumCars(String actualNumCars) {
        this.actualNumCars = actualNumCars;
    }

    public String getDemandForCars() {
        return demandForCars;
    }

    public void setDemandForCars(String demandForCars) {
        this.demandForCars = demandForCars;
    }

    public String getHangCarsDemand() {
        return hangCarsDemand;
    }

    public void setHangCarsDemand(String hangCarsDemand) {
        this.hangCarsDemand = hangCarsDemand;
    }


    public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public String getStopGoGoods() {
        return stopGoGoods;
    }

    public void setStopGoGoods(String stopGoGoods) {
        this.stopGoGoods = stopGoGoods;
    }


	public Date getOppeningTime() {
		return oppeningTime;
	}

	public void setOppeningTime(Date oppeningTime) {
		this.oppeningTime = oppeningTime;
	}

	public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
}