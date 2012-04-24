package net.snake.gamemodel.across.bean;

import net.snake.gamemodel.across.response.LockActionStateMsg10600;
import net.snake.gamemodel.hero.bean.Hero;

public class AcrossLock {
	private boolean isLock = false;
	private long endLockTime = 0;

	public boolean isAcrossLock() {
		if (!isLock) {
			return isLock;
		}
		if (System.currentTimeMillis() > endLockTime) {
			isLock = false;
		}
		return isLock;
	}

	/**
	 * 角色行为 锁定一段时间：
	 * 
	 * @param lockTime
	 *            单位：毫秒
	 */
	public void lockCharacterAction(int lockTime, Hero character) {
		this.isLock = true;
		endLockTime = System.currentTimeMillis() + lockTime;
		character.sendMsg(new LockActionStateMsg10600(lockTime / 1000));
	}

	public void clearCharacterLockAction(Hero character) {
		this.isLock = false;
		endLockTime = 0;
		character.sendMsg(new LockActionStateMsg10600());
	}
}
