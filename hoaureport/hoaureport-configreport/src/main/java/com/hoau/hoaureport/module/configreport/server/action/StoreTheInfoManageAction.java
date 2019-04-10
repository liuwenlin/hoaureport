package com.hoau.hoaureport.module.configreport.server.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.common.server.constants.ItfConifgConstant;
import com.hoau.hoaureport.module.configreport.server.service.IStoreTheInfoManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.StoreTheInfo;
import com.hoau.hoaureport.module.configreport.shared.vo.StoreTheInfoVo;

/**
 * 门店信息管理Action
 * ClassName: StoreTheInfoManageAction 
 * @author 文洁
 * @date 2016年9月12日
 * @version V1.0
 */
@Controller("storeTheInfoManageAction")
@Scope("prototype")
public class StoreTheInfoManageAction extends AbstractAction{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -4084872008451841410L;

private StoreTheInfoVo storeTheInfoVo;

	@Resource
	IStoreTheInfoManageService storeTheInfoManageService;
	
	/**
	 * 
	 * @Description:返回 门店管理 首界面
	 * @return index字符串 
	 * @author 文洁
	 * @date 2016年9月12日
	 */
	public String showStoreTheInfoPage() {
		return "index";
	}
	
	/**
	 * 
	 * @Description:根据条件查询 门店信息
	 * @return String
	 * @author 文洁
	 * @date 2016年9月12日
	 */
	@JSON
	public String queryStoreTheInfo(){
		try {
			storeTheInfoVo.setStoreTheInfoList(storeTheInfoManageService.queryStoreTheInfo(storeTheInfoVo.getStoreTheInfo(),start,limit));
			totalCount = storeTheInfoManageService.queryStoreTheInfoCount(storeTheInfoVo.getStoreTheInfo());
			return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:增加门店信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年9月12日
	 */
	@JSON
	public String addStoreTheInfo(){
		StoreTheInfo param = storeTheInfoVo.getStoreTheInfo();
		try {
			if(storeTheInfoManageService.isExist(param)){//数据已存在无须添加
				return returnError("已存在相同的门店代码,请重新添加！");
			}else{
				StoreTheInfo newInfo = new StoreTheInfo();
				newInfo.setStoreCode(param.getStoreCode());
				newInfo.setStoreName(param.getStoreName());
				newInfo.setTheRoadArea(param.getTheRoadArea());
				newInfo.setTheArea(param.getTheArea());
				newInfo.setTheBusinessDepartment(param.getTheBusinessDepartment());
				newInfo.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
				
				storeTheInfoManageService.addStoreTheInfo(newInfo);
				return returnSuccess("添加成功！");
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:修改门店信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年9月12日
	 */
	@JSON
	public String modifyStoreTheInfo(){
		try {
			StoreTheInfo param = storeTheInfoVo.getStoreTheInfo();
			//根据ID查询数据库门店信息
			StoreTheInfo filterParam = new StoreTheInfo();
			filterParam.setStoreId(param.getStoreId());
			filterParam.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
			List<StoreTheInfo> oldInfo = storeTheInfoManageService.queryStoreTheInfoByExplicitCondition(filterParam, 0, 10);
			//是否修改成本条数据
			if(StringUtil.equals(oldInfo.get(0).getStoreCode(), param.getStoreCode())){
				storeTheInfoManageService.repealAndAddStoreTheInfo(param);
				return returnSuccess("修改成功!");
			}else{
				StoreTheInfo filters = new StoreTheInfo();
				filters.setStoreCode(param.getStoreCode());
				filters.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
				List<StoreTheInfo> infoList = storeTheInfoManageService.queryStoreTheInfoByExplicitCondition(filters, 0, 10);
				if(infoList.size() > 0){
					return returnError("已存在相同的门店代码,请重新修改!");
				}else{
					storeTheInfoManageService.repealAndAddStoreTheInfo(param);
					return returnSuccess("修改成功!");
				}
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:门店作废
	 * @return String 
	 * @author 文洁
	 * @date 2016年9月12日
	 */
	@JSON
	public String repealStoreTheInfo(){
		int count = 0;
		try {
			List<StoreTheInfo> params = storeTheInfoVo.getStoreTheInfoList();
			for (StoreTheInfo param : params) {
				storeTheInfoManageService.repealStoreTheInfo(param);
				count++;
			}
			return returnSuccess("作废成功！共作废" + count + "条记录");
		} catch (Exception e) {
			e.printStackTrace();
			return returnError("e.toString()");
		}
	}

	/** 
	 * 
	 * @Description: excel解析并插入到数据库
	 * @return String 
	 * @author 文洁
	 * @date 2016年10月24日
	 */
	@JSON
	public String implExcel() throws Exception{
		try {
			String uploadPath = ServletActionContext.getServletContext().getRealPath(storeTheInfoVo.getFilePath());
			Map<String,Object> returnMap= storeTheInfoManageService.bathImplStoreTheInfo(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//JSONObject json=new JSONObject();
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			storeTheInfoVo.setAddSize(returnMap.get("addSize").toString());
			storeTheInfoVo.setCoverSize(returnMap.get("coverSize").toString());
			storeTheInfoVo.setSumSize(returnMap.get("sumSize").toString());
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
			storeTheInfoManageService.repealAllStoreTheInfo();
			return returnSuccess("全部作废成功");
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	public StoreTheInfoVo getStoreTheInfoVo() {
		return storeTheInfoVo;
	}

	public void setStoreTheInfoVo(StoreTheInfoVo storeTheInfoVo) {
		this.storeTheInfoVo = storeTheInfoVo;
	}
	
}
