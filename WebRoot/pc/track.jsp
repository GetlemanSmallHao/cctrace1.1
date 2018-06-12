<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>轨迹页面</title>
<%
	pageContext.setAttribute("PATH", request.getContextPath());
%>
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/cctrace1.0
 -->
<link rel="stylesheet" href="${PATH}/css/laydate.css" />
<link rel="stylesheet" href="${PATH}/css/index.css" />
</head>
<body>
	<!-- 导航 -->
    <jsp:include page="header.jsp" flush="true"></jsp:include>
	<main class="main">
		<!--左上角地图-->
		<div class="con__container__map bindEvent clearfix">
			<div class="map__wrap">
				<header class="con__header">
					<ul>
						<li class="con__header--active con__header_none">班列</li>
					</ul>
					<!-- <a class="con__header--refresh" onclick="ajaxRefresh()"
						href="javascript:;"> <i></i> 刷新 -->
					</a>
				</header>
				<div class="conMap__track__time">
					开始时间： <input class="laydate-icon" id="startTime" name="startTime"
						 style="background-color: #E0FFFF" />
					 结束时间： <input class="laydate-icon" id="endTime" name="endTime"
					 	style="background-color: #E0FFFF" />
				</div>
				
				<main class="con__main con__main--track" id="xhrContainer">
				<span>
					&nbsp;&nbsp;&nbsp;查询冷藏箱<input type="text" id="containerId5"/>
					<input type="submit" value="查询" onclick="search();"/>
				</span>
					<form id="data" name="data" method="post" action=""
						onsubmit="return checkIndex()" style="height: -moz-calc(100% - 28px); height: -webkit-calc(100% - 28px); height: calc(100% - 28px);">

						<!-- <a onclick="partRefresh1()" type="button"
							href="javascript:void(0)">查询</a><br> -->
						<!-- <select name="routeType" id="routeType" style="background:#E0FFFF">
                    	<option value="" selected="selected">路线类型</option>
                    	<option value="0">去程</option>
                    	<option value="1">回程</option>				
                   	</select>	 -->
						<div class="con__main__table" style="height:100%;">
							<table>
								<thead>
									<tr>
										<td><div class="leftDown--div1">序号</div></td>
										<td><div class="">集装箱编号</div></td>
										<td><div class="">设备编号</div></td>
										<td><div class="">绑定时间（receivetime）</div></td>
										<td><div class="">班列ID</div></td>
										<!-- <td><div class="">上次已知位置</div></td> -->
										<!--  <td><div class="">冷机告警</div></td>
					        <td><div class="">货物类型</div></td>
					        <td><div class="">下货站</div></td>
					        <td><div class="">场站名称</div></td>
					        <td><div class="">上次报告时间</div></td> -->
									</tr>
								</thead>
								<tbody id="showContainersInTrack">
									<c:forEach var="bindTable" items="${bindTables}"
										varStatus="status">
										<tr>
											<td><div class="leftDown--div1">${status.index+1}</div></td>
											<td><div class="leftDown--div2">${bindTable.containerId}</div></td>
											<td><div class="leftDown--div3">${bindTable.deviceId}</div></td>
											<td><div class="leftDown--div3">${bindTable.bindTime}</div></td>
											<td><div class="leftDown--div3">${bindTable.trainId}</div></td>
											<td><div class="leftDown--div3"></div></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</form>
					<%-- <img style="display:block;" class="con__loading"
						src="${PATH}/img/loading.gif" alt="" /> --%>
				</main>
			</div>
		</div>
		<!--右上角地图信息-->
		<div class="con__map__map bindEvent clearfix">
			<div class="map__wrap">
				<header class="con__header">
					<ul>
				<!-- 		<li>班列</li>
						<li>定位</li> -->
						<li class="con__header--active"  style="background:none;">轨迹</li>
					<!-- 	<li>围栏</li> -->
					</ul>
				</header>
				<main class="con__main">
					<div id="map"></div>
				</main>
			</div>
		</div>
	</main>
	<script>
			function initMap() {
				var map = new google.maps.Map(document.getElementById('map'), {
					zoom : 4,
					center : {
						lat : 43.19,
						lng : 76.55
					}
				//mapTypeId: 'terrain'
				});
			}

	</script>
	<script src="http://maps.google.cn/maps/api/js?key=AIzaSyBncQ86ns5F-I5xF5_dE3Gf2_EauuXQXJA&sensor=false&libraries=places" type="text/javascript"></script>
	<script src="${PATH}/js/jquery-3.2.1.js"></script>
	<script src="${PATH}/js/map.js"></script>
	<script src="${PATH}/js/laydate.js"></script>
	<script>
		var mydateInputStart = document.getElementById("startTime");
		var mydateInputEnd = document.getElementById("endTime");
		var start = {
			elem : '#startTime',
			format : 'YYYY-MM-DD hh:mm:ss',
			min : '2014-01-01 00:00:00', //设定最小日期为当前日期
			max : laydate.now(), //最大日期
			istime : true,
			istoday : true,
			choose : function(datas) {
				end.min = datas; //开始日选好后，重置结束日的最小日期
				end.start = datas;//将结束日的初始值设定为开始日
			}
		};
		var end = {
			elem : '#endTime',
			format : 'YYYY-MM-DD hh:mm:ss',
			min : '2014-01-01 00:00:00',
			max : laydate.now(),
			istime : true,
			istoday : true,
			choose : function(datas) {
				start.max = datas; //结束日选好后，重置开始日的最大日期
			}
		};
		laydate(start);
		laydate(end);
		var now = new Date();
		var selectTime = now.getFullYear()+"-"+((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+now.getDate()+" "+(now.getHours()<10?"0":"")+now.getHours()+":"+(now.getMinutes()<10?"0":"")+now.getMinutes()+":"+(now.getSeconds()<10?"0":"")+now.getSeconds();
		var selectTime1 = now.getFullYear()+"-"+((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+now.getDate()+" "+"00:00:00";
		mydateInputStart.value=selectTime1;
		mydateInputEnd.value=selectTime;
		
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
				url : "${PATH}/pc/bindtable/findDevicesLikeyInLocation.do",
				data : {
					"containerId" : containerId7
				},
				type : "post",
				success : function(result) {
					$("#showContainersInTrack").html(result);
				}
			});
	}
	</script>
	<script type="text/javascript">
		var PATH = "${PATH}";

		//点击table下tr点击选中样式
		function getElementsByClass(classnames) {
			var classobj = newArray();
			var classint = 0;
			var tags = document.getElementsByTagName("*");
			//console.log("aaaa");
			console.log(tags);
			for ( var i in tags) {
				if (tags[i].nodeType == 1) {
					if (tags[i].getAttribute("class") == classnames) {
						classobj[classint] = tags[i];
						classint++;
					}
				}
			}
			return classobj;
		}
		if (document.getElementsByClassName) {
			//console.log("bbbbbb");
			document.getElementsByClassName("con__main")[0].onclick = function(
					ev) {
				var ev = ev || window.event;
				var target = ev.target || ev.srcElement;
				if (target.nodeName.toLowerCase().parentElement == 'tbody'
						|| target.parentElement.parentElement.nodeName
								.toLowerCase() == "tbody"
						|| target.parentElement.parentElement.parentElement.nodeName
								.toLowerCase() == "tbody") {
					//if(target.nodeName.toLowerCase() == 'tr' || target.parentElement.nodeName.toLowerCase() == "tr" || target.parentElement.parentElement.nodeName.toLowerCase() == "tr"){
					//  console.log("aaaaa");
					//}
					var tr;
					if (target.nodeName.toLowerCase() == 'tr') {
						tr = target;
					} else if (target.parentElement.nodeName.toLowerCase() == "tr") {
						tr = target.parentElement;
					} else if (target.parentElement.parentElement.nodeName
							.toLowerCase() == "tr") {
						tr = target.parentElement.parentElement;
					}
					tr.setAttribute("class", " tr__active");
					var cId = tr.children[1].children[0].innerHTML;
					var startTime = document.getElementById("startTime").value;
					var endTime = document.getElementById("endTime").value;
					//ajax异步请求
					var xmlHttpReg = null;
					if (window.ActiveXObject) {
						xmlHttpReg = new ActiveXObject("Microsoft.XMLHTTP");
					} else if (window.XMLHttpRequest) {
						xmlHttpReg = new XMLHttpRequest();
					}
					if (xmlHttpReg != null) {
						xmlHttpReg
								.open(
										"get",
										PATH
												+ "/pc/track/getTrackDatasByContainerId.do?containerId="
												+ cId + "&startTime="
												+ startTime + "&endTime="
												+ endTime, true);
						xmlHttpReg.send(null);
						xmlHttpReg.onreadystatechange = doResult;
					}
					/* console.log(xmlHttpReg.responseText); */
					function doResult() {
						if (xmlHttpReg.readyState == 4) {
							if (xmlHttpReg.status == 200) {
								console.dir(xmlHttpReg.responseText);
								var result = xmlHttpReg.responseText;
								var traceDatas = JSON.parse(result);
								//alert(traceDatas[0].lat);
								//var myTrip = new Array();
								data = traceDatas.concat();
								var obj = {};
								var latArray = [];
								for (var i = 0; i < data.length; i++) {
									obj.lat = data[i].lat;
									obj.lng = data[i].lon;
									latArray.push(obj);
									console.log(typeof (obj.lat));
									console.log(obj);
									obj = {};
								}
								//console.log("first"+latArray[0].lat);
								//console.log("last"+latArray[latArray.length-1]);
								var map = new google.maps.Map(document.getElementById("map"),
								{ 	center:{lat:34.5,
											lng:118},
								 zoom:4,
								 gestureHandling: 'greedy',
								mapTypeId: 'terrain'
								});
								var start_image = PATH+"/img/start_image.png";
								var end_image = PATH+"/img/end_image.png";
								var beachMarker1 = new google.maps.Marker({
									position: latArray[0],
									map: map,
									icon: start_image
								});
								var beachMarker2 = new google.maps.Marker({
									position: latArray[latArray.length-1],
									map: map,
									icon: end_image
								});
								
								var flightPlanCoordinates = latArray;
								var flightPath = new google.maps.Polyline({
									path : flightPlanCoordinates,
									geodesic : true,
									strokeColor : '#000000',
									strokeOpacity : 1.0,
									strokeWeight : 2
								})

								flightPath.setMap(map);
								

								
								
								/* var marker = new Array();
								var kkInfo="";
								var infowindow = new Array();
								for(var i = 1; i<latArray.length;i++){
									marker[i] =  new google.maps.Marker({
								    position: latArray[i]
								});
									infowindow[i] = new google.maps.InfoWindow({content: "北京市天安门" }); //创建一个InfoWindow
									//infowindow[i].open(map, marker[i]); //把这个infoWindow绑定在选定的marker上面
									//infowindows.push(infowindow);
								}
								for(var i = 100; i<latArray.length-50;i++){
									 marker[i+80].setMap(map);
									//使用谷歌地图定义的事件，给这个marker添加点击事件
									kkInfo=kkInfo+
									"google.maps.event.addListener(marker["+i+80+"],'click',function(){infowindow["+i+80+"].open(map,marker["+i+80+"]);});"
								} */
								
								
								
								
								
								/* var marker = new google.maps.Marker({
								    map: map,
								    position: latArray[2000]
								    //position: new google.maps.LatLng(34, 113)
								});
								var infowindow = new google.maps.InfoWindow({content: "北京市天安门" }); //创建一个InfoWindow
								infowindow.open(map, marker); //把这个infoWindow绑定在选定的marker上面
								//使用谷歌地图定义的事件，给这个marker添加点击事件
								google.maps.event.addListener(marker, "mouseout", function(){
								    //infowindow.open(map,marker);
								    console.log(111);
								}); */


						/* 		var lineSymbol = {
									path : google.maps.SymbolPath.CIRCLE,
									scale : 8,
									strokeColor : '#393'
								}; */
								
						/* 		var line = new google.maps.Polyline({
									path : latArray,
									icons : [ {
										icon : lineSymbol,
										offset : '100%'
									} ],
									map : map
								}); */

								//animateCircle(line);
							}
						/* 	function animateCircle(line) {
								var count = 0;
								window.setInterval(function() {
									count = (count + 1) % 200;
									var icons = line.get('icons');
									icons[0].offset = (count / 2) + '%';
									line.set('icons', icons);
								}, 20);
							} */
							
							//alert(aaa[0].lat+" , "+aaa[0].lon);
							/* 	console.log(aaa.extend.map);
							lon = aaa.extend.map.lon;
							lat = aaa.extend.map.lat;
							console.log("1111");
							console.log(lon);
							cccc();
							initialize(); */

						}
					}
				}
				//清除除当前tr外的tr样式
				var obox = tr.parentNode;//当前tr的父
				var trs = obox.children;//tbody的孩子tr[]
				for (var i = 0; i < trs.length; i++) {
					if (trs[i] != tr) {//不等于当前tr的删除class
						var classVal;
						if (trs[i].getAttribute("class")) {
							classVal = trs[i].getAttribute("class").replace(
									/\s*tr__active/g, "");
							trs[i].setAttribute("class", classVal);
						}
					}
				}
			};

		};
		google.maps.event.addDomListener(window, 'load', initMap);
		//eval(kkInfo);
	</script>

</body>
</html>