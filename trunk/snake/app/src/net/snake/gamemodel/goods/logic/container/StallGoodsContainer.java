package net.snake.gamemodel.goods.logic.container;

import net.snake.consts.Position;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.serverenv.stall.OnlineStallManagerImp;

public class StallGoodsContainer extends AbstractGirdContainer implements IStall {
	public StallGoodsContainer() {
		beginPosition = Position.StallGoodsBeginPostion;
		setTotalCapacity(10);
	}

	@Override
	public boolean checksuit(CharacterGoods goods) {
		if (goods.getBind() == 1) {
			owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 757));
			return false;
		}
		return true;
	}

	@Override
	protected void onChangePositionGoodsAdd(CharacterGoods goods) {
		OnlineStallManagerImp.getInstance().checkCharacterStall(owner);
	}

	@Override
	protected void onChangePostionGoodsRemove(CharacterGoods goods) {
		OnlineStallManagerImp.getInstance().checkCharacterStall(owner);
	}

	@Override
	protected void beforeGetGoods(CharacterGoods fromGoods) {
		fromGoods.setInHorseId(0);
	}

	@Override
	public short getSuitPostionForGoods(CharacterGoods fromGoods, IContainer fromContainer) {
		return findFirstIdleGirdPosition();
	}

	@Override
	protected void onGoodsAdd(CharacterGoods goods) {
	}

	@Override
	protected void onGoodsRemove(CharacterGoods goods) {
		OnlineStallManagerImp.getInstance().checkCharacterStall(owner);
	}

}
