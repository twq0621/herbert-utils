package net.snake.gamemodel.friend.response;


import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.netio.ServerResponse;


/**
 * 添加好友角色（int），好友名字（str），好感度（int），是否在线（byte）0不在线/1在线，是否婚宴或摆摊（byte）注：0无，1摆摊，2婚宴
 * 
 * 添加好友成功
 * @author serv_dev
 * 
 */
public class FriendAddMsg10304 extends ServerResponse {
	public FriendAddMsg10304(CharacterFriend cf) {
		this.setMsgCode(10304);
		try {
			this.writeInt(cf.getFriendId());
			this.writeUTF(cf.getViewName());
			this.writeShort(cf.getCce().getGrade());
			this.writeByte(cf.getCce().getPopsinger());
			this.writeUTF(cf.getCce().getFactionName());
			this.writeInt(cf.getFavor());
			this.writeByte(cf.getLineNum());
			this.writeByte(cf.getIconHunyanOrBaitan());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
