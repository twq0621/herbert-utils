package game.dto;

import game.entity.Role;

import java.util.Set;

import lion.core.Amf3BaseDTO;

public class Login_S2C extends Amf3BaseDTO {

	private Set<Role> roleList;

	public Set<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<Role> roleList) {
		this.roleList = roleList;
	}

}
