package net.snake.gamemodel.fight.bean;

/**
 * @author serv_dev
 */
public class GrandPrix {
	String reward;
	byte canTake;
	int iconId;

	public GrandPrix(String content, boolean take, int ico) {
		this.reward = content;
		this.canTake = (byte) (take ? 1 : 0);
		this.iconId = ico;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String content) {
		this.reward = content;
	}

	public byte getCanTake() {
		return canTake;
	}

	public void setCanTake(byte take) {
		this.canTake = take;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int ico) {
		iconId = ico;
	}

}
