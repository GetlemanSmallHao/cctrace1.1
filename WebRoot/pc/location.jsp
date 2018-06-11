<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>定位</title>
<%
	pageContext.setAttribute("PATH", request.getContextPath());
%> 
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/cctrace1.0
 -->
  <%-- <link rel="stylesheet" href="${PATH}/css/laydate.css"/> --%>
  <link rel="stylesheet" href="${PATH}/css/laydate.css" />
  <link rel="stylesheet" href="${PATH}/css/index.css"/>
</head>
<body>
  <!-- 导航 -->
  <jsp:include page="header.jsp" flush="true"></jsp:include>
  <main class="main">
    <!--左上角地图-->
    <div class="con__container__map bindEvent clearfix">
      <div class="map__wrap">
        <header class="con__header">
          <ul>
            <li class="con__header--active" style="background:none;">冷藏箱列表</li>
          </ul>
        </header>
        <main class="con__main" id="xhrContainer">
          <%-- 冷藏箱信息列表
          <img style="display:block;" class="con__loading" src="${PATH }/img/loading.gif" alt=""/>  
            --%> 
            <span>
				&nbsp;&nbsp;&nbsp;查询冷藏箱<input type="text" id="containerId5"/>
				<input type="submit" value="查询" onclick="search();"/>
            </span>
            <div style="margin:1px 0 1px auto;height:46px">
            &nbsp;&nbsp;&nbsp;开始时间：<input type="text" name="containerId" id="startTime" class="laydate-icon"/><br/>
            &nbsp;&nbsp;&nbsp;结束时间：<input type="text" name="timess" id="endTime" class="laydate-icon"/> <input type="button" value="查询地址" id="timeFind"/>
            </div>
            
            
            <form  id="data" name="data" method="post"  action="" onsubmit="return checkIndex()" style="height: -moz-calc(100% - 28px); height: -webkit-calc(100% - 28px); height: calc(100% - 28px);">
					
					<!-- <a onclick="partRefresh1()" type="button" href="javascript:void(0)">查询</a><br> -->
                 	<!-- <select name="routeType" id="routeType" style="background:#E0FFFF">
                    	<option value="" selected="selected">路线类型</option>
                    	<option value="0">去程</option>
                    	<option value="1">回程</option>				
                   	</select>	 -->
                   	<div class="con__main__table" style="height:90%;">
					    <table>
					      <thead>
					      <tr>
					        <td><div class="leftDown--div1">序号</div></td>
					        <td><div class="">集装箱编号</div></td>
					        <td><div class="">设备编号</div></td>
					        <td><div class="">绑定时间（receivetime）</div></td>
					        <td><div class="">班列ID</div></td>
					       <!--  <td><div class="">上次已知位置</div></td> -->
					       <!--  <td><div class="">冷机告警</div></td>
					        <td><div class="">货物类型</div></td>
					        <td><div class="">下货站</div></td>
					        <td><div class="">场站名称</div></td>
					        <td><div class="">上次报告时间</div></td> -->
					      </tr>
					      </thead>
					      <tbody id="contentContainsInLocation">
					           <c:forEach  var="bindTable" items="${bindTables}" varStatus="status">
				            		<tr>
						                <td><div class="leftDown--div1">${status.index+1}</div></td>
						                <td><div class="leftDown--div2">${bindTable.containerId}</div></td>
						                <td><div class="leftDown--div3">${bindTable.deviceId}</div></td>
						                <td><div class="leftDown--div3">${bindTable.bindTime}</div></td>
						            	<td><div class="leftDown--div3">${bindTable.trainId}</div></td>
						            </tr>
				            	</c:forEach>
					      </tbody>
					    </table>
					 </div>		                               
		   </form>   
        </main>
      </div>
    </div>
    <!--右上角地图信息-->
    <div class="con__map__map bindEvent clearfix">
      <div class="map__wrap">
        <header class="con__header">
          <ul>
      <!--       <li>班列</li> -->
            <li class="con__header--active">定位</li>
      <!--       <li>轨迹</li>
            <li>围栏</li> -->
          </ul>
        </header>
        <main class="con__main">
  				   <!--右侧-->
	       <div>
	           <div id="right">
			        <div style="height: -moz-calc(100% - 20px); height: -webkit-calc(100% - 20px); height: calc(100% - 20px);" id="map_canvas" style="width:100%;"></div>
			        </div>
	                <div id="googleMap" style="width:100%;height:100%;margin:0 auto;">           	
   				    </div> 
		           <div id="map" style="width:100%;height:100%">
		               <!-- <iframe src="twoLine/getLonLat.do" id="iframeId" name="iframeName" width="100%" height="100%"></iframe>   			   			        	 -->
				       <!--  <div style="width:718px;height:76px;background-image:url(../img/zt.png); position:absolute;left:50%;top:10%;margin-top:-50px;margin-left:-350px;"></div> -->
				       <!-- <div style="width:359px;height:68px;background-image:url(../img/zj3.png); position:absolute;left:0%;top:90%;"></div> -->
				   </div> 
			      <!--  <div style="position:absolute;left:90%;top:5%;">
			         		<button id="btn" >全屏显示</button>   
			       </div> -->
	           </div>
	       </div>
  				
        </main>
      </div>
    </div>
  </main>
<script src="http://maps.google.cn/maps/api/js?key=AIzaSyBncQ86ns5F-I5xF5_dE3Gf2_EauuXQXJA&sensor=false&libraries=places" type="text/javascript"></script>
  <script src="${PATH}/js/jquery-3.2.1.js"></script>
	<script src="${PATH}/js/map.js"></script>
	<script src="${PATH}/js/laydate.js"></script>
 <script>
/*
 	$("#timeFind").click(function(){
 		var time1 = "";
 		var time2 = "";
 		var ccttId = "";
 		time1 = $("#startTime").val();
 		time2 = $("#endTime").val();
 		ccttId = $("#containerId5").val();
 		if(ccttId==""){
 			alert("");
 		}
 	});
 	*/
 	var mydateInputStart = document.getElementById("startTime");
	var mydateInputEnd = document.getElementById("endTime");
	var start = {
		elem : '#startTime',
		format : 'YYYY-MM-DD hh:mm:ss',
		min : '2014-01-01 00:00:00', //设定最小日期为当前日期
		max : laydate.now(), //最大日期
		istime : true,
		istoday : true,
		choose : function(datas) {
			end.min = datas; //开始日选好后，重置结束日的最小日期
			end.start = datas;//将结束日的初始值设定为开始日
		}
	};
	var end = {
		elem : '#endTime',
		format : 'YYYY-MM-DD hh:mm:ss',
		min : '2014-01-01 00:00:00',
		max : laydate.now(),
		istime : true,
		istoday : true,
		choose : function(datas) {
			start.max = datas; //结束日选好后，重置开始日的最大日期
		}
	};
	laydate(start);
	laydate(end);
	var now = new Date();
	var selectTime = now.getFullYear()+"-"+((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+now.getDate()+" "+(now.getHours()<10?"0":"")+now.getHours()+":"+(now.getMinutes()<10?"0":"")+now.getMinutes()+":"+(now.getSeconds()<10?"0":"")+now.getSeconds();
	var selectTime1 = now.getFullYear()+"-"+((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+now.getDate()+" "+"00:00:00";
	mydateInputStart.value=selectTime1;
	mydateInputEnd.value=selectTime;
	$(".laydate-icon").attr("readonly","readonly");
	//给查询地址按钮绑定事件
	$("#timeFind").click(function(){
		var tconId = "";
		var tstartTime = "";
		var tendTime = "";
		tconId = $("#containerId5").val();
		tstartTime = $("#startTime").val();
		tendTime = $("#endTime").val();
		if(tconId == null||tconId == ""){
			alert("冷藏箱号不能为空！");
			return;
		}
		if(tstartTime == null||tstartTime == ""){
			alert("开始时间不能为空！");
			return;
		}
		if(tendTime == null||tendTime == ""){
			alert("结束时间不能为空！");
			return;
		}
		var year1 = parseInt(tstartTime.substr(0,4));
		var month1 = parseInt(tstartTime.substr(5,2))-1;
		var day1 = parseInt(tstartTime.substr(9,2));
		var hour1 = parseInt(tstartTime.substr(12,2));
		var minute1 = parseInt(tstartTime.substr(15,2));
		var second1 = parseInt(tstartTime.substr(18,2));
		var tdata1 = new Date(year1,month1,day1,hour1,minute1,second1);
		var year2 = parseInt(tendTime.substr(0,4));
		var month2 = parseInt(tendTime.substr(5,2))-1;
		var day2 = parseInt(tendTime.substr(9,2));
		var hour2 = parseInt(tendTime.substr(12,2));
		var minute2 = parseInt(tendTime.substr(15,2));
		var second2 = parseInt(tendTime.substr(18,2));
		var tdata2 = new Date(year2,month2,day2,hour2,minute2,second2);
		if((tdata2-tdata1)<0){
			alert("开始时间不能大于结束时间");
			return;
		}
		if((tdata2-tdata1)>86400000){
			alert("选择的时间区间不能大于一天");
			return;
		}
		
		//如果通过了前端数据格式的校验，就需要发送Ajax
		/*
		var tconId = "";
		var tstartTime = "";
		var tendTime = "";
		
		"get",
		PATH
				+ "/pc/track/getTrackDatasByContainerId.do?containerId="
				+ cId + "&startTime="
				+ startTime + "&endTime="
				+ endTime, true);
		*/
		var TPath = "${PATH}";
		var markerAndWindowArray = new Array();
		var noValueIndexArray = new Array();
		$.ajax({
			url:TPath+"/pc/track/getTrackDatasByContainerIdJson.do?containerId="+tconId+"&startTime="+tstartTime+"&endTime="+tendTime,
			type:"get",
			dataType:"json",
			success:function(data){
				if(data==null||data==""){
					alert("没有数据");
				}else{
					$.ajax({
						url:TPath+"/pc/track/getTrackDatasByContainerIdPage.do?containerId="+tconId+"&startTime="+tstartTime+"&endTime="+tendTime,
						type:"get",
						dataType:"text",
						success:function(pdatas){
							
							$(".con__main__table").html(pdatas);
							
								jQuery(".thsPositionClass").mouseover(function(){
									var indexdes = jQuery(this).find(":first").text();
									//console.log(markerAndWindowArray.length);
									var overindexnumber = 0;
									var flags = true;
									for(sndj in noValueIndexArray){
										if((indexdes-1)==noValueIndexArray[sndj]){
											flags = false;
										}
										if((indexdes-1)>noValueIndexArray[sndj]){
											overindexnumber++;
										}
									}
									if(flags){
										var thisMarkeryt2 = markerAndWindowArray[indexdes-1-overindexnumber].marker;
										var thisInfoyt2 = markerAndWindowArray[indexdes-1-overindexnumber].info;
										thisInfoyt2.open(map,thisMarkeryt2);
									}
								});
								
								jQuery(".thsPositionClass").mouseout(function(){
									var indexdes = jQuery(this).find(":first").text();
									//console.log(markerAndWindowArray.length);
									var overindexnumber = 0;
									var flags = true;
									for(sndj in noValueIndexArray){
										if((indexdes-1)==noValueIndexArray[sndj]){
											flags = false;
										}
										if((indexdes-1)>noValueIndexArray[sndj]){
											overindexnumber++;
										}
									}
									if(flags){
										var thisMarkeryt2 = markerAndWindowArray[indexdes-1-overindexnumber].marker;
										var thisInfoyt2 = markerAndWindowArray[indexdes-1-overindexnumber].info;
										thisInfoyt2.close();
									}
								});
						}
					});
					infowindow.close();
					marker.setMap(null);
					
					//未发生点击冷藏箱的时候地图默认加载的地图
					var uluru = {lat: 34.735892, lng: 113.815918};
					var map = new google.maps.Map(document.getElementById('googleMap'), {
				    	zoom: 9,
				    	center: uluru
				    });
					
					var latArray = [];
					noValueIndexArray = new Array();
					for(iids in data){
						var thisObjecct = data[iids];
						var thisLa = thisObjecct.lat;
						var thisLo = thisObjecct.lon;
						var thisAddress = thisObjecct.gpsPosition?thisObjecct.gpsPosition:"";
						var thisttime = thisObjecct.nowTime;
						var rrjt = typeof(thisLa)!="undefined";
						if(typeof(thisLa)!="undefined"&&thisLa!==""&&typeof(thisLo)!="undefined"&&thisLo!==""){
							//创建标记对象
							
							if(iids!=0&&iids!=data.length-1){
								var markerr=new google.maps.Marker({
							    	  position: new google.maps.LatLng(thisLa,thisLo),
							    	  map:map,
							    	  icon:TPath+"/img/MapPositionFlag.png"
							    });
							}else if(iids==0){
								var markerr=new google.maps.Marker({
							    	  position: new google.maps.LatLng(thisLa,thisLo),
							    	  map:map,
							    	  icon:TPath+"/img/start_image.png"
							    });
							}else{
								var markerr=new google.maps.Marker({
							    	  position: new google.maps.LatLng(thisLa,thisLo),
							    	  map:map,
							    	  icon:TPath+"/img/end_image.png"
							    });
							}
							
							
							
							//创建提示对象
							var infowindowqq = new google.maps.InfoWindow(
								  {
								  content:"经纬度： "+thisLo+" "+thisLa+"<br/>"
								  +"位置 "+thisAddress+"<br/>"+"时间 "+thisttime
								  }
							);
							var latlonObj = {};
							latlonObj.lat = thisLa;
							latlonObj.lng = thisLo;
							latArray.push(latlonObj);
							var markerInfo = new Object();
							markerInfo.marker = markerr;
							markerInfo.info = infowindowqq;
						   /* 鼠标移动上去打开标记信息 
						    google.maps.event.addListener(markerr, 'mouseover', function() {
						    	infowindowqq.open(map,markerr);
						    });
						    
						    /* 鼠标离开关闭标记信息 
						    
						    google.maps.event.addListener(markerr, 'mouseout', function() {
						    	infowindowqq.close();
						    }); 
							*/
							markerAndWindowArray.push(markerInfo);
						}else{
							
							noValueIndexArray.push(iids);
						}
						
					}
					
					
					for(iidg in markerAndWindowArray){
						var thisMarkeryt = markerAndWindowArray[iidg].marker;
						var thisInfoyt = markerAndWindowArray[iidg].info;
						bindHoverMarkerAndInfo(thisMarkeryt,thisInfoyt);
						bindMouseoutMarkerAndInfo(thisMarkeryt,thisInfoyt);
					}
					
					var flightPlanCoordinates = latArray;
					var flightPath = new google.maps.Polyline({
						path : flightPlanCoordinates,
						geodesic : true,
						strokeColor : '#000000',
						strokeOpacity : 1.0,
						strokeWeight : 2
					})
					flightPath.setMap(map);
				}
			}
		});
	});
	
	function bindHoverMarkerAndInfo(marker,info){
		 google.maps.event.addListener(marker, 'mouseover', function() {
			 info.open(map,marker);
		    });
	}
	function bindMouseoutMarkerAndInfo(marker,info){
		  google.maps.event.addListener(marker, 'mouseout', function() {
			  info.close();
		    }); 
	}
	
	
 </script>
 <script type="text/javascript">
     var PATH = "${PATH}";

     //点击table下tr点击选中样式
	 function getElementsByClass(classnames){
	   var classobj = newArray();
	   var classint = 0;
	   var tags =document.getElementsByTagName("*");
	   console.log("aaaa");
	   console.log(tags);
	   for(var i in tags){
	     if(tags[i].nodeType == 1){
	       if(tags[i].getAttribute("class") == classnames){
	         classobj[classint] = tags[i];
	         classint++;
	       }
	     }
	   }
	   return classobj;
	 }
	if(document.getElementsByClassName){
	 console.log("bbbbbb");
	 document.getElementsByClassName("con__main")[0].onclick = function(ev){
	   var ev = ev || window.event;
	   var target = ev.target || ev.srcElement;
	   if((target.nodeName.toLowerCase().parentElement == 'tbody' || target.parentElement.parentElement.nodeName.toLowerCase() == "tbody" || target.parentElement.parentElement.parentElement.nodeName.toLowerCase() == "tbody")&&(target.parentElement.parentElement.getAttribute("class")!="thsPositionClass"&&target.parentElement.getAttribute("class")!="thsPositionClass"&&target.getAttribute("class")!="thsPositionClass"&&target.parentElement.parentElement.parentElement.getAttribute("class")!="thsPositionClass")){
	     //if(target.nodeName.toLowerCase() == 'tr' || target.parentElement.nodeName.toLowerCase() == "tr" || target.parentElement.parentElement.nodeName.toLowerCase() == "tr"){
	     //  console.log("aaaaa");
	     //}
	     var tr;
	     if(target.nodeName.toLowerCase() == 'tr'){
	       tr = target;
	     }else if(target.parentElement.nodeName.toLowerCase() == "tr"){
	       tr = target.parentElement;
	     }else if(target.parentElement.parentElement.nodeName.toLowerCase() == "tr"){
	       tr = target.parentElement.parentElement;
	     }
	     tr.setAttribute("class"," tr__active");
	     var cId = tr.children[1].children[0].innerHTML;
	     document.getElementById("containerId5").value = cId;
	     //ajax异步请求
	     var xmlHttpReg = null;
	     if(window.ActiveXObject){
	   	  xmlHttpReg = new ActiveXObject("Microsoft.XMLHTTP");
	     }else if(window.XMLHttpRequest){
	   	  xmlHttpReg = new XMLHttpRequest();
	     }
	     if(xmlHttpReg != null){
	   	  xmlHttpReg.open("get",PATH+"/pc/getLocation.do?containerId="+cId,true);
	   	  xmlHttpReg.send(null);
	      xmlHttpReg.onreadystatechange = doResult;
	     }
	    /*  
			  console.log(xmlHttpReg.responseText); */
	     function doResult(){
	   	   if(xmlHttpReg.readyState == 4){
	   		  if(xmlHttpReg.status == 200){
	   			  console.dir(xmlHttpReg.responseText);
	   			  var result = xmlHttpReg.responseText;
	   			  var aaa =  JSON.parse(result);
	   			 console.log(aaa.extend.map);
	   			 lon = aaa.extend.map.lon;
	   			 lat = aaa.extend.map.lat;
	   			 if(lon!=null&&lat!=null){
	   				deviceId=aaa.extend.map.deviceId;
	 	   			containerId=aaa.extend.map.containerId;
	 	   			nowTime = aaa.extend.map.nowTime;
	 	   			console.log("1111");
	 	   			console.log(lon);
	 	   			cccc();
	 	   			second();
	 	   		   
	   			 }
	   		  }
	   	  } 
	     }
	     //清除除当前tr外的tr样式
	     var obox=tr.parentNode;//当前tr的父
	     var trs=obox.children;//tbody的孩子tr[]
	     for(var i=0;i<trs.length;i++){
	        if(trs[i] != tr){//不等于当前tr的删除class
	          var classVal;
	          if(trs[i].getAttribute("class")){
	            classVal = trs[i].getAttribute("class").replace(/\s*tr__active/g,"");
	            trs[i].setAttribute("class",classVal);
	          }
	       }
	     }
	   }
	
	 };
	}
	
	//回车事件绑定
		    $("#containerId5").on('keyup', function(event) {
		        if (event.keyCode == "13") {
		            //回车执行查询
		            search();
		        }
		    });
	
		//模糊查询冷藏箱
	function search() {
		var containerId6 = $("#containerId5").val();
		containerId7 = containerId6.replace(/\s*/g, "");
		$.ajax({
			url : "${PATH}/pc/bindtable/findDevicesLikeyInLocation.do",
			data : {
				"containerId" : containerId7
			},
			type : "post",
			success : function(result) {
			$("#contentContainsInLocation").html(result);
			}
		});
	}
	
 </script>
 <script type="text/javascript">
 	
	  var lon;
	  var lat;
	  var deviceId;
	  var containerId;
	  var nowTime;
	  var address;
	  var marker;
	  var infowindow;
	
	  function cccc(){
		  console.log("222"+lon);
		  myCenter=new google.maps.LatLng(lat,lon);
		  var marker;
		
		 
		  /* google.maps.event.addDomListener(window, 'load', initialize); */

	  }
 

	 function initialize()
	 {
	
		 
		 var myCenter;
		 myCenter=new google.maps.LatLng(lat,lon);
		 
	     var mapProp = {
	         center:new google.maps.LatLng(43.19,76.55),
	         zoom:4,
	         gestureHandling: 'greedy',
	         mapTypeId:google.maps.MapTypeId.ROADMAP 
	     };
	     var map=new google.maps.Map(document.getElementById("googleMap"), mapProp);
	


		//控制地图最小缩放比例
	   var MinZoomLevel=3;
	   google.maps.event.addListener(map, 'zoom_changed',function() {
	         if (map.getZoom() < MinZoomLevel) {
	        	 map.setZoom(MinZoomLevel);
	         }
	        	
	       }); 
	     
	 }
	
	 function second()
	 {
	    
	  //1.实例化Geocoder服务
		 var geocoder = new google.maps.Geocoder();
		
		//2.地理反解析过程
		//请求数据GeocoderRequest为location，值类型为LatLng因此我们要实例化经纬度
		geocoder.geocode({location:new google.maps.LatLng(lat, lon)},
			function geoResults(results, status){
			  //这里处理结果和上面一模一样
			  if (status == google.maps.GeocoderStatus.OK) {
			       //  alert('地理反解析结果：'+results[0].formatted_address);
			               //  alert('地理反解析结果：'+results[0].geometry.location);
				  address=results[0].formatted_address;
				  console.log("###"+address);
				//发出ajax,将解析的地点传到后台
				$.ajax({
					url : '${PATH}/pc/OurCcdata/getLocation.do',
					type : 'post',
					data : {
						'containerId' : containerId,
						'address'   : address
 					},
					dataType : 'JSON',
					success : function(result) {
						//location.replace('${PATH}/pc/index.jsp');
					}
				});
				  
			 	 /* 添加信息窗口 */
			    infowindow = new google.maps.InfoWindow({
			    	  content:"冷藏箱编号："+containerId+
			  		"</br>设备编号："+	deviceId+"</br>位置："+address+","+"</br>经纬度："+lat+","+lon+
			  			","+"</br>系统时间："+nowTime
			    	});
			    
			   infowindow.open(map,marker); 
			   
			   /* 点击标记时打开标记信息 */
			    google.maps.event.addListener(marker, 'click', function() {
			    	  infowindow.open(map,marker);
			    	});
				  
			  }
		});
		
		 var myCenter;
		 myCenter=new google.maps.LatLng(lat,lon);
		 
	     var mapProp = {
	         center:myCenter,
/* 	         center:new google.maps.LatLng(43.19,76.55), */
	         zoom:8,
	         gestureHandling: 'greedy',
	         mapTypeId:google.maps.MapTypeId.ROADMAP 
	     };
	     var map=new google.maps.Map(document.getElementById("googleMap"), mapProp);
	    /*添加标记 */ 
	     marker=new google.maps.Marker({
	    	  position: myCenter,
	    	  animation: google.maps.Animation.BOUNCE 
	    	  }); 
	    

	     /* 给标记添加缩放地图 */
	    google.maps.event.addListener(marker,'click',function() {
	    	  map.setZoom(9);
	    	  map.setCenter(marker.getPosition());
	    	});  
	     
	 
	    /* 重置标记 */
	    /* 修改时间：11-16 14：55 ，解决地图不能拖动的问题*/
	    /*  google.maps.event.addListener(map,'center_changed',function() {
	    	  window.setTimeout(function() {
	    	    map.panTo(marker.getPosition());
	    	  },100);
	    	});  */
	    marker.setMap(map); 
		
		
	    
		//控制地图最小缩放比例
	   var MinZoomLevel=3;
	   google.maps.event.addListener(map, 'zoom_changed',function() {
	         if (map.getZoom() < MinZoomLevel) {
	        	 map.setZoom(MinZoomLevel);
	         }
	       });
	 }
	 google.maps.event.addDomListener(window, 'load', initialize);
	 
 </script>


</body>
</html>