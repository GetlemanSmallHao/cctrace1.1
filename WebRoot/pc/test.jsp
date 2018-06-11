<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
 	<meta charset="UTF-8"/>
	<title>添加新的中途记录</title>
  </head>
  <body>
	<form action="${pageContext.request.contextPath}/pc/getLocation.do" method="post">
		containerId:<input type="text" name="containerId"><br/>
		deviceId:<input type="text" name="deviceId"><br/>
		gpsBatVol:<input type="text" name="gpsBatVol"><br/>
		backDoorState:<input type="text" name="backDoorState"><br/>
		oilLevel:<input type="text" name="oilLevel"><br/>
		cenBoxTemp:<input type="text" name="cenBoxTemp"><br/>
		lat:<input type="text" name="lat"><br/>
		lon:<input type="text" name="lon"><br/>
		tankHum:<input type="text" name="tankHum"><br/>
		railId:<input type="text" name="railId"><br/>
		railTag:<input type="text" name="railTag"><br/>
		refSwiState:<input type="text" name="refSwiState"><br/>
		refRunMode:<input type="text" name="refRunMode"><br/>
		enviTemp:<input type="text" name="enviTemp"><br/>
		backWindTemp:<input type="text" name="backWindTemp"><br/>
		chuWindTemp:<input type="text" name="chuWindTemp"><br/>
		gspTime:<input type="text" name="gpsTime"><br/>
		receiveTime:<input type="text" name="receiveTime"><br/>
		companyId:<input type="text" name="companyId"><br/>
		<input type="submit" value="提交">		
	</form>
  </body>
</html>

