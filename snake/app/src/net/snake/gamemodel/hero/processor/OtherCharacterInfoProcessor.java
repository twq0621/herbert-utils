package net.snake.gamemodel.hero.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.Popsinger;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.response.CharacterAttributesMsg49992;
import net.snake.gamemodel.hero.response.OtherCharacterInfoResponse20108;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

@MsgCodeAnn(msgcode = 20107, accessLimit = 500)
public class OtherCharacterInfoProcessor extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int id = request.getInt();
		Hero otherCharacter = GameServer.vlineServerManager.getCharacterById(id);
		// 全服查询
		if (otherCharacter != null) {
			character.sendMsg(new OtherCharacterInfoResponse20108(otherCharacter));
			character.sendMsg(new CharacterAttributesMsg49992(otherCharacter));
			if (character.getEyeShotManager().isContains(otherCharacter)) {
				//
				otherCharacter.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1033, Popsinger.getPopsinger(character.getPopsinger()).getAppellation()));
			}

		} else {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 930));
		}

	}
}
