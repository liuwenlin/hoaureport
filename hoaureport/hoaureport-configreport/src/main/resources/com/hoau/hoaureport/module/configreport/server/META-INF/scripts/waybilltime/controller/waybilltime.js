/**
 * 询价订单管理Controller
 */
Ext.define('report.controller.waybilltime', {
	extend : 'Ext.app.Controller',
	stores : ['waybilltime'],
	models : ['waybilltime'],
	views : ['Viewport', 'waybilltime.List', 'waybilltime.Search'],
	init : function() {
		this.control({
			'waybilltimeSearch button[action=select]' : { // 查询
				click : this.selectGridStore
			},
			'waybilltimeSearch button[action=reset]' : { // 新增
				click : this.resetSearchForm
			},
			'waybilltimeList button[action=csvdown]' : { // 修改
				click : this.csvdown
			}
		});
	},
	resetSearchForm : function(btn) {
		// 重置表单
		btn.up('form').getForm().reset();
	},
	selectGridStore : function(btn) {
		// 查询
		Ext.getCmp('waybilltimeViewId').getwaybillzipqueryGrid().getPagingToolbar().moveFirst();
	},
	csvdown : function() {
		//csv下载
		var selectArr = Ext.getCmp('waybilltimeViewId').getwaybillzipqueryGrid().getSelectionModel().getSelection();
		if (selectArr.length == 0) {
			 Ext.MessageBox.alert('提示', '请选择一条记录');
			 return;
		} else if (selectArr.length > 1) {
			 Ext.MessageBox.alert('提示', '只能选择一条记录进行下载');
			 return;
		}

		Ext.Ajax.request({
			url:"verificationquery!verificationExport.action",									
			success:function(response){					
				var result = Ext.decode(response.responseText);
				if(result.success== true){
					if (!Ext.fly('downloadAttachzipFileForm')) {
						var frm = document.createElement('form');
						frm.id = 'downloadAttachzipFileForm';
						frm.style.display = 'none';
						frm.enctype = "multipart";
						document.body.appendChild(frm);
					}
					Ext.Ajax.request({
						url : 'waybillzipdownload.action',
						form : Ext.fly('downloadAttachzipFileForm'),
						params : {
							id : selectArr[0].get("id")
						},
						isUpload : true
					});
				}else if(result.success== false){
					showInfoMsg(result.message,function(){});
				}else{
					showInfoMsg("系统异常",function(){});
				}
			}
		});	
		
	}

});
