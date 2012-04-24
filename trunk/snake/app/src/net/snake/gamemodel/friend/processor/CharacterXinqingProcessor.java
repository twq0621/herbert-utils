package net.snake.gamemodel.friend.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 10373
 * 
 */
@MsgCodeAnn(msgcode = 10373, accessLimit = 500)
public class CharacterXinqingProcessor extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		String xinqing = request.getString();
		character.getMyFriendManager().changerXinqing(xinqing);

	}

}
