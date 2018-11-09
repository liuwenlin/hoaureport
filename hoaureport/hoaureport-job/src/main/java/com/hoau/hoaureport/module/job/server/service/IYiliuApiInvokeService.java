package com.hoau.hoaureport.module.job.server.service;

/**
 * 统一外部接口请求的服务
 */
public interface IYiliuApiInvokeService {
    /**
     * 用于Get方式请求
     * @param url
     */
    void doGetRequest(String url);

    /**
     * 用于Post方式请求
     * @param url
     */
    void doPostRequest(String url);

    /**
     * 用于指定的接口的Post方式请求
     * @param url 请求地址
     * @param apiName 接口名称
     */
    void doPostRequest(String url, String apiName);
}
