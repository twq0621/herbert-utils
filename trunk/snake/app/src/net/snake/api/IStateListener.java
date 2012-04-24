package net.snake.api;

import net.snake.gamemodel.hero.bean.VisibleObject;

/**
 * 改变状态时拦截
 * @author serv_dev<br>
 * Copyright (c) 2011 Kingnet
 */
public interface IStateListener {
	/**
	 * 是否继续
	 * @param hero
	 * @param state
	 * @return
	 */
	public boolean beforeNewState(VisibleObject object,int state);
}
