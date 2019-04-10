Ext.application({
    name: "report",
    appFolder: '../scripts/configreport/waybilltime',
    controllers: ["waybilltime"],
    autoCreateViewport: true,// 自动加载 view 
    launch: function () {
    	console.log("waybilltime launch 开始执行");
    }
});
