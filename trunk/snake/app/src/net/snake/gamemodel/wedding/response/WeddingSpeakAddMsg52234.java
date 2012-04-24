package net.snake.gamemodel.wedding.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.bean.CouplesSpeak;
import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev
 */

public class WeddingSpeakAddMsg52234 extends ServerResponse {

	/**
	 * @param character
	 */
	public WeddingSpeakAddMsg52234(Hero character, CouplesSpeak cs) {
		character.getMyFriendManager().getRoleCouplesSpeakManager().getCouplesSpeakController();
		this.setMsgCode(52234);
		try {
			int cId = character.getId();
			this.writeDouble(cs.getSpeakDate().getTime());
			if (cId == cs.getSpeakId()) {
				this.writeByte(1);
			} else {
				this.writeByte(2);
			}
			this.writeInt(cs.getTempId());
			this.writeUTF(cs.getContent());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
