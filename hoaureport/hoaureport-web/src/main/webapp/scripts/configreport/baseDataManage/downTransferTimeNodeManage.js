
/**主界面->下转移时间节点模型*/
Ext.define('Ty.Report.DownTransferTimeNode.DownTransferTimeNodeModel', {
	extend : 'Ext.data.Model',
	fields : [ {
	// ID
		name : 'uId'
	},
	{// 大区
		name : 'theArea'
	},
	{// 所属路区
		name : 'theRoadArea'
	},
	{//上转/下转
		name : 'upOrDownTransfer'
	},
	{// 是否串线
		name : 'isLineCrossed'
	},
	{
	// 出发部门
		name : 'departureDepartment'
	},
	{// 次日（1）or当日（0）
		name : 'todayOrNextDay'
	},
	{// 到达部门
		name : 'arrivalDepartment'
	},
	{// 发车时间
		name : 'dispatchTime',
		type : 'date',
		dateFormat : 'time',
	},
	{// 在途时长（分钟）
		name : 'intransitMinutes'
	},
    {
	// 到达时间
		name : 'arrivalTime',
		type : 'date',
		dateFormat : 'time',
	},
	{// 串线线路
		name : 'crossedLine'
	},
	{// 公里数
		name : 'kilometers'
	},
	{// 班次
		name : 'classNum'
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
	{// 创建者编码
		name : 'createUserCode',
	},
	{// 修改时间
		name : 'modifyTime',
			type : 'date',
			dateFormat : 'time',
	},
	{// 修改者编码
		name : 'modifyUserCode',
	}]
});

/***主界面->下转移时间节点store*/
Ext.define('Ty.Report.DownTransferTimeNode.DownTransferTimeNodeStore', {
	extend : 'Ext.data.Store',
	model : 'Ty.Report.DownTransferTimeNode.DownTransferTimeNodeModel',
	pageSize : 100,// 分页条数
	proxy : {
		type : 'ajax',
		url : 'downTransferTimeNodeManageAction!queryDownTransferTimeNode.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'downTransferTimeNodeVo.downTransferTimeNodeList',
			totalProperty : 'totalCount'
		}
	},
	listeners : {
		//在加载数据之前操作 查询条件
		beforeload : function(store, operation, eOpts) {
				var params = {
						'downTransferTimeNodeVo.downTransferTimeNode.theArea':Ext.String.trim(Ext.getCmp('theArea').getValue()),
						'downTransferTimeNodeVo.downTransferTimeNode.theRoadArea':Ext.String.trim(Ext.getCmp('theRoadArea').getValue()),
						'downTransferTimeNodeVo.downTransferTimeNode.departureDepartment':Ext.String.trim(Ext.getCmp('departureDepartment').getValue()),
						'downTransferTimeNodeVo.downTransferTimeNode.arrivalDepartment':Ext.String.trim(Ext.getCmp('arrivalDepartment').getValue()),
						'downTransferTimeNodeVo.downTransferTimeNode.active':Ext.getCmp('active').getValue(),
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
	 /**主界面->上转/下转 模型*/
	 Ext.define("upOrDownTransferModel", {
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
	  * 主界面->上转/下转store
	  */
	 var upOrDownTransferStore = Ext.create('Ext.data.Store', {
	 	model: "upOrDownTransferModel",
	    data : [
	             {'name':'上转','value':'0'},
	             {'name':'下转','value':'1'}
	            ]
	 });
	 /**主界面->是否串线 模型*/
	 Ext.define("isLineCrossedModel", {
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
	  * 主界面->是否串线store
	  */
	 var isLineCrossedStore = Ext.create('Ext.data.Store', {
	 	model: "isLineCrossedModel",
	    data : [
	             {'name':'是','value':'Y'},
	             {'name':'否','value':'N'}
	            ]
	 });
	 
	 /**主界面->次日（1）OR当日（0） 模型*/
	 Ext.define("todayOrNextDayModel", {
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
	  * 主界面->次日（1）OR当日（0）store 
	  */
	 var todayOrNextDayStore = Ext.create('Ext.data.Store', {
	 	model: "todayOrNextDayModel",
	    data : [
	             {'name':'当日','value':'0'},
	             {'name':'次日','value':'1'}
	            ]
	 });
	 /**主界面->下转移时间节点表单*/
	 var queryForm = Ext.create('Ext.form.Panel',{
			layout : {
				type : 'table',	//table布局
				columns : 7,	//列数
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
				fieldLabel: '大区',
				id : 'theArea',
				name:'theArea'
			},{
				fieldLabel: '所属路区',
				id : 'theRoadArea',
				name:'theRoadArea'
			},{
				fieldLabel: '出发部门',
				id : 'departureDepartment',
				name:'departureDepartment'
			},{
				fieldLabel: '到达部门',
				id : 'arrivalDepartment',
				name:'arrivalDepartment'
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
					downTransferTimeNodeStore.reload();  //加载数据
					//downTransferTimeNodeStore.loadPage(1);	//查询后默认返回第一页
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
	var downTransferTimeNodeStore=Ext.create('Ty.Report.DownTransferTimeNode.DownTransferTimeNodeStore',{
		id:'downTransferTimeNodeStoreId'
	});

	/**
	 * 主界面->表格
	 */
	var  downTransferTimeNodeGrid =Ext.create('Ext.grid.Panel', {
		store: downTransferTimeNodeStore,
		border:false,
		columnLines:true,
		selModel:Ext.create('Ext.selection.CheckboxModel', {
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
            dataIndex: 'theArea',
			text:'大区',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'theRoadArea',
			text:'所属路区',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'upOrDownTransfer',
			text:'上转/下转',
			 width:150,
			 menuDisabled:true,
			 renderer: function(value) {
				    if(value=='0'){
						return '上转';
					}else if(value=='1'){
						return '下转';
					}
	         }
        },{
            dataIndex: 'isLineCrossed',
			text:'是否串线',
			 width:150,
			 menuDisabled:true,
			 renderer: function(value) {
				  if(value=='Y'){
						return '是';
					}else if(value=='N'){
						return '否';
					}
	         }
        },{
            dataIndex: 'departureDepartment',
			text:'出发部门',
			width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'todayOrNextDay',
			text:'次日(1)or当日(0)',
			 width:150,
			 menuDisabled:true,
			 renderer: function(value) {
				  if(value=='1'){
						return '次日';
					}else if(value=='0'){
						return '当日';
					}
	         }
        },{
            dataIndex: 'arrivalDepartment',
			text:'到达部门',
			 width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'dispatchTime',
			text:'发车时间',
			 width:150,
			 menuDisabled:true,
			 renderer: function(value) {
	                return dateRender(value,'H:i:s');
	         }
        },{
            dataIndex: 'intransitMinutes',
			text:'在途时长（分钟）',
			 width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'arrivalTime',
			text:'到达时间',
			 width:150,
			 menuDisabled:true,
			 renderer: function(value) {
	                return dateRender(value,'H:i:s');
	         }
        },{
            dataIndex: 'crossedLine',
			text:'串线线路',
			 width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'kilometers',
			text:'公里数',
			 width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'classNum',
			text:'班次',
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
			 width:150,
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
	 			id: 'queryDownTransferTimeNodeGridPaginId',
	 			store:downTransferTimeNodeStore,
	 			dock:'bottom',
	 			displayInfo : true,
	 			autoScroll : true
	 		}],	
        tbar:[{
			xtype:'button',
			text:'新增',
			//iconCls: 'add',
			handler:function(){
				addForm.getForm().reset();
				Ext.getCmp('upOrDownTransferFiled').disable();
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
				Ext.getCmp('upOrDownTransferFiled').disable();
				operateFlag = 2;
				var records = downTransferTimeNodeGrid.getSelectionModel().getSelection();//获取选中集合
				if(records.length == 0){
					showInfoMsg("请选择一条记录！");
					return false;
				};
				if(records.length != 1){
					showInfoMsg("只能选择一条记录！");
					return false;
				};
				var form = addForm.getForm();
				var DownTransferTimeNodeModel = new Ty.Report.DownTransferTimeNode.DownTransferTimeNodeModel();
				form.loadRecord(DownTransferTimeNodeModel);
				form.findField("uId").enable();//修改的时候id为启用
				form.findField("uId").setValue(records[0].get("uId"));
				form.findField("theArea").setValue(records[0].get("theArea"));
				form.findField("theRoadArea").setValue(records[0].get("theRoadArea"));
				form.findField("upOrDownTransfer").setValue(records[0].get("upOrDownTransfer"));
				form.findField("isLineCrossed").setValue(records[0].get("isLineCrossed"));
				form.findField("departureDepartment").setValue(records[0].get("departureDepartment"));
				form.findField("todayOrNextDay").setValue(records[0].get("todayOrNextDay"));
				form.findField("arrivalDepartment").setValue(records[0].get("arrivalDepartment"));
				form.findField("dispatchTime").setValue(records[0].get("dispatchTime"));
				form.findField("intransitMinutes").setValue(records[0].get("intransitMinutes"));
				form.findField("arrivalTime").setValue(records[0].get("arrivalTime"));
				form.findField("crossedLine").setValue(records[0].get("crossedLine"));
				form.findField("kilometers").setValue(records[0].get("kilometers"));
				form.findField("classNum").setValue(records[0].get("classNum"));
				form.updateRecord(DownTransferTimeNodeModel);
				win.setTitle("数据修改");
				win.show();
			}
		},'-',{//提示 '->' 让按钮居右
			id:'repealBtn',
			xtype:'button',
			text:'作废',
			//iconCls: 'edit',
			handler:function(){
				var records = downTransferTimeNodeGrid.getSelectionModel().getSelection();//获取选中集合
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
					var formUrl = 'downTransferTimeNodeManageAction!repealDownTransferTimeNode.action';
					var param = {
								'downTransferTimeNodeVo':{
									'downTransferTimeNode':{
										'uId' : records[0].get("uId"),
									}
								}
							};
					Ext.Msg.wait('保存中，请稍后...', '提示');
					    var returnSuc = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryDownTransferTimeNodeGridPaginId').moveFirst();
							win.hide();
						};
						var returnFail = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryDownTransferTimeNodeGridPaginId').moveFirst();
							win.hide();
						}
						requestUmpAjaxJsonParams(formUrl,param,returnSuc,returnFail);
				 });
			}
		},'-',{
			xtype:'button',
			text:'导入',
			handler:function(){
				uploadT.excelWindow(null,imple);
			}
		}]
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
		       url : 'downTransferTimeNodeManageAction!implExcel.action',  
		       method : 'post',  
		       params : {  
		    	   'downTransferTimeNodeVo.filePath' : outFileName  
		          },  
		          success : function(response, options) {  
		             //隐藏进度条   
		               Ext.Msg.hide();   
		              var responseArray = Ext.util.JSON.decode(response.responseText); 
		              var msg = "";
		              if(responseArray.success){
		            	//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			              msg="本次导入"+responseArray.downTransferTimeNodeVo.sumSize+"条数据，新增数据"+responseArray.downTransferTimeNodeVo.addSize+"条，覆盖原数据"+responseArray.downTransferTimeNodeVo.coverSize+"条。"	
							downTransferTimeNodeStore.reload();
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
			columns : 2 //列数
		},
		defaults: {//统一设置宽、居右
			margin:'5 5 5 5',//间距
			labelWidth:160,
			border:false,
			labelAlign: 'right',
			width : 200,
			height:  100,
		},
		defaultType:'textfield',//默认类型
		frame:true,
		height:180,
		region:'center',
		buttonAlign:'center',
		items:[{
			fieldLabel: 'uId',
			hidden:true,
			name:'uId',
			id:'uIdFiled',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '大区',
			width:250,
			height:15,
			maxLength:20,
			name:'theArea',
			id:'theAreaFiled',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '所属路区',
			width:250,
			height:15,
			maxLength:20,
			name:'theRoadArea',
			id:'theRoadAreaFiled',
		},{
			fieldLabel: '上转/下转',
			xtype : 'combo',
			width:250,
			height:15,
			name:'upOrDownTransfer',
			id:'upOrDownTransferFiled',
			store: upOrDownTransferStore,
            displayField: "name",
            valueField: "value",
            queryMode: "local",
            autoSelect:true,
            //forceSelection: true,
            value: '0', // 默认上转
		},{
			fieldLabel: '是否串线',
			xtype : 'combo',
			width:250,
			height:15,
			name:'isLineCrossed',
			id:'isLineCrossedFiled',
			store: isLineCrossedStore,
            displayField: "name",
            valueField: "value",
            queryMode: "local",
            autoSelect:true,
            forceSelection: true,
            value: 'Y', // 默认串线
		},{
			fieldLabel: '出发部门',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			name:'departureDepartment',
			id:'departureDepartmentFiled',
		},{
			fieldLabel: '次日（1）or当日（0）',
			xtype : 'combo',
			width:250,
			height:15,
			name:'todayOrNextDay',
			id:'todayOrNextDayFiled',
			store: todayOrNextDayStore,
            displayField: "name",
            valueField: "value",
            queryMode: "local",
            autoSelect:true,
            forceSelection: true,
            value: '0', // 默认当日
		},{
			fieldLabel: '到达部门',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			name:'arrivalDepartment',
			id:'arrivalDepartmentFiled',
		},{
			fieldLabel: '发车时间',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			xtype : 'timefield',
			format : 'H:i:s',
			name:'dispatchTime',
			id:'dispatchTimeFiled',
		},{
			fieldLabel: '在途时长（分钟）',
			width:250,
			height:15,
			maxLength:6,
			regex: /^\d+(\.\d{1})?$/,
            regexText: '请输入正确的数据类型',
			name:'intransitMinutes',
			id:'intransitMinutesFiled',
		},{
			fieldLabel: '到达时间',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			xtype : 'timefield',
			format : 'H:i:s',
			name:'arrivalTime',
			id:'arrivalTimeFiled',
		},{
			fieldLabel: '串线线路',
			width:250,
			height:15,
			maxLength:20,
			name:'crossedLine',
			id:'crossedLineFiled',
		},{
			fieldLabel: '公里数',
			width:250,
			height:15,
			maxLength:6,
			regex: /^\d+(\.\d{1})?$/,
            regexText: '请输入正确的数据类型',
			name:'kilometers',
			id:'kilometersFiled',
		},{
			fieldLabel: '班次',
			width:250,
			height:15,
			maxLength:20,
			name:'classNum',
			id:'classNumFiled',
		}
		
		],
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
					saveDownTransferTimeNode();
				}else{//表单验证不通过
					formCheck();
				}
			}
		}]
	});
	function saveDownTransferTimeNode(){
		Ext.Msg.wait('保存中，请稍后...', '提示');
		//var msgWindow = null;
		var form = addForm.getForm();
		var formUrl;
		var param;
		switch (operateFlag) {
		case 1:
			formUrl = 'downTransferTimeNodeManageAction!addDownTransferTimeNode.action';
			param = {
					'downTransferTimeNodeVo':{
						'downTransferTimeNode':{
							'theArea':Ext.String.trim(Ext.getCmp('theAreaFiled').getValue()),
							'theRoadArea':Ext.String.trim(Ext.getCmp('theRoadAreaFiled').getValue()),
							'upOrDownTransfer':Ext.String.trim(Ext.getCmp('upOrDownTransferFiled').getValue()),
							'isLineCrossed':Ext.String.trim(Ext.getCmp('isLineCrossedFiled').getValue()),
							'departureDepartment':Ext.String.trim(Ext.getCmp('departureDepartmentFiled').getValue()),
							'todayOrNextDay':Ext.String.trim(Ext.getCmp('todayOrNextDayFiled').getValue()),
							'arrivalDepartment':Ext.String.trim(Ext.getCmp('arrivalDepartmentFiled').getValue()),
							'dispatchTime':Ext.getCmp('dispatchTimeFiled').getValue(),
							'intransitMinutes':Ext.String.trim(Ext.getCmp('intransitMinutesFiled').getValue()),
							'arrivalTime':Ext.getCmp('arrivalTimeFiled').getValue(),
							'crossedLine':Ext.String.trim(Ext.getCmp('crossedLineFiled').getValue()),
							'kilometers':Ext.String.trim(Ext.getCmp('kilometersFiled').getValue()),
							'classNum':Ext.String.trim(Ext.getCmp('classNumFiled').getValue()),
						}
					}
			};
			break;
		case 2:
			formUrl = 'downTransferTimeNodeManageAction!modifyDownTransferTimeNode.action';
			param = {
					'downTransferTimeNodeVo':{
						'downTransferTimeNode':{
							'uId' : Ext.String.trim(Ext.getCmp('uIdFiled').getValue()),
							'theArea':Ext.String.trim(Ext.getCmp('theAreaFiled').getValue()),
							'theRoadArea':Ext.String.trim(Ext.getCmp('theRoadAreaFiled').getValue()),
							'upOrDownTransfer':Ext.String.trim(Ext.getCmp('upOrDownTransferFiled').getValue()),
							'isLineCrossed':Ext.String.trim(Ext.getCmp('isLineCrossedFiled').getValue()),
							'departureDepartment':Ext.String.trim(Ext.getCmp('departureDepartmentFiled').getValue()),
							'todayOrNextDay':Ext.String.trim(Ext.getCmp('todayOrNextDayFiled').getValue()),
							'arrivalDepartment':Ext.String.trim(Ext.getCmp('arrivalDepartmentFiled').getValue()),
							'dispatchTime':Ext.getCmp('dispatchTimeFiled').getValue(),
							'intransitMinutes':Ext.String.trim(Ext.getCmp('intransitMinutesFiled').getValue()),
							'arrivalTime':Ext.getCmp('arrivalTimeFiled').getValue(),
							'crossedLine':Ext.String.trim(Ext.getCmp('crossedLineFiled').getValue()),
							'kilometers':Ext.String.trim(Ext.getCmp('kilometersFiled').getValue()),
							'classNum':Ext.String.trim(Ext.getCmp('classNumFiled').getValue()),
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
				Ext.getCmp('queryDownTransferTimeNodeGridPaginId').moveFirst();
				win.hide();
			}else{
				
			}
			
		};
		var returnFail = function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('queryDownTransferTimeNodeGridPaginId').moveFirst();
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
			width:600,
			height:350,
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
		items: [queryForm,downTransferTimeNodeGrid]
	});
	
});