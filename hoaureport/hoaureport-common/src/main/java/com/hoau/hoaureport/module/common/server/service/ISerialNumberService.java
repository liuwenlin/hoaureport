package com.hoau.hoaureport.module.common.server.service;

import com.hoau.hoaureport.module.common.shared.define.SerialNumberRuleEnum;

/**
 * @author：高佳
 * @create：2015年8月19日 上午10:07:13
 * @description：序号service接口
 */
public interface ISerialNumberService {
	String generateSerialNumber(SerialNumberRuleEnum serialNumberRule,String... params);
}
