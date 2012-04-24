package net.snake.ai.horsefsm;

import net.snake.ai.horsefsm.state.RestState;
import net.snake.ai.horsefsm.state.ShowState;
import net.snake.commons.program.Updatable;
import net.snake.consts.HorseStateConsts;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.persistence.CharacterHorseDataProvider;

public class HorseFsm implements Updatable {
	// 马的状态机======================================
	private RestState restState;
	// 显示跟随状态
	private ShowState showState;
	// =============================================
	// 马的当前状态引用　
	private HorseBasicState currentState = null;
	private Horse horse;

	public HorseFsm(Horse horse) {
		this.horse = horse;
		restState = new RestState(horse, this);
		// 显示跟随状态
		showState = new ShowState(horse, this);
	}

	public HorseBasicState getCurrentState() {
		return currentState;
	}

	/** 改变马的状态 */
	public void changeStatus(int HORSE_STATUS_CONST) {
		HorseBasicState state = getFSMState(HORSE_STATUS_CONST);
		if (state == null) {
			currentState = null;
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
		// 更新数据库库中的状态
		horse.getCharacterHorse().setStatus(HORSE_STATUS_CONST);
		CharacterHorseDataProvider.getInstance().asynchronousUpdateCharacterHorse(horse.getCharacterHorse(), horse.getCharacter());

	}

	private HorseBasicState getFSMState(int hORSE_STATUS_CONST) {
		switch (hORSE_STATUS_CONST) {
		case HorseStateConsts.REST:
			return restState;
		case HorseStateConsts.SHOW:
			return showState;
		}
		return null;
	}

	@Override
	public void update(long now) {
		currentState.onUpdate(now);
	}

	public void onOwnerAttack(VisibleObject obj) {
		currentState.onOwnerAttack(obj);
	}
}
