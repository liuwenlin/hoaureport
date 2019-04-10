
/**主界面->大客户模型*/
Ext.define('Ty.Report.CustomerInfo.CustomerInfoModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// 大客户编号
		name : 'customerNum'
	},
	{// 增加时效(0或1)
		name : 'addEfficiency'
	},
	{// 大客户名字
		name : 'bigCustomerName'
	},
	{// 是否有效
		name : 'active'
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

/***主界面->大客户store*/
Ext.define('Ty.Report.CustomerInfo.CustomerInfoStore', {
	extend : 'Ext.data.Store',
	model : 'Ty.Report.CustomerInfo.CustomerInfoModel',
	pageSize : 100,// 分页条数
	proxy : {
		type : 'ajax',
		url : 'customerInfoManageAction!queryCustomerInfo.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'customerInfoVo.customerInfoList',
			totalProperty : 'totalCount'
		}
	},
	listeners : {
		//在加载数据之前操作
		beforeload : function(store, operation, eOpts) {
				var params = {
						'customerInfoVo.customerInfo.customerNum':Ext.String.trim(Ext.getCmp('customerNum').getValue()),
						'customerInfoVo.customerInfo.active':Ext.getCmp('active').getValue(),
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
	 
	 /**主界面->增加时效(0或1) 模型*/
	 Ext.define("addEfficiencyModel", {
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
	  * 主界面->增加时效(0或1)store
	  */
	 var addEfficiencyStore = Ext.create('Ext.data.Store', {
	 	model: "addEfficiencyModel",
	    data : [
	             {'name':'0','value':'0'},
	             {'name':'1','value':'1'}
	            ]
	 });
	 

	 /**主界面->大客户表单*/
	 var queryForm = Ext.create('Ext.form.Panel',{
			layout : {
				type : 'table',	//table布局
				columns : 4	//列数
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
				fieldLabel: '客户编号',
				id : 'customerNum',
				name:'customerNum'
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
			},
			{
				xtype:'button',
				text:'查询',
				width:60,
				border : true,
				handler:function(){
					customerInfoStore.reload();  //加载数据
					//customerInfoStore.loadPage(1);	//查询后默认返回第一页
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
	var customerInfoStore=Ext.create('Ty.Report.CustomerInfo.CustomerInfoStore',{
		id:'customerInfoStoreId'
	});

	/**
	 * 主界面->表格
	 */
	var  customerInfoGrid =Ext.create('Ext.grid.Panel', {
		store: customerInfoStore,
		border:false,
		columnLines:true,
		selModel:Ext.create('Ext.selection.CheckboxModel', {
			listeners : {
				//行选中事件
				selectionchange : function(sm, selections) {
					if(selections.length == 1){
						if(selections[0].get('active') == 'N'){//作废时 不能点击 作废 按钮
							Ext.getCmp('repealBtn').setDisabled(true);
							Ext.getCmp('resetBtn').hide();
						}else{
							Ext.getCmp('repealBtn').setDisabled(false);
							Ext.getCmp('resetBtn').show();
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
            dataIndex: 'bigCustomerName',
			text:'大客户名称',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'addEfficiency',
			text:'增加时效(0或1)',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'customerNum',
			text:'客户编号',
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
        }],
        listeners : {
			itemClick : function() {

			}
		},
		 dockedItems:[{//分页
	 			xtype:'pagingtoolbar',
	 			id: 'queryCustomerInfoGridPaginId',
	 			store:customerInfoStore,
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
				win.setTitle("数据新增");
				operateFlag = 1;
				Ext.getCmp('activeField').disable(); 
				Ext.getCmp('customerNumField').enable();  
				Ext.getCmp('bigCustomerNameField').enable();  
				Ext.getCmp('addEfficiencyField').enable();
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
				var records = customerInfoGrid.getSelectionModel().getSelection();//获取选中集合
				if(records.length == 0){
					showInfoMsg("请选择一条记录！");
					return false;
				};
				if(records.length != 1){
					showInfoMsg("只能选择一条记录！");
					return false;
				};
				var form = addForm.getForm();
				var CustomerInfoModel = new Ty.Report.CustomerInfo.CustomerInfoModel();
				form.loadRecord(CustomerInfoModel);
				if(records[0].get("active") == 'N'){
					Ext.getCmp('activeField').enable();  
					Ext.getCmp('customerNumField').disable();  
					Ext.getCmp('bigCustomerNameField').disable();  
					Ext.getCmp('addEfficiencyField').disable();  
				}
				else{
					Ext.getCmp('activeField').disable(); 
					Ext.getCmp('customerNumField').enable();  
					Ext.getCmp('bigCustomerNameField').enable();  
					Ext.getCmp('addEfficiencyField').enable();
				}
				form.findField("currentCustomerNumField").setValue(records[0].get("customerNum"));
				form.findField("customerNumField").setValue(records[0].get("customerNum"));
				form.findField("bigCustomerNameField").setValue(records[0].get("bigCustomerName"));
				form.findField("addEfficiencyField").setValue(records[0].get("addEfficiency"));
				form.findField("activeField").setValue(records[0].get("active"));
				form.updateRecord(CustomerInfoModel);
				win.setTitle("数据修改");
				win.show();
			}
		},'-',{//提示 '->' 让按钮居右
			id:'repealBtn',
			xtype:'button',
			text:'作废',
			//iconCls: 'edit',
			handler:function(){
				var records = customerInfoGrid.getSelectionModel().getSelection();//获取选中集合
				var str = "是否确认作废？";
				if(records.length == 0){
					showInfoMsg("请选择一条记录！");
					return false;
				};
				if(records.length > 1){
					str = "是否确认作废所有数据?";
				};
				
				confirm(str, 
						function(){
					var formUrl = 'customerInfoManageAction!repealCustomerInfo.action';
					var customerInfoArray = [];
					for(var i=0;i<records.length;i++){
						var curItem = records[i];
						var curItemData = curItem.data;
						curItemData.id = "";
						customerInfoArray.push(curItemData);
						}
					var param = {
							'customerInfoVo':{
								'customerInfoList': customerInfoArray
								    }
						        };
					Ext.Msg.wait('保存中，请稍后...', '提示');
					    var returnSuc = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryCustomerInfoGridPaginId').moveFirst();
							win.hide();
						};
						var returnFail = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryCustomerInfoGridPaginId').moveFirst();
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
			labelWidth:120,
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
			fieldLabel: '当前客户编号',
			width:250,
			height:15,
			hidden:true,
			maxLength:10,
			name:'currentCustomerNumField',
			id:'currentCustomerNumField',
		},{
			fieldLabel: '客户编号',
			width:250,
			height:15,
			maxLength:10,
			allowBlank:false,//判断不能为空
			name:'customerNumField',
			id:'customerNumField',
		},{
			fieldLabel: '大客户名称',
			width:250,
			height:15,
			maxLength:25,
			allowBlank:false,//判断不能为空
			name:'bigCustomerNameField',
			id:'bigCustomerNameField',
		},{
			fieldLabel: '增加时效(0或1)',
			width:250,
			height:15,
			id : 'addEfficiencyField',
			name:'addEfficiencyField',
			xtype : 'combo',
            store: addEfficiencyStore,
            displayField: "name",
            valueField: "value",
            queryMode: "local",
            autoSelect:true,
            forceSelection: true,
            value: '0', // 默认0
		},{
			fieldLabel: '是否有效',
			width:250,
			height:15,
			id : 'activeField',
			name:'activeField',
		 	//hidden: true,
			xtype : 'combo',
            store: activeStore,
            displayField: "name",
            valueField: "value",
            queryMode: "local",
            autoSelect:true,
            forceSelection: true,
            value: 'Y', // 默认选中
		}],
		buttons:[{
			xtype:'button',
			id:"resetBtn",
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
					saveCustomerInfo();
					customerInfoStore.reload();  //加载数据
				}else{//表单验证不通过
					formCheck();
				}
			}
		}]
	});
	function saveCustomerInfo(){
		Ext.Msg.wait('保存中，请稍后...', '提示');
		var form = addForm.getForm();
		var formUrl;
		var param;
		switch (operateFlag) {
		case 1:
			formUrl = 'customerInfoManageAction!addCustomerInfo.action';
			param = {
					'customerInfoVo':{
						'customerInfo':{
							'customerNum':Ext.String.trim(Ext.getCmp('customerNumField').getValue()),
							'addEfficiency':Ext.String.trim(Ext.getCmp('addEfficiencyField').getValue()),
							'bigCustomerName':Ext.String.trim(Ext.getCmp('bigCustomerNameField').getValue()),
							'active':Ext.String.trim(Ext.getCmp('activeField').getValue()),
						}
					}
			};
			break;
		case 2:
			formUrl = 'customerInfoManageAction!modifyCustomerInfo.action';
			param = {
					'customerInfoVo':{
						'currentCustomerNum':Ext.String.trim(Ext.getCmp('currentCustomerNumField').getValue()),
						'customerInfo':{
							'customerNum':Ext.String.trim(Ext.getCmp('customerNumField').getValue()),
							'addEfficiency':Ext.String.trim(Ext.getCmp('addEfficiencyField').getValue()),
							'bigCustomerName':Ext.String.trim(Ext.getCmp('bigCustomerNameField').getValue()),
							'active':Ext.String.trim(Ext.getCmp('activeField').getValue()),
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
				Ext.getCmp('queryCustomerInfoGridPaginId').moveFirst();
				win.hide();
			}else{
				
			}
		};
		var returnFail = function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('queryCustomerInfoGridPaginId').moveFirst();
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
		items: [queryForm,customerInfoGrid]
	});
	
});