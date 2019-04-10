
/**
 * 查询表单
 */
Ext.define('oms.view.processRegistra.Search',{
	extend : 'Ext.form.Panel',
	id : 'oms_bse_abnormal_queryform_id',
	alias : 'widget.processRegistraSearch',
	frame : true,
	height : 110,
	collapsible : false,
	layout : 'column',
	region : 'north',
	defaults : {
		rowspan : 2,
		margin : '8 10 0 0',
		labelAlign : 'right'
	},
	defaultType : 'textfield',
	constructor : function(config) {
		var me = this,
		cfg = Ext.apply({}, config);
		me.items = [ 
		{
			name : 'processRegistraName',
			fieldLabel :'处理登记名称',
			columnWidth : 0.25
		},{
			name : 'processRegistraCode',
			fieldLabel :'处理登记代号',
			columnWidth : 0.25
		},{
			name : 'processRegistraAvailable',
			fieldLabel :'处理登记可用',
			value:'Y',
			columnWidth : 0.25,
			xtype: 'yesnocheckbox'
		},{
			name : 'processRegistraDescrip',
			fieldLabel :'处理登记描述',
			columnWidth : 0.8
		}];
		 me.buttons = [      
		{
			text : '查询',
			action : 'select'
//			handler : function() {
//					me.up().getResourceGrid().getPagingToolbar().moveFirst();
//			}
		}, {
			text : '重置',
			action : 'reset'
//			handler : function() {
//				me.getForm().reset();
//			}
		} ];
		me.callParent([ cfg ]);
	}
});