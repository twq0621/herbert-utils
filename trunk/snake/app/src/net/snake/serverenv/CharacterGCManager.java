package net.snake.serverenv;

import net.snake.gamemodel.hero.bean.Hero;

/**
 * 用于角色垃圾收集的管理器
 * 由于玩家下线后，在很短的一段时间内，玩家的引用仍被使用(比如需要用这个引用构造离线消息发给其他玩家)
 * 因此不能马上对角色进行清理操作，因此有了这个类进行延迟的垃圾收集
 * 垃圾收集时对角色内部模块的一些相互引用进行清空
 * 
 * @author serv_dev
 * 
 */
public interface CharacterGCManager {

	public abstract void start();

	/**
	 * 将角色加到的垃圾收集队列中去　
	 * 
	 * @param character
	 */
	public abstract void addCharacterToGC(Hero character);

}
