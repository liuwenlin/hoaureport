var TRACKER_CONFIG = {
    on: false, // 是否
    module: "HAR", // 当前系统模块名称
    domain: "*.10.39.251.79/hoaureport-web", // 当前站点
    remote: "//piwiktest.hoau.net/", // 远程服务器地址
    siteId: 5, // 注意！使用 Number 而不是 String
    visitor: "anomynous" // 默认值为 anomynous，通过系统当前 UserContext 获取用户及部门信息
};