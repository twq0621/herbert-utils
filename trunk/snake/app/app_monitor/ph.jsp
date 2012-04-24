<%@page import="net.snake.persistence.ranking.RankingManager"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page
	import="net.snake.persistence.chardata.character.CharacterManager"%>
<%@page import="net.snake.game.map.processor.ExchangeMapTrans"%>
<%@page import="net.snake.bean.character.Character"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.snake.bean.scenes.Scene"%>
<%@page import="net.snake.serverenv.vline.VLineServer"%>
<%@page import="net.snake.GameServer"%>

<%
	String gm=request.getParameter("gm");
	GameServer gameserver = (GameServer) application.getAttribute("GameServer");
	RankingManager.getInstance().init();
%>
