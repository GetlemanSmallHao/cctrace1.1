<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>GPS围栏</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
<%
	pageContext.setAttribute("PATH", request.getContextPath());
%> 

<script  src="http://maps.google.cn/maps/api/js?key=AIzaSyBncQ86ns5F-I5xF5_dE3Gf2_EauuXQXJA&sensor=true"></script>
<script  type="text/javascript" src="${PATH }/js/jquery-3.2.1.js">
</script>
<script >
function showRail()//初始方法，只显示初始地图
{	var markers=new Array(); 
	var infoWindows=new Array();
	
	var lat= $("#lats").val();
	var lats=lat.replace(/^\[(.*)\]$/, "\ $1\ ").split(",");
	
	var lon= $("#lons").val();
	var lons=lon.replace(/^\[(.*)\]$/, "\ $1\ ").split(",");
	
	var r= $("#rs").val();
	var rs=r.replace(/^\[(.*)\]$/, "\ $1\ ").split(",");
	
	var latl=Number(lats.length)-1;
	var lonl=Number(lons.length)-1;
		 var lat= lats[latl];
	     var lon=lons[lonl];
		 var myCenter=new google.maps.LatLng(lat, lon);//陆港公司	  	  
		 var mapProp = {//地图属性（中心位置，缩放比例和地图类型）
		   	center:myCenter,
		  	zoom:13,
			zoomControl:true,//如果要修改一个控件，必须将其打开
			zoomControlOptions:{style:google.maps.ZoomControlStyle.DEFAULT},
		 	mapTypeId:google.maps.MapTypeId.ROADMAP
		 };	
		var map=new google.maps.Map(document.getElementById("googleMap") ,mapProp);
	
	for(var i=0;i<lats.length;i++){
		
		 var rlat= lats[i];
	     var rlon=lons[i];
	     var r=Number(rs[i]);
		 var myCenter=new google.maps.LatLng(rlat, rlon);//陆港公司
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
	markers[i]=new google.maps.Marker({position:myCenter});
	
	/* var marker=new google.maps.Marker({position:myCenter});//设置标记 
	marker.setMap(map);//在地图上加载标记*/
	
	 var infoWindow=new google.maps.InfoWindow({
		content:"中心位置："+myCenter+"半径："+r+"米"
		});
	infoWindows.push(infoWindow);	
	/* google.maps.event.addListener(marker,'click',function(){infoWindow.open(map,marker);}); */
	}//for
	
	var info="";
	for(i=0;i<markers.length;i++){
		markers[i].setMap(map);
	    info=info+
	    "google.maps.event.addListener(markers["+i+"],'mouseover',function(){infoWindows["+i+"].open(map,markers["+i+"]);});"
	    +
	    "google.maps.event.addListener(markers["+i+"],'mouseout',function(){infoWindows["+i+"].close(map,markers["+i+"]);});"    
	}
	eval(info);
}
google.maps.event.addDomListener(window, 'load', showRail);

</script>	
	
</head>

<body>


<div id="googleMap" style="width:100%;height:100%;margin:0 auto;">     </div>   
<div style="position:absolute;left:90%;top:5%;">
   		<a href="${PATH }/pc/rail/findHadRailName.do"><button id="btn" >退出显示</button>  </a>
</div> 
    <input name="rs" id="rs" value="${rs }" type="hidden">
    <input name="lats" id="lats" value="${lats }" type="hidden">
    <input name="lons" id="lons" value="${lons }" type="hidden">
     
</body>
</html>
