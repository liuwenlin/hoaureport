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
import com.hoau.hoaureport.module.configreport.server.service.IStationManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.StationInfo;
import com.hoau.hoaureport.module.configreport.shared.vo.StationVo;
import com.hoau.hoaureport.module.util.DateUtils;

/**
 * 场站管理
 * ClassName: StationManageAction 
 * @author 刘镇松
 * @date 2016年8月15日
 * @version V1.0
 */
@Controller("stationManageAction")
@Scope("prototype")
public class StationManageAction extends AbstractAction{
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -7838581150846822657L;
	private StationVo stationVo;
	@Resource
	IStationManageService stationManageService;
	/**
	 * 返回场站管理     首界面
	 * @return
	 */
	
	public String showStationPage(){
		return "index";
	}
	/**
	 * 
	 * @Description: 根据条件查询 场站信息
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	@JSON
	public String queryStationInfo(){
		try {
			stationVo.setStationInfoList(stationManageService.queryStationInfo(stationVo.getStationInfoParam(),start,limit));
			totalCount = stationManageService.queryStationCount(stationVo.getStationInfoParam());
			return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	/**
	 * 
	 * @Description: 新增场站信息
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	@JSON
	public String addStationInfo(){
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		try {
			StationInfo param = stationVo.getStationInfoParam();
			StationInfo filterParam = new StationInfo();
			filterParam.setStationsShortName(param.getStationsShortName());
			filterParam.setActive("Y");
			List<StationInfo> infoList = stationManageService.queryStationInfo(filterParam, 0, 10);
			if(infoList.size() > 0){//数据已存在无须添加
				return returnError("已存在相同的场站简称数据,请重新添加!");
			}else{
				StationInfo newInfo = new StationInfo();
				newInfo.setStationsName(param.getStationsName());
				newInfo.setStationsShortName(param.getStationsShortName());
				newInfo.setTheArea(param.getTheArea());
				newInfo.setTheBusinessDepartment(param.getTheBusinessDepartment());
				newInfo.setActive("Y");
				newInfo.setEffectedTime(new Date());
				newInfo.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
				newInfo.setCreateTime(new Date());
				newInfo.setCreateUserCode(user.getEmpCode().substring(2));//工号
				newInfo.setModifyTime(new Date());
				newInfo.setModifyUserCode(user.getEmpCode().substring(2));
				
				newInfo.setLeader(param.getLeader());
				newInfo.setPhone(param.getPhone());
				newInfo.setAddress(param.getAddress());
				newInfo.setWarehouseArea(param.getWarehouseArea());
				newInfo.setDeliveryArea(param.getDeliveryArea());
				newInfo.setArrivalArea(param.getArrivalArea());
				
				stationManageService.addStationInfo(newInfo);
				return returnSuccess("添加成功!");
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	/**
	 * 
	 * @Description: 修改
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	@JSON
	public String modifyStationInfo(){
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		try {
			StationInfo param = stationVo.getStationInfoParam();
			//根据ID查询数据库场站信息
			StationInfo filterParam = new StationInfo();
			filterParam.setStationsId(param.getStationsId());
			filterParam.setActive("Y");
			List<StationInfo> oldInfo = stationManageService.queryStationInfo(filterParam, 0, 10);
			//是否修改成本条数据
			if(StringUtil.equals(oldInfo.get(0).getStationsShortName(), param.getStationsShortName())){
				stationManageService.repealAndAddInfo(param,user.getEmpCode().substring(2));
				return returnSuccess("修改成功!");
			}else{
				StationInfo filters = new StationInfo();
				filters.setStationsShortName(param.getStationsShortName());
				filters.setActive("Y");
				List<StationInfo> infoList = stationManageService.queryStationInfo(filters, 0, 10);
				if(infoList.size() > 0){
					return returnError("已存在相同的场站简称数据,请重新修改!");
				}else{
					stationManageService.repealAndAddInfo(param,user.getEmpCode().substring(2));
					return returnSuccess("修改成功!");
				}
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	/**
	 * 
	 * @Description: 作废
	 * @param @return   
	 * @return StationVo 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	@JSON
	public String repealStationInfo(){
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		try {
			    StationInfo param = stationVo.getStationInfoParam();
				StationInfo newInfo = new StationInfo();
				newInfo.setStationsId(param.getStationsId());
				newInfo.setActive("N");//失效
				newInfo.setInvalidTime(new Date());
				newInfo.setModifyTime(new Date());
				newInfo.setModifyUserCode(user.getEmpCode().substring(2));
				stationManageService.updateStationInfo(newInfo);
				return returnSuccess("作废成功!");
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	public StationVo getStationVo() {
		return stationVo;
	}
	public void setStationVo(StationVo stationVo) {
		this.stationVo = stationVo;
	}
}
