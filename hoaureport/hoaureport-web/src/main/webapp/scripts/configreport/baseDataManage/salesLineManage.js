
/**主界面->平台辖区模型*/
Ext.define('Ty.Report.SalesLine.SalesLineModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'slId'
	},{
		name : 'lineNum'
	},{
		name : 'note'
	},{
		name : 'salesLineName'
	},{
		name : 'loadingPort'
	},{
		name : 'loadingPortId'
	},{
		name : 'transitDepot1'
	},{
		name : 'transitDepot2'
	},{
		name : 'termint'
	},{
		name : 'termintId'
	},{
		name : 'rely1Class'
	},{
		name : 'rely2Class'
	},{
		name : 'rely3Class'
	},{
		name : 'line1'
	},{
		name : 'line2'
	},{
		name : 'line3'
	},{
		name : 'noTransferArrival',
		type : 'date',
		dateFormat : 'time'
	},{
		name : 'oneTransferArrival',
		type : 'date',
		dateFormat : 'time'
	},{
		name : 'twoTransferArrival',
		type : 'date',
		dateFormat : 'time'
	},{
		name : 'breakOneDayTimes'
	},{
		name : 'breakTwoDaysTimes'
	},{
		name : 'relyLine1'
	},{
		name : 'relyLine2'
	},{
		name : 'relyLine3'
	},{
		name : 'dispatchDayAndNextDayNum'
	},{
		name : 'loadingPortFirstLevelDispa',
		type : 'date',
		dateFormat : 'time'
			
	},{
		name : 'transitDepot1DispatchTime',
		type : 'date',
		dateFormat : 'time'
	},{
		name : 'transitDepot2DispatchTime',
		type : 'date',
		dateFormat : 'time'
	},{
		name : 'transitDepot1ArraivalTime',
		type : 'date',
		dateFormat : 'time'
	},{
		name : 'transitDepot2ArraivalTime',
		type : 'date',
		dateFormat : 'time'
	},{
		name : 'termitFirstArrivalTime',
		type : 'date',
		dateFormat : 'time'
	},{
		name : 'TransitHours1'
	},{
		name : 'TransitHours2'
	},{
		name : 'TransitHours3'
	},{
		name : 'breakHours1'
	},{
		name : 'breakHours2'
	},{
		name : 'hoursInTransit'
	},{
		name : 'unloadHours'
	},{
		name : 'terminalShortLongLines'
	},{
		name : 'arrival0And1'
	},{
		name : 'openMonth'
	},{
		name : 'lineStatus'
	},{
		name : 'pickupDays'
	},{
		name : 'deliverDays'
	},{
		name : 'monday'
	},{
		name : 'tuesday'
	},{
		name : 'wednesday'
	},{
		name : 'thusday'
	},{
		name : 'friday'
	},{
		name : 'saturday'
	},{
		name : 'sunday'
	},{
		name : 'timelyNote'
	},{
		name : 'openLineYear'
	},{
		name : 'isOpenGolden100Lines'
	},{
		name : 'standardNote'
	},{
		name : 'linePartner'
	},{
		name : 'active'
	},{// 生效时间
		name : 'effectedTime',
			type : 'date',
			dateFormat : 'time',
	},{// 失效时间
		name : 'invalidTime',
			type : 'date',
			dateFormat : 'time',
	},{//创建时间
		name : 'createTime',
			type : 'date',
			dateFormat : 'time',
	},{// 创建者编号
		name : 'createUserCode',
	},{// 修改时间
		name : 'modifyTime',
			type : 'date',
			dateFormat : 'time',
	},{// 修改者编号
		name : 'modifyUserCode',
	}]
});

/***主界面->平台辖区store*/
Ext.define('Ty.Report.SalesLine.SalesLineStore', {
	extend : 'Ext.data.Store',
	model : 'Ty.Report.SalesLine.SalesLineModel',
	pageSize : 100,// 分页条数
	proxy : {
		type : 'ajax',
		url : 'salesLineAction!querySalesLineInfo.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'salesLineVo.salesLineList',
			totalProperty : 'totalCount'
		} 
	},
	listeners : {
		//在加载数据之前操作
		beforeload : function(store, operation, eOpts) {
				var params = {
						'salesLineVo.salesLine.salesLineName':Ext.String.trim(Ext.getCmp('salesLineNameF').getValue()),
						'salesLineVo.salesLine.loadingPort':Ext.String.trim(Ext.getCmp('loadingPortF').getValue()),
						'salesLineVo.salesLine.termint':Ext.String.trim(Ext.getCmp('termintF').getValue()),
						'salesLineVo.salesLine.active':Ext.String.trim(Ext.getCmp('active').getValue())
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
	  * 主界面->当日/次日
	  */
	 var oneAndZero = Ext.create('Ext.data.Store', {
	 	model: "activeModel",
	    data : [
	             {'name':'1','value':'1'},
	             {'name':'0','value':'0'}
	            ]
	 });
	 
	 /**
	  * 主界面->0/1
	  */
	 var dayAndNextDay = Ext.create('Ext.data.Store', {
	 	model: "activeModel",
	    data : [
	             {'name':'当日','value':'1'},
	             {'name':'次日','value':'0'}
	            ]
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
	  * 主界面->长线/短线
	  */
	 var longAndShort = Ext.create('Ext.data.Store', {
	 	model: "activeModel",
	    data : [
	             {'name':'长线','value':'1'},
	             {'name':'短线','value':'0'}
	            ]
	 });
	 /**主界面->平台辖区表单*/
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
				fieldLabel: '销售线路',
				id : 'salesLineNameF',
				name:'salesLineNameF',
			},{
				fieldLabel: '起运地',
				id : 'loadingPortF',
				name:'loadingPortF'
			},{
				fieldLabel: '目的地',
				id : 'termintF',
				name:'termintF'
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
					salesLineStore.reload();
					//platformAreaStore.loadPage(1);	//查询后默认返回第一页
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
	var salesLineStore=Ext.create('Ty.Report.SalesLine.SalesLineStore',{
		id:'slId'
	});

	/**
	 * 主界面->表格
	 */
	var  salesLineGrid =Ext.create('Ext.grid.Panel', {
		store: salesLineStore,//创建querySqlStore
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
			width:40},
        {
            dataIndex: 'lineNum',
			text:'序号',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'note',
			text:'备注',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'salesLineName',
			text:'销售线路名称',
			width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'loadingPort',
			text:'起运地',
			 width:150,
			 menuDisabled:true,
        },
        {
            dataIndex: 'loadingPortId',
			text:'起运地编号',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'transitDepot1',
			text:'中转地1',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'transitDepot2',
			text:'中转地2',
			width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'termint',
			text:'目的地',
			 width:150,
			 menuDisabled:true,
        },
        {
            dataIndex: 'termintId',
			text:'目的地编号',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'rely1Class',
			text:'依托1班次',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'rely2Class',
			text:'依托2班次',
			width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'rely3Class',
			text:'依托3班次',
			 width:150,
			 menuDisabled:true,
        },
        {
            dataIndex: 'line1',
			text:'线1',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'line2',
			text:'线2',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'line3',
			text:'线3',
			width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'noTransferArrival',
			text:'无中转到车',
			 width:150,
			 menuDisabled:true,
			 renderer: function(value) {
				 if(value ==null || value ==""){
					 return "";
				 }else{
					 return dateRender(value,'H:i');
				 }
	         }
        },
        {
            dataIndex: 'oneTransferArrival',
			text:'经一次中转终点到车',
            width:150,
            menuDisabled:true,
			 renderer: function(value) {
				 if(value ==null || value ==""){
					 return "";
				 }else{
					 return dateRender(value,'H:i');
				 }
	         }
        },{
            dataIndex: 'twoTransferArrival',
			text:'经两次中转终点到车',
            width:150,
            menuDisabled:true,
			 renderer: function(value) {
				 if(value ==null || value ==""){
					 return "";
				 }else{
					 return dateRender(value,'H:i');
				 }
	         }
        },{
            dataIndex: 'breakOneDayTimes',
			text:'中停一天数',
			width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'breakTwoDaysTimes',
			text:'中停两天数',
			 width:150,
			 menuDisabled:true,
        },
        {
            dataIndex: 'relyLine1',
			text:'依托线1',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'relyLine2',
			text:'依托线2',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'relyLine3',
			text:'依托线3',
			width:150,
			 menuDisabled:true,
        },
        {
            dataIndex: 'dispatchDayAndNextDayNum',
			text:'发货当日(0)and次日(1)',
            width:150,
            menuDisabled:true,
            renderer: function(value) {
			    if(value=='0'){
					return '当日';
				}else if(value=='1'){
					return '次日';
				}else if(value=='2'){
					return '后日';
				}else if(value=='3'){
					return '3';
				}else if(value=='4'){
					return '4';
				}
         }
            
        },{
            dataIndex: 'loadingPortFirstLevelDispa',
			text:'起点一级发车时间',
            width:150,
            menuDisabled:true,
			 renderer: function(value) {
				 if(value ==null || value ==""){
					 return "";
				 }else{
					 return dateRender(value,'H:i');
				 }
	         }
        },{
            dataIndex: 'transitDepot1DispatchTime',
			text:'中转地1发车时间',
			width:150,
			 menuDisabled:true,
			 renderer: function(value) {
				 if(value ==null || value ==""){
					 return "";
				 }else{
					 return dateRender(value,'H:i');
				 }
	         }
        },{
            dataIndex: 'transitDepot2DispatchTime',
			text:'中转地2发车时间',
			 width:150,
			 menuDisabled:true,
			 renderer: function(value) {
				 if(value ==null || value ==""){
					 return "";
				 }else{
					 return dateRender(value,'H:i');
				 }
	         }
        },
        {
            dataIndex: 'transitDepot1ArraivalTime',
			text:'中转地1到车时间',
            width:150,
            menuDisabled:true,
			 renderer: function(value) {
				 if(value ==null || value ==""){
					 return "";
				 }else{
					 return dateRender(value,'H:i');
				 }
	         }
        },{
            dataIndex: 'transitDepot2ArraivalTime',
			text:'中转地2到车时间',
            width:150,
            menuDisabled:true,
			 renderer: function(value) {
				 if(value ==null || value ==""){
					 return "";
				 }else{
					 return dateRender(value,'H:i');
				 }
	         }
        },{
            dataIndex: 'transitHours1',
			text:'在途小时1',
			width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'transitHours2',
			text:'在途小时2',
			 width:150,
			 menuDisabled:true,
        },
        {
            dataIndex: 'transitHours3',
			text:'在途小时3',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'breakHours1',
			text:'中停小时1',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'breakHours2',
			text:'中停小时2',
			width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'hoursInTransit',
			text:'总在途小时(H)',
			 width:150,
			 menuDisabled:true,
        },
        {
            dataIndex: 'unloadHours',
			text:'卸车时长(H)',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'terminalShortLongLines',
			text:'终端服务长短线(长线为0，短线为1)',
            width:150,
            menuDisabled:true,
            renderer: function(value) {
			    if(value=='0'){
					return '长线';
				}else if(value=='1'){
					return '短线';
				}
         }
        },{
            dataIndex: 'arrival0And1',
			text:'到货当日(0)and次日(1)',
			 width:150,
			 menuDisabled:true,
			 renderer: function(value) {
				    if(value=='0'){
						return '当日';
					}else if(value=='1'){
						return '次日';
					}else if(value=='2'){
						return '后日';
					}else if(value=='3'){
						return '3';
					}else if(value=='4'){
						return '4';
					}
	         }
        },
        {
            dataIndex: 'openMonth',
			text:'开通月份',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'lineStatus',
			text:'线路状态',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'pickupDays',
			text:'提货时间(天)',
			width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'deliverDays',
			text:'送货时间(天)',
			 width:150,
			 menuDisabled:true,
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
            dataIndex: 'wednesday',
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
            dataIndex: 'thusday',
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
            dataIndex: 'friday',
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
            dataIndex: 'saturday',
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
        },{
            dataIndex: 'sunday',
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
        },{
            dataIndex: 'timelyNote',
			text:'时效备注',
			 width:150,
			 menuDisabled:true,
        },
        {
            dataIndex: 'openLineYear',
			text:'开线年份',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'isOpenGolden100Lines',
			text:'是否黄金100条线路',
            width:150,
            menuDisabled:true,
            renderer: function(value) {
			    if(value=='1'){
					return '黄金100条线路';
				}else if(value=='0'){
					return '';
				}
         }
        },{
            dataIndex: 'standardNote',
			text:'标准备注',
			width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'linePartner',
			text:'线路合伙人',
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
	 			id: 'querysalesLineGridPaginId',
	 			store:salesLineStore,
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
			//iconCls: 'del',
			handler:function(){
				addForm.getForm().reset();
				operateFlag = 2;
				var records = salesLineGrid.getSelectionModel().getSelection();//获取选中集合
				if(records.length == 0){
					showInfoMsg("请选择一条记录！");
					return false;
				};
				if(records.length != 1){
					showInfoMsg("只能选择一条记录！");
					return false;
				};
				var form = addForm.getForm();
				var SalesLineModel = new Ty.Report.SalesLine.SalesLineModel();
				form.loadRecord(SalesLineModel);
				//form.findField("slId").enable();//修改的时候id为启用
//				form.findField("platformAreaId").setValue("");//清空
				form.findField("slId").setValue(records[0].get("slId"));
				form.findField("lineNum").setValue(records[0].get("lineNum"));
				form.findField("note").setValue(records[0].get("note"));
				form.findField("salesLineName").setValue(records[0].get("salesLineName"));
				form.findField("loadingPort").setValue(records[0].get("loadingPort"));
				form.findField("loadingPortId").setValue(records[0].get("loadingPortId"));
				form.findField("transitDepot1").setValue(records[0].get("transitDepot1"));
				form.findField("transitDepot2").setValue(records[0].get("transitDepot2"));
				form.findField("termint").setValue(records[0].get("termint"));
				form.findField("termintId").setValue(records[0].get("termintId"));
				form.findField("rely1Class").setValue(records[0].get("rely1Class"));
				form.findField("rely2Class").setValue(records[0].get("rely2Class"));
				form.findField("rely3Class").setValue(records[0].get("rely3Class"));
				form.findField("line1").setValue(records[0].get("line1"));
				form.findField("line2").setValue(records[0].get("line2"));
				form.findField("line3").setValue(records[0].get("line3"));
				form.findField("noTransferArrival").setValue(records[0].get("noTransferArrival"));
				form.findField("oneTransferArrival").setValue(records[0].get("oneTransferArrival"));
				form.findField("twoTransferArrival").setValue(records[0].get("twoTransferArrival"));
				form.findField("breakOneDayTimes").setValue(records[0].get("breakOneDayTimes"));
				form.findField("breakTwoDaysTimes").setValue(records[0].get("breakTwoDaysTimes"));
				form.findField("relyLine1").setValue(records[0].get("relyLine1"));
				form.findField("relyLine2").setValue(records[0].get("relyLine2"));
				form.findField("relyLine3").setValue(records[0].get("relyLine3"));
				form.findField("dispatchDayAndNextDayNum").setValue(records[0].get("dispatchDayAndNextDayNum"));
				form.findField("loadingPortFirstLevelDispa").setValue(records[0].get("loadingPortFirstLevelDispa"));
				form.findField("transitDepot1DispatchTime").setValue(records[0].get("transitDepot1DispatchTime"));
				form.findField("transitDepot2DispatchTime").setValue(records[0].get("transitDepot2DispatchTime"));
				form.findField("transitDepot1ArraivalTime").setValue(records[0].get("transitDepot1ArraivalTime"));
				form.findField("transitDepot2ArraivalTime").setValue(records[0].get("transitDepot2ArraivalTime"));
				form.findField("termitFirstArrivalTime").setValue(records[0].get("termitFirstArrivalTime"));
				form.findField("transitHours1").setValue(records[0].get("transitHours1"));
				form.findField("transitHours2").setValue(records[0].get("transitHours2"));
				form.findField("transitHours3").setValue(records[0].get("transitHours3"));
				form.findField("breakHours1").setValue(records[0].get("breakHours1"));
				form.findField("breakHours2").setValue(records[0].get("breakHours2"));
				form.findField("hoursInTransit").setValue(records[0].get("hoursInTransit"));
				form.findField("unloadHours").setValue(records[0].get("unloadHours"));
				form.findField("terminalShortLongLines").setValue(records[0].get("terminalShortLongLines"));
				form.findField("arrival0And1").setValue(records[0].get("arrival0And1"));
				form.findField("openMonth").setValue(records[0].get("openMonth"));
				form.findField("lineStatus").setValue(records[0].get("lineStatus"));
				form.findField("pickupDays").setValue(records[0].get("pickupDays"));
				form.findField("deliverDays").setValue(records[0].get("deliverDays"));
				form.findField("monday").setValue(records[0].get("monday"));
				form.findField("tuesday").setValue(records[0].get("tuesday"));
				form.findField("wednesday").setValue(records[0].get("wednesday"));
				form.findField("thusday").setValue(records[0].get("thusday"));
				form.findField("friday").setValue(records[0].get("friday"));
				form.findField("saturday").setValue(records[0].get("saturday"));
				form.findField("sunday").setValue(records[0].get("sunday"));
				form.findField("timelyNote").setValue(records[0].get("timelyNote"));
				form.findField("openLineYear").setValue(records[0].get("openLineYear"));
				form.findField("isOpenGolden100Lines").setValue(records[0].get("isOpenGolden100Lines"));
				form.findField("standardNote").setValue(records[0].get("standardNote"));
				form.findField("linePartner").setValue(records[0].get("linePartner"));
				form.updateRecord(SalesLineModel);
				win.setTitle("数据修改");
				win.show();
			}
		},'-',{//提示 '->' 让按钮居右
			id:'repealBtn',
			xtype:'button',
			text:'作废',
			//iconCls: 'edit',
			handler:function(){
				var records = salesLineGrid.getSelectionModel().getSelection();//获取选中集合
				if(records.length == 0){
					showInfoMsg("请选择一条记录！");
					return false;
				};
//				if(records.length != 1){
//					showInfoMsg("只能选择一条记录！");
//					return false;
//				};
				confirm("已选择"+records.length+"条数据，确认作废？",
						function(){
					var salesLineList = [];
					for(var i = 0;i<records.length;i++){
						var item = records[i].data;
						item.id = "";
						salesLineList.push(item);
					}
					var formUrl = 'salesLineAction!repealSalesLineInfo.action';
					var param = {
								'salesLineVo':{
									'salesLineList':salesLineList
								}
							};
					Ext.Msg.wait('保存中，请稍后...', '提示');
					    var returnSuc = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('querysalesLineGridPaginId').moveFirst();
							win.hide();
						};
						var returnFail = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('querysalesLineGridPaginId').moveFirst();
							win.hide();
						};
						requestUmpAjaxJsonParams(formUrl,param,returnSuc,returnFail);
				 });
			}
		},'-',{//提示 '->' 让按钮居右
			id:'repealAllBtn',
			xtype:'button',
			text:'全部作废',
			//iconCls: 'edit',
			handler:function(){
				confirm("是否作废所有销售线路数据？",
						function(){
					var formUrl = 'salesLineAction!repealAll.action';
					var param = {};
					Ext.Msg.wait('作废中，请稍后...', '提示');
					    var returnSuc = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('querysalesLineGridPaginId').moveFirst();
							win.hide();
						};
						var returnFail = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('querysalesLineGridPaginId').moveFirst();
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
		       url : 'salesLineAction!implExcel.action',  
		       method : 'post',  
		       params : {  
		    	   'salesLineVo.filePath' : outFileName  
		          },  
		          success : function(response, options) {  
		             //隐藏进度条   
		               Ext.Msg.hide();   
		              var responseArray = Ext.util.JSON.decode(response.responseText); 
		              var msg = "";
		              if(responseArray.success){
		            	//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			              msg="本次导入"+responseArray.salesLineVo.sumSize+"条数据，新增数据"+responseArray.salesLineVo.addSize+"条，覆盖原数据"+responseArray.salesLineVo.coverSize+"条。"	
			              salesLineStore.reload();
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
			fieldLabel: 'slId',
			hidden:true,
			name:'slId',
			id:'slId',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			xtype:'numberfield',
			fieldLabel: '序号',
			height:15,
			maxLength:10,
			allowBlank:true,//判断不能为空
			name:'lineNum',
			id:'lineNum',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '备注',
			height:15,
			maxLength:25,
			allowBlank:true,//判断不能为空
			name:'note',
			id:'note',
		}
		,{
			fieldLabel: '销售线路',
			height:15,
			maxLength:25,
			allowBlank:false,//判断不能为空
			name:'salesLineName',
			id:'salesLineName',
		},{
			fieldLabel: '起运地',
			height:15,
			maxLength:10,
			allowBlank:false,//判断不能为空
			name:'loadingPort',
			id:'loadingPort',
		},{
			xtype:'numberfield',
			fieldLabel: '起运地编号',
			height:15,
			maxLength:25,
			allowBlank:false,//判断不能为空
			name:'loadingPortId',
			id:'loadingPortId',
		},{
			fieldLabel: '中转地1',
			height:15,
			maxLength:10,
			allowBlank:true,//判断不能为空
			name:'transitDepot1',
			id:'transitDepot1',
		},{
			fieldLabel: '中转地2',
			height:15,
			maxLength:10,
			allowBlank:true,//判断不能为空
			name:'transitDepot2',
			id:'transitDepot2',
		},{
			fieldLabel: '目的地',
			height:15,
			maxLength:10,
			allowBlank:false,//判断不能为空
			name:'termint',
			id:'termint',
		}
		,{
			xtype:'numberfield',
			fieldLabel: '目的地编号',
			height:15,
			maxLength:25,
			allowBlank:false,//判断不能为空
			name:'termintId',
			id:'termintId',
		},{
			fieldLabel: '依托1班次',
			height:15,
			maxLength:10,
			allowBlank:true,//判断不能为空
			name:'rely1Class',
			id:'rely1Class',
		}
		,{
			fieldLabel: '依托2班次',
			height:15,
			maxLength:10,
			allowBlank:true,
			name:'rely2Class',
			id:'rely2Class',
		},{
			fieldLabel: '依托3班次',
			height:15,
			maxLength:10,
			allowBlank:true,//判断不能为空
			name:'rely3Class',
			id:'rely3Class',
		},{
			fieldLabel: '线1',
			height:15,
			maxLength:25,
			allowBlank:true,
			name:'line1',
			id:'line1',
		},{
			fieldLabel: '线2',
			height:15,
			maxLength:25,
			allowBlank:true,//判断不能为空
			name:'line2',
			id:'line2',
		},
		{
			fieldLabel: '线3',
			height:15,
			maxLength:25,
			allowBlank:true,//判断不能为空
			name:'line3',
			id:'line3',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '无中转到车',
			allowBlank:true,//判断不能为空
			xtype : 'timefield',
			format : 'H:i',
			name:'noTransferArrival',
			id:'noTransferArrival',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '经一次中转终点到车',
			allowBlank:true,//判断不能为空
			xtype : 'timefield',
			format : 'H:i',
			name:'oneTransferArrival',
			id:'oneTransferArrival',
		},{
			fieldLabel: '经两次中转到车',
			allowBlank:true,//判断不能为空
			xtype : 'timefield',
			format : 'H:i',
			name:'twoTransferArrival',
			id:'twoTransferArrival',
		},{
			xtype:'numberfield',
			fieldLabel: '中停1天数',
			height:15,
			maxLength:5,
			allowBlank:true,//判断不能为空
			name:'breakOneDayTimes',
			id:'breakOneDayTimes',
		},{
			xtype:'numberfield',
			fieldLabel: '中停2天数',
			height:15,
			maxLength:5,
			allowBlank:true,//判断不能为空
			name:'breakTwoDaysTimes',
			id:'breakTwoDaysTimes',
		},{
			fieldLabel: '依托线1',
			height:15,
			maxLength:25,
			allowBlank:true,//判断不能为空
			name:'relyLine1',
			id:'relyLine1',
		},{
			fieldLabel: '依托线2',
			height:15,
			maxLength:25,
			allowBlank:true,//判断不能为空
			name:'relyLine2',
			id:'relyLine2',
		},{
			fieldLabel: '依托线3',
			height:15,
			maxLength:25,
			allowBlank:true,//判断不能为空
			name:'relyLine3',
			id:'relyLine3',
		},
		{
			fieldLabel: '发货当日(0)and次日(1)',
			name:'dispatchDayAndNextDayNum',
			id:'dispatchDayAndNextDayNum',
			xtype : 'combo',
            store: oneAndZero,
            displayField: "name",
            valueField: "value",
            queryMode: "local",
            autoSelect:true,
            forceSelection: true,
            allowBlank:false,//判断不能为空
            //value: 'Y', // 默认选中
		},
		 {
			fieldLabel: '起点一级发车时间',
			allowBlank:false,//判断不能为空
			xtype : 'timefield',
			format : 'H:i',
			name:'loadingPortFirstLevelDispa',
			id:'loadingPortFirstLevelDispa',
		},{
			fieldLabel: '中转地1发车时间',
			allowBlank:true,//判断不能为空
			xtype : 'timefield',
			format : 'H:i',
			name:'transitDepot1DispatchTime',
			id:'transitDepot1DispatchTime',
		},{
			fieldLabel: '中转地2发车时间',
			allowBlank:true,//判断不能为空
			xtype : 'timefield',
			format : 'H:i',
			name:'transitDepot2DispatchTime',
			id:'transitDepot2DispatchTime',
		},{
			fieldLabel: '中转地1到车时间',
			allowBlank:true,//判断不能为空
			xtype : 'timefield',
			format : 'H:i',
			name:'transitDepot1ArraivalTime',
			id:'transitDepot1ArraivalTime',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '中转地2到车时间',
			allowBlank:true,//判断不能为空
			xtype : 'timefield',
			format : 'H:i',
			name:'transitDepot2ArraivalTime',
			id:'transitDepot2ArraivalTime',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '终点一级到车时间',
			allowBlank:true,//判断不能为空
			xtype : 'timefield',
			format : 'H:i',
			name:'termitFirstArrivalTime',
			id:'termitFirstArrivalTime',
		},{
			xtype:'numberfield',
			fieldLabel: '在途小时1',
			height:15,
			maxLength:6,
			allowBlank:true,//判断不能为空
			name:'transitHours1',
			id:'transitHours1',
		},{
			xtype:'numberfield',
			allowBlank:true,//判断不能为空
			fieldLabel: '在途小时2',
			height:15,
			maxLength:6,
			name:'transitHours2',
			id:'transitHours2',
		},{
			xtype:'numberfield',
			allowBlank:true,//判断不能为空
			fieldLabel: '在途小时3',
			height:15,
			maxLength:6,
			name:'transitHours3',
			id:'transitHours3',
		},{
			xtype:'numberfield',
			fieldLabel: '中停小时1',
			height:15,
			maxLength:6,
			allowBlank:true,//判断不能为空
			name:'breakHours1',
			id:'breakHours1',
		},{
			xtype:'numberfield',
			fieldLabel: '中停小时2',
			height:15,
			maxLength:6,
			allowBlank:true,//判断不能为空
			name:'breakHours2',
			id:'breakHours2',
		},{
			xtype:'numberfield',
			fieldLabel: '总在途小时(H)',
			height:15,
			maxLength:6,
			allowBlank:true,//判断不能为空
			name:'hoursInTransit',
			id:'hoursInTransit',
		},{
			fieldLabel: '卸车时长(H)',
			height:15,
			maxLength:6,
			allowBlank:true,//判断不能为空
			name:'unloadHours',
			id:'unloadHours',
		},
		{
			fieldLabel: '终端服务长短线',
			name:'terminalShortLongLines',
			id:'terminalShortLongLines',
			xtype : 'combo',
            store: longAndShort,
            displayField: "name",
            valueField: "value",
            queryMode: "local",
            autoSelect:true,
            forceSelection: true,
            allowBlank:true,//判断不能为空
            //value: 'Y', // 默认选中
		},
		{
			fieldLabel: '到货当日(0)and次日(1)',
			name:'arrival0And1',
			id:'arrival0And1',
			xtype : 'combo',
            store: oneAndZero,
            displayField: "name",
            valueField: "value",
            allowBlank:false,//判断不能为空
            queryMode: "local",
            autoSelect:true,
            forceSelection: true
            //value: 'Y', // 默认选中
		},{
			fieldLabel: '开通月份',
			height:15,
			maxLength:10,
			allowBlank:true,
			name:'openMonth',
			id:'openMonth',
		},{
			fieldLabel: '线路状态',
			height:15,
			maxLength:10,
			allowBlank:true,
			name:'lineStatus',
			id:'lineStatus',
		},{
			xtype:'numberfield',
			fieldLabel: '提货时间(天)',
			allowBlank:true,
			maxLength:5,
			name:'pickupDays',
			id:'pickupDays',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			xtype:'numberfield',
			fieldLabel: '送货时间(天)',
			height:15,
			maxLength:5,
			allowBlank:true,
			name:'deliverDays',
			id:'deliverDays',
		},{
			fieldLabel: '时效备注',
			height:15,
			maxLength:25,
			allowBlank:true,
			name:'timelyNote',
			id:'timelyNote',
		},{
			fieldLabel: '开线年份',
			height:15,
			maxLength:10,
			allowBlank:true,
			name:'openLineYear',
			id:'openLineYear',
		},{
			fieldLabel: '是否黄金100条线路',
			xtype: "checkboxfield",
			allowBlank:true,
			inputValue: '1',
			name:'isOpenGolden100Lines',
			id:'isOpenGolden100Lines',
		},{
			fieldLabel: '标准备注',
			height:15,
			maxLength:25,
			allowBlank:true,
			name:'standardNote',
			id:'standardNote',
		},{
			fieldLabel: '线路合伙人',
			height:15,
			maxLength:25,
			allowBlank:true,
			name:'linePartner',
			id:'linePartner',
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
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
			name:'thusday',
			id:'thusday',
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
					saveUpTransferTimeInfo();
				}else{//表单验证不通过
					formCheck();
				}
			}
		}]
	});
	function saveUpTransferTimeInfo(){
		Ext.Msg.wait('保存中，请稍后...', '提示');
		//var msgWindow = null;
		var form = addForm.getForm();
		var formUrl;
		var param;
		switch (operateFlag) {
		case 1:
			formUrl = 'salesLineAction!addSalesLineInfo.action';
			param = {
					'salesLineVo':{
						'salesLine':{
							'lineNum':Ext.getCmp('lineNum').getValue(),
							'note':Ext.String.trim(Ext.getCmp('note').getValue()),
							'salesLineName':Ext.String.trim(Ext.getCmp('salesLineName').getValue()),
							'loadingPort':Ext.String.trim(Ext.getCmp('loadingPort').getValue()),
							'loadingPortId':Ext.getCmp('loadingPortId').getValue(),
							'transitDepot1':Ext.String.trim(Ext.getCmp('transitDepot1').getValue()),
							'transitDepot2':Ext.String.trim(Ext.getCmp('transitDepot2').getValue()),
							'termint':Ext.String.trim(Ext.getCmp('termint').getValue()),
							'termintId':Ext.getCmp('termintId').getValue(),
							'rely1Class':Ext.String.trim(Ext.getCmp('rely1Class').getValue()),
							'rely2Class':Ext.String.trim(Ext.getCmp('rely2Class').getValue()),
							'rely3Class':Ext.String.trim(Ext.getCmp('rely3Class').getValue()),
							'line1':Ext.String.trim(Ext.getCmp('line1').getValue()),
							'line2':Ext.String.trim(Ext.getCmp('line2').getValue()),
							'line3':Ext.String.trim(Ext.getCmp('line3').getValue()),
							'noTransferArrival':Ext.getCmp('noTransferArrival').getValue(),
							'oneTransferArrival':Ext.getCmp('oneTransferArrival').getValue(),
							'twoTransferArrival':Ext.getCmp('twoTransferArrival').getValue(),
							'breakOneDayTimes':Ext.getCmp('breakOneDayTimes').getValue(),
							'breakTwoDaysTimes':Ext.getCmp('breakTwoDaysTimes').getValue(),
							'relyLine1':Ext.String.trim(Ext.getCmp('relyLine1').getValue()),
							'relyLine2':Ext.String.trim(Ext.getCmp('relyLine2').getValue()),
							'relyLine3':Ext.String.trim(Ext.getCmp('relyLine3').getValue()),
							'dispatchDayAndNextDayNum':Ext.getCmp('dispatchDayAndNextDayNum').getValue(),
							'loadingPortFirstLevelDispa':Ext.getCmp('loadingPortFirstLevelDispa').getValue(),
							'transitDepot1DispatchTime':Ext.getCmp('transitDepot1DispatchTime').getValue(),
							'transitDepot2DispatchTime':Ext.getCmp('transitDepot2DispatchTime').getValue(),
							'transitDepot1ArraivalTime':Ext.getCmp('transitDepot1ArraivalTime').getValue(),
							'transitDepot2ArraivalTime':Ext.getCmp('transitDepot2ArraivalTime').getValue(),
							'termitFirstArrivalTime':Ext.getCmp('termitFirstArrivalTime').getValue(),
							'transitHours1':Ext.getCmp('transitHours1').getValue(),
							'transitHours2':Ext.getCmp('transitHours2').getValue(),
							'transitHours3':Ext.getCmp('transitHours3').getValue(),
							'breakHours1':Ext.getCmp('breakHours1').getValue(),
							'breakHours2':Ext.getCmp('breakHours2').getValue(),
							'hoursInTransit':Ext.getCmp('hoursInTransit').getValue(),
							'unloadHours':Ext.getCmp('unloadHours').getValue(),
							'terminalShortLongLines':Ext.String.trim(Ext.getCmp('terminalShortLongLines').getValue()),
							'arrival0And1':Ext.String.trim(Ext.getCmp('arrival0And1').getValue()),
							'openMonth':Ext.String.trim(Ext.getCmp('openMonth').getValue()),
							'lineStatus':Ext.String.trim(Ext.getCmp('lineStatus').getValue()),
							'pickupDays':Ext.getCmp('pickupDays').getValue(),
							'deliverDays':Ext.getCmp('deliverDays').getValue(),
							'monday':Ext.getCmp('monday').getValue() ?'1':'0',
							'tuesday':Ext.getCmp('tuesday').getValue() ?'1':'0',
							'wednesday':Ext.getCmp('wednesday').getValue() ?'1':'0',
							'thusday':Ext.getCmp('thusday').getValue() ?'1':'0',
							'friday':Ext.getCmp('friday').getValue() ?'1':'0',
							'saturday':Ext.getCmp('saturday').getValue() ?'1':'0',
							'sunday':Ext.getCmp('sunday').getValue() ?'1':'0',
							'timelyNote':Ext.String.trim(Ext.getCmp('timelyNote').getValue()),
							'openLineYear':Ext.String.trim(Ext.getCmp('openLineYear').getValue()),
							'isOpenGolden100Lines':Ext.getCmp('isOpenGolden100Lines').getValue()?'1':'0',
							'standardNote':Ext.String.trim(Ext.getCmp('standardNote').getValue()),
							'linePartner':Ext.String.trim(Ext.getCmp('linePartner').getValue())
						}
					}
			};
			break;
		case 2:
			formUrl = 'salesLineAction!modifySalesLineInfo.action';
			param = {
					'salesLineVo':{
						'salesLine':{
							'slId':Ext.String.trim(Ext.getCmp('slId').getValue()),
							'lineNum':Ext.getCmp('lineNum').getValue(),
							'note':Ext.String.trim(Ext.getCmp('note').getValue()),
							'salesLineName':Ext.String.trim(Ext.getCmp('salesLineName').getValue()),
							'loadingPort':Ext.String.trim(Ext.getCmp('loadingPort').getValue()),
							'loadingPortId':Ext.getCmp('loadingPortId').getValue(),
							'transitDepot1':Ext.String.trim(Ext.getCmp('transitDepot1').getValue()),
							'transitDepot2':Ext.String.trim(Ext.getCmp('transitDepot2').getValue()),
							'termint':Ext.String.trim(Ext.getCmp('termint').getValue()),
							'termintId':Ext.getCmp('termintId').getValue(),
							'rely1Class':Ext.String.trim(Ext.getCmp('rely1Class').getValue()),
							'rely2Class':Ext.String.trim(Ext.getCmp('rely2Class').getValue()),
							'rely3Class':Ext.String.trim(Ext.getCmp('rely3Class').getValue()),
							'line1':Ext.String.trim(Ext.getCmp('line1').getValue()),
							'line2':Ext.String.trim(Ext.getCmp('line2').getValue()),
							'line3':Ext.String.trim(Ext.getCmp('line3').getValue()),
							'noTransferArrival':Ext.getCmp('noTransferArrival').getValue(),
							'oneTransferArrival':Ext.getCmp('oneTransferArrival').getValue(),
							'twoTransferArrival':Ext.getCmp('twoTransferArrival').getValue(),
							'breakOneDayTimes':Ext.getCmp('breakOneDayTimes').getValue(),
							'breakTwoDaysTimes':Ext.getCmp('breakTwoDaysTimes').getValue(),
							'relyLine1':Ext.String.trim(Ext.getCmp('relyLine1').getValue()),
							'relyLine2':Ext.String.trim(Ext.getCmp('relyLine2').getValue()),
							'relyLine3':Ext.String.trim(Ext.getCmp('relyLine3').getValue()),
							'dispatchDayAndNextDayNum':Ext.getCmp('dispatchDayAndNextDayNum').getValue(),
							'loadingPortFirstLevelDispa':Ext.getCmp('loadingPortFirstLevelDispa').getValue(),
							'transitDepot1DispatchTime':Ext.getCmp('transitDepot1DispatchTime').getValue(),
							'transitDepot2DispatchTime':Ext.getCmp('transitDepot2DispatchTime').getValue(),
							'transitDepot1ArraivalTime':Ext.getCmp('transitDepot1ArraivalTime').getValue(),
							'transitDepot2ArraivalTime':Ext.getCmp('transitDepot2ArraivalTime').getValue(),
							'termitFirstArrivalTime':Ext.getCmp('termitFirstArrivalTime').getValue(),
							'transitHours1':Ext.getCmp('transitHours1').getValue(),
							'transitHours2':Ext.getCmp('transitHours2').getValue(),
							'transitHours3':Ext.getCmp('transitHours3').getValue(),
							'breakHours1':Ext.getCmp('breakHours1').getValue(),
							'breakHours2':Ext.getCmp('breakHours2').getValue(),
							'hoursInTransit':Ext.getCmp('hoursInTransit').getValue(),
							'unloadHours':Ext.getCmp('unloadHours').getValue(),
							'terminalShortLongLines':Ext.String.trim(Ext.getCmp('terminalShortLongLines').getValue()),
							'arrival0And1':Ext.String.trim(Ext.getCmp('arrival0And1').getValue()),
							'openMonth':Ext.String.trim(Ext.getCmp('openMonth').getValue()),
							'lineStatus':Ext.String.trim(Ext.getCmp('lineStatus').getValue()),
							'pickupDays':Ext.getCmp('pickupDays').getValue(),
							'deliverDays':Ext.getCmp('deliverDays').getValue(),
							'monday':Ext.getCmp('monday').getValue() ?'1':'0',
							'tuesday':Ext.getCmp('tuesday').getValue() ?'1':'0',
							'wednesday':Ext.getCmp('wednesday').getValue() ?'1':'0',
							'thusday':Ext.getCmp('thusday').getValue() ?'1':'0',
							'friday':Ext.getCmp('friday').getValue() ?'1':'0',
							'saturday':Ext.getCmp('saturday').getValue() ?'1':'0',
							'sunday':Ext.getCmp('sunday').getValue() ?'1':'0',
							'timelyNote':Ext.String.trim(Ext.getCmp('timelyNote').getValue()),
							'openLineYear':Ext.String.trim(Ext.getCmp('openLineYear').getValue()),
							'isOpenGolden100Lines':Ext.getCmp('isOpenGolden100Lines').getValue()?'1':'0',
							'standardNote':Ext.String.trim(Ext.getCmp('standardNote').getValue()),
							'linePartner':Ext.String.trim(Ext.getCmp('linePartner').getValue())
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
				Ext.getCmp('querysalesLineGridPaginId').moveFirst();
				win.hide();
			}else{
			}
		};
		var returnFail = function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('querysalesLineGridPaginId').moveFirst();
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
		items: [queryForm,salesLineGrid]
	});
});