<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="net.snake.bean.character.Character"%>
<%@page import="java.util.Collection"%>
<html>
<%@ page language="java" contentType="text/html; charset=GB18030"
	session="false"%>
	<%@page import="java.util.Iterator"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.snake.bean.scenes.Scene"%>
<%@page import="net.snake.serverenv.vline.VLineServer"%>
<%@page import="net.snake.GameServer"%>
<%@page import="net.snake.serverenv.security.SecurityManage"%>
<%@page import="net.snake.bean.character.Horse"%>
<%@page import="net.snake.ai.eyeshot.CharacterEyeShotManager"%>

<%
	GameServer gameserver = (GameServer) application
			.getAttribute("GameServer");
	Collection<VLineServer> lines = GameServer.vlineServerManager
	.getLineServersList();
	int cntPursuit = 0;
	int cntJiaoYi = 0;
	int cntTeam = 0;
	int cntFriend = 0;
	int cntYoulong = 0;
	int cntHorseSkill = 0;
	int cntHorseEye = 0;
	int cntCharacterEye = 0;
	int cntInstanceChar = 0;
	int cntCharacterBuff = 0;
	int cntCharacterEnmity = 0;
		for (VLineServer line : lines) 
		{
			Collection<Character> col = line.getOnlineManager().getCharacterList();
			for (Iterator<Character> iterator = col.iterator(); iterator.hasNext();) {
				Character character = iterator.next();
				try{
					cntPursuit = cntPursuit + character.getPursuitPointManager().getArroundWithMeInFightMonsterPositions().size();
						try{
							cntJiaoYi = cntJiaoYi + (character.getMyTradeController()).getAllObjInHeap();
						} catch(Exception e){
							e.printStackTrace();
						}
						try{
							cntTeam = cntTeam + character.getMyTeamManager().getAllObjInHeap();
						} catch(Exception e){
							e.printStackTrace();
						}
						try{
							cntFriend = cntFriend + character.getMyFriendManager().getAllObjInHeap();
						} catch(Exception e){
							e.printStackTrace();
						}
						try{
								cntYoulong = cntYoulong + character.getCatchYoulongActionController().getAllObjInHeap();
						} catch(Exception e){
							e.printStackTrace();
						}	
					Collection<Horse> horsecol = character.getCharacterHorseController().getAllHorse();
					
						try{
							cntCharacterEye = cntCharacterEye + ((CharacterEyeShotManager)character.getEyeShotManager()).getAllEyeObjInHeap();
						} catch(Exception e){
							e.printStackTrace();
						}
						try{
							cntInstanceChar = cntInstanceChar + character.getMyCharacterInstanceManager().getAllObjInHeap();
						} catch(Exception e){
							e.printStackTrace();
						}	
						try{
							cntCharacterBuff = cntCharacterBuff + character.getEffectController().getBuffList().size();
						} catch(Exception e){
							e.printStackTrace();
						}
				
						try{
							cntCharacterEnmity = cntCharacterEnmity + character.getEnmityManager().getAllObjInHeap();
						} catch(Exception e){
							e.printStackTrace();
						}	
					for (Iterator<Horse> horseInter = horsecol.iterator(); horseInter.hasNext();) {
						Horse horse = horseInter.next();
						try{
							cntHorseSkill = cntHorseSkill +  horse.getAllSkillObjInHeap();
						} catch(Exception e){
							e.printStackTrace();
						}
						try{
							cntHorseEye = cntHorseEye + horse.getAllEyeObjInHeap();
						} catch(Exception e){
							e.printStackTrace();
						}
					}
				} catch(Exception e){
					e.printStackTrace();
				}
			}
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
	<a href="characterstatus.jsp">角色状态监控</a>
	<a href="monsterstatus.jsp">怪物状态监控</a>
	<a href="scenedropgoodstatus.jsp">掉落物品状态监控</a>
<table width="60%" border="1" style="border-collapse: collapse">
	<tr>
		<td>角色追击管理器对象统计</td>
		<td>角色交易管理器对象统计</td>
		<td>角色组队管理器对象统计</td>
		<td>角色好友管理器对象统计</td>
		<td>角色游龙之刃管理器对象统计</td>
		<td>角色视野管理器对象统计</td>
		<td>角色副本管理器对象统计</td>
		<td>角色buff管理器对象统计</td>
		<td>角色仇恨管理器对象统计</td>
		
		<td>坐骑技能管理器对象统计</td>
		<td>坐骑视野管理器对象统计</td>
	</tr>
	<tr>
		<td><%=cntPursuit%></td>
		<td><%=cntJiaoYi%></td>
		<td><%=cntTeam%></td>
		<td><%=cntFriend%></td>
		<td><%=cntYoulong%></td>
		
		<td><%=cntCharacterEye%></td>
		<td><%=cntInstanceChar%></td>
		<td><%=cntCharacterBuff%></td>
		<td><%=cntCharacterEnmity%></td>
		<td><%=cntHorseSkill%></td>
		<td><%=cntHorseEye%></td>
	</tr>
</table>
<br/>
</body>
</html>