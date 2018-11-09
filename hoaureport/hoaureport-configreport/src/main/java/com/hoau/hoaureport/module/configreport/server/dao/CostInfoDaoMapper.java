package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.GraphBaseInfo;
/**
 * 吨成本
 * @author 273503
 *
 */
@Repository
public interface CostInfoDaoMapper {
    List<GraphBaseInfo> queryCostList(String ssr);

	GraphBaseInfo queryCostMaxValue(String dateToStr);
}
