/**
 * 员工信息实体
 */
Ext.define('Butterfly.baseinfo.employee.EmployeeEntity', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'empCode',
        //员工工号
        type: 'string'
    },
    {
        name: 'empName',
        //员工名称
        type: 'string'
    },
    {
        name: 'gender',
        //性别
        type: 'string'
    },
    {
        name: 'pinyin',
        //姓名拼音
        type: 'string'
    },
    {
        name: 'pinyinShort',
        //拼音简称
        type: 'string'
    },
    {
        name: 'account',
        //账户
        type: 'string'
    },
    {
        name: 'password',
        //密码
        type: 'string'
    },
    {
        name: 'jobName',
        //岗位名称
        type: 'string'
    },
    {
        name: 'deptName',
        //部门名称
        type: 'string'
    },
    {
        name: 'deptCode',
        //部门编码
        type: 'string'
    },
    {
        name: 'jobCode',
        //岗位编码
        type: 'string'
    },
    {
        name: 'managerCode',
        //直接上级
        type: 'string'
    }
    ,
    {
        name: 'mobilePhone',
        //手机
        type: 'string'
    },
    {
        name: 'phone',
        //座机
        type: 'string'
    },
    {
        name: 'email',
        //邮箱
        type: 'string'
    },
    {
        name: 'status',
        //状态
        type: 'string'
    },
    {
        name: 'modifyDate',
        type: 'date',
        dateFormat:'time'
    }]
});

function booleanTypeGender(value){
	if(Ext.isEmpty(value)){
		return value;
	}
	if(value== 'M'){
		return '男';
	}
	if(value == 'F'){
		return '女';
	}
	return value;
}



Ext.define('Butterfly.baseinfo.employee.EmployeeForm', {
    extend: 'Ext.form.Panel',
    id: 'oms_baseinfo_employee_EmployeeForm_id',
    frame: true,
    title: baseinfo.employee.i18n('oms.common.queryCondition'),//查询条件
    height: 113,
    collapsible: true,
    layout: 'column',
    region: 'north',
    defaults: {
        margin: '8 10 5 10',
        labelWidth: 100,
        columnWidth: 0.23,
        labelAlign: 'right'
    },
    defaultType: 'textfield',
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        me.items = [{
            name: 'empCode',
            fieldLabel: baseinfo.employee.i18n('bse.employee.empCode'),//'员工工号',
            xtype: 'textfield'
        },
        {
            name: 'empName',
            fieldLabel: baseinfo.employee.i18n('bse.employee.empName'),//'员工姓名',
            xtype: 'textfield'
        },
        {
            name: 'deptName',
            fieldLabel:baseinfo.employee.i18n('bse.employee.deptName'),//'部门名称',
            xtype: 'textfield'
        }],
        me.buttons = [{
            text: baseinfo.employee.i18n('oms.common.query'),//查询
            handler: function() {
                if (me.getForm().isValid()) {
                    me.up().getEmployeeGrid().getPagingToolbar().moveFirst();
                }
            }
        },
        {
            text: baseinfo.employee.i18n('oms.common.reset'),//重置
            handler: function() {
                me.getForm().reset();
            }
        }];
        me.callParent([cfg]);
    }
});

/**
 * 员工信息表格
 */
Ext.define('Butterfly.baseinfo.employee.EmployeeGrid', {
    extend: 'Ext.grid.Panel',
    frame: true,
    autoScroll: true,
    width: '100%',
    height: document.documentElement.clientHeight - 150,
    stripeRows: true,
    // 交替行效果
    region: 'center',
    emptyText: baseinfo.employee.i18n('oms.common.resultisNull'),//查询结果为空
    //查询结果为空
    viewConfig: {
        enableTextSelection: true
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
    constructor: function(config) {
        var me = this,
        cfg = Ext.apply({},
        config);
        me.columns = [{
            text: '序号',
            width: 60,
            xtype: 'rownumberer',
            align: 'center'
        },
        {
            text: baseinfo.employee.i18n('bse.employee.empCode'),//'员工工号'
            dataIndex: 'empCode',
            align: 'center',
            flex: 1
        },
        {
            text: baseinfo.employee.i18n('bse.employee.empName'),//'员工姓名',
            dataIndex: 'empName',
            align: 'center',
            flex: 1
        },
        {
            text: baseinfo.employee.i18n('bse.employee.deptName'),//'性别',
            dataIndex: 'gender',
            align: 'center',
            flex: 1,
            renderer:function(value){
        		return booleanTypeGender(value);
        }
        },
        {
            text: baseinfo.employee.i18n('bse.employee.pinyinShort'),//'拼音名',
            dataIndex: 'pinyin',
            align: 'center',
            flex: 1
        },
        {
            text: baseinfo.employee.i18n('bse.employee.pinyinShort'),//'拼音简称',
            dataIndex: 'pinyinShort',
            align: 'center',
            flex: 1
        },
        {
            text: baseinfo.employee.i18n('bse.employee.account'),//'账号',
            dataIndex: 'account',
            align: 'center',
            flex: 1
        },
        {
            text: baseinfo.employee.i18n('bse.employee.password'),//'密码',
            dataIndex: 'password',
            align: 'center',
            flex: 1
        },
        {
            text:baseinfo.employee.i18n('bse.employee.jobName'),// '岗位名称',
            dataIndex: 'jobName',
            align: 'center',
            flex: 1
        },
        {
            text:baseinfo.employee.i18n('bse.employee.deptName'),//'部门名称',
            dataIndex: 'deptName',
            align: 'center',
            flex: 1
        },
        {
            text: baseinfo.employee.i18n('bse.employee.managerCode'),//'直接上级',
            dataIndex: 'managerCode',
            align: 'center',
            flex: 1
        },
        {
            text: baseinfo.employee.i18n('bse.employee.mobilePhone'),//'手机',
            dataIndex: 'mobilePhone',
            align: 'center',
            flex: 1
        },
        {        	
            text: baseinfo.employee.i18n('bse.employee.modifyDate'),//'修改时间',
            dataIndex: 'modifyDate',
            align: 'center',
            flex: 1,
            xtype :'datecolumn', 
            format :'Y-m-d H:i:s'
        }],
        me.store = Ext.create('Butterfly.baseinfo.employee.EmployeeStore', {
            autoLoad: false
        });
        me.selModel = Ext.create('Ext.selection.CheckboxModel', { //多选框
            mode: 'MULTI',
            checkOnly: true
        });
        me.bbar
        me.bbar = me.getPagingToolbar();
        me.callParent([cfg]);
    }
});



/**
 * 员工信息store
 */
Ext.define('Butterfly.baseinfo.employee.EmployeeStore', {
    extend: 'Ext.data.Store',
    model: 'Butterfly.baseinfo.employee.EmployeeEntity',
    pageSize: 20,
    proxy: {
        type: 'ajax',
        actionMethods: 'post',
        url: 'employeeAction!queryEmployeeList.action',
        reader: {
            type: 'json',
            rootProperty: 'employeeVo.employeeList',
            totalProperty: 'totalCount' //总个数
        }
    },
    listeners: {
        beforeload: function(store, operation, eOpts) {
            var queryForm = Ext.getCmp('oms_baseinfo_employee_EmployeeForm_id');
            if (queryForm != null) {
                var params = {
                    'employeeVo.employeeEntity.deptName': queryForm.getForm().findField('deptName').getValue(),
                    'employeeVo.employeeEntity.empCode': queryForm.getForm().findField('empCode').getValue(),
                    'employeeVo.employeeEntity.empName': queryForm.getForm().findField('empName').getValue()
                };
                Ext.apply(store.proxy.extraParams, params);
            }
        }
    }
});




Ext.onReady(function() {
    var queryForm = Ext.create("Butterfly.baseinfo.employee.EmployeeForm");
    var employeeGrid = Ext.create("Butterfly.baseinfo.employee.EmployeeGrid");
    Ext.create('Ext.Viewport', {
        header: {
            titlePosition: 2,
            titleAlign: 'center'
        },
        border: "0 0 0 0",
        renderTo: Ext.getBody(),
        layout: {
            type: 'border'
        },
        getQueryForm: function() {
            return queryForm;
        },
        getEmployeeGrid: function() {
            return employeeGrid;
        },
        items: [queryForm, employeeGrid]
    });
	
});
















