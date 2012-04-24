package net.snake.gamemodel.faction.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 设置自动同意任意玩家向本帮发出的入帮申请
 *
 */
@MsgCodeAnn(msgcode = 51065)
public class SystemAccessInviteProcssor51065 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		byte type=request.getByte();
		character.getMyFactionManager().systemAccessInviteFaction(type);
	}

}
