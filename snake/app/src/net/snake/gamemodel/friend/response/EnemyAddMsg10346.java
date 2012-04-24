package net.snake.gamemodel.friend.response;


import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.netio.ServerResponse;

/**
 * 仇人添加成功通知消息
 * @author serv_dev
 *
 */
public class EnemyAddMsg10346 extends ServerResponse {

	public EnemyAddMsg10346(CharacterFriend cf) {
		this.setMsgCode(10346);
		try {
			this.writeInt(cf.getFriendId());
			this.writeUTF(cf.getViewName());
			this.writeShort(cf.getCce().getGrade());
			this.writeByte(cf.getCce().getPopsinger());
			this.writeUTF(cf.getCce().getFactionName());
			this.writeInt(cf.getHateValue());
			this.writeByte(cf.getLineNum());
			this.writeByte(cf.getIconHunyanOrBaitan());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
