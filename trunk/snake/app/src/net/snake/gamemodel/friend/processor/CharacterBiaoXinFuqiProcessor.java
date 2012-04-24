package net.snake.gamemodel.friend.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 10365 玩家请求夫妻 以及自己心情表情信息
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10365, accessLimit = 500)
public class CharacterBiaoXinFuqiProcessor extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		character.getMyFriendManager().sendMyInfoAndFuqi();

	}

}
