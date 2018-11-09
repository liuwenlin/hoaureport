/**
 * 
 * 请车员询价订单管理列表 查询Form
 */
Ext.define("report.view.waybilltime.Search", {
			extend : "Ext.form.Panel",
			alias : 'widget.waybilltimeSearch',
			width : '100%',
			height : 70,
			layout : 'column',
			region : 'north',
			defaults : {
				msgTarget : 'qtip',
				margin : '10 0 4 0 ',
				labelAlign : 'right'
			},
			items : [ 
					{
						fieldLabel : '生成时间',
						name : 'beginTime',
						xtype : 'datefield',
						columnWidth : 0.25,
						labelWidth : 100,
						listeners : {// 光标聚焦时触发的事件
							"focus" : function() {
								this.onTriggerClick(); // 显示日期选择框
							},
							"blur" : function() {
							}
						},
						format : 'Y-m-d'
					},{
						fieldLabel : '一',
						name : 'endTime',
						xtype : 'datefield',
						columnWidth : 0.185,
						labelSeparator : '', // 去掉后面的冒号
						labelWidth : 20,
						listeners : {// 光标聚焦时触发的事件
							"focus" : function() {
								this.onTriggerClick(); // 显示日期选择框
							},
							"blur" : function() {
							}
						},
						format : 'Y-m-d'
					}
					
					
					],
			buttons : [{
						text : '查询',
						action : 'select'
					}, {
						text : '重置',
						action : 'reset'
					}]
		});
		
		
		
		
		
