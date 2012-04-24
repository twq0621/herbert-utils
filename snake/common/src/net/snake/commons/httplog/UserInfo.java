package net.snake.commons.httplog;

/**
 * @description 内部日志所需的用户基本信息
 * @author dev
 */
public class UserInfo {

	/**
	 * 用户唯一标识
	 */
	private String userId;

	/**
	 * 用户IP
	 */
	private String userIp;

	/**
	 * 操作的游戏角色ID
	 */
	private String characterId;

	/**
	 * 角色等级
	 */
	private Integer characterGrade;

	/**
	 * 角色所属的服务器ID
	 */
	private String sid;

	private String characterName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getCharacterId() {
		return characterId;
	}

	public void setCharacterId(String characterId) {
		this.characterId = characterId;
	}

	public Integer getCharacterGrade() {
		return characterGrade;
	}

	public void setCharacterGrade(Integer characterGrade) {
		this.characterGrade = characterGrade;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

}
