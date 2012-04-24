package net.snake.gamemodel.goods.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 拆分物品
 * 
 * @author dev 10803 物品索引 拆分的数量
 * 
 */
@MsgCodeAnn(msgcode = 11183, accessLimit = 500)
public class SplitGoodsProcessor extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		short position = request.getShort();// 物品索引
		int num = request.getInt();// 拆分出的数量
		character.getCharacterGoodController().splitGoods(position, num);

	}

}
