/**
 * 自定义查询SQL管理页面
 */

/**
 * 自定义查询SQL Model
 */

Ext.define('QuerySqlModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	},// ID
	{
		name : 'number'
	},// 序列号
	{
		name : 'sql'
	},// SQL
	{
		name : 'tableHead'
	},// 表头
	{
		name : 'param'
	},// 参数
	{
		name : 'remark'
	},// 备注
	{
		name : 'queryRule'
	},// 取数规则
	{
		name : 'roles'
	},// 权限角色
	{
		name : 'createUser'
	},// 创建人
	{
		name : 'createDate',
		type : 'date',
		dateFormat:'time',
		defaultValue : new Date()
	},// 创建时间
	{
		name : 'modifyUser'
	},// 修改人
	{
		name : 'modifyDate',
		type : 'date',
		dateFormat:'time',
		defaultValue : new Date()
	}, // 修改时间
	{name : 'exitType' }, //导出选择
	{name : 'originalHead' },//原值表头
	{name : 'originalSql' } //原值sql
	]
});




//自定义查询SQL Store
Ext.define('QuerySqlStore', {
	extend : 'Ext.data.Store',
	model : 'QuerySqlModel',
	pageSize : 15,// 分页条数
	// 数据库取数据方法，暂时屏蔽
	proxy : {
		type : 'ajax',
		url : 'showPageQuerySql.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'querySqls',
			totalProperty : 'totalCount'
		}
	}
});







Ext.onReady(function(){
	//初始化提示条
	//bug2046
	//bug2049
	
    Ext.QuickTips.init();
    
    
    
	var win;//新增修改自定义查询SQL window
	/**
	* 查询条件form
	*/
	 var isFirstQuery=0;//是否是第一次加载0为第一次加载，1为不是第一次加载
	var form=Ext.create('Ext.form.Panel',{
		layout : {
			type : 'table',	//table布局
			columns : 3	//列数
		},
		defaults: {//统一设置宽、居右
			margin:'5 5 0 5',//间距
			labelWidth:60,
			border:false,
			labelAlign: 'right',
			width : 180
		},
		defaultType:'textfield',//默认类型
		frame:true,
		height:35,
		region:'north',
		items:[{
			fieldLabel: '功能描述',
			id : 'remark',
			name:'remark'
		},{
			xtype:'button',
			text:'查询',
			width:60,
			border : true,
			handler:function(){
				isFirstQuery=1;
				querySqlStore.loadPage(1);	//查询后默认返回第一页
			}
		}
		]
	});
	
	var querySqlStore=Ext.create('QuerySqlStore',{
		listeners: {
			beforeload : function(store, operation, eOpts) {
				if (form!= null) {
					var queryParams = form.getValues();
					var params = {
							'querySql.remark':queryParams.remark
						};
//					Ext.apply(operation,params );
					Ext.apply(store.proxy.extraParams,params );
				}
			},
			load: function(store, operation, eOpts) {
				   if(isFirstQuery==1 && querySqlStore.getCount()==0){		
					    showInfoMsg("没有查询到您需要的相关数据，请重新查询！"); 					   
				   };
				   isFirstQuery=1;
			},
			autoLoad:true
		}
	});
	
	querySqlStore.loadPage(1);	
	

	var roleStore = Ext.create('Ext.data.TreeStore', {
		root : {
			expanded : true,	
			id:'0'
		},
		default:{expanded : true },
		proxy:{
			type:'ajax',
			url:'querySqlRoleList!querySqlRoleList.action',
			actionMethods:'POST',
			reader:{
				type:'json',
				root:'roleThreeVos',	
			}
		 },
		 autoLoad:true
	});	

	
	/*加载角色菜单树*/
	var treePanel = Ext.create('Ext.tree.TreePanel', {
		store : roleStore,
		//rootVisible : false,
		region:'west',
		collapsible : false,//折叠
		width : 186,
		border:true,
		minWidth : 186,
		maxWidth : 186,
	    split: true,//可以合并
	    autoScroll: true,
		//树节点是否可见
	    rootVisible: false,
	  //使用vista风格的箭头图标，默认为false
	    collapseMode:'mini'
	});
	
	
	/**
	 * 查询结果Grid
	 */
	var grid=Ext.create('Ext.grid.Panel', {
		store: querySqlStore,//创建querySqlStore
		border:false,
		columnLines:true,
		selModel:Ext.create('Ext.selection.CheckboxModel'),//checkbox
		region:'center',//border布局的中间
		columns: [//列
		{xtype: 'rownumberer',
			width:40},
		{	
            dataIndex: 'number',
			text:'序列号',
			hideable:false,	
			width:180,
        },{
            dataIndex: 'sql',
			text:'SQL',
            width:180,
            menuDisabled:true
        },{
            dataIndex: 'tableHead',
			text:'表头',
            width:120,
            menuDisabled:true
        },{
            dataIndex: 'param',
			text:'参数',    
			 menuDisabled:true,
        },{
            dataIndex: 'remark',
			text:'功能描述',
			 menuDisabled:true,
			 minWidth:240//,
//            flex:1//整个宽度减去定义过宽度剩余的
        },{
            dataIndex: 'queryRule',
			text:'取数规则',
			 menuDisabled:true,
			 minWidth:240,
            flex:1//整个宽度减去定义过宽度剩余的
        },{
            dataIndex: 'createUser',
			text:'创建人',
			hidden:true,
			 menuDisabled:true,
            width:120
        },{
            dataIndex: 'createDate',
			text:'创建时间',
			hidden:true,
			 menuDisabled:true,
            width:140
        },{
            dataIndex: 'modifyUser',
			text:'修改人',
			hidden:true,
			 menuDisabled:true,
            width:120
        },{
            dataIndex: 'modifyDate',
			text:'修改时间',
			hidden:true,
			 menuDisabled:true,
            width:140
        }],
        listeners : {
			itemClick : function() {
				Ext.getCmp('sqlContent').setValue("");
				Ext.getCmp('paramContent').setValue("");
				var records = grid.getSelectionModel().getSelection();
				Ext.getCmp('sqlContent').setValue(
					records[0].get('sql').
					replace(/select/g,"select\n")					
					.replace(/from/g,"\nfrom\n")
					.replace(/where/g,"\nwhere\n")										
					);
			
				if(records[0].get('param')!=null&&records[0].get('param').length>0){
					Ext.getCmp('paramContent').setValue(
							records[0].get('param')
							.replace(/{/g,"\n{")
							.replace(/],/g,"],\n{")
							.replace(/"m",/g,"\"M\",\n")
							.replace(/"C",/g,"\"C\",\n")
							);
				
						
		      	}
			}
		},
		 dockedItems:[{//分页
	 			xtype:'pagingtoolbar',
	 			id: 'querySqlGridPaginId',
	 			store:querySqlStore,
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
				var querySqlModel = new QuerySqlModel();
				querySqlModel.set("id",null);
				addForm.getForm().loadRecord(querySqlModel);
				addForm.getForm().findField("id").disable();
				addForm.getForm().findField("id").setValue(null);
				//addForm.getForm().findField("querySqlNo").readOnly=true;
				win.setTitle("新增查询SQL");
				roleStore.load({
					params:{
						sqlCode:"" 
					}
				});
				win.show();
				
			}
		},'-',{// '-' 表示分隔符
			xtype:'button',
			text:'删除',
			//iconCls: 'del',
			handler:function(){
				var records=grid.getSelectionModel().getSelection();//获取选中集合
				if(records.length == 0){
					showInfoMsg("请选择后再进行操作！");
					return false;
				};
				
				formUrl = 'toVoidQuerySql.action';
				
				if(records.length > 0){
					var ids = new Array();//定义要删除的id对应后台String[] ids
					for (var j = 0; j < records.length; j++) {
						ids.push(records[j].get('id'));
					}
					confirm("您确定要删除吗？",
							function(){
							var msgWindow=null;
						//保存成功回调函数
							var returnFn = function(json){
								msgWindow.close();
						    	showInfoMsg(json.message);
						    	Ext.getCmp('querySqlGridPaginId').moveFirst();	
						    };	
							
							var params={'querySqlNos':ids};//传数组到后台
							msgWindow=showWait();
							requestUmpAjaxJsonParams(formUrl,params,returnFn);	
							
					    	});
				
				
			    }

			}
		},'-',{//提示 '->' 让按钮居右
			xtype:'button',
			text:'修改',
			//iconCls: 'edit',
			handler:function(){
				var records=grid.getSelectionModel().getSelection();//获取选中集合
				if(records.length == 0){
					showInfoMsg("请选择一条记录！");
					return false;
				};
				if(records.length != 1){
					showInfoMsg("只能选择一条记录！");
					return false;
				};
				var form=addForm.getForm();
				var querySqlModel = new QuerySqlModel();
				form.loadRecord(querySqlModel);
				form.findField("id").enable();//修改的时候id为启用
				form.findField("id").setValue("");//清空
				form.findField("id").setValue(records[0].get("id"));
				form.findField("sql").setValue(records[0].get("sql"));
				form.findField("tableHead").setValue(records[0].get("tableHead"));
				form.findField("param").setValue(records[0].get("param"));
				form.findField("remark").setValue(replaceAll(records[0].get("remark")? records[0].get("remark"):''));
				form.findField("queryRule").setValue(replaceAll(records[0].get("queryRule")? records[0].get("queryRule"):''));
				
				form.findField("exitType").setValue(records[0].get("exitType"));
				form.findField("originalHead").setValue(records[0].get("originalHead"));
				form.findField("originalSql").setValue(records[0].get("originalSql"));
				
				form.updateRecord(querySqlModel);				
				win.setTitle("修改自定义查询SQL");
				
				if(records[0].get("exitType")=='1' || records[0].get("exitType")=='2'){
					form.findField('originalHead').allowBlank=false;
					form.findField('originalHead').setFieldLabel('<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>原值表头');
					form.findField('originalSql').allowBlank=false;
					form.findField('originalSql').setFieldLabel('<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>原值SQL');
	        	}else{
	        		
					form.findField('originalHead').allowBlank=true;
					form.findField('originalHead').setFieldLabel('原值表头');
					form.findField('originalSql').allowBlank=true;
					form.findField('originalSql').setFieldLabel('原值SQL');
	        	}
				
				
				
				roleStore.load({
					params:{
						sqlCode:records[0].get("id") 
					}
				});
				
				win.show();
				
				
			}
		}]
	});
		
	function saveQuerySql(){
		var msgWindow = null;
		var form=addForm.getForm();
		var returnFn = function(json){
	    	showInfoMsg(json.message);
	    	Ext.getCmp('querySqlGridPaginId').moveFirst();
	    	if(json.returnCode==1){
	    		win.hide();
	    	}
	    };	
	   
		var store=treePanel.getStore();//获取Store
		var ids = new Array();
		var roles = "";
		var childNodes = store.getRootNode().childNodes;
		if(childNodes.length != 0){
			for(var i = 0;i<childNodes.length;i++){
				var node = childNodes[i];
				if(true == node.data.checked){
					ids.push(node.data.id);
				}
			}
			roles = ids.join(",");
		}
	    
	    var formUrl = 'saveorModifyQuerySql.action';
		var querySqlModel=form.getRecord();
		querySqlModel.set('roles',roles);
		var querySqlData;
		form.updateRecord(querySqlModel);
		querySqlData = {'querySql':querySqlModel.data};
		msgWindow=showWait();
		requestUmpAjaxJsonParams(formUrl,querySqlData,returnFn);
	}
	
	
	//验证表单是否提交成功
	function formCheck(){
		var msg="表单验证失败，请重新输入";
		showInfoMsg(msg,function(){
			return false;
		});
	};	
	
	
	
	/**
	* 新增查询SQL Form
	*/
	var addForm=Ext.create('Ext.form.Panel',{
		id:'addForm',
		layout : {
			type : 'table',	//table布局
			columns : 1	//列数
		},
		defaults: {//统一设置宽、居右
			margin:'5 5 5 5',//间距
			labelWidth:80,
			border:false,
			labelAlign: 'right',
			//width : 200,
			//height:  100,
		},
		defaultType:'textfield',//默认类型
		frame:true,
		autoScroll : true, // 显示滚动条
//		height:400,
		region:'center',
		buttonAlign:'center',
		items:[{
			fieldLabel: 'ID',
			xtype:'textarea',
			hidden:true,
			width:380,
			value:'',
			name:'id'
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '功能描述',
			xtype:'textarea',
			width:640,
			height:40,
			maxLength:500,
			maxLengthText:'功能描述不能超过500个字',//过长时自定义提示
			colspan: 2,
			name:'remark'
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '取数规则',
			xtype:'textarea',
			width:640,
			height:40,
			maxLength:500,
			maxLengthText:'取数规则不能超过500个字',//过长时自定义提示
			colspan: 2,
			name:'queryRule'
		},{
			fieldLabel: '表头',
			xtype:'textarea',
			width:640,
			colspan: 2,
			height:40,
			allowBlank:false,//判断不能为空
			name:'tableHead'
		},{
			fieldLabel: 'SQL',
			xtype:'textarea',
			width:640,
			height:120,
			colspan: 2,
			allowBlank:false,//判断不能为空
			name:'sql'
		},{
			fieldLabel: '参数',
			xtype:'textarea',
			width:640,
			height:120,
			colspan: 2,
			name:'param'
		},{
			fieldLabel: '导出选择',
			name : 'exitType',
			allowBlank:false,
			readOnly : false,
			editable : false,
			xtype : 'combobox',
			value:'0',
			columnWidth:1,
			store : [['0', "导出界面显示值"], ['1', "导出原始值"],['2', "导出界面值/原始值"]],
			listeners:{
		         'change':function(combox,newValue,oldValue){
		        	 var formp =this.up('form').getForm();
		        	 if(newValue=='1' || newValue=='2'){
			        	 formp.findField('originalHead').allowBlank=false;
			        	 formp.findField('originalHead').setFieldLabel('<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>原值表头');
			        	 formp.findField('originalSql').allowBlank=false;
			        	 formp.findField('originalSql').setFieldLabel('<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>原值SQL');
		        	 }else{
			        	 formp.findField('originalHead').allowBlank=true;
			        	 formp.findField('originalHead').setFieldLabel('原值表头');
			        	 formp.findField('originalSql').allowBlank=true;
			        	 formp.findField('originalSql').setFieldLabel('原值SQL');
		        	 } 
		        	 this.up('form').doLayout();
		         }
		    }
		},{
			fieldLabel: '原值表头',
			xtype:'textarea',
			width:640,
			colspan: 2,
			height:40,
			allowBlank:true,//判断不能为空
			name:'originalHead'
		},{
			fieldLabel: '原值SQL',
			xtype:'textarea',
			width:640,
			height:120,
			colspan: 2,
			allowBlank:true,//判断不能为空
			name:'originalSql'
		}],
		buttons:[{
			xtype:'button',
			text:'保存',
			width:80,
			handler:function(){
				var form=addForm.getForm();
				if(form.isValid()){//表单验证是否可提交					
					saveQuerySql();
				}else{//表单验证不通过
					formCheck();
				}
			
			}
		},{
			xtype:'button',
			text:'关闭',
			width:80,
			handler:function(){
				win.close();
			}
		}]
	});
		
	var updateData=false;
	//比较是否修改
	function isUpdateData(){
		var form=addForm.getForm();		
		//表单验证是否可提交
		// 在修改 的时候 验证数据是否已经修改
		var querySqlModel=form.getRecord();
		var sql=form.findField("sql").getValue();
		var tableHead=form.findField("tableHead").getValue();
		var param=form.findField("param").getValue();
		var remark=form.findField("remark").getValue();
		var queryRule=form.findField("queryRule").getValue();
		if(  compare(querySqlModel.get("sql"),sql)
		   &&compare(querySqlModel.get("tableHead"),tableHead) 
		   &&compare(querySqlModel.get("param"),param)
		   &&compare(querySqlModel.get("remark"),remark)		
		   &&compare(querySqlModel.get("queryRule"),queryRule)		
		){
			//没有修改 提示信息并返回
			updateData=false;
		}else{//已经修改 把修改的数据传入后台
			updateData=true;
		};
	}
	//比较两个字符串是否相等
	function compare(str1,str2){
		if(str1==str2){
			return true;
			
		}else{
			return false;
		};
		
	};
	
	//关闭窗口
	function close(){
		var form=addForm.getForm();
		var querySqlModel=form.getRecord();
		var id=form.findField("id").getValue();
		if(!compare(id,"")&&!compare(id,null)&&form.isValid()){//只有修改中验证是否已修改数据
			isUpdateData();
			if(updateData){
				//没有修改 提示信息并返回
				var msg="数据有修改，是否保存";
				confirm(msg,function(){
					saveQuerySql();
				},function (){//返回修改页面
					return false;
				});
				
			}
		}	
	};	
	
	//添加pannel
	var addPanel =Ext.create('Ext.form.Panel',{
		layout: {
			type: 'border',//用border布局 ，把页面分为上下部分 form为上、grid为下
			padding: 5
		},
		buttonAlign:'center',
		items: [addForm,treePanel]
	});
	
	
	if(!win){//需要定义全局变量,如果不存在就创建一个，保证只创建一次
		win=Ext.create('Ext.window.Window',{
			width:880,
			height:520,
			modal:true,//模式窗口（带蒙层，后面不可操作）
			layout:'fit',
			closeAction:'hide',//关闭方式 （默认为销毁）
//			autoScroll : true, // 显示滚动条
			items:[addPanel],
			listeners:{
		    	close:function(){
		    		close();
		    	}
		    }
		});
	}
	
	var bot = Ext.create('Ext.form.FieldSet', {
		 layout: {
	            type: 'table',
	            columns: 2
	        },
		defaultType:'textarea',//默认类型
		frame:true,
		
		height:200,
		region : 'south',
		items : [{
			xtype : 'label',
			text: '查询SQL'
		},{
			xtype : 'label',
			text: 'SQL参数(JSON)'
		},{
			xtype : 'textarea',
			id : 'sqlContent',
			   width: document.body.clientWidth/2-15,
			height:170,
			readOnly : true
		},{
			xtype : 'textarea',
			width: document.body.clientWidth/2-15,
			id : 'paramContent',
			height:170,
			readOnly : true
		}
		]

	});
	
	//整个页面布局
	var viewport = Ext.create('Ext.Viewport', {
		layout: {
			type: 'border',//用border布局 ，把页面分为上下部分 form为上、grid为下
			padding: 5
		},
		items: [form,grid,bot]
	});
	
});