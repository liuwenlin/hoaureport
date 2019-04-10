package com.hoau.hoaureport.module.job.server.service;

import java.util.Date;
/**
 *  整月报表生成zip服务接口类
 * @author 273503
 *
 */
public interface IAllMonthSqlJobService {

	void allMonthToZip(Date date);

}
