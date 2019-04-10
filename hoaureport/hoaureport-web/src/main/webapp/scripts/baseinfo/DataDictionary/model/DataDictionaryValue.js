/**
 * 数据字典值model
 */
Ext.define('oms.model.DataDictionaryValue',{
	extend : 'Ext.data.Model',
	fields : [{
		name : 'termsCode',//词条编码
		type : 'string'
	},{
		name : 'termsName',//词条名称
		type : 'string'
	},{
		name : 'valueName',//值名称
		type : 'string'
	},{
		name : 'valueCode',//值代码
		type : 'string'
	},{
		name : 'valueSeq',//顺序
		type : 'string'
	},{
		name : 'language',//语言
		type : 'string'
	},{
		name : 'noteInfo',//备注
		type : 'string'
	}]
});