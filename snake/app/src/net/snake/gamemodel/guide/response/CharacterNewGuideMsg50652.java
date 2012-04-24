package net.snake.gamemodel.guide.response;

import java.util.Collection;

import net.snake.gamemodel.guide.bean.CharacterNewguide;
import net.snake.netio.ServerResponse;

/**
 * 发送角色新手引导进度
 * 
 * @author serv_dev
 * 
 */
public class CharacterNewGuideMsg50652 extends ServerResponse {
	public CharacterNewGuideMsg50652(Collection<CharacterNewguide> collection) {
		this.setMsgCode(50652);
		this.writeByte(collection.size());
		for (CharacterNewguide cng : collection) {
			this.writeByte(cng.getType());
			this.writeBoolean(cng.getGuideIsfinish());
			this.writeByte(cng.getGuideNum());
			this.writeByte(cng.getGuideCount());
		}
	}

}
