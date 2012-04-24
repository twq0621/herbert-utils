package net.snake.gamemodel.faction.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

 /**
  * 邀请某人加入帮会
  *
  */
@MsgCodeAnn(msgcode = 51045)
public class FactionIniviteResultProcessor51045 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		int bangzhuOrFuId=request.getInt();
		byte type=request.getByte();
		character.getMyFactionManager().inviterAccessBazhuInviteFaction(bangzhuOrFuId, type);
	}

}
