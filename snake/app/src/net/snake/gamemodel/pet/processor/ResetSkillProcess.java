package net.snake.gamemodel.pet.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CopperAction;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.response.CombineFailMsg50150;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.response.ResetSkillInfoResponse60020;
import net.snake.netio.message.RequestMsg;

/**
 * 重置灵宠技能 60019
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 60019)
public class ResetSkillProcess extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 灵宠ID(int)，请求类型(1展示,2收起,3放生)（byte）

		int id = request.getInt();// 获得要展示的马的ID
		byte num = request.getByte();
		if (num == 0) {
			return;
		}
		int skillPos[] = new int[num];
		for (int i = 0; i < num; i++) {
			skillPos[i] = request.getByte();
			if (skillPos[i] > 4) {// 可以重置的技能为普通技能（0-4），神技不可以重置
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 60055));
				return;
			}
		}
		Horse horse = character.getCharacterHorseController().getBagHorseContainer().getHorseByID(id);
		int copper = horse.getHorseModel().getResetSkillCopper();
		int zhenqi = horse.getHorseModel().getResetSkillZhenqi();
		if (character.getZhenqi() < zhenqi) {
			// 真气不足
			character.sendMsg(new CombineFailMsg50150(3));
			return;
		}
		if (character.getCopper() < copper) {
			// 铜币不足
			character.sendMsg(new CombineFailMsg50150(14));
			return;
		}

		CharacterPropertyManager.changeCopper(character, -copper, CopperAction.CONSUME);
		CharacterPropertyManager.changeZhenqi(character, -zhenqi);
		int hasSuodingNum = character.getCharacterGoodController().getBagGoodsCountByModelID(GoodItemId.SuoDingCaiLiao);
		int allcount = horse.getHorseskillManager().getAllSkillCount();
		if(allcount>5){
			allcount = 5;
		}
		int suodingNum = allcount - skillPos.length;
		if (suodingNum > hasSuodingNum) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 60054));
			return;
		}
		if (!character.getCharacterGoodController().deleteCailiao(GoodItemId.SuoDingPetResetSkill, suodingNum)) {
			return;
		}
		horse.getSkillManager().randomHorseSkill(skillPos);
		character.sendMsg(new ResetSkillInfoResponse60020(horse));
	}
}
