package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.CustomerInfo;

/**
 * 大客户信息VO
 * ClassName: CustomerInfoVo 
 * @author 文洁
 * @date 2016年11月7日
 * @version V1.0
 */
public class CustomerInfoVo {
	/**大客户信息*/
	private CustomerInfo customerInfo;
	/**返回的大客户信息集合*/
	private List<CustomerInfo> customerInfoList;
	/*保存当前操作的大客户记录的编号*/
	private String currentCustomerNum;
	
	public String getCurrentCustomerNum() {
		return currentCustomerNum;
	}

	public void setCurrentCustomerNum(String currentCustomerNum) {
		this.currentCustomerNum = currentCustomerNum;
	}

	public List<CustomerInfo> getCustomerInfoList() {
		return customerInfoList;
	}

	public void setCustomerInfoList(List<CustomerInfo> customerInfoList) {
		this.customerInfoList = customerInfoList;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfoParam) {
		this.customerInfo = customerInfoParam;
	}

}
