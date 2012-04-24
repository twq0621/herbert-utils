package net.snake.gamemodel.friend.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.friend.response.CharacterBiaoXinQingMsg10376;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 10375
 * 
 */
@MsgCodeAnn(msgcode = 10375, accessLimit = 500)
public class CharacterBiaoXinqingInfoProcessor extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int id = request.getInt();
		Hero roleDate = GameServer.vlineServerManager.getCharacterById(id);
		if (roleDate == null) {
			return;
		}
		character.sendMsg(new CharacterBiaoXinQingMsg10376(roleDate));
	}

}
