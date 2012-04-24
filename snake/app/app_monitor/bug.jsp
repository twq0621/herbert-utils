<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@page import="net.snake.gamemodel.goods.bean.CharacterGoods"%>
<%@page import="net.snake.serverenv.vline.VLineServer"%>

<%@page import="net.snake.GameServer"%>
<%@page
	import="net.snake.gamemodel.line.persistence.SeverinfoManager"%>
<%@page import="net.snake.gamemodel.hero.bean.Hero" %>
<%@page import="net.snake.gamemodel.goods.logic.container.IContainer" %>
<%@page import="java.util.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>��Ϸ������ʵʱ���</title>
</head>
<body>
<%
Hero character=GameServer.vlineServerManager.getCharacterById(33135);
CharacterGoods xuanyuan=character.getMycharacterAcrossZhengzuoManager().getXuanyuanjian();
int goodId=character.getMyCharacterAcrossIncomeManager().getAi().getXuanyuanjian();
Date date=character.getMyCharacterAcrossIncomeManager().getAi().getXuanyuanjianTime();

%>
<h1>�Ƿ������� ��<%=character%></h1>
<h1>�Ƿ������Ʒ ��<%=xuanyuan%></h1>
<h1>�Ƿ�������� ��<%=goodId+"   "+date%></h1>
<h1>�Ƿ�������� ��<%=goodId+"   "+date%></h1>
<%if(character!=null){
	character.getMyCharacterAcrossIncomeManager().online();
		//character.getMycharacterAcrossZhengzuoManager().online();
		xuanyuan=character.getMycharacterAcrossZhengzuoManager().getXuanyuanjian();
		out.println("ִ�����===========================");
}
%>
<h1>ִ����� �Ƿ������ԯ�� ��<%=xuanyuan%></h1>
</body>
</html>