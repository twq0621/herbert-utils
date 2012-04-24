package net.snake.gamemodel.goods.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.goods.response.GoodsOnBag10024;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;



/**
 * 整理玩家包裹物品 1、按照物品的类别 2、同一类别的物品按照物品的等级由低到高依次排列 3、同等级的物品按照物品的品级由低到高依次排列
 * 
 * @author benchild
 * 
 */
@MsgCodeAnn(msgcode = 11185,accessLimit=1000)
public class CleanBagGoodsProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		character.getCharacterGoodController().getBagGoodsContiner().cleanGoods();
		character.sendMsg(new GoodsOnBag10024(character.getCharacterGoodController().getBagGoodsContiner().getGoodsList()));
	}	

}
