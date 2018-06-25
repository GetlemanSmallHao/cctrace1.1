<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:forEach var="user" items="${userListLikey}" varStatus="status" >
         	<tr>
               <td><div class="leftDown--div1" >${status.index+1}</div></td>
               <td style="display:none" class="btn--changeUser"><div class="leftDown--div1" >${user.id}</div></td>
               <td><div class="leftDown--div2">${user.username}</div></td>
               <td><div class="leftDown--div3">${user.lastLoginTime}</div></td>
               <td><div class="leftDown--div3">${user.role}</div></td>
               <td><div class="leftDown--div3">${user.phone}</div></td>
               <td><div class="leftDown--div3">${user.companyId}</div></td>
               <td><button onclick="userDelete()">删除</button></td>
           </tr>
</c:forEach>