package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.CityPriceCard;


/**
 * 城市价卡VO
 * ClassName: CityPriceCardVo 
 * @author 文洁
 * @date 2016年12月15日
 * @version V1.0
 */
public class CityPriceCardVo {
	/**城市价卡信息*/
	private CityPriceCard cityPriceCard;
	/**城市价卡信息集合*/
	private List<CityPriceCard> cityPriceCardList;
	
	
		private String filePath;//文件路径
		private String addSize;//新增条数
		private String coverSize;//覆盖条数
		private String sumSize; //导入总数量
	
	//下面是getters和setters
	public CityPriceCard getCityPriceCard() {
		return cityPriceCard;
	}
	public void setCityPriceCard(CityPriceCard cityPriceCard) {
		this.cityPriceCard = cityPriceCard;
	}
	public List<CityPriceCard> getCityPriceCardList() {
		return cityPriceCardList;
	}
	public void setCityPriceCardList(List<CityPriceCard> cityPriceCardList) {
		this.cityPriceCardList = cityPriceCardList;
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
