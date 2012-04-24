package net.snake.gamemodel.wedding.response;

import java.util.List;

import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.netio.ServerResponse;


public class WedderEnemyListMsg52246 extends ServerResponse {
	public WedderEnemyListMsg52246(List<CharacterFriend> list) {
		this.setMsgCode(52246);
		try {
			this.writeByte(list.size());
			for (CharacterFriend cf : list) {
				this.writeInt(cf.getFriendId());
				this.writeUTF(cf.getViewName());
				this.writeInt(cf.getHateValue());
				this.writeByte(cf.getLineNum());
				this.writeByte(cf.getIconHunyanOrBaitan());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}
}
