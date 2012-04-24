package net.snake.gamemodel.heroext.biguandazuo.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 主角查询双修玩家
 * @author jack
 *
 */
public class CharacterListMsg50508 extends ServerResponse {

	public CharacterListMsg50508(byte type, short nowPage, List<Hero> list) {
		this.setMsgCode(50508);
		try {
			this.writeByte(type);
			this.writeShort(nowPage);
			this.writeByte(list.size());
			for (Hero c : list) {
				this.writeInt(c.getId());
				this.writeUTF(c.getViewName());
				this.writeShort(c.getGrade());
				this.writeByte(c.getPopsinger());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
