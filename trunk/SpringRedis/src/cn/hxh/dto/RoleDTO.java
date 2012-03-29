package cn.hxh.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class RoleDTO {

	private String name;

	private int characterId;

	private int gender;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCharacterId() {
		return characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
