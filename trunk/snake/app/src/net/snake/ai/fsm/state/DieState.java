package net.snake.ai.fsm.state;

import net.snake.ai.fsm.FSMState;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.monster.bean.SceneMonster;

/***
 * 死亡
 * @author serv_dev
 * Copyright (c) 2011 Kingnet
 */
public class DieState extends FSMState {

	public DieState(MonsterFSM fsm, SceneMonster sceneMonster) {
		super(fsm, sceneMonster);
	}
	public void onBegin() {
		sceneMonster.getMoveController().stopMove();
		if (sceneMonster.getMonsterModel().getType()==3||sceneMonster.getMonsterModel().getType()==10) {
			long dieTime = sceneMonster.getDieTime() + 10000;
			sceneMonster.setDieTimestamp(dieTime);
			sceneMonster.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
				@Override
				public void run() {
					// 只有发了这个消息才会倒下
					sceneMonster.setFallDown(true);
					sceneMonster.getEyeShotManager().sendMsg(sceneMonster.getDieMsg());
				}
			}, 10000);
		} else {
			// 只有发了这个消息才会倒下
//			sceneMonster.setDieTimestamp(System.currentTimeMillis());
			sceneMonster.setFallDown(true);
			sceneMonster.getEyeShotManager().sendMsg(sceneMonster.getDieMsg());
		}
		if (sceneMonster.getWhoCatchMeManager() != null) {
			sceneMonster.getWhoCatchMeManager().removeAllCatcher();
		}
	}

	public void onUpdate(long now) {
		if (sceneMonster.isDieTimeOut(now)) {
			sceneMonster.setObjectState(VisibleObjectState.Dispose);
		}
	}

}
