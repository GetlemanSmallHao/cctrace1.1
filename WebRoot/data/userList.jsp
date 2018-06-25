<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<%
	pageContext.setAttribute("PATH", request.getContextPath());
%> 
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/cctrace1.0
 -->
</head>
<body>
<div>
    <div class="con__main--search">
        用户：<input type="text"/>
        <button>搜索</button>
    </div>
    <div class="con__main__table">
        <table>
            <thead>
            <tr>
                <td><div class="leftDown--div1">序号</div></td>
                <td><div class="leftDown--div2">用户账户</div></td>
                <td><div class="leftDown--div3">Last access time</div></td>
            </tr>
            </thead>
            <tbody class="btn--changeUser">
            <tr>
                <td><div class="leftDown--div1">1</div></td>
                <td><div class="leftDown--div2">CICU9956919</div></td>
                <td><div class="leftDown--div3">星期二 13-06-17 00:53</div></td>
            </tr>
            <tr>
                <td><div class="leftDown--div1">1</div></td>
                <td><div class="leftDown--div2">10:08</div></td>
                <td><div class="leftDown--div3">告警</div></td>
            </tr>
            </tbody>
        </table>
    </div>

</div>
<%-- <img class="con__loading" src="${PATH }/img/loading.gif" alt=""/> --%>

</body>
</html>