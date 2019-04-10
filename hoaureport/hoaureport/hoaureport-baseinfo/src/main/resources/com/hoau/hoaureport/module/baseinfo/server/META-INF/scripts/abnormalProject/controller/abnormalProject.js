/**
 * 异常项目Controller
 */
Ext.define('oms.controller.abnormalProject', {
			extend : 'Ext.app.Controller',
			stores : ['abnormalProject'],
			models : ['abnormalProject'],
			views : ['Viewport', 'abnormalProject.List',
					'abnormalProject.Search',
					'abnormalProject.addwindow',
					'abnormalProject.addForm'],
			init : function() {
				this.control({
							'abnormalProjectSearch button[action=reset]' : {
								click : this.resetSearchForm
							},
							'abnormalProjectSearch button[action=select]' : {
								click : this.reloadGridStore
							},
							'abnormalProjectList button[action=add]' : {
								click : this.addwindow
							},
							'abnormalProjectList button[action=edit]' : {
								click : this.editwindow
							},
							'abnormalProjectList button[action=delete]' : {
								click : this.deletewindow
							}
						});
			},
			resetSearchForm : function(btn) {
				btn.up('form').getForm().reset();
			},
			reloadGridStore : function(btn) {
				Ext.getCmp('abnormalProjectViewId').getGrid().getPagingToolbar()
						.moveFirst();
			},
			addwindow:function(){
				var win = Ext.widget("addwindow");
				win.setTitle('新增异常项目');
				win.showAddBut();
				win.show();
			},
			editwindow:function(){
				var win = Ext.widget("abnormalProjectList");
				win.onClickUpdate();
			},
			deletewindow:function(){
				var win = Ext.widget("abnormalProjectList");
				win.deleteResourceConflict();
			}

		});
