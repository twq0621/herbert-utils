package net.snake.ai.fsm.state;

import java.util.Collection;

import net.snake.ai.formula.AttackFormula;
import net.snake.ai.fsm.FSMState;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.bean.SceneMonster;

/**
 * 巡逻状态
 * 
 * @author serv_dev
 * 
 */
public class PatrolState extends FSMState {

	public PatrolState(MonsterFSM fsm, SceneMonster monster) {
		super(fsm, monster);
	}

	@Override
	public void onBegin() {
		sceneMonster.getSceneMonsterLineManager().initLineManager();
		short[] path = sceneMonster.getSceneMonsterLineManager().getLine();
		if (path == null) {
			// LOGGER.warn("怪物{}自动巡逻的路径得到为空，请检查 .地图：{}", new Object[] {
			// sceneMonster.getId(), sceneMonster.getScene() });
			// sceneMonster.setStatus(Status.idle);
		} else {
			short targetX = path[path.length - 2];
			short targetY = path[path.length - 1];
			if (!AttackFormula.atkIsEnable2(sceneMonster.getOriginalX(), sceneMonster.getOriginalY(), targetX, targetY, (short) sceneMonster.getMonsterModel().getPatrol())) {
				LOGGER.warn("patrol point is out of patrol scope "+ sceneMonster.getOriginalX()+","+sceneMonster.getOriginalY()+",   "+targetX+","+targetY+","+sceneMonster.getMonsterModel().getPatrol());
			}
			boolean isWalkStop = false;
			Collection<SceneMonster> sceneMonsters = sceneMonster.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_MonsterScene);
			for (SceneMonster sm : sceneMonsters) {
				if (sceneMonster.getId().intValue() != sm.getId().intValue()) {
					if (sm.getX() == targetX && targetY == sm.getY()) {
						isWalkStop = true;
					}
				}
			}

			if (isWalkStop) {
				sceneMonster.setObjectState(VisibleObjectState.Idle);
			} else {
				sceneMonster.getMoveController().setWalkLines(path, System.currentTimeMillis());
			}
		}
	}

	@Override
	public void onExit() {
		sceneMonster.getMoveController().stopMove();
	}

	@Override
	public void onUpdate(long now) {
		if (sceneMonster.getMoveController().checkArrived()) {
			sceneMonster.setObjectState(VisibleObjectState.Idle);
		}
	}

}
