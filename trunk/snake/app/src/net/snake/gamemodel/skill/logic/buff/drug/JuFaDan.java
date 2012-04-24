package net.snake.gamemodel.skill.logic.buff.drug;

import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 
自动聚法丹：
使用后在内力减少时自动补满内力，内力减少后延迟（随机1-10秒）补满全部内力，并PK中无效，每个自动聚法丹有50000点的内力  售价：12元宝
 * @author serv_dev
 *
 */
public class JuFaDan extends Buff {

	public JuFaDan(EffectInfo effectInfo) {
		super(effectInfo);
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
	
	@Override
	public boolean leaveCondition(long now) {
		VisibleObject voObject = effect.getAffecter();
		
		int mp = voObject.getPropertyAdditionController().getExtraMaxMp() - voObject.getNowMp();
		Hero character = (Hero)voObject;
		
		if (character.isZeroHp() || character.getObjectState() == VisibleObjectState.Die) {
			return false;
		}
		
		if (character.getEffectController().isMpOver()) {
			return false;
		}
		
		if (mp > 0 && !character.getFightController().isPk()) {
			if (now - effect.getBufBeginTime() > effect.getDelayRecoverTime()) {//延时
				effect.setBufBeginTime(now);
			}else {
				return false;
			}
			
			if (effect.getRemainPoint() < mp) {
				mp = effect.getRemainPoint();
			}
			
			CharacterPropertyManager.changeNowMp(character, mp);
			effect.setRemainPoint(effect.getRemainPoint() - mp);
			FightMsgSender.broastSustainEffect(effect,effect.getDelayRecoverTime(),voObject);
		}
		if (effect.getRemainPoint() <= 0) return true;
		return false;
	}

}
