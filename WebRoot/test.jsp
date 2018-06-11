<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增人员</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<link rel="stylesheet" href="css/register.css"/>
<script type="text/javascript" src="js/jquery-3.0.0.js"></script>
<script src="layer/layer.js"></script>
<script src="layer/extend/layer.ext.js"></script>
</head>

<body style='overflow:-Scroll;overflow-x:hidden'>
<div class="register">
	<form action="jieshou.do" method="post">
	区域：<input type="text" name="num" value="" />
	温度：<input type="text" name="setTem" value="" />
	  <input type="submit" name="" value="测试" />
	</form>
</div>
</body>
</html>
