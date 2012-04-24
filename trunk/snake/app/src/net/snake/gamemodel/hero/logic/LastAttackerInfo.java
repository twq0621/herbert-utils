package net.snake.gamemodel.hero.logic;

import net.snake.commons.program.Updatable;
import net.snake.gamemodel.hero.bean.VisibleObject;

public class LastAttackerInfo implements Updatable {
	// 保存最后攻击我的人的信息
	private VisibleObject attacker;
	private long attackTime;
	private int skill;
	private short x;
	private short y;

	public void clear() {
		attacker = null;
	}

	public void setAttackInfo(VisibleObject attacker, int skill) {
		this.attacker = attacker;
		attackTime = System.currentTimeMillis();
		x = attacker.getX();
		y = attacker.getY();
		this.skill = skill;
	}

	public VisibleObject getAttacker() {
		return attacker;
	}

	public long getAttackTime() {
		return attackTime;
	}

	public int getSkill() {
		return skill;
	}

	public short getX() {
		return x;
	}

	public short getY() {
		return y;
	}

	/**
	 * 一段时间后自动清空attacker引用 防内存泄露
	 */
	@Override
	public void update(long now) {
		if (now - attackTime > 60 * 1000) {// 60秒
			attacker = null;
		}

	}
}
