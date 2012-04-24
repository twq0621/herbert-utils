package net.snake.gamemodel.trade.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.badwords.persistence.BadWordsFilter;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.trade.response.Stallnamechange13016;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 13015, accessLimit = 1000)
public class ChangeStallNameProcessor extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		String name = request.getString();
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		// 判断摊位名字是否合法
		boolean hasBadWords = BadWordsFilter.getInstance().hashBadWords(name);
		if (hasBadWords) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60063));
			return;
		}
		character.setStallName(name);
		character.sendMsg(new Stallnamechange13016(name));

	}

}
