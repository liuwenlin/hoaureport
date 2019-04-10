/**
 * 请车员询价单列表Store
 */
Ext.define("report.store.monthsql", {
    extend: "Ext.data.Store",
    model: "report.model.monthsql",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'querymonthsql.action',
        reader: {
            type: 'json',
            rootProperty: 'sqlList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {

		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('monthsqlViewId').getMonthSqlqueryForm().getForm();
			
			if (queryForm != null) {
				var params = {
						'sqlEntity.sqlId' : queryForm.findField('sqlId').getValue(),//开始时间
						'sqlEntity.sqlType' : queryForm.findField('sqlType').getValue() //结束时间
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	
		
	},
    autoLoad: true
});
