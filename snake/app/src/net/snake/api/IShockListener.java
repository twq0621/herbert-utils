package net.snake.api;

import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.logic.Scene;

public interface IShockListener {
	public boolean onShocked(VisibleObject object,VisibleObject attacker, Scene scene);
}
