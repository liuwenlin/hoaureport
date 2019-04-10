package com.hoau.hoaureport.module.configreport.server.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;
import java.io.OutputStreamWriter;  
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;  
import java.util.LinkedHashMap;  
import java.util.List;  
import java.util.Map;
import java.util.Set;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import com.hoau.hoaureport.module.configreport.shared.vo.User;



public class CSVUtils {
	
	public static SimpleDateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	   @SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> InputStream createCSVFile(List<T> exportData, Map<String, String> maps,  
	            String outPutPath, String filename,boolean isCover) {  
	  
	        File csvFile = null; 
	        InputStream inputStream = null;
	        BufferedWriter csvFileOutputStream = null;  
	        try {  
	            csvFile = new File(outPutPath + filename + ".csv");  
	            // csvFile.getParentFile().mkdir();  
	            File parent = csvFile.getParentFile();  
	            if (parent != null && !parent.exists()) {  
	                parent.mkdirs();  
	            }  
	            if(!isCover){
	            	csvFile.createNewFile(); 
	            }
	            // GB2312使正确读取分隔符"," 
            	csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(  
            			new FileOutputStream(csvFile,isCover), "GB2312"), 1024);
	            Set<String> sets = maps.keySet();
	            if(!isCover){
	            	// 写入文件头部  
	            	for (Iterator<String> it = sets.iterator(); it.hasNext();) {
	            		String key = it.next();
	            		csvFileOutputStream.write("\""  
	            				+ maps.get(key) + "\"");  	
	            		if(it.hasNext()){
	            			csvFileOutputStream.write(","); 
	            		}
	            		
	            	}	            
	            	csvFileOutputStream.newLine(); 
	            }
	            //写入内容
	            for (int j = 0; j < exportData.size(); j++) {
	            	T p = exportData.get(j);
	            	Class classType = p.getClass();
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
	                    csvFileOutputStream.write("\""    
	                            +  value == null ? "" : value.toString() ); 
	                    if(it.hasNext()){
	                    	csvFileOutputStream.write(",");
	                    }else{
	                    	csvFileOutputStream.newLine();
	                    	break;
	                    }
	            	}
	            }
	            csvFileOutputStream.flush(); 
	            if(csvFile!=null && csvFile.exists()){
	            	inputStream = new FileInputStream(csvFile);
	            }
	        } catch (Exception e) {    
	           e.printStackTrace();    
	        } finally {    
	           try {    
	                csvFileOutputStream.close();    
	            } catch (IOException e) {    
	               e.printStackTrace();  
	           }    
	       } 
	        return inputStream;
	          
	    }  
	  
	   /**
	    * ZIP包压缩
	    * @param folder 路径
	    * @param zipname 文件名称
	    * @return  url zip包路径
	    * @throws IOException
	    */
	    public static String toZip(String folder,String zipname) throws IOException {
	    	String url =folder +zipname+ ".zip";
	        File zipFile = new File(url);
	        if (zipFile.exists()) {
	            zipFile.delete();
	        }      
	        ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(zipFile));
	        File dir = new File(folder);
	        File[] fs = dir.listFiles();
	        byte[] buf = null;
	        if(fs!=null){
	            for (File f : fs) {
	            	
	            	if((zipname+ ".zip").equals(f.getName())){
	            		continue;
	            	}else if(f.getName().endsWith(".zip") && f.exists()){
	            		f.delete(); //有其他名称zip包则删除掉
	            		continue;
	            	}
	                zipout.putNextEntry(new ZipEntry(f.getName()));
	                FileInputStream fileInputStream = new FileInputStream(f);
	                buf = new byte[2048];
	                BufferedInputStream origin = new BufferedInputStream(fileInputStream,2048);
	                int len;
	                while ((len = origin.read(buf,0,2048))!=-1) {
	                    zipout.write(buf,0,len);
	                }
	                zipout.flush();
	                origin.close();
	                fileInputStream.close(); 
	            }
	        }
	        zipout.flush();
	        zipout.close();
	        return url;
	    }
	   
	   
	   
	    public static void main(String[] args) { 
//		    for(int i=0;i<3;i++){
//				 Map<String, String> maps = new LinkedHashMap<String, String>();
//				 maps.put("id", "ID");
//				 maps.put("name", "姓名");
//				 maps.put("age", "年龄");
//				 maps.put("birthday", "生日");
//				
//				
//				 List<User> demo = new ArrayList<User>();
//				
//				 for (int j = 0; j < 100; j++) {
//				 User user = new User();
//				 user.setAge(10 + j);
//				 user.setBirthday(new Date());
//				 user.setId("001" + j);
//				 user.setName("name" + j);
//				 demo.add(user);
//				 }
//		        InputStream inputStream  =  CSVUtils.createCSVFile(demo, maps, "d:/", "121212",i); 		    		
//		        if(inputStream!=null){
//		        	System.out.println("1212---------");
//		        	System.out.println(inputStream.toString()+ "1212---------");
//		        }
//		    }	
 
	    }  
}
