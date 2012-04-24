package net.snake.gamemodel.hero.bean;

import net.snake.ibatis.IbatisEntity;

public class CharacterPropcd implements IbatisEntity {

	private Integer id;

	private Integer characterId;

	private Integer cdType;

	private Integer cdId;

	private Long useTime;

	private Integer totalTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCharacterId() {
		return characterId;
	}

	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	public Integer getCdType() {
		return cdType;
	}

	public void setCdType(Integer cdType) {
		this.cdType = cdType;
	}

	public Integer getCdId() {
		return cdId;
	}

	public void setCdId(Integer cdId) {
		this.cdId = cdId;
	}

	public Long getUseTime() {
		return useTime;
	}

	public void setUseTime(Long useTime) {
		this.useTime = useTime;
	}

	public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}
}
