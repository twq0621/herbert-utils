package game.dto;

import lion.core.Amf3BaseDTO;

public class Login_C2S extends Amf3BaseDTO {

	private String name;

	private String pwd;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
