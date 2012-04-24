package net.snake.gamemodel.chat.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.gm.processor.GMCommand;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 私聊消息 30105
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 30105, accessLimit = 200)
public class ChatPrivateProcessor extends MsgProcessor implements IThreadProcessor {
	GMCommand gm = new GMCommand();

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero chatRole = session.getCurrentCharacter(Hero.class);
		GamePlayer chatSession = chatRole.getChatManager().getChatSession();
		if (!(session==chatSession)) {
			gm.process(session, request);
			return;
		}
		String name = request.getString();
		String content = request.getString();
		chatRole.getChatManager().sendPrivateMsg(name, content);
	}

}
