package net.snake.gamemodel.fight.bean;

/**
 * @author serv_dev
 */
public class RichPlayRule {
	// 丰富玩法总数byte，{名称string，简介string，是否完成byte（0否/1是）}}
	String name;
	String brief;
	byte complt;
	short lvl;

	public RichPlayRule(String nm, String brf, boolean comp, int level) {
		this.name = nm;
		this.brief = brf;
		this.complt = (byte) (comp ? 1 : 0);
		this.lvl = (short) level;
	}

	public String getName() {
		return name;
	}

	public void setName(String nm) {
		this.name = nm;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brf) {
		this.brief = brf;
	}

	public byte getComplt() {
		return complt;
	}

	public void setComplt(byte comp) {
		this.complt = comp;
	}

	public short getLvl() {
		return lvl;
	}

	public void setLvl(short level) {
		this.lvl = level;
	}

}
