Ext.define("report.view.Viewport", {
	id : 'waybilltimeViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"waybilltimeSearch"
    },{
        xtype:"waybilltimeList"
    }]
    ,
    getwaybillzipqueryForm : function(){
    	return this.items.get(0);
    },
    getwaybillzipqueryGrid : function(){
    	return this.items.get(1);
    },
    tabChange : function(){
    	// 刷新数据
//    	this.getdriversignGrid().getStore().reload();
    }
}); 