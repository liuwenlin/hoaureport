$(function () {
    var items = [

        {
            data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            , name: '完成值'
            , color: '#27C1C7'
            }
        , {
            data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            , name: '目标值'
            , color: '#B6A2DE'
            }

        ];
    var xAxisData = ['运营本部', '平台', '门店', '特许', '华北事业部', '华东事业部', '华南事业部', '上海事业部', '西北事业部', '中南事业部'];
    var myChart = echarts.init(document.getElementById('nowDay'));
    var option = {
        title: {
            text: '送货及时率（本月）'
            , textStyle: {
                fontSize: 14
                , color: '#47ABDB'
            }
            , padding: [30, 40]
        }
        , tooltip: {
            trigger: 'axis'
            , axisPointer: {
                type: 'shadowd'
            }
            , formatter: function (params, ticket, callback) {
                var result = params[0].name + '<br/>';
                var index = ticket.substr(-1);
                for (var i = 0; i < 2; i++) {
                	 var option =	myChart.getOption();
                    result += option.series[i].name + '：' + option.series[i].data[index] + '%<br/>';
                }
                return result;
            }
        }
        , legend: {
            data: [
                    items[0].name, items[1].name
                ]
            , padding: [30, 40]
        }
        , grid: {
            y: 70
            , y2: 55
            , x2: 20
        }
        , calculable: true
        , xAxis: [
            {
                type: 'category'
                , position: 'bottom'
                , axisTick: {
                    show: true
                    , lineStyle: {
                        color: '#DCDCDC'
                        , width: 1
                    }
                }
                , axisLine: {
                    show: true
                    , lineStyle: {
                        color: '#0982C9'
                        , width: 2
                        , type: 'solid'
                    }
                }
                , axisLabel: {
                    textStyle: {
                        color: '#4C4C4C'
                        , fontSize: 12
                    , }
                    , interval: 0
                    , rotate: 45
                , }
                , data: xAxisData
                }
                , {
                type: 'category'
                , axisLine: {
                    show: false
                }
                , axisTick: {
                    show: false
                }
                , axisLabel: {
                    show: false
                }
                , splitArea: {
                    show: false
                }
                , splitLine: {
                    show: false
                }
                , data: xAxisData
                }
            ]
        , yAxis: [
            {
                type: 'value'
                , splitArea: {
                    show: true
                    , areaStyle: {
                        color: [
                                'rgba(200,200,200,0.3)'
                                , 'rgba(250,250,250,0.3)'
                            ]
                    }
                }
                , axisTick: {
                    show: false
                }
                , axisLine: {
                    show: true
                    , lineStyle: {
                        color: '#0982C9'
                        , width: 2
                        , type: 'solid'
                    }
                }
                , axisLabel: {
                    formatter: '{value}%'
                    , fontSize: 12
                    , textStyle: {
                        color: '#4C4C4C'
                    , }
                }
                }
            ]
        , series: []
    };
    for (var i = 0, itemLen = items.length; i < itemLen; i++) {
        var series = {
            name: items[i].name
            , barWidth: 16
            , type: 'bar'
            , barGap: '0'
            , itemStyle: {
                normal: {
                    color: items[i].color
                , }
            }
            , data: items[i].data
        };
        option.series.push(series);
    }
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    var nextDay = echarts.init(document.getElementById('nextDay'));
    var nextDayOption = {
        title: {
            text: '次日送达率（本月）'
            , textStyle: {
                fontSize: 14
                , color: '#47ABDB'
            }
            , padding: [30, 40]
        }
        , tooltip: {
            trigger: 'axis'
            , axisPointer: {
                type: 'shadowd'
            }
            , formatter: function (params, ticket, callback) {
                var result = params[0].name + '<br/>';
                var index = ticket.substr(-1);
                for (var i = 0; i < 2; i++) {
                	 var option =	nextDay.getOption();
                     result += option.series[i].name + '：' + option.series[i].data[index] + '%<br/>';
                }
                return result;
            }
        }
        , legend: {
            data: [
                    items[0].name, items[1].name
                ]
            , padding: [30, 40]
        }
        , grid: {
            y: 70
            , y2: 55
            , x2: 20
        }
        , calculable: true
        , xAxis: [
            {
                type: 'category'
                , position: 'bottom'
                , axisTick: {
                    show: true
                    , lineStyle: {
                        color: '#DCDCDC'
                        , width: 1
                    }
                }
                , axisLine: {
                    show: true
                    , lineStyle: {
                        color: '#0982C9'
                        , width: 2
                        , type: 'solid'
                    }
                }
                , axisLabel: {
                    textStyle: {
                        color: '#4C4C4C'
                        , fontSize: 12
                    , }
                    , interval: 0
                    , rotate: 45
                }
                , axisLine: {
                    show: true
                    , lineStyle: {
                        color: '#0982C9'
                        , width: 2
                        , type: 'solid'
                    }
                }
                , data: xAxisData
                }
                , {
                type: 'category'
                , axisLine: {
                    show: false
                }
                , axisTick: {
                    show: false
                }
                , axisLabel: {
                    show: false
                }
                , splitArea: {
                    show: false
                }
                , splitLine: {
                    show: false
                }
                , data: xAxisData
                }
            ]
        , yAxis: [
            {
                type: 'value'
                , splitArea: {
                    show: true
                }
                , axisLabel: {
                    formatter: '{value}%'
                    , textStyle: {
                        color: '#4C4C4C'
                    }
                }
                , axisLine: {
                    show: true
                    , lineStyle: {
                        color: '#0982C9'
                        , width: 2
                        , type: 'solid'
                    }
                }
                }
            ]
        , series: [
            {
                name: items[0].name
                , type: 'bar'
                , barGap: '0'
                , barWidth: 16
                , itemStyle: {
                    normal: {
                        color: items[0].color
                    }
                }
                , data: items[0].data
                }
                , {
                name: items[1].name
                , barWidth: 16
                , type: 'bar'
                , barGap: '0'
                , itemStyle: {
                    normal: {
                        color: items[1].color
                    }
                }
                , data: items[1].data
                }
            ]
    };
    // 使用刚指定的配置项和数据显示图表。
    nextDay.setOption(nextDayOption);
    //送货及时率双击事件
    $("#nowDay div").bind("dblclick", function () {
    	//Ext.MessageBox.alert('提示', 'nowDay'); 
    	var sqlid=TIMELINESS_NOWDAY_ID;
    	window.parent.delTabpanel(sqlid);
    	var tname=TIMELINESS_NOWDAY_NAME;
    	var url = '/hoaureport-web/configreport/showreportpage.action?SqlId=' + sqlid;
    	window.parent.initTabpanel(sqlid, tname, url, true);
    });
    //次日送达率双击事件
    $("#nextDay div").bind("dblclick", function () {
    	var sqlid=TIMELINESS_NEXTDAY_ID;
    	window.parent.delTabpanel(sqlid);
    	var tname=TIMELINESS_NEXTDAY_NAME;
    	var url = '/hoaureport-web/configreport/showreportpage.action?SqlId=' + sqlid;
    	window.parent.initTabpanel(sqlid, tname, url, true);
    });
    
    //送货及时率
    $.ajax( {  
	    url:'nowdaydata.action',// 跳转到 action  
 	    data:{  
 	    	costDay : DateFormat(new Date(),"yyyy-MM-dd")         
 	    },  
	    type:'post',  
	    cache:false,  
	    dataType:'json',  
	    success:function(req) {  
	    	
	        if(req.success){  
	        	
	        	myChart.setOption(
	        			 {  
	        				xAxis:[{
	        					data: req.graphInfoVo.depts
	        				},{
	        					data: req.graphInfoVo.depts
	        				}],
	     		            series: [{
	     		            	data: req.graphInfoVo.outvalues
	     		            },{
	     		                data: req.graphInfoVo.targets
	     		            }]
	        			 }
	        	 );
	        	 
	        }else{
	        	Ext.MessageBox.alert('提示', req.message); 
	        } 
	     },  
	     error : function() {  
	    	 
	    	 Ext.MessageBox.alert('提示', "系统异常！"); 
	     }  
	});
    
  //次日送达率
    $.ajax( {  
	    url:'nextdaydata.action',// 跳转到 action  
 	    data:{  
 	    	costDay : DateFormat(new Date(),"yyyy-MM-dd")         
 	    },  
	    type:'post',  
	    cache:false,  
	    dataType:'json',  
	    success:function(req) {  
	    	
	        if(req.success){  
	        	
	        	nextDay.setOption(
	        			 {  
	        				xAxis:[{
	        					data: req.graphInfoVo.depts
	        				},{
	        					data: req.graphInfoVo.depts
	        				}],
	     		            series: [{
	     		            	data: req.graphInfoVo.outvalues
	     		            },{
	     		                data: req.graphInfoVo.targets
	     		            }]
	        			 }
	        	 );
	        	 
	        }else{
	        	Ext.MessageBox.alert('提示', req.message); 
	        } 
	     },  
	     error : function() {  
	    	 
	    	 Ext.MessageBox.alert('提示', "系统异常！"); 
	     }  
	});
    
    
    
    
})