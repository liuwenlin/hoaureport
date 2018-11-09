
/**主界面->门店模型*/
Ext.define('Ty.Report.StoreTheInfo.StoreTheInfoModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// ID
		name : 'storeId'
	},
	{// 门店代码
		name : 'storeCode'
	},
	{// 门店名称
		name : 'storeName'
	},
	{//所属路区
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

/***主界面->门店store*/
Ext.define('Ty.Report.StoreTheInfo.StoreTheInfoStore', {
	extend : 'Ext.data.Store',
	model : 'Ty.Report.StoreTheInfo.StoreTheInfoModel',
	pageSize : 100,// 分页条数
	proxy : {
		type : 'ajax',
		url : 'storeTheInfoManageAction!queryStoreTheInfo.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'storeTheInfoVo.storeTheInfoList',
			totalProperty : 'totalCount'
		}
	},
	listeners : {
		//在加载数据之前操作
		beforeload : function(store, operation, eOpts) {
				var params = {
						'storeTheInfoVo.storeTheInfo.storeCode':Ext.String.trim(Ext.getCmp('storeCode').getValue()),
						'storeTheInfoVo.storeTheInfo.theRoadArea':Ext.String.trim(Ext.getCmp('theRoadArea').getValue()),
						'storeTheInfoVo.storeTheInfo.theBusinessDepartment':Ext.String.trim(Ext.getCmp('theBusinessDepartment').getValue()),
						'storeTheInfoVo.storeTheInfo.theArea':Ext.String.trim(Ext.getCmp('theArea').getValue()),
						'storeTheInfoVo.storeTheInfo.active':Ext.String.trim(Ext.getCmp('active').getValue()),
				};
				Ext.apply(store.proxy.extraParams, params);
				if(Ext.String.trim(Ext.getCmp('active').getValue()) == 'N'){
					Ext.getCmp('repealAllBtn').setDisabled(true);
				}else{
					Ext.getCmp('repealAllBtn').setDisabled(false);
				}
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

	 /**主界面->门店表单*/
	 var queryForm = Ext.create('Ext.form.Panel',{
			layout : {
				type : 'table',	//table布局
				columns : 7	//列数
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
				fieldLabel: '门店代码',
				id : 'storeCode',
				name:'storeCode'
			},{
				fieldLabel: '所属路区',
				id : 'theRoadArea',
				name:'theRoadArea'
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
					storeTheInfoStore.reload();
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
	var storeTheInfoStore=Ext.create('Ty.Report.StoreTheInfo.StoreTheInfoStore',{
		id:'storeTheInfoStoreId'
	});

	/**
	 * 主界面->表格
	 */
	var  storeTheInfoGrid =Ext.create('Ext.grid.Panel', {
		store: storeTheInfoStore,//创建querySqlStore
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
            dataIndex: 'storeCode',
			text:'门店代码',
            width:125,
            menuDisabled:true
        },{
            dataIndex: 'storeName',
			text:'门店名称',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'theRoadArea',
			text:'所属路区',
			width:150,
			 menuDisabled:true,
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
//            flex:1//整个宽度减去定义过宽度剩余的
        },{
            dataIndex: 'active',
			text:'是否有效',
			 width:75,
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
			 width:135,
			 menuDisabled:true,
			 renderer: function(value) {
	                return dateRender(value,'Y-m-d H:i:s');
	         }
        },{
            dataIndex: 'invalidTime',
			text:'失效时间',
			 width:135,
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
	 			id: 'queryStoreTheInfoGridPaginId',
	 			store:storeTheInfoStore,
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
				var records = storeTheInfoGrid.getSelectionModel().getSelection();//获取选中集合
				if(records.length == 0){
					showInfoMsg("请选择一条记录！");
					return false;
				};
				if(records.length != 1){
					showInfoMsg("只能选择一条记录！");
					return false;
				};
				var form = addForm.getForm();
				var StoreTheInfoModel = new Ty.Report.StoreTheInfo.StoreTheInfoModel();
				form.loadRecord(StoreTheInfoModel);
				form.findField("storeId").enable();//修改的时候id为启用
				form.findField("storeId").setValue(records[0].get("storeId"));
				form.findField("storeCode").setValue(records[0].get("storeCode"));
				form.findField("storeName").setValue(records[0].get("storeName"));
				form.findField("theRoadArea").setValue(records[0].get("theRoadArea"));
				form.findField("theArea").setValue(records[0].get("theArea"));
				form.findField("theBusinessDepartment").setValue(records[0].get("theBusinessDepartment"));
				form.updateRecord(StoreTheInfoModel);
				win.setTitle("数据修改");
				win.show();
			}
		},'-',{//提示 '->' 让按钮居右
			id:'repealBtn',
			xtype:'button',
			text:'作废',
			//iconCls: 'edit',
			handler:function(){
				var records = storeTheInfoGrid.getSelectionModel().getSelection();//获取选中集合
				var str = "作废后的数据将不再有效，且不能恢复生效，确认作废？";
				if(records.length == 0){
					showInfoMsg("请选择一条记录！");
					return false;
				};
				if(records.length > 1){
					str = "是否确认作废所有数据?";
				};
				confirm(str,
						function(){
					var formUrl = 'storeTheInfoManageAction!repealStoreTheInfo.action';
					var storeTheInfoArray = [];
					for(var i = 0; i < records.length; i++){
						var curItem = records[i];
						var curItemData = curItem.data;
						curItemData.id = "";
						storeTheInfoArray.push(curItemData);
					}
					var param = {
								'storeTheInfoVo':{
									'storeTheInfoList': storeTheInfoArray
								}
							};
					Ext.Msg.wait('保存中，请稍后...', '提示');
					    var returnSuc = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryStoreTheInfoGridPaginId').moveFirst();
							win.hide();
						};
						var returnFail = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryStoreTheInfoGridPaginId').moveFirst();
							win.hide();
						}
						requestUmpAjaxJsonParams(formUrl,param,returnSuc,returnFail);
				 });
			}
		},'-',{//提示 '->' 让按钮居右
			id:'repealAllBtn',
			xtype:'button',
			text:'全部作废',
			//iconCls: 'edit',
			handler:function(){
				confirm("是否作废所有门店信息数据？",
						function(){
					var formUrl = 'storeTheInfoManageAction!repealAll.action';
					var param = {};
					Ext.Msg.wait('作废中，请稍后...', '提示');
					    var returnSuc = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryStoreTheInfoGridPaginId').moveFirst();
							win.hide();
						};
						var returnFail = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryStoreTheInfoGridPaginId').moveFirst();
							win.hide();
						};
						requestUmpAjaxJsonParams(formUrl,param,returnSuc,returnFail);
				 });
			}
		},'-',{
				xtype:'button',
				text:'导入',
				handler:function(){
					uploadT.excelWindow(null,imple);
				}
			}],	
       
	});
	
	//文件上传
	var uploadT={
					oufileName:null,
					excelWindow:function(url,fn){
						if(Ext.isEmpty(fn)){
							Ext.toast('请传入回调函数','温馨提示','t');
							return;
						}
						//没有url则默认为上传文件
						var flag=false;
						if(Ext.isEmpty(url)){
							flag=true;
							url='fileUpLoadAction!upLoadFile.action';
						}
						var fp=new Ext.FormPanel({
							renderTo : Ext.getBody(),
							fileUpload : true,
							width : 523,
							frame : true,
							autoHeight : true,
							bodyStyle : 'padding: 10px 10px 0 10px;',
							labelWidth : 50,
							defaults : {
								anchor : '95%',
								allowBlank : false,
								msgTarget : 'side'
							},
							items : [ new Ext.form.FileUploadField({
								buttonText : '浏览...',
								name : 'serviceXls',
								regex: /^.*?\.(xlsx|xls|zip)$/,
								regexText:'只能上传 *.xlsx,*.xls,*.zip',
								emptyText : '请选择一个文件',
								width : 500,
								buttonCfg : {
									width : 40,
									iconCls : 'upload-icon'
								}
							}) ],
							buttons : [ {
								text : '上传',
								handler : function() {
									if (fp.getForm().isValid()) {
										fp.getForm().submit(
												{
													method : 'post',
													url : url,	//后台处理的action
													waitMsg : '正在上传，请稍等...',
													waitTitle : '提示',
													success : function(fp, action) {
														if(flag){
															fn(action.result.outFileName,true);
														}else{
															fn(true);
														}
														 xwindow.destroy();
													},
													failure : function(fp, action) {
														if(flag){
															fn('服务器异常',false);
														}else{
															fn(false);
														}
														fn(action.result.outFileName,true);
														xwindow.destroy();
													}
												});
									}
								}
							}]
						});
						var xwindow=new Ext.Window({
						renderTo : Ext.getBody(),
						closeAction : "hide",
						plain : true,
						width : 540,
						title : "上传数据",
						modal : true,
						items : [fp]
					})
						xwindow.show();
					}
	}	
	
	function imple(outFileName,flag){
		if(flag){
			showInfoMsg('上传完成');
			Ext.Msg.wait('数据导入中，请稍后...', '提示');
			Ext.Ajax.request( {  
		       url : 'storeTheInfoManageAction!implExcel.action',  
		       method : 'post',  
		       params : {  
		    	   'storeTheInfoVo.filePath' : outFileName  
		          },  
		          success : function(response, options) {  
		             //隐藏进度条   
		               Ext.Msg.hide();   
		              var responseArray = Ext.util.JSON.decode(response.responseText); 
		              var msg = "";
		              if(responseArray.success){
		            	//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			              msg="本次导入"+responseArray.storeTheInfoVo.sumSize+"条数据，新增数据"+responseArray.storeTheInfoVo.addSize+"条，覆盖原数据"+responseArray.storeTheInfoVo.coverSize+"条。"	
							storeTheInfoStore.reload();
		              }else{
		            	  msg = responseArray.message;
		              }
		              showInfoMsg(msg);
		             },
		        failure : function() { 
		            Ext.Msg.hide();  
		            Ext.MessageBox.show({title: '失败',msg: '导入失败',buttons: Ext.MessageBox.OK,icon: Ext.MessageBox.ERROR});  
		        }
		    });  
		}else{
			showInfoMsg('服务器异常');
		}
		
}
	
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
			fieldLabel: 'storeId',
			hidden:true,
			name:'storeId',
			id:'storeIdFiled',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '门店代码',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			name:'storeCode',
			id:'storeCodeFiled',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '门店名称',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			name:'storeName',
			id:'storeNameFiled',
		},{
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
					saveStoreTheInfoInfo();
					storeTheInfoStore.reload();
				}else{//表单验证不通过
					formCheck();
				}
			}
		}]
	});
	function saveStoreTheInfoInfo(){
		Ext.Msg.wait('保存中，请稍后...', '提示');
		//var msgWindow = null;
		var form = addForm.getForm();
		var formUrl;
		var param;
		switch (operateFlag) {
		case 1:
			formUrl = 'storeTheInfoManageAction!addStoreTheInfo.action';
			param = {
					'storeTheInfoVo':{
						'storeTheInfo':{
							'storeCode':Ext.String.trim(Ext.getCmp('storeCodeFiled').getValue()),
							'storeName':Ext.String.trim(Ext.getCmp('storeNameFiled').getValue()),
							'theRoadArea':Ext.String.trim(Ext.getCmp('theRoadAreaFiled').getValue()),
							'theBusinessDepartment':Ext.String.trim(Ext.getCmp('theBusinessDepartmentFiled').getValue()),
							'theArea':Ext.String.trim(Ext.getCmp('theAreaFiled').getValue()),
						}
					}
			};
			break;
		case 2:
			formUrl = 'storeTheInfoManageAction!modifyStoreTheInfo.action';
			param = {
					'storeTheInfoVo':{
						'storeTheInfo':{
							'storeId' : Ext.String.trim(Ext.getCmp('storeIdFiled').getValue()),
							'storeCode':Ext.String.trim(Ext.getCmp('storeCodeFiled').getValue()),
							'storeName':Ext.String.trim(Ext.getCmp('storeNameFiled').getValue()),
							'theRoadArea':Ext.String.trim(Ext.getCmp('theRoadAreaFiled').getValue()),
							'theBusinessDepartment':Ext.String.trim(Ext.getCmp('theBusinessDepartmentFiled').getValue()),
							'theArea':Ext.String.trim(Ext.getCmp('theAreaFiled').getValue()),
						}
					}
			};
			break;
		}
		var addModel=form.getRecord();
		var returnSuc = function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			if(json.success){
				Ext.getCmp('queryStoreTheInfoGridPaginId').moveFirst();
				win.hide();
			}else{
				
			}
		};
		var returnFail = function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('queryStoreTheInfoGridPaginId').moveFirst();
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
			height:240,
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
		items: [queryForm,storeTheInfoGrid]
	});
	
});