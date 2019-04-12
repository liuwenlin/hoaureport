package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.DownTransferTimeNode;

/**
 * 上转移时间节点管理服务接口
 * ClassName: IDownTransferTimeNodeManageService 
 * @author 文洁
 * @date 2016年10月31
 * @version V1.0
 */
public interface IDownTransferTimeNodeManageService {
	
	/**
	 * 
	 * @Description:查询上转移时间节点信息
	 * @param 上转移时间节点实例
	 * @param 查询开始索引
	 * @param 查询条数
	 * @return List<DownTransferTimeNode> 
	 * @author 文洁
	 * @date 2016年10月31
	 */
	List<DownTransferTimeNode> queryDownTransferTimeNode(DownTransferTimeNode param,int start,int limit);


	/**
	 * 
	 * @Description:查询记录总数
	 * @param 上转移时间节点实例
	 * @return Long 
	 * @author 文洁
	 * @date 2016年10月31
	 */
	Long queryDownTransferTimeNodeCount(DownTransferTimeNode param);
	
	/**
	 * 
	 * @Description:增加上转移时间节点
	 * @param 上转移时间节点实例
	 * @return void 
	 * @author 文洁
	 * @date 2016年10月31
	 */
	void addDownTransferTimeNode(DownTransferTimeNode record);
	
	/**
	 * 
	 * @Description:在原纪录下更新上转移时间节点信息
	 * @param 上转移时间节点实例
	 * @return void 
	 * @author 文洁
	 * @date 2016年10月31
	 */
	void updateDownTransferTimeNode(DownTransferTimeNode record);
	
	/**
	 * 
	 * @Description:原纪录作废，更新后的记录作为新数据插入
	 * @param 上转移时间节点实例
	 * @return void 
	 * @author 文洁
	 * @date 2016年10月31
	 */
	void repealAndAddDownTransferTimeNode(DownTransferTimeNode param);
	
	/**
	 * 
	 * @Description:纪录作废
	 * @param 上转移时间节点实例
	 * @return 记录作废操作者编号 
	 * @author 文洁
	 * @date 2016年10月31
	 */
	String repealDownTransferTimeNode(DownTransferTimeNode param);
	
	/**
	 * 
	 * @Description:判断记录是否已存在
	 * @param 上转移时间节点实例
	 * @return boolean 已存在为true
	 * @author 文洁
	 * @date 2016年10月31
	 */
	boolean isExist(DownTransferTimeNode param);
	
	/**
	 *  该方法会将错误的信息收集起来，返回给调用者
	 * @Description:处理从Excel里批量导入数据的操作
	 * @param  path
	 * @return Map<String,Object> Map<String,Object> addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
	 * @author 文洁
	 * @date 2016年11月7日
	 */
	public  Map<String,Object>  bathImplDownTransferTimeNode(String path) throws Exception;
	/**
	 * 
	 * @Description: 添加或者更新上转移时间节点信息
	 * @param info
	 * @param countMap
	 * @author 文洁
	 * @date 2016年11月7日
	 */
	public void addOrUpdateDownTransferTimeNode(DownTransferTimeNode info,Map<String,Long> countMap) throws Exception;
	
}
