package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.api.IUseItemListener;
import net.snake.consts.Position;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.logic.EquipmentController;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.hero.bean.Hero;

/**
 * 打磨石 用于修复全身的装备耐久
 * 
 * @author jack
 * 
 */
public class CharacterRepairEquipmentGood implements UseGoodAction {
	public CharacterRepairEquipmentGood(Goodmodel gm) {
	}

	@Override
	public boolean useGoodDoSomething(Hero character, int goodId, int positon, List<IUseItemListener> listeners) {
		CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion((short) positon);
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			return false;
		}
		CharacterGoodController characterGoodController = character.getCharacterGoodController();
		boolean repair = false;
		for (CharacterGoods equipment : characterGoodController.getBodyGoodsList()) {// 有没有需要维修的装备
			if (equipment.getPosition() == Position.POSTION_TESHU) {
				continue;
			}
			if (equipment.getCurrDurability() < equipment.getMaxDurability()) {
				repair = true;
				break;
			}
		}
		if (repair) {// 修理
			EquipmentController equipmentController = character.getEquipmentController();
			equipmentController.repairBodyEquips();
			character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) positon, 1);
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 790));
			return true;
		} else {// 不修理
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 791));
			return false;
		}
	}

}
