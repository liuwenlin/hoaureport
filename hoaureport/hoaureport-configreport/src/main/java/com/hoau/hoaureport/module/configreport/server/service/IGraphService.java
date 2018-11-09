package com.hoau.hoaureport.module.configreport.server.service;

import java.util.Date;
import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.SmsDataEntity;
import com.hoau.hoaureport.module.configreport.shared.vo.GraphInfoVo;
import com.hoau.hoaureport.module.configreport.shared.vo.SmsDataVo;

/**
 * 图形化界面数据服务接口
 * @author 273503
 *
 */
public interface IGraphService {
	/**
	 * 吨成本
	 * @param day
	 * @return
	 */
	public GraphInfoVo queryCost(Date day);
	/**
	 * 送货及时率
	 * @param day
	 * @return
	 */
	public GraphInfoVo queryNowDay(Date day);
	/**
	 * 次日送达率
	 * @param day
	 * @return
	 */
	public GraphInfoVo queryNextDay(Date day);
	/**
	 * 产值早报数据查询
	 * @param smsdata
	 * @param costDay
	 * @return
	 */
	public List<SmsDataEntity> querySmsDataList(SmsDataEntity smsdata,
			Date day);
	/**
	 * 产值早报图形界面数据查询
	 * @param smsdata
	 * @param costDay
	 * @return
	 */
	public SmsDataVo querySmsData(SmsDataEntity smsdata, Date costDay);
}
