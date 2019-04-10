package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.PlatformAreaInfo;
/**
 * 
 * ClassName: IPlatformAreaAreaManageService 
 * @author 刘镇松
 * @date 2016年8月18日
 * @version V1.0
 */
public interface IPlatformAreaManageService {
	/**
	 * 查询平台辖区信息
	 * @return
	 */
	List<PlatformAreaInfo> queryPlatformAreaInfo(PlatformAreaInfo param,int start,int limit);
	/**
	 * 查询总记录数
	 * @param PlatformAreaVo
	 * @return
	 */
	Long queryPlatformAreaCount(PlatformAreaInfo param);
	/**
	 * 
	 * @Description: 添加平台辖区信息
	 * @param @param info   
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	void addPlatformAreaInfo(PlatformAreaInfo info);
	/**
	 * 
	 * @Description: 更新平台辖区信息
	 * @param @param info   
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	void updatePlatformAreaInfo(PlatformAreaInfo info);
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
	void repealAndAddInfo(PlatformAreaInfo param,String empCode);
	
	/**
	 *  该方法会将错误的信息收集起来，返回给调用者
	 * @Description:处理从Excel里批量导入数据的操作
	 * @param  path
	 * @return Map<String,Object> Map<String,Object> addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
	 * @author 文洁
	 * @date 2016年11月02日
	 */
	public  Map<String,Object>  bathImplPlatformAreaInfo(String path) throws Exception;
	/**
	 * 
	 * @Description: 添加或者更新平台辖区信息
	 * @param info
	 * @param countMap
	 * @author 文洁
	 * @date 2016年11月02日
	 */
	public void addOrUpdatePlatformAreaInfo(PlatformAreaInfo info,Map<String,Long> countMap) throws Exception;
	
	public void repealAllPlatformArea();
}
