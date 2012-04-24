package net.snake.gamemodel.chat.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.chat.response.BackAdmin902;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.INotNeedAuthProcessor;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 901 禁言
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 901)
public class BackAdminProcessor extends MsgProcessor implements INotNeedAuthProcessor, IThreadProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		int id = request.getInt();
		String name = request.getString();
		byte type = request.getByte();
		long time = request.getLong();
		Hero chatRole = GameServer.vlineServerManager.getCharacterById(id);
		if (chatRole != null) {
			chatRole.getChatManager().setSpeakTime(time);
			chatRole.getChatManager().setIschat(type);
		}

		Hero namechatRole = GameServer.vlineServerManager.getCharacterByName(name);
		if (namechatRole != null) {
			namechatRole.getChatManager().setSpeakTime(time);
			namechatRole.getChatManager().setIschat(type);
		}
		if (null == namechatRole && null == chatRole) {
			session.sendMsg(new BackAdmin902(0));
			return;
		}
		session.sendMsg(new BackAdmin902(1));
	}
}
