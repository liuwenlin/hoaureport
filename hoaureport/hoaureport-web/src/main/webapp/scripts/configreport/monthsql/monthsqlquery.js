Ext.application({
    name: "report",
    appFolder: '../scripts/configreport/monthsql',
    controllers: ["monthsql"],
    autoCreateViewport: true,// 自动加载 view 
    launch: function () {
    	console.log("monthSqlQuery launch 开始执行");
    }
});
