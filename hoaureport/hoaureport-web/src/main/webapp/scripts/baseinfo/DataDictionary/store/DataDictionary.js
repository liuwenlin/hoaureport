/**
 * 数据字典词条名称下拉框store
 */
Ext.define('oms.store.DataDictionary', {
	extend : 'Ext.data.Store',
	model : 'oms.model.DataDictionary',
	proxy : {
		type : 'ajax',
		url : 'dataDictionaryAction!queryDataDictionaryByEntity.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			root : 'dataDictionaryVo.dataDictionaryEntitys'
		}
	}
});

