package com.hoau.hoaureport.module.baseinfo.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 场站异常类
 * @author 张贞献
 * @date 2015-7-24
 */
public class TransferCenterException extends BusinessException {

    private static final long serialVersionUID = 3964605498450776283L;
    
    /**
     * 场站编码为null异常
     */
    public static final String TRANSFERCENTER_CODE_IS_NULL = "bse.org.TransferCenterCodeNullException";
    /**
     * 场站不存在异常
     */
    public static final String TRANSFERCENTER_NOT_EXIST = "bse.org.TransferCenterNotExist";
    
    public TransferCenterException(String code) {
    	super(code);
    	super.errCode = code;
    }
}
