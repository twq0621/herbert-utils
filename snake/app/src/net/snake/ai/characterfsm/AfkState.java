package net.snake.ai.characterfsm;

import net.snake.ai.fsm.IState;
import net.snake.gamemodel.hero.bean.Hero;

import org.apache.log4j.Logger;


/**
 * 角色状态
 * @author serv_dev
 *
 */
public class AfkState implements IState {
	
	
	protected static final Logger LOGGER = Logger
	.getLogger(AfkState.class);
	protected CharacterFSM fsm;
	protected Hero character;

	public AfkState(CharacterFSM fsm, Hero character) {
		this.fsm = fsm;
		this.character = character;

	}
	
	@Override
	public void onBegin() {

	}

	@Override
	public void onUpdate(long now) {

	}

	@Override
	public void onExit() {

	}

	@Override
	public void reset() {

	}

}
