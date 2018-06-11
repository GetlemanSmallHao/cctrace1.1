<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下货站管理</title>

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
  <!--现有下货站列表-->
  <div class="con__xhzManage bindEvent clearfix">
    <div class="xhzManage__wrap">
      <header class="con__header">
        <ul>
          <li data-con="${PATH }/data/xhzManage.html" class="con__header--active" style="background:none;">现有下货站</li>
        </ul>
       
        <a class="con__header--add" href="javascript:;" style="color: #333;
    background: -webkit-linear-gradient(top, #fff, #e3e3e3);background: -o-linear-gradient(top, #fff, #e3e3e3);
    background: -moz-linear-gradient(top, #fff, #e3e3e3);background: linear-gradient(top, #fff, #e3e3e3);
    border-color: #e3e3e3; padding: 0px 20px;">
          新增下货站
        </a>
      </header>
      <main class="con__main" style="overflow:auto;">
        <!-- 下货站列表信息 -->
         <table>
            <thead>
            <tr>
                <td><div class="leftDown--div1">序号</div></td>
                <td><div class="leftDown--div2">下货站名称</div></td>
                <td><div class="leftDown--div3">公司名称</div></td>
            </tr>
            </thead>
            <tbody>
            	<c:forEach var="allTheNextStation" items="${allTheNextStations}" varStatus="status">
            		<tr>
            			<td><div class="leftDown--div1">${status.index+1}</div></td>
		                <td style="display:none;"><div class="leftDown--div1">${allTheNextStation.id}</div></td>
		                <td><div class="leftDown--div2">${allTheNextStation.stationName}</div></td>
		                <td>
         						<c:if test="${allTheNextStation.companyId==1}">
		         					<div class="leftDown--div2">郑州陆港</div>
		         				</c:if>
         				</td>
		            </tr>
            	</c:forEach>
            		
            </tbody>
         </table>
        
      <%--   <img style="display:block;" class="con__loading" src="${PATH }/img/loading.gif" alt=""/> --%>
      </main>
    </div>
  </div>
  <!--当前下货站信息-->
  <div class="con__xhzManage--menu bindEvent clearfix">
    <div class="xhzManage__wrap xhzManage__amend">
      <header class="con__header">
        <ul>
          <li class="con__header--active">现有下货站</li>
        </ul>
      </header>
      <main class="con__main">
        <div class="xhzManage__main--amend">
          <span class="dcManage__main--header">修改（删除）下货站信息</span>
          <span>下货站名称：<input type="text" id="stationName2" name="stationName" /></span>
          <span><input type="hidden" id="xhzId" name="id"  /></span>
         
          <p class="footer__btn">
            <input class="btn--delete" type="submit" onclick="xhzdelete()"value="删除"/>
            <input type="submit" onclick="update()" value="更新"/>
          </p>
        </div>
      </main>
    </div>
    <div class="xhzManage__wrap manage__add">
      <header class="con__header">
        <ul>
          <li class="con__header--active">新增下货站</li>
        </ul>
      </header>
      <main class="con__main">
        <div class="xhzManage__main--add">
          <span class="xhzManage__main--header">新增下货站信息</span>
          <span>下货站名称：<input type="text" id="stationName" name="stationName"/></span>
          <p class="footer__btn">
           <!--  <input type="submit" value="删除"/> -->
            <input type="submit" onclick="regist()" value="保存"/>
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
  	//添加下货站
	function regist(){
	
		var stationName = $('#stationName').val();
		
		$.ajax({
			url:'${PATH}/pc/xhzRegist.do',
			type: 'post',
			data: {'stationName':stationName},
			dataType: 'JSON',
			success: function(result){
				var field = result.state;
				if(field=='0'){
					var num=Math.random(); 
					layer.msg(result.message);
					location.replace('${PATH}/pc/getXhzInfo.do'); 
					return;
				}
					layer.msg(result.message);
					
			}
		});
	}
	//更新下货站
	function update(){

		var xhzId = $('#xhzId').val();
		var stationName = $('#stationName2').val();		
	
		$.ajax({
			url:'${PATH }/pc/xhzUpdate.do',
			type: 'post',
			data: {'xhzId':xhzId,'stationName':stationName} ,
			dataType: 'JSON',
			success: function(result){
				var field = result.state;
				if(field=='0'){
					var num=Math.random(); 
					layer.msg(result.message);
					location.replace('${PATH}/pc/getXhzInfo.do'); 
					return;
				}
					layer.msg(result.message);
			}
		});
   }
	//删除下货站
	function xhzdelete(){

	
		var stationName = $('#stationName2').val();
	
		$.ajax({
			url:'${PATH }/pc/xhzDelete.do',
			type: 'post',
			data: {'stationName':stationName} ,
			dataType: 'JSON',
			success: function(result){
				var field = result.state;
				if(field=='0'){
					var num=Math.random(); 
					layer.msg(result.message);
					location.replace('${PATH}/pc/getXhzInfo.do'); 
					return;
				}
					layer.msg(result.message);
			}
		});
   }
  
</script>



</body>
</html>