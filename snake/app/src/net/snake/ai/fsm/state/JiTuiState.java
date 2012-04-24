package net.snake.ai.fsm.state;

import net.snake.ai.fsm.FSMState;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.monster.bean.SceneMonster;


public class JiTuiState extends FSMState {

	public JiTuiState(MonsterFSM fsm, SceneMonster sceneMonster) {
		super(fsm, sceneMonster);
	}
	
	
	@Override
	public void onUpdate(long now) {
		if (now - sceneMonster.getEffectController().getJiTuiBeginTime() > sceneMonster.getEffectController().getJiTuiTime()) {
			sceneMonster.setObjectState(VisibleObjectState.Attack);
		}
	}
}
