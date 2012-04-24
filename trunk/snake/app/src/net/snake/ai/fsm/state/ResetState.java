package net.snake.ai.fsm.state;

import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.fsm.FSMState;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.consts.CommonUseNumber;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.monster.bean.SceneMonster;

/**
 * 回家
 * @author serv_dev
 * Copyright (c) 2011 Kingnet
 */
public class ResetState extends FSMState {

	public ResetState(MonsterFSM fsm, SceneMonster sceneMonster) {
		super(fsm, sceneMonster);
	}

	@Override
	public void onUpdate(long now) {
		if (!sceneMonster.isImmb()) {
			resetAction();
		}
	}

	protected void resetAction() {
		if (sceneMonster.getMoveController().checkArrived()) {
			sceneMonster.setObjectState(VisibleObjectState.Idle);
			sceneMonster.forgetAnthing();
			sceneMonster.setNowHp(sceneMonster.getMaxHp());
			FightMsgSender.directHurtBroadCase(null, sceneMonster, 0,CommonUseNumber.byte0);
		}
	}
}
