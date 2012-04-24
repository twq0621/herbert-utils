package net.snake.commons.script;
public interface IAppEventListeners {
	/**
	 * 获取运行时的监听器全类名
	 * @return
	 */
	public String[] getRuntimeListeners();
	/**
	 * 获取运行时的公式全类名
	 * @return
	 */
	public String[] getRuntimeFormulas();
}
