package cn.hxh.dto;

import java.io.Serializable;
import java.util.List;

public class GetOnlineNames_S2C extends Amf3BaseDTO implements Serializable {

	private static final long serialVersionUID = -6623711729839088098L;

	private List<String> roleNames;

	public List<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}

}
