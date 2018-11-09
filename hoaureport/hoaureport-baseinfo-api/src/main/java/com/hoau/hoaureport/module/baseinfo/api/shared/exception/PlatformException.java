package com.hoau.hoaureport.module.baseinfo.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 平台异常类
 * @author 张贞献
 * @date 2015-7-24
 */
public class PlatformException extends BusinessException {

    private static final long serialVersionUID = 3964605498450776283L;
    
    /**
     * 平台编码为null异常
     */
    public static final String PLATFORM_CODE_IS_NULL = "bse.org.PlatformCodeNullException";
    /**
     * 平台不存在异常
     */
    public static final String PLATFORM_NOT_EXIST = "bse.org.PlatformNotExist";
    
    public PlatformException(String code) {
    	super(code);
    	super.errCode = code;
    }
}
