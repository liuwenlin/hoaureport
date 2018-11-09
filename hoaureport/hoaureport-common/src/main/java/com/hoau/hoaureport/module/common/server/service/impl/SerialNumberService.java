package com.hoau.hoaureport.module.common.server.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hoaureport.module.common.server.dao.SerialNumberDao;
import com.hoau.hoaureport.module.common.server.service.ISerialNumberService;
import com.hoau.hoaureport.module.common.shared.define.SerialNumberRuleEnum;
import com.hoau.hoaureport.module.common.shared.domain.SerialNumberRuleEntity;
import com.hoau.hoaureport.module.util.DateUtils;
import com.hoau.hoaureport.module.util.UUIDUtil;

/**
 * @author：高佳
 * @create：2015年8月19日 上午10:13:53
 * @description：序号生成service实现
 */
@Service
public class SerialNumberService implements ISerialNumberService {
	private static final Logger logger = LogManager.getLogger(SerialNumberService.class);
	@Resource
	private SerialNumberDao serialNumberDao;

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public String generateSerialNumber(SerialNumberRuleEnum serialNumberRule,
			String... params) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 根据业务类型查询序号并添加悲观锁
		SerialNumberRuleEntity serialNumberRuleEntity = serialNumberDao
				.querySerialNumberRuleForUpdate(serialNumberRule.getCode());
		if (serialNumberRuleEntity == null) {
			// 业务类型序号不存在则添加序号信息
			serialNumberRuleEntity = new SerialNumberRuleEntity();
			serialNumberRuleEntity.setId(UUIDUtil.getUUID());
			serialNumberRuleEntity.setCode(serialNumberRule.getCode());
			serialNumberRuleEntity.setName(serialNumberRule.getName());
			serialNumberRuleEntity.setCurrentNum(0L);
			serialNumberRuleEntity.setCurrentTime(new Date());
			serialNumberDao.addSerialNumberRule(serialNumberRuleEntity);
			// 新增序号后添加悲观锁
			serialNumberRuleEntity = serialNumberDao
					.querySerialNumberRuleForUpdate(serialNumberRule.getCode());
		}
		try {
			result = spliceSerialNumber(serialNumberRule,
					serialNumberRuleEntity, true, params);
			serialNumberRuleEntity.setCurrentTime(Calendar.getInstance().getTime());
			
			serialNumberRuleEntity.setCurrentNum((Long) result.get("newSeq"));
		} catch (Exception e) {
			logger.error("生成编号出现异常", e);
			throw new BusinessException("生成序号异常", e);
		} finally{
			serialNumberDao.updateSerialNumberRule(serialNumberRuleEntity);
		}
		return (String)result.get("newCode");
	}

	/**
	 * 拼接序号
	 * 
	 * @param serialNumberRule
	 *            序号规则
	 * @param serialNumberRuleEntity
	 *            序号实体
	 * @param params
	 *            参数
	 * @return
	 * @author 高佳
	 * @date 2015年8月19日
	 * @update
	 */
	private Map<String, Object> spliceSerialNumber(
			SerialNumberRuleEnum serialNumberRule,
			SerialNumberRuleEntity serialNumberRuleEntity, boolean formal,
			String[] params) {
		Map<String, Object> mapResult = new HashMap<String, Object>();
		String newCode = "";
		long newSeq = 0;
		boolean resetNo = false;
		if (serialNumberRule.isNeedParams()) {
			// todo 需要拼接参数待实现

		}
		// 是否需要时间
		if (serialNumberRule.isNeedTime()) {
			// 如果对于上一个已生成的编号已跨天，需重置对应的sequence
			String currentDate = DateUtils.convert(
					serialNumberRuleEntity.getCurrentTime(),
					DateUtils.DATE_FORMAT);
			String nowDate = DateUtils.convert(
					Calendar.getInstance().getTime(), DateUtils.DATE_FORMAT);
			Long differDayNum = DateUtils.getTimeDiff(currentDate, nowDate);
			if (differDayNum > 0) {
				resetNo = true;
			}
		}
		// 字母前缀
		if (serialNumberRule.isNeedLetterPrefix()) {
			newCode = serialNumberRule.getLetterPrefix() + newCode;
		}
		// 时间前缀
		if (serialNumberRule.isNeedTime()) {
			newCode += DateUtils.convert(Calendar.getInstance().getTime(),
					serialNumberRule.getTimeFormat());
		}
		// 分隔符
		if (serialNumberRule.isNeedDelimiter()) {
			newCode += serialNumberRule.getDelimiter();
		}
		// 数字项
		if (serialNumberRule.isNeedNumber()) {
			// 如果为正式生成编号的情况，并且需要sequence的直接取nextval
			if (formal && serialNumberRule.isNeedSequence()) {
				// 使用序列待实现
			} else {
				// 针对日期+数字情况，如果跨天，则重置为1
				if (resetNo) {
					newSeq = 1;
					// 针对无需重置的情况，使当前值累加1
				} else {
					newSeq = serialNumberRuleEntity.getCurrentNum() + 1;
				}
			}
			// 数字项是否固定位数
			if (serialNumberRule.isFixedNumLen()) {
				newCode += String.format("%0" + serialNumberRule.getNumLen()
						+ "d", newSeq);
			} else {
				newCode += String.valueOf(newSeq);
			}
		}
		// 后缀
		if (serialNumberRule.isNeedLetterSuffix()) {
			newCode += serialNumberRule.getLetterSuffix();
		}
		mapResult.put("newCode", newCode);
		mapResult.put("newSeq", Long.valueOf(newSeq));
		return mapResult;
	}

}
