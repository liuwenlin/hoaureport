/**
 * Created by Richard on 16/1/20.
 */
var AuthWidgetModuleSwitcherConfig = {
        // 接入环境 生产环境给 PROD
        env: "QA",
        // html div 显示菜单父容器元素ID
        renderToId : "auth-moduleswitcher",
        // 是否已接入统一权限系统
        authEnabled : true,
        // 统一权限系统模块ID，未接入的需提供异于当前已接入的所有moduleId的唯一ID
        moduleId : "HAR",
        currentUser : "",
};