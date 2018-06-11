<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:forEach var="ccdata" items="${ccdatas}" varStatus="status">
	<tr>
		<td><div class="leftDown--div1">${status.index+1}</div></td>
		<td><div class="containerId">${ccdata.containerId}</div></td>
	</tr>
</c:forEach>