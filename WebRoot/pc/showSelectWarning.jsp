<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:choose>
	<c:when test="${empty alerts}">
		<tr>
			<td style="background-color:transparent; color:inherit;">无数据！</td>
		</tr>
	</c:when>
	<c:otherwise>
		<c:forEach items="${alerts}" var="alert" varStatus="status">
			<tr>
				<td><div class="leftDown--div2">${status.index+1}</div></td>
				<td><div class="leftDown--div2">${alert.containerId}</div></td>
				<td><div class="leftDown--div3">${alert.alertTime}</div></td>
				<td><div class="leftDown--div3">${alert.updateTime}</div></td>
				<td><div class="leftDown--div2">${alert.alarm_num }</div></td>
				<td><div class="leftDown--div3">${alert.alertType}</div></td>
				<td><div class="leftDown--div2">${alert.alertContent}</div></td>
				<td><div class="leftDown--div3">${alert.lat}</div></td>
				<td><div class="leftDown--div2">${alert.lon}</div></td>
				<td><div class="leftDown--div2">${alert.gpsPosition}</div></td>
				<td><div class="leftDown--div3 data_readed">
						<c:choose>
							<c:when test="${alert.readed=='no'}">未读</c:when>
							<c:otherwise>已读</c:otherwise>
						</c:choose>
					</div></td>
				<td style="display:none;"><div class="leftDown--div2 data_alert_id">${alert.id}</div></td>	
			</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>