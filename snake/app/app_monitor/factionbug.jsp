<%@page import="net.snake.persistence.chardata.factioncharacter.FactionCharacterManager"%>
<%@page import="net.snake.bean.faction.FactionCharacter"%>
<%@page import="net.snake.bean.faction.FactionController"%>
<%@page import="net.snake.persistence.chardata.faction.FactionManager"%>
<%@page import="net.snake.persistence.gamedata.factionflag.FactionFlagManager"%>
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
<%@page import="net.snake.persistence.chardata.severinfo.SeverinfoManager" %>
<%
SeverinfoManager.getInstance().init();

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>游戏服务器实时监控</title>
</head>
<body>
<%Collection<FactionController> collection= FactionManager.getInstance().getAllFactionCollection(); 
 for(FactionController fc:collection){
	FactionCharacter bangzhu=fc.getBangzhu();
	out.println(bangzhu.getId()+"::::::::::::::"+(fc.getFaction().getId()+"::")+fc.getFaction().getBangzhuId());
	if(bangzhu.getId()==null){
		int in=FactionCharacterManager.getInstance().insert(bangzhu);
		out.println(in+"====================="+bangzhu.getCharacterId()+"tttttttttt"+(fc.getFaction().getId()+"::  ")+fc.getFaction().getBangzhuId());
	}
	if(fc.getFaction().getId()==136){
		out.println(fc.getFaction().getName()+"}}}}}}}}}}ooooooooooooooooooooooooooooooooooooooo");
	}
 }

%>
<h1>恭喜你，开服时间已经更新成功！！更新后的开服时间是 ：<%=SeverinfoManager.getInstance().getKaifuTime() %></h1>
<h1>开服时间：<%=SeverinfoManager.getInstance().getKaifuTime() %></h1>

</body>
</html>