<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table>
    <thead>
    <tr>
      <td><div class="leftDown--div1">序号</div></td>
      <td><div class="">时间</div></td>
      <td><div class="">箱号</div></td>
      <td><div class="">位置描述（receivetime）</div></td>
    </tr>
    </thead>
    <tbody id="contentContainsInLocation">
	        <c:forEach var="ourCcdata" items="${ourCcdatas}" varStatus="status">
				<tr class="thsPositionClass">
					<td><div class="leftDown--div1">${status.index+1}</div></td>
					<td><div class="leftDown--div3">${ourCcdata.nowTime}</div></td>
					<td><div class="leftDown--div2">${ourCcdata.containerId}</div></td>
					<td><div class="leftDown--div3">${ourCcdata.gpsPosition}</div></td>
				</tr>
		</c:forEach>
	</tbody>
</table>