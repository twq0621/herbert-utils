<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<%@page import="net.snake.bean.character.CharacterGoods"%>
<%@page import="net.snake.serverenv.vline.VLineServer"%>

<%@page import="net.snake.GameServer"%>
<%@page
	import="net.snake.persistence.chardata.severinfo.SeverinfoManager"%>
<%@page import="net.snake.bean.character.Character"; %>
<%@page import="net.snake.bean.goodscontainer.IContainer"; %>
<%@page import="java.util.*"; %>
<%
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>游戏服务器实时监控</title>
</head>
<body>
<%
	Collection<VLineServer> list = GameServer.vlineServerManager
			.getLineServersList();
	Character c = null;
	int count=0;
	for (VLineServer vlineserver : list) {
		Collection<Character> roles = vlineserver.getOnlineManager()
				.getCharacterList();
		for (Character character : roles) {
			IContainer bag = character.getCharacterGoodController()
					.getBagGoodsContiner();
			Collection<CharacterGoods> cgList = bag.getGoodsList();
			for (CharacterGoods cg : cgList) {
				if (cg.getStroneAddproperty() != null
						&& cg.getStroneAddproperty().trim().equals("0")) {
					cg.setStroneAddproperty("");
					count++;
				}
			}

			IContainer bag1 = character.getCharacterGoodController()
					.getStorageGoodsContainer();
			Collection<CharacterGoods> cgList1 = bag1.getGoodsList();
			for (CharacterGoods cg : cgList1) {
				if (cg.getStroneAddproperty() != null
						&& cg.getStroneAddproperty().trim().equals("0")) {
					cg.setStroneAddproperty("");
					count++;
				}
			}

			IContainer bag2 = character.getCharacterGoodController()
					.getBodyGoodsContiner();
			Collection<CharacterGoods> cgList2 = bag2.getGoodsList();
			for (CharacterGoods cg : cgList2) {
				if (cg.getStroneAddproperty() != null
						&& cg.getStroneAddproperty().trim().equals("0")) {
					cg.setStroneAddproperty("");
					count++;
				}
			}

			IContainer bag3 = character.getCharacterGoodController().getStallGoodsContainer();
			Collection<CharacterGoods> cgList3 = bag3.getGoodsList();
			for (CharacterGoods cg : cgList3) {
				if (cg.getStroneAddproperty() != null
						&& cg.getStroneAddproperty().trim().equals("0")) {
					cg.setStroneAddproperty("");
					count++;
				}
			}
		}
	}
	count++;
	out.println(""+count);
%>
<h1>恭喜你，开服时间已经更新成功！！更新后的开服时间是 ：<%=count%></h1>
<h1>开服时间：<%=count%></h1>

</body>
</html>