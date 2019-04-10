package com.hoau.hoaureport.module.configreport.shared.vo;
import java.util.List;
import com.hoau.hoaureport.module.configreport.shared.domain.SignedClient;

public class SignedClientVo {
	private SignedClient entity;
	private List<SignedClient> list;
	private String filePath;//文件路径
	private String addSize;//新增条数
	private String coverSize;//覆盖条数
	private String sumSize; //导入总数量
	
	public SignedClient getEntity() {
		return entity;
	}
	public void setEntity(SignedClient entity) {
		this.entity = entity;
	} 
	public List<SignedClient> getList() {
		return list;
	}
	public void setList(List<SignedClient> list) {
		this.list = list;
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
