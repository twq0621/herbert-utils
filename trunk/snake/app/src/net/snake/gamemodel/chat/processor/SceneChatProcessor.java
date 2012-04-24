package net.snake.gamemodel.chat.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.chat.logic.ChatManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 普通频道发言 30111
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 30111, accessLimit = 200)
public class SceneChatProcessor extends MsgProcessor implements IThreadProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		String content = request.getString();
		Hero chatRole = session.getCurrentCharacter(Hero.class);
		chatRole.getChatManager().sendChatMsg(content, ChatManager.MSG_CHAT_SCENE);
	}

}
