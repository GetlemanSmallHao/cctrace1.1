<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

						<td><div class="leftDown--div11">${map.ourCcdata.minoilTemp}</div></td>
						<td><div class="leftDown--div12">${map.ourCcdata.maxoilTemp}</div></td>

						<td><div class="leftDown--div13">${map.container.remoteSwiMac}</div></td>
						<td><div class="leftDown--div14">${map.container.setTemp}</div></td>
						<td><div class="leftDown--div14">${map.container.bootDef}</div></td>
						<td><div class="leftDown--div15">${map.container.clearAlert}</div></td>
						<td><div class="leftDown--div14">${map.container.selfCheck}</div></td>
						<td><div class="leftDown--div15">${map.container.refRunMode}</div></td>
						<td><div class="leftDown--div16">${map.ccdata.vecRunTime}</div></td>
						<td><div class="leftDown--div17">${map.ccdata.engRunTime}</div></td>
						<%-- <td><div class="leftDown--div16">${container.newWindDoorMode}</div></td> --%>

					</tr>
				</c:forEach>