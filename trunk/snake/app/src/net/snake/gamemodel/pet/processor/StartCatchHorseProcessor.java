package net.snake.gamemodel.pet.processor;

import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;
import net.snake.netio.message.RequestMsg;

/**
 * 开始捕获马协议
 * 
 * @author serv_dev
 */
public class StartCatchHorseProcessor extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int scenemonsterid = request.getInt();
		SceneMonster target = character.getSceneRef().getSceneMonster(scenemonsterid);
		if (target == null || target.getMonsterModel().getHorseModelId() == 0) {// 坐骑模型ID不为空
			// 客户端正常情况下,不会执行到这里
			return;
		}
		HorseModel horsemodel = HorseModelDataProvider.getInstance().getHorseModelByID(target.getMonsterModel().getHorseModelId());
		if (horsemodel.getCatchMeGradeLimit() > character.getGrade()) {
			//
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1032, horsemodel.getCatchMeGradeLimit() + ""));
			return;
		}
		// 没空间的情况下不能捕获
		if (character.getCharacterHorseController().getBagHorseContainer().getLeaveSpace() < 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 893));
			return;
		}
		if (target.getMonsterModel().getHorseModelId() == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 894));
			return;
		}
//		character.getRideHorseActionController().breakRideAction();
		character.getCatchHorseActionController().startTime(target);
	}

}
