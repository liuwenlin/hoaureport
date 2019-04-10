
/**
 * 处理登记详情
 */
Ext.define('oms.view.processRegistra.List',{
	extend : 'Ext.grid.Panel',
	frame : true,
	alias : 'widget.processRegistraList',
	region : 'center',
	stripeRows : true,
	// 交替行效果
	selType : "rowmodel",
	// 选择类型设置为：行选择
	emptyText : '查询结果为空',
	columnLines: true,
	viewConfig: {
        enableTextSelection: true
    },
    region : 'center',
    addWindow :null,
    getAddWindow:function(){
    	
    	if (Ext.isEmpty(this.addWindow)) {
            this.addWindow = Ext.create('oms.view.processRegistra.addwindow');
            this.addWindow.parent=this;
        }
        return this.addWindow;
    },
    pagingToolbar : null,
	getPagingToolbar : function() {
		if (this.pagingToolbar == null) {
			this.pagingToolbar = Ext.create('Ext.toolbar.Paging', {
				store : this.store,
				displayInfo : true,
				plugins: new Ext.ui.plugins.ComboPageSize(),
				pageSize : 20
			});
		}
		return this.pagingToolbar;
	},
	deleteResourceConflict:function(){
		 var me = this;
        var selections = me.getSelectionModel().getSelection();
        if (selections.length < 1) { // 判断是否至少选中了一条
            oms.showWoringMessage('至少选中了一条'); // 请选择一条进行作废操作！//'请至少选择一条进行删除操作'
            return; // 没有则提示并返回
        }
        var list = new Array();
        for (var i = 0; i < selections.length; i++) {
        	list.push({
        		'id': selections[i].get('id')
            });
        }
        oms.showQuestionMes('是否要删除',//'是否要删除',
       	        function(e) {
       	            if (e == 'yes') { // 询问是否删除，是则发送请求
       	                var params = {
       	                    'processRegistraVo': {
       	                        'processRegistraList': list
       	                    }
       	                };
       	                var successFun = function(json) {
       	                    var message = json.message;
       	                    oms.showInfoMsg(message);
       	                    me.getStore().load();
       	                };
       	                var failureFun = function(json) {
       	                    if (Ext.isEmpty(json)) {
       	                        oms.showErrorMes(baseinfo.resourcesConflict.i18n('bse.resourcesConfilict.timeout')); // 请求超时
       	                    } else {
       	                        var message = json.message;
       	                        oms.showErrorMes(message);
       	                    }
       	                };
       	                oms.requestJsonAjax('processRegistraAction!deleteProcessRegistra.action', params, successFun, failureFun);
       	            }
       	        });
	},
	onClickUpdate:function(){
		var me = this;
		var selections = me.getSelectionModel().getSelection(); // 获取选中的数据
		if (selections.length != 1) { // 判断是否选中了一条
			oms.showWoringMessage('请选择一条进行修改'); // '请选择一条进行修改'！///
			return; // 没有则提示并返回
		};
		var params = {
				'limit':5,
				'processRegistraVo' : {
					'processRegistraParams' : {
						'id' : selections[0].get('id')
					}
				}
			};
			var successFun = function (res) {
				// 获取后台返回角色信息
				var result = res.processRegistraVo.processRegistraList;
				if(result==null){
					oms.showErrorMes('刷新当前页面！'); 
					return;
				}
				selections[0].data=result[0];
			};
				var failureFun = function (json) {
					if (Ext.isEmpty(json)) {
						oms.showErrorMes('请查看网络状况！'); // 请求超时
					} else {
						var message = json.message;
						oms.showErrorMes(message);
					}
				};
		oms.requestJsonAjax('processRegistraAction!queryProcessRegistra.action', params, successFun, failureFun,false);
		var win = me.getAddWindow();
		win.setTitle('编辑处理说明');
		win.showUpdateBut();
		win.show();
		win.getroutedetileFrom().getForm().loadRecord(selections[0]);
	},
	listeners : {
		dblclick: {
            element: 'body', //bind to the underlying body property on the panel
            fn: function(){ 
            	Ext.getCmp('procesRegistraViewId').getGrid().onClickUpdate();
			}
        }
	},
	constructor : function(config) {
		var me = this,
		cfg = Ext.apply({}, config);
		me.columns = [  {
			text : 'ID',
			dataIndex : 'id',
			flex : 1,
			hidden :true
		}, {
			text : '处理登记名称',
			dataIndex : 'processRegistraName',
			align: 'center',
			flex : 1
		},{
			text : '处理登记代号',
			dataIndex : 'processRegistraCode',
			align: 'center',
			flex : 1
		},{
			text : '处理登记可用',
			dataIndex : 'processRegistraAvailable',
			align: 'center',
			renderer: function(resource) {
				if(resource=='Y')
					return '是';
				if(resource=='N')
					return '否';
    			return '';
			},
			flex : 1
		},{
			text : '处理登记描述',
			dataIndex : 'processRegistraDescrip',
			align: 'center',
			flex : 3
		}];
		me.tbar = [ {
			xtype : 'addbutton',
			text :  '新增',
			handler : function() {
				me.getAddWindow().show();
				me.getAddWindow().setTitle('新增处理说明');
				me.getAddWindow().showAddBut();
			}
		},'-',{
			xtype :'updatebutton',
			id : 'oms_bse_abnormal_but_update_id',
			disabled : true,
			text : '编辑',
			handler : function() {
				me.onClickUpdate();
			}
		},'-',{
			xtype :'deletebutton',
			id : 'oms_bse_abnormal_but_delete_id',
			disabled : true,
			text : '删除',
			handler : function() {
				me.deleteResourceConflict();
			}
		} ];
		me.store = Ext.create('oms.store.processRegistra', {
			autoLoad : false
		});
		me.selModel = Ext.create('Ext.selection.CheckboxModel', { // 多选框
            mode: 'MULTI',
            listeners: {
            selectionchange: function(sm, selections) {
                Ext.getCmp('oms_bse_abnormal_but_delete_id').setDisabled(selections.length === 0);
               Ext.getCmp('oms_bse_abnormal_but_update_id').setDisabled(selections.length != 1);
            }
            }
        });
		me.bbar = me.getPagingToolbar();
		me.callParent([ cfg ]);

	}
	
});