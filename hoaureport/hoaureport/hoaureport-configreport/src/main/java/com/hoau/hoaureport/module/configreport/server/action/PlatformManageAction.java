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
import com.hoau.hoaureport.module.configreport.server.service.IPlatformManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.PlatformInfo;
import com.hoau.hoaureport.module.configreport.shared.vo.PlatformVo;
import com.hoau.hoaureport.module.util.DateUtils;

/**
 * 
 * ClassName: PlatformManageAction 
 * @author 刘镇松
 * @date 2016年8月17日
 * @version V1.0
 */
@Controller("platformManageAction")
@Scope("prototype")
public class PlatformManageAction extends AbstractAction{
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -876071950748381171L;
	private PlatformVo platformVo;
	@Resource
	IPlatformManageService platformManageService;
	/**
	 * 返回场站管理     首界面
	 * @return
	 */
	
	public String showPlatformPage(){
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
	public String queryPlatformInfo(){
		try {
			platformVo.setPlatformInfoList(platformManageService.queryPlatformInfo(platformVo.getPlatformInfoParam(),start,limit));
			totalCount = platformManageService.queryPlatformCount(platformVo.getPlatformInfoParam());
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
	public String addPlatformInfo(){
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		try {
			PlatformInfo param = platformVo.getPlatformInfoParam();
			PlatformInfo filterParam = new PlatformInfo();
			filterParam.setPlatformShortName(param.getPlatformShortName());
			filterParam.setActive("Y");
			List<PlatformInfo> infoList = platformManageService.queryPlatformInfo(filterParam, 0, 10);
			if(infoList.size() > 0){//数据已存在无须添加
				return returnError("已存在相同的平台简称数据,请重新添加!");
			}else{
				PlatformInfo newInfo = new PlatformInfo();
				newInfo.setPlatformName(param.getPlatformName());
				newInfo.setPlatformShortName(param.getPlatformShortName());
				newInfo.setIsHeadQuartersPlatform(param.getIsHeadQuartersPlatform());
				newInfo.setTheFleet(param.getTheFleet());
				newInfo.setTheRoadArea(param.getTheRoadArea());
				newInfo.setTheArea(param.getTheArea());
				newInfo.setTheBusinessDepartment(param.getTheBusinessDepartment());
				newInfo.setActive("Y");
				newInfo.setEffectedTime(new Date());
				newInfo.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
				newInfo.setCreateTime(new Date());
				newInfo.setCreateUserCode(user.getEmpCode().substring(2));//工号
				newInfo.setModifyTime(new Date());
				newInfo.setModifyUserCode(user.getEmpCode().substring(2));
				platformManageService.addPlatformInfo(newInfo);
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
	public String modifyPlatformInfo(){
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		try {
			PlatformInfo param = platformVo.getPlatformInfoParam();
			//根据ID查询数据库场站信息
			PlatformInfo filterParam = new PlatformInfo();
			filterParam.setPlatformId(param.getPlatformId());
			filterParam.setActive("Y");
			List<PlatformInfo> oldInfo = platformManageService.queryPlatformInfo(filterParam, 0, 1);
			//是否修改成本条数据
			if(StringUtil.equals(oldInfo.get(0).getPlatformShortName(), param.getPlatformShortName())){
				platformManageService.repealAndAddInfo(param,user.getEmpCode().substring(2));
				return returnSuccess("修改成功!");
			}else{
				PlatformInfo filters = new PlatformInfo();
				filters.setPlatformShortName(param.getPlatformShortName());
				filters.setActive("Y");
				List<PlatformInfo> infoList = platformManageService.queryPlatformInfo(filters, 0, 10);
				if(infoList.size() > 0){
					return returnError("已存在相同的平台简称数据,请重新修改!");
				}else{
					platformManageService.repealAndAddInfo(param,user.getEmpCode().substring(2));
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
	 * @return PlatformVo 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	@JSON
	public String repealPlatformInfo(){
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		try {
			    PlatformInfo param = platformVo.getPlatformInfoParam();
				PlatformInfo newInfo = new PlatformInfo();
				newInfo.setPlatformId(param.getPlatformId());
				newInfo.setActive("N");//失效
				newInfo.setInvalidTime(new Date());
				newInfo.setModifyTime(new Date());
				newInfo.setModifyUserCode(user.getEmpCode().substring(2));
				platformManageService.updatePlatformInfo(newInfo);
				return returnSuccess("作废成功!");
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	public PlatformVo getPlatformVo() {
		return platformVo;
	}
	public void setPlatformVo(PlatformVo platformVo) {
		this.platformVo = platformVo;
	}
}
