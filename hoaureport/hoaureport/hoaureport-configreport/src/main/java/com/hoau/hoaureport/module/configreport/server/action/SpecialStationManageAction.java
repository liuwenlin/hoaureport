package com.hoau.hoaureport.module.configreport.server.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.jpush.api.report.UsersResult.User;

import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.configreport.server.service.ISpecialStationManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.SpecialStationInfo;
import com.hoau.hoaureport.module.configreport.shared.vo.SpecialStationVo;
import com.hoau.hoaureport.module.util.DateUtils;


/**
 * 
 *
 * @Descripation:场站特许Action
 * @author:liangling 
 * @date:2016年11月22日 上午11:18:06
 *
 */
@Controller("specialStationManageAction")
@Scope("prototype")
public class SpecialStationManageAction extends AbstractAction{
	
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -4870687142106518837L;
    
	private SpecialStationVo specialStationVo;
	@Resource
	ISpecialStationManageService specialStationManageService;
	
	
	/**
	 * 返回场站特许管理     首界面
	 * @return
	 */	
	public String showSpecialStationPage(){
		return "index";
	}
	
	
	/**
	 * 
	 * @Description:根据条件查询场站特许信息
	 * @param  @return
	 * @return String
	 * @exception:
	 * @author: liangling
	 * @time:2016年11月22日  上午11:19:40
	 *
	 */
	@JSON
	public String querySpecialStationInfo(){
		try {
			specialStationVo.setSpecialStationInfoList(
					specialStationManageService.querySpecialStationInfo(
					specialStationVo.getSpecialStationInfoParam(), start, limit));
			//totalCount 场站特许记录的总条数
		    totalCount =specialStationManageService.querySpecialStationCount(
		    		specialStationVo.getSpecialStationInfoParam());
		    return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:新增场站特许信息
	 * @param  @return
	 * @return String
	 * @exception:
	 * @author: liangling
	 * @time:2016年11月22日  上午11:20:36
	 *
	 */
	public String addSpecialStationInfo(){
		UserEntity user = (UserEntity)(UserContext.getCurrentUser());
	   try {
		   SpecialStationInfo param = specialStationVo.getSpecialStationInfoParam();
		    SpecialStationInfo filterParam = new SpecialStationInfo();
		    filterParam.setStationsShortName(param.getStationsShortName());
		    filterParam.setActive("Y");
		    //查询场站
		    List<SpecialStationInfo> lists = 
		    		specialStationManageService.querySpecialStationInfo(filterParam, 0, 10);
		    if(lists.size()>0){//
		    	return returnError("已存在相同的场站简称，请重新添加！");
		    }else{
		    	SpecialStationInfo newInfo = new SpecialStationInfo();
		    	newInfo.setStationsName(param.getStationsName());
		    	newInfo.setStationsShortName(param.getStationsShortName());
		    	newInfo.setTheBusinessDepartment(param.getTheBusinessDepartment());
		    	newInfo.setTheArea(param.getTheArea());
		    	newInfo.setActive("Y");
		    	newInfo.setCreateTime(new Date());//创建时间
		    	newInfo.setEffectedTime(new Date());//生效时间
		    	newInfo.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));//设置失效时间
		    	newInfo.setCreateUserCode(user.getEmpCode().substring(2));//保存新增数据用户的工号
		    	specialStationManageService.addSpecialStationInfo(newInfo);
		    	return returnSuccess("新增场站成功");
		    }
	} catch (Exception e) {
		return returnError(e.toString());
	}
	}
	
	/**
	 * 
	 * @Description:修改场站特许信息
	 * @param  @return
	 * @return String
	 * @exception:
	 * @author: liangling
	 * @time:2016年11月22日  上午11:21:39
	 *
	 */
	@JSON
	public String modifySpecialSpecialStationInfo(){
		UserEntity user = (UserEntity)(UserContext.getCurrentUser());
		try {
			SpecialStationInfo param = specialStationVo.getSpecialStationInfoParam();
			SpecialStationInfo filterParam = new SpecialStationInfo();
			//根据ID查询场站特许信息
			filterParam.setSpecialStationsId(param.getSpecialStationsId());
			filterParam.setActive("Y");
			List<SpecialStationInfo> oldInfo = specialStationManageService.querySpecialStationInfo(filterParam, 0, 10);
			//是否修改为本条数据
			if(StringUtil.equals(oldInfo.get(0).getStationsShortName(),param.getStationsShortName())){
				specialStationManageService.repealAndAddInfo(param, user.getEmpCode().substring(2));
				return returnSuccess("修改成功");
			}else{
				SpecialStationInfo filters = new SpecialStationInfo();
				filters.setStationsShortName(param.getStationsShortName());
				filters.setActive("Y");//设置是否有效状态为  ：Y（是）
				//查询是否存在相同简称的场站信息
				List<SpecialStationInfo> lists = specialStationManageService.querySpecialStationInfo(filters, 0, 10);
				if(lists.size()>0){
					return returnError("已存在相同场站简称数据，请重新输入场站简称！");
				}else{
					specialStationManageService.repealAndAddInfo(param, user.getEmpCode().substring(2));
					return returnSuccess("修改成功");
				}
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:作废场站特许信息
	 * @param  @return
	 * @return String
	 * @exception:
	 * @author: liangling
	 * @time:2016年11月22日  上午11:22:09
	 *
	 */
	@JSON
	public String repealSpecialStationInfo(){
		UserEntity user = (UserEntity)(UserContext.getCurrentUser());
		     try {
		    	  SpecialStationInfo param = specialStationVo.getSpecialStationInfoParam();
			       SpecialStationInfo newInfo = new SpecialStationInfo();
			      //获取要作废数据的ID
			       newInfo.setSpecialStationsId(param.getSpecialStationsId());
			      //将是否有效设置为N（否）即作废
			       newInfo.setActive("N");
			       //失效时间
			       newInfo.setInvalidTime(new Date());
			       //工号
			       newInfo.setModifyUserCode(user.getEmpCode().substring(2));
			       newInfo.setModifyTime(new Date());
			       specialStationManageService.updateSpecialStationInfo(newInfo);
			       return returnSuccess("作废成功");
			} catch (Exception e) {
				return returnError(e.toString());
			}
		
	}
	public SpecialStationVo getSpecialStationVo() {
		return specialStationVo;
	}
	public void setSpecialStationVo(SpecialStationVo specialStationVo) {
		this.specialStationVo = specialStationVo;
	}
	
}
