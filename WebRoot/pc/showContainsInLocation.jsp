<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:forEach var="bindTable" items="${bindTables}" varStatus="status">
	<tr>
		<td><div class="leftDown--div1">${status.index+1}</div></td>
		<td><div class="leftDown--div2">${bindTable.containerId}</div></td>
		<td><div class="leftDown--div3">${bindTable.deviceId}</div></td>
		<td><div class="leftDown--div3">${bindTable.bindTime}</div></td>
		<td><div class="leftDown--div3">${bindTable.trainId}</div></td>
	</tr>
</c:forEach>