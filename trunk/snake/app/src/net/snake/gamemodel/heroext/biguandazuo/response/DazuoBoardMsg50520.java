package net.snake.gamemodel.heroext.biguandazuo.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 玩家状态广播
 * 
 * @author Administrator
 * 
 */
public class DazuoBoardMsg50520 extends ServerResponse {
	public DazuoBoardMsg50520(Hero character, Hero shuangXiu) {
		this.setMsgCode(50520);
		this.writeInt(character.getId());
		if (character.getMyDazuoManager().getCharacterState() == 2 && shuangXiu != null) {
			this.writeByte(2);
			this.writeInt(shuangXiu.getId());
			// this.writeUTF(shuangXiu.getName());
			this.writeShort(shuangXiu.getX());
			this.writeShort(shuangXiu.getY());
		} else {
			this.writeByte(character.getMyDazuoManager().getCharacterState());
		}
	}

}
