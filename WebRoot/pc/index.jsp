<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.cctrace.entity.User"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>陆港冷链温控系统</title>

<%
	pageContext.setAttribute("PATH", request.getContextPath());
	if (request.getSession().getAttribute("user") == null) {
		request.getRequestDispatcher("/pc/login.jsp").forward(request,
				response);
	}
	User user = (User) request.getSession().getAttribute("user");
	String username = user.getUsername();
	String role = user.getRole();
	int role1 = role.equalsIgnoreCase("common") ? 1 : 0;
%>
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/cctrace1.0
 -->
<link rel="stylesheet" href="${PATH}/css/laydate.css" />
<script src="http://maps.google.cn/maps/api/js?key=AIzaSyBncQ86ns5F-I5xF5_dE3Gf2_EauuXQXJA&sensor=false&libraries=places" type="text/javascript"></script>
<script type="text/javascript">
	var path = "${PATH}";
</script>
<link rel="stylesheet" href="${PATH}/css/index.css" />
<style>
	/* 传感器部分表格宽度样式 */
	 .c-sensor1{
	 	width:28px;
	 }
	 .c-sensor2{
	 	width:90px;
	 }
	 .c-sensor3{
	 	width:64px;
	 }
	 .c-sensor4{
	 	width:62px;
	 }
	 .c-sensor5{
	 	width:33px;
	 }
	 /* 关机时表格行样式*/
	 .c-off{
	 	color:gray;background-color: #DDDDDD;
	 }
	 /* 排序小三角 */
	 .j-sort1,.j-sort2,.j-sort3,.j-sort4,.j-sort5,.j-sort6,.j-sort7,.j-sort8,.j-sort9,.j-sort9,.j-sort10,.j-sort11{
	 	position:relative;
	 }
	 .xsj{
	 	position:absolute;right:8px;
	 }
	 .xsjUp{
		top:2px;display:inline-block;width:0;height:0;border:5px solid #fff;
	 	border-top-color:transparent;border-left-color:transparent;border-right-color:transparent;
	 }
	 .xsjDown{
	 	top:7px;display:inline-block;width:0;height:0;border:5px solid #fff;
		border-bottom-color:transparent;border-left-color:transparent;border-right-color:transparent;
	 }
	 /* 分为四个模块的结构样式 （定位、冷藏箱、告警、传感器）*/
	 .con__location{float:left;width:300px;height:60%;border: 3px solid transparent;	
	 }
	 .location__wrap{
	 	width: 100%;height: 100%;border: 2px solid #c3c3c3;border-radius: 3px;
	 }
	 .con__container{
	 	/* -webkit-width:calc(100% - 300px);-moz-width:calc(100% - 300px);width:calc(100% - 300px); */
	 	-webkit-width:100%;-moz-width:100%;width:100%; }
	 .con__warning{width:300px;height:40%;display:none;}
	 .con__sensor{
	 	/* -webkit-width:calc(100% - 300px);-moz-width:calc(100% - 300px);width:calc(100% - 300px); */
	 	-webkit-width:100%;-moz-width:100%;width:100%; }
</style>
</head>

<body>
	<jsp:include page="header.jsp" flush="true"></jsp:include>
	<main class="main"> <!--<div class="con__map bindEvent clearfix">地图</div>-->
	<!-- 冷藏箱列表模块 -->
	<div class="con__container clearfix">
		<div class="container__wrap">
			<header class="con__header">
				<ul>
					<li class="con__header--active con__header_none">班列</li>
					<li><a href="${PATH}/pc/ExportExcel/findBindTableJsonExcel.do" class="red_print">导出报表</a></li>
				</ul>
			</header>
		
		
			<main class="con__main amendBind"> <!-- 绑定表列表 -->
			<div>
				<div class="con__main--search">
					冷藏箱编号：<input type="text" id="containerId2" autocomplete="off" name="containerId" />
					<input type="button" id="search" value="查询" style="cursor:pointer;"/>
				</div>
				<div class="con__main__table" style="overflow:hidden;">
					<table class="j-tableTitle" style="border: none;">
						<thead>
							<tr>
								<td><div style="width:27px;">序号</div></td>
								<td><div style="width:33px;" class="main__caozuo">操作</div></td>
								<td><div class="j-sort1" data-status="up" style="width:110px;">冷藏箱编号<span class="xsj xsjUp"></span></div></td>
								<td><div class="j-sort7" data-status="up" style="width:115px;">GPS接收时间<span class="xsj xsjUp"></span></div></td>
								<td><div class="j-sort10" data-status="up" style="width:150px;">地理位置<span class="xsj xsjUp"></span></div></td>
								<td><div class="j-sort9" data-status="up" style="width:70px;">冷机告警<span class="xsj xsjUp"></span></div></td>
								<td><div class="j-sort8" data-status="up" style="width:100px;">备注<span class="xsj xsjUp"></span></div></td>
								<td><div style="width:100px;">货物类型</div></td>
								<td><div style="width:50px;">油位</div></td>
								<td><div class="j-sort3" data-status="up" style="width:70px;">设定温度<span class="xsj xsjUp"></span></div></td>
								<td><div class="j-sort4" data-status="up" style="width:70px;">回风温度<span class="xsj xsjUp"></span></div></td>
								<td><div class="j-sort5" data-status="up" style="width:95px;">冷机电瓶电压<span class="xsj xsjUp"></span></div></td>
								<td><div class="j-sort6" data-status="up" style="width:70px;">GPS电量<span class="xsj xsjUp"></span></div></td>
								<td><div style="width:64px;">开关机状态</div></td>
								<td><div class="j-sort11" data-status="up"  style="width:70px;">通讯状态<span class="xsj xsjUp"></span></div></td>
								<td><div style="width:125px;">设备编号</div></td>
								<td><div class="j-sort2" data-status="up" style="width:115px;">系统时间<span class="xsj xsjUp"></span></div></td>
								<td><div style="width:83px;">冷机类型</div></td>
								<!-- <td><div style="width:115px;">班列ID</div></td> -->
								<td><div style="width:100px;">下货站</div></td>
								<td><div style="width:100px;">场站名称</div></td>
								<!-- <td><select id = "order" onchange="changeOrder();">
									<option value="CIdASC">冷藏箱正序</option>
									<option value="CIdDESC">冷藏箱倒序</option>
								</select></td> -->
								<!-- <td><div style="width:52px;">路线类型</div></td> -->
							</tr>
						</thead>
					</table>	
					<table id="ccdatas_table" class="j-scrollTop" style="display:block; height: -moz-calc(100% - 20px); height: -webkit-calc(100% - 20px); height: calc(100% - 20px); border: none; overflow-y:auto;">	
						<tbody>

						</tbody>
					</table>
				</div>
			</div>
			<div class="con__modal">
				<div class="con__modal--wrap">
					<span> 
						<b style="display:inline-block; width:110px; font-weight:500;">集装箱编号：</b>
						<input type="text" id="containerId" name="containerId" readonly unselectable="on" />
					</span>
					<!-- <span>
						<b style="display:inline-block; width:110px; font-weight:500;">班列ID：</b>
						<input class="laydate-icon" id="trainId" /> 
					</span>  -->
					<span>
						<b style="display:inline-block; width:110px; font-weight:500;">冷机型号：</b>
						<input type="text" id="lcxModel" name="lcxModel" value="${item.ccdata.lcxModel }"/>
					</span>
					<span>
						<b style="display:inline-block; width:110px; font-weight:500;">货物类型：</b>
						<input type="text" id="carGoType" name="carGoType" />
					</span>
					<span>
						<b style="display:inline-block; width:110px; font-weight:500;">下货站：</b>
						<select id="theNextStation">

						</select>
					</span> 
					<span>
						<b style="display:inline-block; width:110px; font-weight:500;">场站名称：</b>
						<select id="yard">

						</select>
					</span>
					<span>
						<b style="display:inline-block; width:110px; font-weight:500;">备注信息：</b>
						<input type="text" id="remark" name="remark" />
					</span>
					<span class="con__modal--btn">
						<button onclick="bindtableUpdate()">保 存</button>
						<button class="con__modal--cancel rt">取 消</button>
					</span>
				</div>
			</div>
			



			</main>
		</div>
	</div>
	<!--<div class="con__sensor bindEvent clearfix"> 加bindEvent可以切换导航且可以异步加载功能-->
	<!-- 告警模块 -->
	<div class="con__warning clearfix">
		<div class="warning__wrap">
			<header class="con__header">
			<ul>
				<li data-con="${PATH}/data/warning.html" class="con__header--active" onclick="showgaojingxinxi()">告警提示</li>
				<li data-con="${PATH}/data/warning.html" class="con__header--active" onclick="showweilantongzhi()">围栏通知</li>
			</ul>

			</header>
			<main class="con__main"> <!-- 报警信息  -->
			
			<div id="gaojingxinxi">
				<table>
					<thead>
						<tr>
							<td><div style="padding-left:3px;" class="leftDown--div2">告警信息</div></td>
							<td><div style="padding-left:3px;" class="leftDown--div3">当前未查看记录数</div></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="background-color:transparent;">
								<div class="leftDown--div2">
									<a href="${PATH}/pc/alert/getRecentFourDaysAlerts.do">查看明细</a>
								</div>
							</td>
							<td><div class="leftDown--div3" id="countOfNRA"></div></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			<div id="weilantongzhi" style="display:none">
				<table>
					<thead>
						<tr>
							<td><div style="padding-left:3px;" class="leftDown--div2">围栏通知</div></td>
							<td><div style="padding-left:3px;" class="leftDown--div3">当前未查看记录数</div></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="background-color:transparent;">
								<div class="leftDown--div2">
									<a href="${PATH}/pc/alert/getRecentOneDaysGeoAlerts.do">查看明细</a>
								</div>
							</td>
							<td><div class="leftDown--div3" id="geoCount"></div></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			
			
			</main>
		</div>
	</div>
	<!-- 传感器模块 -->
	<div class="con__sensor clearfix">
		<div class="sensor__wrap">
			<header class="con__header">
				<ul class="sensor_ul">
					<li data-con="${PATH}/data/sensor.jsp" class="con__header--active" tab_id="1"
					style="color:#649893;">传感器</li>
					<li class="con__header--active" tab_id="2"><a href="#" id="j-setting">设置</a></li>
					<li class="con__header--active" tab_id="3"><a href="#" id="">告警信息</a></li>
					<li class="con__header--active" tab_id="4"><a href="#" id="">定位</a></li>
					
					<%--  <a href="${PATH}/pc/seting.jsp?containerId="><li class="con__header--active">设置</li></a> --%>
				</ul>
			</header>
			<!-- 传感器 -->
			<main class="con__main tab_main"> 
			<div style="position:relative;">
				<!--  <div class="con__main--search">
			    发射器：<input type="text"/>
			  </div> -->
				<!-- <div class="rotate90" style="height:100%;"> -->
				<div class="con__main__table" style="height:100%;">
					<div class="con__sensor__title">
				<!-- 	冷藏箱编号&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id = "showContainerID"/><br/> -->
					冷藏箱编号&nbsp;&nbsp;<span id = "showContainerID"/></span>
					系统时间&nbsp;&nbsp;<span id = "showOurccdataTime"></span>
						<table>
							<thead>
								<tr>
									<td><div style="width:28px;">序号</div></td>
									<td><div style="width:90px;">发射器</div></td>
									<td><div style="width:64px;">值</div></td>
									<td><div style="width:60px;">计量</div></td>
									<td><div style="width:33px;" class="setMenu__set">设置</div></td>
								</tr>
							</thead>
						</table>
					</div>	
					<!-- 存放正序还是倒序的标志 -->
					<input type="hidden" id="order5555" value="${order}">
					<table id="sensor_table" style="display:block; height: -moz-calc(100% - 36px); height: -webkit-calc(100% - 36px); height: calc(100% - 36px); border: none; overflow-y:auto;">	
						<tbody>
			
						</tbody>
					</table>
				</div>
				<div class="con__main__setmenu">
					<div id="setMenu1">
						<header>冷机电瓶电压设置</header>
						<main>

						<p>
							<span>发送警告上线：</span><input type="text" id="maxRefBatVol"
								maxlength="6" value="" />&nbsp;V(一位小数)
						</p>
						<p>
							<span>发送警告底线：</span><input type="text" id="minRefBatVol"
								maxlength="6" value="" />&nbsp;V(一位小数)
						</p>
						<!--  <p>
			          <span>相同警告时间间隔：</span><input type="text"/>&nbsp;分钟
			        </p>
			        <p>
			          <input type="checkbox"/>&nbsp;永不发送警告
			        </p> --> </main>
						<footer class="footer__btn">
						<button class="btn--save">删除发射器</button>
						<button class="btn--save" onclick="battery();">保存</button>
						</footer>
					</div>
					<div id="setMenu2">
						<header>回风温度设置</header>
						<main> <!--     <p>
			          <span>传感器开关：</span><select name="">
			            <option value="">停用</option>
			            <option value="">启用</option>
			          </select>
			        </p> -->
						<p>
							<span>最低温度范围：</span><input type="text" id="minBackWindTemp" />&nbsp;°C
						</p>
						<p>
							<span>最高温度范围：</span><input type="text" id="maxBackWindTemp" />&nbsp;°C
						</p>
						<!--  <p>
			          <span>相同警告时间间隔：</span><input type="text"/>&nbsp;分钟
			        </p>
			        <p>
			          <input type="checkbox"/>&nbsp;永不发送警告
			        </p> --> </main>
						<footer class="footer__btn">
						<button class="btn--save">删除发射器</button>
						<button class="btn--save" onclick="backWindTemp();">保存</button>
						</footer>
					</div>
					<!--  <div id="setMenu3">
			      <header>后门开关设置</header>
			      <main>
			        <p>
			          <span>传感器开关：</span><select name="" id="">
			          <option value="">停用</option>
			          <option value="">启用</option>
			        </select>
			        </p>
			        <p>
			          <input type="checkbox"/>&nbsp;永不发送警告
			        </p>
			      </main>
			      <footer class="footer__btn">
			        <button class="btn--save">删除发射器</button>
			        <button class="btn--save">保存</button>
			      </footer>
			    </div>
			    <div id="setMenu4">
			      <header>箱内湿度设置</header>
			      <main>
			        <p>
			          <span>传感器开关：</span><select name="" id="">
			          <option value="">停用</option>
			          <option value="">启用</option>
			        </select>
			        </p>
			        <p>
			          <span>最低湿度范围：</span><input type="text" id="minTankHum" />&nbsp;%
			        </p>
			        <p>
			          <span>最高湿度范围：</span><input type="text" id="maxTankHum" />&nbsp;%
			        </p>
			        <p>
			          <span>相同警告时间间隔：</span><input type="text"/>&nbsp;分钟
			        </p>
			        <p>
			          <input type="checkbox"/>&nbsp;永不发送警告
			        </p>
			      </main>
			      <footer class="footer__btn">
			        <button class="btn--save">删除发射器</button>
			        <button class="btn--save" onclick="tankHum();">保存</button>
			      </footer>
			    </div> -->
					<!--  <div id="setMenu3">
			      <header>油箱油位设置</header>
			      <main>
			        <p>
			          <span>油量波动：</span><input type="text" id="maxOilLevel" />&nbsp;%
			        </p>
			        <p>
			          <span>油量底线：</span><input type="text" id="minOilLevel" />&nbsp;%
			        </p> -->
					<!-- <p>
			          <span>相同警告时间间隔：</span><input type="text"/>&nbsp;分钟
			        </p>
			        <p>
			          <input type="checkbox"/>&nbsp;永不发送警告
			        </p> -->
					<!--      </main>
			      <footer class="footer__btn">
			        <button class="btn--save">删除发射器</button>
			        <button class="btn--save" onclick="oilLevel();">保存</button>
			      </footer>
			    </div>
			    <div id="setMenu6">
			      <header>gps电压设置</header>
			      <main>
			        <p>
			          <span>发送警告底线：</span><input type="text" maxlength="6" id="minGpsBatVol"/>&nbsp;V(一位小数)
			        </p>
			        <p>
			          <span>相同警告时间间隔：</span><input type="text"/>&nbsp;分钟
			        </p>
			        <p>
			          <input type="checkbox"/>&nbsp;永不发送警告
			        </p>
			      </main>
			      <footer class="footer__btn">
			        <button class="btn--save">删除发射器</button>
			        <button class="btn--save" onclick="gpsBatVol();">保存</button>
			      </footer>
			    </div> -->

					<div id="setMenu3">
						<header>环境温度设置</header>
						<main>
						
						<p>
							<span>最低环境温度：</span><input type="text" id="minEnviTemp">&nbsp;°C
						</p>
						<p>
							<span>最高环境温度：</span><input type="text" id="maxEnviTemp" />&nbsp;°C
						</p>
						<!--  <p>
			          <span>相同警告时间间隔：</span><input type="text"/>&nbsp;分钟
			        </p>
			        <p>
			          <input type="checkbox"/>&nbsp;永不发送警告
			        </p> --> </main>
						<footer class="footer__btn">
						<button class="btn--save">删除发射器</button>
						<button class="btn--save" onclick="enviTemp();">保存</button>
						</footer>
					</div>

					<div id="setMenu4">
						<header>出风温度设置</header>
						<main>
						<p>
							<span>最低出风温度：</span><input type="text" id="minChuWindTemp" />&nbsp;°C
						</p>
						<p>
							<span>最高出风温度：</span><input type="text" id="maxChuWindTemp" />&nbsp;°C
						</p>
						<!--  <p>
			          <span>相同警告时间间隔：</span><input type="text"/>&nbsp;分钟
			        </p>
			        <p>
			          <input type="checkbox"/>&nbsp;永不发送警告
			        </p> --> </main>
						<footer class="footer__btn">
						<button class="btn--save">删除发射器</button>
						<button class="btn--save" onclick="chuWindTemp();">保存</button>
						</footer>
					</div>

					<!--  <div id="setMenu5">
			      <header>中部温度设置</header>
			      <main>
			        <p>
			          <span>最低中部温度：</span><input type="text" id="minCenBoxTemp" />&nbsp;°C
			        </p>
			        <p>
			          <span>最大中部温度：</span><input type="text" id="maxCenBoxTemp" />&nbsp;°C
			        </p>
			        <p>
			          <span>相同警告时间间隔：</span><input type="text"/>&nbsp;分钟
			        </p>
			        <p>
			          <input type="checkbox"/>&nbsp;永不发送警告
			        </p>
			      </main>
			      <footer class="footer__btn">
			        <button class="btn--save">删除发射器</button>
			        <button class="btn--save" onclick="cenBoxTemp();">保存</button>
			      </footer>
			    </div> -->

					<!-- <div id="setMenu7">
			      <header>尾部箱温设置</header>
			      <main>
			        <p>
			          <span>最低尾部箱温：</span><input type="text" id="minTailBoxTemp" />&nbsp;°C
			        </p>
			        <p>
			          <span>最大尾部箱温：</span><input type="text" id="maxTailBoxTemp" />&nbsp;°C
			        </p>  -->
					<!--  <p>
			          <span>相同警告时间间隔：</span><input type="text"/>&nbsp;分钟
			        </p>
			        <p>
			          <input type="checkbox"/>&nbsp;永不发送警告
			        </p> -->
					<!--      </main>
			      <footer class="footer__btn">
			        <button class="btn--save">删除发射器</button>
			        <button class="btn--save" onclick="tailBoxTemp();">保存</button>
			      </footer>
			    </div> -->


					<!-- <div id="setMenu6">
						<header>油箱温度</header>
						<main>
						<p>
							<span>最低油箱温度：</span><input type="text" id="minoilTemp" />&nbsp;°C
						</p>
						<p>
							<span>最高油箱温度：</span><input type="text" id="maxoilTemp" />&nbsp;°C
						</p> -->
					<!-- <p>
			          <span>相同警告时间间隔：</span><input type="text"/>&nbsp;分钟
			        </p> -->
					<!--    <p>
			          <input type="checkbox"/>&nbsp;永不发送警告
			     <!--    </p> -->
			<!-- </main> -->
			<!-- 			<footer class="footer__btn">
						<button class="btn--save">删除发射器</button>
						<button class="btn--save" onclick="oilTemp();">保存</button>
						</footer>
					</div> -->

					<div id="setMenu7">
						<header>箱内湿度</header>
						<main>
						<p>
							<span>最低箱内湿度：</span><input type="text" id="minboxHum" />&nbsp;%
						</p>
						<p>
							<span>最高箱内湿度：</span><input type="text" id="maxboxHum" />&nbsp;%
						</p>
						<!--  <p>
					          <span>相同警告时间间隔：</span><input type="text"/>&nbsp;分钟
					        </p>
					        <p>
					          <input type="checkbox"/>&nbsp;永不发送警告
					        </p> --> </main>
						<footer class="footer__btn">
						<button class="btn--save">删除发射器</button>
						<button class="btn--save" onclick="boxHum();">保存</button>
						</footer>
					</div>
		
					<div id="setMenu8">
						<header>箱尾温度</header>
						<main>
						<p>
							<span>最低箱尾温度：</span><input type="text" id="mintailBoxTemp" />&nbsp;°C
						</p>
						<p>
							<span>最高箱尾温度：</span><input type="text" id="maxtailBoxTemp" />&nbsp;°C
						</p>
						<!--  <p>
					          <span>相同警告时间间隔：</span><input type="text"/>&nbsp;分钟
					        </p>
					        <p>
					          <input type="checkbox"/>&nbsp;永不发送警告
					        </p> --> </main>
						<footer class="footer__btn">
						<button class="btn--save">删除发射器</button>
						<button class="btn--save" onclick="tailBoxTemp();">保存</button>
						</footer>
					</div>
		
					<div id="setMenu9">
						<header>gps电量</header>
						<main>
						<p>
							<span>最低gps电量：</span><input type="text" id="mingpsPower" />&nbsp;V
						</p>
						<p>
							<span>最高gps电量：</span><input type="text" id="maxgpsPower" />&nbsp;V
						</p>
						<!--  <p>
					          <span>相同警告时间间隔：</span><input type="text"/>&nbsp;分钟
					        </p>
					        <p>
					          <input type="checkbox"/>&nbsp;永不发送警告
					        </p> --> </main>
						<footer class="footer__btn">
						<button class="btn--save">删除发射器</button>
						<button class="btn--save" onclick="gpsPower();">保存</button>
						</footer>
					</div>
		
		
					<div id="setMenu10">
						<header>油箱油位</header>
						<main>
						<p>
							<span>最低油箱油位：</span><input type="text" id="minoilLevel" />&nbsp;%
						</p>
						<p>
							<span>最高油箱油位：</span><input type="text" id="maxoilLevel" />&nbsp;%
						</p>
						<!--  <p>
					          <span>相同警告时间间隔：</span><input type="text"/>&nbsp;分钟
					        </p>
					        <p>
					          <input type="checkbox"/>&nbsp;永不发送警告
					        </p> --> </main>
						<footer class="footer__btn">
						<button class="btn--save">删除发射器</button>
						<button class="btn--save" onclick="oilLevel();">保存</button>
						</footer>
					</div>
		
					<div id="setMenu11">
						<header>后门开关设置</header>
						<main>
						<p>
							<span>传感器开关：</span> <select name="" id="door">
								<option value="off">停用</option>
								<option value="on">启用</option>
							</select>
						</p>
						</main>
						<footer class="footer__btn">
						<button class="btn--save">删除发射器</button>
						<!-- <button class="btn--save" onclick="backDoorStateFun();">保存</button> -->
						<button class="btn--save">保存</button>
						</footer>
					</div><%--
					<div id="setMenu12">
						<header>周期设置</header>
						<main>
						<p>							<span>请输入周期：</span><input type="text" id="cycleMinute" />&nbsp;（分钟1-10）
						</p>
						<!--  <p>
			          <span>相同警告时间间隔：</span><input type="text"/>&nbsp;分钟
			        </p>
			        <p>
			          <input type="checkbox"/>&nbsp;永不发送警告
			        </p> --> </main>
						<footer class="footer__btn">
						<button class="btn--save" onclick="setCycleMinute();">保存</button>
						</footer>
					</div>--%>
				</div>
			</main>
			<!-- 传感器结束 -->
			<main class="con__main tab_main" style="display:none;">bbb </main>
			<main class="con__main tab_main" style="display:none;">ccc </main>
			<main class="con__main tab_main" style="display:none;">
				<div class="con__location">
					<div class="location__wrap">
						<header class="con__header">
							<ul>
								<li class="con__header--active" style="background:none;">定位</li>
							</ul>
						</header>
						<main class="con__main">
							<div>
								<div id="googleMap" style="width:100%;height:100%;margin:0 auto;">           	
			   				    </div>
							</div>
						</main>
					</div>
				</div>
			</main>
			</div>
		</div>
	</div>
</main>

	<script src="${PATH}/js/jquery-3.2.1.js"></script>
	<script src="${PATH}/js/index.js"></script>
	<script src="${PATH}/js/layer_3.1.0/layer.js"></script>
	<script src="${PATH}/js/laydate.js"></script>
	<%-- <script src="${PATH}/js/layer/extend/layer.ext.js"></script> --%>
	<script type="text/javascript">
	<%-- $(document).ready(function(){
		var role1 =<%=role1%>;
		if(role1 == 1){
			$(".setMenu__btn").hide();
		}
});
	 --%>
			var init;//刷新冷藏箱列表定时器的容器
			var order;
			//显示警告信息
			$(function(){
				setInterval(function() {
					var now = new Date();
					var selectTime = now.getFullYear()+"-"+((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+now.getDate()+" "+now.getHours()+":"+(now.getMinutes()<10?"0":"")+now.getMinutes()+":"+now.getSeconds();
					/* var selectTime = nowTime.toString(); */
					//alert(selectTime);
					var username = '<%=username%>';
					$.ajax({
						url : "${PATH}/pc/alert/showAlertsInTwoTime.do",
						type : "get",
						data : {
							'username' : username,
							'selectTime' : selectTime
						},
						success : function(result) {
							var flag = result.state;
							if(flag == '0'){
								var htmlStr = build_alert_list(result.alerts);
								layer.open({
							      type:'1',//1是页面层
							      title: '告警预览',//标题
							      shadeClose:true,//点击遮罩层也关闭
							      id:'1',//同时只能有一个弹窗
							      scrollbar: false,//弹窗时浏览器不能滚动
							      area:['800px','500px'],//弹窗宽高
							      btn: ['查看详情', '取消'],
							      yes: function(index, layero){
								   	window.location.href = "/cctrace1.1/pc/alert/getRecentFourDaysAlerts.do";
								  },
								  btn2: function(index, layero){
								    //按钮【按钮二】的回调
								    
								    //return false 开启该代码可禁止点击该按钮关闭
								  },
							      //弹窗点击×的回调函数
							      /* cancel: function(index, layero){
							       layer.close(index);
							       },*/
							      content: htmlStr
							    });
							}
					    }
					});
				}, 18000000);
				//解析新的告警信息
				function build_alert_list(alerts){
					var htmlStr ="";
					htmlStr += '<main class="con__main" style="height:100%;">';
					htmlStr += '<div>';
					htmlStr += '<table style="width:100%;"> <thead> <tr>';
					htmlStr +=' <td><div class="">序号</div></td>';
					htmlStr += '<td><div class="">报警开始时间</div></td>';
					htmlStr += '<td><div class="">最近一次报警时间</div></td>';
					htmlStr += '<td><div class="">冷藏箱编号</div></td>';
					htmlStr += '<td><div class="">报警类型</div></td>';
					htmlStr += '<td><div class="">报警内容</div></td>';
					htmlStr += '<td><div class="">报警时的纬度</div></td>';
					htmlStr += '<td><div class="">报警时的经度</div></td>';
					htmlStr += '<td><div class="">读取状态</div>';
					htmlStr += '	</tr>';
					htmlStr += '</thead>';
					htmlStr += '<tbody class="alertBind">';
					$.each(alerts,function(index,item) {	             
					htmlStr += '<tr><td><div>'+(index+1)+'</div></td>';
					htmlStr += '<td><div>'+item.alertTime+'</div></td>';
					htmlStr += '<td><div>'+item.updateTime+'</div></td>';
					htmlStr += '<td><div>'+item.containerId+'</div></td>';
					htmlStr += '<td><div>'+item.alertType+'</div></td>';
					htmlStr += '<td><div>'+item.alertContent+'</div></td>';
					htmlStr += '<td><div>'+item.lat+'</div></td>';
					htmlStr += '<td><div>'+item.lon+'</div></td>';
					if(item.readed == "no"){
						htmlStr += '<td><div class="leftDown--div3">'+'未读'+'</div></td>';  
					}else{
						htmlStr += '<td><div class="leftDown--div3">'+'已读'+'</div></td>';  
					}
					htmlStr +=	'</tr>';
					});
					htmlStr +=	'</tbody>';
					htmlStr += '</table></div></main>';
					return htmlStr;
				}
			});
			
			
			//1、页面加载完成以后，直接去发送ajax请求,要到数据
			/* $(function() { */
			//围栏未查看告警数
			var countOfGeo = getCountOfNoReadedGeomessageBeforeDays();
			$("#geoCount").text(countOfGeo);
			
			
			//未查看警告记录数
			var countOfNRA = askCountOfNoReadedAlert();
			$("#countOfNRA").text(countOfNRA);
			/* 当从设置页面点击传感器跳转回来时，显示之前冷藏箱传感器信息，这个冷藏箱被选中置顶 */
			//var cId2 = document.location.href.split("containerId=")[1];
			//var cId2 = sessionStorage.lastClickCid
			/* ranking是第几次，只能为"first"为了只调用一次定时器，order拿到排序的参数（正序或倒序） */
			function ccdatasAjax(pn,ranking,order) {
				//var now = new Date();
				//var nowTimeshow = now.getFullYear()+"-"+((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+now.getDate()+" "+now.getHours()+":"+(now.getMinutes()<10?"0":"")+now.getMinutes()+":"+now.getSeconds();
				//alert("time-----"+nowTimeshow);
				if(pn == undefined){
					pn = 1;
				}
				orderStr = String(order);
				if(orderStr == "undefined" || orderStr=="" || orderStr == "null"){//如果传来的order不为空，就把其赋给order5555，如果没有传order值则拿页面中的排序值
					/* 页面第一次加载时如果oorderStr是空，则去order5555里拿到排序状态 */
					orderStr = $("#order5555").val();
				}
				/* 如果从order为空，从order5555里拿到的还是空时，则用不带order参的url */
				if(orderStr == "undefined" || orderStr=="" || orderStr == "null"){//如果不为空则带order参数
					var url = "${PATH}/pc/bindtable/findBindtableWithJson.do?pn="+pn;
				}else{
					var url = "${PATH}/pc/bindtable/findBindtableWithJson.do?pn="+pn+"&order="+orderStr;
				}
				$.ajax({
					url :url,
					data : null,
					type : "get",
					success : function(result) {
					//console.log(result);
						//解析显示冷藏箱数据
						build_ccdatas_table(result,pn,result.extend.totalSize,ranking,orderStr);
					}
				});
			}
			ccdatasAjax(1,"first");/* 页面加载调用 , 后面这个1标记是告诉函数这是第一次加载需调用定时器*/
			
			

			//console.log(containerTd);

			$.ajax({
				url : "${PATH}/pc/bindtable/findAllDc.do",
				data : "",
				type : "get",
				success : function(result) {
					////解析显示堆场列表
					build_Dc_list(result);
				}
			});
			$.ajax({
				url : "${PATH}/pc/bindtable/findAllXhz.do",
				data : "",
				type : "get",
				success : function(result) {
					//解析显示下货站列表
					build_Xhz_list(result);
				}
			});
			
			//回车事件绑定
		    $("#containerId2").on('keyup', function(event) {
		        if (event.keyCode == "13") {
		            //回车执行查询
		            $("#search").click();
		            //alert(111);
		        }
		    });
		    
		    
			$("#search").click(function() {
								var containerId = $("#containerId2").val();
								containerId = containerId.replace(/\s*/g, "");
								$.ajax({
											url : "${PATH}/pc/bindtable/findBindtableBycompanyIdAndcontainerIdLikely.do",
											data : {
												"containerId" : containerId
											},
											type : "get",
											dataType : "json",
											success : function(result) {
												var addHtml = $(
														"#ccdatas_table tbody")
														.empty();
												var ccdatas = result.extend.maps;
												var htmlStr = "";
													$.each(
																ccdatas,
																function(index,
																		item) {
																	/* if(item.refSwiState == "off"){
																		htmlStr += "<tr class='c-off'>";
																	}else{
																		htmlStr +="<tr>";
																	} */
																	htmlStr +="<tr>"
																	htmlStr += "<td><div style='width:27px;'>"
																			+ (index+1)
																			+ "</div></td> ";
																			
																	htmlStr += "<td><div style='width:33px;'><button class='main__amend'>修改</button> </div></td>";
																	htmlStr += "<td><div style='width:110px;' class='containerId '>"
																			+ item.ccdata.containerId
																			+ "</div></td>";
																	if(item.ourccdata.nowTime != null){
																		htmlStr += "<td><div style='width:115px;'>"
																				+ item.ourccdata.nowTime
																				+ "</div></td>";
																	}else{
																		htmlStr += "<td><div style='width:115px;'>"
																				+ ""
																				+ "</div></td>";
																	}
																	if(item.ourccdata.gpsPosition != null){
																		htmlStr += "<td><div style='width:150px;'>"
																				+ item.ourccdata.gpsPosition
																				+ "</div></td>";
																	}else{
																		htmlStr += "<td><div style='width:150px;'>"
																				+ ""
																				+ "</div></td>";
																	}
																	if(item.ifAlert == "have"){
																		htmlStr += "<td><div style='width:70px;'><font style='color: red;'>"
																				+ "是"
																				+ "</font></div></td>";
																	}else if(item.ifAlert == "no"){
																		htmlStr += "<td><div style='width:70px;'>"
																				+ "不"
																				+ "</div></td>";
																	}else{
																		htmlStr += "<td><div style='width:70px;'>"
																				+ ""
																				+ "</div></td>";
																	}
																	//备注
																	if(item.remark != null){
																		htmlStr += "<td><div style='width:100px;'>"
																				+ item.remark + "</div></td>";
																	}else{
																		htmlStr += "<td><div style='width:100px;'>"
																				+ "" + "</div></td>";
																	}
																	htmlStr += "<td><div style='width:100px;'>"
																			+ item.carGoType + "</div></td>";
																	
																	if(item.oilLevel != null){
																			htmlStr += "<td><div style='width:50px;'>"
																			+ item.oilLevel
																			+ "</div></td>";
																	}else{
																			htmlStr += "<td><div style='width:50px;'>"
																			+ ""
																			+ "</div></td>";
																	}
																	/* if(item.refSwiState == "off"){
																		htmlStr += "<tr class='c-off'>";
																	}else{
																		htmlStr +="<tr>";
																	} */
																	if(item.setTemp != null){
																		if(item.refSwiState == "off"){
																			htmlStr += "<td><div style='width:70px; color:#ccc;'>"
																			+ item.setTemp
																			+ "</div></td>";
																		}else{
																			htmlStr += "<td><div style='width:70px;'>"
																			+ item.setTemp
																			+ "</div></td>";
																		}	
																	}else{
																			htmlStr += "<td><div style='width:70px;'>"
																			+ ""
																			+ "</div></td>";
																	}
																	if(item.backWindTemp != null){
																		if(item.refSwiState == "off"){
																			htmlStr += "<td><div style='width:70px; color:#ccc;'>"
																			+ item.backWindTemp
																			+ "</div></td>";
																		}else{
																			htmlStr += "<td><div style='width:70px;'>"
																			+ item.backWindTemp
																			+ "</div></td>";
																		}	
																	}else{
																			htmlStr += "<td><div style='width:70px;'>"
																			+ ""
																			+ "</div></td>";
																	}
																	
																	if(item.ccdata.refBatVol != null && item.ccdata.refBatVol > 15){
																		if(item.refSwiState == "off"){
																			htmlStr += "<td><div style='width:95px; color:#ccc;'>"
																					+ 15
																					+ "</div></td>";
																		}else{
																			htmlStr += "<td><div style='width:95px;'>"
																					+ 15
																					+ "</div></td>";
																		}		
																	}else if(item.ccdata.refBatVol != null && item.ccdata.refBatVol < 0){
																		if(item.refSwiState == "off"){
																			htmlStr += "<td><div style='width:95px; color:#ccc;'>"
																					+ 0
																					+ "</div></td>";
																		}else{
																			htmlStr += "<td><div style='width:95px;'>"
																					+ 0
																					+ "</div></td>";
																		}		
																	}else if(item.ccdata.refBatVol != null){
																		if(item.refSwiState == "off"){
																			htmlStr += "<td><div style='width:95px; color:#ccc;'>"
																				+ item.ccdata.refBatVol
																				+ "</div></td>";
																		}else{
																			htmlStr += "<td><div style='width:95px;'>"
																				+ item.ccdata.refBatVol
																				+ "</div></td>";
																		}
																	}
																	else{
																		htmlStr += "<td><div style='width:95px;'>"
																				+ ""
																				+ "</div></td>";
																	}
																	if(item.ourccdata.gpsPower != null && item.ourccdata.gpsPower > 15){
																		htmlStr += "<td><div style='width:70px;'>"
																				+ 15
																				+ "</div></td>";
																	}else if(item.ourccdata.gpsPower != null && item.ourccdata.gpsPower < 0){
																		htmlStr += "<td><div style='width:70px;'>"
																				+ 0
																				+ "</div></td>";
																	}else if(item.ourccdata.gpsPower != null){
																		htmlStr += "<td><div style='width:70px;'>"
																				+ item.ourccdata.gpsPower
																				+ "</div></td>";
																	}
																	else{
																		htmlStr += "<td><div style='width:70px;'>"
																				+ ""
																				+ "</div></td>";
																	}
																	if(item.refSwiState != null){
																		htmlStr += "<td><div style='width:64px;'>"
																				+ item.refSwiState
																				+ "</div></td>";
																	}else{
																		htmlStr += "<td><div style='width:64px;'>"
																				+ ""
																				+ "</div></td>";
																	}
																	htmlStr += "<td><div style='width:70px;'>"
																			+ item.communicationState
																			+ "</div></td>";
																	htmlStr += "<td><div style='width:125px;'>"
																			+ item.ccdata.deviceId
																			+ "</div></td>";
																	htmlStr += "<td><div style='width:115px;'>"
																			+ item.ccdata.nowTime
																			+ "</div></td>";
																	htmlStr += "<td><div style='width:83px;'>"
																			+ item.ccdata.lcxModel
																			+ "</div></td>";
																/* 	htmlStr += "<td><div style='width:115px;'>"
																			+ item.trainId + "</div></td>"; */
																	
																	htmlStr += "<td><div style='width:100px;'>"
																			+ item.stationName + "</div></td>";
																	htmlStr += "<td><div style='width:100px;'>"
																			+ item.yardName + "</div></td>";
																/* 	htmlStr += "<td><div style='width:52px;'>"
																			+ item.routeType + "</div></td>"; */
																	htmlStr += "</tr>";

																});
												addHtml.append(htmlStr);
											}
										});
							});
			/* }); */
			//点击设置按钮跳转设置页面并传值
			$("#j-setting").click(function() {
				
			});
			
					
				//未查看围栏告警数目
				function getCountOfNoReadedGeomessageBeforeDays() {
					var countOfNoReaded = 0;
	
					$
							.ajax({
								url : "${pageContext.request.contextPath}/pc/alert/getCountOfNoReadedGeomessageBeforeDays.do",
								data : "",
								async : false,
								type : "get",
								dataType : "json",
								success : function(result) {
									countOfNoReaded = result;
	
								}
							});
					return countOfNoReaded;
				}
			
			//未查看警告记录数
			function askCountOfNoReadedAlert() {
				var countOfNoReaded = 0;

				$
						.ajax({
							url : "${pageContext.request.contextPath}/pc/alert/getCountOfNoReadedALertsBeforeDays.do",
							data : "",
							async : false,
							type : "get",
							dataType : "json",
							success : function(result) {
								countOfNoReaded = result;

							}
						});
				return countOfNoReaded;
			}

			//解析显示堆场列表
			function build_Dc_list(result) {
				var addHtml = $("#yard").empty();
				var allYars = result.extend.allYars;
				var htmlStr = "<option value=''>--请选择堆场--</option>";
				$.each(allYars, function(index, item) {
					htmlStr += "<option value=''>" + item.yardName
							+ "</option>";

				});
				addHtml.append(htmlStr);
			}

			//解析显示下货站列表
			function build_Xhz_list(result) {
				var addHtml = $("#theNextStation").empty();
				var allTheNextStations = result.extend.allTheNextStations;
				var htmlStr = "<option value=''>--请选择下货站--</option>";
				$.each(allTheNextStations, function(index, item) {
					htmlStr += "<option value=''>" + item.stationName
							+ "</option>";
				});
				addHtml.append(htmlStr);
			}

			//解析显示冷藏箱数据
			
			var timerInit;//冷藏箱定时器储存  新增
			function build_ccdatas_table(result,pn,totalSize,ranking,order) {
				if(pn != 1){
					var addHtml = $("#ccdatas_table tbody");
				}else{
					var addHtml = $("#ccdatas_table tbody").empty();
				}	
				var ccdatas = result.extend.maps;
				var htmlStr = "";
				$.each(ccdatas,function(index, item) {
					/* if(item.refSwiState == "off"){
						htmlStr += "<tr class='c-off'>";
					}else{
						htmlStr +="<tr>";
					} */
					htmlStr += "<tr>"
					htmlStr += "<td><div style='width:27px;'>"
							+ (index + 1 + (pn-1) * 12) + "</div></td> ";
					htmlStr += "<td><div style='width:33px;'><button class='main__amend'>修改</button> </div></td>";
					htmlStr += "<td><div style='width:110px;' class='containerId '>"
							+ item.ccdata.containerId
							+ "</div></td>";
					if(item.ourccdata.nowTime != null){
						htmlStr += "<td><div style='width:115px;'>"
								+ item.ourccdata.nowTime
								+ "</div></td>";
					}else{
						htmlStr += "<td><div style='width:115px;'>"
								+ ""
								+ "</div></td>";
					}
					if(item.ourccdata.gpsPosition != null){
						htmlStr += "<td><div style='width:150px;'>"
								+ item.ourccdata.gpsPosition
								+ "</div></td>";
					}else{
						htmlStr += "<td><div style='width:150px;'>"
								+ ""
								+ "</div></td>";
					}
					if(item.ifAlert == "have"){
						htmlStr += "<td><div style='width:70px;'><font style='color: red;'>"
								+ "是"
								+ "</font></div></td>";
					}else if(item.ifAlert == "no"){
						htmlStr += "<td><div style='width:70px;'>"
								+ "不"
								+ "</div></td>";
					}else{
						htmlStr += "<td><div style='width:70px;'>"
								+ ""
								+ "</div></td>";
					}
					//备注
					if(item.remark != null){
						htmlStr += "<td><div style='width:100px;'>"
								+ item.remark + "</div></td>";
					}else{
						htmlStr += "<td><div style='width:100px;'>"
								+ "" + "</div></td>";
					}
					htmlStr += "<td><div style='width:100px;'>"
							+ item.carGoType + "</div></td>";
					if(item.oilLevel != null){
						htmlStr += "<td><div style='width:50px;'>"
							+ item.oilLevel
							+ "</div></td>";
					}else{
						htmlStr += "<td><div style='width:50px;'>"
							+ ""
							+ "</div></td>";
					}
					if(item.setTemp != null){
						if(item.refSwiState == "off"){
							htmlStr += "<td><div style='width:70px; color:#ccc;'>"
							+ item.setTemp
							+ "</div></td>";
						}else{
							htmlStr += "<td><div style='width:70px;'>"
							+ item.setTemp
							+ "</div></td>";
						}	
					}else{
							htmlStr += "<td><div style='width:70px;'>"
							+ ""
							+ "</div></td>";
					}
					if(item.backWindTemp != null){
						if(item.refSwiState == "off"){
							htmlStr += "<td><div style='width:70px; color:#ccc;'>"
							+ item.backWindTemp
							+ "</div></td>";
						}else{
							htmlStr += "<td><div style='width:70px;'>"
							+ item.backWindTemp
							+ "</div></td>";
						}	
					}else{
							htmlStr += "<td><div style='width:70px;'>"
							+ ""
							+ "</div></td>";
					}
					
					if(item.ccdata.refBatVol != null && item.ccdata.refBatVol > 15){
						if(item.refSwiState == "off"){
							htmlStr += "<td><div style='width:95px; color:#ccc;'>"
									+ 15
									+ "</div></td>";
						}else{
							htmlStr += "<td><div style='width:95px;'>"
									+ 15
									+ "</div></td>";
						}		
					}else if(item.ccdata.refBatVol != null && item.ccdata.refBatVol < 0){
						if(item.refSwiState == "off"){
							htmlStr += "<td><div style='width:95px; color:#ccc;'>"
									+ 0
									+ "</div></td>";
						}else{
							htmlStr += "<td><div style='width:95px;'>"
									+ 0
									+ "</div></td>";
						}		
					}else if(item.ccdata.refBatVol != null){
						if(item.refSwiState == "off"){
							htmlStr += "<td><div style='width:95px; color:#ccc;'>"
								+ item.ccdata.refBatVol
								+ "</div></td>";
						}else{
							htmlStr += "<td><div style='width:95px;'>"
								+ item.ccdata.refBatVol
								+ "</div></td>";
						}
					}
					else{
						htmlStr += "<td><div style='width:95px;'>"
								+ ""
								+ "</div></td>";
					}
					if(item.ourccdata.gpsPower != null && item.ourccdata.gpsPower > 15){
						htmlStr += "<td><div style='width:70px;'>"
								+ 15
								+ "</div></td>";
					}else if(item.ourccdata.gpsPower != null && item.ourccdata.gpsPower < 0){
						htmlStr += "<td><div style='width:70px;'>"
								+ 0
								+ "</div></td>";
					}else if(item.ourccdata.gpsPower != null){
						htmlStr += "<td><div style='width:70px;'>"
								+ item.ourccdata.gpsPower
								+ "</div></td>";
					}
					else{
						htmlStr += "<td><div style='width:70px;'>"
								+ ""
								+ "</div></td>";
					}
					htmlStr += "<td><div style='width:64px;'>"
							+ item.refSwiState
							+ "</div></td>";
					htmlStr += "<td><div style='width:70px;'>"
							+ item.communicationState
							+ "</div></td>";
					htmlStr += "<td><div style='width:125px;'>"
							+ item.ccdata.deviceId
							+ "</div></td>";
					htmlStr += "<td><div style='width:115px;'>"
							+ item.ccdata.nowTime
							+ "</div></td>";
					htmlStr += "<td><div style='width:83px;'>"
							+ item.ccdata.lcxModel
							+ "</div></td>";
				/* 	htmlStr += "<td><div style='width:115px;'>"
							+ item.trainId + "</div></td>"; */
					htmlStr += "<td><div style='width:100px;'>"
							+ item.stationName + "</div></td>";
					htmlStr += "<td><div style='width:100px;'>"
							+ item.yardName + "</div></td>";
				/* 	htmlStr += "<td><div style='width:52px;'>"
							+ item.routeType + "</div></td>"; */
					htmlStr += "</tr>";
	
				});
				addHtml.append(htmlStr);
				
				if(pn < totalSize){/* 当前页小于总页数时继续调用ajax */
					ccdatasAjax(pn+1,ranking,order);//从ccdatasAjax带过来的order，为order5555的值
				}else{
					var role1 =<%=role1%>;
					if(role1 == 1){
						$(".main__amend").closest("td").remove();
						$(".main__caozuo").closest("td").remove();
					}
				}
					/* 当pn等于总页数，也就是加载到最后一章，且是第一次调用时执行定时器 */
					if( pn >= totalSize && ranking == "first"){
						/* 刷新页面定时器 */
						timerInit = setInterval(function() {//新增
							$("#countOfNRA").text(askCountOfNoReadedAlert());/* 告警信息条数显示 */
							ccdatasAjax(1,false);/* 冷藏箱列表刷新(除第一次刷新之外，定时器再次调用时ranking为false不再重新创建定时器) */
							//$("#sensor_table tbody").empty();
						},  300000000);
						/* 第一次加载且全部加载完成将冷藏箱列表定位到最后一次点击的行 */
						ccdataScrollTop();
					}
				}
				

			var containerId1;
			//1、点击冷藏箱列表，相应传感器
			$("#ccdatas_table tbody").on("click", "tr", function(e) {
				containerId1 = $(this).find(".containerId").text();
				//cId2 = $(this).find(".containerId").text();
				sessionStorage.lastClickCid = $(this).find(".containerId").text();
				//console.log(cId2);
				//2、异步请求 并 解析显示传感器数据
				//console.log(containerId1);
				var role1 =<%=role1%>;
				build_sensor_table(containerId1);
				doInitMap(containerId1);
			});

			//解析显示传感器数据
			function build_sensor_table(containerId) {
				//console.log("11111111");
				$.ajax({
							url : "${PATH}/pc/sensor/findIndexSensorWithJson.do",
							type : "get",
							data : {
								'containerId' : containerId,
								//'pn'          : pn
							},
							success : function(result) {

								var sensorHtml = $("#sensor_table tbody").empty();
								var ccdata = result.extend.maps;

								var sensorStr = "";

								$.each(ccdata,function(index, item) {
								   //var mydateInputStart = document.getElementById("showContainerID");
									$("#showContainerID").html(item.ccdata.containerId);
									//mydateInputStart.value=item.ccdata.containerId;
									if(item.ccdata.nowTime == undefined){
										item.ccdata.nowTime = "";
									}
									$("#showOurccdataTime").html(item.ccdata.nowTime);
									if(item.ccdata.refSwiState == "off"){
										sensorStr += "<tr class='c-off'>";
									}else{
										sensorStr +="<tr>"
									}
									sensorStr += "<td><div class='c-sensor1'>1</div></td> ";
									sensorStr += "<td><div class='c-sensor2'>冷机电瓶电压</div></td>";
									if(item.ccdata.refBatVol != null && item.ccdata.refBatVol > 15){
										sensorStr += "<td><div class='c-sensor3'>"
												+ 15
												+ "</div></td>";
									}else if(item.ccdata.refBatVol != null && item.ccdata.refBatVol < 0){
										sensorStr += "<td><div class='c-sensor3'>"
												+ 0
												+ "</div></td>";
									}else if(item.ccdata.refBatVol != null){
										sensorStr += "<td><div class='c-sensor3'>"
											+ item.ccdata.refBatVol
											+ "</div></td>";
									}
									else{
										sensorStr += "<td><div class='c-sensor3'>"
												+ ""
												+ "</div></td>";
									}
									sensorStr += " <td><div class='c-sensor4'>V</div></td>";
									sensorStr += "<td><div class='c-sensor5'><button data-id='setMenu1' class='setMenu__btn'>设置</button></div></td>";
									sensorStr += "</tr>";
								if(item.ccdata.refSwiState == "off"){
										sensorStr += "<tr class='c-off'>";
									}else{
										sensorStr +="<tr>";
									}
								sensorStr += "<td><div class='c-sensor1'>2</div></td> ";
								sensorStr += "<td><div class='c-sensor2'>工作状态</div></td>";
								sensorStr += "<td><div class='c-sensor3'>"+item.ccdata.workMode+"</div></td>";
								sensorStr += " <td><div class='c-sensor4'></div></td>";
								sensorStr += "<td><div class='c-sensor5'></div></td>";
								sensorStr +="</tr>";
	
									/* 	sensorStr +="<tr>";
										sensorStr += "<td><div>3</div></td> ";	
										sensorStr += "<td><div>后门开关</div></td>";
										sensorStr += "<td><div>"+item.ccdata.backDoorState+"</div></td>";
										sensorStr += " <td><div>on/off</div></td>";
										sensorStr += "<td><div><button data-id='setMenu3' class='setMenu__btn'>设置</button></div></td>";
										sensorStr += "</tr>";
										
										sensorStr +="<tr>";
										sensorStr += "<td><div>4</div></td> ";	
										sensorStr += "<td><div>箱内湿度</div></td>";
										sensorStr += "<td><div>"+item.ccdata.tankHum+"</div></td>";
										sensorStr += " <td><div>%</div></td>";
										sensorStr += "<td><div><button data-id='setMenu4' class='setMenu__btn'>设置</button></div></td>";
										sensorStr += "</tr>"; */
	
									/* 		sensorStr +="<tr>";
											sensorStr += "<td><div>5</div></td> ";	
											sensorStr += "<td><div>油箱油位</div></td>";
											sensorStr += "<td><div>"+item.ccdata.oilLevel+"</div></td>";
											sensorStr += " <td><div>%</div></td>";
											sensorStr += "<td><div><button data-id='setMenu5' class='setMenu__btn'>设置</button></div></td>";
											sensorStr += "</tr>"; */
	
									/*    sensorStr +="<tr>";
									sensorStr += "<td><div>5</div></td> ";	
									sensorStr += "<td><div>GPS电压</div></td>";
									sensorStr += "<td><div>"+item.ccdata.gpsBatVol+"</div></td>";
									sensorStr += " <td><div>V</div></td>";
									sensorStr += "<td><div><button data-id='setMenu6' class='setMenu__btn'>设置</button></div></td>";
									sensorStr += "</tr>"; */

									if(item.ccdata.refSwiState == "off"){
										sensorStr += "<tr class='c-off'>";
									}else{
										sensorStr +="<tr>"
									}
									sensorStr += "<td><div class='c-sensor1'>3</div></td> ";
									sensorStr += "<td><div class='c-sensor2'>环境温度</div></td>";
									
									if(item.ccdata.enviTemp != null){
										sensorStr += "<td><div class='c-sensor3'>"
												+ item.ccdata.enviTemp
												+ "</div></td>";
									}else{
										sensorStr += "<td><div class='c-sensor3'>"
												+ ""
												+ "</div></td>";
									}
									sensorStr += " <td><div class='c-sensor4'>°C</div></td>";
									sensorStr += "<td><div class='c-sensor5'><button data-id='setMenu3' class='setMenu__btn'>设置</button></div></td>";
									sensorStr += "</tr>";

									if(item.ccdata.refSwiState == "off"){
										sensorStr += "<tr class='c-off'>";
									}else{
										sensorStr +="<tr>"
									}
									sensorStr += "<td><div class='c-sensor1'>4</div></td> ";
									sensorStr += "<td><div class='c-sensor2'>设定温度</div></td>";
									if(item.setTemp != null){
										sensorStr += "<td><div class='c-sensor3'>"
												+ item.setTemp
												+ "</div></td>";
									}else{
										sensorStr += "<td><div class='c-sensor3'>"
												+ ""
												+ "</div></td>";
									}
									sensorStr += " <td><div class='c-sensor4'>°C</div></td>";
									sensorStr += "<td><div class='c-sensor5'><button class='setMenu__btn1'>设置</button></div></td>";
									sensorStr += "</tr>";
									
									if(item.ccdata.refSwiState == "off"){
										sensorStr += "<tr class='c-off'>";
									}else{
										sensorStr +="<tr>"
									}
									sensorStr += "<td><div class='c-sensor1'>5</div></td> ";
									sensorStr += "<td><div class='c-sensor2'>出风温度</div></td>";
									if(item.ccdata.chuWindTemp != null){
										sensorStr += "<td><div class='c-sensor3'>"
												+ item.ccdata.chuWindTemp
												+ "</div></td>";
									}else{
										sensorStr += "<td><div class='c-sensor3'>"
												+ ""
												+ "</div></td>";
									}
									sensorStr += " <td><div class='c-sensor4'>°C</div></td>";
									sensorStr += "<td><div class='c-sensor5'><button data-id='setMenu4' class='setMenu__btn'>设置</button></div></td>";
									sensorStr += "</tr>";

									/* sensorStr +="<tr>";
									sensorStr += "<td><div>5</div></td> ";	
									sensorStr += "<td><div>中部温度</div></td>";
									sensorStr += "<td><div>"+item.ccdata.cenBoxTemp+"</div></td>";
									sensorStr += " <td><div>°C</div></td>";
									sensorStr += "<td><div><button data-id='setMenu5' class='setMenu__btn'>设置</button></div></td>";
									sensorStr += "</tr>"; */

									/* sensorStr +="<tr>";
									sensorStr += "<td><div>9</div></td> ";	
									sensorStr += "<td><div>尾部箱温</div></td>";
									sensorStr += "<td><div>"+item.ccdata.tailBoxTemp+"</div></td>";
									sensorStr += " <td><div>°C</div></td>";
									sensorStr += "<td><div><button data-id='setMenu10' class='setMenu__btn'>设置</button></div></td>";
									sensorStr += "</tr>"; */
									/* 
									sensorStr += "<tr>";
									sensorStr += "<td><div>5</div></td> ";
									sensorStr += "<td><div>油箱温度</div></td>";
									sensorStr += "<td><div>"
											+ item.ourccdata.oilTemp
											+ "</div></td>";
									sensorStr += " <td><div>°C</div></td>";
									sensorStr += "<td><div><button data-id='setMenu6' class='setMenu__btn'>设置</button></div></td>";
									sensorStr += "</tr>"; */

									if(item.ccdata.refSwiState == "off"){
										sensorStr += "<tr class='c-off'>";
									}else{
										sensorStr +="<tr>";
									}
									sensorStr += "<td><div class='c-sensor1'>6</div></td> ";
									sensorStr += "<td><div class='c-sensor2'>回风温度</div></td>";
									
									if(item.ccdata.backWindTemp != null){
										sensorStr += "<td><div class='c-sensor3'>"
												+ item.ccdata.backWindTemp
												+ "</div></td>";
									}else{
										sensorStr += "<td><div class='c-sensor3'>"
												+ ""
												+ "</div></td>";
									}
									sensorStr += " <td><div class='c-sensor4'>°C</div></td>";
									sensorStr += "<td><div class='c-sensor5'><button data-id='setMenu2' class='setMenu__btn'>设置</button></div></td>";
									sensorStr += "</tr>";
									
									
									sensorStr += "<tr>";
									sensorStr += "<td><div class='c-sensor1'>7</div></td> ";
									sensorStr += "<td><div class='c-sensor2'>箱尾温度</div></td>";
									if(item.ourccdata.tailBoxTemp != null){
										sensorStr += "<td><div class='c-sensor3'>"
												+ item.ourccdata.tailBoxTemp
												+ "</div></td>";
									}else{
										sensorStr += "<td><div class='c-sensor3'>"
												+ ""
												+ "</div></td>";
									}
									sensorStr += " <td><div class='c-sensor4'>°C</div></td>";
									sensorStr += "<td><div class='c-sensor5'><button data-id='setMenu8' class='setMenu__btn'>设置</button></div></td>";
									sensorStr += "</tr>";
									
		
									sensorStr += "<tr>";
									sensorStr += "<td><div class='c-sensor1'>8</div></td> ";
									sensorStr += "<td><div class='c-sensor2'>箱内湿度</div></td>";
									if(item.ourccdata.boxHum != null){
										sensorStr += "<td><div class='c-sensor3'>"
												+ item.ourccdata.boxHum
												+ "</div></td>";
									}else{
										sensorStr += "<td><div class='c-sensor3'>"
												+ ""
												+ "</div></td>";
									}
									sensorStr += " <td><div class='c-sensor4'>%</div></td>";
									sensorStr += "<td><div class='c-sensor5'><button data-id='setMenu7' class='setMenu__btn'>设置</button></div></td>";
									sensorStr += "</tr>";

									sensorStr += "<tr>";
									sensorStr += "<td><div class='c-sensor1'>9</div></td> ";
									sensorStr += "<td><div class='c-sensor2'>gps电量</div></td>";
									if(item.ourccdata.gpsPower != null && item.ourccdata.gpsPower > 15){
										sensorStr += "<td><div class='c-sensor3'>"
												+ 15
												+ "</div></td>";
									}else if(item.ourccdata.gpsPower != null && item.ourccdata.gpsPower < 0){
										sensorStr += "<td><div class='c-sensor3'>"
												+ 0
												+ "</div></td>";
									}else if(item.ourccdata.gpsPower != null){
										sensorStr += "<td><div class='c-sensor3'>"
											+ item.ourccdata.gpsPower
											+ "</div></td>";
									}
									else{
										sensorStr += "<td><div class='c-sensor3'>"
												+ ""
												+ "</div></td>";
									}
									sensorStr += " <td><div class='c-sensor4'>V</div></td>";
									sensorStr += "<td><div class='c-sensor5'><button data-id='setMenu9' class='setMenu__btn'>设置</button></div></td>";
									sensorStr += "</tr>";

									sensorStr += "<tr>";
									sensorStr += "<td><div class='c-sensor1'>10</div></td> ";
									sensorStr += "<td><div class='c-sensor2'>油箱油位</div></td>";
									
									if(item.ourccdata.oilLevel != null){
										sensorStr += "<td><div class='c-sensor3'>"
												+ item.ourccdata.oilLevel
												+ "</div></td>";
									}else{
										sensorStr += "<td><div class='c-sensor3'>"
												+ ""
												+ "</div></td>";
									}
									sensorStr += " <td><div class='c-sensor4'>%</div></td>";
									sensorStr += "<td><div class='c-sensor5'><button data-id='setMenu10' class='setMenu__btn'>设置</button></div></td>";
									sensorStr += "</tr>";

									
									sensorStr +="<tr>"
									sensorStr += "<td><div class='c-sensor1'>11</div></td> ";
									sensorStr += "<td><div class='c-sensor2'>后门开关</div></td>";
									if(item.ourccdata.backDoorState != null && (item.ourccdata.backDoorState == "open" || item.ourccdata.backDoorState == "close")){
										sensorStr += "<td><div class='c-sensor3'>"
												+ item.ourccdata.backDoorState
												+ "</div></td>";
									}else{
										sensorStr += "<td><div class='c-sensor3'>"
												+ ""
												+ "</div></td>";
									}
									sensorStr += " <td><div class='c-sensor4'>open/close</div></td>";
									sensorStr += "<td><div class='c-sensor5'><button data-id='setMenu11' class='setMenu__btn'>设置</button></div></td>";
									//sensorStr += "<td><div class='c-sensor5'><button>设置</button></div></td>";
									sensorStr += "</tr>";
									
									if(item.ccdata.refSwiState == "off"){
										sensorStr += "<tr class='c-off'>";
									}else{
										sensorStr +="<tr>"
									}
									sensorStr += "<td><div class='c-sensor1'>12</div></td> ";
									sensorStr += "<td><div class='c-sensor2'>运行模式</div></td>";
									if(item.ccdata.refRunMode != null){
										sensorStr += "<td><div class='c-sensor3'>"
												+ item.ccdata.refRunMode
												+ "</div></td>";
									}
								/* 	else if(item.ccdata.refRunMode == "auto"){
										sensorStr += "<td><div>"
											+ "启停"
											+ "</div></td>";
									}
									 */
									else{
										sensorStr += "<td><div class='c-sensor3'>"
												+ ""
												+ "</div></td>";
									}
									sensorStr += " <td><div class='c-sensor4'>连续/启停</div></td>";
									sensorStr += "<td><div class='c-sensor5'><button class='setMenu__btn1'>设置</button></div></td>";
									sensorStr += "</tr>";
									<%--
									sensorStr += "<td><div class='c-sensor1'>13</div></td> ";
								sensorStr += "<td><div class='c-sensor2'>请求周期</div></td>";
								sensorStr += "<td><div class='c-sensor3'>"
												+ item.cycleMinute
												+ "</div></td>";
								sensorStr += " <td><div class='c-sensor4'>分钟</div></td>";
									sensorStr += "<td><div class='c-sensor5'><button class='setMenu__btn' data-id='setMenu12'>设置</button></div></td>";
									sensorStr += "</tr>";--%>
								});
								sensorHtml.append(sensorStr);
								var role1 =<%=role1%>;
								if(role1 == 1){
									$(".setMenu__btn").closest("td").remove();
									$(".setMenu__set").closest("td").remove();
									$(".setMenu__btn1").closest("td").remove();
								}
							}
						});
			}
			//更新绑定表
			function bindtableUpdate() {

				var containerId = $('#containerId').val();
				var trainId = $('#trainId').val();
				var carGoType = $('#carGoType').val();
				var remark = $('#remark').val();
				var lcxModel = $('#lcxModel').val();
				var nodeSel1 = document.getElementById("theNextStation"); //获取select元素
				var index1 = nodeSel1.selectedIndex; // 选中项的索引
				var stationName = $(nodeSel1.options[index1]).text(); // 获取值
				console.log("+++");
				console.log(stationName);

				var nodeSel2 = document.getElementById("yard"); //获取select元素
				var index2 = nodeSel2.selectedIndex; // 选中项的索引
				var yardName = $(nodeSel2.options[index2]).text(); // 获取值
				console.log(yardName);
				$.ajax({
					url : '${PATH}/pc/bindtable/updateBindtableInfo.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'trainId' : trainId,
						'carGoType' : carGoType,
						'stationName' : stationName,
						'yardName' : yardName,
						'remark'   : remark,
						'lcxModel' : lcxModel
 					},
					dataType : 'JSON',
					success : function(result) {
						location.replace('${PATH}/pc/index.jsp');
					}
				});
			}

			//冷机电瓶电压设置1
			function battery() {
				var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
				var maxRefBatVol = $('#maxRefBatVol').val();
				var minRefBatVol = $('#minRefBatVol').val();
				var containerId = containerId1;
				var type = 'battery';
				$.ajax({
					url : '${PATH}/pc/updateContainerSet.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'maxRefBatVol' : maxRefBatVol,
						'minRefBatVol' : minRefBatVol
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							build_sensor_table(containerId);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			}

			//回风温度设置2
			function backWindTemp() {
				var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
				var minBackWindTemp = $('#minBackWindTemp').val();
				var maxBackWindTemp = $('#maxBackWindTemp').val();
				var containerId = containerId1;
				var type = 'backWindTemp';
				$.ajax({
					url : '${PATH}/pc/updateContainerSet.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'minBackWindTemp' : minBackWindTemp,
						'maxBackWindTemp' : maxBackWindTemp
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							build_sensor_table(containerId);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			}

			//箱内湿度设置3
			function tankHum() {
			var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
				var minTankHum = $('#minTankHum').val();
				var maxTankHum = $('#maxTankHum').val();
				var containerId = containerId1;
				var type = 'tankHum';
				$.ajax({
					url : '${PATH}/pc/updateContainerSet.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'minTankHum' : minTankHum,
						'maxTankHum' : maxTankHum
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							build_sensor_table(containerId);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			}

			//油箱油位设置4
			function oilLevel() {
				var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
				var minOilLevel = $('#minOilLevel').val();
				var maxOilLevel = $('#maxOilLevel').val();
				var containerId = containerId1;
				var type = 'oilLevel';
				$.ajax({
					url : '${PATH}/pc/updateContainerSet.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'minOilLevel' : minOilLevel,
						'maxOilLevel' : maxOilLevel
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							build_sensor_table(containerId);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			}

			//gps电压设置5
			function gpsBatVol() {
				var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
				var minGpsBatVol = $('#minGpsBatVol').val();
				var containerId = containerId1;
				var type = 'gpsBatVol';
				$.ajax({
					url : '${PATH}/pc/updateContainerSet.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'minGpsBatVol' : minGpsBatVol
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							build_sensor_table(containerId);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			}

			//环境温度设置6
			function enviTemp() {
				var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
				var minEnviTemp = $('#minEnviTemp').val();
				var maxEnviTemp = $('#maxEnviTemp').val();
				var containerId = containerId1;
				var type = 'enviTemp';
				$.ajax({
					url : '${PATH}/pc/updateContainerSet.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'minEnviTemp' : minEnviTemp,
						'maxEnviTemp' : maxEnviTemp
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							build_sensor_table(containerId);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			}

			//出风温度设置7
			function chuWindTemp() {
				var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
				var minChuWindTemp = $('#minChuWindTemp').val();
				var maxChuWindTemp = $('#maxChuWindTemp').val();
				var containerId = containerId1;
				var type = 'chuWindTemp';
				$.ajax({
					url : '${PATH}/pc/updateContainerSet.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'minChuWindTemp' : minChuWindTemp,
						'maxChuWindTemp' : maxChuWindTemp
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							build_sensor_table(containerId);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			}

			//中箱温度设置8
			function cenBoxTemp() {
				var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
				var minCenBoxTemp = $('#minCenBoxTemp').val();
				var maxCenBoxTemp = $('#maxCenBoxTemp').val();
				var containerId = containerId1;
				var type = 'cenBoxTemp';
				$.ajax({
					url : '${PATH}/pc/updateContainerSet.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'minCenBoxTemp' : minCenBoxTemp,
						'maxCenBoxTemp' : maxCenBoxTemp
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							build_sensor_table(containerId);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			}

			//尾部箱温设置9
			function tailBoxTemp() {
				var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
				var minTailBoxTemp = $('#minTailBoxTemp').val();
				var maxTailBoxTemp = $('#maxTailBoxTemp').val();
				var containerId = containerId1;
				var type = 'tailBoxTemp';
				$.ajax({
					url : '${PATH}/pc/updateContainerSet.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'minTailBoxTemp' : minTailBoxTemp,
						'maxTailBoxTemp' : maxTailBoxTemp
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							build_sensor_table(containerId);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			}

			//油箱温度设置
			<%-- function oilTemp() {
				var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
				var minoilTemp = $('#minoilTemp').val();
				var maxoilTemp = $('#maxoilTemp').val();
				var containerId = containerId1;
				var type = 'oilTemp';
				$.ajax({
					url : '${PATH}/pc/updateOurCcdata.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'minoilTemp' : minoilTemp,
						'maxoilTemp' : maxoilTemp
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			} --%>

			//箱内湿度设置
			function boxHum() {
				var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
				var minboxHum = $('#minboxHum').val();
				var maxboxHum = $('#maxboxHum').val();
				var containerId = containerId1;
				var type = 'boxHum';
				$.ajax({
					url : '${PATH}/pc/updateOurCcdata.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'minboxHum' : minboxHum,
						'maxboxHum' : maxboxHum
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							build_sensor_table(containerId);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			}

			//箱尾温度设置
			function tailBoxTemp() {
				var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
				var mintailBoxTemp = $('#mintailBoxTemp').val();
				var maxtailBoxTemp = $('#maxtailBoxTemp').val();
				var containerId = containerId1;
				var type = 'tailBoxTemp';
				$.ajax({
					url : '${PATH}/pc/updateOurCcdata.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'mintailBoxTemp' : mintailBoxTemp,
						'maxtailBoxTemp' : maxtailBoxTemp
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							build_sensor_table(containerId);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			}

			//GPS电量设置
			function gpsPower() {
				var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
				var mingpsPower = $('#mingpsPower').val();
				var maxgpsPower = $('#maxgpsPower').val();
				var containerId = containerId1;
				var type = 'gpsPower';
				$.ajax({
					url : '${PATH}/pc/updateOurCcdata.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'mingpsPower' : mingpsPower,
						'maxgpsPower' : maxgpsPower
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							build_sensor_table(containerId);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			}

			//油箱油位
			function oilLevel() {
				var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
				var minoilLevel = $('#minoilLevel').val();
				var maxoilLevel = $('#maxoilLevel').val();
				var containerId = containerId1;
				var type = 'oilLevel';
				$.ajax({
					url : '${PATH}/pc/updateOurCcdata.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'minoilLevel' : minoilLevel,
						'maxoilLevel' : maxoilLevel
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							build_sensor_table(containerId);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			}

			//后门开关  backDoorState()
			function backDoorStateFun() {
				var DoorState = $("#door").val();

				/* 	   var DoorState = $('#door').find("option:selected").val(); */
				/* 	   var DoorState = $('#door :selected').val(); */
				/* 	   var DoorState = $('#door option:selected').val(); */

				var containerId = containerId1;
				var type = 'backDoorState';
				$.ajax({
					url : '${PATH}/pc/updateOurCcdata.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'backDoorState' : DoorState
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							build_sensor_table(containerId);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			}
			
			//设置请求周期 <span>请输入周期：</span><input type="text" id="cycleMinute" />&nbsp;（分钟1-10）
			<%--function setCycleMinute() {
				var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
				var cycleMinute = $('#cycleMinute').val();
				var containerId = containerId1;
				var type = 'cycleMinute';
				$.ajax({
					url : '${PATH}/pc/updateContainerSet.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'cycleMinute' : cycleMinute
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			}
			function enviTemp() {
				var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
				var minEnviTemp = $('#minEnviTemp').val();
				var maxEnviTemp = $('#maxEnviTemp').val();
				var containerId = containerId1;
				var type = 'enviTemp';
				$.ajax({
					url : '${PATH}/pc/updateContainerSet.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'type' : type,
						'minEnviTemp' : minEnviTemp,
						'maxEnviTemp' : maxEnviTemp
					},
					dataType : 'JSON',
					success : function(result) {
						var field = result.state;
						var mess = result.message;
						if (field == '0') {
							layer.msg(mess);
							return;
						} else {
							layer.msg(mess);
						}
					}
				});
			}
			--%>
			/* 冷藏箱列表左右滚动标题滚动 */
			$("#ccdatas_table").on("scroll",function(){
				//console.log($(this).scrollLeft());
				var marginLeft = "-" + $(this).scrollLeft() + "px";
				//console.log($(".j-tableTitle"));
				$(".j-tableTitle").css("marginLeft",marginLeft);
				sessionStorage.lastScrollTop = $(this).scrollTop();
			});
			
			/* 排序小三角点击变化    start */
				/* 冷藏箱编号 */
			/* 排序小三角点击变化    start */
				/* 冷藏箱编号 */
			$(".j-sort1").click(function(){
				if($(this).attr("data-status") == "up"){
					$(this).attr("data-status","down");
					window.order = "CIdDESC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjDown").removeClass("xsjUp");
				}else{
					$(this).attr("data-status","up");
					window.order = "CIdASC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjUp").removeClass("xsjDown");
				}
			});
				/* 系统时间 */
			$(".j-sort2").click(function(){
				if($(this).attr("data-status") == "up"){
					$(this).attr("data-status","down");
					window.order = "NowTimeDESC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjDown").removeClass("xsjUp");
				}else{
					$(this).attr("data-status","up");
					window.order = "NowTimeASC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjUp").removeClass("xsjDown");
				}
			});
				/* 设定温度 */
			$(".j-sort3").click(function(){
				if($(this).attr("data-status") == "up"){
					$(this).attr("data-status","down");
					window.order = "TempSetDESC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjDown").removeClass("xsjUp");
				}else{
					$(this).attr("data-status","up");
					window.order = "TempSetASC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjUp").removeClass("xsjDown");
				}
			});
				/* 回风温度 */
			$(".j-sort4").click(function(){
				if($(this).attr("data-status") == "up"){
					$(this).attr("data-status","down");
					window.order = "BackWindTempDESC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjDown").removeClass("xsjUp");
				}else{
					$(this).attr("data-status","up");
					window.order = "BackWindTempASC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjUp").removeClass("xsjDown");
				}
			});
				/* 冷机电瓶电压 */
			$(".j-sort5").click(function(){
				if($(this).attr("data-status") == "up"){
					$(this).attr("data-status","down");
					window.order = "RefBatVolDESC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjDown").removeClass("xsjUp");
				}else{
					$(this).attr("data-status","up");
					window.order = "RefBatVolASC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjUp").removeClass("xsjDown");
				}
			});
				/* GPS电量 */
			$(".j-sort6").click(function(){
				if($(this).attr("data-status") == "up"){
					$(this).attr("data-status","down");
					window.order = "GPSPowerDESC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjDown").removeClass("xsjUp");
				}else{
					$(this).attr("data-status","up");
					window.order = "GPSPowerASC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjUp").removeClass("xsjDown");
				}
			});
			
				/* GPS接收时间 */
			$(".j-sort7").click(function(){
				if($(this).attr("data-status") == "up"){
					$(this).attr("data-status","down");
					window.order = "OurCCNowTimeDESC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjDown").removeClass("xsjUp");
				}else{
					$(this).attr("data-status","up");
					window.order = "OurCCNowTimeASC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjUp").removeClass("xsjDown");
				}
			});
			
				/* 备注 */
			$(".j-sort8").click(function(){
				if($(this).attr("data-status") == "up"){
					$(this).attr("data-status","down");
					window.order = "RemarkDESC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjDown").removeClass("xsjUp");
				}else{
					$(this).attr("data-status","up");
					window.order = "RemarkASC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjUp").removeClass("xsjDown");
				}
			});
				/* 冷机告警 */
			$(".j-sort9").click(function(){
				if($(this).attr("data-status") == "up"){
					$(this).attr("data-status","down");
					window.order = "coldAlertDESC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjDown").removeClass("xsjUp");
				}else{
					$(this).attr("data-status","up");
					window.order = "coldAlertDESC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjUp").removeClass("xsjDown");
				}
			});
				/*地理位置排序*/
			$(".j-sort10").click(function(){
				if($(this).attr("data-status") == "up"){
					$(this).attr("data-status","down");
					window.order = "positionDESC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjDown").removeClass("xsjUp");
				}else{
					$(this).attr("data-status","up");
					window.order = "positionASC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjUp").removeClass("xsjDown");
				}
			});
			/*通讯状态排序*/
			$(".j-sort11").click(function(){
				if($(this).attr("data-status") == "up"){
					$(this).attr("data-status","down");
					window.order = "lcxModelDESC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjDown").removeClass("xsjUp");
				}else{
					$(this).attr("data-status","up");
					window.order = "lcxModelASC";
					//changeOrder(order);
					clearTimerInit();
					ccdatasAjax(1,"first",window.order);
					$(this).find(".xsj").addClass("xsjUp").removeClass("xsjDown");
				}
			});
			/* 清空定时器调用         新增 */
			function clearTimerInit(){
				clearInterval(timerInit);
			}
			/* 排序小三角点击变化    end */
			function ccdataScrollTop(){
				/* 首页加载时，显示之前点击冷藏箱传感器信息变蓝*/
				if(sessionStorage.lastClickCid != undefined){
					var cId2 = sessionStorage.lastClickCid;
					var containerTr = $("#ccdatas_table>tbody>tr");
					for (var i = 0; i < containerTr.length; i++) {
						if ($(containerTr[i]).find("td").eq(2).find("div").text() == cId2) {
							//$(containerTr[i]).addClass("tr__active");
							$(containerTr[i]).click();
							//console.log($(containerTr[i]).position().top);
							break;
						};
					}
				}
				/* 首页加载时，显示之前浏览到的传感器列表位置*/
				if(sessionStorage.lastScrollTop != undefined){
					$('.j-scrollTop').animate({
						scrollTop : sessionStorage.lastScrollTop
					}, 0);
				}
			}
			
			
			//未发生点击冷藏箱的时候地图默认加载的地图
			var uluru = {lat: 34.735892, lng: 113.815918};
			var map = new google.maps.Map(document.getElementById('googleMap'), {
		    	zoom: 2,
		    	center: uluru
		    });
		    var infowindow;
		    //点击事件，加载地图
			function doInitMap(cId){
				$.get("<%=request.getContextPath()%>/pc/getLocation.do?containerId="+cId,function(data){
					if(data.code == 100){
						var uurl = new Object();
						uurl.lat = data.extend.map.lat;
						uurl.lng = data.extend.map.lon;
						var thisMap = new google.maps.Map(document.getElementById('googleMap'), {
					    	zoom: 10,
					    	center: uurl
					    });
						var thisMarker = new google.maps.Marker({
				          position: uurl,
				          map: thisMap
				        });
				        var address = "";
				        var geocoder = new google.maps.Geocoder();
				        geocoder.geocode({location:new google.maps.LatLng(uurl.lat, uurl.lng)},
							function geoResults(results, status){
							 if (status == google.maps.GeocoderStatus.OK) {
							 	 address=results[0].formatted_address;
							 	 infowindow = new google.maps.InfoWindow({
								    	  content:"冷藏箱编号："+ cId+"<br/>位置："+address+"<br/>"+
								    	  "经纬度："+uurl.lng+" "+uurl.lat}
								    	  );
								  infowindow.open(thisMap,thisMarker); 
							 }
						});
					}else{
						 infowindow = new google.maps.InfoWindow({
								    	  content:"冷藏箱编号："+ cId+"<br/>"+
								    	  "经纬度："+uurl.lng+" "+uurl.lat}
								    	  );
								 infowindow.open(thisMap,thisMarker); 
					}
				},"json");
				
			}
			
			//showgaojingxinxi()
				function showgaojingxinxi(){
					$("#gaojingxinxi").css("display","block");
					$("#weilantongzhi").css("display","none");
				}
				function showweilantongzhi(){
					$("#gaojingxinxi").css("display","none");
					$("#weilantongzhi").css("display","block");
				}
	</script>
	<script>
		/*点击修改传感器头部的字体颜色*/
		$(document).ready(function() {
			$('.sensor_ul li').on('click',function(){
				var role1 =<%=role1%>;
				if (role1 == 1)
					return;
				$('.sensor_ul li').css('color','#333');
				$('.sensor_ul li a').css('color','#333');
				$(this).css('color','#649893');			
				$(this).children('a').css('color','#649893');
				let id = $(this).attr('tab_id');
				//如果为设置则跳转页面
				if(id == 2){
					var cId = $("#ccdatas_table").find(".tr__active").find(".containerId ").text();
					if (cId != "" && cId.replace(/\s+/g, "") != "") {
					<%-- href="${PATH}/pc/seting.jsp?containerId=" --%>
						this.firstChild.href = "${PATH}/pc/seting.jsp?containerId="+ cId;
					} else {
						alert("请选择（点击）一个冷藏箱！！！");
					}
				}else{
					$('.tab_main').hide();
					$('.tab_main:nth-of-type('+ id +')').show();
				}
				
			});
		})
	</script>
</body>
</html>
