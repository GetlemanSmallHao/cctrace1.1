<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta charset="UTF-8">
<title>温度折线图</title>
<%
	pageContext.setAttribute("PATH", request.getContextPath());
%>
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/cctrace1.0
 -->
<link rel="stylesheet" href="${PATH }/css/laydate.css" />
<link rel="stylesheet" href="${PATH }/css/index.css" />
<style type="text/css">
/* 分页条的样式 */
  	.hxpage{
	    clear: both;
	    color: #999;
	    padding:2px 0;
	    font-size: 13px;
	}
	.hxpage>span{
		float:left;
	}
	.hxpage>.pageText{
		margin-left:40px;
	}
	.hxpage span.disabled {
	    float: left;
	    display: inline;
	    padding: 1px 6px 1px 6px;
	    margin:0 2px;
	    border-radius: 3px;
	    -moz-border-radius: 3px;
	    -webkit-border-radius: 3px;
	    border: 1px solid #DFDFDF;
	    background-color: #FFF;
	    color: #DFDFDF;
	}
	.hxpage span.curr {
	    float: left;
	    border: 1px solid #31ACE2;
	    display: inline;
	    padding: 1px 6px 1px 6px;
	    margin:0 2px;
	    border-radius: 3px;
	    -moz-border-radius: 3px;
	    -webkit-border-radius: 3px;
	    background: #F0FDFF;
	    color: #31ACE2;
	}
	.hxpage a {
	    float: left;
	    border: 1px solid #ccc;
	    padding: 1px 6px 1px 6px;
	    margin:0 2px;
	    border-radius: 3px;
	    -moz-border-radius: 3px;
	    -webkit-border-radius: 3px;
	    cursor: pointer;
	    background: #fff;
	    text-decoration: none;
   		color: #999;
    }
    .hxpage .currPageNum {
    	color: #FD7F4D;
	}
	.hxpage_gopage_wrap {
	    position: relative;
	}
	.hxpager_btn_go_input {
	    width: 36px;
	    color: #999;
	    text-align: center;
	    border: 1px solid #DFDFDF;
	    -moz-border-radius: 3px;
	    -webkit-border-radius: 3px;
	}
	.hxpager_btn_go {
	    width: 44px;
	    margin-left:8px;
	    border: 0px;
	    line-height: 140%;
	    cursor: pointer;
	    background-color: #31ACE2;
	    color: #FFF;
	    -moz-border-radius: 3px;
	    -webkit-border-radius: 3px;
	}
  </style>
<style>
.m__table{
	height:50%;
	padding-bottom:50px; 
	border-bottom:1px dashed #bbb;
	overflow:auto;
}

/* .footer__btn .btn--some {
	border: 1px solid #C6C6C6;
	background-color: #D9D9D9;
} */
/* 导出报表按钮样式 */
.footer__btn>a{
	display:inline-block;
	padding:2px 5px;
	margin:0 2px;
	border: 1px solid #4A424D;
	background-color:#f3f3f3;
}
</style>
</head>
<body>
	<!-- 导航 -->
	<jsp:include page="header.jsp" flush="true"></jsp:include>
	<main class="main"> <!--<div class="con__map bindEvent clearfix">地图</div>-->
	<div class="con__boxTable clearfix">
		<div class="boxTable__wrap">
			<header class="con__header">
			<ul>
				<li class="con__header--active" style="background:none;">冷藏箱</li>
			</ul>
			<!--<a class="con__header&#45;&#45;refresh" href="javascript:;">--> <!--<i></i>-->
			<!--刷新--> <!--</a>--> </header>
			<div class="conMap__track__time">
				开始时间： <input class="laydate-icon" id="startTime" name="startTime"
					value="" style="background-color: #E0FFFF" /> 结束时间： <input
					class="laydate-icon" id="endTime" name="endTime" value=""
					style="background-color: #E0FFFF" />
			</div>

			<main class="con__main">
			<div>
				<div class="con__main--search">冷藏箱</div>
				<div>
					&nbsp;&nbsp;查询冷藏箱<input type="text" id="containerId5" /> <input
						type="submit" value="查询" onclick="search();" />
						<br>
				</div>
				<div class="con__main__table" style="position:relutive; height: -moz-calc(100% - 49px); height: -webkit-calc(100% - 49px); height: calc(100% - 49px);">
					<table id="ccdatas_table">
						<thead>
							<tr>
								<td><div class="">序号</div></td>
								<td><div class="">冷藏箱编号</div></td>
							</tr>
						</thead>
						<tbody id="showContainersInPCSensor">
							 <c:forEach  var="ccdata" items="${ccdatas}" varStatus="status">
				            		<tr>
						                <td><div class="leftDown--div1">${status.index+1}</div></td>
						                <td><div class="leftDown--div2">${ccdata.containerId}</div></td>
						            </tr>
				            	</c:forEach>

						</tbody>
					</table>
				</div>

			</div>
			</main>
		</div>
	</div>
	<div class="con__sensorTable clearfix">
		<div class="sensorTable__wrap">
			<header class="con__header">
			<ul>
				<li class="con__header--active">温度折线图</li>
			</ul>
			<!--<a class="con__header&#45;&#45;refresh" href="javascript:;">--> <!--<i></i>-->
			<!--刷新--> <!--</a>--> </header>
			<main class="con__main amendBind">
			<div>
				<!--<div class="con__main&#45;&#45;search">-->
				<!--设备编号：<input type="text"/>-->
				<!--<button>搜索</button>-->
				<!--</div>-->
				<div class="con__main__table" style="/* padding-bottom: 40px; */ overflow:hidden;">
					<font size="3">选择生成的图表<select id = "chooseTemp">
			 <option value="">--请选择--</option>
			 <option value="setTemp">设定温度</option>
			 <option value="enviTemp">环境温度</option>
			 <option value="backWindTemp">回风温度</option>
			</select>	
        <button type="button" onclick="jchart()">查看</button>  	</font>
          <div id="echars" style="width: 700px;height:400px;"></div>  				


				</div>
				<!-- 下方按钮 -->
			</div>
			</main>
		</div>
	</div>
	</main>


	<script src="${PATH }/js/jquery-3.2.1.js"></script>
	<script src="${PATH }/js/index.js"></script>
	<script src="${PATH }/js/laydate.js"></script>
	<script type="text/javascript">
		var mydateInputStart = document.getElementById("startTime");
		var mydateInputEnd = document.getElementById("endTime");
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
		var now = new Date();
		var selectTime = now.getFullYear()+"-"+((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+now.getDate()+" "+(now.getHours()<10?"0":"")+now.getHours()+":"+(now.getMinutes()<10?"0":"")+now.getMinutes()+":"+(now.getSeconds()<10?"0":"")+now.getSeconds();
		var selectTime1 = now.getFullYear()+"-"+((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+now.getDate()+" "+"00:00:00";
		mydateInputStart.value=selectTime1;
		mydateInputEnd.value=selectTime;
		
		//1、页面加载完成以后，直接去发送ajax请求,要到数据
		$(function() {
			$.ajax({
				url : "${PATH}/pc/bindtable/showSensorContainers.do",
				data : "",
				type : "get",
				success : function(result) {
					//解析显示冷藏箱数据
					build_ccdatas_table(result);
				}
			});
		});

		//解析显示冷藏箱数据
		function build_ccdatas_table(result) {

			var addHtml = $("#ccdatas_table tbody").empty();
			var containers = result.extend.maps;
			var htmlStr = "";
			$.each(containers, function(index, item) {
				htmlStr += "<tr>";
				htmlStr += "<td><div class='leftDown--div1'>" + (index+1)
						+ "</div></td> ";
				htmlStr += "<td><div class='containerId '>"
						+ item.container.containerId + "</div></td>";
				htmlStr += "</tr>";

			});
			addHtml.append(htmlStr);

		}
		var containerId;
		var startTime;
		var endTime;
		
		//回车事件绑定
	    $("#containerId5").on('keyup', function(event) {
	        if (event.keyCode == "13") {
	            //回车执行查询
	          	search();
	        }
	    });
		//模糊查询冷藏箱
		function search() {
		var containerId6 = $("#containerId5").val();
		containerId7 = containerId6.replace(/\s*/g, "");
		$.ajax({
			url : "${PATH}/pc/sensor/selectContainersLikeyInPCSersor.do",
			data : {
				"containerId" : containerId7
			},
			type : "post",
			success : function(result) {
				$("#showContainersInPCSensor").html(result);
			}
		});
		
		
		
	
	}
	</script>
	<script src="${PATH}/js/layer/layer.js"></script>
	<script type="text/javascript" src="${PATH}/js/echarts.js"></script>  
	<script type="text/javascript" src="${PATH}/js/macarons.js"></script>  
	<script type="text/javascript">
	 function jchart(){  
	       //获取参数  
	       var startTime=$("#startTime").val();  
	       var endTime=$("#endTime").val();  
	       //var containerId = $("#containerId").val();
	       var containerId = $("#showContainersInPCSensor .tr__active").eq(0).children("td").eq(1).children("div").text();
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
	   	if(containerId==""){
	   		layer.msg("冷藏箱编号不能为空！");
	   		return;
	   	}
	   	if(chooseTemp==""){
	   		layer.msg("请选择温度！");
	   		return;
	   	}
	   	
	       //基于准备好的dom，初始化echarts实例  
	       var myChart = echarts.init(document.getElementById('echars'),'macarons');  
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


</body>
</html>