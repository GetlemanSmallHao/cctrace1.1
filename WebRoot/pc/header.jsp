<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("PATH", request.getContextPath());
%> 
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/cctrace1.0
 -->
</head>
<body>
<header class="header">
    <div class="header__wrap">
      <%-- <img style="height:39px;" src="${PATH}/img/logo.png" alt=""/> --%>
      <h2 style="float:left; color:#0285F0;font-size: 20px;">陆港冷链温控系统</h2>
      <p>
        <a href="javascript:;">用户名：${sessionScope.user.username }</a>
        <a href="${PATH}/user/loginout.do">退出</a>
      </p>
    </div>
    <nav class="navBar">
    	<div class="navBar__wrap width--1200" style="margin-left:15%;">
    	 <ul>
            <li><a href="${PATH }/pc/index.jsp">首页</a></li>
            <li ><a href="${PATH}/pc/rail/findHadRailName.do">地理围栏</a></li>
            <li ><a href="${PATH}/pc/bindtable/findAllDevices.do">单点定位</a></li>
            <li><a href="${PATH}/pc/bindtable/findAllDevicesForTrack.do">轨迹查询</a></li>
            <li ><a href="${PATH}/pc/sensorTable.jsp">传感器报告 </a></li>
            <!-- 权限 -->
            <c:if test="${sessionScope.user.role=='root'}">
             <li ><a href="${PATH}/user/findUser.do">用户操作</a></li>
            </c:if>
            <%--
            <c:if test="${sessionScope.user.role=='root'||sessionScope.user.role=='admin'||sessionScope.user.role=='common'}">
             <li ><a href="${PATH}/pc/Container/findContainers.do">冷藏箱操作</a></li>
            </c:if>
            --%>
            <c:if test="${sessionScope.user.role=='root'||sessionScope.user.role=='admin'}">
              <li ><a href="${PATH}/pc/getXhzInfo.do">下货站操作 </a></li>
            </c:if>
            
            <c:if test="${sessionScope.user.role=='root'||sessionScope.user.role=='admin'}">
               <li ><a href="${PATH}/pc/getDcInfo.do">堆场操作 </a></li>
            </c:if>
            <li ><a href="${PATH}/pc/showPicture.jsp">温度折线图 </a></li>
            <c:if test="${sessionScope.user.role=='root'||sessionScope.user.role=='admin'}">
               <li><a href="${PATH}/pc/journal.jsp">操作日志 </a></li>
            </c:if>
            <c:if test="${sessionScope.user.role=='root'||sessionScope.user.role=='admin'}">
               <li><a href="${PATH}/pc/alert/getRecentFourDaysAlerts.do">告警信息 </a></li>
            </c:if>
            
          </ul>
     		
    	</div>
  	</nav>
</header>
</body>
</html>