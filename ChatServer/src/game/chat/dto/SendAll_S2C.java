package game.chat.dto;

import lion.core.Amf3BaseDTO;

public class SendAll_S2C extends Amf3BaseDTO {

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
