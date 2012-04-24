package net.snake.gamemodel.pet.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.response.CombineFailMsg50150;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.catchpet.CharacterHorseController;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.netio.message.RequestMsg;

/**
 * 灵宠活力恢复 c-->s 60033 请求 灵宠ID(int)
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 60033)
public class HorseLivingnessRestorationProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int horseId = request.getInt();
		CharacterHorseController controller = character.getCharacterHorseController();
		Horse horse = controller.getHorseById(horseId);
		if (horse == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 889));
			return;
		}
		if (character.getZhenqi() <= 0) {
			character.sendMsg(new CombineFailMsg50150(3));
			return;
		}
		horse.getHorseRaiseManager().upLivingness();
	}
}
