package net.snake.ai.fsm.state;

import net.snake.ai.fsm.FSMState;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;

/**
 * 消失
 * 
 * @author serv_dev Copyright (c) 2011 Kingnet
 */
public class DisposeState extends FSMState {

	public DisposeState(MonsterFSM fsm, SceneMonster sceneMonster) {
		super(fsm, sceneMonster);
	}

	@Override
	public void onBegin() {
		// sceneMonster.setDropGoods(null);
		// sceneMonster.setGoodscharacter(null);
		sceneMonster.clearHatesetHurtList();
		// sceneMonster.setFirstAttackPlayer(null);
		sceneMonster.setNowHp(0);

		Scene scene = sceneMonster.getSceneRef();
		if (scene != null) {
			scene.leaveScene(sceneMonster);
		}

		sceneMonster.setX(sceneMonster.getOriginalX());
		sceneMonster.setY(sceneMonster.getOriginalY());
		sceneMonster.setFallDown(false);
		sceneMonster.getDropGoodManager().setCharacter(null);
		if (sceneMonster.getRelive() == 1) {
			MonsterModel model = sceneMonster.getMonsterModel();
			sceneMonster.setReservationTime(System.currentTimeMillis() + model.getWaittime() * 1000);
			sceneMonster.getSceneRef().addToRefreshMonsterController(sceneMonster);// 添加到候补队列
		} else {
			sceneMonster.destory();
		}

	}

}
