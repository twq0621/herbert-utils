package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 双倍经验(药品)
 * @author serv_dev
 *
 */
public class Double5ExpY extends Buff {

	public Double5ExpY(EffectInfo effect) {
		super(effect);
	}

	@Override
	public boolean enter(EffectController controller) {
		
		controller.setDouble5Exp(true);
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		controller.setDouble5Exp(false);
		return true;
	}
	
	
	@Override
	public boolean init(EffectController controller) {
		if (!(controller.getVo().getSceneObjType()==SceneObj.SceneObjType_Hero)) return false;
		
		Hero character = (Hero)(controller.getVo());
		if (character.getDoubExpNum() >= 4) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,20083));
			return false;
		}
		
		return true;
	}
}
