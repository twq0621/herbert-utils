package net.snake.gamemodel.skill.logic.buff.arrow;

import java.util.Collection;

import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.fight.response.CancelImmbMsg10154;
import net.snake.ai.fight.response.ImmbMsg10162;
import net.snake.commons.NetTool;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 失去方向
 */
public class Nodirection extends Buff {

	public Nodirection(EffectInfo effect) {
		super(effect);
	}
	
	@Override
	public boolean enter(EffectController controller) {
		VisibleObject affecter = controller.getVo();
		Collection<Hero> chars=affecter.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
		NetTool.sendToCharacters(chars, new ImmbMsg10162(ImmbMsg10162.Effect_Yinfeng, affecter));
		return true;
	}
	
	@Override
	public boolean leave(EffectController controller) {
		VisibleObject affecter = controller.getVo();
		Collection<Hero> chars=affecter.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
		NetTool.sendToCharacters(chars, new CancelImmbMsg10154(ImmbMsg10162.Effect_Yinfeng, affecter));
		return true;
	}
	
	public static Buff getInstance(EffectInfo effect) {
			return new Nodirection(effect);
	}
}
