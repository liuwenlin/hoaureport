
/**主界面->车队模型*/
Ext.define('Ty.Report.Fleet.FleetModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// ID
		name : 'fleetId'
	},
	{// 车队简称
		name : 'fleetShortName'
	},
	{// 车队名称
		name : 'fleetName'
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

/***主界面->车队store*/
Ext.define('Ty.Report.Fleet.FleetStore', {
	extend : 'Ext.data.Store',
	model : 'Ty.Report.Fleet.FleetModel',
	pageSize : 100,// 分页条数
	proxy : {
		type : 'ajax',
		url : 'fleetManageAction!queryFleetInfo.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'fleetVo.fleetInfoList',
			totalProperty : 'totalCount'
		}
	},
	listeners : {
		//在加载数据之前操作
		beforeload : function(store, operation, eOpts) {
				var params = {
						'fleetVo.fleetInfoParam.fleetShortName':Ext.String.trim(Ext.getCmp('fleetShortName').getValue()),
						'fleetVo.fleetInfoParam.theBusinessDepartment':Ext.String.trim(Ext.getCmp('theBusinessDepartment').getValue()),
						'fleetVo.fleetInfoParam.theArea':Ext.String.trim(Ext.getCmp('theArea').getValue()),
						'fleetVo.fleetInfoParam.active':Ext.String.trim(Ext.getCmp('active').getValue()),
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

	 /**主界面->车队表单*/
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
				fieldLabel: '车队简称',
				id : 'fleetShortName',
				name:'fleetShortName'
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
					fleetStore.reload();
					//fleetStore.loadPage(1);	//查询后默认返回第一页
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
	var fleetStore=Ext.create('Ty.Report.Fleet.FleetStore',{
		id:'fleetStoreId'
	});

	/**
	 * 主界面->表格
	 */
	var  fleetGrid =Ext.create('Ext.grid.Panel', {
		store: fleetStore,//创建querySqlStore
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
            dataIndex: 'fleetShortName',
			text:'车队简称',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'fleetName',
			text:'车队名称',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'theArea',
			text:'所属大区',
			width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'theBusinessDepartment',
			text:'所属事业部',
			 width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'active',
			text:'是否有效',
			 width:150,
			 menuDisabled:true,
			 renderer: function(value) {
				    if(value=='Y'){
						return '是';
					}else if(value=='N'){
						return '否';
					}
	         }
            //flex:1//整个宽度减去定义过宽度剩余的
        },{
            dataIndex: 'effectedTime',
			text:'生效时间',
			 width:150,
			 menuDisabled:true,
			 renderer: function(value) {
	                return dateRender(value,'Y-m-d H:i:s');
	         }
        },{
            dataIndex: 'invalidTime',
			text:'失效时间',
			 width:170,
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
	 			id: 'queryFleetGridPaginId',
	 			store:fleetStore,
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
				var records = fleetGrid.getSelectionModel().getSelection();//获取选中集合
				if(records.length == 0){
					showInfoMsg("请选择一条记录！");
					return false;
				};
				if(records.length != 1){
					showInfoMsg("只能选择一条记录！");
					return false;
				};
				var form = addForm.getForm();
				var FleetModel = new Ty.Report.Fleet.FleetModel();
				form.loadRecord(FleetModel);
				form.findField("fleetId").enable();//修改的时候id为启用
//				form.findField("fleetId").setValue("");//清空
				form.findField("fleetId").setValue(records[0].get("fleetId"));
				form.findField("fleetShortName").setValue(records[0].get("fleetShortName"));
				form.findField("fleetName").setValue(records[0].get("fleetName"));
				form.findField("theArea").setValue(records[0].get("theArea"));
				form.findField("theBusinessDepartment").setValue(records[0].get("theBusinessDepartment"));
				form.updateRecord(FleetModel);
				win.setTitle("数据修改");
				win.show();
			}
		},'-',{//提示 '->' 让按钮居右
			id:'repealBtn',
			xtype:'button',
			text:'作废',
			//iconCls: 'edit',
			handler:function(){
				var records = fleetGrid.getSelectionModel().getSelection();//获取选中集合
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
					var formUrl = 'fleetManageAction!repealFleetInfo.action';
					var param = {
								'fleetVo':{
									'fleetInfoParam':{
										'fleetId' : records[0].get("fleetId"),
									}
								}
							};
					Ext.Msg.wait('保存中，请稍后...', '提示');
					    var returnSuc = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryFleetGridPaginId').moveFirst();
							win.hide();
						};
						var returnFail = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryFleetGridPaginId').moveFirst();
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
			//width : 200,
			//height:  100,
		},
		defaultType:'textfield',//默认类型
		frame:true,
		height:180,
		region:'center',
		buttonAlign:'center',
		items:[{
			fieldLabel: 'fleetId',
			hidden:true,
			name:'fleetId',
			id:'fleetIdFiled',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '车队简称',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			name:'fleetShortName',
			id:'fleetShortNameFiled',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '车队名称',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			name:'fleetName',
			id:'fleetNameFiled',
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
					saveFleetInfo();
				}else{//表单验证不通过
					formCheck();
				}
			}
		}]
	});
	function saveFleetInfo(){
		Ext.Msg.wait('保存中，请稍后...', '提示');
		//var msgWindow = null;
		var form = addForm.getForm();
		var formUrl;
		var param;
		switch (operateFlag) {
		case 1:
			formUrl = 'fleetManageAction!addFleetInfo.action';
			param = {
					'fleetVo':{
						'fleetInfoParam':{
							'fleetShortName':Ext.String.trim(Ext.getCmp('fleetShortNameFiled').getValue()),
							'fleetName':Ext.String.trim(Ext.getCmp('fleetNameFiled').getValue()),
							'theArea':Ext.String.trim(Ext.getCmp('theAreaFiled').getValue()),
							'theBusinessDepartment':Ext.String.trim(Ext.getCmp('theBusinessDepartmentFiled').getValue()),
						}
					}
			};
			break;
		case 2:
			formUrl = 'fleetManageAction!modifyFleetInfo.action';
			param = {
					'fleetVo':{
						'fleetInfoParam':{
							'fleetId' : Ext.String.trim(Ext.getCmp('fleetIdFiled').getValue()),
							'fleetShortName' : Ext.String.trim(Ext.getCmp('fleetShortNameFiled').getValue()),
							'fleetName' : Ext.String.trim(Ext.getCmp('fleetNameFiled').getValue()),
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
			Ext.getCmp('queryFleetGridPaginId').moveFirst();
			win.hide();
		};
		var returnFail = function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('queryFleetGridPaginId').moveFirst();
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
			height:230,
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
		items: [queryForm,fleetGrid]
	});
	
});