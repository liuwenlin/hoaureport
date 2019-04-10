package com.hoau.hoaureport.module.job.shared.domain;

import java.io.Serializable;
import java.sql.Date;

/**
 * 易流接口定义实体
 * @author linwenlin
 * @date 2018/3/7 11:11
 */
public class YiliuApiEntity implements Serializable {
    private int api_id;
    private String api_name;
    private String params_name;
    private String params_value;
    private String is_sign_params;
    private Date create_time;
    private String create_usercode;
    private Date modify_time;
    private String modify_usercode;

    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public String getParams_name() {
        return params_name;
    }

    public void setParams_name(String params_name) {
        this.params_name = params_name;
    }

    public String getParams_value() {
        return params_value;
    }

    public void setParams_value(String params_value) {
        this.params_value = params_value;
    }

    public String getIs_sign_params() {
        return is_sign_params;
    }

    public void setIs_sign_params(String is_sign_params) {
        this.is_sign_params = is_sign_params;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getCreate_usercode() {
        return create_usercode;
    }

    public void setCreate_usercode(String create_usercode) {
        this.create_usercode = create_usercode;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public String getModify_usercode() {
        return modify_usercode;
    }

    public void setModify_usercode(String modify_usercode) {
        this.modify_usercode = modify_usercode;
    }

    @Override
    public String toString() {
        return "YiliuApiEntity{" +
                "api_id=" + api_id +
                ", api_name='" + api_name + '\'' +
                ", params_name='" + params_name + '\'' +
                ", prams_value='" + params_value + '\'' +
                ", is_sign_params='" + is_sign_params + '\'' +
                ", create_time=" + create_time +
                ", create_usercode='" + create_usercode + '\'' +
                ", modify_time=" + modify_time +
                ", modify_usercode='" + modify_usercode + '\'' +
                '}';
    }
}
