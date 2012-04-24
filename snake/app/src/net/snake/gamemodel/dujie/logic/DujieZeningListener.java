package net.snake.gamemodel.dujie.logic;

import net.snake.api.IStateListener;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.hero.bean.VisibleObject;

/**
 * 渡劫打坐状态拦截器
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class DujieZeningListener implements IStateListener {
	@Override
	public boolean beforeNewState(VisibleObject hero, int state) {
		if (hero.getSceneRef().getInstanceModelId()!=9) {
			return true;
		}
		if (state == VisibleObjectState.Dazuo) {
			return false;
		}
		return true;
	}
}
