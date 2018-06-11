$(function(){
  var i = 1;
  var timer = window.setInterval(function(){
    var img = $(".login__layer").find("img");
    //console.log(img);
    //console.log(i);
    //console.log(img[i]);
    $(img[i]).css("opacity",1).css("z-index","-99").siblings("img").css("opacity",0).css("z-index","-100");
    i++;
    if(i > 3){i=0;}
  },9000);
});