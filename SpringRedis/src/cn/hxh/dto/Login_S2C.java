package cn.hxh.dto;

import java.util.Set;

public class Login_S2C extends Amf3BaseDTO {

	private Set<RoleDTO> roleList;

	public Set<RoleDTO> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<RoleDTO> roleList) {
		this.roleList = roleList;
	}

}
