package net.snake.api;

import net.snake.gamemodel.hero.bean.VisibleObject;

public interface IBuffListneer {
	public void onBuff(VisibleObject object,short property, int value);
}
