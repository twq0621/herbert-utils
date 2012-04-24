package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.api.IUseItemListener;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.gift.response.GiftPacksGradeMsg50702;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;


/**
 * 升级礼包打开处理
 * 
 * @author serv_dev
 * 
 */
public class CharacterUseGiftPacksGood implements UseGoodAction {
	private GiftPacks gp;
	public CharacterUseGiftPacksGood(GiftPacks gp) {
		this.gp = gp;
	}

	@Override
	public boolean useGoodDoSomething(Hero character, int goodId,
			int positon,List<IUseItemListener> listeners) {
		CharacterGoods cg = character.getCharacterGoodController()
				.getBagGoodsContiner().getGoodsByPostion((short) positon);
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			return false;
		}
		if (gp.getType() == 0) {
			character.sendMsg(new GiftPacksGradeMsg50702(gp, goodId, positon,
					character));
		} else {
			character.getMyCharacterGiftpacksManger().openGoodGiftPacks(cg);
		}
		return false;
	}

}
