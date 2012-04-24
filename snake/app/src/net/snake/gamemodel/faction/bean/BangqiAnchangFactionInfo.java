package net.snake.gamemodel.faction.bean;
/**
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */

public class BangqiAnchangFactionInfo{
	private int factionId;
	private int anchaCount;
	private String factionName;

	/**
	 * @param id
	 * @param viewName
	 * @param i
	 */
	public BangqiAnchangFactionInfo(Integer id, String viewName, int i) {
		this.factionId = id;
		this.factionName = viewName;
		this.anchaCount = i;
	}

	public int getFactionId() {
		return factionId;
	}

	public void setFactionId(int factionId) {
		this.factionId = factionId;
	}

	public int getAnchaCount() {
		return anchaCount;
	}

	public void setAnchaCount(int anchaCount) {
		this.anchaCount = anchaCount;
	}

	public String getFactionName() {
		return factionName;
	}

	public void setFactionName(String factionName) {
		this.factionName = factionName;
	}

}
