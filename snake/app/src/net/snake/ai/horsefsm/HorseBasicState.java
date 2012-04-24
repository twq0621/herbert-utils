package net.snake.ai.horsefsm;

import net.snake.ai.fsm.IState;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.logic.Horse;


public class HorseBasicState implements IState {

	protected Horse horse;
	protected CharacterHorse characterHorse;
	protected HorseFsm horsefsm;

	public HorseBasicState(Horse horse, HorseFsm horsefsm) {
		this.horse = horse;
		this.characterHorse = horse.getCharacterHorse();
		this.horsefsm = horsefsm;
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

	public void onOwnerAttack(VisibleObject obj) { 
		
	}
}
