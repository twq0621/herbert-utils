package net.snake.api.copy;

import net.snake.api.context.IServerContext;
import net.snake.api.scene.IHero;
import net.snake.api.scene.IMonster;
import net.snake.api.scene.IScene;

public interface ICopyListener {
	/**
	 * 当申请到副本
	 */
	public void onAlloc(IServerContext context,ICopy copy,IHero hero);
	/**
	 * 当副本被释放掉
	 */
	public void onFree(IServerContext context,ICopy copy,IHero hero);
	/**
	 * 当开始副本
	 */
	public void onStartCopy(IServerContext context,ICopy copy,IHero hero);
	/**
	 * 当初退出副本
	 */
	public void onExitCopy(IServerContext context,ICopy copy,IHero hero);
	/**
	 * 当进入副本场景
	 */
	public void onEnterCopyScene(IServerContext context,ICopy copy,IScene scene,IHero hero);
	/**
	 * 当离开副本场景
	 */
	public void onExitCopyScene(IServerContext context,ICopy copy,IScene scene,IHero hero);
	/**
	 * 当初始化场景
	 */
	public void onInitCopyScene(IServerContext context,ICopy copy,IScene scene,IHero hero);
	
	/**
	 * 当玩家死亡
	 */
	public void onHeroDie(IServerContext context,ICopy copy,IScene scene,IHero hero);
	/**
	 * 当怪物死亡
	 */
	public void onMonsterDie(IServerContext context,ICopy copy,IHero hero,IScene scene,IMonster monster);
	/**
	 * 当怪物受伤
	 */
	public void onMonsterInjured(IServerContext context,ICopy copy,IScene scene,IHero hero,IMonster monster);
}
