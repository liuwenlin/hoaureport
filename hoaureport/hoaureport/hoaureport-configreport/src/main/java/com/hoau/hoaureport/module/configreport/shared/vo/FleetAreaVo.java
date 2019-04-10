package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.FleetAreaInfo;

/**
 * 车队辖区VO
 * ClassName: FleetAreaVo 
 * @author 文洁
 * @date 2016年8月18日
 * @version V1.0
 */
public class FleetAreaVo {
	/**车队辖区*/
	private FleetAreaInfo FleetAreaInfo;
	/**车队辖区集合*/
	private List<FleetAreaInfo> FleetAreaInfoList;
	
	private String filePath;//文件路径
	private String addSize;//新增条数
	private String coverSize;//覆盖条数
	private String sumSize; //导入总数量
	
	//下面是getters和setters
	public FleetAreaInfo getFleetAreaInfo() {
		return FleetAreaInfo;
	}
	public void setFleetAreaInfo(FleetAreaInfo FleetAreaInfo) {
		this.FleetAreaInfo = FleetAreaInfo;
	}
	public List<FleetAreaInfo> getFleetAreaInfoList() {
		return FleetAreaInfoList;
	}
	public void setFleetAreaInfoList(List<FleetAreaInfo> FleetAreaInfoList) {
		this.FleetAreaInfoList = FleetAreaInfoList;
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
