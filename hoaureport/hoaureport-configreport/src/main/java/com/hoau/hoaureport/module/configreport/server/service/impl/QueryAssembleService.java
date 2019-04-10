package com.hoau.hoaureport.module.configreport.server.service.impl;

import au.com.bytecode.opencsv.CSVWriter;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.common.server.constants.ItfConifgConstant;
import com.hoau.hoaureport.module.common.shared.util.FolderUtils;
import com.hoau.hoaureport.module.common.shared.util.POIExcelUtil;
import com.hoau.hoaureport.module.configreport.server.dao.QueryAssembleDaoMapper;
import com.hoau.hoaureport.module.configreport.server.dao.ShowburDaoMapper;
import com.hoau.hoaureport.module.configreport.server.service.IQueryAssembleService;
import com.hoau.hoaureport.module.configreport.server.util.CSVUtils;
import com.hoau.hoaureport.module.configreport.server.util.ConfigureReportUtil;
import com.hoau.hoaureport.module.configreport.server.util.ExportExcel;
import com.hoau.hoaureport.module.configreport.shared.domain.*;
import com.hoau.hoaureport.module.configreport.shared.exception.BamSysException;
import com.hoau.hoaureport.module.frameworkimpl.server.context.HoauReportUserContext;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description
 * @version 1.0
 * @author zzx
 * @update 2012-10-9 上午10:18:10
 */
@Service("queryAssembleService")
public class QueryAssembleService implements IQueryAssembleService {
	
	@Resource
	QueryAssembleDaoMapper queryAssembleDao;
	
	@Resource
	ShowburDaoMapper showburDaoMapper;

	@Resource(name = "readjdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	
	/**
	 * 日志
	 */
	private static Logger LOG = LoggerFactory
			.getLogger(QueryAssembleService.class);

	/**
	 * 查询sql总条数
	 * */
	@Override
	public Long totalshowQuerySql(QuerySql queryAssemble) {
		Long countNum = queryAssembleDao.totalshowQuerySql(queryAssemble);
		return countNum;
	}

	/**
	 * 查询个和解析下拉框中的sql
	 * */
	@Override
	public List<QuerySql> showQuerySql(QuerySql queryAssemble, int start,
			int limit) throws BamSysException {
		// json和实体类的转换
		ObjectMapper mapper = new ObjectMapper();
		StringWriter stringWriter = null;
		RowBounds rb = new RowBounds(start, limit);
		List<QuerySql> lists = queryAssembleDao.showQuerySql(queryAssemble, rb);
		List<QuerySql> resultlists = new ArrayList<QuerySql>();

		/**
		 * 1）后去解析实体类，解析param参数，将param从json 转换成实体类集合 2）循环获取实体集合中的每个实体类，取得sql属性
		 * 3）根据 if (存在sql) 调用dao方法执行sql ,得到 NameValue(name,value)
		 * 的集合并保持到实体类map属性中 4）从新将实体类集合转换成json字符串保存到原来的param属性中
		 * 
		 * 功能：参数中有下列框（1：枚举类数据，2：需要从数据库中查询获取的） 将第2个类型数据转换成第1类数据类型给前台解析
		 * */
		for (QuerySql eq : lists) {
			
			if(eq!=null){
				try {
					if(eq.getSqlBlob()!=null){
						eq.setSql(new String(eq.getSqlBlob(),ItfConifgConstant.SQL_BLOB_CODE));
					}
					if(eq.getOriginalSqlBlob()!=null){
						eq.setOriginalSql(new String(eq.getOriginalSqlBlob(),ItfConifgConstant.SQL_BLOB_CODE));
					}
				} catch (UnsupportedEncodingException e) {
					LOG.error("bolo_To_String3:",e);
				}
			}
			
			
			List<ParamBean> pbs = null;
			// 组合表显示的列名
			String[] head = eq.getTableHead().split(",");
			///
			
			List<TableHead> myColumns = queryTableHeads(head);
			stringWriter = new StringWriter();
			try {
				mapper.writeValue(stringWriter, myColumns);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BamSysException(
						"" + ConfigureReportUtil.BUSERVICE_EXCEPTION_UNJSON,
						"com.hoau.crm.module.customer.server.service.impl.QueryAssembleService.implshowQuerySql",
						e);
			}
			eq.setMyColumn(stringWriter.getBuffer().toString());
			stringWriter = null;

			if (null == eq.getParam() || "".equals(eq.getParam())) {
				resultlists.add(eq);
				continue;
			}
			try {
				// 解析json 成实体类集合
				pbs = mapper.readValue(eq.getParam(),
						new TypeReference<List<ParamBean>>() {
						});
			} catch (Exception e) {
				e.printStackTrace();
				throw new BamSysException(
						"" + ConfigureReportUtil.BUSERVICE_EXCEPTION_UNJSON,
						"com.hoau.crm.module.customer.server.service.impl.QueryAssembleService.implshowQuerySql",
						e);
			}

			if (pbs != null) {
				for (int i = 0; i < pbs.size(); i++) {
					if (pbs.get(i).getSql() != ""
							&& pbs.get(i).getSql() != null) {
						// 如果需要执行sql获取数据，则调用到方法获取数据，清空sql
						pbs.get(i).setMap(null);
						try {
							pbs.get(i)
									.setMap(execSqlQuery(pbs.get(i).getSql()));
						} catch (Exception e) {
							e.printStackTrace();
							pbs.get(i).setMap(new ArrayList<NameValue>());
						}
						pbs.get(i).setSql(null);
					}
				}
				stringWriter = new StringWriter();
				try {
					mapper.writeValue(stringWriter, pbs);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BamSysException(
							"" + ConfigureReportUtil.BUSERVICE_EXCEPTION_UNJSON,
							"com.hoau.crm.module.customer.server.service.impl.QueryAssembleService.implshowQuerySql",
							e);
				}

				eq.setParam(stringWriter.getBuffer().toString());
				stringWriter = null;
			}

			resultlists.add(eq);
		}

		return resultlists;
	}

	/**
	 * @param sql
	 * @return 获取下拉框的值
	 */
	public List<NameValue> execSqlQuery(String sql) {
		List<NameValue> list = new ArrayList<NameValue>();

		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		NameValue nv = null;
		while (rs.next()) {
			nv = new NameValue(rs.getString(1), rs.getString(2));
			list.add(nv);
			nv = null;
		}

		return list;
	}

	/**
	 * 检查查询数量
	 * */
	@Override
	public long checkMaxNumber(String sql, String queryParam) {
		//TODO sql 权限判断
		sql = sqlAuthority(sql,HoauReportUserContext.getCurrentDept().getParentName());

		String countSql = "select count(0) from (" + sql + ") ss";
		long number = (long) 0;
		NamedParameterJdbcTemplate newjdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate);
		Map<String, Object> pMap = controlParam(queryParam);
		if (sql.indexOf("&create_user") > 0)
			pMap.put("create_user", UserContext.getCurrentUser().getUserName());

		// 处理SQL中的IF
		String[] ifSqlArr = countSql.split("<if>");
		String ifSql;
		String[] endIfsqlArr;
		if(ifSqlArr.length > 1){
			countSql = ifSqlArr[0];
			for(int i=1; i<ifSqlArr.length; i++){
				ifSql = ifSqlArr[i];
				endIfsqlArr = ifSql.split("</if>");
				if(endIfsqlArr.length > 0){
					if(pMap != null && pMap.size() > 0){
						for (Map.Entry<String, Object> entry : pMap.entrySet()) {
							if(endIfsqlArr[0].indexOf("&" + entry.getKey()) > -1){
								if(StringUtil.isNotEmpty(entry.getValue().toString())){
									countSql += endIfsqlArr[0];
									if(endIfsqlArr.length > 1){
										countSql += endIfsqlArr[1];
									}
								} else if(endIfsqlArr.length > 1){
									countSql += endIfsqlArr[1];
									//pMap.remove(entry.getKey());
								}
								break;
							}
						}
					}
				}
			}
		}

		SqlRowSet rs = newjdbcTemplate.queryForRowSet(countSql, pMap);
		if (rs.next()) {
			number = rs.getLong(1);
		}
		// return number;
		// long num =
		// queryAssembleDao.checkMaxNumber(countSql,controlParam(queryParam));
		return number;
	}

	/**
	 * 获取数据
	 * */
	@Override
	public List<ExportQuery> execSqlAll(String sql, String queryParam,
			int length, int start, int limit) {

		List<ExportQuery> exportQuerys = new ArrayList<ExportQuery>();
		exportQuerys = execSqlAll(sql, controlParam(queryParam), length, true,
				start, limit);

		return exportQuerys;
	}

	/**
	 * @param sql
	 * @param queryParam
	 * @param length
	 * @param start
	 * @param limit
	 * @return 获取导出数据数据的查询结果
	 */
	public List<ExportQuery> execSqlAll(String sql,
			Map<String, Object> queryParam, int length, boolean isMaxCheck,
			int start, int limit) {
		LOG.info("queryParam : " + queryParam + "");
		// 处理SQL中的IF
		String[] ifSqlArr = sql.split("<if>");
		String ifSql;
		String[] endIfsqlArr;
		if(ifSqlArr.length > 1){
			sql = ifSqlArr[0];
			for(int i=1; i<ifSqlArr.length; i++){
				ifSql = ifSqlArr[i];
				endIfsqlArr = ifSql.split("</if>");
				if(endIfsqlArr.length > 0){
					if(queryParam != null && queryParam.size() > 0){
						for (Map.Entry<String, Object> entry : queryParam.entrySet()) {
							if(endIfsqlArr[0].indexOf("&" + entry.getKey()) > -1){
								if(StringUtil.isNotEmpty(entry.getValue().toString())){
									sql += endIfsqlArr[0];
									if(endIfsqlArr.length > 1){
										sql += endIfsqlArr[1];
									}
								} else if(endIfsqlArr.length > 1){
									sql += endIfsqlArr[1];
									//queryParam.remove(entry.getKey());
								}
								break;
							}
						}
					}
				}
			}
		}
		//TODO 加权限控制
		sql = sqlAuthority(sql,HoauReportUserContext.getCurrentDept().getParentName());
		// 查询自定义sql
		String newSql = "select * from (select wg.*,rownum as rn from ( " + sql + ") wg) where rn > " + start	+ " and rn <= " + (start + limit);
		if (sql.indexOf("&create_user") > 0)
			queryParam.put("create_user", UserContext.getCurrentUser()
					.getUserName());
		List<ExportQuery> list = new ArrayList<ExportQuery>();
		NamedParameterJdbcTemplate newjdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate);
		LOG.info("select sql: " + newSql + "");
		SqlRowSet rs = newjdbcTemplate.queryForRowSet(newSql, queryParam);
		
		LOG.info(newjdbcTemplate.toString());
		LOG.info("queryParam : " + queryParam + "");
		if (null != rs) {
			while (rs.next()) {
				/*
				 * if(isMaxCheck && ++number>Constant.QUERY_MAX_NUM){ break; }
				 */
				ExportQuery eq = new ExportQuery();
				for (int i = 1; i <= length; i++) {
					eq.inValue(rs.getString(i), i);
				}
				list.add(eq);
			}
		}
		return list;
	}

	/**
	 * 组装表头
	 * */
	public List<TableHead> queryTableHeads(String[] head) {
		List<TableHead> tableHeads = new ArrayList<TableHead>();
		head = changeHeadParam(head);
		TableHead tab = new TableHead(); // 设置序号行
		tab.setXtype("rownumberer");
		tab.setWidth(60);
		tab.setHeader("序号");
		tableHeads.add(tab);
		int index = 0;
		for (String name : head) { // 组合表的所有列
			tab = new TableHead(name, index);
			tableHeads.add(tab);
			++index;
		}
		return tableHeads;
	}
	/**
	 * 列名转换
	 * @param head
	 * @return
	 */
	public String[] changeHeadParam(String [] head){
		if(head ==null){
			return null;
		}
		for(int i=0;i<head.length;i++){
			String[] hs = head[i].split(":");
			head[i] =hs[0];
		}
		return head;
	}
	
	/**
	 * 组装列和值得映射关系
	 * */
	public List<Field> queryField(int length) {
		List<Field> fields = new ArrayList<Field>();
		Field fd = null;
		for (int i = 0; i < length; i++) {
			fd = new Field("str" + i);
			fields.add(fd);
			fd = null;
		}
		return fields;
	}

	/**
	 * 导出Excel
	 * */
	@Override
	public ExcelReturn toExcelPort(String sql,String remark, String queryParam, int length,
			String[] head, int startNumber, int limitNumber) {
		// 返回参数
		ExcelReturn excelReturn = new ExcelReturn();
		// 创建Excel工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 导出Excel的文件名
		String fileName = ConfigureReportUtil.getNowDateStrNoSep(remark);

		try {
			excelReturn.setFileName(fileName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			// try{
			// log.saveExceptionLog("com.deppon.pda.module.bamCode.server.service.impl.QueryAssembleService.toExcelPort",
			// e);
			// }catch(Exception ef){
			// ef.printStackTrace();
			// }
		}

		// 创建Excel 实体类 计划 获取数据
		ExportExcel<ExportQuery> ex = new ExportExcel<ExportQuery>();

		// 获取要导出的数据
		List<ExportQuery> exportQuerys = null;
		while (startNumber <= limitNumber) {
			exportQuerys = new ArrayList<ExportQuery>();
			exportQuerys = execSqlAll(sql, controlParam(queryParam), length,
					false, startNumber, ConfigureReportUtil.MAX_NUM);
			// 将数据写入Excel 返回文件流
			workbook = exportExcel(workbook, (startNumber + 1) + "-"
					+ (startNumber + ConfigureReportUtil.MAX_NUM), head, exportQuerys);
			startNumber += ConfigureReportUtil.MAX_NUM;
			exportQuerys = null;
		}

		excelReturn.setInputStream(ex.inputToClient(workbook));
		return excelReturn;
	}

	/**
	 * 创建Excel
	 * */
	@SuppressWarnings("deprecation")
	public HSSFWorkbook exportExcel(HSSFWorkbook workbook, String title,
			String[] headers, List<ExportQuery> dataset) {

		//存放单元格样式对象
		Map<String,HSSFCellStyle> cellStyleMap = new HashMap<String, HSSFCellStyle>();

		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i].split(":")[0]);
			cell.setCellValue(text);
		}
		int index = 0;
		for (ExportQuery eq : dataset) {
			index++;
			row = sheet.createRow(index);
			for (int i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				String[] hs = headers[i].trim().split(":");
				if(hs.length>1 && "N".equalsIgnoreCase(hs[1].trim())){ //数值
					try {
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(Double.parseDouble(eq.outValue(i + 1)));
						String fmt = null;
						HSSFCellStyle cellStyleN =null;
						String key ="1";//默认整数
						if(hs.length>2){
							key = hs[2].trim();
							cellStyleN =cellStyleMap.get(key);
							if(cellStyleN==null){
								fmt = POIExcelUtil.getPoiFormats(key);
							}
							
						}else{
							cellStyleN =cellStyleMap.get(key);
						}
						if(cellStyleN !=null){
							cell.setCellStyle(cellStyleN);
						}else{
							if(fmt == null || "".equals(fmt.trim())){
								fmt = "0";//默认整数类型
								key ="1";
							}
							cellStyleN = workbook.createCellStyle();
				            HSSFDataFormat format = workbook.createDataFormat();
				            cellStyleN.setDataFormat(format.getFormat(fmt));
				            cell.setCellStyle(cellStyleN);
				            cellStyleMap.put(key, cellStyleN);
						}
						
						
					} catch (Exception e) {
//						e.printStackTrace();
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(eq.outValue(i + 1));
					}
					
				}else{
					cell.setCellValue(eq.outValue(i + 1));
				}
				
				
			}

		}
		return workbook;

	}

	/**
	 * 解析动态传入后台的参数 转化为Map集合
	 * */
	public Map<String, Object> controlParam(String param) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null == param || "".equals(param)) {
			return null;
		}
		String[] paramCount = param.split(",");
		for (String s : paramCount) {
			String p =s.substring(s.indexOf(":") + 1, s.length());
			//是否文本域
			int index =p.indexOf(FolderUtils.REPORT_TYPE_A);
			if(index>=0){
				p = p.replace(FolderUtils.REPORT_TYPE_A,"");
				String[] parama = p.split("\\.");
				List<String> list = new ArrayList<String>();
				if(parama!=null){
					for(int k=0;k<parama.length;k++){
						list.add(parama[k]);
					}
				}
				map.put(s.substring(0, s.indexOf(":")),list);
//				p = p.replaceAll("\\.", ",");
			}else{
				map.put(s.substring(0, s.indexOf(":")),p);
			}
//			map.put(s.substring(0, s.indexOf(":")),p);
			
//			map.put(s.substring(0, s.indexOf(":")),
//					s.substring(s.indexOf(":") + 1, s.length()));
		}
		return map;
	}


	
	/**
	 * 级联下了框解析
	 * */
	@Override
	public List<NameValue> queryCombo(String queryParam, String sql) {
		List<NameValue> list = new ArrayList<NameValue>();
		//list = queryCombo(controlParam(queryParam), sql);
		Map<String, Object> map = controlParam(queryParam);
		// 查询自定义sql
		//String newSql = "select * from (select wg.*,rownum as rn from ( " + sql + ") wg) where rn > " + start	+ " and rn <= " + (start + limit);
		//LOG.info("select sql: " + newSql + "");
		// 当前用户
		/*if (sql.indexOf("&CREATE_USER_CODE") > 0)
			map.put("CREATE_USER_CODE", UserContext.getCurrentUser()
					.getUserName());
		if (sql.indexOf("&CREATE_USER_DEPTCODE") > 0)
			map.put("CREATE_USER_DEPTCODE", ButterflyUserContext.getCurrentDept().getCode());*/
		list = queryCombo(map, sql);
		return list;
	}

	/**
	 * @param controlParam
	 * @param sql
	 * @return 级联下拉框获取值
	 */
	public List<NameValue> queryCombo(Map<String, Object> controlParam,
			String sql) {
		List<NameValue> list = new ArrayList<NameValue>();
		NamedParameterJdbcTemplate newjdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate);
		SqlRowSet rs = newjdbcTemplate.queryForRowSet(sql, controlParam);
		NameValue nv = null;
		while (rs.next()) {
			nv = new NameValue(rs.getString(1), rs.getString(2));
			list.add(nv);
			nv = null;
		}
		return list;
	}

	

	public File getPath(String webContextPath) {
		String path = webContextPath + "/data/";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		return file;
	}

	@Override
	public QuerySql querySqlById(String id) {
		
		QuerySql eq =queryAssembleDao.querySqlById(id);
		if(eq!=null){
			try {
				if(eq.getSqlBlob()!=null){
					eq.setSql(new String(eq.getSqlBlob(),ItfConifgConstant.SQL_BLOB_CODE));
				}
				if(eq.getOriginalSqlBlob()!=null){
					eq.setOriginalSql(new String(eq.getOriginalSqlBlob(),ItfConifgConstant.SQL_BLOB_CODE));
				}
			} catch (UnsupportedEncodingException e) {
				LOG.error("bolo_To_String4:",e);
			}
		}
		
		return eq;
	}

	/**
	 * 生成CSV包
	 * @throws IOException 
	 */
	@Override
	public ExcelReturn toZipPort(QuerySql querysqlInfo, String queryParam,
			int length, String[] head, int startNumber, int limitNumber,
			String username, String downCode,String charCode) throws IOException {

		head=changeHeadParam(head);
		// 返回参数
		ExcelReturn excelReturn = new ExcelReturn();
		CSVWriter writer = null;
		
		Map<String,String> fmap =FolderUtils.getFolder(downCode, username, querysqlInfo.getRemark(), querysqlInfo.getNumber(), new Date());
		String fileName = fmap.get("fileName");
		String folder = fmap.get("folder");
		// 如果文件夹已存在就删除，可以根据需求安排是否进行删除，按照我的需求我不需要删除，因为我自动生成的文件码不会重复
		File myfolder = new File(folder);
		if (!myfolder.exists() && !myfolder.isDirectory()) {
			myfolder.mkdirs();
		}

		// 获取要导出的数据
		List<String[]> exportQuerys = null;
		File fcsv = new File(myfolder + "/" + fileName + ".csv");
		if (fcsv.exists()) {
			fcsv.delete();
		} else {
			fcsv.createNewFile();
		}
		if(charCode==null ||"".equals(charCode.trim())){
			charCode="GBK";
		}
		writer = new CSVWriter(new FileWriterWithEncoding(fcsv, charCode));
		writer.writeNext(head);
		while (startNumber <= limitNumber) {
			exportQuerys = new ArrayList<String[]>();
			exportQuerys = execSqlAllForCsv(querysqlInfo.getSql(), controlParam(queryParam),
					length, false, startNumber, ConfigureReportUtil.MAX_NUM,"");

			writer.writeAll(exportQuerys);
			writer.flush();

			startNumber += ConfigureReportUtil.MAX_NUM;
			exportQuerys = null;
		}
		writer.close();

		String path = CSVUtils.toZip(folder + "/", fileName);
		if (fcsv.exists()) {
			fcsv.delete();
		}
		excelReturn.setZipName(path);
		excelReturn.setJobFileName(fileName);
		return excelReturn;
	

	}

	public List<String[]> execSqlAllForCsv(String sql,
			Map<String, Object> queryParam, int length, boolean isMaxCheck,
			int start, int limit,String userParentOrgName) {
		LOG.info("queryParam : " + queryParam + "");
		// 处理SQL中的IF
		String[] ifSqlArr = sql.split("<if>");
		String ifSql;
		String[] endIfsqlArr;
		if(ifSqlArr.length > 1){
			sql = ifSqlArr[0];
			for(int i=1; i<ifSqlArr.length; i++){
				ifSql = ifSqlArr[i];
				endIfsqlArr = ifSql.split("</if>");
				if(endIfsqlArr.length > 0){
					if(queryParam != null && queryParam.size() > 0){
						for (Map.Entry<String, Object> entry : queryParam.entrySet()) {
							if(endIfsqlArr[0].indexOf("&" + entry.getKey()) > -1){
								if(StringUtil.isNotEmpty(entry.getValue().toString())){
									sql += endIfsqlArr[0];
									if(endIfsqlArr.length > 1){
										sql += endIfsqlArr[1];
									}
								} else if(endIfsqlArr.length > 1){
									sql += endIfsqlArr[1];
									//queryParam.remove(entry.getKey());
								}
								break;
							}
						}
					}
				}
			}
		}
		//权限判断
		sql = sqlAuthority(sql,userParentOrgName);
		// 查询自定义sql
		String newSql = "select * from (select wg.*,rownum as rn from ( " + sql + ") wg) where rn > " + start	+ " and rn <= " + (start + limit);
		if (sql.indexOf("&create_user") > 0)
			queryParam.put("create_user", UserContext.getCurrentUser()
					.getUserName());
		List<String[]> list = new ArrayList<String[]>();
		NamedParameterJdbcTemplate newjdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate);
		LOG.info("select sql: " + newSql + "");
		SqlRowSet rs = newjdbcTemplate.queryForRowSet(newSql, queryParam);
		LOG.info("queryParam : " + queryParam + "");
		if (null != rs) {
			while (rs.next()) {
				
				String[] eq = new String[length];
				for (int i = 1; i <= length; i++) {
					eq[i-1]=rs.getString(i);
				}
				list.add(eq);
			}
		}
		return list;
	}
	
	@SuppressWarnings("static-access")
	public List getShowburInfo(String id){
		List<ShowburEntity> showburEntities = null;
		showburEntities = showburDaoMapper.getShowburInfo(id);
		List reList = new ArrayList();
		if(showburEntities!=null){
			Map remap = new HashMap();
			for(ShowburEntity showburEntity : showburEntities){
				//0按天 1按月 2按年 3区域天 4区域月统计
				if("1".equals(showburEntity.getXaxis())){
			        //获取当前时间  
			        Calendar cal = Calendar.getInstance();  
			        //调到上个月  
			        cal.add(Calendar.MONTH, -1);
			        //得到一个月最最后一天日期(31/30/29/28)  
			        int MaxDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
		        	List legendList = new ArrayList();
		        	legendList.add("N上海");
		        	legendList.add("N杭州");
		        	legendList.add("N青岛");
		        	legendList.add("N厦门");
		        	remap.put("legend", legendList);
		        	List seriesList = new ArrayList();
		        	List monthList = new ArrayList();
		        	for(int j=0;j<legendList.size();j++){
		        		Map tempser = new HashMap();
		        		List seriesList1 = new ArrayList();
		        		monthList = new ArrayList();
		        		tempser.put("name",(String)legendList.get(j));
		        		if("0".equals(showburEntity.getChartype())){
		        			tempser.put("type", "bar");
		        		}else if("1".equals(showburEntity.getChartype())){
		        			tempser.put("type", "pie");
		        		}
				        for(int i=1;i<=MaxDay;i++){
				        	cal.set( cal.get(cal.YEAR), cal.get(cal.MONTH), i);
				        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				        	String tmp_day = sdf.format(cal.getTime());
				        	String sumByMonth = showburDaoMapper.getSumByMonth((String)legendList.get(j),tmp_day);
				        	monthList.add(i);
				        	seriesList1.add(sumByMonth);
				        }
		        		tempser.put("series", seriesList1);
		        		seriesList.add(tempser);
		        	}		        	
		        	remap.put("seriesList", seriesList);
			        remap.put("category", monthList);
				}
			}
			reList.add(remap);
		}
		return reList;
	}

	//TODO 权限处理
	public String sqlAuthority(String tmp_sql,String userParentOrgName){
		//LOG.info(tmp_sql);
		// 获得当前用户的上级部门
		//String userParentOrgName = HoauReportUserContext.getCurrentDept().getParentName();
		StringBuffer sqlAuthority = new StringBuffer();

		if (tmp_sql.contains("<autSyb>") && userParentOrgName.contains("事业部") ){
			String tmp_Sql_String = tmp_sql.replace("<autSyb>","")
					.replace("</autSyb>",userParentOrgName);

			sqlAuthority.append(tmp_Sql_String.substring(0,tmp_Sql_String.indexOf("<autDq>")));
		}
		else if (tmp_sql.contains("<autDq>") && userParentOrgName.contains("大区") ){
			String tmp_Sql_noSyb = tmp_sql.substring(0,tmp_sql.indexOf("<autSyb>"));
			String tmp_Sql_dq = tmp_sql.substring(tmp_sql.indexOf("<autDq>"));
			String tmp_Sql_String = tmp_Sql_dq.replace("<autDq>","")
					.replace("</autDq>",userParentOrgName);
			sqlAuthority.append(tmp_Sql_noSyb);
			sqlAuthority.append(tmp_Sql_String);
			//sqlAuthority.append("'");
		}else{
			if(tmp_sql.contains("<autSyb>")){
				sqlAuthority.append(tmp_sql.substring(0,tmp_sql.indexOf("<autSyb>")));
			}
			else{
				sqlAuthority.append(tmp_sql);
			}
		}
		//LOG.info(sqlAuthority.toString());
		return sqlAuthority.toString();
	}

	@Override
	public ExcelReturn toZipPortParentOrg(QuerySql querysqlInfo, String queryParam, int length, String[] head, int startNumber, int limitNumber, String username, String downCode, String charCode, String userParentOrgName) throws IOException {

		head=changeHeadParam(head);
		// 返回参数
		ExcelReturn excelReturn = new ExcelReturn();
		CSVWriter writer = null;

		Map<String,String> fmap =FolderUtils.getFolder(downCode, username, querysqlInfo.getRemark(), querysqlInfo.getNumber(), new Date());
		String fileName = fmap.get("fileName");
		String folder = fmap.get("folder");
		// 如果文件夹已存在就删除，可以根据需求安排是否进行删除，按照我的需求我不需要删除，因为我自动生成的文件码不会重复
		File myfolder = new File(folder);
		if (!myfolder.exists() && !myfolder.isDirectory()) {
			myfolder.mkdirs();
		}

		// 获取要导出的数据
		List<String[]> exportQuerys = null;
		File fcsv = new File(myfolder + "/" + fileName + ".csv");
		if (fcsv.exists()) {
			fcsv.delete();
		} else {
			fcsv.createNewFile();
		}
		if(charCode==null ||"".equals(charCode.trim())){
			charCode="GBK";
		}
		writer = new CSVWriter(new FileWriterWithEncoding(fcsv, charCode));
		writer.writeNext(head);
		while (startNumber <= limitNumber) {
			exportQuerys = new ArrayList<String[]>();
			exportQuerys = execSqlAllForCsv(querysqlInfo.getSql(), controlParam(queryParam),
					length, false, startNumber, ConfigureReportUtil.MAX_NUM,userParentOrgName);

			writer.writeAll(exportQuerys);
			writer.flush();

			startNumber += ConfigureReportUtil.MAX_NUM;
			exportQuerys = null;
		}
		writer.close();

		String path = CSVUtils.toZip(folder + "/", fileName);
		if (fcsv.exists()) {
			fcsv.delete();
		}
		excelReturn.setZipName(path);
		excelReturn.setJobFileName(fileName);
		return excelReturn;
	}
}

