package net.snake.gamemodel.friend.response;

import java.util.List;

import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.netio.ServerResponse;

public class LatelinkMsg10332 extends ServerResponse {
	public LatelinkMsg10332(List<CharacterFriend> list) {
		this.setMsgCode(10332);
		try {
			this.writeByte(list.size());
			for (int i = list.size(); i > 0; i--) {
				CharacterFriend cf = list.get(i - 1);
				this.writeInt(cf.getFriendId());
				this.writeUTF(cf.getViewName());
				if (cf.getCce() == null) {
					//logger.info("cf.getCce()==null,cf.getViewName()=" + cf.getViewName());
					this.writeShort(0);
					this.writeByte(0);
					this.writeUTF("");
				} else {
					this.writeShort(cf.getCce().getGrade());
					this.writeByte(cf.getCce().getPopsinger());
					this.writeUTF(cf.getCce().getFactionName());
				}
				this.writeByte(cf.getIsOnline());
				this.writeByte(cf.getIconHunyanOrBaitan());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
