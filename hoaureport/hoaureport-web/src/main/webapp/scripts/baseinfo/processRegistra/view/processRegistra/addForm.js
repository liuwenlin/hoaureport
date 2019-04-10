
/**
 * 表单(route-detile_form)
 */
Ext.define('oms.view.processRegistra.addForm', {
    extend: 'Ext.form.Panel',
    header: false,
    frame: true,
    id : 'oms_bse_abnormal_addfrom_id',
    collapsible: true,
    width: 500,
    fieldDefaults: {
        labelWidth: 100,
        margin: '10 10 10 10',
        labelAlign : 'right'
    },
    layout : 'column',
    defaultType: 'textfield',
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        me.items = [{
            name: 'id',
            fieldLabel: 'ID',
            hidden:true
        },{
            name: 'processRegistraName',
            fieldLabel: '处理登记名称',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false,
            columnWidth : 0.5
        },
        {
            name: 'processRegistraCode',
            fieldLabel: '处理登记代号',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false,
            columnWidth : 0.5
        },
        {
            name: 'processRegistraAvailable',
            fieldLabel: '处理登记可用',
            value:'Y',
            xtype: 'yesnocheckbox',
            columnWidth : 0.5
        },
        {
            name: 'processRegistraDescrip',
            fieldLabel: '处理登记描述',
            xtype: 'textareafield',
            columnWidth : 1
        }];
        me.callParent([cfg]);
    }
});