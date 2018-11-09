Ext.define("report.view.Viewport", {
	id : 'monthsqlViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"monthsqlSearch"
    },{
        xtype:"monthsqlList"
    }]
    ,
    getMonthSqlqueryForm : function(){
    	return this.items.get(0);
    },
    getMonthSqlqueryGrid : function(){
    	return this.items.get(1);
    },
    tabChange : function(){
    	// 刷新数据
//    	this.getdriversignGrid().getStore().reload();
    }
}); 