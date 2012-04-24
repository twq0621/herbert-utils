package net.snake.gamemodel.goods.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.action.UseGoodAction;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 
 * 使用道具 调整为11201
 * 
 * @author serv_dev
 * 
 */

@MsgCodeAnn(msgcode = 11201)
public class GoodsUseProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int goodId = request.getInt();
		short positon = request.getShort();
		CharacterGoods cg = character.getCharacterGoodController().getGoodsByPositon((short) positon);
		if (cg == null || cg.getGoodmodelId() != goodId) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 822));
			return;
		}
		if (cg.getLastDate() != null) {
			if (System.currentTimeMillis() > cg.getLastDate().getTime()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 877));
				return;
			}
		}
		UseGoodAction uga = cg.getUseGoodAction();
		if (uga == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 878));
			return;
		}
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 879));
		}
		boolean tf = uga.useGoodDoSomething(character, goodId, positon, character.getSceneRef().getUseItemListeners());
		if (tf && cg.getBuyType() == 1) {
			// 发送日志
			long time = 0;
			if (cg.getLastDate() != null) {
				time = cg.getLastDate().getTime() / 1000;
			}
			GameServer.dataLogManager.logRMBUseItem("1", goodId, cg.getBuyPrice(), time, 1, character.getLogUid(), character.getHeroInfo());
		}
	}
}
