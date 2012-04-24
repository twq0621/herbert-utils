package net.snake.api.copy;

import net.snake.api.scene.IHero;

/**
 * 限制条件
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public interface ICondition {
	public boolean isOK(IHero hero);
	public void doIt(IHero hero);
}
