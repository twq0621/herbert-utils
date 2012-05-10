<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<script type="text/javascript">
    function immediately(){
    var element = document.getElementById("mytext");
     var element2 = document.getElementById("mytext2");
      var element3 = document.getElementById("mytext3");
    if("\v"=="v") {
    element.onpropertychange = webChange;
    }else{
    element.addEventListener("input",webChange,false);
    }
    
    function webChange(){
    var a=0,b=0,c=0;
    if(element.value){
  	a=element.value;
   // document.getElementById("test").innerHTML = element.value
    };
    
    if(element2.value){
	 b=element2.value;
   // document.getElementById("test2").innerHTML = element.value
    };
    
    if(element3.value){
	 c=element3.value;
   // document.getElementById("test2").innerHTML = element.value
    };
    var d="type="+a+"&ftype="+b+"&date="+c+"";
    alert(d);
    }
    
    }
    
  
    
    
    </script>
   
    
    <title>换装和效果详细配置</title><!--
    
	 直接写在页面中的示例：
    <input type="text" name="textfield" oninput="document.getElementById('webtest').innerHTML=this.value;" onpropertychange="document.getElementById('webtest').innerHTML=this.value;" />
    <div>您输入的值为：<span id="webtest">还未输入</span></div>
    <br /><br /><br /><br /><br />
    
    
    
    
    
    -->
  <title>
  	<tr><td>请输入要生成avatar人物换装类型（默认是所有）</td><td> <input type="text" name="textfield" id="mytext" /></td></tr>
  	<tr><td>请输入要生成avatar人物换装的日期（1 为当天 默认是所有）</td><td> <input type="text2" name="textfield2" id="mytext2" /></td></tr>
  	<tr><td></td><td></td></tr>
  	<tr></tr>
  
  </title><!--
    <input type="text" name="textfield" id="mytext" />
    <div>您输入的值为：<span id="test">还未输入</span></div>
     <input type="text2" name="textfield2" id="mytext2" />
  <input type="text3" name="textfield3" id="mytext3" />
     <script type="text/javascript">
    immediately();
    </script>    
   
  -->
  
  </head>
  
  <body>
   

  </body>
</html>
