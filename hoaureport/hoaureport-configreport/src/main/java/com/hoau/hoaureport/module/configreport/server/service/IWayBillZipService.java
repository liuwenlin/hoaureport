package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.WayBillZipEntity;
import com.hoau.sso.module.api.shared.domain.UserEntity;

/**
 * 运单物流时间报表csv服务接口
 * @author 273503
 *
 */
public interface IWayBillZipService {

	/**
	 * 总记录数
	 * @param zipEntity
	 * @return
	 */
	Long queryCountByEntity(WayBillZipEntity zipEntity);
	/**
	 * 分页查询
	 * @param wayBillZipService
	 * @param start
	 * @param limit
	 * @return
	 */
	List<WayBillZipEntity> queryByEntity(WayBillZipEntity zipEntity ,
			int start, int limit);
	WayBillZipEntity queryEntityById(String id);
	/**
	 * 添加记录   （没有新增，有则更新）
	 * @param waybill
	 * @return
	 */
	WayBillZipEntity addWayBillZip(WayBillZipEntity waybill,UserEntity user);
	/**
	 * 根据创建时间清除数据
	 * @param date
	 * @return
	 */
	int deleteByCreatedate(String date);

}
