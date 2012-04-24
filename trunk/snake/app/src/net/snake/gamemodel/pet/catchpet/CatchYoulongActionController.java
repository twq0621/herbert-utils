package net.snake.gamemodel.pet.catchpet;

import net.snake.commons.program.SafeTimer;
import net.snake.commons.program.Updatable;
import net.snake.gamemodel.fight.response.CatchYoulongDelay51124;
import net.snake.gamemodel.fight.response.CatchYoulongOK51126;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.SceneFactionCtMonster;

public class CatchYoulongActionController implements Updatable {
	// 捕获开始计时的时间
	private SafeTimer safetime = new SafeTimer();
	// 要捕获的目标在捕获前都是怪物
	private SceneFactionCtMonster target;
	private Hero character;
	private boolean isStart;

	public void destory() {
		target = null;
	}

	public int getAllObjInHeap() throws Exception {
		if (target != null) {
			return target.getWhoCatchYoulongManager().getCharactersList().size() + (target.getYoulongCharacter() != null ? 1 : 0);
		}
		return 0;
	}

	public void startTime(SceneMonster target) {
		if (isStart) {
			return;
		}
		isStart = true;
		int type = target.getMonsterModel().getType();
		if (type == 6) {
			// 增加捕获的人
			SceneFactionCtMonster monster = (SceneFactionCtMonster) target;
			this.target = monster;
			monster.getWhoCatchYoulongManager().addCatcher(character);
			safetime.start(10000);
			// 开始时间
			character.sendMsg(new CatchYoulongDelay51124(target.getId(), 10));
			character.getUpdateObjManager().addFrameUpdateObject(this);
		}
	}

	public CatchYoulongActionController(Hero character) {
		this.character = character;
	}

	// 被别的模块调用，打断
	public void breakCatch() {
		if (!isStart) {
			return;
		}
		isStart = false;
		character.sendMsg(new CatchYoulongOK51126(target.getId(), 14510));
		target.getWhoCatchYoulongManager().removeCatcher(character);
		character.getUpdateObjManager().removeFrameUpdateObject(this);
		isStart = false;
	}

	@Override
	public void update(long now) {
		// 第一次时间到了
		if (safetime.isFirstOK(now)) {
			// 发送捕获请求
			character.getUpdateObjManager().removeFrameUpdateObject(this);
			target.getWhoCatchYoulongManager().catchTimeOK(character);
			this.isStart = false;
			// 移除时间
		}
	}

	/**
	 * 玩家处于拔剑状态
	 * 
	 * @return
	 */
	public boolean isCatchYoulongState() {
		return isStart;
	}
}
