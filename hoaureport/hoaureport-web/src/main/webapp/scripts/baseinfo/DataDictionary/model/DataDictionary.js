/**
 * 数据字典词条model
 */
Ext.define('oms.model.DataDictionary',{
	extend : 'Ext.data.Model',
	fields : [{
		name : 'termsCode',//词条编码
		type : 'string'
	},{
		name : 'termsName',//词条名称
		type : 'string'
	},{
		name : 'notes',//备注
		type : 'string'
	}]
});


