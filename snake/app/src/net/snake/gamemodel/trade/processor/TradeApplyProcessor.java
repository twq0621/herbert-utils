package net.snake.gamemodel.trade.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 修改为10821 A-S: 申请交易 B的ID:角色(int)
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10821, accessLimit = 200)
public class TradeApplyProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int characterId = request.getInt();
		if (characterId == character.getId()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1305));
			return;
		}
		Hero hisCharacter = CharacterManager.getInstance().getCache(characterId);
		character.getMyTradeController().requestTradeWith(hisCharacter);

	}

}
