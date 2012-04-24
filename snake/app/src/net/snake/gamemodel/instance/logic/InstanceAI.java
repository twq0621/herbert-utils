package net.snake.gamemodel.instance.logic;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.Teleport;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.task.bean.Task;

/**
 * 副本AI判断
 * 
 * @author serv_dev
 * 
 */
public interface InstanceAI {
	/**
	 * 设置副本上下文
	 */
	void setApi(InstanceAPI instancecontext);
	InstanceAPI getApi();
	/**
	 * 当完成任务时
	 * 
	 * @param character
	 * @param task
	 */
	void onCompleteTask(Hero character, Task task);
	
	//当站在隐藏传送点上时,返回是否可以传送
	boolean onStandHideTelport(Hero character,int sceneid,Teleport currentscene);
	
	/**
	 * 当移动,可以此类时判断移动到那里了
	 * 
	 * @param character
	 */
	void onMove(Hero character);

	void onMove(SceneMonster sceneMonster);

	/**
	 * 当死亡之后
	 * 
	 * @param scenemonster
	 */
	public void onDie(Hero character);

	public void onDie(SceneMonster scenemonster);
	/**
	 * 每次刷桢时调用
	 */
	public void onTimeEvent();
	/**
	 * 当副本初始化时
	 */
	public void onInstanceInit();
	/**
	 * 当玩家进入副本时调用
	 * @param character
	 */
	public void onEnterInstanceScene(Hero character);	

}
