Ext.onReady(function() {
	Ext.create('Ext.panel.Panel', {
		id : 'T_baseinfo-selector_content',
		layout : 'auto',
		items : [{
			xtype : 'dynamicorgcombselector',
			id : 'dynamicorgselector',
			fieldLabel : '组织单选(dynamicorgcombselector)',
			isTransferCenter : 'Y',
			labelWidth : 160 
		},{
			xtype : 'datetimefield',
			format : 'Y-m-d H:i:s',
			id : 'datetimepicker',
			fieldLabel : '日期',
			
			labelWidth : 160 
		},{
			xtype : 'dynamicusercombselector',
			id : 'dynamicusercombselector',
			fieldLabel : '用户单选(dynamicusercombselector)',
			labelWidth : 160 
		},{
			xtype : 'dynamicemployeecombselector',
			id : 'dynamicemployeecombselector',
			fieldLabel : '员工单选(dynamicemployeecombselector)',
			labelWidth : 160 
		},{
			xtype : 'commondistrictselector',
			id : 'commondistrictselector',
			fieldLabel : '行政区域单选(commondistrictselector)',
			districtType : 'CITY',
			labelWidth : 160 
		},{
			xtype : 'dynamicfranchisecombselector',
			id : 'dynamicfranchisecombselector',
			fieldLabel : '特许经营网点单选(dynamicfranchisecombselector)',
			orgCode : '',
			labelWidth : 160 
		},{
			xtype : 'dynamicTransfercombselector',
			id : 'dynamicTransfercombselector',
			fieldLabel : '上下转移目的地(dynamicTransfercombselector)',
			orgCode : 'DP141814',
			labelWidth : 160 
		},{
			xtype : 'dynamiccardcitycombselector',
			id : 'dynamiccardcitycombselector',
			fieldLabel : '价卡城市单选(dynamiccardcitycombselector)',
			labelWidth : 160
		},{
			xtype : 'dynamicommonFinanceOrgselector',
			id : 'dynamicommonFinanceOrgselector',
			fieldLabel : '财务组织单选(dynamicommonFinanceOrgselector)',
			orgCode : '',
			labelWidth : 160
		},{
			xtype : 'commonproductselector',
			id : 'commonproductselector',
			fieldLabel : '产品类型(commonproductselector)',
			labelWidth : 160 
		},/*{
			fieldLabel : '国省市区(xtype:linkregincombselector,type:N-P-C-C)',
			labelWidth:320,
			type : 'N-P-C-C',
			xtype : 'linkregincombselector',
			id : 'npcclinkregincombselector'
		},*/ {
			labelWidth:20,
			cityLabel : '市',
			cityName : 'cityName',//名称
			provinceLabel : '省',
			provinceName:'provinceName',//省名称
			areaLabel : '县',
			areaName : 'areaName',// 县名称
			type : 'P-C-C',
			xtype : 'linkregincombselector'
		},{
			xtype: 'button',
            text: '测试等待',
            width: 80,
            handler : function() {
            	/*
            	var task = {
            		    run: function(){
            		    	oms.loadMaskClear();
            		    },
            		    interval: 3000 //2 second
            	     }
        	     Ext.TaskManager.start(task);*/
            	
            	oms.loadMaskshow("请稍等...");

            	
			}
		},{
			name: 'deliveryMethod',
			id: 'deliveryMethod_id',
			fieldLabel: '测试 setTermscode',
			flex: 0.9,
			value: '',
			xtype: 'dataDictionarySelector',
			termsCode: "PKP_DELIVERY_METHOD",			
		},{
			xtype: 'button',
            text: '测试其他',
            width: 80,
            handler : function() {
            	//oms.loadMaskshow("其他...");
            	Ext.getCmp('deliveryMethod_id').setTermsCode('PKP_TM_DELIVERY_METHOD');
			}
		},{
			xtype: 'button',
            text: '关闭等待',
            width: 80,
            handler : function() {
            	Ext.getCmp('deliveryMethod_id').setTermsCode('PKP_DELIVERY_METHOD');
			}
		},{
			xtype : 'toolbar',
			labelWidth : 60,
			width :1000,
			layout : 'column',
			items:[{
				xtype: 'settingsbutton',
	            text: '配置',
	            width: 80,
	            handler : function() {
	            	do_printpreview('demo',{"fnumber" : "123456756"});
				}
			},{
				xtype: 'addbutton',
	            text: '新增',
	            width: 80
			},{
				xtype: 'updatebutton',
	            text: '修改',
	            width: 80
			},{
				xtype: 'deletebutton',
	            text: '删除',
	            width: 80
			},{
				xtype: 'searchbutton',
	            text: '查询',
	            width: 80
			},{
				xtype: 'uploadbutton',
	            text: '上传',
	            width: 80
			},{
				xtype: 'downloadbutton',
	            text: '下载',
	            width: 80
			},{
				xtype: 'printbutton',
	            text: '打印',
	            width: 80
			},{
				xtype: 'chartbutton',
	            text: '图表',
	            width: 80
			},{
				xtype: 'truckbutton',
	            text: '汽车',
	            width: 80
			},{
				xtype: 'paypalbutton',
	            text: '支付',
	            width: 80
			},{
				xtype: 'rmbbutton',
	            text: '人民币',
	            width: 80
			},,{
				xtype: 'exportbutton',
	            text: '拷贝|导入',
	            width: 90
			},{
				xtype: 'userbutton',
	            text: '人员|审核',
	            width: 90
			}]
		 
		},{
			xtype: 'rating',
			listeners:{
				change:function(picker, value){
					 console.log('Rating ' + value);
				}
			}
		}],
		renderTo: Ext.getBody()
	});
});



