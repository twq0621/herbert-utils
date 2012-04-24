package net.snake.gamemodel.heroext.biguandazuo.response;

import org.apache.log4j.Logger;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


public class ShuangxiuMsg50512 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(ShuangxiuMsg50512.class);
	public ShuangxiuMsg50512(byte type, Hero character){
		this.setMsgCode(50512);
		try {
			this.writeByte(type);
			this.writeInt(character.getId());
			this.writeUTF(character.getViewName());
			this.writeShort(character.getGrade());
			this.writeUTF(character.getMyFactionManager().getFactionName());
			this.writeByte(character.getPopsinger());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
