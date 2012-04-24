package net.snake.gamemodel.team.response;

import java.util.List;

import net.snake.GameServer;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 分页返回附近玩家（全地图）
 *
 */
public class HereCharacterList10214 extends ServerResponse {
public HereCharacterList10214(int nowPage,int maxPage,List<Hero> list){
	this.setMsgCode(10214);
	try {
		this.writeShort(nowPage);
		this.writeShort(maxPage);
		this.writeByte(list.size());
		for(Hero c:list){
			this.writeInt(c.getId());
			this.writeUTF(c.getViewName());
			this.writeShort(c.getGrade());
			this.writeByte(c.getPopsinger());
			this.writeUTF(c.getMyFactionManager().getFactionName());
			int wedderId=c.getMyFriendManager().getRoleWedingManager().getWedderId();
			if(wedderId<1){
				this.writeByte(0);
			}else{
				this.writeByte(1);
				this.writeInt(wedderId);
				this.writeUTF(c.getMyFriendManager().getRoleWedingManager().getWedderName());
				Hero wedder=GameServer.vlineServerManager.getCharacterById(wedderId);
				this.writeBoolean(wedder!=null);
			}
				
		}
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	
}
}
