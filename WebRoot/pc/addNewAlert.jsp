<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add New Alert</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/test/addNewAlert.do" method="post">
		冷藏箱编号:<input type="text" name="containerId"/>
		告警类型:<input type="text" name="alertType"/>
		告警内容:<input type="text" name="alertContent"/>
		当前经度:<input type="text" name="lon"/>
		当前纬度:<input type="text" name="lat"/>
		<input type="submit" value="提交">
	</form>
</body>
</html>