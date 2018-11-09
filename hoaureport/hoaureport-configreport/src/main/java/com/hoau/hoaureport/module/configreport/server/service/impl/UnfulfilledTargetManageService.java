package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.math.BigDecimal;
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
import com.hoau.hoaureport.module.configreport.server.dao.UnfulfilledTargetMapper;
import com.hoau.hoaureport.module.configreport.server.service.IUnfulfilledTargetManageService;
import com.hoau.hoaureport.module.configreport.server.util.ExcelUtil;
import com.hoau.hoaureport.module.configreport.shared.domain.UnfulfilledTarget;
import com.hoau.hoaureport.module.util.DateUtils;

/**
 * DD未兑现目标值管理服务实现
 * ClassName: UnfulfilledTargetManageService 
 * @author 文洁
 * @date 2016年12月20日
 * @version V1.0
 */
@Service
public class UnfulfilledTargetManageService implements IUnfulfilledTargetManageService{
	private static final Logger log = LoggerFactory.getLogger(UnfulfilledTarget.class);
	@Resource
	UnfulfilledTargetMapper unfulfilledTargetMapper;
	/**字段不存在的标识 中文符号——标识*/
	private static final String unExitFlagCn = "——";
	/**字段不存在的标识 英文符号--标识*/
	private static final String unExitFlagEn = "--";
	/**
	 * 查询DD未兑现目标值信息
	 */
	@Override
	public List<UnfulfilledTarget> queryUnfulfilledTarget(
			UnfulfilledTarget param, int start, int limit) {
		RowBounds rowBounds = new RowBounds(start, limit);
		return unfulfilledTargetMapper.queryUnfulfilledTargetByCondition(param, rowBounds );
	}

	/**
	 * 查询记录总数
	 */
	@Override
	public Long queryUnfulfilledTargetCount(UnfulfilledTarget param) {
		return unfulfilledTargetMapper.queryUnfulfilledTargetCountByCondition(param);
	}

	/**
	 * 增加DD未兑现目标值信息
	 */
	@Override
	public void addUnfulfilledTarget(UnfulfilledTarget record) {
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
		
		unfulfilledTargetMapper.insertSelective(record);
	}

	/**
	 * 在原纪录上更新未兑现目标值信息
	 */
	@Transactional
	@Override
	public void updateUnfulfilledTarget(UnfulfilledTarget record) {
		unfulfilledTargetMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 原纪录作废，更新后的记录作为新数据插入
	 */
	@Transactional
	@Override
	public void repealAndAddUnfulfilledTarget(UnfulfilledTarget param) {
		//原纪录作废，并取得修改者编号
		String modifier = repealUnfulfilledTarget(param);
		
		//另外插入修改数据
		param.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		param.setEffectedTime(new Date());
		param.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		param.setCreateTime(new Date());
		param.setCreateUserCode(modifier);
		param.setModifyTime(new Date());
		param.setModifyUserCode(modifier);
		
		addUnfulfilledTarget(param);		
	}

	/**
	 * 纪录作废
	 */
	@Transactional
	@Override
	public String repealUnfulfilledTarget(UnfulfilledTarget param) {
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		//工号
		String currUserCode = currUser.getEmpCode().substring(2);
		UnfulfilledTarget oldInfo = new UnfulfilledTarget();
		oldInfo.setUnfulfilledTargetId(param.getUnfulfilledTargetId());
		oldInfo.setActive(ItfConifgConstant.HAR_ACTIVE_NO);
		oldInfo.setInvalidTime(new Date());
		oldInfo.setModifyTime(new Date());
		oldInfo.setModifyUserCode(currUserCode);
		
		updateUnfulfilledTarget(oldInfo);
		return  null;
		}

	/**
	 * 判断记录是否已存在
	 */
	@Override
	public boolean isExist(UnfulfilledTarget param) {
		UnfulfilledTarget exitCondition = new UnfulfilledTarget();
		//设置判断已经存在的条件
		exitCondition.setDepartment(param.getDepartment());
		exitCondition.setTargetValueMonth(param.getTargetValueMonth());
		//不考虑已作废记录
		exitCondition.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		
		List<UnfulfilledTarget> unfulfilledTargets = queryUnfulfilledTarget(exitCondition, 0, 10);
		return unfulfilledTargets.size() > 0;
	}

	@Transactional
	@Override
	public Map<String, Object> bathImplUnfulfilledTarget(String path) throws Exception {
		//导入结果
		Map<String,Object> retuMap =new HashMap<String, Object>();
		//解析Excel
		List<Map<String, String>> list = ExcelUtil.readToListByFile(path, 32, 2);
		//把键值对 转换成 对象集合
		List<UnfulfilledTarget> unfulfilledTargetList = new ArrayList<UnfulfilledTarget>();
		
		//匹配目标值和历史值 带百分号数字 如：0.121%
		  Pattern pValue = Pattern.compile("^\\d{1,9}(\\.\\d{1,38})?%$");
		//匹配挑战值 非负整数 最长10位
		  Pattern pNum = Pattern.compile("0|([1-9][0-9]{0,9})");
		//匹配所属月份 如： 201601
		  Pattern pMonth = Pattern.compile("^\\d{6}");
		  if(list.size() > 0){
			  Map<String, String> map = null;
			  for(int i = 0;i<list.size();i++){
				  map = list.get(i); 
				  
				  String cell1 = StringUtil.trim(map.get(0+""));
				  String cell2 = StringUtil.trim(map.get(1+""));
				  String cell3 = StringUtil.trim(map.get(2+""));
				  String cell4 = StringUtil.trim(map.get(3+""));
				  String cell5 = StringUtil.trim(map.get(4+""));
				  String cell6 = StringUtil.trim(map.get(5+""));
				  
				  Boolean isEmpty = false;
				  Boolean isPatternMatched = false;
				  
				  if(StringUtil.isEmpty(cell1)){
					  retuMap.put("error", "第"+(++i)+"行,部门不能为空!");
						return retuMap;
				  }
				  if(StringUtil.isEmpty(cell2)){
					  retuMap.put("error", "第"+(++i)+"行,负责人不能为空,为空项请用‘-’代替!");
						return retuMap;
				  }
				  if(StringUtil.isEmpty(cell3)){
					  retuMap.put("error", "第"+(++i)+"行,目标值不能为空!");
						return retuMap;
				  }
				  if(StringUtil.isEmpty(cell4)){
					  retuMap.put("error", "第"+(++i)+"行,挑战值不能为空!");
						return retuMap;
				  }
				  if(StringUtil.isEmpty(cell5)){
					  retuMap.put("error", "第"+(++i)+"行,历史值不能为空!");
						return retuMap;
				  }
				  if(StringUtil.isEmpty(cell6)){
					  retuMap.put("error", "第"+(++i)+"行,所属月份不能为空!");
						return retuMap;
				  }
				  
				  isEmpty = StringUtil.equals(unExitFlagCn, cell3) || StringUtil.equals(unExitFlagEn, cell3);
				  isPatternMatched = pValue.matcher(cell3).matches();
				  if(!(isEmpty||isPatternMatched)){
							retuMap.put("error", "第"+(++i)+"行,目标值必须为带百分号数字如“0.12%”或"+ unExitFlagCn +",请修改后重新导入!");
							return retuMap;						
					}
				  isEmpty = StringUtil.equals(unExitFlagCn, cell4) || StringUtil.equals(unExitFlagEn, cell4);
				  isPatternMatched = pNum.matcher(cell4).matches();
				  if(!(isEmpty||isPatternMatched)){
									retuMap.put("error", "第"+(++i)+"行,挑战值必须为非负整数或"+ unExitFlagCn +",请修改后重新导入!");
									return retuMap;
				   }

				  System.out.println("cell5:" + cell5 + " unExitFlagEn:" + unExitFlagEn + " unExitFlagCn:" + unExitFlagCn );
				  System.err.println(unExitFlagCn.equals(cell5)||unExitFlagEn.equals(cell5));
				  isEmpty = StringUtil.equals(unExitFlagCn, cell5) || StringUtil.equals(unExitFlagEn, cell5);
				  isPatternMatched = pValue.matcher(cell5).matches();
				  if(!(isEmpty||isPatternMatched)){
									retuMap.put("error", "第"+(++i)+"行,历史值必须为带百分号数字如“0.12%”或"+ unExitFlagCn +",请修改后重新导入!");
									return retuMap;
				   }
				
					if(!pMonth.matcher(cell6).matches()){
						retuMap.put("error", "第"+(++i)+"行,所属月份格式有错误,请修改后重新导入!");
						return retuMap;
					}
			  } 
		  }
		for (Map<String, String> map : list) {
			UnfulfilledTarget bean = new UnfulfilledTarget();
			  
			String cell1 = StringUtil.trim(map.get(0+""));
			String cell2 = StringUtil.trim(map.get(1+""));
			String cell3 = StringUtil.trim(map.get(2+""));
			String cell4 = StringUtil.trim(map.get(3+""));
			String cell5 = StringUtil.trim(map.get(4+""));
			String cell6 = StringUtil.trim(map.get(5+""));
			Boolean isEmpty = false;
			try {
				//部门
				bean.setDepartment(cell1);
				//负责人
				bean.setManager(cell2);
				//所属月份
				bean.setTargetValueMonth(cell6);
				//目标值
				isEmpty = StringUtil.equals(unExitFlagCn, cell3) || StringUtil.equals(unExitFlagEn, cell3);
				if(isEmpty){
					log.error("目标值为空");
				}
				else {
					try {
						bean.setTargetValue(new BigDecimal(cell3.substring(0, cell3.indexOf("%")))
						.divide(new BigDecimal(100.00)));
					}catch(NumberFormatException e){
						log.error("目标值格式错误", e);
					}catch (Exception e) {
						log.error("目标值转换错误", e);
					}
				}
				
				//挑战值
				isEmpty = StringUtil.equals(unExitFlagCn, cell4) || StringUtil.equals(unExitFlagEn, cell4);
				if(isEmpty){
					log.error("挑战值为空");
				}
				else {
					try {
						bean.setChallengeValue(new BigDecimal(cell4));
					}catch(NumberFormatException e){
						log.error("挑战值格式错误", e);
					}catch (Exception e) {
						log.error("挑战值转换错误", e);
					}
				}
				
				//历史值
				isEmpty = StringUtil.equals(unExitFlagCn, cell5) || StringUtil.equals(unExitFlagEn, cell5);
				if(isEmpty){
					log.error("历史值为空");
				}
				else {
					try {
						bean.setHistoricalValue(new BigDecimal(cell5.substring(0, cell5.indexOf("%")))
						.divide(new BigDecimal(100.00)));
					}catch(NumberFormatException e){
						log.error("历史值格式错误", e);
					}catch (Exception e) {
						log.error("历史值转换错误", e);
					}
				}
				
				
			} catch (Exception e) {
				bean = null;// 如果有异常就把pcbean设为null,这样每条信息都加进去了
				log.error("批量导入模版数据异常，业务需要仅作提示", e);
			} finally {
				unfulfilledTargetList.add(bean);
			}
		}
		//设置增加条数,覆盖条数
		Map<String, Long> countMap = new HashMap<String, Long>();
		countMap.put("addSize", new Long(0));// 增加条数
		countMap.put("coverSize", new Long(0));// 覆盖条数
		Long beforAddSize=null;
		Long beforCoverSize=null;
		for (int i = 0; i < unfulfilledTargetList.size(); i++) {
			UnfulfilledTarget info = unfulfilledTargetList.get(i);
			try {
				if (info == null) {// 有异常
					continue;
				} else {
					 beforAddSize = countMap.get("addSize");
					 beforCoverSize = countMap.get("coverSize");
					this.addOrUpdateUnfulfilledTarget(info, countMap);
				}
			} catch (Exception e) {
				log.error("批量导入模版数据更新操作异常，业务需要仅作提示", e);
				countMap.put("addSize", beforAddSize);
				countMap.put("coverSize", beforCoverSize);
			}
		}
		
		retuMap.put("addSize", countMap.get("addSize"));
		retuMap.put("coverSize", countMap.get("coverSize"));
		retuMap.put("sumSize", unfulfilledTargetList.size());
		retuMap.put("error", "");
		return retuMap;
	}
	
	/**
	 * 
	 * @Description: 新增或覆盖DD未兑现目标值数据
	 * @param pcbean
	 * @param countMap
	 * @author 文洁
	 * @date 2016年12月20日
	 */
	@Transactional
	public void addOrUpdateUnfulfilledTarget(UnfulfilledTarget info,Map<String,Long> countMap) throws Exception {
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		long addSize= countMap.get("addSize").longValue();
		long coverSize= countMap.get("coverSize").longValue();
		//查询历史有效信息
		UnfulfilledTarget queryParam = new UnfulfilledTarget();
		queryParam.setDepartment(info.getDepartment());
		queryParam.setTargetValueMonth(info.getTargetValueMonth());
		List<UnfulfilledTarget> infoList = this.queryUnfulfilledTarget(queryParam, 0, 1);
		if(infoList.size() > 0){//已存在 就直接更新
			//更新数据
			UnfulfilledTarget updateParam = new UnfulfilledTarget();
			updateParam.setImportTime(new Date());
			updateParam.setTargetValue(info.getTargetValue());
			updateParam.setChallengeValue(info.getChallengeValue());
			updateParam.setHistoricalValue(info.getHistoricalValue());
			updateParam.setManager(info.getManager());
			updateParam.setModifyTime(new Date());
			updateParam.setModifyUserCode(user.getEmpCode().substring(2));
			updateParam.setUnfulfilledTargetId(infoList.get(0).getUnfulfilledTargetId());
			this.updateUnfulfilledTarget(updateParam);
			//覆盖+1
			coverSize++;
		}else{
			//插入数据
			info.setImportTime(new Date());
			this.addUnfulfilledTarget(info);
			//新增+1
			addSize++;
		}
		countMap.put("addSize", addSize);
		countMap.put("coverSize", coverSize);
	}
	
	public static void main(String[] args) {
		String numString = "122222222.00";
		Pattern pValue = Pattern.compile("^\\d{1,9}(\\.\\d{1,38})?%$");
		BigDecimal bDecimal = new BigDecimal("0.00");
		System.out.println(bDecimal);
		System.out.println(pValue.matcher(numString).matches());
		System.out.println(StringUtil.equals(unExitFlagEn, "-"));
	}

}
