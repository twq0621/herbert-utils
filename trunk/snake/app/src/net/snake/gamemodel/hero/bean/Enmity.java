package net.snake.gamemodel.hero.bean;

import net.snake.commons.script.SEnmity;

/**
 * 仇恨
 * 
 * @author serv_dev
 */
public class Enmity implements SEnmity, Comparable<Enmity> {
	private VisibleObject target;// 仇恨目标
	private int enmityValue;// 对目标的仇恨值
	private int hurt;// 对目标的伤害值
	private long lastHurtTime;

	public VisibleObject getTarget() {
		return target;
	}

	public Enmity(VisibleObject target) {
		this.target = target;
	}

	public void addHurt(int hurt) {
		this.hurt = (this.getHurt() + hurt);
		lastHurtTime = System.currentTimeMillis();
	}

	public long getLastHurtTime() {
		return lastHurtTime;
	}

	public int getHurt() {
		return hurt;
	}

	public void addEnmityValue(int enmityValue) {
		this.enmityValue = (this.enmityValue + enmityValue);
	}

	public int getEnmityValue() {
		return enmityValue;
	}

	@Override
	public int compareTo(Enmity o) {
		return this.enmityValue < o.enmityValue ? 1 : ((this.enmityValue == o.enmityValue) ? 0 : -1);
	}
}
