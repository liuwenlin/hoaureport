package com.hoau.hoaureport.module.configreport.server.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * 利用开源组件POI3.0.2动态导出EXCEL文档 转载时请保留以下信息，注明出处！
 * 
 * @author wwn
 * @version v1.0
 * @param <T>
 *            应用泛型，代表任意一个符合javabean风格的类
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 *            byte[]表jpg格式的图片数据
 */
public class ExportExcel<T> {

	public void exportExcel(Collection<T> dataset, OutputStream out) {
		exportExcel("f", null, dataset, out, "yyyy-MM-dd");
	}

	public void exportExcel(String[] headers, Collection<T> dataset,
			OutputStream out) {
		exportExcel("f", headers, dataset, out, "yyyy-MM-dd");
	}

	public void exportExcel(String[] headers, Collection<T> dataset,
			OutputStream out, String pattern) {
		exportExcel("f", headers, dataset, out, pattern);
	}

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public HSSFWorkbook exportExcel(String title, String[] headers,
			Collection<T> dataset, OutputStream out, String pattern) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
	// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		
		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
		
			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName,
							new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
				 String textValue = null;
					if (value instanceof Integer) {
						
					 int intValue = (Integer) value;
				 cell.setCellValue(intValue);
				 } else if (value instanceof Float) {
					
				    float fValue = (Float) value;
				    cell.setCellValue(fValue);				
				 } else if (value instanceof Double) {				 
					  double dValue = (Double) value;					
					 cell.setCellValue(dValue);
				 } else if (value instanceof Long) {
					 long longValue = (Long) value;
					// cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					 cell.setCellValue(longValue);
					}else
					if (value instanceof Boolean) {
						boolean bValue = (Boolean) value;
						textValue = "是";
						if (!bValue) {
							textValue = "否";
						}
					}else if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						
						 textValue = sdf.format(date);
						 cell.setCellValue(textValue);
					}else{	
						if(value!=null){
						 textValue = value.toString();
						  cell.setCellValue(textValue);
						  }else{
							  cell.setCellValue(""); 
						  }
		         
						
					}

				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}

		}
		
		return workbook;

	}
	// 返回Excel输入流
	public InputStream inputToClient(HSSFWorkbook workbook) {
		InputStream excelStream = null;
		ByteArrayOutputStream out = null;
        try {
        	out = new ByteArrayOutputStream();
            workbook.write(out);
            out.flush();
            byte[] excelBytes = out.toByteArray();
            excelStream = new ByteArrayInputStream(excelBytes, 0, excelBytes.length);
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                	out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return excelStream;
	}
	
}