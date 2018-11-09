package com.hoau.hoaureport.module.configreport.server.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hoau.hoaureport.module.frameworkimpl.server.context.HoauReportUserContext;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.server.context.SessionContext;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.common.shared.util.FolderUtils;
import com.hoau.hoaureport.module.configreport.server.service.IQueryAssembleService;
import com.hoau.hoaureport.module.configreport.server.util.ConfigureReportUtil;
import com.hoau.hoaureport.module.configreport.shared.domain.ExcelReturn;
import com.hoau.hoaureport.module.configreport.shared.domain.ExportEntity;
import com.hoau.hoaureport.module.configreport.shared.domain.ExportQuery;
import com.hoau.hoaureport.module.configreport.shared.domain.Field;
import com.hoau.hoaureport.module.configreport.shared.domain.NameValue;
import com.hoau.hoaureport.module.configreport.shared.domain.QuerySql;
import com.hoau.hoaureport.module.configreport.shared.domain.ResultMessage;
import com.hoau.hoaureport.module.configreport.shared.domain.TableHead;
import com.hoau.hoaureport.module.configreport.shared.exception.BamSysException;

@Controller
@Scope("prototype")
public class QueryAssembleAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * @fields serialVersionUID
	 */
	@Resource
	IQueryAssembleService queryAssembleService;

	private List<QuerySql> sqlLists;
	private List reList;
	private QuerySql queryAssemble;
	private ResultMessage resultMessage;

	private String sql;
	private String tableHead;
	private String queryParam;
	private String queryParamText;

	// 导出Excel报表
	private String fileName;
	private InputStream excelStream;
	private int exportNumber;
	private int startNumber;
	private int limitNumber;

	private String id;
	
	private String downCode;
	private String charCode;
	//导出选择
	private int exportType;
	
	public String getId() {
		return id;
	}

	public List getReList() {
		return reList;
	}

	public void setReList(List reList) {
		this.reList = reList;
	}

	public void setId(String id) {
		this.id = id;
	}

	private List<TableHead> tableHeads;
	private List<Field> fields;
	private List<ExportQuery> exportQuerys;

	// 下拉框数据
	private List<NameValue> comboList;

	public String showQueryPage() {
		return this.returnSuccess();
	}

	/**
	 * 查询SQL
	 * */
	public String showQuerySql() {
		// ActionContext context = ActionContext.getContext();
		// Map<String, Object> sessions = context.getSession();
		// UserEntity user = (UserEntity) sessions.get("user");
		// queryAssemble.setCreateUser(user.getEmpCode());
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		String currentDeptCode = (String) SessionContext.getSession()
				.getObject(ConfigureReportUtil.KEY_CURRENT_DEPTCODE);
		if (null == queryAssemble)
			queryAssemble = new QuerySql();
		queryAssemble.setCreateUser(currUser.getUserName());
		queryAssemble.setRoleids(currUser.getRoleids());
		queryAssemble.setCurrDeptCode(currentDeptCode);

		// 模糊查询相关功能 自动忽略日期条件
		if (queryAssemble != null && queryAssemble.getRemark() != null
				&& !"".equals(queryAssemble.getRemark())) {
			queryAssemble.setStartTime(null);
			queryAssemble.setEndTime(null);
		}
		try {
			sqlLists = queryAssembleService.showQuerySql(queryAssemble,
					this.getStart(), this.getLimit());
			this.setTotalCount(queryAssembleService
					.totalshowQuerySql(queryAssemble));
			resultMessage = new ResultMessage(
					ConfigureReportUtil.RETURN_SUCCESS, "查询成功");
		} catch (BamSysException e) {
			// try{
			// log.saveExceptionLog(e.getErrPath(), e);
			// }catch(Exception ef){
			// ef.printStackTrace();
			// }
			resultMessage = new ResultMessage(ConfigureReportUtil.RETURN_FAIL,
					"JSON转换失败！");

		}

		return this.returnSuccess();

	}
	
	/**
	 * 根据ID获取显示sql信息
	 * @return
	 */
	public String querySqlInfo(){
		try {
			if(queryAssemble==null || queryAssemble.getId()==null || "".equals(queryAssemble.getId().trim())){
				return this.returnError("查询参数为空");
			}

			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			String currentDeptCode = (String) SessionContext.getSession()
					.getObject(ConfigureReportUtil.KEY_CURRENT_DEPTCODE);
			
			queryAssemble.setCreateUser(currUser.getUserName());
			queryAssemble.setRoleids(currUser.getRoleids());
			queryAssemble.setCurrDeptCode(currentDeptCode);

			sqlLists = queryAssembleService.showQuerySql(queryAssemble,0, 1);
			if(sqlLists!=null && sqlLists.size()>0){
				queryAssemble=sqlLists.get(0);
				return this.returnSuccess();
			}else{
				return this.returnError("SQL配置数据为空");
			}
			
		}catch (BamSysException e1) {
			log.error("querySqlForTab JSON转换失败",e1);
			return this.returnError("JSON转换失败！");
		} catch (Exception e) {
			log.error("querySqlForTab",e);
			return this.returnError("SQL数据查询异常");
		}
		
		
	}
	
	
	

	/**
	 * 根据解析的sql 和台传来的参数查数据
	 * */
	public String execSqlAll() {

		String[] head = tableHead.split(",");
		try {
			QuerySql querysqlInfo = queryAssembleService.querySqlById(id);
			exportQuerys = queryAssembleService.execSqlAll(
					querysqlInfo.getSql(), queryParam, head.length,
					this.getStart(), this.getLimit());
			this.setTotalCount(queryAssembleService.checkMaxNumber(
					querysqlInfo.getSql(), queryParam));

		} catch (Exception e) {
			// try{
			// log.saveExceptionLog("com.hoau.crm.module.customer.server.action.QueryAssembleAction.showQuerySql",
			// e);
			// }catch(Exception ef){
			// ef.printStackTrace();
			// }
			log.error("execSqlA11_error:",e);
			exportQuerys = new ArrayList<ExportQuery>();
			ExportQuery eq = new ExportQuery();
			eq.setStr0("执行sql错误");
			exportQuerys.add(eq);
			return this.returnSuccess();

		}
		tableHeads = queryAssembleService.queryTableHeads(head);
		fields = queryAssembleService.queryField(head.length);

		resultMessage = new ResultMessage(ConfigureReportUtil.RETURN_SUCCESS,
				"查询成功");
		return this.returnSuccess();
	}

	/**
	 * 导出Excel
	 * */
	public String exportExcel() {

		//String[] head = tableHead.split(",");
		ExcelReturn excelReturn = new ExcelReturn();
		ExportType(true,"正在导出Excel文件......");
		try {
			QuerySql querysqlInfo = queryAssembleService.querySqlById(id);
			
			String[] head = querysqlInfo.getTableHead().split(",");
			String querysql =querysqlInfo.getSql();
			String sqlName =querysqlInfo.getRemark();
			
			//是否导原值
			if(exportType==1 && querysqlInfo.getExitType()>0){
				querysql =querysqlInfo.getOriginalSql();
				head = querysqlInfo.getOriginalHead().split(",");
			}
			
			//------导出的时候添加remark区分文件名
			excelReturn = queryAssembleService.toExcelPort(
					querysql,sqlName, queryParam, head.length, head,
					startNumber, limitNumber);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		
		}

		
		this.fileName = excelReturn.getFileName();
		excelStream = excelReturn.getInputStream();
		ExportType(false,"导出Excel文件完成");
		return this.returnSuccess();

	}
	/**
	 * 下载验证
	 * @return
	 */
	public String verificationExport(){

		try {
			HttpSession session =ServletActionContext.getRequest().getSession();
			ExportEntity info =(ExportEntity)session.getAttribute(ConfigureReportUtil.CSV_KEY_REPORT_EXPORT);
			if(info!=null && info.isType() && new Date().getTime()-info.getTimes()<10*60*1000 ){
				//操作中。。。
				return this.returnError(info.getMessage());
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return this.returnSuccess();
	}
	
	public String csvExist(){
		try {

			QuerySql querysqlInfo = queryAssembleService.querySqlById(id);
			String username = UserContext.getCurrentUser().getUserName();
			if(FolderUtils.FILE_EXTENSION_CODE1002.equals(downCode)){
				username=FolderUtils.FILE_USER_ADMIN;
			}
			Map<String,String> folderMap =FolderUtils.getFolder(downCode, username, querysqlInfo.getRemark(), querysqlInfo.getNumber(), new Date());
			String zippath =folderMap.get("filePath")+".zip";
			log.info("zippath:"+zippath);
	         //将此文件流写入到response输出流中
	        File file = new File(zippath);
	        if(!file.exists()){
	        	return this.returnError("没有已生成的CSV包");
	        }
		} catch (Exception e) {
			log.error("csvExist_err:",e);
			e.printStackTrace();
			return this.returnError("系统验证CSV包异常");
		}
		return this.returnSuccess();
	}
	
	
	
	
	private void ExportType(boolean type,String msg){
		HttpSession session =ServletActionContext.getRequest().getSession();
		ExportEntity info = new ExportEntity(type,new Date().getTime(),msg);
		session.setAttribute(ConfigureReportUtil.CSV_KEY_REPORT_EXPORT, info);
	}
	
//	private boolean ifExporting(){
//		try {
//			HttpSession session =ServletActionContext.getRequest().getSession();
//			ExportEntity info =(ExportEntity)session.getAttribute(ConfigureReportUtil.CSV_KEY_REPORT_EXPORT);
//			if(info!=null && info.isType() && new Date().getTime()-info.getTimes()<10*60*1000 ){
//				return true;
//			}
//		} catch (Exception e) {
//			log.error(e);
//			e.printStackTrace();
//		}
//		return false;
//	}
	/**
	 * 生成csv包
	 * @return
	 */
	public String queryToZip(){
		String[] head = tableHead.replaceAll("[\\t\\n\\r]","").split(",");
		QueryToCsvZipThread ft =new QueryToCsvZipThread();
		ft.setQueryAssembleService(queryAssembleService);
		ft.setSqlid(id);
		ft.setHead(head);
		ft.setLimitNumber(limitNumber);
		ft.setStartNumber(startNumber);
		ft.setQueryParam(queryParam);
		ft.setCharCode(charCode);
		ft.setExportsel(exportType);
		ft.setSession(ServletActionContext.getRequest().getSession());
		ft.setUsername(UserContext.getCurrentUser().getUserName());
		// 获得当前用户的上级部门
		String userParentOrgName = HoauReportUserContext.getCurrentDept().getParentName();
		ft.setUserParentOrgName(userParentOrgName);
		ft.start();
		return this.returnSuccess();
	}
	
	public String showburqueryMethod(){
		//通过前台传来的ID获取数据
		reList = queryAssembleService.getShowburInfo(id);
		if(reList!=null && reList.size()>0){
			return this.returnSuccess();
		}else{
			return  this.returnError("查询无数据");
		}
		
	}
	
	
	/**
	 * 导出CSV
	 * */
	public String exportCSV() {
		
		ExcelReturn excelReturn = new ExcelReturn();
		ExportType(true,"正在导出CSV文件.....");
		try {

			
			QuerySql querysqlInfo = queryAssembleService.querySqlById(id);
			String username = UserContext.getCurrentUser().getUserName();
			if(FolderUtils.FILE_EXTENSION_CODE1002.equals(downCode)){
				username=FolderUtils.FILE_USER_ADMIN;
			}
			Map<String,String> folderMap =FolderUtils.getFolder(downCode, username, querysqlInfo.getRemark(), querysqlInfo.getNumber(), new Date());
			String zippath =folderMap.get("filePath")+".zip";
			
			excelReturn.setFileName(folderMap.get("fileName")+".zip");
	        StringBuffer sb = new StringBuffer();
	        sb.append("attachment;  filename=").append(excelReturn.getFileName());
			HttpServletResponse response =ServletActionContext.getResponse();
			response.setHeader("Expires", "0");
	        response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
	        response.setHeader("Pragma", "public");
	        response.setContentType("application/x-msdownload;charset=UTF-8");
	        response.setHeader("Content-Disposition", new String( sb.toString().getBytes(), "UTF-8"));
	          
	        
	         //将此文件流写入到response输出流中
	         File file = new File(zippath);
	         if(file.exists()){
	        	 FileInputStream inputStream = new FileInputStream(file);
		         OutputStream outputStream = response.getOutputStream(); 
//		         outputStream.write(new   byte []{( byte ) 0xEF ,( byte ) 0xBB ,( byte ) 0xBF }); 
		         byte[] buffer = new byte[1024];
		         int i = -1;
		         while ((i = inputStream.read(buffer)) != -1) {
		             outputStream.write(buffer, 0, i);
		         } 
		         outputStream.flush();
		         outputStream.close();
		         inputStream.close(); 
	         }
	         
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		ExportType(false,"导出CSV完成");
		return this.returnSuccess();

	}
	
	

	/**
	 * 执行下拉框查询
	 * */
	public String queryCombo() {
		try {
			comboList = queryAssembleService.queryCombo(queryParamText, sql);
		} catch (Exception e) {
			resultMessage = new ResultMessage(ConfigureReportUtil.RETURN_FAIL,
					"执行级联查询sql有误");
			return this.returnSuccess();
		}
		resultMessage = new ResultMessage(ConfigureReportUtil.RETURN_SUCCESS,
				"下拉框验证通过");
		if (null == comboList) {
			comboList = new ArrayList<NameValue>();
			resultMessage = new ResultMessage(ConfigureReportUtil.RETURN_FAIL,
					"级联未获取到数据，将无法查询");

		}

		return this.returnSuccess();

	}

	
	public String getCharCode() {
		return charCode;
	}

	public void setCharCode(String charCode) {
		this.charCode = charCode;
	}

	public void setQueryAssemble(QuerySql queryAssemble) {
		this.queryAssemble = queryAssemble;
	}

	public List<QuerySql> getSqlLists() {
		return sqlLists;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void setTableHead(String tableHead) {
		this.tableHead = tableHead;
	}

	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}

	public ResultMessage getResultMessage() {
		return resultMessage;
	}

	public List<TableHead> getTableHeads() {
		return tableHeads;
	}

	public List<Field> getFields() {
		return fields;
	}

	public List<ExportQuery> getExportQuerys() {
		return exportQuerys;
	}

	public String getFileName() {
		return fileName;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public QuerySql getQueryAssemble() {
		return queryAssemble;
	}

	public List<NameValue> getComboList() {
		return comboList;
	}

	public int getExportNumber() {
		return exportNumber;
	}

	public void setExportNumber(int exportNumber) {
		this.exportNumber = exportNumber;
	}

	public int getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}

	public int getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(int limitNumber) {
		this.limitNumber = limitNumber;
	}

	public String getDownCode() {
		return downCode;
	}

	public void setDownCode(String downCode) {
		this.downCode = downCode;
	}

	public int getExportType() {
		return exportType;
	}

	public void setExportType(int exportType) {
		this.exportType = exportType;
	}


	public String getQueryParamText() {
		return queryParamText;
	}

	public void setQueryParamText(String queryParamText) {
		this.queryParamText = queryParamText;
	}
}

/**
 * 生成csv包
 * @author 273503
 *
 */
class QueryToCsvZipThread extends Thread{
	Logger logger = Logger.getLogger(this.getClass());
	private IQueryAssembleService queryAssembleService;
	private String queryParam;
	private String[] head;
	private int startNumber;
	private int limitNumber;
	private String sqlid;
	private String username;
	private HttpSession session;
	private String charCode;
	private int exportsel; //导出选择
	private String userParentOrgName;//上级部门
	public void run(){  
		ExportType(session,true,"正在生成CSV文件......");
		
		try {
			QuerySql querysqlInfo = queryAssembleService.querySqlById(sqlid);
			//是否原值
			if(exportsel==1 && querysqlInfo.getExitType()>0){
				head = querysqlInfo.getOriginalHead().replaceAll("[\\t\\n\\r]","").split(",");
				querysqlInfo.setSql(querysqlInfo.getOriginalSql());
			}
			ExcelReturn excelReturn = queryAssembleService.toZipPortParentOrg(
					querysqlInfo,
					queryParam, head.length, head, startNumber, limitNumber,username,FolderUtils.FILE_EXTENSION_CODE1001,charCode,userParentOrgName);
			logger.info("ZipName:"+excelReturn.getZipName());
//			System.out.println("ZipName:"+excelReturn.getZipName());
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		ExportType(session,false,"生成CSV完成");
		
	}

	private void ExportType(HttpSession session,boolean type,String msg) {
		try {
			ExportEntity info = new ExportEntity(type, new Date().getTime(),msg);
			session.setAttribute(ConfigureReportUtil.CSV_KEY_REPORT_EXPORT, info);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
	}
	 
	
	public IQueryAssembleService getQueryAssembleService() {
		return queryAssembleService;
	}

	public void setQueryAssembleService(IQueryAssembleService queryAssembleService) {
		this.queryAssembleService = queryAssembleService;
	}

	public String getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}

	public String[] getHead() {
		return head;
	}

	public void setHead(String[] head) {
		this.head = head;
	}

	public int getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}

	public int getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(int limitNumber) {
		this.limitNumber = limitNumber;
	}

	public String getSqlid() {
		return sqlid;
	}

	public void setSqlid(String sqlid) {
		this.sqlid = sqlid;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCharCode() {
		return charCode;
	}

	public void setCharCode(String charCode) {
		this.charCode = charCode;
	}

	public int getExportsel() {
		return exportsel;
	}

	public void setExportsel(int exportsel) {
		this.exportsel = exportsel;
	}

	public String getUserParentOrgName() {
		return userParentOrgName;
	}

	public void setUserParentOrgName(String userParentOrgName) {
		this.userParentOrgName = userParentOrgName;
	}
}


