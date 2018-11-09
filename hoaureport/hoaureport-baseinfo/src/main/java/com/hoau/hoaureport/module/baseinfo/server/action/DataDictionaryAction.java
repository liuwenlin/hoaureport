package com.hoau.hoaureport.module.baseinfo.server.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IDataDictionaryService;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IDataDictionaryValueService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.DataDictionaryValueEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.MessageType;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.DataDictionaryVo;

/**
 * @author：高佳
 * @create：2015年6月9日 上午9:01:33
 * @description：数据字典管理
 */
@Controller
@Scope("prototype")
public class DataDictionaryAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private DataDictionaryVo dataDictionaryVo;
	
	/**
	 * 客户端数据字典版本号
	 */
	private long clientVersionNo;
	@Autowired
	private IDataDictionaryService dataDictionaryService;
	@Autowired
	private IDataDictionaryValueService dataDictionaryValueService;

	public DataDictionaryVo getDataDictionaryVo() {
		return dataDictionaryVo;
	}

	public void setDataDictionaryVo(DataDictionaryVo dataDictionaryVo) {
		this.dataDictionaryVo = dataDictionaryVo;
	}

	/**
	 * 跳转数据字典管理界面
	 * 
	 * @return
	 * @author 高佳
	 * @date 2015年6月9日
	 * @update
	 */
	public String dataDictionary() {
		return returnSuccess();
	}

	/**
	 * 数据字典词条名称下拉框store
	 * 
	 * @author 李旭锋
	 * @date 2015年6月9日
	 * @update
	 */
	@JSON
	public String queryDataDictionaryByEntity() {
		try {

			dataDictionaryVo.setDataDictionaryEntitys(dataDictionaryService
					.queryDictionaryByExample(dataDictionaryVo
							.getDataDictionaryEntity(),limit,start));
			totalCount =  dataDictionaryService.queryCountDictionaryByExaple(dataDictionaryVo
							.getDataDictionaryEntity());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}

	}

	/**
	 * 数据字典信息store
	 * 
	 * @author 李旭锋
	 * @date 2015年6月9日
	 * @update
	 */
	@JSON
	public String queryDataDictionaryValueByEntity() {
		try {
			DataDictionaryValueEntity datadictionaryValueEntity = dataDictionaryVo
					.getDataDictionaryValueEntity();
			
			
			List<DataDictionaryValueEntity> dataDictionaryValueEntityList = dataDictionaryValueService
					.queryDataDictionaryValueByEntity(
							datadictionaryValueEntity, limit, start);
			
			totalCount = dataDictionaryValueService
					.queryDataDictionaryValueByEntityCount(dataDictionaryVo
							.getDataDictionaryValueEntity());
			dataDictionaryVo
					.setDataDictionaryValueEntityList(dataDictionaryValueEntityList);
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	/**
	 * 新增词条
	 * 
	 * @return
	 * @author 李旭锋
	 * @date 2015年6月10日
	 * @update
	 */
	@JSON
	public String addDataDictionary() {
		try {
			dataDictionaryService.addDictionary(dataDictionaryVo
					.getDataDictionaryEntity());
			return returnSuccess(MessageType.ADD_SUCCESS);
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	/**
	 * 新增数据字典数据
	 * 
	 * @return
	 * @author 李旭锋
	 * @date 2015年6月10日
	 * @update
	 */
	@JSON
	public String addDataDictionaryValue() {
		try {
			dataDictionaryValueService.addDictionaryValue(dataDictionaryVo
					.getDataDictionaryValueEntity());
			return returnSuccess(MessageType.ADD_SUCCESS);
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	public String deleteDataDictionaryValue() {

		try {
			List<DataDictionaryValueEntity> list = dataDictionaryVo
					.getDataDictionaryValueEntityList();

			dataDictionaryValueService.deleteDataDictionaryValueList(list);

			return returnSuccess(MessageType.DELETE_SUCCESS);
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	
	/**
	 * 
	 * 查询所有数据字典数据
	 * @return
	 * @author 高佳
	 * @date 2015年5月15日
	 * @update
	 */
	@JSON
	public String searchAllDataDictionary(){
		try {
			dataDictionaryVo = new DataDictionaryVo();
			dataDictionaryVo.setDataDictionaryEntitys(
				dataDictionaryService.queryAllDataDictionary()
			);
			long lLastChangeVersionNo = dataDictionaryValueService.getLastChangeVersionNo();
			clientVersionNo = lLastChangeVersionNo;
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	/**
	 * 检测数据字典是否有更新
	 * @return
	 * @author 高佳
	 * @date 2015年5月15日
	 * @update 
	 */
	@JSON
	public String isDictionaryHasChanged() {
		try {
			long lLastChangeVersionNo = dataDictionaryValueService.getLastChangeVersionNo();
			if(clientVersionNo == lLastChangeVersionNo) {
				return returnSuccess("keep");
			} else {
				clientVersionNo = lLastChangeVersionNo;
				return returnSuccess("refresh");
			}
		} catch (BusinessException e) {
			return returnError(e.getMessage());
		}
	}
	public long getClientVersionNo() {
		return clientVersionNo;
	}

	public void setClientVersionNo(long clientVersionNo) {
		this.clientVersionNo = clientVersionNo;
	}
	
}
