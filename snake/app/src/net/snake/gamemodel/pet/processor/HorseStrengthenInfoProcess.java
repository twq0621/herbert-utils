package net.snake.gamemodel.pet.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.response.HorseStrengthenInfoResponse60038;
import net.snake.netio.message.RequestMsg;

/**
 * 灵宠强化消耗查询
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 60037)
public class HorseStrengthenInfoProcess extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 灵宠ID(int)，请求类型(1展示,2收起,3放生)（byte）

		int id = request.getInt();// 获得要展示的马的ID
		Horse horse = character.getCharacterHorseController().getBagHorseContainer().getHorseByID(id);
		CharacterHorse characterHorse = horse.getCharacterHorse();
		HorseModel horseModel = horse.getHorseModel();
		if (characterHorse.getPin() > 5) {
			character.sendMsg(new HorseStrengthenInfoResponse60038());
			return;
		}
		// HeroXingfu heroxingfu = character.getMyHeroXingfuController().getHeroXingfu(EquipmentPlayType.horseStrengthen, horseModel.getId());
		character.sendMsg(new HorseStrengthenInfoResponse60038(horseModel, characterHorse.getJinjieCount()));
	}

}
