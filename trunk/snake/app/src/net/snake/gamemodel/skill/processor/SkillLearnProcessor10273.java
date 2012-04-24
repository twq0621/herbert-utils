package net.snake.gamemodel.skill.processor;

import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.logic.CharacterSkillManager;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.netio.message.RequestMsg;


public class SkillLearnProcessor10273 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		int skillId = request.getInt();
		CharacterSkillManager characterSkillManager = (CharacterSkillManager) character
				.getSkillManager();
		Skill _skill = SkillManager.getInstance().getSkillById(skillId);
		if (_skill == null)
			return;
		if (character.getCharacterGoodController().getBagGoodsCountByModelID(
				_skill.getLearningBook()) > 0) {
			if (characterSkillManager.learnSkill(_skill)) {
				character.getCharacterGoodController().deleteGoodsFromBag(
						_skill.getLearningBook(), 1);
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1020,
						GoodmodelManager.getInstance()
								.get(_skill.getLearningBook()).getNameI18n(),
						_skill.getNameI18n()));

			}
		} else {
			//
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1034,
					GoodmodelManager.getInstance()
							.get(_skill.getLearningBook()).getNameI18n()));
		}
	}
}
