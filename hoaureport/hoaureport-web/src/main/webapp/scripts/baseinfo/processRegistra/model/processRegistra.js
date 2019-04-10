/**
 * model
 */
Ext.define('oms.model.processRegistra',{
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		//id
		type : 'string'

	},{
		name : 'processRegistraName',
		//处理登记名称
		type : 'string'
	},{
		name : 'processRegistraCode',
		//处理登记代号
		type : 'string'
	},{
		name : 'processRegistraAvailable',
		//处理登记可用
		type : 'string'
	},{
		name : 'processRegistraDescrip',
		//处理登记描述
		type : 'string'
	}]
});