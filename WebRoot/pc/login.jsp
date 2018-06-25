<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
 
<%
	pageContext.setAttribute("PATH", request.getContextPath());
%> 


<%  
    String name="";  
    String psw="";  
    String checked="";  
    Cookie[] cookies=request.getCookies();  
    if(cookies!=null&&cookies.length>0){   
        //遍历Cookie  
        for(int i=0;i<cookies.length;i++){  
            Cookie cookie=cookies[i];  
            //此处类似与Map有name和value两个字段,name相等才赋值,并处理编码问题   
            if("username".equals(cookie.getName()) && cookie.getName()!= null){  
                name=cookie.getValue();  
                //将"记住我"设置为勾选   
                checked="checked";  
            }  
            if("password".equals(cookie.getName()) && cookie.getName()!= null){  
                psw=cookie.getValue();  
            }  
        }  
    }  
 %>  
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/cctrace1.0
 -->
<link rel="stylesheet" href="${PATH}/css/login.css"/>
<script type="text/javascript">
  	//登录页面
	function login(){
		//console.log("Login clicked!")
		var username = $('#username').val();
		var password = $('#password').val();
		var checked5;
		//var verifyCode = $('#verifyCode').val();
		if($('#ifCheck').prop('checked')) {
    		checked5 = "yes";
		}else{
			checked5 = "no";
		}
		$.ajax({
			url:'${PATH}/user/userLogin.do',
			type: 'post',
			data: {'username':username,'password':password,'ifCheck':checked5},
/* 			data: {'username':username,'password':password,'verifyCode':verifyCode,'ifCheck':checked5}, */
			dataType: 'JSON',
			success: function(result){
				var field = result.state;
				if(field=='0'){
					var num=Math.random(); 
					layer.msg(result.message);
					location.replace('${PATH}/pc/lengcangxiangPC.html'); 
					//location.replace('${PATH}/pc/index.jsp'); 
					return;
				}
					layer.msg(result.message);
					
			}
		});
	}
  	/* 验证码  */
  	function changeImage(){
		var imgEle = document.getElementById("img");
		imgEle.src = "${PATH}/verifyCode.do?a="+new Date().getTime();
  	}
 
</script>
</head>

<body>
  <div class="login__wrap">
    <p class="login__logo">
      <img src="${PATH}/img/login_logo.png" alt=""/>
    </p>
    <div class="login__box">
      <p>登录</p>
      <!-- <form class="clearfix" action=""> -->
        <input class="login__box--txt" type="text" id="username" autocomplete="off" name="username"  value="<%=name %>" placeholder="请输入用户名"/>
        <input class="login__box--pwd" type="password" id="password" name="password" autocomplete="off" value="<%=psw %>" placeholder="请输入密码"/>
       <!--  <input class="login__box--yzm" type="text" id="verifyCode"  autocomplete="off" placeholder="请输入验证码"/> -->
		<%--验证码--%>
		<%-- <img style="float: right; margin-top: -39px; margin-right: 5px;" id="img" onclick="changeImage()" src="${pageContext.request.contextPath}/verifyCode.do">       --%>
        <label>
        
          <input type="checkbox"  id = "ifCheck" <%=checked %>/>记住密码
        </label>
        
        <input class="login__box--sub" type="button" onclick="login()" value="登录"/>
     <!--  </form> -->
    </div>
  </div>
  <div class="login__layer">
    <img src="${PATH}/img/1.jpg"/>
    <img src="${PATH}/img/2.jpg"/>
    <img src="${PATH}/img/3.jpg"/>
    <img src="${PATH}/img/4.jpg"/>
  </div>
  <script src="${PATH}/js/jquery-3.2.1.js"></script>
  <script src="${PATH}/js/login.js"></script>
  <script src="${PATH}/js/layer/layer.js"></script>
  <script src="${PATH}/js/layer/extend/layer.ext.js"></script>
  <script type="text/javascript">
  	 //回车事件绑定
		  $(document).keyup(function(event){
		        if (event.keyCode == "13") {
		            //回车执行查询
		           login();
		           // alert(111);
		        }
		    });
  </script>
</body>
 
 

</html>