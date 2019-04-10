/**
 * 定义功能树的store
 */
Ext.define('Butterfly.baseinfo.orgAdministrative.DepartmentTreeStore',{
	extend: 'Ext.data.TreeStore',
	root: {
		text:'集团',
		id : 'Root',
		expanded: true
	},
	proxy:{
		type:'ajax',
		url:'orgAdministrativeAction!loadDepartmentTree.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			rootProperty:'nodes'
		}
	}
});

/**.
 * <p>
 * 公共方法，通过storeId和model创建STORE<br/>
 * <p>
 * @param  storeId  
 * @param  model   store所用到的model名
 * @param  fields   store所用到的fields
 * @returns store  返回创建的store
 * @author 张贞献
 * @时间 2015-7-22
 */
baseinfo.getStore = function(storeId,model,fields,data) {
	var store = null;
	if(!Ext.isEmpty(storeId)){
		store = Ext.data.StoreManager.lookup(storeId);
	}
	if(Ext.isEmpty(data)){
		data = [];
	}
	if(!Ext.isEmpty(model)){
		if(Ext.isEmpty(store)){
			store = Ext.create('Ext.data.Store', {
				storeId:storeId,
			    model:model,
			    data:data
		     });
		}
	}
	if(!Ext.isEmpty(fields)){
		if(Ext.isEmpty(store)){
			store = Ext.create('Ext.data.Store', {
				storeId:storeId,
			    fields:fields,
			    data:data
		     });
		}
	}
	return store;
};

/**
 * 门店信息
 */
Ext.define('Butterfly.baseinfo.orgAdministrative.SalesDepartmentEntity', {
    extend: 'Ext.data.Model',
    fields : [{
        name : 'id',
        type : 'string'
    },{
        name : 'code',//组织编码
        type : 'string'
    },{
        name : 'name',//组织名称
        type : 'string'
    },{
        name : 'logistCode',//物流代码
        type : 'string'
    },{	
    	name : 'transterCenter',//驻地营业部所属外场
        type : 'string'
    },{
    	name : 'isLeave',//可出发
        type : 'string'
    },{
        name : 'isArrive',//可到达
        type : 'string'
    },{
        name : 'isStation',//是否驻地部门
        type : 'string'
    },{
        name : 'isSpecifiedTime',//是否提供定日达
        type : 'string'
    },{
    	 name : 'isShipment',//是否可发货
        type : 'string'
    },{
        name : 'isDelivery',//是否可送货
        type : 'string'
    },{
        name : 'isPickUp',//是否可自提
        type : 'string'
    },{
        name : 'notes',//备注
        type : 'string'
    },{
        name : 'active',//是否可用
        type : 'string'
    }]
});

/**
 * 平台信息
 */
Ext.define('Butterfly.baseinfo.orgAdministrative.PlatformEntity', {
    extend: 'Ext.data.Model',
    fields : [{
        name : 'id',
        type : 'string'
    },{
        name : 'code',//组织编码
        type : 'string'
    },{
        name : 'name',//组织名称
        type : 'string'
    },{
        name : 'logistCode',//物流代码
        type : 'string'
    },{	
    	name : 'transterCenter',//驻地营业部所属外场
        type : 'string'
    },{
    	name : 'isLeave',//可出发
        type : 'string'
    },{
        name : 'isArrive',//可到达
        type : 'string'
    },{
        name : 'isStation',//是否驻地部门
        type : 'string'
    },{
        name : 'isSpecifiedTime',//是否提供定日达
        type : 'string'
    },{
    	 name : 'isShipment',//是否可发货
        type : 'string'
    },{
        name : 'isDelivery',//是否可送货
        type : 'string'
    },{
        name : 'isPickUp',//是否可自提
        type : 'string'
    },{
        name : 'notes',//备注
        type : 'string'
    },{
        name : 'active',//是否可用
        type : 'string'
    }]
});

/**
 * 场站信息
 */
Ext.define('Butterfly.baseinfo.orgAdministrative.TransferCenterEntity', {
    extend: 'Ext.data.Model',
    fields : [{
        name : 'id',
        type : 'string'
    },{
        name : 'code',//组织编码
        type : 'string'
    },{
        name : 'name',//组织名称
        type : 'string'
    },{
        name : 'logistCode',//物流代码
        type : 'string'
    },{
        name : 'isVehicleAssemble',// 可汽运配载
        type : 'string'
    },{
        name : 'isOutAssemble',//可外发配载
        type : 'string'
    },{
        name : 'isPackingWood',//可打木架
        type : 'string'
    },{
        name : 'isTransfer',//可中转
        type : 'string'
    },{
        name : 'goodsArea',//货区面积
        type : 'string'
    },{
    	name : 'platArea',//货台面积
        type : 'string'
    },{
        name : 'platformCount',//月台总数
        type : 'int'
    },{
    	name : 'notes',//备注
        type : 'string'
    },{
        name : 'active',//是否可用
        type : 'string'
    }]
});

/**
 * 组织信息
 */
Ext.define('Butterfly.baseinfo.orgAdministrative.OrgAdministrativeInfoEntity', {
	 extend: 'Ext.data.Model',
    fields : [{
        name : 'id',
        type : 'string'
    },{
        name : 'code',//组织编码
        type : 'string'
    },{
        name : 'name',//组织名称
        type : 'string'
    },{
        name : 'pinyin',//拼音
        type : 'string'
    },{
    	name : 'simplePinyin',//拼音简写（首字母）
        type : 'string'
    },{
        name : 'managerName',//组织负责人
        type : 'string'
    },{
        name : 'managerCode',//组织负责人工号
        type : 'string'
    },{
        name : 'parentName',//上级组织名称
        type : 'string'
    },{
        name : 'parentCode',//上级组织标编码
        type : 'string'
    },{
        name : 'nature',//组织性质
        type : 'int'
    },{
        name : 'logistCode',//物流代码
        type : 'string'
    },{
        name : 'provinceCode',//省份编码
        type : 'string'
    },{
        name : 'cityCode',//城市编码
        type : 'string'
    },{
        name : 'countyCode',//区县编码
        type : 'string'
    },{
        name : 'provinceName',//省份编码
        type : 'string'
    },{
        name : 'cityName',//城市编码
        type : 'string'
    },{
        name : 'countyName',//区县编码
        type : 'string'
    },{
        name : 'areaCode',//区号
        type : 'string'
    },{
        name : 'phone',//电话
        type : 'string'
    },{
    	name : 'fax',//传真
        type : 'string'
    },{
        name : ' ',//详细地址
        type : 'string'
    },{
    	name : 'lat',//纬度
        type : 'number'
    },{
        name : 'lng',//经度
        type : 'number'
    },{
        name : 'divisionCode',//事业部编码
        type : 'string'
    },{
        name : 'bigRegionCode',//大区编码
        type : 'string'
    },{
    	name : 'divisionName',//事业部名称
        type : 'string'
    },{
        name : 'bigRegionName',//大区名称
        type : 'string'
    },{
    	name : 'isDivision',//是否事业部
        type : 'string'
    },{
    	name : 'isBigRegion',//是否大区
        type : 'string'
    },{
        name : 'isRoadArea',//是否路区
        type : 'string'
    },{
        name : 'isTransferCenter',//是否场站
        type : 'string'
    },{
        name : 'isFleet',//是否车队
        type : 'string'
    },{
        name : 'isSalesDepartment',//是否平台
        type : 'string'
    },{
        name : 'active',//是否可用
        type : 'string'
    },{
        name : 'notes',//是否可用
        type : 'string'
    }]
});


/**
 * 左侧树结构面板
 */
Ext.define('Butterfly.baseinfo.orgAdministrative.DepartmentTreePanle', {
    extend : 'Ext.tree.Panel',
    height : 900,
    minWidth : 200,
	autoScroll:true,
	oldFullPath:null,//刷新之前展开的路径
	useArrows: true,
	rootVisible: true,  
	viewConfig: {plugins: { ptype: 'treeviewdragdrop', appendOnly: true } },
	layoutConfig : {
		// 展开折叠是否有动画效果
		animate : true
	},
	oldId:null,
	//清空数据+关闭form操作
	removeData:function(){
		var me =this;
		//获取主面板
		var orgInfoPanel=me.up('panel').getOrgInfoPanel();
		orgInfoPanel.getOrgAuxiliaryInfoForm().getForm().reset();//清空组织架构基础信息
		orgInfoPanel.getOrgMainInfoForm().getForm().reset();//清空主信息面板的数据
		orgInfoPanel.getOrgBusinessInfoForm().getForm().reset();//清除行政业务属性的原有数据;
		orgInfoPanel.getSaleDeptForm().getForm().reset();//清空门店面板的数据
		orgInfoPanel.getPlatFormForm().getForm().reset();//清空主平台面板的数据
		orgInfoPanel.getTransferCenterForm().getForm().reset();//清除场站面板的数据
			
	},
	//加载数据+和展开页面
	loadDataAndExpand:function(json){
		
		//获得行政组织信息
		orgAdministrativeInfoModel =new Butterfly.baseinfo.orgAdministrative.OrgAdministrativeInfoEntity(json.orgAdministrativeVo.orgAdministrativeInfoEntity);
		//门店

		
		
		
		var me =this;
		//获取主面板
		var orgInfoPanel=me.up('panel').getOrgInfoPanel();
		orgInfoPanel.getOrgAuxiliaryInfoForm().getForm().loadRecord(orgAdministrativeInfoModel);//加载组织架构基础信息
		orgInfoPanel.getOrgMainInfoForm().getForm().loadRecord(orgAdministrativeInfoModel);//加载主信息面板的数据
		 Ext.getCmp('idCPC').setReginValue(orgAdministrativeInfoModel.get('provinceName'),orgAdministrativeInfoModel.get('provinceCode'),1)//省
		 Ext.getCmp('idCPC').setReginValue(orgAdministrativeInfoModel.get('cityName'),orgAdministrativeInfoModel.get('cityCode'),2)//市
		 Ext.getCmp('idCPC').setReginValue(orgAdministrativeInfoModel.get('countyName'),orgAdministrativeInfoModel.get('countyCode'),3)//区
		orgInfoPanel.getOrgBusinessInfoForm().getForm().loadRecord(orgAdministrativeInfoModel);//加载行政业务属性的原有数据;

		//若是行政业务属性中的信息,展开该页面
		if((orgAdministrativeInfoModel.get('isDivision')=='Y')||(orgAdministrativeInfoModel.get('isBigRegion')=='Y')
				||(orgAdministrativeInfoModel.get('isRoadArea')=='Y')||(orgAdministrativeInfoModel.get('isTransferCenter')=='Y')
				||(orgAdministrativeInfoModel.get('isFleet')=='Y')||(orgAdministrativeInfoModel.get('isPlatform')=='Y')
				||(orgAdministrativeInfoModel.get('isSalesDepartment')=='Y')){
			orgInfoPanel.getOrgBusinessInfoForm().expand();
		}else{
			orgInfoPanel.getOrgBusinessInfoForm().collapse();
		}

		//若是门店，展开门店form
		if(orgAdministrativeInfoModel.get('isSalesDepartment')=='Y'){
			salesDepartmentModel =new Butterfly.baseinfo.orgAdministrative.SalesDepartmentEntity(json.orgAdministrativeVo.salesDepartmentEntity);
			orgInfoPanel.getSaleDeptForm().getForm().loadRecord(salesDepartmentModel);
			orgInfoPanel.getSaleDeptForm().expand();
		}else {
			orgInfoPanel.getSaleDeptForm().collapse();
		}
		
		//若是平台，展开平台form
		if(orgAdministrativeInfoModel.get('isPlatform')=='Y'){
			platformModel =new Butterfly.baseinfo.orgAdministrative.PlatformEntity(json.orgAdministrativeVo.platformEntity);
			orgInfoPanel.getPlatFormForm().getForm().loadRecord(platformModel);
			orgInfoPanel.getPlatFormForm().expand();
		}else {
			orgInfoPanel.getPlatFormForm().collapse();
		}

		//若是场站，展开场站form
		if(orgAdministrativeInfoModel.get('isTransferCenter')=='Y'){
			transferCenterModel =new Butterfly.baseinfo.orgAdministrative.TransferCenterEntity(json.orgAdministrativeVo.transferCenterEntity);
			orgInfoPanel.getTransferCenterForm().getForm().loadRecord(transferCenterModel);
			orgInfoPanel.getTransferCenterForm().expand();
		}else {
			orgInfoPanel.getTransferCenterForm().collapse();
		}
		
	},
	//根据用户所在部门操作权限设置按钮
	buttonIsSetDisabled:function(buttonStatus){
		/*var me =this;
		//获取主面板
		var orgInfoPanel=me.up('panel').up('panel').getOrgInfoPanel();
		//根据用户所在部门操作权限设置保存的button属性
		Ext.getCmp('Foss_BaseInfo_OrgAdministrativeInfo_SaveButton_Id').setDisabled(buttonStatus);
		//根据用户所在部门操作权限设置修改的button属性
		Ext.getCmp('Foss_BaseInfo_OrgAdministrativeInfo_UpdateButton_Id').setDisabled(buttonStatus);
		//行政组织业务属性的保存的属性
		orgInfoPanel.getOrgBusinessInfoForm().getDockedItems()[1].items.items[1].setDisabled(buttonStatus);
		//营业部保存的button属性
		orgInfoPanel.getSaleDeptMainPanel().getDockedItems()[1].items.items[1].setDisabled(buttonStatus);
		//营业部修改的button属性
		orgInfoPanel.getSaleDeptMainPanel().getDockedItems()[1].items.items[2].setDisabled(buttonStatus);*/
	},
	//左键单击事件
	treeLeftKeyAction:function(node,record,item,index,e){
	var me = this,
			orgInfoPanel = me.up('panel').getOrgInfoPanel();
			orgMainInfoForm = orgInfoPanel.getOrgMainInfoForm();//主信息form
		if(Ext.isEmpty(record.raw)||record.raw.id==me.oldId){
		/*	Ext.getCmp('Foss_BaseInfo_OrgAdministrativeInfo_SaveButton_Id').setDisabled(false);
			Ext.getCmp('Foss_BaseInfo_OrgAdministrativeInfo_UpdateButton_Id').setDisabled(false);*/
			return;
		}else{
			me.oldId = record.raw.id;
			me.removeData();//发送请求之前先清空数据
			if(!Ext.isEmpty(record.raw)){
				var orgAdministrativeInfoModel = new Butterfly.baseinfo.orgAdministrative.OrgAdministrativeInfoEntity(record.raw.entity);//得到部门的model
				me.oldFullPath = orgAdministrativeInfoModel.get('fullPath');
				orgMainInfoForm.orgAdministrativeInfoModel = orgAdministrativeInfoModel;//设置加载的数据，重置用
			
				var params = {'node':record.raw.id};
		    	var successFun = function(json){
		    		
		    		//加载数据并展开form
		    		me.loadDataAndExpand(json);
		    		
					//先获取数据   再设置为不可编辑
					//orgInfoPanel.colseFun();
					//根据用户的权限设置按钮
					//me.buttonIsSetDisabled(json.orgAdministrativeInfoVo.buttonStatus);
		    	};
		    	
		    	var failureFun = function(json) {
                    if (Ext.isEmpty(json)) {
                        oms.showErrorMes('请求超时'); // 请求超时
                    } else {
                        var message = json.message;
                        oms.showErrorMes(message);
                    }
                };
	
		    	oms.requestJsonAjax('orgAdministrativeAction!loadOrgAllInfo.action', params, successFun, failureFun, false);
		  
			}
		}
	},
	
	resTextfield: null,
	getResTextfield: function(){
		var me = this;
		if(this.resTextfield==null){
			this.resTextfield = Ext.create('Ext.form.field.Text',{
				height:25,
				columnWidth: 0.7,
		        emptyText: '部门名称',//'输入功能名',
		        /*margin:'0 0 0 19',*/
		        name: 'name',
		      /*  regex:  /^(\w|[\u4E00-\u9FA5])*$/,
		        regexText: '非法字符',*/
		        listeners: {
		        	specialkey: function(field, e){
	                    if (e.getKey() == e.ENTER) {
	                        me.getQueryButton().handler();
	                    }
	                }
		        }
			});
		}
		return this.resTextfield;
	},
	queryButton: null,
	getQueryButton: function(){
		var me = this;
		if(this.queryButton==null){
			this.queryButton = Ext.create('Ext.button.Button',{ 
		    	height:25,
		    	margin:'0 0 0 3',
		    	columnWidth: 0.3,
		    	text: '查询',
		    	handler: function(){
		    		var field = me.getResTextfield(),
		    			queryValue = field.getValue();
		    		if(!Ext.isEmpty(queryValue)){
		    			var params = {'node' : queryValue};
		    			var successFun = function(json) {
		    				var view = me.getView(),
							position = false,
    						pathList = json.pathList;
	    					me.expandNodes = [];
	    					me.collapseAll();
	    					if(pathList.length==0){
	    						Ext.toast('没有找到部门','温馨提示','t');
	    						return;
	    					}
	    					for(var i=0;i<pathList.length;i++){
	    						oms.log(pathList[i]);
	    						me.expandPath(pathList[i],'id','/',function(success, lastNode){
	    							if(success){
	    								var nodeHtmlEl = view.getNode(lastNode),
	    									nodeEl = Ext.get(nodeHtmlEl);
	    								if(Ext.isEmpty(nodeEl)){
	    									me.expandNodes.push(lastNode);
	    									return;
	    								}
	    								var divHtmlEl = nodeEl.query('div')[0],
	    								    divEl = Ext.get(divHtmlEl);
	    								divEl.highlight("ff0000", { attr: 'color', duration:10000 });
										if(!position){
											divHtmlEl.scrollIntoView();
											position = true;
										}
	    							}else{
	    								oms.log('展开失败');
	    							}
	    						});	    						
	    					}
		    	        };
		    	        var failureFun = function(json) {
		    	            if (Ext.isEmpty(json)) {
		    	                oms.showErrorMes('请求超时'); // 请求超时
		    	            } else {
		    	                var message = json.message;
		    	                oms.showErrorMes(message);
		    	            }
		    	        };
		    	        //Ajax请求得到所有查询到的节点全路径
		    	        oms.requestJsonAjax('orgAdministrativeAction!queryTreePathForName.action', params, successFun, failureFun);
		    		}
		    	}
		    });
		}
		return this.queryButton;
	},
	
    constructor : function(config) {
		var me = this, 
			cfg = Ext.apply({}, config);
		me.store = Ext.create('Butterfly.baseinfo.orgAdministrative.DepartmentTreeStore');
		
		me.dockedItems = [{
		    xtype: 'toolbar',
		    dock: 'top',
		    layout: 'column',
		    id: 'baseinfo_orgAdministrativeToolbar_id',
		    items: [me.getResTextfield(),me.getQueryButton()]
		}];
		
		// 监听事件
		me.listeners={
		  	scrollershow: function(scroller) {
		  		if (scroller && scroller.scrollEl) {
		  				scroller.clearManagedListeners(); 
		  				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
		  		}
		  	},
	    	itemclick : me.treeLeftKeyAction//单击事件
	    },
		me.callParent([cfg]);
     }
});

/**
 * =============================================@description 行政组织查主界面=============================================
 */
Ext.define('Butterfly.baseinfo.orgAdministrative.OrgInfoPanel', {
	extend:'Ext.panel.Panel', 
	frame:true,
	id:'oms_baseinfo_orgAdministrative_OrgInfoPanel_Id',
	height:1782,
	layout:'auto',
	//定义mask 罩
	autoScroll:true,
	myMask:null,
	getMyMask:function(){
		if(Ext.isEmpty(this.myMask)){
			this.myMask = new Ext.LoadMask(this, {msg:"Please wait..."});
		}
		return this.myMask;
	},
	//组织架构基本信息FORM
    orgAuxiliaryInfoForm:null,
	getOrgAuxiliaryInfoForm:function(){
	if(Ext.isEmpty(this.orgAuxiliaryInfoForm)){
    		this.orgAuxiliaryInfoForm = Ext.create('Butterfly.baseinfo.orgAdministrative.OrgAuxiliaryInfoForm');
    	}
    	return this.orgAuxiliaryInfoForm;
	},
	
	//行政组织主信息FORM
	orgMainInfoForm:null,
	getOrgMainInfoForm:function(){
		if(Ext.isEmpty(this.orgMainInfoForm)){
    		this.orgMainInfoForm = Ext.create('Butterfly.baseinfo.orgAdministrative.OrgMainInfoForm');
    		//this.orgMainInfoForm.getForm().findField('orgEnSimple').allowBlank = false;//不可为空
    	}
    	return this.orgMainInfoForm;
	},
	
	//行政组织业务主信息FORM
	orgBusinessInfoForm:null,
	getOrgBusinessInfoForm:function(){
		if(Ext.isEmpty(this.orgBusinessInfoForm)){
    		this.orgBusinessInfoForm = Ext.create('Butterfly.baseinfo.orgAdministrative.OrgBusinessInfoForm');
    		
    	}
    	return this.orgBusinessInfoForm;
	},
	
	//门店主信息FORM
	saleDeptForm:null,
	getSaleDeptForm:function(){
		if(Ext.isEmpty(this.saleDeptForm)){
    		this.saleDeptForm = Ext.create('Butterfly.baseinfo.orgAdministrative.SaleDeptMainForm');
    		//this.orgMainInfoForm.getForm().findField('orgEnSimple').allowBlank = false;//不可为空
    	}
    	return this.saleDeptForm;
	},
	
	//平台主信息FORM
	platFormForm:null,
	getPlatFormForm:function(){
		if(Ext.isEmpty(this.platFormForm)){
    		this.platFormForm = Ext.create('Butterfly.baseinfo.orgAdministrative.platFormMainForm');
    	}
    	return this.platFormForm;
	},
	
	//场站主信息FORM
	transferCenterForm:null,
	getTransferCenterForm:function(){
		if(Ext.isEmpty(this.transferCenterForm)){
    		this.transferCenterForm = Ext.create('Butterfly.baseinfo.orgAdministrative.TransferCenterMainForm');
    	}
    	return this.transferCenterForm;
	},
	
	constructor : function(config) {
			var me = this, 
				cfg = Ext.apply({}, config);
			me.items = [me.getOrgAuxiliaryInfoForm(),//组织架构基本信息
			            me.getOrgMainInfoForm(),//组织属性信息
			            me.getOrgBusinessInfoForm(),//行政组织业务属性
			            me.getSaleDeptForm(),//门店主界面
			            me.getPlatFormForm(),//平台主界面
			            me.getTransferCenterForm()//场站主界面
			            ];
			me.callParent([cfg]);
	}
});

/**
 * @description 组织架构基本信息查看界面(不可编辑基础属性)
 */
Ext.define('Butterfly.baseinfo.orgAdministrative.OrgAuxiliaryInfoForm', {
	extend : 'Ext.form.Panel',
	title : baseinfo.orgAdministrative.i18n('bse.orgAdministrative.OrgAuxiliaryInfo'),//组织架构基本信息
	animCollapse : true,
	collapsible : true,
	//collapsed : true,  //展开 false
	frame : true,
	width : 'auto',
    height : 280,
    layout : {
		type:'table',
		columns: 2
	},
    defaults : {
    	colspan : 1,
    	xtype:'displayfield',
    	labelWidth:110,
    	width:350,
    	margin : '3 5 3 35'
    },
    constructor : function(config) {
		var me = this, 
			cfg = Ext.apply({}, config);
			me.fbar = [];
		me.items = [{
			name: 'code',
	        fieldLabel: '组织编码'//组织编码
		},{
			name: 'parentCode',
	        fieldLabel: '上级组织编码'//上级组织编码
		},{
			name: 'name',
	        fieldLabel: '组织名称'//组织名称
		},{
			name: 'parentName',
	        fieldLabel: '上级组织名称'//上级组织名称
		},{
			name: 'divisionCode',
	        fieldLabel: '所属事业部编码'//组织编码
		},{
			name: 'bigRegionCode',
	        fieldLabel: '所属大区编码'//上级组织编码
		},{
			name: 'divisionName',
	        fieldLabel: '所属事业部名称'//组织名称
		},{
			name: 'bigRegionName',
	        fieldLabel: '所属大区名称'//上级组织名称
		},{
			name: 'managerCode',
	        fieldLabel: '组织负责人'//组织负责人
		},{
			name: 'logistCode',
	        fieldLabel: '物流代码'//物流代码
		},{
			name: 'managerName',
	        fieldLabel: '组织负责人姓名'//组织负责人姓名
	    }];
		me.callParent([cfg]);
	}
});

/**
 * @description 组织属性信息（可编辑修改的）
 */
Ext.define('Butterfly.baseinfo.orgAdministrative.OrgMainInfoForm', {
	extend:'Ext.form.Panel',  
	title: baseinfo.orgAdministrative.i18n('bse.orgAdministrative.OrgMainInfo'),//查看/修改详情
    height:460,
    width:'auto',
    frame:true,
    collapsible :true,
    animCollapse : true,
    orgAdministrativeInfoModel:null,//加载的数据Model
    outfieldModel:null,//外场相关信息
    motorcadeModel:null,//车队相关信息
    billingGroupTransFerModel:null,//集中开单组外场相关信息
    saleDepartmentModel:null,//营业部
    salesMotorcadeEntityList:[],//营业部车队信息

    layout:{
		type:'table',
		columns: 6
	},
    defaults : {
    	colspan : 1,
    	readOnly:true,
    	labelWidth:110,
    	width:350,
    	margin : '13 5 3 35'
    },
 

    constructor : function(config) {
		var me = this, 
			cfg = Ext.apply({}, config);
	/*	me.fbar = [{
			xtype : 'button', 
			width:70,
			hidden:true,
			text : '重置',//重置
			handler : function() {
				Ext.toast('没有找到部门','提示消息','tl');
	    						//"br"/"bl"/"tr"/"tl"/"t"/"l"/"b"/"r"
				me.getForm().loadRecord(me.orgAdministrativeInfoModel);
			}
		},{
			xtype : 'button', 
			id:'oms_baseInfo_orgAdministrative_saveButton_Id',
			width:80,
			//hidden:!baseinfo.orgAdministrativeInfo.isPermission('queryOrgBiz/queryOrgBizSaveButton'),
			text : '保存',//保存
			cls:'yellow_button',
			handler : function() {
				me.saveOrgAndSalesInfo(this);
				Ext.toast('没有找到部门','提示消息','l');
	    						//"br"/"bl"/"tr"/"tl"/"t"/"l"/"b"/"r"
			}
		},{
			xtype : 'button', 
			id:'oms_baseInfo_orgAdministrative_updateButton_Id',
			width:80,
			//hidden:!baseinfo.orgAdministrativeInfo.isPermission('queryOrgBiz/queryOrgBizUpdateButton'),
			text :'修改',//修改
			cls:'yellow_button',
			handler : function() {
				Ext.toast('没有找到部门','提示消息','t');
	    						//"br"/"bl"/"tr"/"tl"/"t"/"l"/"b"/"r"
				me.updateFun();
			}
		}];*/
		me.items = [{
			name: 'pinyin',
			colspan : 3,
			width:300,
			labelWidth:100,
	        fieldLabel: '部门拼音',//部门编号
	        xtype : 'textfield'
		},{
			name: 'simplePinyin',
			colspan : 3,
			width:300,
			labelWidth:100,
	        fieldLabel: '拼音首字母',//标杆编码
	        xtype : 'textfield'
		},{
          	//fieldLabel : '省市区',
         	id:'idCPC',
  			layout : {
  				type: 'table',
  		        columns: 3
  			},
  			readOnly:true,
  			colspan : 6,
  			provinceLabel : '省',
  			provinceName:'provinceCode',//省名称
  			cityLabel : '市',
  			cityName : 'cityCode',//名称
  			areaLabel : '县',
  			areaName : 'countyCode',// 县名称
  			provinceLabelWidth : 100,//省label宽度
			cityLabelWidth : 100,// 城市长度
			areaLabelWidth : 100,// 县长度
			cityWidth : 213,// 城市长度
			areaWidth : 214,// 县长度
  			provinceWidth : 213,// 省份长度
  			type : 'P-C-C',
  			xtype : 'linkregincombselector',
  			columnWidth: 1
        },{
			name: 'areaCode',
			colspan : 3,
			width:300,
			labelWidth:100,
	        fieldLabel: '区号',//部门名称
	        xtype : 'textfield'
		},{
			name: 'phone',
			colspan : 3,
			width:300,
			labelWidth:100,
	        fieldLabel: '电话',//部门名称
	        xtype : 'textfield'
		},{
			name: 'lng',
			colspan : 3,
			width:300,
			labelWidth:100,
	        fieldLabel: '经度',//部门名称
	        decimalPrecision:6,
			maxValue: 180.000000,
			maxLength:11,
			minValue:-180.000000,
	        step:0.000001,
	        xtype : 'numberfield'
		},{
			name: 'fax',
			colspan : 3,
			width:300,
			labelWidth:100,
	        fieldLabel: '传真',//部门名称
	        xtype : 'textfield'
		},{
			name: 'lat',
			colspan : 6,
			width:300,
			labelWidth:100,
	        fieldLabel: '纬度',//部门名称
	        decimalPrecision:6,
			maxValue: 90.000000,
			maxLength:10,
			minValue:-90.000000,
	        step:0.000001,
	        xtype : 'numberfield'
		},{
			name: 'addressDetail',
			colspan : 6,
			labelWidth:100,
			width:640,
	        fieldLabel: '详细地址',//部门名称
	        xtype : 'textarea'
		},{
			name: 'notes',
			colspan : 6,
			labelWidth:100,
			width:640,
	        fieldLabel: '备注',//部门名称
	        xtype : 'textarea'
		}];
		me.callParent([cfg]);
	}
});

/**
 * @description 行政组织业务属性（目前不可编辑）
 */
Ext.define('Butterfly.baseinfo.orgAdministrative.OrgBusinessInfoForm', {
	extend:'Ext.form.Panel',  
	title: baseinfo.orgAdministrative.i18n('bse.orgAdministrative.OrgBusinessInfo'),//查看/修改详情
    height:160,
    width:'auto',
    frame:true,
    collapsible :true,
    animCollapse : true,
    orgAdministrativeInfoModel:null,//加载的数据Model

    layout:{
		type:'table',
		columns: 6
	},
    defaults : {
    	colspan : 1,
    	readOnly:true,
    	labelWidth:110,
    	width:350,
    	margin : '13 5 3 35'
    },


    constructor : function(config) {
		var me = this, 
			cfg = Ext.apply({}, config);
		/*me.fbar = [{
			xtype : 'button', 
			width:70,
			hidden:true,
			text : '重置',//重置
			handler : function() {

			}
		},{
			xtype : 'button', 
			id:'oms_baseInfo_OrgBusinessInfoForm_saveButto_Id',
			width:80,	
			hidden : true,
			//hidden:!baseinfo.orgAdministrativeInfo.isPermission('queryOrgBiz/queryOrgBizSaveButton'),
			text : '保存',//保存
			cls:'yellow_button',
			handler : function() {
				me.saveOrgAndSalesInfo(this);
			}
		},{
			xtype : 'button', 
			id:'oms_baseInfo_OrgBusinessInfoForm_updateButto_Id',
			width:80,
			hidden : true,
			//hidden:!baseinfo.orgAdministrativeInfo.isPermission('queryOrgBiz/queryOrgBizUpdateButton'),
			text :'修改',//修改
			cls:'yellow_button',
			handler : function() {
				me.updateFun();
			}
		}];*/
		me.items = [{
			boxLabel  : '事业部',//事业部
            name  : 'isDivision',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){
            		/*//获取主面板
            		var orgMainInfoForm	=Ext.getCmp('Foss_baseinfo_orgAdministrativeInfo_OrgInfoPanel_Id').getOrgMainInfoForm();
            		if(newvalue){
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').show();
            			if(me.up('panel').getOutfieldMainForm().getForm().findField('transferCenter').getValue()){ //如果是外場，就不能是配載部門 和空運總調
            				checkbox.setValue(false);
                        	baseinfo.showErrorMes(baseinfo.orgAdministrativeInfo.i18n('foss.baseinfo.departmentHasFieldCanNoLongerCheckAirDispatch'));//该部门已经是外场！
                        	return;
            			}
            			//选择了可空运配载，必须选择可空运总调
            			me.getForm().findField('airDispatch').setValue('Y');
            			me.doLayout();
            		}else{
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').reset();
            			me.getForm().findField('airDispatch').hide();
            			me.doLayout();
            		}*/
            	}
            }
		},{
			boxLabel  : '大区',//大区
            name  : 'isBigRegion',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){
            		/*//获取主面板
            		var orgMainInfoForm	=Ext.getCmp('Foss_baseinfo_orgAdministrativeInfo_OrgInfoPanel_Id').getOrgMainInfoForm();
            		if(newvalue){
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').show();
            			if(me.up('panel').getOutfieldMainForm().getForm().findField('transferCenter').getValue()){ //如果是外場，就不能是配載部門 和空運總調
            				checkbox.setValue(false);
                        	baseinfo.showErrorMes(baseinfo.orgAdministrativeInfo.i18n('foss.baseinfo.departmentHasFieldCanNoLongerCheckAirDispatch'));//该部门已经是外场！
                        	return;
            			}
            			//选择了可空运配载，必须选择可空运总调
            			me.getForm().findField('airDispatch').setValue('Y');
            			me.doLayout();
            		}else{
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').reset();
            			me.getForm().findField('airDispatch').hide();
            			me.doLayout();
            		}*/
            	}
            }
		},{
			boxLabel  : '路区',//事业部
            name  : 'isRoadArea',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){
            		/*//获取主面板
            		var orgMainInfoForm	=Ext.getCmp('Foss_baseinfo_orgAdministrativeInfo_OrgInfoPanel_Id').getOrgMainInfoForm();
            		if(newvalue){
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').show();
            			if(me.up('panel').getOutfieldMainForm().getForm().findField('transferCenter').getValue()){ //如果是外場，就不能是配載部門 和空運總調
            				checkbox.setValue(false);
                        	baseinfo.showErrorMes(baseinfo.orgAdministrativeInfo.i18n('foss.baseinfo.departmentHasFieldCanNoLongerCheckAirDispatch'));//该部门已经是外场！
                        	return;
            			}
            			//选择了可空运配载，必须选择可空运总调
            			me.getForm().findField('airDispatch').setValue('Y');
            			me.doLayout();
            		}else{
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').reset();
            			me.getForm().findField('airDispatch').hide();
            			me.doLayout();
            		}*/
            	}
            }
		},{
			boxLabel  : '场站',//大区
            name  : 'isTransferCenter',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){
            		/*//获取主面板
            		var orgMainInfoForm	=Ext.getCmp('Foss_baseinfo_orgAdministrativeInfo_OrgInfoPanel_Id').getOrgMainInfoForm();
            		if(newvalue){
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').show();
            			if(me.up('panel').getOutfieldMainForm().getForm().findField('transferCenter').getValue()){ //如果是外場，就不能是配載部門 和空運總調
            				checkbox.setValue(false);
                        	baseinfo.showErrorMes(baseinfo.orgAdministrativeInfo.i18n('foss.baseinfo.departmentHasFieldCanNoLongerCheckAirDispatch'));//该部门已经是外场！
                        	return;
            			}
            			//选择了可空运配载，必须选择可空运总调
            			me.getForm().findField('airDispatch').setValue('Y');
            			me.doLayout();
            		}else{
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').reset();
            			me.getForm().findField('airDispatch').hide();
            			me.doLayout();
            		}*/
            	}
            }
		},{
			boxLabel  : '车队',//大区
            name  : 'isFleet',
            colspan : 2,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){
            		/*//获取主面板
            		var orgMainInfoForm	=Ext.getCmp('Foss_baseinfo_orgAdministrativeInfo_OrgInfoPanel_Id').getOrgMainInfoForm();
            		if(newvalue){
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').show();
            			if(me.up('panel').getOutfieldMainForm().getForm().findField('transferCenter').getValue()){ //如果是外場，就不能是配載部門 和空運總調
            				checkbox.setValue(false);
                        	baseinfo.showErrorMes(baseinfo.orgAdministrativeInfo.i18n('foss.baseinfo.departmentHasFieldCanNoLongerCheckAirDispatch'));//该部门已经是外场！
                        	return;
            			}
            			//选择了可空运配载，必须选择可空运总调
            			me.getForm().findField('airDispatch').setValue('Y');
            			me.doLayout();
            		}else{
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').reset();
            			me.getForm().findField('airDispatch').hide();
            			me.doLayout();
            		}*/
            	}
            }
		},{
			boxLabel  : '平台',//事业部
            name  : 'isPlatform',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){
            		/*//获取主面板
            		var orgMainInfoForm	=Ext.getCmp('Foss_baseinfo_orgAdministrativeInfo_OrgInfoPanel_Id').getOrgMainInfoForm();
            		if(newvalue){
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').show();
            			if(me.up('panel').getOutfieldMainForm().getForm().findField('transferCenter').getValue()){ //如果是外場，就不能是配載部門 和空運總調
            				checkbox.setValue(false);
                        	baseinfo.showErrorMes(baseinfo.orgAdministrativeInfo.i18n('foss.baseinfo.departmentHasFieldCanNoLongerCheckAirDispatch'));//该部门已经是外场！
                        	return;
            			}
            			//选择了可空运配载，必须选择可空运总调
            			me.getForm().findField('airDispatch').setValue('Y');
            			me.doLayout();
            		}else{
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').reset();
            			me.getForm().findField('airDispatch').hide();
            			me.doLayout();
            		}*/
            	}
            }
		},{
			boxLabel  : '门店',//大区
            name  : 'isSalesDepartment',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){
            		/*//获取主面板
            		var orgMainInfoForm	=Ext.getCmp('Foss_baseinfo_orgAdministrativeInfo_OrgInfoPanel_Id').getOrgMainInfoForm();
            		if(newvalue){
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').show();
            			if(me.up('panel').getOutfieldMainForm().getForm().findField('transferCenter').getValue()){ //如果是外場，就不能是配載部門 和空運總調
            				checkbox.setValue(false);
                        	baseinfo.showErrorMes(baseinfo.orgAdministrativeInfo.i18n('foss.baseinfo.departmentHasFieldCanNoLongerCheckAirDispatch'));//该部门已经是外场！
                        	return;
            			}
            			//选择了可空运配载，必须选择可空运总调
            			me.getForm().findField('airDispatch').setValue('Y');
            			me.doLayout();
            		}else{
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').reset();
            			me.getForm().findField('airDispatch').hide();
            			me.doLayout();
            		}*/
            	}
            }
		}];
		me.callParent([cfg]);
	}
});

/**
 * @description 门店主界面
 */
Ext.define('Butterfly.baseinfo.orgAdministrative.SaleDeptMainForm', {
	extend:'Ext.form.Panel',  
	title: baseinfo.orgAdministrative.i18n('bse.orgAdministrative.SaleDepartment'),//门店信息
    height:280,
    width:'auto',
    frame:true,
    collapsible :true,
    animCollapse : true,
    orgAdministrativeInfoModel:null,//加载的数据Model

    layout:{
		type:'table',
		columns: 6
	},
    defaults : {
    	colspan : 1,
    	readOnly:true,
    	labelWidth:110,
    	width:350,
    	margin : '13 5 3 35'
    },


    constructor : function(config) {
		var me = this, 
			cfg = Ext.apply({}, config);
	/*	me.fbar = [{
			xtype : 'button', 
			width:70,
			hidden:true,
			text : '重置',//重置
			handler : function() {
				Ext.toast('没有找到部门','提示消息','tl');
	    						//"br"/"bl"/"tr"/"tl"/"t"/"l"/"b"/"r"
				me.getForm().loadRecord(me.orgAdministrativeInfoModel);
			}
		},{
			xtype : 'button', 
			id:'oms_baseInfo_saleDeptMainForm_saveButto_Id',
			width:80,
			//hidden:!baseinfo.orgAdministrativeInfo.isPermission('queryOrgBiz/queryOrgBizSaveButton'),
			text : '保存',//保存
			cls:'yellow_button',
			handler : function() {
				me.saveOrgAndSalesInfo(this);
				Ext.toast('没有找到部门','提示消息','br');
	    						//"br"/"bl"/"tr"/"tl"/"t"/"l"/"b"/"r"
			}
		},{
			xtype : 'button', 
			id:'oms_baseInfo_saleDeptMainForm_updateButto_Id',
			width:80,
			//hidden:!baseinfo.orgAdministrativeInfo.isPermission('queryOrgBiz/queryOrgBizUpdateButton'),
			text :'修改',//修改
			cls:'yellow_button',
			handler : function() {
				Ext.toast('没有找到部门','提示消息','tl');
	    						//"br"/"bl"/"tr"/"tl"/"t"/"l"/"b"/"r"
				me.updateFun();
			}
		}];*/
		me.items = [{
			boxLabel  : '可出发',//可出发
            name  : 'isLeave',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){
            		/*//获取主面板
            		var orgMainInfoForm	=Ext.getCmp('Foss_baseinfo_orgAdministrativeInfo_OrgInfoPanel_Id').getOrgMainInfoForm();
            		if(newvalue){
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').show();
            			if(me.up('panel').getOutfieldMainForm().getForm().findField('transferCenter').getValue()){ //如果是外場，就不能是配載部門 和空運總調
            				checkbox.setValue(false);
                        	baseinfo.showErrorMes(baseinfo.orgAdministrativeInfo.i18n('foss.baseinfo.departmentHasFieldCanNoLongerCheckAirDispatch'));//该部门已经是外场！
                        	return;
            			}
            			//选择了可空运配载，必须选择可空运总调
            			me.getForm().findField('airDispatch').setValue('Y');
            			me.doLayout();
            		}else{
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').reset();
            			me.getForm().findField('airDispatch').hide();
            			me.doLayout();
            		}*/
            	}
            }
		},{
			boxLabel  : '可到达',//可到达
            name  : 'isArrive',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){

            	}
            }
		},{
			boxLabel  : '提供定日达',//提供定日达
            name  : 'isSpecifiedTime',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){

            	}
            }
		},{
			boxLabel  : '可发货',//可发货
            name  : 'isShipment',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){

            	}
            }
		},{
			boxLabel  : '可送货',//事业部
            name  : 'isDelivery',
            colspan : 2,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){

            	}
            }
		},{
			boxLabel  : '可自提',//事业部
            name  : 'isDelivery',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){

            	}
            }
		},{
			boxLabel  : '驻地部门',//驻地部门
            name  : 'isStation',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){
            		/*//获取主面板
            		var orgMainInfoForm	=Ext.getCmp('Foss_baseinfo_orgAdministrativeInfo_OrgInfoPanel_Id').getOrgMainInfoForm();
            		if(newvalue){
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').show();
            			if(me.up('panel').getOutfieldMainForm().getForm().findField('transferCenter').getValue()){ //如果是外場，就不能是配載部門 和空運總調
            				checkbox.setValue(false);
                        	baseinfo.showErrorMes(baseinfo.orgAdministrativeInfo.i18n('foss.baseinfo.departmentHasFieldCanNoLongerCheckAirDispatch'));//该部门已经是外场！
                        	return;
            			}
            			//选择了可空运配载，必须选择可空运总调
            			me.getForm().findField('airDispatch').setValue('Y');
            			me.doLayout();
            		}else{
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').reset();
            			me.getForm().findField('airDispatch').hide();
            			me.doLayout();
            		}*/
            	}
            }
		},{
            name : 'transterCenter',                    
			fieldLabel : '所属场站',
			isTransferCenter : 'Y',
			depart : 'Y',
			colspan : 4,
			width:280,
        	xtype : 'dynamicorgcombselector'
        },{
			name: 'notes',
			colspan : 6,
			labelWidth:100,
			width:640,
	        fieldLabel: '备注',//部门名称
	        xtype : 'textarea'
		}];
		me.callParent([cfg]);
	}
});

/**
 * @description 平台界面
 */
Ext.define('Butterfly.baseinfo.orgAdministrative.platFormMainForm', {
	extend:'Ext.form.Panel',  
	title: baseinfo.orgAdministrative.i18n('bse.orgAdministrative.PlatForm'),//平台信息
    height:280,
    width:'auto',
    frame:true,
    collapsible :true,
    animCollapse : true,
    orgAdministrativeInfoModel:null,//加载的数据Model

    layout:{
		type:'table',
		columns: 6
	},
    defaults : {
    	colspan : 1,
    	readOnly:true,
    	labelWidth:110,
    	width:350,
    	margin : '13 5 3 35'
    },


    constructor : function(config) {
		var me = this, 
			cfg = Ext.apply({}, config);
	/*	me.fbar = [{
			xtype : 'button', 
			width:70,
			hidden:true,
			text : '重置',//重置
			handler : function() {
				Ext.toast('没有找到部门','提示消息','tl');
	    						//"br"/"bl"/"tr"/"tl"/"t"/"l"/"b"/"r"
				me.getForm().loadRecord(me.orgAdministrativeInfoModel);
			}
		},{
			xtype : 'button', 
			id:'oms_baseInfo_platFormMainForm_saveButto_Id',
			width:80,
			//hidden:!baseinfo.orgAdministrativeInfo.isPermission('queryOrgBiz/queryOrgBizSaveButton'),
			text : '保存',//保存
			cls:'yellow_button',
			handler : function() {
				me.saveOrgAndSalesInfo(this);
				Ext.toast('没有找到部门','提示消息','br');
	    						//"br"/"bl"/"tr"/"tl"/"t"/"l"/"b"/"r"
			}
		},{
			xtype : 'button', 
			id:'oms_baseInfo_platFormMainForm_updateButto_Id',
			width:80,
			//hidden:!baseinfo.orgAdministrativeInfo.isPermission('queryOrgBiz/queryOrgBizUpdateButton'),
			text :'修改',//修改
			cls:'yellow_button',
			handler : function() {
				Ext.toast('没有找到部门','提示消息','tl');
	    						//"br"/"bl"/"tr"/"tl"/"t"/"l"/"b"/"r"
				me.updateFun();
			}
		}];*/
		me.items = [{
			boxLabel  : '可出发',//可出发
            name  : 'isLeave',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){
            		/*//获取主面板
            		var orgMainInfoForm	=Ext.getCmp('Foss_baseinfo_orgAdministrativeInfo_OrgInfoPanel_Id').getOrgMainInfoForm();
            		if(newvalue){
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').show();
            			if(me.up('panel').getOutfieldMainForm().getForm().findField('transferCenter').getValue()){ //如果是外場，就不能是配載部門 和空運總調
            				checkbox.setValue(false);
                        	baseinfo.showErrorMes(baseinfo.orgAdministrativeInfo.i18n('foss.baseinfo.departmentHasFieldCanNoLongerCheckAirDispatch'));//该部门已经是外场！
                        	return;
            			}
            			//选择了可空运配载，必须选择可空运总调
            			me.getForm().findField('airDispatch').setValue('Y');
            			me.doLayout();
            		}else{
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').reset();
            			me.getForm().findField('airDispatch').hide();
            			me.doLayout();
            		}*/
            	}
            }
		},{
			boxLabel  : '可到达',//可到达
            name  : 'isArrive',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){

            	}
            }
		},{
			boxLabel  : '提供定日达',//提供定日达
            name  : 'isSpecifiedTime',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){

            	}
            }
		},{
			boxLabel  : '可发货',//可发货
            name  : 'isShipment',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){

            	}
            }
		},{
			boxLabel  : '可送货',//事业部
            name  : 'isDelivery',
            colspan : 2,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){

            	}
            }
		},{
			boxLabel  : '可自提',//事业部
            name  : 'isDelivery',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){

            	}
            }
		},{
			boxLabel  : '驻地部门',//驻地部门
            name  : 'isStation',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){
            		/*//获取主面板
            		var orgMainInfoForm	=Ext.getCmp('Foss_baseinfo_orgAdministrativeInfo_OrgInfoPanel_Id').getOrgMainInfoForm();
            		if(newvalue){
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').show();
            			if(me.up('panel').getOutfieldMainForm().getForm().findField('transferCenter').getValue()){ //如果是外場，就不能是配載部門 和空運總調
            				checkbox.setValue(false);
                        	baseinfo.showErrorMes(baseinfo.orgAdministrativeInfo.i18n('foss.baseinfo.departmentHasFieldCanNoLongerCheckAirDispatch'));//该部门已经是外场！
                        	return;
            			}
            			//选择了可空运配载，必须选择可空运总调
            			me.getForm().findField('airDispatch').setValue('Y');
            			me.doLayout();
            		}else{
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').reset();
            			me.getForm().findField('airDispatch').hide();
            			me.doLayout();
            		}*/
            	}
            }
		},{
            name : 'transterCenter',                    
			fieldLabel : '所属场站',
			isTransferCenter : 'Y',
			depart : 'Y',
			colspan : 4,
			width:280,
        	xtype : 'dynamicorgcombselector'
        },{
			name: 'notes',
			colspan : 6,
			labelWidth:100,
			width:640,
	        fieldLabel: '备注',//部门名称
	        xtype : 'textarea'
		}];
		me.callParent([cfg]);
	}
});

/**
 * @description 场站主界面
 */
Ext.define('Butterfly.baseinfo.orgAdministrative.TransferCenterMainForm', {
	extend:'Ext.form.Panel',  
	title: baseinfo.orgAdministrative.i18n('bse.orgAdministrative.TransferCenter'),//门店信息
    height:320,
    width:'auto',
    frame:true,
    collapsible :true,
    animCollapse : true,
    orgAdministrativeInfoModel:null,//加载的数据Model

    layout:{
		type:'table',
		columns: 6
	},
    defaults : {
    	colspan : 1,
    	readOnly:true,
    	labelWidth:110,
    	width:350,
    	margin : '13 5 3 35'
    },


    constructor : function(config) {
		var me = this, 
			cfg = Ext.apply({}, config);
		/*me.fbar = [{
			xtype : 'button', 
			width:70,
			hidden:true,
			text : '重置',//重置
			handler : function() {
				Ext.toast('没有找到部门','提示消息','tl');
	    						//"br"/"bl"/"tr"/"tl"/"t"/"l"/"b"/"r"
				me.getForm().loadRecord(me.orgAdministrativeInfoModel);
			}
		},{
			xtype : 'button', 
			id:'oms_baseInfo_transferCenterMainForm_saveButto_Id',
			width:80,
			//hidden:!baseinfo.orgAdministrativeInfo.isPermission('queryOrgBiz/queryOrgBizSaveButton'),
			text : '保存',//保存
			cls:'yellow_button',
			handler : function() {
				me.saveOrgAndSalesInfo(this);
				Ext.toast('没有找到部门','提示消息','br');
	    						//"br"/"bl"/"tr"/"tl"/"t"/"l"/"b"/"r"
			}
		},{
			xtype : 'button', 
			id:'oms_baseInfo_transferCenterMainForm_updateButto_Id',
			width:80,
			//hidden:!baseinfo.orgAdministrativeInfo.isPermission('queryOrgBiz/queryOrgBizUpdateButton'),
			text :'修改',//修改
			cls:'yellow_button',
			handler : function() {
				Ext.toast('没有找到部门','提示消息','tl');
	    						//"br"/"bl"/"tr"/"tl"/"t"/"l"/"b"/"r"
				me.updateFun();
			}
		}];*/
		me.items = [{
			boxLabel  : '汽运配载',//事业部
            name  : 'isVehicleAssemble',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){
            		/*//获取主面板
            		var orgMainInfoForm	=Ext.getCmp('Foss_baseinfo_orgAdministrativeInfo_OrgInfoPanel_Id').getOrgMainInfoForm();
            		if(newvalue){
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').show();
            			if(me.up('panel').getOutfieldMainForm().getForm().findField('transferCenter').getValue()){ //如果是外場，就不能是配載部門 和空運總調
            				checkbox.setValue(false);
                        	baseinfo.showErrorMes(baseinfo.orgAdministrativeInfo.i18n('foss.baseinfo.departmentHasFieldCanNoLongerCheckAirDispatch'));//该部门已经是外场！
                        	return;
            			}
            			//选择了可空运配载，必须选择可空运总调
            			me.getForm().findField('airDispatch').setValue('Y');
            			me.doLayout();
            		}else{
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').reset();
            			me.getForm().findField('airDispatch').hide();
            			me.doLayout();
            		}*/
            	}
            }
		},{
			boxLabel  : '外发配载',//事业部
            name  : 'isOutAssemble',
            colspan : 1,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){
            		/*//获取主面板
            		var orgMainInfoForm	=Ext.getCmp('Foss_baseinfo_orgAdministrativeInfo_OrgInfoPanel_Id').getOrgMainInfoForm();
            		if(newvalue){
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').show();
            			if(me.up('panel').getOutfieldMainForm().getForm().findField('transferCenter').getValue()){ //如果是外場，就不能是配載部門 和空運總調
            				checkbox.setValue(false);
                        	baseinfo.showErrorMes(baseinfo.orgAdministrativeInfo.i18n('foss.baseinfo.departmentHasFieldCanNoLongerCheckAirDispatch'));//该部门已经是外场！
                        	return;
            			}
            			//选择了可空运配载，必须选择可空运总调
            			me.getForm().findField('airDispatch').setValue('Y');
            			me.doLayout();
            		}else{
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').reset();
            			me.getForm().findField('airDispatch').hide();
            			me.doLayout();
            		}*/
            	}
            }
		},{
			boxLabel  : '打木架',//事业部
            name  : 'isPackingWood',
            colspan : 3,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){
            		/*//获取主面板
            		var orgMainInfoForm	=Ext.getCmp('Foss_baseinfo_orgAdministrativeInfo_OrgInfoPanel_Id').getOrgMainInfoForm();
            		if(newvalue){
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').show();
            			if(me.up('panel').getOutfieldMainForm().getForm().findField('transferCenter').getValue()){ //如果是外場，就不能是配載部門 和空運總調
            				checkbox.setValue(false);
                        	baseinfo.showErrorMes(baseinfo.orgAdministrativeInfo.i18n('foss.baseinfo.departmentHasFieldCanNoLongerCheckAirDispatch'));//该部门已经是外场！
                        	return;
            			}
            			//选择了可空运配载，必须选择可空运总调
            			me.getForm().findField('airDispatch').setValue('Y');
            			me.doLayout();
            		}else{
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').reset();
            			me.getForm().findField('airDispatch').hide();
            			me.doLayout();
            		}*/
            	}
            }
		},{
			boxLabel  : '中转',//事业部
            name  : 'isTransfer',
            colspan : 2,
			width:100,
            xtype:'checkbox',	
            inputValue: 'Y',
            listeners:{
            	change:function(checkbox,newvalue,oldvalue){
            		/*//获取主面板
            		var orgMainInfoForm	=Ext.getCmp('Foss_baseinfo_orgAdministrativeInfo_OrgInfoPanel_Id').getOrgMainInfoForm();
            		if(newvalue){
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').show();
            			if(me.up('panel').getOutfieldMainForm().getForm().findField('transferCenter').getValue()){ //如果是外場，就不能是配載部門 和空運總調
            				checkbox.setValue(false);
                        	baseinfo.showErrorMes(baseinfo.orgAdministrativeInfo.i18n('foss.baseinfo.departmentHasFieldCanNoLongerCheckAirDispatch'));//该部门已经是外场！
                        	return;
            			}
            			//选择了可空运配载，必须选择可空运总调
            			me.getForm().findField('airDispatch').setValue('Y');
            			me.doLayout();
            		}else{
            			orgMainInfoForm.setOrgSimpleNameField(orgMainInfoForm);
            			me.getForm().findField('airDispatch').reset();
            			me.getForm().findField('airDispatch').hide();
            			me.doLayout();
            		}*/
            	}
            }
		},{
			name: 'goodsArea',
			colspan : 4,
			width:300,
			labelWidth:100,
	        fieldLabel: '货区面积',//部门名称
	        xtype : 'textfield'
		},{
			name: 'platArea',
			colspan : 2,
			width:300,
			labelWidth:100,
	        fieldLabel: '货台面积',//部门名称
	        xtype : 'textfield'
		},{
			name: 'platformCount',
			colspan : 6,
			width:300,
			labelWidth:100,
	        fieldLabel: '月台总数',//部门名称
	        decimalPrecision:6,
			maxValue: 999,
			maxLength:3,
			minValue:0,
	        step:1,
	        xtype : 'numberfield'
		},{
			name: 'notes',
			colspan : 6,
			labelWidth:100,
			width:640,
	        fieldLabel: '备注',//部门名称
	        xtype : 'textarea'
		}];
		me.callParent([cfg]);
	}
});



/**
 * @description 行政组织主页
 */
Ext.onReady(function() {
	Ext.QuickTips.init();
	if (Ext.getCmp('T_baseinfo-queryOrgBiz_content')) {
		return;
	};
	//baseinfo.searchConfigParams();//获取配置参数值
	var orgTreeSearchPanel= Ext.create('Butterfly.baseinfo.orgAdministrative.DepartmentTreePanle',{width:250});//组织树查询panel
	var orgInfoPanel = Ext.create('Butterfly.baseinfo.orgAdministrative.OrgInfoPanel',{columnWidth:1});//组织详细信息PANEL
	Ext.create('Ext.panel.Panel', {
        renderTo : Ext.getBody(),
        layout:'column', 
		//组织树查询panel
		getOrgTreeSearchPanel : function() {
			return orgTreeSearchPanel;
		},
		//组织详细信息PANEL
		getOrgInfoPanel : function() {
			return orgInfoPanel;
		},
		items : [orgTreeSearchPanel,orgInfoPanel] 
	});
});
