package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.api.IUseItemListener;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.logic.SystemPublishTask;

public class CharacterUseTaskGood implements UseGoodAction {
	private Task task;

	public CharacterUseTaskGood(Task task) {
		this.task = task;
	}

	@Override
	public boolean useGoodDoSomething(Hero character, int goodId, int positon, List<IUseItemListener> listeners) {
		CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion((short) positon);
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			return false;
		}
		if (character.getCharacterGoodController().getGoodsByPositon((short) positon) == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 822));
			return false;
		}
		boolean b = character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) positon, 1);
		if (!b) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 823));
			return false;
		}
		SystemPublishTask.publishTaskToPlayer(task.getTaskId(), character);
		return true;
	}

}
