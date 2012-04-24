package net.snake.ai.fsm.state;


import net.snake.ai.fsm.FSMState;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.ShockImg;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.shell.Options;

/**
 * 将玩家杀至濒死时进入等待玩家服不服的状态。
 * 
 * @author serv_dev
 * Copyright (c) 2011 Kingnet
 */
public class ShockWaitingState extends FSMState {

	private long shockWait_timeout;
	public ShockWaitingState(MonsterFSM fsm, SceneMonster sceneMonster) {
		super(fsm, sceneMonster);
		shockWait_timeout=(Options.Shock_Timeout-1)*1000;
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
		boolean zero=sceneMonster.isZeroHp();
		if (zero) {
			return ;
		}
		
		ShockImg img=sceneMonster.getShockSomeoneImg();
		if (img.shockTimestamp==-1) {
			sceneMonster.setObjectState(VisibleObjectState.Idle);
			return ;
		}
		long shockedTime=img.shockTimestamp;
		long diff = now- shockedTime;
		
		if (diff < shockWait_timeout) {
			return ;
		}
		int shockedId=img.shockedId;
		Hero shocked=sceneMonster.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero, shockedId);
		if (shocked ==null) {
			sceneMonster.setTarget(null);
			sceneMonster.setObjectState(VisibleObjectState.Idle);
			return ;
		}
		if (shocked.getObjectState()==VisibleObjectState.Die) {
			sceneMonster.setTarget(null);
			sceneMonster.setObjectState(VisibleObjectState.Idle);
			return ;
		}
		sceneMonster.setTarget(shocked);
		sceneMonster.setObjectState(VisibleObjectState.Attack);
	}

	@Override
	public void reset() {
	}
}
