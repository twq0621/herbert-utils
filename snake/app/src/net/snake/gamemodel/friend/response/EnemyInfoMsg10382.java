package net.snake.gamemodel.friend.response;


import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


public class EnemyInfoMsg10382 extends ServerResponse {
	public EnemyInfoMsg10382(Hero character) {
		this.setMsgCode(10382);
		try {
			this.writeInt(character.getId());
			this.writeByte(1);
			this.writeShort(character.getGrade());
			this.writeUTF(character.getSceneRef().getShowName());
			this.writeShort(character.getX());
			this.writeShort(character.getY());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public EnemyInfoMsg10382(int id,int msgKey,String... vars) {
		this.setMsgCode(10382);
		try {
			this.writeInt(id);
			this.writeByte(0);
			this.writeInterUTF(msgKey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
