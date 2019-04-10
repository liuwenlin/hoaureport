package com.hoau.hoaureport.module.common.shared.util;


import com.hoau.hbdp.framework.exception.BusinessException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

public class POIExcelUtil {
	public static final String FILE_EXTENSION_XLS = "xls";
	public static final String FILE_EXTENSION_XLSX = "xlsx";
	public static final int  FILE_DEFAUT_SIZE =3000;
	
	private static  Map<String,String> DATA_FORMATS =new HashMap<String, String>();
	
	static{
		DATA_FORMATS.put("0", "General");//常规
		DATA_FORMATS.put("1", "0");//数值整数（默认）
		DATA_FORMATS.put("2", "0.00");//数值2位小数 
		DATA_FORMATS.put("2_1", "0.0");//数值1位小数
		DATA_FORMATS.put("2_3", "0.000");//数值3位小数
		DATA_FORMATS.put("2_4", "0.0000");//数值4位小数
		DATA_FORMATS.put("2_5", "0.00000");//数值5位小数
		DATA_FORMATS.put("2_6", "0.000000");//数值6位小数
		DATA_FORMATS.put("2_8", "0.00000000");//数值8位小数
		DATA_FORMATS.put("3", "#,##0");//货币
		DATA_FORMATS.put("4", "#,##0.00");//货币2位小数
		DATA_FORMATS.put("4_1", "#,##0.0");//货币1位小数
		DATA_FORMATS.put("4_3", "#,##0.000");//货币3位小数
		DATA_FORMATS.put("4_4", "#,##0.0000");//货币4位小数
		DATA_FORMATS.put("4_5", "#,##0.00000");//货币5位小数
		DATA_FORMATS.put("4_6", "#,##0.000000");//货币6位小数
		DATA_FORMATS.put("4_8", "#,##0.00000000");//货币8位小数
		DATA_FORMATS.put("5", "\"$\"#,##0_);(\"$\"#,##0)");
		DATA_FORMATS.put("6", "\"$\"#,##0_);[Red](\"$\"#,##0)");
		DATA_FORMATS.put("7", "\"$\"#,##0.00_);(\"$\"#,##0.00)");
		DATA_FORMATS.put("8", "\"$\"#,##0.00_);[Red](\"$\"#,##0.00)");
		DATA_FORMATS.put("9", "0%");
		DATA_FORMATS.put("10", "0.00%");
		DATA_FORMATS.put("10_1", "0.0%");
		DATA_FORMATS.put("10_3", "0.000%");
		DATA_FORMATS.put("10_4", "0.0000%");
		DATA_FORMATS.put("10_6", "0.000000%");
		DATA_FORMATS.put("11", "0.00E+00");
		DATA_FORMATS.put("12", "# ?/?");
		DATA_FORMATS.put("13", "# ??/??");
		DATA_FORMATS.put("14", "m/d/yy");
		DATA_FORMATS.put("15", "d-mmm-yy");
		DATA_FORMATS.put("16", "d-mmm");
		DATA_FORMATS.put("17", "mmm-yy");
		DATA_FORMATS.put("18", "h:mm AM/PM");
		DATA_FORMATS.put("19", "h:mm:ss AM/PM");
		DATA_FORMATS.put("20", "h:mm");
		DATA_FORMATS.put("21", "h:mm:ss");
		DATA_FORMATS.put("22", "m/d/yy h:mm");
		DATA_FORMATS.put("23", "reserved-0x" + Integer.toHexString(23));
		DATA_FORMATS.put("37", "#,##0_);(#,##0)");
		DATA_FORMATS.put("38", "#,##0_);[Red](#,##0)");
		DATA_FORMATS.put("39", "#,##0.00_);(#,##0.00)");
		DATA_FORMATS.put("40", "#,##0.00_);[Red](#,##0.00)");
		DATA_FORMATS.put("41","_(\"$\"* #,##0_);_(\"$\"* (#,##0);_(\"$\"* \"-\"_);_(@_)");
		DATA_FORMATS.put("42","_(* #,##0_);_(* (#,##0);_(* \"-\"_);_(@_)");
		DATA_FORMATS.put("43","_(\"$\"* #,##0.00_);_(\"$\"* (#,##0.00);_(\"$\"* \"-\"??_);_(@_)");
		DATA_FORMATS.put("44", "_(* #,##0.00_);_(* (#,##0.00);_(* \"-\"??_);_(@_)");
		DATA_FORMATS.put("45", "mm:ss");
		DATA_FORMATS.put("46", "[h]:mm:ss");
		DATA_FORMATS.put("47", "mm:ss.0");
		DATA_FORMATS.put("48", "##0.0E+0");
		DATA_FORMATS.put("49", "@");
	}
	

	public static SimpleDateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static final Log logger = LogFactory.getLog(POIExcelUtil.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> boolean excelExport(Map<String, String> maps,
			List<T> list, File file){
		return excelExport(maps, list, file, FILE_DEFAUT_SIZE);
	}
	
	/**
	 * 
	 * @param maps
	 *            <String,String> maps 属性表，成员属性age为KEY，中文名称为VALUE
	 * @param list
	 *            <T> list 需要导出的数据列表对象
	 * @param file
	 *            file 指定输出文件位置，只能导出excel2003以上版本
	 * 
	 * @return true 导出成功 false 导出失败
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> boolean excelExport(Map<String, String> maps,
			List<T> list, File file,int maxSize) {
		if(list.size() > maxSize){
			throw new POIExcelTooManyException("数据单次最多只能导出"+maxSize+"条");
		}
		try {
			Workbook wb = null;
			String filename = file.getName();
			String type = filename.substring(filename.lastIndexOf(".") + 1)
					.toLowerCase();
			if (type.equals(FILE_EXTENSION_XLS)) {
				wb = new HSSFWorkbook();
			}
			if (type.equals(FILE_EXTENSION_XLSX)) {
				wb = new XSSFWorkbook();
			}
			CreationHelper createHelper = wb.getCreationHelper();
			Sheet sheet = wb.createSheet("sheet1");
			Set<String> sets = maps.keySet();
			Row row = sheet.createRow(0);
			int i = 0;
			// 定义表头
			for (Iterator<String> it = sets.iterator(); it.hasNext();) {
				String key = it.next();
				Cell cell = row.createCell(i++);
				cell.setCellValue(createHelper.createRichTextString(maps
						.get(key)));
			}
			// 填充表单内容
			logger.info("--------------------100%");
			float avg = list.size() / 20f;
			int count = 1;
			int size = list.size() > 5000 ? 5000: list.size();
			for (int j = 0; j < size;j++) {
				T p = list.get(j);
				Class classType = p.getClass();
				int index = 0;
				Row row1 = sheet.createRow(j + 1);
				for (Iterator<String> it = sets.iterator(); it.hasNext();) {
					String key = it.next();
					String firstLetter = key.substring(0, 1).toUpperCase();
					// 获得和属性对应的getXXX()方法的名字
					String getMethodName = "get" + firstLetter
							+ key.substring(1);
					// 获得和属性对应的getXXX()方法
					Method getMethod = classType.getMethod(getMethodName,
							new Class[] {});
					// 调用原对象的getXXX()方法
					Object value = getMethod.invoke(p, new Object[] {});
					if (value instanceof Date) {
						value = df.format(value);  
					}
					Cell cell = row1.createCell(index++);
					cell.setCellValue(value == null ? "" : value.toString());
				}
				if (j > avg * count) {
					count++;
					logger.info("I");
				}
				if (count == 20) {
					logger.info("I100%");
					count++;
				}
			}
			FileOutputStream fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (SecurityException e) {
			e.printStackTrace();
			return false;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public static <T> InputStream excelExport(Map<String, String> maps,
			List<T> list, String filename) {
		return excelExport(maps, list, filename, FILE_DEFAUT_SIZE);
	}
	/**
	 * 
	 * @param maps
	 *            <String,String> maps 属性表，成员属性age为KEY，中文名称为VALUE
	 * @param list
	 *            <T> list 需要导出的数据列表对象
	 * @param filename 指定输出文件名称，只能导出excel2003以上版本
	 * 
	 * @return InputStream 返回输入流
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> InputStream excelExport(Map<String, String> maps,
			List<T> list, String filename,int maxSize) {
		if(list.size() > maxSize){
			throw new POIExcelTooManyException("数据单次最多只能导出"+maxSize+"条");
		}
		InputStream excelStream=null;
		Workbook wb = null;
		try {
			
			String type = filename.substring(filename.lastIndexOf(".") + 1)
					.toLowerCase();
			if (type.equals(FILE_EXTENSION_XLS)) {
				wb = new HSSFWorkbook();
			}
			if (type.equals(FILE_EXTENSION_XLSX)) {
				wb = new XSSFWorkbook();
			}
			CreationHelper createHelper = wb.getCreationHelper();
			Sheet sheet = wb.createSheet("sheet1");
			Set<String> sets = maps.keySet();
			Row row = sheet.createRow(0);
			int i = 0;
			// 定义表头
			for (Iterator<String> it = sets.iterator(); it.hasNext();) {
				String key = it.next();
				Cell cell = row.createCell(i++);
				cell.setCellValue(createHelper.createRichTextString(maps
						.get(key)));
			}
			// 填充表单内容
			logger.info("--------------------100%");
			float avg = list.size() / 20f;
			int count = 1;
			int size = list.size() > 5000 ? 5000: list.size();
			for (int j = 0; j < size; j++) {
				T p = list.get(j);
				Class classType = p.getClass();
				int index = 0;
				Row row1 = sheet.createRow(j + 1);
				for (Iterator<String> it = sets.iterator(); it.hasNext();) {
					String key = it.next();
					String firstLetter = key.substring(0, 1).toUpperCase();
					// 获得和属性对应的getXXX()方法的名字
					String getMethodName = "get" + firstLetter
							+ key.substring(1);
					// 获得和属性对应的getXXX()方法
					Method getMethod = classType.getMethod(getMethodName,
							new Class[] {});
					// 调用原对象的getXXX()方法
					Object value = getMethod.invoke(p, new Object[] {});
					if (value instanceof Date) {
						value = df.format(value);  
					}
					Cell cell = row1.createCell(index++);
					cell.setCellValue(value == null ? "" : value.toString());
				}
				if (j > avg * count) {
					count++;
					logger.info("I");
				}
				if (count == 20) {
					logger.info("I100%");
					count++;
				}
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			baos.flush();
      		byte[] aa = baos.toByteArray();
      		excelStream = new ByteArrayInputStream(aa, 0, aa.length);
      		baos.close();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return excelStream;
	}
	
	public static String getPoiFormats(String fmt){
		return DATA_FORMATS.get(fmt);
	}
	
	
	public static void main(String[] args) {
		// Map<String, String> maps = new LinkedHashMap<String, String>();
		// maps.put("id", "ID");
		// maps.put("name", "姓名");
		// maps.put("age", "年龄");
		// maps.put("birthday", "生日");
		//
		// Properties props = System.getProperties();
		// String USER_HOME = props.getProperty("user.home");
		// System.out.println(USER_HOME);
		// File file = new File(USER_HOME + "/Desktop/excelExport.xlsx");
		//
		// List<User> demo = new ArrayList<User>();
		//
		// for (int i = 0; i < 100; i++) {
		// User user = new User();
		// user.setAge(10 + i);
		// user.setBirthday(new Date());
		// user.setId("001" + i);
		// user.setName("name" + i);
		// demo.add(user);
		// }
		// POIExcelUtil.excelExport(maps, demo, file);
	}
}

class POIExcelTooManyException extends BusinessException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public POIExcelTooManyException(String code, String msg) {
		super(msg);
		this.errCode = code;
	}
	
	public POIExcelTooManyException(String errCode) {
		super(errCode);
		super.errCode = errCode;
	}

	
	
	
}

