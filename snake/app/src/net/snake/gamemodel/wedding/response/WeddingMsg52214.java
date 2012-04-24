/**
 * 
 */
package net.snake.gamemodel.wedding.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 请求求婚者面板
 * 
 * @author serv_dev
 */

public class WeddingMsg52214 extends ServerResponse {

	public WeddingMsg52214(Hero character) {
		this.setMsgCode(52214);
		try {
			this.writeInt(character.getId());
			this.writeByte(character.getHeadimg());
			this.writeUTF(character.getViewName());
			this.writeShort(character.getGrade());
			this.writeUTF(character.getMyFactionManager().getFactionName());
			this.writeByte(character.getPopsinger());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
