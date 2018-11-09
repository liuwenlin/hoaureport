Ext.define('Butterfly.model.login.UserEntity', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'userName',
		type : 'string'
	}, {
		name : 'password',
		type : 'string'
	} ]
});

/**
 * 登录表单
 */
Ext.define('Butterfly.view.login.LoginForm', {
	extend : 'Ext.form.Panel',
	frame : true,
	width : 410,
	id : 'loginFormId',
	defaultType : 'textfield',
	cls : 'ui_login_form',
	fieldDefaults: {
        margin: '0 0 20 0'
    },
	items : [{
		id : 'usernameId',
		allowBlank : false,
		name : 'userName',
		cls : 'ui_login_input_user',
		fieldCls : 'ui_login_input_size',
		emptyText : '请输入您的用户名'
	},{
		allowBlank : false,
		name : 'password',
		cls : 'ui_login_input_lock',
		fieldCls : 'ui_login_input_size',
		emptyText : '请输入您的密码',
		inputType : 'password'
	}],
/*,
	buttons : [
			{
				text : '重置',
				handler : function() {
					this.up('form').getForm().reset();
				}
			},
			{
				text : '登录',
				handler : function() {
					var form = this.up('form').getForm();
					if (form.isValid()) { // 校验form是否通过校验
						var userModel = new Butterfly.model.login.UserEntity();
						form.updateRecord(userModel); // 将FORM中数据设置到MODEL里面
						var params = {
							'currentUser' : userModel.data
						};
						var successFun = function(json) {
							window.location.href = "main.action";
						};
						var failureFun = function(json) {
							if (Ext.isEmpty(json)) {
								Butterfly.showErrorMes('连接超时'); // 请求超时
							} else {
								var message = json.message;
								Butterfly.showErrorMes(message); // 提示失败原因
							}
						};
						Butterfly.requestJsonAjax('login.action', params, successFun,
								failureFun); // 发送AJAX请求
					}
				}
			} ],
*/
	initComponent : function() {
		this.defaults = {
			anchor : '100%',
			labelWidth : 60
		};
		this.callParent();
	}
});

function login(){
	var form = Ext.getCmp("loginFormId").getForm();
	if (form.isValid()) { // 校验form是否通过校验
		var userModel = new Butterfly.model.login.UserEntity();
		form.updateRecord(userModel); // 将FORM中数据设置到MODEL里面
		var params = {
			'currentUser' : userModel.data
		};
		var successFun = function(json) {
			window.location.href = "index.action";
		};
		var failureFun = function(json) {
			if (Ext.isEmpty(json)) {
				//Butterfly.showErrorMes('连接超时'); // 请求超时
				document.getElementById("msg").style.display = "block";
				document.getElementById("error").innerText = "连接超时!";
			} else {
				var message = json.message;
				//Butterfly.showErrorMes(message); // 提示失败原因
				document.getElementById("msg").style.display = "block";
				document.getElementById("error").innerText = message+"!";
			}
		};
		oms.requestJsonAjax('loginAction!login.action', params, successFun,
				failureFun); // 发送AJAX请求
	}
}

Ext.onReady(function() {
	Ext.QuickTips.init();
	var loginForm = Ext.create('Butterfly.view.login.LoginForm');
	Ext.create('Ext.panel.Panel', {
		renderTo : 'login_form_div',
		items : [ loginForm ]
	});
	Ext.getCmp('usernameId').focus(false,100)
});