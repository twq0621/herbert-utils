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
<%@page import="net.snake.gamemodel.bulletin.persistence.ScrollBulletinManager"%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="net.snake.gamemodel.bulletin.bean.ScrollBulletin"%>

<%
request.setCharacterEncoding("utf-8");
String actionFlag = request.getParameter("action");
if(actionFlag != null && !actionFlag.isEmpty())
{
	if(actionFlag.equals("delete"))
	{
		String idStr = request.getParameter("id");
		if(idStr != null && !idStr.isEmpty())
		{
			int id = Integer.valueOf(idStr);
			ScrollBulletinManager.getInstance().extraMsgList.remove(id);
		}
	}
	else if(actionFlag.equals("add"))
	{
		String contentStr = request.getParameter("content");
		String timeexpStr = request.getParameter("timeexp");
		String typeStr = request.getParameter("type");
		if(contentStr != null && !contentStr.isEmpty() && timeexpStr != null && !timeexpStr.isEmpty() && typeStr != null && !typeStr.isEmpty())
		{
			Map<Integer, ScrollBulletin> extraMsgList = ScrollBulletinManager.getInstance().extraMsgList;
			int id = extraMsgList.size()+1;
			ScrollBulletin sb = new ScrollBulletin();
			sb.setContent(contentStr);
			sb.setContentI18n(contentStr);
			sb.setId(id);
			sb.setType(typeStr);
			sb.setTimeexp(timeexpStr);
			sb.setState((short) 0);
			extraMsgList.put(id, sb);
		}
	}
}


%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑定时滚动公告</title>
</head>
<body>
	<h2>滚动公告列表</h2>
		<table width="100%" border="1">
			<tr>
				<th>id</th>
				<th>内容</th>
				<th>时间表达式</th>
				<th>类型</th>
				<th>是否已发送</th>
				<th>操作</th>
			</tr>
			<%
			for (Map.Entry<Integer,ScrollBulletin> mapEntry : ScrollBulletinManager.getInstance().extraMsgList.entrySet()) {
				ScrollBulletin scrollBulletin = mapEntry.getValue();
			%>
			<tr>
				<td><%=scrollBulletin.getId()%></td>
				<td><%=scrollBulletin.getContent()%></td>
				<td><%=scrollBulletin.getTimeexp()%></td>
				<td><%=scrollBulletin.getType()%></td>
				<td><%=scrollBulletin.isSend()%></td>
				<td><a href="_scrollBulletin.jsp?action=delete&id=<%=scrollBulletin.getId()%>">delete</a></td>
			</tr>
			<%
			}
			%>
		</table>
	<h2>添加新的公告</h2>
	<form action="_scrollBulletin.jsp" method="post">
		<input type="hidden" name="action" value="add"/>
		<table width="100%" border="1" witdh="600px">
			<tr>
				<td>内容</td>
				<td><textarea name="content" cols="40" rows="10"></textarea></td>
			</tr>
			<tr>
				<td>时间表达式</td>
				<td><input type="text" name="timeexp" value="[*][*][*][10:00-10:01]" /></td>
			<tr>
			<tr>
				<td>类型</td>
				<td><input type="text" name="type" value="1,6"/></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="reset"/>&nbsp;<input type="submit"/></td>
			</tr>
		</table>
	</form>
</body>
</html>