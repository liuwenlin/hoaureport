
/**
 * 表单(route-detile_form)
 */
Ext.define('oms.view.abnormalProject.addForm', {
    extend: 'Ext.form.Panel',
    header: false,
    frame: true,
    id : 'oms_bse_abnormal_addfrom_id',
    collapsible: true,
    width: 500,
    fieldDefaults: {
        labelWidth: 80,
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
            name: 'abnormalName',
            fieldLabel: '异常名称',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false,
            columnWidth : 0.5
        },
        {
            name: 'abnormalCode',
            fieldLabel: '异常代号',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false,
            columnWidth : 0.5
        },
        {
            name: 'abnormalType',
            fieldLabel: '异常类型',
            xtype: 'dataDictionarySelector',
			termsCode: oms.data.abnormaltype,
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            columnWidth : 0.5,
            allowBlank: false
        },
        {
            name: 'orderState',
            fieldLabel: '订单状态',
            xtype: 'dataDictionarySelector',
			termsCode: oms.data.orderstate,
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            columnWidth : 0.5,
            allowBlank: false
        },
        {
            name: 'responsibleParty',
            fieldLabel: '责任方',
            columnWidth : 0.5
        },
        {
            name: 'abnormalAvailable',
            fieldLabel: '异常可用',
            value:'Y',
            xtype: 'yesnocheckbox',
            columnWidth : 0.5
        },
        {
            name: 'abnormalReaso',
            fieldLabel: '异常原因',
            columnWidth : 1
        },
        {
            name: 'abnormalDescribe',
            fieldLabel: '异常描述',
            columnWidth : 1
        },
        {
            name: 'solution',
            fieldLabel: '解决方案',
            columnWidth : 1
        },
        {
            name: 'abnormalRemarks',
            fieldLabel: '异常备注',
            columnWidth : 1
        }];
        me.callParent([cfg]);
    }
});