/**
 * 获取当前登录用户信息
 * 从父页面中获取当前登录用户信息
 * @returns
 * @author 高佳
 * @date 2015年5月15日
 * @update
 */
function getUserContext(){
	return window.parent.UserContext;
}
/**
 * 获取数据字典信息
 * 从父页面中获取数据字典信息
 * @returns
 * @author 高佳
 * @date 2015年5月15日
 * @update
 */
function getDataDictionary(){
	return window.parent.DataDictionary;
}

/**
 * 判断当前用户是否有此权限
 * 
 * @param authCode
 * @returns
 * @author 蒋落琛
 * @date 2015-6-2
 * @update
 */
function isButtonHide(functionCode){
	return null;//window.parent.UserContext.isButtonHide(functionCode);
}


/**
 * 日期类型数据转换器
 */
function dateConvert(value, record) {
	if (value != null) {
		var date = new Date(value);
		return date;
	} else {
		return null;
	}
}

/**
 * 表格日期显示
 * @param value
 * @param format
 * @returns
 */
function dateRender(value,format){
	if(Ext.isEmpty(format)){
		format = 'Y-m-d';
	}
	if(!Ext.isEmpty(value)){
		return Ext.Date.format(new Date(value), format);
	}
	return value;
}

/**
 * 是和否Render
 * @param value
 * @param format
 * @returns
 */
function booleanTypeRender(value){
	if(Ext.isEmpty(value)){
		return value;
	}
	if(oms.booleanType.yes == value){
		return '是';
	}
	if(oms.booleanType.no == value){
		return '否';
	}
	return value;
}

/**
 * 保留2位小数 四舍五入
 */
function numberRound(value){
	return value.toFixed(2);
}

/**
 * 保留2位小数下取
 */
function numberFloor(value){
	return Math.floor(value*100)/100;
}

/**
 * 保留2位小数上取
 */
function numberCeil(value){
	return Math.ceil(value*100)/100;
	
}

/**
 * 字体图标按钮定义
 */
//新增按钮
Ext.define('Butterfly.commonButton.CommonAddButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.addbutton',
	glyph : 0xf055,
	cls: 'add-btn-item'
});
//删除按钮
Ext.define('Butterfly.commonButton.CommonDeleteButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.deletebutton',
	glyph : 0xf014,
	cls: 'delete-btn-item'
});
//修改编辑按钮
Ext.define('Butterfly.commonButton.CommonUpdateButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.updatebutton',
	glyph : 0xf044,
	cls: 'update-btn-item'
});
//设置按钮
Ext.define('Butterfly.commonButton.CommonSettingsButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.settingsbutton',
	glyph : 0xf085,
	cls: 'settings-btn-item'
});
//刷新
Ext.define('Butterfly.commonButton.CommonRefreshButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.refreshbutton',
	glyph : 0xf021,
	cls: 'settings-btn-item'
});
//下载按钮
Ext.define('Butterfly.commonButton.CommonDownloadButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.downloadbutton',
	glyph : 0xf08e,
	cls: 'download-btn-item'
});
//上传按钮
Ext.define('Butterfly.commonButton.CommonUploadButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.uploadbutton',
	glyph : 0xf093,
	cls: 'upload-btn-item'
});
//查询按钮
Ext.define('Butterfly.commonButton.CommonSearchButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.searchbutton',
	glyph : 0xf00e,
	cls: 'search-btn-item'
});
//关闭按钮
Ext.define('Butterfly.commonButton.CommonCloseButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.closebutton',
	glyph : 0xf00d,
	cls: 'delete-btn-item'
});

//支付按钮
Ext.define('Butterfly.commonButton.CommonPaypalButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.paypalbutton',
	glyph : 0xf1ed,
	cls: 'paypal-btn-item'
});

//人民币（金额处理）按钮
Ext.define('Butterfly.commonButton.CommonRmbButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.rmbbutton',
	glyph : 0xf157,
	cls: 'rmb-btn-item'
});

//打印按钮
Ext.define('Butterfly.commonButton.CommonPrintButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.printbutton',
	glyph : 0xf02f,
	cls: 'print-btn-item'
});

//图表按钮
Ext.define('Butterfly.commonButton.CommonChartButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.chartbutton',
	glyph : 0xf080,
	cls: 'chart-btn-item'
});

//汽车按钮
Ext.define('Butterfly.commonButton.CommonTruckButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.truckbutton',
	glyph : 0xf0d1,
	cls: 'truck-btn-item'
});

//拷贝（导入）按钮
Ext.define('Butterfly.commonButton.CommonExportButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.exportbutton',
	glyph : 0xf0c5,
	cls: 'export-btn-item'
});
//导入开单
Ext.define('Butterfly.commonButton.CommonLevelDownButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.develdownbutton',
	glyph : 0xf149,
	cls: 'export-btn-item'
});
//用户
Ext.define('Butterfly.commonButton.CommonUserButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.userbutton',
	glyph : 0xf007,
	cls: 'export-btn-item'
});

//提交
Ext.define('Butterfly.commonButton.CommonSubmitButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.submitbutton',
	glyph : 0xf00c,
	cls: 'export-btn-item'
});

//审核
Ext.define('Butterfly.commonButton.CommonAuditButton', {
	extend : 'Ext.button.Button',
	alias: 'widget.auditbutton',
	glyph : 0xf14a,
	cls: 'export-btn-item'
});

Ext.override(Ext.window.Window, {
	buttonAlign  : 'center'
});


function commsubmit(btn,wintype,isopen){
	//获取表单数据
	var win = btn.up('window'),
	form = win.down('form').getForm(),orderOperateVo = {},params = {},startdate,enddate;
	orderOperateVo = form.getValues();
	if(!form.isValid()){
		oms.showWoringMessage('请完善必填字段信息');  
	}
	if(Ext.isEmpty(orderOperateVo.orderNo)){
		oms.showWoringMessage('订单号为空');
		win.close();
		return;
	}
	if(wintype=='receive'){
		url = 'orderOperateAction!receiveSuccess.action';
		orderOperateVo.waybillCount = parseFloat(orderOperateVo.waybillCount);
	}else{
		url = 'orderOperateAction!enquirySuccess.action';
		//判断是否在同一天
		if(!Ext.isEmpty(orderOperateVo.custDemandStartTime)&&!Ext.isEmpty(orderOperateVo.custDemandEndTime)){
			startdate = orderOperateVo.custDemandStartTime.split(' ')[0];
			enddate = orderOperateVo.custDemandEndTime.split(' ')[0];
			if(Date.parse(startdate)!=Date.parse(enddate)){
	    		oms.showWoringMessage('客户期望提货/送货起始时间和结束时间要选择同一天');
	    		return;
	    	}
		}
		//判断起始时间是否正确
		if(Date.parse(orderOperateVo.custDemandStartTime)>=Date.parse(orderOperateVo.custDemandEndTime)){
			oms.showWoringMessage('客户期望提货/送货起始时间不能大于结束时间');
			return;
		}
		orderOperateVo.custDemandStartTime = orderOperateVo.custDemandStartTime+':00';
		orderOperateVo.custDemandEndTime = orderOperateVo.custDemandEndTime+':00';
	}
	params.orderOperateVo = orderOperateVo;
	oms.requestJsonAjax(url,params,function(res){
		//console.log(res);
		if(res.success){
			oms.showInfoMsg('操作成功',function(){
    			win.close();
    			if(isopen){
    				var url = '/oms-web/order/toLookOrder.action?orderNo='+res.orderOperateVo.orderNo;
        			window.parent.initTabpanel('web_10901031','订单详情',url, true);
        			window.parent.delTabpanel('web_1090103');
    			}
    		}); 
		}
	},function(res){
		oms.showErrorMes(res.message)
	})
}
