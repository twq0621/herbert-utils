package net.snake.gamemodel.team.processor.fight;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 请求阵法列表 消息号10251
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10251, accessLimit = 300)
public class TeamFightingListProcessor extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		character.getMyZhenfaManager().sendTeamFightingList();
	}

}
