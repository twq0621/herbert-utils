package net.snake.gamemodel.goods.logic.container;

import net.snake.consts.BindChangeType;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.HorseStateConsts;
import net.snake.consts.Position;
import net.snake.consts.TakeMethod;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodsDataEntryManager;
import net.snake.gamemodel.goods.response.GoodsDelete10178;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.response.HorseGoodsUpdata10078;
import net.snake.gamemodel.pet.response.HorseInfoResponse60006;

public class HorseBodyGoodsContiner extends AbstractContainer implements IHorseBody {
	protected Horse horse;
	protected int horseid;

	public void setHorseid(int horseid) {
		this.horseid = horseid;
	}

	@Override
	public Horse getHorse() {
		return horse;
	}

	// 用于删除马身上的所有物品
	public void removeAllGoods() {
		for (CharacterGoods goods : goodsMap.values()) {
			// 易主
			goods.setCharacterId(owner.getId());
			GoodsDataEntryManager.getInstance().asynDeleteCharacterGoods(owner, goods.getId());
		}
	}

	// 易主 用于坐骑
	public void changeOwner(Hero character) {
		this.owner = character;
		for (CharacterGoods goods : goodsMap.values()) {
			// 易主
			goods.setCharacterId(character.getId());
			GoodsDataEntryManager.getInstance().asynUpdataCharacterGoods(character, goods);
		}

	}

	public void setHorse(Horse horse) {
		this.horse = horse;
	}

	public HorseBodyGoodsContiner() {
		beginPosition = Position.HorseGoodsBeginPostion;
		capacity = 4;
	}

	@Override
	public boolean checksuit(CharacterGoods fromGoods) {
		Goodmodel goodmodel = fromGoods.getGoodModel();
		int topostion = goodmodel.getPosition();
		if (topostion < Position.HorseGoodsBeginPostion || topostion > Position.HorseGoodsEndPostion) {
			owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 756));
			return false;
		}
		CharacterHorse characterhorse = horse.getCharacterHorse();
		if (goodmodel.getLimitGrade() > characterhorse.getGrade()) {
			//
			owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1012, goodmodel.getLimitGrade() + ""));
			return false;
		}
		return true;
	}

	@Override
	protected void onChangePositionGoodsAdd(CharacterGoods goods) {
		if ((goods.getPingzhiColor() == 4 && goods.getBind() == 0) || (goods.getBind() == 0 && goods.getGoodModel().getBinding() == BindChangeType.DRESSBIND)) {
			owner.sendMsg(new GoodsDelete10178(goods.getPosition(), horse.getId()));
			goods.setBind(CommonUseNumber.byte1);
			GoodsDataEntryManager.getInstance().asynUpdataCharacterGoods(owner, goods);
			owner.sendMsg(new HorseGoodsUpdata10078(goods));
		}
		if (horse.getCharacterHorse().getStatus() == HorseStateConsts.SHOW) {
			owner.getEquipmentController().changeProperty(owner, goods, TakeMethod.on);
		}
		owner.sendMsg(new HorseInfoResponse60006(owner, horse));
	}

	@Override
	protected void onChangePostionGoodsRemove(CharacterGoods goods) {
		if (horse.getCharacterHorse().getStatus() == HorseStateConsts.SHOW) {
			owner.getEquipmentController().changeProperty(owner, goods, TakeMethod.off);
		}
		owner.sendMsg(new HorseInfoResponse60006(owner, horse));
	}

	@Override
	protected void beforeGetGoods(CharacterGoods fromGoods) {
		fromGoods.setInHorseId(horse.getId());

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
