package net.snake.gamemodel.friend.response;


import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.netio.ServerResponse;


public class LateLinkAddMsg10336 extends ServerResponse {
	public LateLinkAddMsg10336(CharacterFriend cf) {
		this.setMsgCode(10336);
		try {
			this.writeInt(cf.getFriendId());
			this.writeUTF(cf.getViewName());
			this.writeShort(cf.getCce().getGrade());
			this.writeByte(cf.getCce().getPopsinger());
			this.writeUTF(cf.getCce().getFactionName());
			this.writeByte(cf.getLineNum());
			this.writeByte(cf.getIconHunyanOrBaitan());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
