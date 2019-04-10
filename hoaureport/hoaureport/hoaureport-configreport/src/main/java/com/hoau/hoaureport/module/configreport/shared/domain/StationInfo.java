package com.hoau.hoaureport.module.configreport.shared.domain;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * @author 刘镇松
 *
 */
public class StationInfo {
	
    private BigDecimal stationsId;
 // 场站简称
    private String stationsShortName;
 // 场站名称
    private String stationsName;
  //所属大区
    private String theArea;
 // 所属事业部
    private String theBusinessDepartment;
 // 是否有效
    private String active;
 // 生效时间
    private Date effectedTime;
 // 失效时间
    private Date invalidTime;
  //创建时间
    private Date createTime;
 // 创建者编码
    private String createUserCode;
 // 修改时间
    private Date modifyTime;
 // 修改者编号
    private String modifyUserCode;
    /**
     * LEADER 负责人
     */
    private String leader;
    
    /**
     * PHONE 联系方式
     */
    private String phone;
    
    /**
     * ADDRESS 场站地址
     */
    private String address;
    /**
     * WAREHOUSE_AREA 仓库面积
     */
    private BigDecimal warehouseArea;
    
    /**
     * DELIVERY_AREA 发货库面积
     */
    private BigDecimal deliveryArea;
    
    /**
     * ARRIVAL_AREA 到货库面积
     */
    private BigDecimal arrivalArea;

    public BigDecimal getStationsId() {
		return stationsId;
	}

	public void setStationsId(BigDecimal stationsId) {
		this.stationsId = stationsId;
	}

	public String getStationsShortName() {
        return stationsShortName;
    }

    public void setStationsShortName(String stationsShortName) {
        this.stationsShortName = stationsShortName;
    }

    public String getStationsName() {
        return stationsName;
    }

    public void setStationsName(String stationsName) {
        this.stationsName = stationsName;
    }

    public String getTheArea() {
        return theArea;
    }

    public void setTheArea(String theArea) {
        this.theArea = theArea;
    }

    public String getTheBusinessDepartment() {
        return theBusinessDepartment;
    }

    public void setTheBusinessDepartment(String theBusinessDepartment) {
        this.theBusinessDepartment = theBusinessDepartment;
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

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getWarehouseArea() {
		return warehouseArea;
	}

	public void setWarehouseArea(BigDecimal warehouseArea) {
		this.warehouseArea = warehouseArea;
	}

	public BigDecimal getDeliveryArea() {
		return deliveryArea;
	}

	public void setDeliveryArea(BigDecimal deliveryArea) {
		this.deliveryArea = deliveryArea;
	}

	public BigDecimal getArrivalArea() {
		return arrivalArea;
	}

	public void setArrivalArea(BigDecimal arrivalArea) {
		this.arrivalArea = arrivalArea;
	}
    
}