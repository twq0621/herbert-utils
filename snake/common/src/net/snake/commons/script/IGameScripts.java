package net.snake.commons.script;
/**
 * 应用程序对脚本实现透明，此类沟通了应用程序程序和脚本实现。
 * @author serv_dev
 * Copyright (c) 2011 Kingnet
 */

public interface IGameScripts {
	/**
	 * 获取运行时的脚本全类名
	 * @return
	 */
	public String[] getRuntimeScripts();
}
