
/**主界面->场站模型*/
Ext.define('Ty.Report.StationAging.StationAgingModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// ID
		name : 'stationAgingId'
	},
	{// 作业单位代码
		name : 'operatingUnitCode'
	},
	{// 场站简称
		name : 'shortName'
	},
	{//所属大区
		name : 'theArea'
	},
	{// 所属事业部
		name : 'theBusinessDepartmemt'
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

/***主界面->场站store*/
Ext.define('Ty.Report.StationAging.StationAgingStore', {
	extend : 'Ext.data.Store',
	model : 'Ty.Report.StationAging.StationAgingModel',
	pageSize : 100,// 分页条数
	proxy : {
		type : 'ajax',
		url : 'stationAgingManageAction!queryStationAgingInfo.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'stationAgingVo.stationAgingList',
			totalProperty : 'totalCount'
		}
	},
	//加载数据之前的操作
	listeners:{
		beforeload:function(store,operation,eOpts){
			var params={
					'stationAgingVo.stationAgingInfoParam.operatingUnitCode':Ext.String.trim(Ext.getCmp('operatingUnitCode').getValue()),
//					'stationAgingVo.stationAgingInfoParam.shortName':Ext.String.trim(Ext.getCmp('shortName').getValue()),
					'stationAgingVo.stationAgingInfoParam.theArea':Ext.String.trim(Ext.getCmp('theArea').getValue()),
			        'stationAgingVo.stationAgingInfoParam.theBusinessDepartmemt':Ext.String.trim(Ext.getCmp('theBusinessDepartmemt').getValue()),
			        'stationAgingVo.stationAgingInfoParam.active':Ext.String.trim(Ext.getCmp('active').getValue()),
			}
			Ext.apply(store.proxy.extraParams,params);
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
	 /**
	  * 主界面是否有效 模型
	  * 
	  */
	 Ext.define('activeModel',{
		extend:'Ext.data.Model',
		fields:[{
			name : 'name',
			type : 'string',
		},{
			name : 'value',
			type : 'string',
		}]
	 });
	 /**
	  * 主界面是否有效store
	  */
	 var activeStore =Ext.create('Ext.data.Store',{
		 model:'activeModel',
		 data :[
		        {'name':'是','value':'Y'},
		        {'name':'否','value':'N'}
		       ]
	 });
	 /**主界面->场站表单*/
	 var queryForm = Ext.create('Ext.form.Panel',{
			layout : {
				type : 'table',	//table布局
				columns :6 //列数
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
				fieldLabel: '作业单位',
				id : 'operatingUnitCode',
				name:'operatingUnitCode'
			},{
				fieldLabel: '所属大区',
				id : 'theArea',
				name:'theArea'
			},{
				fieldLabel: '所属事业部',
				id : 'theBusinessDepartmemt',
				name:'theBusinessDepartmemt'
			},{
				fieldLabel: '是否有效',
				id : 'active',
				name:'active',
				xtype : 'combo',
				store:activeStore,
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
					//点击查询按钮时加载数据
					stationAgingStore.reload();
					
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
	 
	/**
	 * 主界面-->表格 store
	 */ 
	 var stationAgingStore = Ext.create('Ty.Report.StationAging.StationAgingStore',{
		 id:'stationAgingStoreId'
	 })
	/**
	 * 主界面->表格
	 */
	var  stationAgingGrid =Ext.create('Ext.grid.Panel', {
		border:false,
		store:stationAgingStore,
		columnLines:true,
		selModel:Ext.create('Ext.selection.CheckboxModel',{
			//行选中事件
			listeners:{
				selectionchange:function(sm,selections){
					if(selections.length == 1){
						if(selections[0].get('active') == 'N'){
							//作废时不能点击 修改作废按钮
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
		}),
		region:'center',//border布局的中间
		columns: [//列
		{xtype: 'rownumberer',
			width:40},
        {
            dataIndex: 'operatingUnitCode',
			text:'作业单位代码',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'shortName',
			text:'场站简称',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'theArea',
			text:'所属大区',
			width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'theBusinessDepartmemt',
			text:'所属事业部',
			 width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'active',
			text:'是否有效',
			 width:150,
			 menuDisabled:true,
			 renderer:function(value){
				 if(value == 'Y'){
					 return '是';
				 }else if(value == 'N'){
					 return '否';
				 }
			 }
        },{
            dataIndex: 'effectedTime',
			text:'生效时间',
			 width:150,
			 menuDisabled:true,
			 //设置时间的输出格式
			 renderer:function(value){
				 return dateRender(value,'Y-m-d H:i:s');
			 }
        },{
            dataIndex: 'invalidTime',
			text:'失效时间',
			 width:170,
			 menuDisabled:true,
			 renderer:function(value){
				 return dateRender(value,'Y-m-d H:i:s');
			 }
        }],
        listeners : {
			itemClick : function() {
			}
		},
		 dockedItems:[{//分页
	 			xtype:'pagingtoolbar',
	 			id: 'queryStationAgingGridPaginId',
	 			store:stationAgingStore,
	 			dock:'bottom',
	 			displayInfo : true,
	 			autoScroll : true
	 		}],	
	 		//新增按钮
	 		tbar:[{
	 			id:'addBtn',
	 			xtype:'button',
	 			text:'新增',
	 			handler:function(){
	 				addForm.getForm().reset();
	 				operateFlag = 1;
	 				win.setTitle("新增数据");
	 				win.show();
	 			}
	 		},'-',{//修改按钮
	 			id:'modifyBtn',
	 			xtype:'button',
	 			text:'修改',
	 			handler:function(){
	 				addForm.getForm().reset();
	 				operateFlag = 2;
	 				var records = stationAgingGrid.getSelectionModel().getSelection();//获取选中集合
	 				if(records.length == 0){
	 					 showInfoMsg("请选择一条记录！");
	 					 return false;
	 				};
	 				if(records.length !=1){
	 					showInfoMsg("只能选中一条记录！");
	 				};
	 				var form = addForm.getForm();
	 				var stationAgingModel = new Ty.Report.StationAging.StationAgingModel();
	 				form.loadRecord(stationAgingModel);
	 				form.findField("stationAgingId").enable();//修改时id启用
	 				form.findField("stationAgingId").setValue("");//清空？
	 				form.findField("stationAgingId").setValue(records[0].get("stationAgingId"));
	 				form.findField("operatingUnitCode").setValue(records[0].get("operatingUnitCode"));
	 				form.findField("shortName").setValue(records[0].get("shortName"));
	 				form.findField("theArea").setValue(records[0].get("theArea"));
	 				form.findField("theBusinessDepartmemt").setValue(records[0].get("theBusinessDepartmemt"));
	 				form.updateRecord(stationAgingModel);
	 				win.setTitle("数据修改");
	 				win.show();
	 			}
	 		},'-',{//作废按钮
	 			id:'repealBtn',
	 			xtype:'button',
	 			text:'作废',
	 			handler:function(){
	 				var records = stationAgingGrid.getSelectionModel().getSelection();//获取选中集合
	 				if(records.length == 0){
	 					showInfoMsg("请选择一条记录！");
	 					return false;
	 				};
	 				if(records.length !=1){
	 					showInfoMsg("只能选择一条记录！");
	 					return false;
	 				};
	 				confirm("作废后的数据将不再有效，且不能恢复生效，确认作废？",
	 						function(){
	 					var formUrl = 'stationAgingManageAction!repealStationAgingInfo.action';//访问路径
	 					var param = {
	 							'stationAgingVo':{
	 								'stationAgingInfoParam':{
	 									'stationAgingId':records[0].get("stationAgingId"),//选中ID
	 								}
	 							}
	 					}
	 					Ext.Msg.wait('数据保存中，请稍后...','提示');
	 					var returnSuc = function(json){
	 						Ext.Msg.hide();
	 						showInfoMsg(json.message);
	 						Ext.getCmp('queryStationAgingGridPaginId').moveFirst();
	 						win.hide();
	 					};
	 					var returnErro = function(json){
	 						Ext.Msg.hide();
	 						showInfoMsg(json.message);
	 						Ext.getCmp('queryStationAgingGridPaginId').moveFirst();
	 						wind.hide();
	 					}
	 					requestUmpAjaxJsonParams(formUrl,param,returnSuc,returnErro);
	 					
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
			fieldLabel: 'stationAgingId',
			hidden:true,
			name:'stationAgingId',
			id:'stationAgingIdFiled',
		},{
			fieldLabel: '作业单位代码',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			name:'operatingUnitCode',
			id:'operatingUnitCodeFiled',
		},{
			fieldLabel: '场站简称',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			name:'shortName',
			id:'shortNameFiled',
		},{
			fieldLabel: '所属大区',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			name:'theArea',
			id:'theAreaFiled',
		},{
			fieldLabel: '所属事业部',
			width:250,
			height:15,
			maxLength:20,
			name:'theBusinessDepartmemt',
			id:'theBusinessDepartmemtFiled',
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
				var form = addForm.getForm();
				if(form.isValid){//验证表单是否可以提交
					saveStationAgingInfo();
				}else{//表单验证没有通过
					formCheck();
				}
			}
		}]
	});
	
	function saveStationAgingInfo(){
		Ext.Msg.wait('数据保存中，请稍后...','提示');
		var form = addForm.getForm();
		var formUrl;
		var param;
		switch(operateFlag){
		case 1:
			formUrl = 'stationAgingManageAction!addStationAgingInfo.action';
			param = {
                   'stationAgingVo':{
                	   'stationAgingInfoParam':{
                		     //'operatingUnitCode':Ext.String.trim(Ext.getCmp('operatingUnitCodeFiled').getValue()),
                		   'operatingUnitCode':Ext.String.trim(Ext.getCmp('operatingUnitCodeFiled').getValue()),
                		     'shortName':Ext.String.trim(Ext.getCmp('shortNameFiled').getValue()),
                		     'theArea':Ext.String.trim(Ext.getCmp('theAreaFiled').getValue()),
                		     'theBusinessDepartmemt':Ext.String.trim(Ext.getCmp('theBusinessDepartmemtFiled').getValue()),
                	   }
                   }
			};
		break;
		case 2:
			formUrl = 'stationAgingManageAction!modifyStationAgingInfo.action';
			param = {
                   'stationAgingVo':{
                	   'stationAgingInfoParam':{
                		    'stationAgingId':Ext.String.trim(Ext.getCmp('stationAgingIdFiled').getValue()),
                		     'operatingUnitCode':Ext.String.trim(Ext.getCmp('operatingUnitCodeFiled').getValue()),
                		     'shortName':Ext.String.trim(Ext.getCmp('shortNameFiled').getValue()),
                		     'theArea':Ext.String.trim(Ext.getCmp('theAreaFiled').getValue()),
                		     'theBusinessDepartmemt':Ext.String.trim(Ext.getCmp('theBusinessDepartmemtFiled').getValue()),
                	   }
                   }
			};
		break;
		}
		var addModel = form.getRecord();
		var returnSuc =function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('queryStationAgingGridPaginId').moveFirst();
			win.hide();
		}
		var returnFail = function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('queryStationAgingGridPaginId').moveFirst();
			win.hide();
		}
		requestUmpAjaxJsonParams(formUrl,param,returnSuc,returnFail);
	};
	function formCheck(){
		var msg = "表单验证失败，请重新输入";
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
			height:220,
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
		items: [queryForm,stationAgingGrid]
	});
});