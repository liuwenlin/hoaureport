package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.common.server.constants.ItfConifgConstant;
import com.hoau.hoaureport.module.configreport.server.dao.PlatDeliverIntimeRateMapper;
import com.hoau.hoaureport.module.configreport.server.service.IPlatDeliverIntimeRateManageService;
import com.hoau.hoaureport.module.configreport.server.util.ExcelUtil;
import com.hoau.hoaureport.module.configreport.shared.domain.PlatDeliverIntimeRate;
import com.hoau.hoaureport.module.util.DateUtils;

/**
 * 平台-送货及时率ManageService
 * ClassName: PlatDeliverIntimeRateManageService 
 * @author 文洁
 * @date 2016年10月17日
 * @version V1.0
 */
@Service
public class PlatDeliverIntimeRateManageService implements IPlatDeliverIntimeRateManageService{
	private static final Logger log = LoggerFactory.getLogger(PlatDeliverIntimeRate.class);
	@Resource
	PlatDeliverIntimeRateMapper platDeliverIntimeRateMapper;
	
	/**
	 * 查询平台次日送达率信息
	 */
	@Override
	public List<PlatDeliverIntimeRate> queryPlatDeliverIntimeRate(
			PlatDeliverIntimeRate param, int start, int limit) {
		RowBounds rowBounds = new RowBounds(start, limit);
		return platDeliverIntimeRateMapper.queryPlatDeliverIntimeRateByCondition(param, rowBounds);
	}

	/**
	 * 查询记录总数
	 */
	@Override
	public Long queryPlatDeliverIntimeRateCount(PlatDeliverIntimeRate param) {
		return platDeliverIntimeRateMapper.queryPlatDeliverIntimeRateCountByCondition(param);
	}

	/**
	 * 增加平台次日送达率
	 */
	@Transactional
	@Override
	public void addPlatDeliverIntimeRate(PlatDeliverIntimeRate record) {
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		//工号
		String currUserCode = currUser.getEmpCode().substring(2);
		record.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		record.setEffectedTime(new Date());
		record.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		record.setCreateTime(new Date());
		record.setCreateUserCode(currUserCode);
		record.setModifyTime(new Date());
		record.setModifyUserCode(currUserCode);
		
		platDeliverIntimeRateMapper.insertSelective(record);
	}

	/**
	 * 在原纪录上更新平台次日送达率信息
	 */
	@Transactional
	@Override
	public void updatePlatDeliverIntimeRate(PlatDeliverIntimeRate record) {
		platDeliverIntimeRateMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 原纪录作废，更新后的记录作为新数据插入
	 */
	@Transactional
	@Override
	public void repealAndAddPlatDeliverIntimeRate(PlatDeliverIntimeRate param) {
		//原纪录作废，并取得修改者编号
		String modifier = repealPlatDeliverIntimeRate(param);
		
		//另外插入修改数据
		param.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		param.setEffectedTime(new Date());
		param.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		param.setCreateTime(new Date());
		param.setCreateUserCode(modifier);
		param.setModifyTime(new Date());
		param.setModifyUserCode(modifier);
		
		addPlatDeliverIntimeRate(param);
	}

	/**
	 * 纪录作废
	 */
	@Transactional
	@Override
	public String repealPlatDeliverIntimeRate(PlatDeliverIntimeRate param) {
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		//工号
		String currUserCode = currUser.getEmpCode().substring(2);
		PlatDeliverIntimeRate oldInfo = new PlatDeliverIntimeRate();
		oldInfo.setPlatIntimerateId(param.getPlatIntimerateId());
		oldInfo.setActive(ItfConifgConstant.HAR_ACTIVE_NO);
		oldInfo.setInvalidTime(new Date());
		oldInfo.setModifyTime(new Date());
		oldInfo.setModifyUserCode(currUserCode);
		
		updatePlatDeliverIntimeRate(oldInfo);
		return currUserCode;
	}

	/**
	 * 判断记录是否已存在
	 */
	@Override
	public boolean isExist(PlatDeliverIntimeRate param) {
		//设置判断为已存在的条件
		PlatDeliverIntimeRate exitCondition = new PlatDeliverIntimeRate();
						exitCondition.setDepartment(param.getDepartment());
						exitCondition.setTargetValueMonth(param.getTargetValueMonth());
						//不考虑已作废记录
						exitCondition.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
								
						List<PlatDeliverIntimeRate> platDeliverIntimeRates = this.queryPlatDeliverIntimeRate(exitCondition,0,10);
						return platDeliverIntimeRates.size() > 0;
	}

	@Override
	public Map<String, Object> bathImplPlatDeliverIntimeRate(String path)
			throws Exception {
		//导入结果
				Map<String,Object> retuMap =new HashMap<String, Object>();
				//解析Excel
				List<Map<String, String>> list = ExcelUtil.readToListByFile(path, 32, 2);
				//把键值对 转换成 对象集合
				List<PlatDeliverIntimeRate> platDeliverIntimeRates = new ArrayList<PlatDeliverIntimeRate>();
				//“目标值”必须为数字类型字符，精确到小数点后两位（不可以用%表示）。
			       // “上月完成值”必须是数字类型的字符，精确到小数点后两位(不可以用%表示)。
			       // “所属月份”必须是“2016-08”这种格式
				//匹配目标值 和 上月完成值 
				  Pattern pValue = Pattern.compile("^\\d{1,9}(\\.\\d{1,41})?$");
				//匹配所属月份  
				  Pattern pMonth = Pattern.compile("^\\d{4}(\\-\\d{2})?$");
				  if(list.size() > 0){
					  Map<String, String> map = null;
					  for(int i = 0;i<list.size();i++){
						  map = list.get(i); 
						  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(2+"")))
								  ||!StringUtil.isNotEmpty(StringUtil.trim(map.get(3+"")))){
							  retuMap.put("error", "第"+(++i)+"行,为空项请用0替换!");
								return retuMap;
						  }
						  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(4+"")))){
							  retuMap.put("error", "第"+(++i)+"行,所属月份不能为空!");
								return retuMap;
						  }
						  if(!StringUtil.equals("0", StringUtil.trim(map.get(2+"")))){
								if(!pValue.matcher(StringUtil.trim(map.get(2+""))).matches()){
									retuMap.put("error", "第"+(++i)+"行,目标值格式有错误,请修改后重新导入!");
									return retuMap;
								}
							}
							if(!StringUtil.equals("0", StringUtil.trim(map.get(3+"")))){
								if(!pValue.matcher(StringUtil.trim(map.get(3+""))).matches()){
									retuMap.put("error", "第"+(++i)+"行,上月完成值格式有错误,请修改后重新导入!");
									return retuMap;
								}
							}
							if(!pMonth.matcher(StringUtil.trim(map.get(4+""))).matches()){
								retuMap.put("error", "第"+(++i)+"行,所属月份格式有错误,请修改后重新导入!");
								return retuMap;
							}
					  } 
				  }
				for (Map<String, String> map : list) {
					PlatDeliverIntimeRate bean = new PlatDeliverIntimeRate();
					try {
						bean.setDepartment(StringUtil.trim(map.get(0+"")));
						bean.setManager(StringUtil.trim(map.get(1+"")));
						bean.setTargetValue(StringUtil.trim(map.get(2+"")));
						bean.setLastMonthFinishValue(StringUtil.trim(map.get(3+"")));
						bean.setTargetValueMonth(StringUtil.trim(map.get(4+"")));
					} catch (Exception e) {
						bean = null;// 如果有异常就把pcbean设为null,这样每条信息都加进去了
						log.error("批量导入模版数据异常，业务需要仅作提示", e);
					} finally {
						platDeliverIntimeRates.add(bean);
					}
				}
				//设置增加条数,覆盖条数
				Map<String, Long> countMap = new HashMap<String, Long>();
				countMap.put("addSize", new Long(0));// 增加条数
				countMap.put("coverSize", new Long(0));// 覆盖条数
				Long beforAddSize=null;
				Long beforCoverSize=null;
				for (int i = 0; i < platDeliverIntimeRates.size(); i++) {
					PlatDeliverIntimeRate info = platDeliverIntimeRates.get(i);
					try {
						if (info == null) {// 有异常
							continue;
						} else {
							 beforAddSize = countMap.get("addSize");
							 beforCoverSize = countMap.get("coverSize");
							this.addOrUpdatePlatDeliverIntimeRate(info, countMap);
						}
					} catch (Exception e) {
						log.error("批量导入模版数据更新操作异常，业务需要仅作提示", e);
						countMap.put("addSize", beforAddSize);
						countMap.put("coverSize", beforCoverSize);
					}
				}
				
				retuMap.put("addSize", countMap.get("addSize"));
				retuMap.put("coverSize", countMap.get("coverSize"));
				retuMap.put("sumSize", platDeliverIntimeRates.size());
				retuMap.put("error", "");
				return retuMap;
	}

	/**
	 * @Description: 新增或覆盖平台次日送达率数据
	 * @param @param pcbean
	 * @param @param countMap
	 * @author 文洁
	 * @date 2016年10月17日
	 */
	@Override
	public void addOrUpdatePlatDeliverIntimeRate(PlatDeliverIntimeRate info,
			Map<String, Long> countMap) throws Exception {
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		long addSize= countMap.get("addSize").longValue();
		long coverSize= countMap.get("coverSize").longValue();
		//查询历史有效信息
		PlatDeliverIntimeRate queryParam = new PlatDeliverIntimeRate();
		queryParam.setDepartment(info.getDepartment());
		queryParam.setTargetValueMonth(info.getTargetValueMonth());
		List<PlatDeliverIntimeRate> infoList = this.queryPlatDeliverIntimeRate(queryParam, 0, 1);
		if(infoList.size() > 0){//已存在 就直接更新
			//更新数据
			PlatDeliverIntimeRate updateParam = new PlatDeliverIntimeRate();
			updateParam.setImportTime(new Date());
			updateParam.setTargetValue(info.getTargetValue());
			updateParam.setLastMonthFinishValue(info.getLastMonthFinishValue());
			updateParam.setManager(info.getManager());
			updateParam.setModifyTime(new Date());
			updateParam.setModifyUserCode(user.getEmpCode().substring(2));
			updateParam.setPlatIntimerateId(infoList.get(0).getPlatIntimerateId());
			this.updatePlatDeliverIntimeRate(updateParam);
			//覆盖+1
			coverSize++;
		}else{
			//插入数据
			info.setImportTime(new Date());
			this.addPlatDeliverIntimeRate(info);
			//新增+1
			addSize++;
		}
		countMap.put("addSize", addSize);
		countMap.put("coverSize", coverSize);		
	}

}
