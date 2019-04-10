// ------------------------------------常量----------------------------------
var oms = {};
oms.operatorCount = {
	defaultV : 0,
	successV : 1,
	failureV : -1
}; // 操作返回值 1为成功，-1为失败
oms.delAgencyType = 'MANY'; // 删除的类型，批量
oms.delType = 'MANY'; // 删除的类型，批量
oms.viewState = {
	add : 'ADD',
	update : 'UPDATE',
	view : 'VIEW'
}; // 查看状态viewState："ADD"新增,"UPDATE"修改,"VIEW"查看
oms.booleanType = {
	yes : 'Y',
	no : 'N'
}; // booleanType 对应后台常量 "布尔类型"
oms.effectiveState = {
	active : 'Y',
	inactive : 'N'
}; // booleanType 对应后台常量 "生效/未生效"
oms.booleanStr = {
	yes : 'true',
	no : 'false'
}; // booleanStr 从复选框中得到值
oms.operateType = {
	save : 'SAVE'
}; // 标识 是否 保存操作
oms.levelType = {
	p : 'PARENT',
	c : 'CHILDREN'
}; // 标识 是父容器还是子容器
// 图片glyph
oms.iconGlyph = {
	add : 0xf055,// 新增
	update : 0xf044,// 修改
	del : 0xf014,// 设置
	download : 0xf019,// 下载
	upload : 0xf093,// 上传
	settings : 0xf013,// 设置
	search : 0xf002
	// 查询
};

/**
 * <p>
 * AJAX请求<br/>
 * <p>
 * 
 * @param url
 *            url请求地址
 * @param params
 *            PARAMS参数
 * @param successFn
 *            successFn调用成功
 * @param failFn
 *            FAILFN调用失败
 * @param syncFlag
 *            是否异步
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.requestJsonAjax = function(url, params, successFn, failFn, asyncFlag) {
	if (Ext.isEmpty(asyncFlag)) {
		asyncFlag = true;
	}
	// !(asyncFlag && true)
	Ext.Ajax.request({
				url : url,
				async : asyncFlag,
				jsonData : params,
				success : function(response) {
					var result = Ext.decode(response.responseText);
					// 系统异常
					if (result.isException) {
						var errorWindow = Ext.create(
								'Hoau.errorMessage.Window', {
									message : result.message,
									requestId : result.requestId,
									stackTrace : result.stackTrace
								});
						errorWindow.show();
					} else {
						if (result.success) {
							successFn(result);
						} else {
							failFn(result);
						}
					}
				},
				failure : function(response) {
					var result = Ext.decode(response.responseText);
					failFn(result);
				},
				exception : function(response) {
					var result = Ext.decode(response.responseText);
					failFn(result);
				}
			});
};

/**
 * <p>
 * AJAX请求<br/>
 * <p>
 * 
 * @param url
 *            url请求地址
 * @param params
 *            PARAMS参数
 * @param successFn
 *            successFn调用成功
 * @param failFn
 *            FAILFN调用失败
 * @param eventFn
 *            异常后触发操作
 * @param syncFlag
 *            是否异步
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.requestJsonEventAjax = function(url, params, successFn, failFn, eventFn,
		asyncFlag) {
	if (Ext.isEmpty(asyncFlag)) {
		asyncFlag = true;
	}
	// !(asyncFlag && true)
	Ext.Ajax.request({
				url : url,
				async : asyncFlag,
				jsonData : params,
				success : function(response) {
					var result = Ext.decode(response.responseText);
					// 系统异常
					if (result.isException) {
						var errorWindow = Ext.create(
								'Hoau.errorMessage.Window', {
									message : result.message,
									requestId : result.requestId,
									stackTrace : result.stackTrace
								});
						eventFn();
						errorWindow.show();
					} else {
						if (result.success) {
							successFn(result);
						} else {
							failFn(result);
						}
					}
				},
				failure : function(response) {
					var result = Ext.decode(response.responseText);
					failFn(result);
				},
				exception : function(response) {
					var result = Ext.decode(response.responseText);
					failFn(result);
				}
			});
};

/**
 * <p>
 * Ajax请求--文件表单的请求<br/>
 * <p>
 * 
 * @param url
 *            url请求地址
 * @param params
 *            PARAMS参数
 * @param successFn
 *            successFn调用成功
 * @param failFn
 *            FAILFN调用失败
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.requestFileUploadAjax = function(url, form, successFn, failFn) {
	Ext.Ajax.request({
				url : url,
				form : form,
				isUpload : true,
				success : function(form, action) {
					var result = action.result;
					if (result.success) {
						successFn(result);
					} else {
						failFn(result);
					}
				},
				failure : function(form, action) {
					var result = action.result;
					failFn(result);
				},
				exception : function(response) {
					var result = Ext.decode(response.responseText);
					failFn(result);
				}
			});
};

/**
 * <p>
 * Ajax请求--非json字符串<br/>
 * <p>
 * 
 * @param url
 *            url请求地址
 * @param params
 *            PARAMS参数
 * @param successFn
 *            successFn调用成功
 * @param failFn
 *            FAILFN调用失败
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.requestAjax = function(url, params, successFn, failFn) {
	Ext.Ajax.request({
				url : url,
				params : params,
				success : function(response) {
					var result = Ext.decode(response.responseText);
					if (result.success) {
						successFn(result);
					} else {
						failFn(result);
					}
				},
				failure : function(response) {
					var result = Ext.decode(response.responseText);
					failFn(result);
				},
				exception : function(response) {
					var result = Ext.decode(response.responseText);
					failFn(result);
				}
			});
};

/**
 * <p>
 * 填值方法<br/>
 * <p>
 * 
 * @param form
 *            需要加载数据的form
 * @param formRecord
 *            需要加载的数据model
 * @param grid
 *            需要加载数据的grid
 * @param girdData
 *            需要加载的数据data
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.formReset = function(form, formRecord, grid, girdData) {
	if (!Ext.isEmpty(form) && !Ext.isEmpty(formRecord)) {
		Ext.Array.each(form, function(name, index, countriesItSelf) {
					form[index].loadRecord(formRecord[index]);
				});
	}
	if (!Ext.isEmpty(grid)) {
		Ext.Array.each(grid, function(name, index, countriesItSelf) {
					if (Ext.isEmpty(girdData)) {
						grid[index].store.loadData([]);
					} else {
						grid[index].store.loadPage(1);
					}
				});
	}
};

/**
 * .
 * <p>
 * form表单所有元素 设置 readOnly值<br/>
 * <p>
 * 
 * @param flag
 *            需要设值的表单
 * @param form
 *            表单readOnly值
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.formSetReadOnly = function(flag, form) {
	var arr = form.items.items;
	if (!Ext.isEmpty(arr)) {
		for (var i = 0; i < arr.length; i++) {
			arr[i].setReadOnly(flag);
		}
	}
};
oms.formFieldSetReadOnly = function(flag, form) {
	var arr = form.query('field');
	if (!Ext.isEmpty(arr)) {
		for (var i = 0; i < arr.length; i++) {
			arr[i].setReadOnly(flag);
		}
	}
};

/**
 * .
 * <p>
 * 消息提示框 ，无国际化<br/>
 * <p>
 * 
 * @param message
 *            提示消息
 * @param fun
 *            回调fun
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.showInfoMsg = function(message, fun) {
	var len = message.length;
	Ext.Msg.show({
				title : '系统提醒您:',
				width : 110 + len * 15,
				msg : '<div id="message">' + message + '</div>',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.INFO,
				callback : function(e) {
					if (!Ext.isEmpty(fun)) {
						if (e == 'ok') {
							fun();
						}
					}
				}
			});

};

/**
 * .
 * <p>
 * 警告提示框<br/>
 * <p>
 * 
 * @param message
 *            提示消息
 * @param fun
 *            回调fun
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.showWoringMessage = function(message, fun) {
	var len = message.length;
	Ext.Msg.show({
				title : '系统提醒您:',
				msg : message,
				// cls:'mesbox',
				width : 110 + len * 15,
				msg : '<div id="message">' + message + '</div>',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.WARNING,
				callback : function(e) {
					if (!Ext.isEmpty(fun)) {
						if (e == 'ok') {
							fun();
						}
					}
				}
			});

};

/**
 * .
 * <p>
 * 是和否选择提示框<br/>
 * <p>
 * 
 * @param message
 *            提示消息
 * @param fun
 *            回调fun
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.showQuestionMes = function(message, fun) {
	var len = message.length;
	Ext.Msg.show({
				title : '系统提醒您:',
				width : 110 + len * 15,
				msg : '<div id="message">' + message + '</div>',
				buttons : Ext.Msg.YESNO,
				icon : Ext.MessageBox.QUESTION,
				callback : function(e) {
					if (!Ext.isEmpty(fun)) {
						fun(e);
					}
				}
			});
};

/**
 * .
 * <p>
 * 错误提示框<br/>
 * <p>
 * 
 * @param message
 *            提示消息
 * @param fun
 *            回调fun
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.showErrorMes = function(message, fun) {
	var len = message.length;
	Ext.Msg.show({
				title : '系统提醒您:',
				width : 110 + len * 15,
				msg : '<div id="message">' + message + '</div>',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.ERROR,
				callback : function(e) {
					if (!Ext.isEmpty(fun)) {
						if (e == 'ok') {
							fun();
						}
					}
				}
			});
};

/**
 * .
 * <p>
 * 查看状态下 只有 取消按钮可用 [添加网点,取消]按钮分别占 0和1<br/>
 * <p>
 * 
 * @param win
 *            提示消息
 * @param viewState
 *            查看状态
 * @param operateType
 *            操作状态
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.operateWinBtn = function(win, viewState, operateType) {
	// 查看状态下 只有 取消按钮可用 [添加,取消]按钮分别占 0和1
	if (oms.viewState.view === viewState) {
		var btnArr = win.query('button');
		for (var i = 0; i < btnArr.length; i++) {
			btnArr[i].setDisabled(i != 2);
		}
	} else if (!Ext.isEmpty(operateType)
			&& oms.operateType.save === operateType) {
		var btnArr = win.query('button');
		for (var i = 0; i < btnArr.length; i++) {
			btnArr[i].setDisabled(i > 2);
		}
	}
};

/**
 * .
 * <p>
 * 覆盖Ext.form.RadioGroup的setValue方法<br/> item.getRawValue全为false
 * <p>
 * 
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
Ext.override(Ext.form.RadioGroup, {
			setValue : function(v) {
				if (this.rendered)
					this.items.each(function(item) {
								item.setValue(item.inputValue == v);
							});
				else {
					for (var k = 0; k < this.items.items.length; k++) {
						this.items.items[k]
								.setValue(this.items.items[k].inputValue == v);
					}
				}
			}
		});

/**
 * .
 * <p>
 * 公共方法，通过storeId和model创建STORE<br/>
 * <p>
 * 
 * @param storeId
 * @param model
 *            store所用到的model名
 * @param fields
 *            store所用到的fields
 * @param data
 * @returns store 返回创建的store
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.getStore = function(storeId, model, fields, data) {
	var store = null;
	if (!Ext.isEmpty(storeId)) {
		store = Ext.data.StoreManager.lookup(storeId);
	}
	if (Ext.isEmpty(data)) {
		data = [];
	}
	if (!Ext.isEmpty(model)) {
		if (Ext.isEmpty(store)) {
			store = Ext.create('Ext.data.Store', {
						storeId : storeId,
						model : model,
						data : data
					});
		}
	}
	if (!Ext.isEmpty(fields)) {
		if (Ext.isEmpty(store)) {
			store = Ext.create('Ext.data.Store', {
						storeId : storeId,
						fields : fields,
						data : data
					});
		}
	}
	return store;
};

/**
 * .
 * <p>
 * 设置元素为readOnly<br/>
 * <p>
 * 
 * @param readOnlyIdList
 *            设置为readOnly的元素ID数组
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.setReadOnly = function(readOnlyIdList) {
	for (var i = 0; i < readOnlyIdList.length; i++) {
		Ext.getCmp(readOnlyIdList[i]).setReadOnly(true);
		Ext.getCmp(readOnlyIdList[i]).addCls('readonly');
	}
};

/**
 * .
 * <p>
 * 设置元素为隐藏并且销毁，使其不在校验<br/>
 * <p>
 * 
 * @param hiddenIdList
 *            设置为隐藏的元素ID数组
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.setHiddenAndDestroy = function(hiddenIdList) {
	for (var i = 0; i < hiddenIdList.length; i++) {
		Ext.getCmp(hiddenIdList[i]).hide();
		Ext.getCmp(hiddenIdList[i]).destroy();
	}
};

/**
 * .
 * <p>
 * 设置元素为隐藏<br/>
 * <p>
 * 
 * @param hiddenIdList
 *            设置为隐藏的元素ID数组
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.setHidden = function(hiddenIdList) {
	for (var i = 0; i < hiddenIdList.length; i++) {
		Ext.getCmp(hiddenIdList[i]).hide();
	}
};

/**
 * .
 * <p>
 * 设置元素为销毁<br/>
 * <p>
 * 
 * @param destoryIdList
 *            设置为destory的元素ID数组
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.setDestroy = function(destoryIdList) {
	for (var i = 0; i < destoryIdList.length; i++) {
		Ext.getCmp(destoryIdList[i]).destroy();
	}
};

/**
 * .
 * <p>
 * 设置元素为不可用<br/>
 * <p>
 * 
 * @param disabledIdList
 *            设置为Disabled的元素ID数组
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.setDisabled = function(disabledIdList) {
	for (var i = 0; i < disabledIdList.length; i++) {
		Ext.getCmp(disabledIdList[i]).setDisabled(true);
	}
};

/**
 * .
 * <p>
 * 清除事件<br/>
 * <p>
 * 
 * @param clearIdList
 *            设置为清除事件的元素ID数组
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.clearListeners = function(clearIdList) {
	for (var i = 0; i < clearIdList.length; i++) {
		Ext.getCmp(clearIdList[i]).clearListeners();
	}
};

/**
 * .
 * <p>
 * 数组中是否有空值<br/>
 * <p>
 * 
 * @param array
 *            数组
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.isHaveEmpty = function(array) {
	var boolen = false;
	for (var i = 0; i < array.length; i++) {
		if (Ext.isEmpty(array[i])) {
			boolen = true;
			return boolen;
		}
	}
	return boolen;
};

/**
 * .
 * <p>
 * JS日期的format方法<br/>
 * <p>
 * 
 * @param format
 *            日期格式
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
Date.prototype.format = function(format) {
	if (Ext.isEmpty(this) || this.getTime() == 0
			|| this.toString().indexOf('GMT') == -1) {
		return null;
	}
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
		// millisecond
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4
						- RegExp.$1.length));
	};

	for (var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1
							? o[k]
							: ("00" + o[k]).substr(("" + o[k]).length));
		}
	};
	return format;
};

/**
 * .
 * <p>
 * 根据传的参数生成查询条件<br/>
 * <p>
 * 
 * @param modelList
 *            要转换的Modellist
 * @returns dataList
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.changeModelListToDataList = function(modelList) {
	var dataList = new Array();
	for (var i = 0; i < modelList.length; i++) {
		dataList.push(modelList[i].data);
	}
	return dataList;
};

/**
 * .
 * <p>
 * 数据的将全局变量复制出来<br/>
 * <p>
 * 
 * @param modelList
 *            要转换的Modellist
 * @returns dataList
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.copyModelListToDataList = function(modelList) {
	var dataList = new Array();
	for (var i = 0; i < modelList.length; i++) {
		dataList.push(modelList[i]);
	}
	return dataList;
};

/**
 * .
 * <p>
 * 为js中的STRING加上trim方法<br/>
 * <p>
 * 
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
String.prototype.trim = function() {
	// 用正则表达式将前后空格
	// 用空字符串替代。
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

/**
 * .
 * <p>
 * 为数组添加数组元素<br/>
 * <p>
 * 
 * @param list
 *            数组
 * @param all
 *            数组
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.addAll = function(list, all) {
	var newlist = new Array();
	newlist.push(all);
	for (var i = 0; i < list.length; i++) {
		newlist.push(list[i]);
	}
	return newlist;
};

/**
 * .
 * <p>
 * 根据code获取name<br/>
 * <p>
 * 
 * @param list
 *            集合
 * @param code
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.changeCodeToName = function(list, code) {
	var name = '';
	for (var i = 0; i < list.length; i++) {
		if (list[i].valueCode == code) {
			name = list[i].valueName;
		}
	}
	return name;
};
/**
 * .
 * <p>
 * store中根据code获取name<br/>
 * <p>
 * 
 * @param store
 * @param code
 * @author 高佳
 * @date 2015年5月13日
 * @update
 */
oms.changeCodeToNameStore = function(store, code) {
	var name = '';
	if (!Ext.isEmpty(store)) {
		store.each(function(record) {
					if (record.get('valueCode') == code) {
						name = record.get('valueName');
					}
				});
	}
	return name;
};

/**
 * 获取浏览器高度
 */
oms.getBrowserHeight = function() {
	var browserHeight = document.documentElement.clientHeight;
	return browserHeight;
};
oms.getBrowserWidth = function() {
	var browserWidth = document.documentElement.clientWidth;

	return browserWidth;
};
/**
 * 打印日志
 */
oms.log = function(message) {
	if (console) {
		console.log(message);
	}
}

var loadWin;
oms.loadMaskshow = function(text) {
	if (Ext.isEmpty(text)) {
		text = "Loading...";
	}
	if (Ext.isEmpty(loadWin)) {
		loadWin = Ext.create('Ext.window.Window', {
					height : 120,
					width : 200,
					// hideShadowOnDeactivate : true,
					plain : true,
					// bodyStyle: 'background:#ffc; padding:10px;',
					closable : false,
					modal : true,
					// frame:true,
					loadMarsk : null
				});
		var myMask = new Ext.LoadMask({
					msg : text,
					target : loadWin
				});
		// myMask.show();
		loadWin.loadMarsk = myMask;

	} else {

		loadWin.loadMarsk.msg = text;
	}

	loadWin.show();
	loadWin.loadMarsk.show();
};

oms.loadMaskClear = function() {
	loadWin.loadMarsk.hide();
	loadWin.hide();
};
// 随机数
oms.generateMixed = function(n) {
	var chars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
			'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
	var res = "";
	for (var i = 0; i < n; i++) {
		var id = Math.ceil(Math.random() * 35);
		res += chars[id];
	}
	return res;
}
oms.shiOrFouToYN = function(value) {
	if (value == 'Y')
		return '是';
	if (value == 'N')
		return '否';
	return '';
}
/**
 * 可控页数控件------begin
 * 用法：plugins: new Ext.ui.plugins.ComboPageSize([{pageSizes:[10, 20, 50, 100]}])
 * 默认分页为：{pageSizes:[10, 20, 50, 100]}
 */
Ext.namespace('Ext.ui.plugins');
Ext.ui.plugins.ComboPageSize = function(config) {
	Ext.apply(this, config);
};
Ext.extend(Ext.ui.plugins.ComboPageSize, Ext.util.Observable, {

	pageSizes : [10, 20, 50, 100],
	prefixText : '每页显示',
	postfixText : '条',
	addToItem : true, // true添加到items中去，配合index；false则直接添加到最后
	index : 10, // 在items中的位置
	init : function(pagingToolbar) {
		var ps = this.pageSizes;
		var combo = new Ext.form.ComboBox({
					typeAhead : true,
					triggerAction : 'all',
					lazyRender : true,
					mode : 'local',
					width : 80,
					store : ps,
					value : 20, //初始化默认值
					enableKeyEvents : true,
					editable : false,
					listeners : {
						select : function(c, r, i) {
							pagingToolbar.store.pageSize = r.data.field1;
							pagingToolbar.moveFirst();
						}
					}
				});
		if (this.addToItem) {
			var inputIndex = this.index;
			if (inputIndex > pagingToolbar.items.length)
				inputIndex = pagingToolbar.items.length;
			pagingToolbar.insert(++inputIndex, '-');
			pagingToolbar.insert(++inputIndex, this.prefixText);
			pagingToolbar.insert(++inputIndex, combo);
			pagingToolbar.insert(++inputIndex, this.postfixText);
		} else {
			pagingToolbar.add('-');
			pagingToolbar.add(this.prefixText);
			pagingToolbar.add(combo);
			pagingToolbar.add(this.postfixText);
		}
		pagingToolbar.on({
					beforedestroy : function() {
						combo.destroy();
					}
				});
	}
});
/**
 * 可控页数控件------end
 */