package net.snake.ai.fsm;
/**
 * 某个状态的抽象
 * @author serv_dev<br>
 * Copyright (c) 2011 Kingnet
 */
public interface IState {

	/**
	 * 当进入此AI状态时触发
	 */
	public void onBegin();

	/**
	 * 当需要更新时触发
	 * @param now
	 */
	public void onUpdate(long now);

	/**
	 * 当退出此AI状态时触发
	 */
	public void onExit();

	/**
	 * 重置到初始状态
	 */
	public void reset();

}
