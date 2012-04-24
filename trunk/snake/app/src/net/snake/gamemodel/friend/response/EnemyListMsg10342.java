package net.snake.gamemodel.friend.response;

import java.util.List;

import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.netio.ServerResponse;

public class EnemyListMsg10342 extends ServerResponse {
	public EnemyListMsg10342(List<CharacterFriend> list) {
		this.setMsgCode(10342);
		try {
			this.writeByte(list.size());
			for (CharacterFriend cf : list) {
				this.writeInt(cf.getFriendId());
				this.writeUTF(cf.getViewName());
				this.writeShort(cf.getCce().getGrade());
				this.writeByte(cf.getCce().getPopsinger());
				this.writeUTF(cf.getCce().getFactionName());
				this.writeInt(cf.getHateValue());
				this.writeByte(cf.getLineNum());
				this.writeByte(cf.getIconHunyanOrBaitan());
				this.writeByte(cf.getCce().getHeadimg());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
