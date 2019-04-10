package com.hoau.hoaureport.module.util;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.hoau.hoaureport.module.util.define.CtmsConstants;


public class DataTrans {
    public static String boolToYes(boolean bool){
	return bool?CtmsConstants.YES:CtmsConstants.NO;
    }

    public static boolean flagToBool(String flag){
	return StringUtils.equals(CtmsConstants.YES, flag);
    }
    
    public static double bigDecimalToDouble(BigDecimal bigDecimal){
	return bigDecimal==null?0:bigDecimal.doubleValue();
    }
}
