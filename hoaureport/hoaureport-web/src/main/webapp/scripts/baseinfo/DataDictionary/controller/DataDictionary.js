/**
 * 数据字典Controller
 */
Ext.define('oms.controller.DataDictionary', {
    extend: 'Ext.app.Controller',
    stores: ['DataDictionary','DataDictionaryValue'],
    models: ['DataDictionary','DataDictionaryValue'],
    views: ['Viewport','datadictionary.Search','datadictionary.List','datadictionary.Add','datadictionary.AddValue'],
    init: function () {
        this.control({
            'dataDictionarySearch button[action=reset]': {
                click: this.resetSearchForm
            },
            'dataDictionarySearch button[action=select]': {
                click: this.reloadGridStore
            }
        });
    },
    resetSearchForm : function(btn){
    	btn.up('form').getForm().reset();
    },
    reloadGridStore : function(btn){
    	Ext.getCmp('dataDictionaryViewId').getGrid().getPagingToolbar().moveFirst();
    }
});