package com.hoau.hoaureport.module.configreport.server.util;

import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.hoau.hoaureport.module.configreport.shared.domain.ExcelExportResultEntity;
/**
 * 
 * ClassName: ExcelUtil 
 * @author 刘镇松
 * @date 2016年8月24日
 * @version V1.0
 */
@SuppressWarnings("hiding")
public class ExcelUtil<T> {
	private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);

	public static void main(String[] args) throws Exception {
//		String aa="C:/Users/123/workspace/miser/miser-biz/miser-biz-web/src/main/webapp/excelExport/2016-03-23-12-59-33-763.xlsx";
//		List<Map<String,String>> list=readToListByFile(aa,16,2);
//		System.out.println(list.size());
//		List<String> alist=new ArrayList<String>();
//		alist.add("userName");
//		alist.add("userPwd");
//		alist.add("age");
//		alist.add("sex");
//		List<String> blist=new ArrayList<String>();
//		blist.add("姓名");
//		blist.add("密码");
//		blist.add("年龄");
//		blist.add("性别");
//		List<User> datalist=new ArrayList<User>();
//		User user=new User();
//		user.setUserName("zs");
//		user.setUserPwd("123");
//		user.setAge(18);
//		user.setSex("男");
//		datalist.add(user);
//		User user1=new User();
//		user1.setUserName("ls");
//		user1.setUserPwd("456");
//		user1.setAge(23);
//		user1.setSex("女");
//		datalist.add(user1);
//		String name=new ExcelUtil<User>().exportCVS(blist,alist,datalist);
//		System.out.println(name);
	}
	public String exportCVS(List<String> titleNames, List<String> paramNames,
			Collection<T> dataset) throws IOException {
		// 设置上传文件目录
		String ExportPath = "";
		BufferedWriter csvFileOutputStream = null;
		try{
			ExportPath=ServletActionContext.getServletContext().getRealPath("/cvsExport");
		}catch(NullPointerException e){
			//本地测试
			ExportPath="D:/cvsExport";
		}
		File file = new File(ExportPath);
		if (!file.isDirectory()) {
			file.mkdir();
		}
		String ExportFileName=getNewFileName("csv");
		String exportExcelPath = ExportPath + "/" + ExportFileName;
		// 设置目标文件
		  csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
				  exportExcelPath), "UTF-8"), 1024);
		// 声明一个工作薄
		try {
			// 首先检查数据看是否是正确的
			Iterator<T> iterator = dataset.iterator();
			if (dataset == null || !iterator.hasNext() || csvFileOutputStream == null) {
				throw new Exception("传入的数据不对！");
			}
			// 取得实际泛型类
			T tObject = iterator.next();
			@SuppressWarnings("unchecked")
			Class<T> clazz = (Class<T>) tObject.getClass();
			// 导出的字段的get方法
			List<Method> methodObj = new ArrayList<Method>();
			// 遍历整个filed
			for (int i = 0; i < paramNames.size(); i++) {
				// 添加到需要导出的字段的方法
				String fieldname = paramNames.get(i);
				String getMethodName = "get"
						+ fieldname.substring(0, 1).toUpperCase()
						+ fieldname.substring(1);
				Method getMethod = clazz.getMethod(getMethodName,
						new Class[] {});
				methodObj.add(getMethod);
			}
			log.info("-------------------生成表头");
			// 产生表格标题行
			for (int i = 0; i < titleNames.size(); i++) {
				if(i!=0){
					csvFileOutputStream.write(",");
				}
				csvFileOutputStream.write("\"" + (String) titleNames.get(i) != null ? (String) titleNames.get(i) : "" + "\"");
			}
			csvFileOutputStream.newLine();
			log.info("-------------------生成行");
			// 循环整个集合
			iterator = dataset.iterator();
			while (iterator.hasNext()) {
				T t = (T) iterator.next();
				for (int k = 0; k < methodObj.size(); k++) {
					Method getMethod = methodObj.get(k);
					Object value = getMethod.invoke(t, new Object[] {});
					String textValue = getValue(value);
					if(k!=0){
						csvFileOutputStream.write(",");
					}
					csvFileOutputStream.write("\"" + (String) textValue != null ? (String)textValue : "" + "\"");
				}
				 csvFileOutputStream.newLine();
			}
			log.info("-------------------开始写文件");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			csvFileOutputStream.flush();
			csvFileOutputStream.close();
		}
		log.info("-------------------excel生成完成");
		return "/cvsExport/"+ExportFileName;
	}
	/**
	 * 
	 * @Description: jdbc导出
	 * @param @param titleNames 表头
	 * @param @param indexs	列对应sql的列下标
	 * @param @param sql	需要执行的sql
	 * @param @param params	sql参数没有传递null
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author mao.wang@newHoau.com.cn
	 * @date 2016年4月7日
	 */
	public ExcelExportResultEntity sqlTofile(List<String> titleNames, String sql, List<Object> params) {
		int totalCount=0;
		JdbcTemplate jdbcTemplate = (JdbcTemplate)SpringContextUtil.getBean("jdbcTemplate");
		// 设置上传文件目录
		String ExportPath = "";
		try{
			ExportPath=ServletActionContext.getServletContext().getRealPath("/excelExport");
		}catch(NullPointerException e){
			//本地测试
			ExportPath="D:/excelExport";
		}
		File file = new File(ExportPath);
		if (!file.isDirectory()) {
			file.mkdir();
		}
		String ExportFileName=getNewFileName("xlsx");
		String exportExcelPath = ExportPath + "/" + ExportFileName;
		// 设置目标文件
		OutputStream out = null;
		try {
			out = new FileOutputStream(exportExcelPath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 声明一个工作薄
		try {
			// 首先检查数据看是否是正确的
			// 取得实际泛型类
			SXSSFWorkbook workbook = new SXSSFWorkbook(200);
			List<Sheet> sheets = new ArrayList<Sheet>(3);
			Sheet sheet = workbook.createSheet();
			sheets.add(sheet);
			// 设置表格默认列宽度为20个字节
			sheet.setDefaultColumnWidth(20);
			// 生成一个样式
			CellStyle style = workbook.createCellStyle();
			// 设置标题样式
			style = ExcelUtil.setHeadStyle(workbook, style);
			log.info("-------------------生成表头");
			// 产生表格标题行
			Row row = sheet.createRow(0);
			for (int i = 0; i < titleNames.size(); i++) {
				Cell cell = row.createCell(i);
				cell.setCellStyle(style);
				XSSFRichTextString text = new XSSFRichTextString(
						titleNames.get(i));
				cell.setCellValue(text);
			}
			log.info("-------------------生成行");
			SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, params.toArray());
			int index=1;
			int sheetIndex = 0;
			while(rs.next()){
				totalCount++;
				if (index == SpreadsheetVersion.EXCEL2007.getLastRowIndex() + 1) {
					index = 0;
					sheetIndex ++;
					sheets.add(workbook.createSheet());
				}
				row = sheets.get(sheetIndex).createRow(index++);
				for (int k = 0; k < titleNames.size(); k++) {
					Cell cell = row.createCell(k);
					//默认按列头顺序读取
					cell.setCellValue(rs.getString(k+1));
				}
			}
			log.info("-------------------开始写文件");
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log.info("-------------------excel生成完成");
		ExcelExportResultEntity entity = new ExcelExportResultEntity();
		entity.setFilePath("/excelExport/" + ExportFileName);
		entity.setRecordCount(totalCount);
		return entity;
	}
	//
	public static List<Map<String,String>> readToListByFile(String path,
			int minColumns, int skipRows) throws Exception {
		path=path.replaceAll("\\\\", "/");
		String uploadPath=path.substring(0,path.lastIndexOf("/"));
		String filename=path.substring(path.lastIndexOf("/")+1);
		String prefix = filename.substring(filename.lastIndexOf(".") + 1);
		File file = new File(uploadPath,filename);
		List<String> filePath = new ArrayList<String>();
		// 如果文件为压缩文件则进行解压
		if (prefix.equals("zip")) {
			filePath = unzip(file.getPath(), uploadPath);
		} else {
			filePath.add(file.getPath());
		}
		List<Map<String,String>> lands = new ArrayList<Map<String,String>>();
		for (String str : filePath) {
			// 判断为xlsx格式excel则进行sax解析否则进入poi解析
			if (str.toLowerCase().endsWith(".xlsx")) {
				log.info("------------sax开始读取excel文件");
				List<String[]> list = XLSXCovertCSVReader.readerExcel(str,
						minColumns);
				log.info("------------sax读取excel文件完成,共" + list.size() + "条数据");
				// 遍历第一行数据，为表头
				List<String> keys = new ArrayList<String>();
				for (String cell : list.get(0)) {
					keys.add(cell);
				}
				/**
				 * 由于SAX返回为List<String[]> 类型，在此将其转为List<T>
				 */
				int index = 1;
				for (String[] cellone : list) {
					if (index >= skipRows) {
						Map<String, String> map = new HashMap<String, String>();
						for (int i = 0; i < cellone.length; i++) {
							//map.put(keys.get(i), cellone[i]);
							map.put(i+"", cellone[i]);
						}
						lands.add(map);
					}
					index++;
				}
			} else {
				log.info("------------poiUserModel 开始读取excel文件");
				log.info("------------开始读取excel文件");
				Workbook wb = loadWorkbook(str);
				log.info("------------excel文件读取完成");
				Sheet sheet = wb.getSheetAt(0);
				log.info("------------总行数" + sheet.getLastRowNum());
				Iterator<Row> rows = sheet.rowIterator();
				int index = 1;
				List<String> keys = new ArrayList<String>();
				while (rows.hasNext()) {
					Row row = rows.next();
					if (index == 1) {
						int num = row.getLastCellNum();
						for (int i = 0; i < num; i++) {
							Cell cell = row.getCell(i);
							if (cell != null) {
								String value = getStringCellValue(cell);
								keys.add(value);
							}
						}
					}
					// 决定从哪一行开始提取
					if (index >= skipRows) { 
						Map<String, String> map = new HashMap<String, String>();
						int num = row.getLastCellNum();
						for (int i = 0; i < num; i++) {
							Cell cell = row.getCell(i);
							if (cell != null) {
								//map.put(keys.get(i), getStringCellValue(cell));
								map.put(i+"", getStringCellValue(cell));
							}
						}
							lands.add(map);
							if (index % 1000 == 0) {
								log.info("------------excel文件读取" + index);
							}
						
					}
					index++;
				}
			}
		}
		// 删除上传文件
		/*file.delete();
		for (String files : filePath) {
			new File(files).delete();
		}*/
		log.info("------------excel文件读取共" + lands.size());
		return lands;
	}
	/**
	 * 
	 * @Description: 传入excel文件转为对应实体
	 * @param @param xlsFile 传入的文件
	 * @param @param filename 传入文件名称
	 * @param @param minColumns	传入的excel列数
	 * @param @param skipRows	从第几行还是读取
	 * @param @param clazz		返回集合泛型
	 * @param @return
	 * @param @throws Exception   
	 * @return List<T> 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	public static <T> List<T> readToList(File xlsFile, String filename,
			int minColumns, int skipRows, Class<T> clazz) throws Exception {
		List<T> lands = new ArrayList<T>();
		String prefix = filename.substring(filename.lastIndexOf(".") + 1);
		InputStream is = new FileInputStream(xlsFile);
		// 设置上传文件目录
		String uploadPath = ServletActionContext.getServletContext()
				.getRealPath("/excelImport");
		File file = new File(uploadPath);
		if (!file.isDirectory()) {
			file.mkdir();
		}
		// 设置目标文件
		File toFile = new File(uploadPath, getNewFileName(prefix));
		// 创建一个输出流
		OutputStream os = new FileOutputStream(toFile);
		// 设置缓存
		byte[] buffer = new byte[1024];
		int length = 0;
		// 读取myFile文件输出到toFile文件中
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		// 关闭输入流
		is.close();
		// 关闭输出流
		os.close();
		List<String> filePath = new ArrayList<String>();
		// 如果文件为压缩文件则进行解压
		if (prefix.equals("zip")) {
			filePath = unzip(toFile.getPath(), uploadPath);
		} else {
			filePath.add(toFile.getPath());
		}
		for (String str : filePath) {
			// 判断为xlsx格式excel则进行sax解析否则进入poi解析
			if (str.toLowerCase().endsWith(".xlsx")) {
				log.info("------------sax开始读取excel文件");
				List<String[]> list = XLSXCovertCSVReader.readerExcel(str,
						minColumns);
				log.info("------------sax读取excel文件完成,共" + list.size() + "条数据");
				// 遍历第一行数据，为表头
				List<String> keys = new ArrayList<String>();
				for (String cell : list.get(0)) {
					keys.add(cell);
				}
				/**
				 * 由于SAX返回为List<String[]> 类型，在此将其转为List<T>
				 */
				int index = 1;
				for (String[] cellone : list) {
					if (index >= skipRows) {
						Map<String, String> map = new HashMap<String, String>();
						for (int i = 0; i < cellone.length; i++) {
							map.put(keys.get(i), cellone[i]);
						}
						T land = newTclass(clazz);
						ExcelUtil.setFieldValue(land, map);
						lands.add(land);
					}
					index++;
				}
			} else {
				log.info("------------poiUserModel 开始读取excel文件");
				log.info("------------开始读取excel文件");
				Workbook wb = loadWorkbook(str);
				log.info("------------excel文件读取完成");
				Sheet sheet = wb.getSheetAt(0);
				log.info("------------总行数" + sheet.getLastRowNum());
				Iterator<Row> rows = sheet.rowIterator();
				int index = 1;
				List<String> keys = new ArrayList<String>();
				while (rows.hasNext()) {
					Row row = rows.next();
					if (index == 1) {
						int num = row.getLastCellNum();
						for (int i = 0; i < num; i++) {
							Cell cell = row.getCell(i);
							if (cell != null) {
								String value = getStringCellValue(cell);
								keys.add(value);
							}
						}
					}
					// 决定从哪一行开始提取
					if (index >= skipRows) { 
						Map<String, String> map = new HashMap<String, String>();
						int num = row.getLastCellNum();
						for (int i = 0; i < num; i++) {
							Cell cell = row.getCell(i);
							if (cell != null) {
								map.put(keys.get(i), getStringCellValue(cell));
							}
						}
						try {
							T land = newTclass(clazz);
							ExcelUtil.setFieldValue(land, map);
							lands.add(land);
							if (index % 1000 == 0) {
								log.info("------------excel文件读取" + index);
							}
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					index++;
				}
			}
		}
		// 删除上传文件
		toFile.delete();
		for (String files : filePath) {
			new File(files).delete();
		}
		log.info("------------excel文件读取共" + lands.size());
		return lands;
	}

	/**
	 * 
	 * @Description: 将集合生成excel并返回文件路径
	 * @param @param titleNames 表头数组
	 * @param @param paramNames	表头对应实体属性数组
	 * @param @param dataset	对应实体集合
	 * @param @return
	 * @param @throws IOException   
	 * @return String 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	@SuppressWarnings({ "unchecked" })
	public String exportExcel(List<String> titleNames, List<String> paramNames,
			Collection<T> dataset) throws IOException {
		// 设置上传文件目录
		String ExportPath = "";
		try{
			ExportPath=ServletActionContext.getServletContext().getRealPath("/excelExport");
		}catch(NullPointerException e){
			//本地测试
			ExportPath="D:/excelExport";
		}
		File file = new File(ExportPath);
		if (!file.isDirectory()) {
			file.mkdir();
		}
		String ExportFileName=getNewFileName("xlsx");
		String exportExcelPath = ExportPath + "/" + ExportFileName;
		// 设置目标文件
		OutputStream out = new FileOutputStream(exportExcelPath);
		// 声明一个工作薄
		try {
			// 首先检查数据看是否是正确的
			Iterator<T> iterator = dataset.iterator();
			if (dataset == null || !iterator.hasNext() || out == null) {
				throw new Exception("传入的数据不对！");
			}
			// 取得实际泛型类
			T tObject = iterator.next();
			Class<T> clazz = (Class<T>) tObject.getClass();
			SXSSFWorkbook workbook = new SXSSFWorkbook(200);
			Sheet sheet = workbook.createSheet();
			// 设置表格默认列宽度为20个字节
			sheet.setDefaultColumnWidth(20);
			// 生成一个样式
			CellStyle style = workbook.createCellStyle();
			// 设置标题样式
			style = ExcelUtil.setHeadStyle(workbook, style);
			// 导出的字段的get方法
			List<Method> methodObj = new ArrayList<Method>();
			// 遍历整个filed
			for (int i = 0; i < paramNames.size(); i++) {
				// 添加到需要导出的字段的方法
				String fieldname = paramNames.get(i);
		        PropertyDescriptor pd = new PropertyDescriptor(fieldname, clazz);  
		        Method getMethod = pd.getReadMethod();//获得get方法 
				methodObj.add(getMethod);
			}
			log.info("-------------------生成表头");
			// 产生表格标题行
			Row row = sheet.createRow(0);
			for (int i = 0; i < titleNames.size(); i++) {
				Cell cell = row.createCell(i);
				cell.setCellStyle(style);
				XSSFRichTextString text = new XSSFRichTextString(
						titleNames.get(i));
				cell.setCellValue(text);
			}
			log.info("-------------------生成行");
			// 循环整个集合
			int index = 0;
			iterator = dataset.iterator();
			while (iterator.hasNext()) {
				// 从第二行开始写，第一行是标题
				index++;
				row = sheet.createRow(index);
				T t = (T) iterator.next();
				for (int k = 0; k < methodObj.size(); k++) {
					Cell cell = row.createCell(k);
					Method getMethod = methodObj.get(k);
					Object value = getMethod.invoke(t);
					String textValue = getValue(value);
					cell.setCellValue(textValue);
				}
			}
			log.info("-------------------开始写文件");
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		log.info("-------------------excel生成完成");
		return "/excelExport/"+ExportFileName;
	}
	
	/**
	 * 
	 * @Description: 将集合生成模板excel并返回文件路径
	 * @param @param titleNames 表头数组
	 * @param @return
	 * @param @throws IOException   
	 * @return String 
	 * @throws
	 * @author 陈启勇
	 * @date 2016年02月29日
	 */
	public String exportExcel(List<String> titleNames) throws IOException {
		// 设置上传文件目录
		String ExportPath = ServletActionContext.getServletContext()
				.getRealPath("/excelExport");
		File file = new File(ExportPath);
		if (!file.isDirectory()) {
			file.mkdir();
		}
		String ExportFileName=getNewFileName("xlsx");
		String exportExcelPath = ExportPath + "/" + ExportFileName;
		// 设置目标文件
		OutputStream out = new FileOutputStream(exportExcelPath);
		// 声明一个工作薄
		try {
			// 取得实际泛型类
			SXSSFWorkbook workbook = new SXSSFWorkbook();
			Sheet sheet = workbook.createSheet("sheet1");
			// 设置表格默认列宽度为20个字节
			sheet.setDefaultColumnWidth(20);
			// 生成一个样式
			CellStyle style = workbook.createCellStyle();
			// 设置标题样式
			style = ExcelUtil.setHeadStyle(workbook, style);
			log.info("-------------------生成表头");
			// 产生表格标题行
			Row row = sheet.createRow(0);
			for (int i = 0; i < titleNames.size(); i++) {
				Cell cell = row.createCell(i);
				cell.setCellStyle(style);
				XSSFRichTextString text = new XSSFRichTextString(
						titleNames.get(i));
				cell.setCellValue(text);
			}
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		log.info("-------------------excel生成完成");
		return "/excelExport/"+ExportFileName;
	}

	/**
	 * 
	 * @Description: 将文件转为传入类型集合
	 * @param @param file
	 * @param @param clazz
	 * @param @return
	 * @param @throws Exception   
	 * @return List<T> 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	public static <T> List<T> excelToClass(File file, Class<T> clazz)
			throws Exception {
		List<T> lands = new ArrayList<T>();
		try {
			Workbook wb = loadWorkbook(file.getName());
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			int index = 0;
			List<String> keys = new ArrayList<String>();
			while (rows.hasNext()) {
				Row row = rows.next();
				if (index == 0) {
					int num = row.getLastCellNum();
					for (int i = 0; i < num; i++) {
						Cell cell = row.getCell(i);
						if (cell != null) {
							String value = getStringCellValue(cell);
							keys.add(value);
						}
					}
				}
				if (index >= 1) { // 决定从哪一行开始提取,这里从第一行
					Map<String, String> map = new HashMap<String, String>();
					int num = row.getLastCellNum();
					for (int i = 0; i < num; i++) {
						Cell cell = row.getCell(i);
						if (cell != null) {
							map.put(keys.get(i), getStringCellValue(cell));
						}
					}
					try {
						T land = newTclass(clazz);
						ExcelUtil.setFieldValue(land, map);
						lands.add(land);
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				index++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lands;
	}

	/**
	 * 
	 * @Description:读取Excel文件，支持2003与2007格式
	 * @param @param fileName
	 * @param @return
	 * @param @throws Exception   
	 * @return Workbook 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	public static Workbook loadWorkbook(String fileName) throws Exception {
		if (null == fileName)
			return null;

		Workbook wb = null;
		if (fileName.toLowerCase().endsWith(".xls")) {
			try {
				InputStream in = new FileInputStream(fileName);
				POIFSFileSystem fs = new POIFSFileSystem(in);
				wb = new HSSFWorkbook(fs);
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (fileName.toLowerCase().endsWith(".xlsx")) {
			try {
				InputStream in = new FileInputStream(fileName);
				wb = new XSSFWorkbook(new BufferedInputStream(in));
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new Exception("不是一个有效的Excel文件");
		}
		return wb;
	}

	/**
	 * 
	 * @Description: set属性的值到Bean
	 * @param @param bean
	 * @param @param valMap   
	 * @return void 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	public static void setFieldValue(Object bean, Map<String, String> valMap) {
		Class<?> cls = bean.getClass();
		// 取出bean里的所有方法
		Method[] methods = cls.getDeclaredMethods();
		Field[] fields = cls.getDeclaredFields();

		for (Field field : fields) {
			try {
				String fieldSetName = parSetName(field.getName());
				if (!checkSetMet(methods, fieldSetName)) {
					continue;
				}
				Method fieldSetMet = cls.getMethod(fieldSetName,
						field.getType());
				// String fieldKeyName = parKeyName(field.getName());
				String fieldKeyName = field.getName();
				String value = valMap.get(fieldKeyName);
				if (null != value && !"".equals(value) && !"null".equals(value)) {
					String fieldType = field.getType().getSimpleName();
					if ("String".equals(fieldType)) {
						fieldSetMet.invoke(bean, value);
					} else if ("Date".equals(fieldType)) {
						Date temp = parseDate(value);
						fieldSetMet.invoke(bean, temp);
					} else if ("Integer".equals(fieldType)
							|| "int".equals(fieldType)) {
						if (value.contains(".")) {
							value = value.substring(0, value.indexOf("."));
						}
						Integer intval = Integer.parseInt(value);
						fieldSetMet.invoke(bean, intval);
					} else if ("Long".equalsIgnoreCase(fieldType)) {
						Long temp = Long.parseLong(value);
						fieldSetMet.invoke(bean, temp);
					} else if ("Double".equalsIgnoreCase(fieldType)) {
						Double temp = Double.parseDouble(value);
						fieldSetMet.invoke(bean, temp);
					} else if ("Boolean".equalsIgnoreCase(fieldType)) {
						Boolean temp = Boolean.parseBoolean(value);
						fieldSetMet.invoke(bean, temp);
					} else {
						log.info("not supper type" + fieldType);
					}
				}
			} catch (Exception e) {
				continue;
			}
		}
	}
	/**
	 * 
	 * @Description: 设置导出列样式
	 * @param @param workbook
	 * @param @param style
	 * @param @return   
	 * @return XSSFCellStyle 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	public static CellStyle setHeadStyle(SXSSFWorkbook workbook,
			CellStyle style) {
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成字体
		Font font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样样式
		style.setFont(font);
		return style;

	}
	/**
	 * 
	 * @Description: 设置导出body样式
	 * @param @param workbook
	 * @param @param style
	 * @param @return   
	 * @return HSSFCellStyle 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	public static HSSFCellStyle setbodyStyle(HSSFWorkbook workbook,
			HSSFCellStyle style) {
		style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样样式
		style.setFont(font);
		return style;
	}

	/**
	 * 
	 * @Description: getValue方法-cell值处理
	 * @param @param value
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	public String getValue(Object value) {
		String textValue = "";
		if (value == null) {
			return textValue;
		}
		if (value instanceof Boolean) {
			boolean bValue = (Boolean) value;
			textValue = "是";
			if (!bValue) {
				textValue = "否";
			}
		} else if (value instanceof Date) {
			Date date = (Date) value;
			textValue = fmtDate(date);

		} else {
			textValue = value.toString();
		}
		return textValue;
	}

	/**
	 * 
	 * @Description: 格式化string为Date
	 * @param @param datestr
	 * @param @return   
	 * @return Date 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	public static Date parseDate(String datestr) {
		if (null == datestr || "".equals(datestr)) {
			return null;
		}
		try {
			String fmtstr = null;
			if (datestr.indexOf(':') > 0) {
				fmtstr = "yyyy-MM-dd HH:mm:ss";
			} else {
				fmtstr = "yyyy-MM-dd";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);
			return sdf.parse(datestr);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Description: 日期转化为String
	 * @param @param date
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	public static String fmtDate(Date date) {
		if (null == date) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
					Locale.US);
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Description: 判断是否存在某属性的 set方法
	 * @param @param methods
	 * @param @param fieldSetMet
	 * @param @return   
	 * @return boolean 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	public static boolean checkSetMet(Method[] methods, String fieldSetMet) {
		for (Method met : methods) {
			if (fieldSetMet.equals(met.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @Description: 判断是否存在某属性的 get方法
	 * @param @param methods
	 * @param @param fieldGetMet
	 * @param @return   
	 * @return boolean 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	public static boolean checkGetMet(Method[] methods, String fieldGetMet) {
		for (Method met : methods) {
			if (fieldGetMet.equals(met.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @Description: 拼接某属性的 get方法
	 * @param @param fieldName
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	public static String parGetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		int startIndex = 0;
		if (fieldName.charAt(0) == '_')
			startIndex = 1;
		return "get"
				+ fieldName.substring(startIndex, startIndex + 1).toUpperCase()
				+ fieldName.substring(startIndex + 1);
	}

	/**
	 * 
	 * @Description: 拼接在某属性的 set方法
	 * @param @param fieldName
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	public static String parSetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		int startIndex = 0;
		if (fieldName.charAt(0) == '_')
			startIndex = 1;
		return "set"
				+ fieldName.substring(startIndex, startIndex + 1).toUpperCase()
				+ fieldName.substring(startIndex + 1);
	}

	/**
	 * 
	 * @Description: 获取存储的键名称（调用parGetName）
	 * @param @param fieldName
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	public static String parKeyName(String fieldName) {
		String fieldGetName = parGetName(fieldName);
		if (fieldGetName != null && fieldGetName.trim() != ""
				&& fieldGetName.length() > 3) {
			return fieldGetName.substring(3);
		}
		return fieldGetName;
	}
	/**
	 * 
	 * @Description:  获取单元格数据内容为字符串类型的数据
	 * @param @param cell
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	private static String getStringCellValue(Cell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		return strCell;
	}
	/**
	 * 
	 * @Description: 返回传入类型实例
	 * @param @param clazz
	 * @param @return
	 * @param @throws InstantiationException
	 * @param @throws IllegalAccessException   
	 * @return T 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	private static <T> T newTclass(Class<T> clazz)
			throws InstantiationException, IllegalAccessException {
		T a = clazz.newInstance();
		return a;
	}
	/**
	 * 
	 * @Description: 生成excel文件名称
	 * @param @param prefix
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	public static String getNewFileName(String prefix) {
		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS")
				.format(new Date()) + "." + prefix;
	}
	/**
	 * 
	 * @Description: 将zip文件解压并返回excel文件地址数组
	 * @param @param zipFileName
	 * @param @param destDir
	 * @param @return
	 * @param @throws Exception   
	 * @return List<String> 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月24日
	 */
	public static List<String> unzip(String zipFileName, String destDir)
			throws Exception {
		List<String> list = new ArrayList<String>();
		try {
			ZipFile zipFile = new ZipFile(zipFileName, "gbk");
			Enumeration<?> e = zipFile.getEntries();
			ZipEntry zipEntry = null;
			File fD = new File(destDir);
			if (!fD.exists()) {
				fD.mkdir();
			}
			while (e.hasMoreElements()) {
				boolean flag = true;
				zipEntry = (ZipEntry) e.nextElement();
				// 获取解压文件夹文件名
				String entryName = zipEntry.getName();
				if (entryName.toLowerCase().endsWith(".xls")) {
					entryName = getNewFileName("xls");
				} else if (entryName.toLowerCase().endsWith(".xlsx")) {
					entryName = getNewFileName("xlsx");
				} else {
					flag = false;
				}
				// 判断不是excel格式文件不执行解压方法
				if (flag) {
					list.add(destDir + "/" + entryName);
					String names[] = entryName.split("/");
					int length = names.length;
					String path = destDir;
					for (int v = 0; v < length; v++) {
						if (v < length - 1) {
							path += "/" + names[v];
							new File(path).mkdir();
						} else {
							if (entryName.endsWith("/")) {
								new File(destDir + "/" + entryName).mkdir();
							} else {
								InputStream in = zipFile
										.getInputStream(zipEntry);
								OutputStream os = new FileOutputStream(
										new File(destDir + "/" + entryName));
								byte[] buf = new byte[1024];
								int len;
								while ((len = in.read(buf)) > 0) {
									os.write(buf, 0, len);
								}
								in.close();
								os.close();
							}
						}
					}
				}
			}
			zipFile.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	/**
	 * 
	 * @Description: 压缩文件(高效)
	 * @param @param filepath
	 * @param @param zippath   
	 * @return void 
	 * @throws
	 * @author 王茂
	 * @date 2015年12月30日
	 */
	public static boolean ZipFile(String filepath ,String zippath) {
	    try {
	    	
	        File file = new File(filepath);
	        File zipFile = new File(zippath);
	        InputStream input = new FileInputStream(file);
	        BufferedInputStream in = new BufferedInputStream(input);
	        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
	        out.putNextEntry(new ZipEntry(file.getName()));
	        int temp = 0;
	        while((temp = in.read()) != -1){
	        	out.write(temp);
	        }
	        in.close();
	        out.close();
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
