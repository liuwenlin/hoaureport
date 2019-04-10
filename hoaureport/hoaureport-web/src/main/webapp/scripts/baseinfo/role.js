/**
 * 获取浏览器高度
 */
oms.getBrowserHeight = function() {
    var browserHeight = document.documentElement.clientHeight;
    return browserHeight;
};
baseinfo.role.type='ROLE_TYPE';

/**
 * 角色model
 */

Ext.define('Butterfly.model.RoleEntity', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'name',
        //角色名称
        type: 'string'
    },
    {
        name: 'code',
        //角色编号
        type: 'string'
    },
    {
        name: 'notes',
        //角色描述
        type: 'string'
    },
    {
        name: 'active',
        //是否启用
        type: 'string'
    },
    {
        name: 'type',
        //角色类型
        type: 'string'
    }
    ,
    {
       //修改日期
       name: 'modifyDate', 
       type : 'date', 
       dateFormat:'time'
    }]
});

/**
 * 角色stores
 */
Ext.define('Butterfly.store.RoleStore', {
    extend: 'Ext.data.Store',
    model: 'Butterfly.model.RoleEntity',
    pageSize: 20,
    proxy: {
        type: 'ajax',
        actionMethods: 'post',
        url: 'roleAction!queryRole.action',
        reader: {
            type: 'json',
            rootProperty: 'roleVo.roleEntityList',
            totalProperty: 'totalCount' //总个数
        }
    },
    listeners: {
        beforeload: function(store, operation, eOpts) {
            var queryForm = Ext.getCmp('queryForm');
            if (queryForm != null) {
                var params = {
                    'roleVo.roleEntity.code': queryForm.getForm().findField('code').getValue(),
                    'roleVo.roleEntity.name': queryForm.getForm().findField('name').getValue()
                    };
                Ext.apply(store.proxy.extraParams, params);
            }
        }
    }
});


/**
 * 资源权限树model
 */
Ext.define('Butterfly.model.role.ResourceTreeModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name:'id'},
        {name:'text'},
        {name:'leaf'},
        {name:'checked'}
    ]
});

/**
 * 资源权限树store
 */
Ext.define('Butterfly.store.role.ResourceTreeStore', {
	extend: 'Ext.data.TreeStore',
    model: 'Butterfly.model.role.ResourceTreeModel',
	proxy:{
		type:'ajax',
		url:'roleAction!loadRoleResourceTree.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			rootProperty:'roleVo.nodes'
			
		},
		"default":{expanded : true}
	},
	listeners : {
		beforeload : function(store, operation, eOpts) {
			// 选中的数据
	    	var selectArr = Ext.getCmp('roleGrid').getSelectionModel().getSelection();
			var searchParams = store.proxy.extraParams;
			var params = {
					'node' : searchParams.node,
					'roleVo.roleEntity.code' : selectArr.length > 0 ? selectArr[0].get('code') : ''
				}
			Ext.apply(store.proxy.extraParams, params);  
		}
	}
});
/**
 * 查询表单
 */
Ext.define('Butterfly.view.role.QueryForm', {
    extend: 'Ext.form.Panel',
    id: 'queryForm',
    frame: true,
//    title: baseinfo.role.i18n('oms.common.queryCondition'),//查询条件
    //查询条件
    height: 120,
    collapsible: false,
    layout: 'hbox',
    defaults: {
        colspan: 1,
        margin: '8 10 5 10'
    },
    defaultType: 'textfield',
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        me.items = [{
            name: 'code',
            fieldLabel: baseinfo.role.i18n('bse.org.code'),//角色编号
            width: 250,
            xtype: 'textfield'
        },
        {
            name: 'name',
            fieldLabel:baseinfo.role.i18n('bse.org.name'),//角色名称
            width: 250,
            xtype: 'textfield'
        }],
        me.buttons = [{
            text:baseinfo.role.i18n('oms.common.query'),//查询
            handler: function() {
                if (me.getForm().isValid()) {
                    me.up().getRoleGrid().getPagingToolbar().moveFirst();
                }
            }
        },
        {
            text: baseinfo.role.i18n('bse.org.empty'),//清空
            handler: function() {
                me.getForm().reset();
            }
        }];
        me.callParent([cfg]);
    }
});

/**
 * 角色信息表格
 */
Ext.define('Butterfly.view.role.Grid', {
    extend: 'Ext.grid.Panel',
    id : 'roleGrid',
    frame: true,
    autoScroll: true,
    height: oms.getBrowserHeight() - 120,
    stripeRows: true,
    // 交替行效果
    selType: "rowmodel",
    // 选择类型设置为：行选择
    emptyText:baseinfo.role.i18n('bse.org.queryResult'),//查询结果为空
    columnLines: true,
    viewConfig: {
        enableTextSelection: true
    },
    roleAddWindow: null,
    getRoleAddWindow: function() {
        if (this.roleAddWindow == null) {
            this.roleAddWindow = Ext.create('Butterfly.view.role.roleAddWindow');
            this.roleAddWindow.parent = this; //父元素
        }
        return this.roleAddWindow;
    },
    roleUpdateWindow: null,
    getRoleUpdateWindow: function() {
        if (this.roleUpdateWindow == null) {
            this.roleUpdateWindow = Ext.create('Butterfly.view.role.roleUpdateWindow');
            this.roleUpdateWindow.parent = this; //父元素
        }
        return this.roleUpdateWindow;
    },
    resourceWindow: null,
    getResourceWindow:function(){
    	if (this.resourceWindow == null) {
            this.resourceWindow = Ext.create('Butterfly.baseinfo.role.ResourceWindow');
            this.resourceWindow.parent = this; //父元素
        }
        return this.resourceWindow;
    },    
    deleteRole:function() {
    	var me = this;
        var selections = me.getSelectionModel().getSelection(); //获取选中的数据
        if (selections.length < 1) { //判断是否至少选中了一条
            oms.showWoringMessage('请选择一条进行删除操作'); //请选择一条进行作废操作！
            return; //没有则提示并返回
        }
        var roleEntityList = new Array();
        for (var i = 0; i < selections.length; i++) {
            roleEntityList.push({
                'code': selections[i].get('code')
            });
        }
        oms.showQuestionMes('是否要删除',
        function(e) {
            if (e == 'yes') { //询问是否删除，是则发送请求
                var params = {
                    'roleVo': {
                        'roleEntityList': roleEntityList
                    }
                };
                var successFun = function(json) {
                    var message = json.message;
                    oms.showInfoMsg(message);
                    me.getStore().load();
                };
                var failureFun = function(json) {
                    if (Ext.isEmpty(json)) {
                        oms.showErrorMes('请求超时'); //请求超时
                    } else {
                        var message = json.message;
                        oms.showErrorMes(message);
                    }
                };
                oms.requestJsonAjax('roleAction!deleteRole.action', params, successFun, failureFun);
            }
        });
    },
    updateRole : function(){
		var me = this;
		var selections = me.getSelectionModel().getSelection(); //获取选中的数据
		if (selections.length != 1) { //判断是否至少选中了一条
			oms.showWoringMessage('请选择一条进行修改操作'); //请选择一条进行作废操作！
			return; //没有则提示并返回
		}
		var code = selections[0].get('code');
		var params = {
				'roleVo' :{
					'roleEntity' : {
						'code' : code
					}
				}
			};
		var successFun = function (json) {
			var updateWindow = me.getRoleUpdateWindow(); //获得修改窗口
			updateWindow.roleEntity = json.roleVo.roleEntity;
			updateWindow.show(); //显示修改窗口
		};
		var failureFun = function (json) {
			if (Ext.isEmpty(json)) {
				oms.showErrorMes('请求超时'); //请求超时
			} else {
				var message = json.message;
				oms.showErrorMes(message);
			}
		};
		oms.requestJsonAjax('roleAction!queryRoleByCode.action', params, successFun, failureFun);
	},
    pagingToolbar: null,
    getPagingToolbar: function() {
        if (this.pagingToolbar == null) {
            this.pagingToolbar = Ext.create('Ext.toolbar.Paging', {
                store: this.store,
                pageSize: 20
            });
        }
        return this.pagingToolbar;
    },
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        me.columns = [{
            text:baseinfo.role.i18n('bse.org.rownumberer'),//序号
            dataIndex: 'valueSeq',
            xtype : 'rownumberer',
            align : 'center',
            width: 60
        },
        {
            dataIndex: 'code',
            width: 250,
            //flex : 1,
            text: baseinfo.role.i18n('bse.org.code')//角色编号
        },
        {
            text: baseinfo.role.i18n('bse.org.name'),//角色名称
            dataIndex: 'name',
            width:250
            //flex : 1
        },
        {
            text: '角色类型',//角色类型
            dataIndex: 'type',
            width:250,
            renderer : function (value) {
				return oms.changeCodeToNameStore(getDataDictionary().getDataDictionaryStore(baseinfo.role.type),value);
			}
            //flex : 1
        },
        {
            text: baseinfo.role.i18n('bse.org.notes'),
            dataIndex: 'notes',//描述
            width: 300
            //flex : 1
        },
        {
        	text: baseinfo.role.i18n('bse.org.modifyDate'),
            dataIndex: 'modifyDate',//修改时间
            width: 200,
            //flex : 1,
            xtype :'datecolumn', 
            format :'Y-m-d H:i:s'
        }],
        me.store = Ext.create('Butterfly.store.RoleStore', {
            autoLoad: false
        });
        me.selModel = Ext.create('Ext.selection.CheckboxModel', {
	        listeners: {
	            selectionchange: function(sm, selections) {
	                Ext.getCmp('oms_baseinfo_role_roleupdatebtn_id').setDisabled(selections.length !== 1);
	                Ext.getCmp('oms_baseinfo_role_roledeletebtn_id').setDisabled(selections.length === 0);
	                Ext.getCmp('oms_baseinfo_role_roleresourcesbtn_id').setDisabled(selections.length === 0);
	            }
	        }
    	}),
        me.tbar = [{
        	xtype: 'addbutton',
            text:baseinfo.role.i18n('oms.common.add'),//新增
            handler: function() {
                me.getRoleAddWindow().show();
            }
        },
        '-', {
        	id:'oms_baseinfo_role_roleupdatebtn_id',
        	xtype: 'updatebutton',
            text: baseinfo.role.i18n('oms.common.update'),//修改
            disabled:true,
            handler: function() {
                me.updateRole();
            }
        },
        '-', {
        	id:'oms_baseinfo_role_roledeletebtn_id',
        	xtype: 'deletebutton',
            text:baseinfo.role.i18n('oms.common.delete'),//删除
            disabled:true,
            handler: function() {
                me.deleteRole();
            }
        }, {
        	id:'oms_baseinfo_role_roleresourcesbtn_id',
        	xtype: 'settingsbutton',
            text:baseinfo.role.i18n('bse.org.permissions'),//权限配置
            disabled:true,
            handler: function() {
                me.getResourceWindow().show();
            }
        }];
        me.bbar = me.getPagingToolbar();
        me.callParent([cfg]);
    }
});


/**
 * 角色表单
 */
Ext.define('Butterfly.view.role.roleForm', {
    extend: 'Ext.form.Panel',
    header: false,
    frame: true,
    collapsible: true,
    width: 280,
    fieldDefaults: {
        labelWidth: 80,
        margin: '8 10 5 10'
    },
    defaultType: 'textfield',
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        me.items = [{
            name: 'code',
            fieldLabel:baseinfo.role.i18n('bse.org.code'),//角色编号
            allowBlank: false
           
        },
        {
            name: 'name',
            fieldLabel:baseinfo.role.i18n('bse.org.name'),//角色名称
            allowBlank: false
        },
        {
            name: 'type',
            fieldLabel:'角色类型',//角色类型
            allowBlank: false,
            xtype : 'dataDictionarySelector',
            termsCode :baseinfo.role.type
        },
        {
            name: 'notes',
            fieldLabel:baseinfo.role.i18n('bse.org.notes'),//描述
            xtype: 'textarea'
        } ];
        me.callParent([cfg]);
    }
});


/**
 * 修改角色窗口
 */
	Ext.define('Butterfly.view.role.roleUpdateWindow',{
		extend : 'Ext.window.Window',
		title : baseinfo.role.i18n('bse.org.updateRoleInfo'),//修改角色信息
		closable : true,
		parent : null, // 父元素
		modal : true,
		resizable : false, // 可以调整窗口的大小
		closeAction : 'hide', // 点击关闭是隐藏窗口
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		roleEntity: null,
		listeners : {
			beforehide : function(me) { // 隐藏WINDOW的时候清除数据
				me.getRoleForm().getForm().reset(); // 表格重置
			},
			beforeshow : function(me) { // 显示WINDOW的时候清除数据
				var fielsds = me.getRoleForm().getForm().getFields();
				if (!Ext.isEmpty(fielsds)) {
					fielsds.each(function(item, index, length) {
						item.clearInvalid();
						item.unsetActiveError();
					});
				}
				fielsds = null;
				me.getRoleForm().getForm().loadRecord(new Butterfly.model.RoleEntity(me.roleEntity));
				//角色号不可编辑				
				var code = me.getRoleForm().getForm().findField('code');
				code.setReadOnly(true);
			}
		},
		roleForm : null,
		getRoleForm : function() {
			if (Ext.isEmpty(this.roleForm)) {
				this.roleForm = Ext.create('Butterfly.view.role.roleForm');
			}
			return this.roleForm;
		},
		submitDriver:function(){
			var me = this;
			if (me.getRoleForm().getForm().isValid()) { //校验form是否通过校验
				var roleModel = new Butterfly.model.RoleEntity();
				me.getRoleForm().getForm().updateRecord(roleModel); //将FORM中数据设置到MODEL里面
				var params = {
						 'roleVo': {
			                    'roleEntity': roleModel.data
			                }
				}; 
				var successFun = function (json) {
					var message = json.message;
					oms.showInfoMsg(message); //提示新增成功
					me.close();
					me.parent.getStore().load(); //成功之后重新查询刷新结果集
				};
				var failureFun = function (json) {
					if (Ext.isEmpty(json)) {
						oms.showErrorMes('连接超时'); //请求超时
					} else {
						var message = json.message;
						oms.showErrorMes(message); //提示失败原因
					}
				};
				oms.requestJsonAjax('roleAction!updateRole.action', params, successFun, failureFun); //发送AJAX请求
			}
		},
		constructor : function(config) {
			var me = this, cfg = Ext.apply({}, config);
			me.fbar = [ {
				text :baseinfo.role.i18n('oms.common.cancel'), //取消
				handler : function() {
					me.close();
				}
			}, {
				text :baseinfo.role.i18n('oms.common.reset'), //重置
				handler : function() {
					 me.getRoleForm().getForm().loadRecord(
					 new Butterfly.model.RoleEntity({
						 'code' : me.roleEntity.code
					 }));
				}
			}, {
				text :baseinfo.role.i18n('oms.common.save'), //保存
				margin : '0 0 0 305',
				handler : function() {
					 me.submitDriver();
				}
			} ];
			me.items = [ me.getRoleForm() ];
			me.callParent([ cfg ]);
		}
	});



/**
 * 新增角色窗口
 */
Ext.define('Butterfly.view.role.roleAddWindow', {
    extend: 'Ext.window.Window',
    title: baseinfo.role.i18n('bse.org.insertRoleInfo'),//新增角色信息
    closable: true,
    parent: null,
    // 父元素
    modal: true,
    resizable: false,
    // 可以调整窗口的大小
    closeAction: 'hide',
    // 点击关闭是隐藏窗口
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    listeners: {
        beforehide: function(me) { // 隐藏WINDOW的时候清除数据
            me.getRoleForm().getForm().reset(); // 表格重置
        },
        beforeshow: function(me) { // 显示WINDOW的时候清除数据
            var fielsds = me.getRoleForm().getForm().getFields();
            if (!Ext.isEmpty(fielsds)) {
                fielsds.each(function(item, index, length) {
                    item.clearInvalid();
                    item.unsetActiveError();
                });
            }
            fielsds = null;
        }
    },
    roleForm: null,
    getRoleForm: function() {
        if (Ext.isEmpty(this.roleForm)) {
            this.roleForm = Ext.create('Butterfly.view.role.roleForm');
        }
        return this.roleForm;
    },
    submitRole: function() {
    	
        var me = this;
        if (me.getRoleForm().getForm().isValid()) { //校验form是否通过校验
        	
            var roleModel = new Butterfly.model.RoleEntity();
            me.getRoleForm().getForm().updateRecord(roleModel); //将FORM中数据设置到MODEL里面
            var params = {
                'roleVo': {
                    'roleEntity': roleModel.data
                }
            }
            var successFun = function(json) {
                var message = json.message;
                oms.showInfoMsg(message); //提示新增成功
                me.close();
                me.parent.getStore().load(); //成功之后重新查询刷新结果集
            };
            var failureFun = function(json) {
                if (Ext.isEmpty(json)) {
                    oms.showErrorMes('连接超时'); //请求超时
                } else {
                    var message = json.message;
                    oms.showErrorMes(message); //提示失败原因
                }
            };
            oms.requestJsonAjax('roleAction!addRole.action', params, successFun, failureFun); //发送AJAX请求
          
        }
    },
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        me.fbar = [{
            text:baseinfo.role.i18n('oms.common.cancel'),//取消
            handler: function() {
                me.close();
            }
        },
        {
            text:baseinfo.role.i18n('oms.common.reset'),//重置
            handler: function() {
                me.getRoleForm().reset();
            }
        },
        {
        	text:baseinfo.role.i18n('oms.common.save'),//保存
            margin : '0 0 0 55',
            handler: function() {
                me.submitRole();
            }
        }];
        me.items = [me.getRoleForm()];
        me.callParent([cfg]);
    }
});

/**
 * 权限配置窗口
 */
Ext.define('Butterfly.view.role.ResourceWindow', {
    extend: 'Ext.window.Window',
    closable: true,
    parent: null,
    title:baseinfo.role.i18n('bse.org.rolePermissions'),//角色权限配置
    closeAction : 'hide',
    width: 400,
    height: 400,
    layout: "fit",
    modal:true,
    treePanel :null,
    getTreePanel : function(){
    	if(Ext.isEmpty(this.treePanel)){
    		this.treePanel = Ext.create('Butterfly.view.role.ResourceTree');
    	}
    	return this.treePanel
    },
    listeners: {
        beforeshow: function(me) { 
        	// 显示WINDOW的时候重新加载树
        	me.getTreePanel().getStore().load();
        }
    },
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        me.fbar = [{
            text:baseinfo.role.i18n('bse.org.allSpread')//全部展开
        },{
            text:baseinfo.role.i18n('oms.common.submit')//提交
        }];
        me.items = [me.getTreePanel()];
        me.callParent([cfg]);
    }
});

/**
 * 权限树
 */
Ext.define("Butterfly.view.role.ResourceTree", {
	extend : 'Ext.tree.TreePanel',
	store: Ext.create('Butterfly.store.role.ResourceTreeStore'),
	border:false,
	split: true,
    split:true,//可以合并
	//动画效果
	animate:true,
    autoScroll: true,
	//树节点是否可见
    rootVisible: false,
    //使用vista风格的箭头图标，默认为false
    useArrows: true
});

Ext.onReady(function() {
    Ext.QuickTips.init();
    var queryForm = Ext.create('Butterfly.view.role.QueryForm'); //查询FORM
    var roleGrid = Ext.create('Butterfly.view.role.Grid');
    Ext.create('Ext.panel.Panel', {
        renderTo: Ext.getBody(),
        getQueryForm: function() {
            return queryForm;
        },
        getRoleGrid: function() {
            return roleGrid;
        },
        items: [queryForm, roleGrid]
    });
});