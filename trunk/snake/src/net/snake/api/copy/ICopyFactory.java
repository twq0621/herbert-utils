package net.snake.api.copy;

import net.snake.api.scene.IHero;

public interface ICopyFactory {
	public ICopy allocCopy(int copyId,IHero hero);
	public void freeCopy(ICopy copy);
}
