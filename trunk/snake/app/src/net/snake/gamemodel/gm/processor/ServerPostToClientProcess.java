package net.snake.gamemodel.gm.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.gm.response.MsgToBackAdmin904;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.INotNeedAuthProcessor;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;
import net.snake.serverenv.vline.CharacterRun;

/**
 * 消息 205 公告给所有在线玩家
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 205)
public class ServerPostToClientProcess extends MsgProcessor implements INotNeedAuthProcessor, IThreadProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		final String msg = request.getString();
		if (msg.equals("")) {
			return;
		}
		session.sendMsg(new MsgToBackAdmin904(CommonUseNumber.byte1));
		GameServer.vlineServerManager.runToOnlineCharacter(new CharacterRun() {
			@Override
			public void run(Hero character) {
				character.sendMsg(new TipMsg(TipMsg.MSGPOSITION_SYSBROADCAST, msg));
			}
		});
	}

}
