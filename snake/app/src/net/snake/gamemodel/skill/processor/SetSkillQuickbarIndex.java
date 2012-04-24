package net.snake.gamemodel.skill.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.netio.message.RequestMsg;

@MsgCodeAnn(msgcode = 10277)
public class SetSkillQuickbarIndex extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int skillid = request.getInt();
		int index = request.getByte();
		CharacterSkill characterskill = character.getCharacterSkillManager().getCharacterSkillById(skillid);
		if (characterskill == null) {
			// 找不到技能
			return;
		}
		character.getQuickbarController().setQuickBarGoods(index, characterskill);
	}
}
