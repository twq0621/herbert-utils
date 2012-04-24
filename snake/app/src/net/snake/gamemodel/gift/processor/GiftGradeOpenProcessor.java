package net.snake.gamemodel.gift.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 50703 请求领取升级礼包
 * 
 * 
 */
@MsgCodeAnn(msgcode = 50703, accessLimit = 500)
public class GiftGradeOpenProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int goodId = request.getInt();
		short positon = request.getShort();
		CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion(positon);
		if (cg == null || cg.getGoodmodelId()!=goodId) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 863));
			return;
		}
		if (!cg.getGoodModel().isGiftBag()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 864));
			return;
		}
		character.getMyCharacterGiftpacksManger().openGoodGiftPacks(cg);

	}

}
