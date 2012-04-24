/**
 * 
 */
package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.api.IUseItemListener;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;

/**
 * 角色使用商城打折卡 生成角色打折商城物品 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */

public class CharacterUseShopDazheGood implements UseGoodAction {

	public CharacterUseShopDazheGood(Goodmodel gm) {

	}

	@Override
	public boolean useGoodDoSomething(Hero character, int goodId, int positon, List<IUseItemListener> listeners) {
		CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion((short) positon);
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			return false;
		}
		return character.getMyShopManger().useLinShiDazheKa(cg);
	}

}
