package net.snake.gamemodel.common.processor;

import net.snake.commons.message.SimpleResponse;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.INotNeedAuthProcessor;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

public class PingProcessor extends MsgProcessor implements INotNeedAuthProcessor, IThreadProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		session.sendMsg(SimpleResponse.onlyMsgCodeMsg(9998));
	}
}
