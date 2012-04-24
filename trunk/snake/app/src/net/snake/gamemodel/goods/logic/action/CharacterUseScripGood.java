package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.api.IUseItemListener;
import net.snake.commons.script.IEventListener;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;

/**
 * 使用buffer 产生物品 对角色产生临时buffer 效果
 * 
 * @author serv_dev
 * 
 */
public class CharacterUseScripGood implements UseGoodAction {
	public Goodmodel gm;

	public CharacterUseScripGood(Goodmodel gm) {
		this.gm = gm;
	}

	@Override
	public boolean useGoodDoSomething(Hero character, int goodId, int positon, List<IUseItemListener> listeners) {
		CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion((short) positon);
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			return false;
		}
		// ScriptEventCenter.getInstance().onRoleUseGoods(null, character, cg);
		AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_UseGoods, new Object[] { character, cg });
		return true;
	}

}
