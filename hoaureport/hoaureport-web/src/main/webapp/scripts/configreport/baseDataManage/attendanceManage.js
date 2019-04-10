
/**主界面->出勤信息模型*/
Ext.define('Ty.Report.AttendanceInfo.AttendanceInfoModel', {
	extend : 'Ext.data.Model',
	fields : [ 
    {// ID
	name : 'attendanceId'
    },         
	{// 工号
		name : 'userId'
	},
	{// 姓名
		name : 'userName'
	},
	{// 操作公司
		name : 'companyName'
	},
	{//日期
		name : 'workDate'
	},
	{//所属年月
		name : 'currMonth'
	},
	{// 工作量
		name : 'dayLoadQuantity'
	}]
});

/***主界面->出勤信息store*/
Ext.define('Ty.Report.AttendanceInfo.AttendanceInfoStore', {
	extend : 'Ext.data.Store',
	model : 'Ty.Report.AttendanceInfo.AttendanceInfoModel',
	pageSize : 100,// 分页条数
	proxy : {
		type : 'ajax',
		url : 'attendanceManageAction!queryAttendanceInfo.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'attendanceVo.monthAttendanceInfoList',
			totalProperty : 'totalCount'
		}
	},
	listeners : {
		//在加载数据之前操作
		beforeload : function(store, operation, eOpts) {
				var params = {
						'attendanceVo.attendanceInfo.userId':Ext.String.trim(Ext.getCmp('userId').getValue()),
						'attendanceVo.attendanceInfo.companyName':Ext.String.trim(Ext.getCmp('companyName').getValue()),
						'attendanceVo.attendanceInfo.currMonth':Ext.String.trim(Ext.getCmp('currMonth').getValue()),
				};
				Ext.apply(store.proxy.extraParams, params);
		}
	}
});
Ext.onReady(function(){
	//初始化提示条
    Ext.QuickTips.init();
    var operateFlag = 0;
	/**
	* 查询条件form
	*/
	 var isFirstQuery=0;//是否是第一次加载0为第一次加载，1为不是第一次加载
	 

	 /**主界面->出勤信息表单*/
	 var queryForm = Ext.create('Ext.form.Panel',{
			layout : {
				type : 'table',	//table布局
				columns : 5	//列数
			},
			defaults: {//统一设置宽、居右
				margin:'5 5 0 5',//间距
				labelWidth:70,
				border:false,
				labelAlign: 'right',
				width : 180
			},
			defaultType:'textfield',//默认类型
			frame:true,
			height:35,
			region:'north',
			items:[{
				fieldLabel: '查询年月',
				id : 'currMonth',
				name:'currMonth'
			},{
				fieldLabel: '工号',
				id : 'userId',
				name:'userId'
			},{
				fieldLabel: '操作公司',
				id : 'companyName',
				name:'companyName'
			},{
				xtype:'button',
				text:'查询',
				width:60,
				border : true,
				handler:function(){
					attendanceInfoStore.reload();  //加载数据
				}
			},{
				xtype:'button',
				text:'重置',
				width:60,
				border : true,
				handler:function(){
					queryForm.getForm().reset();
				}
			}
			]
		});
//主界面->表格->store
	var attendanceInfoStore=Ext.create('Ty.Report.AttendanceInfo.AttendanceInfoStore',{
		id:'attendanceInfoStoreId'
	});
	

	/**
	 * 主界面->表格
	 */
	var  attendanceInfoGrid =Ext.create('Ext.grid.Panel', {
		store: attendanceInfoStore,
		border:false,
		columnLines:true,
		region:'center',//border布局的中间
		columns: [//列
		{xtype: 'rownumberer',
			width:40},
        {
            dataIndex: 'userName',
			text:'姓名',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'userId',
			text:'工号',
            width:150,
            menuDisabled:true
        },{
            dataIndex: 'companyName',
			text:'操作公司',
			width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'workMonth',
			text:'月份',
			 width:150,
			 menuDisabled:true,
        },{
            dataIndex: 'day1LoadQuantity',
			text:'1',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day2LoadQuantity',
			text:'2',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day3LoadQuantity',
			text:'3',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day4LoadQuantity',
			text:'4',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day5LoadQuantity',
			text:'5',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day6LoadQuantity',
			text:'6',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day7LoadQuantity',
			text:'7',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day8LoadQuantity',
			text:'8',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day9LoadQuantity',
			text:'9',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day10LoadQuantity',
			text:'10',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day11LoadQuantity',
			text:'11',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day12LoadQuantity',
			text:'12',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day13LoadQuantity',
			text:'13',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day14LoadQuantity',
			text:'14',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day15LoadQuantity',
			text:'15',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day16LoadQuantity',
			text:'16',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day17LoadQuantity',
			text:'17',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day18LoadQuantity',
			text:'18',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day19LoadQuantity',
			text:'19',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day20LoadQuantity',
			text:'20',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day21LoadQuantity',
			text:'21',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day22LoadQuantity',
			text:'22',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day23LoadQuantity',
			text:'23',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day24LoadQuantity',
			text:'24',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day25LoadQuantity',
			text:'25',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day26LoadQuantity',
			text:'26',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day27LoadQuantity',
			text:'27',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day28LoadQuantity',
			text:'28',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day29LoadQuantity',
			text:'29',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day30LoadQuantity',
			text:'30',
			 width:80,
			 menuDisabled:true,
        },{
            dataIndex: 'day31LoadQuantity',
			text:'31',
			 width:80,
			 menuDisabled:true,
        },],
        listeners : {
			itemClick : function() {

			}
		},
		 dockedItems:[{//分页
	 			xtype:'pagingtoolbar',
	 			id: 'queryAttendanceInfoGridPaginId',
	 			store:attendanceInfoStore,
	 			dock:'bottom',
	 			displayInfo : true,
	 			autoScroll : true
	 		}],	
       
	});

	
	//整个页面布局
	var viewport = Ext.create('Ext.Viewport', {
		layout: {
			type: 'border',//用border布局 ，把页面分为上下部分 form为上、grid为下
			padding: 5
		},
		items: [queryForm,attendanceInfoGrid]
	});
	
});