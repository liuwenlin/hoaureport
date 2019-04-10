package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.SalesLine;
/**
 * 销售线路
 * ClassName: ISalesLineService 
 * @author 刘镇松
 * @date 2016年8月18日
 * @version V1.0
 */
public interface ISalesLineService {
	/**
	 * 查询销售线路
	 * @return
	 */
	List<SalesLine> querySalesLineInfo(SalesLine param,int start,int limit);
	/**
	 * 查询总记录数
	 * @param SalesLine
	 * @return
	 */
	Long querySalesLineCount(SalesLine param);
	/**
	 * 
	 * @Description: 添加销售线路
	 * @param @param info   
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	void addSalesLine(SalesLine info);
	/**
	 * 
	 * @Description: 更新销售线路
	 * @param @param info   
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	void updateSalesLineInfo(SalesLine info);
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
	void repealAndAddInfo(SalesLine param,String empCode);
	/**
	 * 
	 * @Description: 是否存在
	 * @param @param param
	 * @param @return   
	 * @return boolean 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年11月1日
	 */
	boolean isExist(SalesLine param);
	/**
	 *  该方法会将错误的信息收集起来，返回给调用者
	 * @Description:处理从Excel里批量导入数据的操作
	 * @param  path
	 * @return Map<String,Object> Map<String,Object> addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
	 * @author 刘镇松
	 * @date 2016年11月02日
	 */
	public  Map<String,Object>  bathImplPlatformAreaInfo(String path) throws Exception;
	/**
	 * 
	 * @Description: 添加或者更新平台辖区信息
	 * @param info
	 * @param countMap
	 * @author 刘镇松
	 * @date 2016年11月02日
	 */
	public void addOrUpdateSalesLineInfo(SalesLine info,Map<String,Long> countMap) throws Exception;
	/**
	 * 
	 * @Description: 作废所有销售路线
	 * @param    
	 * @return void 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年11月11日
	 */
	public void repealAllSalesLine();
}
