package net.snake.gamemodel.friend.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 请求删除某玩家在仇恨列表中 10343
 * 
 * 
 */
@MsgCodeAnn(msgcode = 10343, accessLimit = 500)
public class EnemtyDeleteProcess extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int enemyId = request.getInt();
		character.getMyFriendManager().getRoleEnemyManager().deleteEmeny(enemyId);
	}

}
