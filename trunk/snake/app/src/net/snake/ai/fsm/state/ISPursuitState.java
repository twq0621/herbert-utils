package net.snake.ai.fsm.state;

import net.snake.ai.fsm.FSMState;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.monster.bean.SceneMonster;


/**
 * 准备追击状态
 * 
 * @author serv_dev
 * 
 */
public class ISPursuitState extends FSMState {
	public ISPursuitState(MonsterFSM fsm, SceneMonster monster) {
		super(fsm, monster);
	}

	@Override
	public void onUpdate(long now) {
		if (!sceneMonster.isImmb()) {
			ispursuitAction();
		}
	}

	private void ispursuitAction() {
		VisibleObject target = sceneMonster.getTarget();
		// 判断是否在区域之内
		// 1.追击到达的话 设置攻击状态
		// 2.追击未到达的话 继续追击 || !sceneMonster.isInMyPursuitScope(target)
		if (target == null ) {// 如果不在我的追击范围内
			sceneMonster.setObjectState(VisibleObjectState.IsReset);
			return;
		}
		
		if (sceneMonster.getMonsterModel().getSkin() == 1) {
			//喋喋不休的贴身
			sceneMonster.setObjectState(VisibleObjectState.Attack);
			short[] _ppoint = target.getPursuitPointManager().getPursuitPoint(sceneMonster, 1);
			if (_ppoint == null) {
				// 到不到追击目标点,怪物重置
				sceneMonster.setObjectState(VisibleObjectState.IsReset);
			}else {
				sceneMonster.getMoveController().instantMove(_ppoint[0], _ppoint[1]);
				sceneMonster.setObjectState(VisibleObjectState.Attack);
			}
			return ;
		}

		short[] _ppoint = target.getPursuitPointManager().getPursuitPoint(
				sceneMonster,sceneMonster.getSkillDistance());

		if (_ppoint == null) {
			// 到不到追击目标点,怪物重置
			sceneMonster.setObjectState(VisibleObjectState.IsReset);
		} else if (_ppoint[0]==sceneMonster.getX()&& _ppoint[1]==sceneMonster.getY()) {// 怪物位置不调整,直接可以攻击
			sceneMonster.setObjectState(VisibleObjectState.Attack);
		} else {
			sceneMonster.getMoveController().stopMove();
			short[] path = sceneMonster.findWay(_ppoint[0], _ppoint[1]);

			if (path == null || path.length < 4) {
				// 为空可能的原因是： 1.目标点是障碍物点 2.起始点是障碍物点
				// 怪物跑进一个封闭的区域内。无法出来继续追击玩家
				// 发生这样的情况检查一下路径是否有问题
				sceneMonster.haveArest();
			} else {
//				int as=sceneMonster.getSkillDistance();// sceneMonster.getMonsterModel().getAs() + 1
//				if (!AttackFormula.atkIsEnable(sceneMonster.getX(),
//						sceneMonster.getY(), target.getX(), target.getY(),
//						(short) (as + 1))) {// 真实算得+1
//					// cellNum = cellNum - as;// exa:
//					// 范围为2，攻击范围反减
//					// 1，2，3，4，5 只取得
//					// 1，2，3坐标
//					// 攻击范围 不论周围有没有障碍物
					sceneMonster.getMoveController().setWalkLines(path,System.currentTimeMillis());
					sceneMonster.setObjectState(VisibleObjectState.Pursuit);
//				} else {
//					sceneMonster.setStatus(MonsterStateConsts.attack);
//				}
			}
		}

	}
}
