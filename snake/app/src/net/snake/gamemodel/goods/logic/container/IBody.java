package net.snake.gamemodel.goods.logic.container;

import net.snake.gamemodel.goods.bean.CharacterGoods;


public interface IBody extends IContainer {
	void initEquipPropertiesToCharacter();
	public boolean checksuit(CharacterGoods fromGoods);
	public boolean checksuit(CharacterGoods fromGoods, boolean sendsuitmsg);
}
