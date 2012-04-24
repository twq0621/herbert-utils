package net.snake.gamemodel.skill.bean;

import net.snake.ibatis.IbatisEntity;

public class ContinuumKillDataEntry implements IbatisEntity {

	private Integer id;
	private Integer killCountFrom;
	private Integer killCountTo;
	private Integer bufId;
	private Integer appellationId;
	private Integer countDown;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKillCountFrom() {
		return killCountFrom;
	}

	public void setKillCountFrom(Integer killCountFrom) {
		this.killCountFrom = killCountFrom;
	}

	public Integer getKillCountTo() {
		return killCountTo;
	}

	public void setKillCountTo(Integer killCountTo) {
		this.killCountTo = killCountTo;
	}

	public Integer getBufId() {
		return bufId;
	}

	public void setBufId(Integer bufId) {
		this.bufId = bufId;
	}

	public Integer getAppellationId() {
		return appellationId;
	}

	public void setAppellationId(Integer appellationId) {
		this.appellationId = appellationId;
	}

	public Integer getCountDown() {
		return countDown * 1000;
	}

	public void setCountDown(Integer countDown) {
		this.countDown = countDown;
	}
}
