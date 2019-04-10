package com.hoau.hoaureport.module.configreport.server.action;

import java.util.Date;
import java.util.List;


import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.configreport.server.service.ILinePlanSupendedService;
import com.hoau.hoaureport.module.configreport.shared.domain.LinePlanSuspended;
import com.hoau.hoaureport.module.configreport.shared.vo.LinePlanSuspendedVo;


@Controller("linePlanSuspendedAction")
@Scope("prototype")
public class LinePlanSuspendedAction extends AbstractAction{
	
	private static final long serialVersionUID = 1L;
	private LinePlanSuspendedVo linePlanVo;
	
	@Resource
	ILinePlanSupendedService lineService;
	/**
	 * 首页
	 * @return
	 */
	public String showLinePlanSuspendedPage(){
		return "index";
	}
	/**
	 * 查询线路规划停发时间信息
	 * @return
	 */
	@JSON
	public String queryLinePlanSuspendedInfo(){
		 try {
			 linePlanVo.setLinePlanList(
					  lineService.queryLinePlanSupendedInfo(
							  linePlanVo.getLinePlanparam(), start, limit));
			totalCount = lineService.queryLinePlanSuspendedCount(linePlanVo.getLinePlanparam());
		     return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
/**
 * 新增线路规划停发时间 action	
 * @return
 */
	@JSON
	public String addLinePlanSuspendedInfo(){
		LinePlanSuspended param = linePlanVo.getLinePlanparam();
		try {
			if(lineService.isExist(param)){
				return returnError("已存在相同的线路规划停发时间，请重新添加");
			}else{
				 lineService.addLinePlanSuspendedInfo(param);
				 return returnSuccess("添加成功");
			}
		
		} catch (Exception e) {
              return returnError(e.toString());
		}
	}
	@JSON
	public String modifyLinePlanSuspendedInfo(){
		UserEntity user = (UserEntity)(UserContext.getCurrentUser());
		try {
			LinePlanSuspended param = linePlanVo.getLinePlanparam();
			LinePlanSuspended filterParam = new LinePlanSuspended();
			filterParam.setLinePlanId(param.getLinePlanId());
			filterParam.setActive("Y");
			List<LinePlanSuspended> oldInfo = lineService.queryLinePlanSupendedInfo(filterParam, 0, 10);
			if(StringUtil.equals(oldInfo.get(0).getStartWork(), param.getStartWork())||
			StringUtil.equals(oldInfo.get(0).getArriveWork(),param.getArriveWork())){
				lineService.repealAndAddInfo(param,user.getEmpCode().substring(2));
				return returnSuccess("修改成功!");
			}else{
				LinePlanSuspended filters = new LinePlanSuspended();
				filters.setStartWork(param.getStartWork());//起始运作
				filters.setArriveWork(param.getArriveWork());//到达运作
				filters.setDepartureTime(param.getDepartureTime());//发车时间
				filters.setShifts(param.getShifts());//班次
				filters.setActive("Y");
				if(lineService.isExist(filters)){
					return returnError("已存在相同线路规划停发信息，请重新修改");
				}else{
					lineService.repealAndAddInfo(param,user.getEmpCode().substring(2));
					return returnSuccess("修改成功");
				}
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
		
	}
	/**
	 * 作废
	 * @return
	 */
	@JSON
	public String repealLinePlanSuspendedInfo(){
		UserEntity user = (UserEntity)(UserContext.getCurrentUser());
		int count = 0;
		try {
			List<LinePlanSuspended> lists = linePlanVo.getLinePlanList();
			for(LinePlanSuspended items : lists){
				LinePlanSuspended oldInfo = new LinePlanSuspended();
				oldInfo.setLinePlanId(items.getLinePlanId());
				oldInfo.setActive("N");
				oldInfo.setInvalidTime(new Date());
				oldInfo.setModifyTime(new Date());
				oldInfo.setModifyUserCode(user.getEmpCode().substring(2));
				lineService.updateLinePlanSuspendedInfo(oldInfo);
				count++;
			}
			return returnSuccess("作废成功!共作废"+ count + "条记录");
		} catch (Exception e) {
			return returnError(e.toString());
		}
		
	}
	/**
	 * 导入Excel
	 * @return
	 * @throws Exception
	 */
	@JSON
	public String implExcel() throws Exception{
		try {
			String uploadPath = ServletActionContext.getServletContext().getRealPath(linePlanVo.getFilePath());
			Map<String,Object> returnMap = lineService.bathImplLinePlanSuspended(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){
				return returnError(returnMap.get("error").toString());				
			}
			
			linePlanVo.setAddSize(returnMap.get("addSize").toString());
			linePlanVo.setCoverSize(returnMap.get("coverSize").toString());
			linePlanVo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	/**
	 * 作废所有数据
	 * @return
	 */
	@JSON
   public String repealAllLinePlanSuspendedInfo(){
	 try {
		 lineService.repealAll();
		  return returnSuccess("全部作废成功");
	} catch (BusinessException e) {
	    return returnError(e);
	}
   }
	public LinePlanSuspendedVo getLinePlanVo() {
		return linePlanVo;
	}
	public void setLinePlanVo(LinePlanSuspendedVo linePlanVo) {
		this.linePlanVo = linePlanVo;
	}
}



