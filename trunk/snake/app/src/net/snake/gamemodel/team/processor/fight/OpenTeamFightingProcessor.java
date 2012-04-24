package net.snake.gamemodel.team.processor.fight;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 开启阵法请求 消息号是10255
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10255, accessLimit = 500)
public class OpenTeamFightingProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		short zhenfaId = request.getShort();
		character.getMyZhenfaManager().openTeamfighting(zhenfaId);

	}

}
