
/**主界面->场站模型*/
Ext.define('Ty.Report.SpecialStations.SpecialStationsModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// ID
		name : 'specialStationsId'
	},
	{// 场站简称
		name : 'stationsShortName'
	},
	{// 场站名称
		name : 'stationsName'
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

/***主界面->场站store*/
Ext.define('Ty.Report.SpecialStations.SpecialStationsStore', {
	extend : 'Ext.data.Store',
	model : 'Ty.Report.SpecialStations.SpecialStationsModel',
	pageSize : 100,// 分页条数
	proxy : {
		type : 'ajax',
		url : 'specialStationManageAction!querySpecialStationInfo.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'specialStationVo.specialStationInfoList',
			totalProperty : 'totalCount'
		}
	},
	//加载数据之前的操作
	listeners:{
		beforeload:function(store,operation,eOpts){
			var params={
					'specialStationVo.specialStationInfoParam.stationsShortName':Ext.String.trim(Ext.getCmp('stationsShortName').getValue()),
					'specialStationVo.specialStationInfoParam.theBusinessDepartment':Ext.String.trim(Ext.getCmp('theBusinessDepartment').getValue()),
			        'specialStationVo.specialStationInfoParam.theArea':Ext.String.trim(Ext.getCmp('theArea').getValue()),
			        'specialStationVo.specialStationInfoParam.active':Ext.String.trim(Ext.getCmp('active').getValue()),
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
				fieldLabel: '场站简称',
				id : 'stationsShortName',
				name:'stationsShortName'
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
					specialStationsStore.reload();
					
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
	 var specialStationsStore = Ext.create('Ty.Report.SpecialStations.SpecialStationsStore',{
		 id:'specialStationsStoreId'
	 })
	/**
	 * 主界面->表格
	 */
	var  stationGrid =Ext.create('Ext.grid.Panel', {
		border:false,
		store:specialStationsStore,
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
            dataIndex: 'stationsShortName',
			text:'场站简称',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'stationsName',
			text:'场站名称',
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
	 			id: 'queryStationGridPaginId',
	 			store:specialStationsStore,
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
	 				var records = stationGrid.getSelectionModel().getSelection();//获取选中集合
	 				if(records.length == 0){
	 					 showInfoMsg("请选择一条记录！");
	 					 return false;
	 				};
	 				if(records.length !=1){
	 					showInfoMsg("只能选中一条记录！");
	 				};
	 				var form = addForm.getForm();
	 				var specialStationsModel = new Ty.Report.SpecialStations.SpecialStationsModel();
	 				form.loadRecord(specialStationsModel);
	 				form.findField("specialStationsId").enable();//修改时id启用
	 				form.findField("specialStationsId").setValue("");//清空？
	 				form.findField("specialStationsId").setValue(records[0].get("specialStationsId"));
	 				form.findField("stationsShortName").setValue(records[0].get("stationsShortName"));
	 				form.findField("stationsName").setValue(records[0].get("stationsName"));
	 				form.findField("theArea").setValue(records[0].get("theArea"));
	 				form.findField("theBusinessDepartment").setValue(records[0].get("theBusinessDepartment"));
	 				form.updateRecord(specialStationsModel);
	 				win.setTitle("数据修改");
	 				win.show();
	 			}
	 		},'-',{//作废按钮
	 			id:'repealBtn',
	 			xtype:'button',
	 			text:'作废',
	 			handler:function(){
	 				var records = stationGrid.getSelectionModel().getSelection();//获取选中集合
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
	 					var formUrl = 'specialStationManageAction!repealSpecialStationInfo.action';//访问路径
	 					var param = {
	 							'specialStationVo':{
	 								'specialStationInfoParam':{
	 									'specialStationsId':records[0].get("specialStationsId"),//选中ID
	 								}
	 							}
	 					}
	 					Ext.Msg.wait('数据保存中，请稍后...','提示');
	 					var returnSuc = function(json){
	 						Ext.Msg.hide();
	 						showInfoMsg(json.message);
	 						Ext.getCmp('queryStationGridPaginId').moveFirst();
	 						win.hide();
	 					};
	 					var returnErro = function(json){
	 						Ext.Msg.hide();
	 						showInfoMsg(json.message);
	 						Ext.getCmp('queryStationGridPaginId').moveFirst();
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
			fieldLabel: 'specialStationsId',
			hidden:true,
			name:'specialStationsId',
			id:'specialStationsIdFiled',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '场站简称',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			name:'stationsShortName',
			id:'stationsShortNameFiled',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '场站名称',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			name:'stationsName',
			id:'stationsNameFiled',
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
				var form = addForm.getForm();
				if(form.isValid){//验证表单是否可以提交
					saveSpecialStationInfo();
				}else{//表单验证没有通过
					formCheck();
				}
			}
		}]
	});
	
	function saveSpecialStationInfo(){
		Ext.Msg.wait('数据保存中，请稍后...','提示');
		var form = addForm.getForm();
		var formUrl;
		var param;
		switch(operateFlag){
		case 1:
			formUrl = 'specialStationManageAction!addSpecialStationInfo.action';
			param = {
                   'specialStationVo':{
                	   'specialStationInfoParam':{
                		     'stationsShortName':Ext.String.trim(Ext.getCmp('stationsShortNameFiled').getValue()),
                		     'stationsName':Ext.String.trim(Ext.getCmp('stationsNameFiled').getValue()),
                		     'theArea':Ext.String.trim(Ext.getCmp('theAreaFiled').getValue()),
                		     'theBusinessDepartment':Ext.String.trim(Ext.getCmp('theBusinessDepartmentFiled').getValue()),
                	   }
                   }
			};
		break;
		case 2:
			formUrl = 'specialStationManageAction!modifySpecialSpecialStationInfo.action';
			param = {
                   'specialStationVo':{
                	   'specialStationInfoParam':{
                		     'specialStationsId':Ext.String.trim(Ext.getCmp('specialStationsIdFiled').getValue()),
                		     'stationsShortName':Ext.String.trim(Ext.getCmp('stationsShortNameFiled').getValue()),
                		     'stationsName':Ext.String.trim(Ext.getCmp('stationsNameFiled').getValue()),
                		     'theArea':Ext.String.trim(Ext.getCmp('theAreaFiled').getValue()),
                		     'theBusinessDepartment':Ext.String.trim(Ext.getCmp('theBusinessDepartmentFiled').getValue()),
                	   }
                   }
			};
		break;
		}
		var addModel = form.getRecord();
		var returnSuc =function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('queryStationGridPaginId').moveFirst();
			win.hide();
		}
		var returnFail = function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('queryStationGridPaginId').moveFirst();
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
		items: [queryForm,stationGrid]
	});
	
});