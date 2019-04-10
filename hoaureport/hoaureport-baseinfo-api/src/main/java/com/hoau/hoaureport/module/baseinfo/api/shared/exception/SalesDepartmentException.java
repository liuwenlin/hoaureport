package com.hoau.hoaureport.module.baseinfo.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 门店异常类
 * @author 张贞献
 * @date 2015-7-24
 */
public class SalesDepartmentException extends BusinessException {

    private static final long serialVersionUID = 3964605498450776283L;
    
    /**
     * 门店编码为null异常
     */
    public static final String SALESDEPARTMENT_CODE_IS_NULL = "bse.org.SalesDepartmentCodeNullException";
    /**
     * 门店不存在异常
     */
    public static final String SALESDEPARTMENT_NOT_EXIST = "bse.org.SalesDepartmentNotExist";
    
    public SalesDepartmentException(String code) {
    	super(code);
    	super.errCode = code;
    }
}
