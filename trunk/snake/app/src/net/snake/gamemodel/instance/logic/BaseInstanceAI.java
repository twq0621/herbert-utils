package net.snake.gamemodel.instance.logic;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.Teleport;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.task.bean.Task;
/**
 * 适配器配置,防止每个脚本都需要实现所有接口
 * @author serv_dev
 */
public class BaseInstanceAI implements InstanceAI {
	protected InstanceAPI api;
	

	@Override
	public void setApi(InstanceAPI instancecontext) {
		this.api=instancecontext;
	}
	@Override
	public InstanceAPI getApi() {
		return api;
	}
	@Override
	public void onCompleteTask(Hero character, Task task) {
	}

	@Override
	public void onDie(Hero character) {
	}

	@Override
	public void onDie(SceneMonster scenemonster) {
	}

	@Override
	public void onMove(Hero character) {
	}

	@Override
	public void onMove(SceneMonster sceneMonster) {
	}

	@Override
	public void onInstanceInit() {
	}
	@Override
	public void onTimeEvent() {
	}
	@Override
	public void onEnterInstanceScene(Hero character) {
		
	}
	@Override
	public boolean onStandHideTelport(Hero character, int sceneid,
			Teleport teleport) {
		return false;
	}

}
