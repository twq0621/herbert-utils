package net.snake.gamemodel.friend.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 发送表情心情信息
 * 
 */
public class CharacterBiaoXinQingMsg10376 extends ServerResponse {
	public CharacterBiaoXinQingMsg10376(Hero roleDate) {
		this.setMsgCode(10376);
		try {
			this.writeInt(roleDate.getId());
			this.writeUTF(roleDate.getNowBiaoqing());
			this.writeUTF(roleDate.getNowXingqing());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
