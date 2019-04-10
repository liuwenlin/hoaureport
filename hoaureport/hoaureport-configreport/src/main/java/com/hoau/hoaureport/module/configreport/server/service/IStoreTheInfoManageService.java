package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.StoreTheInfo;

/**
 * 门店信息管理服务接口
 * ClassName: IStoreTheInfoManageService 
 * @author 文洁
 * @date 2016年9月12日
 * @version V1.0
 */
public interface IStoreTheInfoManageService {
	/**
	 * 
	 * @Description:查询门店信息
	 * @param 门店实例
	 * @param 查询开始索引
	 * @param 查询条数
	 * @return List<StoreTheInfo> 
	 * @author 文洁
	 * @date 2016年9月12
	 */
	List<StoreTheInfo> queryStoreTheInfo(StoreTheInfo param,int start,int limit);


	
	/**
	 * 
	 * @Description:通过明确的条件查询门店信息（不支持模糊查询）
	 * @param 门店实例
	 * @param 查询开始索引
	 * @param 查询条数
	 * @return List<StoreTheInfo> 
	 * @author 文洁
	 * @date 2016年9月12
	 */
	List<StoreTheInfo> queryStoreTheInfoByExplicitCondition(StoreTheInfo param,int start,int limit);
	/**
	 * 
	 * @Description:查询记录总数
	 * @param 门店实例
	 * @return Long 
	 * @author 文洁
	 * @date 2016年9月12
	 */
	Long queryStoreTheInfoCount(StoreTheInfo param);
	
	/**
	 * 
	 * @Description:增加门店
	 * @param 门店实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年9月12
	 */
	void addStoreTheInfo(StoreTheInfo record);
	
	/**
	 * 
	 * @Description:在原纪录上更新门店信息
	 * @param 门店实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年9月12
	 */
	void updateStoreTheInfo(StoreTheInfo record);
	
	/**
	 * 
	 * @Description:原纪录作废，更新后的记录作为新数据插入
	 * @param 门店实例
	 * @return void 
	 * @author 文洁
	 * @date 2016年9月12
	 */
	void repealAndAddStoreTheInfo(StoreTheInfo param);
	
	/**
	 * 
	 * @Description:纪录作废
	 * @param 门店实例   
	 * @return 记录作废操作者编号 
	 * @author 文洁
	 * @date 2016年9月12
	 */
	String repealStoreTheInfo(StoreTheInfo param);
	
	/**
	 * 
	 * @Description:判断记录是否已存在
	 * @param 门店实例
	 * @return boolean 已存在为true
	 * @author 文洁
	 * @date 2016年9月12
	 */
	boolean isExist(StoreTheInfo param);
	
	/**
	 *  该方法会将错误的信息收集起来，返回给调用者
	 * @Description:处理从Excel里批量导入数据的操作
	 * @param  path
	 * @return Map<String,Object> Map<String,Object> addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
	 * @author 文洁
	 * @date 2016年10月24日
	 */
	public  Map<String,Object>  bathImplStoreTheInfo(String path) throws Exception;
	/**
	 * 
	 * @Description: 添加或者更新门店所属信息
	 * @param info
	 * @param countMap
	 * @author 文洁
	 * @date 2016年10月24日
	 */
	public void addOrUpdateStoreTheInfo(StoreTheInfo info,Map<String,Long> countMap) throws Exception;
	public void repealAllStoreTheInfo();
}
