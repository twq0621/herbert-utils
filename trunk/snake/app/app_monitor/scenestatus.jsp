<%@page import="net.snake.serverenv.security.SecurityManage"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.snake.bean.scenes.Scene"%>
<%@page import="net.snake.serverenv.vline.VLineServer"%>
<%@page import="net.snake.GameServer"%>
<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030" session="false"%>
<%
	GameServer gameserver = (GameServer) application
			.getAttribute("GameServer");
	SecurityManage sm=gameserver.securityManage;
	if(sm==null){
		response.getWriter().println("服务器未完全初始化");
		return ;
	}
	String address=request.getRemoteAddr();
	if(address==null||address.equals("")||!sm.checkManageIP(address)){
		response.setStatus(404,"非法请求");
		return ;
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<meta http-equiv=refresh content="20">
<title>游戏服务器实时监控</title>
</head>
<body>
<p><a href="serverstatus.jsp">性能监控</a>&nbsp;<a
	href="scenestatus.jsp">场景状态监控</a>&nbsp;<a
	href="processstatus.jsp">PROCESS状态监控</a></p>
	
<table width="60%" border="1" style="border-collapse: collapse">
	<tr>
		<td>世界场景[ID]</td>
		<td>角色数</td>
		<td>出战坐骑数</td>
		<td>怪物数</td>
	</tr>
	<%
		ArrayList<Scene> scenelist = new ArrayList<Scene>(
				gameserver.shareSceneManager.getWorldSceneList());
		Collections.sort(scenelist, new Comparator<Scene>() {
			public int compare(Scene o1, Scene o2) {
				return o1.getId() < o2.getId() ? -1 : (o1.getId() == o2
						.getId() ? 0 : 1);
			}
		});

		for (Scene scene : scenelist) {
	%>
	<tr>
		<td><%=scene.getShowName()%>[<%=scene.getId()%>]</td>
		<td>
		<table width="100%" border="1" style="border-collapse: collapse">
			<tr>
				<%
					int playercount = 0;
				%>
				<td width="70%">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<%
						for (VLineServer vlineserver : gameserver.vlineServerManager
									.getLineServersList()) {
								int sceneplayercount = vlineserver.getSceneManager()
										.getScene(scene.getId()).getCharacterCount();
								playercount = playercount + sceneplayercount;
					%>
					<tr>
						<td>分线[<%=vlineserver.getLineid()%>]</td>
						<td align="right"><%=sceneplayercount%></td>
					</tr>
					<%
						}
					%>
				</table>
				</td>
				<td>总:<%=playercount%></td>
			</tr>
		</table>
		</td>
		<td>
		<table width="100%" border="1" style="border-collapse: collapse">
			<tr>
				<%
					int horsecount = 0;
				%>
				<td width="70%">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<%
						for (VLineServer vlineserver : gameserver.vlineServerManager
									.getLineServersList()) {
								int scenehorsecount = vlineserver.getSceneManager()
										.getScene(scene.getId()).getHorseCount();
								horsecount = horsecount + scenehorsecount;
					%>
					<tr>
						<td>分线[<%=vlineserver.getLineid()%>]</td>
						<td align="right"><%=scenehorsecount%></td>
					</tr>
					<%
						}
					%>
				</table>
				</td>
				<td>总:<%=horsecount%></td>
			</tr>
		</table>
		</td>
		<td>
		<table width="100%" border="1" style="border-collapse: collapse">
			<tr>
				<%
					int monstercount = 0;
				%>
				<td width="70%">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<%
						for (VLineServer vlineserver : gameserver.vlineServerManager
									.getLineServersList()) {
								int scenemonstercount = vlineserver.getSceneManager()
										.getScene(scene.getId()).getMonsterCount();
								monstercount = monstercount + scenemonstercount;
					%>
					<tr>
						<td>分线[<%=vlineserver.getLineid()%>]</td>
						<td align="right"><%=scenemonstercount%></td>
					</tr>
					<%
						}
					%>
				</table>
				</td>
				<td>总:<%=monstercount%></td>
			</tr>
		</table>
		</td>
	</tr>
	<%
		}
	%>
</table>
<p>&nbsp;</p>
</body>
</html>