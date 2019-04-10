
function DateFormat(datef,fmt)   
    { //author: meizz   
      var o = {   
        "M+" : datef.getMonth()+1,                 //月份   
        "d+" : datef.getDate(),                    //日   
        "h+" : datef.getHours(),                   //小时   
        "m+" : datef.getMinutes(),                 //分   
        "s+" : datef.getSeconds(),                 //秒   
        "q+" : Math.floor((datef.getMonth()+3)/3), //季度   
        "S"  : datef.getMilliseconds()             //毫秒   
      };   
      if(/(y+)/.test(fmt))   
        fmt=fmt.replace(RegExp.$1, (datef.getFullYear()+"").substr(4 - RegExp.$1.length));   
      for(var k in o)   
        if(new RegExp("("+ k +")").test(fmt))   
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
      return fmt;   
    };