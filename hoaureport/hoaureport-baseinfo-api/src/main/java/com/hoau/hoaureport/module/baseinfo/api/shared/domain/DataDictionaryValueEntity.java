package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * @author：高佳
 * @create：2015年6月5日 下午4:06:04
 * @description：数据字典值信息表
 */
public class DataDictionaryValueEntity extends BaseEntity{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String termsCode;//词条编码
	
	private String termsName;//词条名称
	
	private String valueCode;//编码
	
	private String valueName;//值名称
	
	private long valueSeq;//顺序
	
	private String language;//语言
	
	private String notes;//备注
	
	private String active;//是否激活
	
	private Long versionNo;//版本号
	
	private String isAppUse;

	public String getTermsCode() {
		return termsCode;
	}

	public void setTermsCode(String termsCode) {
		this.termsCode = termsCode;
	}

	public String getValueCode() {
		return valueCode;
	}

	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	public long getValueSeq() {
		return valueSeq;
	}

	public void setValueSeq(long valueSeq) {
		this.valueSeq = valueSeq;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

	public String getTermsName() {
		return termsName;
	}

	public void setTermsName(String termsName) {
		this.termsName = termsName;
	}

	public String getIsAppUse() {
		return isAppUse;
	}

	public void setIsAppUse(String isAppUse) {
		this.isAppUse = isAppUse;
	}
	
	
}
