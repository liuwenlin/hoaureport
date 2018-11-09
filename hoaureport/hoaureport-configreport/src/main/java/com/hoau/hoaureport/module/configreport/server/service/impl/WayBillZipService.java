package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoau.hoaureport.module.common.server.constants.ItfConifgConstant;
import com.hoau.hoaureport.module.configreport.server.dao.WayBillZipEntityMapper;
import com.hoau.hoaureport.module.configreport.server.service.IWayBillZipService;
import com.hoau.hoaureport.module.configreport.shared.domain.WayBillZipEntity;
import com.hoau.hoaureport.module.util.UUIDUtil;
import com.hoau.sso.module.api.shared.domain.UserEntity;
/**
 * 运单物流时间报表csv服务类
 * @author 273503
 *
 */
@Service
public class WayBillZipService implements IWayBillZipService {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private WayBillZipEntityMapper wayBillZipEntityMapper;
	
	@Override
	public Long queryCountByEntity(WayBillZipEntity zipEntity) {
		// TODO Auto-generated method stub
		return wayBillZipEntityMapper.queryCount(zipEntity);
	}

	@Override
	public List<WayBillZipEntity> queryByEntity(
			WayBillZipEntity zipEntity, int start, int limit) {
		// TODO Auto-generated method stub
		RowBounds row = new RowBounds(start, limit);
		return wayBillZipEntityMapper.queryByEntity(zipEntity,row);
	}

	@Override
	public WayBillZipEntity queryEntityById(String id) {
		// TODO Auto-generated method stub
		WayBillZipEntity zipEntity = new WayBillZipEntity();
		zipEntity.setId(id);
		return wayBillZipEntityMapper.queryById(zipEntity);
	}

	@Override
	public WayBillZipEntity addWayBillZip(WayBillZipEntity waybill,UserEntity user) {
		
		try {
			
			WayBillZipEntity zipEntity =wayBillZipEntityMapper.queryByZipNum(waybill.getZipNum());
			//存在则更新
			if(zipEntity!=null && zipEntity.getId()!=null && !"".equals(zipEntity.getId())){
				//修改
				zipEntity.setZipName(waybill.getZipName());
				zipEntity.setZipPath(waybill.getZipPath());
				zipEntity.setModifier(user.getUserName());
				zipEntity.setModifyDate(new Date());
				wayBillZipEntityMapper.update(zipEntity);
				return zipEntity;
			}else{
				//新增
				waybill.setId(UUIDUtil.getUUID());
				waybill.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
				waybill.setCreateDate(new Date());
				waybill.setCreator(user.getUserName());
				wayBillZipEntityMapper.insertSelective(waybill);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("添加记录异常:",e);
		}
		
		return waybill;
	}

	@Override
	public int deleteByCreatedate(String date) {
		
		return wayBillZipEntityMapper.deleteByCreatedate(date);
	}

}
