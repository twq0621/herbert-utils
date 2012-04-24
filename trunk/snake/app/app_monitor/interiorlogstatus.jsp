<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=GB18030"
	session="false"%>
<%@page import="net.snake.GameServer"%>
<%@page import="net.snake.serverenv.security.SecurityManage"%>
<%@page import="net.snake.commons.httplog.HttpInteriorLogService" %>
<%@page import="net.snake.log.HttpLogService" %>

<%
	GameServer gameserver = (GameServer) application
			.getAttribute("GameServer");
	SecurityManage sm = gameserver.securityManage;
	if (sm == null) {
		response.getWriter().println("服务器未完全初始化");
		return;
	}
	String address = request.getRemoteAddr();
	if (address==null||address.equals("")||!sm.checkManageIP(address)) {
		response.setStatus(404,"非法请求");
		return;
	}
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<meta http-equiv=refresh content="5">
<title>游戏服务器实时监控</title>
</head>
<body>
<p><a href="serverstatus.jsp">性能监控</a>&nbsp;<a
	href="scenestatus.jsp">场景状态监控</a>&nbsp;<a
	href="processstatus.jsp">PROCESS状态监控</a></p>
	
<table width="60%" border="1" style="border-collapse: collapse">
	<tr>
		<td>服务器ID</td>
		<td>内部日志处理线程最大数</td>
		<td>内部日志处理线程当前使用数</td>
		<td>内部日志开关状态</td>
		<td>内部日志请求地址</td>
		<td>外部日志处理线程最大数</td>
		<td>外部日志处理线程当前使用数</td>
		<td>外部日志开关状态</td>
		<td>外部日志请求地址</td>
	</tr>
	<tr>
		<td><%=gameserver.sid%></td>
		<td><%=HttpInteriorLogService.getInstance().getHttplogthread().getThreadsize()%></td>
		<td><%=HttpInteriorLogService.getInstance().getHttplogthread().getRunstatusthread()%></td>
		<td><%=HttpInteriorLogService.getInstance().isInteriorhttplog() == true ? "开" : "关" %></td>
		<td><%=HttpInteriorLogService.getInstance().getInteriorLogaddress() %></td>
		<td><%=HttpLogService.getInstance().getHttplogthread().getThreadsize()%></td>
		<td><%=HttpLogService.getInstance().getHttplogthread().getRunstatusthread()%></td>
		<td><%=HttpLogService.getInstance().isIsopen() == true ? "开" : "关" %></td>
		<td><%=HttpLogService.getInstance().getAddress() %></td>
	</tr>
</table>
<a href="updatelogparam.jsp">更新日志参数</a>
<br/>

</body>
</html>