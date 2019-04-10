/**主界面->下转准点率目标值模型*/
Ext.define('Ty.Report.DownTransferOnTimeRate.DownTransferOnTimeRateModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// ID
		name : 'downTransferOnTimeRateId'
	},
	{// 部门
		name : 'department'
	},
	{// 目标值
		name : 'targetValue'
	},
	{//目标值所属月份
		name : 'targetValueMonth'
	},
	{// 导入时间
		name : 'importTime',
			type : 'date',
			dateFormat : 'time',
	},
	{// 负责人
		name : 'manager'
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
	{// 上月完成值
		name : 'lastMonthFinishValue',
	}]
});

/***主界面->下转准点率目标值store*/
Ext.define('Ty.Report.DownTransferOnTimeRate.DownTransferOnTimeRateStore', {
	extend : 'Ext.data.Store',
	model : 'Ty.Report.DownTransferOnTimeRate.DownTransferOnTimeRateModel',
	pageSize : 100,// 分页条数
	proxy : {
		type : 'ajax',
		url : 'downTransferOnTimeRateManageAction!queryDownTransferOnTimeRate.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'downTransferOnTimeRateVo.downTransferOnTimeRates',
			totalProperty : 'totalCount'
		}
	},
	listeners : {
		//在加载数据之前操作
		beforeload : function(store, operation, eOpts) {
				var params = {
						'downTransferOnTimeRateVo.downTransferOnTimeRate.department':Ext.String.trim(Ext.getCmp('department').getValue()),
						'downTransferOnTimeRateVo.downTransferOnTimeRate.targetValueMonth':Ext.String.trim(Ext.getCmp('targetValueMonth').getValue()),
						'downTransferOnTimeRateVo.downTransferOnTimeRate.manager':Ext.String.trim(Ext.getCmp('manager').getValue()),
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
				fieldLabel: '部门',
				id : 'department',
				name:'department',
			},{
				fieldLabel: '所属月份',
				id : 'targetValueMonth',
				name:'targetValueMonth',
			},{
				fieldLabel: '负责人',
				id : 'manager',
				name:'manager',
			},
			{
				xtype:'button',
				text:'查询',
				width:60,
				border : true,
				handler:function(){
					downTransferOnTimeRateStore.reload();  //加载数据
					//stationAreaStore.loadPage(1);	//查询后默认返回第一页
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
		var downTransferOnTimeRateStore=Ext.create('Ty.Report.DownTransferOnTimeRate.DownTransferOnTimeRateStore',{
			id:'downTransferOnTimeRateStoreId'
		});

		/**
		 * 主界面->表格
		 */
		var  downTransferOnTimeRateGrid =Ext.create('Ext.grid.Panel', {
			store: downTransferOnTimeRateStore,
			border:false,
			columnLines:true,
			selModel:Ext.create('Ext.selection.CheckboxModel', {
				listeners : {
					//行选中事件
					selectionchange : function(sm, selections) {
					}
				}
			}),//checkbox
			region:'center',//border布局的中间
			columns: [//列
			{xtype: 'rownumberer',
				width:40},
	        {
	            dataIndex: 'department',
				text:'部门',
	            width:150,
	            menuDisabled:true
	        },{
	            dataIndex: 'targetValue',
				text:'目标值',
	            width:145,
	            menuDisabled:true,
	            renderer: function(value) {
	            	if(value == '0'){
	            		return value;
	            	}else{
	            		return Ext.util.Format.number(value * 100,'0.0')+"%";
	            	}
	            }
	        },{
	            dataIndex: 'lastMonthFinishValue',
				text:'上月完成值',
	            width:145,
	            menuDisabled:true,
	            renderer: function(value) {
	            	if(value == '0'){
	            		return value;
	            	}else{
	            		return Ext.util.Format.number(value * 100,'0.0')+"%";
	            	}
		        }
	        },{
	            dataIndex: 'targetValueMonth',
				text:'所属月份',
				width:100,
				 menuDisabled:true
	        },{
	            dataIndex: 'importTime',
				text:'导入时间',
				 width:150,
				 menuDisabled:true,
				 renderer: function(value) {
		           return dateRender(value,'Y-m-d H:i:s');
		         }
	        },{
	            dataIndex: 'manager',
				text:'负责人',
				 width:145,
				 menuDisabled:true,
	        }],
	        listeners : {
				itemClick : function() {

				}
			},
			 dockedItems:[{//分页
		 			xtype:'pagingtoolbar',
		 			id: 'queryDownTransferOnTimeRateGridPaginId',
		 			store:downTransferOnTimeRateStore,
		 			dock:'bottom',
		 			displayInfo : true,
		 			autoScroll : true
		 		}],	
	        tbar:[{
				xtype:'button',
				text:'导入',
				//iconCls: 'add',
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
			       url : 'downTransferOnTimeRateManageAction!implExcel.action',  
			       method : 'post',  
			       params : {  
			    	   'downTransferOnTimeRateVo.filePath' : outFileName  
			          },  
			          success : function(response, options) {  
			             //隐藏进度条   
			               Ext.Msg.hide();   
			              var responseArray = Ext.util.JSON.decode(response.responseText); 
			              var msg = "";
			              if(responseArray.success){
			            	//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
				              msg="本次导入"+responseArray.downTransferOnTimeRateVo.sumSize+"条数据，新增待生效数据"+responseArray.downTransferOnTimeRateVo.addSize+"条，覆盖原待生效数据"+responseArray.downTransferOnTimeRateVo.coverSize+"条。"
				              downTransferOnTimeRateStore.reload();
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
	//整个页面布局
		var viewport = Ext.create('Ext.Viewport', {
			layout: {
				type: 'border',//用border布局 ，把页面分为上下部分 form为上、grid为下
				padding: 5
			},
			items: [queryForm , downTransferOnTimeRateGrid]
		});
});





