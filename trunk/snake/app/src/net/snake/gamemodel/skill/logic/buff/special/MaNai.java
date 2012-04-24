package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.controller.EffectController;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;
import org.apache.log4j.Logger;


public class MaNai extends Buff {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MaNai.class);

	public MaNai(EffectInfo effect) {
		super(effect);
	}

	@Override
	public boolean enter(EffectController controller) {
		// TODO 产生一个马奶的道具
		VisibleObject affecter = effect.getAffecter();
		if (affecter.getSceneObjType()==SceneObj.SceneObjType_Hero) {
			Hero character = (Hero) affecter;
			Goodmodel goodmodel = GoodmodelManager.getInstance().get(
					effect.getGoodmodelId());

			if (goodmodel == null) {
				if (logger.isInfoEnabled()) {
					logger.warn("enter(EffectController) - no good when skill about manai"); //$NON-NLS-1$
					return false;
				}
			}

			CharacterGoods characterGood = CharacterGoods.createCharacterGoods(
					1, goodmodel, 0, 0);
			characterGood.setBind(CommonUseNumber.byte0);

			character.getCharacterGoodController().getBagGoodsContiner()
					.addGoods(characterGood);
		}
		return false;
	}

	@Override
	public boolean leave(EffectController controller) {
		return false;
	}

}
