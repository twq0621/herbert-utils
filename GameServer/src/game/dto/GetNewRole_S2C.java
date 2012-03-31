package game.dto;

import java.io.Serializable;

import lion.core.Amf3BaseDTO;

public class GetNewRole_S2C extends Amf3BaseDTO implements Serializable {

	private static final long serialVersionUID = 3039831360347561327L;

	private int roleCount;

	public int getRoleCount() {
		return roleCount;
	}

	public void setRoleCount(int roleCount) {
		this.roleCount = roleCount;
	}

}
