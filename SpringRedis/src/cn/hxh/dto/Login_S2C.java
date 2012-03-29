package cn.hxh.dto;

import java.util.Set;

public class Login_S2C extends Amf3BaseDTO {

	private Set<RoleDto> roleList;

	public Set<RoleDto> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<RoleDto> roleList) {
		this.roleList = roleList;
	}

}
