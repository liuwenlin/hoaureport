package com.hoau.hoaureport.module.common.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * @author：高佳
 * @create：2015年6月17日 下午8:09:19
 * @description：
 */
public class BusinessMonitorException extends BusinessException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    public static final String ADD_FAILURE = "添加异常";

    public static final String UPDATE_FAILURE = "修改异常";

    public static final String DELETE_FAILURE = "删除异常";

	public BusinessMonitorException(String errCode) {
		super(errCode);
		this.errCode = errCode;
	}

}
