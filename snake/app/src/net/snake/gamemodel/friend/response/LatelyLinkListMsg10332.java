package net.snake.gamemodel.friend.response;

import java.util.List;

import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.netio.ServerResponse;



public class LatelyLinkListMsg10332 extends ServerResponse {
	public LatelyLinkListMsg10332(List<CharacterFriend> list) {
		this.setMsgCode(10332);
		try {
			this.writeByte(list.size());
			for (CharacterFriend cf : list) {
				this.writeInt(cf.getFriendId());
				this.writeUTF(cf.getViewName());
				this.writeByte(cf.getLineNum());
				this.writeByte(cf.getIconHunyanOrBaitan());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}
}
