package net.snake.gamemodel.friend.response;

import java.util.List;

import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.netio.ServerResponse;


public class BlackListMsg10358 extends ServerResponse {
	public BlackListMsg10358(List<CharacterFriend> list) {
		this.setMsgCode(10358);
		try {
			this.writeByte(list.size());
			for (CharacterFriend cf : list) {
				this.writeInt(cf.getFriendId());
				this.writeUTF(cf.getViewName());
				this.writeShort(cf.getCce().getGrade());
				this.writeByte(cf.getCce().getPopsinger());
				this.writeUTF(cf.getCce().getFactionName());
				this.writeByte(cf.getLineNum());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}
}
