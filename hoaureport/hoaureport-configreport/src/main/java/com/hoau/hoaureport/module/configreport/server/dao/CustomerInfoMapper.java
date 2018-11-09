package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.hoaureport.module.configreport.shared.domain.CustomerInfo;

/**
 * 大客户信息Mapper
 * ClassName: CustomerInfoMapper 
 * @author 文洁
 * @date 2016年11月7日
 * @version V1.0
 */
public interface CustomerInfoMapper {
    int deleteByPrimaryKey(String customerNum);

    int insert(CustomerInfo record);

    int insertSelective(CustomerInfo record);

    CustomerInfo selectByPrimaryKey(String customerNum);

    int updateByPrimaryKeySelective(CustomerInfo record);

    int updateByPrimaryKey(CustomerInfo record);
    
    /**
     * 
     * @Description:根据条件查询 大客户信息
     * @param 大客户实例
     * @param 行限制实例
     * @return List<CustomerInfo> 
     * @author 文洁
     * @date 2016年11月7日
     */
    List<CustomerInfo> queryCustomerInfoByCondition(CustomerInfo param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @param 大客户实例
     * @return
     */
    Long queryCustomerInfoCountByCondition(CustomerInfo param);
}