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
import com.hoau.hoaureport.module.configreport.server.service.IPlatformAreaManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.PlatformAreaInfo;
import com.hoau.hoaureport.module.configreport.shared.vo.PlatformAreaVo;
import com.hoau.hoaureport.module.util.DateUtils;

/**
 * 
 * ClassName: PlatformAreaManageAction 
 * @author 刘镇松
 * @date 2016年8月18日
 * @version V1.0
 */
@Controller("platformAreaManageAction")
@Scope("prototype")
public class PlatformAreaManageAction extends AbstractAction{
	
	
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 8868524288920181908L;
	private PlatformAreaVo platformAreaVo;
	@Resource
	IPlatformAreaManageService platformAreaManageService;
	/**
	 * 返回平台辖区管理     首界面
	 * @return
	 */
	
	public String showPlatformAreaPage(){
		return "index";
	}
	/**
	 * 
	 * @Description: 根据条件查询 平台辖区信息
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	@JSON
	public String queryPlatformAreaInfo(){
		try {
			platformAreaVo.setPlatformAreaInfoList(platformAreaManageService.queryPlatformAreaInfo(platformAreaVo.getPlatformAreaInfoParam(),start,limit));
			totalCount = platformAreaManageService.queryPlatformAreaCount(platformAreaVo.getPlatformAreaInfoParam());
			return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	/**
	 * 
	 * @Description: 新增平台辖区信息
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	@JSON
	public String addPlatformAreaInfo(){
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		try {
			PlatformAreaInfo param = platformAreaVo.getPlatformAreaInfoParam();
			PlatformAreaInfo fileterParam = new PlatformAreaInfo();
			fileterParam.setAreaOperatingUnitShortName(param.getAreaOperatingUnitShortName());
			fileterParam.setActive("Y");
			List<PlatformAreaInfo> infoList = platformAreaManageService.queryPlatformAreaInfo(fileterParam, 0, 10);
			if(infoList.size() > 0){//数据已存在无须添加
				return returnError("已存在相同的辖区作业单位简称数据,请重新添加!");
			}else{
				PlatformAreaInfo newInfo = new PlatformAreaInfo();
				newInfo.setAreaOperatingUnitShortName(param.getAreaOperatingUnitShortName());
				newInfo.setThePlatFormAreaShortName(param.getThePlatFormAreaShortName());
				newInfo.setTheArea(param.getTheArea());
				newInfo.setTheBusinessDepartment(param.getTheBusinessDepartment());
				newInfo.setActive("Y");
				newInfo.setEffectedTime(new Date());
				newInfo.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
				newInfo.setCreateTime(new Date());
				newInfo.setCreateUserCode(user.getEmpCode().substring(2));//工号
				newInfo.setModifyTime(new Date());
				newInfo.setModifyUserCode(user.getEmpCode().substring(2));
				platformAreaManageService.addPlatformAreaInfo(newInfo);
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
	public String modifyPlatformAreaInfo(){
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		try {
			PlatformAreaInfo param = platformAreaVo.getPlatformAreaInfoParam();
			//根据ID查询数据库平台辖区信息
			PlatformAreaInfo filterParam = new PlatformAreaInfo();
			filterParam.setPlatformAreaId(param.getPlatformAreaId());
			filterParam.setActive("Y");
			List<PlatformAreaInfo> oldInfo = platformAreaManageService.queryPlatformAreaInfo(filterParam, 0, 10);
			//是否修改成本条数据
			if(StringUtil.equals(oldInfo.get(0).getAreaOperatingUnitShortName(), param.getAreaOperatingUnitShortName())){
				platformAreaManageService.repealAndAddInfo(param,user.getEmpCode().substring(2));
				return returnSuccess("修改成功!");
			}else{
				PlatformAreaInfo filters = new PlatformAreaInfo();
				filters.setAreaOperatingUnitShortName(param.getAreaOperatingUnitShortName());
				filters.setActive("Y");
				List<PlatformAreaInfo> infoList = platformAreaManageService.queryPlatformAreaInfo(filters, 0, 10);
				if(infoList.size() > 0){
					return returnError("已存在相同的辖区作业单位简称数据,请重新修改!");
				}else{
					platformAreaManageService.repealAndAddInfo(param,user.getEmpCode().substring(2));
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
	 * @return PlatformAreaVo 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月16日
	 */
	@JSON
	public String repealPlatformAreaInfo(){
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		int count = 0;
		try {
			List<PlatformAreaInfo> params = platformAreaVo.getPlatformAreaInfoList();
			    for (PlatformAreaInfo  param : params) {
					PlatformAreaInfo newInfo = new PlatformAreaInfo();
					newInfo.setPlatformAreaId(param.getPlatformAreaId());
					newInfo.setActive("N");//失效
					newInfo.setInvalidTime(new Date());
					newInfo.setModifyTime(new Date());
					newInfo.setModifyUserCode(user.getEmpCode().substring(2));
					platformAreaManageService.updatePlatformAreaInfo(newInfo);
					count++;
				}
			    return returnSuccess("作废成功！共作废" + count + "条记录");
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/** 
	 * 
	 * @Description: excel解析并插入到数据库
	 * @return String 
	 * @author 文洁
	 * @date 2016年11月02日
	 */
	@JSON
	public String implExcel() throws Exception{
		try {
			String uploadPath = ServletActionContext.getServletContext().getRealPath(platformAreaVo.getFilePath());
			Map<String,Object> returnMap= platformAreaManageService.bathImplPlatformAreaInfo(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//JSONObject json=new JSONObject();
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			platformAreaVo.setAddSize(returnMap.get("addSize").toString());
			platformAreaVo.setCoverSize(returnMap.get("coverSize").toString());
			platformAreaVo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	/**
	 * 
	 * @Description: 作废所有的数据
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年11月11日
	 */
	@JSON
	public String repealAll(){
		try {
			platformAreaManageService.repealAllPlatformArea();
			return returnSuccess("全部作废成功");
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	
	public PlatformAreaVo getPlatformAreaVo() {
		return platformAreaVo;
	}
	public void setPlatformAreaVo(PlatformAreaVo platformAreaVo) {
		this.platformAreaVo = platformAreaVo;
	}
}
