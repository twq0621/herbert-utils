package net.snake.gamemodel.friend.response;


import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


public class BiaoqingUpdateMsg10372 extends ServerResponse {
	public BiaoqingUpdateMsg10372(Hero character) {
		this.setMsgCode(10372);
		try {
			this.writeUTF(character.getNowBiaoqing());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
	}
}
