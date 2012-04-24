package net.snake.gamemodel.hero.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev wutao
 * 
 */
public class CreateCharacterResponse10016 extends ServerResponse {

	public CreateCharacterResponse10016(Hero character) {
		setMsgCode(10016);
		try {
			writeByte(1);
			addCharacter(character);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void addCharacter(Hero character) throws Exception {
		writeInt(character.getId().intValue());
		writeByte(character.getHeadimg());
		writeUTF(character.getViewName());
		writeShort(character.getGrade());
		writeByte(character.getPopsinger());
		// 可显示的换装数量(byte){换装资源id(int)},骑乘状态(byte 1骑 0不骑){坐骑换装资源id(int)}
		// 初始创建时，都使用的默认换装
		writeByte(0);
		writeByte(0);
	}

}
