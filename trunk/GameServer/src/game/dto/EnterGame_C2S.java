package game.dto;

import lion.core.Amf3BaseDTO;

public class EnterGame_C2S extends Amf3BaseDTO {

	private String selectedRole;

	public String getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(String selectedRole) {
		this.selectedRole = selectedRole;
	}

}
