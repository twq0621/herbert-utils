package net.snake.gamemodel.goods.logic.container;

import net.snake.consts.Position;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.persistence.GoodsDataEntryManager;


public class StorageGoodsContainer extends AbstractGirdContainer implements IStorage{
	public StorageGoodsContainer() {
		beginPosition = Position.StorageGoodsBeginPostion;
	}
	@Override
	public boolean checksuit(CharacterGoods goods) {
		return true;
	}
	public void clear(){
		goodsMap.clear();
		GoodsDataEntryManager.getInstance().asynDeleteCharacterGoods(owner,beginPosition,capacity);
	}
	@Override
	protected void onChangePositionGoodsAdd(CharacterGoods goods) {

	}

	@Override
	protected void onChangePostionGoodsRemove(CharacterGoods goods) {

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
	}

}
