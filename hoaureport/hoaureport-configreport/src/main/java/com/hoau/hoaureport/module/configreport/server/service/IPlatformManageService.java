package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.PlatformInfo;
/**
 * 
 * ClassName: IPlatformManageService 
 * @author 刘镇松
 * @date 2016年8月17日
 * @version V1.0
 */
public interface IPlatformManageService {
	/**
	 * 查询场站信息
	 * @return
	 */
	List<PlatformInfo> queryPlatformInfo(PlatformInfo param,int start,int limit);
	/**
	 * 查询总记录数
	 * @param PlatformVo
	 * @return
	 */
	Long queryPlatformCount(PlatformInfo param);
	/**
	 * 
	 * @Description: 添加场站信息
	 * @param @param info   
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	void addPlatformInfo(PlatformInfo info);
	/**
	 * 
	 * @Description: 更新场站信息
	 * @param @param info   
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	void updatePlatformInfo(PlatformInfo info);
	/**
	 * 
	 * @Description: 作废老数据新增修改新数据
	 * @param @param param
	 * @param @param empCode   
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	void repealAndAddInfo(PlatformInfo param,String empCode);
}
