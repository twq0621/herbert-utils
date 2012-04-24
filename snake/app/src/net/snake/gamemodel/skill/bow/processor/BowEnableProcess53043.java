package net.snake.gamemodel.skill.bow.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bow.persistence.BowEntryManager;
import net.snake.netio.ServerResponse;
import net.snake.netio.message.RequestMsg;

/**
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 53043, accessLimit = 100)
public class BowEnableProcess53043 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		boolean tag = request.getByte() == 1;
		if (character.getBowController().getBow() != null) {
			character.getBowController().getBow().setEnable(tag);
			BowEntryManager.getInstance().syncUpdateBow(character.getBowController().getBow());
			character.sendMsg(new BowEnableResult53044(character));
			return;
		}
	}
}

class BowEnableResult53044 extends ServerResponse {
	public BowEnableResult53044(Hero character) {
		setMsgCode(53044);
		writeBoolean(character.getBowController().getBow().getEnable());
	}
}
