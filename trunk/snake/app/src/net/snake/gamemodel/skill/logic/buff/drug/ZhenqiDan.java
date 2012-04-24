package net.snake.gamemodel.skill.logic.buff.drug;

import java.util.List;

import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.api.IBuffListneer;
import net.snake.commons.VisibleObjectState;
import net.snake.consts.MaxLimit;
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
public class ZhenqiDan extends Buff {

	public ZhenqiDan(EffectInfo effectInfo) {
		super(effectInfo);
	}

	@Override
	public boolean leaveCondition(long now) {
		VisibleObject voObject = effect.getAffecter();
		Hero character = null;
		if(voObject instanceof Hero){
			character = (Hero)voObject;
		}else{
			return true;
		}

		int zhenqi = MaxLimit.ZHENQI_MAX - character.getZhenqi();
		if (character.getObjectState() == VisibleObjectState.Shock) {
			return false;
		}
		if (character.getObjectState() == VisibleObjectState.Die) {
			return false;
		}
		if (zhenqi > 0 && !character.getFightController().isPk()) {
			if (now - effect.getBufBeginTime() > effect.getDelayRecoverTime()) {// 延时
				effect.setBufBeginTime(now);
			} else {
				return false;
			}
			if (effect.getRemainPoint() < zhenqi) {
				zhenqi = effect.getRemainPoint();
			}
			CharacterPropertyManager.changeZhenqi(character, zhenqi);
			effect.setRemainPoint(effect.getRemainPoint() - zhenqi);
			FightMsgSender.broastSustainEffect(effect, effect.getDelayRecoverTime(), voObject);
			
			List<IBuffListneer> list=character.getSceneRef().getBuffListneers();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				IBuffListneer listneer = list.get(i);
				listneer.onBuff(character, (short)effect.getEffect().getType(), zhenqi);
			}
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
