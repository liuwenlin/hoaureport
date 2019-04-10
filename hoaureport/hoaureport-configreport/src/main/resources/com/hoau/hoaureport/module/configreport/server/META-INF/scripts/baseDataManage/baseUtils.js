/**
 * ExtJs基本信息提示框（用于基本信息的提示）
 * @param msg 需要提示的信息
 * @param fn 提示后需要执行的操作
 */
function showInfoMsg(msg,fn){
	if(!Ext.isEmpty(msg)){
		Ext.Msg.show({
			title:'提示',
			msg:msg,
			buttons: Ext.Msg.OK,
			icon: Ext.MessageBox.INFO,
			callback:function(e){
				if(!Ext.isEmpty(fn)){
					if(e=='ok'){
						fn();
					}
				}
			}
		});
	}
};
//替换将oldStr 中的'< '替换为'<'
function replaceAll(oldStr){
	var reg=new RegExp("< ","g"); //创建正则RegExp对象  
	var newStr=oldStr.replace(reg,"<");  
	return newStr;

};

/**
 * ExtJs隐藏等待提示框
 */
function hideWait(){
	Ext.Msg.hide();
}
/**
 * ExtJs确认提示框（用于确认信息提示）
 * @param msg 需要提示的信息
 * @param yesFn 点击是后需要执行的操作
 * @param noFn 点击否后需要执行的操作
 */
function confirm(msg,yesFn,noFn){
	if(!Ext.isEmpty(msg)){
		Ext.Msg.show({
			title:'确定',
			msg:msg,
			buttons: Ext.Msg.YESNO,
			icon: Ext.MessageBox.QUESTION,
			callback:function(e){
				if(!Ext.isEmpty(yesFn)){
					if(e=='yes'){
						yesFn();
					}
				}
				if(!Ext.isEmpty(noFn)){
					if(e=='no'){
						noFn();
					}
				}
			}
		});
	}
};
/**
 * jsonData 参数的Ajax
 * @param url 路径
 * @param params 参数
 * @param successFn 成功回调
 * @param failFn 失败回调
 */
function requestUmpAjaxJsonParams(url,params,returnSuc,returnFail){
	Ext.Ajax.request({
		url:url,
		jsonData:params,
		waitMsg:'保存中,请稍后...',//表单提交等待过程中,出现的等待字符
		success:function(response){
			var result = Ext.decode(response.responseText);				
			returnSuc(result);					
				},
		failure:function(response){
			 var result = Ext.decode(response.responseText);
			 returnFail(result);
			}
	});
};
/**
 * ExtJs添加Button
 * @param panelId 添加Button的面板的ID
 * @param text Button显示的名称
 * @param fun Button点击后调用方法
 */

 function addButton(panelId,text,fun){
	 Ext.getCmp(panelId).add(Ext.create('Ext.Button', {					
	    text: text,
	    width:60,
	    xtype : "button",
	    border : true,
	    handler: fun
	}));
 }
 
 /**
  * ExtJs添加文本输入框
  * @param panelId 添加文本框的面板的ID
  *  * @param fieldName 文本框显示的标签名称
  * @param textid 文本输入框的ID
  */
 function addTextField(panelId,fieldName,textid){
	 Ext.getCmp(panelId).add(
			Ext.create('Ext.form.field.Text', {					
		    id:textid,
		    name:textid,
		    labelWidth : 80,
			border : false,
			labelAlign : 'right',
			width : 180,
		    fieldLabel:fieldName		    
		})
	);
 }
 
 
 /**
  * ExtJs添加文本域输入框
  * @param panelId 添加文本域的面板的ID
  *  * @param fieldName 文本域显示的标签名称
  * @param textid 文本域输入框的ID
  */
 function addTextAreaField(panelId,fieldName,textid){
	 Ext.getCmp(panelId).add(
			Ext.create('Ext.form.field.TextArea', {					
		    id:textid,
		    name:textid,
		    labelWidth : 80,
			border : false,
			labelAlign : 'right',
			width : 600,
			rows:2,
			maxLength:6000,
			height:'30px',
		    fieldLabel:fieldName		    
		})
	);
 }
 
 
 /**
  * ExtJs等待提示框（用于等待信息提示）
  */
 function showWait(){
 	return Ext.Msg.show({
 		title:'请等待',
 	    msg: '正在执行......',
 	    wait:true,
 	    closable: false ,
 	    waitConfig: {interval:150}
    });
 };


 /**
  * ExtJs日期选择输入框
  * @param panelId 添加日期选择输入框的面板的ID
  * @param fieldName 日期选择输入框的标签名称
  * @param timeId 日期选择输入框的ID
  */
  
 function addTimeField(panelId,fieldName,timeId){
	 Ext.getCmp(panelId).add(
	    Ext.create('Ext.form.field.Date',{
//	    	id:timeId,
	    	name:timeId,
	    	labelWidth : 60,
			border : false,
			labelAlign : 'right',
			width : 180,
			value : new Date(),
			format : "Y-m-d",
	    	fieldLabel:fieldName,
	    })
	 );
 }
 
 
 /**
  * ExtJs日期时间选择输入框
  * @param panelId 添加日期选择输入框的面板的ID
  * @param fieldName 日期选择输入框的标签名称
  * @param timeId 日期选择输入框的ID
  */
  
 function addDateTimeField(panelId,fieldName,timeId){
	 Ext.getCmp(panelId).add(
	    Ext.create('baseUx.form.datetime.DateTime',{
	    	name:timeId,
	    	labelWidth : 60,
			border : false,
			labelAlign : 'right',
			width : 230,
			value : new Date(),
			format : 'Y-m-d H:i:s',
	    	fieldLabel:fieldName,
	    })
	 );
 }
 
 
 /**
  * ExtJs日期选择输入框  显示年月值
  * @param panelId 添加日期选择输入框的面板的ID
  * @param fieldName 日期选择输入框的标签名称
  * @param timeId 日期选择输入框的ID
  */
  
 function addMonthValueField(panelId,fieldName,timeId){
	 Ext.getCmp(panelId).add(
	    Ext.create('Ext.form.field.Date',{
	    	name:timeId,
	    	labelWidth : 60,
			border : false,
			labelAlign : 'right',
			width : 180,
			value : new Date(),
			format : "Y-m",
	    	fieldLabel:fieldName,
	    })
	 );
 }
 

 /**
  * ExtJs下拉控件
  * @param panelId 添加下拉控件的面板的ID
  * @param fieldName 下拉控件的标签名称
  * @param comboid 下拉控件的ID
  * @param mydate 日下拉控件的值
  */
 
 function createComboFixed(panelId,fieldName,comboid,mydate){	 
	 Ext.define('comboMode', {
	     extend: 'Ext.data.Model',
	     fields: [
	         {name: 'name', type: 'string'},
	         {name: 'value',  type: 'string'}				       
	     ]
	 });
	 var mystore = 	 Ext.create('Ext.data.Store', {
	     model: 'comboMode',
	     data :mydate 
	 });
 
	 var mycombo =  Ext.create('Ext.form.ComboBox',{
		 fieldLabel:fieldName,
		 id:comboid,
		 name:comboid,
		 store:mystore,
		 labelWidth : 60,
			border : false,
			labelAlign : 'right',
			width : 180,
		 queryMode: 'local',  
		 triggerAction : 'all',
		 emptyText : '请选择',
		 displayField : 'name',	
		 matchFieldWidth : false,
		 valueField : 'value'							 
	  });
	
	 Ext.getCmp(panelId).add(mycombo); 
    	
 }
 
 /**
  * ExtJs级联下拉控件
  * @param panelId 添加下拉控件的面板的ID
  * @param fieldName 下拉控件的标签名称
  * @param comboid 下拉控件的ID
  * @param mydate 日下拉控件的值
  * @param bindIdSql 级联绑定的id和sql
  * @param url 获取级联数据的url
  */
 function createCombo(panelId,fieldName,comboid,mydate,bindIdSql,url){
	 showInfoMsg(mystore);
	 
	 Ext.define('comboMode', {
	     extend: 'Ext.data.Model',
	     fields: [
	         {name: 'name', type: 'string'},
	         {name: 'value',  type: 'string'}				       
	     ]
	 });
	 
	 var mystore = 	 Ext.create('Ext.data.Store', {
	     model: 'comboMode',
	     data :mydate 
	 });
 
 
	 var mycombo =  Ext.create('Ext.form.field.ComboBox',{
		 fieldLabel:fieldName,
		 id:comboid,
		 name:comboid,
		 store:mystore,
		 labelWidth : 60,
			border : false,
			labelAlign : 'right',
			width : 180,
		 queryMode: 'local',  
		 triggerAction : 'all',
		 emptyText : '请选择',
		 displayField : 'name',							
		 valueField : 'value',
		listeners:{
				"select":function(){												
					
				    bindCombo(bindIdSql,this.getValue(),url);
				}
			},
	  });
	
	 Ext.getCmp(panelId).add(mycombo); 
    	
 }
 
 /**
  * ExtJs 动态加载级联菜单数据
  * @param bindIdSql  被动下拉框的id 和或去数据需要的sql
  * @param param  主动选择的下拉框选中的值
  * @param myurl  级联菜单获取数据url 被动关联的下拉框
  */
function bindCombo(bindIdSql,param,myurl){
	var id = Ext.util.Format.substr(bindIdSql,0,new String(bindIdSql).indexOf(';'))
    var sql = Ext.util.Format.substr(bindIdSql,
			new String(bindIdSql).indexOf(';')+1,new String(bindIdSql).length);	
	
	Ext.Ajax.request({
		url:myurl,
		params:{
			sql:sql,
			queryParam:id+":"+param			
			},									
		success:function(response){					
			var result = Ext.decode(response.responseText);
			if(result.resultMessage.returnCode == 1){					  
				Ext.getCmp(id).clearValue();				
				Ext.getCmp(id).getStore().loadData(result.comboList);
				
			}else{					
				showInfoMsg(result.resultMessage.message,function(){});
			}
		}
	});	
	
}
 
 
 /**
  * ExtJs清空面板
  * @param panelId 面板的ID
  */
function clearPanel(panelId){
	Ext.getCmp(panelId).removeAll();
}


/**
 * ExtJs 清空查询面板gridpanel
 * @param grid  gridpanel面板
 */
function nullGrid(grid){
	 Ext.define('comboMode', {
	     extend: 'Ext.data.Model',
	     fields: []
	 });	 
	 var mystore = 	 Ext.create('Ext.data.Store', {
	     model: 'comboMode',
	     data :[]	 });
	 grid.reconfigure(mystore,[]);	
}
	


/**
 * ExtJs 查询提交表单
 * @param listId 参数集合
 */

function  submit(listId){

  var str;
  var strValue;
	 for(var i = 0 ;i<listId.length;i++){
		str =  Ext.getCmp(listId[i]).getValue()+",";		
		strValue += str;
	 }
	 return strValue
 }
 

/**
 * ExtJs 根据结果集动态添加结果集
 * @param grid  gridpanel面板
 * @param mydate 查询的结果
 * @param myfield 结果和列绑定关系模型
 * @param mycolumn 列名
 */
 function createGird(grid,mydate,myfield,mycolumn){
	 Ext.define('comboMode', {
	     extend: 'Ext.data.Model',
	     fields: myfield
	 });
	 
	 var mystore = 	 Ext.create('Ext.data.Store', {
	     model: 'comboMode',
	     data :mydate 
	 });
	 grid.reconfigure(mystore,mycolumn);

	
}




/**
 * ExtJs 获取查询空间中的值
 * @param param  查询需要的参数JSON
 */

function  getAllParam(param){
	if(param==null||param==""){
		return "";
	}
    var json = Ext.decode(param);                       
	  var str="";
	  var strValue="";
	  var formTop = Ext.getCmp('queryformTop').getForm();
		 for(var i = 0 ;i<json.length;i++){	
			 if(json[i].type=="D"){
				 str = json[i].id+":"+ Ext.Date.format( formTop.findField(json[i].id).getValue(), 'Y-m-d');					
			 }else if(json[i].type=="DT"){
				 str = json[i].id+":"+ Ext.Date.format( formTop.findField(json[i].id).getValue(), 'Y-m-d H:i:s');					
			 }else if(json[i].type=="DM"){
				 str = json[i].id+":"+ Ext.Date.format( formTop.findField(json[i].id).getValue(), 'Y-m');					
			 }else if(json[i].type=="A"){
				 var p = formTop.findField(json[i].id).getValue();
				 p="REPORT_TYPE_A"+p.replace(/,|，/g,".");
				 //去掉回车换行
				 p=p.replace(/[\r\n]/g,"");
				 str = json[i].id+":" + p;
			 }else{
				 str = json[i].id+":" + formTop.findField(json[i].id).getValue();
				 
			 }
			if(i==0){
				strValue += str;
			}else{
				strValue += ","+str;
			}		
			
		 }				
	 return strValue
 }


/**
 * ExtJs 检查控件是否有空的值
 * @param param  查询需要的参数JSON
 */
function  checkAllParam(param){
	if(param==null||param==""){
		return true;
	}
    var json = Ext.decode(param);                       
	  var str="";
	  var strValue="";
	  var formTop = Ext.getCmp('queryformTop').getForm();
		 for(var i = 0 ;i<json.length;i++){	
			// alert(Ext.getCmp('queryformTop').getForm().findField(json[i].id).getValue());
			if(formTop.findField(json[i].id).getValue()==null||formTop.findField(json[i].id).getValue()==""){
				return false;
			}						
		 }				
	 return true;
 }
 