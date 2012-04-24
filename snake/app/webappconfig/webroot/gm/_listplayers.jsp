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
<title>查看当前在线玩家</title>
</head>
<body>

在线人数:<%=GameServer.vlineServerManager.getOnlineCount()%>___
<% 
Collection<VLineServer> col=GameServer.vlineServerManager.getLineServersList(); 
long now= new Date().getTime();

%>
共有分线：<%=col.size()%>条<br/>
<br/><br/><br/>
<%
Iterator<VLineServer> it = col.iterator();
while(it.hasNext())
{
	VLineServer line = it.next();
	GameServerOnlineManager mng = line.getOnlineManager();
	Collection<Hero> heros = mng.getCharacterList();
	
	Iterator<Hero> iterator = heros.iterator();
	while(iterator.hasNext())
	{
		Hero hero = iterator.next();
		Account acnt = hero.getAccount();
%>
		<%
			Date login = acnt.getLastloginDate();
			long loginlong = login.getTime();
			long diff = now - loginlong;
			long sec = diff / 60000;
		%>
		姓名:<%=hero.getName()%>|
		元宝数量:<%=acnt.getYuanbao()%>|
		登录时间:<%=login%>|
		在线时长:<%=sec%>分钟|
		职业:<%=Popsinger.getPopsingerName(hero.getPopsinger())%>|
		ID:<%=hero.getId()%> |
		级别:<%=hero.getGrade()%>|
		当前场景:<%=hero.getSceneRef().getName()%>|
		<%
			String str="";
			int stat = hero.getObjectState();
			switch(stat){
                case VisibleObjectState.Die:
				str = "死亡";
				break;

					case VisibleObjectState.Jitui:
					str = "击退";
					break;

					case VisibleObjectState.Attack:
					str = "攻击";
					break;

					case VisibleObjectState.Idle:
					str = "站立";
					break;
					case VisibleObjectState.Shock:
					str = "濒死";
					break;
					case VisibleObjectState.Walk:
						str = "移动";
					break;
					case VisibleObjectState.Dazuo:
							str = "参禅";
					break;
					case VisibleObjectState.BeAttacked:
								str = "被攻击";
					break;
			}
		%>
		当前状态:<%=str%>|
		<br/>
		<%
	}%>
	
	<br/><br/>
<%}
%>
</body>
</html>