package net.snake.gamemodel.goods.logic.container;

import net.snake.consts.Position;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;

public class AcrossGoodsContainer extends StorageGoodsContainer {
	public AcrossGoodsContainer() {
		beginPosition = Position.AcrossBagBeginPostion;
		capacity = 30;
	}

	public boolean checksuit(CharacterGoods goods) {
		Goodmodel goodModel = goods.getGoodModel();
		if (goodModel.isAcross()) {// 是否可跨服
			if (goodModel.isArrow()) {// 是否箭支
				if (!owner.getBowController().hasActivate(goodModel.getSkill())) {
					// 技能未激活
					owner.sendMsg(new PrompMsg(50072));
					return false;
				}
				if (owner.getBowController().acrossHasArrow(goodModel.getSkill())) {
					// 已经存在同类型的箭支。请先将同类型的箭支拖下再行放入
					owner.sendMsg(new PrompMsg(50071));
					return false;
				}
			}
			// 可以加入其它判断
			return true;

		} else {
			owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60037));
			return false;
		}
	}

}
