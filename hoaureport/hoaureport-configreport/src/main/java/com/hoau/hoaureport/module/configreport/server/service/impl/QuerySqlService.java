package com.hoau.hoaureport.module.configreport.server.service.impl;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.RoleEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.SSOEntityConvert;
import com.hoau.hoaureport.module.common.server.constants.ItfConifgConstant;
import com.hoau.hoaureport.module.configreport.server.dao.QuerySqlDaoMapper;
import com.hoau.hoaureport.module.configreport.server.service.IQuerySqlService;
import com.hoau.hoaureport.module.configreport.server.util.ConfigureReportUtil;
import com.hoau.hoaureport.module.configreport.shared.domain.QuerySql;
import com.hoau.hoaureport.module.configreport.shared.domain.SqlRoleEntity;
import com.hoau.hoaureport.module.configreport.shared.exception.BamSysException;
import com.hoau.hoaureport.module.configreport.shared.vo.RoleThreeVo;
import com.hoau.hoaureport.module.util.UUIDUtil;
import com.hoau.sso.module.api.server.service.impl.SSORoleService;

@Service("querySqlService")
public class QuerySqlService implements IQuerySqlService {
	Logger log = Logger.getLogger(this.getClass());
	@Resource
	QuerySqlDaoMapper querySqlDao;
	
	@Resource
	SSORoleService ssoRoleService;

	@Transactional
	@Override
	public void saveQuerySql(QuerySql querySql) throws BamSysException {
		try {
			initBlob(querySql);
			SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmssms");
			querySql.setId(UUIDUtil.getUUID());
			querySql.setNumber("SQL_" + time.format(new Date()));
			querySqlDao.insertQuerySql(querySql);
			addOrUpdateSqlRole(querySql);
		} catch (Exception e) {
			e.printStackTrace();
			// log.saveExceptionLog("basefunction.impl.QuerySqlService.saveQuerySql",e.getMessage(),SessionUtil.getUserNo());
			throw new BamSysException(
					"" + ConfigureReportUtil.SYSTEM_EXCEPTION_CODE,
					"com.hoau.crm.module.customer.server.service.impl.QuerySqlService.saveQuerySql",
					e);
		}
	}

	private void initBlob(QuerySql querySql){
		if(querySql!=null){
			try {
				//SQL
				if(querySql.getSql()!=null &&!"".equals(querySql.getSql())){
					querySql.setSqlBlob(querySql.getSql().getBytes(ItfConifgConstant.SQL_BLOB_CODE));
				}
				//原值SQL
				if(querySql.getOriginalSql()!=null &&!"".equals(querySql.getOriginalSql())){
					querySql.setOriginalSqlBlob(querySql.getOriginalSql().getBytes(ItfConifgConstant.SQL_BLOB_CODE));
				}
			} catch (Exception e) {
				log.error("String_To_Blob:",e);
			}
			
		}
		
	}
	@Transactional
	@Override
	public void modifyQuerySql(QuerySql querySql) throws BamSysException {
		try {
			initBlob(querySql);
			querySqlDao.updateQuerySql(querySql);
			addOrUpdateSqlRole(querySql);
		} catch (Exception e) {
			e.printStackTrace();
			// log.saveExceptionLog("basefunction.impl.QuerySqlService.modifyQuerySql",e.getMessage(),SessionUtil.getUserNo());
			throw new BamSysException(
					"" + ConfigureReportUtil.SYSTEM_EXCEPTION_CODE,
					"com.hoau.crm.module.customer.server.service.impl.QuerySqlService.modifyQuerySql",
					e);
		}
	}

	public void addOrUpdateSqlRole(QuerySql querySql) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("createUser", UserContext.getCurrentUser().getUserName());
		map.put("sqlId", querySql.getId());
		querySqlDao.deleteSqlRoleById(map);

		String[] ids = querySql.getRoles().split(",");
		for (int i = 0; i < ids.length; i++) {
			if(!StringUtil.isEmpty(ids[i])){
				String id = UUIDUtil.getUUID();
				SqlRoleEntity sqlRole = new SqlRoleEntity();
				sqlRole.setId(id);
				sqlRole.setRoleId(ids[i]);
				sqlRole.setSqlId(querySql.getId());
				sqlRole.setCreateUser(UserContext.getCurrentUser().getUserName());
				querySqlDao.addSqlRoles(sqlRole);
			}
		}
	}

	@Transactional
	@Override
	public int toVoidQuerySql(List<String> querySqlNos) {
		// 删除角色
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createUser", UserContext.getCurrentUser().getUserName());
		map.put("sqlNos", querySqlNos);
		querySqlDao.deleteSqlRoles(map);
		int number = querySqlDao.deleteQuerySql(querySqlNos);

		return number;
	}

	@Override
	public List<QuerySql> showPageQuerySql(QuerySql querySql, int start,
			int limit) {
		RowBounds rb = new RowBounds(start, limit);
		List<QuerySql> querySqls = querySqlDao.selectPageQuerySql(querySql, rb);
		if(querySqls.size()>0){
			for(QuerySql sqlinfo:querySqls){
				try {
					if(sqlinfo.getSqlBlob()!=null){
						sqlinfo.setSql(new String(sqlinfo.getSqlBlob(),ItfConifgConstant.SQL_BLOB_CODE));
					}
					if(sqlinfo.getOriginalSqlBlob()!=null){
						sqlinfo.setOriginalSql(new String(sqlinfo.getOriginalSqlBlob(),ItfConifgConstant.SQL_BLOB_CODE));
					}
				} catch (UnsupportedEncodingException e) {
					log.error("bolo_To_String:",e);
				}
			}
		}
		
		return querySqls;
	}

	@Override
	public Long totalQuerySqlCount(QuerySql querySql) {
		return querySqlDao.totalQuerySqlCount(querySql);
	}

	@Override
	public List<RoleThreeVo> querySqlRoleList(String sqlCode) {
		// 查询所有的角色
		List<RoleEntity> roleList = SSOEntityConvert.rolesConvert(ssoRoleService.queryAllRole());
		// 查询此SQL已经配置的角色
		List<RoleEntity> roles = querySqlDao.querySqlRoleList(sqlCode);
		// 返回的角色与SQL信息
		List<RoleThreeVo> rolesList = new ArrayList<RoleThreeVo>();
		for (int i = 0; i < roleList.size(); i++) {
			RoleEntity entity = roleList.get(i);
			RoleThreeVo vo = new RoleThreeVo();
			vo.setId(entity.getCode());
			vo.setText(entity.getName());
			vo.setLeaf(true);
			// 判断是否选中
			boolean isCheck = false;
			for(int j=0; j<roles.size(); j++){
				RoleEntity r = roles.get(j);
				if(r.getCode().equals(entity.getCode())){
					isCheck = true;
					break;
				}
			}
			vo.setChecked(isCheck);
			vo.setIconCls("treeNodeLeaf");
			rolesList.add(vo);
		}
		return rolesList;
	}

	@Override
	public QuerySql querySqlInfo(QuerySql querySql) {
		// TODO Auto-generated method stub
		QuerySql sqlinfo = querySqlDao.querySqlInfo(querySql);
		if(sqlinfo!=null){
			try {
				if(sqlinfo.getSqlBlob()!=null){
					sqlinfo.setSql(new String(sqlinfo.getSqlBlob(),ItfConifgConstant.SQL_BLOB_CODE));
				}
				if(sqlinfo.getOriginalSqlBlob()!=null){
					sqlinfo.setOriginalSql(new String(sqlinfo.getOriginalSqlBlob(),ItfConifgConstant.SQL_BLOB_CODE));
				}
			} catch (UnsupportedEncodingException e) {
				log.error("bolo_To_String2:",e);
			}
		}
		return sqlinfo;
	}

}
