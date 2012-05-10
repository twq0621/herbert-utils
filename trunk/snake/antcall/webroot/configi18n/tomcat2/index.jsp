<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工具生成xml请点击要生成的表明</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
    <h1>这给是韩国打包---------</h1>
  		下边是当前执行  的模版type是0的话就是执行完了1的话就是正在执行中<br>
   <tr bgcolor="#FF0000"><iframe src="/antcall/anttype" name="ifrmname" id="ifrmid" width="500" height="40" frameborder="0" align="top" ></iframe></tr>
   	<script type="text/javascript">  
 function reflesh(){	

document.location.reload();
}
setTimeout("reflesh()",10*1000);//每10秒钟刷新一次
</script>
  <h1>策划导完数据之后请点击一下同步数据之后在点击下面的xml生成</h1>
  <h1><a href="/antcall/ant?task=sql"  target="_blank">策划导完数据之后请点击一下同步数据</a><br>	</h1>
  <body>
  		<a href="/antcall/ant?task=banyun"  target="_blank">同步各个版本的avatar人物换装，avatar_effect效果 注意需要手动去对应工程目录提交</a><br>	
	<br/>
    <br/>
    <a href="/antcall/ant?task=lianti"  target="_blank">lianti人物连体（这个不再生成所有配置xml里边需要单独生成</a><br>
     <br/>
     <a href="/antcall/ant?task=languageflash"  target="_blank">lan_text（这个不再生成所有配置xml里边需要单独生成</a><br>
    
    
  	<a href="/antcall/ant?task=build"  target="_blank">生成所有表配置xml(排除avatar人物换装，avatar_effect效果)</a><br>
	<br/>
  	<a href="/antcall/ant?task=updateFlash"  target="_blank">更新客户端目录,对客户端资源进行加密,并将加密后的文件自动提交svn</a><br>
	<br/>
		<a href="/antcall/ant?task=totem"  target="_blank">totem.xml表生成</a><br>	
	<br/>
		<a href="/antcall/ant?task=language"  target="_blank">lan语言配置</a><br>	
	<br/>
	<a href="/antcall/ant?task=hiddenweapons"  target="_blank">更新hiddenweapons暗器</a><br>
	<a href="/antcall/ant?task=equipmentplayconfig"  target="_blank">更新equipmentplayconfig.xml装备玩法</a><br>
	<br/>
		<a href="/antcall/ant?task=goodsdc"  target="_blank">goodsdc古玩收集</a><br>
	<a href="/antcall/ant?task=instance"  target="_blank">instance副本</a><br>
	<a href="/antcall/ant?task=achieve"  target="_blank">achieve人物成就</a><br>
   	<a href="/antcall/ant?task=goodmodel"  target="_blank">goodmodel</a><br>
   	<a href="/antcall/ant?task=npc"  target="_blank">npc</a><br>
   	<a href="/antcall/ant?task=horsemodel"  target="_blank">horsemodel</a><br>
   	<a href="/antcall/ant?task=map"  target="_blank">trans传送点</a><br>
   	<a href="/antcall/ant?task=map2"  target="_blank">map地图数据</a><br>
   	<a href="/antcall/ant?task=task"  target="_blank">task</a><br>
   	<a href="/antcall/ant?task=taskdialog"  target="_blank">taskdialog</a><br>
   	<a href="/antcall/ant?task=skill"  target="_blank">skill</a><br>
   	<a href="/antcall/ant?task=effect"  target="_blank">skilleffect</a><br>
   	<a href="/antcall/ant?task=monstermodel"  target="_blank">monstermodel</a><br>
	<a href="/antcall/ant?task=sceneMonster"  target="_blank">sceneMonster</a><br>	
	<br/>
	
   	<a href="/antcall/ant?task=avatar"  target="_blank">avatar人物换装11</a><br>
   	<a href="/antcall/ant?task=avatar_effect"  target="_blank">avatar_effect效果</a><br>
   	<a href="/antcall/ant?task=iconToSwf"  target="_blank">小图标打包swf，大图标目录构建</a><br>
   	<a href="/antcall/ant?task=sound"  target="_blank">音频swf生成</a><br>
	<br/>
	---------------------------------------------------------------------------------------------------------<br>
	<%--<jsp:include page="myjsp.jsp"></jsp:include><br>
   


  --%></body>
</html>
