/**
 * 
 * window 弹出框
 */
Ext.define('oms.view.abnormalProject.addwindow',{
	extend: 'Ext.window.Window',
	alias : "widget.addwindow",
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
    listeners: {
        beforehide: function(me) { // 隐藏WINDOW的时候清除数据
            me.getAddFrom().getForm().reset(); // 表格重置
        },
        beforeshow: function(me) { // 显示WINDOW的时候清除数据
            var fielsds = me.getAddFrom().getForm().getFields();
            if (!Ext.isEmpty(fielsds)) {
                fielsds.each(function(item, index, length) {
                    item.clearInvalid();
                    item.unsetActiveError();
                });
            }
            fielsds = null;
        }
    },
    routedetileFrom: null,
    getAddFrom: function() {
        if (Ext.isEmpty(this.routedetileFrom)) {
            this.routedetileFrom = Ext.create('oms.view.abnormalProject.addForm');
        }
        return this.routedetileFrom;
    },
    submitAdd: function() {
        var me = this;
        if (me.getAddFrom().getForm().isValid()) { // 校验form是否通过校验
            var addModel = new oms.model.abnormalProject();
            me.getAddFrom().getForm().updateRecord(addModel); // 将FORM中数据设置到MODEL里面
            var params = {
                'abnormalProjectVo': {
                    'abnormalProjectEntity': addModel.data
                }
            }
            var successFun = function(json) {
            	
                var message = json.message;
                oms.showInfoMsg(message); // 提示新增成功
                me.close();
                me.parent.getStore().load(); // 成功之后重新查询刷新结果集
            };
            var failureFun = function(json) {
                if (Ext.isEmpty(json)) {
                    oms.showErrorMes('请求超时'); // 请求超时
                } else {
                    var message = json.message;
                    oms.showErrorMes(message); // 提示失败原因
                }
            };
            oms.requestJsonAjax('abnormalProjectAction!addAbnormalProject.action', params, successFun, failureFun); // 发送AJAX请求
        }
    },
    submitUpdate:function(){
    	 var me = this;
        if (me.getAddFrom().getForm().isValid()) { // 校验form是否通过校验
            var addModel = new oms.model.abnormalProject();
            me.getAddFrom().getForm().updateRecord(addModel); // 将FORM中数据设置到MODEL里面
            var params = {
                'abnormalProjectVo': {
                    'abnormalProjectEntity': addModel.data
                }
            }
            var successFun = function(json) {
            	
                var message = json.message;
                oms.showInfoMsg(message); // 提示新增成功
                me.close();
                me.parent.getStore().load(); // 成功之后重新查询刷新结果集
            };
            var failureFun = function(json) {
                if (Ext.isEmpty(json)) {
                    oms.showErrorMes('请求超时'); // 请求超时
                } else {
                    var message = json.message;
                    oms.showErrorMes(message); // 提示失败原因
                }
            };
            oms.requestJsonAjax('abnormalProjectAction!updateAbnormalProject.action', params, successFun, failureFun); // 发送AJAX请求
        }
    },
    showAddBut:function(){
    	Ext.getCmp('bse_addwin_from_add_id').setHidden(false);
    	Ext.getCmp('bse_addwin_from_update_id').setHidden(true);
    },
    showUpdateBut:function(){
    	Ext.getCmp('bse_addwin_from_add_id').setHidden(true);
    	Ext.getCmp('bse_addwin_from_update_id').setHidden(false);
    },
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        me.fbar = [{
            text: '保存',
            id:'bse_addwin_from_add_id',
            handler: function() {
                me.submitAdd();
            }
        },{
            text: '保存',
            id:'bse_addwin_from_update_id',
            handler: function() {
                me.submitUpdate();
            }
        },{
            text: '取消',
            // 取消
            handler: function() {
                me.close();
            }
        }
        ];
        me.items = [me.getAddFrom()];
        me.callParent([cfg]);
    }
});
