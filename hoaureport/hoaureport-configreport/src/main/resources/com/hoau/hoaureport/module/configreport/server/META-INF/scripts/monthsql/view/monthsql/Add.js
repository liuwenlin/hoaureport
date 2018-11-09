/**
 * 新增信息WINDOW
 */
Ext.define("report.view.monthsql.Add", {
//			id : "monthsqlAddWin",
			extend : "Ext.window.Window",
			alias : "widget.monthsqlAddWin",
			title : "新增",
			width : '90%',
			height : '80%',
			autoScroll : true, // 显示滚动条
			modal : true,
			items : {
				xtype : "form",
				border : true,
				record : null,
				layout:'column',
				fieldDefaults : {
					labelAlign : 'right',
					columnWidth : 0.47,
					margin : '5 5 5 5',
					multi: true,
					labelWidth : 100
				},
				items : [
		         		    {
								fieldLabel : '报表名称',
								xtype:'querySqlcombselector',
								name:'sqlId',
								allowBlank : false
						    },{
						    	fieldLabel : '顺序',
								allowBlank : false,
								name : 'sqlSeq',
								xtype : 'numberfield',
								minValue: 0,
				                maxValue: 9999,
								allowNegative: false, //不允许未负数
								allowDecimals: false  //不允许为小数
						    },{
								fieldLabel : '编码格式',
								xtype : 'textfield',
								name : 'charCode',
								editable : false,
								xtype : 'combobox',
								allowBlank : false,
								store : [['GBK', "编码格式：GBK"], ['UTF-8', "编码格式：UTF-8"]]
								
						    },{
						    	fieldLabel : '类型',
								name : 'sqlType',
								editable : false,
								allowBlank : false,
								xtype : 'combobox',
								store : [['1001', "整月"], ['1002', "运单物流时间上月16号"]]
						    },{
								fieldLabel : '表头字段',
								xtype : 'textareafield', 
								allowBlank : false,
								columnWidth : 0.94,
								height:80,
	//							grow:true,
								name : 'sqlName'
						    },{
								fieldLabel : 'SQL语句',
								xtype : 'textareafield',
								allowBlank : false,
								columnWidth : 0.94,
								height:180,
								name : 'sqlBodyStr'
						    }
						
				]
			},
			buttons : [{
							text : '重置',
							handler : function() {
								var win =this.up('window');
								var form = win.down('form').getForm();
								form.reset();
							}
						},{
							text : '提交',
							handler : function() {
								var win =this.up('window');
								var form = win.down('form').getForm();
								if (form.isValid()) {
									form.submit({
										url : 'addmonthsql.action',
										waitMsg : '信息提交中,请稍候...',
										params : {
											'sqlEntity.remark' : form.findField('sqlId').rawValue,
											'sqlEntity.sqlId' : form.findField('sqlId').getValue(),
											'sqlEntity.sqlName' : form.findField('sqlName').getValue(),
											'sqlEntity.sqlBodyStr' : form.findField('sqlBodyStr').getValue(),
											'sqlEntity.sqlSeq' : form.findField('sqlSeq').getValue(),
											'sqlEntity.sqlType' : form.findField('sqlType').getValue(),
											'sqlEntity.charCode' : form.findField('charCode').getValue()
										
										},
										success : function(form, response) {
											Ext.MessageBox.alert('提示', '提交成功', function() {
												win.close();
												Ext.getCmp('monthsqlViewId').getMonthSqlqueryGrid().getStore().reload();
											});
										},
										failure : function(form, response) {
											var result = response.result;
											if (result.message == undefined) {
												Ext.MessageBox.alert('提示', "系统异常,请联系系统管理员!");
											} else {
												// 业务异常
												Ext.MessageBox.alert('提示', result.message);
											}
										}
									});
								}
							}
						},{
							text : '取消',
							handler:function(){
								this.up('window').close();
							}
						}]
						
		
			}
		
);

