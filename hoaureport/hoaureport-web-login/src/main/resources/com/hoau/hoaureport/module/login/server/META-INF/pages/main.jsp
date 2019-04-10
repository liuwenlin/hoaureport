<%@page import="com.hoau.hbdp.framework.server.Definitions"%>
<%@page import="com.hoau.hbdp.framework.server.context.SessionContext"%>
<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/ext" prefix="ext" %>
<%@page import="org.jasig.cas.client.authentication.AttributePrincipal"%>
<%@page import="org.apache.struts2.ServletActionContext"%>

<%@page import="com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil"%>
<%@page import="com.hoau.hbdp.framework.server.context.UserContext"%>


<%
	String AUTH_LIB_URL = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("auth.lib.url");
	String AUTH_JS_URL = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("auth.js.url");
	String ssoLogoutService =ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("sso.logout.service");
	String ssoLogoutUrl =ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("sso.logout.url");
	
	
	// 网站跟踪
    String trackerDomain = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("tracker.domain");
    String trackerRemote = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("tracker.remote");
    String trackerSiteId = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("tracker.siteid");
	
	
	// 获取用户名
	AttributePrincipal principal = (AttributePrincipal) request
			.getUserPrincipal();
%>


<!doctype html>
<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
 <title>天宇-报表</title>
 <link rel="shortcut icon" href="../favicon.ico" type="image/x-icon">
 <link rel="icon" href="../favicon.ico" type="image/x-icon">
<%@include file="common.jsp"%>
<%-- <%@include file="../common/commonpiwik.jsp"%> --%>
<ext:module groups="main" subModule="main"/>
<link href="${styles}/left_tree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${scripts}/main.js"></script>
<script type="text/javascript" src="${scripts}/view/MainView.js"></script>

<script type="text/javascript">
	var AUTH_LIB_URL = '<%= AUTH_LIB_URL%>';
	var AUTH_JS_URL = '<%= AUTH_JS_URL%>';
	var CAS_EPRINCIPAL ='<%=principal !=null?true:false%>';
</script>



<%
	if(principal != null){
%>
		<%--切换插件所需js及css--%>
		<link href="${scripts}/plugin/moduleswitcher/css/auth-widget-moduleswitcher-main.css" rel="stylesheet" type="text/css" />
		<script src="${scripts}/plugin/moduleswitcher/js/auth-widget-moduleswitcher-config.js" type="text/javascript" ></script>
		<script src="${scripts}/plugin/lib/require.js" data-main="${scripts}/plugin/moduleswitcher/js/auth-widget-moduleswitcher-plugin"></script>

		<%-- 注意设置当前登录用户 --%>
		<script>
		    AuthWidgetModuleSwitcherConfig.currentUser = "<%=(String)SessionContext.getSession().getObject(Definitions.KEY_USER)%>";
		    AuthWidgetModuleSwitcherConfig.ssoLogoutUrl = "<%=ssoLogoutService + ssoLogoutUrl%>";
		    
		    </script>
		<%--切换插件所需js及css--%>
<%
	}
%>

<%--Piwik--%>
    <script type="text/javascript" src="${scripts}/plugin/tracker/js/tracker-config.js"></script>
    <script>
        /**
         * 此方式仅用于login.jsp 及 main.jsp 主页面
         */

        TRACKER_CONFIG.domain = '<%=trackerDomain%>';
        TRACKER_CONFIG.remote = '<%=trackerRemote%>';
        TRACKER_CONFIG.siteId = '<%=trackerSiteId%>';
        <%--TRACKER_CONFIG.visitor = '<%= (request.getRemoteUser() == null ? "anomynous" : request.getRemoteUser())%>';--%>
        TRACKER_CONFIG.visitor = UserContext.getCurrentUser().userName + "-" + UserContext.getCurrentUser().empName;
//      TRACKER_CONFIG.visitor = UserContext.getCurrentUser().userName + "-" + UserContext.getCurrentUserDept().code;

     </script>
    <script type="text/javascript" src="${scripts}/plugin/tracker/js/tracker-plugin.js"></script>
    <%--End Piwik--%>


</head>
<script type="text/javascript">
function logout(){
	
	Ext.Msg.confirm('提示', '确定退出？', function(btn) {
		if(btn == 'yes') {
			var successFun = function(json) {
				if(CAS_EPRINCIPAL=='false'){
					window.location = '../login/index.action';
				}else{
					//sso登出！
					window.location = AuthWidgetModuleSwitcherConfig.ssoLogoutUrl;
				}

			};
			var failureFun = function(json) {
				if (Ext.isEmpty(json)) {
					//Butterfly.showErrorMes('连接超时'); // 请求超时
					document.getElementById("msg").style.display = "block";
					document.getElementById("error").innerText = "连接超时!";
				} else {
					var message = json.message;
					//Butterfly.showErrorMes(message); // 提示失败原因
					document.getElementById("msg").style.display = "block";
					document.getElementById("error").innerText = message+"!";
				}
			};
			oms.requestJsonAjax('logoutAction!logout.action', null, successFun,
					failureFun);
		}
	});
	
		
	
}
</script>
<body>

	<%
	if(principal != null){
%>
	<%--切换插件显示面板--%>
	<div id="auth-moduleswitcher" class="auth_moduleswitcher_dropdown_container">
	    <span class="left_arrow"></span>
	</div>
	<%--切换插件显示面板--%>
<%
	}
%>
	<div id="logoImageDiv" style="width: 200px;"><img  src="${images}/biao.png"></div>
	<div id="loginoutDiv" onclick="logout();"><img  src="${images}/quit.jpg"></div>
</body>
</html>
