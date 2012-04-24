package net.snake.gamemodel.common.processor;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.Language;
import net.snake.gamemodel.common.response.ServerTimeMsg50002;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 50001
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 50001, accessLimit = 500)
public class ServerTimeProcess extends MsgProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy" + Language.YEAR + "MM" + Language.MONTH + "dd" + Language.DAY + "HH" + Language.HOUR + "mm" + Language.MINUTE + "ss"
				+ Language.SECOND);
		session.sendMsg(new ServerTimeMsg50002(format.format(date)));
	}

}
