package net.snake.gamemodel.gm.processor;

import net.snake.GameServer;
import net.snake.gamemodel.gm.response.TellPlayerOtherUserLoginResponse;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

import org.apache.log4j.Logger;

/**
 * 消息来自登陆服务器 踢出服务器上的玩家 消息号 25
 * 
 * @author dev
 */
public class KickPlayerProcessor extends MsgProcessor implements IThreadProcessor {
	private static final Logger logger = Logger.getLogger(KickPlayerProcessor.class);

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		int accountId = request.getInt();// 角色id
		Hero character = GameServer.vlineServerManager.getCharacterByAccountId(accountId);
		if (character != null) {
			// 告诉 被踢下线的玩家 信息
			TellPlayerOtherUserLoginResponse response = new TellPlayerOtherUserLoginResponse(1130);
			character.sendMsg(response);
			character.getPlayer().logout();
		} else {
			logger.warn("accountid: 为null "+ accountId);
		}
	}
}
