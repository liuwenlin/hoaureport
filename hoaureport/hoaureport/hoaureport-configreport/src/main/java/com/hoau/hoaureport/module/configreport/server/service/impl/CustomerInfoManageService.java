package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.common.server.constants.ItfConifgConstant;
import com.hoau.hoaureport.module.configreport.server.dao.CustomerInfoMapper;
import com.hoau.hoaureport.module.configreport.server.service.ICustomerInfoManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.CustomerInfo;
/**
 * 大客户信息管理服务实现类
 * ClassName: CustomerInfoManageService 
 * @author 文洁
 * @date 2016年11月7日
 * @version V1.0
 */
@Service
public class CustomerInfoManageService implements ICustomerInfoManageService{

	@Resource
	CustomerInfoMapper customerInfoMapper;
	
	/**
	 * 查询大客户信息
	 */
	@Override
	public List<CustomerInfo> queryCustomerInfo(CustomerInfo param,
			int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return customerInfoMapper.queryCustomerInfoByCondition(param, rowBounds);
	}

	/**
	 * 查询记录总数
	 */
	@Override
	public Long queryCustomerInfoCount(CustomerInfo param) {
		return customerInfoMapper.queryCustomerInfoCountByCondition(param);
	}

	/**
	 * 增加大客户
	 */
	@Transactional
	@Override
	public void addCustomerInfo(CustomerInfo record) {
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		//工号
		String currUserCode = currUser.getEmpCode().substring(2);
		
		record.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		record.setCreateTime(new Date());
		record.setCreateUserCode(currUserCode);
		record.setModifyTime(new Date());
		record.setModifyUserCode(currUserCode);
		customerInfoMapper.insertSelective(record);
	}

	/**
	 * 在原纪录上更新大客户信息
	 */
	@Transactional
	@Override
	public void updateCustomerInfo(CustomerInfo record) {
		customerInfoMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 原纪录作废，更新后的记录作为新数据插入
	 */
	@Transactional
	@Override
	public void repealAndAddCustomerInfo(CustomerInfo param) {
		//原纪录作废，并取得修改者编号
		String modifier = repealCustomerInfo(param);
		
		//另外插入修改数据
		param.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		param.setCreateTime(new Date());
		param.setCreateUserCode(modifier);
		param.setModifyTime(new Date());
		param.setModifyUserCode(modifier);
		
		addCustomerInfo(param);
		
	}

	/**
	 * 原记录作废,返回操作者编号
	 */
	@Override
	public String repealCustomerInfo(CustomerInfo param) {
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		//工号
		String currUserCode = currUser.getEmpCode().substring(2);
		CustomerInfo oldInfo = new CustomerInfo();
		oldInfo.setCustomerNum(param.getCustomerNum());
		oldInfo.setActive(ItfConifgConstant.HAR_ACTIVE_NO);
		oldInfo.setModifyTime(new Date());
		oldInfo.setModifyUserCode(currUserCode);
		
		updateCustomerInfo(oldInfo);
		return currUserCode;
	}

	/**
	 * 判断是否已存在
	 */
	@Override
	public boolean isExist(CustomerInfo param) {
		//设置判断为已存在的条件
		CustomerInfo exitCondition = new CustomerInfo();
		exitCondition.setCustomerNum(param.getCustomerNum());
		//因为可以修改 已经作废的记录 所以 判断编号唯一性时，也考虑作废的记录
		//exitCondition.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
				
		List<CustomerInfo> customerInfos = this.queryCustomerInfo(exitCondition,0,10);
		return customerInfos.size() > 0;
	}
}
