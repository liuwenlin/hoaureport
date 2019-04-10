
/**
 * 查询SQL报表Model
 */
Ext.define('QuerySqlModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id'},//ID  
        {name: 'createDate',type:'date',dateFormat:'time'},//日期
        {name: 'number'},//SQL编号
        {name: 'sql'},//供应商名称
        {name: 'tableHead'},//表头
        {name: 'param'},//sql参数
        {name: 'myColumn'},//表头结果集 json格式字符串
        {name: 'remark'},//备注sql功能
        {name: 'queryRule'} // 取数规则
   
    ]
});

/**
 * 动态执行sql获取结果集model
 * */
Ext.define('QueryResultModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'str0'},
        {name: 'str1'}, 
        {name: 'str2'},   
        {name: 'str3'},   
        {name: 'str4'},   
        {name: 'str5'},   
        {name: 'str6'},   
        {name: 'str7'},   
        {name: 'str8'},   
        {name: 'str9'},   
        {name: 'str10'},   
        {name: 'str11'},   
        {name: 'str12'},   
        {name: 'str13'},   
        {name: 'str14'},   
        {name: 'str15'},   
        {name: 'str16'},   
        {name: 'str17'},   
        {name: 'str18'},   
        {name: 'str19'},
        {name: 'str20'},   
        {name: 'str21'},   
        {name: 'str22'},   
        {name: 'str23'},   
        {name: 'str24'},   
        {name: 'str25'},   
        {name: 'str26'},   
        {name: 'str27'},   
        {name: 'str28'},   
        {name: 'str29'},
        {name: 'str30'},   
        {name: 'str31'},   
        {name: 'str32'},   
        {name: 'str33'},   
        {name: 'str34'},   
        {name: 'str35'},   
        {name: 'str36'},   
        {name: 'str37'},   
        {name: 'str38'},   
        {name: 'str39'},
        {name: 'str40'},   
        {name: 'str41'},   
        {name: 'str42'},   
        {name: 'str43'},   
        {name: 'str44'},   
        {name: 'str45'},   
        {name: 'str46'},   
        {name: 'str47'},   
        {name: 'str48'},   
        {name: 'str49'},
        {name: 'str50'},   
        {name: 'str51'},   
        {name: 'str52'},   
        {name: 'str53'},   
        {name: 'str54'},   
        {name: 'str55'},   
        {name: 'str56'},   
        {name: 'str57'},   
        {name: 'str58'},   
        {name: 'str59'},
        {name: 'str60'},   
        {name: 'str61'},   
        {name: 'str62'},   
        {name: 'str63'},   
        {name: 'str64'},   
        {name: 'str65'},   
        {name: 'str66'},   
        {name: 'str67'},   
        {name: 'str68'},   
        {name: 'str69'},
        {name: 'str70'},   
        {name: 'str71'},   
        {name: 'str72'},   
        {name: 'str73'},   
        {name: 'str74'},   
        {name: 'str75'},   
        {name: 'str76'},   
        {name: 'str77'},   
        {name: 'str78'},   
        {name: 'str79'},
        {name: 'str80'},   
        {name: 'str81'},   
        {name: 'str82'},   
        {name: 'str83'},   
        {name: 'str84'},   
        {name: 'str85'},   
        {name: 'str86'},   
        {name: 'str87'},   
        {name: 'str88'},   
        {name: 'str89'},
        {name: 'str90'},   
        {name: 'str91'},   
        {name: 'str92'},   
        {name: 'str93'},   
        {name: 'str94'},   
        {name: 'str95'},   
        {name: 'str96'},   
        {name: 'str97'},   
        {name: 'str98'},   
        {name: 'str99'}
    ]
});

/**
 * 动态结果集store
 * */
Ext.define('QueryResultStore', {
	extend : 'Ext.data.Store',
	model : 'QueryResultModel',
	// 数据库取数据方法，暂时屏蔽
	pageSize : 3000,// 分页条数
	proxy : {
		type : 'ajax',
		url : "execSqlAll.action",
		actionMethods : 'POST',
		timeout : 120000,
		reader : {
			type : 'json',
			root : 'exportQuerys',
			totalProperty : 'totalCount'
		}
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.callParent([ cfg ]);
	}

});

/**
 * 查询SQL报表Store
 */
Ext.define('QuerySqlStore', {
	extend : 'Ext.data.Store',
	model : 'QuerySqlModel',
	// 数据库取数据方法，暂时屏蔽
	pageSize : 20,// 分页条数
	proxy : {
		type : 'ajax',
		url : 'showQuerySql.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			root : 'sqlLists',
			totalProperty : 'totalCount'
		}
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.callParent([ cfg ]);
	}

});





Ext.onReady(function() {
		  var isFirstQuery = 0;	 //判断是否第一次查询sql
		  var vSql;
		  var vQueryParam;
		  var vTableHead;
		  
		  var maxNumber = 500000; //excel最大导出条数
		  
    /**
     * 执行导出数据
     * */
	function exportExce(exportUrl,vSql,vQueryParam,vTableHead,startNum,limitNum,downCode){	     
	
					if (!Ext.fly('downloadAttachFileForm')) {
							var frm = document
									.createElement('form');
							frm.id = 'downloadAttachFileForm';
							frm.style.display = 'none';
							frm.enctype = "multipart";
							document.body.appendChild(frm);
						}
						Ext.Ajax
								.request({
									url : exportUrl,
									form : Ext
											.fly('downloadAttachFileForm'),
									params:{
												id:vSql,
												//sql:vSql,
												queryParam:vQueryParam,
												tableHead:vTableHead,
												startNumber:startNum,
												limitNumber:limitNum,
												downCode:downCode
												},
									isUpload : true
								});
						
	}

	
	function toCsvWait(){
		Ext.defer(function () {

			Ext.Ajax.request({
						url : "verificationquery!verificationExport.action",
						success : function(response) {
							var result = Ext.decode(response.responseText);
							if (result.success == true) {
								 Ext.MessageBox.close();
								 showInfoMsg("生成CSV结束", function(){});
							} else if (result.success == false) {
								toCsvWait();
							} else {
								Ext.MessageBox.close();
								showInfoMsg("系统异常", function() {
								});
							}
						}
					});	
	       
	        
	    }, 5*1000); //5秒查一次
	}
	
	
	
    /**
     * 点击查询调用的查询获取后台数据方法
     * */
	function getData(querygrid,vSql,vQueryParam,vTableHead){			
		Ext.Ajax.request({
			url:"execSqlAll.action",
			params:{
				sql:vSql,
				queryParam:vQueryParam,
				tableHead:vTableHead
				},									
			success:function(response){					
				var result = Ext.decode(response.responseText);
				if(result.resultMessage.returnCode == 1){
				    createGird(querygrid,result.exportQuerys,result.fields,result.tableHeads);
				    hideWait();
				}else if(result.resultMessage.returnCode == 2){
					showInfoMsg(result.resultMessage.message,function(){});
				
				}else{
					showInfoMsg("系统异常",function(){});
				}
			}
		});	
	}	

	
	 /**
	  * ExtJs 根据Param 创建自动添加查询控件
	  * @param panelId  面板id
	  * @param list  配置参数（json格式参数）
	  * @param fun 点击查询按钮调用的函数
	  * @author 张贞献
	  * @时间 2013-6-27
	  */		 
	function createQueryPanel(panelId,list,fun){

		if(list==null||list==""){                 //严重参数是否为空
			addButton(panelId,"查询",fun);
		}else{			
		var json=Ext.decode(list);
		for(var i = 0;i<json.length;i++){
			if(json[i].type=="D"){
				addTimeField(panelId,json[i].labelName,json[i].id);
			}else if(json[i].type=="T"){
				addTextField(panelId,json[i].labelName,json[i].id);
			}else if(json[i].type=="M"){
				createComboFixed(panelId,json[i].labelName,json[i].id,json[i].map);
			}else if(json[i].type == "C"){
				 var url = "queryCombo.action";
				createCombo(panelId,json[i].labelName,json[i].id,json[i].map,json[i].bindIdSql,url);
			}								
		}
		addButton(panelId,"查询",fun);
	  }
	}

	/**
	 * 获取SQL的store
	 * */	
	var store=Ext.create('QuerySqlStore',{
			listeners: {
				beforeload : function(store, operation, eOpts) {
					if (Topform!= null) {
						var queryParams = Topform.getValues();
						var params = {
								'queryAssemble.number' : queryParams.number,
								'queryAssemble.remark' : queryParams.remark,
							};
						Ext.apply(store.proxy.extraParams, params);
//						Ext.apply(operation, {
//							params : {
//								'queryAssemble.number' : queryParams.number,
//								'queryAssemble.remark' : queryParams.remark,
//							}
//						});
					}
				},
				load : function(store, operation, eOpts){
					if (isFirstQuery == 1 && store.getCount() == 0) {
						showInfoMsg("没有查询到您需要的相关数据，请重新查询！");
					}
					isFirstQuery = 1;	
//					clearPanel('queryformTop');  
//				    nullGrid(querygrid);
//				    Ext.getCmp('export').hide();
				    
				}
			},
			autoLoad : true
		});
		
	
	function queryStore_create(){
		return Ext.create('QueryResultStore',{
				listeners: {
					beforeload : function(store, operation, eOpts) {
					var records = sqlgrid.getSelectionModel().getSelection();
	//				Ext.apply(operation, {
	//					params:{
	//						sql:records[0].get('sql'),
	//						queryParam:getAllParam(records[0].get('param')),
	//						tableHead:records[0].get('tableHead')
	//						},
	//				});
					var params = {
							id : records[0].get('id'),
							//sql:records[0].get('sql'),
							queryParam:getAllParam(records[0].get('param')),
							tableHead:records[0].get('tableHead')
						};
					Ext.apply(store.proxy.extraParams,params);
					
				},
				load : function(store, operation, eOpts){
					if (store.getCount() == 0) {
						showInfoMsg("没有查询到您需要的相关数据，请重新查询！");
					}else{
						hideWait();
					}	
					//动态改变列
					querygrid.reconfigure(queryStore,
		    				Ext.decode(sqlgrid.getSelectionModel().getSelection()[0].get('myColumn')));
				}
			}
		});
	};
	
	/**
	 * 动态结果集store
	 * */	
	var queryStore= queryStore_create();
		
//		Ext.create('QueryResultStore',{
//			listeners: {
//					beforeload : function(store, operation, eOpts) {
//					var records = sqlgrid.getSelectionModel().getSelection();
////					Ext.apply(operation, {
////						params:{
////							sql:records[0].get('sql'),
////							queryParam:getAllParam(records[0].get('param')),
////							tableHead:records[0].get('tableHead')
////							},
////					});
//					var params = {
//							id : records[0].get('id'),
//							//sql:records[0].get('sql'),
//							queryParam:getAllParam(records[0].get('param')),
//							tableHead:records[0].get('tableHead')
//						};
//					Ext.apply(store.proxy.extraParams,params);
//					
//				},
//				load : function(store, operation, eOpts){
//					if (store.getCount() == 0) {
//						showInfoMsg("没有查询到您需要的相关数据，请重新查询！");
//					}else{
//						hideWait();
//					}	
//					//动态改变列
//					querygrid.reconfigure(queryStore,
//		    				Ext.decode(sqlgrid.getSelectionModel().getSelection()[0].get('myColumn')));
//				}
//			}
//		});
//	

			
	/**
	 * 查询sql的顶部查询面板
	 * */		 
	var Topform = Ext.create('Ext.form.Panel',{
			    frame:true,
				height:35,
				region:'north',
				layout : {
					type : 'table', // table布局
					columns : 10    // 列数				
				},
				defaults : {// 统一设置宽、居右
					margin : '5 5 0 5',// 间距
					labelWidth : 80,
					border : false,
					labelAlign : 'right',
					width : 180
				},            
				items:[	{
							xtype : 'textfield',
							fieldLabel : '报表编号',
							name : 'number',
							id : 'number',											
							//maxLength:15,
							//maxLengthText:'报表编码不能超过15个字',//过长时自定义提示	
							labelWidth : 60,
							width : 180
						}, {
							xtype : 'textfield',
							fieldLabel : '报表名称',
							name : 'remark',
							id : 'remark',
							//maxLength:15,
							//maxLengthText:'报表名称不能超过15个字',//过长时自定义提示	
							labelWidth : 60,
							width : 180
						},{
							xtype : 'button',
							text : '查询',
							width : 60,
							border : true,
							handler : function() {													
								store.loadPage(1);
						    }
						}
				       ]
			  }
			);
				
	
	
	/**
	 * 显示查询到的sql 表格面板
	 * */		
	var sqlgrid = Ext.create('Ext.grid.Panel', {
				store : store,//创建Store
				border : false,
				columnLines : true,
				layout:'fit',			
				region : 'center',//border布局的中间
			    viewConfig: {
			        enableTextSelection: true
			    },
				columns : [//列
				{
					xtype : 'rownumberer',
					text: "序号",
					width : 60				
				}, {
					dataIndex : 'number',
					text : '报表编号',
					width : 180
				}, {
					dataIndex : 'createDate',
					text : '报表创建时间',
				//	renderer : formatUmpDatetoMin,
					width : 150,
					xtype : 'datecolumn', 
					format : 'Y-m-d H:i:s'
				}, {
					dataIndex : 'remark',
					text : '报表名称',
					width : 200//,
//					flex : 1  //整个宽度减去定义过宽度剩余的
				}, {
					dataIndex : 'queryRule',
					text : '取数规则',
					width : 180,
					flex : 1  //整个宽度减去定义过宽度剩余的
				}],
				listeners : {
					'rowdblclick': function() {

						var  selectParam = "";
						var records = sqlgrid.getSelectionModel().getSelection();
						
						var sqlid=records[0].get('id');
						var tname =records[0].get('remark');
						if(tname.length>8){
							tname =tname.substring(0,8)+"...";
						}
						
						window.parent.delTabpanel(sqlid);
			        	var url = '/hoaureport-web/configreport/showreportpage.action?SqlId=' + sqlid;
			        	window.parent.initTabpanel(sqlid, tname, url, true);
						
			        	return;
			        	
			        	
//						selectParam =records[0].get('param');
////						var tname =records[0].get('remark');
//						init_window_panel();
//						//初始化查询面板
//					    clearPanel('queryformTop');  
//					    nullGrid(querygrid);					    
//					    //根据参数给查询面板添加条件控件
//					    createQueryPanel('queryformTop',selectParam,
//					    		function(){
//					    			showWait();
//							    	if(!checkAllParam(selectParam)){
//							    		showInfoMsg("查询条件参数不能为空！");
//							    	}else{
//							    		  queryStore.loadPage(1);
//							    		  Ext.getCmp('export').show();
//							    		  Ext.getCmp('exportcsv').show();
//							    		  Ext.getCmp('querytocsv').show();
//							    		  queryformwind.doLayout();
//							    	}						    
//					    		}
//					    );						
//					    Ext.getCmp('export').show();
//					    Ext.getCmp('exportcsv').show();
//					    Ext.getCmp('querytocsv').show();
//					    var queryformwind = Ext.create('Ext.window.Window',{
//								id : "reportwindowid",
//								extend : "Ext.window.Window",
//								title:tname,
//								frame:true,
//								modal : true,
//								layout:'border',
//								closeactin: 'destroy',
//								width : '100%',
//								height : '100%',
//								items:[queryform]
//								
//					    });
//					
//					    queryformwind.show();
//					    queryformwind.doLayout();
					 
				  }
				},
				dockedItems : [ {//分页
					xtype : 'pagingtoolbar',
					store : store,
					dock : 'bottom',
					displayInfo : true,
					autoScroll : true
				} ],			
			});
		
	
		function create_queryformTop(){
			return Ext.create('Ext.form.Panel',{
				id:'queryformTop',
			    frame:true,
				height:45,
				region:'north',
				layout : {
					type : 'table', // table布局
					columns : 10
				// 列数
				},
				defaults : {// 统一设置宽、居右
					margin : '5 5 5 5',// 间距
					labelWidth : 50,
					border : false,
					labelAlign : 'right',
					width : 180
				},              
				items:[]
			
			  }
			);
		};
	
		/**
		 * 动态创建查询控件面板
		 * */
		var queryformTop = create_queryformTop();
			
//			Ext.create('Ext.form.Panel',{
//			id:'queryformTop',
//		    frame:true,
//			height:45,
//			region:'north',
//			layout : {
//				type : 'table', // table布局
//				columns : 10
//			// 列数
//			},
//			defaults : {// 统一设置宽、居右
//				margin : '5 5 5 5',// 间距
//				labelWidth : 50,
//				border : false,
//				labelAlign : 'right',
//				width : 180
//			},              
//			items:[]
//		
//		  }
//		);
		
		
		
		
		
		function querygrid_create(){
			
			return Ext.create('Ext.grid.Panel', {
			    id:'querygrid',
				border : false,
				columnLines : true,
				layout:'fit',			
				region : 'center',//border布局的中间
			    viewConfig: {
			        enableTextSelection: true
			    },
				columns : [],
				dockedItems : [ {//分页
					xtype : 'pagingtoolbar',
					store : queryStore,
					dock : 'bottom',
					displayInfo : true,
					autoScroll : true
				} ],		
				tbar : [ {
					xtype : 'button',
					id:'export',
					text : '导出Excel',
					hidden:true,
					//iconCls : 'export',
					handler : function() {
									
						Ext.Ajax.request({
							url:"verificationquery!verificationExport.action",									
							success:function(response){					
								var result = Ext.decode(response.responseText);
								if(result.success== true){
								   
									 var url ='querySqlExcelPort!exportExcel.action'
										  var  selectParam = "";
										  var records = sqlgrid.getSelectionModel().getSelection();
										  selectParam =records[0].get('param');
											if(!checkAllParam(selectParam)){
									    		showInfoMsg("请先查询后再导出！")
									    	}else if(querygrid.getStore().getCount()==0){
									    		showInfoMsg("当前没有要导出的数据！")
									    	}else{				    	
									    		var totalNumber = queryStore.getTotalCount()	;
									    		var fileNumber =  Math.floor(totalNumber%maxNumber==0?
									    				totalNumber/maxNumber:
									    					totalNumber/maxNumber+1);
									    		var msg = "当前导出数据量为:"+totalNumber;		

									    		confirm(msg,function(){
									    			exportExce(url,
									    					records[0].get('id'),
												    		//records[0].get('sql'),
												    		getAllParam(selectParam),
												    		records[0].get('tableHead'),
												    		0,
												    		totalNumber);
										 					    		
									    		},function(){
								    				return false
							    				});				   			
									    	}
									
									
								}else if(result.success== false){
									showInfoMsg(result.message,function(){});
								}else{
									showInfoMsg("系统异常",function(){});
								}
							}
						});	
						
						
						
					 
					
					}
				},{


					xtype : 'button',
					id:'querytocsv',
					text : '生成CSV',
					hidden:true,
					//iconCls : 'export',
					handler : function() {
						
						Ext.Ajax.request({
							url:"verificationquery!verificationExport.action",									
							success:function(response){					
								var result = Ext.decode(response.responseText);
								if(result.success== true){
								   
									 var url ='querytocsv!queryToZip.action'
										  var  selectParam = "";
										  var records = sqlgrid.getSelectionModel().getSelection();
										  selectParam =records[0].get('param');
											if(!checkAllParam(selectParam)){
									    		showInfoMsg("请先查询后再导出！")
									    	}else if(querygrid.getStore().getCount()==0){
									    		showInfoMsg("当前没有要导出的数据！")
									    	}else{				    	
									    		var totalNumber = queryStore.getTotalCount()	;
									    		var fileNumber =  Math.floor(totalNumber%maxNumber==0?
									    				totalNumber/maxNumber:
									    					totalNumber/maxNumber+1);
									    		var msg = "当前数据量为:"+totalNumber;		


									    		var win = new Ext.Window({
									    			title:"生成CSV",
									    			modal:true,
									    			width:250,
									    			height:125,
									    			collapsible:false,
									    			resizable:false,
									    			closeAction:'hide',
									    			items:[{
									    				autoWidth:true,
									    				xtype : "form",
									    				layout:"column",
									    				items:[
									    				    {
									    				    	xtype: "label",
									    		                forId: "msgName",
									    		                text: msg,
									    		                margin : '5 10 0 10',
									    		                columnWidth:1
									    				    },{
																name : 'charCode',
																margin : '5 10 0 10',
																readOnly : false,
																editable : false,
																xtype : 'combobox',
																value:'GBK',
																columnWidth:1,
																store : [['GBK', "编码格式：GBK"], ['UTF-8', "编码格式：UTF-8"]]
									    				}] 
									    				
									    				
									    			}],
									    			buttons : [{
									    				text : '确定',
									    				handler : function(){
									    					var form =this.up('window').down('form').getForm();
									    					var code =form.findField('charCode').getValue();
									    					var wid = this.up('window');
									    					wid.close();
									    					Ext.MessageBox.wait("正在生成csv文件，请稍候...", "等待");
											    			Ext.Ajax.request({
																url:url,
																params:{
																	id:records[0].get('id'),
																	//sql:vSql,
																	queryParam:getAllParam(selectParam),
																	tableHead:records[0].get('tableHead'),
																	startNumber:0,
																	charCode:code,
																	limitNumber:totalNumber
																	},
																success:function(response){					
																	var result = Ext.decode(response.responseText);
																	if(result.success== true){
																		
																	   //正常 
																		toCsvWait();
																		
																	}else if(result.success== false){
																		Ext.MessageBox.close();
																		showInfoMsg(result.message,function(){});
																	}else{
																		Ext.MessageBox.close();
																		showInfoMsg("系统异常",function(){});
																	}
																}
															});	
									    					
									    				}
									    				}, {
									    				text : '取消',
									    				handler : function(){
									    					this.up('window').close();
									    				}
									    				}]
									    			});
									    			win.show();
									    		
									    	}
									
									
									
								}else if(result.success== false){
									showInfoMsg(result.message,function(){});
								}else{
									showInfoMsg("系统异常",function(){});
								}
							}
						});	
						
						
																						
					 
					
					}
				
				
				},{

					xtype : 'button',
					id:'exportcsv',
					text : '导出CSV',
					hidden:true,
					//iconCls : 'export',
					handler : function() {
						
						Ext.Ajax.request({
							url:"verificationquery!verificationExport.action",									
							success:function(response){					
								var result = Ext.decode(response.responseText);
								if(result.success== true){
								   
									 var url ='querySqlcsvPort!exportCSV.action'
										  var  selectParam = "";
										  var records = sqlgrid.getSelectionModel().getSelection();
										  selectParam =records[0].get('param');
											if(!checkAllParam(selectParam)){
									    		showInfoMsg("请先查询后再导出！")
									    	}
//											else if(querygrid.getStore().getCount()==0){
//									    		showInfoMsg("当前没有要导出的数据！")
//									    	}
									    	else{				    	
									    		var totalNumber = queryStore.getTotalCount()	;
									    		var fileNumber =  Math.floor(totalNumber%maxNumber==0?
									    				totalNumber/maxNumber:
									    					totalNumber/maxNumber+1);
									    		var msg = "确认导出已生成的CSV文件 ？";		

									    		var win = new Ext.Window({
									    			title:"导出选择",
									    			modal:true,
									    			width:250,
									    			height:115,
									    			collapsible:false,
									    			resizable:false,
									    			closeAction:'hide',
									    			items:[{
									    				autoWidth:true,
									    				xtype : "form",
									    				layout:"form",
									    				margin : '10 15 10 15',
//									    				frame:true,
									    				items:[
										    				{
																name : 'downcode',
																allowBlank : false,
																readOnly : false,
																editable : false,
																xtype : 'combobox',
																value:'1001',
																store : [['1001', "导出手动生成的CSV包"], ['1002', "导出平台整月CSV包"]]
									    				}] 
									    				
									    				
									    				
									    			}],
									    			buttons : [{
									    				text : '确定',
									    				handler : function(){
									    					var form =this.up('window').down('form').getForm();
									    					var code =form.findField('downcode').getValue();
									    					var wid = this.up('window');
									    					Ext.Ajax.request({
																url:"verificationcsv!csvExist.action",									
																params:{
																	id:records[0].get('id'),
																	downCode:code
																},
																success:function(response){
																	var res = Ext.decode(response.responseText);
																	if(res.success== true){
																		exportExce(url,
														    					records[0].get('id'),
																	    		getAllParam(selectParam),
																	    		records[0].get('tableHead'),
																	    		0,
																	    		totalNumber,code);
												    					
																		wid.close();
																	}else if(res.success== false){
																		showInfoMsg(res.message,function(){});
																	}else{
																		showInfoMsg("系统异常",function(){});
																	}
																}
									    					});
									    					
									    					
//									    					exportExce(url,
//											    					records[0].get('id'),
//														    		getAllParam(selectParam),
//														    		records[0].get('tableHead'),
//														    		0,
//														    		totalNumber,code);
//									    					
//									    					
//									    					this.up('window').close();
									    				}
									    				}, {
									    				text : '取消',
									    				handler : function(){
									    					this.up('window').close();
									    				}
									    				}]
									    			});
									    			win.show();
									    		
									    		
									    		
//									    		confirm(msg,function(){
//									    			exportExce(url,
//									    					records[0].get('id'),
//												    		//records[0].get('sql'),
//												    		getAllParam(selectParam),
//												    		records[0].get('tableHead'),
//												    		0,
//												    		totalNumber);
//										 					    		
//									    		},function(){
//								    				return false
//							    				});				   			
									    	}
									
									
									
								}else if(result.success== false){
									showInfoMsg(result.message,function(){});
								}else{
									showInfoMsg("系统异常",function(){});
								}
							}
						});	
						
						
																						
					 
					
					}
				
				}
/*				,{
					xtype : 'button',
					id:'showbur',
					text : '查看图表',
					hidden:false,
					handler : function(){
						var records = sqlgrid.getSelectionModel().getSelection();
						Ext.Ajax.request({
							url:"showburquery!showburqueryMethod.action",
							params:{
								id:records[0].get('id')
							},
							success:function(response){
								var result = Ext.decode(response.responseText);
								if(result.success==true){
									fdhzlShowWin = Ext.create('fdhzlShowWin');
									getShowData(result.reList);
									load_Echarts();
									fdhzlShowWin.show();
									
								}else if(result.success== false){
									showInfoMsg(result.message,function(){});
								}else{
									showInfoMsg("系统异常",function(){});
								}
							}
						});
					}
					
				}*/ 
				]
			});
		};
		
		
		/**
		 *动态获取结果集面板 
		 * */
		var querygrid =querygrid_create();
		
//		Ext.create('Ext.grid.Panel', {
//			    id:'querygrid',
//				border : false,
//				columnLines : true,
//				layout:'fit',			
//				region : 'center',//border布局的中间
//			    viewConfig: {
//			        enableTextSelection: true
//			    },
//				columns : [],
//				dockedItems : [ {//分页
//					xtype : 'pagingtoolbar',
//					store : queryStore,
//					dock : 'bottom',
//					displayInfo : true,
//					autoScroll : true
//				} ],		
//				tbar : [ {
//					xtype : 'button',
//					id:'export',
//					text : '导出',
//					hidden:true,
//					//iconCls : 'export',
//					handler : function() {
//																						
//					  url ='querySqlExcelPort!exportExcel.action'
//					  var  selectParam = "";
//					  var records = sqlgrid.getSelectionModel().getSelection();
//					  selectParam =records[0].get('param');
//						if(!checkAllParam(selectParam)){
//				    		showInfoMsg("请先查询后再导出！")
//				    	}else if(querygrid.getStore().getCount()==0){
//				    		showInfoMsg("当前没有要导出的数据！")
//				    	}else{				    	
//				    		var totalNumber = queryStore.getTotalCount()	;
//				    		var fileNumber =  Math.floor(totalNumber%maxNumber==0?
//				    				totalNumber/maxNumber:
//				    					totalNumber/maxNumber+1);
//				    		var msg = "当前导出数据量为:"+totalNumber;		
//
//				    		confirm(msg,function(){
//				    			exportExce(url,
//				    					records[0].get('id'),
//							    		//records[0].get('sql'),
//							    		getAllParam(selectParam),
//							    		records[0].get('tableHead'),
//							    		0,
//							    		totalNumber);
//					 					    		
//				    		},function(){
//			    				return false
//		    				});				   			
//				    	}
//					
//					}
//				} ]
//			});
			
		
		function queryform_create(){
			return Ext.create('Ext.form.Panel',{
				id:'queryform',
				 layout:'border',
				 region:'center',
				items:[queryformTop,querygrid]
				
			});
		};
		
		
			/**
			 *动态查询结果集面板
			 * */
			var queryform = queryform_create();
				
//				Ext.create('Ext.form.Panel',{
//				id:'queryform',
//				 layout:'border',
//				 region:'center',
//				items:[queryformTop,querygrid]
//				
//			});
			
           /**
            * SQL查询面板
            * */
			 var form = Ext.create('Ext.form.Panel',{
				 title:"报表查询",
				 layout:'border',
				 region:'center',				 
				 frame:true,
				 height:200,
//				  collapsible: true,
//	              animCollapse: true,
//	              titleCollapse:true,
				 items:[Topform,sqlgrid]
			 });
			
			 
			 
//			 var queryformwind = Ext.create('Ext.window.Window',{
//						id : "reportwindowid",
//						extend : "Ext.window.Window",
//						layout:'border',
//						closeactin: 'hide',
//						width : '100%',
//						height : '100%',
//						items:[queryform]
//						
//			 });

			 
  function init_window_panel(){
	  if(!Ext.getCmp('querygrid')){
		  querygrid =querygrid_create();
	  }
	  if(!Ext.getCmp('queryformTop')){
		  queryformTop = create_queryformTop();
	  }
	  if(!Ext.getCmp('queryform')){
		  queryform = queryform_create();
	  }
	  
  }			 
			 
			 
			//整个页面布局
		var viewport = Ext.create('Ext.Viewport', {
			layout : {
				type : 'border',//用border布局 ，把页面分为上下部分 form为上、grid为下
				padding : 5
			},
			 frame:true,
			items : [ form 
			          //,queryform 
			          ]
		});

});
