package net.snake.gamemodel.friend.response;


import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


/**
 * 角色A的id（int），角色Aname（str），角色A的等级（short）,角色A的门派（byte），角色A的帮派(str)，是否已经添加为好友（byte
 * ）0不是，1是
 * 
 * @author serv_dev
 * 
 */
public class FriendaddMsg10306 extends ServerResponse {

	public FriendaddMsg10306(Hero c,byte istag) {
		this.setMsgCode(10306);
		try {
			this.writeInt(c.getId());
			this.writeUTF(c.getViewName());
			this.writeByte(c.getHeadimg());
			this.writeShort(c.getGrade());
			this.writeByte(c.getPopsinger());
			this.writeUTF(c.getMyFactionManager().getFactionName()); //帮会头像目前不做处理
			this.writeByte(istag); //不是好友 1是
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
