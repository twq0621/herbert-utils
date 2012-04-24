<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=GB18030"
	session="false"%>
<%@page import="java.util.*"%>
<%@page import="net.snake.bean.scenes.Scene"%>
<%@page import="net.snake.serverenv.vline.VLineServer"%>
<%@page import="net.snake.GameServer"%>
<%@page import="net.snake.serverenv.security.SecurityManage"%>

<%
	String isOpenProcessCountInfo=request.getParameter("isOpenProcessCountInfo");
	String sort=request.getParameter("sort");
	GameServer gameserver = (GameServer) application.getAttribute("GameServer");
	
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

	if(isOpenProcessCountInfo != null){
		if(isOpenProcessCountInfo.equals("true")){
			gameserver.isOpenProcessCountInfo=true;
		}else{
			gameserver.isOpenProcessCountInfo=false;
			ArrayList<VLineServer> vlineserverlist = new ArrayList<VLineServer>(gameserver.vlineServerManager.getLineServersList());
			for (VLineServer vlineserver : vlineserverlist) {
				vlineserver.getProcessInfo().clear();
			}
		}
	}

	int totalCharacter = gameserver.vlineServerManager.getOnlineCount();
	int characterInScene = 0;

	int horseInScene = 0;
	for (VLineServer vlineserver : gameserver.vlineServerManager
			.getLineServersList()) {
		characterInScene += vlineserver.getSceneManager()
				.getCharacterCount();
		horseInScene += vlineserver.getSceneManager()
				.getShowHorseCount();
	}
	int instanceCount=gameserver.vlineServerManager.getAllInstanceCount();
	int instanceCharactersCount=gameserver.vlineServerManager.getAllInstanceCharacterCount();
	int instanceDownLineCharactersCount=gameserver.vlineServerManager.getAllInstanceDownLineCharacterCount();
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
	
<br/>
<a href="processstatus.jsp?isOpenProcessCountInfo=true">开启process监控</a>
<a href="processstatus.jsp?isOpenProcessCountInfo=false">关闭process监控并清空记录</a>
<br/>
<br/>
<%
if(!gameserver.isOpenProcessCountInfo){
	return;
}

Map<Integer,long[]> countmaps = new HashMap<Integer,long[]>();
countmaps.clear();
for (VLineServer vlineserver : gameserver.vlineServerManager.getLineServersList()) {
    Iterator<Integer> iterator = vlineserver.getProcessInfo().keySet().iterator();
    while (iterator.hasNext()) {
    	int key = iterator.next();
		if(countmaps.containsKey(key)){
			long[] processInfo1 = countmaps.get(key);
			long[] processInfo2 = vlineserver.getProcessInfo().get(key);
			//[1]=累计处理时间;[2]=累计处理次数;[3]=最长处理时间;[4]=最短处理时间
			processInfo1[1] += processInfo2[1];
			processInfo1[2] += processInfo2[2];
			if(processInfo1[3] < processInfo2[3]){
				processInfo1[3]=processInfo2[3];
			}
			if(processInfo1[4] > processInfo2[4]){
				processInfo1[4]=processInfo2[4];
			}
		}else{
			countmaps.put(key,vlineserver.getProcessInfo().get(key).clone());
		}
	}
}
%>
<table width="60%" border="1" style="border-collapse: collapse">
	<tr>
		<td>processID</td>
		<td><a href="processstatus.jsp?sort=1">处理次数</a></td>
		<td><a href="processstatus.jsp?sort=2">平均处理时间(毫秒)</a></td>
		<td><a href="processstatus.jsp?sort=3">最长处理时间(毫秒)</a></td>
		<td><a href="processstatus.jsp?sort=4">最短处理时间(毫秒)</a></td>
	</tr>
	<%	
	ArrayList<long[]> processlist = new ArrayList<long[]>(countmaps.values());
	if(sort==null || sort.equals("1")){
		Collections.sort(processlist, new Comparator<long[]>() {
			public int compare(long[] o1, long[] o2) {
				return o1[2] < o2[2] ? 1 : -1;
			}
		});
	} else if(sort.equals("2")){
		Collections.sort(processlist, new Comparator<long[]>() {
			public int compare(long[] o1, long[] o2) {
				return o1[1]/o1[2] < o2[1]/o2[2] ? 1 : -1;
			}
		});
	} else if(sort.equals("3")){
		Collections.sort(processlist, new Comparator<long[]>() {
			public int compare(long[] o1, long[] o2) {
				return o1[3] < o2[3] ? 1 : -1;
			}
		});
	} else if(sort.equals("4")){
		Collections.sort(processlist, new Comparator<long[]>() {
			public int compare(long[] o1, long[] o2) {
				return o1[4] < o2[4] ? 1 : -1;
			}
		});
	} 
	for(long[] processInfo:processlist){ 
	%>
	<tr>
		<td><%=processInfo[0]%></td>
		<td><%=processInfo[2]%></td>
		<td><%=processInfo[1]/processInfo[2]%></td>
		<td><%=processInfo[3]%></td>
		<td><%=processInfo[4]%></td>
	</tr>
	<% } %>
</table>
</body>
</html>