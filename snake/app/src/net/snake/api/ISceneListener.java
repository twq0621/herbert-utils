package net.snake.api;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.Scene;

/**
 * 进出场景监听器
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public interface ISceneListener {
	public void onEnterScene(Hero hero, Scene scene);

	public void onLeaveScene(Hero hero, Scene scene, int x, int y);
}
