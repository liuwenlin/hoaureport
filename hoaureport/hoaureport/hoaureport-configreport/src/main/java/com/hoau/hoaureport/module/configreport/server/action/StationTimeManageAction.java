package com.hoau.hoaureport.module.configreport.server.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.configreport.server.service.IStationTimeInfoManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.StationTimeInfo;
import com.hoau.hoaureport.module.configreport.shared.vo.StationTimeVo;
import com.hoau.hoaureport.module.util.DateUtils;
/** 
 *@Description:场站（时效） Action
 *@author : 梁令
 *@date 创建时间：2017年1月11日 下午2:15:33 
 */
@Controller("stationTimeManageAction")
@Scope("prototype")
public class StationTimeManageAction extends AbstractAction{
      private StationTimeVo stationTimeVo;
      @Resource
      IStationTimeInfoManageService stationTimeInfoManageService;

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 返回场站(时效) 首页
	 * @return
	 */
	public String showStationTimePage(){
		return "index";
	}
	/**
	 * @Title: queryStationTimeInfo 
	 * @Description: 查询场站-时效信息
	 * @author 梁令
	 * @date 2017年1月11日 下午3:30:01 
	 * @return String    返回类型 
	 * @throws
	 */
	public String queryStationTimeInfo(){
		try {
			stationTimeVo.setStationTimeInfoList(
					stationTimeInfoManageService.queryStationTimeInfo(
				    stationTimeVo.getStationTimeParam(), start, limit));
			//totalCount总的记录条数
			totalCount = stationTimeInfoManageService.queryStationTimeInfoCount(
					stationTimeVo.getStationTimeParam());
			return returnSuccess();
			
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * @Title: addStationTimeInfo 
	 * @Description: 新增场站(时效)信息
	 * @author 梁令
	 * @date 2017年1月11日 下午3:17:10 
	 * @return String    返回类型 
	 * @throws
	 */
	public String addStationTimeInfo(){
		UserEntity user  = (UserEntity)(UserContext.getCurrentUser());
		try {
			StationTimeInfo param = stationTimeVo.getStationTimeParam();
			StationTimeInfo filterParam = new StationTimeInfo();
			filterParam.setShortName(param.getShortName());
			filterParam.setActive("Y");
			//根据场站简称和是否有效进行查询是否存在
			List<StationTimeInfo> lists = stationTimeInfoManageService.queryStationTimeInfo(filterParam, 0, 10);
			if(lists.size() > 0){//当大于0时，场站简称已存在
				return returnError("已存在场站简称相同的数据，请重新添加!");
			}else{
				StationTimeInfo newInfo = new StationTimeInfo();
				newInfo.setShortName(param.getShortName());
				newInfo.setStationName(param.getStationName());
				newInfo.setTheArea(param.getTheArea());
				newInfo.setTheBusinessDepartment(param.getTheBusinessDepartment());
				newInfo.setActive("Y");
				newInfo.setEffectedTime(new Date());
				newInfo.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
				newInfo.setCreateTime(new Date());
				newInfo.setCreateUserCode(user.getEmpCode().substring(2));//创建人工号
	            stationTimeInfoManageService.addStationTimeInfo(newInfo);
	            return returnSuccess("新增场站-时效信息成功!");
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * @Title: modifyStationTimeInfo 
	 * @Description: 修改场站(时效)信息
	 * @author 梁令
	 * @date 2017年1月12日 下午1:54:07 
	 * @return String    
	 * @throws
	 */
	public String modifyStationTimeInfo(){
		 UserEntity user = (UserEntity)(UserContext.getCurrentUser());
		try {
			 StationTimeInfo param = stationTimeVo.getStationTimeParam();
			 StationTimeInfo filterParam = new StationTimeInfo();
			 //根据ID来查询场站-时效 信息
			 filterParam.setStationTimeId(param.getStationTimeId());
			 filterParam.setActive("Y");
			 List<StationTimeInfo> oldInfo = stationTimeInfoManageService.queryStationTimeInfo(filterParam, 0, 10);
			 //是否修改成本条数据
			 if(StringUtil.equals(oldInfo.get(0).getShortName(), param.getShortName())){
				 stationTimeInfoManageService.repealAndAddInfo(filterParam, user.getEmpCode().substring(2));
				 return returnSuccess("修改数据成功!");
			 }else{
				 StationTimeInfo filters = new StationTimeInfo();
				 filters.setShortName(param.getShortName());
				 List<StationTimeInfo> infoLists = stationTimeInfoManageService.queryStationTimeInfo(filters, 0, 10);
				 if(infoLists.size() > 0){
					 return returnError("已存在相同场站简称数据，请重新添加!");
				 }else{
					 stationTimeInfoManageService.repealAndAddInfo(param, user.getEmpCode().substring(2));
					 return returnSuccess("修改成功");
				 }
			 }
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	public String repealStationTimeInfo(){
		UserEntity user = (UserEntity)(UserContext.getCurrentUser());
		try {
			StationTimeInfo param = stationTimeVo.getStationTimeParam();
			StationTimeInfo newInfo = new StationTimeInfo();
			newInfo.setStationTimeId(param.getStationTimeId());//获取作废数据的ID
			newInfo.setActive("N");//设置作废状态为N
			newInfo.setInvalidTime(new Date());
			newInfo.setModifyTime(new Date());
			newInfo.setModifyUserCode(user.getEmpCode().substring(2));
			stationTimeInfoManageService.updateStationTimeInfo(newInfo);
			return returnSuccess("数据作废成功!");
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	public StationTimeVo getStationTimeVo() {
		return stationTimeVo;
	}
	public void setStationTimeVo(StationTimeVo stationTimeVo) {
		this.stationTimeVo = stationTimeVo;
	}
	
}
