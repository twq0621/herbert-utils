package game.dto;

import lion.core.Amf3BaseDTO;

public class CreateRole_C2S extends Amf3BaseDTO {

	/** 角色名 */
	private String roleName;

	/** 职业类型id */
	private int characterId;

	/** 性别 */
	private int gender;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

}
