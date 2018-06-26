<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>日志页面</title>
<%
	pageContext.setAttribute("PATH", request.getContextPath());
%> 
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/cctrace1.0
 -->
  <link rel="stylesheet" href="${PATH }/css/index.css"/>
  <link rel="stylesheet" href="${PATH }/css/laydate.css"/>
  <%-- <link rel="stylesheet" href="${PATH }/css/kkpager_blue.css"/> --%>
  <style type="text/css">
  	#hxpage{
	    clear: both;
	    color: #999;
	    padding: 5px 0px 5px 0px;
	    font-size: 13px;
	}
	#hxpage>span{
		float:left;
	}
	#hxpage>.pageText{
		margin:3px 0 0 40px;
	}
	#hxpage span.disabled {
	    float: left;
	    display: inline;
	    padding: 3px 10px 3px 10px;
	    margin-right: 5px;
	    border-radius: 3px;
	    -moz-border-radius: 3px;
	    -webkit-border-radius: 3px;
	    border: 1px solid #DFDFDF;
	    background-color: #FFF;
	    color: #DFDFDF;
	}
	#hxpage span.curr {
	    float: left;
	    border: 1px solid #31ACE2;
	    display: inline;
	    padding: 3px 10px 3px 10px;
	    margin-right: 5px;
	    border-radius: 3px;
	    -moz-border-radius: 3px;
	    -webkit-border-radius: 3px;
	    background: #F0FDFF;
	    color: #31ACE2;
	}
	#hxpage a {
	    float: left;
	    border: 1px solid #ccc;
	    padding: 3px 10px 3px 10px;
	    margin-right: 5px;
	    border-radius: 3px;
	    -moz-border-radius: 3px;
	    -webkit-border-radius: 3px;
	    cursor: pointer;
	    background: #fff;
	    text-decoration: none;
   		color: #999;
    }
    #hxpage .currPageNum {
    	color: #FD7F4D;
	}
	#hxpage_gopage_wrap {
	    position: relative;
	}
	#hxpager_btn_go_input {
	    width: 36px;
	    color: #999;
	    text-align: center;
	    border: 1px solid #DFDFDF;
	    -moz-border-radius: 3px;
	    -webkit-border-radius: 3px;
	}
	#hxpager_btn_go {
	    width: 44px;
	    margin-left:8px;
	    border: 0px;
	    line-height: 140%;
	    cursor: pointer;
	    background-color: #31ACE2;
	    color: #FFF;
	    -moz-border-radius: 3px;
	    -webkit-border-radius: 3px;
	}
  </style>
</head>
<body>
	<!-- 导航 -->
  <jsp:include page="header.jsp" flush="true"></jsp:include>
  <main class="main">
    <div class="con__warnDetails clearfix" style="width:100%;">
      <div class="warnDetails__wrap">
        <header class="con__header">
          <ul>
            <li data-con="${PATH }/data/warnDetails.html" class="con__header--active con__header_none">操作日志</li>
            <li><a href = "" id = "a1" onclick="excel();" class="red_print">导出报表</a></li>
          </ul>
         
        </header>
        <main class="con__main amendBind">
          <div>
            <div class="con__main--search">
              	选择时间：<input id="startTime" class="laydate-icon">
                <input id="endTime" class="laydate-icon">
               	 冷藏箱编号：<input type="text" name = "containerId111" id = "containerId111"/>
                <button class="j-timeSearch">查询</button>
            	 操作内容：<input type="text" name="commandType" id="commandType"/>
                <button class="commandTypeSearch">查询</button>
            </div>
            <div class="con__main__table" style="height: -webkit-calc(100% - 110px); height: calc(100% - 110px); overflow:hidden;">
              <table id="logs_table">
                <thead>
	                <tr>
	                  <td><div class="">序号</div></td>
	                  <td><div class="">操作者</div></td>
	                  <td><div class="">操作的冷藏箱</div></td>
	                  <td><div class="">所属部门</div></td>
	                  <td><div class="">操作时间</div></td>
	                  <td><div class="">操作内容</div></td>
	                  <td><div class="">设定值</div></td>
	                  <td><div class="">执行结果</div></td>
	                </tr>
                </thead>
                <tbody></tbody>
              </table>
            </div>
            <div id="hxpage" style="width:800px; margin:20px auto 0;"></div>
          </div>
        </main>
      </div>
    </div>
  </main>

  <script src="${PATH }/js/jquery-3.2.1.js"></script>
  <script src="${PATH }/js/index.js"></script>
  <script src="${PATH }/js/laydate.js"></script>
  
  <!-- 时间插件js部分 -->
  <script>
  	//第一次给后台一个时间，后来每次查询更改这个时间
  	var startTime = laydate.now(Date.parse(new Date(laydate.now())) - 7*24*60*60*1000) + " 00:00:00";
  	var endTime = laydate.now() + " 23:59:59";
  	//时间插件部分 
    var start = {
      elem: '#startTime',
      event: 'click',
      format: 'YYYY-MM-DD hh:mm:ss',
      min: '2014-01-01 00:00:00', //最小日期
      max: laydate.now(), //设定最大日期为当前日期
      istime: true,
      istoday: true,
      choose: function(datas){
        var nowDate = Date.parse(new Date(laydate.now()));
        var maxDate = Date.parse(new Date(datas)) + 31*24*60*60*1000;
        end.min = datas; //开始日选好后，重置结束日的最小日期
        end.start = datas; //将结束日的初始值设定为开始日
        if(maxDate < nowDate){//当加7天后的时间 小于 当前时间
        	end.max = laydate.now(maxDate);//才重置结束最大日期为maxDate
        }else{
        	end.max = laydate.now();
        }
      }
    };
    var end = {
      elem: '#endTime',
      event: 'click',
      format: 'YYYY-MM-DD hh:mm:ss',
      min: '2014-01-01 00:00:00',
      max: laydate.now(),
      istime: true,
      istoday: true,
      choose: function(datas){
        start.max = datas; //结束日选好后，重置开始日的最大日期
        var minDate = Date.parse(new Date(datas)) - 31*24*60*60*1000;
        start.min = laydate.now(minDate);//结束日选好，重置开始的最小时间为当前-31天
      }
    };
    laydate(start);
    laydate(end);
  </script>
  <!-- 数据解析js部分 -->
  <script type="text/javascript">
  var formatDateTime = function (date) {  
      var y = date.getFullYear();  
      var m = date.getMonth() + 1;  
      m = m < 10 ? ('0' + m) : m;  
      var d = date.getDate();  
      d = d < 10 ? ('0' + d) : d;  
      var h = date.getHours();  
      h=h < 10 ? ('0' + h) : h;  
      var minute = date.getMinutes();  
      minute = minute < 10 ? ('0' + minute) : minute;  
      var second=date.getSeconds();  
      second=second < 10 ? ('0' + second) : second;  
      return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;  
  };  
  function excel(){
  	var startTime = $("#startTime").val();
  	var endTime = $("#endTime").val();
  	var containerId = $("#containerId111").val();
  	
  	if(startTime == ""||endTime == ""){
  		//初始化时间操作
  		endTime = formatDateTime(new Date());
  		startTime = new Date();
  		startTime.setTime(startTime.getTime()-24*60*60*1000);
  		startTime = formatDateTime(startTime);
  	}
 	var objA=document.getElementById("a1");
 	objA.href="${PATH}/pc/ExportExcel/findExcelLog.do?containerId="
				+ containerId + "&startTime=" + startTime
				+ "&endTime=" + endTime;
  }
  	function ajax123(pn){
  		var sTime = startTime;
  		var eTime = endTime;
  		var cID = $("#containerId111").val();
 		$.ajax({
		    url:"${PATH}/getLogsForCommand.do",
			data:{"startTime":sTime,"endTime":eTime,"containerId":cID,"pn":pn},
			type:"POST",
			success:function(result){
				var page = result.extend.page.pageNum;
  				var totalPage = result.extend.page.pages;
				if(totalPage < 1){
					alert("数据查询为零条，请重新选择时间");
					return false;
				}
				//解析显示日志数据
				build_logs_table(result);
  				hxpage(page,totalPage);// 重新解析分页条 
			}
	  });
  	}
	$('.commandTypeSearch').on('click',function(){
  		search_commanType(1);
  	});
  	function search_commanType(pn) {
  		var commandType = $('#commandType').val();
  		$.ajax({
			url:"${PATH}/getLogsForCommandType.do",
			data:{commandType:commandType,"pn":pn},
			type:"POST",
			success:function(result){
			console.log(result)
				var page = result.extend.page.pageNum;
  				var totalPage = result.extend.page.pages;
				if(totalPage < 1){
					alert("数据查询为零条!");
					return false;
				}
				//解析显示日志数据
				build_logs_table(result);
  				hxpage_copy(page,totalPage);// 重新解析分页条 
			}
	  });
  	}
	$(function(){
	 	ajax123(1);
	});
	  //解析数据 并显示
	function build_logs_table(result){
		var addHtml = $("#logs_table tbody").empty();
		var logs = result.extend.page.list;
		var page =result.extend.page;
		var htmlStr = "";
		$.each(logs,function(index,item){
			htmlStr += "<tr>";
			htmlStr += "<td><div class='leftDown--div1'>"+(index + 1)+"</div></td> ";	
			if(item.userName != null){
				htmlStr += "<td><div class='containerId '>"+item.userName+"</div></td>";
			}else{
				htmlStr += "<td><div class='containerId '>"+""+"</div></td>";
			}
			htmlStr += "<td><div class='leftDown--div2'>"+item.containerId+"</div></td>";
			htmlStr += "<td><div class='leftDown--div2'>"+item.buMenM+"</div></td>";
			htmlStr += "<td><div class='leftDown--div2'>"+item.time+"</div></td>";
			if(item.command =="clearAlert"){
				htmlStr += "<td><div class='leftDown--div2'>"+"清除警告"+"</div></td>";
			}else if(item.command =="selfCheck"){
				htmlStr += "<td><div class='leftDown--div2'>"+"自检"+"</div></td>";
			}else if(item.command =="temSet"){
				htmlStr += "<td><div class='leftDown--div2'>"+"设置温度"+"</div></td>";
			}else if(item.command =="bootDef"){
				htmlStr += "<td><div class='leftDown--div2'>"+"启动除霜"+"</div></td>";
			}else if(item.command =="refRunMode"){
				htmlStr += "<td><div class='leftDown--div2'>"+"设置运行模式"+"</div></td>";
			}else if(item.command =="remoteSwiMac"){
				htmlStr += "<td><div class='leftDown--div2'>"+"远程开关机"+"</div></td>";
			}else if(item.command =="remoteXFSwiMac"){
				htmlStr += "<td><div class='leftDown--div2'>"+"新风门开关机"+"</div></td>";
			}else if(item.command =="cfmSet"){
				htmlStr += "<td><div class='leftDown--div2'>"+"cfm开机设置"+"</div></td>";
			}
			if(item.value != null){
				htmlStr += "<td><div class='leftDown--div2'>"+item.value+"</div></td>";
			}else{
				htmlStr += "<td><div class='leftDown--div2'>"+""+"</div></td>";
			}
			if(item.status == "Y"){
				htmlStr += "<td><div class='leftDown--div2'>"+"成功"+"</div></td>";
			}else if(item.status == "N"){
				htmlStr += "<td><div class='leftDown--div2'>"+"失败"+"</div></td>";
			}else if(item.status == 0){
				htmlStr += "<td><div class='leftDown--div2'>"+"无返回"+"</div></td>";
			}else{
				htmlStr += "<td><div class='leftDown--div2'>"+""+"</div></td>";
			}
			htmlStr += "</tr>";
		});
		addHtml.append(htmlStr);
	} 
</script>
<!-- 分页部分 -->
<script type="text/javascript">
	//当前页数，总页数
	function hxpage(page,totalPage){
		var htmlStr = "<span class='pageBtnWrap'>";
		if(page < 1 || page > totalPage){
			alert("将要跳转的页码不正确，页码不能小于“1”或大于总页码数");
			return false;
		}
		/* 首页和上一页    前面部分 */
		if(page > 1){
			htmlStr += "<a href='javascript:;' onclick='ajax123(1)' title='首页'>首页</a><a href='javascript:;' onclick='ajax123("+(page-1)+")' title='上一页'>上一页</a>";
		}
		if(page == 1){//当前页小于1时
			htmlStr += "<span class='disabled'>首页</span><span class='disabled'>上一页</span>";
		}
		/* 中间  页码  部分 */
		if(totalPage < 8){//1到7时
			for(var i = 1;i <= totalPage;i++){
				if(i != page){
					htmlStr += "<a href='javascript:;' onclick='ajax123("+i+")' title='第"+i+"页'>"+i+"</a>";
				}else{
					htmlStr += "<span class='curr'>"+i+"</span>";
				}
			}
		}else if(totalPage > 7){//大于7时
			if((page-3) <= 1){//当前页减3小于或等于1时，当前页左边区域全部显示
				for(var i = 1;i < page;i++){//1到当前的
					htmlStr += "<a href='javascript:;' onclick='ajax123("+i+")' title='第"+i+"页'>"+i+"</a>";
				}
				htmlStr += "<span class='curr'>"+page+"</span>";//当前的
				for(var i = 1;i<(7-page);i++){//当前的  到  第6个  //如：123（4） 56...7
					htmlStr += "<a href='javascript:;' onclick='ajax123("+(i+page)+")' title='第"+(i+page)+"页'>"+(i+page)+"</a>";
				}
				//显示大于7页   显示...
				htmlStr += "<b style='float:left;'>...</b>";
				//显示大于7页   显示...后最后一个
				htmlStr += "<a href='javascript:;' onclick='ajax123("+totalPage+")' title='第"+totalPage+"页'>"+totalPage+"</a>";
			}else if((page+3) >= totalPage){//当前页加3大于totalPage时，当前页右边区域全部显示
				//显示第一页内容
				htmlStr += "<a href='javascript:;' onclick='ajax123(1)' title='第1页'>1</a>";
				htmlStr += "<b style='float:left;'>...</b>";
				for(var i = 5;i >= 0;i--){//显示从最大页数向前显示六个
					if((totalPage-i) == page){//当等于page时，显示为不可点击的span
						htmlStr += "<span class='curr'>"+page+"</span>";//当前的
						continue;
					}
					htmlStr += "<a href='javascript:;' onclick='ajax123("+(totalPage-i)+")' title='第"+(totalPage-i)+"页'>"+(totalPage-i)+"</a>";
				}	
			}else{//前不着村后不着店时，即前面减3不小于1，后面加3不大于最大页码  （前面显示第1页和... ， 后面显示...和最后一页）
				htmlStr += "<a href='javascript:;' onclick='ajax123(1)' title='第1页'>1</a>";
				htmlStr += "<b style='float:left;'>...</b>";
				for(var i = 2;i > 0;i--){//显示当前页码的上两个
					htmlStr += "<a href='javascript:;' onclick='ajax123("+(page-i)+")' title='第"+(page-i)+"页'>"+(page-i)+"</a>";
				}
				htmlStr += "<span class='curr'>"+page+"</span>";//当前的页码
				for(var i = 1;i < 3;i++){//显示当前页码的下两个
					htmlStr += "<a href='javascript:;' onclick='ajax123("+(page+i)+")' title='第"+(page+i)+"页'>"+(page+i)+"</a>";
				}
				htmlStr += "<b style='float:left;'>...</b>";
				htmlStr += "<a href='javascript:;' onclick='ajax123("+totalPage+")' title='第"+totalPage+"页'>"+totalPage+"</a>";
			}	
		}
		/* 尾页和下一页   后面部分 */
		if(page < totalPage){
			htmlStr += "<a href='javascript:;' onclick='ajax123("+(page+1)+")' title='下一页'>下一页</a><a href='javascript:;' onclick='ajax123("+totalPage+")' title='尾页'>尾页</a>";
		}
		if(page == totalPage){//当前页等于尾页时，按钮停用
			htmlStr += "<span class='disabled'>下一页</span><span class='disabled'>尾页</span>";
		}
		htmlStr += "</span>";//第一部分页码的结尾
		
		/*当前页   总页  和   跳转部分 */
		htmlStr +="<span class='pageText'>";
	    htmlStr +=	"<span class='totalText'>当前第";
		htmlStr +=		"<span class='currPageNum'>"+page+"</span>页";
		htmlStr +=		"<span class='totalInfoSplitStr'>/</span>共";
		htmlStr +=		"<span class='totalPageNum'>"+totalPage+"</span>页";
	    htmlStr +=	"</span>";
	    htmlStr +=	"<span class='goPageBox'>&nbsp;转到";
	    htmlStr +=		"<span id='hxpage_gopage_wrap'>";
	    
	    var iVal;//声明input框内的值为iVal;
	    //当前页小于总页input框内为当前页加1，等于总页数时input框内为总页数（即当前页数）
	    page < totalPage ? iVal = (page+1) : iVal = page;
	    
		htmlStr += "<input type='number' id='hxpager_btn_go_input' value='"+iVal+"'/>";
		htmlStr += "页<input type='button' id='hxpager_btn_go' value='确定'/>";
		htmlStr += "</span>";
	    htmlStr += "</span>";
        htmlStr += "</span>";
		$("#hxpage").html(htmlStr);
	}
	
	/* 点击确定跳转页面 */
	$("#hxpage").on("click","#hxpager_btn_go",function(){
		var inputVal = $("#hxpager_btn_go_input").val();
		if(inputVal == ""){
	       alert("跳转页码不能为空！！！");
	    }else{
	       inputVal = inputVal.replace(/\s/g,"");
	        if(inputVal == ""){
	          	alert("跳转页码不能为空！！！");
	        }else{
				ajax123(inputVal);
			} 
		}  	
	});
	
	//当前页数，总页数
	function hxpage_copy(page,totalPage){
		var htmlStr = "<span class='pageBtnWrap'>";
		if(page < 1 || page > totalPage){
			alert("将要跳转的页码不正确，页码不能小于“1”或大于总页码数");
			return false;
		}
		/* 首页和上一页    前面部分 */
		if(page > 1){
			htmlStr += "<a href='javascript:;' onclick='search_commanType(1)' title='首页'>首页</a><a href='javascript:;' onclick='search_commanType("+(page-1)+")' title='上一页'>上一页</a>";
		}
		if(page == 1){//当前页小于1时
			htmlStr += "<span class='disabled'>首页</span><span class='disabled'>上一页</span>";
		}
		/* 中间  页码  部分 */
		if(totalPage < 8){//1到7时
			for(var i = 1;i <= totalPage;i++){
				if(i != page){
					htmlStr += "<a href='javascript:;' onclick='search_commanType("+i+")' title='第"+i+"页'>"+i+"</a>";
				}else{
					htmlStr += "<span class='curr'>"+i+"</span>";
				}
			}
		}else if(totalPage > 7){//大于7时
			if((page-3) <= 1){//当前页减3小于或等于1时，当前页左边区域全部显示
				for(var i = 1;i < page;i++){//1到当前的
					htmlStr += "<a href='javascript:;' onclick='search_commanType("+i+")' title='第"+i+"页'>"+i+"</a>";
				}
				htmlStr += "<span class='curr'>"+page+"</span>";//当前的
				for(var i = 1;i<(7-page);i++){//当前的  到  第6个  //如：123（4） 56...7
					htmlStr += "<a href='javascript:;' onclick='search_commanType("+(i+page)+")' title='第"+(i+page)+"页'>"+(i+page)+"</a>";
				}
				//显示大于7页   显示...
				htmlStr += "<b style='float:left;'>...</b>";
				//显示大于7页   显示...后最后一个
				htmlStr += "<a href='javascript:;' onclick='search_commanType("+totalPage+")' title='第"+totalPage+"页'>"+totalPage+"</a>";
			}else if((page+3) >= totalPage){//当前页加3大于totalPage时，当前页右边区域全部显示
				//显示第一页内容
				htmlStr += "<a href='javascript:;' onclick='search_commanType(1)' title='第1页'>1</a>";
				htmlStr += "<b style='float:left;'>...</b>";
				for(var i = 5;i >= 0;i--){//显示从最大页数向前显示六个
					if((totalPage-i) == page){//当等于page时，显示为不可点击的span
						htmlStr += "<span class='curr'>"+page+"</span>";//当前的
						continue;
					}
					htmlStr += "<a href='javascript:;' onclick='search_commanType("+(totalPage-i)+")' title='第"+(totalPage-i)+"页'>"+(totalPage-i)+"</a>";
				}	
			}else{//前不着村后不着店时，即前面减3不小于1，后面加3不大于最大页码  （前面显示第1页和... ， 后面显示...和最后一页）
				htmlStr += "<a href='javascript:;' onclick='search_commanType(1)' title='第1页'>1</a>";
				htmlStr += "<b style='float:left;'>...</b>";
				for(var i = 2;i > 0;i--){//显示当前页码的上两个
					htmlStr += "<a href='javascript:;' onclick='search_commanType("+(page-i)+")' title='第"+(page-i)+"页'>"+(page-i)+"</a>";
				}
				htmlStr += "<span class='curr'>"+page+"</span>";//当前的页码
				for(var i = 1;i < 3;i++){//显示当前页码的下两个
					htmlStr += "<a href='javascript:;' onclick='search_commanType("+(page+i)+")' title='第"+(page+i)+"页'>"+(page+i)+"</a>";
				}
				htmlStr += "<b style='float:left;'>...</b>";
				htmlStr += "<a href='javascript:;' onclick='search_commanType("+totalPage+")' title='第"+totalPage+"页'>"+totalPage+"</a>";
			}	
		}
		/* 尾页和下一页   后面部分 */
		if(page < totalPage){
			htmlStr += "<a href='javascript:;' onclick='search_commanType("+(page+1)+")' title='下一页'>下一页</a><a href='javascript:;' onclick='search_commanType("+totalPage+")' title='尾页'>尾页</a>";
		}
		if(page == totalPage){//当前页等于尾页时，按钮停用
			htmlStr += "<span class='disabled'>下一页</span><span class='disabled'>尾页</span>";
		}
		htmlStr += "</span>";//第一部分页码的结尾
		
		/*当前页   总页  和   跳转部分 */
		htmlStr +="<span class='pageText'>";
	    htmlStr +=	"<span class='totalText'>当前第";
		htmlStr +=		"<span class='currPageNum'>"+page+"</span>页";
		htmlStr +=		"<span class='totalInfoSplitStr'>/</span>共";
		htmlStr +=		"<span class='totalPageNum'>"+totalPage+"</span>页";
	    htmlStr +=	"</span>";
	    htmlStr +=	"<span class='goPageBox'>&nbsp;转到";
	    htmlStr +=		"<span id='hxpage_gopage_wrap'>";
	    
	    var iVal;//声明input框内的值为iVal;
	    //当前页小于总页input框内为当前页加1，等于总页数时input框内为总页数（即当前页数）
	    page < totalPage ? iVal = (page+1) : iVal = page;
	    
		htmlStr += "<input type='number' id='hxpager_btn_go_input' value='"+iVal+"'/>";
		htmlStr += "页<input type='button' id='hxpager_btn_go_go' value='确定'/>";
		htmlStr += "</span>";
	    htmlStr += "</span>";
        htmlStr += "</span>";
		$("#hxpage").html(htmlStr);
	}
	
	/* 点击确定跳转页面 */
	$("#hxpage").on("click","#hxpager_btn_go_go",function(){
		var inputVal = $("#hxpager_btn_go_input").val();
		if(inputVal == ""){
	       alert("跳转页码不能为空！！！");
	    }else{
	       inputVal = inputVal.replace(/\s/g,"");
	        if(inputVal == ""){
	          	alert("跳转页码不能为空！！！");
	        }else{
				search_commanType(inputVal);
			} 
		}  	
	});
</script>
<!-- 选择时间后解析数据部分 -->
<script>	    
  //回车事件绑定
    $("#containerId111").on('keyup', function(event) {
        if (event.keyCode == "13") {
            //回车执行查询
           $(".j-timeSearch").click();
            //alert(111);
        }
    });
	$(".j-timeSearch").on("click",function(pn){
		  startTime = $("#startTime").val();
	      endTime = $("#endTime").val();
	      containerId = $("#containerId111").val();
	      if(startTime == ""||endTime == ""){
		  		//初始化时间操作
		  		endTime = formatDateTime(new Date());
		  		startTime = new Date();
		  		startTime.setTime(startTime.getTime()-24*60*60*1000);
		  		startTime = formatDateTime(startTime);
		  	}
	      if(startTime == "" || endTime == ""){
	        alert("查询条件不能为空");
	      }else{
	        startTime = startTime.replace(/(^\s*)|(\s*$)/g,"");
	        endTime = endTime.replace(/(^\s*)|(\s*$)/g,"");
	        containerId = containerId.replace(/(^\s*)|(\s*$)/g,"");
	        if(startTime == "" || endTime == ""){
	          alert("查询条件不能为空");
	        }else{
	        	ajax123(1);
	        }
	      }
	});
  </script>
</body>
</html>