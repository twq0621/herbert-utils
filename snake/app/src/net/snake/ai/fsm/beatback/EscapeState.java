package net.snake.ai.fsm.beatback;

import net.snake.ai.fsm.FSMState;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.commons.program.SafeTimer;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.monster.bean.SceneMonster;


/**
 * 逃跑状态
 * @author serv_dev
 *
 */
public class EscapeState extends FSMState {
	private static final int RESTTIME=3000;//逃跑到目地点后休息的时间
	private SafeTimer safetime=new SafeTimer();
	private boolean isToTargetPoint=false;//是否到了逃亡目标点	
	public EscapeState(MonsterFSM fsm, SceneMonster sceneMonster) {
		super(fsm, sceneMonster);
	}
	
	
	public boolean condition() {
		return !sceneMonster.isImmb();
	}
	

	@Override
	public void onUpdate(long now) {
		if (condition()) {
			escapeAction();
		}
	}

	protected void escapeAction() {
		if(!isToTargetPoint){
			isToTargetPoint=sceneMonster.getMoveController().checkArrived();
			if (isToTargetPoint) {				
				safetime.start(RESTTIME);
			}
		}else{
			if(safetime.isOK()){
				sceneMonster.setObjectState(VisibleObjectState.Idle);
			}		
		}		
	}
	public void onExit() {
		isToTargetPoint=false;
	}
}
