/*** 主界面-场站模型 */
Ext.define('Ty.StationTime.StationTimeModel',{
	extend:'Ext.data.Model',
	fields:[{
		name:'stationTimeId',
	},{
		name:'shortName',
	},{
		name:'stationName',
	},{
		name:'theArea'
	},{
		name:'theBusinessDepartment'
	},{
		name:'active'
	},{
		name:'effectedTime',
			type:'date',
			dateFormat:'time',
	},{
		name:'invalidTime',
			type:'date',
			dateFormat:'time',
	},{
		name:'createTime',
			name:'invalidTime',
			type:'date',
			dateFormat:'time',
	},{
		name:'createUserCode'
	},{
		name:'modifyTime',
			name:'invalidTime',
			type:'date',
			dateFormat:'time',
	}]
});

/*** 主界面 场站（时效）store */
Ext.define('Ty.StationTime.StationTimeStore',{
	extend:'Ext.data.Store',
	model:'Ty.StationTime.StationTimeModel',
	pageSize:100,//设置每页条数
	proxy:{
		type:'ajax',
		url:'stationTimeManageAction!queryStationTimeInfo.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			rootProperty:'stationTimeVo.stationTimeInfoList',
			totalProperty:'totalCount'
		}
	},
	listeners:{
		beforeload:function(store,operation,eOpts){
			var params = {
					'stationTimeVo.stationTimeParam.shortName':Ext.String.trim(Ext.getCmp('shortName').getValue()),
//					'stationTimeVo.stationTimeParam.stationName':Ext.String.trim(Ext.getCmp('stationName').getValue()),
					'stationTimeVo.stationTimeParam.theArea':Ext.String.trim(Ext.getCmp('theArea').getValue()),
					'stationTimeVo.stationTimeParam.theBusinessDepartment':Ext.String.trim(Ext.getCmp('theBusinessDepartment').getValue()),
					'stationTimeVo.stationTimeParam.active':Ext.String.trim(Ext.getCmp('active').getValue()),
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
		 field:[{
			 name:'name',
			 type:'string',
		 },{
			 name:'value',
			 name:'string',
		 }]
	 });
/*** 主界面是否有效Store*/
	 var activeStore = Ext.create('Ext.data.Store',{
		 model:'activeModel',
		 data:[
		       {'name':'是','value':'Y' },
		       {'name':'否','value':'N'}
		       ]
	 });
/*** 主界面场站表单*/
	 var queryForm = Ext.create('Ext.form.Panel',{
		 layout:{
			 type:'table',//table布局
			 columns:6  //7列
			 },
			 defaults:{
				 margin: '5 5 0 5',
				 labelWidth:70,
				 border:false,
				 labelAlign:'right',
				 width: 180
			 },
			 defaultType:'textfield',
		     frame:true,
			 height:35,
			 region:'north',
			 items:[{
				 fieldLabel:'场站简称',
				 id:'shortName',
				 name:'shortName'
			 },{
				 fieldLabel:'所属大区',
				 id:'theArea',
				 name:'theArea'
			 },{
				 fieldLabel:'所属事业部',
				 id:'theBusinessDepartment',
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
				 border:true,
				 handler:function(){
					 stationTimeStore.reload();
				 }
				 
			 },{
				 xtype:'button',
				 text:'重置',
				 width:60,
				 border:true,
				 handler:function(){
					 //重置时清空表单数据
					 queryForm.getForm().reset();
				 }
			 }]
	 });
	 
	 //主界面-->表格store
	 var stationTimeStore = Ext.create('Ty.StationTime.StationTimeStore',{
		 id:'stationTimeStoreId',
	 });
	 
	 /**
		 * 主界面->表格
		 */
		var  stationTimeGrid =Ext.create('Ext.grid.Panel', {
			border:false,
			store:stationTimeStore,
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
	            dataIndex: 'shortName',
				text:'场站简称',
	            width:150,
	            menuDisabled:true
	        }, {
	            dataIndex: 'stationName',
				text:'场站名称',
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
		 			id: 'queryStationTimeGridPaginId',
		 			store:stationTimeStore,
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
		 				var records = stationTimeGrid.getSelectionModel().getSelection();
		 				if(records.length == 0){
		 					showInfoMsg("请选择一条记录！");
		 					return false;
		 				};
		 				if(records.length != 1){
		 					showInfoMsg("只能选择一条记录！");
		 					return false;
		 				};
		 				var form = addForm.getForm();
		 				var stationTimeModel = new Ty.StationTime.StationTimeModel();
		 				form.loadRecord(stationTimeModel);
		 				form.findField('stationTimeId').enable();
		 				form.findField('stationTimeId').setValue("");
		 				form.findField('stationTimeId').setValue(records[0].get('stationTimeId'));
		 				form.findField('shortName').setValue(records[0].get('shortName'));
		 				form.findField('stationName').setValue(records[0].get('stationName'));
		 				form.findField('theArea').setValue(records[0].get('theArea'));
		 				form.findField('theBusinessDepartment').setValue(records[0].get('theBusinessDepartment'));
		 				form.updateRecord(stationTimeModel);
		 				win.setTitle("数据修改");
		 				win.show();
		 			}
		 		},'-',{//作废按钮
		 			id:'repealBtn',
		 			xtype:'button',
		 			text:'作废',
		 			handler:function(){
		 				var records = stationTimeGrid.getSelectionModel().getSelection();
		 				if(records.length == 0){
		 					showInfoMsg("请选择一条记录!");
		 					return false;
		 				};
		 				if(records.length != 1){
		 					showInfoMsg("只能选择一条记录!");
		 					return false;
		 				};
		 				confirm("作废后数据将不再有效，切数据不能回复生效，确认作废？",
		 						function(){
		 					var formUrl = 'stationTimeManageAction!repealStationTimeInfo.action';
		 					var param = {
		 							'stationTimeVo':{
		 								'stationTimeParam':{
		 									'stationTimeId':records[0].get("stationTimeId"),
		 								}
		 							}
		 					}
		 					Ext.Msg.wait('数据保存中，请稍后...','提示');
		 					var returnSuc = function(json){
		 						Ext.Msg.hide();
		 						showInfoMsg(json.message);
		 						Ext.getCmp('queryStationTimeGridPaginId').moveFirst();
		 						win.hide();
		 					};
		 					var returnFail = function(json){
		 						Ext.Msg.hide();
		 						showInfoMsg(json.message);
		 						Ext.getCmp('queryStationTimeGridPaginId').moveFirst();
		 						win.hide();
		 					};
		 					requestUmpAjaxJsonParams(formUrl,param,returnSuc,returnFail);
		 				});
		 			}
		 		}]
		});
	 
/*** 新增表单*/
	var addForm = Ext.create('Ext.form.Panel',{
		id:'addForm',
		layout:{
			type:'table',
			columns:1
		},
		defaults:{
			margin:'5 5 5 5',
			labelWidth:100,
			border:false,
			labelAlign:'right',
		},
		defaultType:'textfield',
		frame:true,
		height:180,
		region:'center',
		buttonAlign:'center',
		items:[{
			fieldLabel:'stationTimeId',
			hidden:true,
			id:'stationTimeIdField',
			name:'stationTimeId'
		},{
			fieldLabel:'场站简称',
			width:250,      
			height:15,      
			maxLength:20,   
			allowBlank:false,
			name:'shortName',
			id:'shortNameField',
		},{
			fieldLabel:'场站名称',
			width:250,      
			height:15,      
			maxLength:20,   
			allowBlank:false,
			name:'stationName',
			id:'stationNameField',
		},{
			fieldLabel:'所属大区',
			width:250,      
			height:15,      
			maxLength:20,   
			allowBlank:false,
			name:'theArea',
			id:'theAreaField',
		},{
			fieldLabel:'所属事业部',
			width:250,      
			height:15,      
			maxLength:20,   
			allowBlank:false,
			name:'theBusinessDepartment',
			id:'theBusinessDepartmentField',
		}],
		buttons:[{
			xtype:'button',
			text:'重置',
			width:80,
			handler:function(){
				addForm.getForm().resert();
			}
		},{
			xtype:'button',
			text:'提交',
			width:80,
			handler:function(){
				 var form = addForm.getForm();
				 if(form.isValid){
					 saveStationTimeInfo();
				 }else{
					 formCkeck();
				 }
			}
		}]
	});

	function saveStationTimeInfo(){
		Ext.Msg.wait('数据保存中，请稍后...','提示');
		var form = addForm.getForm();
		var formUrl;
		var param;
		switch(operateFlag){
		case 1:
			formUrl = 'stationTimeManageAction!addStationTimeInfo.action';
			param = {
                   'stationTimeVo':{
                	   'stationTimeParam':{
                		     'shortName':Ext.String.trim(Ext.getCmp('shortNameField').getValue()),
							 'stationName':Ext.String.trim(Ext.getCmp('stationNameField').getValue()),
                		     'theArea':Ext.String.trim(Ext.getCmp('theAreaField').getValue()),
                		     'theBusinessDepartment':Ext.String.trim(Ext.getCmp('theBusinessDepartmentField').getValue()),
                	   }                                                                      
                   }
			};
		break;
		case 2:
			formUrl = 'stationTimeManageAction!modifyStationTimeInfo.action';
			param = {
					'stationTimeVo':{
						'stationTimeParam':{
							'stationTimeId':Ext.String.trim(Ext.getCmp('stationTimeIdField').getValue()),
							 'shortName':Ext.String.trim(Ext.getCmp('shortNameField').getValue()),
							 'stationName':Ext.String.trim(Ext.getCmp('stationNameField').getValue()),
                		     'theArea':Ext.String.trim(Ext.getCmp('theAreaField').getValue()),
                		     'theBusinessDepartment':Ext.String.trim(Ext.getCmp('theBusinessDepartmentField').getValue()),
						}
					}
			};
		break;
		}
		var addModel = form.getRecord();
		var returnSuc =function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('queryStationTimeGridPaginId').moveFirst();
			win.hide();
		}
		var returnFail = function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('queryStationTimeGridPaginId').moveFirst();
			win.hide();
		}
		requestUmpAjaxJsonParams(formUrl,param,returnSuc,returnFail);
	};
	function formCheck(){
		var msg ="表单验证失败，请重新输入";
		showInfoMsg(msg,function(){
			return false;
		});
	};
/*** 添加Panel*/
	var addPanel = Ext.create('Ext.form.Panel',{
		layout:{
			type:'border',
			padding:5
		},
		buttonAlign:'center',
		items:[addForm]
	});
	
	if(!win){
		win = Ext.create('Ext.window.Window',{
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
		items: [queryForm,stationTimeGrid]
    });
});

















