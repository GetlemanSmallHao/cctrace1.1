<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.cctrace.entity.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户页面</title>
<%
	pageContext.setAttribute("PATH", request.getContextPath());
	User user = (User)request.getSession().getAttribute("user");
	String usernamenow = user.getUsername();
%> 
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/cctrace1.0
 -->
  <link rel="stylesheet" href="${PATH}/css/index.css"/>
  
  
</head>
<body>
<!-- 导航 -->
<jsp:include page="header.jsp" flush="true"></jsp:include>

<main class="main">
    <!--左边用户列表-->
    <div class="con__user clearfix">
        <div class="user__wrap">
            <header class="con__header">
                <%--data-con="${PATH}/data/userList.html" 
             
                --%>
                <ul>
                    <li class="con__header--active con__header_none">用户</li>
                </ul> 
               
                <a href="${PATH}/user/userRegist.do" class="btn--createUser" style="color: #333;
    background: -webkit-linear-gradient(top, #fff, #e3e3e3);background: -o-linear-gradient(top, #fff, #e3e3e3);
    background: -moz-linear-gradient(top, #fff, #e3e3e3);background: linear-gradient(top, #fff, #e3e3e3);
    border-color: #e3e3e3; padding: 2px 20px;">创建新用户</a>
            </header>
            <main class="con__main">
             <%--  用户列表
              <img style="display:block;" class="con__loading" src="${PATH}/img/loading.gif" alt=""/> --%>
              <span>
						查询用户<input type="text" id="usernameSearch" name="username" />
						<input type="button" id="search" value="查询" onclick="searchUser();"/>
              </span>
           		 <div>
				    <div class="con__main__table">
				        <table>
				            <thead>
				            <tr>
				                <td><div class="leftDown--div1">序号</div></td>
				                <td><div class="leftDown--div2">用户账户</div></td>
				                <td><div class="leftDown--div3">上次登陆时间</div></td>
				                <td><div class="leftDown--div3">用户级别</div></td>
				                <td><div class="leftDown--div3">电话</div></td>
				                <td><div class="leftDown--div3">公司</div></td>
				                <td><div class="leftDown--div3">操作</div></td>
				            </tr>
				            </thead>
				            <!--  -->
				            <tbody id="contentUser">
				            	<c:forEach  var="user" items="${userList}" varStatus="status" >
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
				            </tbody>
				           <%--  <tbody>
				            	<c:forEach  var="user" items="${userListLikey}" varStatus="status">
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
				            	</c:forEach> --%>
				            </tbody>
				        </table>
				    </div>
				
				</div>
				<!-- <img class="con__loading" src="img/loading.gif" alt=""/> -->
				           		
            </main>
        </div>
    </div>
    <!--右边用户详细信息-->
    <div class="con__userDetails clearfix">
        <div class="user__wrap">
            <header class="con__header">
                <ul>
                    <li class="con__header--active con__header_none">用户</li>
                </ul>
            </header>
            <main class="con__main">
                <!--默认页面-->
                <div class="con__main--defaultUser">
                    <header class="maintain--user">
                        <div></div>
                    </header>
                    <div>
                        <p></p>
                        <p>
                           <a href="${PATH}/user/userRegist.do"  class="btn--createUser">创建新用户</a>
                        </p>
         
                    </div>
                </div>
                <!--创建新用户-->
                <div class="hide con__main--createUser">
                    <header class="found--user">
                        <div>创建新用户</div>
                    </header>
                    <div class="found__user--name">
                        <span>用户名称：</span>
                        <input type="text" id="username" name="username" class="active"/>
                    </div>
                    <div>
                        <span>密码：</span>
                        <input type="password" id="password" name="password"/>
                    </div>
                    <div>
                        <span>确认密码：</span>
                        <input type="password" id="passwordc" name="passwordc"/>
                    </div>
                    <div>
                        <span>电话：</span>
                        <input type="text" id="phone" name="phone"/>
                       
                        
                    </div>
                    <div>
                        <span>角色：</span>
                        <select  id="role" name="role">
						  <option  value ="admin">admin</option>
						  <option  value ="common" selected="selected">common</option>
					    </select>
                        
                    </div>
                     
                   
                   <!--  
                   <div>
                        <span>用户级别：</span>
                        <select>
                            
                            <option value="1">common</option>
                        </select>
                    </div> 
                    -->
                    <div class="footer__btn clearfix">
                            <button class="btn--reset"  >重置</button>
                            <button class="btn--save" onclick="regist()">保存</button>
                    </div>
                </div>
                <!--修改用户信息-->
                <div class="con__main--changeUser">
                	<!--修改用户信息-->
					<header class="found--user">
					    <div>修改用户信息</div>
					</header>
					<div>
					    <span>用户id:</span>
					    <b id="userid2">2067</b>
					</div>
					<div>
					    <span>用户名称:</span>
					    <b id="username2"></b>
					</div>
					<div>
					    <span>密码:</span>
					    <b>*********</b>
					    <a href="javascript:;" class="change_Password">变更密码</a>
					</div>
					<div>
					    <span>最后一次登录时间：</span>
					    <b></b>
					</div>
					<!-- <div>
					    <span>修改名称:</span>
					    <input readonly="true" type="text" id="username2" name="username"/>
					</div> -->
					<div>
					    <span>用户级别:</span>
					    <input readonly="true" class="clickModal" type="text">
					    <!-- <select  id="role2" name="role" class="con_main--userGrade">
						  <option  value ="admin">admin</option>
						  <option  value ="common" selected="selected">common</option>
					    </select> -->
					</div>
					<div>
					    <span>电话</span>
					    <input class="clickModal" readonly="true" type="text" />
					</div>
					<!-- <div class="footer__btn clearfix">
					    <button class="btn--delete" onclick="userDelete()">删除</button>
					    <button class="btn--save " onclick="update()">更新</button>
					</div> -->
					<!--修改密码框-->
					<div class="changeUser--password">
					    <p class="found--user">修改密码</p>
					    <p>
					        <span>密码：</span>
					        <input type="password" id="password2" name="password"/>
					    </p>
					    <p>
					        <span>确认密码：</span>
					        <input type="password" id="passwordc2" name="passwordc"/>
					    </p>
					    <p class="changeUser--password--btn">
					        <button onclick="updateWithPassword()">保存</button>
					        <button class="changeUser__btn--cancel">取消</button>
					    </p>
					</div>
                </div>
                <!-- 修改模态框  3个  -->
				<!-- <div class="con__modal">
					<div class="con__modal--wrap" style="min-height: 140px; margin-top: -77px; margin-left: 130px;">
						<span>
							修改用户名称：<input type="text"/>
						</span>
						<span class="con__modal--btn">
							<button>保 存</button>
							<button class="con__modal--cancel rt">取 消</button>
						</span>
					</div>
				</div> -->
				<div class="con__modal">
					<div class="con__modal--wrap" style="min-height: 140px; margin-top: -77px; margin-left: 130px;">
						<span>
							修改用户级别：<select  id="role2" name="role" class="clickModal con_main--userGrade">
					        <option value="1">admin</option>
					        <option value="2">common</option>
					    </select>
						</span>
						<span class="con__modal--btn">
							<button onclick="updateWithRole()">保 存</button>
							<button class="con__modal--cancel rt">取 消</button>
						</span>
					</div>
				</div>
				<div class="con__modal">
					<div class="con__modal--wrap" style="min-height: 140px; margin-top: -77px; margin-left: 130px;">
						<span>
							修改电话：<input type="text" id="phone2" name="phone"/>
						</span>
						<span class="con__modal--btn">
							<button onclick="updateWithPhone()">保 存</button>
							<button class="con__modal--cancel rt">取 消</button>
						</span>
					</div>
				</div>
            </main>
        </div>
    </div>
</main>
<script src="${PATH}/js/jquery-3.2.1.js"></script>
<script src="${PATH}/js/index.js"></script>
<script src="${PATH}/js/layer/layer.js"></script>
<script src="${PATH}/js/layer/extend/layer.ext.js"></script>
<script type="text/javascript">
  	//用户注册
	function regist(){
	 
		var username = $('#username').val();
		var password = $('#password').val();
		var passwordc =$('#passwordc').val();
		var phone =$('#phone').val();
		var role =$('#role').val();
		
		$.ajax({
			url:'${PATH}/user/userRegist.do',
			type: 'post',
			data: {'username':username,'password':password,'passwordc':passwordc,'phone':phone,'role':role} ,
			dataType: 'JSON',
			success: function(result){
				var field = result.state;
				if(field=='0'){
					var num=Math.random(); 
					layer.msg(result.message);
					location.replace('${PATH}/user/findUser.do'); 
					return;
				}
					layer.msg(result.message);
					$("#username").val("").focus();
					
					
			}
		});
	}
	//用户更新（角色）
	function updateWithRole(){
		var id =$('#userid2').text();
		var username = $('#username2').text();
		
        var nodeSel1=document.getElementById("role2"); //获取select元素
        var index = nodeSel1.selectedIndex; // 选中项的索引
        var role = nodeSel1.options[index].value; // 选中项的值
		console.log(role);
		$.ajax({
			url:'${PATH}/user/userUpdateWithRole.do',
			type: 'post',
			data: {'id':id,'username':username,'role':role} ,
			dataType: 'JSON',
			success: function(result){
				var field = result.state;
				if(field=='0'){
					var num=Math.random(); 
					layer.msg(result.message);
					location.replace('${PATH}/user/findUser.do'); 
					return;
				}
					layer.msg(result.message);
					
			}
		});
	}
	//用户更新（密码）
	function updateWithPassword(){
		var id =$('#userid2').text();
		var username = $('#username2').text();
		var password =$('#password2').val();
		var passwordc =$('#passwordc2').val();
		var usernamenow = '<%=usernamenow%>';
	/* 	alert(usernamenow); */
		
		
		$.ajax({
			url:'${PATH}/user/userUpdateWithPassword.do',
			type: 'post',
			data: {'id':id,'username':username,'password':password,'passwordc':passwordc} ,
			dataType: 'JSON',
			success: function(result){
				var field = result.state;
				if(field=='0'){
					var num=Math.random(); 
			
					if(usernamenow==username){
						/* layer.msg("修改密码成功，请重新登陆"); */
						alert("修改密码成功，请重新登陆");
						location.replace('${path}/cctrace1.1/pc/login.jsp');
					}
					else{
						alert("修改密码成功!");
						location.replace('${PATH}/user/findUser.do'); 
					}
					
				 	
					
					return;
				}
					layer.msg(result.message);
					
			}
		});
	}
	//用户更新（电话）
	function updateWithPhone(){
		var id =$('#userid2').text();
		var username = $('#username2').text();
	
		var phone =$('#phone2').val();
		
		
		$.ajax({
			url:'${PATH}/user/userUpdateWithPhone.do',
			type: 'post',
			data: {'id':id,'username':username,'phone':phone} ,
			dataType: 'JSON',
			success: function(result){
				var field = result.state;
				if(field=='0'){
					var num=Math.random(); 
					layer.msg(result.message);
					location.replace('${PATH}/user/findUser.do'); 
					return;
				}
					layer.msg(result.message);
			}
		});
	}
	
	
	//删除用户
	function userDelete(){
	
	var usernamenow1 = '<%=usernamenow%>';
	var usernameselected1 = $(event.target).parents("tr").find("div").eq(2).text();
	if(usernamenow1 == usernameselected1){
		alert("不能删除当前用户");
	}else{
		var flag = confirm("你确定要删除吗？");
		if(flag == 1)
			var cId = $(event.target).parents("tr").find("div").eq(1).text();
			var usernameselected = $(event.target).parents("tr").find("div").eq(2).text();
			if(usernamenow1 != usernameselected){
			console.log(cId);
			$.ajax({
				url:'${PATH}/user/userDelete.do',
				type: 'post',
				data: {'id':cId} ,
				dataType: 'JSON',
				success: function(result){
					var field = result.state;
					if(field=='0'){
						var num=Math.random(); 
						layer.msg(result.message);
						location.replace('${PATH}/user/findUser.do'); 
						return;
					}
						layer.msg(result.message);
						
			}
		});
		}
	}
	
   	}
	function searchUser() {
		var usernameSearch1 = $("#usernameSearch").val();
		usernameSearch2 = usernameSearch1.replace(/\s*/g, "");
		$.ajax({
			url : "${PATH}/user/selectUserLikey.do",
			data : {
				"username" : usernameSearch2
			},
			type : "post",
			success : function(result) {
				$("#contentUser").html(result);
			}
		});
	}
</script>
<script>
	$(".con__main--changeUser").on("click",".clickModal",function(){
		for(var i = 0;i < 2;i++){
			if($(this)[0] == $(".con__main--changeUser .clickModal")[i]){
				$($(".con__modal")[i]).find("input").val($(this).val());
				$($(".con__modal")[i]).show().siblings(".con__modal").hide();
			}
		}	
	});
	$(".con__modal--cancel").click(function(){
		$(this).parents(".con__modal").hide();
	});
</script>
</body>
</html>