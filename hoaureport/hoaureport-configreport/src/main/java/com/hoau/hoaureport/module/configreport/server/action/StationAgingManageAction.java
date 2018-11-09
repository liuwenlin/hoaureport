package com.hoau.hoaureport.module.configreport.server.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.configreport.server.service.IStationAgingManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.StationAgingInfo;
import com.hoau.hoaureport.module.configreport.shared.vo.StationAgingVo;
import com.hoau.hoaureport.module.util.DateUtils;

/**
 * 
  *@Description:场站辖区(时效)管理 Action
  *@author : 梁令
  *@date 创建时间：2017年1月6日 下午3:20:04
 */
@Controller("stationAgingManageAction")
@Scope("prototype")
public class StationAgingManageAction extends AbstractAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StationAgingVo stationAgingVo;
    
	@Resource 
	IStationAgingManageService stationAgingService;
	/**
	 * 返回场场站辖区(时效)管理  首界面
	 * @return
	 */
	public String showStationAgingPage(){
		return "index";
	}
	/**
	 * 查询场站辖区(时效)信息
	 * @return
	 */
	@JSON
	public String queryStationAgingInfo(){
		try {
			stationAgingVo.setStationAgingList(
					stationAgingService.queryStationAgingInfo(
					stationAgingVo.getStationAgingInfoParam(), start, limit));
		    totalCount = stationAgingService.queryStationAgingInfoCount(
		    		stationAgingVo.getStationAgingInfoParam());
		    return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 *新增场站辖区(时效)信息 
	 * @return
	 */
	@JSON
	public String addStationAgingInfo(){
		UserEntity user = (UserEntity)(UserContext.getCurrentUser());
		try {
			StationAgingInfo param = stationAgingVo.getStationAgingInfoParam();
			StationAgingInfo filterParam = new StationAgingInfo();
			filterParam.setOperatingUnitCode(param.getOperatingUnitCode());
			filterParam.setActive("Y");
			List<StationAgingInfo> lists = stationAgingService.queryStationAgingInfo(filterParam, 0, 10);
			if(lists.size() > 0){
				return returnError("已存在相同的作业单位代码数据，请重新添加!");
			}
			else{
				StationAgingInfo newInfo = new StationAgingInfo();
				newInfo.setOperatingUnitCode(param.getOperatingUnitCode());
				newInfo.setShortName(param.getShortName());
				newInfo.setTheArea(param.getTheArea());
				newInfo.setTheBusinessDepartmemt(param.getTheBusinessDepartmemt());
				newInfo.setActive("Y");
				newInfo.setCreateTime(new Date());//创建时间
		    	newInfo.setEffectedTime(new Date());//生效时间
		    	newInfo.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));//设置失效时间
		    	newInfo.setCreateUserCode(user.getEmpCode().substring(2));//保存新增数据用户的工号
				stationAgingService.addStationAgingInfo(newInfo);
				return returnSuccess("新增数据成功!");
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 修改场站辖区(时效)信息
	 * @return
	 */
	@JSON
	public String modifyStationAgingInfo(){
		UserEntity user = (UserEntity)(UserContext.getCurrentUser());
		try {
			StationAgingInfo param = stationAgingVo.getStationAgingInfoParam();
			StationAgingInfo filterParam = new StationAgingInfo();
			//根据Id来查询场站(时效)信息
			filterParam.setStationAgingId(param.getStationAgingId());
			filterParam.setActive("Y");
	        List<StationAgingInfo> oldInfo = stationAgingService.queryStationAgingInfo(filterParam, 0, 10);
	        //是否修改成本条数据
	        if(StringUtil.equals(oldInfo.get(0).getOperatingUnitCode(),param.getOperatingUnitCode())){
	        	stationAgingService.repealAndAddInfo(param, user.getEmpCode().substring(2));
	        	return returnSuccess("修改成功!");
	        }else{
	        	StationAgingInfo filters = new StationAgingInfo();
	        	filters.setOperatingUnitCode(param.getOperatingUnitCode());
	        	List<StationAgingInfo> infoLists = stationAgingService.queryStationAgingInfo(filters, 0, 10);
	        	if(infoLists.size()>0){
	        		return returnError("已存在相同的作业单位代码简称数据，请重新修改!");
	        	}else{
	        		stationAgingService.repealAndAddInfo(param, user.getEmpCode().substring(2));
	        		return returnSuccess("修改成功!");
	        	}
	        }
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 作废场站辖区(时效)信息
	 * @return
	 */
	@JSON
	public String repealStationAgingInfo(){
		UserEntity user = (UserEntity)(UserContext.getCurrentUser());
		try {
			StationAgingInfo param = stationAgingVo.getStationAgingInfoParam();
			StationAgingInfo newInfo = new StationAgingInfo();
			newInfo.setStationAgingId(param.getStationAgingId());
			newInfo.setActive("N");//作废成功时状态为失效
			newInfo.setInvalidTime(new Date());
			newInfo.setModifyTime(new Date());
			newInfo.setModifyUserCode(user.getEmpCode().substring(2));
			stationAgingService.updateStationAgingInfo(newInfo);
			return returnSuccess("作废成功");
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	public StationAgingVo getStationAgingVo() {
		return stationAgingVo;
	}
	public void setStationAgingVo(StationAgingVo stationAgingVo) {
		this.stationAgingVo = stationAgingVo;
	}
}
