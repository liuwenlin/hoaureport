/**
 * store
 */
Ext.define('oms.store.abnormalProject',{
	extend : 'Ext.data.Store',
	model : 'oms.model.abnormalProject',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		actionMethods : 'post',
		url : 'abnormalProjectAction!queryAbnormalProject.action',
		reader : {
			type : 'json',
			rootProperty : 'abnormalProjectVo.abnormalProjectList',
			totalProperty : 'totalCount' // 总个数
		}
	},
		listeners : {
		beforeload : function(store, operation, eOpts) {
			var queryResourceForm = Ext.getCmp('oms_bse_abnormal_queryform_id');
			if (queryResourceForm != null) {
				var params = {
					'abnormalProjectVo.abnormalProjectParams.abnormalName' : queryResourceForm.getForm().findField('abnormalName').getValue(),
					'abnormalProjectVo.abnormalProjectParams.abnormalCode' : queryResourceForm.getForm().findField('abnormalCode').getValue(),
					'abnormalProjectVo.abnormalProjectParams.abnormalType' : queryResourceForm.getForm().findField('abnormalType').getValue(),
					'abnormalProjectVo.abnormalProjectParams.responsibleParty' : queryResourceForm.getForm().findField('responsibleParty').getValue(),
					'abnormalProjectVo.abnormalProjectParams.orderState' : queryResourceForm.getForm().findField('orderState').getValue(),
					'abnormalProjectVo.abnormalProjectParams.abnormalAvailable' : queryResourceForm.getForm().findField('abnormalAvailable').getValue()
				}
				Ext.apply(store.proxy.extraParams, params);
			}
		}
	}
});