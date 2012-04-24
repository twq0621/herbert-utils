package net.snake.gamemodel.wedding.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.persistence.WedFeast;
import net.snake.gamemodel.wedding.persistence.WedFeastManager;
import net.snake.gamemodel.wedding.response.wedfeast.WedFeastReceiveResponse52260;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 52259, accessLimit = 100)
public class WedFeastReceiveProcess52259 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		// int feastid = request.getInt();
		WedFeast feast = WedFeastManager.getInstance().getFeastByRoleid(character.getId());
		if (feast != null && feast.receive(character)) {
			character.sendMsg(new WedFeastReceiveResponse52260(0));
			return;
		}
		character.sendMsg(new WedFeastReceiveResponse52260(1));
	}

}
