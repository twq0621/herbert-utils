package lion.dto;

import java.io.Serializable;

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
