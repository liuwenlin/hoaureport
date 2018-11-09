package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.StationAreaInfo;

/**
 * 场站辖区VO
 * ClassName: StationAreaVo 
 * @author 文洁
 * @date 2016年8月17日
 * @version V1.0
 */
public class StationAreaVo {
	/**场站辖区*/
	private StationAreaInfo stationAreaInfo;
	/**场站辖区集合*/
	private List<StationAreaInfo> stationAreaInfoList;
	private String filePath;//文件路径
	private String addSize;//新增条数
	private String coverSize;//覆盖条数
	private String sumSize; //导入总数量

	//下面是getters和setters
	public StationAreaInfo getStationAreaInfo() {
		return stationAreaInfo;
	}
	public void setStationAreaInfo(StationAreaInfo stationAreaInfo) {
		this.stationAreaInfo = stationAreaInfo;
	}
	public List<StationAreaInfo> getStationAreaInfoList() {
		return stationAreaInfoList;
	}
	public void setStationAreaInfoList(List<StationAreaInfo> stationAreaInfoList) {
		this.stationAreaInfoList = stationAreaInfoList;
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
