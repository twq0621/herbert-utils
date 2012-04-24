package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.api.IUseItemListener;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.logic.TeamFightingController;

/**
 * 使用阵法物品获取阵法
 * 
 * @author serv_dev
 * 
 */
public class CharacterUseZhenfaGood implements UseGoodAction {
	private TeamFightingController tfc;

	public CharacterUseZhenfaGood(TeamFightingController tfc) {
		this.tfc = tfc;
	}

	/**
	 * 阵法学习
	 */
	public boolean useGoodDoSomething(Hero character, int goodId, int position, List<IUseItemListener> listeners) {
		CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion((short) position);
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			return false;
		}
		return character.getMyZhenfaManager().learnZhenfa(tfc, goodId, position);

	}
}
