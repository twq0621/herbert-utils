package net.snake.ai.fsm.beatback;

import net.snake.ai.fsm.FSMState;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.gamemodel.monster.bean.SceneMonster;


/**
 * 攻击状态
 * 
 * @author serv_dev
 * 
 */
public class AttackState extends FSMState {

	public AttackState(MonsterFSM fsm, SceneMonster sceneMonster) {
		super(fsm, sceneMonster);
	}

	@Override
	public void onBegin() {
		sceneMonster.getMoveController().stopMove();
	}

	@Override
	public void onExit() {
	}

	@Override
	public void onUpdate(long now) {
			attackAction();
	}

	private void attackAction() {
		sceneMonster.setObjectState(net.snake.commons.VisibleObjectState.Isescape);
	}

	@Override
	public void reset() {
	}

}
