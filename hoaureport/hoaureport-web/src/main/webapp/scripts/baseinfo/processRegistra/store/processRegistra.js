
/**
 * store
 */
Ext.define('oms.store.processRegistra', {
	extend : 'Ext.data.Store',
	model : 'oms.model.processRegistra',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		actionMethods : 'post',
		url : 'processRegistraAction!queryProcessRegistra.action',
		reader : {
			type : 'json',
			rootProperty : 'processRegistraVo.processRegistraList',
			totalProperty : 'totalCount' // 总个数
		}
	},
	listeners : {
		beforeload : function(store, operation, eOpts) {
			var queryResourceForm = Ext.getCmp('oms_bse_abnormal_queryform_id');
			if (queryResourceForm != null) {
				var params = {
					'processRegistraVo.processRegistraParams.processRegistraName' : queryResourceForm
							.getForm().findField('processRegistraName')
							.getValue(),
					'processRegistraVo.processRegistraParams.processRegistraCode' : queryResourceForm
							.getForm().findField('processRegistraCode')
							.getValue(),
					'processRegistraVo.processRegistraParams.processRegistraAvailable' : queryResourceForm
							.getForm().findField('processRegistraAvailable')
							.getValue(),
					'processRegistraVo.processRegistraParams.processRegistraDescrip' : queryResourceForm
							.getForm().findField('processRegistraDescrip')
							.getValue()
				}
				Ext.apply(store.proxy.extraParams, params);
			}
		}
	}
});