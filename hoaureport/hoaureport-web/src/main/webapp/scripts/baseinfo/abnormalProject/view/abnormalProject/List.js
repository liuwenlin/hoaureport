
/**
 * 异常项目详情
 */
Ext.define('oms.view.abnormalProject.List', {
	extend : 'Ext.grid.Panel',
	frame : true,
	// height : oms.getBrowserHeight() - 120,
	alias : 'widget.abnormalProjectList',
	region : 'center',
	stripeRows : true,
	// 交替行效果
	selType : "rowmodel",
	// 选择类型设置为：行选择
	emptyText : '查询结果为空',
	columnLines : true,
	viewConfig : {
		enableTextSelection : true
	},
	region : 'center',
	addWindow : null,
	getAddWindow : function() {
		if (Ext.isEmpty(this.addWindow)) {
			this.addWindow = Ext.create('oms.view.abnormalProject.addwindow');
			this.addWindow.parent = this;
		}
		return this.addWindow;
	},
	pagingToolbar : null,
	getPagingToolbar : function() {
		if (this.pagingToolbar == null) {
			this.pagingToolbar = Ext.create('Ext.toolbar.Paging', {
						store : this.store,
						displayInfo : true,
						plugins: new Ext.ui.plugins.ComboPageSize()
					});
		}
		return this.pagingToolbar;
	},
	deleteResourceConflict : function() {
		var me = this;
		var selections = me.getSelectionModel().getSelection();
		if (selections.length < 1) { // 判断是否至少选中了一条
			oms.showWoringMessage('请选择一条进行删除操作！'); // 请选择一条进行作废操作！//'请至少选择一条进行删除操作'
			return; // 没有则提示并返回
		}
		var resourceEntityList = new Array();
		for (var i = 0; i < selections.length; i++) {
			resourceEntityList.push({
						'id' : selections[i].get('id')
					});
		}
		oms.showQuestionMes('是否要删除', function(e) {
					if (e == 'yes') { // 询问是否删除，是则发送请求
						var params = {
							'abnormalProjectVo' : {
								'abnormalProjectList' : resourceEntityList
							}
						};
						var successFun = function(json) {
							var message = json.message;
							oms.showInfoMsg(message);
							me.getStore().load();
						};
						var failureFun = function(json) {
							if (Ext.isEmpty(json)) {
								oms
										.showErrorMes(baseinfo.resourcesConflict
												.i18n('bse.resourcesConfilict.timeout')); // 请求超时
							} else {
								var message = json.message;
								oms.showErrorMes(message);
							}
						};
						oms
								.requestJsonAjax(
										'abnormalProjectAction!deleteAbnormalProject.action',
										params, successFun, failureFun);
					}
				});
	},
	onClickUpdate : function() {
		var me = Ext.getCmp('abnormalProjectViewId').getGrid();
		var selections = me.getSelectionModel().getSelection(); // 获取选中的数据
		if (selections.length != 1) { // 判断是否选中了一条
			oms.showWoringMessage('请选择一条进行修改'); // '请选择一条进行修改'！///
			return; // 没有则提示并返回
		};
		var params = {
				'limit':5,
				'abnormalProjectVo' : {
					'abnormalProjectParams' : {
						'id' : selections[0].get('id')
					}
				}
			};
			var successFun = function (res) {
				// 获取后台返回角色信息
				var result = res.abnormalProjectVo.abnormalProjectList;
				if(result==null){
					oms.showErrorMes('刷新当前页面！'); 
					return;
				}
				selections[0].data=result[0];
			};
				var failureFun = function (json) {
					if (Ext.isEmpty(json)) {
						oms.showErrorMes('请查看网络状况！'); // 请求超时
					} else {
						var message = json.message;
						oms.showErrorMes(message);
					}
				};
		oms.requestJsonAjax('abnormalProjectAction!queryAbnormalProject.action', params, successFun, failureFun,false);
		var win = me.getAddWindow();
		win.show();
		me.getAddWindow().setTitle('编辑异常项目');
		me.getAddWindow().showUpdateBut();
		win.getAddFrom().getForm().loadRecord(selections[0]);
	},
	listeners : {
		dblclick : {
			element : 'body', 
			fn : function() {
				Ext.getCmp('abnormalProjectViewId').getGrid().onClickUpdate();
			}
		}
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.columns = [{
					text : 'ID',
					dataIndex : 'id',
					width : 200,
					hidden : true
				}, {
					text : '异常名称',
					dataIndex : 'abnormalName',
					align : 'center',
					width : 100
				}, {
					text : '异常代号',
					dataIndex : 'abnormalCode',
					align : 'center',
					width : 100
				}, {
					text : '异常类型',
					dataIndex : 'abnormalType',
					align : 'center',
					renderer : function(value) {
						return oms
								.changeCodeToNameStore(
										getDataDictionary()
												.getDataDictionaryStore(oms.data.abnormaltype),
										value);
					},
					width : 100
				}, {
					text : '责任方',
					dataIndex : 'responsibleParty',
					align : 'center',
					width : 100
				}, {
					text : '订单状态',
					dataIndex : 'orderState',
					align : 'center',
					renderer : function(value) {
						return oms
								.changeCodeToNameStore(
										getDataDictionary()
												.getDataDictionaryStore(oms.data.orderstate),
										value);
					},
					width : 100
				}, {
					text : '异常描述',
					dataIndex : 'abnormalDescribe',
					width : 200
				}, {
					text : '异常原因',
					dataIndex : 'abnormalReaso',
					width : 200
				}, {
					text : '异常可用',
					dataIndex : 'abnormalAvailable',
					align : 'center',
					renderer : function(resource) {
						if (resource == 'Y')
							return '是';
						if (resource == 'N')
							return '否';
						return '';
					},
					width : 100
				}, {
					text : '解决方案描述',
					dataIndex : 'solution',
					align : 'center',
					width : 200
				}, {
					text : '异常备注',
					dataIndex : 'abnormalRemarks',
					align : 'center',
					width : 200
				}];
		me.tbar = [{
					xtype : 'addbutton',
					text : '新增',
					// action:'add'
					handler : function() {
						me.getAddWindow().show();
						me.getAddWindow().setTitle('新增异常项目');
						me.getAddWindow().showAddBut();
					}
				}, '-', {
					xtype : 'updatebutton',
					id : 'oms_bse_abnormal_but_update_id',
					disabled : true,
					text : '编辑',
					// action:'edit'
					handler : function() {
						me.onClickUpdate();
					}
				}, '-', {
					xtype : 'deletebutton',
					id : 'oms_bse_abnormal_but_delete_id',
					disabled : true,
					// action:'delete',
					text : '删除',
					handler : function() {
						me.deleteResourceConflict();
					}
				}];
		me.store = Ext.create('oms.store.abnormalProject', {
					autoLoad : true
				});
		me.selModel = Ext.create('Ext.selection.CheckboxModel', { // 多选框
			mode : 'MULTI',
			listeners : {
				selectionchange : function(sm, selections) {
					Ext.getCmp('oms_bse_abnormal_but_delete_id')
							.setDisabled(selections.length === 0);
					Ext.getCmp('oms_bse_abnormal_but_update_id')
							.setDisabled(selections.length != 1);
				}
			}
		});
		me.bbar = me.getPagingToolbar();
		me.callParent([cfg]);

	}

});