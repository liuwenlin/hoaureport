package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.CustomerInfo;

/**
 * 大客户信息管理服务
 * ClassName: ICustomerInfoManageService 
 * @author 文洁
 * @date 2016年11月7日
 * @version V1.0
 */
public interface ICustomerInfoManageService {
	
	/**
	 * 
	 * @Description:查询大客户信息
	 * @param 大客户实例
	 * @param 查询开始索引
	 * @param 查询条数
	 * @return List<CustomerInfo> 
	 * @author 文洁
	 * @date 2016年11月7日
	 */
	List<CustomerInfo> queryCustomerInfo(CustomerInfo param,int start,int limit);


	/**
	 * 
	 * @Description:查询记录总数
	 * @param 大客户实例
	 * @return Long 
	 * @author 文洁
	 * @date 2016年11月7日
	 */
	Long queryCustomerInfoCount(CustomerInfo param);
	
	/**
	 * 
	 * @Description:增加大客户
	 * @param 大客户实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年11月7日
	 */
	void addCustomerInfo(CustomerInfo record);
	
	/**
	 * 
	 * @Description:在原纪录上更新大客户信息
	 * @param 大客户实例   
	 * @return void 
	 * @author 文洁
	 * @date 2016年11月7日
	 */
	void updateCustomerInfo(CustomerInfo record);
	
	/**
	 * 
	 * @Description:原纪录作废，更新后的记录作为新数据插入
	 * @param 大客户实例
	 * @return void 
	 * @author 文洁
	 * @date 2016年11月7日
	 */
	void repealAndAddCustomerInfo(CustomerInfo param);
	
	/**
	 * 
	 * @Description:纪录作废
	 * @param 大客户实例   
	 * @return 记录作废操作者编号 
	 * @author 文洁
	 * @date 2016年11月7日
	 */
	String repealCustomerInfo(CustomerInfo param);
	
	/**
	 * 
	 * @Description:判断记录是否已存在
	 * @param 大客户实例
	 * @return boolean 已存在为true
	 * @author 文洁
	 * @date 2016年11月7日
	 */
	boolean isExist(CustomerInfo param);
	
}
