package net.snake.gamemodel.heroext.biguandazuo.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 50509, accessLimit = 1000)
public class ShuangXiuInviteProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		byte type = request.getByte();
		int characterId = request.getInt();
		Hero hisCharacter = character.getVlineserver().getOnlineManager().getByID(characterId);
		if (type == 1) {
			character.getMyDazuoManager().requestShuangXiuWith(hisCharacter);
		} else {
			character.getMyDazuoManager().requestLiaoshanWith(hisCharacter);
		}
	}

}
