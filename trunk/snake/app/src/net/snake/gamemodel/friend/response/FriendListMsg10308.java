package net.snake.gamemodel.friend.response;

import java.util.List;

import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.netio.ServerResponse;



public class FriendListMsg10308 extends ServerResponse {
	public FriendListMsg10308(List<CharacterFriend> list) {
		this.setMsgCode(10308);
		try {
			this.writeByte(list.size());
			
			for (CharacterFriend cf : list) {
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
				this.writeInt(cf.getFavor());
				this.writeByte(cf.getLineNum());
				this.writeByte(cf.getIconHunyanOrBaitan());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}
}
