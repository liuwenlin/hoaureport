package com.hoau.hoaureport.module.configreport.server.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ctc.wstx.util.StringUtil;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hoaureport.module.common.shared.util.FolderUtils;
import com.hoau.hoaureport.module.configreport.server.service.IWayBillZipService;
import com.hoau.hoaureport.module.configreport.server.util.ConfigureReportUtil;
import com.hoau.hoaureport.module.configreport.shared.domain.ExcelReturn;
import com.hoau.hoaureport.module.configreport.shared.domain.ExportEntity;
import com.hoau.hoaureport.module.configreport.shared.domain.QuerySql;
import com.hoau.hoaureport.module.configreport.shared.domain.WayBillZipEntity;
/**
 * 运单物流时间报表
 * @author 273503
 *
 */
@Controller
@Scope("prototype")
public class WayBillZipAction extends AbstractAction {

	private static final long serialVersionUID = -743914414051965303L;
	private Logger log = Logger.getLogger(this.getClass());
	private WayBillZipEntity zipEntity;
	private List<WayBillZipEntity> zipList;
	private String id;
	
	@Autowired
	private IWayBillZipService wayBillZipService;
	public String wayBillZipQuery(){
		try {
			this.totalCount = wayBillZipService.queryCountByEntity(zipEntity);
			zipList =wayBillZipService.queryByEntity(zipEntity,start,limit);
		} catch (Exception e) {
			log.error(e);
			return this.returnSuccess("系统查询异常");
		}
		return this.returnSuccess();
	}
	
	/**
	 * 下载
	 * @return
	 */
	public String downLoad(){
		
		
		try {
			
			if(id==null || "".equals(id.trim())){
				return this.returnSuccess();
			}
			ExportType(true,"正在导出运单物流时间报表CSV文件.....");
			ExcelReturn excelReturn = new ExcelReturn();
			
			WayBillZipEntity zipEntity = wayBillZipService.queryEntityById(id);
			
			
			
			excelReturn.setFileName(zipEntity.getZipName());
	        StringBuffer sb = new StringBuffer();
	        sb.append("attachment;  filename=").append(excelReturn.getFileName());
			HttpServletResponse response =ServletActionContext.getResponse();
			response.setHeader("Expires", "0");
	        response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
	        response.setHeader("Pragma", "public");
	        response.setContentType("application/x-msdownload;charset=UTF-8");
	        response.setHeader("Content-Disposition", new String( sb.toString().getBytes(), "UTF-8"));
	          
	        
	         //将此文件流写入到response输出流中
	         File file = new File(zipEntity.getZipPath());
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

	
	
	private void ExportType(boolean type,String msg){
		HttpSession session =ServletActionContext.getRequest().getSession();
		ExportEntity info = new ExportEntity(type,new Date().getTime(),msg);
		session.setAttribute(ConfigureReportUtil.CSV_KEY_REPORT_EXPORT, info);
	}
	
	
	
	
	
	
	public WayBillZipEntity getZipEntity() {
		return zipEntity;
	}
	public void setZipEntity(WayBillZipEntity zipEntity) {
		this.zipEntity = zipEntity;
	}
	public List<WayBillZipEntity> getZipList() {
		return zipList;
	}
	public void setZipList(List<WayBillZipEntity> zipList) {
		this.zipList = zipList;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
	

}
