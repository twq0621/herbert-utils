package net.snake.ai.horsefsm.state;

import net.snake.GameServer;
import net.snake.ai.horsefsm.HorseBasicState;
import net.snake.ai.horsefsm.HorseFsm;
import net.snake.commons.program.TimeAccumulate;
import net.snake.consts.HorseStateConsts;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.onhoor.logic.CharacterOnHoorController;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.response.HorseChangeStatusResponse;

public class ShowState extends HorseBasicState {

	private TimeAccumulate timeAccumulate;

	public ShowState(Horse horse, HorseFsm horseFsm) {
		super(horse, horseFsm);
		timeAccumulate = new TimeAccumulate(GameServer.configParamManger.getConfigParam().getHorseShowConsume() * 1000);
	}

	public void onExit() {
		horse.getCharacter().getCharacterHorseController().setCurrentShowHorse(null);
		horse.getAotoAttackController().stopAttack();
		horse.removeEquipProperties();
		Hero character = horse.getCharacter();
		horse.getCharacter().getPropertyAdditionController().removeChangeListener(horse);
		horse.removeEquipProperties();
		horse.getHorseskillManager().onUnRide();
		character.getEyeShotManager().sendMsg(new HorseChangeStatusResponse(character.getId(), horse.getHorseModel().getId(), horse.getId(), HorseStateConsts.REST));
	}

	@Override
	public void onBegin() {
		timeAccumulate.setStartTime(System.currentTimeMillis());
		Hero character = horse.getCharacter();
		character.getCharacterHorseController().setCurrentShowHorse(horse);
		horse.getCharacter().getPropertyAdditionController().addChangeListener(horse);
		horse.addEquipPropertis();
		horse.getHorseskillManager().onRide();
		character.getEyeShotManager().sendMsg(new HorseChangeStatusResponse(character.getId(), horse.getHorseModel().getId(), horse.getId(), HorseStateConsts.SHOW));

	}

	@Override
	public void onUpdate(long now) {
		if (timeAccumulate.isAccumulateTimeOK(now)) {
			// 活力减一，该方法会切换坐骑状态
			horse.addLivingness(-1);
			CharacterOnHoorController characterOnHoorController = horse.getCharacter().getCharacterOnHoorController();
			if (characterOnHoorController.isAutoOnHoor() && characterOnHoorController.getCharacterOnHoorConfig().getHorseAutoLivingness()) {
				if (characterHorse.getLivingness() < characterOnHoorController.getCharacterOnHoorConfig().getHorseLivingness()) {
					horse.getHorseRaiseManager().upLivingness();
				}
			}
		}
		if (characterHorse.getLivingness() == 0) {
			horsefsm.changeStatus(HorseStateConsts.REST);
			horse.getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 506));
			return;
		}
		VisibleObject attackobj = horse.getTarget();
		if (attackobj == null || attackobj.isZeroHp()) {// 角色没有攻击目标了
			horse.getAotoAttackController().stopAttack();
			return;
		}
	}
}
