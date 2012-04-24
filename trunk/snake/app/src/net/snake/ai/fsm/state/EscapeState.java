package net.snake.ai.fsm.state;

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
	/**逃跑到目地点后休息的时间*/
	private static final int RESTTIME=3000;//
	private SafeTimer safetime=new SafeTimer();
	/**是否到了逃亡目标点*/
	private boolean isToTargetPoint=false;//	
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
				sceneMonster.setObjectState(VisibleObjectState.Attack);
			}		
		}		
	}
	public void onExit() {
		isToTargetPoint=false;
	}
}
