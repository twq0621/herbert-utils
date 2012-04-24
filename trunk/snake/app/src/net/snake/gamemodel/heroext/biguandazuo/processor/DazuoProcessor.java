package net.snake.gamemodel.heroext.biguandazuo.processor;

import net.snake.ai.move.Location;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;
import net.snake.netio.message.RequestMsg;

/**
 * 50503 打坐请求
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 50503, accessLimit = 1000)
public class DazuoProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int charStatus = character.getObjectState();
		if (charStatus == VisibleObjectState.Walk && character.getLocation() == Location.Location_KONG) {
			return;
		}
		character.getMyDazuoManager().requestChangeDazuoState();
	}

}
