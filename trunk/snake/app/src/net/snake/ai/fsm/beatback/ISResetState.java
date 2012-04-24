package net.snake.ai.fsm.beatback;

import net.snake.ai.fsm.FSMState;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.monster.bean.SceneMonster;

public class ISResetState extends FSMState {
	long starttime;

	public ISResetState(MonsterFSM fsm, SceneMonster sceneMonster) {
		super(fsm, sceneMonster);
	}

	@Override
	public void onBegin() {
		starttime = System.currentTimeMillis();
	}

	@Override
	public void onUpdate(long now) {
		if (!sceneMonster.isImmb()) {
			isresetAction();
		}
	}

	protected void isresetAction() {
		short orginalX = (short) sceneMonster.getOriginalX();
		short orginalY = (short) sceneMonster.getOriginalY();
		short[] path = sceneMonster.findWay(orginalX, orginalY);
		sceneMonster.getMoveController().setWalkLines(path, System.currentTimeMillis());
		sceneMonster.setObjectState(VisibleObjectState.Reset);
	}

}
