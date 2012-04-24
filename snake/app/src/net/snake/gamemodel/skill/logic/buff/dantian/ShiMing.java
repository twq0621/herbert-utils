package net.snake.gamemodel.skill.logic.buff.dantian;

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
 * 失明
 * 
 * @author serv_dev
 * 
 */
public class ShiMing extends Buff {

	public ShiMing(EffectInfo effect) {
		super(effect);
	}

	@Override
	public boolean enter(EffectController controller) {
		VisibleObject affecter = effect.getAffecter();

		if (affecter.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			Hero character = (Hero) affecter;
			Collection<Hero> chars = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
			NetTool.sendToCharacters(chars, new ImmbMsg10162(ImmbMsg10162.Effect_Shiming, character));
		}
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		VisibleObject vo = controller.getVo();
		Collection<Hero> chars = vo.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
		NetTool.sendToCharacters(chars, new CancelImmbMsg10154(ImmbMsg10162.Effect_Shiming, vo));
		return true;
	}

	public static Buff getInstance(EffectInfo effect) {
		return new ShiMing(effect);
	}
}
