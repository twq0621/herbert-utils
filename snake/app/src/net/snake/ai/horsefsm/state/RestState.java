package net.snake.ai.horsefsm.state;

import net.snake.GameServer;
import net.snake.ai.horsefsm.HorseBasicState;
import net.snake.ai.horsefsm.HorseFsm;
import net.snake.commons.program.TimeAccumulate;
import net.snake.gamemodel.pet.logic.Horse;

public class RestState extends HorseBasicState {
	TimeAccumulate timeAccumulate;

	public RestState(Horse horse, HorseFsm horsefsm) {
		super(horse, horsefsm);
		timeAccumulate = new TimeAccumulate(GameServer.configParamManger.getConfigParam().getHorseRestResume() * 1000);
	}

	@Override
	public void onBegin() {
		timeAccumulate.setStartTime(System.currentTimeMillis());
		horse.getAotoAttackController().stopAttack();
		horse.getPursuitPointManager().clearArroundWithMeInFightMonsterPositions();
	}

	@Override
	public void onUpdate(long now) {
		if (timeAccumulate.isAccumulateTimeOK(now)) {
			// TODO 每3分钟增加一点活力
			if (characterHorse.getLivingness().intValue() != characterHorse.getLivingnessMax().intValue()) {
				horse.addLivingness(1);
			}
		}
	}
}
