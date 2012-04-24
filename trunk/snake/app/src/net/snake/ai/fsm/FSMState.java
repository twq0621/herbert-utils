package net.snake.ai.fsm;

import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.monster.bean.SceneMonster;
import org.apache.log4j.Logger;


/**
 * 
 * @author serv_dev
 *
 */
public class FSMState implements IState {
	protected static final Logger LOGGER = Logger
	.getLogger(FSMState.class);
	protected MonsterFSM fsm;
	protected SceneMonster sceneMonster;

	public FSMState(MonsterFSM fsm, SceneMonster sceneMonster) {
		this.fsm = fsm;
		this.sceneMonster = sceneMonster;

	}
	@Override
	public void onBegin() {

	}
	@Override
	public void onExit() {

	}
	@Override
	public void onUpdate(long now) {
	}
	@Override
	public void reset() {

	}
	//用于NPC(怪物)好友
	public void onOwnerAttack(VisibleObject obj) {
		
	}
}
