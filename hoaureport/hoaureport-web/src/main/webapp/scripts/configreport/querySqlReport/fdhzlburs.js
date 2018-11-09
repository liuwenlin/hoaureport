Ext.require([
    'Ext.tab.*',
    'Ext.window.*',
    'Ext.tip.*',
    'Ext.layout.container.Border'
]);
Ext.define('fdhzlShowWin', {
	extend: 'Ext.window.Window',
    title: "发到货总量图表" ,	
	 width: 1000 ,
	 height: 500 ,
	 html:' <div id="main" style="width:100%;height:500px;border:1px solid green"></div>'	
});

function load_Echarts(){
    var script = document.createElement('script');
    script.async = true;
    script.src = '../scripts/configreport/echarts/esl.js';
    if (script.readyState) {
        script.onreadystatechange = fireLoad;
    }
    else {
        script.onload = fireLoad;
    }
    (document.getElementsByTagName('head')[0] || document.body).appendChild(script);
    function fireLoad() {
    	script.onload = script.onreadystatechange = null;  
//        require.config({
//            paths:{ 
//                echarts: '../scripts/configreport/echarts',
//                'echarts/chart/line':'../scripts/configreport/echarts/chart/line',
//                'echarts/chart/bar':'../scripts/configreport/echarts/chart/bar'
//            }
//        });
        require.config({
            packages: [
                {
                    name: 'echarts',
                    location: '../scripts/configreport/echarts',
                    main: 'echarts'
                },
                {
                    name: 'zrender',
                    location: '../scripts/configreport/zrender',
                    main: 'zrender'
                }
            ]
        });   	
        require(
                [
                    'echarts/echarts',
                    'echarts/chart/bar',
                    'echarts/chart/line',
//                    'echarts/chart/scatter',
//                    'echarts/chart/k',
//                    'echarts/chart/pie',
//                    'echarts/chart/radar',
//                    'echarts/chart/force',
//                    'echarts/chart/chord',
//                    'echarts/chart/map',
//                    'echarts/chart/gauge',
//                    'echarts/chart/funnel',
//                    'echarts/chart/venn',
//                    'echarts/chart/treemap',
//                    'echarts/chart/tree',
//                    'echarts/chart/eventRiver'
                ],
                requireCallback
            );    	
    }    
}

function getShowData(listObj){
	if(listObj!=null){
		xaxisData = listObj[0].category;
		legendData = listObj[0].legend;
		var la_temp = listObj[0].seriesList;
		for(var i=0;i<la_temp.length;i++){
			seriesData.push({name:la_temp[i].name,type:la_temp[i].type,data:la_temp[i].series});
		}

	}
}

function option(xaxisData,legendData,seriesData){
	var opt = {
		animation:false,
        title : {
            text: '发到货转量月统计'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:legendData
        }, 
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
//        dataRange: {
//            calculable : true
//        },
        xAxis : [
                 {
                     type : 'category',
                     data : xaxisData
                 }
             ], 
	     yAxis : [
	              {
	                  type : 'value'
	              }
	          ],
      series : seriesData
	};      
      return opt;
}

var echarts;
var myChart;
legendData = new Array() ;
seriesData = new Array();
xaxisData = new Array();

function requireCallback (ec) {
    echarts = ec;
    var domMain = document.getElementById('main');
    myChart = echarts.init(domMain);
    var opt = option(xaxisData,legendData,seriesData);
    myChart.setOption(opt, true);
}


