/**
 * 处理登记Controller
 */
Ext.define('oms.controller.processRegistra', {
			extend : 'Ext.app.Controller',
			stores : ['processRegistra'],
			models : ['processRegistra'],
			views : ['Viewport', 'processRegistra.Search',
					'processRegistra.List', 'processRegistra.addwindow',
					'processRegistra.addForm'],
			init : function() {
				this.control({
							'processRegistraSearch button[action=reset]' : {
								click : this.resetSearchForm
							},
							'processRegistraSearch button[action=select]' : {
								click : this.reloadGridStore
							}
						});
			},
			resetSearchForm : function(btn) {
				btn.up('form').getForm().reset();
			},
			reloadGridStore : function(btn) {
				Ext.getCmp('procesRegistraViewId').getGrid()
						.getPagingToolbar().moveFirst();
			}
		});
