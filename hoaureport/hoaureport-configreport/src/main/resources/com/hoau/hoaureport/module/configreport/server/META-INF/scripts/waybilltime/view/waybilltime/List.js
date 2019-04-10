/**
 * 请车员询价订单管理 grid
 */
Ext.define("report.view.waybilltime.List", {
			extend : "Ext.grid.Panel",
			alias : 'widget.waybilltimeList',
			store : "waybilltime",
			region : 'center',
			width : '100%',
			viewConfig : {
				enableTextSelection : true
			},
			selModel : Ext.create('Ext.selection.CheckboxModel'),
			columns : {
				defaults : {
					align : 'left'
				},
				items : [{
							text : '序号',
//							flex : 0.5,
							align : 'center',
							width : 50,
							xtype : 'rownumberer'
						}, {
							text : 'id',
							dataIndex : 'driverid',
							xtype : 'hiddenfield'
						}, {
							text : '编号',
							dataIndex : 'zipNum',
							width : 250
						}, {
							text : '名称',
							dataIndex : 'zipName',
							width : 250
						}, {
							text : '生成时间',
							dataIndex : 'createDate',//**
							xtype : 'datecolumn',
							format : 'Y-m-d H:i:s',
							flex : 1
						}]
			},
			pagingToolbar : null,
			getPagingToolbar : function() {
				if (this.pagingToolbar == null) {
					this.pagingToolbar = Ext.create('Ext.toolbar.Paging', {
								store : this.store,
								dock : 'bottom',
								displayInfo : true
							});
				}
				return this.pagingToolbar;
			},
			constructor : function(config) {
				var me = this, cfg = Ext.apply({}, config);
				me.bbar = me.getPagingToolbar();
				me.callParent([cfg]);
			},
			tbar : [{
						text : 'CSV下载',
						xtype:'downloadbutton',
						action : 'csvdown'
					}
					]
		});
