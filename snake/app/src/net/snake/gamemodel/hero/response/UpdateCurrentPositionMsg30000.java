package net.snake.gamemodel.hero.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 更新当前位置，与客户端同步
 * 
 * @author serv_dev
 * 
 */
public class UpdateCurrentPositionMsg30000 extends ServerResponse {

	public UpdateCurrentPositionMsg30000(Hero character) {
		setMsgCode(30000);
		writeInt(character.getId());
		writeShort(character.getX());
		writeShort(character.getY());
	}
}
