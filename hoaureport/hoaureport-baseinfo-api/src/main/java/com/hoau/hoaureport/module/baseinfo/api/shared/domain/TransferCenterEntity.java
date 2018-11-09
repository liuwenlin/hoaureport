package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * @author：高佳
 * @create：2015年7月6日 下午2:23:21
 * @description：场站实体
 */
public class TransferCenterEntity extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 外场编码
	 */
	private String code;

	/**
	 * 外场名称
	 */
	private String name;
	
	/**
	 * 物流代码
	 */
	private String logistCode;

	/**
	 * 可汽运配载
	 */
	private String isVehicleAssemble;

	/**
	 * 可外发配载
	 */
	private String isOutAssemble;

	/**
	 * 可打木架
	 */
	private String isPackingWood;

	/**
	 * 可中转
	 */
	private String isTransfer;

	/**
	 * 货区面积
	 */
	private String goodsArea;

	/**
	 * 货台面积
	 */
	private String platArea;

	/**
	 * 是否启用
	 */
	private String active;
	
	/**
	 * 数据版本号
	 */
	private Long versionNo;
	
	/**
	 * 月台总数
	 */
	private int platformCount;
	
    /**
     * 备注
     */
    private String notes;

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsVehicleAssemble() {
		return isVehicleAssemble;
	}

	public void setIsVehicleAssemble(String isVehicleAssemble) {
		this.isVehicleAssemble = isVehicleAssemble;
	}

	public String getIsOutAssemble() {
		return isOutAssemble;
	}

	public void setIsOutAssemble(String isOutAssemble) {
		this.isOutAssemble = isOutAssemble;
	}

	public String getIsPackingWood() {
		return isPackingWood;
	}

	public void setIsPackingWood(String isPackingWood) {
		this.isPackingWood = isPackingWood;
	}

	public String getIsTransfer() {
		return isTransfer;
	}

	public void setIsTransfer(String isTransfer) {
		this.isTransfer = isTransfer;
	}

	public String getGoodsArea() {
		return goodsArea;
	}

	public void setGoodsArea(String goodsArea) {
		this.goodsArea = goodsArea;
	}

	public String getPlatArea() {
		return platArea;
	}

	public void setPlatArea(String platArea) {
		this.platArea = platArea;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public int getPlatformCount() {
		return platformCount;
	}

	public void setPlatformCount(int platformCount) {
		this.platformCount = platformCount;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getLogistCode() {
		return logistCode;
	}

	public void setLogistCode(String logistCode) {
		this.logistCode = logistCode;
	}

}
