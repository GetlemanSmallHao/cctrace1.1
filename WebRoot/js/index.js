
/*
//  集装箱所有列表信息
if($(".con__container .con__main").length != 0){
  $.ajax({
    url:path+"/data/container.html",
    success:function(data){
      $(".con__container .con__main").html(data);
    }
  });
}
*/
 /* //设置页面告警信息
if($(".con__warning .con__main").length != 0){
  $.ajax({
    url:path+"/data/warning.html",
    success:function(data){
      $(".con__warning .con__main").html(data);
    }
  });
}*/

/*  //告警页面告警详细信息（内容）
if($(".con__warnDetails .con__main").length != 0){
  $.ajax({
    url:path+"/data/warnDetails.html",
    success:function(data){
      $(".con__warnDetails .con__main").html(data);
    }
  });
}*/
/*  //传感器信息
if($(".con__sensor .con__main").length != 0){
  $.ajax({
    url:path+"/data/sensor.html",
    success:function(data){
      $(".con__sensor .con__main").html(data);
    }
  });
}*/
 /* //冷藏箱管理页面信息
if($(".con__boxManage .con__main").length != 0){
  $.ajax({
    url:path+"/data/boxManage.html",
    success:function(data){
      $(".con__boxManage .con__main").html(data);
    }
  });
}*/
  /*//堆场管理页面信息
if($(".con__dcManage .con__main").length != 0){
  $.ajax({
    url:path+"/data/dcManage.html",
    success:function(data){
      $(".con__dcManage .con__main").html(data);
    }
  });
}
  //下货站管理页面信息
if($(".con__xhzManage .con__main").length != 0){
  $.ajax({
    url:path+"/data/xhzManage.html",
    success:function(data){
      $(".con__xhzManage .con__main").html(data);
    }
  });
}*/
/*//用户页面用户信息
if($(".con__user .con__main").length != 0){
  $.ajax({
    url:path+"/data/userList.html",
    success:function(data){
      $(".con__user .con__main").html(data);
    }
  });
}*/
//内容区域导航栏点击异步加载刷新(用于传感器部分位置等导航栏切换内容)
/*$(".bindEvent").on("click",".con__header li",function(){
  //如果当前导航不是被选中的，且其拥有data-con属性
  if(!$(this).hasClass("con__header--active") && $(this).attr("data-con")){
    //导航对应内容区域异步加载
    var funevent = $(this);
    $.ajax({
      url:$(this).attr("data-con"),
      success:function(data){
        //console.log(funevent.parents(".con__header").siblings(".con__main"));
        funevent.parents(".con__header").siblings(".con__main").html(data);
      }
    })
  }
  //导航栏的的选中效果
  $(this).addClass("con__header--active")
         .siblings("li").removeClass("con__header--active");
});*/
//点击刷新局部刷新
/*$(".con__header--refresh").click(function(e){
  e.preventDefault();
  var eventThis = $(this);
  //console.log(eventThis.parent(".con__header").siblings(".con__main").find(".con__loading"));
  eventThis.parent(".con__header").siblings(".con__main").find(".con__loading").show();
  $.ajax({
    url:eventThis.siblings("ul").children(".con__header--active").attr("data-con"),
    success:function(data){
      //loading.css("display","none");
      eventThis.parent(".con__header").siblings(".con__main").html(data);
    }
  });
});*/
//主页 修改按钮点击出现修改模态框
$(".amendBind").on("click",".main__amend",function(e){
  $(".con__modal").fadeIn(600).show();
  var divArr = $(this).parents("tr").find("div");
  var inputArr = $(".con__modal").find("input");
  var selectArr = $(".con__modal").find("select");
  var xhzArr = $(selectArr[0]).find("option");
  var dcArr = $(selectArr[1]).find("option");
  console.log(divArr);
  $(inputArr[0]).val($(divArr[2]).text());
  $(inputArr[1]).val($(divArr[17]).text());
  $(inputArr[2]).val($(divArr[7]).text());
  $(inputArr[3]).val($(divArr[6]).text());
  for(var i = 0;i < xhzArr.length;i++){
	  console.log($(xhzArr[i]).text().replace(/\s*/g, ""));
	  if($(divArr[18]).text().replace(/\s*/g, "") == $(xhzArr[i]).text().replace(/\s*/g, "")){
		  $(xhzArr[i]).prop("selected",true).siblings("option").prop("selected",false);
		  break;
	  }else{
		  $(xhzArr[0]).prop("selected",true).siblings("option").prop("selected",false);
	  }
  }
  for(var i = 0;i < dcArr.length;i++){
	  if($(divArr[19]).text().replace(/\s*/g, "") == $(dcArr[i]).text().replace(/\s*/g, "")){
		  $(dcArr[i]).prop("selected",true).siblings("option").prop("selected",false);
		  break;
	  }else{
		  $(dcArr[0]).prop("selected",true).siblings("option").prop("selected",false);
	  }
  }
});

$(".amendBind").on("click",".con__modal--cancel",function(e){
  $(".con__modal").hide();
});
//设置部分三级页面跳转
  //点击一级菜单选项时
var showEvent;//点击一级菜单后当前所对应并显示的三级菜单
$(".set__menu1 tbody").on("click","tr",function(){
  $("."+$(this).attr("data-url2")).show()//显示选项所对应的二级菜单
                                  .siblings(".set__menu2").hide();//其他二级菜单隐藏
  $("."+$(this).attr("data-url3")).show()//显示选项所对应的三级菜单，但三级菜单中的内容没有显示
                                  .siblings(".set__menu3").hide();//其他三级菜单隐藏
  showEvent = $("."+$(this).attr("data-url3"));//点击一级菜单后当前所对应并显示的三级菜单
});
  //点击二级菜单选项时
 $(".set__menu2 tbody").on("click","tr",function(){
   //console.log($(this).attr("data-url"));
   showEvent.children("."+$(this).attr("data-url")).show()//点击的二级菜单选项所对应的三级菜单
           .siblings(".set__menu--wrap").hide();//其他三级菜单隐藏
 });
//冷藏箱管理页面点击出现修改（删除）冷藏箱信息
$(".con__boxManage .con__main").on("click","tbody>tr",function(){
  $(".boxManage__amend").show().siblings("div").hide();
  var findDiv = $(this).find("div");
  var findInput = $(".boxManage__main--amend>span").find("input");
  $(findInput[0]).val($(findDiv[1]).text());
  $(findInput[1]).val($(findDiv[4]).text());
  //console.log($(findDiv[4]).text());
  //$(findInput[2]).val($(findDiv[4]).text());
});
//堆场 管理页面点击出现修改（删除）堆场信息
$(".con__dcManage .con__main").on("click","tbody>tr",function(){
  $(".dcManage__amend").show().siblings("div").hide();
  var findDiv = $(this).find("div");
  var findInput = $(".dcManage__main--amend>span").find("input");
  $(findInput[0]).val($(findDiv[2]).text());
  $(findInput[1]).val($(findDiv[1]).text());
});
//下货站 管理页面点击出现修改（删除）堆场信息
$(".con__xhzManage .con__main").on("click","tbody>tr",function(){
  $(".xhzManage__amend").show().siblings("div").hide();
  var findDiv = $(this).find("div");
  var findInput = $(".xhzManage__main--amend>span").find("input");
  $(findInput[0]).val($(findDiv[2]).text());
  $(findInput[1]).val($(findDiv[1]).text());
});
$(".con__header--add").click(function(e){
  e.preventDefault();
  $(".manage__add").show().siblings("div").hide();
});
/*用户页面创建新用户*/
$(".btn--createUser").click(function(e){
  e.preventDefault();
  //console.log($(".con__main--createUser"));
  $(".con__main--createUser").show()
      .siblings("div").hide();
});
/*修改用户*/
$(".con__user .con__main").on("click","tbody>tr",function () {
	$(".con__main--changeUser").show().siblings("div").hide();
	var findDiv = $(this).find("div");//用户列表每行内容数组
	var findInput = $(".con__main--changeUser>div").find("input");//修改用户可修改的input框
	var findB = $(".con__main--changeUser>div").find("b");//修改用户不可修改的b标签
	var userGrade = $(".con_main--userGrade");//修改用户select标签
	/*console.log(userGrade);*/
	//默认选择用户等级select标签内容  //模态框内select选择内容
	if($(findDiv[4]).text() == "admin"){
		var optionVal = 1;
		userGrade.removeAttr("disabled");
		userGrade.find("option[value=3]").remove();
	}else if($(findDiv[4]).text() == "common"){
		var optionVal = 2;
		userGrade.removeAttr("disabled");
		userGrade.find("option[value=3]").remove();
	}else if($(findDiv[4]).text() == "root"){
		var optionVal = 3;
		userGrade.find("option[value=3]").remove();
		userGrade.append("<option value='3'>root</option>").prop("disabled","disabled"); 
	}
	userGrade.find("option[value="+optionVal+"]").prop("selected",true)
							 .siblings("option").prop("selected",false);
	//填写不可修改b标签内容
	$(findB[0]).text($(findDiv[1]).text());
	$(findB[1]).text($(findDiv[2]).text());
	$(findB[3]).text($(findDiv[3]).text());
	//填写可修改input内容
	$(findInput[0]).val($(findDiv[4]).text());
	$(findInput[1]).val($(findDiv[5]).text());
});
//修改用户页面修改密码显示隐藏
$(".con__main--changeUser").on("click",".change_Password",function(e){
  e.preventDefault();
  $(".changeUser--password").show();
});
$(".con__main--changeUser").on("click",".changeUser__btn--cancel",function(e){
  e.preventDefault();
  $(".changeUser--password").hide()
            .find("input").val("");
});
//点击table下tr点击选中样式
$(".con__main").on("click","table tbody>tr",function(e){
  $(this).addClass("tr__active")
      .siblings("tr").removeClass("tr__active");
});

//主页传感器设置部分弹出菜单
$(".con__main").on("click","[data-id]",function(){
  var dataType = $(this).attr("data-type");
  console.log(dataType);
  if(dataType){//当data-type状态属性存在时进入
    dataType = dataType.replace(/\s/g,"");//将状态值去掉空格
    if(dataType == 1){//当状态值为1时，及当前菜单打开中，将状态值清空并将菜单隐藏
      $(this).attr("data-type","");
      $(".con__main__setmenu").hide();
    }else{//状态值不为1时
      $(".con__main__setmenu").show();//显示菜单内容父div
      $("#"+$(this).attr("data-id")).show().siblings("div").hide();//显示当前菜单，隐藏其他菜单
      $("[data-id]").attr("data-type","");//将所有状态值清空
      $(this).attr("data-type","1");//将当前状态值改为1，及打开
    }
  }else{//当data-type状态属性不存在时
    $(".con__main__setmenu").show();
    $("#"+$(this).attr("data-id")).show().siblings("div").hide();
    $("[data-id]").attr("data-type","");
    $(this).attr("data-type","1");
  }
});







