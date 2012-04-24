package net.snake.ai.fsm.beatback;

import net.snake.ai.fsm.FSMState;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.monster.bean.SceneMonster;


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
		}
	}
}
