package game.chat.dto;

import cn.hxh.dto.Amf3BaseDTO;

public class ConnectChat_C2S extends Amf3BaseDTO {

	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
