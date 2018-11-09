
/**主界面->平台模型*/
Ext.define('Ty.Report.Platform.PlatformModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// ID
		name : 'platformId'
	},
	{// 平台简称
		name : 'platformShortName'
	},
	{// 平台名称
		name : 'platformName'
	},
	{// 是否总部平台
		name : 'isHeadQuartersPlatform'
	},
	{// 平台名称
		name : 'thePlatform'
	},
	{// 平台名称
		name : 'theRoadArea'
	},
	{//所属大区
		name : 'theArea'
	},
	{// 所属事业部
		name : 'theBusinessDepartment'
	},
	{// 是否有效
		name : 'active'
	},
	{// 生效时间
		name : 'effectedTime',
			type : 'date',
			dateFormat : 'time',
	},
	{// 失效时间
		name : 'invalidTime',
			type : 'date',
			dateFormat : 'time',
	},
	{//创建时间
		name : 'createTime',
			type : 'date',
			dateFormat : 'time',
	},
	{// 创建者编号
		name : 'createUserCode',
	},
	{// 修改时间
		name : 'modifyTime',
			type : 'date',
			dateFormat : 'time',
	},
	{// 修改者编号
		name : 'modifyUserCode',
	}]
});

/***主界面->平台store*/
Ext.define('Ty.Report.Platform.PlatformStore', {
	extend : 'Ext.data.Store',
	model : 'Ty.Report.Platform.PlatformModel',
	pageSize : 100,// 分页条数
	proxy : {
		type : 'ajax',
		url : 'platformManageAction!queryPlatformInfo.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'platformVo.platformInfoList',
			totalProperty : 'totalCount'
		}
	},
	listeners : {
		//在加载数据之前操作
		beforeload : function(store, operation, eOpts) {
				var params = {
						'platformVo.platformInfoParam.platformShortName':Ext.String.trim(Ext.getCmp('platformShortName').getValue()),
						'platformVo.platformInfoParam.theBusinessDepartment':Ext.String.trim(Ext.getCmp('theBusinessDepartment').getValue()),
						'platformVo.platformInfoParam.theArea':Ext.String.trim(Ext.getCmp('theArea').getValue()),
						'platformVo.platformInfoParam.active':Ext.String.trim(Ext.getCmp('active').getValue()),
				};
				Ext.apply(store.proxy.extraParams, params);
		}
	}
});

Ext.onReady(function(){
	//初始化提示条
    Ext.QuickTips.init();
    var operateFlag = 0;
	var win;//全局通用window
	/**
	* 查询条件form
	*/
	 var isFirstQuery=0;//是否是第一次加载0为第一次加载，1为不是第一次加载
	 /**主界面->是否有效 模型*/
	 Ext.define("activeModel", {
         extend: "Ext.data.Model",
         fields : [{
 	 		name : 'name',
 	 		type : 'string'
 	 	},{
 	 		name : 'value',
 	 		type : 'string'
 	 	}]
     });
	
	 /**
	  * 主界面->是否有效store
	  */
	 var activeStore = Ext.create('Ext.data.Store', {
	 	model: "activeModel",
	    data : [
	             {'name':'是','value':'Y'},
	             {'name':'否','value':'N'}
	            ]
	 });
	 /**
	  * 主界面->是否总部平台store
	  */
	 var isPlatformStore = Ext.create('Ext.data.Store', {
	 	model: "activeModel",
	    data : [
	             {'name':'是','value':'Y'},
	             {'name':'否','value':'N'}
	            ]
	 });

	 /**主界面->平台表单*/
	 var queryForm = Ext.create('Ext.form.Panel',{
			layout : {
				type : 'table',	//table布局
				columns : 6	//列数
			},
			defaults: {//统一设置宽、居右
				margin:'5 5 0 5',//间距
				labelWidth:70,
				border:false,
				labelAlign: 'right',
				width : 180
			},
			defaultType:'textfield',//默认类型
			frame:true,
			height:35,
			region:'north',
			items:[{
				fieldLabel: '平台简称',
				id : 'platformShortName',
				name:'platformShortName'
			},{
				fieldLabel: '所属大区',
				id : 'theArea',
				name:'theArea'
			},{
				fieldLabel: '所属事业部',
				id : 'theBusinessDepartment',
				name:'theBusinessDepartment'
			},{
				fieldLabel: '是否有效',
				id : 'active',
				name:'active',
				xtype : 'combo',
                store: activeStore,
                displayField: "name",
                valueField: "value",
                queryMode: "local",
                autoSelect:true,
                forceSelection: true,
                value: 'Y', // 默认选中
			},{
				xtype:'button',
				text:'查询',
				width:60,
				border : true,
				handler:function(){
					platformStore.reload();
					//platformStore.loadPage(1);	//查询后默认返回第一页
				}
			},{
				xtype:'button',
				text:'重置',
				width:60,
				border : true,
				handler:function(){
					queryForm.getForm().reset();
				}
			}
			]
		});
//主界面->表格->store
	var platformStore=Ext.create('Ty.Report.Platform.PlatformStore',{
		id:'platformStoreId'
	});

	/**
	 * 主界面->表格
	 */
	var  platformGrid =Ext.create('Ext.grid.Panel', {
		store: platformStore,//创建querySqlStore
		border:false,
		columnLines:true,
		selModel:Ext.create('Ext.selection.CheckboxModel',{
			listeners : {
				//行选中事件
				selectionchange : function(sm, selections) {
					if(selections.length == 1){
						if(selections[0].get('active') == 'N'){//作废时 不能点击 修改、作废 按钮
							Ext.getCmp('repealBtn').setDisabled(true);
							Ext.getCmp('modifyBtn').setDisabled(true);
						}else{
							Ext.getCmp('repealBtn').setDisabled(false);
							Ext.getCmp('modifyBtn').setDisabled(false);
						}
					}else if(selections.length > 1){
						Ext.getCmp('repealBtn').setDisabled(true);
						Ext.getCmp('modifyBtn').setDisabled(true);
					}else{
						Ext.getCmp('repealBtn').setDisabled(false);
						Ext.getCmp('modifyBtn').setDisabled(false);
					}
				}
			}
		}),//checkbox
		region:'center',//border布局的中间
		columns: [//列
		{xtype: 'rownumberer',
			width:40},
        {
            dataIndex: 'platformShortName',
			text:'平台简称',
            width:120,
            menuDisabled:true
        },{
            dataIndex: 'platformName',
			text:'平台名称',
            width:120,
            menuDisabled:true
        },{
            dataIndex: 'isHeadQuartersPlatform',
			text:'是否总部平台',
			width:100,
			 menuDisabled:true,
			 renderer: function(value) {
				    if(value=='Y'){
						return '是';
					}else if(value=='N'){
						return '否';
					}
	         }
        },{
            dataIndex: 'theFleet',
			text:'所属车队',
            width:120,
            menuDisabled:true
        },{
            dataIndex: 'theRoadArea',
			text:'所属路区',
            width:120,
            menuDisabled:true
        },{
            dataIndex: 'theArea',
			text:'所属大区',
			width:120,
			 menuDisabled:true,
        },{
            dataIndex: 'theBusinessDepartment',
			text:'所属事业部',
			 width:120,
			 menuDisabled:true,
        },{
            dataIndex: 'active',
			text:'是否有效',
			 width:90,
			 menuDisabled:true,
			 renderer: function(value) {
				    if(value=='Y'){
						return '是';
					}else if(value=='N'){
						return '否';
					}
	         }
        },{
            dataIndex: 'effectedTime',
			text:'生效时间',
			 width:140,
			 menuDisabled:true,
			 renderer: function(value) {
	                return dateRender(value,'Y-m-d H:i:s');
	         }
        },{
            dataIndex: 'invalidTime',
			text:'失效时间',
			 width:140,
			 menuDisabled:true,
			 renderer: function(value) {
	                return dateRender(value,'Y-m-d H:i:s');
	         }
        }],
        listeners : {
			itemClick : function() {
				
			}
		},
		 dockedItems:[{//分页
	 			xtype:'pagingtoolbar',
	 			id: 'queryPlatformGridPaginId',
	 			store:platformStore,
	 			dock:'bottom',
	 			displayInfo : true,
	 			autoScroll : true
	 		}],	
        tbar:[{
        	id:'addBtn',
			xtype:'button',
			text:'新增',
			//iconCls: 'add',
			handler:function(){
				addForm.getForm().reset();
				win.setTitle("数据新增");
				operateFlag = 1;
				win.show();
			}
		},'-',{// '-' 表示分隔符
			id:'modifyBtn',
			xtype:'button',
			text:'修改',
			//iconCls: 'del',
			handler:function(){
				addForm.getForm().reset();
				operateFlag = 2;
				var records = platformGrid.getSelectionModel().getSelection();//获取选中集合
				if(records.length == 0){
					showInfoMsg("请选择一条记录！");
					return false;
				};
				if(records.length != 1){
					showInfoMsg("只能选择一条记录！");
					return false;
				};
				var form = addForm.getForm();
				var PlatformModel = new Ty.Report.Platform.PlatformModel();
				form.loadRecord(PlatformModel);
				form.findField("platformId").enable();//修改的时候id为启用
//				form.findField("platformId").setValue("");//清空
				form.findField("platformId").setValue(records[0].get("platformId"));
				form.findField("platformShortName").setValue(records[0].get("platformShortName"));
				form.findField("platformName").setValue(records[0].get("platformName"));
				form.findField("isHeadQuartersPlatform").setValue(records[0].get("isHeadQuartersPlatform"));
				form.findField("theFleet").setValue(records[0].get("theFleet"));
				form.findField("theRoadArea").setValue(records[0].get("theRoadArea"));
				form.findField("theArea").setValue(records[0].get("theArea"));
				form.findField("theBusinessDepartment").setValue(records[0].get("theBusinessDepartment"));
				form.updateRecord(PlatformModel);
				win.setTitle("数据修改");
				win.show();
			}
		},'-',{//提示 '->' 让按钮居右
			id:'repealBtn',
			xtype:'button',
			text:'作废',
			//iconCls: 'edit',
			handler:function(){
				var records = platformGrid.getSelectionModel().getSelection();//获取选中集合
				if(records.length == 0){
					showInfoMsg("请选择一条记录！");
					return false;
				};
				if(records.length != 1){
					showInfoMsg("只能选择一条记录！");
					return false;
				};
				confirm("作废后的数据将不再有效，且不能恢复生效，确认作废？",
						function(){
					var formUrl = 'platformManageAction!repealPlatformInfo.action';
					var param = {
								'platformVo':{
									'platformInfoParam':{
										'platformId' : records[0].get("platformId"),
									}
								}
							};
					Ext.Msg.wait('保存中，请稍后...', '提示');
					    var returnSuc = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryPlatformGridPaginId').moveFirst();
							win.hide();
						};
						var returnFail = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryPlatformGridPaginId').moveFirst();
							win.hide();
						}
						requestUmpAjaxJsonParams(formUrl,param,returnSuc,returnFail);
				 });
			}
		}]
	});
	/**
	 * 新增表单
	 */
	var addForm = Ext.create('Ext.form.Panel',{
		id:'addForm',
		layout : {
			type : 'table',	//table布局
			columns : 1	//列数
		},
		defaults: {//统一设置宽、居右
			margin:'5 5 5 5',//间距
			labelWidth:100,
			border:false,
			labelAlign: 'right',
		},
		defaultType:'textfield',//默认类型
		frame:true,
		height:180,
		region:'center',
		buttonAlign:'center',
		items:[{
			fieldLabel: 'platformId',
			hidden:true,
			name:'platformId',
			id:'platformIdFiled',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '平台简称',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			name:'platformShortName',
			id:'platformShortNameFiled',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '平台名称',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			name:'platformName',
			id:'platformNameFiled',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '是否总部平台',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			name:'isHeadQuartersPlatform',
			id:'isHeadQuartersPlatformFiled',
			xtype : 'combo',
            store: isPlatformStore,
            displayField: "name",
            valueField: "value",
            queryMode: "local",
            autoSelect:true,
            forceSelection: true,
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '所属车队',
			width:250,
			height:15,
			maxLength:20,
			//allowBlank:false,//判断不能为空
			name:'theFleet',
			id:'theFleetFiled',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '所属路区',
			width:250,
			height:15,
			maxLength:20,
			//allowBlank:false,//判断不能为空
			name:'theRoadArea',
			id:'theRoadAreaFiled',
		},{
			fieldLabel: '所属事业部',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			name:'theBusinessDepartment',
			id:'theBusinessDepartmentFiled',
		},{
			fieldLabel: '所属大区',
			width:250,
			height:15,
			maxLength:20,
			//allowBlank:false,//判断不能为空
			name:'theArea',
			id:'theAreaFiled',
		}],
		buttons:[{
			xtype:'button',
			text:'重置',
			width:80,
			handler:function(){
				addForm.getForm().reset();
			}
		},{
			xtype:'button',
			text:'提交',
			width:80,
			handler:function(){
				//win.close();
				var form = addForm.getForm();
				if(form.isValid()){//表单验证是否可提交
					savePlatformInfo();
				}else{//表单验证不通过
					formCheck();
				}
			}
		}]
	});
	function savePlatformInfo(){
		Ext.Msg.wait('保存中，请稍后...', '提示');
		//var msgWindow = null;
		var form = addForm.getForm();
		var formUrl;
		var param;
		switch (operateFlag) {
		case 1:
			formUrl = 'platformManageAction!addPlatformInfo.action';
			param = {
					'platformVo':{
						'platformInfoParam':{
							'platformShortName':Ext.String.trim(Ext.getCmp('platformShortNameFiled').getValue()),
							'platformName':Ext.String.trim(Ext.getCmp('platformNameFiled').getValue()),
							'isHeadQuartersPlatform':Ext.String.trim(Ext.getCmp('isHeadQuartersPlatformFiled').getValue()),
							'theFleet':Ext.String.trim(Ext.getCmp('theFleetFiled').getValue()),
							'theRoadArea':Ext.String.trim(Ext.getCmp('theRoadAreaFiled').getValue()),
							'theArea':Ext.String.trim(Ext.getCmp('theAreaFiled').getValue()),
							'theBusinessDepartment':Ext.String.trim(Ext.getCmp('theBusinessDepartmentFiled').getValue()),
						}
					}
			};
			break;
		case 2:
			formUrl = 'platformManageAction!modifyPlatformInfo.action';
			param = {
					'platformVo':{
						'platformInfoParam':{
							'platformId' : Ext.String.trim(Ext.getCmp('platformIdFiled').getValue()),
							'platformShortName' : Ext.String.trim(Ext.getCmp('platformShortNameFiled').getValue()),
							'platformName' : Ext.String.trim(Ext.getCmp('platformNameFiled').getValue()),
							'isHeadQuartersPlatform':Ext.String.trim(Ext.getCmp('isHeadQuartersPlatformFiled').getValue()),
							'theFleet':Ext.String.trim(Ext.getCmp('theFleetFiled').getValue()),
							'theRoadArea':Ext.String.trim(Ext.getCmp('theRoadAreaFiled').getValue()),
							'theArea' : Ext.String.trim(Ext.getCmp('theAreaFiled').getValue()),
							'theBusinessDepartment' : Ext.String.trim(Ext.getCmp('theBusinessDepartmentFiled').getValue()),
						}
					}
			};
			break;
		}
		var addModel=form.getRecord();
		var returnSuc = function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('queryPlatformGridPaginId').moveFirst();
			win.hide();
		};
		var returnFail = function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('queryPlatformGridPaginId').moveFirst();
			win.hide();
		}
		requestUmpAjaxJsonParams(formUrl,param,returnSuc,returnFail);
	};
	//验证表单是否提交成功
	function formCheck(){
		var msg="表单验证失败，请重新输入";
		showInfoMsg(msg,function(){
			return false;
		});
	};	
//添加pannel
	var addPanel =Ext.create('Ext.form.Panel',{
		layout: {
			type: 'border',//用border布局 ，把页面分为上下部分 form为上、grid为下
			padding: 5
		},
		buttonAlign:'center',
		items: [addForm]
	});

	if(!win){//需要定义全局变量,如果不存在就创建一个，保证只创建一次
		win=Ext.create('Ext.window.Window',{
			width:400,
			height:310,
			modal:true,//模式窗口（带蒙层，后面不可操作）
			layout:'fit',
			closeAction:'hide',//关闭方式 （默认为销毁）
			items:[addPanel],
			listeners:{
				close:function(){
					close();
				}
			}
		});
	}
	//整个页面布局
	var viewport = Ext.create('Ext.Viewport', {
		layout: {
			type: 'border',//用border布局 ，把页面分为上下部分 form为上、grid为下
			padding: 5
		},
		items: [queryForm,platformGrid]
	});
});