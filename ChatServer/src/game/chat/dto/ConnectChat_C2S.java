package game.chat.dto;

import lion.core.Amf3BaseDTO;

public class ConnectChat_C2S extends Amf3BaseDTO {

	private String roleName;

	private String sid;

	private String userName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
