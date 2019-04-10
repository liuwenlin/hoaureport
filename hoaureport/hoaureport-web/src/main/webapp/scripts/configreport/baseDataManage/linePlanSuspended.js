
/**主界面->平台辖区模型*/
Ext.define('Ty.Report.LinePlanSuspended.LinePlanSuspendedModel',{
	extend:'Ext.data.Model',
	fields:[{//序号
		name:'linePlanId'
	},{//开始运作
		name:'startWork'
	},{//到达运作
		name:'arriveWork'
	},{//发车路线（原）
		name:'startGridLines'
	},{//发车线路
		name:'gridLines'
	},{//所属线路
		name:'belongLines'
	},{//单/对发
		name:'departureType'
	},{//线路货量
		name:'lineQuantity'
	},{//发车频率
		name:'departureFrequency'
	},{//周一
		name:'monday'
	},{//周二
		name:'tuesday'
	},{//周三
		name:'wednesday'
	},{//周四
		name:'thursday'
	},{//周五
		name:'friday'
	},{//周六
		name:'saturday'
	},{//周日
		name:'sunday'
	},{//线路公里数
		name:'lineKil'
	},{//线路类型
		name:'lineType'
	},{//班次
		name:'shifts'
	},{//总班次
		name:'totalShifts'
	},{//发车时间
		name:'departureTime',
	    type : 'date',
	    dateFormat : 'time'
	},{//在途时间1（h）
		name:'travelTimeOne'
	},{//到达时间1
		name:'arrivalTimeOne',
		type : 'date',
		dateFormat : 'time'
	},{//发车时间2
		name:'departureTimeTwo',
		type : 'date',
	    dateFormat : 'time'
	},{//在途时间2（h）
		name:'travelTimeTwo'
	},{//到达时间2
		name:'arrivalTimeTwo',
		type : 'date',
		dateFormat : 'time'
	},{//发车时间3
		name:'departureTimeThr',
		type : 'date',
		dateFormat : 'time'
	},{//在途时间3(h)
		name:'travelTimeThr'
	},{//到达时间
		name:'arrivalTime',
		type : 'date',
		dateFormat : 'time'
	},{//车型
		name:'carType'
	},{//月承诺趟数
		name:'trainNumber'
	},{//实际车辆数
		name:'actualNumCars'
	},{//需求车辆数
		name:'demandForCars'
	},{//甩挂车辆需求
		name:'hangCarsDemand'
	},{//合同结束日期
		name:'contractDate'
	},{//停发走货规定
		name:'stopGoGoods'
	},{//开通时间
		name:'oppeningTime',
		type : 'date',
		dateFormat : 'time'
	},{//备注
		name:'remarks'
	},{//生效时间
		name:'effectedTime',
		type : 'date',
		dateFormat : 'time'
	},{//失效时间
		name:'invalidTime',
		type : 'date',
		dateFormat : 'time'
	},{//是否有效
		name:'active'
	},{//创建时间
		name:'createTime',
		type : 'date',
		dateFormat : 'time'
	},{//创建人
		name:'createUserCode'
	},{//修改时间
		name:'modifyTime',
		type : 'date',
	    dateFormat : 'time'
	},{//修改人
		name:'modifyUserCode'
	}]
});

/***主界面->平台辖区store*/
Ext.define('Ty.Report.LinePlanSuspended.LinePlanSuspendedStore', {
	extend : 'Ext.data.Store',
	model : 'Ty.Report.LinePlanSuspended.LinePlanSuspendedModel',
	pageSize : 100,// 分页条数
	proxy : {
		type : 'ajax',
		url : 'linePlanSuspendedAction!queryLinePlanSuspendedInfo.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'linePlanVo.linePlanList',
			totalProperty : 'totalCount'
		} 
	},
	listeners : {
		//在加载数据之前操作
		beforeload : function(store, operation, eOpts) {
				var params = {
						'linePlanVo.linePlanparam.startWork':Ext.String.trim(Ext.getCmp('startWorkFild').getValue()),
						'linePlanVo.linePlanparam.arriveWork':Ext.String.trim(Ext.getCmp('arriveWorkFild').getValue()),
						'linePlanVo.linePlanparam.active':Ext.String.trim(Ext.getCmp('active').getValue())
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
	 //是否有效
	 var isActive = Ext.create('Ext.data.Store', {
		 	model: "activeModel",
		    data : [
		             {'name':'是','value':'Y'},
		             {'name':'否','value':'N'}
		            ]
		 });
	 /**
	  * 主界面 单/对发
	  */
	 Ext.define("singAndDouble",{
	    extend: "Ext.data.Model",
	    fields : [{
 	 		name : 'name',
 	 		type : 'string'
 	 	},{
 	 		name : 'value',
 	 		type : 'string'
 	 	}]
	 });
	 //单/对发 模型
	 var singAndDouble = Ext.create('Ext.data.Store', {
		 	model: "singAndDouble",
		    data : [
		             {'name':'单发','value':'1'},
		             {'name':'对发','value':'0'}
		            ]
		 });
	 /**
	  * 主界面->长线/短线模型
	  */
	 Ext.define("longAndShortModel",{
		    extend: "Ext.data.Model",
		    fields : [{
	 	 		name : 'name',
	 	 		type : 'string'
	 	 	},{
	 	 		name : 'value',
	 	 		type : 'string'
	 	 	}]
		 });
	 
	 var longAndShort = Ext.create('Ext.data.Store', {
	 	model: "longAndShortModel",
	    data : [
	             {'name':'长线','value':'1'},
	             {'name':'短线','value':'0'}
	            ]
	 });
	 /**主界面->平台辖区表单*/
	 var queryForm = Ext.create('Ext.form.Panel',{
			layout : {
				type : 'table',	//table布局
				columns :5	//列数
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
				fieldLabel: '出发运作',
				id : 'startWorkFild',
				name:'startWork',
			},{
				fieldLabel: '到达运作',
				id : 'arriveWorkFild',
				name:'arriveWork'
			},{
				fieldLabel: '是否有效',
				id : 'active',
				name:'active',
				xtype : 'combo',
                store: isActive,
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
					linePlanStore.reload();
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
	var linePlanStore=Ext.create('Ty.Report.LinePlanSuspended.LinePlanSuspendedStore',{
		id:'linePlanId'
	});

	/**
	 * 主界面->表格
	 */
	var  linePlanGrid =Ext.create('Ext.grid.Panel', {
		store: linePlanStore,
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
						Ext.getCmp('repealBtn').setDisabled(false);
						Ext.getCmp('modifyBtn').setDisabled(true);
					}else{
						Ext.getCmp('repealBtn').setDisabled(true);
						Ext.getCmp('modifyBtn').setDisabled(true);
					}
				}
			}
		}),//checkbox
		region:'center',//border布局的中间
		columns: [//列
		{xtype: 'rownumberer',
			text:'序号',
			width:60},
        {
            dataIndex: 'startWork',
			text:'出发运作',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'arriveWork',
			text:'到达运作',
			width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'startGridLines',
			text:'发车线路(原)',
			 width:150,
			 menuDisabled:true,
        },
        {
            dataIndex: 'gridLines',
			text:'发车线路',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'belongLines',
			text:'所属路线',
            width:150,
            menuDisabled:true
        },{
        	dataIndex: 'departureType',
 			text:'单/对发',
 			 width:140,
 			 menuDisabled:true,
 			 renderer: function(value) {
 				    if(value=='1'){
 						return '单发';
 					}else if(value=='0'){
 						return '对发';
 					}
 	         }
        },{
            dataIndex: 'lineQuantity',
			text:'线路货量',
			 width:150,
			 menuDisabled:true,
        },
        {
            dataIndex: 'departureFrequency',
			text:'发车频率',
            width:150,
            menuDisabled:true
        },
        {
            dataIndex: 'monday',
			text:'周一',
            width:150,
            menuDisabled:true,
            renderer: function(value) {
			    if(value=='1'){
					return '√';
				}else if(value=='0'){
					return '';
				}
         }
        },{
            dataIndex: 'tuesday',
			text:'周二',
            width:150,
            menuDisabled:true,
            renderer: function(value) {
			    if(value=='1'){
					return '√';
				}else if(value=='0'){
					return '';
				}
         }
        },{
            dataIndex:'wednesday',
			text:'周三',
			width:150,
			 menuDisabled:true,
	            renderer: function(value) {
				    if(value=='1'){
						return '√';
					}else if(value=='0'){
						return '';
					}
	         }
        },{
        	text:'周四',
            dataIndex:'thursday',
            width:150,
            renderer: function(value) {
			    if(value=='1'){
					return '√';
				}else if(value=='0'){
					return '';
				}
         }
        },
        {
            dataIndex:'friday',
			text:'周五',
            width:150,
            menuDisabled:true,
            renderer: function(value) {
			    if(value=='1'){
					return '√';
				}else if(value=='0'){
					return '';
				}
         }
        },{
            dataIndex:'saturday',
			text:'周六',
            width:150,
            menuDisabled:true,
            renderer: function(value) {
			    if(value=='1'){
					return '√';
				}else if(value=='0'){
					return '';
				}
         }
        }
        ,{
            dataIndex:'sunday',
			text:'周日',
			width:150,
			 menuDisabled:true,
	            renderer: function(value) {
				    if(value=='1'){
						return '√';
					}else if(value=='0'){
						return '';
					}
	         }
        },
        {
            dataIndex: 'lineKil',
			text:'线路公里数',
            width:150,
            menuDisabled:true
        },
        {
            dataIndex: 'lineType',
			text:'线路类型',
			width:150,
			 menuDisabled:true,
			 renderer: function(value) {
				    if(value=='1'){
						return '长线';
					}else if(value=='0'){
						return '短线';
					}
	         }
        },{
            dataIndex: 'shifts',
			text:'班次',
			 width:150,
			 menuDisabled:true,
        },
        {
            dataIndex: 'totalShifts',
			text:'总班次',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'departureTime',
			text:'发车时间',
            width:150,
            menuDisabled:true,
            renderer: function(value) {
            	if(value ==null || value ==""){
					 return "—";
				 }else{
					 return dateRender(value,'H:i:s');
				 }
	         }
        },{
            dataIndex: 'travelTimeOne',
			text:'在途时间1',
			width:150,
			menuDisabled:true,
			renderer:function(value){
				if(value == null || value == ""){
					return "—";
				}else{
				    return value;
				}
			}
        },{
            dataIndex: 'arrivalTimeOne',
			text:'到达时间1',
			 width:150,
			 menuDisabled:true,
			 renderer: function(value) {
				 if(value ==null || value ==""){
					 return "—";
				 }else{
					 return dateRender(value,'H:i:s');
				 }
	         }
        },
        {
            dataIndex: 'departureTimeTwo',
			text:'发车时间2',
            width:150,
            menuDisabled:true,
			 renderer: function(value) {
				 if(value ==null || value ==""){
					 return "—";
				 }else{
					 return dateRender(value,'H:i:s');
				 }
	         }
        },{
            dataIndex: 'travelTimeTwo',
			text:'在途时间2(h)',
            width:150,
            menuDisabled:true,
            renderer:function(value){
				if(value == null || value == ""){
					return "—";
				}else{
				    return value;
				}
			}
        },{
            dataIndex: 'arrivalTimeTwo',
			text:'到达时间2',
			width:150,
			 menuDisabled:true,
			 renderer: function(value) {
				 if(value ==null || value ==""){
					 return "—";
				 }else{
					 return dateRender(value,'H:i:s');
				 }
	         }
        },{
            dataIndex: 'departureTimeThr',
			text:'发车时间3',
			 width:150,
			 menuDisabled:true,
			 renderer: function(value) {
				 if(value ==null || value ==""){
					 return "—";
				 }else{
					 return dateRender(value,'H:i:s');
				 }
	         }
        },
        {
            dataIndex: 'travelTimeThr',
			text:'在途时间3(h)',
            width:150,
            menuDisabled:true,
        renderer:function(value){
			if(value == null || value == ""){
				return "—";
			}else{
			    return value;
			}
		}
        },{
            dataIndex: 'arrivalTime',
			text:'到达时间',
            width:150,
            menuDisabled:true,
            renderer: function(value) {
				 if(value ==null || value ==""){
					 return "—";
				 }else{
					 return dateRender(value,'H:i:s');
				 }
	         }
        },{
            dataIndex: 'carType',
			text:'车型',
			width:150,
			 menuDisabled:true,
        },
        {
            dataIndex: 'trainNumber',
			text:'月承诺趟数',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'actualNumCars',
			text:'实际车辆数',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'demandForCars',
			text:'需求车辆数',
			width:150,
			 menuDisabled:true
        },{
            dataIndex: 'hangCarsDemand',
			text:'甩挂车辆需求',
			 width:150,
			 menuDisabled:true
        },
        {
            dataIndex: 'contractDate',
			text:'合同结束日期',
            width:150,
            menuDisabled:true,
            renderer: function(value){
				 if(value ==null || value ==""){
					 return "";
				 }else{
					 return dateRender(value,'Y-m-d');
				 }
	         }
        },{
            dataIndex: 'stopGoGoods',
			text:'停发走货规定',
			 width:150,
			 menuDisabled:true
        },{
            dataIndex: 'oppeningTime',
			text:'开通时间',
            width:150,
            menuDisabled:true,
            renderer: function(value) {
				 if(value ==null || value ==""){
					 return "";
				 }else{
					 return dateRender(value,'Y-m-d');
				 }
	         }
        },{
            dataIndex: 'remarks',
			text:'备注',
			width:150,
			 menuDisabled:true,
        },
        {
            dataIndex: 'active',
			text:'是否有效',
			 width:140,
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
			 width:160,
			 menuDisabled:true,
			 renderer: function(value) {
	                return dateRender(value,'Y-m-d H:i:s');
	         }
        },{
            dataIndex: 'invalidTime',
			text:'失效时间',
			 width:160,
			 menuDisabled:true,
			 renderer: function(value) {
	                return dateRender(value,'Y-m-d H:i:s');
	         }
        }],
        listeners : {
			itemClick : function() {}
		},
		 dockedItems:[{//分页
	 			xtype:'pagingtoolbar',
	 			id: 'querylinePlanGridPaginId',
	 			store:linePlanStore,
	 			dock:'bottom',
	 			displayInfo : true,
	 			autoScroll : true
	 		}],	
        tbar:[{
        	id:'addBtn',
			xtype:'button',
			text:'新增',
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
			handler:function(){
				addForm.getForm().reset();
				operateFlag = 2;
				var records = linePlanGrid.getSelectionModel().getSelection();//获取选中集合
				if(records.length == 0){
					showInfoMsg("请选择一条记录！");
					return false;
				};
				if(records.length != 1){
					showInfoMsg("只能选择一条记录！");
					return false;
				};
				var form = addForm.getForm();
				var LinePlanSuspendedModel = new Ty.Report.LinePlanSuspended.LinePlanSuspendedModel();
				form.loadRecord(LinePlanSuspendedModel);
				form.findField("linePlanId").enable();//修改的时候id为启用
				form.findField("linePlanId").setValue(records[0].get("linePlanId"));
				form.findField("startWork").setValue(records[0].get("startWork"));
				form.findField("arriveWork").setValue(records[0].get("arriveWork"));
				form.findField("startGridLines").setValue(records[0].get("startGridLines"));
				form.findField("gridLines").setValue(records[0].get("gridLines"));
				form.findField("belongLines").setValue(records[0].get("belongLines"));
				form.findField("departureType").setValue(records[0].get("departureType"));
				form.findField("lineQuantity").setValue(records[0].get("lineQuantity"));
				form.findField("departureFrequency").setValue(records[0].get("departureFrequency"));
				form.findField("monday").setValue(records[0].get("monday"));
				form.findField("tuesday").setValue(records[0].get("tuesday"));
				form.findField("wednesday").setValue(records[0].get("wednesday"));
				form.findField("thursday").setValue(records[0].get("thursday"));
				form.findField("friday").setValue(records[0].get("friday"));
				form.findField("saturday").setValue(records[0].get("saturday"));
				form.findField("sunday").setValue(records[0].get("sunday"));
				form.findField("lineKil").setValue(records[0].get("lineKil"));
				form.findField("lineType").setValue(records[0].get("lineType"));
				form.findField("shifts").setValue(records[0].get("shifts"));
				form.findField("totalShifts").setValue(records[0].get("totalShifts"));
				form.findField("departureTime").setValue(records[0].get("departureTime"));
				form.findField("travelTimeOne").setValue(records[0].get("travelTimeOne"));
				form.findField("arrivalTimeOne").setValue(records[0].get("arrivalTimeOne"));
				form.findField("departureTimeTwo").setValue(records[0].get("departureTimeTwo"));
				form.findField("travelTimeTwo").setValue(records[0].get("travelTimeTwo"));
				form.findField("arrivalTimeTwo").setValue(records[0].get("arrivalTimeTwo"));
				form.findField("departureTimeThr").setValue(records[0].get("departureTimeThr"));
				form.findField("travelTimeThr").setValue(records[0].get("travelTimeThr"));
				form.findField("arrivalTime").setValue(records[0].get("arrivalTime"));
				form.findField("carType").setValue(records[0].get("carType"));
				form.findField("trainNumber").setValue(records[0].get("trainNumber"));
				form.findField("actualNumCars").setValue(records[0].get("actualNumCars"));
				form.findField("demandForCars").setValue(records[0].get("demandForCars"));
				form.findField("hangCarsDemand").setValue(records[0].get("hangCarsDemand"));
				form.findField("contractDate").setValue(records[0].get("contractDate"));
				form.findField("stopGoGoods").setValue(records[0].get("stopGoGoods"));
				form.findField("oppeningTime").setValue(records[0].get("oppeningTime"));
				form.findField("remarks").setValue(records[0].get("remarks"));
				form.updateRecord(LinePlanSuspendedModel);
				win.setTitle("数据修改");
				win.show();
			}
		},'-',{//提示 '->' 让按钮居右
			id:'repealBtn',
			xtype:'button',
			text:'作废',
			handler:function(){
				var records = linePlanGrid.getSelectionModel().getSelection();//获取选中集合
				if(records.length == 0){
					showInfoMsg("请选择一条记录！");
					return false;
				};
				confirm("已选择"+records.length+"条数据，确认作废？",
						function(){
					var linePlanLists = [];
					for(var i = 0;i<records.length;i++){
						var item = records[i].data;
						item.id = "";
						linePlanLists.push(item);
					}
					var formUrl = 'linePlanSuspendedAction!repealLinePlanSuspendedInfo.action';
					var param = {
								'linePlanVo':{
									'linePlanList':linePlanLists
								}
							};
					Ext.Msg.wait('保存中，请稍后...', '提示');
					    var returnSuc = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('querylinePlanGridPaginId').moveFirst();
							win.hide();
						};
						var returnFail = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('querylinePlanGridPaginId').moveFirst();
							win.hide();
						};
						requestUmpAjaxJsonParams(formUrl,param,returnSuc,returnFail);
				 });
			}
		},'-',{//提示 '->' 让按钮居右
			id:'repealAllBtn',
			xtype:'button',
			text:'全部作废',
			handler:function(){
				confirm("是否作废所有销售线路数据？",
						function(){
					var formUrl = 'linePlanSuspendedAction!repealAllLinePlanSuspendedInfo.action';
					var param = {};
					Ext.Msg.wait('作废中，请稍后...', '提示');
					    var returnSuc = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('querylinePlanGridPaginId').moveFirst();
							win.hide();
						};
						var returnFail = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('querylinePlanGridPaginId').moveFirst();
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
		       url : 'linePlanSuspendedAction!implExcel.action',  
		       method : 'post',  
		       params : {  
		    	   'linePlanVo.filePath' : outFileName  
		          },  
		          success : function(response, options) {  
		             //隐藏进度条   
		               Ext.Msg.hide();   
		              var responseArray = Ext.util.JSON.decode(response.responseText); 
		              var msg = "";
		              if(responseArray.success){
		            	//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			              msg="本次导入"+responseArray.linePlanVo.sumSize+"条数据，新增数据"+responseArray.linePlanVo.addSize+"条，覆盖原数据"+responseArray.linePlanVo.coverSize+"条。"	
			              linePlanStore.reload();
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
			columns : 3 //列数
		},
		defaults: {//统一设置宽、居右
			margin:'5 5 5 5',//间距
			labelWidth:130,
			border:false,
			labelAlign: 'right',
			//width : 200,
			//height:  100,
		},
		autoScroll:true,
		defaultType:'textfield',//默认类型
		frame:true,
		width:980,
		height:500,
		region:'center',
		buttonAlign:'center',
		items:[{
			fieldLabel: 'linePlanId',
			hidden:true,
			name:'linePlanId',
			id:'linePlanId',
		},{
			fieldLabel: '出发运作',
			height:15,
			maxLength:25,
			allowBlank:false,//判断不能为空
			name:'startWork',
			id:'startWork',
		}
		,{
			fieldLabel: '到达运作',
			height:15,
			maxLength:25,
			allowBlank:false,//判断不能为空
			name:'arriveWork',
			id:'arriveWork',
		}
		,{
			fieldLabel: '发车线路(原)',
			height:15,
			maxLength:10,
			allowBlank:false,//判断  不能为空
			name:'startGridLines',
			id:'startGridLines',
		},{
			fieldLabel: '发车线路',
			height:15,
			maxLength:25,
			allowBlank:false,//判断不能为空
			name:'gridLines',
			id:'gridLines',
		},{
			fieldLabel: '所属路线',
			height:15,
			maxLength:10,
			allowBlank:false,//判断不能为空
			name:'belongLines',
			id:'belongLines',
		},{
			fieldLabel: '单/对发',
			name:'departureType',
			id:'departureType',
			xtype : 'combo',
            store: singAndDouble,
            displayField: "name",
            valueField: "value",
            queryMode: "local",
            autoSelect:true,
            forceSelection: true,
            allowBlank:false,
		},{
			fieldLabel: '线路货量',
			height:15,
			maxLength:10,
			allowBlank:false,//判断不能为空
			name:'lineQuantity',
			id:'lineQuantity',
		},{
			fieldLabel: '发车频率',
			height:15,
			maxLength:25,
			allowBlank:false,//判断不能为空
			regex: /^\d+$/, //数字
            regexText: '只能输入数字',
			name:'departureFrequency',
			id:'departureFrequency',
		},{
			fieldLabel: '线路公里数',
			height:15,
			maxLength:10,
			allowBlank:false,//判断不能为空
			name:'lineKil',
			id:'lineKil',
		}
		,{
			fieldLabel: '线路类型',
			name:'lineType',
			id:'lineType',
			xtype : 'combo',
            store: longAndShort,
            displayField: "name",
            valueField: "value",
            queryMode: "local",
            autoSelect:true,
            forceSelection: true,
            allowBlank:false,
		},{
			fieldLabel:'班次',
			height:15,
			maxLength:10,
			allowBlank:false,//判断不能为空
			regex: /^\d+$/, //数字
            regexText: '只能输入数字',
			name:'shifts',
			id:'shifts',
		},{
			fieldLabel:'总班次',
			height:15,
			maxLength:10,
			allowBlank:false,//判断不能为空
			regex: /^\d+$/, //数字
            regexText: '只能输入数字',
			name:'totalShifts',
			id:'totalShifts',
		},{
			fieldLabel: '发车时间',
			allowBlank:false,//判断不能为空
			xtype : 'timefield',
			format : 'H:i:s',
			name:'departureTime',
			id:'departureTime',
		},{
			fieldLabel: '在途时间1(h)',
			height:15,
			maxLength:25,
			allowBlank:false,//判断不能为空
			name:'travelTimeOne',
			id:'travelTimeOne',
		},
		{
			fieldLabel: '到达时间1',
			allowBlank:true,//判断不能为空
			xtype : 'timefield',
			format : 'H:i:s',
			name:'arrivalTimeOne',
			id:'arrivalTimeOne',
		},{
			fieldLabel: '发车时间2',
			allowBlank:true,//判断不能为空
			xtype : 'timefield',
			format : 'H:i:s',
			name:'departureTimeTwo',
			id:'departureTimeTwo',
		},{
			fieldLabel: '在途时间2(h)',
			allowBlank:true,//判断不能为空
			height:15,
			maxLength:5,
			name:'travelTimeTwo',
			id:'travelTimeTwo',
		},{
			fieldLabel: '到达时间2',
			allowBlank:true,//判断不能为空
			xtype : 'timefield',
			format : 'H:i:s',
			name:'arrivalTimeTwo',
			id:'arrivalTimeTwo',
		},{
			fieldLabel: '发车时间3',
			allowBlank:true,//判断不能为空
			xtype : 'timefield',
			format : 'H:i:s',
			name:'departureTimeThr',
			id:'departureTimeThr',
		},{
			fieldLabel: '在途时间3(h)',
			height:15,
			maxLength:5,
			allowBlank:true,//判断不能为空
			name:'travelTimeThr',
			id:'travelTimeThr',
		},{
			fieldLabel: '到达时间',
			allowBlank:true,//判断不能为空
			xtype : 'timefield',
			format : 'H:i:s',
			name:'arrivalTime',
			id:'arrivalTime',
		},{
			fieldLabel: '车型',
			height:15,
			maxLength:25,
			allowBlank:true,//判断不能为空
			name:'carType',
			id:'carType',
		},
		{
			fieldLabel: '月承诺趟数',
			height:15,
			maxLength:25,
			allowBlank:true,//判断不能为空
			name:'trainNumber',
			id:'trainNumber',
		},
		 {
			fieldLabel: '实际车辆数',
			height:15,
			maxLength:25,
			allowBlank:true,//判断不能为空
			name:'actualNumCars',
			id:'actualNumCars',
		},{
			fieldLabel: '需求车辆数',
			allowBlank:true,//判断不能为空
			height:15,
			maxLength:25,
			name:'demandForCars',
			id:'demandForCars',
		},{
			fieldLabel: '甩挂车辆需求',
			allowBlank:true,//判断不能为空
			height:15,
			maxLength:25,
			name:'hangCarsDemand',
			id:'hangCarsDemand',
		},{
			fieldLabel: '合同结束日期',
			allowBlank:true,//判断不能为空
			height:15,
			maxLength:25,
			xtype : 'timefield',
			format : 'Y-m-d',
			name:'contractDate',
			id:'contractDate',
		},{
			fieldLabel: '停发走货规定',
			allowBlank:true,//判断不能为空
			height:15,
			maxLength:25,
			name:'stopGoGoods',
			id:'stopGoGoods',
		},{
			fieldLabel: '开通时间',
			allowBlank:true,//判断不能为空
			xtype : 'timefield',
			format : 'Y-m-d',
			name:'oppeningTime',
			id:'oppeningTime',
		},{
			fieldLabel: '周一',
			xtype: "checkboxfield",
			inputValue: '1',
			name:'monday',
			id:'monday',
		},{
			fieldLabel: '周二',
			xtype: "checkboxfield",
			inputValue: '1',
			name:'tuesday',
			id:'tuesday',
		},{
			fieldLabel: '周三',
			xtype: "checkboxfield",
			inputValue: '1',
			name:'wednesday',
			id:'wednesday',
		},{
			fieldLabel: '周四',
			xtype: "checkboxfield",
			inputValue: '1',
			name:'thursday',
			id:'thursday',
		},{
			fieldLabel: '周五',
			xtype: "checkboxfield",
			inputValue: '1',
			name:'friday',
			id:'friday',
		},{
			fieldLabel: '周六',
			xtype: "checkboxfield",
			inputValue: '1',
			name:'saturday',
			id:'saturday',
		},{
			fieldLabel: '周日',
			xtype: "checkboxfield",
			inputValue: '1',
			name:'sunday',
			id:'sunday',
		},{
			fieldLabel: '备注',
			width:640,
			height:15,
			maxLength:60,
			colspan:2, 
			name:'remarks',
			id:'remarks',
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
				var form = addForm.getForm();
				if(form.isValid()){//表单验证是否可提交
					saveLinePlanTimeInfo();
                    linePlanStore.reload();
				}else{//表单验证不通过
					formCheck();
				}
			}
		}]
	});
	function saveLinePlanTimeInfo(){
		Ext.Msg.wait('保存中，请稍后...', '提示');
		var form = addForm.getForm();
		var formUrl;
		var param;
		switch (operateFlag) {
		case 1:
			formUrl = 'linePlanSuspendedAction!addLinePlanSuspendedInfo.action';
			param = {
					'linePlanVo':{
						'linePlanparam':{
							'startWork':Ext.String.trim(Ext.getCmp('startWork').getValue()),
							'arriveWork':Ext.String.trim(Ext.getCmp('arriveWork').getValue()),
							'startGridLines':Ext.String.trim(Ext.getCmp('startGridLines').getValue()),
							'gridLines':Ext.String.trim(Ext.getCmp('gridLines').getValue()),
							'belongLines':Ext.String.trim(Ext.getCmp('belongLines').getValue()),
							'departureType':Ext.String.trim(Ext.getCmp('departureType').getValue()),
							'lineQuantity':Ext.String.trim(Ext.getCmp('lineQuantity').getValue()),
							'departureFrequency':Ext.String.trim(Ext.getCmp('departureFrequency').getValue()),
							'monday':Ext.getCmp('monday').getValue() ?'1':'0',
							'tuesday':Ext.getCmp('tuesday').getValue() ?'1':'0',
							'wednesday':Ext.getCmp('wednesday').getValue() ?'1':'0',
							'thursday':Ext.getCmp('thursday').getValue() ?'1':'0',
							'friday':Ext.getCmp('friday').getValue() ?'1':'0',
							'saturday':Ext.getCmp('saturday').getValue() ?'1':'0',
							'sunday':Ext.getCmp('sunday').getValue() ?'1':'0',
							'lineKil':Ext.String.trim(Ext.getCmp('lineKil').getValue()),
							'lineType':Ext.String.trim(Ext.getCmp('lineType').getValue()),
							'shifts':Ext.String.trim(Ext.getCmp('shifts').getValue()),
							'totalShifts':Ext.String.trim(Ext.getCmp('totalShifts').getValue()),
							'departureTime':Ext.getCmp('departureTime').getValue(),
							'travelTimeOne':Ext.String.trim(Ext.getCmp('travelTimeOne').getValue()),
							'arrivalTimeOne':Ext.getCmp('arrivalTimeOne').getValue(),
							'departureTimeTwo':Ext.getCmp('departureTimeTwo').getValue(),
							'travelTimeTwo':Ext.String.trim(Ext.getCmp('travelTimeTwo').getValue()),
					 		'arrivalTimeTwo':Ext.getCmp('arrivalTimeTwo').getValue(),
							'departureTimeThr':Ext.getCmp('departureTimeThr').getValue(),
							'travelTimeThr':Ext.String.trim(Ext.getCmp('travelTimeThr').getValue()),
							'arrivalTime':Ext.getCmp('arrivalTime').getValue(),
							'carType':Ext.String.trim(Ext.getCmp('carType').getValue()),
							'trainNumber':Ext.String.trim(Ext.getCmp('trainNumber').getValue()),
							'actualNumCars':Ext.String.trim(Ext.getCmp('actualNumCars').getValue()),
							'demandForCars':Ext.String.trim(Ext.getCmp('demandForCars').getValue()),
							'hangCarsDemand':Ext.String.trim(Ext.getCmp('hangCarsDemand').getValue()),
							'contractDate':Ext.getCmp('contractDate').getValue(),
							'stopGoGoods':Ext.String.trim(Ext.getCmp('stopGoGoods').getValue()),
							'oppeningTime':Ext.getCmp('oppeningTime').getValue(),
							'remarks':Ext.String.trim(Ext.getCmp('remarks').getValue())
						}
					}
			};
			break;
		case 2:
			formUrl = 'linePlanSuspendedAction!modifyLinePlanSuspendedInfo.action';
			param = {
					'linePlanVo':{
						'linePlanparam':{
							'linePlanId':Ext.String.trim(Ext.getCmp('linePlanId').getValue()),
							'startWork':Ext.String.trim(Ext.getCmp('startWork').getValue()),
							'arriveWork':Ext.String.trim(Ext.getCmp('arriveWork').getValue()),
							'startGridLines':Ext.String.trim(Ext.getCmp('startGridLines').getValue()),
							'gridLines':Ext.String.trim(Ext.getCmp('gridLines').getValue()),
							'belongLines':Ext.String.trim(Ext.getCmp('belongLines').getValue()),
							'departureType':Ext.String.trim(Ext.getCmp('departureType').getValue()),
							'lineQuantity':Ext.String.trim(Ext.getCmp('lineQuantity').getValue()),
							'departureFrequency':Ext.String.trim(Ext.getCmp('departureFrequency').getValue()),
							'monday':Ext.getCmp('monday').getValue() ?'1':'0',
							'tuesday':Ext.getCmp('tuesday').getValue() ?'1':'0',
							'wednesday':Ext.getCmp('wednesday').getValue() ?'1':'0',
							'thursday':Ext.getCmp('thursday').getValue() ?'1':'0',
							'friday':Ext.getCmp('friday').getValue() ?'1':'0',
							'saturday':Ext.getCmp('saturday').getValue() ?'1':'0',
							'sunday':Ext.getCmp('sunday').getValue() ?'1':'0',
							'lineKil':Ext.String.trim(Ext.getCmp('lineKil').getValue()),
							'lineType':Ext.String.trim(Ext.getCmp('lineType').getValue()),
							'shifts':Ext.String.trim(Ext.getCmp('shifts').getValue()),
							'totalShifts':Ext.String.trim(Ext.getCmp('totalShifts').getValue()),
							'departureTime':Ext.getCmp('departureTime').getValue(),
							'travelTimeOne':Ext.String.trim(Ext.getCmp('travelTimeOne').getValue()),
							'arrivalTimeOne':Ext.getCmp('arrivalTimeOne').getValue(),
							'departureTimeTwo':Ext.getCmp('departureTimeTwo').getValue(),
							'travelTimeTwo':Ext.String.trim(Ext.getCmp('travelTimeTwo').getValue()),
					 		'arrivalTimeTwo':Ext.getCmp('arrivalTimeTwo').getValue(),
							'departureTimeThr':Ext.getCmp('departureTimeThr').getValue(),
							'travelTimeThr':Ext.String.trim(Ext.getCmp('travelTimeThr').getValue()),
							'arrivalTime':Ext.getCmp('arrivalTime').getValue(),
							'carType':Ext.String.trim(Ext.getCmp('carType').getValue()),
							'trainNumber':Ext.String.trim(Ext.getCmp('trainNumber').getValue()),
							'actualNumCars':Ext.String.trim(Ext.getCmp('actualNumCars').getValue()),
							'demandForCars':Ext.String.trim(Ext.getCmp('demandForCars').getValue()),
							'hangCarsDemand':Ext.String.trim(Ext.getCmp('hangCarsDemand').getValue()),
							'contractDate':Ext.getCmp('contractDate').getValue(),
							'stopGoGoods':Ext.String.trim(Ext.getCmp('stopGoGoods').getValue()),
							'oppeningTime':Ext.getCmp('oppeningTime').getValue(),
							'remarks':Ext.String.trim(Ext.getCmp('remarks').getValue())
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
				Ext.getCmp('querylinePlanGridPaginId').moveFirst();
				win.hide();
			}else{
			}
		};
		var returnFail = function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('querylinePlanGridPaginId').moveFirst();
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
			width:980,
			height:500,
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
		items: [queryForm,linePlanGrid]
	});
});