package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.api.IUseItemListener;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.dujie.persistence.DujieDataTbl;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 
 * 1-13对应各界使用 0是无限制，以最近一次渡劫优先使用
 * 
 */
public class CharacterUseRoushenGood implements UseGoodAction {
	private Goodmodel gm;

	public CharacterUseRoushenGood(Goodmodel gm) {
		this.gm = gm;
	}

	@Override
	public boolean useGoodDoSomething(Hero character, int goodId, int position, List<IUseItemListener> listeners) {
		CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion((short) position);
		if (cg == null) {
			return false;
		}
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			return false;
		}
		if (cg.getLastDate() != null) {
			if (System.currentTimeMillis() > cg.getLastDate().getTime()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 877));
				return false;
			}
		}
		byte type = gm.getDrugType().byteValue();
		int value = gm.getDrugValue();
		int num = character.getDujieCtrlor().currentTianjieNum();
		if (type != 0 && num != type) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60020));
			return false;
		}
		int max = DujieDataTbl.getInstance().getDujie(num).getMax_point();
		if(character.getDujieCtrlor().getHeroDujieData().getRoushen()>=max){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60021));
			return false;
		}
		if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
			ServerResponse response = character.getDujieCtrlor().increaseRoushen(value);
			if (response != null) {
				character.sendMsg(response);
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60057, value));
			}
			return true;
		}

		return false;
	}
}
