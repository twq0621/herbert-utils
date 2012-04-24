package net.snake.ai.fsm.beatback;

import net.snake.ai.fsm.FSMState;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.monster.bean.SceneMonster;


/**
 * 准备逃跑状态
 * 
 * @author serv_dev
 * 
 */
public class ISEscapeState extends FSMState {

	public ISEscapeState(MonsterFSM fsm, SceneMonster sceneMonster) {
		super(fsm, sceneMonster);
	}

	@Override
	public void onUpdate(long now) {
		isescapeAction();
	}

	protected void isescapeAction() {
		short[] path = sceneMonster.getSceneRef().getRandomPathII(sceneMonster,
				12);
		if (path != null) {
			sceneMonster.getMoveController().setWalkLines(path,
					System.currentTimeMillis());
			sceneMonster.setObjectState(VisibleObjectState.Escape);
		} else {
			sceneMonster.setObjectState(VisibleObjectState.Idle);
		}
	}
}
