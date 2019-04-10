<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">


<link rel="stylesheet" href="${styles}/graph/cost.css">
<%
	String costid = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("timeliness.cost.id");
	String costname = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("timeliness.cost.name");

%>
<%@include file="common.jsp"%>
<script type="text/javascript" src="${scripts}/baseDataManage/baseUtils.js"></script>

<style type="text/css">
.x-form-text-default.x-form-textarea {
	display: inline-block;
}
</style>
<title>成本</title>
<script type="text/javascript">
	var TIMELINESS_COST_ID='<%=costid%>';
	var TIMELINESS_COST_NAME='<%=costname%>';
	
</script>
</head>
<body>
<div id="main"></div>
        <script type="text/javascript" src="${scripts}/graph/jquery-1.11.0.js"></script>
        <script type="text/javascript" src="${scripts}/graph/echarts.min.js"></script>
        <script type="text/javascript" src="${scripts}/graph/dateformatutil.js"></script>
        <script type="text/javascript" src="${scripts}/graph/cost.js"></script>
</body>
</html>