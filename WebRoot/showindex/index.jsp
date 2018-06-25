<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>冷藏箱首页</title>
  <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css"/>
  <link rel="stylesheet" href="css/global.css"/>
  <style>
    .c-hero{
      position:relative;
    }
    .c-hero .c-heroTitle{
      position:absolute;
      top:-moz-calc(50% - 6rem);
      top:-webkit-calc(50% - 6rem);
      top:calc(50% - 6rem);
      z-index:500;
      width:100%;
      text-align:left;
      text-indent:1em;
      font-size:8rem;
      color:#fff;
      font-weight:600;
      text-shadow:3px 3px 5px #333;
    }
    .c-hero #carousel-banner img{
    	width:100%;
    }
    @media (max-width: 992px){
      .c-hero .c-heroTitle{
        font-size:6rem;
        top:-moz-calc(50% - 3rem);
        top:-webkit-calc(50% - 3rem);
        top:calc(50% - 3rem);
      }
    }
    @media (max-width: 768px){
      .c-hero .c-heroTitle{
        font-size:4rem;
        top:-moz-calc(50% - 1.5rem);
        top:-webkit-calc(50% - 1.5rem);
        top:calc(50% - 1.5rem);
      }
    }
    @media (max-width: 450px){
      .c-hero .c-heroTitle{
        font-size:3rem;
        top:-moz-calc(50% - 1rem);
        top:-webkit-calc(50% - 1rem);
        top:calc(50% - 1rem);
      }
    }
  </style>
</head>
<body>
<div class="container-fluid">
  <div class="row" style="position:relative;/* display:flex; display:-webkit-flex;*/">
    <nav class="c-nav navbar-default navbar-static-top" role="navgation">
      <div class="container c-container-1080">
        <div class="navbar-header">
          <a href="index.jsp" class="navbar-brand">
            <img src="img/indexImg/ccLogo.png">
          </a>

          <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>

        <div class="c-navList navbar-collapse collapse pull-right">
          <ul class="navbar-nav nav">
            <li><a href="lengcangxiang.html">产品</a></li>
            <li><a href="zhonghao.html">中浩科技</a></li>
            <li><a href="lianxiwomen.html">联系我们</a></li>
            <li><a href="/cctrace1.1/pc/login.jsp">登录</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="col-lg-12 c-hero">
      <div class="c-heroTitle">智行天下，掌控完美</div>
      <!--<div class="c-hero-bg" style="background:url('img/indexImg/1.png') no-repeat; background-size:cover;"></div>-->
      <div id="carousel-banner" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#carousel-banner" data-slide-to="0" class="active"></li>
          <li data-target="#carousel-banner" data-slide-to="1" class=""></li>
          <li data-target="#carousel-banner" data-slide-to="2" class=""></li>
          <li data-target="#carousel-banner" data-slide-to="3" class=""></li>
        </ol>
        <div class="carousel-inner" role="listbox">
          <div class="item active">
            <img src="img/indexImg/1.png" alt=""/>
          </div>
          <div class="item">
            <img src="img/indexImg/2.png" alt=""/>
          </div>
          <div class="item">
            <img src="img/indexImg/3.png" alt=""/>
          </div>
          <div class="item">
            <img src="img/indexImg/4.png" alt=""/>
          </div>
        </div>
        <!--<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>-->
      </div>
    </div>
  </div>
</div>
<div class="container-fluid">
  <div class="row c-friendLink">
    <!--<div class="col-xs-12" style="color:#848484; text-align:left; padding-left:50px;">友情链接</div>-->
    <div class="col-xs-12 col-sm-6 col-lg-3">
   	  <a href="http://42.228.11.187/transport/public/index/index/index.html" target="_blank">
      	<img src="img/indexImg/lenglian.png" alt=""/>
      </a>
    </div>
    <div class="col-xs-12 col-sm-6 col-lg-3">
      <a href="http://www.thermoking.com.cn/tk-innovation-ap/CN/en.html" target="_blank">
      	<img src="img/indexImg/lengwang.png" alt=""/>
      </a>
    </div>
    <div class="col-xs-12 col-sm-6 col-lg-3">
      <a href="http://www.qdcimc.com/" target="_blank">
      	<img src="img/indexImg/zhongji.png" alt=""/>
      </a>	
    </div>
    <div class="col-xs-12 col-sm-6 col-lg-3">
      <a href="https://www.carrier.com/carrier/en/cn/" target="_blank">
      	<img src="img/indexImg/kaili.png" alt=""/>
      </a>	
    </div>
  </div>
  <div class="footer row">
    <div class="col-lg-12">@2017 郑州陆港开发建设有限公司 版权所有</div>
  </div>
</div>

<script src="js/jquery-3.2.1.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script>
  $(function(){
    $('#carousel-banner').carousel({interval:9000,pause:null});//每隔5秒自动轮播
  });
</script>
</body>
</html>