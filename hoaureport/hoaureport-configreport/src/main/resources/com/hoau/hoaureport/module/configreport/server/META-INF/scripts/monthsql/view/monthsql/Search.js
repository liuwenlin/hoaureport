/**
 * 
 * 请车员询价订单管理列表 查询Form
 */
Ext.define("report.view.monthsql.Search", {
			extend : "Ext.form.Panel",
			alias : 'widget.monthsqlSearch',
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
						fieldLabel : '报表名称',
						xtype:'querySqlcombselector',
						name:'sqlId',
						labelWidth : 100,
						columnWidth : 0.25,
//						pageSize: 10,
//						listWidth : 300,// 设置下拉框宽度
						displayField : 'remark',// 显示名称
						valueField : 'id'// 值
					},{
						fieldLabel : '类型',
						name : 'sqlType',
						margin : '5 10 0 10',
						readOnly : false,
						editable : false,
						xtype : 'combobox',
						
						columnWidth:0.25,
						store : [['1001', "整月"], ['1002', "运单物流时间上月16号"]]
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
		
		
		
		
		
