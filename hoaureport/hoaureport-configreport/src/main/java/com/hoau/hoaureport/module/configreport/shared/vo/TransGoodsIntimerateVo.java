package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;
import com.hoau.hoaureport.module.configreport.shared.domain.TransGoodsIntimerate;

/**
 * 必走货目标值
 * ClassName: TransGoodsVo 
 * @author 刘镇松
 * @date 2016年11月21日
 * @version V1.0
 */
public class TransGoodsIntimerateVo {
	private TransGoodsIntimerate transGoodsIntimerate;
	private List<TransGoodsIntimerate> transGoodsIntimerates;
	private String isConfirm;
	private String order;
	private String selects;
	//文件路径
	private String filePath;
	private String addSize;//新增条数
	private String coverSize;//覆盖条数
	private String sumSize; //导入总数量
	
	public String getAddSize() {
		return addSize;
	}
	public void setAddSize(String addSize) {
		this.addSize = addSize;
	}
	public String getCoverSize() {
		return coverSize;
	}
	public void setCoverSize(String coverSize) {
		this.coverSize = coverSize;
	}
	public String getSumSize() {
		return sumSize;
	}
	public void setSumSize(String sumSize) {
		this.sumSize = sumSize;
	}
	public String getIsConfirm() {
		return isConfirm;
	}
	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSelects() {
		return selects;
	}
	public void setSelects(String selects) {
		this.selects = selects;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public TransGoodsIntimerate getTransGoodsIntimerate() {
		return transGoodsIntimerate;
	}
	public void setTransGoodsIntimerate(TransGoodsIntimerate transGoodsIntimerate) {
		this.transGoodsIntimerate = transGoodsIntimerate;
	}
	public List<TransGoodsIntimerate> getTransGoodsIntimerates() {
		return transGoodsIntimerates;
	}
	public void setTransGoodsIntimerates(
			List<TransGoodsIntimerate> transGoodsIntimerates) {
		this.transGoodsIntimerates = transGoodsIntimerates;
	}
	
	
}
