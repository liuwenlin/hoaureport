<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hoau.hoaureport.module.configreport.shared.domain.SmsDataEntity"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">


<link rel="stylesheet" href="/hoaureport-web/styles/configreport/map/map.css"> </head>

<%@include file="common.jsp"%>
<script type="text/javascript" src="${scripts}/baseDataManage/baseUtils.js"></script>
<title>首页</title>

</head>
<body class="body" onload="pageOnload()">
    <div id="div_left"></div>
    <div id="div_right">
        <div class="head">
            <div class="left"> 本月时间进度：<s:property value='smsdata.bysjjd' />% </div>
            <div class="right"> 本年时间进度：<s:property value='smsdata.bnsjjd' />% </div>
        </div>
        <div class="container">
            <div id="content_one"></div>
            <div id="content_two"></div>
        </div>
        <div class="select">
            <label>当前部门：</label>
            <select name="" id="se">
                <option value="全国">全国</option>
                <option value="华北事业部">华北事业部</option>
                <option value="华南事业部">华南事业部</option>
                <option value="华东事业部">华东事业部</option>
                <option value="上海事业部">上海事业部</option>
                <option value="西北事业部">西北事业部</option>
                <option value="中南事业部">中南事业部</option>
            </select>
        </div>
        <div id="content_three"></div>
    </div>
    <script type="text/javascript" src="/hoaureport-web/scripts/configreport/graph/jquery-1.11.0.js"></script>
    <script src="/hoaureport-web/scripts/configreport/map/dist/echarts.js"></script>
    <script src="/hoaureport-web/scripts/configreport/map/echarts-all.js"></script>
     <script type="text/javascript" src="${scripts}/graph/dateformatutil.js"></script>
    <script type="text/javascript" src="/hoaureport-web/scripts/configreport/map/map.js"></script>
</body>
</html>