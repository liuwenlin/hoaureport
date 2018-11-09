package com.hoau.hoaureport.module.job.server.service;

import java.util.Date;

/**
 * 运单物流时间报表生成包接口
 * @author 273503
 *
 */
public interface IWaybillJobService {
	/**
	 * 运单物流时间报表包处理
	 * @param date
	 */
	void queryToCsvZip(Date date);

}
