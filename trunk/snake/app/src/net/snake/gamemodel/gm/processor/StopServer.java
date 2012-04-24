package net.snake.gamemodel.gm.processor;

import net.snake.commons.message.SimpleResponse;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.INotNeedAuthProcessor;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

public class StopServer extends MsgProcessor implements IThreadProcessor, INotNeedAuthProcessor {
	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		if (session.getIoSession().getRemoteAddress().toString().indexOf("127.0.0.1") != -1) {
			session.sendMsg(SimpleResponse.onlyMsgCodeMsg(0));
			// GameServer.getInstance().shutdown();
		}
	}

}
