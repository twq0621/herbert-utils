package net.snake.ai.fsm.state;

import java.util.List;

import net.snake.ai.fsm.FSMState;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.api.IAttackListener;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.SceneMonster;

//import org.apache.log4j.Logger;

/**
 * 攻击状态
 * 
 * @author serv_dev
 * 
 */
public class AttackState extends FSMState {
//	private static final Logger logger = Logger.getLogger(AttackState.class);

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
		if (!sceneMonster.isImmb()) {
			attackAction();
		}
	}

	private void attackAction() {
		VisibleObject targetObject =sceneMonster.getTarget();
		if (targetObject == null) {
			sceneMonster.setObjectState(VisibleObjectState.IsReset);
			return ;
		}
		
		Scene scene= sceneMonster.getSceneRef();
		List<IAttackListener> attackListeners=scene.getAttackListeners();
		int size = attackListeners.size();
		for(int i=0;i<size;i++){
			IAttackListener listener=attackListeners.get(i);
			if (!listener.beforeAttack(sceneMonster, sceneMonster.getTarget())) {
				sceneMonster.setObjectState(VisibleObjectState.Idle);
				return ;
			}
		}
		
		if (sceneMonster.getTarget() != null) {
			sceneMonster.getFightController().autofight();

		} else {
			sceneMonster.setObjectState(VisibleObjectState.IsReset);
		}
	}

	@Override
	public void reset() {
	}

}
