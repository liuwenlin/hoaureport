package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.PlatformAreaInfo;
/**
 * 
 * ClassName: PlatformAreaVo 
 * @author 刘镇松
 * @date 2016年8月18日
 * @version V1.0
 */
public class PlatformAreaVo {
	/**参数*/
	private PlatformAreaInfo platformAreaInfoParam;
	/**返回的平台辖区集合*/
	private List<PlatformAreaInfo> platformAreaInfoList;
	private String filePath;//文件路径
	private String addSize;//新增条数
	private String coverSize;//覆盖条数
	private String sumSize; //导入总数量
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

	public List<PlatformAreaInfo> getPlatformAreaInfoList() {
		return platformAreaInfoList;
	}

	public void setPlatformAreaInfoList(List<PlatformAreaInfo> platformAreaInfoList) {
		this.platformAreaInfoList = platformAreaInfoList;
	}

	public PlatformAreaInfo getPlatformAreaInfoParam() {
		return platformAreaInfoParam;
	}

	public void setPlatformAreaInfoParam(PlatformAreaInfo platformAreaInfoParam) {
		this.platformAreaInfoParam = platformAreaInfoParam;
	}

}
