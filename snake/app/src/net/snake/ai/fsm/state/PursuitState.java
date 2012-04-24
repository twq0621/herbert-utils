package net.snake.ai.fsm.state;

import net.snake.ai.formula.AttackFormula;
import net.snake.ai.fsm.FSMState;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.monster.bean.SceneMonster;

/**
 * 追击
 * 
 * @author serv_dev Copyright (c) 2011 Kingnet
 */
public class PursuitState extends FSMState {
	// 上一次检查是否追击状态的时间
	private long previousIsPursuitCheckTime = 0;
	private static final long checkinterval = 2000;
	private short previousX;
	private short previousY;

	public PursuitState(MonsterFSM fsm, SceneMonster monster) {
		super(fsm, monster);
	}

	@Override
	public void onUpdate(long now) {
		if (!sceneMonster.isImmb()) {
			pursuitAction();
		}
	}

	@Override
	public void onExit() {
		previousIsPursuitCheckTime = 0;
	}

	private void pursuitAction() {

		// 超出了追击范围，准备回家
		if (!checkPursuitScope()) {
			sceneMonster.getMoveController().stopMove();
			sceneMonster.setObjectState(VisibleObjectState.IsReset);
			return;
		}
		if (sceneMonster.getMoveController().checkArrived()) {// 追击到了玩家？
			VisibleObject target = sceneMonster.getTarget();
			if (target != null) {
				// MonsterModel model = sceneMonster.getMonsterModel();
				// TODO 计算可以开始攻击的距离
				int as = sceneMonster.getSkillDistance();
				if (AttackFormula.atkIsEnable2(sceneMonster.getX(), sceneMonster.getY(), target.getX(), target.getY(), (short) (as))) {// //真实算得+1
					sceneMonster.setObjectState(VisibleObjectState.Attack);
				} else {
					// 怪物位置有变化时,再次检查是否需要追击
					if (previousX != sceneMonster.getX() || previousY != sceneMonster.getY() || System.currentTimeMillis() - previousIsPursuitCheckTime > checkinterval) {
						previousIsPursuitCheckTime = System.currentTimeMillis();
						previousX = sceneMonster.getX();
						previousY = sceneMonster.getY();
						sceneMonster.setObjectState(VisibleObjectState.Ispursuit);
					} else {
						// 忽略但不改变状态
					}

				}
			}
		}

	}

	/**
	 * 检查追击范围
	 */
	private boolean checkPursuitScope() {
		int pursuitScope = sceneMonster.getPursuitScope();
		short originalX = sceneMonster.getOriginalX();
		short originalY = sceneMonster.getOriginalY();
		short x = sceneMonster.getX();
		short y = sceneMonster.getY();
		if (x > originalX + pursuitScope || x < originalX - pursuitScope || y > originalY + pursuitScope || y < originalY - pursuitScope) {
			return false;
		} else {
			return true;
		}
	}

}
/*
 * LOGGER.debug("追击到得时候怪物坐标{},{}，玩家当前坐标{},{}", new Object[] { sceneMonster.getX(), sceneMonster.getY(), target.getX(), target.getY() });
 */

/*
 * LOGGER.debug("追击的距离{},{}不足，继续追击", new Object[] { DistanceFormula.getDistance2(sceneMonster.getX() , sceneMonster.getY() , target.getX(), target.getY()), (model.getAs() + 1) });
 */
