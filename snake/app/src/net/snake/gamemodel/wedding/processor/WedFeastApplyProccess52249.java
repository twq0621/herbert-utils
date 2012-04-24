package net.snake.gamemodel.wedding.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.persistence.WedFeastManager;
import net.snake.gamemodel.wedding.response.wedfeast.WedFeastApplyResponse52250;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 52249, accessLimit = 100)
public class WedFeastApplyProccess52249 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		byte type = request.getByte();
		boolean applyFeast = WedFeastManager.getInstance().applyFeast(type, character);
		if (applyFeast) {
			character.sendMsg(new WedFeastApplyResponse52250(0));
		} else {
			character.sendMsg(new WedFeastApplyResponse52250(1));
		}

	}

}
