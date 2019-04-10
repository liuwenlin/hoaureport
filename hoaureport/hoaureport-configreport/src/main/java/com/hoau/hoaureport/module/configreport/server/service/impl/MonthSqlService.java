package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.common.server.constants.ItfConifgConstant;
import com.hoau.hoaureport.module.common.shared.util.FolderUtils;
import com.hoau.hoaureport.module.configreport.server.dao.MonthSqlEntityMapper;
import com.hoau.hoaureport.module.configreport.server.service.IMonthSqlService;
import com.hoau.hoaureport.module.configreport.shared.domain.MonthSqlEntity;
import com.hoau.hoaureport.module.configreport.shared.exception.MonthReportSqlException;
import com.hoau.hoaureport.module.util.UUIDUtil;
/**
 * 整月报表生成SQL管理接口实现类
 * @author 273503
 *
 */
@Service
public class MonthSqlService implements IMonthSqlService {
	private Logger log =  Logger.getLogger(this.getClass());
	@Autowired
	private MonthSqlEntityMapper monthSqlEntityMapper;
	
	@Override
	public Long queryTotalCount(MonthSqlEntity sqlEntity) {
		return monthSqlEntityMapper.queryTotalCount(sqlEntity);
	}

	@Override
	public List<MonthSqlEntity> queryMonthSql(MonthSqlEntity sqlEntity,
			int start, int limit) {
		RowBounds row = new RowBounds(start, limit);
		List<MonthSqlEntity> querySql =monthSqlEntityMapper.queryMonthSql(sqlEntity,row);
		if(querySql!=null && querySql.get(0)!=null && querySql.get(0).getSqlBody()!=null){
			for(MonthSqlEntity ms:querySql){
				try {
					ms.setSqlBodyStr(new String(ms.getSqlBody(),ItfConifgConstant.SQL_BLOB_CODE));
				} catch (UnsupportedEncodingException e) {
					log.error("byte_to_String_err:",e);
				}
			}
		}
		return querySql;
	}

	@Override
	public void addMonthSql(MonthSqlEntity sqlEntity) {
		
		checkEntity(sqlEntity);
		try {
			
			
			sqlEntity.setSqlBody(sqlEntity.getSqlBodyStr().getBytes(ItfConifgConstant.SQL_BLOB_CODE));
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			sqlEntity.setId(UUIDUtil.getUUID());
			sqlEntity.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
			sqlEntity.setCreateDate(new Date());
			sqlEntity.setCreator(currUser.getUserName());
			ifExist(sqlEntity);
			monthSqlEntityMapper.insertSelective(sqlEntity);
		}catch (BusinessException e1) {
			throw new MonthReportSqlException(e1.getErrorCode());
		} catch (Exception e) {
			log.error("addMonthSql_err:",e);
			throw new MonthReportSqlException("新增异常");
		}
		
		
		
	}
	
	private void checkEntity(MonthSqlEntity sqlEntity){
		if(sqlEntity==null){
			throw new MonthReportSqlException("参数不能为空");
		}
		isNullStr(sqlEntity.getSqlId(),"报表名称");
		isNullStr(sqlEntity.getSqlBodyStr(),"SQL语句");
		isNullStr(sqlEntity.getCharCode(),"编码格式");
		isNullStr(sqlEntity.getSqlType(),"类型");
		isNullStr(sqlEntity.getSqlName(),"标题字段");
	}
	
	private void isNullStr(String str,String msg){
		if(str==null || "".equals(str.trim())){
			throw new MonthReportSqlException(msg+"不能为空");
		}
	}

	@Override
	public void updateMonthSql(MonthSqlEntity sqlEntity) {
		if(sqlEntity==null){
			throw new MonthReportSqlException("参数不能为空");
		}
		isNullStr(sqlEntity.getId(),"ID");
		isNullStr(sqlEntity.getSqlBodyStr(),"SQL语句");
		isNullStr(sqlEntity.getCharCode(),"编码格式");
		try {
			sqlEntity.setSqlBody(sqlEntity.getSqlBodyStr().getBytes(ItfConifgConstant.SQL_BLOB_CODE));
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			sqlEntity.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
			sqlEntity.setRemark(null);
			sqlEntity.setModifyDate(new Date());
			sqlEntity.setModifier(currUser.getUserName());
//			ifExist(sqlEntity);
			monthSqlEntityMapper.updateByPrimaryKeySelective(sqlEntity);
		}catch (BusinessException e1) {
			throw new MonthReportSqlException(e1.getErrorCode());
		} catch (Exception e) {
			log.error("updateMonthSql_err:",e);
			throw new MonthReportSqlException("修改异常");
		}
		
	}

	private void ifExist(MonthSqlEntity SqlEntity){
		MonthSqlEntity sql = new MonthSqlEntity();
		sql.setSqlId(SqlEntity.getSqlId());
		sql.setSqlType(SqlEntity.getSqlType());
		sql.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		long r =monthSqlEntityMapper.queryTotalCount(sql);
		if(r>0){
			throw new MonthReportSqlException("该类型报表已经存在");
		}
	}
	
	
	@Override
	@Transactional
	public void removeMonthSql(List<String> ids) {
		
		try {
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			for(String id:ids){
				//运单物流时间16号报表不能删除
				if(FolderUtils.FILE_EXTENSION_CODE1001_ID.equals(id)){
					continue;
				}
				MonthSqlEntity sqlEntity = new MonthSqlEntity();
				sqlEntity.setId(id);
				sqlEntity.setActive(ItfConifgConstant.HAR_ACTIVE_NO);
				sqlEntity.setModifyDate(new Date());
				sqlEntity.setModifier(currUser.getUserName());
				monthSqlEntityMapper.updateByPrimaryKeySelective(sqlEntity);
			}
			
		} catch (Exception e) {
			log.error("removeMonthSql_err:",e);
			throw new MonthReportSqlException("删除异常");
		}
		
	}

//	public static void main(String[] args) {
//		System.out.println(Charset.defaultCharset().name());
//	}
}
