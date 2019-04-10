/**
 * 获取浏览器高度
 */
oms.getBrowserHeight = function() {
    var browserHeight = document.documentElement.clientHeight;
    return browserHeight;
};

/**
 * model(resouce)
 */
Ext.define('Butterfly.model.resourceEntity',{
	extend: 'Ext.data.Model',
	fields: [{
		name : 'id',
		// id
		type : 'string'

	},{
		name : 'code',
		// 权限编码
		type : 'string'
	},{
		name : 'name',
		// 权限名称
		type : 'string'
	},{
		name : 'entryUri',
		// 权限入口URI
		type : 'string'
	},{
		name : 'resLevel',
		// 功能层级
		type :'string'
	},{
		name : 'parentRes'
		// 父id
		
	},
	{
		name : 'checked',
		type : 'string'
	},{
		name : 'displayOrder',
		// 显示顺序
		type : 'int'
	},{
		name : 'resType',
		// 权限类型
		type : 'string'
	},{
		name : 'leafFlag',
		// 是否叶子节点
		type : 'string'
	},{
		name : 'iconCls',
		// 图标的CSS样式
		type : 'string'
	},{
		name : 'cls',
		// 节点的CSS样式
		type : 'string'
	},{
		name : 'notes',
		// 权限描述
		type : 'string'
	},{
		name : 'belongSystemType',
		// 所属系统类型
		type : 'string'
	}
	]
});
/**
 * 权限管理详情视图(resource)
 */
Ext.define('Butterfly.view.resource.Grid', {
	extend : 'Ext.grid.Panel',
// extend : 'Ext.window.Window',
	id : 'resourceGrid2',
	frame : true,
	autoScroll : true,
// height : oms.getBrowserHeight() - 120,
	height : 40,
	width : '100%',
	addResourcewindow :null,
	getaddResourcewindow : function(){
		if (this.addResourcewindow == null) {
    		
            this.addResourcewindow = Ext.create('Butterfly.view.addResource.window');
            this.addResourcewindow.parent = this; // 父元素
        }
        return this.addResourcewindow;
	},
	changResource :function(){
		var me = this;
		var selections = me.up().getresourceTree().getSelectionModel().getSelection(); // 获取选中的数据
        if (selections.length != 1) { // 判断是否选中了一条
            oms.showWoringMessage(baseinfo.resource.i18n('bse.route.selectedone')); // 请选择一条进行作废操作！
            return; // 没有则提示并返回
        }
        var winIn = me.getaddResourcewindow();
    	winIn.show();
    	winIn.setTitle(baseinfo.resource.i18n('oms.common.update'));//修改');
    	changbox = winIn.getResourceFrom().getForm();
    	winIn.setupdate();
    	var resobj = selections[0].get('resourceEntity');
    	changbox.findField('id').setValue(resobj.id);
    	changbox.findField('code').setValue(resobj.code);
    	changbox.findField('name').setValue(resobj.name);
    	changbox.findField('entryUri').setValue(resobj.entryUri);
    	changbox.findField('resLevel').setValue(resobj.resLevel);
    	changbox.findField('displayOrder').setValue(resobj.displayOrder);
    	changbox.findField('checked').setValue(resobj.checked);
    	changbox.findField('resType').setValue(resobj.resType);
    	changbox.findField('leafFlag').setValue(resobj.leafFlag);
    	changbox.findField('iconCls').setValue(resobj.iconCls);
    	changbox.findField('cls').setValue(resobj.cls);
    	changbox.findField('notes').setValue(resobj.notes);
    	changbox.findField('belongSystemType').setValue(resobj.belongSystemType);
    	Ext.getCmp('oms_bse_resource_sele').setCombValue(resobj.parentRes.name,resobj.parentRes.code);
    	changbox.findField('code').setReadOnly(true);
    	changbox.findField('name').setReadOnly(true);
        
	},
	 deleteResource : function(){
   	 var me = this;
        var selections = me.up().getresourceTree().getSelectionModel().getSelection(); // 获取选中的数据
        if (selections.length < 1) { // 判断是否至少选中了一条
            oms.showWoringMessage(baseinfo.route.i18n('bse.resource.selectedone')); // 请选择一条进行作废操作！
            return; // 没有则提示并返回
        }
        var resourceEntityList = new Array();
        for (var i = 0; i < selections.length; i++) {
        	resourceEntityList.push({
        		'id': selections[0].get('resourceEntity').id,
                'code': selections[0].get('resourceEntity').code
            });
        }
        oms.showQuestionMes(baseinfo.resource.i18n('bse.resource.deleteresource'),//'是否要删除?其子节点也将被删除',
       	        function(e) {
       	            if (e == 'yes') { // 询问是否删除，是则发送请求
       	                var params = {
       	                    'resourceVo': {
       	                        'resourceEntityList': resourceEntityList
       	                    }
       	                };
       	                var successFun = function(json) {
       	                    var message = json.message;
       	                    oms.showInfoMsg(message);
       	                    me.up().getresourceTree().getStore().load();
       	                };
       	                var failureFun = function(json) {
       	                    if (Ext.isEmpty(json)) {
       	                        oms.showErrorMes(baseinfo.resource.i18n('bse.resource.timeout')); // 请求超时
       	                    } else {
       	                        var message = json.message;
       	                        oms.showErrorMes(message);
       	                    }
       	                };
       	                oms.requestJsonAjax('resourceAction!deleteResource.action', params, successFun, failureFun);
       	            }
       	        });
   },
	constructor : function(config) {
		var me = this,
		cfg = Ext.apply({}, config);
		
		me.tbar = [ {
			xtype:'addbutton',
			text :baseinfo.resource.i18n('oms.common.add'),// '新增URL',
			handler : function() {
				var winIn = me.getaddResourcewindow();
				winIn.show();
				winIn.setTitle(baseinfo.resource.i18n('oms.common.add'));//'新增');
				changbox = winIn.getResourceFrom().getForm();
				winIn.setsave();
				changbox.findField('code').setReadOnly(false);
    			changbox.findField('name').setReadOnly(false);
			}
		},'-',{
			xtype :'updatebutton',
			id : 'oms_bseinfo_resource_update_id',
		
			text : baseinfo.resource.i18n('oms.common.update'),//'修改',
			handler : function() {
				me.changResource();
			}
		},'-',{
			xtype : 'deletebutton',
			id : 'oms_bseinfo_resource_delete_id',
			text :baseinfo.resource.i18n('oms.common.delete'),// '删除',
			handler : function() {
					me.deleteResource();
			}
		}];
//		me.selModel = Ext.create('Ext.selection.CheckboxModel', { // 多选框
//
//            listeners: {
//            selectionchange: function(sm, selections) {
//                Ext.getCmp('oms_bseinfo_resource_update_id').setDisabled(selections.length !== 1);
//                Ext.getCmp('oms_bseinfo_resource_delete_id').setDisabled(selections.length === 0);
//
//            }
//        }
//        });
		me.store = Ext.create('Butterfly.store.resource', {
			autoLoad : false
		});
		me.callParent([ cfg ]);		
	}

});


/**
 * 权限store
 */
Ext.define('Butterfly.store.resource',{
	extend : 'Ext.data.Store',
	model : 'Butterfly.model.resourceEntity',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		actionMethods : 'post',
		url : 'resourceAction!queryResourceByExample.action',
		reader : {
			type : 'json',
			rootProperty : 'resourceVo.resourceEntityList',
			totalProperty : 'totalCount' // 总个数
		}
	},
	listeners : {
		beforeload : function(store, operation, eOpts) {
			var queryRouteForm = Ext.getCmp('queryResourceForm');
			if (queryRouteForm != null) {
				var params = {
					'resourceVo.resourceEntityDetail.code' : queryRouteForm.getForm().findField('code').getValue()
					
				};
				Ext.apply(store.proxy.extraParams, params);
			}
		}
	}
});

/**
 * 添加面版
 */
Ext.define('Butterfly.view.addResource.window',{
	extend: 'Ext.window.Window',
	id : 'oms_bse_add_resource2',
	closable: true,
	modal: true,
    resizable: false,
    // 可以调整窗口的大小
    closeAction: 'hide',
    // 点击关闭是隐藏窗口
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    resourceFrom: null,
    getResourceFrom: function() {
    	var me = this;
        if (Ext.isEmpty(this.resourceFrom)) {
            this.resourceFrom = Ext.create('Butterfly.view.resourceFrom');
        }
        return this.resourceFrom;
    },
    listeners: {
        beforehide: function(me) { // 隐藏WINDOW的时候清除数据
            me.getResourceFrom().getForm().reset(); // 表格重置
        },
        beforeshow: function(me) { // 显示WINDOW的时候清除数据
            var fielsds = me.getResourceFrom().getForm().getFields();
            if (!Ext.isEmpty(fielsds)) {
                fielsds.each(function(item, index, length) {
                    item.clearInvalid();
                    item.unsetActiveError();
                });
            }
            fielsds = null;
        }
    },
   
   submitFrom: function() {
        var me = this;
        if (me.getResourceFrom().getForm().isValid()) { // 校验form是否通过校验
            var resourceModel = new Butterfly.model.resourceEntity();
            me.getResourceFrom().getForm().updateRecord(resourceModel); // 将FORM中数据设置到MODEL里面
            resourceModel.set('parentRes',{'code':me.getResourceFrom().getForm().findField('parentRes').getValue()});
//           
            var params = {
                'resourceVo': {
                    'resourceEntityDetail': resourceModel.data
       // 'resourceEntityDetail.parentRes.code':me.getResourceFrom().getForm().findField('parentRes').getValue()
                }
            }
            var successFun = function(json) {
            	
                var message = json.message;
                oms.showInfoMsg(message); // 提示新增成功
                me.close();
                me.parent.up().getresourceTree().getStore().load(); // 成功之后重新查询刷新结果集
            };
            var failureFun = function(json) {
                if (Ext.isEmpty(json)) {
                    oms.showErrorMes(baseinfo.resource.i18n('bse.resource.timeout')); // 请求超时
                } else {
                    var message = json.message;
                    oms.showErrorMes(message); // 提示失败原因
                }
            };
            oms.requestJsonAjax('resourceAction!addResource.action', params, successFun, failureFun); // 发送AJAX请求
        }
    },
    updateResource : function(){
    	var me = this;
        if (me.getResourceFrom().getForm().isValid()) { // 校验form是否通过校验
            var resourceModel = new Butterfly.model.resourceEntity();
            me.getResourceFrom().getForm().updateRecord(resourceModel); // 将FORM中数据设置到MODEL里面
            resourceModel.set('parentRes',{'code':me.getResourceFrom().getForm().findField('parentRes').getValue()});
           	resourceModel.set('id',me.getResourceFrom().getForm().findField('id').getValue());
            var params = {
                'resourceVo': {
                    'resourceEntityDetail': resourceModel.data
                }
            }
            
            var successFun = function(json) {
            	
                var message = json.message;
                oms.showInfoMsg(message); // 提示新增成功
                me.close();
                me.parent.up().getresourceTree().getStore().load();// 成功之后重新查询刷新结果集
            };
            var failureFun = function(json) {
                if (Ext.isEmpty(json)) {
                    oms.showErrorMes(baseinfo.resource.i18n('bse.resource.timeout')); // 请求超时
                } else {
                    var message = json.message;
                    oms.showErrorMes(message); // 提示失败原因
                }
            };
            oms.requestJsonAjax('resourceAction!updateResource.action', params, successFun, failureFun); // 发送AJAX请求
        }
    },
    setsave : function(){
    	Ext.getCmp('oms_bse_reset_resource_but').setHidden(false);
    	Ext.getCmp('oms_bse_save_resource_but').setHidden(false);
    	Ext.getCmp('oms_bse_update_resource_but').setHidden(true);
    },
    setupdate : function(){
    	Ext.getCmp('oms_bse_reset_resource_but').setHidden(true);
    	Ext.getCmp('oms_bse_save_resource_but').setHidden(true);
    	Ext.getCmp('oms_bse_update_resource_but').setHidden(false);
    },
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        me.fbar = [{
            text: baseinfo.resource.i18n('oms.common.cancel'),// '取消',
            // 取消
            handler: function() {
                me.close();
            }
        },
        {
            text: baseinfo.resource.i18n('oms.common.reset'),// '重置',
            id : 'oms_bse_reset_resource_but',
            // 重置
            handler: function() {
                me.getResourceFrom().reset();
            }
        },
        {
            text: baseinfo.resource.i18n('oms.common.save'),//'保存',
            id : 'oms_bse_save_resource_but',
            handler: function() {
                me.submitFrom();
            }
        },
        {
            text: baseinfo.resource.i18n('oms.common.update'),//'更新',
            id : 'oms_bse_update_resource_but',
            handler: function() {
                me.updateResource();
            }
        }];
        me.items = [me.getResourceFrom()];
        me.callParent([cfg]);
    }
});
/**
 * 父code下拉框staor
 */
Ext.define('Butterfly.store.resourceSelect',{
	extend : 'Ext.data.Store',
	model : 'Butterfly.model.resourceEntity',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		actionMethods : 'post',
		url : 'resourceAction!queryResourceByExample.action',
		reader : {
			type : 'json',
			rootProperty : 'resourceVo.resourceEntityList',
			totalProperty : 'totalCount' // 总个数
		}
	}
});
/**
 * 父code下拉框
 */
Ext.define('Butterfly.selector.ResourceSelector', {
    extend: 'Butterfly.commonSelector.CommonCombSelector',
    alias: 'widget.resourceselector',
    displayField: 'name',
    // 显示名称
    valueField: 'code',
    // 值
    queryParam: 'resourceVo.resourceEntityDetail.name',
    // 查询参数
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        me.store = Ext.create('Butterfly.store.resourceSelect');
        me.callParent([cfg]);
    }
});
/**
 * 新增，修改，权限URL表单(resource_form)
 */
Ext.define('Butterfly.view.resourceFrom', {
    extend: 'Ext.form.Panel',
    header: false,
    frame: true,
    id : 'oms_bseinfo_resource_form',
    collapsible: true,
    width: 600,
    fieldDefaults: {
        labelWidth: 80,
        margin: '10 10 10 10',
        labelAlign : 'right'
    },
    layout : 'column',

    defaultType: 'textfield',
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        var typeStore = getDataDictionary().getDataDictionaryStore('SYSTEM_TYPE');
        var jurisdictionStore = getDataDictionary().getDataDictionaryStore('JURISDICTION_TYPE');
        var resourceStore = getDataDictionary().getDataDictionaryStore('RESOURCES_LEVEL');
        
        me.items = [{
            name: 'id',
            fieldLabel: 'ID',
            columnWidth: 0.45,
            hidden:true
        },
        	{
            name: 'code',
            fieldLabel: baseinfo.resource.i18n('bse.resource.code'),//'编码',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            columnWidth: 0.45,
            allowBlank: false
        },
        {
            name: 'name',
            fieldLabel: baseinfo.resource.i18n('bse.resource.name'),//'名称',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            columnWidth: 0.45,
            allowBlank: false
        },{
        	name : 'entryUri',
        	fieldLabel : baseinfo.resource.i18n('bse.resource.entryUri'),//'入口URL',
        	columnWidth :0.9
        },{
        	name : 'resLevel',
        	fieldLabel : baseinfo.resource.i18n('bse.resource.resLevel'),//'层级',
        	beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            columnWidth: 0.45,
            
            xtype : 'combo',
			store : resourceStore,
			displayField: 'valueName',
		    valueField: 'valueCode',
            allowBlank: false
        },{
        	name : 'resType',
        	fieldLabel :baseinfo.resource.i18n('bse.resource.resType'),// '权限类型',
        	beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            columnWidth: 0.45,
            xtype : 'combo',
			store : jurisdictionStore,
			displayField: 'valueName',
		    valueField: 'valueCode',
            allowBlank: false
        },{
			id :'oms_bse_resource_sele',
        	name : 'parentRes',
        	fieldLabel :baseinfo.resource.i18n('bse.resource.parentRes'),// '上级',
        	columnWidth :0.45,
        	xtype :'resourceselector'
        },{
        	name : 'displayOrder',
        	fieldLabel : baseinfo.resource.i18n('bse.resource.displayOrder'),//'显示顺序',
        	columnWidth :0.45,
        	xtype: 'numberfield'
        },{

        	name : 'checked',
        	fieldLabel : baseinfo.resource.i18n('bse.resource.checked'),//'是否权限检查',
        	beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            columnWidth: 0.45,
            xtype:'yesnocheckbox',
            allowBlank: false
        },{
        	id : 'oms_bse_resource_addleafflag',
        	name : 'leafFlag',
        	fieldLabel : baseinfo.resource.i18n('bse.resource.leafFlag'),//'是否叶子节点',
        	columnWidth: 0.45,
        	xtype:'yesnocheckbox'
            
        },{
        	name : 'iconCls',
        	fieldLabel : baseinfo.resource.i18n('bse.resource.iconCls'),//'图标样式',
        	columnWidth :0.45
        },{
        	name : 'cls',
        	fieldLabel :baseinfo.resource.i18n('bse.resource.cls'),// '节点样式',
        	columnWidth :0.45
        },{
        	name : 'belongSystemType',
        	fieldLabel : baseinfo.resource.i18n('bse.resource.belongSystemType'),//'所属系统类型',
        	columnWidth :0.45,
        	xtype : 'combo',
			store : typeStore,
			displayField: 'valueName',
		    valueField: 'valueCode'
        	
        },

        {
            name: 'notes',
            fieldLabel:baseinfo.resource.i18n('bse.resource.notes'),// '描述',
            columnWidth: 0.9
        }];
        me.callParent([cfg]);
    }
});


/**
 * 资源权限树store
 */
Ext.define('Butterfly.resource.role.ResourceTreeStore', {
	extend: 'Ext.data.TreeStore',
	root: {
		text:baseinfo.resource.i18n('bse.resource.websystem'),//'WEB系统',
		id : 'web_1'// ,
		// expanded: true
	},
	proxy:{
		type:'ajax',
		url:'resourceAction!queryResourceByParentRes.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			rootProperty:'nodes'
		},
		default:{expanded : true }
	},
	folderSort: true
});

// 定义web权限树
Ext.define('Butterfly.resource.role.RoleTree',{
	extend:'Ext.tree.Panel',
	id: 'Butterfly_resource_RoleTree_Id',
	height : oms.getBrowserHeight() - 40,
// collapsible: true, //是否可隐藏，默认false不能
    useArrows: false, // 显示箭头
    rootVisible: false, // 根节点是否可见
    multiSelect: false,
	animate: false,   // 动画
	autoScroll : true,
	frame: true,
	resTextfield: null,
	getResTextfield: function(){
		var me = this;
		if(this.resTextfield==null){
			this.resTextfield = Ext.create('Ext.form.field.Text',{
				height:25,
				columnWidth: 0.4,
				
		        emptyText: baseinfo.resource.i18n('bse.resource.inputname'),// '输入功能名',
		        /* margin:'0 0 0 19', */
		        name: 'name',
		      /*
				 * regex: /^(\w|[\u4E00-\u9FA5])*$/, regexText: '非法字符',
				 */
		        listeners: {
		        	specialkey: function(field, e){
	                    if (e.getKey() == e.ENTER) {
	                        me.getQueryButton().handler();
	                    }
	                }
		        }
			});
		}
		return this.resTextfield;
	},
	queryButton: null,
	getQueryButton: function(){
		var me = this;
		if(this.queryButton==null){
			this.queryButton = Ext.create('Ext.button.Button',{ 
		    	height:25,
		    	margin:'0 0 0 3',
		    	columnWidth: 0.2,
		    	
		    	text:baseinfo.resource.i18n('oms.common.query'),// '查询',
		    	handler: function(){
		    		var field = me.getResTextfield(),
		    			queryValue = field.getValue();
		    			
		    		if(!Ext.isEmpty(queryValue)){
		    			var params = {'node' : queryValue};
		    			var successFun = function(json) {
		    				var view = me.getView(),
							position = false,
    						pathList = json.pathList;
	    					me.expandNodes = [];
	    					me.collapseAll();
	    					if(pathList.length==0){
	    						/*Ext.ux.Toast.msg(login.i18n('foss.login.messageTitle'),login.i18n('foss.login.notFindMenu'), 'error', 1000);*/
	    						return;
	    					}
	    					for(var i=0;i<pathList.length;i++){
//	    						oms.log(pathList[i]);
	    						me.expandPath(pathList[i],'id','/',function(success, lastNode){
	    							if(success){
	    								var nodeHtmlEl = view.getNode(lastNode),
	    									nodeEl = Ext.get(nodeHtmlEl);
	    								if(Ext.isEmpty(nodeEl)){
	    									me.expandNodes.push(lastNode);
	    									return;
	    								}
	    								var divHtmlEl = nodeEl.query('div')[0],
	    								    divEl = Ext.get(divHtmlEl);
	    								divEl.highlight("ff0000", { attr: 'color', duration:50000 });
										if(!position){
											divHtmlEl.scrollIntoView();
											position = true;
										}
	    							}else{
	    								
	    								oms.showErrorMes(baseinfo.resource.i18n('bse.resource.filedsearch')); //oms.log('查询失败');
	    							}
	    						});	    						
	    					}
		    	        };
		    	        var failureFun = function(json) {
		    	            if (Ext.isEmpty(json)) {
		    	                oms.showErrorMes(baseinfo.resource.i18n('bse.resource.timeout')); // 请求超时
		    	            } else {
		    	                var message = json.message;
		    	                oms.showErrorMes(message);
		    	            }
		    	        };
		    	        //Ajax请求得到所有查询到的节点全路径
		    	        oms.requestJsonAjax('resourceAction!queryTreePathForName.action', params, successFun, failureFun);
		    		}
		    	}
		    });
		}
		return this.queryButton;
	},
	store : Ext.create('Butterfly.resource.role.ResourceTreeStore'),
    columns: [{
            xtype: 'treecolumn', // this is so we know which column will show
									// the tree
            text: baseinfo.resource.i18n('bse.resource.name'),//'名称',
            width: 200,
            sortable: true,
            dataIndex: 'text',
            locked: true
        },{
        	text: 'id',
            width: 150,
            hidden : true,
            dataIndex: 'resourceEntity',
            renderer: function(resource) {
				if(resource==null||resource=='')
					return '';
    			return resource.id;
			}
        
        },{
        	text: baseinfo.resource.i18n('bse.resource.code'),//'编码',
            width: 150,
            dataIndex: 'resourceEntity',
            renderer: function(resource) {
				if(resource==null||resource=='')
					return '';
    			return resource.code;
			},
            sortable: true
        
        },{
        	text: baseinfo.resource.i18n('bse.resource.entryUri'),//'入口URL',
            width: 250,
            dataIndex: 'resourceEntity',
            renderer: function(resource) {
				if(resource==null||resource=='')
					return '';
    			return resource.entryUri;
			}
 // sortable: true
        
        },{
        	text: baseinfo.resource.i18n('bse.resource.resLevel'),//'功能层级',
            width: 100,
            dataIndex: 'resourceEntity',
            renderer: function(resource) {
				if(resource==null||resource=='')
					return '';
    			return resource.resLevel;
			}
        },{
        	text: baseinfo.resource.i18n('bse.resource.seq'),//'显示顺序',
            width: 100,
            dataIndex: 'resourceEntity',
            renderer: function(resource) {
				if(resource==null||resource=='')
					return '';
    			return resource.displayOrder;
			}
        },{
        	text: baseinfo.resource.i18n('bse.resource.checked'),//'权限检查',
            width: 100,
            dataIndex: 'resourceEntity',
            renderer: function(resource) {
				if(resource==null||resource=='')
					return '';
				if(resource.checked=='Y')
					return baseinfo.resource.i18n('bse.resource.yes');//'是';
				if(resource.checked=='N')
					return baseinfo.resource.i18n('bse.resource.no');//'否';
    			return resource.checked;
			}
        },{
        	text: baseinfo.resource.i18n('bse.resource.leafFlag'),//'叶子节点',
            width: 100,
            dataIndex: 'resourceEntity',
            renderer: function(resource) {
				if(resource==null||resource=='')
					return '';
				if(resource.leafFlag=='Y')
					return baseinfo.resource.i18n('bse.resource.yes');//'是';
				if(resource.leafFlag=='N')
					return baseinfo.resource.i18n('bse.resource.no');//'否';
    			return resource.leafFlag;
			}
        },{
        	text: baseinfo.resource.i18n('bse.resource.iconCls'),//'图片样式',
            width: 150,
            dataIndex: 'resourceEntity',
            renderer: function(resource) {
				if(resource==null||resource=='')
					return '';
    			return resource.iconCls;
			}
        },{
        	text: baseinfo.resource.i18n('bse.resource.cls'),//'节点样式',
            width: 150,
            dataIndex: 'resourceEntity',
            renderer: function(resource) {
				if(resource==null||resource=='')
					return '';
    			return resource.cls;
			}
        },{
        	text: baseinfo.resource.i18n('bse.resource.notes'),//'描述',
            width: 150,
            dataIndex: 'resourceEntity',
            renderer: function(resource) {
				if(resource==null||resource=='')
					return '';
    			return resource.notes;
			}
        },{
        	text: baseinfo.resource.i18n('bse.resource.systemtype'),//'系统类型',
            width: 100,
            dataIndex: 'resourceEntity',
            renderer: function(resource) {
				if(resource==null||resource=='')
					return '';
    			return resource.belongSystemType;
			}
        }],
        constructor: function(config){
		var me = this,
			cfg = Ext.apply({}, config);
		me.dockedItems = [{
		    xtype: 'toolbar',
		    dock: 'top',
		    layout: 'column',
		    id: 'mainNavToolbar',
		    items: [me.getResTextfield(),me.getQueryButton()]
		}];
	//	me.initListeners();
		me.callParent([cfg]);
	}

});


Ext.onReady(function() {
	/**
	 * 权限信息页面
	 */
	Ext.QuickTips.init();
	
	var resourceGrid = Ext.create('Butterfly.view.resource.Grid');
	var resourcerouleTree = Ext.create('Butterfly.resource.role.RoleTree');
	
	Ext.create('Ext.panel.Panel', {
		renderTo : Ext.getBody(),
		id : 'resourcePanel',

		getresourceGrid : function() {
			return resourceGrid;
		},
		getresourceTree : function(){
			return resourcerouleTree;
		},
		items : [resourceGrid,resourcerouleTree ]
	});
});
		