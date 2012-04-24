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
 * 10825 B-S: B是否接受交易申请 是否接受:1/0(byte),A的ID:playerId(int)
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10825, accessLimit = 200)
public class TradeIsAccessProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		Byte type = request.getByte();
		int hisId = request.getInt();
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		if (hisId == character.getId()) {
			return;
		}
		Hero hisCharacter = CharacterManager.getInstance().getCache(hisId);
		if (type == 0) {
			if (hisCharacter == null) {
				return;
			}
			if (hisCharacter.getMyTradeController().isTrading()) {
				return;
			}
			hisCharacter.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1035, character.getViewName() + ""));
			character.getMyTradeController().removeInviteTime(hisId);
		}
		if (type == 1) {
			character.getMyTradeController().createTrading(hisCharacter);
		}
	}

}
