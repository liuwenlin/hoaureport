/**
 * 新增数据字典窗口
 */
Ext.define('oms.view.datadictionary.AddValue',{
	extend : 'Ext.window.Window',
	title : '新增数据字典',
	closable : true,
	parent : null, // 父元素
	modal : true,
	resizable : false, // 可以调整窗口的大小
	closeAction : 'hide', // 点击关闭是隐藏窗口
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	listeners : {
		beforehide : function(me) { // 隐藏WINDOW的时候清除数据
			me.getDataDictionaryValueForm().getForm().reset(); // 表格重置
		},
		beforeshow : function(me) { // 显示WINDOW的时候清除数据
			var fielsds = me.getDataDictionaryValueForm().getForm().getFields();
			if (!Ext.isEmpty(fielsds)) {
				fielsds.each(function(item, index, length) {
					item.clearInvalid();
					item.unsetActiveError();
				});
			}
			fielsds = null;
		}
	},
	dataDictionaryValueForm : null,
	getDataDictionaryValueForm : function() {
		if (Ext.isEmpty(this.dataDictionaryValueForm)) {
			this.dataDictionaryValueForm = Ext.create('oms.view.datadictionary.DataDictionaryValueForm');
		}
		return this.dataDictionaryValueForm;
	},
	submitDataDictionaryValue:function(){
		var me = this;
		if (me.getDataDictionaryValueForm().getForm().isValid()) { //校验form是否通过校验
			var dataDictionaryValueModel = new oms.model.DataDictionaryValue();
			me.getDataDictionaryValueForm().getForm().updateRecord(dataDictionaryValueModel); //将FORM中数据设置到MODEL里面
			var params = {
					'dataDictionaryVo':{
						'dataDictionaryValueEntity':dataDictionaryValueModel.data
					}
			}
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
			oms.requestJsonAjax('dataDictionaryAction!addDataDictionaryValue.action', params, successFun, failureFun); //发送AJAX请求
		}
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.fbar = [ {
			text : '取消', // 取消
			handler : function() {
				me.close();
			}
		}, {
			text : '重置', // 重置
			handler : function() {
				me.getDataDictionaryValueForm().reset();
			}
		}, {
			text : '保存', // 保存
			/*margin : '0 0 0 55',*/
			handler : function() {
				 me.submitDataDictionaryValue();
			}
		} ];
		me.items = [ me.getDataDictionaryValueForm() ];
		me.callParent([ cfg ]);
	}
});


/**
 * 数据字典表单
 */
Ext.define('oms.view.datadictionary.DataDictionaryValueForm',{
	extend : 'Ext.form.Panel',
	header : false,
	frame : true,
	collapsible : true,
	width: 280,
	fieldDefaults : {
        labelWidth: 80,
        margin : '8 10 5 10'
	},
	defaultType: 'textfield',
	constructor : function(config){
		var me = this,
		cfg = Ext.apply({}, config);
		me.items = [{
        	name : 'termsCode',
            fieldLabel: '词条名称',
            xtype : 'dataDictionarySelector',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },{
        	name : 'valueSeq',
            fieldLabel: '序号',
            xtype : 'numberfield',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },{
        	name : 'valueCode',
            fieldLabel: '值代码',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },{
        	name : 'valueName',
            fieldLabel: '值名称',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },{
        	name : 'noteInfo',
            fieldLabel: '备注',
            xtype:'textarea'
        }];
		me.callParent([cfg]);
	}
});