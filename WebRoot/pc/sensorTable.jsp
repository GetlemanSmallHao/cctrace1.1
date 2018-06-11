<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta charset="UTF-8">
<title>传感器报告</title>
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
	height: -moz-calc(100% - 60px);
    height: -webkit-calc(100% - 60px);
	height:calc(100% - 60px);
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
	<div class="con__boxTable clearfix" style="width: 260px;">
		<div class="boxTable__wrap">
			<header class="con__header">
			<ul>
				<li class="con__header--active" style="background:none;">冷藏箱</li>
			</ul>
			<!--<a class="con__header&#45;&#45;refresh" href="javascript:;">--> <!--<i></i>-->
			<!--刷新--> <!--</a>--> </header>
			<div class="conMap__track__time" style="height:80px;">
				<span>开始时间：</span> <input class="laydate-icon" id="startTime" name="startTime"
					style="background-color: #E0FFFF" /> <br/>
				<span>结束时间：</span> <input
					class="laydate-icon" id="endTime" name="endTime" 
					style="background-color: #E0FFFF" />
			</div>

			<main class="con__main" style="height: -moz-calc(100% - 100px); height: -webkit-calc(100% - 100px); height: calc(100% - 100px);">
			<div>
				<div class="con__main--search">冷藏箱</div>
				<div style="height:40px;">
					<span style="padding-left:10px;">查询冷藏箱：</span>
					<input type="text" id="containerId5" /> <input
						type="submit" value="查询" onclick="search();" />
				</div>
				<div class="con__main__table" style="position:relutive; height: -moz-calc(100% - 68px); height: -webkit-calc(100% - 68px); height: calc(100% - 68px);">
					<table id="ccdatas_table" style="font-size: 15px; width:100%">
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
	<div class="con__sensorTable clearfix" style=" width: -moz-calc(100% - 260px); width: -webkit-calc(100% - 260px); width: calc(100% - 260px);">
		<div class="sensorTable__wrap">
			<header class="con__header">
			<!-- <ul>
				<li class="con__header--active">传感器列表</li>
			</ul> -->
			<ul>
				<li class="con__header--active"><a href = "javascript:;" onclick="$('.m__table1').show();$('.m__table2').hide()">传感器列表1</a></li>
			</ul>
			<ul>
				<li class="con__header--active"><a href = "javascript:;" onclick="$('.m__table2').show();$('.m__table1').hide()">传感器列表2</a></li>
			</ul>
			<!--<a class="con__header&#45;&#45;refresh" href="javascript:;">--> <!--<i></i>-->
			<!--刷新--> <!--</a>--> </header>
			<main class="con__main amendBind">
			<div>
				<!--<div class="con__main&#45;&#45;search">-->
				<!--设备编号：<input type="text"/>-->
				<!--<button>搜索</button>-->
				<!--</div>-->
				<div class="con__main__table" style="/* padding-bottom: 40px; */ overflow:hidden; width: 100%; font-size:14px;">
					
					<div class="m__table m__table1">
						<table id="ourccdatasDetail_table" style="width:100%;">
							<thead>
								<tr>
									<!-- 新增 -->
									<td><div class="leftDown--div1">序号</div></td>
									<td><div class="">冷藏箱编号</div></td>
									<td><div class="">接收数据时间</div></td>
									<!-- <td><div class="">油箱温度</div></td> -->
									<td><div class="">箱内湿度</div></td>
									<td><div class="">箱尾温度</div></td>
									<td><div class="">gps电量</div></td>
									<td><div class="">油箱油位</div></td>
									<td><div class="">后门状态</div></td>
									<td><div class="">经度</div></td>
									<td><div class="">纬度</div></td>
									<td><div class="">地理位置</div></td>
								</tr>
							</thead>
							<tbody>
	
							</tbody>
						</table>
						<div class="hxpage2_wrap" style="position:absolute; bottom:10px; height:50px; background-color:#fff;width: -moz-calc(100% - 17px); width: -webkit-calc(100% - 17px); width: calc(100% - 17px);">
						 <!-- style="position:absolute; top:50%; height:50px; margin-top:-67px; background-color:#fff;width: -moz-calc(100% - 17px); width: -webkit-calc(100% - 17px); width: calc(100% - 17px);" -->
							<div class="hxpage hxpage2 clearfix" style="width:800px; margin:0 auto;">
							</div>
						</div>
					</div>
					<div class="m__table m__table2" style="display: none;">
						<table id="ccdatasDetail_table" style="width:100%;">
							<thead>
								<tr>
									<td><div class="leftDown--div1">序号</div></td>
									<td><div class="">冷藏箱编号</div></td>
									<td><div class="">接收数据时间</div></td>
									<td><div class="">冷机电瓶电压</div></td>
									<td><div class="">回风温度</div></td>
									<!--          <td><div class="">冷机运行模式</div></td> -->
									<!--  <td><div class="">后门开关</div></td>
	                  <td><div class="">箱内湿度</div></td>
	                  <td><div class="">油箱油位</div></td>
	                  <td><div class="">GPS电压</div></td> -->
									<td><div class="">冷机状态</div></td>
									<td><div class="">环境温度</div></td>
									<td><div class="">出风温度</div></td>
									<td><div class="">设定温度</div></td>
									<!--              <td><div class="">中部箱温</div></td>
	                  <td><div class="">尾部箱温</div></td> -->
									<td><div class="">冷机运行模式</div></td>
									<td><div class="">车辆运行时长</div></td>
									<td><div class="">发动机运行时长</div></td>
	
	
								</tr>
	
							</thead>
							<tbody>
	
							</tbody>
						</table>
						<br>
						<br>
						<br>
						<div class="hxpage1_wrap" style="position:absolute; bottom:10px; height:50px; background-color:#fff;width: -moz-calc(100% - 17px); width: -webkit-calc(100% - 17px); width: calc(100% - 17px);">
							<div class="hxpage hxpage1 clearfix" style="width:800px; margin:0 auto;">
							</div>
						</div>
					</div>


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
		function ajax111(pn){
			$.ajax({

						url : "${PATH}/pc/sensor/findSensorWithJson.do",
						data : {
							'containerId' : containerId,
							'startTime' : startTime,
							'endTime' : endTime ,
							'pn' : pn 
						},
						type : "get",
						success : function(result) {
							////解析显示冷藏箱数据
							build_ccdatasDetail_table1(result);
							if(result.extend.page.pageNum == 0 || result.extend.page.pages == 0){
								result.extend.page.pageNum = 1;
								 result.extend.page.pages = 1;
							}
							new hxpage(result.extend.page.pageNum,result.extend.page.pages,"one");
							/* build_(result);  */

						}

					});
		}
		function ajax222(pn){
			$.ajax({

						url : "${PATH}/pc/sensor/findSensorWithJson1.do",
						data : {
							'containerId' : containerId,
							'startTime' : startTime,
							'endTime' : endTime ,
							'pn' : pn 
						},
						type : "get",
						success : function(result) {
							////解析显示冷藏箱数据
							build_ccdatasDetail_table2(result);
							if(result.extend.page.pageNum == 0 || result.extend.page.pages == 0){
								result.extend.page.pageNum = 1;
								 result.extend.page.pages = 1;
							}
							new hxpage(result.extend.page.pageNum,result.extend.page.pages,"two");
							//alert(result.extend.page.pageNum);
							/* build_(result);  */

						}

					});
		}
		//根据containId获取报表信息
		$(".con__boxTable tbody").on("click", "tr", function() {
			containerId = $(this).find(".containerId").text();
			startTime = $("#startTime").val();
			endTime = $("#endTime").val();

			if (startTime == "" || endTime == "") {
				alert("开始、结束时间不能为空");
			} else {
				//	        console.log(startTime.replace(/(^\s*)|(\s*$)/g,""));
				//	        console.log(endTime.replace(/(^\s*)|(\s*$)/g,""));

				startTime = startTime.replace(/(^\s*)|(\s*$)/g, "");
				endTime = endTime.replace(/(^\s*)|(\s*$)/g, "");

				if (startTime == "" || endTime == "") {
					alert("开始、结束时间不能为空");
				} else {
					ajax111(1);
					ajax222(1);
					
				}

			}
		});
		//解析显示冷藏箱数据
		function build_ccdatasDetail_table1(result) {
			console.log(result);
			var addHtml = $("#ccdatasDetail_table tbody").empty();
			var ccdatas = result.extend.page.list;
			var htmlStr = "";
			$.each(ccdatas, function(index, item) {
				htmlStr += "<tr>";
				htmlStr += "<td><div class='leftDown--div1'>" + (index+1)
						+ "</div></td> ";
				htmlStr += "<td><div class=''>" + item.containerId
						+ "</div></td>";
				htmlStr += "<td><div class=''>" + item.nowTime
						+ "</div></td>";
				if(item.refBatVol != null && item.refBatVol > 15){
					htmlStr += "<td><div class=''>" + 15
							+ "</div></td>";
				}else if(item.refBatVol != null && item.refBatVol < 0){
					htmlStr += "<td><div class=''>" + 0
							+ "</div></td>";
					
				}else if(item.refBatVol != null){
					htmlStr += "<td><div class=''>" + item.refBatVol
							+ "</div></td>";
				}else{
					htmlStr += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				if(item.backWindTemp != null){
					htmlStr += "<td><div class=''>" + item.backWindTemp
							+ "</div></td>";
				}else{
					htmlStr += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				if(item.workMode != null){
					htmlStr += "<td><div class=''>" + item.workMode
							+ "</div></td>";
				}else{
					htmlStr += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				if(item.enviTemp != null){
					htmlStr += "<td><div class=''>" + item.enviTemp
							+ "</div></td>";
				}else{
					htmlStr += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				if(item.chuWindTemp != null){
					htmlStr += "<td><div class=''>" + item.chuWindTemp
							+ "</div></td>";
				}else{
					htmlStr += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				if(item.tempSet != null){
					htmlStr += "<td><div class=''>" + item.tempSet
							+ "</div></td>";
				}else{
					htmlStr += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				if(item.refRunMode != null){
					htmlStr += "<td><div class=''>" + item.refRunMode
							+ "</div></td>";
				}else{
					htmlStr += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				if(item.vecRunTime != null){
					htmlStr += "<td><div class=''>" + item.vecRunTime
							+ "</div></td>";
				}else{
					htmlStr += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				if(item.engRunTime != null){
					htmlStr += "<td><div class=''>" + item.engRunTime
							+ "</div></td>";
				}else{
					htmlStr += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				htmlStr += "</tr>";
				htmlStr += "<tr>";
				htmlStr += "</tr>";
			});
			addHtml.append(htmlStr);
			var btnStr = "<div class='footer__btn clearfix'><a href='' class='enviTemp btn--some'>导出环境温度</a><a href='' class='refBatVol btn--some'>导出冷机电瓶电压</a><a href='' class='sensorBackWindTemp btn--some'>导出回风温度</a><a href='' class='sensorall btn--all'>导出硬件报表</a></div>";
			$(".hxpage1_wrap .footer__btn").remove();
			$(".hxpage1_wrap").append(btnStr);
		}
		//解析自研设备数据
		function build_ccdatasDetail_table2(result) {
			var addHtml1 = $("#ourccdatasDetail_table tbody").empty();
			var ourccdatas = result.extend.page.list;
			var htmlStr1 = "";
			$.each(ourccdatas, function(index, item) {

				htmlStr1 += "<tr>";
				htmlStr1 += "<td><div>" + (index + 1) + "</div></td> ";
				htmlStr1 += "<td><div class=''>" + item.containerId
						+ "</div></td>";
				htmlStr1 += "<td><div class=''>" + item.nowTime
						+ "</div></td>";
				if(item.boxHum != null){
					htmlStr1 += "<td><div class=''>" + item.boxHum
							+ "</div></td>";
				}else{
					htmlStr1 += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				if(item.tailBoxTemp != null){
					htmlStr1 += "<td><div class=''>" + item.tailBoxTemp
							+ "</div></td>";
				}else{
					htmlStr1 += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				if(item.gpsPower != null && item.gpsPower > 15){
					htmlStr1 += "<td><div class=''>" + 15
							+ "</div></td>";
				}else if(item.gpsPower != null && item.gpsPower < 0){
					htmlStr1 += "<td><div class=''>" + 0
							+ "</div></td>";
				}else if(item.gpsPower != null){
					htmlStr1 += "<td><div class=''>" + item.gpsPower
							+ "</div></td>";
				}
				else{
					htmlStr1 += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				if(item.oilLevel != null){
					htmlStr1 += "<td><div class=''>" + item.oilLevel
							+ "</div></td>";
				}else{
					htmlStr1 += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				if(item.backDoorState != null){
					htmlStr1 += "<td><div class=''>" + item.backDoorState
							+ "</div></td>";
				}else{
					htmlStr1 += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				if(item.lon != null){
					htmlStr1 += "<td><div class=''>" + item.lon
							+ "</div></td>";
				}else{
					htmlStr1 += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				if(item.lat != null){
					htmlStr1 += "<td><div class=''>" + item.lat
							+ "</div></td>";
				}else{
					htmlStr1 += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				if(item.gpsPosition != null){
					htmlStr1 += "<td><div class=''>" + item.gpsPosition
							+ "</div></td>";
				}else{
					htmlStr1 += "<td><div class=''>" + ""
							+ "</div></td>";
				}
				htmlStr1 += "</tr>";
			});

			/*  function build_ourccdatasDetail_table(result) {
			console.log(result);
			var addHtml = $("#ourccdatasDetail_table tbody").empty();
			var ourccdatas = result.extend.maps;

			var htmlStr = "";
			$.each(ourccdatas, function(index, item) {
				htmlStr += "<tr>";
				htmlStr += "<td><div class='leftDown--div1'>" + index
						+ "</div></td> ";
				htmlStr += "<td><div class=''>" + item.ourccdata.containerId
						+ "</div></td>";
				htmlStr += "<td><div class=''>" + item.ourccdata.nowTime
						+ "</div></td>";
				htmlStr += "<td><div class=''>" + item.ourccdata.oilTemp
						+ "</div></td>";
				htmlStr += "<td><div class=''>" + item.ourccdata.boxHum
						+ "</div></td>";
				htmlStr += "<td><div class=''>" + item.ourccdata.tailBoxTemp
						+ "</div></td>";
				htmlStr += "<td><div class=''>" + item.ourccdata.gpsPower
						+ "</div></td>";
				htmlStr += "<td><div class=''>" + item.ourccdata.oilLevel
						+ "</div></td>";
				htmlStr += "<td><div class=''>" + item.ourccdata.backDoorState
						+ "</div></td>";
			}); */

			//1.导出数据（0）
			
			addHtml1.append(htmlStr1);
			var btnStr = "<div class='footer__btn clearfix'><a href='' class='ourccdataall btn--all'>导出自研设备报表</a></div>";
			/* $(".con__sensorTable .con__main .con__main__table").append(btnStr); */
			$(".hxpage2_wrap .footer__btn").remove();
			$(".hxpage2_wrap").append(btnStr);
		}
		//（1）
		$(".con__sensorTable .con__main").on(
				"click",
				".sensorall",
				function() {
					console.log(containerId);
					console.log(startTime);
					console.log(endTime);
					$(this).attr(
							"href",
							"${PATH}/pc/ExportExcel/findSensorAllWithJson.do?containerId="
									+ containerId + "&startTime=" + startTime
									+ "&endTime=" + endTime);

				});
		//（2）
		$(".con__sensorTable .con__main").on(
				"click",
				".sensorBackWindTemp",
				function() {
					console.log(containerId);
					console.log(startTime);
					console.log(endTime);
					$(this).attr(
							"href",
							"${PATH}/pc/ExportExcel/findSensorBackWindTempWithJson.do?containerId="
									+ containerId + "&startTime=" + startTime
									+ "&endTime=" + endTime);

				});
		//（3）
		$(".con__sensorTable .con__main").on(
				"click",
				".refBatVol",
				function() {
					console.log(containerId);
					console.log(startTime);
					console.log(endTime);
					$(this).attr(
							"href",
							"${PATH}/pc/ExportExcel/findSensorRefBatVolWithJson.do?containerId="
									+ containerId + "&startTime=" + startTime
									+ "&endTime=" + endTime);

				});
		//（4）
		$(".con__sensorTable .con__main").on(
				"click",
				".enviTemp",
				function() {
					console.log(containerId);
					console.log(startTime);
					console.log(endTime);
					$(this).attr(
							"href",
							"${PATH}/pc/ExportExcel/findSensorEnviTempWithJson.do?containerId="
									+ containerId + "&startTime=" + startTime
									+ "&endTime=" + endTime);

				});

		//（5）
		$(".con__sensorTable .con__main").on(
				"click",
				".ourccdataall",
				function() {
					console.log(containerId);
					console.log(startTime);
					console.log(endTime);
					$(this).attr(
							"href",
							"${PATH}/pc/ExportExcel/findOurCcdataallAllWithJson.do?containerId="
									+ containerId + "&startTime=" + startTime
									+ "&endTime=" + endTime);

				});
		
		
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
	<!-- 分页部分 -->
<script type="text/javascript">
				  //当前页数，总页数
	var ajaxObj = "";			  
	function hxpage(page,totalPage,sign){
		//console.log(page + "aaaaa"+totalPage);
		var htmlStr = "<span class='pageBtnWrap'>";
		switch(sign){
			case "one":
				ajaxObj = "ajax111";
				var ele = $(".hxpage1");
			break;
			case "two":
				ajaxObj = "ajax222";
				var ele = $(".hxpage2");
			break;
			default:
			break;
		}
		if(page < 1 || page > totalPage){
			alert("将要跳转的页码不正确，页码不能小于“1”或大于总页码数");
			return false;
		}
	            	
		/* 首页和上一页    前面部分 */
		if(page > 1){
			htmlStr += "<a href='javascript:;' onclick='"+ajaxObj+"(1)' title='首页'>首页</a><a href='javascript:;' onclick='"+ajaxObj+"("+(page-1)+")' title='上一页'>上一页</a>";
		}
		if(page == 1){//当前页小于1时
			htmlStr += "<span class='disabled'>首页</span><span class='disabled'>上一页</span>";
		}
		/* 中间  页码  部分 */
		if(totalPage < 8){//1到7时
			for(var i = 1;i <= totalPage;i++){
				if(i != page){
					htmlStr += "<a href='javascript:;' onclick='"+ajaxObj+"("+i+")' title='第"+i+"页'>"+i+"</a>";
				}else{
					htmlStr += "<span class='curr'>"+i+"</span>";
				}
			}
		}else if(totalPage > 7){//大于7时
			if((page-3) <= 1){//当前页减3小于或等于1时，当前页左边区域全部显示
				for(var i = 1;i < page;i++){//1到当前的
					htmlStr += "<a href='javascript:;' onclick='"+ajaxObj+"("+i+")' title='第"+i+"页'>"+i+"</a>";
				}
				htmlStr += "<span class='curr'>"+page+"</span>";//当前的
				for(var i = 1;i<(7-page);i++){//当前的  到  第6个  //如：123（4） 56...7
					htmlStr += "<a href='javascript:;' onclick='"+ajaxObj+"("+(i+page)+")' title='第"+(i+page)+"页'>"+(i+page)+"</a>";
				}
				//显示大于7页   显示...
				htmlStr += "<b style='float:left;'>...</b>";
				//显示大于7页   显示...后最后一个
				htmlStr += "<a href='javascript:;' onclick='"+ajaxObj+"("+totalPage+")' title='第"+totalPage+"页'>"+totalPage+"</a>";
			}else if((page+3) >= totalPage){//当前页加3大于totalPage时，当前页右边区域全部显示
				//显示第一页内容
				htmlStr += "<a href='javascript:;' onclick='"+ajaxObj+"(1)' title='第1页'>1</a>";
				htmlStr += "<b style='float:left;'>...</b>";
				for(var i = 5;i >= 0;i--){//显示从最大页数向前显示六个
					if((totalPage-i) == page){//当等于page时，显示为不可点击的span
						htmlStr += "<span class='curr'>"+page+"</span>";//当前的
						continue;
					}
					htmlStr += "<a href='javascript:;' onclick='"+ajaxObj+"("+(totalPage-i)+")' title='第"+(totalPage-i)+"页'>"+(totalPage-i)+"</a>";
				}	
			}else{//前不着村后不着店时，即前面减3不小于1，后面加3不大于最大页码  （前面显示第1页和... ， 后面显示...和最后一页）
				htmlStr += "<a href='javascript:;' onclick='"+ajaxObj+"(1)' title='第1页'>1</a>";
				htmlStr += "<b style='float:left;'>...</b>";
				for(var i = 2;i > 0;i--){//显示当前页码的上两个
					htmlStr += "<a href='javascript:;' onclick='"+ajaxObj+"("+(page-i)+")' title='第"+(page-i)+"页'>"+(page-i)+"</a>";
				}
				htmlStr += "<span class='curr'>"+page+"</span>";//当前的页码
				for(var i = 1;i < 3;i++){//显示当前页码的下两个
					htmlStr += "<a href='javascript:;' onclick='"+ajaxObj+"("+(page+i)+")' title='第"+(page+i)+"页'>"+(page+i)+"</a>";
				}
				htmlStr += "<b style='float:left;'>...</b>";
				htmlStr += "<a href='javascript:;' onclick='"+ajaxObj+"("+totalPage+")' title='第"+totalPage+"页'>"+totalPage+"</a>";
			}	
		}
		/* 尾页和下一页   后面部分 */
		if(page < totalPage){
			htmlStr += "<a href='javascript:;' onclick='"+ajaxObj+"("+(page+1)+")' title='下一页'>下一页</a><a href='javascript:;' onclick='"+ajaxObj+"("+totalPage+")' title='尾页'>尾页</a>";
		}
		if(page == totalPage){//当前页等于尾页时，按钮停用
			htmlStr += "<span class='disabled'>下一页</span><span class='disabled'>尾页</span>";
		}
		htmlStr += "</span>";//第一部分页码的结尾
		
		/*当前页   总页  和   跳转部分 */
		htmlStr +="<span class='pageText'>";
	    htmlStr +=	"<span class='totalText'>当前第";
		htmlStr +=		"<span class='currPageNum'>"+page+"</span>页";
		htmlStr +=		"<span class='totalInfoSplitStr'>/</span>共";
		htmlStr +=		"<span class='totalPageNum'>"+totalPage+"</span>页";
	    htmlStr +=	"</span>";
	    htmlStr +=	"<span class='goPageBox'>&nbsp;转到";
	    htmlStr +=		"<span class='hxpage_gopage_wrap'>";
	    
	    var iVal;//声明input框内的值为iVal;
	    //当前页小于总页input框内为当前页加1，等于总页数时input框内为总页数（即当前页数）
	    page < totalPage ? iVal = (page+1) : iVal = page;
	    
		htmlStr +=			"<input type='number' class='hxpager_btn_go_input' value='"+iVal+"'/>";
		htmlStr +=			"页<input type='button' class='hxpager_btn_go' value='确定'/>";
		htmlStr +=		"</span>";
	    htmlStr +=	"</span>";
        htmlStr +="</span>";
		//console.log($("#hxpage"));
		/* console.log("12343435345");
		console.log(ele); */
		ele.html(htmlStr);
	}
	
	/*第一个分页 点击确定跳转页面 */
	$(".hxpage1").on("click",".hxpager_btn_go",function(){
		var inputVal = $(this).prev().val();
		if(inputVal == ""){
	       alert("跳转页码不能为空！！！");
	    }else{
	       inputVal = inputVal.replace(/\s/g,"");
	        if(inputVal == ""){
	          	alert("跳转页码不能为空！！！");
	        }else{
	        console.log("1111111"+ajaxObj);
				//eval(ajaxObj+"("+inputVal+");");
				ajax111(inputVal);
			} 
		}  	
	});
	/*第二个分页 点击确定跳转页面 */
	$(".hxpage2").on("click",".hxpager_btn_go",function(){
		var inputVal = $(this).prev().val();
		if(inputVal == ""){
	       alert("跳转页码不能为空！！！");
	    }else{
	       inputVal = inputVal.replace(/\s/g,"");
	        if(inputVal == ""){
	          	alert("跳转页码不能为空！！！");
	        }else{
				//eval(ajaxObj+"("+inputVal+");");
				ajax222(inputVal);
			} 
		}  	
	});
</script>


</body>
</html>