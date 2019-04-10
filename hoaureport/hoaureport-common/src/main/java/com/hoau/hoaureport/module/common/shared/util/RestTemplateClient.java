package com.hoau.hoaureport.module.common.shared.util;

import javax.annotation.Resource;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hoau.hbdp.framework.shared.util.JsonUtils;

/**
 * @author：高佳
 * @create：2015年7月6日 上午9:22:31
 * @description：RestTemplate调用工具类
 */
@Component
public class RestTemplateClient implements InitializingBean{
	/**
	 * Rest 调用
	 */
	@Resource
	private RestTemplate restTemplate;

	/**
	 * get调用
	 * @param url url
	 * @param reponseType 返回Object 类型
	 * @param parameterClasses 返回参数泛型类型
	 * @param urlVariables url参数
	 * @return
	 * @author 高佳
	 * @date 2015年7月6日
	 * @update 
	 */
	public <T> T getForObject(String url, TypeReference<T> typeReference, Object... urlVariables) {
		String json = restTemplate
				.getForObject(url, String.class, urlVariables);
		return JsonUtils.toObject(json, typeReference);
	}

	/**
	 * POST 请求
	 * @param url url
	 * @param request 请求参数对象
	 * @param reponseType  返回Object 类型
	 * @param parameterClasses 返回参数泛型类型
	 * @param urlVariables url参数
	 * @return
	 * @author 高佳
	 * @date 2015年7月6日
	 * @update 
	 */
	public <T> T postForObject(String url,Object request,TypeReference<T> typeReference, Object... urlVariables){
		String requestJson = JsonUtils.toJson(request);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json;charset=UTF-8");
		headers.set("Accept", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		String json = restTemplate.exchange(url, HttpMethod.POST, entity,
				String.class, urlVariables).getBody();
		return JsonUtils.toObject(json, typeReference);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	}
}
