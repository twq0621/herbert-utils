package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.api.IUseItemListener;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.lianti.bean.Lianti;

public class CharacterUseLiantiFood implements UseGoodAction {
	public CharacterUseLiantiFood(Goodmodel gm) {
	}

	@Override
	public boolean useGoodDoSomething(Hero character, int goodId, int positon,List<IUseItemListener> listeners) {
		Lianti lianti = character.getLiantiController().getLianTiData();
		if (lianti.getFoodGoodsid() != goodId) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60020));// 很抱歉，您当前肉身境界下无法使用该食材增加属性
			return false;
		}
		if (character.getLiantiController().isMaxProperties()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60021));// 当前肉身境界的各项属性值已满，请提升肉身境界。
			return false;
		}
		if (lianti.getFoodGoodsid() == goodId) {
			boolean isok = character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) positon, 1);
			if (isok) {
				character.getLiantiController().promoteProperties();
				return true;
			}
		}
		return false;
	}

}
