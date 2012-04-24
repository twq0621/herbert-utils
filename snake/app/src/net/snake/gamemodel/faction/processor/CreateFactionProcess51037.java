package net.snake.gamemodel.faction.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 51037)
public class CreateFactionProcess51037 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		String factionName = request.getString();
		String factionNotice = request.getString();
		String bangqiName = request.getString();
		Byte icoId = request.getByte();
		String icoStr = request.getString();
		character.getMyFactionManager().createFaction(factionName, factionNotice, bangqiName, icoId, icoStr);
	}

}
