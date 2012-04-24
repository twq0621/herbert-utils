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
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Set"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看服务器性能</title>
</head>
<body>

<%
Collection<VLineServer> col=GameServer.vlineServerManager.getLineServersList();
Iterator<VLineServer> it = col.iterator();
while(it.hasNext())
{
	VLineServer line = it.next();
	%>
	<br/><br/>分线:<%=line.getLinename()%><br/>
	帧频:<%=line.getFrameRate()%><br/>
	<%
	Map<Integer, long[]> records = line.getProcessInfo();
	Set<Entry<Integer, long[]>> set = records.entrySet();
	Iterator<Entry<Integer, long[]>> entryIt = set.iterator();
	while(entryIt.hasNext()){
		Entry<Integer,long[]> one = entryIt.next();
		long[] value = one.getValue();
		
	%>
		消息号:<%=value[0]%>|累计用时:<%=value[1]%> ms|累计处理次数:<%=value[2]%>|最长时间:<%=value[3]%> ms|最短时间:<%=value[4]%> ms|<br/>
	<%
	}
}

%>

<br/><br/><br/>

</body>
</html>