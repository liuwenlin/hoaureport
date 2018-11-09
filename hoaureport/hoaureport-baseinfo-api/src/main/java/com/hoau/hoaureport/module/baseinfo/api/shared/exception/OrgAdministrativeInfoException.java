package com.hoau.hoaureport.module.baseinfo.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 组织异常类
 * @author 高佳
 * @date 2015年6月12日
 */
public class OrgAdministrativeInfoException extends BusinessException {

    private static final long serialVersionUID = 3964605498450776283L;
    
    /**
     * 组织编码为null异常
     */
    public static final String ORGADMINISTRATIVEINFO_CODE_IS_NULL = "bse.org.OrgAdministrativeInfoCodeNullException";
    /**
     * 组织编码为null异常
     */
    public static final String ORGADMINISTRATIVEINFO_LOGISTCODE_IS_NULL = "bse.org.OrgAdministrativeInfoLogistCodeNullException";
    
    /**
     * 组织不存在异常
     */
    public static final String ORGADMINISTRATIVEINFO_NOT_EXIST = "bse.org.OrgAdministrativeInfoNotExist";
    
    public OrgAdministrativeInfoException(String code) {
    	super(code);
    	super.errCode = code;
    }
}
