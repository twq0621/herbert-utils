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
 * 健体药剂
 * @author serv_dev
 *
 */
public class JTYaoJi extends Buff {

	public JTYaoJi(EffectInfo effectInfo) {
		super(effectInfo);
	}

	@Override
	public boolean enter(EffectController controller) {
		if (controller.getJtYaoBuff() != null) {
((Hero)controller.getVo()).sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,761));
			return false;
		}
		controller.setJtYaoBuff(effect);
		controller.getVo().getPropertyAdditionController().addChangeListener(this);
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		controller.setJtYaoBuff(null);
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
		pe.addExtraProperty(Property.maxHp, AppEventCtlor.getInstance().getEvtDrugFormula().jianTiHpPrecent());
		pe.addExtraProperty(Property.maxMp, AppEventCtlor.getInstance().getEvtDrugFormula().jianTiMpPrecent());
		pe.addExtraProperty(Property.maxSp, AppEventCtlor.getInstance().getEvtDrugFormula().jianTiSp());
		return pe;
	}

}
