package net.snake.gamemodel.pet.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.netio.message.RequestMsg;

/**
 * 灵宠展示收起放生 60007
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 60007, accessLimit = 100)
public class ShowHorseProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 灵宠ID(int)，请求类型(1展示,2收起,3放生)（byte）

		int id = request.getInt();// 获得要展示的马的ID
		byte b = request.getByte();
		Horse horse = character.getCharacterHorseController().getBagHorseContainer().getHorseByID(id);
		// 验证展示的马有没有活力
		switch (b) {
		case 1:
			character.getCharacterHorseController().show(horse);
			break;
		case 2:
			character.getCharacterHorseController().unShow();
			break;
		case 3:
			character.getCharacterHorseController().drop(horse);
			break;
		}

	}
}
