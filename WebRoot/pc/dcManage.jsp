<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>堆场管理</title>

<%
	pageContext.setAttribute("PATH", request.getContextPath());
%> 
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/cctrace1.0
 -->
<link rel="stylesheet" href="${PATH }/css/index.css"/>
 
</head>
<body>
<!-- 导航 -->
<jsp:include page="header.jsp" flush="true"></jsp:include>
<main class="main">
  <!--现有堆场列表-->
  <div class="con__dcManage bindEvent clearfix">
    <div class="dcManage__wrap">
      <header class="con__header">
        <ul>
          <li data-con="${PATH }/data/dcManage.html" class="con__header--active con__header_none">现有堆场</li>
        </ul>
      
        <a class="con__header--add" href="javascript:;" style="color: #333;
    background: -webkit-linear-gradient(top, #fff, #e3e3e3);background: -o-linear-gradient(top, #fff, #e3e3e3);
    background: -moz-linear-gradient(top, #fff, #e3e3e3);background: linear-gradient(top, #fff, #e3e3e3);
    border-color: #e3e3e3; padding: 0px 20px;">
          新增堆场
        </a>
      </header>
      <main class="con__main" style="overflow:auto;">
       <!-- 堆场列表信息  --> 
         <table>
         	<thead>
         		<tr>
         			<td><div class="leftDown--div1"><font size="3">序号</font></div></td>
         			<td align="center" ><font size="3">堆场名称</font></td>
         		 	<td><div class="leftDown--div3"><font size="3">公司名称</font></div></td>
         		</tr>
         	</thead>
         	<tbody>
         		<c:forEach var ="yard" items ="${yards }" varStatus="status" >
         			<tr>
         				<td><div class="leftDown--div1">${status.index+1}</div></td>
         				<td style="display:none;"><div class="leftDown--div1">${yard.id }</div></td>
         				<td><div class="leftDown--div2">${yard.yardName }</div></td>
         				<td>
         						<c:if test="${yard.companyId==1}">
		         					<div class="leftDown--div2">郑州陆港</div>
		         				</c:if>
         				</td>
         			</tr>
         		</c:forEach>
         	</tbody>
         </table>
     <%--    <img style="display:block;" class="con__loading" src="${PATH }/img/loading.gif" alt=""/> --%>
      </main>
    </div>
  </div>
  <!--当前冷藏箱信息-->
  <div class="con__dcManage--menu bindEvent clearfix">
    <div class="dcManage__wrap dcManage__amend">
      <header class="con__header">
        <ul>
          <li class="con__header--active">现有堆场</li>
        </ul>
      </header>
      <main class="con__main">
        <div class="dcManage__main--amend">
          <span class="dcManage__main--header">修改（删除）堆场信息</span>
          <span>堆场名称：<input type="text" id="yardName2" name="yardName"/></span>
          <span><input type="hidden" id="yardId" name="Id"/></span>
          
          <p class="footer__btn">
            <input class="btn--delete" type="submit"  onclick="dcdelete()" value="删除"/>
            <input type="submit" value="更新" onclick="update()"/>
          </p>
        </div>
      </main>
    </div>
    <div class="dcManage__wrap manage__add" style="display:block;">
      <header class="con__header">
        <ul>
          <li class="con__header--active">新增堆场</li>
        </ul>
      </header>
      <main class="con__main">
        <div class="dcManage__main--add">
          <span class="dcManage__main--header">新增堆场信息</span>
          <span>堆场名称：<input type="text" id="yardName" name="yardName"/></span>
          <p class="footer__btn">
            <input type="submit" value="保存" onclick="regist()"/>
          </p>
        </div>
      </main>
    </div>
  </div>

</main>
<script src="${PATH }/js/jquery-3.2.1.js"></script>
<script src="${PATH }/js/index.js"></script>
<script src="${PATH}/js/layer/layer.js"></script>
<script src="${PATH}/js/layer/extend/layer.ext.js"></script>
<script type="text/javascript">
	//添加堆场
	function regist(){
	
		var yardName = $('#yardName').val();
		
		$.ajax({
			url:'${PATH}/pc/dcRegist.do',
			type: 'post',
			data: {'yardName':yardName},
			dataType: 'JSON',
			success: function(result){
				var field = result.state;
				if(field=='0'){
					var num=Math.random(); 
					layer.msg(result.message);
					location.replace('${PATH}/pc/getDcInfo.do'); 
					return;
				}
					layer.msg(result.message);
					
			}
		});
	}
	
	//更新堆场
	function update(){

		var yardId = $('#yardId').val();
		var yardName = $('#yardName2').val();		
	
		$.ajax({
			url:'${PATH }/pc/dcUpdate.do',
			type: 'post',
			data: {'yardId':yardId,'yardName':yardName} ,
			dataType: 'JSON',
			success: function(result){
				var field = result.state;
				if(field=='0'){
					var num=Math.random(); 
					layer.msg(result.message);
					location.replace('${PATH}/pc/getDcInfo.do'); 
					return;
				}
					layer.msg(result.message);
			}
		});
   }
	
	//删除堆场
	function dcdelete(){

	
		var yardName = $('#yardName2').val();
	
		$.ajax({
			url:'${PATH }/pc/dcDelete.do',
			type: 'post',
			data: {'yardName':yardName} ,
			dataType: 'JSON',
			success: function(result){
				var field = result.state;
				if(field=='0'){
					var num=Math.random(); 
					layer.msg(result.message);
					location.replace('${PATH}/pc/getDcInfo.do'); 
					return;
				}
					layer.msg(result.message);
			}
		});
   }
  

</script>


</body>
</html>