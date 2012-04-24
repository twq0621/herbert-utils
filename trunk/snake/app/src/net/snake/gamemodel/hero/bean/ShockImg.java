package net.snake.gamemodel.hero.bean;

/**
 * 描述了哪一个将哪一个杀至濒死
 * 
 * @author serv_dev Copyright (c) 2011 Kingnet
 */
public class ShockImg {
	public int killerId;
	public Class<?> killerType;
	public int shockedId;
	public Class<?> shockedType;
	public long shockTimestamp;

	public ShockImg() {
		this.killerId = -1;
		this.killerType = null;
		this.shockedId = -1;
		this.shockedType = null;
		this.shockTimestamp = -1;
	}
}
