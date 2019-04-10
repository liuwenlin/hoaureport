<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<!-- <meta name="description" content="" /> -->
<!-- <meta name="keywords" content="" /> -->

<%-- <link rel="stylesheet" href="${styles}/graph/cost.css"> --%>
<%
	String nowdayid = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("timeliness.nowday.id");
	String nowdayname = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("timeliness.nowday.name");
	String nextdayid = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("timeliness.nextday.id");
	String nextdayname = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("timeliness.nextday.name");

%>

<%@include file="common.jsp"%>
<script type="text/javascript" src="${scripts}/baseDataManage/baseUtils.js"></script>

<style type="text/css">
        .body {
            margin: 0;
        }
        
        .div {
            float: left;
            margin-top: 0px;
            margin-left: 0px;
            width:50%;
            height:310px;
        }
    </style>
<title>时效</title>
<script type="text/javascript">
	var TIMELINESS_NOWDAY_ID='<%=nowdayid%>';
	var TIMELINESS_NOWDAY_NAME='<%=nowdayname%>';
	var TIMELINESS_NEXTDAY_ID='<%=nextdayid%>';
	var TIMELINESS_NEXTDAY_NAME='<%=nextdayname%>';
</script>

</head>
<body class="body">
	<div class="div" id="nowDay" ></div>
    <div class="div" id="nextDay" ></div>
        <script type="text/javascript" src="${scripts}/graph/jquery-1.11.0.js"></script>
        <script type="text/javascript" src="${scripts}/graph/echarts.min.js"></script>
        <script type="text/javascript" src="${scripts}/graph/dateformatutil.js"></script>
        <script type="text/javascript" src="${scripts}/graph/timeliness.js"></script>
</body>
</html>