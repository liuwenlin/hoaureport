package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import java.util.List;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * @author：高佳
 * @create：2015年6月5日 下午4:05:46
 * @description：数据字典信息表
 */
public class DataDictionaryEntity extends BaseEntity{
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String termsCode;//词条编码
	
	private String termsName;//词条名称
	
	private String isLeaf;//是否叶子节点
	
	private String parentTermsCode;//上级编码
	
	private String notes;//备注
	
	private String active;//是否激活
	
	private Long versionNo;//版本号
	
	/**
	 * 子词条的LIST
	 */
	private List<DataDictionaryValueEntity> dataDictionaryValueEntityList;

	public String getTermsCode() {
		return termsCode;
	}

	public void setTermsCode(String termsCode) {
		this.termsCode = termsCode;
	}

	public String getTermsName() {
		return termsName;
	}

	public void setTermsName(String termsName) {
		this.termsName = termsName;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getParentTermsCode() {
		return parentTermsCode;
	}

	public void setParentTermsCode(String parentTermsCode) {
		this.parentTermsCode = parentTermsCode;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	public List<DataDictionaryValueEntity> getDataDictionaryValueEntityList() {
		return dataDictionaryValueEntityList;
	}

	public void setDataDictionaryValueEntityList(
			List<DataDictionaryValueEntity> dataDictionaryValueEntityList) {
		this.dataDictionaryValueEntityList = dataDictionaryValueEntityList;
	}
	

}
