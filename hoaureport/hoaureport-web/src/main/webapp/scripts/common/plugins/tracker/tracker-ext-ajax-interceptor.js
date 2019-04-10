/**
 * @auther 魏喆
 * 重要！勿删除
 * 为 ajax 请求增加 beforerequest 监听事件，供前端按钮事件使用
 * 为 form.submit 增加 beforeAction 监听事件，供前端按钮事件使用
 */
Ext.require('Ext.Ajax');
Ext.onReady( function() {
    Ext.Ajax.on("beforerequest", function(conn, options, eOpts){
        if (Ext.isDefined(TRACKER_CONFIG) && TRACKER_CONFIG.on) {
            try {
                console.log("beforerequest");
                var activeTab = window.parent.Ext.getCmp('tabPanel').getActiveTab();
                /**
                 *
                 * 为tab内的按钮记录访问事件到 Piwik
                 * activeTab.title e.g. 上转移发货
                 * activeTab.id e.g. (web_102001)
                 */
                if (Ext.isDefined(options.proxy)) { // 判断是否定义 proxy
                    // 如果是则为 store ajax proxy
                    // 过滤 baseinfo
                	buttonClickPiwik(activeTab.title,activeTab.id,options.proxy.url,PiwikTracker.EVENT.BUTTON_CLICK);
//                    PiwikTracker.record(activeTab.title + '(' + activeTab.id + ')',
//                        options.proxy.url,
//                        PiwikTracker.EVENT.BUTTON_CLICK);
                } else if (Ext.isDefined(options.url) && !Ext.isEmpty(options.url)) {
                    // 如果是则为 butterfly.requestJsonAjax
                    var action = options.url;
                    var index = options.url.indexOf('?');
                    if (index >= 0) {
                        action = options.url.substr(0, index);
                    }
                    buttonClickPiwik(activeTab.title,activeTab.id,action,PiwikTracker.EVENT.BUTTON_CLICK);
//                    PiwikTracker.record(activeTab.title + '(' + activeTab.id + ')',
//                        action,
//                        PiwikTracker.EVENT.BUTTON_CLICK);
                }
            } catch(e) {
                console.log(e);
            }

        }
    });

    // 尝试 拦截 form.submit
    //Ext.override(Ext.form.action.Action, {
    //    processResponse : function(response){
    //
    //        try {
    //            console.log("form.submit");
    //            var activeTab = window.parent.Ext.getCmp('tabPanel').getActiveTab();
    //            /**
    //             *
    //             * 为tab内的按钮记录访问事件到 Piwik
    //             * activeTab.title e.g. 上转移发货
    //             * activeTab.id e.g. (web_102001)
    //             */
    //            if (Ext.isDefined(this.url)) { // 判断是否定义 proxy
    //                // 如果是则为 store ajax proxy
    //                PiwikTracker.record(activeTab.title + '(' + activeTab.id + ')',
    //                    this.url,
    //                    PiwikTracker.EVENT.BUTTON_CLICK);
    //                console.log("Tracking... " + this.url);
    //            }
    //        } catch(e) {
    //            console.log(e);
    //        }
    //
    //        /**
    //         * 以下代码为重载 processResponse 的原始代码，勿动
    //         */
    //        this.response = response;
    //        if (!response.responseText && !response.responseXML) {
    //            return true;
    //        }
    //        return (this.result = this.handleResponse(response));
    //    }
    //});
});


function buttonClickPiwik(tabTitle,tabId, action, name, value){
	
	if( PiwikTracker.EVENT.URL_VERIFICATION != action && PiwikTracker.EVENT.URL_VERIFICATION_EXIST != action ){
		
		//导出excel
		if(PiwikTracker.EVENT.BUTTON_CLICK_EXCEL_URL==action){
			name = tabTitle+'-'+PiwikTracker.EVENT.BUTTON_CLICK_EXCEL;
		}else if(PiwikTracker.EVENT.BUTTON_CLICK_INIT_CSV_URL==action){
		  //生成CSV
			name = tabTitle+'-'+PiwikTracker.EVENT.BUTTON_CLICK_INIT_CSV;
		}else if(PiwikTracker.EVENT.BUTTON_CLICK_CSV_URL==action){
		 //导出csv	
			name = tabTitle+'-'+PiwikTracker.EVENT.BUTTON_CLICK_CSV;
		}
		if(tabId && tabId.indexOf("web_")<0){
			tabId ='报表';
		}
		PiwikTracker.record(tabTitle + '(' + tabId + ')', action, name);
	}
	
}
