package net.snake.gamemodel.map.logic;

import net.snake.api.ISceneListener;
import net.snake.gamemodel.hero.bean.Hero;

/**
 * 当离开场景时记录下上一个场景的离开点
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class RecordPrepositionListener implements ISceneListener {
	@Override
	public void onEnterScene(Hero hero, Scene scene) {

	}

	@Override
	public void onLeaveScene(Hero hero, Scene scene, int x, int y) {
		if (scene.getInstanceModelId() != 0) {
			return;
		}

		hero.worldPos[0] = scene.getId();
		hero.worldPos[1] = x ;
		hero.worldPos[2] = y ;
	}
}
