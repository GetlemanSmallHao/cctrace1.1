<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>GPS围栏</title>
	<%
		pageContext.setAttribute("PATH", request.getContextPath());
	%> 
	<!-- web路径：
	不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
	以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
			http://localhost:3306/cctrace1.0
	 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">


<script  src="http://maps.google.cn/maps/api/js?key=AIzaSyBncQ86ns5F-I5xF5_dE3Gf2_EauuXQXJA&sensor=true">
</script>
<script  type="text/javascript" src="${PATH}/js/jquery-3.2.1.js">
</script>
<script  type="text/javascript">

	function showRail()//初始方法，只显示初始地图
	{	
				//围栏中心纬度
				var rlat;
				//围栏中心经度
				var rlon;
				//围栏半径
				var r;
				var myCenter;
			 rlat= $("#latitude").val();
		     rlon=$("#longitude").val();
		     r=Number($("#R").val());
			myCenter=new google.maps.LatLng(rlat, rlon);//陆港公司
		  
		  
	var mapProp = {//地图属性（中心位置，缩放比例和地图类型）
	  	center:myCenter,
	  	zoom:13,
		zoomControl:true,//如果要修改一个控件，必须将其打开
		zoomControlOptions:{style:google.maps.ZoomControlStyle.DEFAULT},
	 	mapTypeId:google.maps.MapTypeId.ROADMAP
		};	
		var map=new google.maps.Map(document.getElementById("googleMap") ,mapProp);
		var myZone=new google.maps.Circle({
		center:myCenter,
		radius:r,//半径以米为单位
		strokeColor:"#DC143C",
		strokeOpacity:0.8,
		strokeWeight:2,
		fillColor:"#0000FF",
		fillOpacity:0
		//editable:true
		});
	myZone.setMap(map);	
	var marker=new google.maps.Marker({position:myCenter});//设置标记
	marker.setMap(map);//在地图上加载标记
	var infoWindow=new google.maps.InfoWindow({
		content:"中心位置："+myCenter+"半径："+r+"米"
		});
	google.maps.event.addListener(marker,'click',function(){infoWindow.open(map,marker);});
	
	}
	
	google.maps.event.addDomListener(window, 'load', showRail);
	
</script>	
		
</head>

<body>
<!-- <div style="position:absolute;left:90%;top:5%;">
			         		<button id="btn" >全屏显示</button>   
</div>  -->

<div id="googleMap" style="width:100%;height:100%;margin:0 auto;">      
     	
  
            <!--查看地理围栏模态框-->
            <div class="modal fade" id="detail">
                <div class="modal-dialog modal-md">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h2 class="modal-title text-center" >查看地理围栏</h2>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-xs-2 col-xs-offset-1">
                                    <label for="latitude">圆心纬度</label>
                                </div>
                                <div class="col-xs-9">
                                    <input id="latitude" type="text"  value="${rail.railLat }" placeholder="请输入圆心纬度" readonly="true"/>
                                    <span class="sr-only">圆心纬度</span>
                                    <span ></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-2 col-xs-offset-1">
                                    <label for="longitude">圆心经度</label>
                                </div>
                                <div class="col-xs-9">
                                    <input id="longitude"  type="text" value="${rail.railLon }" placeholder="请输入圆心经度" readonly="true"/>
                                    <span class="sr-only">圆心经度</span>
                                    <span></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-2 col-xs-offset-1">
                                    <label for="R">围栏半径(m):</label>
                                </div>
                                <div class="col-xs-9">
                                    <input id="R" type="text" value="${rail.radius }" placeholder="请输入围栏半径" readonly="true"/>
                                    <span class="sr-only">围栏半径</span>
                                    <span></span>
                                </div>
                            </div>
                        </div>
                </div>
            </div>
        </div>
         
</div>
<div style="position:absolute;left:90%;top:5%;">
   		<a href="${PATH }/pc/rail/findHadRailName.do"><button id="btn" >退出显示</button>  </a>
</div> 

     
</body>
</html>
