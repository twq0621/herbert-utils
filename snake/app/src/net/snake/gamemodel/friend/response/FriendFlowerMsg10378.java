package net.snake.gamemodel.friend.response;


import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


public class FriendFlowerMsg10378 extends ServerResponse {
	public FriendFlowerMsg10378(Hero character,short count,int flower) {
		this.setMsgCode(10378);
		try {
			this.writeInt(character.getId());
			this.writeByte(1);
			this.writeUTF(character.getViewName());
			this.writeShort(count);
			this.writeInt(flower);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public FriendFlowerMsg10378(Hero character,int key ,String ...str) {
		this.setMsgCode(10378);
		try {
			this.writeInt(character.getId());
			this.writeByte(0);
			this.writeInterUTF(key,str);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
}
