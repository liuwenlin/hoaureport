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
import com.hoau.hoaureport.module.configreport.server.service.ICityPriceCardManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.CityPriceCard;
import com.hoau.hoaureport.module.configreport.shared.vo.CityPriceCardVo;

/**
 * 城市价卡信息管理Action
 * ClassName: CityPriceCardManageAction 
 * @author 文洁
 * @date 2016年12月15日
 * @version V1.0
 */
@Controller("cityPriceCardManageAction")
@Scope("prototype")
public class CityPriceCardManageAction extends AbstractAction{


/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 6615554441521700742L;

private CityPriceCardVo cityPriceCardVo;

	@Resource
	ICityPriceCardManageService cityPriceCardManageService;
	
	/**
	 * 
	 * @Description:返回 城市价卡管理 首界面
	 * @return index字符串 
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	public String showCityPriceCardPage() {
		return "index";
	}
	
	/**
	 * 
	 * @Description:根据条件查询 城市价卡信息
	 * @return String
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	@JSON
	public String queryCityPriceCard(){
		try {
			cityPriceCardVo.setCityPriceCardList(cityPriceCardManageService.queryCityPriceCard(cityPriceCardVo.getCityPriceCard(),start,limit));
			totalCount = cityPriceCardManageService.queryCityPriceCardCount(cityPriceCardVo.getCityPriceCard());
			return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:增加城市价卡信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	@JSON
	public String addCityPriceCard(){
		CityPriceCard param = cityPriceCardVo.getCityPriceCard();
		try {
			if(cityPriceCardManageService.isExist(param)){//数据已存在无须添加
				return returnError("已存在相同的城市价卡代码,请重新添加！");
			}else{
				CityPriceCard newInfo = new CityPriceCard();
                newInfo.setLineName(param.getLineName());
				newInfo.setLoadingPort(param.getLoadingPort());
				newInfo.setTermini(param.getTermini());
				newInfo.setCityLine(param.getCityLine());
				newInfo.setDispatchCityId(param.getDispatchCityId());
				newInfo.setArrivalCityId(param.getArrivalCityId());
				newInfo.setDispatchCity(param.getDispatchCity());
				newInfo.setArrivalCity(param.getArrivalCity());
				newInfo.setServeLoadIntime(param.getServeLoadIntime());
				newInfo.setFromRemoteBranch(param.getFromRemoteBranch());
				newInfo.setServeReachIntime(param.getServeReachIntime());
				newInfo.setToRemoteBranch(param.getToRemoteBranch());
				newInfo.setPromiseNoteTime(param.getPromiseNoteTime());
				newInfo.setPromiseDeliverTime(param.getPromiseDeliverTime());
				newInfo.setOpenMonth(param.getOpenMonth());
				newInfo.setMonday(param.getMonday());
				newInfo.setTuesday(param.getTuesday());
				newInfo.setWednesday(param.getWednesday());
				newInfo.setThursday(param.getThursday());
				newInfo.setFriday(param.getFriday());
				newInfo.setSaturday(param.getSaturday());
				newInfo.setSunday(param.getSunday());
				newInfo.setNote(param.getNote());
				
				cityPriceCardManageService.addCityPriceCard(newInfo);
				return returnSuccess("添加成功！");
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:修改城市价卡信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	@JSON
	public String modifyCityPriceCard(){
		try {
			CityPriceCard param = cityPriceCardVo.getCityPriceCard();
			//根据ID查询数据库城市价卡信息
			CityPriceCard filterParam = new CityPriceCard();
			filterParam.setCityPriceCardId(param.getCityPriceCardId());
			filterParam.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
			List<CityPriceCard> oldInfo = cityPriceCardManageService.queryCityPriceCardByExplicitCondition(filterParam, 0, 10);
			//是否修改成本条数据
			if(StringUtil.equals(oldInfo.get(0).getDispatchCity(), param.getDispatchCity())&&
			   StringUtil.equals(oldInfo.get(0).getArrivalCity(), param.getArrivalCity())){
				//在发货城市和到货城市没有变化时，城市编号也不改变
				param.setDispatchCityId(oldInfo.get(0).getDispatchCityId());
				param.setArrivalCityId(oldInfo.get(0).getArrivalCityId());
				cityPriceCardManageService.repealAndAddCityPriceCard(param);
				return returnSuccess("修改成功!");
			}else{
				CityPriceCard filters = new CityPriceCard();
				filters.setDispatchCity(param.getDispatchCity());
				filters.setArrivalCity(param.getArrivalCity());
				filters.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
				List<CityPriceCard> infoList = cityPriceCardManageService.queryCityPriceCardByExplicitCondition(filters, 0, 10);
				if(infoList.size() > 0){
					return returnError("已存在相同的城市价卡代码,请重新修改!");
				}else{
					cityPriceCardManageService.repealAndAddCityPriceCard(param);
					return returnSuccess("修改成功!");
				}
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:城市价卡作废
	 * @return String 
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	@JSON
	public String repealCityPriceCard(){
		int count = 0;
		try {
			List<CityPriceCard> params = cityPriceCardVo.getCityPriceCardList();
			for (CityPriceCard param : params) {
				cityPriceCardManageService.repealCityPriceCard(param);
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
	 * @date 2016年12月15日
	 */
	@JSON
	public String implExcel() throws Exception{
		try {
			String uploadPath = ServletActionContext.getServletContext().getRealPath(cityPriceCardVo.getFilePath());
			Map<String,Object> returnMap= cityPriceCardManageService.bathImplCityPriceCard(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//JSONObject json=new JSONObject();
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			cityPriceCardVo.setAddSize(returnMap.get("addSize").toString());
			cityPriceCardVo.setCoverSize(returnMap.get("coverSize").toString());
			cityPriceCardVo.setSumSize(returnMap.get("sumSize").toString());
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
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	@JSON
	public String repealAll(){
		try {
			cityPriceCardManageService.repealAllCityPriceCard();
			return returnSuccess("全部作废成功");
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	public CityPriceCardVo getCityPriceCardVo() {
		return cityPriceCardVo;
	}

	public void setCityPriceCardVo(CityPriceCardVo cityPriceCardVo) {
		this.cityPriceCardVo = cityPriceCardVo;
	}
	
}
