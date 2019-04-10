
/**
 * 数据字典词条model
 */
Ext.define('Butterfly.model.DataDictionaryEntity', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'termsCode',
        // 词条编码
        type: 'string'
    },
    {
        name: 'termsName',
        // 词条名称
        type: 'string'
    },
    {
        name: 'isLeaf',
        // 是否叶子节点
        type: 'string'
    },
    {
        name: 'parmentCode',
        // 上级编码
        type: 'string'
    },
    {
        name: 'notes',
        // 备注
        type: 'string'
    }]
});
/**
 * 数据字典值model
 */
Ext.define('Butterfly.model.DataDictionaryValueEntity', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'termsCode',
        // 词条编码
        type: 'string'
    },{
        name: 'termsName',
        // 词条名称
        type: 'string'
    },
    {
        name: 'valueName',
        // 值名称
        type: 'string'
    },
    {
        name: 'valueCode',
        // 值代码
        type: 'string'
    },
    {
        name: 'valueSeq',
        // 顺序
        type: 'string'
    },
    {
        name: 'isAppUse',
        // 顺序
        type: 'string'
    },
    {
        name: 'language',
        // 语言
        type: 'string'
    },
    {
        name: 'notes',
        // 备注
        type: 'string'
    }]
});
/**
 * 数据字典词条名称下拉框store
 */
Ext.define('Butterfly.commonSelector.DataDictionaryStore', {
    extend: 'Ext.data.Store',
    model: 'Butterfly.model.DataDictionaryEntity',
    proxy: {
        type: 'ajax',
        url: 'dataDictionaryAction!queryDataDictionaryByEntity.action',
        actionMethods: 'POST',
        reader: {
            type: 'json',
            root: 'dataDictionaryVo.dataDictionaryEntitys'
        }
    }
});
/**
 * 数据字典信息store
 */
Ext.define('Butterfly.store.DataDictionaryValueStore', {
    extend: 'Ext.data.Store',
    model: 'Butterfly.model.DataDictionaryValueEntity',
    pageSize: 20,
    proxy: {
        type: 'ajax',
        actionMethods: 'post',
        url: 'dataDictionaryAction!queryDataDictionaryValueByEntity.action',
        reader: {
            type: 'json',
            rootProperty: 'dataDictionaryVo.dataDictionaryValueEntityList',
            totalProperty: 'totalCount' // 总个数
        }
    },
    listeners: {
        beforeload: function(store, operation, eOpts) {
            var queryForm = Ext.getCmp('queryForm');
            if (queryForm != null) {
                var params = {
                    'dataDictionaryVo.dataDictionaryValueEntity.termsCode': queryForm.getForm().findField('termsCode').getValue(),
                    'dataDictionaryVo.dataDictionaryValueEntity.valueCode': queryForm.getForm().findField('valueCode').getValue(),
                    'dataDictionaryVo.dataDictionaryValueEntity.valueName': queryForm.getForm().findField('valueName').getValue(),
                    'dataDictionaryVo.dataDictionaryValueEntity.isAppUse': queryForm.getForm().findField('isAppUse').getValue()
                };
                Ext.apply(store.proxy.extraParams, params);
            }
        }
    }
});

/**
 * 数据字典词条名称下拉框
 */
Ext.define('Butterfly.selector.DataDictionarySelector', {
    extend: 'Butterfly.commonSelector.CommonCombSelector',
    alias: 'widget.datadictionaryselector',
    displayField: 'termsName',
    // 显示名称
    valueField: 'termsCode',
    // 值
    queryParam: 'dataDictionaryVo.dataDictionaryEntity.termsName',
    // 查询参数
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        me.store = Ext.create('Butterfly.commonSelector.DataDictionaryStore');
        me.callParent([cfg]);
    }
});

/**
 * 查询表单
 */
Ext.define('Butterfly.view.dataDictionary.QueryForm', {
    extend: 'Ext.form.Panel',
    id: 'queryForm',
    frame: true,
    title : baseinfo.dataDictionary.i18n('oms.common.queryCondition'),// '查询条件',
    height: 120,
    collapsible: true,
    layout: 'hbox',
    defaults: {
        colspan: 1,
        margin: '8 10 5 10',
		labelAlign : 'right'
    },
    defaultType: 'textfield',
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},config);
        
        me.items = [{
            name: 'termsCode',
            fieldLabel: baseinfo.dataDictionary.i18n('bse.datadictionary.termsname'),// '词条名称',
            width: 250,
            xtype: 'datadictionaryselector'
        },
        {
            name: 'valueCode',
            fieldLabel: baseinfo.dataDictionary.i18n('bse.datadictionary.valuecode'),// '值代码',
            width: 250,
            xtype: 'textfield'
        },
        {
            name: 'valueName',
            fieldLabel:baseinfo.dataDictionary.i18n('bse.datadictionary.valuename'),// '值名称',
            width: 250,
            xtype: 'textfield'
        },
        {
            name: 'isAppUse',
            fieldLabel:baseinfo.dataDictionary.i18n('bse.datadictionary.isAppUse'),// '是否app使用',
            width: 250,
            xtype: 'yesnocombselector'
        }],
        me.buttons = [{
            text: baseinfo.dataDictionary.i18n('oms.common.query'),
            handler: function() {
                if (me.getForm().isValid()) {
                    me.up().getDataDictionaryGrid().getPagingToolbar().moveFirst();;
                }
            }
        },
        {
            text: baseinfo.dataDictionary.i18n('oms.common.reset'),
            handler: function() {
                me.getForm().reset();
            }
        }];
        me.callParent([cfg]);
    }
});

/**
 * 数据字典信息表格
 */
Ext.define('Butterfly.view.dataDictionary.Grid', {
    extend: 'Ext.grid.Panel',
    frame: true,
    autoScroll: true,
    height: oms.getBrowserHeight() - 120,
    stripeRows: true,
    // 交替行效果
    selType: "rowmodel",
    // 选择类型设置为：行选择
    emptyText: '查询结果为空',
    // 查询结果为空
    columnLines: true,
    viewConfig: {
        enableTextSelection: true
    },
    dataDictionaryValueAddWindow: null,
    getDataDictionaryValueAddWindow: function() {
        if (this.dataDictionaryValueAddWindow == null) {
            this.dataDictionaryValueAddWindow = Ext.create('Butterfly.view.dataDictionary.DataDictionaryValueAddWindow');
            this.dataDictionaryValueAddWindow.parent = this; // 父元素
        }
        return this.dataDictionaryValueAddWindow;
    },
    dataDictionaryAddWindow: null,
    getDataDictionaryAddWindow: function() {
        if (this.dataDictionaryAddWindow == null) {
            this.dataDictionaryAddWindow = Ext.create('Butterfly.view.dataDictionary.DataDictionaryAddWindow');
            this.dataDictionaryAddWindow.parent = this; // 父元素
        }
        return this.dataDictionaryAddWindow;
    },
    pagingToolbar: null,
    getPagingToolbar: function() {
        if (this.pagingToolbar == null) {
            this.pagingToolbar = Ext.create('Ext.toolbar.Paging', {
                store: this.store,
                pageSize: 20
            });
        }
        return this.pagingToolbar;
    },
    deleteDataDictionaryValue: function() {
        var me = this;
        var selections = me.getSelectionModel().getSelection(); // 获取选中的数据
        if (selections.length < 1) { // 判断是否至少选中了一条
            oms.showWoringMessage(baseinfo.dataDictionary.i18n('bse.datadictionary.oneselected')); // 请选择一条进行作废操作！
            return; // 没有则提示并返回
        }
        var dataDictionaryValueEntityList = new Array();
        for (var i = 0; i < selections.length; i++) {
            dataDictionaryValueEntityList.push({
                'valueCode': selections[i].get('valueCode'),
                'termsCode' : selections[i].get('termsCode')
            });
        }
        oms.showQuestionMes(baseinfo.dataDictionary.i18n('bse.datadictionary.isdelete'),//是否删除
        function(e) {
            if (e == 'yes') { // 询问是否删除，是则发送请求
                var params = {
                    'dataDictionaryVo': {
                        'dataDictionaryValueEntityList': dataDictionaryValueEntityList
                    }
                };
                var successFun = function(json) {
                    var message = json.message;
                    oms.showInfoMsg(message);
                    me.getStore().load();
                };
                var failureFun = function(json) {
                    if (Ext.isEmpty(json)) {
                        oms.showErrorMes(baseinfo.dataDictionary.i18n('bse.datadictionary.timeout')); // 请求超时
                    } else {
                        var message = json.message;
                        oms.showErrorMes(message);
                    }
                };
                oms.requestJsonAjax('dataDictionaryAction!deleteDataDictionaryValue.action', params, successFun, failureFun);
            }
        });
    },
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        me.columns = [{
            dataIndex: 'termsName',
            width: 200,
            text:baseinfo.dataDictionary.i18n('bse.datadictionary.termsname')  // '词条名称'
        },
        {
            dataIndex: 'termsCode',
            width: 200,
            text: baseinfo.dataDictionary.i18n('bse.datadictionary.termscode')  //'词条代码'
        },
        {
            text: baseinfo.dataDictionary.i18n('bse.datadictionary.valuecode'),  //'值代码',
            dataIndex: 'valueCode',
            width: 200
        },
        {
            text: baseinfo.dataDictionary.i18n('bse.datadictionary.valuename'),  //'值名称',
            dataIndex: 'valueName',
            width: 200
        },
        {
            text: baseinfo.dataDictionary.i18n('bse.datadictionary.valueseq'),  //'顺序',
            dataIndex: 'valueSeq',
            width: 100
        },
        {
            text: baseinfo.dataDictionary.i18n('bse.datadictionary.isAppUse'),  //'语言',
            dataIndex: 'isAppUse',
            width: 100
        },
        {
            text: baseinfo.dataDictionary.i18n('bse.datadictionary.language'),  //'语言',
            dataIndex: 'language',
            width: 100
        },
        {
            text: baseinfo.dataDictionary.i18n('bse.datadictionary.notes'),  //'备注',
            dataIndex: 'notes',
            width: 200
        }
        /*
							 * ,{ text : '操作', width : 200 }
							 */
        ],
        me.store = Ext.create('Butterfly.store.DataDictionaryValueStore', {
            autoLoad: false
        });
        me.selModel = Ext.create('Ext.selection.CheckboxModel', { // 多选框
            mode: 'MULTI',
            checkOnly: true
        });
        me.tbar = [{
            text:baseinfo.dataDictionary.i18n('bse.datadictionary.addentry'),// '新增词条',
            xtype: 'addbutton',
            // 新增
            hidden:!baseinfo.dataDictionary.isPermission('dataDictionary/dataDictionaryAddButton'),
            handler: function() {
                me.getDataDictionaryAddWindow().show();
            }
        },
        '-', {
            text:baseinfo.dataDictionary.i18n('bse.datadictionary.adddatadiction'),// '新增数据字典',
            xtype: 'addbutton',
            // 作废
            handler: function() {
                me.getDataDictionaryValueAddWindow().show();
            }
        },
        '-', {
        	id : 'oms_bseinfo_datadictionary_del_id',
            text:baseinfo.dataDictionary.i18n('oms.common.delete'),// '删除',
            xtype: 'deletebutton',
            disabled : true,
            // 作废
            handler: function() {
                me.deleteDataDictionaryValue();
            }
        }];
        me.bbar = me.getPagingToolbar();
        me.selModel = Ext.create('Ext.selection.CheckboxModel', {
        listeners: {
            selectionchange: function(sm, selections) {
                Ext.getCmp('oms_bseinfo_datadictionary_del_id').setDisabled(selections.length === 0);
            }
        }
    }),
        // Ext.setGlyphFontFamily('FontAwesome');
        me.callParent([cfg]);
    }
});

/**
 * 新增数据字典窗口
 */
Ext.define('Butterfly.view.dataDictionary.DataDictionaryValueAddWindow', {
    extend: 'Ext.window.Window',
    title:baseinfo.dataDictionary.i18n('bse.datadictionary.adddatadiction'),// '新增数据字典',
    closable: true,
    parent: null,
    // 父元素
    modal: true,
    resizable: false,
    // 可以调整窗口的大小
    closeAction: 'hide',
    // 点击关闭是隐藏窗口
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    listeners: {
        beforehide: function(me) { // 隐藏WINDOW的时候清除数据
            me.getDataDictionaryValueForm().getForm().reset(); // 表格重置
        },
        beforeshow: function(me) { // 显示WINDOW的时候清除数据
            var fielsds = me.getDataDictionaryValueForm().getForm().getFields();
            if (!Ext.isEmpty(fielsds)) {
                fielsds.each(function(item, index, length) {
                    item.clearInvalid();
                    item.unsetActiveError();
                });
            }
            fielsds = null;
        }
    },
    dataDictionaryValueForm: null,
    getDataDictionaryValueForm: function() {
        if (Ext.isEmpty(this.dataDictionaryValueForm)) {
            this.dataDictionaryValueForm = Ext.create('Butterfly.view.dataDictionary.DataDictionaryValueForm');
        }
        return this.dataDictionaryValueForm;
    },
    submitDataDictionaryValue: function() {
        var me = this;
        if (me.getDataDictionaryValueForm().getForm().isValid()) { // 校验form是否通过校验
            var dataDictionaryValueModel = new Butterfly.model.DataDictionaryValueEntity();
            me.getDataDictionaryValueForm().getForm().updateRecord(dataDictionaryValueModel); // 将FORM中数据设置到MODEL里面
            var params = {
                'dataDictionaryVo': {
                    'dataDictionaryValueEntity': dataDictionaryValueModel.data
                }
            }
            var successFun = function(json) {
            	
                var message = json.message;
                oms.showInfoMsg(message); // 提示新增成功
                me.close();
                me.parent.getStore().load(); // 成功之后重新查询刷新结果集
            };
            var failureFun = function(json) {
                if (Ext.isEmpty(json)) {
                    oms.showErrorMes(baseinfo.dataDictionary.i18n('bse.datadictionary.timeout')); // 请求超时
                } else {
                    var message = json.message;
                    oms.showErrorMes(message); // 提示失败原因
                }
            };
            oms.requestJsonAjax('dataDictionaryAction!addDataDictionaryValue.action', params, successFun, failureFun); // 发送AJAX请求
        }
    },
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        me.fbar = [{
            text:baseinfo.dataDictionary.i18n('oms.common.cancel'),// '取消',
            // 取消
            handler: function() {
                me.close();
            }
        },
        {
            text: baseinfo.dataDictionary.i18n('oms.common.reset'),//'重置',
            // 重置
            handler: function() {
                me.getDataDictionaryValueForm().reset();
            }
        },
        {
            text: baseinfo.dataDictionary.i18n('oms.common.save'),//'保存',
            // 保存
            /* margin : '0 0 0 55', */
            handler: function() {
                me.submitDataDictionaryValue();
            }
        }];
        me.items = [me.getDataDictionaryValueForm()];
        me.callParent([cfg]);
    }
});

Ext.define('Butterfly.view.dataDictionary.DataDictionaryAddWindow', {
    extend: 'Ext.window.Window',
    title: baseinfo.dataDictionary.i18n('bse.datadictionary.addentry'),// '新增词条',
    closable: true,
    parent: null,
    // 父元素
    modal: true,
    resizable: false,
    // 可以调整窗口的大小
    closeAction: 'hide',
    // 点击关闭是隐藏窗口
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    listeners: {
        beforehide: function(me) { // 隐藏WINDOW的时候清除数据
            me.getDataDictionaryForm().getForm().reset(); // 表格重置
        },
        beforeshow: function(me) { // 显示WINDOW的时候清除数据
            var fielsds = me.getDataDictionaryForm().getForm().getFields();
            if (!Ext.isEmpty(fielsds)) {
                fielsds.each(function(item, index, length) {
                    item.clearInvalid();
                    item.unsetActiveError();
                });
            }
            fielsds = null;
        }
    },
    dataDictionaryForm: null,
    getDataDictionaryForm: function() {
        if (Ext.isEmpty(this.dataDictionaryForm)) {
            this.dataDictionaryForm = Ext.create('Butterfly.view.dataDictionary.DataDictionaryForm');
        }
        return this.dataDictionaryForm;
    },
    submitDataDictionaryValue: function() {
        var me = this;
        if (me.getDataDictionaryForm().getForm().isValid()) { // 校验form是否通过校验
            var dataDictionaryModel = new Butterfly.model.DataDictionaryEntity();
            me.getDataDictionaryForm().getForm().updateRecord(dataDictionaryModel); // 将FORM中数据设置到MODEL里面
            var params = {
                'dataDictionaryVo': {
                    'dataDictionaryEntity': dataDictionaryModel.data
                }
            }
            var successFun = function(json) {
                var message = json.message;
                oms.showInfoMsg(message); // 提示新增成功
                me.close();
                me.parent.getStore().load(); // 成功之后重新查询刷新结果集
            };
            var failureFun = function(json) {
                if (Ext.isEmpty(json)) {
                    oms.showErrorMes(baseinfo.dataDictionary.i18n('bse.datadictionary.timeout')); // 请求超时
                } else {
                    var message = json.message;
                    oms.showErrorMes(message); // 提示失败原因
                }
            };
            oms.requestJsonAjax('dataDictionaryAction!addDataDictionary.action', params, successFun, failureFun); // 发送AJAX请求
        }
    },
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        me.fbar = [{
            text: baseinfo.dataDictionary.i18n('oms.common.cancel'),// '取消',
            // 取消
            handler: function() {
                me.close();
            }
        },
        {
            text:baseinfo.dataDictionary.i18n('oms.common.reset'),// '重置',
            // 重置
            handler: function() {
                me.getDataDictionaryForm().reset();
            }
        },
        {
            text: baseinfo.dataDictionary.i18n('oms.common.save'),//'保存',
            // 保存
            /* margin : '0 0 0 55', */
            handler: function() {
                me.submitDataDictionaryValue();
            }
        }];
        me.items = [me.getDataDictionaryForm()];
        me.callParent([cfg]);
    }
});
/**
 * 数据字典表单
 */
Ext.define('Butterfly.view.dataDictionary.DataDictionaryValueForm', {
    extend: 'Ext.form.Panel',
    header: false,
    frame: true,
    collapsible: true,
    width: 280,
    fieldDefaults: {
        labelWidth: 80,
        margin: '8 10 5 10'
    },
    defaultType: 'textfield',
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        me.items = [{
            name: 'termsCode',
            fieldLabel:baseinfo.dataDictionary.i18n('bse.datadictionary.termsname'), // '词条编码',
            xtype: 'datadictionaryselector',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },
        {
            name: 'valueName',
            fieldLabel: baseinfo.dataDictionary.i18n('bse.datadictionary.valuename'), //'值名称',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },
        {
            name: 'valueCode',
            fieldLabel:baseinfo.dataDictionary.i18n('bse.datadictionary.valuecode'), // '值编码',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },
        {
            name: 'valueSeq',
            fieldLabel:baseinfo.dataDictionary.i18n('bse.datadictionary.valueseq'), // '序号',
            xtype: 'numberfield',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },
        {
            name: 'isAppUse',
            fieldLabel:baseinfo.dataDictionary.i18n('bse.datadictionary.isAppUse'), // 'app使用',
            xtype: 'yesnocheckbox',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },
        {
            name: 'language',
            fieldLabel: baseinfo.dataDictionary.i18n('bse.datadictionary.language'), //'语言',
            allowBlank: true
        },
        {
            name: 'notes',
            fieldLabel: baseinfo.dataDictionary.i18n('bse.datadictionary.notes'), //'备注',
            xtype: 'textarea'
        }];
        me.callParent([cfg]);
    }
});

/**
 * 数据字典词条表单
 */
Ext.define('Butterfly.view.dataDictionary.DataDictionaryForm', {
    extend: 'Ext.form.Panel',
    header: false,
    frame: true,
    collapsible: true,
    width: 280,
    fieldDefaults: {
        labelWidth: 80,
        margin: '8 10 5 10'
    },
    defaultType: 'textfield',
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},config);
        
        me.items = [{
            name: 'termsName',
            fieldLabel:baseinfo.dataDictionary.i18n('bse.datadictionary.termsname'), // '词条名称',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },
        {
            name: 'termsCode',
            fieldLabel: baseinfo.dataDictionary.i18n('bse.datadictionary.termscode'), //'词条代码',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },
        {
            name: 'isLeaf',
            fieldLabel: baseinfo.dataDictionary.i18n('bse.datadictionary.isleaf') //'是否子节点'
            
        },
        {
            name: 'parmentCode',
            fieldLabel:baseinfo.dataDictionary.i18n('bse.datadictionary.parentnode'), // '父节点',
            xtype: 'datadictionaryselector'
        },
        {
            name: 'notes',
            fieldLabel:baseinfo.dataDictionary.i18n('bse.datadictionary.notes'), // '备注',
            xtype: 'textarea'
        }];
        me.callParent([cfg]);
    }
});
Ext.onReady(function() {
    /**
	 * 数据字典页面
	 */
    Ext.QuickTips.init();
    var queryForm = Ext.create('Butterfly.view.dataDictionary.QueryForm'); //查询FORM
    var dataDictionaryGrid = Ext.create('Butterfly.view.dataDictionary.Grid');
    Ext.create('Ext.panel.Panel', {
        renderTo: Ext.getBody(),
        getQueryForm: function() {
            return queryForm;
        },
        getDataDictionaryGrid: function() {
            return dataDictionaryGrid;
        },
        items: [queryForm, dataDictionaryGrid]
    });
});