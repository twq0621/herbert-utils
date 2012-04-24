package net.snake.gamemodel.hero.bean;

import net.snake.gamemodel.pet.logic.Horse;

/**
 * 该类用于保存角色休克时的状态
 * 
 * @author serv_dev
 * 
 */
public class CharacterShockData {
	public Horse ridehorse;
	public int rideHorseLostLiving;
	public Horse showhorse;
	public int showHorseLostLiving;
	public long shockTime;//死亡时间
	
	public void clear() {
		ridehorse = null;
		showhorse = null;
		rideHorseLostLiving=0;
		showHorseLostLiving=0;
		shockTime = 0l;
	}
}
