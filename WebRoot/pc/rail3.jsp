<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>地理围栏</title>
<%
	pageContext.setAttribute("PATH", request.getContextPath());
%> 
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/cctrace1.0
 -->
<link rel="stylesheet" href="${PATH }/css/index.css"/>

<!-- 地图插件 -->
<script src="http://maps.google.cn/maps/api/js?key=AIzaSyBncQ86ns5F-I5xF5_dE3Gf2_EauuXQXJA&sensor=false&libraries=places" type="text/javascript"></script>
<script type="text/javascript" src="${PATH }/js/toLatLng.js"></script><!--//地图上显示当前位置经纬度-->
</head>
<body>
  <!--头部-->
  <header class="header">
    <div class="header__wrap">
      <img style="height:39px;" src="${PATH }/img/logo.png" alt=""/>
      <p>
        <a href="javascript:;">用户名</a>
        <a href="javascript:;">退出</a>
      </p>
    </div>
  </header>
  <!--导航-->
  <nav class="navBar">
    <div class="navBar__wrap width--1200">
      导航条个人中心等
    </div>
  </nav>
  <main class="main">
    <!--左边围栏-->
    <div class="con__rail bindEvent clearfix">
      <div class="map__wrap">
        <header class="con__header">
          <ul>
            <li class="con__header--active">地理围栏</li>
          </ul>
        </header>
        <main class="con__main">
          <div id="infoPanel">
            <form class="railLeftMain" method="post" action="${PATH }/pc/rail/insertRail.do">
              <div>
                <span> 围栏中心纬度：</span>
                <input id="lat" name="lat" size="20" type="text" value="" disabled="disabled">
              </div>
              <div>
                <span> 围栏中心经度：</span>
                <input id="lon" name="lon" size="20" type="text" value="" disabled="disabled">
                <input id="railLat" name="railLat" type="hidden" value="">
                <input id="railLon" name="railLon" type="hidden" value="">
              </div>
              <div>
                <span> 围栏半径：</span>
                <input id="radius" name="radius" type="text" value="" required="required" placeholder="请输入以米(m)为单位的数值">
              </div>
              <div>
                <span> 围栏名称：</span>
                <input id="railName" name="railName" type="text" value="" required="required" placeholder="请输入围栏名称">
              </div>
              <input class="railLeft--btn" type="submit" value="增加 ">
            </form>
            <form method="post" action="${PATH }/pc/rail/deleteRail.do">
              <p class="selectRail">
                选择围栏：
                 <select name="railName" id="railName" required="required">
					 <c:forEach var="railList" items="${railList }" varStatus="status">
	                   <option value='${railList.railName }'>${railList.railName  }</option>
	                 </c:forEach> 
	             </select>
                
              </p>
              <input class="railLeft--btn" type="submit" value="删除">
            </form>
            <form method="post" action="${PATH }/pc/rail/findRail.do">
              <input id="raillat" name="raillat" type="hidden" value="">
              <input class="railLeft--btn" type="submit" value="查看 ">
            </form>
            <b style="display:none">当前位置经纬度：</b>
            <div id="info" class="infoDiv" style="display:none"></div>
            <b style="margin-left:10px; color:#5a9bfb;">最佳匹配地址：</b>
            <div class="infoDiv" id="endAddress">等待解析</div>
            <b style="margin-left:10px; color:#5a9bfb;">经纬度范围：</b>
            <div class="infoDiv" id="latLngRange">等待解析</div>
            <b style="margin-left:10px; color:#5a9bfb;">拖动解析指示：</b>
            <div id="markerStatus" class="infoDiv">请点击并拖动地标</div>
          </div>
        </main>
      </div>
    <!--右边围栏地图部分-->
    <div class="con__rail__map bindEvent clearfix">
      <div class="map__wrap">
        <header class="con__header">
          <ul>
            <li>班列</li>
            <li>定位轨迹</li>
            <li class="con__header--active">地理围栏</li>
          </ul>
        </header>
        <main class="con__main">
          <!--地图所在位置  -->
	        <div>
	        	<div tyle="height:20px;" class='searchDiv'>
	            请输入地点：<input id="address" type="textbox" value="" />
	            <input type="button" value=" 解 析 " id="go" onclick="codeAddress()" /> 
	          </div>
	          <div id="mapLoading"><img src="${PATH }/img/MapLoading.gif" /><span>地图加载中。。。</span></div>
	          <div style="height: -moz-calc(100% - 20px); height: -webkit-calc(100% - 20px); height: calc(100% - 20px);" id="map_canvas" style="width:100%;"></div>
	        </div>
	        
        </main>
      </div>
    </div>
  </main>


</body>
</html>