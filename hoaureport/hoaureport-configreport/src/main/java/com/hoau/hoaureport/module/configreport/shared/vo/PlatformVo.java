package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.PlatformInfo;
/**
 * 
 * ClassName: PlatformVo 
 * @author 刘镇松
 * @date 2016年8月17日
 * @version V1.0
 */
public class PlatformVo {
	/**参数*/
	private PlatformInfo platformInfoParam;
	/**返回的场站集合*/
	private List<PlatformInfo> platformInfoList;
	
	public List<PlatformInfo> getPlatformInfoList() {
		return platformInfoList;
	}

	public void setPlatformInfoList(List<PlatformInfo> platformInfoList) {
		this.platformInfoList = platformInfoList;
	}

	public PlatformInfo getPlatformInfoParam() {
		return platformInfoParam;
	}

	public void setPlatformInfoParam(PlatformInfo platformInfoParam) {
		this.platformInfoParam = platformInfoParam;
	}

}
