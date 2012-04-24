<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
String logon = (String)session.getAttribute("logon");
if(logon==null ||!logon.equals("no001")){
	response.sendRedirect("_login.jsp");
	}
%>
<%@page import="net.snake.GameServer"%>
<%@page import="net.snake.serverenv.vline.VLineServerManager"%>
<%@page import="net.snake.serverenv.vline.VLineServer"%>
<%@page import="net.snake.serverenv.vline.GameServerOnlineManager"%>

<%@page import="net.snake.serverenv.vline.GameServerOnlineManager"%>
<%@page import="net.snake.gamemodel.map.logic.Scene"%>
<%@page import="net.snake.gamemodel.instance.logic.RuningInstanceManager"%>
<%@page import="net.snake.gamemodel.instance.logic.InstanceController"%>
<%@page import="net.snake.gamemodel.instance.bean.InstanceDataRef"%>

<%@page import="net.snake.gamemodel.hero.bean.Hero"%>
<%@page import="net.snake.gamemodel.account.bean.Account"%>

<%@page import="net.snake.consts.Popsinger"%>
<%@page import="net.snake.commons.VisibleObjectState"%>

<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看当前运行时副本</title>
</head>
<body>



<% 


int sum = 0;
int player = 0;
int lineSum =0;
int lineplayer =0;
Collection<VLineServer> col=GameServer.vlineServerManager.getLineServersList();
Iterator<VLineServer> it = col.iterator();
while(it.hasNext())
{
	
	lineSum =0;
	lineplayer =0;
	VLineServer line = it.next();
	Collection<InstanceController> copies = line.getRuningInstanceManager().getInstanceCollection();
	Iterator<InstanceController> copyIt = copies.iterator();
	while(copyIt.hasNext()){
		InstanceController inst = copyIt.next();
		InstanceDataRef data = inst.getInstanceData();
		
		String name =data.getInstanceName();
		int size = inst.getInstanceAllCharacters().size();
		sum +=1;
		lineSum +=1;
		lineplayer +=size;
		player +=size;
		%>
			<br/>分线名称:<%=line.getLinename()%>|副本名称:<%=name%>|活跃人数:<%=size%>
		<%
	}%>
	<br/>
	<br/>分线小计:<%=line.getLinename()%>|活跃副本小计:<%=lineSum%>|活跃人数小计:<%=lineplayer%>
	<br/><br/>
<%
} 
%>
<br/>
共<%=sum%>个副本，活跃于副本中<%=player%>人
<br/><br/><br/>

</body>
</html>