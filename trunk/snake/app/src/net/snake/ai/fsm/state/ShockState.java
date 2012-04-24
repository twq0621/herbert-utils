package net.snake.ai.fsm.state;

import net.snake.ai.fsm.FSMState;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.gamemodel.hero.bean.ShockImg;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.shell.Options;

public class ShockState extends FSMState {

	private int timeout;
	public ShockState(MonsterFSM fsm, SceneMonster sceneMonster) {
		super(fsm, sceneMonster);
		timeout= Options.Shock_Timeout_Monster *1000;
	}

	@Override
	public void onBegin() {
		sceneMonster.getMoveController().stopMove();
		sceneMonster.getUpdateObjManager().clearEffectFromOther();
		sceneMonster.getEffectController().clearAllEffectListAndRemoveBuffOnBody();
	}		

	@Override
	public void onExit() {
	}

	@Override
	public void onUpdate(long now) {
		
		ShockImg img=sceneMonster.getShockMeImg();
		if (img.shockTimestamp==-1) {
			sceneMonster.setObjectState(VisibleObjectState.Die);
			return ;
		}
		long shockedTime=img.shockTimestamp;
		long diff = now- shockedTime;
		if (diff < timeout) {
			return ;
		}
		sceneMonster.setFallDown(true);
		sceneMonster.setDieTimestamp(0);
		sceneMonster.setObjectState(VisibleObjectState.Die);
	}

	@Override
	public void reset() {
	}
}
