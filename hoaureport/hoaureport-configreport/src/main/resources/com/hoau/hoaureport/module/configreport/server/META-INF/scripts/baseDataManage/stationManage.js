
/**主界面->场站模型*/
Ext.define('Ty.Report.Station.StationModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// ID
		name : 'stationsId'
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
	},
	{// 负责人
		name : 'leader',
	},
	{// 联系方式
		name : 'phone',
	},
	{// 场站地址
		name : 'address',
	},
	{// 仓库面积
		name : 'warehouseArea',
	},
	{// 发货库面积
		name : 'deliveryArea',
	},
	{// 到货库面积
		name : 'arrivalArea',
	}]
});

/***主界面->场站store*/
Ext.define('Ty.Report.Station.StationStore', {
	extend : 'Ext.data.Store',
	model : 'Ty.Report.Station.StationModel',
	pageSize : 100,// 分页条数
	proxy : {
		type : 'ajax',
		url : 'stationManageAction!queryStationInfo.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'stationVo.stationInfoList',
			totalProperty : 'totalCount'
		}
	},
	listeners : {
		//在加载数据之前操作
		beforeload : function(store, operation, eOpts) {
				var params = {
						'stationVo.stationInfoParam.stationsShortName':Ext.String.trim(Ext.getCmp('stationsShortName').getValue()),
						'stationVo.stationInfoParam.theBusinessDepartment':Ext.String.trim(Ext.getCmp('theBusinessDepartment').getValue()),
						'stationVo.stationInfoParam.theArea':Ext.String.trim(Ext.getCmp('theArea').getValue()),
						'stationVo.stationInfoParam.active':Ext.String.trim(Ext.getCmp('active').getValue()),
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
					stationStore.reload();
					//stationStore.loadPage(1);	//查询后默认返回第一页
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
	var stationStore=Ext.create('Ty.Report.Station.StationStore',{
		id:'stationStoreId'
	});

	/**
	 * 主界面->表格
	 */
	var  stationGrid =Ext.create('Ext.grid.Panel', {
		store: stationStore,//创建querySqlStore
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
//            flex:1//整个宽度减去定义过宽度剩余的
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
        },{
            dataIndex: 'leader',
			text:'负责人',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'phone',
			text:'联系方式',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'address',
			text:'场站地址',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'warehouseArea',
			text:'仓库面积',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'deliveryArea',
			text:'发货库面积',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'arrivalArea',
			text:'到货库面积',
            width:150,
            menuDisabled:true
        }],
        listeners : {
			itemClick : function() {

			}
		},
		 dockedItems:[{//分页
	 			xtype:'pagingtoolbar',
	 			id: 'queryStationGridPaginId',
	 			store:stationStore,
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
				var records = stationGrid.getSelectionModel().getSelection();//获取选中集合
				if(records.length == 0){
					showInfoMsg("请选择一条记录！");
					return false;
				};
				if(records.length != 1){
					showInfoMsg("只能选择一条记录！");
					return false;
				};
				var form = addForm.getForm();
				var StationModel = new Ty.Report.Station.StationModel();
				form.loadRecord(StationModel);
				form.findField("stationsId").enable();//修改的时候id为启用
//				form.findField("stationsId").setValue("");//清空
				form.findField("stationsId").setValue(records[0].get("stationsId"));
				form.findField("stationsShortName").setValue(records[0].get("stationsShortName"));
				form.findField("stationsName").setValue(records[0].get("stationsName"));
				form.findField("theArea").setValue(records[0].get("theArea"));
				form.findField("theBusinessDepartment").setValue(records[0].get("theBusinessDepartment"));
				
				form.findField("leader").setValue(records[0].get("leader"));
				form.findField("phone").setValue(records[0].get("phone"));
				form.findField("address").setValue(records[0].get("address"));
				form.findField("warehouseArea").setValue(records[0].get("warehouseArea"));
				form.findField("deliveryArea").setValue(records[0].get("deliveryArea"));
				form.findField("arrivalArea").setValue(records[0].get("arrivalArea"));
				
				form.updateRecord(StationModel);
				win.setTitle("数据修改");
				win.show();
			}
		},'-',{//提示 '->' 让按钮居右
			id:'repealBtn',
			xtype:'button',
			text:'作废',
			//iconCls: 'edit',
			handler:function(){
				var records = stationGrid.getSelectionModel().getSelection();//获取选中集合
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
					var formUrl = 'stationManageAction!repealStationInfo.action';
					var param = {
								'stationVo':{
									'stationInfoParam':{
										'stationsId' : records[0].get("stationsId"),
									}
								}
							};
					Ext.Msg.wait('保存中，请稍后...', '提示');
					    var returnSuc = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryStationGridPaginId').moveFirst();
							win.hide();
						};
						var returnFail = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryStationGridPaginId').moveFirst();
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
			columns : 2	//列数
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
			fieldLabel: 'stationsId',
			hidden:true,
			name:'stationsId',
			id:'stationsIdFiled',
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
			//allowBlank:false,//判断不能为空
			name:'theArea',
			id:'theAreaFiled',
		},{// 字段长度限制
			fieldLabel: '负责人',
			width:250,
			height:15,
			maxLength:10,
//			allowBlank:false,//判断不能为空
			name:'leader',
			id:'leaderFiled',
		},{
			fieldLabel: '联系方式',
			width:250,
			height:15,
//			allowBlank:false,//判断不能为空
			/*emptyText: '手机',
            regex: /^1\d{10}$/,
            regexText: '手机号必须以1开头11位纯数字',*/
			regex: /^(\d+|\/|-)+$/,
            regexText: '固话数字(包含/ -)',
            emptyText: '手机/固话',
            maxLength:20,
			name:'phone',
			id:'phoneFiled',
		},{
			fieldLabel: '场站地址',
			width:250,
			height:15,
			maxLength:100,
//			allowBlank:false,//判断不能为空
			name:'address',
			id:'addressFiled',
		},{
			fieldLabel: '仓库面积㎡',
			width:250,
			height:15,
//			maxLength:20,
			xtype :"numberfield",
            decimalPrecision:2,
            allowDecimals: true,
            maxValue: 999999999999999999.99,
            minValue: 1,
			allowBlank:false,//判断不能为空
			name:'warehouseArea',
			id:'warehouseAreaFiled',
		},{
			fieldLabel: '发货库面积㎡',
			width:250,
			height:15,
			xtype :"numberfield",
            decimalPrecision:2,
            allowDecimals: true,
            maxValue: 999999999999999999.99,
            minValue: 1,
			allowBlank:false,//判断不能为空
			name:'deliveryArea',
			id:'deliveryAreaFiled',
		},{
			fieldLabel: '到货库面积㎡',
			width:250,
			height:15,
			xtype :"numberfield",
            decimalPrecision:2,
            allowDecimals: true,
            maxValue: 999999999999999999.99,
            minValue: 1,
			allowBlank:false,//判断不能为空
			name:'arrivalArea',
			id:'arrivalAreaFiled',
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
					saveStationInfo();
				}else{//表单验证不通过
					formCheck();
				}
			}
		}]
	});
	function saveStationInfo(){
		//var msgWindow = null;
		var form = addForm.getForm();
		var formUrl;
		var param;
		switch (operateFlag) {
		case 1:
			formUrl = 'stationManageAction!addStationInfo.action';
			param = {
					'stationVo':{
						'stationInfoParam':{
							'stationsShortName':Ext.String.trim(Ext.getCmp('stationsShortNameFiled').getValue()),
							'stationsName':Ext.String.trim(Ext.getCmp('stationsNameFiled').getValue()),
							'theArea':Ext.String.trim(Ext.getCmp('theAreaFiled').getValue()),
							'theBusinessDepartment':Ext.String.trim(Ext.getCmp('theBusinessDepartmentFiled').getValue()),
							'leader':Ext.String.trim(Ext.getCmp('leaderFiled').getValue()),
							'phone':Ext.String.trim(Ext.getCmp('phoneFiled').getValue()),
							'address':Ext.String.trim(Ext.getCmp('addressFiled').getValue()),
							'warehouseArea':Ext.getCmp('warehouseAreaFiled').getValue(),
							'deliveryArea':Ext.getCmp('deliveryAreaFiled').getValue(),
							'arrivalArea':Ext.getCmp('arrivalAreaFiled').getValue()
						}
					}
			};
			break;
		case 2:
			formUrl = 'stationManageAction!modifyStationInfo.action';
			param = {
					'stationVo':{
						'stationInfoParam':{
							'stationsId' : Ext.String.trim(Ext.getCmp('stationsIdFiled').getValue()),
							'stationsShortName' :Ext.String.trim( Ext.getCmp('stationsShortNameFiled').getValue()),
							'stationsName' : Ext.String.trim(Ext.getCmp('stationsNameFiled').getValue()),
							'theArea' : Ext.String.trim(Ext.getCmp('theAreaFiled').getValue()),
							'theBusinessDepartment' : Ext.String.trim(Ext.getCmp('theBusinessDepartmentFiled').getValue()),
							'leader':Ext.String.trim(Ext.getCmp('leaderFiled').getValue()),
							'phone':Ext.String.trim(Ext.getCmp('phoneFiled').getValue()),
							'address':Ext.String.trim(Ext.getCmp('addressFiled').getValue()),
							'warehouseArea':Ext.getCmp('warehouseAreaFiled').getValue(),
							'deliveryArea':Ext.getCmp('deliveryAreaFiled').getValue(),
							'arrivalArea':Ext.getCmp('arrivalAreaFiled').getValue()
						}
					}
			};
			break;
		}
		var addModel=form.getRecord();
		var returnSuc = function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('queryStationGridPaginId').moveFirst();
			win.hide();
		};
		var returnFail = function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('queryStationGridPaginId').moveFirst();
			win.hide();
		}
		Ext.Msg.wait('保存中，请稍后...', '提示');
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
			width:550,
			height:250,
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