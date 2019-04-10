
/**
 * 查询表单
 */
Ext.define('oms.view.abnormalProject.Search',{
	extend : 'Ext.form.Panel',
	id : 'oms_bse_abnormal_queryform_id',
	alias : 'widget.abnormalProjectSearch',
	frame : true,
	height : 100,
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
			name : 'abnormalName',
			fieldLabel :'异常项目名称',
			columnWidth : 0.3
		},{
			name : 'abnormalCode',
			fieldLabel :'异常项目代号',
			columnWidth : 0.3
		},{
			name : 'abnormalType',
			fieldLabel :'异常类型',
			xtype: 'dataDictionarySelector',
			termsCode: oms.data.abnormaltype,
			columnWidth : 0.3
		},{
			name : 'responsibleParty',
			fieldLabel :'责任方',
			columnWidth : 0.3
		},{
			name : 'orderState',
			xtype: 'dataDictionarySelector',
			termsCode: oms.data.orderstate,
			fieldLabel :'订单状态',
			columnWidth : 0.3
		},{
			name : 'abnormalAvailable',
			fieldLabel :'异常项目可用',
			columnWidth : 0.3,
			value:'Y',
			xtype: 'yesnocheckbox'
		}];
		 me.buttons = [      
		{
			text : '查询',
			action : 'select'
		}, {
			text : '重置',
			action : 'reset'
		} ];
		me.callParent([ cfg ]);
	}
});
