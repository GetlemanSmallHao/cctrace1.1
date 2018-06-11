<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.cctrace.entity.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>冷藏箱控制系统设置页</title>
<%
	pageContext.setAttribute("PATH", request.getContextPath());
	User user = (User) request.getSession().getAttribute("user");
	String role = user.getRole();
	int role1 = role.equals("common") ? 1 : 0;
%>

<%
	StringBuffer ss = request.getRequestURL();
%>
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/cctrace1.0
 -->
<link rel="stylesheet" href="${PATH}/css/index.css" />


</head>
<body>
	<!-- 导航 -->
	<jsp:include page="header.jsp" flush="true"></jsp:include>
	<!--内容部分-->
	<main class="main"> <!--<div class="con__map bindEvent clearfix">地图</div>-->
	<!--告警信息模块-->
	<div class="con__warning con__warning--set bindEvent clearfix">
		<div class="warning__wrap">
			<header class="con__header">
			<ul>
				<li data-con="${PATH}/data/warning.html" class="con__header--active">告警提示</li>
			</ul>

			</header>
		</div>
	</div>
	<!--本集装箱信息-->
	<div class="con__container--set clearfix">
		<div class="container__wrap">
			<header class="con__header">
			<ul>
				<li data-con="${PATH}/data/container.html"
					class="con__header--active">班列</li>
				<!-- <li>地图</li> -->
			</ul>
			<!--<a class="con__header&#45;&#45;refresh" href="javascript:;">--> <!--<i></i>-->
			<!--刷新--> <!--</a>--> </header>
			<main class="con__main amendBind">
			<div>
				<div class="con__main--search">
					冷藏箱编号：<input type="text" id="containerIdSearch" /> <input
						type="button" value="查询" onclick="searchContainerId();"
						id="Search111"> <font style="color: red;">请输入CICU后7位</font>
				</div>
				<div class="con__main__table">
					<table>
						<thead>
							<tr>

								<td><div class="">冷藏箱编号</div></td>
								<td><div class="">设备编号</div></td>
								<td><div class="">班列ID</div></td>
								<td><div class="">系统时间</div></td>
								<td><div class="">冷机告警类型</div></td>
								<td><div class="">货物类型</div></td>
								<td><div class="">下货站</div></td>
								<td><div class="">场站名称</div></td>
								<td><div class="">路线类型</div></td>
								<td><div class="">回风温度</div></td>
								<td><div class="">油位</div></td>
								<td><div class="">设定温度</div></td>
								<!-- <td><div class="">开关机状态</div></td>
								<td><div class="">通讯模式</div></td> -->
							</tr>
						</thead>
						<tbody id="ccdatas_table">

						</tbody>
					</table>
				</div>
				<!--<img class="con__loading" src="img/loading.gif" alt=""/>-->
			</div>
			<div class="con__modal">
				<div class="con__modal--wrap">
					<span> 集装箱编号：<input type="text" readonly unselectable="on" />
					</span> <span> 班列ID：<input type="text" />
					</span> <span> 货物类型：<input type="text" />
					</span> <span> 下货站：<input type="text" />
					</span> <span> 场站名称：<input type="text" />
					</span> <span class="con__modal--btn">
						<button>保 存</button>
						<button class="con__modal--cancel rt">取 消</button>
					</span>
				</div>
			</div>
			</main>
		</div>
	</div>
	<!--当前集装箱设置部分-->
	<div class="con__sensor--set clearfix">
		<div class="sensor__wrap">
			<header class="con__header">
			<ul>
				<li data-con="${PATH}/data/sensor.html" class="j-sensor">传感器</li>
				<li data-con="${PATH}/data/set.html" class="con__header--active">设置</li>
			</ul>
			<!--<a class="con__header&#45;&#45;refresh" href="javascript:;">--> <!--<i></i>-->
			<!--刷新--> <!--</a>--> </header>
			<main class="con__main set__menu">
			<div class="set__menu1">
				<div class="set__menu--wrap">
					<div class="con__main--search">
						设备编号：
						<!-- <span>CICU9913310</span> -->
						<span> <%
 	String path = request.getContextPath();
 	String basePath = request.getScheme() + "://"
 			+ request.getServerName() + ":" + request.getServerPort()
 			+ path + "/";
 	String containerId = request.getParameter("containerId");//用request得到
 %> <span><%=containerId%></span>

						</span>
					</div>

					<div class="con__main__table">
						<table style="width:100%;">
							<thead>
								<tr>
									<td><div class="leftDown--div1">序号</div></td>
									<td><div class="">选项</div></td>
								</tr>
							</thead>
							<tbody>
								<tr data-url2="set__menu2--list1" data-url3="set__menu3--list1">
									<td><div class="">2</div></td>
									<td>
										<div class="">冷藏箱控制设置</div>
									</td>
								</tr>
								<!--<tr>-->
								<!--<td><div class="">3</div></td>-->
								<!--<td>-->
								<!--<div class="">-->
								<!--车辆日志-->
								<!--</div>-->
								<!--</td>-->
								<!--</tr>-->
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!--二级菜单--> <!--二级菜单 —— 冷藏箱控制设置 list1-->
			<div class="set__menu2 set__menu2--list1">
				<div class="set__menu--wrap">
					<div class="con__main--search">冷藏箱控制设置</div>
					<div class="con__main__table">
						<table style="width:100%;">
							<thead>
								<tr>
									<td><div class="leftDown--div1">序号</div></td>
									<td><div class="">选项</div></td>
								</tr>
							</thead>
							<tbody>
								<tr data-url="set__menu3--wrap1">
									<td>
										<div>1</div>
									</td>
									<td>
										<div onclick="showchiller()">温度设定</div>
									</td>
								</tr>
								<tr data-url="set__menu3--wrap2">
									<td>
										<div>2</div>
									</td>
									<td>
										<div onclick="showchiller1()">启动除霜</div>
									</td>
								</tr>
								<tr data-url="set__menu3--wrap3">
									<td>
										<div>3</div>
									</td>
									<td>
										<div onclick="showchiller2()">启动自检</div>
									</td>
								</tr>
								<tr data-url="set__menu3--wrap4">
									<td>
										<div>4</div>
									</td>
									<td>
										<div onclick="showchiller3()">清除告警</div>
									</td>
								</tr>
								<tr data-url="set__menu3--wrap5">
									<td>
										<div>5</div>
									</td>
									<td>
										<div onclick="showchiller4()">配置运行模式</div>
									</td>
								</tr>
								<tr data-url="set__menu3--wrap6">
									<td>
										<div>6</div>
									</td>
									<td>
										<div onclick="showchiller5()">远程开关机</div>
									</td>
								</tr>
								<tr data-url="set__menu3--wrap7">
									<td>
										<div>7</div>
									</td>
									<td>
										<div onclick="showchiller6()">新风门开关机</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!--三级菜单--> <!--三级菜单 —— 冷藏箱控制的三级菜单 list1-->
			<div class="set__menu3 set__menu3--list1">
				<!--温度设定-->
				<div class="set__menu--wrap set__menu3--wrap1">
					<div class="con__main--search">温度设定</div>
					<div class="set__menu3--main clearfix">
						<div class="set__menu3--header">
							<p>
								冷藏箱编号：<span id="showcontainerId"><%=containerId%></span>
							</p>
							<!-- <p>
								PIC固件版本：<span>1071</span>
							</p> -->
							<p>
								冷机类型：<span id="chiller"></span>
							</p>
						</div>
						<div class="set__menu3--input">
							设置隔箱温度：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="temp"
								maxlength="6" onfocus="{this.value=''};" />&nbsp;°C
						</div>
					</div>
					<input class="set__menu3--send" type="button" value="发送" id="btn1" />
				</div>
				<!--启动除霜-->
				<div class="set__menu--wrap set__menu3--wrap2">
					<div class="con__main--search">启动除霜</div>
					<div class="set__menu3--main clearfix">
						<div class="set__menu3--header">
							<p>
								冷藏箱编号：<span id="showcontainerId"><%=containerId%></span>
							</p>
							<!-- <p>
								PIC固件版本：<span>1071</span>
							</p> -->
							<p>
								冷机类型：<span id="chiller1"></span>
							</p>
						</div>
						<div class="set__menu3--input">
							<label id="setBootDef"> <input type="checkbox"
								id="bootDef" />启动除霜
							</label>
						</div>
					</div>
					<input class="set__menu3--send" type="button" value="发送" id="btn2" />
				</div>
				<!--启动自检-->
				<div class="set__menu--wrap set__menu3--wrap3">
					<div class="con__main--search">启动自检</div>
					<div class="set__menu3--main clearfix">
						<div class="set__menu3--header">
							<p>
								冷藏箱编号：<span><%=containerId%></span>
							</p>
							<!-- <p>
								PIC固件版本：<span>1071</span>
							</p> -->
							<p>
								冷机类型：<span id="chiller2"></span>
							</p>
						</div>
						<div class="set__menu3--input">
							<label> <input type="checkbox" id="selfCheck" />启动自检
							</label>
						</div>
					</div>
					<input class="set__menu3--send" type="button" value="发送" id="btn3" />
				</div>
				<!--清除告警-->
				<div class="set__menu--wrap set__menu3--wrap4">
					<div class="con__main--search">清除告警</div>
					<div class="set__menu3--main clearfix">
						<div class="set__menu3--header">
							<p>
								冷藏箱编号：<span><%=containerId%></span>
							</p>
							<!-- <p>
								PIC固件版本：<span>1071</span>
							</p> -->
							<p>
								冷机类型：<span id="chiller3"></span>
							</p>
						</div>
						<div class="set__menu3--input">
							<label> <input type="checkbox" id="clearAlert" />清除告警
							</label>
						</div>
					</div>
					<input class="set__menu3--send" type="button" value="发送" id="btn4" />
				</div>
				<!--配置运行模式-->
				<div class="set__menu--wrap set__menu3--wrap5">
					<div class="con__main--search">配置运行模式</div>
					<div class="set__menu3--main clearfix">
						<div class="set__menu3--header">
							<p>
								冷藏箱编号：<span><%=containerId%></span>
							</p>
							<!-- <p>
								PIC固件版本：<span>1071</span>
							</p> -->
							<p>
								冷机类型：<span id="chiller4"></span>
							</p>
						</div>
						<div class="set__menu3--input">
							配置启停模式：
							<!--  <select name="">
                  <option value="">请选择启停模式</option>
                  <option value="">连续运转模式</option>
                  <option value="">自动启停模式</option>
                </select> -->
							<input id="refRunMode_01" type="radio" name="refRunMode"
								value="continuous">连续运转模式 <input id="refRunMode_02"
								type="radio" name="refRunMode" value="auto">启停运转模式
						</div>
					</div>
					<input class="set__menu3--send" type="button" value="发送" id="btn5" />
				</div>
				<!--远程开关机-->
				<div class="set__menu--wrap set__menu3--wrap6">
					<div class="con__main--search">远程开关机</div>
					<div class="set__menu3--main clearfix">
						<div class="set__menu3--header">
							<p>
								冷藏箱编号：<span><%=containerId%></span>
							</p>
							<!-- <p>
								PIC固件版本：<span>1071</span>
							</p> -->
							<p>
								冷机类型：<span id="chiller5"></span>
							</p>
							<!-- <p>
								当前电源状态：<span>Carrier</span>
							</p> -->
						</div>
						<div class="set__menu3--input">
							开关机选择：
							<!-- <select name="">
                  <option value="">关机</option>
                  <option value="">开机</option>
                </select> -->
							<input id="remoteSwiMac_01" type="radio" name="remoteSwiMac"
								value="on">开机 <input id="remoteSwiMac_02" type="radio"
								name="remoteSwiMac" value="off">关机
						</div>
					</div>
					<input class="set__menu3--send" type="button" value="发送" id="btn6" />
				</div>
				<!--新风门开关机-->
				<div class="set__menu--wrap set__menu3--wrap7">
					<div class="con__main--search">新风门开关机</div>
					<div class="set__menu3--main clearfix">
						<div class="set__menu3--header">
							<p>
								冷藏箱编号：<span><%=containerId%></span>
							</p>
							<!-- <p>
								PIC固件版本：<span>1071</span>
							</p> -->
							<p>
								冷机类型：<span id="chiller6"></span>
							</p>
							<!-- <p>
								当前电源状态：<span>Carrier</span>
							</p> -->
						</div>
						<div class="set__menu3--input">
							开关机选择：
							<!-- <select name="">
                  <option value="">关机</option>
                  <option value="">开机</option>
                </select> -->
							<input id="remoteXFSwiMac_01" type="radio" name="remoteXFSwiMac"
								value="on">开机 <input id="remoteXFSwiMac_02"
								type="radio" name="remoteXFSwiMac" value="off">关机<br>
							<input id="remoteXFSwiMac_03" type="radio" name="remoteXFSwiMac"
								value="cfmOn">开启CFM控制量<input type="text" id="CFM"
								maxlength="2" onblur="checkNum()" onfocus="{this.value=''};" />
							(仅允许输入0~50且能被5整除的数字)
						</div>
					</div>
					<input class="set__menu3--send" type="button" value="发送" id="btn7" />
				</div>


			</div>
			</main>
		</div>
	</div>
	</main>


	<script src="${PATH}/js/jquery-3.2.1.js"></script>
	<script src="${PATH}/js/index.js"></script>
	<script src="${PATH}/js/layer/layer.js"></script>
	<script src="${PATH}/js/layer/extend/layer.ext.js"></script>
	<script type="text/javascript">
		function checkNum(){
			var x=document.getElementById("CFM").value;
			if(x<=0||x>50||(x%5!=0)){
				document.getElementById("CFM").value=null;
			}
		}
	</script>
	<script type="text/javascript">
	function searchContainerId(){
	/* alert("1111111111"); */
		var containerId22 = $("#containerIdSearch").val();
		if(containerId22 == null || containerId22 == ""){
			alert("请输入编号后7位");
		}else{
			location.replace('<%=ss%>?containerId=CICU'+containerId22); 
  			<%-- objA.href="<%=ss%>+?containerId=CICU"+containerId22; --%>
		}
	}
	
	
		var wait = 10;
		function time(o, o1, o2, o3, o4, o5, o6) {
			if (wait == 0) {
				o.removeAttribute("disabled");
				o.value = "发送";

				o1.removeAttribute("disabled");
				o1.value = "发送";

				o2.removeAttribute("disabled");
				o2.value = "发送";

				o3.removeAttribute("disabled");
				o3.value = "发送";

				o4.removeAttribute("disabled");
				o4.value = "发送";

				o5.removeAttribute("disabled");
				o5.value = "发送";
				
				o6.removeAttribute("disabled");
				o6.value = "发送";

				wait = 10;
			} else {
				o.setAttribute("disabled", true);
				o.value = wait + "秒后可再点击";

				o1.setAttribute("disabled", true);
				o1.value = wait + "秒后可再点击";

				o2.setAttribute("disabled", true);
				o2.value = wait + "秒后可再点击";

				o3.setAttribute("disabled", true);
				o3.value = wait + "秒后可再点击";

				o4.setAttribute("disabled", true);
				o4.value = wait + "秒后可再点击";

				o5.setAttribute("disabled", true);
				o5.value = wait + "秒后可再点击";
				
				o6.setAttribute("disabled", true);
				o6.value = wait + "秒后可再点击";
				wait--;
				setTimeout(function() {
					time(o, o1, o2, o3, o4, o5, o6)
				}, 1000)
			}
		}

		var id1 = document.getElementById("btn1");
		var id2 = document.getElementById("btn2");
		var id3 = document.getElementById("btn3");
		var id4 = document.getElementById("btn4");
		var id5 = document.getElementById("btn5");
		var id6 = document.getElementById("btn6");
		var id7 = document.getElementById("btn7");
		//温度设定
		id1.onclick = function() {
			time(id1, id2, id3, id4, id5, id6, id7);
			var setTemp = $("#temp").val();
			var chillerType = $("#chiller").text();
			$.ajax({
				url : "${PATH}/pc/seting/setTemp.do",
				data : {
					"containerId" : containerId,
					"deviceId" : deviceId,
					"setTemp" : setTemp,
					"chillerType" : chillerType
				},
				type : "get",
				success : function(result) {

					var field = result.state;
					if (field == '0') {
						var num = Math.random();
						layer.msg(result.message);
						return;
					}
					layer.msg(result.message);
				}
			});

		}
		//启动除霜
		id2.onclick = function() {
			time(id1, id2, id3, id4, id5, id6, id7);
			var bootDef;
			var chillerType = $("#chiller1").text();
			if ($("#bootDef").is(':checked')) {
				bootDef = "on";
			} else {
				bootDef = "off";
			}
			$.ajax({
				url : "${PATH}/pc/seting/bootDef.do",
				data : {
					"containerId" : containerId,
					"deviceId" : deviceId,
					"bootDef" : bootDef,
					"chillerType" : chillerType
				},
				type : "get",
				success : function(result) {

					var field = result.state;
					if (field == '0') {
						var num = Math.random();
						layer.msg(result.message);
						return;
					}
					layer.msg(result.message);
				}
			});
		}
		//自检
		id3.onclick = function() {
			time(id1, id2, id3, id4, id5, id6, id7);
			var selfCheck;
			var chillerType = $("#chiller2").text();
			if ($("#selfCheck").is(':checked')) {
				selfCheck = "on";
			} else {
				selfCheck = "off";
			}
			$.ajax({
				url : "${PATH}/pc/seting/selfCheck.do",
				data : {
					"containerId" : containerId,
					"deviceId" : deviceId,
					"selfCheck" : selfCheck,
					"chillerType" : chillerType
				},
				type : "get",
				success : function(result) {

					var field = result.state;
					if (field == '0') {
						var num = Math.random();
						layer.msg(result.message);
						return;
					}
					layer.msg(result.message);
				}
			});
		}
		//清除警告
		id4.onclick = function() {
			time(id1, id2, id3, id4, id5, id6, id7);
			var clearAlert;
			var chillerType = $("#chiller3").text();
			if ($("#clearAlert").is(':checked')) {
				clearAlert = "on";
			} else {
				clearAlert = "off";
			}
			$.ajax({
				url : "${PATH}/pc/seting/clearAlert.do",
				data : {
					"containerId" : containerId,
					"deviceId" : deviceId,
					"clearAlert" : clearAlert,
					"chillerType" : chillerType
				},
				type : "get",
				success : function(result) {

					var field = result.state;
					if (field == '0') {
						var num = Math.random();
						layer.msg(result.message);
						return;
					}
					layer.msg(result.message);
				}
			});
		}
		//运行模式
		id5.onclick = function() {
			time(id1, id2, id3, id4, id5, id6, id7);
			getVal_01();
			hasValue = false,//定义变量
			refRunMode = checkedVal_01;
			var chillerType = $("#chiller4").text();

			console.log("$$" + refRunMode);
			$.ajax({
				url : "${PATH}/pc/seting/refRunMode.do",
				data : {
					"containerId" : containerId,
					"deviceId" : deviceId,
					"refRunMode" : refRunMode,
					"chillerType" : chillerType
				},
				type : "get",
				success : function(result) {

					var field = result.state;
					if (field == '0') {
						var num = Math.random();
						layer.msg(result.message);
						return;
					}
					layer.msg(result.message);
				}
			});

		}
		//远程开关机
		id6.onclick = function() {
			time(id1, id2, id3, id4, id5, id6, id7);
			getVal();
			var chillerType = $("#chiller5").text();
			var remoteSwiMac = checkedVal;
			console.log("##" + remoteSwiMac);
			$.ajax({
				url : "${PATH}/pc/seting/remoteSwiMac.do",
				data : {
					"containerId" : containerId,
					"deviceId" : deviceId,
					"remoteSwiMac" : remoteSwiMac,
					"chillerType" : chillerType
				},
				type : "get",
				success : function(result) {

					var field = result.state;
					if (field == '0') {
						var num = Math.random();
						layer.msg(result.message);
						return;
					}
					layer.msg(result.message);
				}
			});
		}
		//新风门开关机
		id7.onclick = function() {
			time(id1, id2, id3, id4, id5, id6, id7);
			getVal_02();
			var chillerType = $("#chiller6").text();
			var remoteXFSwiMac = checkedVal_02;
			var setCfm = $("#CFM").val();
			console.log("##" + remoteXFSwiMac);
			$.ajax({
				url : "${PATH}/pc/seting/remoteXFSwiMac.do",
				data : {
					"containerId" : containerId,
					"deviceId" : deviceId,
					"remoteXFSwiMac" : remoteXFSwiMac,
					"chillerType" : chillerType,
					"setCfm":setCfm
				},
				type : "get",
				success : function(result) {

					var field = result.state;
					if (field == '0') {
						var num = Math.random();
						layer.msg(result.message);
						return;
					}
					layer.msg(result.message);
				}
			});
		}

		var containerId = document.location.href.split("containerId=")[1];
		var deviceId;

		//点击传感器返回主页面，并将当前冷藏箱id带回
		$(".j-sensor").on(
				"click",
				function() {
					window.location.href = "${PATH }/pc/index.jsp?containerId="
							+ containerId;
				});

		//1.页面加载完成后，直接去发送ajax请求，要到数据
		$(function() {
			$.ajax({
				url : "${PATH}/pc/seting/getOneBindtable.do",
				data : {
					"containerId" : containerId
				},
				type : "get",
				success : function(result) {
					//解析显示绑定表单条记录信息
					build_bd_one(result);
				}
			});

		});
		//解析显示绑定表单条记录信息
		function build_bd_one(result) {
			var addHtml = $("#ccdatas_table").empty();
			var map = result.extend.map;
			deviceId = map.ccdata.deviceId;

			var htmlStr = "";

			htmlStr += "<tr>";

			htmlStr += "<td><div class='containerId '>"
					+ map.ccdata.containerId + "</div></td>";
			htmlStr += "<td><div class='leftDown--div2' id='deviceId'>"
					+ map.ccdata.deviceId + "</div></td>";
			htmlStr += "<td><div class='leftDown--div2'>" + map.trainId
					+ "</div></td>";
			htmlStr += "<td><div class='leftDown--div2'>"
					+ map.ccdata.nowTime + "</div></td>";

			htmlStr += "<td><div class='leftDown--div2'></div></td>";
			htmlStr += "<td><div class='leftDown--div2'>" + map.carGoType
					+ "</div></td>";
			htmlStr += "<td><div class='leftDown--div2'>" + map.stationName
					+ "</div></td>";
			htmlStr += "<td><div class='leftDown--div2'>" + map.yardName
					+ "</div></td>";
			htmlStr += "<td><div class='leftDown--div2'>" + map.routeType
					+ "</div></td>";
			if(map.backWindTemp != null){
				htmlStr += "<td><div class='leftDown--div2'>" + map.backWindTemp
						+ "</div></td>";
			}else{
				htmlStr += "<td><div class='leftDown--div2'>" + ""
					+ "</div></td>";
			}
			if(map.oilLevel != null){
				htmlStr += "<td><div class='leftDown--div2'>" + map.oilLevel
						+ "</div></td>";
			}else{
				htmlStr += "<td><div class='leftDown--div2'>" + ""
					+ "</div></td>";
			}
			if(map.setTemp != null){
				htmlStr += "<td><div class='leftDown--div2'>" + map.setTemp
						+ "</div></td>";
			}else{
				htmlStr += "<td><div class='leftDown--div2'>" + ""
					+ "</div></td>";
			}
			/* htmlStr += "<td><div class='leftDown--div2'>"
					+ item.ccdata.refSwiState
					+ "</div></td>";
			htmlStr += "<td><div class='leftDown--div2'>"
					+ item.ccdata.workMode
					+ "</div></td>"; */
			htmlStr += "</tr>";

			addHtml.append(htmlStr);
		}

		/* 	 //1.页面加载完成后，直接去发送ajax请求，要到containerId
		 $(function(){
		 var username = ${user.username};
		 $.ajax({
		 url:"${PATH}/pc/seting/showContainer.do",
		 data:{"username":username},
		 type:"get",
		 success:function(result){
		 //解析显示绑定表单条记录信息
		 console.log(result);
		 //show_containerId(result);
		 }
		 });
		
		 });
		 //解析显示绑定表单条记录信息
		 function show_containerId(result){
		 var addHtml=$("#ccdatas_table").empty();
		 var map=result.extend.map;
		 // deviceId=map.ccdata.deviceId;
		 containerIdList = map.containerId;
		 var htmlStr="";
		
		
		 htmlStr +="<tr>";
		
		 htmlStr += "<td><div class='containerId '>"+map.containerId+"</div></td>"; 
		
		 addHtml.append(htmlStr);
		 }
		 */
		//显示冷机类型
		function showchiller() {
			$.ajax({
				url : "${PATH}/pc/seting/showContainerchillerType.do",
				data : {
					"containerId" : containerId
				},
				type : "get",
				success : function(result) {
					/* 				data = eval("(" + result + ")"); */
					/* alert(result.chillerType); */
					/* alert(result.chillerType); */
					$('#chiller').html(result.chillerType);
				}
			});
			$.ajax({
				url : "${PATH}/pc/seting/showCommandSetValue.do",
				data : {
					"containerId" : containerId,
					"commandType" : 'temSet'
				},
				type : "get",
				success : function(result) {
					$('#temp').val(result);
				}
			});
			
		}
		//显示冷机类型
		function showchiller1() {
			$.ajax({
				url : "${PATH}/pc/seting/showContainerchillerType.do",
				data : {
					"containerId" : containerId
				},
				type : "get",
				success : function(result) {
					$('#chiller1').html(result.chillerType);
				}
			});
		}
		//显示冷机类型
		function showchiller2() {
			$.ajax({
				url : "${PATH}/pc/seting/showContainerchillerType.do",
				data : {
					"containerId" : containerId
				},
				type : "get",
				success : function(result) {
					$('#chiller2').html(result.chillerType);
				}
			});
			
			
		}
		//显示冷机类型
		function showchiller3() {
			$.ajax({
				url : "${PATH}/pc/seting/showContainerchillerType.do",
				data : {
					"containerId" : containerId
				},
				type : "get",
				success : function(result) {
					$('#chiller3').html(result.chillerType);
				}
			});
		}
		//显示冷机类型
		function showchiller4() {
			$.ajax({
				url : "${PATH}/pc/seting/showContainerchillerType.do",
				data : {
					"containerId" : containerId
				},
				type : "get",
				success : function(result) {
					$('#chiller4').html(result.chillerType);
				}
			});

			$.ajax({
				url : "${PATH}/pc/seting/showCommandSetValue.do",
				data : {
					"containerId" : containerId,
					"commandType" : 'refRunMode'
				},
				type : "get",
				success : function(result) {
					if (result == 'continuous') {
						$("#refRunMode_01").attr("checked", "checked");
					}else if(result == 'auto'){
						$("#refRunMode_02").attr("checked", "checked");
					}else{
						$("#refRunMode_01").attr("checked", false);
						$("#refRunMode_02").attr("checked", false);
					}
				}
			});
		}
		//显示冷机类型
		function showchiller5() {
			$.ajax({
				url : "${PATH}/pc/seting/showContainerchillerType.do",
				data : {
					"containerId" : containerId
				},
				type : "get",
				success : function(result) {
					$('#chiller5').html(result.chillerType);
				}
			});
			$.ajax({
				url : "${PATH}/pc/seting/showCommandSetValue.do",
				data : {
					"containerId" : containerId,
					"commandType" : 'remoteSwiMac'
				},
				type : "get",
				success : function(result) {
					if (result == 'on') {
						$("#remoteSwiMac_01").attr("checked", "checked");
					}else if(result == 'off'){
						$("#remoteSwiMac_02").attr("checked", "checked");
					}else{
						$("#remoteSwiMac_01").attr("checked", false);
						$("#remoteSwiMac_02").attr("checked", false);
					}
				}
			});
		}
		//显示冷机类型
		function showchiller6() {
			$.ajax({
				url : "${PATH}/pc/seting/showContainerchillerType.do",
				data : {
					"containerId" : containerId
				},
				type : "get",
				success : function(result) {
					$('#chiller6').html(result.chillerType);
				}
			});
			$.ajax({
				url : "${PATH}/pc/seting/showCommandSetValue.do",
				data : {
					"containerId" : containerId,
					"commandType" : 'remoteXFSwiMac'
				},
				type : "get",
				success : function(result) {
					if (result == 'on') {
						$("#remoteXFSwiMac_01").attr("checked", "checked");
					}else if(result == 'off'){
						$("#remoteXFSwiMac_02").attr("checked", "checked");
					}else{
						$("#remoteXFSwiMac_01").attr("checked", false);
						$("#remoteXFSwiMac_02").attr("checked", false);
					}
				}
			});
		}
		/* 温度设定 */
		/* function setTemp() {
			time(id1, id2, id3, id4, id5, id6);
			var setTemp = $("#temp").val();

			$.ajax({
				url : "${PATH}/pc/seting/setTemp.do",
				data : {
					"containerId" : containerId,
					"deviceId" : deviceId,
					"setTemp" : setTemp
				},
				type : "get",
				success : function(result) {

					var field = result.state;
					if (field == '0') {
						var num = Math.random();
						layer.msg(result.message);
						return;
					}
					layer.msg(result.message);
				}
			});

		} */

		/* 设置除霜 */
		/* function bootDef() {
			var bootDef;
			if ($("#bootDef").is(':checked')) {
				bootDef = "on";
			} else {
				bootDef = "off";
			}
			$.ajax({
				url : "${PATH}/pc/seting/bootDef.do",
				data : {
					"containerId" : containerId,
					"deviceId" : deviceId,
					"bootDef" : bootDef
				},
				type : "get",
				success : function(result) {

					var field = result.state;
					if (field == '0') {
						var num = Math.random();
						layer.msg(result.message);
						return;
					}
					layer.msg(result.message);
				}
			});

		} */
		/* 设置告警 */
		/* function setClearAlert() {
			var clearAlert;
			if ($("#clearAlert").is(':checked')) {
				clearAlert = "on";
			} else {
				clearAlert = "off";
			}
			$.ajax({
				url : "${PATH}/pc/seting/clearAlert.do",
				data : {
					"containerId" : containerId,
					"deviceId" : deviceId,
					"clearAlert" : clearAlert
				},
				type : "get",
				success : function(result) {

					var field = result.state;
					if (field == '0') {
						var num = Math.random();
						layer.msg(result.message);
						return;
					}
					layer.msg(result.message);
				}
			});

		} */

		/* 设置自检 */
		/* function setSelfCheck() {
			var selfCheck;
			if ($("#selfCheck").is(':checked')) {
				selfCheck = "on";
			} else {
				selfCheck = "off";
			}
			$.ajax({
				url : "${PATH}/pc/seting/selfCheck.do",
				data : {
					"containerId" : containerId,
					"deviceId" : deviceId,
					"selfCheck" : selfCheck
				},
				type : "get",
				success : function(result) {

					var field = result.state;
					if (field == '0') {
						var num = Math.random();
						layer.msg(result.message);
						return;
					}
					layer.msg(result.message);
				}
			});

		} */

		/* 设置开关机 */
		//（1）radio选定操作
		var oRadio = document.getElementsByName("remoteSwiMac");
		hasValue = false,//定义变量
		checkedVal = "";
		function getVal() { //获取radio选中值函数
			for (i = 0; i < oRadio.length; i++) {//循环数组
				if (oRadio[i].checked) {//判断当前项是否被选中
					//已选中的操作，获取选中的值
					hasValue = true;
					checkedVal = oRadio[i].value;
					//return checkedVal;
				} else {
					hasValue = false;
				}
			}
		}
		//（2）开关
		/* 	function setRemoteSwiMac() {
				getVal();
				var remoteSwiMac = checkedVal;
				console.log("##" + remoteSwiMac);
				$.ajax({
					url : "${PATH}/pc/seting/remoteSwiMac.do",
					data : {
						"containerId" : containerId,
						"deviceId" : deviceId,
						"remoteSwiMac" : remoteSwiMac
					},
					type : "get",
					success : function(result) {

						var field = result.state;
						if (field == '0') {
							var num = Math.random();
							layer.msg(result.message);
							return;
						}
						layer.msg(result.message);
					}
				});

			} */

		/* 设置冷机运行模式 */
		//（1）radio选定操作
		var oRadio_01 = document.getElementsByName("refRunMode");
		hasValue_01 = false,//定义变量
		checkedVal_01 = "";
		function getVal_01() { //获取radio选中值函数
			for (i = 0; i < oRadio_01.length; i++) {//循环数组
				if (oRadio_01[i].checked) {//判断当前项是否被选中
					//已选中的操作，获取选中的值
					hasValue_01 = true;
					checkedVal_01 = oRadio_01[i].value;
					//return checkedVal;
				} else {
					hasValue = false;
				}
			}
		}
		//（2）运行模式 
		/* function setRefRunMode() {
			getVal_01();
			hasValue = false,//定义变量
			refRunMode = checkedVal_01;

			console.log("$$" + refRunMode);
			$.ajax({
				url : "${PATH}/pc/seting/refRunMode.do",
				data : {
					"containerId" : containerId,
					"deviceId" : deviceId,
					"refRunMode" : refRunMode
				},
				type : "get",
				success : function(result) {

					var field = result.state;
					if (field == '0') {
						var num = Math.random();
						layer.msg(result.message);
						return;
					}
					layer.msg(result.message);
				}
			});

		} */
		/* 设置新风门开关机 */
		//（2）radio选定操作
		var oRadio_02 = document.getElementsByName("remoteXFSwiMac");
		hasValue_02 = false,//定义变量
		checkedVal_02 = "";
		function getVal_02() { //获取radio选中值函数
			for (i = 0; i < oRadio_02.length; i++) {//循环数组
				if (oRadio_02[i].checked) {//判断当前项是否被选中
					//已选中的操作，获取选中的值	
					hasValue_02 = true;
					checkedVal_02 = oRadio_02[i].value;
					//return checkedVal;
				} else {
					hasValue = false;
				}
			}
		}
	</script>


</body>
</html>