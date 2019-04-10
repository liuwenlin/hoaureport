/**
 * 数据字典信息store
 */
Ext.define('oms.store.DataDictionaryValue', {
	extend : 'Ext.data.Store',
	model : 'oms.model.DataDictionaryValue', 
	pageSize : 20,
	proxy : {
		type : 'ajax',
		actionMethods : 'post',
		url : 'dataDictionaryAction!queryDataDictionaryValueByEntity.action',
		reader : {
			type : 'json',
			rootProperty : 'dataDictionaryVo.dataDictionaryValueEntityList', 
			totalProperty : 'totalCount' //总个数
		}
	},
	listeners : {
		beforeload : function (store, operation, eOpts) {
			var queryForm = Ext.getCmp('queryForm');
			if (queryForm != null) {
				var params =  { 
						'dataDictionaryVo.dataDictionaryValueEntity.termsCode' : queryForm.getForm().findField('termsCode').getValue(),
						'dataDictionaryVo.dataDictionaryValueEntity.valueCode' : queryForm.getForm().findField('valueCode').getValue(),
						'dataDictionaryVo.dataDictionaryValueEntity.valueName' : queryForm.getForm().findField('valueName').getValue()
					};
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	}
});