package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.api.IUseItemListener;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.logic.HorseCreator;

/**
 * 使用马物品进行获取马的操作
 * 
 * @author serv_dev
 * 
 */
public class CharacterUseHorseGood implements UseGoodAction {
	private HorseModel hm;

	public CharacterUseHorseGood(HorseModel hm) {
		this.hm = hm;
	}

	@Override
	public boolean useGoodDoSomething(Hero character, int goodId, int positon,List<IUseItemListener> listeners) {
		CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion((short) positon);
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			return false;
		}
		boolean b = HorseCreator.createCharacterHorse(character, hm.getId());
		if (b) {
			character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) positon, 1);
		}
		return b;
	}
}
