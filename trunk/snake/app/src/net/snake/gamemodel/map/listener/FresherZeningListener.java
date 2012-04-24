package net.snake.gamemodel.map.listener;

import net.snake.api.IStateListener;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.hero.bean.VisibleObject;

public class FresherZeningListener implements IStateListener {
	@Override
	public boolean beforeNewState(VisibleObject object, int state) {
		if (object.getSceneRef().getInstanceModelId() != 16) {
			return true;
		}
		if (state == VisibleObjectState.Dazuo) {
			return false;
		}
		return true;
	}
}
