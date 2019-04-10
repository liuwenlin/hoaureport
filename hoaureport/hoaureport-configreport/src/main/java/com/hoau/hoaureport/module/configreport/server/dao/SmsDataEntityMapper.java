package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.SmsDataEntity;

@Repository
public interface SmsDataEntityMapper {
    /**
     * 根据公司名称和所属日查询数据
     * @param record
     * @return
     */
	List<SmsDataEntity> queryList(SmsDataEntity record);

   
}