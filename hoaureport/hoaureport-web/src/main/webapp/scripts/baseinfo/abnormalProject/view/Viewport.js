
/**
 * 异常项目Viewport
 */
Ext.define("oms.view.Viewport", {
			id : 'abnormalProjectViewId',
			extend : "Ext.container.Viewport",
			layout : "border",
			items : [{
						xtype : "abnormalProjectSearch"
					}, {
						xtype : "abnormalProjectList"
					}],
			getSearchForm : function() {
				return this.items.get(0);
			},
			getGrid : function() {
				return this.items.get(1);
			},
			listeners : {
				keydown : {
					element : 'el',
					fn : function(me) {
						if (me.getKey() == Ext.EventObject.ENTER) {
							Ext.getCmp('abnormalProjectViewId').getGrid()
									.getPagingToolbar().moveFirst();
						}
					}

				}

			}
		});