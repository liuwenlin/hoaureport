/**
 * 数据字典Viewport
 */
Ext.define("oms.view.Viewport", {
	id : 'dataDictionaryViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"dataDictionarySearch"
    }
    ,{
        xtype:"dataDictionaryList"
    }
    ],
    getSearchForm : function(){
    	return this.items.get(0);
    },
    getGrid : function(){
    	return this.items.get(1);
    }
});
