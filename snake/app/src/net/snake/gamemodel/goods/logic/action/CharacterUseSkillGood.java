package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.api.IUseItemListener;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bean.Skill;

/**
 * 使用技能物品进行技能操作
 * 
 * @author serv_dev
 * 
 */
public class CharacterUseSkillGood implements UseGoodAction {
	private Skill skill;

	public CharacterUseSkillGood(Skill skill) {
		this.skill = skill;
	}

	@Override
	public boolean useGoodDoSomething(Hero character, int goodId, int positon,List<IUseItemListener> listeners) {
		CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion((short) positon);
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			return false;
		}
		if (cg.getLastDate() != null) {
			if (System.currentTimeMillis() > cg.getLastDate().getTime()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 877));
				return false;
			}
		}
		if (skill.getSkilltype() == 1) {
			return false;
		}
		if (character.getCharacterSkillManager().learnSkill(skill)) {
			character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) positon, 1);
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1020, GoodmodelManager.getInstance().get(skill.getLearningBook()).getNameI18n(), skill.getNameI18n()));
			return true;
		} else {
			return false;
		}
	}
}
