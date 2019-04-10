
/**主界面->城市价卡模型*/
Ext.define('Ty.Report.CityPriceCard.CityPriceCardModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// 城市价卡ID
		name : 'cityPriceCardId'
	},
	 {
		// 线路
		name : 'lineName'
	},
	 {
		// 起运地
		name : 'loadingPort'
	},
	 {
		// 目的地
		name : 'termini'
	},
	 {
		// 城市线路
		name : 'cityLine'
	},
	 {
		// 发货城市
		name : 'dispatchCity'
	},
	 {
		// 发货城市编码
		name : 'dispatchCityId'
	},
	 {
		//到货城市
		name : 'arrivalCity'
	},
	 {
		// 到货城市编码
		name : 'arrivalCityId'
	},
	 {
		// 发货是否提供定日达
		name : 'serveLoadIntime'
	},
	 {
		// 发货是否偏远网点
		name : 'fromRemoteBranch'
	},
	 {
		// 到货是否提供定日达
		name : 'serveReachIntime'
	},
	 {
		// 到货是否偏远网点
		name : 'toRemoteBranch'
	},
	 {
		// 通知承诺时间
		name : 'promiseNoteTime'
	},
	 {
		// 送货承诺时间
		name : 'promiseDeliverTime'
	},
	 {
		// 开通月份
		name : 'openMonth'
	},
	 {
		// 星期一
		name : 'monday'
	},
	 {
		// 星期二
		name : 'tuesday'
	},
	 {
		// 星期三
		name : 'wednesday'
	},
	 {
		// 星期四
		name : 'thursday'
	},
	 {
		// 星期五
		name : 'friday'
	},
	 {
		// 星期六
		name : 'saturday'
	},
	 {
		// 星期日
		name : 'sunday'
	},
	 {
		// 备注
		name : 'note'
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

/***主界面->城市价卡store*/
Ext.define('Ty.Report.CityPriceCard.CityPriceCardStore', {
	extend : 'Ext.data.Store',
	model : 'Ty.Report.CityPriceCard.CityPriceCardModel',
	pageSize : 100,// 分页条数
	proxy : {
		type : 'ajax',
		url : 'cityPriceCardManageAction!queryCityPriceCard.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'cityPriceCardVo.cityPriceCardList',
			totalProperty : 'totalCount'
		}
	},
	listeners : {
		//在加载数据之前操作
		beforeload : function(store, operation, eOpts) {
				var params = {
						'cityPriceCardVo.cityPriceCard.dispatchCity':Ext.String.trim(Ext.getCmp('dispatchCity').getValue()),
						'cityPriceCardVo.cityPriceCard.arrivalCity':Ext.String.trim(Ext.getCmp('arrivalCity').getValue()),
						'cityPriceCardVo.cityPriceCard.active':Ext.String.trim(Ext.getCmp('active').getValue()),
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

	 /**主界面->发货是否提供定日达 模型*/
	 Ext.define("serveLoadIntimeModel", {
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
	  * 主界面->发货是否提供定日达 store
	  */
	 var serveLoadIntimeStore = Ext.create('Ext.data.Store', {
	 	model: "serveLoadIntimeModel",
	    data : [
	             {'name':'是','value':'1'},
	             {'name':'否','value':'0'}
	            ]
	 });
	 
	 /**主界面->发货是否偏远网点 模型*/
	 Ext.define("fromRemoteBranchModel", {
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
	  * 主界面->发货是否偏远网点 store
	  */
	 var fromRemoteBranchStore = Ext.create('Ext.data.Store', {
	 	model: "fromRemoteBranchModel",
	    data : [
	             {'name':'是','value':'1'},
	             {'name':'否','value':'0'}
	            ]
	 });
	 
	 /**主界面->到货是否提供定日达 模型*/
	 Ext.define("serveReachIntimeModel", {
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
	  * 主界面->到货是否提供定日达 store
	  */
	 var serveReachIntimeStore = Ext.create('Ext.data.Store', {
	 	model: "serveReachIntimeModel",
	    data : [
	             {'name':'是','value':'1'},
	             {'name':'否','value':'0'}
	            ]
	 });
	 
	 /**主界面->到货是否偏远网点 模型*/
	 Ext.define("toRemoteBranchModel", {
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
	  * 主界面->到货是否偏远网点 store
	  */
	 var toRemoteBranchStore = Ext.create('Ext.data.Store', {
	 	model: "toRemoteBranchModel",
	    data : [
	             {'name':'是','value':'1'},
	             {'name':'否','value':'0'}
	            ]
	 });
	 
	
	 
	 /**主界面->城市价卡表单*/
	 var queryForm = Ext.create('Ext.form.Panel',{
			layout : {
				type : 'table',	//table布局
				columns : 5	//列数
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
				fieldLabel: '发货城市',
				id : 'dispatchCity',
				name:'dispatchCity'
			},{
				fieldLabel: '到货城市',
				id : 'arrivalCity',
				name:'arrivalCity'
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
					cityPriceCardStore.reload();
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
	var cityPriceCardStore=Ext.create('Ty.Report.CityPriceCard.CityPriceCardStore',{
		id:'cityPriceCardStoreId'
	});

	/**
	 * 主界面->表格
	 */
	var cityPriceCardGrid =Ext.create('Ext.grid.Panel', {
		store: cityPriceCardStore,//创建querySqlStore
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
            dataIndex: 'lineName',
			text:'线路',
            width:125,
            menuDisabled:true
        },
        {
            dataIndex: 'loadingPort',
			text:'起运地',
            width:125,
            menuDisabled:true
        },
        {
            dataIndex: 'termini',
			text:'目的地',
            width:125,
            menuDisabled:true
        },
        {
            dataIndex: 'cityLine',
			text:'城市线路',
            width:125,
            menuDisabled:true
        },
        {
            dataIndex: 'dispatchCity',
			text:'发货城市',
            width:125,
            menuDisabled:true
        },
        {
            dataIndex: 'dispatchCityId',
			text:'发货城市编码',
            width:125,
            menuDisabled:true
        },
        {
            dataIndex: 'arrivalCity',
			text:'到货城市',
            width:125,
            menuDisabled:true
        },
        {
            dataIndex: 'arrivalCityId',
			text:'到货城市编码',
            width:125,
            menuDisabled:true
        },
        {
            dataIndex: 'serveLoadIntime',
			text:'发货是否提供定日达',
			 width:157,
			 menuDisabled:true,
			 renderer: function(value) {
				    if(value=='1'){
						return '是';
					}else if(value=='0'){
						return '否';
					}
	         }
        },
        {
            dataIndex: 'fromRemoteBranch',
			text:'发货是否偏远网点',
			 width:157,
			 menuDisabled:true,
			 renderer: function(value) {
				    if(value=='1'){
						return '是';
					}else if(value=='0'){
						return '否';
					}
	         }
        },
        {
            dataIndex: 'serveReachIntime',
			text:'到货是否提供定日达',
			 width:157,
			 menuDisabled:true,
			 renderer: function(value) {
				    if(value=='1'){
						return '是';
					}else if(value=='0'){
						return '否';
					}
	         }
        },
        {
            dataIndex: 'toRemoteBranch',
			text:'到货是否偏远网点',
			 width:157,
			 menuDisabled:true,
			 renderer: function(value) {
				    if(value=='1'){
						return '是';
					}else if(value=='0'){
						return '否';
					}
	         }
        },
        {
            dataIndex: 'promiseNoteTime',
			text:'通知承诺时间',
            width:125,
            menuDisabled:true
        },
        {
            dataIndex: 'promiseDeliverTime',
			text:'送货承诺时间',
            width:125,
            menuDisabled:true
        },
        {
            dataIndex: 'openMonth',
			text:'开通月份',
            width:125,
            menuDisabled:true
        },
        {
            dataIndex: 'monday',
			text:'星期一',
			 width:125,
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
            dataIndex: 'tuesday',
			text:'星期二',
			 width:125,
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
            dataIndex: 'wednesday',
			text:'星期三',
			 width:125,
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
            dataIndex: 'thursday',
			text:'星期四',
			 width:125,
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
            dataIndex: 'friday',
			text:'星期五',
			 width:125,
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
            dataIndex: 'saturday',
			text:'星期六',
			 width:125,
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
            dataIndex: 'sunday',
			text:'星期日',
			 width:125,
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
            dataIndex: 'note',
			text:'备注',
            width:125,
            menuDisabled:true
        },
        {
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
	 			id: 'queryCityPriceCardGridPaginId',
	 			store:cityPriceCardStore,
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
				var records = cityPriceCardGrid.getSelectionModel().getSelection();//获取选中集合
				if(records.length == 0){
					showInfoMsg("请选择一条记录！");
					return false;
				};
				if(records.length != 1){
					showInfoMsg("只能选择一条记录！");
					return false;
				};
				var form = addForm.getForm();
				var CityPriceCardModel = new Ty.Report.CityPriceCard.CityPriceCardModel();
				form.loadRecord(CityPriceCardModel);
				form.findField("cityPriceCardId").enable();//修改的时候id为启用
				form.findField("cityPriceCardId").setValue(records[0].get("cityPriceCardId"));
				
				form.findField("lineName").setValue(records[0].get("lineName"));
				form.findField("loadingPort").setValue(records[0].get("loadingPort"));
				form.findField("termini").setValue(records[0].get("termini"));
				form.findField("cityLine").setValue(records[0].get("cityLine"));
				form.findField("dispatchCity").setValue(records[0].get("dispatchCity"));
				form.findField("dispatchCityId").setValue(records[0].get("dispatchCityId"));
				form.findField("arrivalCity").setValue(records[0].get("arrivalCity"));
				form.findField("arrivalCityId").setValue(records[0].get("arrivalCityId"));
				form.findField("serveLoadIntime").setValue(records[0].get("serveLoadIntime"));
				form.findField("fromRemoteBranch").setValue(records[0].get("fromRemoteBranch"));
				form.findField("serveReachIntime").setValue(records[0].get("serveReachIntime"));
				form.findField("toRemoteBranch").setValue(records[0].get("toRemoteBranch"));
				form.findField("promiseNoteTime").setValue(records[0].get("promiseNoteTime"));
				form.findField("promiseDeliverTime").setValue(records[0].get("promiseDeliverTime"));
				form.findField("openMonth").setValue(records[0].get("openMonth"));
				form.findField("monday").setValue(records[0].get("monday"));
				form.findField("tuesday").setValue(records[0].get("tuesday"));
				form.findField("wednesday").setValue(records[0].get("wednesday"));
				form.findField("thursday").setValue(records[0].get("thursday"));
				form.findField("friday").setValue(records[0].get("friday"));
				form.findField("saturday").setValue(records[0].get("saturday"));
				form.findField("sunday").setValue(records[0].get("sunday"));
				form.findField("note").setValue(records[0].get("note"));
				
				
				form.updateRecord(CityPriceCardModel);
				win.setTitle("数据修改");
				win.show();
			}
		},'-',{//提示 '->' 让按钮居右
			id:'repealBtn',
			xtype:'button',
			text:'作废',
			//iconCls: 'edit',
			handler:function(){
				var records = cityPriceCardGrid.getSelectionModel().getSelection();//获取选中集合
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
					var formUrl = 'cityPriceCardManageAction!repealCityPriceCard.action';
					var cityPriceCardArray = [];
					for(var i = 0; i < records.length; i++){
						var curItem = records[i];
						var curItemData = curItem.data;
						curItemData.id = "";
						cityPriceCardArray.push(curItemData);
					}
					var param = {
								'cityPriceCardVo':{
									'cityPriceCardList': cityPriceCardArray
								}
							};
					Ext.Msg.wait('保存中，请稍后...', '提示');
					    var returnSuc = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryCityPriceCardGridPaginId').moveFirst();
							win.hide();
						};
						var returnFail = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryCityPriceCardGridPaginId').moveFirst();
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
				confirm("是否作废所有城市价卡信息数据？",
						function(){
					var formUrl = 'cityPriceCardManageAction!repealAll.action';
					var param = {};
					Ext.Msg.wait('作废中，请稍后...', '提示');
					    var returnSuc = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryCityPriceCardGridPaginId').moveFirst();
							win.hide();
						};
						var returnFail = function(json){
							Ext.Msg.hide();
							showInfoMsg(json.message);
							Ext.getCmp('queryCityPriceCardGridPaginId').moveFirst();
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
		       url : 'cityPriceCardManageAction!implExcel.action',  
		       method : 'post',  
		       params : {  
		    	   'cityPriceCardVo.filePath' : outFileName  
		          },  
		          success : function(response, options) {  
		             //隐藏进度条   
		               Ext.Msg.hide();   
		              var responseArray = Ext.util.JSON.decode(response.responseText); 
		              var msg = "";
		              if(responseArray.success){
		            	//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			              msg="本次导入"+responseArray.cityPriceCardVo.sumSize+"条数据，新增数据"+responseArray.cityPriceCardVo.addSize+"条，覆盖原数据"+responseArray.cityPriceCardVo.coverSize+"条。"	
							cityPriceCardStore.reload();
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
			columns : 2	//列数
		},
		defaults: {//统一设置宽、居右
			margin:'5 25 5 5',//间距
			labelWidth:250,
			labelStyle : "text-align:left;width:130;",  
			border:false,
			labelAlign: 'left',
			//width : 200,
			//height:  100,
		},
		defaultType:'textfield',//默认类型
		frame:true,
		height:180,
		region:'center',
		buttonAlign:'center',
		items:[{
			fieldLabel: 'cityPriceCardId',
			hidden:true,
			name:'cityPriceCardId',
			id:'cityPriceCardIdFiled',
		},{
			fieldLabel: '线路',
			width:250,
			height:15,
			maxLength:20,
			allowBlank:false,//判断不能为空
			name:'lineName',
			id:'lineNameFiled',
		},{
			fieldLabel: '起运地',
			width:250,
			height:15,
			maxLength:10,
			allowBlank:false,//判断不能为空
			name:'loadingPort',
			id:'loadingPortFiled',
		},{
			fieldLabel: '目的地',
			width:250,
			height:15,
			maxLength:10,
			allowBlank:false,//判断不能为空
			name:'termini',
			id:'terminiFiled',
		},{
			fieldLabel: '城市线路',
			width:250,
			height:15,
			maxLength:60,
			allowBlank:false,//判断不能为空
			name:'cityLine',
			id:'cityLineFiled',
		},{
			fieldLabel: '发货城市',
			width:250,
			height:15,
			maxLength:10,
			allowBlank:false,//判断不能为空
			name:'dispatchCity',
			id:'dispatchCityFiled',
		},{
			fieldLabel: '发货城市编码',
			width:250,
			height:15,
			maxLength:10,
			allowBlank:false,//判断不能为空
			regex: /^\d+$/, //数字
            regexText: '只能输入数字',
			name:'dispatchCityId',
			id:'dispatchCityIdFiled',
		},{
			fieldLabel: '到货城市',
			width:250,
			height:15,
			maxLength:10,
			allowBlank:false,//判断不能为空
			name:'arrivalCity',
			id:'arrivalCityFiled',
		},{
			fieldLabel: '到货城市编码',
			width:250,
			height:15,
			maxLength:10,
			allowBlank:false,//判断不能为空
			regex: /^\d+$/, //数字
            regexText: '只能输入数字',
			name:'arrivalCityId',
			id:'arrivalCityIdFiled',
		},{
			fieldLabel: '发货是否提供定日达',
			width:250,
			height:15,
			allowBlank:false,//判断不能为空
			name:'serveLoadIntime',
			id:'serveLoadIntimeFiled',
			xtype : 'combo',
			store: serveLoadIntimeStore,
            displayField: "name",
            valueField: "value",
            queryMode: "local",
            autoSelect:true,
            forceSelection: true,
            value: '1', // 默认是
		},{
			fieldLabel: '发货是否偏远网点',
			width:250,
			height:15,
			allowBlank:false,//判断不能为空
			name:'fromRemoteBranch',
			id:'fromRemoteBranchFiled',
			xtype : 'combo',
			store: fromRemoteBranchStore,
            displayField: "name",
            valueField: "value",
            queryMode: "local",
            autoSelect:true,
            forceSelection: true,
            value: '1', // 默认是
		},{
			fieldLabel: '到货是否提供定日达',
			width:250,
			height:15,
			allowBlank:false,//判断不能为空
			name:'serveReachIntime',
			id:'serveReachIntimeFiled',
			xtype : 'combo',
			store: serveReachIntimeStore,
            displayField: "name",
            valueField: "value",
            queryMode: "local",
            autoSelect:true,
            value: '1', // 默认是
		},{
			fieldLabel: '到货是否偏远网点',
			width:250,
			height:15,
			allowBlank:false,//判断不能为空
			name:'toRemoteBranch',
			id:'toRemoteBranchFiled',
			xtype : 'combo',
			store: toRemoteBranchStore,
            displayField: "name",
            valueField: "value",
            queryMode: "local",
            autoSelect:true,
            value: '1', // 默认是
		},{
			fieldLabel: '通知承诺时间',
			width:250,
			height:15,
			xtype:'numberfield',
			maxLength:10,
			minValue:0,    //最小值
			allowDecimals:false,  //不允许输入小数
			allowBlank:false,//判断不能为空
			name:'promiseNoteTime',
			id:'promiseNoteTimeFiled',
		},{
			fieldLabel: '送货承诺时间',
			width:250,
			height:15,
			xtype:'numberfield',
			maxLength:10,
			minValue:0,    //最小值
			allowDecimals:false,  //不允许输入小数
			allowBlank:false,//判断不能为空
			name:'promiseDeliverTime',
			id:'promiseDeliverTimeFiled',
		},{
			fieldLabel: '开通月份',
			width:250,
			height:15,
			maxLength:6,
			allowBlank:false,//判断不能为空
			regex: /\d{6}/, //为6位数字
            regexText: '请输入正确的月份，如“201601”',
			name:'openMonth',
			id:'openMonthFiled',
		},{
			fieldLabel: '星期一',
			width:250,
			height:15,
			xtype: "checkboxfield",
			name:'monday',
			id:'mondayFiled',
		},{
			fieldLabel: '星期二',
			width:250,
			height:15,
			xtype: "checkboxfield",
			name:'tuesday',
			id:'tuesdayFiled',
		},{
			fieldLabel: '星期三',
			width:250,
			height:15,
			xtype: "checkboxfield",
			name:'wednesday',
			id:'wednesdayFiled',
		},{
			fieldLabel: '星期四',
			width:250,
			height:15,
			xtype: "checkboxfield",
			name:'thursday',
			id:'thursdayFiled',
		},{
			fieldLabel: '星期五',
			width:250,
			height:15,
			xtype: "checkboxfield",
			name:'friday',
			id:'fridayFiled',
		},{
			fieldLabel: '星期六',
			width:250,
			height:15,
			xtype: "checkboxfield",
			name:'saturday',
			id:'saturdayFiled',
		},{
			fieldLabel: '星期日',
			width:250,
			height:15,
			xtype: "checkboxfield",
			name:'sunday',
			id:'sundayFiled',
		},{
			fieldLabel: '备注',
			width:500,
			height:15,
			maxLength:40,
			colspan:2, 
			name:'note',
			id:'noteFiled',
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
					saveCityPriceCardInfo();
					cityPriceCardStore.reload();
				}else{//表单验证不通过
					formCheck();
				}
			}
		}]
	});
	function saveCityPriceCardInfo(){
		Ext.Msg.wait('保存中，请稍后...', '提示');
		//var msgWindow = null;
		var form = addForm.getForm();
		var formUrl;
		var param;
		switch (operateFlag) {
		case 1:
			formUrl = 'cityPriceCardManageAction!addCityPriceCard.action';
			param = {
					'cityPriceCardVo':{
						'cityPriceCard':{
							'lineName':Ext.String.trim(Ext.getCmp('lineNameFiled').getValue()),
							'loadingPort':Ext.String.trim(Ext.getCmp('loadingPortFiled').getValue()),
							'termini':Ext.String.trim(Ext.getCmp('terminiFiled').getValue()),
							'cityLine':Ext.String.trim(Ext.getCmp('cityLineFiled').getValue()),
							'dispatchCity':Ext.String.trim(Ext.getCmp('dispatchCityFiled').getValue()),
							'dispatchCityId':Ext.String.trim(Ext.getCmp('dispatchCityIdFiled').getValue()),
							'arrivalCity':Ext.String.trim(Ext.getCmp('arrivalCityFiled').getValue()),
							'arrivalCityId':Ext.String.trim(Ext.getCmp('arrivalCityIdFiled').getValue()),
							'serveLoadIntime':Ext.String.trim(Ext.getCmp('serveLoadIntimeFiled').getValue()),
							'fromRemoteBranch':Ext.String.trim(Ext.getCmp('fromRemoteBranchFiled').getValue()),
							'serveReachIntime':Ext.String.trim(Ext.getCmp('serveReachIntimeFiled').getValue()),
							'toRemoteBranch':Ext.String.trim(Ext.getCmp('toRemoteBranchFiled').getValue()),
							'promiseNoteTime':Ext.getCmp('promiseNoteTimeFiled').getValue(),
							'promiseDeliverTime':Ext.getCmp('promiseDeliverTimeFiled').getValue(),
							'openMonth':Ext.String.trim(Ext.getCmp('openMonthFiled').getValue()),
							'monday':Ext.getCmp('mondayFiled').getValue()?'1':'0',
							'tuesday':Ext.getCmp('tuesdayFiled').getValue()?'1':'0',
							'wednesday':Ext.getCmp('wednesdayFiled').getValue()?'1':'0',
							'thursday':Ext.getCmp('thursdayFiled').getValue()?'1':'0',
							'friday':Ext.getCmp('fridayFiled').getValue()?'1':'0',
							'saturday':Ext.getCmp('saturdayFiled').getValue()?'1':'0',
							'sunday':Ext.getCmp('sundayFiled').getValue()?'1':'0',
							'note':Ext.String.trim(Ext.getCmp('noteFiled').getValue()),
						}
					}
			};
			break;
		case 2:
			formUrl = 'cityPriceCardManageAction!modifyCityPriceCard.action';
			param = {
					'cityPriceCardVo':{
						'cityPriceCard':{
						    'cityPriceCardId':Ext.String.trim(Ext.getCmp('cityPriceCardIdFiled').getValue()),
							'lineName':Ext.String.trim(Ext.getCmp('lineNameFiled').getValue()),
							'loadingPort':Ext.String.trim(Ext.getCmp('loadingPortFiled').getValue()),
							'termini':Ext.String.trim(Ext.getCmp('terminiFiled').getValue()),
							'cityLine':Ext.String.trim(Ext.getCmp('cityLineFiled').getValue()),
							'dispatchCity':Ext.String.trim(Ext.getCmp('dispatchCityFiled').getValue()),
							'dispatchCityId':Ext.String.trim(Ext.getCmp('dispatchCityIdFiled').getValue()),
							'arrivalCity':Ext.String.trim(Ext.getCmp('arrivalCityFiled').getValue()),
							'arrivalCityId':Ext.String.trim(Ext.getCmp('arrivalCityIdFiled').getValue()),
							'serveLoadIntime':Ext.String.trim(Ext.getCmp('serveLoadIntimeFiled').getValue()),
							'fromRemoteBranch':Ext.String.trim(Ext.getCmp('fromRemoteBranchFiled').getValue()),
							'serveReachIntime':Ext.String.trim(Ext.getCmp('serveReachIntimeFiled').getValue()),
							'toRemoteBranch':Ext.String.trim(Ext.getCmp('toRemoteBranchFiled').getValue()),
							'promiseNoteTime':Ext.getCmp('promiseNoteTimeFiled').getValue(),
							'promiseDeliverTime':Ext.getCmp('promiseDeliverTimeFiled').getValue(),
							'openMonth':Ext.String.trim(Ext.getCmp('openMonthFiled').getValue()),
							'monday':Ext.getCmp('mondayFiled').getValue()?'1':'0',
							'tuesday':Ext.getCmp('tuesdayFiled').getValue()?'1':'0',
							'wednesday':Ext.getCmp('wednesdayFiled').getValue()?'1':'0',
							'thursday':Ext.getCmp('thursdayFiled').getValue()?'1':'0',
							'friday':Ext.getCmp('fridayFiled').getValue()?'1':'0',
							'saturday':Ext.getCmp('saturdayFiled').getValue()?'1':'0',
							'sunday':Ext.getCmp('sundayFiled').getValue()?'1':'0',
							'note':Ext.String.trim(Ext.getCmp('noteFiled').getValue()),
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
				Ext.getCmp('queryCityPriceCardGridPaginId').moveFirst();
				win.hide();
			}else{
				
			}
		};
		var returnFail = function(json){
			Ext.Msg.hide();
			showInfoMsg(json.message);
			Ext.getCmp('queryCityPriceCardGridPaginId').moveFirst();
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
			width:580,
			height:510,
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
		items: [queryForm,cityPriceCardGrid]
	});
	
});