package net.snake.gamemodel.friend.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 添加好友请求 消息 10303
 * 
 * 
 */
@MsgCodeAnn(msgcode = 10303, accessLimit = 500)
public class FriendAddProcess extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int fId = request.getInt();
		character.getMyFriendManager().getRoleFriendManager().addFriend(fId);

	}

}
