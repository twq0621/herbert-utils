<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
String logon = (String)session.getAttribute("logon");
if(logon==null ||!logon.equals("no001")){
	response.sendRedirect("_login.jsp");
	}
%>
<%@page import="net.snake.GameServer"%>
<%@page import="java.util.Collection"%>
<%@page import="net.snake.serverenv.vline.VLineServerManager"%>
<%@page import="net.snake.serverenv.vline.VLineServer"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看当前运行时副本</title>
</head>
<body>
<%
String focus=request.getParameter("prof");

if(focus!=null){
	if(focus.equals("1")){
		GameServer.isOpenProcessCountInfo =true;
	}else{
		GameServer.isOpenProcessCountInfo = false;
		Collection<VLineServer> col = GameServer.vlineServerManager.getLineServersList();
			for (VLineServer vlineserver : col) {
				vlineserver.getProcessInfo().clear();
			}
	}
	
	
	
}
%>
现在process监控处于<%=GameServer.isOpenProcessCountInfo?"开启":"关闭"%>状态<br/>
<a href="_console.jsp?prof=1">开启process监控</a>|<a href="_console.jsp?prof=0">开启process监控</a><br/>
<br/>

<a href="_serv.jsp">服务器请求处理能力</a><br/>
<br/>
<a href="_listplayers.jsp">玩家列表</a><br/>
<br/>
<a href="_listinst.jsp">副本列表</a><br />
<br/>
<a href="_scrollBulletin.jsp">编辑定时滚动公告</a>
</body>
</html>