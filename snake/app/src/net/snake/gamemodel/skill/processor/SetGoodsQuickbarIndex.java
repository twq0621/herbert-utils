package net.snake.gamemodel.skill.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 设置快捷栏
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 10179)
public class SetGoodsQuickbarIndex extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		short goodspostion = request.getShort();
		int index = request.getByte();
		CharacterGoods charactergoods = character.getCharacterGoodController().getGoodsByPositon(goodspostion);
		if (charactergoods == null || (charactergoods.getGoodModel().getKind() != 3 && charactergoods.getGoodModel().getKind() != 29 && charactergoods.getGoodModel().getKind() != 34)) {
			return;
		}
		character.getQuickbarController().setQuickBarGoods(index, charactergoods);
	}
}
