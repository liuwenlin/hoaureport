/**
 * model
 */
Ext.define('oms.model.abnormalProject',{
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		//id
		type : 'string'

	},{
		name : 'abnormalName',
		//异常名称
		type : 'string'
	},{
		name : 'abnormalCode',
		//异常代号
		type : 'string'
	},{
		name : 'abnormalType',
		//异常类型
		type : 'string'
	},{
		name : 'responsibleParty',
		//责任方
		type : 'string'
	},{
		name : 'orderState',
		//订单状态
		type : 'string'
	},{
		name : 'abnormalDescribe',
		//异常描述
		type : 'string'
	},{
		name : 'abnormalReaso',
		//异常原因
		type : 'string'
	},{
		name : 'abnormalAvailable',
		//异常可用
		type : 'string'
	},{
		name : 'solution',
		//解决方案描述
		type : 'string'
	},{
		name : 'abnormalRemarks',
		//异常备注
		type : 'string'
	}]
});
