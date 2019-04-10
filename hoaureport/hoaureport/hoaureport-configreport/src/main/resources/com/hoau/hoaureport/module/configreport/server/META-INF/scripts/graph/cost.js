$(function () {
    var item = [
        {
            name: "完成值"
            , color: "#27C1C7"
            , data: [0, 0, 0, 0, 0, 0, 0]
        }
        , {
            name: "目标值"
            , color: "#B6A2DE"
            , data: [0, 0, 0, 0, 0, 0, 0]
        }
    ];
    var xAxisData = ['运营本部', '华北事业部', '华东事业部', '华南事业部', '上海事业部', '西北事业部', '中南事业部'];
    var myChart = echarts.init(document.getElementById("main"));
    option = {
        title: {
            text: '吨成本（当月）'
            , textStyle: {
                color: '#47ABDB'
                , fontSize: 14
            }
            , padding: [30, 40]
        }
        , tooltip: {
            trigger: 'axis'
            , axisPointer: {
                type: 'shadows'
            }
            , formatter: function (params, ticket, callback) {
                var result = params[0].name + '<br/>';
                var index = ticket.substr(-1);
                for (var i = 0; i < 2; i++) {
                   var option =	myChart.getOption();
                    //result += item[i].name + ':' + item[i].data[index] + '<br/>'
                    result += option.series[i].name + ':' + option.series[i].data[index] + '<br/>'
                }
                return result;
            }
        }
        , legend: {
            data: [
            item[0].name, item[1].name
        ]
            , padding: [30, 40]
        }
        , grid: {
            y: 70
            , y2: 30
            , x2: 20
        }
        , calculable: true
        , xAxis: [
            {
                type: 'category'
                , axisLabel: {
                    interval: 0
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
             ,{
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
            }]
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
                , axisLine: {
                    show: true
                    , lineStyle: {
                        color: '#0982C9'
                        , width: 2
                        , type: 'solid'
                    }
                }
                , min: 0
                , max: 309
                            }]
        , series: [
            {
                name: item[0].name
                , type: 'bar'
                , barWidth: 16
                , barGap: '0'
                , itemStyle: {
                    normal: {
                        color: item[0].color
                    }
                }
//                ,label: {
//                    normal: {
//                        show: true,
//                        position: 'top'
//                    }
//                }
                , data: item[0].data
            }
            ,{
                name: item[1].name
                , type: 'bar'
                , barWidth: 16
                , barGap: '0'
                , itemStyle: {
                    normal: {
                        color: item[1].color
                    }
                }
//                ,label: {
//                    normal: {
//                        show: true,
//                        position: 'top'
//                    }
//                }
                , data: item[1].data
            }
         ]
    };
    myChart.setOption(option);
    
   //吨成本双击事件
    $("#main div").bind("dblclick", function () {
    	//Ext.MessageBox.alert('提示', 'nowDay'); 
    	var sqlid=TIMELINESS_COST_ID;
    	window.parent.delTabpanel(sqlid);
    	var tname=TIMELINESS_COST_NAME;
    	var url = '/hoaureport-web/configreport/showreportpage.action?SqlId=' + sqlid;
    	window.parent.initTabpanel(sqlid, tname, url, true);
    });
    
    $.ajax( {  
	    url:'costdata.action',// 跳转到 action  
 	    data:{  
 	    	costDay : DateFormat(new Date(),"yyyy-MM-dd")         
 	    },  
	    type:'post',  
	    cache:false,  
	    dataType:'json',  
	    success:function(req) {  
	    	
	        if(req.success){  
	        	var vmax =req.graphInfoVo.cmax + 20;
	        	var vmin =0;
	        	if(req.graphInfoVo.cmin >=50){
	        		vmin =req.graphInfoVo.cmin -30;
	        	}
	        	myChart.setOption(
	        			 {  
	        				yAxis:[{
	        					min: vmin
	        		            ,max: vmax
	        				}],
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