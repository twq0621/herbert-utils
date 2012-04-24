package net.snake.gamemodel.skill.logic.buff.drug;

import net.snake.ai.fight.controller.EffectController;
import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.events.AppEventCtlor;
//import net.snake.script.ScriptEventCenter;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;

/**
 * 防御药剂
 * @author serv_dev
 *
 */
public class FYYaoJi extends Buff {

	public FYYaoJi(EffectInfo effectInfo) {
		super(effectInfo);
	}

	@Override
	public boolean enter(EffectController controller) {
		if (controller.getFyYaoBuff() != null) {
((Hero)controller.getVo()).sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,761));
			return false;
		}
		controller.setFyYaoBuff(effect);
		controller.getVo().getPropertyAdditionController().addChangeListener(this);
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		controller.setFyYaoBuff(null);
		controller.getVo().getPropertyAdditionController().removeChangeListener(this);
		return true;
	}
	
	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.specialYJ;
	}
	
	@Override
	public PropertyEntity getPropertyEntity() {
		PropertyEntity pe = new PropertyEntity();
		pe.addExtraProperty(Property.defence, AppEventCtlor.getInstance().getEvtDrugFormula().fangyuPrecent());
		return pe;
	}

}
