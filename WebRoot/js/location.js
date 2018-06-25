/*//原生异步加载定位轨迹页面 冷藏箱信息列表
var xmlHttpReg = null;
console.log("aaa");
if (window.ActiveXObject) {//如果是IE
  xmlHttpReg = new ActiveXObject("Microsoft.XMLHTTP");
  console.log("1");
} else if (window.XMLHttpRequest) {
  xmlHttpReg = new XMLHttpRequest(); //实例化一个xmlHttpReg
  console.log("2");
}
function ajaxRefresh(){
  //如果实例化成功,就调用open()方法,就开始准备向服务器发送请求
  if (xmlHttpReg != null) {
    console.log("3");
    xmlHttpReg.open("get","data/container.html",true);
    console.log("4");
    xmlHttpReg.send(null);
    xmlHttpReg.onreadystatechange = doResult; //设置回调函数
  }
}
ajaxRefresh();
//回调函数
//一旦readyState的值改变,将会调用这个函数,readyState=4表示完成相应
//设定函数doResult()
function doResult() {
  if (xmlHttpReg.readyState == 4) {//4代表执行完成
    console.log("5");
    if (xmlHttpReg.status == 200) {//200代表执行成功
      //将xmlHttpReg.responseText的值赋给ID为resText的元素
      console.log("6");
      document.getElementById("xhrContainer").innerHTML = xmlHttpReg.responseText;
    }
  }
}*/

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
    if(target.nodeName.toLowerCase().parentElement == 'tbody' || target.parentElement.parentElement.nodeName.toLowerCase() == "tbody" || target.parentElement.parentElement.parentElement.nodeName.toLowerCase() == "tbody"){
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
      console.error(tr.children[1].children[0].innerHTML);
      var cId = tr.children[1].children[0].innerHTML;
      //ajax异步请求
      var xmlHttpReg = null;
      if(window.ActiveXObject){
    	  xmlHttpReg = new ActiveXObject("Microsoft.XMLHTTP");
      }else if(window.XMLHttpRequest){
    	  xmlHttpReg = new XMLHttpRequest();
      }
      if(xmlHttpReg != null){
    	  xmlHttpReg.open("get","pc/getLocation.do?cId="+cId,true);
    	  xmlHttpReg.send(null);
    	  xmlHttpReg.onrendystatechange = doResult;
      }
      function doResult(){
    	  if(xmlHttpReg.readyState == 4){
    		  if(xmlHttpReg.status == 200){
    			  xmlHttpReg.responseText
    			  
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







