package net.snake.api.scene;

import net.snake.api.copy.ICopy;

public interface IHero {
	/**
	 * 当前所在的副本实例
	 * 
	 * @return
	 */
	public ICopy currentCopy();
	public void currentCopy(ICopy copy);
}
