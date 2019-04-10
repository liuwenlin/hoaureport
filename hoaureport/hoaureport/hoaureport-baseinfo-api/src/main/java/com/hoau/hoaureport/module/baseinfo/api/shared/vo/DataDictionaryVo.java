package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import java.io.Serializable;
import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.DataDictionaryEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.DataDictionaryValueEntity;


/**
 * 数据字典vo对象
 * @author 高佳
 * @date 2015年6月9日
 */
public class DataDictionaryVo implements Serializable{
    private static final long serialVersionUID = -3967231350132228718L;
    
    /**
     *业务字典实体对象（查询条件）
     */
    private DataDictionaryEntity dataDictionaryEntity;
    
    /**
     *业务字典实体对象（查询条件）
     */
    private DataDictionaryValueEntity dataDictionaryValueEntity;
    
    /**
     * 词条集合对象
     */
    private List<DataDictionaryEntity> dataDictionaryEntitys;
    
    /**
     * 值集合对象
     */
    private List<DataDictionaryValueEntity> dataDictionaryValueEntityList;
    
    /**
     * 词条代码LIST
     */
    private List<String> termsCodeList;
    /**
     * 词条代码
     */
    private String termsCode;
    
    public DataDictionaryEntity getDataDictionaryEntity() {
        return dataDictionaryEntity;
    }
    
    public void setDataDictionaryEntity(DataDictionaryEntity dataDictionaryEntity) {
        this.dataDictionaryEntity = dataDictionaryEntity;
    }
    
    public List<DataDictionaryEntity> getDataDictionaryEntitys() {
        return dataDictionaryEntitys;
    }
    
    public void setDataDictionaryEntitys(List<DataDictionaryEntity> dataDictionaryEntitys) {
        this.dataDictionaryEntitys = dataDictionaryEntitys;
    }

	public DataDictionaryValueEntity getDataDictionaryValueEntity() {
		return dataDictionaryValueEntity;
	}

	public void setDataDictionaryValueEntity(
			DataDictionaryValueEntity dataDictionaryValueEntity) {
		this.dataDictionaryValueEntity = dataDictionaryValueEntity;
	}

	public List<String> getTermsCodeList() {
		return termsCodeList;
	}

	public void setTermsCodeList(List<String> termsCodeList) {
		this.termsCodeList = termsCodeList;
	}

	public List<DataDictionaryValueEntity> getDataDictionaryValueEntityList() {
		return dataDictionaryValueEntityList;
	}

	public void setDataDictionaryValueEntityList(
			List<DataDictionaryValueEntity> dataDictionaryValueEntityList) {
		this.dataDictionaryValueEntityList = dataDictionaryValueEntityList;
	}

	public String getTermsCode() {
		return termsCode;
	}

	public void setTermsCode(String termsCode) {
		this.termsCode = termsCode;
	} 
    
}
