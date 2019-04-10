/**
 * 请车员询价单列表Store
 */
Ext.define("report.store.waybilltime", {
    extend: "Ext.data.Store",
    model: "report.model.waybilltime",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'waybilltimequery.action',
        reader: {
            type: 'json',
            rootProperty: 'zipList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {

		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('waybilltimeViewId').getwaybillzipqueryForm().getForm();
			
			if (queryForm != null) {
				var params = {
						
						'zipEntity.createDate' : queryForm.findField('beginTime').getValue(),//开始时间
						'zipEntity.endTime' : queryForm.findField('endTime').getValue() //结束时间
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	
		
	},
    autoLoad: true
});
