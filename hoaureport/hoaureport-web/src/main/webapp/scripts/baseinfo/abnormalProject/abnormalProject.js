oms.data = {};
oms.data.abnormaltype='EXCEPTION_TYPE';//异常类型
oms.data.orderstate='ORDER_STATUS';//订单状态
oms.data.operationstate='OMS_OPERATION_TYPE';//操作状态
/**
 * 异常项目
 */
Ext.application({
    name: "oms",
    appFolder: '../scripts/baseinfo/abnormalProject',
    controllers: ["abnormalProject"],
    autoCreateViewport: true,
    launch: function () {
    }
});