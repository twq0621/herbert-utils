package net.snake.ai.fsm;

import org.apache.log4j.Logger;

import net.snake.ai.fsm.state.AttackState;
import net.snake.ai.fsm.state.DieState;
import net.snake.ai.fsm.state.DisposeState;
import net.snake.ai.fsm.state.EscapeState;
import net.snake.ai.fsm.state.ISEscapeState;
import net.snake.ai.fsm.state.ISPursuitState;
import net.snake.ai.fsm.state.ISResetState;
import net.snake.ai.fsm.state.IdleState;
import net.snake.ai.fsm.state.JiTuiState;
import net.snake.ai.fsm.state.PatrolState;
import net.snake.ai.fsm.state.PursuitState;
import net.snake.ai.fsm.state.ResetState;
import net.snake.ai.fsm.state.ShockState;
import net.snake.ai.fsm.state.ShockWaitingState;
import net.snake.commons.program.Updatable;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.monster.bean.SceneMonster;


/**
 * 怪物状态机管理
 * 
 * @author serv_dev
 * 
 */
public class MonsterFSM implements Updatable {

	private static Logger logger = Logger.getLogger(MonsterFSM.class);
	private SceneMonster sceneMonster;

	protected FSMState disposeState;// 消失或等待上场状态

	protected FSMState idelState;// 空闲状态

	protected FSMState attackState;// 攻击状态

	protected FSMState patrolState;// 巡逻状态

	protected FSMState pursuitState;// 追击状态

	protected FSMState ispursuitState;// 准备追击状态

	protected FSMState isescapeState;// 准备逃跑状态

	protected FSMState escapeState;// 逃跑状态

	protected FSMState dieState;// 逃跑状态

	protected FSMState resetState;// 复位状态

	protected FSMState isresetState;// 准备复位状态
	
	protected FSMState shockWaitState;
	
	protected FSMState shockState;

//	protected FSMState followState;// 跟随
//	protected FSMState followAttackState;// 跟随攻击
//	protected FSMState followPursuitState;// 跟随追击
//	protected FSMState followIdelState;// 跟随追击
	
	protected FSMState jituiState;// 击退状态

	// ===========================
	private FSMState currentState;// 当前状态

	public MonsterFSM(SceneMonster monster) {
		this.sceneMonster = monster;
	}

	protected boolean init = false;

	/**
	 * 初始化
	 */
	public void initFSM() {
		if (init) {
			return;
		}
		init = true;
		if (sceneMonster.getMonsterModel().getBeatBack() == 1) {
			idelState = new net.snake.ai.fsm.beatback.IdleState(this, sceneMonster);
			patrolState = new PatrolState(this, sceneMonster);
			dieState = new DieState(this, sceneMonster);
			disposeState = new DisposeState(this, sceneMonster);
			
			attackState = new net.snake.ai.fsm.beatback.AttackState(this, sceneMonster);
			isescapeState = new net.snake.ai.fsm.beatback.ISEscapeState(this, sceneMonster);
			escapeState = new net.snake.ai.fsm.beatback.EscapeState(this, sceneMonster);
			resetState = new net.snake.ai.fsm.beatback.ResetState(this, sceneMonster);
			isresetState = new net.snake.ai.fsm.beatback.ISResetState(this, sceneMonster);
			
			shockWaitState = new ShockWaitingState(this, sceneMonster);
			shockState = new ShockState(this, sceneMonster);
			return;
		} else {
			idelState = new IdleState(this, sceneMonster);
			patrolState = new PatrolState(this, sceneMonster);
			attackState = new AttackState(this, sceneMonster);
			pursuitState = new PursuitState(this, sceneMonster);
			ispursuitState = new ISPursuitState(this, sceneMonster);
			isescapeState = new ISEscapeState(this, sceneMonster);
			escapeState = new EscapeState(this, sceneMonster);
			dieState = new DieState(this, sceneMonster);
			resetState = new ResetState(this, sceneMonster);
			isresetState = new ISResetState(this, sceneMonster);
			disposeState = new DisposeState(this, sceneMonster);
//			followState = new FollowNomalState(this, sceneMonster);
//			followAttackState = new FollowAttackState(this, sceneMonster);
//			followPursuitState = new FollowPursuitState(this, sceneMonster);
//			followIdelState = new FollowIdelState(this, sceneMonster);
			jituiState = new  JiTuiState(this, sceneMonster);
			
			shockWaitState = new ShockWaitingState(this, sceneMonster);
			shockState = new ShockState(this, sceneMonster);
		}
	}

	protected SceneMonster getSceneMonster() {
		return sceneMonster;
	}

	/**
	 * 取得状态机状态
	 * 
	 * @param status
	 * @return 状态
	 */
	private FSMState getFSMState(int status) {
		if (status == VisibleObjectState.Idle) {
			return idelState;
		} else if (status == VisibleObjectState.Patrol) {
			return patrolState;
		} else if (status == VisibleObjectState.Attack) {
			return attackState;
		} else if (status == VisibleObjectState.Pursuit) {
			return pursuitState;
		} else if (status == VisibleObjectState.Ispursuit) {
			return ispursuitState;
		} else if (status == VisibleObjectState.Escape) {
			return escapeState;
		} else if (status == VisibleObjectState.Isescape) {
			return isescapeState;
		} else if (status == VisibleObjectState.Die) {
			return dieState;
		} else if (status == VisibleObjectState.Reset) {
			return resetState;
		} else if (status == VisibleObjectState.IsReset) {
			return isresetState;
		} else if (status == VisibleObjectState.Dispose) {
			return disposeState;
		}  else if (status == VisibleObjectState.Jitui) {
			return jituiState;
		}else if (status == VisibleObjectState.ShockWaiting) {
			return shockWaitState;
		}else if (status == VisibleObjectState.Shock) {
			return shockState;
		}
		return null;
	}

	@Override
	public void update(long now) {
		try {
			sceneMonster.getEffectController().update(now);
			
			if (currentState != null) {
				currentState.onUpdate(now);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	/**
	 * 
	 * 选择状态
	 * 
	 * @param status
	 * 
	 */
	public void switchState(int status) {
		FSMState state = getFSMState(status);
		if (state == null) {
			return;
		}
		if (currentState == state) {
			return;
		}
		if (currentState != null) {
			currentState.onExit();
		}
		currentState = state;
		state.onBegin();
	}

	/** 当改变对像目标时*/
	public void onChangeTarget(VisibleObject target) {
		if (target == null) {
			if (!sceneMonster.isDie()&& sceneMonster.getObjectState()!=VisibleObjectState.Shock) {// 怪物还活着,但是仇恨目标不在了
				sceneMonster.haveArest();
			}
		} else {// 怪物最高仇恨目标改变了
			if (currentState == pursuitState || currentState == attackState
					|| currentState == idelState) {//
				sceneMonster.setObjectState(VisibleObjectState.Attack);
			}
		}
	}
	public void onOwnerAttack(VisibleObject obj) {
		if(currentState!=null){
			currentState.onOwnerAttack(obj);
		}
	}

}
