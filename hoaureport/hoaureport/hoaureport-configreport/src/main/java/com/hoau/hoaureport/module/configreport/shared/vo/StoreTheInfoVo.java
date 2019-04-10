package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.StoreTheInfo;


/**
 * 门店信息VO
 * ClassName: StoreTheInfoVo 
 * @author 文洁
 * @date 2016年9月12日
 * @version V1.0
 */
public class StoreTheInfoVo {
	/**门店信息*/
	private StoreTheInfo storeTheInfo;
	/**门店信息集合*/
	private List<StoreTheInfo> storeTheInfoList;
	
	
		private String filePath;//文件路径
		private String addSize;//新增条数
		private String coverSize;//覆盖条数
		private String sumSize; //导入总数量
	
	//下面是getters和setters
	public StoreTheInfo getStoreTheInfo() {
		return storeTheInfo;
	}
	public void setStoreTheInfo(StoreTheInfo storeTheInfo) {
		this.storeTheInfo = storeTheInfo;
	}
	public List<StoreTheInfo> getStoreTheInfoList() {
		return storeTheInfoList;
	}
	public void setStoreTheInfoList(List<StoreTheInfo> storeTheInfoList) {
		this.storeTheInfoList = storeTheInfoList;
	}	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getAddSize() {
		return addSize;
	}
	public void setAddSize(String addSize) {
		this.addSize = addSize;
	}
	public String getCoverSize() {
		return coverSize;
	}
	public void setCoverSize(String coverSize) {
		this.coverSize = coverSize;
	}
	public String getSumSize() {
		return sumSize;
	}
	public void setSumSize(String sumSize) {
		this.sumSize = sumSize;
	}
	
}
