package net.snake.gamemodel.skill.bean;

/**
 * 技能公共冷却时间
 * @author serv_dev
 *
 */
public class CommonTime {

	private long commonStartCD;//公共冷却时间开始
	private int intervalTime;//间隔时间
	
	public long getCommonStartCD() {
		return commonStartCD;
	}
	public void setCommonStartCD(long commonStartCD) {
		this.commonStartCD = commonStartCD;
	}
	
	public int getIntervalTime() {
		return intervalTime;
	}
	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}
	public boolean isArrivedTime(){
		return (System.currentTimeMillis() - commonStartCD) > intervalTime;
	}
	
	
}
