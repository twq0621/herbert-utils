package net.snake.gamemodel.dujie.logic;


import net.snake.api.ISceneListener;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.map.logic.Scene;

/**
 * 进出渡劫副本的的拦截器。
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class DujieSceneListener implements ISceneListener {
	public void onEnterScene(Hero hero, Scene scene) {
		
	}

	@Override
	public void onLeaveScene(Hero hero, Scene scene,int x,int y) {
		
		Object buffObject = scene.getInstanceController().getAttribute("zhenBuff");
		if (buffObject == null) {
			return;
		}
		hero.getPropertyAdditionController().removeChangeListener((PropertyChangeListener) buffObject);
		scene.getInstanceController().removeAttribute("zhenBuff");
		
		
	}
}
