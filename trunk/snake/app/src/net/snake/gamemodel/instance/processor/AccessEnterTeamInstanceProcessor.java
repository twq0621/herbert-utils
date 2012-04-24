package net.snake.gamemodel.instance.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 10705 d队友确认是否进行传送
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10705)
public class AccessEnterTeamInstanceProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// int scene=request.getInt();
		byte tag = request.getByte();
		character.getMyCharacterInstanceManager().onResponseEnterTeamInstance(tag);
	}

}
