<%@page import="net.snake.bean.gongchengdate.GongchengDate"%>
<%@page import="net.snake.persistence.chardata.gongchengdate.GongchengDateManager"%>
<%@page import="net.snake.persistence.chardata.factioncity.FactionCityManager"%>
<%@page import="net.snake.persistence.chardata.scenebangqi.SceneBangqiManager"%>
<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030" %>
<%@page import="net.snake.GameServer"%>
<%@page import="net.snake.quartz.*" %>
<%@page import="net.snake.quartz.*" %>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.*" %>
<%@page import="net.snake.bean.scenebangqi.SceneBangqi" %>
<%
//FactionCityManager.getInstance().init();
//SceneBangqi bangqit= SceneBangqiManager.getInstance().getSceneBangqiByPositionId(13);
//out.println(bangqit+"temp1=================");
//SceneBangqi bangqitt= SceneBangqiManager.getInstance().getSceneBangqiByPositionId(14);
//out.println(bangqitt+"temp2=================");
//SceneBangqi bangqi1=new SceneBangqi();
//bangqi1.setId(8);
//bangqi1.setBangqiPositionId(12);
//bangqi1.setFactionId(52);
//bangqi1.setLine(1);
//bangqi1.setMaxHp(5000);
//bangqi1.setNowHp(5000);
//SceneBangqiManager.getInstance().bangqiLeaveScene(bangqi1);
 Calendar t = Calendar.getInstance();
		t.setTimeInMillis(System.currentTimeMillis());
		t.set(Calendar.DAY_OF_MONTH,t.get(Calendar.DAY_OF_MONTH)-7);
		t.set(Calendar.HOUR_OF_DAY, FactionCityManager.startHorse);
	t.set(Calendar.MINUTE, 0);
		t.set(Calendar.SECOND, 0);
		t.set(Calendar.MILLISECOND, 0);
	GongchengDate gongchengDate = new GongchengDate(
			49);
	gongchengDate.setGongchengDate(t.getTime());
	gongchengDate.setId(29);
	GongchengDateManager.getInstance().deleteGongchengDate(gongchengDate);
	

SceneBangqi bangqit5= SceneBangqiManager.getInstance().getSceneBangqiByPositionId(12);
SceneBangqiManager.getInstance().bangqiLeaveScene(bangqit5);
SceneBangqi bangqit5dd= SceneBangqiManager.getInstance().getSceneBangqiByPositionId(12);
out.println(bangqit5dd+"temp1=================");
SceneBangqiManager.getInstance().initDate();
SceneBangqi bangqit5ddd= SceneBangqiManager.getInstance().getSceneBangqiByPositionId(12);
out.println(bangqit5ddd+"temp1=================");
SceneBangqi bangqitt5= SceneBangqiManager.getInstance().getSceneBangqiByPositionId(16);
out.println(bangqitt5+"temp2=================");
SceneBangqi bangqit4= SceneBangqiManager.getInstance().getSceneBangqiByPositionId(13);
out.println(bangqit4+"temp1=================");
SceneBangqi bangqitt4= SceneBangqiManager.getInstance().getSceneBangqiByPositionId(14);
out.println(bangqitt4+"temp2=================");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>游戏服务器实时监控</title>
</head>
<body>
<h1>恭喜你，任务更新成功！！每小时更新！！！</h1>
</body>
</html>