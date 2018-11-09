/**
 * 数据字典查询表单
 */
Ext.define('oms.view.datadictionary.Search', {
	extend : 'Ext.form.Panel',
	alias : 'widget.dataDictionarySearch',
	frame : true,
	title : '查询条件',
	height : 120,
	collapsible : true,
	region : 'north',
	layout: 'hbox',
	titleCollapse:true,
	defaults : {
		colspan : 1,
		margin : '8 10 5 10'
	},
	defaultType : 'textfield',
	constructor : function(config){
		var me = this,
		cfg = Ext.apply({}, config);
		me.items = [{
			name : 'termsCode',
			fieldLabel : '词条名称',
			width : 250,
			xtype : 'dataDictionarySelector'
		},{
			name : 'valueCode',
			fieldLabel : '值代码',
			width : 250,
			xtype : 'textfield'
		},{
			name : 'valueName',
			fieldLabel : '值名称',
			width : 250,
			xtype : 'textfield'
		}],
		me.buttons = [{
			text : '查询',
			handler : function(){
				if (me.getForm().isValid()) {
					me.up().getGrid().getPagingToolbar().moveFirst();;
				}
			}
		}, {
			text : '清空',
			handler : function() {
				me.getForm().reset();
			}
		}];
		me.callParent([cfg]);
	}
});