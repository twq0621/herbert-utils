package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.ai.formula.CharacterFormula;
import net.snake.api.IUseItemListener;
import net.snake.gamemodel.activities.persistence.LinshiActivityManager;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;

/**
 * 洗点
 * @author jack
 *
 */
public class CharacterXiDianGood implements UseGoodAction {

	public CharacterXiDianGood(Goodmodel gm) {
	}

	@Override
	public boolean useGoodDoSomething(Hero character, int goodId, int positon, List<IUseItemListener> listeners) {

		boolean inActivity = LinshiActivityManager.getInstance().isTimeByLinshiActivityID(51);
		if (!inActivity) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1085));
			return false;
		}
		CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion((short) positon);
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			return false;
		}

		if (CharacterFormula.xiAllDian(character)) {
			character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) positon, 1);
		}
		return true;
	}

}
