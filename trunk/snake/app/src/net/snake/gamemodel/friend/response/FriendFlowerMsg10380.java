package net.snake.gamemodel.friend.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class FriendFlowerMsg10380 extends ServerResponse{

	public FriendFlowerMsg10380(Hero character,int id,short fcount,int flower){
		this.setMsgCode(10380);
		try {
			this.writeInt(character.getId());
			this.writeUTF(character.getViewName());
			this.writeInt(id);
			this.writeShort(fcount);
			this.writeInt(flower);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		//this.getOut().writeUTF(character.getName());
	}

}
