package net.snake.gamemodel.team.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 被任命玩家是否同意加入队伍消息处理 消息10205
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10205, accessLimit = 500)
public class AppointLeaderIsAgreeProcessor extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		byte isgread = request.getByte();
		int oldId = request.getInt();
		character.getMyTeamManager().changeNewLeader(oldId, isgread);
	}

}
