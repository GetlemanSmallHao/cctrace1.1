<%@ page language="java" pageEncoding="UTF-8"%>  
<%    
String path = request.getContextPath();    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%>   
<% pageContext.setAttribute("PATH", request.getContextPath()); %>
<!DOCTYPE html>  
<html>  
<head>  
<meta charset="utf-8">  
<title>生成图表</title>  
<script type="text/javascript" src="${PATH}/js/echarts.js"></script>  
<script src="${PATH}/js/jquery-3.2.1.js"></script>
<script src="${PATH }/js/laydate.js"></script>
	<script type="text/javascript">
		var start = {
			elem : '#startTime',
			event : 'click',
			format : 'YYYY-MM-DD hh:mm:ss',
			min : '2014-01-01 00:00:00', //最小日期
			max : laydate.now(), //设定最大日期为当前日期
			istime : true,
			istoday : true,
			choose : function(datas) {
				//        console.log(datas);
				//console.log(new Date(datas));//Thu Jul 13 2017 15:03:53 GMT+0800 (中国标准时间)
				//console.log(new Date(datas).getTime());//1499929433000
				//console.log((new Date(datas)).valueOf());//1499929433000
				//        console.log(Date.parse(new Date(datas)));//1499929433000
				var nowDate = Date.parse(new Date(laydate.now()));
				var maxDate = Date.parse(new Date(datas)) + 7 * 24 * 60 * 60
						* 1000;
				end.min = datas; //开始日选好后，重置结束日的最小日期
				end.start = datas; //将结束日的初始值设定为开始日
				if (maxDate < nowDate)//当加7天后的时间 小于 当前时间
					end.max = laydate.now(maxDate);//才重置结束最大日期为maxDate
			}
		};
		var end = {
			elem : '#endTime',
			event : 'click',
			format : 'YYYY-MM-DD hh:mm:ss',
			min : '2014-01-01 00:00:00',
			max : laydate.now(),
			istime : true,
			istoday : true,
			choose : function(datas) {
				start.max = datas; //结束日选好后，重置开始日的最大日期
				var minDate = Date.parse(new Date(datas)) - 7 * 24 * 60 * 60
						* 1000;
				start.min = laydate.now(minDate);//结束日选好，重置开始的最小时间为当前-7天
			}
		};
		laydate(start);
		laydate(end);
</script>
</head>  
<body>  
	<!--上方导航-->
    <header id="header"><!--异步加载--></header>
    <div style="margin-top:10px;">  
      
				开始时间： <input class="laydate-icon" id="startTime" name="startTime"
					value="2017-10-10 00:00:00" style="background-color: #E0FFFF" /> 结束时间： <input
					class="laydate-icon" id="endTime" name="endTime" value="2017-11-01 00:00:00"
					style="background-color: #E0FFFF" />
		
        <span style="font-size:20px;">冷藏箱编号：</span><input class="Wdate" type="text"  id="containerId" required="required"><br/> 
        <!-- <input name="chart" type="radio" value="2">柱状图   -->
        	选择生成的图表<select id = "chooseTemp">
			 <option value="">--请选择--</option>
			 <option value="setTemp">设定温度</option>
			 <option value="enviTemp">环境温度</option>
			 <option value="backWindTemp">回风温度</option>
			</select>	
        <button type="button" onclick="jchart()">查看</button>  
    </div>  
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->  
    <div id="echars" style="width: 700px;height:400px;"></div>  
    <script type="text/javascript">  
        //-------------------图表开始--------------------  
        function jchart(){  
            //获取参数  
            var startTime=$("#startTime").val();  
            var endTime=$("#endTime").val();  
            var containerId = $("#containerId").val();
            var chooseTemp = $("#chooseTemp").val();
           // var chart=$("input[name=chart]:checked").val();  
        	if(startTime==""){
        		layer.msg("开始时间不能为空！");
        		return;
        	}
        	if(endTime==""){
        		layer.msg("结束时间不能为空！");
        		return;
        	}
        	if(containerId!=""){
        		containerId =$.trim(containerId);
        	}
        	if(containerId==""){
        		layer.msg("冷藏箱编号不能为空！");
        		return;
        	}
        	
            //基于准备好的dom，初始化echarts实例  
            var myChart = echarts.init(document.getElementById('echars'));  
            //图表显示加载样式  
            myChart.showLoading();  
            var options = {  
                    title: {
                       text: "时间  — 温度图",  
                   },  
                   tooltip: {  
                       trigger: 'axis'  
                   },  
                   legend: {  
                       data: []  
                   },  
                   toolbox: {
                       show: true,  
                       feature: {  
                           mark: false  
                       }  
                   },  
                   calculable: false,  
                   label:{   
                       normal:{   
                       show: true,   
                       position: 'inside'}   
                    },  
                   xAxis: [  
                       {  
                           /* axisLabel: {rotate: 90,}, */  
                           type: 'category',  
                           data: []  
                       }  
                   ],  
                   yAxis: [  
                       { 
                           type: 'value',  
                           splitArea: { show: true }  
                       }  
                   ],  
                   series: []
            };  
            //通过Ajax获取数据  
            $.ajax({  
                async : false, //同步执行  
                url : "${PATH}/pc/showPicture.do",  
                data: {"containerId" : containerId,
						"startTime"  : startTime,
						"endTime"    : endTime,
						"chooseTemp" : chooseTemp},  
                dataType : "json",  
                success:function(data) {  
                    //将返回的category和series对象赋值给options对象内的category和series  
                    //主体内容
                    options.series = data.series;  
                    //头部动态选择
                    options.legend.data = data.legend;  
                    //因为xAxis是一个数组 这里需要是xAxis[i]的形式,横坐标
                    options.xAxis[0].data = data.category;  
                    myChart.hideLoading();  
                    // 使用刚指定的配置项和数据显示图表
                    myChart.setOption(options);  
                },  
                error : function(errorMsg) {  
                    alert("图表请求数据失败啦!");
                }  
            });  
        }  
    </script> 
   <!--  <script type="text/javascript">
  //实现日期选择联动
    var start = {
        format: 'YYYY-MM-DD hh:mm:ss',
        minDate: '2014-06-16 23:59:59', //设定最小日期为当前日期
        //festival:true,//是否显示农历
        maxDate: $.nowDate({DD:0}), //最大日期
        choosefun: function(elem,val,data){
    		console.log(val);
    		var starDate = val;
            end.minDate = data; //开始日选好后，重置结束日的最小日期
            //endDates();
        }
    };
    var end = {
        format: 'YYYY-MM-DD hh:mm:ss',
        minDate: '2014-06-16 23:59:59', //设定最小日期为当前日期
        //festival:true,
        maxDate: $.nowDate({DD:0}), //最大日期
        choosefun: function(elem,val,data){
    		//console.log(val);
    		var endDate = val;
            start.maxDate = data; //将结束日的初始值设定为开始日的最大日期
        }
    };
    $("#start").jeDate(start);
    $("#end").jeDate(end);
    </script>  -->
</body>  
</html>  