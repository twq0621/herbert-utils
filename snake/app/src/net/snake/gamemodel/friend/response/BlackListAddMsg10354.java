package net.snake.gamemodel.friend.response;


import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.netio.ServerResponse;


public class BlackListAddMsg10354 extends ServerResponse {
	public BlackListAddMsg10354(CharacterFriend cf) {
		this.setMsgCode(10354);
		try {
			this.writeInt(cf.getFriendId());
			this.writeUTF(cf.getViewName());
			this.writeShort(cf.getCce().getGrade());
			this.writeByte(cf.getCce().getPopsinger());
			this.writeUTF(cf.getCce().getFactionName());
			this.writeByte(cf.getLineNum());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
