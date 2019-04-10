/**
 * 询价订单管理Controller
 */
Ext.define('report.controller.monthsql', {
	extend : 'Ext.app.Controller',
	stores : ['monthsql'],
	models : ['monthsql'],
	views : ['Viewport', 'monthsql.List', 'monthsql.Search','monthsql.update','monthsql.Add'],
	init : function() {
		this.control({
			'monthsqlSearch button[action=select]' : { // 查询
				click : this.selectGridStore
			},
			'monthsqlSearch button[action=reset]' : { // 重置
				click : this.resetSearchForm
			},
			'monthsqlList button[action=addbut]' : { // 新增
				click : this.addsql
			},
			'monthsqlList button[action=dupdatebut]' : { // 修改
				click : this.update
			},
			'monthsqlList button[action=deletebut]' : { // 删除
				click : this.delet
			}
		});
	},
	resetSearchForm : function(btn) {
		// 重置表单
		btn.up('form').getForm().reset();
	},
	selectGridStore : function(btn) {
		// 查询
		Ext.getCmp('monthsqlViewId').getMonthSqlqueryGrid().getPagingToolbar().moveFirst();
	},
	addsql:function(){
		// 新增
    	var win = Ext.widget("monthsqlAddWin");
    	win.show();

	},
	update:function(){
		// 修改
		var selectArr = Ext.getCmp('monthsqlViewId').getMonthSqlqueryGrid().getSelectionModel().getSelection();
		if (selectArr.length == 0) {
			 Ext.MessageBox.alert('提示', '请选择一条记录');
			 return;
		} else if (selectArr.length > 1) {
			 Ext.MessageBox.alert('提示', '只能选择一条记录进行操作');
			 return;
		}else{
			var win = Ext.widget("monthsqlUpdateWin");
	    	var form = win.down("form").getForm();
	    	form.record = selectArr[0];
	    	win.down("form").loadRecord(selectArr[0]);
	    	win.show();
		}
    	
	},
	delet : function() {
		//删除
		var selectArr = Ext.getCmp('monthsqlViewId').getMonthSqlqueryGrid().getSelectionModel().getSelection();
		if (selectArr.length == 0) {
			 Ext.MessageBox.alert('提示', '请选择一条记录');
			 return;
		}
		if(selectArr.length > 0){
			Ext.Msg.confirm('提示', '您确定要删除选中的信息？', function(btn) {
				if(btn == 'yes') {
						//删除的信息集合
					var deleteInfoArr = [];
					for(var i=0; i<selectArr.length; i++){
						deleteInfoArr.push(selectArr[i].get("id"))
					}
					// AJAX请求
					Ext.Ajax.request({
						url:"removemonthsql.action",	
						params : {
							'ids':deleteInfoArr
						},
						success:function(response){					
							var result = Ext.decode(response.responseText);
							if(result.success== true){
								Ext.MessageBox.alert('提示', '删除成功', function() {
									Ext.getCmp('monthsqlViewId').getMonthSqlqueryGrid().getStore().reload();
								});
							}else if(result.success== false){
								showInfoMsg(result.message,function(){});
							}else{
								showInfoMsg("系统异常",function(){});
							}
						}
					});	
				}
			});
		}else{
			Ext.MessageBox.alert('提示','请选择需要删除的信息');
		}
		
		
		
		
		
		
	}

});
