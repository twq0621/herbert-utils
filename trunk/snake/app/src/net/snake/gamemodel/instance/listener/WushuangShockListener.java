package net.snake.gamemodel.instance.listener;

import net.snake.api.IShockListener;
import net.snake.commons.VisibleObjectState;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.Position;
import net.snake.consts.TakeMethod;
import net.snake.gamemodel.fabao.response.RideFabaoResponse;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterResurrect;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.logic.Scene;

public class WushuangShockListener implements IShockListener {
	@Override
	public boolean onShocked(VisibleObject object, VisibleObject attacker, Scene scene) {
		if (scene.getInstance() == null) {
			return true;
		}
		if (scene.getInstance().getInstanceId() != 1) {
			return true;
		}

		Hero hero = (Hero) object;
		CharacterGoods fabaogoods = hero.getCharacterGoodController().getBodyGoodsContiner().getGoodsByPostion(Position.POSTION_TESHU);
		if (fabaogoods != null && fabaogoods.getIsUse() == 1) {
			hero.getEquipmentController().changeProperty(hero, fabaogoods, TakeMethod.off);
			hero.getEyeShotManager().sendMsg(new RideFabaoResponse(hero.getId(), CommonUseNumber.byte0, fabaogoods.getGoodmodelId()));
		}
		// long now = System.currentTimeMillis();
		hero.getMyCharacterVipManger().removeHuQiFeibaohuBuffer();

		hero.setObjectState(VisibleObjectState.Die);// /////////////////////////
		hero.getMoveController().stopMove();
		hero.getEffectController().clearEffectListAndRemoveBuffOnBody();

		hero.getPursuitPointManager().clearArroundWithMeInFightMonsterPositions();
		hero.getCharacterHorseController().onOwnerDie();

		hero.getFightController().releaseFightStatus();
		hero.getEnmityManager().clearEnmityList();
		// 我死了,把我的仇恨移除掉
		hero.getEnmityManager().clearWhosEnmityisMe();
		attacker.getEnmityManager().removeFromMyEnmityList(hero);
		hero.setTarget(null);// 最后是死在谁手里的

		attacker.setObjectState(VisibleObjectState.Idle);
		// hero.someoneShocksMe(attacker.getId(), SceneMonster.class, hero.getId(), Hero.class, now);
		// attacker.iShockSomeone(attacker.getId(), SceneMonster.class, hero.getId(), Hero.class, now);

		CharacterResurrect.huichengFuhuo(hero);
		// scene.getInstance().missionFailed();
		// hero.setObjectState(VisibleObjectState.Idle);
		return false;
	}
}
