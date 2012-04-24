package net.snake.gamemodel.skill.logic.buff.drug;

import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;

/**
 * 自动续命丹有50000点的血量
 * 
 * @author serv_dev
 * 
 */
public class XuMingDan extends Buff {

	public XuMingDan(EffectInfo effectInfo) {
		super(effectInfo);
	}

	@Override
	public boolean leaveCondition(long now) {
		VisibleObject voObject = effect.getAffecter();

		int hp = voObject.getPropertyAdditionController().getExtraMaxHp() - voObject.getNowHp();
		Hero character = (Hero) voObject;
		if (character.getObjectState() == VisibleObjectState.Shock) {
			return false;
		}
		if (character.getObjectState() == VisibleObjectState.Die) {
			return false;
		}
		if (hp > 0 && !character.getFightController().isPk()) {
			if (now - effect.getBufBeginTime() > effect.getDelayRecoverTime()) {// 延时
				effect.setBufBeginTime(now);
			} else {
				return false;
			}
			if (effect.getRemainPoint() < hp) {
				hp = effect.getRemainPoint();
			}
			CharacterPropertyManager.changeNowHp(character, hp);
			effect.setRemainPoint(effect.getRemainPoint() - hp);
			FightMsgSender.broastSustainEffect(effect, effect.getDelayRecoverTime(), voObject);
		}
		if (effect.getRemainPoint() <= 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean enter(EffectController controller) {
		if (effect.getDelayRecoverTime() == 0) {
			effect.randomDelayRecoverTime();
		}
		if (effect.getRemainPoint() == 0) {
			effect.setRemainPoint(effect.getHurtValue());
		}
		effect.setAffecter(controller.getVo());
		effect.setAttacker(controller.getVo());
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		return true;
	}

}
