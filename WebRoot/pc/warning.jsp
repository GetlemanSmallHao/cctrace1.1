<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<base href="<%=basePath%>">

<title>告警信息页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/index.css" />
<link rel="stylesheet" href="css/laydate.css" />
<link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<!-- 导航 -->
	<jsp:include page="header.jsp" flush="true"></jsp:include>
	<main class="main"> <!--告警信息-->
	<div class="warn__warning clearfix">
		<div class="warning__wrap">
			<header class="con__header">
			<ul>
				<li data-con="data/warning.html" class="con__header--active con__header_none">告警提示</li>
			</ul>
			</a> </header>
			<main class="con__main"> <!-- 报警提示 -->
			<div>
				<table>
					<thead>
						<tr>
							<td><div style="padding-left:3px;" class="leftDown--div2">告警信息</div></td>
							<td><div style="padding-left:3px;" class="leftDown--div3">当前未查看记录数</div></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="background-color:transparent; color:inherit;"><div class="leftDown--div2">查看明细</div></td>
							<td><div class="leftDown--div3" id="countOfNRA"></div></td>
						</tr>
					</tbody>
				</table>
				<!-- <img class="con__loading" src="img/loading.gif" alt="" /> -->
			</div>
			<!-- <img style="display:block;" class="con__loading"
				src="img/loading.gif" alt="" /> --> </main>
		</div>
	</div>
	<div class="con__warnDetails clearfix">
		<div class="warnDetails__wrap">
			<header class="con__header">
			<ul>
				<li data-con="${basePath }/data/warnDetails.jsp"
					class="con__header--active con__header_none">告警信息</li>
					<li id="exportAlertExcelFile" onclick="exportAlertExcelFile();" class="red_print">导出报表</li>
			</ul>
			</header>
			<main class="con__main amendBind"><!--  集装箱列表 -->

			<div>
				<div class="con__main--search">
				<!-- 	<form id="form" onsubmit="false" method="post"> -->
						集装箱编号：<input type="text" id="containerId" name="containerId" autocomplete="off" />
						开始时间： <input class="laydate-icon" id="startTime" name="startTime" value="" autocomplete="off"/> 
						结束时间： <input class="laydate-icon" id="endTime" name="endTime" value="" autocomplete="off"/>
						<!-- 	<select
							name="" id="">
							<option value="">所有地标</option>
							<option value="">在地标里</option>
							<option value="">在地标外</option>
						</select> -->
						<input type="button" id="search" value="查询" onclick="searchAlert();"/>
					<!-- </form> -->
				</div>
				<div class="con__main__table">
					<table id = "alert_table">
						<thead>
							<tr>
								<td><div class="">序号</div></td>
								<td><div class="">冷藏箱编号</div></td>
								<td><div class="">报警开始时间</div></td>
								<%--<td><div class="">最近一次报警时间</div></td>--%>
								<td><div class="">告警编号   </div></td>
								<td><div class="">报警类型</div></td>
								<td><div class="">报警内容</div></td>
								<td><div class="">报警时的纬度</div></td>
								<td><div class="">报警时的经度</div></td>
								<td><div class="">报警位置</div></td>
								<td><div class="">读取状态</div>
							</tr>
						</thead>
						<tbody class="alertBind" id = "showSelectAlerts">
							<c:choose>
								<c:when test="${empty alerts}">
									<tr>
										<td style="background-color:transparent; color:inherit;">无数据！</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${alerts}" var="alert" varStatus="status">
										<tr>
											<td><div class="leftDown--div2">${status.index+1}</div></td>
											<td><div class="leftDown--div2">${alert.containerId}</div></td>
											<td><div class="leftDown--div3">${alert.alertTime}</div></td>
											<%--<td><div class="leftDown--div3">${alert.updateTime}</div></td>--%>
											<td><div class="leftDown--div2">${alert.alarm_num }</div></td>
											<td><div class="leftDown--div3">${alert.alertType}</div></td>
											<td><div class="leftDown--div2">${alert.alertContent}</div></td>
											<td><div class="leftDown--div3">${alert.lat}</div></td>
											<td><div class="leftDown--div2">${alert.lon}</div></td>
											<td><div class="leftDown--div2">${alert.gpsPosition}</div></td>
											<td><div class="leftDown--div3 data_readed">
													<c:choose>
														<c:when test="${alert.readed=='no'}">未读</c:when>
														<c:otherwise>已读</c:otherwise>
													</c:choose>
												</div></td>
											<td style="display:none;"><div class="leftDown--div2 data_alert_id">${alert.id}</div></td>	
										</tr>
							</c:forEach>
									
								</c:otherwise>
							</c:choose>
							
						</tbody>
					</table>
				</div>
				
				
				<!-- <div><table>
					<tr>
							<td><a>1</a></td>
							<td>2</td>
							<td>3</td>
						</tr>
				
				</table></div> -->
				
				
			</div>
		 </main>

		</div>
	</div>
	</main>
	<div class="popup_div">
		<i class="fa fa-info-circle fa-3x" aria-hidden="true"></i>
		<p class="popup_read">确认已读</p>
		<span class="popup_go">确认</span>
		<span class="popup_hide">取消</span>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
	<script src="${pageContext.request.contextPath}/js/index.js"></script>
	<script src="${pageContext.request.contextPath}/js/laydate.js"></script>
	<script src="${pageContext.request.contextPath}/js/layer/layer.js" ></script>
	<script src="${pageContext.request.contextPath}/js/layer/extend/layer.ext.js" ></script>
	<script type="text/javascript">
	//回车事件绑定
    $(document).on('keyup', function(event) {
        if (event.keyCode == "13") {
            //回车执行查询
            //alert(111);
           searchAlert();
        }
    });
	
	//下载报表
	function exportAlertExcelFile(){
		debugger;
		var containerId = $.trim($("#containerId").val());
		var startTime = $.trim($("#startTime").val());
		var endTime = $.trim($("#endTime").val());
		
		/*
				$.ajax({
					url : "${pageContext.request.contextPath}/pc/ExportExcel/getAlertExportExcel.do",
					data : {
						"containerId" : containerId,
						"startTime"  : startTime,
						"endTime"    : endTime
					},
					type : "get",
				});
		*/	
				
				var form=$("<form>");//定义一个form表单
				form.attr("style","display:none");
				form.attr("target","");
				form.attr("method","post");
				form.attr("action","${pageContext.request.contextPath}/pc/ExportExcel/getAlertExportExcel.do");
				var input1=$("<input>");
				input1.attr("type","hidden");
				input1.attr("name","containerId");
				input1.attr("value",containerId);
				
				
				var input2=$("<input>");
				input2.attr("type","hidden");
				input2.attr("name","startTime");
				input2.attr("value",startTime);
				
				var input3=$("<input>");
				input3.attr("type","hidden");
				input3.attr("name","endTime");
				input3.attr("value",endTime);
				
				
				
				$("body").append(form);//将表单放置在web中
				form.append(input1);
				form.append(input2);
				form.append(input3);
				form.submit();
				
	}
	
	function searchAlert(){
		var containerId = $.trim($("#containerId").val());
		var startTime = $.trim($("#startTime").val());
		var endTime = $.trim($("#endTime").val());
		$.ajax({
			url : "${pageContext.request.contextPath}/pc/alert/showAlertsInTwoTimeAndContainerId.do",
			data : {
				"containerId" : containerId,
				"startTime"  : startTime,
				"endTime"    : endTime
			},
			type : "get",
			success : function(result) {
				$("#showSelectAlerts").html(result);
			} 
		});
	}
	</script>
	<script type="text/javascript">
		var maxAlertId = 0;
		function askMaxAlertId() {
			var alertId = 0;
			$.ajax({
						url : "${pageContext.request.contextPath}/pc/alert/getMaxAlertIdByCompanyId.do",
						data : "",
						async : false,
						type : "get",
						dataType : "json",
						success : function(result) {
							alertId = result;
						}
					});
			return alertId;
		}
		
		function askCountOfNoReadedAlert(){
			var countOfNoReaded = 0;
			$.ajax({
				url:"${pageContext.request.contextPath}/pc/alert/getCountOfNoReadedALertsBeforeDays.do",
				data:"",
				async : false,
				type : "get",
				dataType : "json",
				success : function(result) {
					countOfNoReaded = result;
				}
			});
			return countOfNoReaded;
		}

		function compareMaxAlertId() {
			var currentMaxId = askMaxAlertId();
			if (maxAlertId == currentMaxId) {
				console.log("相同" + maxAlertId);
				return false;
			} else {
				location.href = "${pageContext.request.contextPath}/pc/alert/getRecentFourDaysAlerts.do";
			}
		}
	
		$(function() {
			console.log(maxAlertId);
			maxAlertId = askMaxAlertId();
			console.log(maxAlertId);
			var compareAlertInterval = setInterval("compareMaxAlertId()",150000);
			var countOfNRA = askCountOfNoReadedAlert();
			console.log("countOfNRA");
			$("#countOfNRA").text(countOfNRA);
		});
		$(".alertBind").on("click","tr",function(){
			if($(this).children('td').text() == '无数据！'){
				return;
			};
			var alertId = $(this).find(".data_alert_id").text();
			var readStateEle = $(this).find(".data_readed");
			var readState = readStateEle.text().replace(/\s*/g, "");
			if(readState=='已读'){
				layer.msg("这是一个已读的信息!");
				return;
			}
			$('.popup_div').show();
			$('.popup_hide').on('click',function(){
				$('.popup_div').hide();
			});
			$('.popup_go').on('click',function(){
				$('.popup_div').hide();
				$.ajax({
					url:"${pageContext.request.contextPath}/pc/alert/modifyAlertReadStateByAlertId.do",
					data:{"alertId":alertId},
					type:"post",
					async : false,
					dataType : "json",
					success : function(result) {
						if(result.readed=='yes'){
							readStateEle.text("已读");
							var countOfNRA = askCountOfNoReadedAlert();
							$("#countOfNRA").text(countOfNRA);
						}
					}
				});
			});
		});
		
		
		var start = {
			elem : '#startTime',
			event : 'click',
			format : 'YYYY-MM-DD hh:mm:ss',
			min : '2014-01-01 00:00:00', //最小日期
			max : laydate.now(), //设定最大日期为当前日期
			istime : true,
			istoday : true,
			choose : function(datas) {
				//console.log(datas);
				//console.log(new Date(datas));//Thu Jul 13 2017 15:03:53 GMT+0800 (中国标准时间)
				//console.log(new Date(datas).getTime());//1499929433000
				//console.log((new Date(datas)).valueOf());//1499929433000
				//console.log(Date.parse(new Date(datas)));//1499929433000
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
</body>
</html>