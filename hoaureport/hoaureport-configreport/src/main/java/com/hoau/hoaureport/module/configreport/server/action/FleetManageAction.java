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
import com.hoau.hoaureport.module.configreport.server.service.IFleetManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.FleetInfo;
import com.hoau.hoaureport.module.configreport.shared.vo.FleetVo;
import com.hoau.hoaureport.module.util.DateUtils;

/**
 * 车队管理
 * ClassName: FleetManageAction 
 * @author 刘镇松
 * @date 2016年8月17日
 * @version V1.0
 */
@Controller("fleetManageAction")
@Scope("prototype")
public class FleetManageAction extends AbstractAction{
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1083947315816034524L;
	private FleetVo fleetVo;
	@Resource
	IFleetManageService fleetManageService;
	/**
	 * 返回场站管理     首界面
	 * @return
	 */
	
	public String showFleetPage(){
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
	public String queryFleetInfo(){
		try {
			fleetVo.setFleetInfoList(fleetManageService.queryFleetInfo(fleetVo.getFleetInfoParam(),start,limit));
			totalCount = fleetManageService.queryFleetCount(fleetVo.getFleetInfoParam());
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
	public String addFleetInfo(){
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		try {
			FleetInfo param = fleetVo.getFleetInfoParam();
			FleetInfo filterParam = new FleetInfo();
			filterParam.setFleetShortName(param.getFleetShortName());
			filterParam.setActive("Y");
			List<FleetInfo> infoList = fleetManageService.queryFleetInfo(filterParam, 0, 10);
			if(infoList.size() > 0){//数据已存在无须添加
				return returnError("已存在相同的车队简称数据,请重新添加!");
			}else{
				FleetInfo newInfo = new FleetInfo();
				newInfo.setFleetName(param.getFleetName());
				newInfo.setFleetShortName(param.getFleetShortName());
				newInfo.setTheArea(param.getTheArea());
				newInfo.setTheBusinessDepartment(param.getTheBusinessDepartment());
				newInfo.setActive("Y");
				newInfo.setEffectedTime(new Date());
				newInfo.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
				newInfo.setCreateTime(new Date());
				newInfo.setCreateUserCode(user.getEmpCode().substring(2));//工号
				newInfo.setModifyTime(new Date());
				newInfo.setModifyUserCode(user.getEmpCode().substring(2));
				fleetManageService.addFleetInfo(newInfo);
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
	public String modifyFleetInfo(){
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		try {
			FleetInfo param = fleetVo.getFleetInfoParam();
			//根据ID查询数据库场站信息
			FleetInfo filterParam = new FleetInfo();
			filterParam.setFleetId(param.getFleetId());
			filterParam.setActive("Y");
			List<FleetInfo> oldInfo = fleetManageService.queryFleetInfo(filterParam, 0, 10);
			//是否修改成本条数据
			if(StringUtil.equals(oldInfo.get(0).getFleetShortName(), param.getFleetShortName())){
				fleetManageService.repealAndAddInfo(param,user.getEmpCode().substring(2));
				return returnSuccess("修改成功!");
			}else{
				FleetInfo filters = new FleetInfo();
				filters.setFleetShortName(param.getFleetShortName());
				filters.setActive("Y");
				List<FleetInfo> infoList = fleetManageService.queryFleetInfo(filters, 0, 10);
				if(infoList.size() > 0){
					return returnError("已存在相同的车队简称数据,请重新修改!");
				}else{
					fleetManageService.repealAndAddInfo(param,user.getEmpCode().substring(2));
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
	 * @return FleetVo 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	@JSON
	public String repealFleetInfo(){
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
			try {
				    FleetInfo param = fleetVo.getFleetInfoParam();
					FleetInfo newInfo = new FleetInfo();
					newInfo.setFleetId(param.getFleetId());
					newInfo.setActive("N");//失效
					newInfo.setInvalidTime(new Date());
					newInfo.setModifyTime(new Date());
					newInfo.setModifyUserCode(user.getEmpCode().substring(2));
					fleetManageService.updateFleetInfo(newInfo);
					return returnSuccess("作废成功!");
		} catch (Exception e) {
			return returnError(e.toString());
		}
}
	public FleetVo getFleetVo() {
		return fleetVo;
	}
	public void setFleetVo(FleetVo fleetVo) {
		this.fleetVo = fleetVo;
	}
}
