package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.DownTransferTimeNode;

/**
 * 上转移时间节点VO
 * ClassName: DownTransferTimeNodeVo 
 * @author 文洁
 * @date 2016年10月31日
 * @version V1.0
 */
public class DownTransferTimeNodeVo {
	/**上转移时间节点*/
	private DownTransferTimeNode downTransferTimeNode;
	/**上转移时间节点集合*/
	private List<DownTransferTimeNode> downTransferTimeNodeList;
	
	private String filePath;//文件路径
	private String addSize;//新增条数
	private String coverSize;//覆盖条数
	private String sumSize; //导入总数量
	
	//下面是getters和setters
	public DownTransferTimeNode getDownTransferTimeNode() {
		return downTransferTimeNode;
	}
	public void setDownTransferTimeNode(DownTransferTimeNode downTransferTimeNode) {
		this.downTransferTimeNode = downTransferTimeNode;
	}
	public List<DownTransferTimeNode> getDownTransferTimeNodeList() {
		return downTransferTimeNodeList;
	}
	public void setDownTransferTimeNodeList(List<DownTransferTimeNode> downTransferTimeNodeList) {
		this.downTransferTimeNodeList = downTransferTimeNodeList;
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
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
}
