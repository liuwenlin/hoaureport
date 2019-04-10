/**
 * 数据字典信息表格
 */
Ext.define('oms.view.datadictionary.List',{
	extend : 'Ext.grid.Panel',
	alias : 'widget.dataDictionaryList',
	frame : true,
	region : 'center',
	autoScroll : true,
	stripeRows : true, // 交替行效果
	selType : "rowmodel", // 选择类型设置为：行选择
	emptyText : '查询结果为空', //查询结果为空
	columnLines: true,
	viewConfig:{
		enableTextSelection:true
	},
	dataDictionaryValueAddWindow : null,
	getDataDictionaryValueAddWindow : function(){
		if (this.dataDictionaryValueAddWindow == null) {
			this.dataDictionaryValueAddWindow = Ext.create('oms.view.datadictionary.AddValue');
			this.dataDictionaryValueAddWindow.parent = this; //父元素
		}
		return this.dataDictionaryValueAddWindow;
	},
	dataDictionaryAddWindow : null,
	getDataDictionaryAddWindow : function(){
		if (this.dataDictionaryAddWindow == null) {
			this.dataDictionaryAddWindow = Ext.create('oms.view.datadictionary.Add');
			this.dataDictionaryAddWindow.parent = this; //父元素
		}
		return this.dataDictionaryAddWindow;
	},
	pagingToolbar:null,
	getPagingToolbar : function () {
		if (this.pagingToolbar == null) {
			this.pagingToolbar = Ext.create('Ext.toolbar.Paging', {
					store : this.store,
					pageSize : 20
				});
		}
		return this.pagingToolbar;
	},
	deleteDataDictionaryValue:function(){
		var me = this;
		var selections = me.getSelectionModel().getSelection(); //获取选中的数据
		if (selections.length < 1) { //判断是否至少选中了一条
			oms.showWoringMessage('请选择一条进行删除操作'); //请选择一条进行作废操作！
			return; //没有则提示并返回
		}
		var dataDictionaryValueEntityList = new Array();
		for(var i = 0 ; i<selections.length ; i++){
			dataDictionaryValueEntityList.push({'valueCode':selections[i].get('valueCode')});
		}
		oms.showQuestionMes('是否要删除', function (e) { 
			if (e == 'yes') { //询问是否删除，是则发送请求
				var params = {'dataDictionaryVo':{'dataDictionaryValueEntityList':dataDictionaryValueEntityList}};
				var successFun = function (json) {
					var message = json.message;
					oms.showInfoMsg(message);
					me.getStore().load();
				};
				var failureFun = function (json) {
					if (Ext.isEmpty(json)) {
						oms.showErrorMes('请求超时'); //请求超时
					} else {
						var message = json.message;
						oms.showErrorMes(message);
					}
				};
				oms.requestJsonAjax('dataDictionaryAction!deleteDataDictionaryValue.action', params, successFun, failureFun);
			}
		});
	},
	constructor : function(config){
		var me = this,
		cfg = Ext.apply({}, config);
		me.columns = [{
			dataIndex : 'termsName',
			flex : 1,
			text : '词名称'
		},{
			text : '值代码', 
			dataIndex : 'valueCode',
			flex : 1
		},{
			text : '值名称', 
			dataIndex : 'valueName',
			flex : 1
		},{
			text : '序号', 
			dataIndex : 'valueSeq',
			flex : 1
		},{
			text : '备注', 
			dataIndex : 'noteInfo',
			flex : 1
		}/*,{
			text : '操作', 
			width : 200
		}*/],
		me.store = Ext.create('oms.store.DataDictionaryValue', {
			autoLoad : false
		});
		me.selModel = Ext.create('Ext.selection.CheckboxModel', { //多选框
			mode : 'MULTI',
			checkOnly : true
		});
		me.tbar = [ {
			text : '新增词条', // 新增
			width : 100,
			handler : function() {
				me.getDataDictionaryAddWindow().show();
			}
		}, '-', {
			text : '新增数据字典', // 作废
			width : 120,
			handler : function() {
				me.getDataDictionaryValueAddWindow().show();
			}
		} ,'-', {
			text : '删除', // 作废
			width : 80,
			handler : function() {
				me.deleteDataDictionaryValue();
			}
		}];
		me.bbar = me.getPagingToolbar();
		//Ext.setGlyphFontFamily('FontAwesome'); 
		me.callParent([cfg]);
	}
});