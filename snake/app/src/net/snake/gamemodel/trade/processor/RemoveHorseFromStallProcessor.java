package net.snake.gamemodel.trade.processor;

import org.apache.log4j.Logger;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.catchpet.CharacterHorseController;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.logic.HorseContainer;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;


@MsgCodeAnn(msgcode = 13019, accessLimit = 100)
public class RemoveHorseFromStallProcessor extends CharacterMsgProcessor implements IThreadProcessor {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(RemoveHorseFromStallProcessor.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int horseid = request.getInt();

		CharacterHorseController controller = character.getCharacterHorseController();
		HorseContainer container = controller.getStallHorseContainer();
		Horse horse = container.getHorseByID(horseid);
		if (horse == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 963));
			return;
		}
		HorseContainer bagcontainer = controller.getBagHorseContainer();
		if (bagcontainer.getLeaveSpace() < 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 964));
			return;
		}
		container.moveToContainer(bagcontainer, horse);
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 965));

	}
}
