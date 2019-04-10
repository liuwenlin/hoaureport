//下拉单选框
Ext.define('Butterfly.commonSelector.CommonCombSelector', {
	extend : 'Hoau.commonselector.DynamicComboSelector',
	minChars : 0,
	disableKeyFilter: true,
	isPaging : false,// 分页
	isEnterQuery : true,// 回车查询
	listWidth : 200,// 设置下拉框宽度
	active : null,
	myActive : 'Y',
	record : null,
	displayField : null,
	valueField : null, 
	displayField : null,// 显示名称
	valueField : null,// 值
	queryParam : null,// 查询参数
	realValue : null,
	isSearchContacts:false,
	setCombValue : function(displayText, valueText) {
		var me = this, key = me.displayField + '', value = me.valueField
				+ '';
		var m = Ext.create(me.store.getModel().getName());
		m.set(key, displayText);
		m.set(value, valueText);
		me.record = m;
		me.store.loadRecords([m]);
		me.setValue(valueText);
		me.realValue = valueText;
	},
	listeners: {
        /*change: function(comb, newValue, oldValue, eOpts){
        		if(comb.isExpanded==true){
					comb.collapse();
				}
				if(newValue != oldValue){
					comb.realValue = null;
				}
        },*/
        //失去焦点时校验是否做出选择，未做出选择清空下拉框
		blur:function(comb, the, eOpts){
			if(!comb.isSearchContacts){
				if (Ext.isEmpty(comb.realValue)) {
					comb.setRawValue(null);
					comb.setValue(null);
				}else{
					var display = comb.record.data[comb.displayField];
					if(!Ext.Object.equals(display,comb.getRawValue())){
						comb.setRawValue(null);
						comb.setValue(null);
						comb.realValue = null;
					}
				}
			}
		},
		select:function(comb, records, obs){
			comb.record = records;
			var data = records.data;
			comb.realValue = data[comb.valueField];
			//显示情况控件
			comb.getTrigger('clear').show();
		},
		keyup : function(combo, event, eOpts){
			//下拉框输入值
			var value = combo.getRawValue();
			//情况控件
			var clearTrigger = combo.getTrigger('clear');
	        if(!Ext.isEmpty(value)){
	        	//输入不为空显示情况控件
	        	clearTrigger.show();
	        }else{
	        	//隐藏情况控件
	        	clearTrigger.hide();
	        }
	        //关闭下拉框
	        //combo.collapse();
			if(combo.isEnterQuery == true && event.getKey() == event.ENTER){
				var rawValue = combo.getRawValue();
				combo.store.loadPage(1,{
					scope: this,
					callback: function(records, operation, success) {
						if(records.length>0){
							//展开下拉框
							combo.expand();
						}
						combo.setRawValue(rawValue);
					}
				});
			}
		}
    },
	
	getBeforeLoad : function(store, operation, e) {
		var me = this;
		var me = this, searchParams = operation.getParams();
		if (Ext.isEmpty(searchParams)) {
			searchParams = {};
		}
		var prefix = me.queryParam.substring(0, me.queryParam
						.lastIndexOf('.'))
				+ '.';
		var param = prefix + me.myQueryParam;
		if (!Ext.isEmpty(me.myQueryParam)) {
			searchParams[param] = me.getRawValue();
			searchParams[me.queryParam] = null;
		} else {
			searchParams[me.queryParam] = me.rawValue;
			if(!Ext.isEmpty(me.myQueryParam)){
				searchParams[param] = null;
			}
		}
		/*var activeParam = prefix + 'active';
		var act = Ext.isEmpty(me.active) ? me.myActive : me.active;
		searchParams[activeParam] = act;*/
		Ext.apply(store.proxy.extraParams, searchParams);
	},
	triggers:{
		clear : {
				cls : 'x-form-clear-trigger',
				handler : function(){
					this.setRawValue(null);
					this.setValue(null);
					this.realValue = null;
					this.getTrigger('clear').hide();
				},
				hidden : true
		}
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.active = config.active; 
		me.store.addListener('select', function(comb, records, obs) {
			var record = records[0];
			me.record = record;
			me.realValue = record[me.valueField];
		});
		me.callParent([cfg]);
		/*me.on('blur',me.getBlur,me); 
		me.on('select',me.getSelect,me); 
		me.on('change',me.getChange,me);
		me.on('keyup',me.getKeyup,me);*/
	},
	getRecord : function() {
		var me = this;
		return me.record;
	}
});




/**
 * #########################################
 * #######      数据字典下拉框开始          #######
 * #########################################
 */
//数据字典下拉单选框
Ext.define('Butterfly.commonSelector.DataDictionaryCommonSelector', {
	extend : 'Ext.form.field.ComboBox',
	alias: 'widget.dataDictionarySelector',
    displayField: 'valueName',
    valueField: 'valueCode',
    queryMode : 'local',
    editable : false,
    setTermsCode:function(termsCode){
    	var store = getDataDictionary().getDataDictionaryStore(termsCode);
		if(!Ext.isEmpty(store)&&!Ext.isEmpty(this.anyRecords)){
			store.insert(0,this.anyRecords);
		}
    	this.setStore(store);
    },
    
    constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		var store = getDataDictionary().getDataDictionaryStore(config.termsCode);
		if(!Ext.isEmpty(store)&&!Ext.isEmpty(config.anyRecords)){
			//store.add(config.anyRecords);
			store.insert(0,config.anyRecords);//新增的数据在第一个位置(“全部”)
		}
		me.store = store;
		me.callParent([cfg]);
	}
});
/**
 * #########################################
 * #######      数据字典下拉框结束          #######
 * #########################################
 */

/**
 * #########################################
 * #######      是否下拉框开始          #######
 * #########################################
 */
Ext.define('Butterfly.model.baseinfo.YesNoModel',{
	extend : 'Ext.data.Model',
	fields : [{
		name : 'name',
		type : 'string'
	},{
		name : 'value',
		type : 'string'
	}]
});
Ext.define('Butterfly.commonSelector.YesNoAllStore', {
	extend : 'Ext.data.Store',
	model: 'Butterfly.model.baseinfo.YesNoModel',
    data : [{'name':'全部','value':''},{'name':'是','value':'Y'},{'name':'否','value':'N'}]
});
Ext.define('Butterfly.commonSelector.YesNoStore', {
	extend : 'Ext.data.Store',
	model: 'Butterfly.model.baseinfo.YesNoModel',
    data : [{'name':'是','value':'Y'},{'name':'否','value':'N'}]
});
Ext.define('Butterfly.commonSelector.YesOrNoSelector', {
	extend : 'Ext.form.ComboBox',
	alias : 'widget.yesnocombselector',
	listWidth : this.width,// 下拉列表宽度，从外面传入
	multiSelect : false,// 从外面传入
	displayField : 'name',// 显示名称
	valueField : 'value',// 值
	isShowAll : false,// 是否显示全部
	constructor : function(config) {
		var me = this, 
		cfg = Ext.apply({}, config); 
		if (config.isShowAll){
			me.store = Ext.create('Butterfly.commonSelector.YesNoAllStore');
		}else{
			me.store = Ext.create('Butterfly.commonSelector.YesNoStore');
		}
		me.callParent([cfg]);
	}
});
//是和否checkBox
Ext.define('Butterfly.commonCheckBox.YesOrNoCheckBox', {
	extend : 'Ext.form.field.Checkbox',
	alias : 'widget.yesnocheckbox',
	inputValue: 'Y',         //选中的值
    uncheckedValue: 'N',
    getValue : function(){
    	if(this.checked){
    		return 'Y';
    	}else{
    		return 'N';
    	}
    }
});
/**
 * #########################################
 * #######      是否下拉框结束          #######
 * #########################################
 */
/**
 * #########################################
 * #######      组织相关下拉框开始          #######
 * #########################################
 */
/**
 * 用户model
 */
Ext.define('Butterfly.baseinfo.commonSelector.UserModel', {
	extend : 'Ext.data.Model',
	fields : [{
				name : 'id'
			}, {
				name : 'userName'
				//用户名
			}, {
				name : 'empCode'
				//员工工号
			}, {
				name : 'empName'
				//员工姓名
			}, {
				name : 'password'
				//密码
			}, {
				name : 'title'
				//称谓
			}, {
				name : 'beginTime'
			}, {
				name : 'endTime'
			}, {
				name : 'isEmp'
				//是否公司员工
			}]
});
//用户下拉框store
Ext.define('Butterfly.commonUserSelector.UserCombStore', {
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.UserModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/commonUserSearchAction!seacrhUserByParam.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'commonUserVo.commonUserEntityList',
			totalProperty : 'totalCount'
		}
	}
});
//用户单选
Ext.define('Butterfly.commonUserSelector.DynamicUserSelector', {
	extend : 'Butterfly.commonSelector.CommonCombSelector',
	alias : 'widget.dynamicusercombselector',
	displayField : 'empName',// 显示名称
	valueField : 'userName',// 值

	isEnable : null,//当前是否可用（Y，N--根据启用时间，失效时间判定）

	active : null,
	queryParam : 'commonUserVo.userSearchConditionEntity.queryParam',// 查询参数
	showContent : '{empName}&nbsp;&nbsp;&nbsp;{title}',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);    
		me.store = Ext.create('Butterfly.commonUserSelector.UserCombStore');
		
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.getParams();
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
			}

			if (!Ext.isEmpty(config.isEnable)) {
				searchParams['commonUserVo.userSearchConditionEntity.isEnable'] = config.isEnable;
			}
			if (!Ext.isEmpty(config.active)) {
				searchParams['commonUserVo.userSearchConditionEntity.active'] = config.active;
			}else{
				searchParams['commonUserVo.userSearchConditionEntity.active'] = 'Y';
			}
			Ext.apply(store.proxy.extraParams, searchParams);  
		})
		me.callParent([cfg]);
	}
});

/**
 * 员工model
 * 
 */
Ext.define('Butterfly.baseinfo.commonSelector.EmployeModel',{
	extend : 'Ext.data.Model',
	fields : [{
				name : 'id'
			}, {
				name : 'employeeCode'
			}, {
				name : 'employeeName'
			}, {
				name : 'pininName'
			}, {
				name : 'pinyinShortName'
			}, {
				name : 'account'
			}, {
				name : 'deptName'
			}, {
				name : 'deptCode'
			}, {
				name : 'jobName'
			}, {
				name : 'jobCode'
			}
			]
});

//员工下拉框store
Ext.define('Butterfly.commonEmployeSelector.EmployeCombStore',{
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.EmployeModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/commonEmployeeSearchAction!seacrhEmployeeByParam.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'commonEmployeeVo.commonEmployeeList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 员工单选
 */
Ext.define('Butterfly.commonEmpSelector.EmployeeCommonSelector',{
	extend : 'Butterfly.commonSelector.CommonCombSelector',
	alias : 'widget.dynamicemployeecombselector',
	displayField : 'employeeName',// 显示名称
	valueField : 'employeeCode',// 值
	active : null,
	queryParam : 'commonEmployeeVo.employeeSearchConditionEntity.queryParam',// 查询参数
	showContent : '{employeeName}&nbsp;&nbsp;&nbsp;{jobName}',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);    
		me.store = Ext.create('Butterfly.commonEmployeSelector.EmployeCombStore');
		
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.getParams();
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
			}
			if (!Ext.isEmpty(config.active)) {
				searchParams['commonEmployeeVo.employeeSearchConditionEntity.active'] = config.active;
			}else{
				searchParams['commonEmployeeVo.employeeSearchConditionEntity.active'] = 'Y';
			}
			Ext.apply(store.proxy.extraParams, searchParams);  
		})
		me.callParent([cfg]);
	}
});


/**********************司机业务员信息begin**************************/
/**
 * 司机业务员model
 * 
 */
Ext.define('Butterfly.baseinfo.commonSelector.OmsHRModel',{
	extend : 'Ext.data.Model',
	fields : [{
		name : 'empNo'
	}, {
		name : 'empName'
	}, {
		name : 'jobName'
	}, {
		name : 'logistCode'
	}, {
		name : 'empMobile'
	}
	]
});

/**
 * 司机业务员下拉框store
 */
Ext.define('Butterfly.commonOmsHRSelector.OmsHRCombStore',{
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.OmsHRModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/commonOmsHRSearchAction!seacrhOmsHRByParam.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'commonOmsHRVo.commonOmsHRList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 司机业务员单选
 */
Ext.define('Butterfly.commonOmsHRSelector.OmsHRCommonSelector',{
	extend : 'Butterfly.commonSelector.CommonCombSelector',
	alias : 'widget.dynaOmsHRcombselector',
	displayField : 'empName',// 显示名称
	valueField : 'empNo',// 值
	queryParam : 'commonOmsHRVo.omsHRSearchConditionEntity.queryParam',// 查询参数
	showContent : '{empName}&nbsp;&nbsp;{logistCode}&nbsp;{jobName}',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);    
		me.store = Ext.create('Butterfly.commonOmsHRSelector.OmsHRCombStore');
		
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.getParams();
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
			}
			Ext.apply(store.proxy.extraParams, searchParams);  
		})
		me.callParent([cfg]);
	}
});
/**********************司机业务员信息end**************************/

/**
 * 组织model
 */
Ext.define('Butterfly.baseinfo.commonSelector.OrgModel', {
	extend : 'Ext.data.Model',
	fields : [{
				name : 'id'
			}, {
				name : 'code'
			}, {
				name : 'logistCode'
			}, {
				name : 'name'
			}, {
				name : 'cityCode'
			}, {
				name : 'countyCode'
			}, {
				name : 'provinceCode'
			}, {
				name : 'active'
			}, {
				name : 'isDivision'
			}, {
				name : 'isBigRegion'
			}, {
				name : 'isRoadArea'
			}, {
				name : 'isSalesDepartment'
			}, {
				name : 'isTransferCenter'
			}, {
				name : 'isFleet'
			},{
				name : 'isPlatform'
			},{
				name : 'phone'
			}]
});
//组织下拉框store
Ext.define('Butterfly.commonOrgSelector.OrgCombStore', {
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.OrgModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/commonOrgSearchAction!seacrhOrgByParam.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'commonOrgVo.commonOrgEntityList',
			totalProperty : 'totalCount'
		}
	}
});
//组织单选
Ext.define('Butterfly.commonOrgSelector.DynamicOrgSelector', {
	extend : 'Butterfly.commonSelector.CommonCombSelector',
	alias : 'widget.dynamicorgcombselector',
	displayField : 'name',// 显示名称
	valueField : 'code',// 值
	type : 'ORG',// 部门类型 一种部门类型，传递此值
	types : null,// 部门类型 多种部门类型传递次值 两个类型的值之间用","隔开
	isDivision : null,// 查询事业部 配置此值
	isBigRegion : null,// 查询大区 配置此值
	isRoadArea : null,// 查询路区 配置此值
	isSalesDepartment : null,// 查询门店 配置此值
	isTransferCenter : null,// 查询场站 配置此值
	isFleet : null,// 查询车队 配置此值
	isPlatform : null,// 查询平台 配置此值
	logistCode : null,//物流代码	
	arrive:null,//是否可到达
	depart:null,//是否可出发
	queryParam : 'commonOrgVo.orgSearchConditionEntity.queryParam',// 查询参数
	showContent : '{name}&nbsp;&nbsp;&nbsp;{logistCode}',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);    
		me.store = Ext.create('Butterfly.commonOrgSelector.OrgCombStore');
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.getParams();
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
			}
			// 传递的部门类型是多种
			var types = null;
			if (!Ext.isEmpty(config.types)) {
				types = config.types.split(',');
				searchParams['commonOrgVo.orgSearchConditionEntity.types'] = types;
			}
			if (!Ext.isEmpty(config.type)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.type'] = config.type;
			}else{
				searchParams['commonOrgVo.orgSearchConditionEntity.type'] = me.type;
			}
			if (!Ext.isEmpty(config.isDivision)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.isDivision'] = config.isDivision;
			}
			if (!Ext.isEmpty(config.isBigRegion)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.isBigRegion'] = config.isBigRegion;
			}
			if (!Ext.isEmpty(config.isLoadArea)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.isRoadArea'] = config.isRoadArea;
			}
			if (!Ext.isEmpty(config.isSalesDepartment)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.isSalesDepartment'] = config.isSalesDepartment;
			}
			if (!Ext.isEmpty(config.isTransferCenter)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.isTransferCenter'] = config.isTransferCenter;
			}
			if (!Ext.isEmpty(config.isFleet)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.isFleet'] = config.isFleet;
			}
			if (!Ext.isEmpty(config.isPlatform)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.isPlatform'] = config.isPlatform;
			}
			if (!Ext.isEmpty(config.active)) {
				searchParams['commonOrgVo.orgSearchConditionEntity.active'] = config.active;
			}else{
				searchParams['commonOrgVo.orgSearchConditionEntity.active'] = 'Y';
			}
			Ext.apply(store.proxy.extraParams, searchParams);  
		})
		me.callParent([cfg]);
	}
});
/**
 * #########################################
 * #######      组织相关下拉框结束         #######
 * #########################################
 */
/**
 * #########################################
 * #######      省市区相关下拉框开始        #######
 * #########################################
 */
//行政区域Model
Ext.define('Butterfly.baseinfo.commonSelector.DistrictModel', {
	extend : 'Ext.data.Model',
	fields : [{
				name : 'districtCode',
				type : 'string'
			}, {
				name : 'districtName',
				type : 'string'
			}, {
				name : 'districtType',
				type : 'string'
			}, {
				name : 'parentDistrictName',
				type : 'string'
			},{
				name : 'parentDistrictCode',
				type : 'string'
			}]
});
//行政区域store
Ext.define('Butterfly.baseinfo.commonSelector.DistrictStore', {
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.DistrictModel',
	pageSize : 50,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/districtAction!queryDistrictByName.action',
		actionMethods : 'POST',// 否则可能会出现中文乱码
		reader : {
			type : 'json',
			rootProperty : 'districtVo.districtList',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * 国家store
 */
Ext.define('Butterfly.baseinfo.commonSelector.NationStore', {
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.DistrictModel',
	pageSize : 50,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/districtAction!queryAllNation.action',
		actionMethods : 'POST',// 否则可能会出现中文乱码
		reader : {
			type : 'json',
			rootProperty : 'districtVo.districtList',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * 省份store
 */
Ext.define('Butterfly.baseinfo.commonSelector.ProvinceStore', {
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.DistrictModel',
	pageSize : 50,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/districtAction!queryProvince.action',
		actionMethods : 'POST',// 否则可能会出现中文乱码
		reader : {
			type : 'json',
			rootProperty : 'districtVo.districtList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 城市store
 */
Ext.define('Butterfly.baseinfo.commonSelector.CityStore', {
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.DistrictModel',
	pageSize : 50,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/districtAction!queryCity.action',
		actionMethods : 'POST',// 否则可能会出现中文乱码
		reader : {
			type : 'json',
			rootProperty : 'districtVo.districtList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 区县store
 */
Ext.define('Butterfly.baseinfo.commonSelector.AreaStore', {
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.DistrictModel',
	pageSize : 50,
	proxy : {
		type : 'ajax',
		url : '../baseinfo/districtAction!queryArea.action',
		actionMethods : 'POST',// 否则可能会出现中文乱码
		reader : {
			type : 'json',
			rootProperty : 'districtVo.districtList',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * 省市区单个下拉框
 */
Ext.define('Butterfly.commonSelector.DistrictSelector', {
	extend : 'Butterfly.commonSelector.CommonCombSelector',
	alias : 'widget.commondistrictselector',
	eventType : ['callparent'],// 一般callparent包含focus事件，如果有callparent,则focus事件可不用传递
	displayField : 'districtName',// 显示名称
	valueField : 'districtCode',
	queryParam : 'districtVo.district.districtName',// 查询参数
	districtType: null,//省市县类别
	showContent : '{districtName}',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Butterfly.baseinfo.commonSelector.DistrictStore');
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.getParams();
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
			}
			if(!Ext.isEmpty(config.districtType)){
				searchParams['districtVo.district.districtType'] = config.districtType;
			} 
			Ext.apply(store.proxy.extraParams, searchParams);
		});
		me.callParent([cfg]);
	}
});

//联动container	
Ext.define('Butterfly.commonselector.LinkageContainer',{
	extend: 'Ext.form.FieldContainer',
	alias: 'widget.linkagecontainer',
	defaultType: 'linkagecomboselector',
	getItemValue: function(itemId){
		var me = this,
			item = me.items.get(itemId);
		if(item!=null){
			return item.getValue();
		}
		return null;
	},
	getValue: function(){
		var me = this,
			values = new Array();
		for(var i=0;i<me.items.length;i++){
			values[i] = me.getItemValue(me.items.items[i].itemId);
		}
		return values;
	}
});

/**
 * 联动查询组件
 */
Ext.define('Butterfly.commonselector.LinkageComboSelector',{
	extend: 'Hoau.commonselector.DynamicComboSelector',
	alias: 'widget.linkagecomboselector',
	minChars : 0,
	isPaging : false,// 分页
	isEnterQuery : true,// 回车查询
	parentParamsAndItemIds: null,//级联父组件参数
	realValue : null,
	listeners:{
        //失去焦点时校验是否做出选择，未做出选择清空下拉框
		/*blur:function(comb, the, eOpts){
			if (Ext.isEmpty(comb.realValue)) {
				comb.setRawValue(null);
				comb.setValue(null);
			}else{
				var display = comb.record.data[comb.displayField];
				if(!Ext.Object.equals(display,comb.getRawValue())){
					comb.setRawValue(null);
					comb.setValue(null);
					comb.realValue = null;
				}
			}
		},*/
		select:function(comb, records, obs){
			comb.record = records;
			var data = records.data;
			comb.realValue = data[comb.valueField];
			//显示情况控件
			comb.getTrigger('clear').show();
		},
		keyup : function(combo, event, eOpts){
			//下拉框输入值
			var value = combo.getRawValue();
			//情况控件
			var clearTrigger = combo.getTrigger('clear');
	        if(!Ext.isEmpty(value)){
	        	//输入不为空显示情况控件
	        	clearTrigger.show();
	        }else{
	        	//隐藏情况控件
	        	clearTrigger.hide();
	        }
	        //关闭下拉框
	        //combo.collapse();
			if(combo.isEnterQuery == true && event.getKey() == event.ENTER){
				var rawValue = combo.getRawValue();
				combo.store.loadPage(1,{
					scope: this,
					callback: function(records, operation, success) {
						if(records.length>0){
							//展开下拉框
							combo.expand();
						}
						combo.setRawValue(rawValue);
					}
				});
			}
		}
	},
	/*eventType: ['callparent'],//事件(传入)
	getFocus: function(){
		var me = this;
		me.on('focus',function(){
			me.setValue(null);
			me.store.loadPage(1,{
				scope: this,
				callback: function(records, operation, success) {
					if(records.length>0){
						me.expand();
					}
				}
			});
		});
	},*/
	/*getCallParent: function(){
		var me = this,
			cmp;
		if(!Ext.isEmpty(me.parentParamsAndItemIds)){
			Ext.Object.each(me.parentParamsAndItemIds, function(queryParam, itemId, parentParamsAndItemIds) {
				cmp = me.up().items.get(itemId);
				if(!cmp.hasListener('select')){
					cmp.on('select',function(combo){
						me.setValue(null);
						me.store.loadPage(1,{
							scope: this,
							callback: function(records, operation, success) {
								me.focus(false, 100);
								me.expand();
							}
						});
					});
				}
			});
		}
	},*/
	triggers:{
		clear : {
				cls : 'x-form-clear-trigger',
				handler : function(){
					this.setRawValue(null);
					this.setValue(null);
					this.realValue = null;
					this.getTrigger('clear').hide();
				},
				hidden : true
		}
	},
	initComponent:function(){
		var me = this;
		/*me.on('boxready',function(){
			var callparent = null;
			for(var i = 0;i<me.eventType.length;i++){
				if(me.eventType[i]=='focus'){
					me.getFocus();
				}else if(me.eventType[i]=='callparent'){
					callparent = 'callparent';
					me.getCallParent();
				}
			}
			//调用父类方法会自动包含focus事件
			if(callparent=='callparent'){
				me.un('focus');
			}
		});*/
		this.callParent(arguments);
		//增加级联查询条件
		me.store.on('beforeLoad', function(st,operation,e){
			var cmp=null, searchParams=operation.getParams();
			if(Ext.isEmpty(searchParams)){
				searchParams={};
			}
			searchParams[me.queryParam] = me.rawValue;
			if(!Ext.isEmpty(me.parentParamsAndItemIds)){
				Ext.Object.each(me.parentParamsAndItemIds, function(queryParam, itemId, parentParamsAndItemIds) {
					cmp = me.up().items.get(itemId);
					searchParams[queryParam] = cmp.getValue();
				});
			}
			Ext.apply(st.proxy.extraParams, searchParams);
		});
	}
});	

Ext.define('Butterfly.commonSelector.linkReginCombSelector', {
	extend : 'Butterfly.commonselector.LinkageContainer',
	alias : 'widget.linkregincombselector',
//	fieldLabel : '联动选择',
	type : 'N-P-C-C',// type ：N-P-C-C 国省市县 P-C-C :省市县 C-C:市县 P-C:省市
	width : 600,
	nationWidth : 150,// 国家长度
	nationLabel : '',// 国家label
	nationName : '',// 国家名称--对应name
	nationIsBlank : true,
	nationLabelWidth:null,
	provinceWidth : 150,// 省份长度
	provinceLabel : '',// 省份label
	provinceLabelWidth:null,
	provinceName : '',// 省份名称—对应name
	provinceIsBlank : true,
	cityWidth : 150,// 城市长度
	cityLabel : '',// 城市label
	cityName : '',// 城市name
	cityIsBlank : true,
	cityLabelWidth:null,
	areaWidth : 150,// 县长度
	areaLabel : '',// 县label
	areaName : '',// 县name
	areaIsBlank : true,
	areaLabelWidth:null,
	layout : 'column',
	labelWid : 20,
	readOnly : false,
	setReadOnly : function(flag){
		var me =this;
		me.getNation().setReadOnly(flag);
		me.getProvince().setReadOnly(flag);
		me.getCity().setReadOnly(flag);
		me.getCounty().setReadOnly(flag);
	},
	getDefults : function(config) {
		return {
			labelWidth : config.labelWid,
			minChars : 0,
			labelSeparator : ''
		}
	},
	setReginValue : function(displayText, valueText, order) {// 0：国家的值，1：省的值，2：市的值，3：县的值
		var me = this;
		var regionObj=null;
		if(!Ext.isEmpty(order)){
			if(order == '0'){
				regionObj=me.nation;
			}else if(order == '1'){
				regionObj=me.province;
			}else if(order == '2'){
				regionObj=me.city;
			}else if(order == '3'){
				regionObj=me.county;
			}
		}
		var  key = regionObj.displayField + '', value =regionObj.valueField
				+ '';
		var m = Ext.create(regionObj.store.getModel().getName());
		m.set(key, displayText);
		m.set(value, valueText);
		regionObj.store.loadRecords([m]);
		regionObj.setValue(valueText);
	},
	nation:null,
	getNation:function(nationWidth,nationLabel,nationName,nationIsBlank,nationLabelWidth,configType,provinceObj){
		if(Ext.isEmpty(this.nation)){
			this.nation=Ext.widget('linkagecomboselector',{
				configType :configType,
				provinceObj : provinceObj,
				/*editable:false,*/
				eventType : ['focus'],// 一般callparent包含focus事件，如果有callparent,则focus事件可不用传递
				name : 'province',
				itemId : 'Butterfly_baseinfo_Nation_ID',
				store : Ext.create('Butterfly.baseinfo.commonSelector.NationStore'),// 从外面传入
				listeners : {
					'change' : function(ths, the, eOpts){
						if(ths.configType == 'N-P-C-C'){
							ths.provinceObj.setReadOnly(false);
							var provObj=ths.provinceObj.getEl();
							if(!Ext.isEmpty(provObj)){
								provObj.query('input')[0].readOnly = 'readOnly';	
							}
						} 
					}
				},
				displayField : 'districtName',// 显示名称
				valueField : 'districtCode',
				width : nationWidth,
				fieldLabel : nationLabel,
				name : nationName,
				labelWidth:nationLabelWidth,
				allowBlank : nationIsBlank,
				queryParam : 'districtVo.district.queryParam'
			});
		}
		return this.nation;
	},
	province:null,
	getProvince:function(provinceWidth,provinceLabel,provinceName,provinceIsBlank,provinceLabelWidth,configType,cityObj){
		if(Ext.isEmpty(this.province)){
			this.province=Ext.widget('linkagecomboselector',{
				configType:configType,
				cityObj : cityObj,
				/*editable:false,*/
				itemId : 'Butterfly_baseinfo_Province_ID',
				eventType : ['callparent'],
				store : Ext.create('Butterfly.baseinfo.commonSelector.ProvinceStore'),// 从外面传入
				listeners : {
					'change' : function(ths, the, eOpts){
						ths.cityObj.disable();
						ths.cityObj.setValue(null);
						ths.cityObj.realValue = null;
						/*var cityObj=ths.cityObj.getEl();*/
						/*if(!Ext.isEmpty(cityObj)){
							cityObj.query('input')[0].readOnly = 'readOnly';	
						}*/
					},
					'select' : function(ths, the, eOpts){
						ths.cityObj.enable();
						ths.cityObj.getStore().load();
					}
				},
				displayField : 'districtName',// 显示名称
				valueField : 'districtCode',
				minChars : 0,
				width : provinceWidth,
				fieldLabel : provinceLabel,
				name : provinceName,
				labelWidth:provinceLabelWidth,
				allowBlank : provinceIsBlank,
				isPaging: false, 
				parentParamsAndItemIds : {
					'districtVo.district.parentDistrictCode' : 'Butterfly_baseinfo_Nation_ID'
				},// 此处城市不需要传入
				queryParam : 'districtVo.district.queryParam'
			
			});
		}
		return this.province;
	},
	city:null,
	getCity:function(cityWidth,cityLabel,cityName,cityIsBlank,cityLabelWidth,configType,countyObj){
		if(Ext.isEmpty(this.city)){
				this.city=Ext.widget('linkagecomboselector',{
					configType:configType,
					countyObj : countyObj,
					/*editable:false,*/
					itemId : 'Butterfly_baseinfo_City_ID',
					eventType : ['callparent'],// 一般callparent包含focus事件，如果有callparent,则focus事件可不用传递
					store : Ext.create('Butterfly.baseinfo.commonSelector.CityStore'),// 从外面传入
					listeners : {
						'change' : function(ths, the, eOpts){
							ths.countyObj.disable();
							ths.countyObj.setValue(null);
							ths.countyObj.realValue = null;
							/*ths.countyObj.setReadOnly(false);
							var countyObj=ths.countyObj.getEl();
							if(!Ext.isEmpty(countyObj)){
								countyObj.query('input')[0].readOnly = 'readOnly';	
							}*/
						},
						'select' : function(ths, the, eOpts){
							ths.countyObj.enable();
							ths.countyObj.getStore().load();
						}
					},
					displayField : 'districtName',// 显示名称
					valueField : 'districtCode',
					minChars : 0,
					width : cityWidth,
					fieldLabel : cityLabel,
					name : cityName,
					labelWidth:cityLabelWidth,
					allowBlank : cityIsBlank,
					isPaging: false,  
					parentParamsAndItemIds : {
						'districtVo.district.parentDistrictCode' : 'Butterfly_baseinfo_Province_ID'
					},// 此处城市不需要传入
					queryParam : 'districtVo.district.queryParam'
			});
		}
		return this.city;
	},
	county:null,
	getCounty:function(areaWidth,areaLabel,areaNames,areaIsBlank,areaLabelWidth){
		if(Ext.isEmpty(this.county)){
			this.county=Ext.widget('linkagecomboselector',{
				store : Ext.create('Butterfly.baseinfo.commonSelector.AreaStore'),// 从外面传入
				displayField : 'districtName',// 显示名称
				valueField : 'districtCode',
				minChars : 0,
				/*editable:false,*/
				width : areaWidth,
				fieldLabel : areaLabel,
				name : areaNames,
				allowBlank : areaIsBlank,
				labelWidth:areaLabelWidth,
				isPaging: false, 
				parentParamsAndItemIds : {
					'districtVo.district.parentDistrictCode' : 'Butterfly_baseinfo_City_ID'
				},// 此处区域不需要传入
				queryParam : 'districtVo.district.queryParam'
				});
			}
		return  this.county;
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.defaults = me.getDefults(config);
		var county = me.getCounty(config.areaWidth,config.areaLabel,config.areaName,config.areaIsBlank,config.areaLabelWidth);
		var city = me.getCity(config.cityWidth,config.cityLabel,config.cityName,config.cityIsBlank,config.cityLabelWidth,config.type,county);
		var province = me.getProvince(config.provinceWidth,config.provinceLabel,config.provinceName,config.provinceIsBlank,config.provinceLabelWidth,config.type,city);
		var nation = me.getNation(config.nationWidth,config.nationLabel,config.nationName,config.nationIsBlank,config.nationLabelWidth,config.type,province);
		me.items=[nation,province,city,county];
		if (config.type == 'N-P-C-C') {
			/*province.setReadOnly(true);   
			city.setReadOnly(true);   
			county.setReadOnly(true);*/  
			/*province.cls='readonlyhaveborder'; 
			city.cls='readonlyhaveborder';
			county.cls='readonlyhaveborder';*/			
			/*me.items=[nation,province,city,county];*/
		} else if (config.type == 'P-C-C') {
			if(config.readOnly){
				/*province.disable();
				city.disable();   
				county.disable();
				province.setReadOnly(true);
				city.setReadOnly(true);
				county.setReadOnly(true);*/
			}else{
				/*city.disable();   
				county.disable();*/
			}
			/*city.cls='readonlyhaveborder';
			county.cls='readonlyhaveborder';*/
			province.eventType=['focus'];
			province.parentParamsAndItemIds = null;
			me.items=[province,city,county];
		} else if (config.type == 'P-C') {
			if(config.readOnly){
				/*province.disable();
				city.disable();*/
				province.setReadOnly(true);
				city.setReadOnly(true);
			}else{
				city.disable();
			}
			/*city.cls='readonlyhaveborder';*/
			province.eventType=['focus'];
			province.parentParamsAndItemIds = null;
			me.items=[province,city];
		} else if (config.type == 'C-C') {
			if(config.readOnly){
				/*city.disable();
				county.disable(); */
				city.setReadOnly(true);
				county.setReadOnly(true);
			}else{
				county.disable(true); 
			}
			/*county.cls='readonlyhaveborder';*/
			city.eventType=['focus'];
			city.parentParamsAndItemIds = null;
			me.items=[city,county];
		}
		me.callParent([cfg]);
	}
});
/**
 * #########################################
 * #######      省市区相关下拉框结束        #######
 * #########################################
 */
/**
 * #########################################
 * #######      异常项目下拉框开始        #######
 * #########################################
 */
/**
 * 异常项目
 */
Ext.define('Butterfly.baseinfo.commonSelector.AbnormalProjectModel',{
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		//id
		type : 'string'
	},{
		name : 'abnormalName',
		//异常名称
		type : 'string'
	},{
		name : 'abnormalCode',
		//异常代号
		type : 'string'
	},{
		name : 'abnormalType',
		//异常类型
		type : 'string'
	},{
		name : 'responsibleParty',
		//责任方
		type : 'string'
	},{
		name : 'orderState',
		//订单状态
		type : 'string'
	},{
		name : 'abnormalDescribe',
		//异常描述
		type : 'string'
	},{
		name : 'abnormalReaso',
		//异常原因
		type : 'string'
	},{
		name : 'abnormalAvailable',
		//异常可用
		type : 'string'
	},{
		name : 'solution',
		//解决方案描述
		type : 'string'
	},{
		name : 'abnormalRemarks',
		//异常备注
		type : 'string'
	}]
});
Ext.define('Butterfly.baseinfo.commonEmployeSelector.AbnormalProjectStore',{
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.AbnormalProjectModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		actionMethods : 'post',
		url : '../baseinfo/abnormalProjectAction!queryAbnormalProject.action',
		reader : {
			type : 'json',
			rootProperty : 'abnormalProjectVo.abnormalProjectList',
			totalProperty : 'totalCount' // 总个数
		}
	}
});

/**
 * 异常项目单选
 */
Ext.define('Butterfly.commonEmpSelector.AbnormalProjectSelector',{
	extend : 'Butterfly.commonSelector.CommonCombSelector',
	alias : 'widget.abnormalProjectSelector',
	displayField : 'abnormalCode',// 显示名称
	valueField : 'abnormalCode',// 值
	active : null,
	queryParam : 'abnormalProjectVo.abnormalProjectParams.abnormalCode',// 查询参数
	showContent : '{abnormalCode}【{abnormalDescribe}】',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);    
		me.store = Ext.create('Butterfly.baseinfo.commonEmployeSelector.AbnormalProjectStore');
		
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.getParams();
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
			}
			if (!Ext.isEmpty(config.active)) {
				searchParams['abnormalProjectVo.abnormalProjectParams.abnormalAvailable'] = config.active;
			}else{
				searchParams['abnormalProjectVo.abnormalProjectParams.abnormalAvailable'] = 'Y';
			}
			Ext.apply(store.proxy.extraParams, searchParams);  
		})
		me.callParent([cfg]);
	}
});
/**
 * #########################################
 * #######      异常项目下拉框结束        #######
 * #########################################
 */



/*****************收发货人搜索框**************/
/**收发货 联系人公共下拉选择model**/
Ext.define('Butterfly.order.commonSelector.ContactsModel', {
	extend : 'Ext.data.Model',
	fields :[{
			name: 'id',type:'string'
		},{
			name: 'showValue',type:'string'
		}]
});
/**收发货 联系人公共下拉选择Store**/
Ext.define('Butterfly.order.commonSelector.ContactsStore',{
	extend:'Ext.data.Store',
	model: 'Butterfly.order.commonSelector.ContactsModel',
	pageSize:15,
	proxy:{
		type:"ajax",
		url:"../order/contactsAction!queryContacts.action",
		actionMethods:"POST",
		reader:{
			type:'json',
			root:'contacts',
			totalProperty : 'totalCount'
		}
	}
});
/**收发货 联系人公共下拉选择**/
Ext.define('Butterfly.order.commonContactsSelector.DynamicContactsSelector',{
	extend : 'Butterfly.commonSelector.CommonCombSelector',
	alias : 'widget.dynamiccontactcombselector',
	displayField : 'showValue',//显示的值
	valueField : 'showValue',// 值
	contactType : null, //联系人类型,
	paramType : null, //参数类型
	queryParam : 'selectParam',
	showContent : '{showValue}【{contactName}】',// 显示表格列
	isSearchContacts:true,
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Butterfly.order.commonSelector.ContactsStore');
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.getParams();
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
			}
			if (!Ext.isEmpty(config.contactType)) {
				searchParams['contactType'] = config.contactType;
			}
			if (!Ext.isEmpty(config.paramType)) {
				searchParams['paramType'] = config.paramType;
			}
			Ext.apply(store.proxy.extraParams, searchParams);  
		})
		me.callParent([cfg]);
	}
	
});
/*****************收发货人搜索框**************/




/******************报表查询*********************/

/**
 * 报表sqlmodel
 * 
 */
Ext.define('Butterfly.baseinfo.commonSelector.QuerySqlModel',{
	extend : 'Ext.data.Model',
	fields : [{name: 'id'},//ID  
	          {name: 'createDate',type:'date',dateFormat:'time'},//日期
	          {name: 'number'},//SQL编号
	          {name: 'sql'},//sql
	          {name: 'tableHead'},//表头
	          {name: 'param'},//sql参数
	          {name: 'myColumn'},//表头结果集 json格式字符串
	          {name: 'remark'},//备注sql功能
	          {name: 'queryRule'} // 取数规则
			]
});

//报表sqlstore
Ext.define('Butterfly.commonEmployeSelector.QuerySqlCombStore',{
	extend : 'Ext.data.Store',
	model : 'Butterfly.baseinfo.commonSelector.QuerySqlModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../configreport/showQuerySql.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'sqlLists',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 报表单选
 */
Ext.define('Butterfly.commonEmpSelector.QuerySqlCommonSelector',{
	extend : 'Butterfly.commonSelector.CommonCombSelector',
	alias : 'widget.querySqlcombselector',
	displayField : 'remark',// 显示名称
	valueField : 'id',// 值
	active : null,
	queryParam : 'queryAssemble.remark',// 查询参数
//	showContent : '{remark}',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);    
		me.store = Ext.create('Butterfly.commonEmployeSelector.QuerySqlCombStore');
		
		me.store.addListener('beforeload', function(store, operation, eOpts) {
			var searchParams = operation.getParams();
			if (Ext.isEmpty(searchParams)) {
				searchParams = {};
			}
			if (!Ext.isEmpty(config.active)) {
				searchParams['queryAssemble.active'] = config.active;
			}else{
				searchParams['queryAssemble.active'] = 'Y';
			}
			Ext.apply(store.proxy.extraParams, searchParams);  
		})
		me.callParent([cfg]);
	}
});


/******************报表查询 end *********************/





