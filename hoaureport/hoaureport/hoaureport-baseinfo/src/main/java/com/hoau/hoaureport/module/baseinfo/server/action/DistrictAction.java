package com.hoau.hoaureport.module.baseinfo.server.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.server.components.security.SecurityNonCheckRequired;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IDistrictService;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.DistrictVo;

/**
 * @author：高佳
 * @create：2015年6月30日 下午6:44:42
 * @description：
 */
@Controller
@Scope("prototype")
public class DistrictAction extends AbstractAction{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IDistrictService districtService;
	
	private DistrictVo districtVo;
	
	public String queryDistrictByName(){
		districtVo.setDistrictList(districtService.queryDistrictByEntity(districtVo.getDistrict(),limit,start));
		return returnSuccess();
	}
	
	/**
	 * 查询所有国家信息
	 * @return
	 * @author 高佳
	 * @date 2015年7月9日
	 * @update 
	 */
	@SecurityNonCheckRequired
	public String queryAllNation(){
		districtVo = new DistrictVo();
		districtVo.setDistrictList(districtService.queryAllNation(limit,start));
		return returnSuccess();
	}
	
	/**
	 * 查询省份
	 * @return
	 * @author 高佳
	 * @date 2015年7月9日
	 * @update 
	 */
	@SecurityNonCheckRequired
	public String queryProvince(){
		districtVo.getDistrict().setDistrictType("PROVINCE");
		districtVo.setDistrictList(districtService.queryDistrictByEntity(districtVo.getDistrict(), limit, start));
		return returnSuccess();
	}
	@SecurityNonCheckRequired
	public String queryCity(){
		districtVo.getDistrict().setDistrictType("CITY");
		districtVo.setDistrictList(districtService.queryDistrictByEntity(districtVo.getDistrict(), limit, start));
		return returnSuccess();
	}
	@SecurityNonCheckRequired
	public String queryArea(){
		districtVo.getDistrict().setDistrictType("AREA");
		districtVo.setDistrictList(districtService.queryDistrictByEntity(districtVo.getDistrict(), limit, start));
		return returnSuccess();
	}
	public DistrictVo getDistrictVo() {
		return districtVo;
	}

	public void setDistrictVo(DistrictVo districtVo) {
		this.districtVo = districtVo;
	}
	
	
	

}
