package net.snake.gamemodel.chat.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.chat.logic.ChatManager;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 30151 请求喇叭聊天
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 30151)
public class ChatLabaSpeakProcessor extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		String content = request.getString();
		character.getChatManager().sendChatMsg(content, ChatManager.MSG_CHAT_LABA);
		// character.getChatLabaSpeakManager().sendWorldMsg(content);
	}

}
