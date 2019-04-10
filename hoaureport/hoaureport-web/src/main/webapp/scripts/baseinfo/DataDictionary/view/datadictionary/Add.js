Ext.define('oms.view.datadictionary.Add',{
	extend : 'Ext.window.Window',
	title : '新增词条',
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
			me.getDataDictionaryForm().getForm().reset(); // 表格重置
		},
		beforeshow : function(me) { // 显示WINDOW的时候清除数据
			var fielsds = me.getDataDictionaryForm().getForm().getFields();
			if (!Ext.isEmpty(fielsds)) {
				fielsds.each(function(item, index, length) {
					item.clearInvalid();
					item.unsetActiveError();
				});
			}
			fielsds = null;
		}
	},
	dataDictionaryForm : null,
	getDataDictionaryForm : function() {
		if (Ext.isEmpty(this.dataDictionaryForm)) {
			this.dataDictionaryForm = Ext.create('oms.view.datadictionary.DataDictionaryForm');
		}
		return this.dataDictionaryForm;
	},
	submitDataDictionaryValue:function(){
		var me = this;
		if (me.getDataDictionaryForm().getForm().isValid()) { //校验form是否通过校验
			var dataDictionaryModel = new oms.model.DataDictionary();
			me.getDataDictionaryForm().getForm().updateRecord(dataDictionaryModel); //将FORM中数据设置到MODEL里面
			var params = {
					'dataDictionaryVo':{
						'dataDictionaryEntity':dataDictionaryModel.data
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
			oms.requestJsonAjax('dataDictionaryAction!addDataDictionary.action', params, successFun, failureFun); //发送AJAX请求
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
		me.items = [ me.getDataDictionaryForm() ];
		me.callParent([ cfg ]);
	}
});


/**
 * 数据字典词条表单
 */
Ext.define('oms.view.datadictionary.DataDictionaryForm',{
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
        	name : 'termsName',
            fieldLabel: '词条名称',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },{
        	name : 'termsCode',
            fieldLabel: '词条代码',
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