package net.snake.gamemodel.achieve.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.achieve.response.OtherAchieveInfoMsg51014;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 
 */

@MsgCodeAnn(msgcode = 51013, accessLimit = 300)
public class OtherAchieveInfoProcessor51013 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int otherId = request.getInt();
		byte kind = request.getByte();
		Hero other = GameServer.vlineServerManager.getCharacterById(otherId);
		if (other == null) {
			return;
		}
		character.sendMsg(new OtherAchieveInfoMsg51014(other, kind));
	}

}
