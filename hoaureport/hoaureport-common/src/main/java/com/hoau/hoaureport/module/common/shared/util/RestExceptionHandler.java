package com.hoau.hoaureport.module.common.shared.util;

import java.util.Locale;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.message.IMessageBundle;
import com.hoau.hbdp.webservice.components.rest.define.HttpContentTypeConstants;
import com.hoau.hbdp.webservice.components.rest.define.RestErrorCodeConstants;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * @author：高佳
 * @create：2015年8月3日 下午2:47:28
 * @description：Rest接口调用异常处理类
 */
@Provider
public class RestExceptionHandler implements ExceptionMapper<Throwable>{
	/**
     * 用于国际化消息生成
     */
    @Resource
    protected IMessageBundle messageBundle;
	public Response toResponse(Throwable ex) {
		ResponseBuilder rb = Response
				.status(Response.Status.OK);
		rb.type(HttpContentTypeConstants.JSON_UTF8);
		ResponseBaseEntity<Void> entity = new ResponseBaseEntity<Void>();
		if (ex instanceof BusinessException) {// 自定义的异常类
			ex.printStackTrace();
			BusinessException e = (BusinessException) ex;
			entity.setErrorCode(RestErrorCodeConstants.STATUS_BUSSINESS_ERROR);
			entity.setErrorMessage(messageBundle.getMessage(e.getErrorCode(), e.getErrorArguments()));
			rb.entity(entity);
		} else {
			ex.printStackTrace();
			entity.setErrorCode(RestErrorCodeConstants.STATUS_SYSTEM_ERROR);
			entity.setErrorMessage(RestErrorCodeConstants.STATUS_SYSTEM_ERROR_INFO);
			rb.entity(entity);
		}
		rb.language(Locale.SIMPLIFIED_CHINESE);
		Response r = rb.build();
		return r;
	}
}
