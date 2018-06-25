<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.cctrace.entity.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>冷藏箱管理</title>

<%
	pageContext.setAttribute("PATH", request.getContextPath());
	if (request.getSession().getAttribute("user") == null) {
		request.getRequestDispatcher("/pc/login.jsp").forward(request, response);
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
<link rel="stylesheet" href="${PATH}/css/index.css" />

</head>

<body>
	<!-- 导航 -->
	<jsp:include page="header.jsp" flush="true"></jsp:include>
	<main class="main"> <!--现有冷藏箱列表-->
	<div class="con__boxManage bindEvent clearfix">
		<div class="boxManage__wrap">
			<header class="con__header">
				<ul>
					<li data-con="${PATH}/data/boxManage.html"
						class="con__header--active">现有冷藏箱</li>
				</ul>
	
				<%-- <a  id ="showforRole" class="con__header--add"
					href="${PATH }/pc/Container/containerRegist.do"> 新增冷藏箱 </a> --%>
			</header>
			<main class="con__main">
			<span>
						冷藏箱编号：<input type="text" id="containerId3" autocomplete="off" name="containerId" />
						<input type="button" id="search" value="查询" onclick="search();"/>
						<span id="totalPageShow" style="display: none;">${totalPage}</span>
			</span>
			<div class="con__main__table100" style="height: -moz-calc(100% - 28px); height: -webkit-calc(100% - 28px); height: calc(100% - 28px);">
				<table>
					<thead>
						<tr>
							<td><div class="leftDown--div1">序号</div></td>
							<td><div class="leftDown--div1">冷藏箱编号</div></td>
							<td><div class="leftDown--div1">冷机类型</div></td>
							<td><div class="leftDown--div2">注册时间</div></td>
							<td><div class="leftDown--div3">设备编号</div></td>
							<td><div class="leftDown--div3">公司id</div></td>
							<td><div class="leftDown--div3">最后修改设备时间</div></td>
							<td><div class="leftDown--div3">冷机最低电压</div></td>
							<td><div class="leftDown--div1">冷机最高电压</div></td>
							<td><div class="leftDown--div1">最低回风温度</div></td>
							<td><div class="leftDown--div2">最高回风温度</div></td>
							<td><div class="leftDown--div3">最低箱内湿度</div></td>
							<td><div class="leftDown--div3">最高箱内湿度</div></td>
							<td><div class="leftDown--div3">最低油位</div></td>
							<td><div class="leftDown--div1">最高油位</div></td>
							<td><div class="leftDown--div1">gps设备最低电量</div></td>
							<td><div class="leftDown--div2">gps设备最高电量</div></td>
							<td><div class="leftDown--div2">最低环境温度</div></td>
							<td><div class="leftDown--div2">最高环境温度</div></td>
							<td><div class="leftDown--div2">最低出风温度</div></td>
							<td><div class="leftDown--div2">最高出风温度</div></td>
							<td><div class="leftDown--div2">最低尾部箱温</div></td>
							<td><div class="leftDown--div2">最高尾部箱温</div></td>
							<!-- <td><div class="leftDown--div2">最低油箱温度</div></td>
							<td><div class="leftDown--div2">最高油箱温度</div></td> -->
							<td><div class="leftDown--div2">远程开关机</div></td>
							<td><div class="leftDown--div2">设定温度</div></td>
							<td><div class="leftDown--div2">启动除霜</div></td>
							<td><div class="leftDown--div2">清除警告</div></td>
							<td><div class="leftDown--div3">自检功能</div></td>
							<td><div class="leftDown--div3">冷机运行模式</div></td>
							<td><div class="leftDown--div3">车辆运行时长</div></td>
							<td><div class="leftDown--div3">发动机运行时长</div></td>
							<!--   <td><div class="leftDown--div3">新风门模式</div></td> -->


						</tr>
					</thead>
					<tbody class="btn--changeUser" id="contentContainers">
						<c:forEach var="map" items="${maps}" varStatus="status">

							<tr>
								<td><div class="leftDown--div1">${status.index+1}</div></td>
								<td><div class="leftDown--div2">${map.container.containerId}</div></td>
								<td><div class="leftDown--div2">${map.container.chillerType}</div></td>
								<td><div class="leftDown--div3">${map.container.registTime}</div></td>
								<td><div class="leftDown--div4">${map.container.deviceId}</div></td>
								<td>
									<div class="leftDown--div15">
										<c:if test="${map.container.companyId == 1}">郑州陆港</c:if>
									</div>
								</td>
								<td><div class="leftDown--div5">${map.container.modifyDeviceTime}</div></td>
								<td><div class="leftDown--div6">${map.container.minRefBatVol}</div></td>
								<td><div class="leftDown--div7">${map.container.maxRefBatVol}</div></td>
								<td><div class="leftDown--div8">${map.container.minBackWindTemp}</div></td>
								<td><div class="leftDown--div10">${map.container.maxBackWindTemp}</div></td>
								<td><div class="leftDown--div11">${map.ourCcdata.minboxHum}</div></td>
								<td><div class="leftDown--div12">${map.ourCcdata.maxboxHum}</div></td>
								<td><div class="leftDown--div13">${map.ourCcdata.minoilLevel}</div></td>
								<td><div class="leftDown--div14">${map.ourCcdata.maxoilLevel}</div></td>
								<td><div class="leftDown--div15">${map.ourCcdata.mingpsPower}</div></td>
								<td><div class="leftDown--div16">${map.ourCcdata.maxgpsPower}</div></td>

								<td><div class="leftDown--div6">${map.container.minEnviTemp}</div></td>
								<td><div class="leftDown--div7">${map.container.maxEnviTemp}</div></td>
								<td><div class="leftDown--div8">${map.container.minChuWindTemp}</div></td>
								<td><div class="leftDown--div10">${map.container.maxChuWindTemp}</div></td>
								<td><div class="leftDown--div11">${map.ourCcdata.mintailBoxTemp}</div></td>
								<td><div class="leftDown--div12">${map.ourCcdata.maxtailBoxTemp}</div></td>

								<%-- <td><div class="leftDown--div11">${map.ourCcdata.minoilTemp}</div></td>
								<td><div class="leftDown--div12">${map.ourCcdata.maxoilTemp}</div></td>
 --%>
								<td><div class="leftDown--div13">${map.remoteSwiMac}</div></td>
								<td><div class="leftDown--div14">${map.setTemp}</div></td>
								<td><div class="leftDown--div14">${map.container.bootDef}</div></td>
								<td><div class="leftDown--div15">${map.container.clearAlert}</div></td>
								<td><div class="leftDown--div14">${map.container.selfCheck}</div></td>
								<td><div class="leftDown--div15">${map.refRunMode}</div></td>
								<td><div class="leftDown--div16">${map.ccdata.vecRunTime}</div></td>
								<td><div class="leftDown--div17">${map.ccdata.engRunTime}</div></td>
								<%-- <td><div class="leftDown--div16">${container.newWindDoorMode}</div></td> --%>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</main>
		</div>
	</div>

	<!--当前冷藏箱信息-->
	<div class="con__boxManage--menu bindEvent clearfix">
		<div class="boxManage__wrap boxManage__amend">
			<header class="con__header">
			<ul>
				<li class="con__header--active">现有冷藏箱</li>
			</ul>
			</header>
			<main class="con__main">
			<div class="boxManage__main--amend">
				<span class="boxManage__main--header">删除冷藏箱信息</span> <span>冷藏箱编号：<input
					type="text" id="containerId2" name="containerId"
					disabled="disabled" /></span> <span>设备编号：<input type="text"
					id="deviceId2" name="deviceId" disabled="disabled" /></span>
				<!--   <span>冷机最低电压：<input type="text" id="minRefBatVol2" name="minRefBatVol" /></span>
          <span>冷机最高电压：<input type="text" id="maxRefBatVol2" name="maxRefBatVol" /></span>
          <span>最低回风温度：<input type="text" id="minBackWindTemp2" name="minBackWindTemp" /></span>
          <span>最高回风温度：<input type="text" id="maxBackWindTemp2" name="maxBackWindTemp" /></span>
          <span>最低箱内湿度：<input type="text" id="minTankHum2" name="minTankHum" /></span>
          <span>最高箱内湿度：<input type="text" id="maxTankHum2" name="maxTankHum" /></span>
          <span>最低油位：<input type="text" id="minOilLevel2" name="minOilLevel" /></span>
          <span>最高油位：<input type="text" id="maxOilLevel2" name="maxOilLevel" /></span>
          <span>gps设备最低电压：<input type="text" id="minGpsBatVol2" name="minGpsBatVol" /></span>
          <span>gps设备最高电压：<input type="text" id="maxGpsBatVol2" name="maxGpsBatVol" /></span>
          <span>最低环境温度：<input type="text" id="minEnviTemp2" name="minEnviTemp" /></span>
          <span>最高环境温度：<input type="text" id="maxEnviTemp2" name="maxEnviTemp" /></span>
          <span>最低出风温度：<input type="text" id="minChuWindTemp2" name="minChuWindTemp" /></span>
          <span>最高出风温度：<input type="text" id="maxChuWindTemp2" name="maxChuWindTemp" /></span>
          <span>最低尾部箱温：<input type="text" id="minTailBoxTemp2" name="minTailBoxTemp" /></span>
          <span>最高尾部箱温：<input type="text" id="maxTailBoxTemp2" name="maxTailBoxTemp" /></span>
          <span>gps设备最低电压：<input type="text" id="minGpsBatVol2" name="minGpsBatVol" /></span>
          <span>gps设备最高电压：<input type="text" id="maxGpsBatVol2" name="maxGpsBatVol" /></span>
          <span>远程开关机：<input type="text" id="remoteSwiMac2" name="remoteSwiMac" /></span>
          <span>设定温度：<input type="text" id="setTemp2" name="setTemp" /></span>
          <span>启动除霜：<input type="text" id="bootDef2" name="bootDef" /></span>
          <span>清除警告：<input type="text" id="clearAlert2" name="clearAlert" /></span>
          <span>自检功能：<input type="text" id="selfCheck2" name="selfCheck" /></span>
          <span>冷机运行模式：<input type="text" id="refRunMode2" name="refRunMode" /></span>
          <span>新风门模式：<input type="text" id="newWindDoorMode2" name="newWindDoorMode" /></span> 
          <span>所属公司：<input type="text" id="companyId2" name="companyId" disabled="disabled"/></span>-->
				<p class="footer__btn">
					<!-- <input type="submit" value="保存" onclick="update()" /> -->
					<input type="submit" value="删除" onclick="dell()" id = "forRoleDelete"/>
				</p>
			</div>
			</main>
		</div>
		<!-- 新增冷藏箱 -->
		<div class="boxManage__wrap manage__add">
			<header class="con__header">
			<ul>
				<li class="con__header--active">新增冷藏箱</li>
			</ul>
			</header>
			<main class="con__main">
			<div class="boxManage__main--add">
				<span class="boxManage__main--header">新增冷藏箱信息</span> <span>冷藏箱编号：<input
					type="text" id="containerId" name="containerId" /></span> <span>设备编号：<input
					type="text" id="deviceId" name="deviceId" /></span> <span>冷机类型：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select id="chillerType" style="width: 150px">
						<option value="carrier">carrier</option>
						<option value="thermoking">thermoking</option>
				</select>
				</span>
				<!-- <span>冷机类型：<input type="text" id="chillerType" name="chillerType" /></span>  -->
				<!--   <span>冷机最低电压：<input type="text" id="minRefBatVol" name="minRefBatVol" /></span>
          <span>冷机最高电压：<input type="text" id="maxRefBatVol" name="maxRefBatVol" /></span>
          <span>最低回风温度：<input type="text" id="minBackWindTemp" name="minBackWindTemp" /></span>
          <span>最高回风温度：<input type="text" id="maxBackWindTemp" name="maxBackWindTemp" /></span>
          <span>最低箱内湿度：<input type="text" id="minTankHum" name="minTankHum" /></span>
          <span>最高箱内湿度：<input type="text" id="maxTankHum" name="maxTankHum" /></span>
          <span>最低油位：<input type="text" id="minOilLevel" name="minOilLevel" /></span>
          <span>最高油位：<input type="text" id="maxOilLevel" name="maxOilLevel" /></span>
          <span>gps设备最低电压：<input type="text" id="minGpsBatVol" name="minGpsBatVol" /></span>
          <span>gps设备最高电压：<input type="text" id="maxGpsBatVol" name="maxGpsBatVol" /></span>
          <span>最低环境温度：<input type="text" id="minEnviTemp" name="minEnviTemp" /></span>
          <span>最高环境温度：<input type="text" id="maxEnviTemp" name="maxEnviTemp" /></span>
          <span>最低出风温度：<input type="text" id="minChuWindTemp" name="minChuWindTemp" /></span>
          <span>最高出风温度：<input type="text" id="maxChuWindTemp" name="maxChuWindTemp" /></span>
          <span>最低尾部箱温：<input type="text" id="minTailBoxTemp" name="minTailBoxTemp" /></span>
          <span>最高尾部箱温：<input type="text" id="maxTailBoxTemp" name="maxTailBoxTemp" /></span>
          <span>gps设备最低电压：<input type="text" id="minGpsBatVol" name="minGpsBatVol" /></span>
          <span>gps设备最高电压：<input type="text" id="maxGpsBatVol" name="maxGpsBatVol" /></span>
          <span>远程开关机：<input type="text" id="remoteSwiMac" name="remoteSwiMac" /></span>
          <span>设定温度：<input type="text" id="setTemp" name="setTemp" /></span>
          <span>启动除霜：<input type="text" id="bootDef" name="bootDef" /></span>
          <span>清除警告：<input type="text" id="clearAlert" name="clearAlert" /></span>
          <span>自检功能：<input type="text" id="selfCheck" name="selfCheck" /></span>
          <span>冷机运行模式：<input type="text" id="refRunMode" name="refRunMode" /></span>
          <span>新风门模式：<input type="text" id="newWindDoorMode" name="newWindDoorMode" /></span>
          <span>所属公司：<input type="text" id="companyId" name="companyId"/></span> -->

				<p class="footer__btn">
					<input type="submit" value="保存" onclick="regist()" /> <input
						type="submit" value="删除" />
				</p>
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
	// 测试隐藏按钮（权限）
	$(document).ready(function(){
	var role1 =<%=role1%>;
	if(role1 == 1){
		 $("#forRoleDelete").hide();
		 $("#showforRole").hide();
	}
});
	
	
		//删除冷藏箱
		function dell() {
			var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
			var containerId = $('#containerId2').val();
			var deviceId = $('#deviceId2').val();
			/* alert(deviceId); */
			var companyName = $('#companyyName2').val();

			$
					.ajax({
						url : '${PATH }/pc/Container/containerDelete.do',
						type : 'post',
						data : {
							'containerId' : containerId,
							'deviceId' : deviceId,
							'companyName' : companyName
						},
						dataType : 'JSON',
						success : function(result) {
							var field = result.state;
							if (field == '0') {
								var num = Math.random();
								layer.msg(result.message);
								location
										.replace('${PATH}/pc/Container/findContainers.do');
								return;
							}
							layer.msg(result.message);
						}
					});
		}

		//添加冷藏箱
		function regist() {
			var role1 =<%=role1%>;
				if(role1 == 1){
					return;
				}
			var containerId = $('#containerId').val();
			var deviceId = $('#deviceId').val();
			var chillerType = $('#chillerType').val();
			var companyName = $('#companyName').val();

			$
					.ajax({
						url : '${PATH }/pc/Container/containerRegist.do',
						type : 'post',
						data : {
							'containerId' : containerId,
							'deviceId' : deviceId,
							'chillerType' : chillerType,
							'companyName' : companyName
						},
						dataType : 'JSON',
						success : function(result) {
							var field = result.state;
							if (field == '0') {
								var num = Math.random();
								layer.msg(result.message);
								location
										.replace('${PATH}/pc/Container/findContainers.do');
								return;
							}
							layer.msg(result.message);
						}
					});
		}

		//更新冷藏箱
		function update() {
			var containerId = $('#containerId2').val();
			var deviceId = $('#deviceId2').val();
			var companyName = $('#companyName2').val();

			$
					.ajax({
						url : '${PATH }/pc/Container/containerUpdate.do',
						type : 'post',
						data : {
							'containerId' : containerId,
							'deviceId' : deviceId,
							'companyName' : companyName
						},
						dataType : 'JSON',
						success : function(result) {
							var field = result.state;
							if (field == '0') {
								var num = Math.random();
								layer.msg(result.message);
								location
										.replace('${PATH}/pc/Container/findContainers.do');
								return;
							}
							layer.msg(result.message);
						}
					});
		}
		
		//回车事件绑定
	    $("#containerId3").on('keyup', function(event) {
	        if (event.keyCode == "13") {
	            //回车执行查询
	          	search();
	        }
	    });
		
		//模糊查询冷藏箱
		function search() {
		var containerId3 = $("#containerId3").val();
		containerId4 = containerId3.replace(/\s*/g, "");
		$.ajax({
			url : "${PATH}/pc/Container/findContainersLikey.do",
			data : {
				"containerId" : containerId4
			},
			type : "post",
			success : function(result) {
				$("#contentContainers").html(result);
			}
		});
	}
	</script>

</body>
</html>