<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>  	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>freshMap</title>	
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <style>
        body{
            margin:0;
        }
    </style>
    
<script  src="http://maps.google.cn/maps/api/js?key=AIzaSyBncQ86ns5F-I5xF5_dE3Gf2_EauuXQXJA&sensor=true"></script>
<script  type="text/javascript" src="js/jquery-3.0.0.js"> </script>
<script>
var myTrip=new Array();
var flightPath=new Array();
var polyLineColor=new Array(["#FF3300"],["#0000FF"],["#333333"]);
var ids=new Array([1],[2],[3]);//存放线路id的数组

var stations=new Array();//存放下货地点的marker
var points=new Array();

//下货地点
points[0]= new google.maps.LatLng(43.2220146,76.8512485);//阿拉木图
points[1]= new google.maps.LatLng(53.199680, 24.423635);//布列斯特
points[2]= new google.maps.LatLng(50.767200, 23.846887);//马瓦谢维切（马拉舍维奇）
points[3]= new google.maps.LatLng(52.573280, 19.338763);//华沙
points[4]= new google.maps.LatLng(53.5510846,9.99368179999999);//汉堡
points[5]= new google.maps.LatLng(43.916356, 116.023823);//二连浩特
points[6]= new google.maps.LatLng(45.277641, 86.282155);//阿拉山口
points[7]= new google.maps.LatLng(34.416339, 115.622852);//郑州
//沿途国家首都
points[8]= new google.maps.LatLng(47.904660, 106.998050);//蒙古首都
points[9]= new google.maps.LatLng(50.389070, 106.104889);//纳乌什基
points[10]= new google.maps.LatLng(54.799057, 32.050765);//斯摩棱斯克
//南线重点坐标
points[11]= new google.maps.LatLng(43.640652, 51.156928);//阿克套
points[12]= new google.maps.LatLng(40.420650, 49.865933);//巴库
points[13]= new google.maps.LatLng(42.167730, 41.667195);//波季
points[14]= new google.maps.LatLng(42.645389, 27.498412);//布尔加斯
points[15]= new google.maps.LatLng(44.438721, 26.093939);//布加勒斯特
//重要沿途地点
points[16]= new google.maps.LatLng(56.8383264,60.6042055);//叶卡捷琳堡
points[17]= new google.maps.LatLng(54.989235,73.323702);//鄂木斯克
points[18]= new google.maps.LatLng(53.258937, 50.217262);//萨马拉
points[19]= new google.maps.LatLng(55.843018, 49.091117); //喀山
points[20]= new google.maps.LatLng(51.785510, 55.088648);//奥伦堡
points[21]= new google.maps.LatLng(54.204262, 45.171873);//萨兰斯克
</script>

<script>
var ims=new Array();//存放下货点的marker图片
//下货地点图片
ims[0]='images/zhandian/almt.png';
ims[1]='images/zhandian/blst.png';
ims[2]='images/zhandian/mlswq.png';
ims[3]='images/zhandian/hs.png';
ims[4]='images/zhandian/hb.png';
ims[5]='images/zhandian/elht.png';
ims[6]='images/zhandian/alsk.png';
ims[7]='images/zhandian/zz.png';
//沿途国家首都图片
ims[8]='images/shoudu/mg.png';
ims[9]='images/shoudu/nwsj.png';
ims[10]='images/shoudu/smlsk.png';
//南线重点坐标图片
ims[11]='images/southline/akt.png';
ims[12]='images/southline/bk.png';
ims[13]='images/southline/bj.png';
ims[14]='images/southline/bejs.png';
ims[15]='images/southline/bjlst.png';
//重要沿途地点图片
ims[16]='images/yantu/ykjlb.png';
ims[17]='images/yantu/emsk.png';
ims[18]='images/yantu/sml.png';
ims[19]='images/yantu/ks.png';
ims[20]='images/yantu/alb.png';
ims[21]='images/yantu/slsk.png';

//虚线样式
  var lineSymbol = {
    path: 'M 0,-1 0,1',
    strokeOpacity: 1,
    scale: 4
  };
  

function initializeMap()//初始方法，只显示初始地图
{	//路线一
	var zuobiao1=new Array();
	var latStr1 = $("#lat1").val();
	var b1 = latStr1.replace(/^\[(.*)\]$/, "\ $1\ ");
	var lat1 = b1.split(",");
	var lonStr1 = $("#lon1").val();
	var a1 = lonStr1.replace(/^\[(.*)\]$/, "\ $1\ ");
	var lon1 = a1.split(",");
	for(var i=0; i<lat1.length; i++){
		zuobiao1[i] = new google.maps.LatLng(lat1[i],lon1[i]);
	}
	//路线二
	var zuobiao2=new Array();
	var latStr2 = $("#lat2").val();
	var b2 = latStr2.replace(/^\[(.*)\]$/, "\ $1\ ");
	var lat2 = b2.split(",");
	var lonStr2 = $("#lon2").val();
	var a2 = lonStr2.replace(/^\[(.*)\]$/, "\ $1\ ");
	var lon2 = a2.split(",");
	for(var i=0; i<lat2.length; i++){
		zuobiao2[i] = new google.maps.LatLng(lat2[i],lon2[i]);
	}
	//路线三
	var zuobiao3=new Array();
	var latStr3 = $("#lat3").val();
	var b3 = latStr3.replace(/^\[(.*)\]$/, "\ $1\ ");
	var lat3 = b3.split(",");
	var lonStr3 = $("#lon3").val();
	var a3 = lonStr3.replace(/^\[(.*)\]$/, "\ $1\ ");
	var lon3 = a3.split(",");
	for(var i=0; i<lat3.length; i++){
		zuobiao3[i] = new google.maps.LatLng(lat3[i],lon3[i]);
	}
	myTrip.push(zuobiao1);
	myTrip.push(zuobiao2);
	myTrip.push(zuobiao3);
	
var ini=new google.maps.LatLng(43.2220146,76.8512485);//中心位置阿拉木图
var MinZoomLevel=4;
var mapProp = {//地图属性（中心位置，缩放比例和地图类型）
  	center:ini,
  	zoom:MinZoomLevel,
	zoomControl:true,//如果要修改一个控件，必须将其打开
	zoomControlOptions:{style:google.maps.ZoomControlStyle.DEFAULT},
	scaleControl:true,
 	mapTypeId:google.maps.MapTypeId.ROADMAP
	};	
//创建一个map，并放在id为googleMap的div中，相关属性为mapProp	
var map=new google.maps.Map(document.getElementById("googleMap") ,mapProp);
//控制地图最小缩放比例
 google.maps.event.addListener(map, 'zoom_changed',function() {
      if (map.getZoom() < MinZoomLevel) map.setZoom(MinZoomLevel);
       });
 
//重要地点显示标记
for(var i=0,j=0;i<points.length,j<ims.length;i++,j++){
	 stations[i]=new google.maps.Marker({
     position:points[i],
     icon:ims[j] 	 
	 });
	 stations[i].setMap(map);
	} 

//固定的三条线 
for(var i=0;i<myTrip.length;i++){
if(i==2){
	flightPath[i]=new google.maps.Polyline({//创建线性叠加层对象
	path:myTrip[i],//路径
	strokeColor:polyLineColor[i%3],//线条颜色
	strokeOpacity:0,//线条透明度
	strokeWeight:4,//线条宽度
	icons: [{
      icon: lineSymbol,
      offset: '0',
      repeat: '20px'
    }]
  });
}else{
	flightPath[i]=new google.maps.Polyline({//创建线性叠加层对象
	path:myTrip[i],//路径
	strokeColor:polyLineColor[i%3],//线条颜色
	strokeOpacity:0.8,//线条透明度
	strokeWeight:4,//线条宽度
	});
}	
flightPath[i].setMap(map);
}
}

function xianshi(){
	//路线一
	var zuobiao1=new Array();
	var latStr1 = $("#lat1").val();
	var b1 = latStr1.replace(/^\[(.*)\]$/, "\ $1\ ");
	var lat1 = b1.split(",");
	var lonStr1 = $("#lon1").val();
	var a1 = lonStr1.replace(/^\[(.*)\]$/, "\ $1\ ");
	var lon1 = a1.split(",");
	for(var i=0; i<lat1.length; i++){
		zuobiao1[i] = new google.maps.LatLng(lat1[i],lon1[i]);
	}
	//路线二
	var zuobiao2=new Array();
	var latStr2 = $("#lat2").val();
	var b2 = latStr2.replace(/^\[(.*)\]$/, "\ $1\ ");
	var lat2 = b2.split(",");
	var lonStr2 = $("#lon2").val();
	var a2 = lonStr2.replace(/^\[(.*)\]$/, "\ $1\ ");
	var lon2 = a2.split(",");
	for(var i=0; i<lat2.length; i++){
		zuobiao2[i] = new google.maps.LatLng(lat2[i],lon2[i]);
	}
	//路线三
	var zuobiao3=new Array();
	var latStr3 = $("#lat3").val();
	var b3 = latStr3.replace(/^\[(.*)\]$/, "\ $1\ ");
	var lat3 = b3.split(",");
	var lonStr3 = $("#lon3").val();
	var a3 = lonStr3.replace(/^\[(.*)\]$/, "\ $1\ ");
	var lon3 = a3.split(",");
	for(var i=0; i<lat3.length; i++){
		zuobiao3[i] = new google.maps.LatLng(lat3[i],lon3[i]);
	}
	myTrip.push(zuobiao1);
	myTrip.push(zuobiao2);
	myTrip.push(zuobiao3);
	
	
	var myCenters=new Array();
	var markers=new Array(); 
	var infoWindows=new Array();
	var infoWindow;
	
	var times=new Array();
	var ids=new Array();
	var trainIds=new Array();
	var containerIds=new Array();
	var containerTypes=new Array();
	var routeTypes=new Array();
	
	var latstr = $("#lats").val();
	var lats = latstr.replace(/^\[(.*)\]$/, "\ $1\ ").split(",");
	
	var lonstr = $("#lons").val();
	var lons = lonstr.replace(/^\[(.*)\]$/, "\ $1\ ").split(",");
	
	var addstr = $("#address").val();
	var adds = addstr.replace(/^\[(.*)\]$/, "\ $1\ ").split(",");	
	
	var trainIdstr = $("#trainIds").val();
	var trainIds = trainIdstr.replace(/^\[(.*)\]$/, "\ $1\ ").split(",");
	
	var containerIdstr = $("#containerIds").val();
	var containerIds = containerIdstr.replace(/^\[(.*)\]$/, "\ $1\ ").split(",");
	
	var containerTypestr = $("#containerTypes").val();
	var containerTypes = containerTypestr.replace(/^\[(.*)\]$/, "\ $1\ ").split(",");
	
	var routeTypestr = $("#routeTypes").val();
	var routeTypes = routeTypestr.replace(/^\[(.*)\]$/, "\ $1\ ").split(",");
	
	var timestr = $("#times").val();
	var times = timestr.replace(/^\[(.*)\]$/, "\ $1\ ").split(",");
	
	var idstr = $("#ids").val();
	var ids = idstr.replace(/^\[(.*)\]$/, "\ $1\ ").split(",");
	
	var deviceNamestr = $("#deviceNames").val();
	var deviceNames = deviceNamestr.replace(/^\[(.*)\]$/, "\ $1\ ").split(",");
	
	for(var i=0; i<lats.length; i++){
		var myCenter=new google.maps.LatLng(lats[i],lons[i]);
		myCenters.push(myCenter);
	}
	
	var zx=new google.maps.LatLng(51.341897, 61.553867);
	var MinZoomLevel=4;
	//地图属性（中心位置，缩放比例和地图类型）
	var mapProp = {
	  	center:zx,	  	
	  	zoom:MinZoomLevel,
	  	//如果要修改一个控件，必须将其打开
		zoomControl:true,
		zoomControlOptions:{style:google.maps.ZoomControlStyle.DEFAULT},
	 	mapTypeId:google.maps.MapTypeId.ROADMAP
		};
	//创建一个map，并放在id为googleMap的div中，相关属性为mapProp	
	var map=new google.maps.Map(document.getElementById("googleMap") ,mapProp);
	//控制地图最小缩放比例
 	  google.maps.event.addListener(map, 'zoom_changed',function() {
      if (map.getZoom() < MinZoomLevel) map.setZoom(MinZoomLevel);
       });
       
	for(var i=0,j=0;i<points.length,j<ims.length;i++,j++){
		 stations[i]=new google.maps.Marker({
	     position:points[i],
	     icon:ims[j]
	     //zIndex: 10     
		 });
		 stations[i].setMap(map);
		} 
	
	for( i=0,j=0,k=0,l=0,m=0,n=0,t=0,add=0;
		 i<myCenters.length,j<trainIds.length,k<containerIds.length,l<containerTypes.length,m<routeTypes.length,n<ids.length,t<times.length,add<adds.length;	 
		 i++,j++,k++,l++,m++,n++,t++,add++){	
		 var tagc;
		 var tagr;
		 var image;
		//根据不同的路线和箱型选取不同的Marker图片
		 if(routeTypes[m]==0&&containerTypes[l]==0){
		 		tagr="去程";
		 		tagc="普通箱";
		 		image='images/trains/qp.gif';
		 }
		 else if(routeTypes[m]==1&&containerTypes[l]==0){
		 	    tagr="回程";
		   		tagc="普通箱";
		 		image='images/trains/hp.gif';
		 }		 		
		 else if(routeTypes[m]==0&&containerTypes[l]==1){
				tagr="去程";
				tagc="冷藏箱";		
				image='images/trains/ql.gif';			
			}   
		 else if(routeTypes[m]==1&&containerTypes[l]==1){
				tagr="回程";	
				tagc="冷藏箱";		
				image='images/trains/hl.gif';	
			}
	
		 markers[i]=new google.maps.Marker({
	     position: myCenters[i],
		 animation: google.maps.Animation.DROP,
		 draggable:true,
		 icon: image
		 });//设置开始标记
			if(ids[n]==""){
				return;
			}
		  infoWindow=new google.maps.InfoWindow({
			content:"设备号："+ids[n]+
			"</br>设备名称："+deviceNames[n]+
			"</br>最新位置:"+myCenters[i]+
			/* "</br>详细位置:"+adds[add]+ */
			"</br>发车日期:"+trainIds[j]+
			"</br>路线类型:"+tagr+
			"</br>所在集装箱号:"+containerIds[k]+
			"</br>时间:"+times[t]
			
	});
	infoWindows.push(infoWindow);
	}
	
	var info="";
	for(i=0;i<markers.length;i++){
		markers[i].setMap(map);
	    info=info+
	    "google.maps.event.addListener(markers["+i+"],'mouseover',function(){infoWindows["+i+"].open(map,markers["+i+"]);});"
	    +
	    "google.maps.event.addListener(markers["+i+"],'mouseout',function(){infoWindows["+i+"].close(map,markers["+i+"]);});"    
	}
	eval(info);
	for(var i=0;i<myTrip.length;i++){
		if(i==2){
		flightPath[i]=new google.maps.Polyline({//创建线性叠加层对象
		path:myTrip[i],//路径
		strokeColor:polyLineColor[i%3],//线条颜色
		strokeOpacity:0,//线条透明度
		strokeWeight:4,//线条宽度
		icons: [{
	      icon: lineSymbol,
	      offset: '0',
	      repeat: '20px'
	    }]
	  });
	}else{
		flightPath[i]=new google.maps.Polyline({//创建线性叠加层对象
		path:myTrip[i],//路径
		strokeColor:polyLineColor[i%3],//线条颜色
		strokeOpacity:0.8,//线条透明度
		strokeWeight:4,//线条宽度
		});
	}	
	flightPath[i].setMap(map);
	}
}
</script>	

<c:choose>
<c:when test="${ids==null }">
    <script>
		google.maps.event.addDomListener(window, 'load', initializeMap);//窗口载入后通过执行 xianshi() 函数来初始化 Map 对象，这样可以确保在页面完全载入后再加载 Google 地图
	</script> 
</c:when>
<c:otherwise>
      <script>
		google.maps.event.addDomListener(window, 'load', xianshi);//窗口载入后通过执行 xianshi() 函数来初始化 Map 对象，这样可以确保在页面完全载入后再加载 Google 地图
	  </script>
</c:otherwise>								
</c:choose>

 </head>
  <body>
	<input name="lat1" id="lat1" value="${lat1 }" type="hidden">
	<input name="lon1" id="lon1" value="${lon1 }" type="hidden">
	<input name="lat2" id="lat2" value="${lat2 }" type="hidden">
	<input name="lon2" id="lon2" value="${lon2 }" type="hidden">
	<input name="lat3" id="lat3" value="${lat3 }" type="hidden">
	<input name="lon3" id="lon3" value="${lon3 }" type="hidden">
	
	<input name="lats" id="lats" value="${lats }" type="hidden">
	<input name="lons" id="lons" value="${lons }" type="hidden">
	<input name="address" id="address" value="${address }" type="hidden">
	<input name="trainIds" id="trainIds" value="${trainIds }" type="hidden">
	<input name="containerIds" id="containerIds" value="${containerIds }" type="hidden">
	<input name="containerTypes" id=containerTypes value="${containerTypes }" type="hidden">
	<input name="routeTypes" id="routeTypes" value="${routeTypes }" type="hidden">
	<input name="times" id="times" value="${times }" type="hidden">
	<input name="ids" id="ids" value="${ids }" type="hidden">
	<input name="deviceNames" id="deviceNames" value="${deviceNames }" type="hidden">
	
   <div id="googleMap" style="width:100%;height:100%;margin:0 auto;">           	
   </div> 
   
  </body>
</html>
