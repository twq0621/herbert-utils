package net.snake.gamemodel.team.processor.fight;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 关闭阵法 消息10257
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10257, accessLimit = 500)
public class CloseTeamFightingProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		character.getMyZhenfaManager().closeTeamfighting();

	}

}
