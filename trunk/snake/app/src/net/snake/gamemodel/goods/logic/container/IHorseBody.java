package net.snake.gamemodel.goods.logic.container;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.pet.logic.Horse;

public interface IHorseBody extends IContainer {
	public boolean checksuit(CharacterGoods goods);
	public Horse getHorse();
}
