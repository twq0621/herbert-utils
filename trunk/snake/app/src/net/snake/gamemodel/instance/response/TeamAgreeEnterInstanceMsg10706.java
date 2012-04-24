package net.snake.gamemodel.instance.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;


public class TeamAgreeEnterInstanceMsg10706 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(TeamAgreeEnterInstanceMsg10706.class);
	public TeamAgreeEnterInstanceMsg10706( Hero character,byte tag) {
		this.setMsgCode(10706);
		try {
			this.writeInt(character.getId());
			this.writeByte(tag);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
	}

}
