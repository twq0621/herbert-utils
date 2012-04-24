package net.snake.gamemodel.equipment.processor.gem;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 宝石合成 支持批量
 */
@MsgCodeAnn(msgcode = 50193,accessLimit=100)
public class GemCombiningProcessor50193 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			return;
		}
		short postion = request.getShort();
		int modelid = request.getInt();
		byte byte1 = request.getByte();
		boolean containOtherBind = byte1 == 1;
		int xingyunNum = request.getInt();
		int gemcount = request.getInt();
		if (gemcount <= 0) {
			gemcount = 1;
		}
		CharacterGoods goods = character.getCombineController().gemCondition(modelid, postion);
		if (goods==null) {
			return;
		}
		character.getCombineController().gemCombining(goods, containOtherBind, xingyunNum, gemcount);
	}

}
