/**
 * 请车员询价订单管理 grid
 */
Ext.define("report.view.monthsql.List", {
			extend : "Ext.grid.Panel",
			alias : 'widget.monthsqlList',
			store : "monthsql",
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
							align : 'center',
							width : 50,
							xtype : 'rownumberer'
						}, {
							text : 'id',
							dataIndex : 'driverid',
							xtype : 'hiddenfield'
						}, {
							text : '名称',
							dataIndex : 'remark',
							width : 250
						}, {
							text : '类型',
							dataIndex : 'sqlType',
							width : 160,renderer:sqlTypeFun
						}, {
							text : '编码格式',
							dataIndex : 'charCode',
							width : 100
						}, {
							text : '顺序',
							dataIndex : 'sqlSeq',
							width : 80
						}, {
							text : '创建人',
							dataIndex : 'creator',
							flex : 1
						}, {
							text : '创建时间',
							dataIndex : 'createDate',//**
							xtype : 'datecolumn',
							format : 'Y-m-d H:i:s',
							flex : 1
						}, {
							text : '修改人',
							dataIndex : 'modifier',
							flex : 1
						}, {
							text : '修改时间',
							dataIndex : 'modifyDate',//**
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
						text : '新增',
						xtype:'addbutton',
						action : 'addbut'
					},
					{
						text : '修改',
						xtype:'updatebutton',
						action : 'dupdatebut'
					},{
						text : '删除',
						xtype:'deletebutton',
						action : 'deletebut'
					}
					]
		});


function sqlTypeFun(value){
	if(value=='1001'){
		return '整月';
	}else if(value =='1002'){
		return '运单物流时间上月16号';
	}else{
		return value;
	}
}
