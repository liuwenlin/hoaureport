package com.hoau.hoaureport.module.common.shared.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hoau.hoaureport.module.common.server.constants.ItfConifgConstant;


public class FolderUtils {
	/**手动生成csv**/
	public static final String FILE_EXTENSION_CODE1001 = "1001";
	/**整月csv**/
	public static final String FILE_EXTENSION_CODE1002 = "1002";
	
	public static final String FILE_USER_ADMIN = "adminjob";
	public static final String FILE_USER_WAYBILLZIP = "waybilltime";
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String REPORT_TYPE_A = "REPORT_TYPE_A";
	
	public static final String FILE_EXTENSION_CODE1001_ID = "888888";
	
	private static SimpleDateFormat DATE_NO_DAY_FORMAT = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat DATE_NO_MONTH_FORMAT = new SimpleDateFormat("yyyyMM");
	
	/**
	 * 返回当前日期无分隔符格式化字符串 格式为yyyyMMdd
	 * 
	 * @return
	 */
	public static String getNowDateStrNoSep(String remark) {
		return getNowDateStrNoSep(remark,new Date());
	}
	public static String getNowDateStrNoSep(String remark,Date date) {
		return remark+DATE_NO_DAY_FORMAT.format(date);
	}
	/**
	 * 返回当前日期无分隔符格式化字符串 格式为yyyyMM
	 * @param remark
	 * @param date
	 * @return
	 */
	public static String getMonthDateStrNoSep(String remark,Date date) {
		return remark+DATE_NO_MONTH_FORMAT.format(date);
	}
	/**
	 * 返回当前日期无分隔符格式化字符串 格式为yyyyMM
	 * @param remark
	 * @return
	 */
	public static String getMonthDateStrNoSep(String remark) {
		return getMonthDateStrNoSep(remark,new Date());
	}
	
	public static Map<String,String> getFolder(String downCode,String username,String sqlName,String sqlNumber,Date nowDate){
		Map<String,String> map = new HashMap<String, String>();
		//手动生成
		if(FILE_EXTENSION_CODE1001.equals(downCode)){
			String fileName = getNowDateStrNoSep(sqlName,nowDate);
			String folder = ItfConifgConstant.HAR_FILE_DIR + DATE_NO_DAY_FORMAT.format(nowDate) + "/"
					+ username + "/" + sqlNumber + "_myfolder";
		
			map.put("filePath", folder+"/"+fileName);
			map.put("fileName", fileName);
			map.put("folder", folder);
		}else if(FILE_EXTENSION_CODE1002.equals(downCode)){
			String fileName = getMonthDateStrNoSep(sqlName,addDateByMonth(nowDate,-1));
			String folder = ItfConifgConstant.HAR_FILE_DIR + DATE_NO_DAY_FORMAT.format(firstDay(nowDate,1)) + "/"
					+ username + "/" + sqlNumber + "_myfolder";
			
			map.put("filePath", folder+"/"+fileName);
			map.put("fileName", fileName);
			map.put("folder", folder);
		}
		
		return map;
		
	}
	
	/**
	 * 加减月
	 */
	public static Date addDateByMonth(Date date,int month){
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.MONTH,  month);
		return rightNow.getTime();
	}
	/**
	 * 加减 日
	 * @param date 日期
	 * @param days 天数
	 * @return
	 */
	public static Date addDateByday(Date date,int days){
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.DATE,  days);
		return rightNow.getTime();
	}
	
	public static Date firstDay(Date date,int day){
		Calendar c = Calendar.getInstance();    
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH,day);//day=1,当前日期既为本月第一天 
        return c.getTime();
	}
	/**
	 * 日期转字符
	 *  默认  yyyy-MM-dd 格式
	 * @param date
	 * @return
	 */
	public static String dateToStr(Date date){
		
		return dateToStr(date,DATE_FORMAT);
	}
	/**
	 * 日期转字符
	 * @param date
	 * @param format  自定义格式
	 * @return
	 */
	public static String dateToStr(Date date,String format){
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}
	
	public static void main(String[] args) throws ParseException {
	
		Map<String,String> m=getFolder("1001", "27353", "测试报表","sql_123",  new Date());
		Map<String,String> m2=getFolder("1002", FolderUtils.FILE_USER_ADMIN,"整月测试报表", "sql_123",  new Date());
		System.out.println(m.get("fileName"));
		System.out.println(m.get("folder"));
		System.out.println(m.get("filePath"));
		System.out.println("------------------");
		System.out.println(m2.get("fileName"));
		System.out.println(m2.get("folder"));
		System.out.println(m2.get("filePath"));
	
		
	}
	
	
}
