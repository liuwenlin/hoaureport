$(function () {
    map();
    circleFirst();
    circleSecond();
    circleThree();
});

var YEARHRATIO=0;//本年完成比
var MONTHYOY =0;//当月同比
var MONTHMOM =0;//当月环比

var map = function () {
    var finished = [];
    var item = [
        {
            name: "全国"
            , selected: false
}

       , {
            name: '华北事业部'
            , selected: true
                                },

        {
            name: '华东事业部'
            , selected: true
                                },

        {
            name: '华南事业部'
            , selected: true
                                }
                                , {
            name: '西北事业部'
            , selected: true
                                },

        {
            name: '中南事业部'
            , selected: true
                                }
                                , {
            name: '上海事业部'
            , selected: true
                                }]
    $("#se").change(function () {
    	queryData($(this).val());
        for (var i = 0; i < item.length; i++) {
            if ($(this).val() == item[i].name) {
                finished.splice(0, 6, item[i]);
                mapfun();
            }
        }
    });
    var mapfun = function () {
        require.config({
            paths: {
                echarts: '/hoaureport-web/scripts/configreport/map/dist'
            }
        });
        $(function () {
            areaMap("div_left")
        });

        function areaMap(divId) {
            require(
   ['echarts', 'echarts/chart/map']
                , function (ec) {
                    //加载中国地图（分区域）           
                    require('echarts/util/mapData/params').params.area = {
                        getGeoJson: function (callback) {
                            $.getJSON('/hoaureport-web/scripts/configreport/map/geoJson/area.geo.json', callback);
                        }
                    };
                    var myChart = ec.init(document.getElementById("div_left"));
                    var se = document.getElementById("se");
                    var ecConfig = require('echarts/config');
                    myChart.on(ecConfig.EVENT.MAP_SELECTED, function (param) {
                        var selected = param.selected;
//                        console.log(selected);
//                        var val = $("#se").find('option:selected').value;
                        var notAll=true;
                        for (var i in selected) {
//                            console.log(i);
                            if (selected[i]) {
                                for (var m = 0; m < se.length; m++) {
                                    if (se[m].value == i) {
                                        se[m].selected = true;
                                    }
                                }
                                notAll=false;
                            }
                            else {
                                for (var m = 0; m < se.length; m++) {
                                    if (se[m].value == i) {
                                        se[m].selected = false;
                                    }
                                }
                            }
                            
                        }

                        if(notAll){
                      	
                      	    for (var m = 0; m < se.length; m++) {
                               if (se[m].value == "全国") {
                                  se[m].selected = true;
                               }
                            }
                      	
                        }
                      
                        queryData($("#se").val());
                        
                        
                    });
                    var option = {
                        title: {
                            text: '华宇产值汇报'
                            , textStyle: {
                                color: '#5DB2EB'
                                , fontSize: 22
                            }
                            , padding: [5, 40]
                        }
                        , tooltip: {
                            trigger: 'item'
                            , formatter: '{b}'
                        }
                        , dataRange: {
                            show: false
                            , min: 0
                            , max: 100
                            , x: 'left'
                            , y: 'bottom'
                            , text: ['高', '低'], // 文本，默认为数值文本
                            calculable: true
                        }
                        , series: [

                            {
                                name: '产值汇报'
                                , type: 'map'
                                , mapType: 'area'
                                , selectedMode: 'single'
                                , itemStyle: {
                                    normal: {
                                        label: {
                                            show: true
                                        }
                                    }
                                    , emphasis: {
                                        label: {
                                            show: true
                                        }
                                    }
                                , }
                                , data: finished
                     }

                 ]
                    };
                    myChart.setOption(option, true);
                });
        }
    }
    mapfun();
};
var circleFirst = function () {
    myChartFirst = echarts.init(document.getElementById('content_one'));
    var dataStyleGo = {
        normal: {
            color: "#27C1C7"
            , label: {
                show: false
            }
            , labelLine: {
                show: false
            }
        }
    };
    var dataStyleFinish = {
        normal: {
            color: "#B6A2DE"
            , label: {
                show: false
            }
            , labelLine: {
                show: false
            }
        }
    };
    var placeHolderStyle = {
        normal: {
            color: 'rgba(0,0,0,0)'
            , label: {
                show: false
            }
            , labelLine: {
                show: false
            }
        }
        , emphasis: {
            color: 'rgba(0,0,0,0)'
        }
    };
    var item = [
        {
            name: "目标值"
            , data: [
                {
                    value: 0
                    , name: '昨日目标值'
                }
                    , {
                    value: 34
                    , name: 'invisible'
                    , itemStyle: placeHolderStyle
                }
            ]
        }
    , {
            name: "完成值"
            , data: [
                {
                    value: 0
                    , name: '昨日完成值'
                }
                    , {
                    value: 71
                    , name: 'invisible'
                    , itemStyle: placeHolderStyle
                }
            ]
}]
    option = {
        title: {
            text: '昨日完成情况'
            , sublink: 'http://e.weibo.com/1341556070/AhQXtjbqh'
            , x: 'center'
            , y: 'center'
            , itemGap: 20
            , textStyle: {
                color: 'rgba(30,144,255,0.8)'
                , fontFamily: '微软雅黑'
                , fontSize: 14
                , fontWeight: 'bolder'
            }
        }
        , tooltip: {
            show: true
            , formatter: function (params, ticket, callback) {
                var result = "";
                var option =myChartFirst.getOption();
                for (var i = 0; i < 2; i++) {
                    result += option.series[i].name + ':' + option.series[i].data[0].value + 'w<br/>';
                }
                return result;
            }
        }
        , legend: {
            orient: 'vertical'
            , x: document.getElementById('content_one').offsetWidth / 1.8
            , y: 25
            , itemGap: 4
            , data: ['昨日目标值', '昨日完成值']
        }
        , series: [
            {
                name: item[0].name
                , type: 'pie'
                , clockWise: false
                , radius: [75, 90]
                , itemStyle: dataStyleGo
                , data: item[0].data
        }
        , {
                name: item[1].name
                , type: 'pie'
                , clockWise: false
                , radius: [60, 75]
                , itemStyle: dataStyleFinish
                , data: item[1].data
        }
    ]
    };
    myChartFirst.setOption(option);
};
var circleSecond = function () {
    myChartSecond = echarts.init(document.getElementById('content_two'));
    var dataStyleGo = {
        normal: {
            color: "#27C1C7"
            , label: {
                show: false
            }
            , labelLine: {
                show: false
            }
        }
    };
    var dataStyleFinish = {
        normal: {
            color: "#B6A2DE"
            , label: {
                show: false
            }
            , labelLine: {
                show: false
            }
        }
    };
    var dataStylePlan = {
        normal: {
            color: "#59B1F0"
            , label: {
                show: false
            }
            , labelLine: {
                show: false
            }
        }
    };
    var dataStyleMonthFinish = {
        normal: {
            color: "#FEB980"
            , label: {
                show: false
            }
            , labelLine: {
                show: false
            }
        }
    };
    var placeHolderStyle = {
        normal: {
            color: 'rgba(0,0,0,0)'
            , label: {
                show: false
            }
            , labelLine: {
                show: false
            }
        }
        , emphasis: {
            color: 'rgba(0,0,0,0)'
        }
    };
    var item = [
        {
            name: "目标值"
            , data: [
                {
                    value: 0
                    , name: '目标值'
                }
                    , {
                    value: 34
                    , name: 'invisible'
                    , itemStyle: placeHolderStyle
                }
            ]
        }
    , {
            name: "完成值"
            , data: [
                {
                    value: 0
                    , name: '完成值'
                }
                    , {
                    value: 71
                    , name: 'invisible'
                    , itemStyle: placeHolderStyle
                }
            ]
}, {
            name: "月完成比"
            , data: [
                {
                    value: 0
                    , name: '月完成比'
                }
                    , {
                    value: 66
                    , name: 'invisible'
                    , itemStyle: placeHolderStyle
                }
            ]
        }
        , {
            name: "预计完成比"
            , data: [
                {
                    value: 0
                    , name: '预计完成比'
                }
                    , {
                    value: 29
                    , name: 'invisible'
                    , itemStyle: placeHolderStyle
                }
            ]
        }, {
            name: "当月同比"
            , data: [
                {
                    value: 0
                    , name: 'invisible'
                    , itemStyle: placeHolderStyle
                }
                    , {
                    value: 0
                    , name: 'invisible'
                    , itemStyle: placeHolderStyle
                }
            ]
        }, {
            name: "当月环比"
            , data: [
                {
                    value: 0
                    , name: 'invisible'
                    , itemStyle: placeHolderStyle
                }
                    , {
                    value: 0
                    , name: 'invisible'
                    , itemStyle: placeHolderStyle
                }
            ]
        }

    ]
    option = {
        title: {
            text: '本月'
            , sublink: 'http://e.weibo.com/1341556070/AhQXtjbqh'
            , x: 'center'
            , y: 'center'
            , itemGap: 20
            , textStyle: {
                color: 'rgba(30,144,255,0.8)'
                , fontFamily: '微软雅黑'
                , fontSize: 14
                , fontWeight: 'bolder'
            }
        }
        , tooltip: {
            show: true
            , formatter: function (params, ticket, callback) {
                var result = "";
                var option =myChartSecond.getOption();
                
                if ($("#se").val() == "全国") {
                    for (var i = 0; i < 6; i++) {
                        if (i < 2) {
                            result += item[i].name + ':' + option.series[i].data[0].value + 'w<br/>'
                        }
                        else if (i == 2) {
                            result += item[i].name + ':' + option.series[i].data[0].value + '%<br/>'
                        }
                        else if (i < 4) {
                            result += item[i].name + ':' + option.series[i].data[0].value + '%<br>'
                        }
                        else if(i==4){
                        	result += item[i].name + ':' + MONTHYOY + '%<br/>'
                        }else {
                            result += item[i].name + ':' + MONTHMOM + '%<br/>'
                        }
                    }
                    return result;
                }
                else {
                    for (var i = 0; i < 4; i++) {
                        if (i < 2) {
                            result += item[i].name + ':' + option.series[i].data[0].value + 'w<br/>'
                        }
                        else if (i == 2) {
                            result += item[i].name + ':' + option.series[i].data[0].value + '%<br/>'
                        }
                        else {
                            result += item[i].name + ':' + option.series[i].data[0].value + '%<br>'
                        }
                    }
                    return result;
                }
            }
        }
        , legend: {
            orient: 'vertical'
            , x: document.getElementById('content_two').offsetWidth / 1.8
            , y: 25
            , itemGap: 4
            , data: [item[0].name, item[1].name, item[2].name, item[3].name]
        }
        , series: [
            {
                name: item[0].name
                , type: 'pie'
                , clockWise: false
                , radius: [75, 90]
                , itemStyle: dataStyleGo
                , data: item[0].data
        }
        , {
                name: item[1].name
                , type: 'pie'
                , clockWise: false
                , radius: [60, 75]
                , itemStyle: dataStyleFinish
                , data: item[1].data
        }
            , {
                name: item[2].name
                , type: 'pie'
                , clockWise: false
                , radius: [45, 60]
                , itemStyle: dataStylePlan
                , data: item[2].data
        }
            , {
                name: item[3].name
                , type: 'pie'
                , clockWise: false
                , radius: [30, 45]
                , itemStyle: dataStyleMonthFinish
                , data: item[3].data
        }
    ]
    };
    myChartSecond.setOption(option);
};
var circleThree = function () {
    myChartThree = echarts.init(document.getElementById('content_three'));
    var dataStyleGo = {
        normal: {
            color: "#27C1C7"
            , label: {
                show: false
            }
            , labelLine: {
                show: false
            }
        }
    };
    var dataStyleFinish = {
        normal: {
            color: "#B6A2DE"
            , label: {
                show: false
            }
            , labelLine: {
                show: false
            }
        }
    };
    var placeHolderStyle = {
        normal: {
            color: 'rgba(0,0,0,0)'
            , label: {
                show: false
            }
            , labelLine: {
                show: false
            }
        }
        , emphasis: {
            color: 'rgba(0,0,0,0)'
        }
    };
    var item = [
        {
            name: "目标值"
            , data: [
                {
                    value: 0
                    , name: '本年目标值'
                }
                    , {
                    value: 34
                    , name: 'invisible'
                    , itemStyle: placeHolderStyle
                }
            ]
        }
    , {
            name: "完成值"
            , data: [
                {
                    value: 0
                    , name: '本年完成值'
                }
                    , {
                    value: 71
                    , name: 'invisible'
                    , itemStyle: placeHolderStyle
                }
            ]
}, {
            name: "完成比"
            , data: [
                {
                    value: 0
                    , name: 'invisible'
                    , itemStyle: placeHolderStyle
                }
                    , {
                    value: 71
                    , name: 'invisible'
                    , itemStyle: placeHolderStyle
                }
            ]
}

    ]
    option = {
        title: {
            text: '本年完成情况'
            , sublink: 'http://e.weibo.com/1341556070/AhQXtjbqh'
            , x: 'center'
            , y: 'center'
            , itemGap: 20
            , textStyle: {
                color: 'rgba(30,144,255,0.8)'
                , fontFamily: '微软雅黑'
                , fontSize: 14
                , fontWeight: 'bolder'
            }
        }
        , tooltip: {
            show: true
            , formatter: function (params, ticket, callback) {
                var result = "";
                var option =myChartThree.getOption();
               
                for (var i = 0; i < 3; i++) {
                    if (i < 2) {
                    	
                       
                        result += option.series[i].name + ':' + option.series[i].data[0].value + 'w<br/>'
                    }
                    else {
                        result += item[i].name + ':' + YEARHRATIO + '%'
                    }
                }
                return result;
            }
        }
        , legend: {
            orient: 'vertical'
            , x: document.getElementById('content_one').offsetWidth / 1.8
            , y: 25
            , itemGap: 4
            , data: ['本年目标值', '本年完成值']
        }
        , series: [
            {
                name: item[0].name
                , type: 'pie'
                , clockWise: false
                , radius: [75, 90]
                , itemStyle: dataStyleGo
                , data: item[0].data
        }
        , {
                name: item[1].name
                , type: 'pie'
                , clockWise: false
                , radius: [60, 75]
                , itemStyle: dataStyleFinish
                , data: item[1].data
        }
    ]
    };
    myChartThree.setOption(option);
};




function queryData(cname){
	$.ajax( {  
	    url:'getsmsdata.action',// 跳转到 action  
 	    data:{  
 	    	'costDay' : DateFormat(new Date(),"yyyy-MM-dd"),
 	    	'smsdata.compname':cname
 	    },  
	    type:'post',  
	    cache:false,  
	    dataType:'json',  
	    success:function(req) {  
	    	
	        if(req.success){  
	        	initFirst(req.smsDataVo);
	        	initSecond(req.smsDataVo);
	        	initThree(req.smsDataVo);
	        }else{
	        	Ext.MessageBox.alert('提示', req.message); 
	        }
	     },  
	     error : function() {  
	    	 Ext.MessageBox.alert('提示', "系统异常！"); 
	     }  
	});
    
}




//当日
function initFirst(data){
	myChartFirst.setOption(
			 {  
				
	            series: [{
	            	data: [{
	            			value:data.dayTarget.displayVal
	            		},{
	            			value:data.dayTarget.hideVal
	            			}]
	            },{
	                data:[{
	                	value:data.dayComplete.displayVal
	                },{
	                	value:data.dayComplete.hideVal
	                }]
	            }]
			 }
	 );
}

//当月
function initSecond(data){
	myChartSecond.setOption(
			 {  
				
	            series: [{
	            	data: [{  //目标值
	            			value:data.monthTarget.displayVal
	            		},{
	            			value:data.monthTarget.hideVal
	            			}]
	            },{
	                data:[{ //完成值
	                	value:data.monthComplete.displayVal
	                },{
	                	value:data.monthComplete.hideVal
	                }]
	            },{
	                data:[{//完成比
	                	value:data.monthRatio.displayVal
	                },{
	                	value:data.monthRatio.hideVal
	                }]
	            },{
	                data:[{//预计完成比
	                	value:data.monthPlanRatio.displayVal
	                },{
	                	value:data.monthPlanRatio.hideVal
	                }]
	            }]
			 }
	 );
	
	MONTHYOY =data.monthYOY.displayVal;
	MONTHMOM =data.monthMOM.displayVal;
}

//本年
function initThree(data){
	myChartThree.setOption(
			 {  
				
	            series: [{
	            	data: [{
	            			value:data.yearTarget.displayVal
	            		},{
	            			value:data.yearTarget.hideVal
	            			}]
	            },{
	                data:[{
	                	value:data.yearComplete.displayVal
	                },{
	                	value:data.yearComplete.hideVal
	                }]
	            }]
			 }
	 );
	YEARHRATIO = data.yearhRatio.displayVal;
}

function pageOnload(){
	queryData($("#se").val());
//	setInterval(function(){
//		queryData($("#se").val());
//	},500);
	
}
