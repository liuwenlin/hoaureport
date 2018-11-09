package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import java.util.List;

import com.hoau.hbdp.framework.entity.BaseEntity;
import com.hoau.hbdp.framework.entity.IFunction;
import com.hoau.hbdp.framework.entity.IModule;
import com.hoau.hoaureport.module.util.BaseinfoConstants;

/**
 * 权限实体
 * @author 高佳
 * @date 2015年6月8日
 */
public class ResourceEntity extends BaseEntity implements IFunction {

	/**
	 * 序列ID
	 */
    private static final long serialVersionUID = 8204215052602820708L;

	/**
	 * 功能编码
	 */
    private String code;

	/**
	 * 功能名称
	 */
    private String name;

	/**
	 * 功能入口URI
	 */
    private String entryUri;

	/**
	 * 功能层次
	 */
    private String resLevel;

	/**
	 * 父功能
	 */
    private ResourceEntity parentRes;

    /**
    * 数据版本号
    */	
    private Long versionNo;
    
    /**
    * 是否有效
    */
    private String active;

    /**
    * 显示顺序
    */
    private String displayOrder;

    /**
    * 是否权限检查
    */
    private String checked;

    /**
    * 功能类型
    */
    private String resType;

    /**
    * 是否叶子节点
    */
    private String leafFlag;

    /**
    * 图标的CSS样式
    */
    private String iconCls;

    /**
    * 节点的CSS样式
    */
    private String cls;

    /**
    * 功能描述
    */
    private String notes;
    
    /**
    * 所属系统类型
    */
    private String belongSystemType;
    /**
     * 权限类型集合,.
     */
    private List<String> resourceTypes;
    /**
     * 权限所属系统类型，
     */
    private List<String> systemTypes;
    
    

  

	public List<String> getSystemTypes() {
		return systemTypes;
	}

	public void setSystemTypes(List<String> systemTypes) {
		this.systemTypes = systemTypes;
	}

	public List<String> getResourceTypes() {
		return resourceTypes;
	}

	public void setResourceTypes(List<String> resourceTypes) {
		this.resourceTypes = resourceTypes;
	}

	public String getFunctionCode() {
	return this.code;
    }

    @Override
    public String getKey() {
	return code;
    }

    @Override
    public Boolean getValidFlag() {
	return BaseinfoConstants.ACTIVE.equalsIgnoreCase(active);
    }

	@Override
    @Deprecated
    public IModule getModule() {
	return null;
    }

    @Override
    public String getUri() {
	return entryUri;
    }

    public String createAccessUri(){
    	String accessUri = entryUri;
    	if(accessUri.indexOf("#!") != -1){
    		accessUri = "/" + accessUri.substring(accessUri.indexOf("#!")+2);
		}
    	return accessUri;
    }

	/**
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param  code  
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param  name  
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return entryUri
	 */
	public String getEntryUri() {
		return entryUri;
	}

	/**
	 * @param  entryUri  
	 */
	public void setEntryUri(String entryUri) {
		this.entryUri = entryUri;
	}

	/**
	 * @return resLevel
	 */
	public String getResLevel() {
		return resLevel;
	}

	/**
	 * @param  resLevel  
	 */
	public void setResLevel(String resLevel) {
		this.resLevel = resLevel;
	}

	/**
	 * @return active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * @param  active  
	 */
	public void setActive(String active) {
		this.active = active;
	}

	/**
	 * @return displayOrder
	 */
	public String getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * @param  displayOrder  
	 */
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	/**
	 * @return checked
	 */
	public String getChecked() {
		return checked;
	}

	/**
	 * @param  checked  
	 */
	public void setChecked(String checked) {
		this.checked = checked;
	}

	/**
	 * @return resType
	 */
	public String getResType() {
		return resType;
	}

	/**
	 * @param  resType  
	 */
	public void setResType(String resType) {
		this.resType = resType;
	}

	/**
	 * @return leafFlag
	 */
	public String getLeafFlag() {
		return leafFlag;
	}

	/**
	 * @param  leafFlag  
	 */
	public void setLeafFlag(String leafFlag) {
		this.leafFlag = leafFlag;
	}

	/**
	 * @return notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param  notes  
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return parentRes
	 */
	public ResourceEntity getParentRes() {
		return parentRes;
	}

	/**
	 * @param  parentRes  
	 */
	public void setParentRes(ResourceEntity parentRes) {
		this.parentRes = parentRes;
	}

	/**
	 * @return versionNo
	 */
	public Long getVersionNo() {
		return versionNo;
	}

	/**
	 * @param  versionNo  
	 */
	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	/**
	 * @return iconCls
	 */
	public String getIconCls() {
		return iconCls;
	}

	/**
	 * @param  iconCls  
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	/**
	 * @return cls
	 */
	public String getCls() {
		return cls;
	}

	/**
	 * @param  cls  
	 */
	public void setCls(String cls) {
		this.cls = cls;
	}

	/**
	 * @return belongSystemType
	 */
	public String getBelongSystemType() {
		return belongSystemType;
	}

	/**
	 * @param  belongSystemType  
	 */
	public void setBelongSystemType(String belongSystemType) {
		this.belongSystemType = belongSystemType;
	}
}
