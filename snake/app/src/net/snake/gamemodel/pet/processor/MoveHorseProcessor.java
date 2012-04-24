package net.snake.gamemodel.pet.processor;

import org.apache.log4j.Logger;

import net.snake.consts.HorseContainerType;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.catchpet.CharacterHorseController;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.logic.HorseContainer;
import net.snake.netio.message.RequestMsg;


/**
 * 
 * @author serv_dev
 */
public class MoveHorseProcessor extends CharacterMsgProcessor {
	private static Logger logger = Logger.getLogger(MoveHorseProcessor.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {

		int horseid = request.getInt();
		int target = request.getByte();
		CharacterHorseController controller = character.getCharacterHorseController();
		Horse horse = controller.getHorseById(horseid);
		if (horse == null) {
			// 已经在显示中了不
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 889));
			return;
		}

		HorseContainer srccontainer = controller.getHorseContainer(horse.getCharacterHorse().getLocation());

		HorseContainer destcontainer = controller.getHorseContainer(HorseContainerType.getByValue(target));
		if (destcontainer == null) {
			logger.warn("can not find horse container");
			return;
		}
		if (destcontainer.getLeaveSpace() < 1) {
			if (destcontainer.getContainerType() == HorseContainerType.onBag) {
				//
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1026, destcontainer.getContainerType().getName() + ""));
			} else {
				//
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1027, destcontainer.getContainerType().getName()));
			}
			return;
		}
		if (srccontainer.getContainerType().equals(HorseContainerType.onBag)) {
			if (horse.getCharacterHorse().getStatus() == 1) {
				//
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1028, destcontainer.getContainerType().getName()));
				return;
			}
			if (horse.getCharacterHorse().getStatus() == 2) {
				//
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1029, destcontainer.getContainerType().getName()));
				return;
			}
		}
		if (horse.isInTrade()) {
			//
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1030, destcontainer.getContainerType().getName()));
			return;
		}
		srccontainer.moveToContainer(destcontainer, horse);
	}
}
