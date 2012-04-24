package net.snake.gamemodel.goods.logic.container;

import org.apache.log4j.Logger;

import net.snake.consts.BindChangeType;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.Popsinger;
import net.snake.consts.Position;
import net.snake.consts.TakeMethod;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodsDataEntryManager;
import net.snake.gamemodel.goods.response.BodyGoodsUpdata10180;
import net.snake.gamemodel.goods.response.GoodsDelete10178;
import net.snake.gamemodel.map.response.hero.AvatarChange60000;

public class BodyGoodsContiner extends AbstractContainer implements IBody {
	private static final Logger logger = Logger.getLogger(BodyGoodsContiner.class);

	public BodyGoodsContiner() {
		beginPosition = Position.BodyGoodsBeginPostion;
		capacity = 13;
	}

	public void initEquipPropertiesToCharacter() {
		try {
			for (CharacterGoods goods : getGoodsList()) {
				owner.getEquipmentController().changeProperty(owner, goods, TakeMethod.on);
			}
			owner.getMyCharacterAchieveCountManger().getEquipmentCount().takeOnOfferEquipment();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public boolean checksuit(CharacterGoods fromGoods) {
		return checksuit(fromGoods, true);
	}

	public boolean checksuit(CharacterGoods fromGoods, boolean sendsuitmsg) {
		// 条件包含 佩戴位置正确;道具过期;等级门派限制;潜能加点限制;
		Goodmodel goodmodel = fromGoods.getGoodModel();
		int topostion = goodmodel.getPosition();

		if (topostion < Position.BodyGoodsBeginPostion || topostion > Position.BodyGoodsEndPostion) {
			if (sendsuitmsg) {
				owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 756));
			}
			return false;
		}
		if (goodmodel.getPopsinger() != 0 && goodmodel.getPopsinger() != owner.getPopsinger()) {
			if (sendsuitmsg) {
				owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1006, Popsinger.getPopsingerName(goodmodel.getPopsinger())));
			}
			return false;
		}
		if (goodmodel.getLimitGrade() > owner.getGrade()) {
			if (sendsuitmsg) {
				owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1007, goodmodel.getLimitGrade() + ""));
			}

			return false;
		}
		if (goodmodel.getAttackAddpoint() > owner.getAttackAddpoint()) {
			if (sendsuitmsg) {
				//
				owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1008, goodmodel.getAttackAddpoint() + ""));
			}
			return false;
		}
		if (goodmodel.getDefenceAddpoint() > owner.getDefenceAddpoint()) {
			if (sendsuitmsg) {
				//
				owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1009, goodmodel.getDefenceAddpoint() + ""));
			}
			return false;
		}
		if (goodmodel.getStrongAddpoint() > owner.getStrongAddpoint()) {
			if (sendsuitmsg) {
				//
				owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1010, goodmodel.getDefenceAddpoint() + ""));
			}
			return false;
		}
		if (goodmodel.getLightAddpoint() > owner.getLightAddpoint()) {
			if (sendsuitmsg) {
				//
				owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1011, goodmodel.getDefenceAddpoint() + ""));
			}
			return false;
		}
		return true;
	}

	@Override
	protected void onChangePositionGoodsAdd(CharacterGoods goods) {
		if (goods.getGoodModel().isShowInAvator()) {
			owner.getEyeShotManager().sendMsg(new AvatarChange60000(owner, goods.getGoodModel(), true));
		}
		owner.getMyCharacterAchieveCountManger().getEquipmentCount().takeOnOfferEquipment();
		if ((goods.getPingzhiColor() == 4 && goods.getBind() == 0) || (goods.getBind() == 0 && goods.getGoodModel().getBinding() == BindChangeType.DRESSBIND)) {
			owner.sendMsg(new GoodsDelete10178(goods.getPosition(), 0));
			goods.setBind(CommonUseNumber.byte1);
			GoodsDataEntryManager.getInstance().asynUpdataCharacterGoods(owner, goods);
			owner.sendMsg(new BodyGoodsUpdata10180(goods));
		}
		owner.getEquipmentController().changeProperty(owner, goods, TakeMethod.on);

	}

	@Override
	protected void onChangePostionGoodsRemove(CharacterGoods goods) {
		if (goods.getGoodModel().isShowInAvator()) {
			owner.getEyeShotManager().sendMsg(new AvatarChange60000(owner, goods.getGoodModel(), false));
		}
		owner.getMyCharacterAchieveCountManger().getEquipmentCount().takeOnOfferEquipment();
		owner.getEquipmentController().changeProperty(owner, goods, TakeMethod.off);

	}

	@Override
	protected void beforeGetGoods(CharacterGoods fromGoods) {
		fromGoods.setInHorseId(0);
	}

	@Override
	public short getSuitPostionForGoods(CharacterGoods fromGoods, IContainer fromContainer) {
		return fromGoods.getGoodModel().getPosition();
	}

	@Override
	protected void onGoodsAdd(CharacterGoods goods) {
	}

	@Override
	protected void onGoodsRemove(CharacterGoods goods) {
	}

}
