package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.api.IUseItemListener;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.catchpet.CharacterHorseController;
import net.snake.gamemodel.pet.logic.Horse;

/**
 * 使用马物品进行获取马的操作
 * 
 * @author serv_dev
 * 
 */
public class CharacterUseJinjieChuanshuoHorseGood implements UseGoodAction {

	public CharacterUseJinjieChuanshuoHorseGood(Goodmodel gm) {
	}

	@Override
	public boolean useGoodDoSomething(Hero character, int goodId, int positon,List<IUseItemListener> listeners) {
		CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion((short) positon);
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			return false;
		}
		CharacterHorseController chc = character.getCharacterHorseController();
		Horse horse = chc.getCurrentRideHorse();
		if (horse == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 13603));
			return false;
		}
		HorseModel hm = horse.getHorseModel();
		if (hm.getKind() < 10) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 13602));
			return false;
		}

		boolean b = character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) positon, 1);
		if (!b) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 13600));
			return false;
		}

		return b;
	}
}
